package com.qp.grocery_booking.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {


    @Before("execution(* com.qp.grocery_booking.controller.*.*(..))")
    public void logBeforeControllerMethod(){
        System.out.println("Calling controller...");
    }


}
