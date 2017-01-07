package com.yilihjy.savesinastockdata;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对新浪财经查询股票实时数据API的封装<br>
 * API：http://hq.sinajs.cn/<br>
 * 封装的参数：list<br>
 * @author yilihjy Email:yilihjy@gmail.com
 * @version 1.0.0
 *
 */
public class RealTimeData {
	/**
	 * 获取股票历史数据
	 * 例子：<br>
	 * String[] codes = {"sz000002","sz000001"};<br>
	 * List&lt;RealTimeDataPOJO&gt; result = RealTimeData.getRealTimeDataObjects(codes);<br>
	 * 
	 * @param codes 股票代码数组 例如 {"sz000002","sz000001"}
	 * @return 一个{@link List}，里面是{@link RealTimeDataPOJO}对象
	 */
	public static List<RealTimeDataPOJO> getRealTimeDataObjects(String[] codes){
		String indexPatternString = "var hq_str_s_(\\w{8})=\"(.+)\"";
		String stockPatterString = "var hq_str_(\\w{8})=\"(.+)\"";
		Pattern indexPatter = Pattern.compile(indexPatternString);
		Pattern stockPatter = Pattern.compile(stockPatterString);
		List<RealTimeDataPOJO> result =new ArrayList<>();
		String url = createURL(codes);
		String response = Tools.sendHTTPGET(url,"GBK");
		String[] responses = response.split(";");
		for(int i=0;i<responses.length;i++){
			String reresponseString = responses[i];
			Matcher stockMatcher = stockPatter.matcher(reresponseString);
			if(stockMatcher.find()){
				RealTimeDataPOJO obj = new RealTimeDataPOJO();
				obj.setType(RealTimeDataPOJO.STOCK);
				obj.setFullCode(stockMatcher.group(1));
				String[] array = stockMatcher.group(2).split(",");
				obj.setName(array[0]);
				obj.setOpen(Double.parseDouble(array[1]));
				obj.setClose(Double.parseDouble(array[2]));
				obj.setNow(Double.parseDouble(array[3]));
				obj.setHigh(Double.parseDouble(array[4]));
				obj.setLow(Double.parseDouble(array[5]));
				obj.setBuyPrice(Double.parseDouble(array[6]));
				obj.setSellPrice(Double.parseDouble(array[7]));
				obj.setVolume(Double.parseDouble(array[8]));
				obj.setVolumePrice(Double.parseDouble(array[9]));
				obj.setBuy1Num(Double.parseDouble(array[10]));
				obj.setBuy1Pricae(Double.parseDouble(array[11]));
				obj.setBuy2Num(Double.parseDouble(array[12]));
				obj.setBuy2Pricae(Double.parseDouble(array[13]));
				obj.setBuy3Num(Double.parseDouble(array[14]));
				obj.setBuy3Pricae(Double.parseDouble(array[15]));
				obj.setBuy4Num(Double.parseDouble(array[16]));
				obj.setBuy4Pricae(Double.parseDouble(array[17]));
				obj.setBuy5Num(Double.parseDouble(array[18]));
				obj.setBuy5Pricae(Double.parseDouble(array[19]));
				obj.setSell1Num(Double.parseDouble(array[20]));
				obj.setSell1Pricae(Double.parseDouble(array[21]));
				obj.setSell2Num(Double.parseDouble(array[22]));
				obj.setSell2Pricae(Double.parseDouble(array[23]));
				obj.setSell3Num(Double.parseDouble(array[24]));
				obj.setSell3Pricae(Double.parseDouble(array[25]));
				obj.setSell4Num(Double.parseDouble(array[26]));
				obj.setSell4Pricae(Double.parseDouble(array[27]));
				obj.setSell5Num(Double.parseDouble(array[28]));
				obj.setSell5Pricae(Double.parseDouble(array[29]));
				LocalDateTime ldt = Tools.string2LocalDateTime(array[30]+" "+array[31]);
				obj.setDate(ldt.toLocalDate());
				obj.setTime(ldt.toLocalTime());
			    result.add(obj);
			}else{
				Matcher indexMatcher = indexPatter.matcher(reresponseString);
				if(indexMatcher.find()){
					RealTimeDataPOJO obj = new RealTimeDataPOJO();
					obj.setType(RealTimeDataPOJO.INDEX);
					obj.setFullCode(indexMatcher.group(1));
					String[] array = indexMatcher.group(2).split(",");
					obj.setName(array[0]);
					obj.setNow(Double.parseDouble(array[1]));
					obj.setRiseAndFall(Double.parseDouble(array[2]));
					obj.setRiseAndFallPercent(Double.parseDouble(array[3]));
					obj.setVolume(Double.parseDouble(array[4]));
					obj.setVolume(Double.parseDouble(array[5]));
					LocalDateTime ldt = LocalDateTime.now();
					obj.setDate(ldt.toLocalDate());
					obj.setTime(ldt.toLocalTime());
					result.add(obj);
				}
			}
		}
		return result;
	}
	
	private static String createURL(String[] codes){
		String codelist ="";
		for(int i=0;i<codes.length;i++){
			codelist +=codes[i];
			if(i != codes.length-1){
				codelist +=",";
			}
		}
		String url = String.format("http://hq.sinajs.cn/list=%s", codelist);
		return url;
	}
}
