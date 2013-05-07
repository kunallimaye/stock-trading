/**
 * 
 */
package com.kunal.stock.dm.model;

/**
 * @author kunallimaye
 *
 */
public enum ExchangeEnum {

	ASX("ASX", "Australian Stock Exchange");
	
	private String symbol;
	private String name;
	/**
	 * @param symbol
	 * @param name
	 */
	private ExchangeEnum(String symbol, String name) {
		this.symbol = symbol;
		this.name = name;
	}
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
	
}
