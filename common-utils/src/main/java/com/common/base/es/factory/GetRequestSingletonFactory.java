package com.common.base.es.factory;

import org.elasticsearch.action.get.GetRequest;

/**
 * @author lihao
 * @date 2018-11-21 15:58
 */
public class GetRequestSingletonFactory {

    private static final GetRequest GET_REQUEST = new GetRequest();

    public static GetRequest getGetRequest(){
        return GET_REQUEST;
    }

}
