package pt.isel.ps.li61n.controller.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RequestErrorHandler - Description
 * Created on 14/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com
 */
@ControllerAdvice
public class RequestErrorHandler {

    private MessageSource messageSource;

    @Autowired
    public RequestErrorHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorInfo processValidationError(MethodArgumentNotValidException exc
//            , HttpServletRequest request
    ) {
        BindingResult result = exc.getBindingResult();
        FieldError fieldError = result.getFieldError();

        ErrorInfo dto = new ErrorInfo("", fieldError.getDefaultMessage());
        return dto;
    }

}
