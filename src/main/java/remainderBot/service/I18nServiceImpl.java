package remainderBot.service;

import org.springframework.stereotype.Service;
import remainderBot.bot.I18nService;
import remainderBot.bot.LanguageService;

import java.util.Map;

@Service
public class I18nServiceImpl implements I18nService {
    final private Map<String, String> phraseMap = Map.of(
            "en#hello", "What's up! Get started?",
            "en#What-is-the-day", "What is the day today?",
            "en#What-is-your-name", "What is your name?",
            "en#monday", "plans for %dayOfTheWeek%: pet the cat, drink water, watch tv",
            "en#tuesday", "plans for %dayOfTheWeek%: wash the floor, go for a walk, read a book",
            "en#wednesday", "plans for %dayOfTheWeek%: eat a pizza, buy food",
            "en#thursday", "plans for %dayOfTheWeek%: go to shop, feed the fish",
            "en#friday", "plans for %dayOfTheWeek%: take a bath, read a magazine, make a coffee",
            "en#saturday", "plans for %dayOfTheWeek%: feed the cat, wash the dishes, do hw",
            "en#sunday", "plans for %dayOfTheWeek%: lie down, relax, buy food for fish and cat"
    );

    private final LanguageService languageService;

    public I18nServiceImpl(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public String getMessage(String code) {
        return getMessage(code, null);
    }

    @Override
    public String getMessage(String code, Map<String, Object> params) {
        var language = languageService.getCurrentLanguage();
        var key = language + "#" + code;
        var phrase = phraseMap.get(key);
        if (params != null)
            phrase = handlePlaceholders(phrase, params);
        return phrase;
    }

    private String handlePlaceholders(String phrase, Map<String, Object> params) {
        for (var e : params.entrySet())
            phrase = phrase.replace("%" + e.getKey() + "%", e.getValue().toString());
        return phrase;
    }
}
