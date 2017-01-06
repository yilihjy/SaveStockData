package com.yilihjy.savesinastockdata;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author yilihjy Email:yilihjy@gmail.com
 * @version 1.0.0
 *
 */
public class RealTimeDataTest {

	@Test
	public void testGetRealTimeDataObjectsMethod() throws UnsupportedEncodingException {
		String[] li ={"s_sh000001","s_sz399001","sz000002","sh603377","sz300443"};
		List<RealTimeDataPOJO> list = RealTimeData.getRealTimeDataObjects(li);
		assertEquals("上证指数",list.get(0).getName());
		assertEquals(0.0,list.get(0).getOpen(),0.0);
		assertEquals(RealTimeDataPOJO.INDEX,list.get(0).getType());
		assertEquals("深证成指",list.get(1).getName());
		assertEquals("万 科Ａ",list.get(2).getName());
		assertNotEquals(RealTimeDataPOJO.INDEX,list.get(2).getType());
		assertEquals(RealTimeDataPOJO.STOCK,list.get(2).getType());
		assertEquals("东方时尚",list.get(3).getName());
		assertEquals("金雷风电",list.get(4).getName());
	}

}
