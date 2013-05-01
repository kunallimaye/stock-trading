/**
 * Describes the data model for a Security Exchange.
 * 
 * @author kunallimaye
 */
 package com.kunal.stock.dm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "Exchange", 
	uniqueConstraints = @UniqueConstraint(columnNames = "symbol"))
public class Exchange implements Serializable {

    /**
	 * Value generated to remove the warning. 
	 */
	private static final long serialVersionUID = -8234836159504448668L;

	/**
	 * Unique ID for the Exchange
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long 	id;

	/**
	 * Symbol for the exchange
	 */
	@NotNull
	@NotEmpty
	private String	symbol;
	
	/**
	 * Name of the exchange
	 */
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

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

	@Override
	public String toString(){
		StringBuffer output = new StringBuffer();
		output.append("\n\tExchange[");
		output.append(" id:" + this.getId());
		output.append(" symbol:" + this.getSymbol());
		output.append(" name:'" + this.getName() + "'");
		output.append(" ]");
		
		return output.toString();
	}
}
