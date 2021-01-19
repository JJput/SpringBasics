package com.twj.spirngbasics.server.exception;

import com.twj.spirngbasics.server.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.twj.spirngbasics.server.util.Constant.Http.ERROR.*;

/**
 * 统一异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常 长度或对象为空
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ValidatorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleValidatorException(ValidatorException exception) {
        return ResponseDto.createByFail(exception);
    }

    /**
     * 自定义异常 业务异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleBusinessException(BusinessException exception) {
        return ResponseDto.createByFail(exception);
    }

    /**
     * hibernate-validator model校验失败时
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        ResponseDto response = ResponseDto.createByFail(new ValidatorException(VIOLATION_EXCEPTION));
        response.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        return response;
    }

    /**
     * hibernate-validator 常用于get请求校验失败时
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleConstraintViolationException(ConstraintViolationException exception) {
        StringBuilder errorInfo = new StringBuilder();
        String errorMessage;

        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        for (ConstraintViolation<?> item : violations) {
            errorInfo.append(item.getMessage()).append(",");
        }
        errorMessage = errorInfo.substring(0, errorInfo.toString().length() - 1);

        ResponseDto response = ResponseDto.createByFail(new ValidatorException(VIOLATION_EXCEPTION));
        response.setMessage(errorMessage);
        return response;
    }

    /**
     * DynamicSql查询为空
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleNoSuchElementException(NoSuchElementException exception) {
        ResponseDto responseDto = ResponseDto.createByFail(OBJECT_NULL);
        responseDto.setMessage(exception.getMessage());
        return responseDto;
    }

    /**
     * 通用异常错误
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleDefaultException(Exception exception) {
        exception.printStackTrace();
        ResponseDto responseDto = ResponseDto.createByFail(DEFAULT);
        responseDto.setMessage(exception.getMessage());
        return responseDto;
    }
}
