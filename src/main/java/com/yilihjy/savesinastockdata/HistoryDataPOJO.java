package com.yilihjy.savesinastockdata;

import java.time.LocalDateTime;
/**
 * 
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
	
	public HistoryDataPOJO(){
		super();
	}
	public HistoryDataPOJO(LocalDateTime day,double open,double high ,double low ,double close,double volume){
		super();
		this.day = day;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
	}
	public HistoryDataPOJO(LocalDateTime day,double open,double high ,double low ,double close,double volume,double MA5,double MA5Volume){
		this(day,open,high ,low ,close,volume);
		this.MA5 = MA5;
		this.MA5Volume = MA5Volume;
	}
	
	public HistoryDataPOJO(LocalDateTime day,double open,double high ,double low ,double close,double volume,double MA5,double MA5Volume,double MA10,double MA10Volume){
		this(day,open,high ,low ,close,volume,MA5,MA5Volume);
		this.MA10 = MA10;
		this.MA10Volume = MA10Volume;
	}
	public HistoryDataPOJO(LocalDateTime day,double open,double high ,double low ,double close,double volume,double MA5,double MA5Volume,double MA10,double MA10Volume,double MA30,double MA30Volume){
		this(day,open,high ,low ,close,volume,MA5,MA5Volume,MA10,MA10Volume);
		this.MA30 = MA30;
		this.MA30Volume = MA30Volume;
	}
	public LocalDateTime getDay() {
		return day;
	}
	public void setDay(LocalDateTime day) {
		this.day = day;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getMA5() {
		return MA5;
	}
	public void setMA5(double mA5) {
		MA5 = mA5;
	}
	public double getMA5Volume() {
		return MA5Volume;
	}
	public void setMA5Volume(double mA5Volume) {
		MA5Volume = mA5Volume;
	}
	public double getMA10() {
		return MA10;
	}
	public void setMA10(double mA10) {
		MA10 = mA10;
	}
	public double getMA10Volume() {
		return MA10Volume;
	}
	public void setMA10Volume(double mA10Volume) {
		MA10Volume = mA10Volume;
	}
	public double getMA30() {
		return MA30;
	}
	public void setMA30(double mA30) {
		MA30 = mA30;
	}
	public double getMA30Volume() {
		return MA30Volume;
	}
	public void setMA30Volume(double mA30Volume) {
		MA30Volume = mA30Volume;
	}
	
}
