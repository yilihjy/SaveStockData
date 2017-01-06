package com.yilihjy.savesinastockdata;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * 
 * @author yilihjy Email:yilihjy@gmail.com
 * @version 1.0.0
 *
 */
public class RealTimeDataPOJO {
	
	public static final int INDEX = 1;
	public static final int STOCK = 2;
	
	
	private int type;
	private String fullCode;
	private String name;
	private double open;
	private double close;
	private double now;
	private double high;
	private double low;
	private double buyPrice;
	private double sellPrice;
	private double volume;
	private double volumePrice;
	private double buy1Num;
	private double buy1Pricae;
	private double buy2Num;
	private double buy2Pricae;
	private double buy3Num;
	private double buy3Pricae;
	private double buy4Num;
	private double buy4Pricae;
	private double buy5Num;
	private double buy5Pricae;
	private double sell1Num;
	private double sell1Pricae;
	private double sell2Num;
	private double sell2Pricae;
	private double sell3Num;
	private double sell3Pricae;
	private double sell4Num;
	private double sell4Pricae;
	private double sell5Num;
	private double sell5Pricae;
	private LocalDate date;
	private LocalTime time;
	private double riseAndFall;
	private double riseAndFallPercent;
	
	public RealTimeDataPOJO(){
		super();
	}
	
	
	public RealTimeDataPOJO(int type, String fullCode, String name, double now, double volume, double volumePrice,
			double riseAndFall, double riseAndFallPercent) {
		super();
		this.type = type;
		this.fullCode = fullCode;
		this.name = name;
		this.now = now;
		this.volume = volume;
		this.volumePrice = volumePrice;
		this.riseAndFall = riseAndFall;
		this.riseAndFallPercent = riseAndFallPercent;
	}
	public RealTimeDataPOJO(int type, String fullCode, String name, double open, double close, double now, double high,
			double low, double buyPrice, double sellPrice, double volume, double volumePrice, double buy1Num,
			double buy1Pricae, double buy2Num, double buy2Pricae, double buy3Num, double buy3Pricae, double buy4Num,
			double buy4Pricae, double buy5Num, double buy5Pricae, double sell1Num, double sell1Pricae, double sell2Num,
			double sell2Pricae, double sell3Num, double sell3Pricae, double sell4Num, double sell4Pricae,
			double sell5Num, double sell5Pricae, LocalDate date, LocalTime time) {
		super();
		this.type = type;
		this.fullCode = fullCode;
		this.name = name;
		this.open = open;
		this.close = close;
		this.now = now;
		this.high = high;
		this.low = low;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.volume = volume;
		this.volumePrice = volumePrice;
		this.buy1Num = buy1Num;
		this.buy1Pricae = buy1Pricae;
		this.buy2Num = buy2Num;
		this.buy2Pricae = buy2Pricae;
		this.buy3Num = buy3Num;
		this.buy3Pricae = buy3Pricae;
		this.buy4Num = buy4Num;
		this.buy4Pricae = buy4Pricae;
		this.buy5Num = buy5Num;
		this.buy5Pricae = buy5Pricae;
		this.sell1Num = sell1Num;
		this.sell1Pricae = sell1Pricae;
		this.sell2Num = sell2Num;
		this.sell2Pricae = sell2Pricae;
		this.sell3Num = sell3Num;
		this.sell3Pricae = sell3Pricae;
		this.sell4Num = sell4Num;
		this.sell4Pricae = sell4Pricae;
		this.sell5Num = sell5Num;
		this.sell5Pricae = sell5Pricae;
		this.date = date;
		this.time = time;
	}
	/**
	 * @return the fullCode
	 */
	public String getFullCode() {
		return fullCode;
	}
	/**
	 * @param fullCode the fullCode to set
	 */
	public void setFullCode(String fullCode) {
		this.fullCode = fullCode;
	}
	/**
	 * @return the riseAndFall
	 */
	public double getRiseAndFall() {
		return riseAndFall;
	}
	/**
	 * @param riseAndFall the riseAndFall to set
	 */
	public void setRiseAndFall(double riseAndFall) {
		this.riseAndFall = riseAndFall;
	}
	/**
	 * @return the riseAndFallPercent
	 */
	public double getRiseAndFallPercent() {
		return riseAndFallPercent;
	}
	/**
	 * @param riseAndFallPercent the riseAndFallPercent to set
	 */
	public void setRiseAndFallPercent(double riseAndFallPercent) {
		this.riseAndFallPercent = riseAndFallPercent;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the now
	 */
	public double getNow() {
		return now;
	}
	/**
	 * @param now the now to set
	 */
	public void setNow(double now) {
		this.now = now;
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
	 * @return the buyPrice
	 */
	public double getBuyPrice() {
		return buyPrice;
	}
	/**
	 * @param buyPrice the buyPrice to set
	 */
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	/**
	 * @return the sellPrice
	 */
	public double getSellPrice() {
		return sellPrice;
	}
	/**
	 * @param sellPrice the sellPrice to set
	 */
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
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
	 * @return the volumePrice
	 */
	public double getVolumePrice() {
		return volumePrice;
	}
	/**
	 * @param volumePrice the volumePrice to set
	 */
	public void setVolumePrice(double volumePrice) {
		this.volumePrice = volumePrice;
	}
	/**
	 * @return the buy1Num
	 */
	public double getBuy1Num() {
		return buy1Num;
	}
	/**
	 * @param buy1Num the buy1Num to set
	 */
	public void setBuy1Num(double buy1Num) {
		this.buy1Num = buy1Num;
	}
	/**
	 * @return the buy1Pricae
	 */
	public double getBuy1Pricae() {
		return buy1Pricae;
	}
	/**
	 * @param buy1Pricae the buy1Pricae to set
	 */
	public void setBuy1Pricae(double buy1Pricae) {
		this.buy1Pricae = buy1Pricae;
	}
	/**
	 * @return the buy2Num
	 */
	public double getBuy2Num() {
		return buy2Num;
	}
	/**
	 * @param buy2Num the buy2Num to set
	 */
	public void setBuy2Num(double buy2Num) {
		this.buy2Num = buy2Num;
	}
	/**
	 * @return the buy2Pricae
	 */
	public double getBuy2Pricae() {
		return buy2Pricae;
	}
	/**
	 * @param buy2Pricae the buy2Pricae to set
	 */
	public void setBuy2Pricae(double buy2Pricae) {
		this.buy2Pricae = buy2Pricae;
	}
	/**
	 * @return the buy3Num
	 */
	public double getBuy3Num() {
		return buy3Num;
	}
	/**
	 * @param buy3Num the buy3Num to set
	 */
	public void setBuy3Num(double buy3Num) {
		this.buy3Num = buy3Num;
	}
	/**
	 * @return the buy3Pricae
	 */
	public double getBuy3Pricae() {
		return buy3Pricae;
	}
	/**
	 * @param buy3Pricae the buy3Pricae to set
	 */
	public void setBuy3Pricae(double buy3Pricae) {
		this.buy3Pricae = buy3Pricae;
	}
	/**
	 * @return the buy4Num
	 */
	public double getBuy4Num() {
		return buy4Num;
	}
	/**
	 * @param buy4Num the buy4Num to set
	 */
	public void setBuy4Num(double buy4Num) {
		this.buy4Num = buy4Num;
	}
	/**
	 * @return the buy4Pricae
	 */
	public double getBuy4Pricae() {
		return buy4Pricae;
	}
	/**
	 * @param buy4Pricae the buy4Pricae to set
	 */
	public void setBuy4Pricae(double buy4Pricae) {
		this.buy4Pricae = buy4Pricae;
	}
	/**
	 * @return the buy5Num
	 */
	public double getBuy5Num() {
		return buy5Num;
	}
	/**
	 * @param buy5Num the buy5Num to set
	 */
	public void setBuy5Num(double buy5Num) {
		this.buy5Num = buy5Num;
	}
	/**
	 * @return the buy5Pricae
	 */
	public double getBuy5Pricae() {
		return buy5Pricae;
	}
	/**
	 * @param buy5Pricae the buy5Pricae to set
	 */
	public void setBuy5Pricae(double buy5Pricae) {
		this.buy5Pricae = buy5Pricae;
	}
	/**
	 * @return the sell1Num
	 */
	public double getSell1Num() {
		return sell1Num;
	}
	/**
	 * @param sell1Num the sell1Num to set
	 */
	public void setSell1Num(double sell1Num) {
		this.sell1Num = sell1Num;
	}
	/**
	 * @return the sell1Pricae
	 */
	public double getSell1Pricae() {
		return sell1Pricae;
	}
	/**
	 * @param sell1Pricae the sell1Pricae to set
	 */
	public void setSell1Pricae(double sell1Pricae) {
		this.sell1Pricae = sell1Pricae;
	}
	/**
	 * @return the sell2Num
	 */
	public double getSell2Num() {
		return sell2Num;
	}
	/**
	 * @param sell2Num the sell2Num to set
	 */
	public void setSell2Num(double sell2Num) {
		this.sell2Num = sell2Num;
	}
	/**
	 * @return the sell2Pricae
	 */
	public double getSell2Pricae() {
		return sell2Pricae;
	}
	/**
	 * @param sell2Pricae the sell2Pricae to set
	 */
	public void setSell2Pricae(double sell2Pricae) {
		this.sell2Pricae = sell2Pricae;
	}
	/**
	 * @return the sell3Num
	 */
	public double getSell3Num() {
		return sell3Num;
	}
	/**
	 * @param sell3Num the sell3Num to set
	 */
	public void setSell3Num(double sell3Num) {
		this.sell3Num = sell3Num;
	}
	/**
	 * @return the sell3Pricae
	 */
	public double getSell3Pricae() {
		return sell3Pricae;
	}
	/**
	 * @param sell3Pricae the sell3Pricae to set
	 */
	public void setSell3Pricae(double sell3Pricae) {
		this.sell3Pricae = sell3Pricae;
	}
	/**
	 * @return the sell4Num
	 */
	public double getSell4Num() {
		return sell4Num;
	}
	/**
	 * @param sell4Num the sell4Num to set
	 */
	public void setSell4Num(double sell4Num) {
		this.sell4Num = sell4Num;
	}
	/**
	 * @return the sell4Pricae
	 */
	public double getSell4Pricae() {
		return sell4Pricae;
	}
	/**
	 * @param sell4Pricae the sell4Pricae to set
	 */
	public void setSell4Pricae(double sell4Pricae) {
		this.sell4Pricae = sell4Pricae;
	}
	/**
	 * @return the sell5Num
	 */
	public double getSell5Num() {
		return sell5Num;
	}
	/**
	 * @param sell5Num the sell5Num to set
	 */
	public void setSell5Num(double sell5Num) {
		this.sell5Num = sell5Num;
	}
	/**
	 * @return the sell5Pricae
	 */
	public double getSell5Pricae() {
		return sell5Pricae;
	}
	/**
	 * @param sell5Pricae the sell5Pricae to set
	 */
	public void setSell5Pricae(double sell5Pricae) {
		this.sell5Pricae = sell5Pricae;
	}
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	/**
	 * @return the time
	 */
	public LocalTime getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	
}
