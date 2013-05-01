/**
 * 
 */
package com.kunal.stock.dm.model;

/**
 * @author kunallimaye
 *
 */
public enum IndexEnum {

	XGD("XGD", "S&P/ASX All Ordinaries Gold Index"),
	XPJ("XPJ", "S&P/ASX 200 A-REIT Index"),
	XDJ("XDJ", "S&P/ASX 200 Consumer Discretionary Index"),
	XSJ("XSJ", "S&P/ASX 200 Consumer Staples Index"),
	XEJ("XEJ", "S&P/ASX 200 Energy Index"),
	XFJ("XFJ", "S&P/ASX 200 Financial Index"),
	XXJ("XXJ", "S&P/ASX 200 Financials excluding A-REITs Index"), 
	XHJ("XHJ", "S&P/ASX 200 Health Care Index"),
	XNJ("XNJ", "S&P/ASX 200 Industrials Index "),
	XIJ("XIJ", "S&P/ASX 200 Information Technology Index"),
	XIC("XIC", "ASX LIC Index"),
	XMJ("XMJ", "S&P/ASX 200 Materials Index"),
	XMM("XMM", "S&P/ASX 300 Metals and Mining Index"),
	XJR("XJR", "S&P/ASX 200 Resources"),
	XTJ("XTJ", "S&P/ASX 200 Telecommunications Services Index"),
	XUJ("XUJ", "S&P/ASX 200 Utilities Index");
	
	private String indexSymbol;
	private String indexName;

	IndexEnum(String indexSymbol, String indexName){
		this.indexSymbol = indexSymbol;
		this.indexName = indexName;
	}

	/**
	 * @return the indexSymbol
	 */
	public String getIndexSymbol() {
		return indexSymbol;
	}

	/**
	 * @param indexSymbol the indexSymbol to set
	 */
	public void setIndexSymbol(String indexSymbol) {
		this.indexSymbol = indexSymbol;
	}

	/**
	 * @return the indexName
	 */
	public String getIndexName() {
		return indexName;
	}

	/**
	 * @param indexName the indexName to set
	 */
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	
}
