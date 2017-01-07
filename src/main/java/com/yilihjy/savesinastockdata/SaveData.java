package com.yilihjy.savesinastockdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 将历史数据或实时数据保存进入数据库。<br>
 * 注意：此类未经过良好的测试，有潜在的问题，请谨慎使用！！！
 * @author yilihjy Email:yilihjy@gmail.com
 * @version 1.0.0
 *
 */
public class SaveData {
	
	private static final String DBURL_FORMAT ="jdbc:mysql://localhost:3306/%s?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC&useSSL=true";
	private final String USER;
	private final String PDWORD;
	private String url;
	
	private static final String HISTORY_5_MINUTES_TABLE_NAME = "history_5_minutes";
	private static final String HISTORY_15_MINUTES_TABLE_NAME = "history_15_minutes";
	private static final String HISTORY_30_MINUTES_TABLE_NAME = "history_30_minutes";
	private static final String HISTORY_1_HOUR_TABLE_NAME = "history_1_hour";
	private static final String HISTORY_1_DAY_TABLE_NAME = "history_1_day";
	private static final String HISTORY_1_WEEK_TABLE_NAME = "history_1_week";
	
	private static final String INSERT_HISTORY = "insert into `%s` ("
			+ "`day`,"
			+ "`full_code`,"
			+ "`open`,"
			+ "`high`,"
			+ "`low`,"
			+ "`close`,"
			+ "`volume`,"
			+ "`MA5`,"
			+ "`MA5_volume`,"
			+ "`MA10`,"
			+ "`MA10_volume`,"
			+ "`MA30`,"
			+ "`MA30_volume`) "
			+ "value (?,?,?,?,?,?,?,?,?,?,?,?,?);";
	
	private static final String CREATE_TABLES_STOCK_CODE = "create table if not exists stock_code("
			+ "code varchar(255) primary key,"
			+ "full_code varchar(255) not null,"
			+ "name varchar(255) not null"
			+ ")";
	private static final String INSERT_STOCK_CODE ="insert into stock_code ("
			+ "code,"
			+ "full_code,"
			+ "name"
			+ ") value(?,?,?);";
	private static final String CREATE_HISTORY_TABLE_FORMAT = "CREATE TABLE if not exists `%s` ("
			+ "`day`  datetime NOT NULL ,"
			+ "`full_code`  varchar(255) NOT NULL ,"
			+ "`open`  double NOT NULL ,"
			+ "`high`  double NOT NULL ,"
			+ "`low`  double NOT NULL ,"
			+ "`close`  double NOT NULL ,"
			+ "`volume`  double NOT NULL ,"
			+ "`MA5`  double NULL ,"
			+ "`MA5_volume`  double NULL ,"
			+ "`MA10`  double NULL ,"
			+ "`MA10_volume`  double NULL ,"
			+ "`MA30`  double NULL ,"
			+ "`MA30_volume`  double NULL ,"
			+ "PRIMARY KEY (`day`, `full_code`));";
	
