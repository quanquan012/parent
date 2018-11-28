package com.common.base.es.factory;

import org.elasticsearch.action.delete.DeleteRequest;

/**
 *
 * @author lihao
 * @date 2018-11-21 15:58
 */
public class DeleteRequestSingletonFactory {

    private static final DeleteRequest DELETE_REQUEST = new DeleteRequest();

    public static DeleteRequest getIndexRequest(){
        return DELETE_REQUEST;
    }

}
