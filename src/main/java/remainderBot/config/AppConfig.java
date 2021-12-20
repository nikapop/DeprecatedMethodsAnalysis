package remainderBot.config;

import deprecatedAnalysis.LoggingAspect;
import org.springframework.context.annotation.Bean;
import remainderBot.bot.I18nService;
import remainderBot.bot.IOService;
import remainderBot.bot.LanguageService;
import remainderBot.bot.ReminderBot;
import remainderBot.service.I18nServiceImpl;
import remainderBot.service.IOServiceImpl;
import remainderBot.service.LanguageServiceImpl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

//@Configuration
public class AppConfig {
    @Bean
    LanguageService languageService() {
        return new LanguageServiceImpl();
    }

    @Bean
    IOService ioService() {
        return new IOServiceImpl(System.in, System.out);
    }

    @Bean
    I18nService i18nService() {
        return new I18nServiceImpl(languageService());
    }

    @Bean(name = "bot")
    ReminderBot reminderBot(I18nService i18n, IOService io) {
        return new ReminderBot(i18n, io, languageService());
    }

    @Bean
    ConcurrentHashMap<String, AtomicLong> methodTimingMap() {
        return new ConcurrentHashMap<>();
    }

}
