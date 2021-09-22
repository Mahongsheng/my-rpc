package com.mhsfire.myrpc.codec;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JSONEncoderTest {

    @Test
    public void encode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("smgeek");
        bean.setAge(18);

        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);
    }
}