package kcl.ac.uk.kaiji_machine.config;

import kcl.ac.uk.kaiji_machine.enums.ErrorCodeEnum;
import kcl.ac.uk.kaiji_machine.exception.BaseException;
import kcl.ac.uk.kaiji_machine.util.JWTUtils;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Component
public class UserPermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Allows the browser's preflight request
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        // Gets token from headers
        String token = request.getHeader("Authorisation");

        // Token validation
        if (!JWTUtils.checkToken(token)) {
            throw new BaseException(ErrorCodeEnum.AUTHENTICATION_FAIL);
        }

        return true;
    }
}
