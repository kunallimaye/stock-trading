/**
 * 
 */
package com.kunal.stock.dm.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kunal.stock.dm.constants.Constants;
import com.kunal.stock.dm.data.DataSearchService;
import com.kunal.stock.dm.model.Company;

/**
 * This is an example of JAX-RS service.
 * <p>
 * This class provides an implementation of RESTful service to read/write companies.
 * @author kunallimaye
 *
 */
@Path("/" + Constants.COMPANY_REST_SERVICE)
@RequestScoped
public class CompanyData {
	@Inject
	Logger log;
	
	@Inject
	DataSearchService searchService;
	
	/**
	 * RESTful service which returns all the companies in an XML format.
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Company> getAllCompanies(){
//		List<Company> companies = new ArrayList<Company>();
		List<Company> companies = searchService.findAllCompaniesOrderedByName();
		
		return companies;
	}
	
	/**
	 * RESTful service which searches the DB for a company provided a company symbol. 
	 * @param companySymbol is a string with format CompanySymbol.ExchangeSymbol
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/{" + Constants.COMPANY_BY_SYMBOL_PREFIX + Constants.COMPANY_BY_SYMBOL_REGEX + "}")
	public Company getCompanyBySymbol(String companySymbol){
		Company company = null;
		company = searchService.findCompanyBySymbol(companySymbol);
		return company;
	}
}
