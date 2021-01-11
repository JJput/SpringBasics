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
import java.util.Set;

import static com.twj.spirngbasics.server.util.Constant.Http.ERROR.DEFAULT;
import static com.twj.spirngbasics.server.util.Constant.Http.ERROR.VIOLATION_EXCEPTION;

/**
 * 统一异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        ResponseDto response = ResponseDto.createByFail(new ValidatorException(VIOLATION_EXCEPTION));
        response.setMessage(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        return response;
    }

    @ExceptionHandler(ValidatorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleValidatorException(ValidatorException exception) {
        return ResponseDto.createByFail(exception);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleBusinessException(BusinessException exception) {
        return ResponseDto.createByFail(exception);
    }

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


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleDefaultException(Exception exception) {
        ResponseDto responseDto = ResponseDto.createByFail(DEFAULT);
        responseDto.setMessage(exception.getMessage());
        return responseDto;
    }
}
