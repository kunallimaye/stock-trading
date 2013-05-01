/**
 * Describes the data model for Earnings of a share.
 * 
 * @author kunallimaye
 */
 package com.kunal.stock.dm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "EPS")
public class EarningsPerShare implements Serializable {

    /**
	 * Value generated to remove the warning. 
	 */
	private static final long serialVersionUID = 1693727559912230314L;

	/**
	 * Unique ID for the EPS
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long 	id;

	/**
	 * Company that the EPS belongs to
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "eps")
	private Company company;
	
	/**
	 * Year of the EPS
	 */
    @NotNull
    @Size(min = 4, max = 4, message="Year value must be 4 digits long")
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Year value must by between 1900 to 2999. Regex value: ^(19|20)\\d{2}$")
    private String year;

    @NotNull
    private Double epsValue;
    
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the epsValue
	 */
	public Double getEpsValue() {
		return epsValue;
	}

	/**
	 * @param epsValue the epsValue to set
	 */
	public void setEpsValue(Double epsValue) {
		this.epsValue = epsValue;
	}
	
	@Override
	public String toString(){
		StringBuffer output = new StringBuffer();
		output.append(" eps<");
		output.append(" id:" + this.getId());
		output.append(" year:" + this.getYear());
		output.append(" epsValue:'" + this.getEpsValue() + "'");
		output.append(" >");
		
		return output.toString();
	}

}
