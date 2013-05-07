/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kunal.stock.dm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import com.kunal.stock.dm.data.DataSearchService;
import com.kunal.stock.dm.model.Company;
import com.kunal.stock.dm.model.EarningsPerShare;
import com.kunal.stock.dm.model.Exchange;
import com.kunal.stock.dm.model.Index;
import com.kunal.stock.dm.service.RegistrationService;

import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CompanyRegistrationTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return TestDeploymentArchive.createTestArchive("company-registration-test.war");
    }

    @Inject
    RegistrationService registrationService;

    @Inject
    DataSearchService searchService;
    
    @Inject
    Logger log;
    
    private Exchange createExchange(int suffix) throws Exception{
    	Exchange exchange = new Exchange();
        exchange.setName("Company Registration Test Exchange" + suffix);
        exchange.setSymbol("CRTE" + suffix);
        
        registrationService.register(exchange);
    	return exchange;
    }

    private Index createIndex(Exchange exchange, int suffix) throws Exception{
    	// 1. Register an exchange
    	Index index = new Index();
        index.setName("Index Registration Test Index" + suffix);
        index.setSymbol("IRT" + suffix);
        index.setExchange(exchange);
        // randomly generate max value
        index.setMaxValueIn52Weeks(new Double(new Random().nextDouble()));
        // Ensure that low value is smaller than max value
        index.setLowestValueIn52Weeks(new Double(new Random(index.getMaxValueIn52Weeks().longValue() - 1).nextDouble()));
        index.setUpdatedOn(new Date());
        
        // 2. Register an Index
        registrationService.register(index);
        
        return index;
    }

    private Company createCompany(int suffix) throws Exception{
    	// 1. Register an exchange
    	Exchange exchange = this.createExchange(suffix);
    	Index index = this.createIndex(exchange, suffix);
    	
        Company company = new Company();
        company.setName("Company Registration Test Company Name" + suffix);
        company.setSymbol("CRTCN" + suffix);
        company.setExchange(exchange);
        // randomly generate max value
        company.setMaxValueIn52Weeks(new Double(new Random().nextDouble()));
        // Ensure that low value is smaller than max value
        company.setLowestValueIn52Weeks(new Double(new Random(company.getMaxValueIn52Weeks().longValue() - 1).nextDouble()));
        company.setUpdatedOn(new Date());
        
        Set<EarningsPerShare> epsList = new HashSet<EarningsPerShare>();
        EarningsPerShare eps1 = new EarningsPerShare();
        eps1.setCompany(company);
        eps1.setYear("2012");
        eps1.setEpsValue(new Double(new Random().nextDouble()));
        EarningsPerShare eps2 = new EarningsPerShare();
        eps2.setCompany(company);
        eps2.setYear("2013");
        eps2.setEpsValue(new Double(new Random().nextDouble()));
        epsList.add(eps1);
        epsList.add(eps2);
        company.setEps(epsList);
        
        Set<Index> sectors = new HashSet<Index>();
        sectors.add(index);
        company.setSectors(sectors);
        
        // 2. Register the company
        registrationService.register(company);
        
        return company;
    }

    @Test
    public void testCompanyRegistration() throws Exception {
    	int counter = 1;
        Company company = createCompany(counter);
        log.info(company.toString());
        
        assertNotNull(company.getId());
        log.info(company.getName() + " was persisted with id " + company.getId());
    }

    @Test
    public void testCompanySearchById() throws Exception{
        int counter = 2;
        Company newCompany = createCompany(counter);
    	log.info("Searching for company with id: " + newCompany.getId());
        Company searchCompany = searchService.findCompanyById(newCompany.getId());
        assertEquals(searchCompany.getId(), newCompany.getId());
    }
    
    @Test
    public void testCompanySearchBySymbol() throws Exception{
        int counter = 3;
        Company newCompany = createCompany(counter);
    	log.info("Searching for company with symbol: " + newCompany.getSymbol() + " and Exchange: " + newCompany.getExchange().getSymbol());
        Company searchCompany = searchService.findCompanyBySymbol(newCompany.getSymbol(), newCompany.getExchange().getSymbol());
        
        assertEquals(searchCompany.getId(), newCompany.getId());
    }

    @Test
    public void testfindAllCompaniesOrderedByName() throws Exception{
        int counter = 4;
        createCompany(counter);
    	log.info("Searching for all the companys ordered by name");
        List<Company> indices = searchService.findAllCompaniesOrderedByName();
        for(Company i : indices){
        	log.info(i.toString());
        }
        assertTrue(indices.size() > 0);
    }
}
