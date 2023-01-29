package kcl.ac.uk.kaiji_machine.config;

import kcl.ac.uk.kaiji_machine.enums.ErrorCodeEnum;
import kcl.ac.uk.kaiji_machine.exception.BaseException;
import kcl.ac.uk.kaiji_machine.response.BaseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult defaultExceptionHandler(HttpServletRequest request, Exception e) {
        BaseResult baseResult = new BaseResult();

        if (e instanceof BaseException) {
            baseResult.setCode(((BaseException) e).getCode());
            baseResult.setDesc(((BaseException) e).getDesc());
        } else {
            e.printStackTrace();
            baseResult.setCode(ErrorCodeEnum.SYSTEM_ERROR.getCode());
            baseResult.setDesc(ErrorCodeEnum.SYSTEM_ERROR.getDesc());
        }

        return baseResult;
    }

}
