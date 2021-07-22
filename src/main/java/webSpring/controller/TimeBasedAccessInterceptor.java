package webSpring.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;

@Component
public class TimeBasedAccessInterceptor extends HandlerInterceptorAdapter {
    private int openingTime = 8;
    private int closingTime = 22;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        int hourNow = LocalTime.now().getHour();
        if(openingTime <= hourNow && hourNow < closingTime){
            System.out.println("We are open");
            return true;
        }
        response.sendRedirect("/outsideOfficeHours.html");
        return false;
    }

}
