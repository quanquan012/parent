package com.common.base.es.factory;

import org.elasticsearch.action.index.IndexRequest;

/**
 * @author lihao
 * @date 2018-11-21 15:58
 */
public class IndexRequestSingletonFactory {

    private static final IndexRequest INDEX_REQUEST = new IndexRequest();

    public static IndexRequest getIndexRequest(){
        return INDEX_REQUEST;
    }

}
