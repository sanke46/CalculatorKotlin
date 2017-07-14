package com.example.ilafedoseev.calculatorkotlin;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.ilafedoseev.calculatorkotlin", appContext.getPackageName());
    }

    @Test
    public void text() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("2");
        arrayExample.add("+");
        arrayExample.add("3");
        MainActivityK mainActivity = new MainActivityK();
        mainActivity.setArrayExample(arrayExample);
        mainActivity.calculate(arrayExample);
    }

}
