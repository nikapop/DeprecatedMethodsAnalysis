package remainderBot.service;

import org.springframework.stereotype.Service;
import remainderBot.bot.LanguageService;

import java.util.Collection;
import java.util.Set;

@Service
public class LanguageServiceImpl implements LanguageService {
    private static final Collection<String> supportedLanguages = Set.of("ru", "en");

    private String currentLanguage = "en";

    @Override
    public String getCurrentLanguage() {
        return currentLanguage;
    }

    @Override
    public void setCurrentLanguage(String language) {
       language = language.strip().toLowerCase();
       if (! supportedLanguages.contains(language))
           throw new IllegalArgumentException("Not supported language: " + language);
       currentLanguage = language;
    }
}