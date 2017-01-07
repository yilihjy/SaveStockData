package com.yilihjy.savesinastockdata;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * 对新浪财经查询股票历史数据API的封装<br>
 * API：http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData<br>
 * 封装的参数：symbol，scale，datalen<br>
 * 
 * @author yilihjy Email:yilihjy@gmail.com
 * @version 1.0.0
 *
 */
public class HistoryData {
	/**
	 * 五分钟，字符串"5"
	 */
	public static final String FIVE_MINUTES = "5";
	/**
	 * 十五分钟，字符串"15"
	 */
	public static final String FIFTEEN_MINUTES = "15";
	/**
	 * 三十分钟，字符串"30"
	 */
	public static final String THIRTY_MINUTES = "30";
	/**
	 * 一小时，字符串"60"
	 */
	public static final String ONE_HOUR = "60";
	/**
	 * 一天，字符串"240"
	 */
	public static final String ONE_DAY = "240";
	/**
	 * 一周，字符串"1680"
	 */
	public static final String ONE_WEEK = "1680";
	/**
	 * 全部数据，字符串"1000000"
	 */
	public static final String DEFAULT_DATALEN = "1000000";
	
	/**
	 * 获取股票历史数据<br>
	 * 例子：<br>
	 * String result = HistoryData.getKLineData("sz000001", HistoryData.FIVE_MINUTES, "20");<br>
	 * 
	 * @param code 股票代码
	 * @param scale 时间跨度  可选[FIVE_MINUTES,FIFTEEN_MINUTES,THIRTY_MINUTES,ONE_HOUR,ONE_DAY,ONE_WEEK]
	 * @param datalen 数据量
	 * @return json字符串
	 */
	public static String getKLineData(String code, String scale, String datalen){
		String url = HistoryData.createURL(code, scale, datalen);
		return Tools.sendHTTPGET(url,"UTF-8");
	}
	/**
	 * 获取股票历史数据
	 * 例子：<br>
	 * List&lt;HistoryDataPOJO&gt; result = HistoryData.getKLineDataObjects("sz000001", HistoryData.FIVE_MINUTES, "20");<br>
	 * 
	 * @param code 股票代码
	 * @param scale 时间跨度 可选[FIVE_MINUTES,FIFTEEN_MINUTES,THIRTY_MINUTES,ONE_HOUR,ONE_DAY,ONE_WEEK]
	 * @param datalen 数据量
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> getKLineDataObjects(String code, String scale, String datalen){
		String jsonText = getKLineData(code, scale, datalen);
		JSONArray jsonarray = new JSONArray(jsonText);
		int lengh = jsonarray.length();
		List<HistoryDataPOJO> result = new ArrayList<>();
		for(int i =0;i<lengh;i++){
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			String dayString = jsonobject.getString("day");
			LocalDateTime day = Tools.string2LocalDateTime(dayString);
			double open = Double.parseDouble(jsonobject.getString("open"));
			double high = Double.parseDouble(jsonobject.getString("high"));
			double low = Double.parseDouble(jsonobject.getString("low"));
			double close = Double.parseDouble(jsonobject.getString("close"));
			double volume = Double.parseDouble(jsonobject.getString("volume"));
			double MA5 = jsonobject.optDouble("ma_price5");
			double MA5Volume = jsonobject.optDouble("ma_volume5");
			double MA10 = jsonobject.optDouble("ma_price10");
			double MA10Volume = jsonobject.optDouble("ma_volume10");
			double MA30 = jsonobject.optDouble("ma_price30");
			double MA30Volume = jsonobject.optDouble("ma_volume30");
			HistoryDataPOJO pojo;
			if(!Double.isNaN(MA30) && !Double.isNaN(MA30Volume)){
				pojo = new HistoryDataPOJO(day,open,high,low,close,volume,MA5,MA5Volume,MA10,MA10Volume,MA30,MA30Volume);
			}else if(!Double.isNaN(MA10) && !Double.isNaN(MA10Volume)){
				pojo = new HistoryDataPOJO(day,open,high,low,close,volume,MA5,MA5Volume,MA10,MA10Volume);
			}else if(!Double.isNaN(MA5) && !Double.isNaN(MA5Volume)){
				pojo = new HistoryDataPOJO(day,open,high,low,close,volume,MA5,MA5Volume);
			}else{
				pojo = new HistoryDataPOJO(day,open,high,low,close,volume);
			}
			result.add(pojo);
		}
		return result;
	}
	
	private static String createURL(String code, String scale, String datalen){
		String url = String.format("http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?"
				+ "symbol=%s&scale=%s&datalen=%s", code, scale, datalen);
		return url;
	}
	/**
	 * 获取股票历史数据<br>
	 * 实际调用getKLineData(String code, String scale, HistoryData.DEFAULT_DATALEN)<br>
	 * 例子：<br>
	 * String result = HistoryData.getKLineData("sz000001", HistoryData.FIVE_MINUTES);<br>
	 * 
	 * @param code 股票代码
	 * @param scale 时间跨度 可选[FIVE_MINUTES,FIFTEEN_MINUTES,THIRTY_MINUTES,ONE_HOUR,ONE_DAY,ONE_WEEK]
	 * @return json字符串
	 */
	public static String getKLineData(String code, String scale){
		return HistoryData.getKLineData(code, scale, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 * 实际调用：getKLineData(code, HistoryData.FIVE_MINUTES, datalen)
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return json字符串
	 */
	public static String get5MKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.FIVE_MINUTES, datalen);
	}
	/**
	 * 实际调用：getKLineData(code, HistoryData.FIVE_MINUTES, HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @return json字符串
	 */
	public static String get5MKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.FIVE_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 *实际调用：getKLineData(code, HistoryData.FIFTEEN_MINUTES, datalen)
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return json字符串
	 */
	public static String get15MKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.FIFTEEN_MINUTES, datalen);
	}
	/**
	 * 实际调用：getKLineData(code, HistoryData.FIFTEEN_MINUTES, HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @return json字符串
	 */
	public static String get15MKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.FIFTEEN_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 * 实际调用：getKLineData(code, HistoryData.THIRTY_MINUTES, datalen);
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return json字符串
	 */
	public static String get30MKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.THIRTY_MINUTES, datalen);
	}
	/**
	 * 实际调用：getKLineData(code, HistoryData.THIRTY_MINUTES, HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @return json字符串
	 */
	public static String get30MKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.THIRTY_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 * 实际调用：getKLineData(code, HistoryData.ONE_HOUR, datalen);
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return json字符串
	 */
	public static String get1HKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.ONE_HOUR, datalen);
	}
	/**
	 * 实际调用：getKLineData(code, HistoryData.ONE_HOUR, HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @return json字符串
	 */
	public static String get1HKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.ONE_HOUR, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 *  实际调用：getKLineData(code, HistoryData.ONE_DAY, datalen);
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return json字符串
	 */
	public static String get1DKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.ONE_DAY, datalen);
	}
	/**
	 * 实际调用：getKLineData(code, HistoryData.ONE_DAY, HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @return json字符串
	 */
	public static String get1DKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.ONE_DAY, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 * 实际调用：getKLineData(code, HistoryData.ONE_WEEK, datalen);
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return json字符串
	 */
	public static String get1WKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.ONE_WEEK, datalen);
	}
	/**
	 * 实际调用：getKLineData(code, HistoryData.ONE_WEEK, HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @return json字符串
	 */
	public static String get1WKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.ONE_WEEK, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, scale,HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @param scale 时间跨度
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> getKLineDataObjects(String code, String scale){
		return HistoryData.getKLineDataObjects(code, scale,HistoryData.DEFAULT_DATALEN);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.FIVE_MINUTES, datalen);
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get5MKLineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.FIVE_MINUTES, datalen);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.FIVE_MINUTES, HistoryData.DEFAULT_DATALEN);
	 * @param code股票代码
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get5MKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.FIVE_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.FIFTEEN_MINUTES, datalen);
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get15MKlineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.FIFTEEN_MINUTES, datalen);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.FIFTEEN_MINUTES, HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get15MKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.FIFTEEN_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.THIRTY_MINUTES, datalen);
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get30MKlineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.THIRTY_MINUTES, datalen);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.THIRTY_MINUTES, HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get30MKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.THIRTY_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.ONE_HOUR, datalen);
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get1HKlineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_HOUR, datalen);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.ONE_HOUR, HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get1HKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_HOUR, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.ONE_DAY, datalen);
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get1DKlineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_DAY, datalen);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.ONE_DAY, HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get1DKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_DAY, HistoryData.DEFAULT_DATALEN);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.ONE_WEEK, datalen);
	 * @param code 股票代码
	 * @param datalen 数据量
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get1WKlineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_WEEK, datalen);
	}
	/**
	 * 实际调用：getKLineDataObjects(code, HistoryData.ONE_WEEK, HistoryData.DEFAULT_DATALEN);
	 * @param code 股票代码
	 * @return 一个{@link List}，里面是{@link HistoryDataPOJO}对象
	 */
	public static List<HistoryDataPOJO> get1WKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_WEEK, HistoryData.DEFAULT_DATALEN);
	}
}
