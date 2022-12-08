package com.example.learnTogether.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@Slf4j
public class LoggingAdvice {
    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    //this is not workable--> deadlock might occurred and the embedded tomcat  could not be started
    /*@Pointcut(value = "execution(* com.apu.example.springsecurityjwt.*.*.*(..) )")//star first = any package, 2nd = class, 3rd = method
    public void myPointcut(){

    }*/

    //If we define the Controller then no deadlock and works fine
    @Pointcut(value = "execution(* com.example.learnTogether.controllers.*.*(..) )")//star first = any package, 2nd = class, 3rd = method
    public void controllerPointcut(){

    }

    @Around("controllerPointcut()")
    public Object applicationLogger(ProceedingJoinPoint point) throws Throwable{
//        ObjectMapper mapper = new ObjectMapper();

        String className = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();
//        Object[] array = point.getArgs();

//        log.info("Method Invoked: "+className+ "-->"+methodName+"() arguments: "+mapper.writeValueAsString(array));
        log.info("Controller start: "+className+ "-->"+methodName+"()");
//        log.info(methodName+"()");

        Object object = point.proceed();

//        log.info("Method end: "+className+ "-->"+methodName+"() Response: "+mapper.writeValueAsString(object));
        log.info("Controller end: "+className+ "-->"+methodName+"()\n");
//        log.info(methodName+"()\n");


        return object;
    }

    @Pointcut(value = "execution(* com.example.learnTogether.services.impls.*.*(..) )")//star first = any package, 2nd = class, 3rd = method
    public void servicePointcut(){

    }

    @Around("servicePointcut()")
    public Object applicationLogger2(ProceedingJoinPoint point) throws Throwable{
//        ObjectMapper mapper = new ObjectMapper();

        String className = point.getTarget().getClass().getSimpleName();
         String methodName = point.getSignature().getName();
//        Object[] array = point.getArgs();

        log.info("Service start: "+className+ "-->"+methodName+"()");
//        log.info(methodName+"()");


        Object object = point.proceed();

//        log.info("Service end: "+className+ "-->"+methodName+"() Response: "+mapper.writeValueAsString(object));
        log.info("Service end: "+className+ "-->"+methodName+"()");
//        log.info(methodName+"()");


        return object;
    }
}
