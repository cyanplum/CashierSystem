package org.uppower.project.cashiermanagesystem.exceptions;

import cn.windyrjc.utils.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ServerException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Response allExceptionHandler(HttpServletRequest request, Exception exception) {
        return Response.fail(exception.getMessage());
    }


}
