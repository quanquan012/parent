package com.common.utils.properities;

import org.apache.http.HttpHost;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * ES 配置属性解析工具 es: hosts: ['ip:port'...]
 *
 * @author lihao
 * @date 2018-11-21 15:21
 */
public class ElasticSearchProperitiesUtils {

    private static final List<HttpHost> list = new ArrayList<>();

    public static List<HttpHost> getHosts(String hosts){
        System.out.println(hosts);
        return list;
    }

}