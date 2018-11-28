package com.common.base.es.factory;

import org.elasticsearch.action.update.UpdateRequest;

/**
 * @author lihao
 * @date 2018-11-21 15:58
 */
public class UpdateRequestSingletonFactory {

    private static final UpdateRequest UPDATE_REQUEST = new UpdateRequest();

    public static UpdateRequest getUpdateRequest() {
        return UPDATE_REQUEST;
    }

}
