package com.moodanalyzer;

import com.moodanalyserexception.MoodAnalyzerException;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;

public class MoodAnalyzerTest {
    private MoodAnalyzer moodAnalyzer;

    @Test
    public void givenMessageInConstructor_WhenContainAnyMood_ShouldReturnHappy() {
        moodAnalyzer = new MoodAnalyzer("I am in Happy Mood");
        try {
            String mood = moodAnalyzer.analyseMood();
            Assert.assertEquals("Happy", mood);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMessageInConstructor_WhenContainSad_ShouldReturnSad() {
        moodAnalyzer = new MoodAnalyzer("I am in Sad Mood");
        try {
            String mood = moodAnalyzer.analyseMood();
            Assert.assertEquals("Sad", mood);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenMessageInConstructor_WhenNull_ShouldThrowMoodAnalyzerException() {
        moodAnalyzer = new MoodAnalyzer(null);
        try {
            moodAnalyzer.analyseMood();
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.IS_NULL, e.type);
        }
    }

    @Test
    public void givenMessageInConstructor_WhenEmpty_ShouldThrowMoodAnalyzerException() {
        moodAnalyzer = new MoodAnalyzer("");
        try {
            moodAnalyzer.analyseMood();
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.IS_EMPTY, e.type);
        }
    }
    //Reflections
    @Test
    public void givenMessageInConstructorUsingReflection_WhenContainsAnyMood_ShouldReturnHappy() {
        try {
            Constructor<?> constructor = Class.forName("com.moodanalyzer.MoodAnalyzer").getConstructor(String.class);
            MoodAnalyzer moodAnalyzer = (MoodAnalyzer) constructor.newInstance("I am in Happy Mood");
            String mood = moodAnalyzer.analyseMood();
            Assert.assertEquals("Happy", mood);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMoodAnalyserClassUsingDefaultConstructor_WhenProper_ShouldReturnObject() {
        try {
            MoodAnalyzer reflectionMoodObj = MoodAnalyzerFactory.createMoodAnalyzer();
            Assert.assertEquals(new MoodAnalyzer(), reflectionMoodObj);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenMoodAnalyserClassUsingParameterizedConstructor_WhenProper_ShouldReturnObject() {
        try {
            MoodAnalyzer reflectionMoodObj = MoodAnalyzerFactory.createMoodAnalyzer("I am in Happy Mood");
            Assert.assertEquals(new MoodAnalyzer("I am in Happy Mood"), reflectionMoodObj);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenClassName_WhenImproper_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzerFactory.getConstructor("com.moodanalyzer.MoodAnalyzer1", String.class);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_CLASS, e.type);
        }
    }

    @Test
    public void givenClassName_WhenProperWithImproperConstructor_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzerFactory.getConstructor("com.moodanalyzer.MoodAnalyzer", int.class);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }
}
