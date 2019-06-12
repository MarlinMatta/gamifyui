package edu.uapa.ui.gamify.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class I18n {
    private static final Locale ENGLISH = Locale.ENGLISH;
    private static final Locale SPANISH = new Locale("es");
    private static Locale CURRENT_LANGUAGE = ENGLISH;

    public static String getText(String key) {
        try {
            return getI18N().getString(key);
        } catch (Exception ignored) {
            return key;
        }
    }

    private static ResourceBundle getI18N() throws IOException {
        Reader reader;
        switch (CURRENT_LANGUAGE.getLanguage()) {
            case "en":
                reader = new InputStreamReader(I18n.class.getResourceAsStream("/messages.properties"), StandardCharsets.UTF_8);
                break;
            case "es":
            default:
                reader = new InputStreamReader(I18n.class.getResourceAsStream("/messages_es.properties"), StandardCharsets.UTF_8);
                break;
        }
        return new PropertyResourceBundle(reader);
    }

    static String getMapLanguage(Locale language) {
        switch (language.getLanguage()) {
            case "en":
                return getText("language.english");
            case "es":
            default:
                return getText("language.spanish");
        }
    }

    public static Locale getCurrentLanguage() {
        return CURRENT_LANGUAGE;
    }

    public static void setCurrentLanguage(Locale currentLanguage) {
        CURRENT_LANGUAGE = currentLanguage;
    }

    static List<Locale> values() {
        return Arrays.asList(ENGLISH, SPANISH);
    }
}
