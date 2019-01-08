package com.client.service;

import com.common.web.MessageConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lihao
 * @date 2018-12-06 17:46
 */
@ServerEndpoint(value = "/wx/{roomId}/{openId}")
@Component
@Slf4j
public class WxWebSocket {

    private String openId;

    private Session session;

    /**
     * current online users
     */
    private volatile AtomicInteger onlineCount = new AtomicInteger(0);
    /**
     * 当前服务的房间Map<RoomId, WebSockets>
     */
    private static Map<String, Set<WxWebSocket>> roomsMap = new ConcurrentHashMap<>(200);

    @OnOpen
    public void onOpen(@PathParam("roomId") String roomId, @PathParam("openId") String openId, Session session) {
        log.info("{} 进入连接..", openId);
        setOpenId(openId);
        setSession(session);
        increase();
        organizeRooms(roomId);
        log.info("有新连接加入！当前在线人数为{} -- 当前房间人数:{}", getOnlineCount(), roomsMap.get(roomId).size());
        try {
            sendMessage(MessageConstant.SOCKET_CONNECTED);
        } catch (IOException e) {
            log.error("IO异常");
        }
    }

    @OnClose
    public void onClose(@PathParam("roomId") String roomId, @PathParam("openId") String openId, Session session) {
        decrease();
        removeRoom(roomId);
        log.info("有一连接关闭！当前在线人数为:{}" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息:{}" + message);
    }

    @OnError
    public void onError(Throwable error) {
        log.error("发生错误{}", error);
        try {
            this.getSession().close();
        } catch (IOException e) {
            log.error("连接关闭异常{}", e);
        }
    }

    private void sendMessage(String message) throws IOException {
        this.getSession().getBasicRemote().sendText(message);
    }

    private void sendInfo(String message) throws IOException {

    }

    private int getOnlineCount() {
        return this.onlineCount.get();
    }

    private void increase() {
        this.onlineCount.incrementAndGet();
    }

    private void decrease() {
        this.onlineCount.decrementAndGet();
    }

    /**
     * 整理rooms
     *
     * @return
     */
    private void organizeRooms(String roomId) {
        Set<WxWebSocket> roomSet = roomsMap.get(roomId);
        if (null == roomSet) {
            Set<WxWebSocket> sockets = new CopyOnWriteArraySet<>();
            sockets.add(this);
            roomsMap.put(roomId, sockets);
        } else {
            roomSet.add(this);
        }
    }

    /**
     * 移除room
     *
     * @param roomId
     */
    private void removeRoom(String roomId) {
        Set<WxWebSocket> roomSet = roomsMap.get(roomId);
        releaseLinks(roomSet);
        roomsMap.remove(roomId);
    }

    /**
     * 服务端主动释放连接
     *
     * @param roomSet
     */
    private void releaseLinks(Set<WxWebSocket> roomSet){
        Iterator<WxWebSocket> iterator = roomSet.iterator();
        while (iterator.hasNext()){
            try {
                iterator.next().getSession().close();
            } catch (IOException e) {
                continue;
            }
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}