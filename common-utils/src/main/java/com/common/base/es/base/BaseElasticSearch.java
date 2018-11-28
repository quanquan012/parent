package com.common.base.es.base;

import com.common.base.es.factory.DeleteRequestSingletonFactory;
import com.common.base.es.factory.GetRequestSingletonFactory;
import com.common.base.es.factory.IndexRequestSingletonFactory;
import com.common.base.es.factory.UpdateRequestSingletonFactory;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;

/**
 * @author lihao
 * @date 2018-11-21 15:58
 */
public abstract class BaseElasticSearch<T> {

    protected IndexRequest indexRequest = IndexRequestSingletonFactory.getIndexRequest();

    protected GetRequest getRequest = GetRequestSingletonFactory.getGetRequest();

    protected UpdateRequest updateRequest = UpdateRequestSingletonFactory.getUpdateRequest();

    protected DeleteRequest deleteRequest = DeleteRequestSingletonFactory.getIndexRequest();


}