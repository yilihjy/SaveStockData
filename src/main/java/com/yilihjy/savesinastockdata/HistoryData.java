package com.yilihjy.savesinastockdata;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * 对新浪财经查询股票历史数据API的封装
 * @author yilihjy Email:yilihjy@gmail.com
 * @version 1.0.0
 *
 */
public class HistoryData {
	
	public static final String FIVE_MINUTES = "5";
	public static final String FIFTEEN_MINUTES = "15";
	public static final String THIRTY_MINUTES = "30";
	public static final String ONE_HOUR = "60";
	public static final String ONE_DAY = "240";
	public static final String ONE_WEEK = "1680";
	public static final String DEFAULT_DATALEN = "1000000";
	
	/**
	 * 获取股票历史数据
	 * @param code 股票代码，比如sz000002
	 * @param scale 查询时间
	 * @param datalen
	 * @return json字符串
	 */
	public static String getKLineData(String code, String scale, String datalen){
		String url = HistoryData.createURL(code, scale, datalen);
		return Tools.sendHTTPGET(url,"UTF-8");
	}
	
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
	
	public static String getKLineData(String code, String scale){
		return HistoryData.getKLineData(code, scale, HistoryData.DEFAULT_DATALEN);
	}
	
	public static String get5MKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.FIVE_MINUTES, datalen);
	}
	public static String get5MKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.FIVE_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	public static String get15MKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.FIFTEEN_MINUTES, datalen);
	}
	public static String get15MKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.FIFTEEN_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	public static String get30MKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.THIRTY_MINUTES, datalen);
	}
	public static String get30MKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.THIRTY_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	public static String get1HKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.ONE_HOUR, datalen);
	}
	public static String get1HKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.ONE_HOUR, HistoryData.DEFAULT_DATALEN);
	}
	public static String get1DKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.ONE_DAY, datalen);
	}
	public static String get1DKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.ONE_DAY, HistoryData.DEFAULT_DATALEN);
	}
	public static String get1WKlineData(String code, String datalen){
		return HistoryData.getKLineData(code, HistoryData.ONE_WEEK, datalen);
	}
	public static String get1WKlineData(String code){
		return HistoryData.getKLineData(code, HistoryData.ONE_WEEK, HistoryData.DEFAULT_DATALEN);
	}
	public static List<HistoryDataPOJO> getKLineDataObjects(String code, String scale){
		return HistoryData.getKLineDataObjects(code, scale,HistoryData.DEFAULT_DATALEN);
	}
	public static List<HistoryDataPOJO> get5MKLineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.FIVE_MINUTES, datalen);
	}
	public static List<HistoryDataPOJO> get5MKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.FIVE_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	public static List<HistoryDataPOJO> get15MKlineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.FIFTEEN_MINUTES, datalen);
	}
	public static List<HistoryDataPOJO> get15MKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.FIFTEEN_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	public static List<HistoryDataPOJO> get30MKlineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.THIRTY_MINUTES, datalen);
	}
	public static List<HistoryDataPOJO> get30MKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.THIRTY_MINUTES, HistoryData.DEFAULT_DATALEN);
	}
	public static List<HistoryDataPOJO> get1HKlineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_HOUR, datalen);
	}
	public static List<HistoryDataPOJO> get1HKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_HOUR, HistoryData.DEFAULT_DATALEN);
	}
	public static List<HistoryDataPOJO> get1DKlineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_DAY, datalen);
	}
	public static List<HistoryDataPOJO> get1DKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_DAY, HistoryData.DEFAULT_DATALEN);
	}
	public static List<HistoryDataPOJO> get1WKlineDataObjects(String code, String datalen){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_WEEK, datalen);
	}
	public static List<HistoryDataPOJO> get1WKlineDataObjects(String code){
		return HistoryData.getKLineDataObjects(code, HistoryData.ONE_WEEK, HistoryData.DEFAULT_DATALEN);
	}
}
