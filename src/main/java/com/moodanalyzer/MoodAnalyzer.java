package com.moodanalyzer;

import com.moodanalyserexception.MoodAnalyzerException;

public class MoodAnalyzer {
    private String mood;

    public MoodAnalyzer() {
    }

    public MoodAnalyzer(String mood) {
        this.mood = mood;
    }

    public String analyseMood() {
        try {
            if (mood.contains("Sad")) {
                return "Sad";
            } else if (mood.isEmpty()) {
                throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.IS_EMPTY,"Empty String Passed Pass Valid String");
            }
            return "Happy";
        } catch (NullPointerException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.IS_NULL,"Null Value Passed Pass Valid Value");
        }
    }
}
