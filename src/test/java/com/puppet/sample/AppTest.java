package com.puppet.sample;

import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import spark.servlet.SparkApplication;

public class AppTest 
{
    @Test
    public void testMsg() {
        assertEquals("Hello World!", new App().enMsg());
    }
}
