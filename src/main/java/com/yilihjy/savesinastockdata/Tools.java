package com.yilihjy.savesinastockdata;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;
/**
 * 提供一些工具方法
 * @author yilihjy Email:yilihjy@gmail.com
 * @version 1.0.0
 *
 */
public class Tools {
	/**
	 * 发送get请求，返回内容字符串
	 * @param url 请求urll
	 * @param charsetName 字符码
	 * @return 响应内容字符串
	 */
	public static String sendHTTPGET(String url,String charsetName){
		String result =null;
		HttpGet httpGet = new HttpGet(url);
		try (
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpGet);)
		{
			int status = response.getStatusLine().getStatusCode();
			if(status == 200){
				HttpEntity entity = response.getEntity();
				result = InputStreamToString(entity.getContent(),charsetName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 将如"2017-01-07 14:07:35"或"2017-01-07"这样的字符串转换为LocalDateTime对象
	 * @param time 时间字符串
	 * @return {@link LocalDateTime}对象
	 */
	public static LocalDateTime string2LocalDateTime(String time){
		LocalDateTime result;
		String[] times = time.split(" ");
		if(times.length == 1){
			times = time.split("-");
			result = LocalDateTime.of(Integer.parseInt(times[0]), Integer.parseInt(times[1]), Integer.parseInt(times[2]), 15, 0);
		}else{
			String[] day = times[0].split("-");
			String[] daytime = times[1].split(":");
			result = LocalDateTime.of(Integer.parseInt(day[0]), Integer.parseInt(day[1]), Integer.parseInt(day[2]), Integer.parseInt(daytime[0]), Integer.parseInt(daytime[1]), Integer.parseInt(daytime[2]));
		}
		return result;
	}
	/**
	 * 将{@link InputStream}转换为{@link String}
	 * @param in {@link InputStream}
	 * @param charsetName 字符串编码
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static String InputStreamToString(InputStream in,String charsetName) throws UnsupportedEncodingException, IOException{
		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b)) != -1){
		sb.append(new String(b, 0, len, charsetName));}
		return sb.toString();
	}
	
	/**
	 * 从{@link readExcel2JSON}方法生成的文件中读取指定属性
	 * @param file 文件路径
	 * @param key 关键词
	 * @return 一个{@link List}
	 * @throws Exception
	 */
	public static List<String> getValueFromJSONFile(String file,String key) throws  Exception{
		List<String> result = new ArrayList<>();
		InputStream inp = new FileInputStream(file);
		JSONArray jsonarray = new JSONArray(Tools.InputStreamToString(inp, "UTF-8"));
		for(int i=0;i<jsonarray.length();i++){
			JSONObject obj = jsonarray.optJSONObject(i);
			if(obj !=null){
				String value = obj.optString(key);
				if(!value.equals("")){
					result.add(value);
				}
			}
		}
		return result;
	}
	
	/**
	 * 将{@link LocalDateTime}转换为旧式{@link Date}
	 * @param localDateTime {@link LocalDateTime}对象
	 * @return {@link Date}对象
	 */
	public static Date LocalDateTime2Date(LocalDateTime localDateTime) {
	    ZoneId zone = ZoneId.of("UTC");
	    Instant instant = localDateTime.atZone(zone).toInstant();
	    return Date.from(instant);
	}
	
	/**
	 * 读取excel文件，生成一个json文件，文件格式见项目根目录的.xlsx文件，<a href="https://github.com/yilihjy/SaveSinaStockData">项目主页</a>
	 * @param inFileName excel文件文件路径
	 * @param outFileName 输出文件路径
	 */
	public static void readExcel2JSON(String inFileName, String outFileName){
		try 
		(
			InputStream inp = new FileInputStream(inFileName);
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outFileName)));)
		{   Workbook wb = WorkbookFactory.create(inp);
		    Sheet sheet = wb.getSheetAt(0);
		    Boolean flag = true;
		    int startRow = 1;
		    JSONWriter json = new JSONWriter(out);
		    json.array();
		    while(flag){
		    	Row row = sheet.getRow(startRow++);
		    	if(row != null){
		    		Cell code = row.getCell(0);
			    	Cell name = row.getCell(1);
			    	if(code != null && name != null){
			    		String codeString = code.getStringCellValue();
			    		String nameString = name.getStringCellValue();
			    		String fullCode = "";
			    		switch (codeString.charAt(0)){
			    		case '6': fullCode = "sh"+codeString;
			    		          break;
			    		case '0': fullCode = "sz"+codeString;
	    		                  break;
			    		case '3': fullCode = "sz"+codeString;
		                          break;
			    		}
			    		json.object();
			    		json.key("code").value(codeString);
			    		json.key("fullCode").value(fullCode);
			    		json.key("name").value(nameString);
			    		json.endObject();
			    	}else{
			    		flag = false;
			    	}
		    	}else{
		    		flag = false;
		    	}
		    }
		    json.endArray();
		} catch (FileNotFoundException e) {
			System.out.print("Don't find "+inFileName);
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
