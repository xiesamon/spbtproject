package com.samon.spbt.aop;

import com.samon.spbt.listener.Customlister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 同一controller 异常返回
 */
@ControllerAdvice
public class CommonExceptionHandler {

    private final static Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);
    /**
     *  拦截Exception类的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> exceptionHandler(Exception e){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("respCode", "9999");
        result.put("respMsg", e.getMessage());
        //正常开发中，可创建一个统一响应实体，如CommonResp
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,Object> handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("respCode", "01002");
        result.put("respMsg", fieldError.getDefaultMessage());
        return result;
    }

    @ExceptionHandler(BindException.class)
    public Map<String,Object> handleBindException(BindException ex) {
        //校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("respCode", "01002");
        result.put("respMsg", fieldError.getDefaultMessage());
        return result;
    }

}
