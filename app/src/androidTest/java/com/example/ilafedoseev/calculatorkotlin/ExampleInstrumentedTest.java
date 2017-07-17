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
    private Calculate calculate = new Calculate();
//    private MainActivityK mainActivityK = new MainActivityK();

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.ilafedoseev.calculatorkotlin", appContext.getPackageName());
    }

    /** Integer test */

    @Test
    public void PlusIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("2");
        arrayExample.add("+");
        arrayExample.add("3");
        assertEquals("5", calculate.calculate(arrayExample));
    }

    @Test
    public void PlusBigIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("22");
        arrayExample.add("+");
        arrayExample.add("35");
        assertEquals("57", calculate.calculate(arrayExample));
    }

    @Test
    public void MinusIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("3");
        arrayExample.add("-");
        arrayExample.add("2");
        assertEquals("1", calculate.calculate(arrayExample));
    }

    @Test
    public void MinusBigIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("33");
        arrayExample.add("-");
        arrayExample.add("22");
        assertEquals("11", calculate.calculate(arrayExample));
    }

    @Test
    public void DivIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("/");
        arrayExample.add("2");
        assertEquals("2", calculate.calculate(arrayExample));
    }

    @Test
    public void DivBigIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("42");
        arrayExample.add("/");
        arrayExample.add("21");
        assertEquals("2", calculate.calculate(arrayExample));
    }

    @Test
    public void TimesIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        arrayExample.add("2");
        assertEquals("8", calculate.calculate(arrayExample));
    }

    @Test
    public void TimesBigIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("42");
        arrayExample.add("*");
        arrayExample.add("20");
        assertEquals("840", calculate.calculate(arrayExample));
    }


    /** Double test */

    @Test
    public void PlusDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("2");
        arrayExample.add(".");
        arrayExample.add("1");
        arrayExample.add("+");
        arrayExample.add("3");
        arrayExample.add(".");
        arrayExample.add("1");
        assertEquals("5.2", calculate.calculate(arrayExample));

    }

    @Test
    public void MinusDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("3");
        arrayExample.add(".");
        arrayExample.add("2");
        arrayExample.add("-");
        arrayExample.add("2");
        arrayExample.add(".");
        arrayExample.add("1");
        assertEquals("1.1", calculate.calculate(arrayExample));
    }

    @Test
    public void DivDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add(".");
        arrayExample.add("2");
        arrayExample.add("/");
        arrayExample.add("2");
        arrayExample.add(".");
        arrayExample.add("1");
        assertEquals("2", calculate.calculate(arrayExample));

    }

    @Test
    public void TimesDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add(".");
        arrayExample.add("2");
        arrayExample.add("*");
        arrayExample.add("2");
        arrayExample.add(".");
        arrayExample.add("1");
        assertEquals("8.82", calculate.calculate(arrayExample));

    }

    /** Minus Number */

    @Test
    public void TimesMinusNumberIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("-");
        arrayExample.add("4");
        arrayExample.add("*");
        arrayExample.add("2");
        assertEquals("-8", calculate.calculate(arrayExample));
    }

    @Test
    public void MMinusNumberIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("-");
        arrayExample.add("4");
        arrayExample.add("-");
        arrayExample.add("2");
        assertEquals("-6", calculate.calculate(arrayExample));
    }

    @Test
    public void PlusMinusNumberIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("-");
        arrayExample.add("4");
        arrayExample.add("+");
        arrayExample.add("6");
        assertEquals("2", calculate.calculate(arrayExample));
    }

    @Test
    public void DivMinusNumberIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("-");
        arrayExample.add("6");
        arrayExample.add("/");
        arrayExample.add("2");
        assertEquals("-3", calculate.calculate(arrayExample));
    }

    /** Zero test */

    @Test
    public void PlusZeroDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add(".");
        arrayExample.add("2");
        arrayExample.add("+");
        arrayExample.add("0");
        assertEquals("4.2", calculate.calculate(arrayExample));
    }

    @Test
    public void MinusZeroDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add(".");
        arrayExample.add("2");
        arrayExample.add("-");
        arrayExample.add("0");
        assertEquals("4.2", calculate.calculate(arrayExample));
    }

    @Test
    public void TimesZeroDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add(".");
        arrayExample.add("2");
        arrayExample.add("*");
        arrayExample.add("0");
        assertEquals("0", calculate.calculate(arrayExample));
    }

    @Test
    public void DivZeroDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add(".");
        arrayExample.add("2");
        arrayExample.add("/");
        arrayExample.add("0");
        assertEquals("0", calculate.calculate(arrayExample));
    }

    @Test
    public void MinusZeroTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("-");
        arrayExample.add("0");
        assertEquals("4", calculate.calculate(arrayExample));
    }

    @Test
    public void PlusZeroTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("+");
        arrayExample.add("0");
        assertEquals("4", calculate.calculate(arrayExample));
    }

    @Test
    public void DivZeroTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("/");
        arrayExample.add("0");
        assertEquals("0", calculate.calculate(arrayExample));
    }

    @Test
    public void TimesZeroTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        arrayExample.add("0");
        assertEquals("0", calculate.calculate(arrayExample));
    }
}
