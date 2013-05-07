/**
 * 
 */
package com.kunal.stock.dm.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.kunal.stock.dm.data.DataSearchService;
import com.kunal.stock.dm.model.Company;
import com.kunal.stock.dm.model.CompanyEnum;
import com.kunal.stock.dm.model.Exchange;
import com.kunal.stock.dm.model.Index;
import com.kunal.stock.dm.model.IndexEnum;

/**
 * This service is used to populate the database with the initial Universe. The
 * population process is started post activation of the session bean.
 * 
 * @author kunallimaye
 * 
 */

@Stateless
public class ActivationService {

	@Inject
	DataSearchService searchService;

	@Inject
	RegistrationService registrationService;

	@Inject
	Logger log;

	private Exchange setupExchange() throws Exception {
		Exchange exchange = new Exchange();
		exchange.setName("Australian Stock Exchange");
		exchange.setSymbol("ASX");
		if (searchService.findExchangeBySymbol(exchange.getSymbol()) == null) {
			registrationService.register(exchange);
		} else {
			log.info("Exchange already exists. Not registering exchange with symbol: "
					+ exchange.getSymbol());
		}

		exchange = searchService.findExchangeBySymbol("ASX");
		return exchange;
	}

	private Index setupIndex(Index index) throws Exception {
		if (searchService.findIndexBySymbol(index.getSymbol()) == null) {
			registrationService.register(index);
		} else {
			log.info("Index already exists. Not registering index with symbol: "
					+ index.getSymbol());
		}
		index = searchService.findIndexBySymbol(index.getSymbol());
		return index;
	}

	private Map<String, Index> setupIndices(Exchange asxExchange) throws Exception {
		log.info("Trying to setup indices");
		Map<String, Index> indices = new HashMap<String, Index>();
		
		for (IndexEnum indexEnum : IndexEnum.values()){
			Index index = new Index(indexEnum.getIndexSymbol(), indexEnum.getIndexName(),
					asxExchange, null, null, null, null, null, new Date());
			indices.put(indexEnum.getIndexSymbol(), this.setupIndex(index));
		}

		return indices;
	}

	private Company setupCompany(Company company) throws Exception {
		if (searchService.findCompanyBySymbol(company.getSymbol()) == null) {
			registrationService.register(company);
		} else {
			log.info("Company already exists. Not registering company with symbol: "
					+ company.getSymbol());
		}
		company = searchService.findCompanyBySymbol(company.getSymbol());
		return company;
	}

	private List<Company> setupCompanies(Exchange asxExchange, Map<String, Index> indices) throws Exception {
		List<Company> companies = new ArrayList<Company>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		log.info("Trying to setup companies");
		for(CompanyEnum companyEnum : CompanyEnum.values()){
			Set<Index> sectors = new HashSet<Index>();
			Iterator<IndexEnum> iterator = companyEnum.getSectors().iterator();
			while( companyEnum.getSectors()!=null && iterator.hasNext() ){
				IndexEnum sector = iterator.next();
				Index index = indices.get(sector.getIndexSymbol());
				sectors.add(index);
			}
			
			Company company = new Company(companyEnum.getCompanySymbol(),
					companyEnum.getCompanyName(), asxExchange,
					null, null,
					null, null,
					null, 
					sectors, 
					dateFormat.parse("1/05/2013"));
			companies.add(this.setupCompany(company));
		}

		return companies;
	}

	@PostActivate
	public void populateDB() throws Exception {
		Exchange asxExchange = this.setupExchange();
		this.setupCompanies(asxExchange, this.setupIndices(asxExchange));

		return;
	}
}
