/**
 * 
 */
package com.kunal.stock.dm.model;

/**
 * @author kunallimaye
 *
 */
public class StockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2001593722642889485L;

	@Override
	public String toString(){
		return super.getMessage();
	}
}
