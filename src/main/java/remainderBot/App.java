package remainderBot;

import deprecatedAnalysis.LoggingAspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import remainderBot.bot.ReminderBot;
import remainderBot.config.AppConfig;

@ComponentScan
@Configuration
public class App {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(App.class)
                .getBean("bot", ReminderBot.class)
                .conversation();

        new AnnotationConfigApplicationContext(App.class)
                .getBean(LoggingAspect.class);
    }
}