	private static final String CREATE_REAL_TIME_TABLE ="CREATE TABLE if not exists `real_time` ("
			+ "`date`  date NOT NULL ,"
			+ "`time`  time NOT NULL ,"
			+ "`full_code`  varchar(255) NOT NULL ,"
			+ "`name`  text NOT NULL ,"
			+ "`open`  double NULL ,"
			+ "`close`  double NULL ,"
			+ "`now`  double NOT NULL ,"
			+ "`high`  double NULL ,"
			+ "`low`  double NULL ,"
			+ "`buy_price`  double NULL ,"
			+ "`sell_price`  double NULL ,"
			+ "`volume`  double NOT NULL ,"
			+ "`volume_price`  double NOT NULL ,"
			+ "`buy_1_num`  double NULL ,"
			+ "`buy_1_price`  double NULL ,"
			+ "`buy_2_num`  double NULL ,"
			+ "`buy_2_price`  double NULL ,"
			+ "`buy_3_num`  double NULL ,"
			+ "`buy_3_price`  double NULL ,"
			+ "`buy_4_num`  double NULL ,"
			+ "`buy_4_price`  double NULL ,"
			+ "`buy_5_num`  double NULL ,"
			+ "`buy_5_price`  double NULL ,"
			+ "`sell_1_num`  double NULL ,"
			+ "`sell_1_price`  double NULL ,"
			+ "`sell_2_num`  double NULL ,"
			+ "`sell_2_price`  double NULL ,"
			+ "`sell_3_num`  double NULL ,"
			+ "`sell_3_price`  double NULL ,"
			+ "`sell_4_num`  double NULL ,"
			+ "`sell_4_price`  double NULL ,"
			+ "`sell_5_num`  double NULL ,"
			+ "`sell_5_price`  double NULL ,"
			+ "`rise_fall`  double NULL ,"
			+ "`rise_fall_percent`  double NULL ,"
			+ "PRIMARY KEY (`date`, `time`, `full_code`)"
			+ ")"
			+ ";";
	private static final String INSERT_REAL_TIME = "insert into `real_time` ("
			+ "`date`,"
			+ "`time`,"
			+ "`full_code`,"
			+ "`name`,"
			+ "`open`,"
			+ "`close`,"
			+ "`now`,"
			+ "`high`,"
			+ "`low`,"
			+ "`buy_price`,"
			+ "`sell_price`,"
			+ "`volume`,"
			+ "`volume_price`,"
			+ "`buy_1_num`,"
			+ "`buy_1_price`,"
			+ "`buy_2_num`,"
			+ "`buy_2_price`,"
			+ "`buy_3_num`,"
			+ "`buy_3_price`,"
			+ "`buy_4_num`,"
			+ "`buy_4_price`,"
			+ "`buy_5_num`,"
			+ "`buy_5_price`,"
			+ "`sell_1_num`,"
			+ "`sell_1_price`,"
			+ "`sell_2_num`,"
			+ "`sell_2_price`,"
			+ "`sell_3_num`,"
			+ "`sell_3_price`,"
			+ "`sell_4_num`,"
			+ "`sell_4_price`,"
			+ "`sell_5_num`,"
			+ "`sell_5_price`,"
			+ "`rise_fall`,"
			+ "`rise_fall_percent`) "
			+ "value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "
			+ ";";
	/**
	 * 构造方法
	 * @param dbname 数据库名
	 * @param user 用户名
	 * @param pdword 密码
	 */
	public SaveData(String dbname, String user, String pdword){
		super();
		this.url = String.format(SaveData.DBURL_FORMAT, dbname);
		this.USER = user;
		this.PDWORD = pdword;
	}
	
