/**
 * Describes the data model for Company
 * 
 * Technical
 * ---------
 * A good example of ManyToMany relationship using JoinTable
 * 
 * @author kunallimaye
 */
package com.kunal.stock.dm.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Company")
@Inheritance(strategy = InheritanceType.JOINED)
public class Company extends Security {

	/**
	 * Value generated to remove the warning.
	 */
	private static final long serialVersionUID = -8234836159504448668L;

	@OneToMany(mappedBy = "company", cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Set<EarningsPerShare> eps;

	@ManyToMany(fetch=FetchType.EAGER, targetEntity=Index.class)
	@JoinTable(
			name="CompanyIndicesMap",
			joinColumns=
				@JoinColumn(name="companyId", referencedColumnName="id"),
			inverseJoinColumns=
				@JoinColumn(name="indexId", referencedColumnName="id")
			)
	private Set<Index> sectors;
	
	@NotNull
	private Date updatedOn;

	/**
	 * 
	 */
	public Company() {
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
	 * @param sectors
	 * @param quotes
	 * @param updatedOn
	 */
	public Company(String symbol, String name, Exchange exchange,
			Double maxValueIn52Weeks, Double lowestValueIn52Weeks, 
			Double currentBuyValue, Double currentSellValue, Set<Quote> quotes,  
			Set<Index> sectors,
			Date updatedOn) {
		super(symbol, name, exchange, maxValueIn52Weeks, lowestValueIn52Weeks, 
				currentBuyValue, currentSellValue, quotes);
		this.sectors = sectors;
		this.updatedOn = updatedOn;
		
		return ;
	}

	/**
	 * @return the eps
	 */
	public Set<EarningsPerShare> getEps() {
		return eps;
	}

	/**
	 * @param eps
	 *            the eps to set
	 */
	public void setEps(Set<EarningsPerShare> eps) {
		this.eps = eps;
	}

	/**
	 * @return the sectors
	 */
	public Set<Index> getSectors() {
		return sectors;
	}

	/**
	 * @param sectors the sectors to set
	 */
	public void setSectors(Set<Index> sectors) {
		this.sectors = sectors;
	}

	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn
	 *            the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		StringBuffer output = new StringBuffer();
		output.append("\n\tCompany[");
		output.append(" id: " + this.getId());
		output.append(" name: " + this.getName());
		output.append(" symbol: " + this.getSymbol());
		output.append(" sectors:{ ");
		if (this.getSectors() != null) {
			for (Index sector : this.getSectors()) {
				output.append(sector.toString());
			}
		}
		output.append(" }");
		output.append(" EPS:{ ");
		if (this.getEps() != null) {
			for (EarningsPerShare eps : this.getEps()) {
				output.append(eps.toString());
			}
		}
		output.append(" }");
		output.append(" ]");
		return output.toString();
	}
}
