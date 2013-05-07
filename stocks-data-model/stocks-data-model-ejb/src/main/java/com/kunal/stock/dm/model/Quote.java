/**
 * Describes the data model for the Quote for a security.
 * 
 * @author kunallimaye
 */
 package com.kunal.stock.dm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "Quote")
public class Quote implements Serializable {

    /**
	 * Value generated to remove the warning. 
	 */
	private static final long serialVersionUID = -9194064416570928562L;

	/**
	 * Unique ID for the Quote
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long 	id;

	/**
	 * Company that the EPS belongs to
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "quotes")
	private Security security;
	
	private Date	timestamp;
	private Double	openingValue;
	private Double	closingValue;
	private Double	highValue;
	private Double	lowValue;
	private Double	volumeTraded;
	private Double	adjustedToValueOnClose;
	
	/**
	 * Default constructor
	 */
	public Quote() {
	}

	/**
	 * @param security
	 * @param timestamp
	 * @param openingValue
	 * @param closingValue
	 * @param highValue
	 * @param lowValue
	 * @param volumeTraded
	 * @param adjustedToValueOnClose
	 */
	public Quote(Security security, Date timestamp, Double openingValue,
			Double closingValue, Double highValue, Double lowValue,
			Double volumeTraded, Double adjustedToValueOnClose) {
		this.security = security;
		this.timestamp = timestamp;
		this.openingValue = openingValue;
		this.closingValue = closingValue;
		this.highValue = highValue;
		this.lowValue = lowValue;
		this.volumeTraded = volumeTraded;
		this.adjustedToValueOnClose = adjustedToValueOnClose;
	}

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
	 * @return the security
	 */
	public Security getSecurity() {
		return security;
	}


	/**
	 * @param security the security to set
	 */
	public void setSecurity(Security security) {
		this.security = security;
	}


	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}


	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	/**
	 * @return the openingValue
	 */
	public Double getOpeningValue() {
		return openingValue;
	}


	/**
	 * @param openingValue the openingValue to set
	 */
	public void setOpeningValue(Double openingValue) {
		this.openingValue = openingValue;
	}


	/**
	 * @return the closingValue
	 */
	public Double getClosingValue() {
		return closingValue;
	}


	/**
	 * @param closingValue the closingValue to set
	 */
	public void setClosingValue(Double closingValue) {
		this.closingValue = closingValue;
	}


	/**
	 * @return the highValue
	 */
	public Double getHighValue() {
		return highValue;
	}


	/**
	 * @param highValue the highValue to set
	 */
	public void setHighValue(Double highValue) {
		this.highValue = highValue;
	}


	/**
	 * @return the lowValue
	 */
	public Double getLowValue() {
		return lowValue;
	}


	/**
	 * @param lowValue the lowValue to set
	 */
	public void setLowValue(Double lowValue) {
		this.lowValue = lowValue;
	}


	/**
	 * @return the volumeTraded
	 */
	public Double getVolumeTraded() {
		return volumeTraded;
	}


	/**
	 * @param volumeTraded the volumeTraded to set
	 */
	public void setVolumeTraded(Double volumeTraded) {
		this.volumeTraded = volumeTraded;
	}


	/**
	 * @return the adjustedToValueOnClose
	 */
	public Double getAdjustedToValueOnClose() {
		return adjustedToValueOnClose;
	}


	/**
	 * @param adjustedToValueOnClose the adjustedToValueOnClose to set
	 */
	public void setAdjustedToValueOnClose(Double adjustedToValueOnClose) {
		this.adjustedToValueOnClose = adjustedToValueOnClose;
	}


	@Override
	public String toString(){
		StringBuffer output = new StringBuffer();
		output.append(" quote<");
		output.append(" id:" + this.getId());
		output.append(" timestamp:" + this.getTimestamp());
		output.append(" open:" + this.getOpeningValue());
		output.append(" close:" + this.getClosingValue());
		output.append(" high value:" + this.getHighValue());
		output.append(" low value:" + this.getLowValue());
		output.append(" volume:" + this.getVolumeTraded());
		output.append(" adjusted on close to:'" + this.getAdjustedToValueOnClose() + "'");
		output.append(" >");
		
		return output.toString();
	}

}
