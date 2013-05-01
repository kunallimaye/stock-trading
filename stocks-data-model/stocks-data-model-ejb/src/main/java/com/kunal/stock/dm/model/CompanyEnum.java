/**
 * 
 */
package com.kunal.stock.dm.model;

import java.util.EnumSet;

/**
 * @author kunallimaye
 *
 */
public enum CompanyEnum {

	ANZ("ANZ", "ANZ Bank", EnumSet.of(IndexEnum.XFJ));
	
	String companySymbol;
	String companyName;
	EnumSet<IndexEnum> sectors;
	
	/**
	 * @param companySymbol
	 * @param companyName
	 * @param sectors
	 */
	private CompanyEnum(String companySymbol, String companyName,
			EnumSet<IndexEnum> sectors) {
		this.companySymbol = companySymbol;
		this.companyName = companyName;
		this.sectors = sectors;
	}
	/**
	 * @return the companySymbol
	 */
	public String getCompanySymbol() {
		return companySymbol;
	}
	/**
	 * @param companySymbol the companySymbol to set
	 */
	public void setCompanySymbol(String companySymbol) {
		this.companySymbol = companySymbol;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the sectors
	 */
	public EnumSet<IndexEnum> getSectors() {
		return sectors;
	}
	/**
	 * @param sectors the sectors to set
	 */
	public void setSectors(EnumSet<IndexEnum> sectors) {
		this.sectors = sectors;
	}
}
