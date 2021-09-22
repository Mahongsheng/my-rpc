package com.mhsfire.myrpc.codec;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JSONDecoderTest {

    @Test
    public void decode() {
        Encoder encoder = new JSONEncoder();
        TestBean bean = new TestBean();
        bean.setName("smgeek");
        bean.setAge(18);

        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);

        Decoder decoder = new JSONDecoder();
        TestBean testBean = decoder.decode(bytes, TestBean.class);
        assertEquals("smgeek", testBean.getName());
        assertEquals(18, testBean.getAge());
    }
}