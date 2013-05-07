/**
 * Describes the data model for Company
 * 
 * @author kunallimaye
 */
package com.kunal.stock.dm.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Index")
@Inheritance(strategy = InheritanceType.JOINED)
public class Index extends Security {

	/**
	 * Value generated to remove the warning.
	 */
	private static final long serialVersionUID = -5149485312362259939L;

	@NotNull
	private Date updatedOn;

	/**
	 * 
	 */
	public Index() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param symbol
	 * @param name
	 * @param exchange
	 * @param maxValueIn52Weeks
	 * @param lowestValueIn52Weeks
	 * @param currentBuyValue
	 * @param currentSellValue
	 * @param quotes
	 */
	public Index(String symbol, String name, Exchange exchange,
			Double maxValueIn52Weeks, Double lowestValueIn52Weeks, 
			Double currentBuyValue, Double currentSellValue,
			Set<Quote> quotes, 
			Date updatedOn) {
		super(symbol, name, exchange, maxValueIn52Weeks, lowestValueIn52Weeks, 
				currentBuyValue, currentSellValue, quotes);
		this.updatedOn = updatedOn;
		
		return ;
	}

	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString(){
		StringBuffer output = new StringBuffer();
		output.append("\n\tIndex[");
		output.append(" id:" + this.getId());
		output.append(" symbol:" + this.getSymbol());
		output.append(" name:'" + this.getName() + "'");
		output.append(" ]");
		
		return output.toString();
	}

}
