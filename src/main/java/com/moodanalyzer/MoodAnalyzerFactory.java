package com.moodanalyzer;

import com.moodanalyserexception.MoodAnalyzerException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyzerFactory {
    //Return Object of Default Constructor
    public static MoodAnalyzer createMoodAnalyzer() {
        try {
            Constructor<?> constructor = Class.forName("com.moodanalyzer.MoodAnalyzer").getConstructor();
            MoodAnalyzer obj = (MoodAnalyzer) constructor.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Return Object of Parameterized Constructor
    public static MoodAnalyzer createMoodAnalyzer(String mood) {
        try {
            Constructor<?> constructor = Class.forName("com.moodanalyzer.MoodAnalyzer").getConstructor(String.class);
            MoodAnalyzer obj = (MoodAnalyzer) constructor.newInstance(mood);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Return Constructor of Passed Class Name And Constructor Name
    public static Constructor<?> getConstructor(String className, Class constructor) {
        try {
            Class<?> moodAnalyserClass = Class.forName(className);
            return moodAnalyserClass.getConstructor(constructor);
        } catch (ClassNotFoundException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_CLASS, e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.getMessage());
        }
    }

    //Invoking Method of Passed Class Name And Passed Method Name And Returning Message
    public static String invokeMethod(MoodAnalyzer obj, String methodName) {
        try {
            return (String) obj.getClass().getDeclaredMethod(methodName).invoke(obj);
        } catch (NoSuchMethodException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String setFieldValue(MoodAnalyzer obj, String message, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, message);
            return (String) obj.getClass().getDeclaredMethod("analyseMood").invoke(obj);

        } catch (NoSuchFieldException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.NO_SUCH_FIELD, e.getMessage());
        } catch (IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new MoodAnalyzerException(MoodAnalyzerException.ExceptionType.METHOD_INVOCATION_ISSUE, e.getMessage());
        }
        return "message";
    }
}
