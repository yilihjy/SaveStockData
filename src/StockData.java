import java.sql.Timestamp;

public class StockData {
	
	private Timestamp day;
	private double open;
	private double close;
	private double low;
	private double high;
	private long volume;
	
	public void setDay(String day){
		this.day=Timestamp.valueOf(day);
	}
	
	public void setOpen(String open){
		this.open=Double.valueOf(open);
	}
	
	public void setClose(String close){
		this.close=Double.valueOf(close);
	}
	
	public void setLow(String low){
		this.low=Double.valueOf(low);
	}
	
	public void setHigh(String high){
		this.high=Double.valueOf(high);
	}
	
	public void setVolume(String volume){
		this.volume=Long.valueOf(volume);
	}
	
	public Timestamp getDay(){
		return day;
	}
	
	public double getOpen(){
		return open;
	}
	
	public double getClose(){
		return close;
	}
	
	public double getLow(){
		return low;
	}
	
	public double getHigh(){
		return high;
	}
	
	public long getVolume(){
		return volume;
	}
}
