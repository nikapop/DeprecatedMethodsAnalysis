package remainderBot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("bot")
public class ReminderBot {
    private final  I18nService i18nService;
    private final IOService ioService;
    private final LanguageService languageService;

    public ReminderBot(I18nService i18nService,
                       IOService ioService,
                       LanguageService languageService) {
        this.i18nService = i18nService;
        this.ioService = ioService;
        this.languageService = languageService;
    }

    public void conversation() {
        selectLanguage();
        sayHi();
        String name = askName();
        abused(name);
        String dayOfTheWeek = askDayOfTheWeek();
        remind(dayOfTheWeek);
        abused(name);
    }

    private void selectLanguage() {
        ioService.print("Select language (sorry only en :/): ");
        var language = ioService.readLine();
        languageService.setCurrentLanguage(language);
    }

    private void sayHi() {
        ioService.println(i18nService.getMessage("hello"));
    }

    @Deprecated
    private String askName() {
        ioService.println(i18nService.getMessage("what-is-your-name"));
        ioService.print("> ");
        return ioService.readLine();
    }

    private String askDayOfTheWeek() {
        ioService.println(i18nService.getMessage("What-is-the-day"));
        ioService.print("> ");
        return ioService.readLine();
    }
    private void remind(String dayOfTheWeek) {
        ioService.println(i18nService.getMessage(
                dayOfTheWeek,
                Map.of("dayOfTheWeek", dayOfTheWeek)
        ));
    }

    @Deprecated
    private void abused(String name) {
        ioService.println(name + ", вставай, лентяй!!!");
    }
}