	/**
	 * 初始化数据库，创建表
	 * @return 成功返回true
	 */
	public boolean initDB(){
		boolean result =false;
		Connection conn=getConnection();
		Statement stmt=null;
		if(conn!=null){
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(SaveData.CREATE_TABLES_STOCK_CODE);
				stmt.executeUpdate(String.format(SaveData.CREATE_HISTORY_TABLE_FORMAT, SaveData.HISTORY_5_MINUTES_TABLE_NAME));
				stmt.executeUpdate(String.format(SaveData.CREATE_HISTORY_TABLE_FORMAT, SaveData.HISTORY_15_MINUTES_TABLE_NAME));
				stmt.executeUpdate(String.format(SaveData.CREATE_HISTORY_TABLE_FORMAT, SaveData.HISTORY_30_MINUTES_TABLE_NAME));
				stmt.executeUpdate(String.format(SaveData.CREATE_HISTORY_TABLE_FORMAT, SaveData.HISTORY_1_HOUR_TABLE_NAME));
				stmt.executeUpdate(String.format(SaveData.CREATE_HISTORY_TABLE_FORMAT, SaveData.HISTORY_1_DAY_TABLE_NAME));
				stmt.executeUpdate(String.format(SaveData.CREATE_HISTORY_TABLE_FORMAT, SaveData.HISTORY_1_WEEK_TABLE_NAME));
				stmt.executeUpdate(SaveData.CREATE_REAL_TIME_TABLE);
				result =true;
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private Connection getConnection(){
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(this.url,USER,PDWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 保存历史数据
	 * @param fullCodeList 股票代码list，注意股票代码要有sz或sh前缀
	 */
	public void saveHistoryData(List<String> fullCodeList){
		Connection conn=getConnection();
		Statement stmt=null;
		if(conn!=null){
			try {
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				for(String fullCode:fullCodeList){
					List<List<HistoryDataPOJO>> llh = getHistoryData(fullCode);
					System.out.println("开始保存"+fullCode);
					executeHistorySQL(conn,stmt,llh.get(0),SaveData.HISTORY_5_MINUTES_TABLE_NAME,fullCode);
					executeHistorySQL(conn,stmt,llh.get(1),SaveData.HISTORY_15_MINUTES_TABLE_NAME,fullCode);
					executeHistorySQL(conn,stmt,llh.get(2),SaveData.HISTORY_30_MINUTES_TABLE_NAME,fullCode);
					executeHistorySQL(conn,stmt,llh.get(3),SaveData.HISTORY_1_HOUR_TABLE_NAME,fullCode);
					executeHistorySQL(conn,stmt,llh.get(4),SaveData.HISTORY_1_DAY_TABLE_NAME,fullCode);
					executeHistorySQL(conn,stmt,llh.get(5),SaveData.HISTORY_1_WEEK_TABLE_NAME,fullCode);
					conn.commit();
					System.out.println(fullCode+"保存完毕");
				}
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally{
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void executeHistorySQL(Connection conn,Statement stmt,List<HistoryDataPOJO> lh,String tableName,String fullCode) throws SQLException{
		String sqlString = String.format(SaveData.INSERT_HISTORY, tableName);
		for(HistoryDataPOJO obj:lh){
			PreparedStatement sql = conn.prepareStatement(sqlString);
			java.util.Date data = Tools.LocalDateTime2Date(obj.getDay());
			sql.setTimestamp(1, new Timestamp(data.getTime()));
			sql.setString(2, fullCode);
			sql.setDouble(3, obj.getOpen());
			sql.setDouble(4, obj.getHigh());
			sql.setDouble(5, obj.getLow());
			sql.setDouble(6, obj.getClose());
			sql.setDouble(7, obj.getVolume());
			sql.setDouble(8, obj.getMA5());
			sql.setDouble(9, obj.getMA5Volume());
			sql.setDouble(10, obj.getMA10());
			sql.setDouble(11, obj.getMA10Volume());
			sql.setDouble(12, obj.getMA30());
			sql.setDouble(13, obj.getMA30Volume());
			sql.executeUpdate();
		}
		
	}
	
	private List<List<HistoryDataPOJO>> getHistoryData(String fullCode){
		List<List<HistoryDataPOJO>> result = new ArrayList<>();
		System.out.println("开始查询"+fullCode);
		result.add(HistoryData.get5MKlineDataObjects(fullCode));
		result.add(HistoryData.get15MKlineDataObjects(fullCode));
		result.add(HistoryData.get30MKlineDataObjects(fullCode));
		result.add(HistoryData.get1HKlineDataObjects(fullCode));
		result.add(HistoryData.get1DKlineDataObjects(fullCode));
		result.add(HistoryData.get1WKlineDataObjects(fullCode));
		System.out.println(fullCode+"查询完毕");
		return result;
	}
	
	/**
	 * 保存历史数据，使用多线程<br>
	 * 注意：使用此方法需要谨慎，因为这个方法没有经过良好测试！！存在潜在问题！！
	 * @param fullCodeList 股票代码list，注意股票代码要有sz或sh前缀
	 * @param num 线程数
	 */
	public void saveHistoryDataPointThread(List<String> fullCodeList,int num){
		int trems =fullCodeList.size()/num;
		List<List<String>> ll = new ArrayList<>();
		int start =0;
		for(int m=0;m <num;m++){
			if(m == num-1){
				List<String> l = new ArrayList<>();
				while(start!=fullCodeList.size()-1){
					l.add(fullCodeList.get(start));
					start++;
				}
				ll.add(l);
			}else{
				List<String> l = new ArrayList<>();
				int count = 0;
				while(count < trems){
					if (start!=fullCodeList.size()-1){
						l.add(fullCodeList.get(start));
						start++;
						count++;
					}
				}
				ll.add(l);
			}
		}
		for(List<String> codes:ll){
			Thread t = new Thread(() -> {
				this.saveHistoryData(codes);
			});
			t.start();
		}
	}
	/**
	 * 保存实时数据，当数据库内已有数据时会打印错误信息
	 * 
	 * @param fullCodeList  股票代码list，注意股票代码要有sz或sh前缀
	 */
	public void saveRealTimeData(List<String> fullCodeList){
		String[] codes = new String[fullCodeList.size()];
		for(int i=0;i<fullCodeList.size();i++){
			codes[i] = fullCodeList.get(i);
		}
		List<RealTimeDataPOJO> lr =RealTimeData.getRealTimeDataObjects(codes);
		Connection conn=getConnection();
		Statement stmt=null;
		if(conn!=null){
			try {
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				for(RealTimeDataPOJO obj:lr){
					PreparedStatement sql = conn.prepareStatement(SaveData.INSERT_REAL_TIME);
					LocalDateTime ld = LocalDateTime.of(obj.getDate(), obj.getTime());
					sql.setDate(1,new java.sql.Date(Tools.LocalDateTime2Date(ld).getTime()));
					sql.setTime(2, new java.sql.Time(Tools.LocalDateTime2Date(ld).getTime()));
					sql.setString(3, obj.getFullCode());
					sql.setString(4, obj.getName());
					sql.setDouble(5, obj.getOpen());
					sql.setDouble(6, obj.getClose());
					sql.setDouble(7, obj.getNow());
					sql.setDouble(8, obj.getHigh());
					sql.setDouble(9, obj.getLow());
					sql.setDouble(10, obj.getBuyPrice());
					sql.setDouble(11, obj.getSellPrice());
					sql.setDouble(12, obj.getVolume());
					sql.setDouble(13, obj.getVolumePrice());
					sql.setDouble(14, obj.getBuy1Num());
					sql.setDouble(15, obj.getBuy1Pricae());
					sql.setDouble(16, obj.getBuy2Num());
					sql.setDouble(17, obj.getBuy2Pricae());
					sql.setDouble(18, obj.getBuy3Num());
					sql.setDouble(19, obj.getBuy3Pricae());
					sql.setDouble(20, obj.getBuy4Num());
					sql.setDouble(21, obj.getBuy4Pricae());
					sql.setDouble(22, obj.getBuy5Num());
					sql.setDouble(23, obj.getBuy5Pricae());
					sql.setDouble(24, obj.getSell1Num());
					sql.setDouble(25, obj.getSell1Pricae());
					sql.setDouble(26, obj.getSell2Num());
					sql.setDouble(27, obj.getSell2Pricae());
					sql.setDouble(28, obj.getSell3Num());
					sql.setDouble(29, obj.getSell3Pricae());
					sql.setDouble(30, obj.getSell4Num());
					sql.setDouble(31, obj.getSell4Pricae());
					sql.setDouble(32, obj.getSell5Num());
					sql.setDouble(33, obj.getSell5Pricae());
					sql.setDouble(34, obj.getRiseAndFall());
					sql.setDouble(35, obj.getRiseAndFallPercent());
					sql.executeUpdate();
				}
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally{
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 将股票代码插入数据库
	 * @param fullCode 股票代码，有sz或sh前缀
	 * @param code 股票代码，无sz或sh前缀
	 * @param name 股票名称
	 */
	public void insertCode(List<String> fullCode,List<String> code,List<String> name){
		Connection conn=getConnection();
		Statement stmt=null;
		if(conn!=null){
			try {
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				for(int i =0;i<fullCode.size();i++){
					PreparedStatement sql = conn.prepareStatement(SaveData.INSERT_STOCK_CODE);
					sql.setString(1, code.get(i));
					sql.setString(2, fullCode.get(i));
					sql.setString(3, name.get(i));
					sql.executeUpdate();
				}
				conn.commit();
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally{
				try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
