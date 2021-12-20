package deprecatedAnalysis;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Aspect
@Component
@Slf4j

public class LoggingAspect {
    private final ConcurrentHashMap<String, AtomicLong> methodTimingMap;

    public LoggingAspect(ConcurrentHashMap<String, AtomicLong> methodTimingMap) {
        this.methodTimingMap = methodTimingMap;
    }

    @Before("@annotation(java.lang.Deprecated)")
    public void loggingDeprecated(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info(methodName + " is deprecated method in class " + className);
    }

    @Around("@annotation(java.lang.Deprecated)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long sTime = System.currentTimeMillis();
        Object result = null;
        result = joinPoint.proceed();
        long eTime = System.currentTimeMillis();
        methodTimingMap.get(joinPoint.getTarget()).addAndGet(eTime-sTime);
        return result;
    }

    @PreDestroy
    public void afterAdvice() {
        methodTimingMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }
}