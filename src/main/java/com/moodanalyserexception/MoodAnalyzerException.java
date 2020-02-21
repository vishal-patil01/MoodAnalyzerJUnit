package com.moodanalyserexception;

public class MoodAnalyzerException extends RuntimeException {

    public enum ExceptionType {
        IS_EMPTY, IS_NULL, NO_SUCH_CLASS, NO_SUCH_METHOD;
    }

    public final ExceptionType type;

    public MoodAnalyzerException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
