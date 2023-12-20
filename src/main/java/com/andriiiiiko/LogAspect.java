package com.andriiiiiko;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * Pointcut definition for all methods within the package com.andriiiiiko.
     */
    @Pointcut("within(com.andriiiiiko..*)")
    public void methodExecuting() {
    }

    /**
     * Advice executed after a method successfully returns.
     *
     * @param joinPoint      The join point (method) being executed.
     * @param returningValue The value returned by the method.
     */
    @AfterReturning(value = "methodExecuting()", returning = "returningValue")
    public void recordSuccessfulExecution(JoinPoint joinPoint, Object returningValue) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSourceLocation().getWithinType().getName();
        if (returningValue != null) {
            logger.info("Method {} of class {} executed successfully with result {}",
                    methodName, className, returningValue);
        } else {
            logger.info("Method {} of class {} executed successfully", methodName, className);
        }
    }

    /**
     * Advice executed after a method throws an exception.
     *
     * @param joinPoint The join point (method) being executed.
     * @param exception The exception thrown by the method.
     */
    @AfterThrowing(value = "methodExecuting()", throwing = "exception")
    public void recordFailedExecution(JoinPoint joinPoint, Throwable exception) {
        logger.error("Method {} of class {} terminated unexpectedly with exception: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getSourceLocation().getWithinType().getName(),
                exception.getMessage());
    }
}
