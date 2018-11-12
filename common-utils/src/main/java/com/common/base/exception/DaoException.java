package com.common.base.exception;

/**
 * DAO层数据异常
 *
 * @author lihao
 * @date 2018/11/12
 */
public class DaoException extends RuntimeException {
    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }


    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
