package com.client.util;

import com.common.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 房间Redis工具类
 *
 * @author lihao
 * @date 2018-12-07 13:33
 */
@Component
public class RoomRedisUtils {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 加入房间
     *
     * @param roomId
     */
    public void joinRoom(String roomId, String openId) {
        if (redisUtil.hasKey(roomId)) {
            redisUtil.sSet(roomId, openId);
        }
    }

    /**
     * 销毁房间
     *
     * @param roomId
     */
    public void destoryRoom(String roomId){
        if (redisUtil.hasKey(roomId)) {
            redisUtil.del(roomId);
        }
    }
}