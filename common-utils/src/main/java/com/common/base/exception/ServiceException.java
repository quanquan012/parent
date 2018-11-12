package com.common.base.exception;

/**
 * Service 层异常
 *
 * @author lihao
 * @date 2018/11/12
 */
public class ServiceException extends RuntimeException{
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }


    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
