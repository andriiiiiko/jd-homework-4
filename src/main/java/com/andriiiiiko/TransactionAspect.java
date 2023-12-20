package com.andriiiiiko;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TransactionAspect {

    private static final Logger logger = LoggerFactory.getLogger(TransactionAspect.class);

    /**
     * Pointcut definition for the execution of the makeSomeOperation method in the Main class.
     */
    @Pointcut("execution(* Main.makeSomeOperation(String))")
    public void executeOperation() {
    }

    /**
     * Advice executed around the execution of the makeSomeOperation method.
     *
     * @param joinPoint The join point (method) being executed.
     * @return The result of the operation.
     * @throws Throwable If an error occurs during the operation.
     */
    @Around(value = "executeOperation()")
    public Object beforeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        logger.info("Opening transaction...");
        try {
            result = joinPoint.proceed();
            logger.info("Closing transaction...");
        } catch (Throwable throwable) {
            logger.error("Operation failed, rolling back the transaction...", throwable);
            throw throwable;
        }

        return result;
    }
}
