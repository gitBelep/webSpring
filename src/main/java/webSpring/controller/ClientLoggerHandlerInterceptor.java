package webSpring.controller;

import org.slf4j.Logger;    // ! This I need
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientLoggerHandlerInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String client = request.getHeader("User-Agent");
        logger.debug("Client: {}", client);
        System.out.println("ClientLoggerHandlerInterceptor is working");
        return true;
    }

}
