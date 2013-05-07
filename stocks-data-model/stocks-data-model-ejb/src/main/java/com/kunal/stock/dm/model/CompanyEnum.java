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

	ANZ("ANZ", "Australia And New Zealand Banking Group Limited", EnumSet.of(IndexEnum.XFJ, IndexEnum.XXJ)),
	BEN("BEN", "Bendigo and Adelaide Bank Limited", EnumSet.of(IndexEnum.XFJ, IndexEnum.XXJ)),
	BHP("BHP", "BHP Billion Limited", EnumSet.of(IndexEnum.XMJ, IndexEnum.XJR)),
	COH("COH", "Cochlear Limited", EnumSet.of(IndexEnum.XHJ));
	
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
