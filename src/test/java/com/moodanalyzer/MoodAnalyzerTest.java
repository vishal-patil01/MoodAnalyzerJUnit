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
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.ENTERED_NULL, e.type);
        }
    }

    @Test
    public void givenMessageInConstructor_WhenEmpty_ShouldThrowMoodAnalyzerException() {
        moodAnalyzer = new MoodAnalyzer("");
        try {
            moodAnalyzer.analyseMood();
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.ENTERED_EMPTY, e.type);
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

    //Comparing Two Objects Are equal or Not Using Default Constructors
    @Test
    public void givenMoodAnalyserClassUsingDefaultConstructor_WhenProper_ShouldReturnObject() {
        try {
            MoodAnalyzer reflectionMoodObj = MoodAnalyzerFactory.createMoodAnalyzer();
            Assert.assertEquals(new MoodAnalyzer(), reflectionMoodObj);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //Comparing Two Objects Are equal or Not Using Parameterized Constructors
    @Test
    public void givenMoodAnalyserClassUsingParameterizedConstructor_WhenProper_ShouldReturnObject() {
        try {
            MoodAnalyzer reflectionMoodObj = MoodAnalyzerFactory.createMoodAnalyzer("I am in Happy Mood");
            Assert.assertEquals(new MoodAnalyzer("I am in Happy Mood"), reflectionMoodObj);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //No Such class Exception Using Reflection
    @Test
    public void givenClassName_WhenImproper_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzerFactory.getConstructor("com.moodanalyzer.MoodAnalyzer1", String.class);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_CLASS, e.type);
        }
    }

    //No Such Method Exception Using Reflection
    @Test
    public void givenClassName_WhenProperWithImproperConstructor_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzerFactory.getConstructor("com.moodanalyzer.MoodAnalyzer", int.class);
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    //Invoking Methods Comparing Message
    @Test
    public void givenProperMethodName_WhenInvoked_ShouldReturnHappy() {
        try {
            MoodAnalyzer moodObject = MoodAnalyzerFactory.createMoodAnalyzer("I Am In Happy mood");
            String mood = MoodAnalyzerFactory.invokeMethod(moodObject, "analyseMood");
            Assert.assertEquals("Happy", mood);
        } catch (MoodAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //Invoking Methods If Method Name Not Found Throw No Such Method Exception
    @Test
    public void givenMethodName_WhenNotProper_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzer moodObject = MoodAnalyzerFactory.createMoodAnalyzer("I Am In Happy mood");
            MoodAnalyzerFactory.invokeMethod(moodObject, "analyseMood1");
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_METHOD, e.type);
        }
    }

    //Set Field (Variable) Value At Runtime Using Reflection
    @Test
    public void givenFieldNameAndItsValue_WhenProper_ShouldReturnValue() {

        MoodAnalyzer moodObject = MoodAnalyzerFactory.createMoodAnalyzer();
        String mood = MoodAnalyzerFactory.setFieldValue(moodObject, "I am in Happy Mood", "mood");
        Assert.assertEquals("Happy", mood);
    }

    // When Field Value Not Present Throw Exception
    @Test
    public void givenFieldNameAndItsValue_WhenFieldNotFound_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzer moodObject = MoodAnalyzerFactory.createMoodAnalyzer();
            MoodAnalyzerFactory.setFieldValue(moodObject, "Happy", "mood1");
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.NO_SUCH_FIELD, e.type);
        }
    }

    //Set Null value To Field Dynamically Using Reflection It Throws Exception
    @Test
    public void givenFieldNameAndNullValue_ShouldThrowMoodAnalyzerException() {
        try {
            MoodAnalyzer moodObject = MoodAnalyzerFactory.createMoodAnalyzer();
            MoodAnalyzerFactory.setFieldValue(moodObject, null, "mood");
        } catch (MoodAnalyzerException e) {
            Assert.assertEquals(MoodAnalyzerException.ExceptionType.METHOD_INVOCATION_ISSUE, e.type);
        }
    }
}
