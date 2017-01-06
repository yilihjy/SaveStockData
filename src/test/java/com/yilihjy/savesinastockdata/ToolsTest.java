package com.yilihjy.savesinastockdata;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
/**
 * 
 * @author yilihjy Email:yilihjy@gmail.com
 * @version 1.0.0
 *
 */
public class ToolsTest {

	@Test
	public void testString2LocalDateTimeMethod() {
		String time1 = "2017-01-05";
		String time2 = "2017-01-05 15:00:00";
		LocalDateTime ltime = LocalDateTime.of(2017, 1, 5, 15, 0);
		assertEquals(ltime,Tools.string2LocalDateTime(time1));
		assertEquals(ltime,Tools.string2LocalDateTime(time2));
	}

}
