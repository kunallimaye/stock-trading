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
import com.kunal.stock.dm.model.Security;

/**
 * This is an example of JAX-RS service.
 * <p>
 * This class provides an implementation of RESTful service to read/write companies.
 * @author kunallimaye
 *
 */
@Path("/" + Constants.SECURITY_REST_SERVICE)
@RequestScoped
public class SecurityData {
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
	public List<Security> getAllSecurities(){
//		List<Company> companies = new ArrayList<Company>();
		List<Security> securities = null;
		securities = searchService.findAllSecuritysOrderedByName();
		
		return securities;
	}
	
}
