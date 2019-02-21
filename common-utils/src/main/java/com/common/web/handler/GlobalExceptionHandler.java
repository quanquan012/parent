package com.common.web.handler;

import com.common.web.Message;
import com.common.web.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public Message basicException(BusinessException exception){
        return Message.error(exception.getMessage());
    }
}
