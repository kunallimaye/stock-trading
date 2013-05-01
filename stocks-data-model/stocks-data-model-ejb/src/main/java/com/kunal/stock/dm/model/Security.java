/**
 * Describes the data model for Security. This is the parent class for all types of securities. E.g. companies and indices.
 * 
 * Technical
 * ---------
 * 
 * With the join table strategy there is a table per class in the hierarchy, but the subclass tables only have the extra attribute they define in their subclass.
 * 
 * @author kunallimaye
 */
package com.kunal.stock.dm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "Security", uniqueConstraints = @UniqueConstraint(columnNames = "symbol"))
@Inheritance(strategy = InheritanceType.JOINED)
public class Security implements Serializable {

	/**
	 * Value generated to remove the warning.
	 */
	private static final long serialVersionUID = -8234836159504448668L;

	/**
	 * Unique ID for the security
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Symbol for the security
	 */
	@NotNull
	@NotEmpty
	private String symbol;

	/**
	 * Descriptive Name for the security
	 */
	@NotNull
	@Size(min = 1, max = 50)
	private String name;

	/**
	 * Exchange that the security belongs to
	 */
	@NotNull
	private Exchange exchange;

	/**
	 * Maximum value in the last 52 weeks for the security
	 */
	private Double maxValueIn52Weeks;
	
	/**
	 * Lowest value in the last 52 weeks for the security
	 */
	private Double lowestValueIn52Weeks;

	/**
	 * 
	 */
	public Security() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param symbol
	 * @param name
	 * @param exchange
	 * @param maxValueIn52Weeks
	 * @param lowestValueIn52Weeks
	 */
	public Security(String symbol, String name, Exchange exchange,
			Double maxValueIn52Weeks, Double lowestValueIn52Weeks) {
		this.symbol = symbol;
		this.name = name;
		this.exchange = exchange;
		this.maxValueIn52Weeks = maxValueIn52Weeks;
		this.lowestValueIn52Weeks = lowestValueIn52Weeks;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the exchange
	 */
	public Exchange getExchange() {
		return exchange;
	}

	/**
	 * @param exchange
	 *            the exchange to set
	 */
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	/**
	 * @return the maxValueIn52Weeks
	 */
	public Double getMaxValueIn52Weeks() {
		return maxValueIn52Weeks;
	}

	/**
	 * @param maxValueIn52Weeks the maxValueIn52Weeks to set
	 */
	public void setMaxValueIn52Weeks(Double maxValueIn52Weeks) {
		this.maxValueIn52Weeks = maxValueIn52Weeks;
	}

	/**
	 * @return the lowestValueIn52Weeks
	 */
	public Double getLowestValueIn52Weeks() {
		return lowestValueIn52Weeks;
	}

	/**
	 * @param lowestValueIn52Weeks the lowestValueIn52Weeks to set
	 */
	public void setLowestValueIn52Weeks(Double lowestValueIn52Weeks) {
		this.lowestValueIn52Weeks = lowestValueIn52Weeks;
	}

}
