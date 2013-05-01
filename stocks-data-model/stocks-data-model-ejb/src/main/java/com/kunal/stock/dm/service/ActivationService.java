/**
 * 
 */
package com.kunal.stock.dm.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.kunal.stock.dm.data.DataSearchService;
import com.kunal.stock.dm.model.Company;
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

		return exchange;
	}

	private Index setupIndex(Index index) throws Exception {
		if (searchService.findIndexBySymbol(index.getSymbol()) == null) {
			registrationService.register(index);
		} else {
			log.info("Index already exists. Not registering index with symbol: "
					+ index.getSymbol());
		}
		return index;
	}

	private Map<String, Index> setupIndices(Exchange asxExchange) throws Exception {

		Map<String, Index> indices = new HashMap<String, Index>();
		
		for (IndexEnum indexEnum : IndexEnum.values()){
			Index index = new Index(indexEnum.getIndexSymbol(), indexEnum.getIndexName(),
					asxExchange, null, null, new Date());
			indices.put(IndexEnum.XGD.getIndexSymbol(), this.setupIndex(index));
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

		return company;
	}

	private List<Company> setupCompanies(Exchange exchange) {
		List<Company> companies = new ArrayList<Company>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// TODO: populate companies from the universe
		// Company anz = new Company(
		// symbol, name,
		// exchange,
		// maxValueIn52Weeks,
		// lowestValueIn52Weeks,
		// dateFormat.parse("30/04/2013"));
		// this.setupCompany(anz);
		return companies;
	}

	@PostActivate
	public void populateDB() throws Exception {
		Exchange asxExchange = this.setupExchange();
		this.setupIndices(asxExchange);
		// this.setupCompanies(exchange);

		return;
	}
}
