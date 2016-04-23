
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import jxl.*;
import jxl.read.biff.BiffException;

/**
 * 将沪深A股的股票代码存入数据库
 * @author yilihjy
 *
 */
public class ReadExcelAndSaveStockCode {
	//请根据情况修改
	public static final String URL="jdbc:mysql://localhost:3306/sinastockdata?user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
	public static void main(String[] args) throws SQLException {
		Connection coon=null;
		Statement statement=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			coon=DriverManager.getConnection(URL);
			coon.setAutoCommit(false);
			statement= coon.createStatement();
			Workbook workbook=Workbook.getWorkbook(new File("沪深Ａ股.xls"));
			Sheet sheet =workbook.getSheet(0);
			for(int i=1;i<2845;i++){
				String code=sheet.getCell(0, i).getContents().trim();
				String name=sheet.getCell(1, i).getContents().trim();
				String insert= "insert into stock_code_name(stock_code,stock_name) values(\""+code+"\",\""+name+"\")";
				statement.executeUpdate(insert);
			}
			coon.commit();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			statement.close();
			coon.close();
		}
	}

}
