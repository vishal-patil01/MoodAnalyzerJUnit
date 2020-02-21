package com.moodanalyzer;

import com.moodanalyserexception.MoodAnalyzerException;

import java.lang.reflect.Constructor;

public class MoodAnalyzerFactory {
    //For Default Constructor
    public static MoodAnalyzer createMoodAnalyzer() {
        try {
            Constructor<?>  constructor = Class.forName("com.moodanalyzer.MoodAnalyzer").getConstructor();
            MoodAnalyzer obj = (MoodAnalyzer) constructor.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Constructor<?> getConstructor(String className, Class constructor) throws MoodAnalyzerException {
        try {
            Class<?> moodAnalyserClass = Class.forName(className);
            return moodAnalyserClass.getConstructor(constructor);
        } catch (ClassNotFoundException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_CLASS, e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.getMessage());
        }
    }
}
