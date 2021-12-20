package deprecatedAnalysis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@EnableAspectJAutoProxy
public class LogConfig {
    private final ConcurrentHashMap<String, AtomicLong> hashMap;

    public LogConfig(ConcurrentHashMap<String, AtomicLong> hashMap) {
        this.hashMap = hashMap;
    }

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect(hashMap);
    }


}
