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

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.ilafedoseev.calculatorkotlin", appContext.getPackageName());
    }

    /** Integer test */

    @Test
    public void plusIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("2");
        arrayExample.add("+");
        arrayExample.add("3");
        assertEquals("5", calculate.calculate(arrayExample));
    }

    @Test
    public void halfIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("2");
        arrayExample.add("+");
        assertEquals("2", calculate.calculate(arrayExample));
    }

    @Test
    public void plusBigIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("22");
        arrayExample.add("+");
        arrayExample.add("35");
        assertEquals("57", calculate.calculate(arrayExample));
    }

    @Test
    public void minusIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("3");
        arrayExample.add("-");
        arrayExample.add("2");
        assertEquals("1", calculate.calculate(arrayExample));
    }

    @Test
    public void minusBigIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("33");
        arrayExample.add("-");
        arrayExample.add("22");
        assertEquals("11", calculate.calculate(arrayExample));
    }

    @Test
    public void divIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("/");
        arrayExample.add("2");
        assertEquals("2", calculate.calculate(arrayExample));
    }

    @Test
    public void divBigIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("42");
        arrayExample.add("/");
        arrayExample.add("21");
        assertEquals("2", calculate.calculate(arrayExample));
    }

    @Test
    public void timesIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        arrayExample.add("2");
        assertEquals("8", calculate.calculate(arrayExample));
    }

    @Test
    public void timesBigIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("42");
        arrayExample.add("*");
        arrayExample.add("20");
        assertEquals("840", calculate.calculate(arrayExample));
    }


    /** Double test */

    @Test
    public void plusDoubleTest() {
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
    public void halfDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("2");
        arrayExample.add(".");
        arrayExample.add("1");
        arrayExample.add("+");
        assertEquals("2.1", calculate.calculate(arrayExample));
    }

    @Test
    public void minusDoubleTest() {
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
    public void divDoubleTest() {
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
    public void timesDoubleTest() {
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
    public void timesMinusNumberIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("-");
        arrayExample.add("4");
        arrayExample.add("*");
        arrayExample.add("2");
        assertEquals("-8", calculate.calculate(arrayExample));
    }

    @Test
    public void mMinusNumberIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("-");
        arrayExample.add("4");
        arrayExample.add("-");
        arrayExample.add("2");
        assertEquals("-6", calculate.calculate(arrayExample));
    }

    @Test
    public void plusMinusNumberIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("-");
        arrayExample.add("4");
        arrayExample.add("+");
        arrayExample.add("6");
        assertEquals("2", calculate.calculate(arrayExample));
    }

    @Test
    public void divMinusNumberIntTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("-");
        arrayExample.add("6");
        arrayExample.add("/");
        arrayExample.add("2");
        assertEquals("-3", calculate.calculate(arrayExample));
    }

    /** Zero test */

    @Test
    public void plusZeroDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add(".");
        arrayExample.add("2");
        arrayExample.add("+");
        arrayExample.add("0");
        assertEquals("4.2", calculate.calculate(arrayExample));
    }

    @Test
    public void minusZeroDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add(".");
        arrayExample.add("2");
        arrayExample.add("-");
        arrayExample.add("0");
        assertEquals("4.2", calculate.calculate(arrayExample));
    }

    @Test
    public void timesZeroDoubleTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add(".");
        arrayExample.add("2");
        arrayExample.add("*");
        arrayExample.add("0");
        assertEquals("0", calculate.calculate(arrayExample));
    }

    @Test
    public void minusZeroTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("-");
        arrayExample.add("0");
        assertEquals("4", calculate.calculate(arrayExample));
    }

    @Test
    public void plusZeroTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("+");
        arrayExample.add("0");
        assertEquals("4", calculate.calculate(arrayExample));
    }

    @Test
    public void timesZeroTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        arrayExample.add("0");
        assertEquals("0", calculate.calculate(arrayExample));
    }

    /** other methods */

    @Test
    public void withCalculatelogTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        arrayExample.add("2");
        assertEquals("2.0794", calculate.log(calculate.calculate(arrayExample)));
    }

    @Test
    public void withMultyCalculatelogTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        arrayExample.add("2");
        arrayExample.add("+");
        arrayExample.add("2");
        assertEquals("2.3026", calculate.log(calculate.calculate(arrayExample)));
    }

    @Test
    public void halfCalculateLogTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        assertEquals("1.3863", calculate.log(calculate.calculate(arrayExample)));
    }

    @Test
    public void halfArrayLogTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        assertEquals("1.3863", calculate.log(calculate.calculate(arrayExample)));
    }

    @Test
    public void logTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        assertEquals("1.3863", calculate.log(calculate.calculate(arrayExample)));
    }


    @Test
    public void sinTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        assertEquals("-0.7568", calculate.functionIndecate(calculate.calculate(arrayExample),"sin"));
    }

    @Test
    public void multySinTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        arrayExample.add("2");
        assertEquals("0.9894", calculate.functionIndecate(calculate.calculate(arrayExample),"sin"));
    }

    @Test
    public void halfArraySinTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        assertEquals("-0.7568", calculate.functionIndecate(calculate.calculate(arrayExample),"sin"));
    }

    @Test
    public void cosTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        assertEquals("-0.6536", calculate.functionIndecate(calculate.calculate(arrayExample),"cos"));
    }

    @Test
    public void multyCosTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        arrayExample.add("2");
        assertEquals("-0.1455", calculate.functionIndecate(calculate.calculate(arrayExample),"cos"));
    }

    @Test
    public void halfArrayCosTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        assertEquals("-0.6536", calculate.functionIndecate(calculate.calculate(arrayExample),"cos"));
    }

    @Test
    public void tanTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        assertEquals("1.1578", calculate.functionIndecate(calculate.calculate(arrayExample),"tan"));
    }

    @Test
    public void multyTanTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        arrayExample.add("2");
        assertEquals("-6.7997", calculate.functionIndecate(calculate.calculate(arrayExample),"tan"));
    }

    @Test
    public void halfArrayTanTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("4");
        arrayExample.add("*");
        assertEquals("1.1578", calculate.functionIndecate(calculate.calculate(arrayExample),"tan"));
    }

    @Test
    public void percentTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("10");
        assertEquals("0.1", calculate.percent(calculate.calculate(arrayExample)));
    }

    @Test
    public void halfPercentTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("10");
        arrayExample.add("+");
        assertEquals("0.1", calculate.percent(calculate.calculate(arrayExample)));
    }

    @Test
    public void plusPercentTest() {
        ArrayList<String> arrayExample = new ArrayList<String>();
        arrayExample.add("10");
        arrayExample.add("+");
        arrayExample.add("10");
        assertEquals("0.2", calculate.percent(calculate.calculate(arrayExample)));
    }
}
