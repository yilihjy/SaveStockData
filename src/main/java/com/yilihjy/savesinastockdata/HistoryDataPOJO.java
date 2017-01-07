package com.yilihjy.savesinastockdata;

import java.time.LocalDateTime;
/**
 * 负责储存历史数据的对象
 * @author yilihjy Email:yilihjy@gmail.com
 * @version 1.0.0
 *
 */
public class HistoryDataPOJO {
	
	private LocalDateTime day;
	private double open;
	private double high;
	private double low;
	private double close;
	private double volume;
	private double MA5;
	private double MA5Volume;
	private double MA10;
	private double MA10Volume;
	private double MA30;
	private double MA30Volume;
	
	/**
	 * 无参数构造方法
	 */
	public HistoryDataPOJO(){
		super();
	}
	/**
	 * 
	 * @param day 时间
	 * @param open 开盘价
	 * @param high 最高价
	 * @param low 最低价
	 * @param close 收盘价
	 * @param volume 成交量，单位股，除以100为手
	 */
	public HistoryDataPOJO(LocalDateTime day,double open,double high ,double low ,double close,double volume){
		super();
		this.day = day;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}
	/**
	 * 
	 * @param day 时间
	 * @param open 开盘价
	 * @param high 最高价
	 * @param low 最低价
	 * @param close 收盘价
	 * @param volume 成交量，单位股，除以100为手
	 * @param MA5 五日平均价
	 * @param MA5Volume 五日平均交易量
	 */
	public HistoryDataPOJO(LocalDateTime day,double open,double high ,double low ,double close,double volume,double MA5,double MA5Volume){
		this(day,open,high ,low ,close,volume);
		this.MA5 = MA5;
		this.MA5Volume = MA5Volume;
	}
	/**
	 * 
	 * @param day 时间
	 * @param open 开盘价
	 * @param high 最高价
	 * @param low 最低价
	 * @param close 收盘价
	 * @param volume 成交量，单位股，除以100为手
	 * @param MA5 五日平均价
	 * @param MA5Volume 五日平均交易量
	 * @param MA10 十日平均价
	 * @param MA10Volume 十日平均交易量
	 */
	public HistoryDataPOJO(LocalDateTime day,double open,double high ,double low ,double close,double volume,double MA5,double MA5Volume,double MA10,double MA10Volume){
		this(day,open,high ,low ,close,volume,MA5,MA5Volume);
		this.MA10 = MA10;
		this.MA10Volume = MA10Volume;
	}
	/**
	 * 
	 * @param day 时间
	 * @param open 开盘价
	 * @param high 最高价
	 * @param low 最低价
	 * @param close 收盘价
	 * @param volume 成交量，单位股，除以100为手
	 * @param MA5 五日平均价
	 * @param MA5Volume 五日平均交易量
	 * @param MA10 十日平均价
	 * @param MA10Volume 十日平均交易量
	 * @param MA30 三十日平均价
	 * @param MA30Volume 三十日平均交易量
	 */
	public HistoryDataPOJO(LocalDateTime day,double open,double high ,double low ,double close,double volume,double MA5,double MA5Volume,double MA10,double MA10Volume,double MA30,double MA30Volume){
		this(day,open,high ,low ,close,volume,MA5,MA5Volume,MA10,MA10Volume);
		this.MA30 = MA30;
		this.MA30Volume = MA30Volume;
	}
	/**
	 * @return the day
	 */
	public LocalDateTime getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(LocalDateTime day) {
		this.day = day;
	}
	/**
	 * @return the open
	 */
	public double getOpen() {
		return open;
	}
	/**
	 * @param open the open to set
	 */
	public void setOpen(double open) {
		this.open = open;
	}
	/**
	 * @return the high
	 */
	public double getHigh() {
		return high;
	}
	/**
	 * @param high the high to set
	 */
	public void setHigh(double high) {
		this.high = high;
	}
	/**
	 * @return the low
	 */
	public double getLow() {
		return low;
	}
	/**
	 * @param low the low to set
	 */
	public void setLow(double low) {
		this.low = low;
	}
	/**
	 * @return the close
	 */
	public double getClose() {
		return close;
	}
	/**
	 * @param close the close to set
	 */
	public void setClose(double close) {
		this.close = close;
	}
	/**
	 * @return the volume
	 */
	public double getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(double volume) {
		this.volume = volume;
	}
	/**
	 * @return the mA5
	 */
	public double getMA5() {
		return MA5;
	}
	/**
	 * @param mA5 the mA5 to set
	 */
	public void setMA5(double mA5) {
		MA5 = mA5;
	}
	/**
	 * @return the mA5Volume
	 */
	public double getMA5Volume() {
		return MA5Volume;
	}
	/**
	 * @param mA5Volume the mA5Volume to set
	 */
	public void setMA5Volume(double mA5Volume) {
		MA5Volume = mA5Volume;
	}
	/**
	 * @return the mA10
	 */
	public double getMA10() {
		return MA10;
	}
	/**
	 * @param mA10 the mA10 to set
	 */
	public void setMA10(double mA10) {
		MA10 = mA10;
	}
	/**
	 * @return the mA10Volume
	 */
	public double getMA10Volume() {
		return MA10Volume;
	}
	/**
	 * @param mA10Volume the mA10Volume to set
	 */
	public void setMA10Volume(double mA10Volume) {
		MA10Volume = mA10Volume;
	}
	/**
	 * @return the mA30
	 */
	public double getMA30() {
		return MA30;
	}
	/**
	 * @param mA30 the mA30 to set
	 */
	public void setMA30(double mA30) {
		MA30 = mA30;
	}
	/**
	 * @return the mA30Volume
	 */
	public double getMA30Volume() {
		return MA30Volume;
	}
	/**
	 * @param mA30Volume the mA30Volume to set
	 */
	public void setMA30Volume(double mA30Volume) {
		MA30Volume = mA30Volume;
	}
}
