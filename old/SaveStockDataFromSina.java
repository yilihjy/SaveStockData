
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * 查询并保存数据
 * @author yilihjy
 *
 */
public class SaveStockDataFromSina implements Runnable{
	
	private String jsonString;
	private String code;
	private String sheet;
	
	public SaveStockDataFromSina(String jsonString,String code,String sheet){
		this.jsonString=jsonString;
		this.code=code;
		this.sheet=sheet;
	}

	public static void main(String[] args) throws SQLException {
		Connection coon=null;
		Statement statement=null;
		
		ArrayList<String> codes=new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			coon=DriverManager.getConnection(ReadExcelAndSaveStockCode.URL);
		    statement= coon.createStatement();
		    String sql = "select stock_code FROM stock_code_name";
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while(rs.next()){
		    	
		    		codes.add(rs.getString("stock_code"));
		
		    }
		    
		    
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			statement.close();
			coon.close();
		}
		Iterator<String> it=codes.iterator();
		while(it.hasNext()){
			String code=it.next();
			queryStock(code,5);
			queryStock(code,15);
			queryStock(code,30);
			queryStock(code,60);
			queryStock(code,240);
			queryStock(code,1680);
		}

	}
	
	
	
	private static <T> ArrayList<T> JSONArrayStringToArrayList(String jsonString,Class<T> classofT){
		Gson gson=new Gson();
		JsonParser parser = new JsonParser();
		JsonElement el = parser.parse(jsonString);
		JsonArray jsonArray=el.getAsJsonArray();
		Iterator<JsonElement> it = jsonArray.iterator();
		ArrayList<T> al=new ArrayList<>();
		while(it.hasNext()){
			JsonElement e = it.next();
			T data=gson.fromJson(e, classofT);
			al.add(data);
		}
		return al;
	}
	
	private static String InputStreamToString(InputStream in) throws UnsupportedEncodingException, IOException{
		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = in.read(b)) != -1){
		sb.append(new String(b, 0, len, "UTF-8"));}
		return sb.toString();
	}
	
	private static void saveStock(ArrayList<StockData> arraylist,String code,String sheet) throws SQLException, ClassNotFoundException{
		Connection coon = null;
		Statement statement = null;
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/sinastockdata?"
				+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		coon = DriverManager.getConnection(url);
		coon.setAutoCommit(false);
		statement = coon.createStatement();
		Iterator<StockData> it = arraylist.iterator();
		while (it.hasNext()) {
			StockData s = it.next();
			String sql = "insert into " + sheet + "(stock_code,stock_time,open,close,high,low,volume) values(\"";
			sql += code + "\",\"";
			sql += s.getDay() + "\",";
			sql += s.getOpen() + ",";
			sql += s.getClose() + ",";
			sql += s.getHigh() + ",";
			sql += s.getLow() + ",";
			sql += s.getVolume() + ")";
			statement.executeUpdate(sql);
		}
		coon.commit();
		coon.close();
	}
	
	public static void queryStock(String code,int type){
		String codeFlag="";
		switch (code.charAt(0)){
		case '0':codeFlag="sz"+code;
		break;
		case '3':codeFlag="sz"+code;
		break;
		case '6':codeFlag="sh"+code;
		break;
		}
		String sheet="";
		switch(type){
		case 5:sheet="5mLine";
		break;
		case 15:sheet="15mLine";
		break;
		case 30:sheet="30mLine";
		break;
		case 60:sheet="1hLine";
		break;
		case 240:sheet="dailyline";
		break;
		case 1680:sheet="weekline";
		break;
		}
		HttpResponse ss;
		try {
			ss = Request.Get("http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol="+codeFlag+"&scale="+type+"&ma=no&datalen=100000").execute().returnResponse();
			if(ss.getStatusLine().getStatusCode()==200){
				System.out.println(codeFlag+"查询成功，scale值为"+type);
				new Thread(new SaveStockDataFromSina(InputStreamToString(ss.getEntity().getContent()),code,sheet)).start();;
			}
			ss=null;
		} catch (ClientProtocolException e) {
			noteError(code,type);
		} catch (IOException e) {
			noteError(code,type);
		}
		
	}

	@Override
	public void run() {
		try {
			ArrayList<StockData> al=JSONArrayStringToArrayList(jsonString,StockData.class);
			saveStock(al,code,sheet);
			System.out.println(code+"已存入"+sheet);
		} catch (Exception e) {
			noteError(code,sheet);
		}
	}
	
	private static  void noteError(String code,int scale){
		FileOutputStream fos = null;  
        OutputStreamWriter osw = null;  
        try {
        	fos = new FileOutputStream("ErrorLog.xml", true); 
        	osw = new OutputStreamWriter(fos, "utf-8");
        	String out="<error><time>"+(new Date()).toString()+"</time><code>"+code+"</code><scale>"+scale+"</scale></error>";
			osw.write(out);
		} catch (IOException e) {
		} finally{
			try {
				osw.close();
			} catch (IOException e) {
			}finally{
				 try {
					fos.close();
				} catch (IOException e) {
				} 
			}
	       
		}
         
	}
private static void noteError(String code,String type){
	int sheet=0;
	switch(type){
	case "5mLine":sheet=5;
	break;
	case "15mLine":sheet=15;
	break;
	case "30mLine":sheet=30;
	break;
	case "1hLine":sheet=60;
	break;
	case "dailyline":sheet=240;
	break;
	case "weekline":sheet=1680;
	break;
	}

	noteError(code,sheet);
	}
	
	//CloseableHttpClient httpClient= HttpClients.createDefault();
			//HttpGet httpGet = new HttpGet("http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sz000025&scale=5&ma=no&datalen=10000");
			//CloseableHttpResponse response=null;
			//try {
				//response = httpClient.execute(httpGet);
				//System.out.println(response.getStatusLine().getStatusCode());
				//System.out.println(InputStreamToString(response.getEntity().getContent()));
			//} catch (IOException e) {
				//e.printStackTrace();
			//} catch (UnsupportedOperationException e) {
				//e.printStackTrace();
			//} catch (Exception e) {
				//e.printStackTrace();
			//}finally{
				//response.close();
			//}
	
	
}
