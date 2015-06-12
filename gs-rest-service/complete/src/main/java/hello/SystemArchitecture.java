package hello;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemArchitecture {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* hello.*.*(..))")
    protected void Profile() {}

    @Pointcut("bean(dbService)")
    protected void Profile1() {}

//    @Pointcut("execution(* hello.RestFilter.*(..))")
//    protected void DontProfile() {}

//    @Around("Profile() && !DontProfile()")
    @Around("Profile()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Starting .. " + pjp.getSignature());

        Object retVal = pjp.proceed();

        log.info("Finshed .. " + pjp.getSignature());
        return retVal;
    }

//    @AfterThrowing(pointcut="Profile() && !DontProfile()", throwing="ex")
    @AfterThrowing(pointcut="Profile()", throwing="ex")
    public void LogException(Exception ex){
        log.error(ex.getMessage());
    }
}
