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
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import com.kunal.stock.dm.data.DataSearchService;
import com.kunal.stock.dm.model.Company;
import com.kunal.stock.dm.model.Exchange;
import com.kunal.stock.dm.model.Index;
import com.kunal.stock.dm.service.ActivationService;
import com.kunal.stock.dm.service.RegistrationService;

import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ActivationServiceTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return TestDeploymentArchive.createTestArchive("activation-service-test.war");
    }

    @Inject
    DataSearchService searchService;
    
    @Inject
    ActivationService activationService;
    
    @Inject
    Logger log;
    
    @Test
    public void testPopulateDB() throws Exception{
    	//TODO: fix this test case
        activationService.populateDB();
        // Check Exchanges
    	log.info("Following Indices are available in the DB: ");
        List<Exchange> exchanges = searchService.findAllExchangesOrderedByName();
        for(Exchange e : exchanges){
        	log.info(e.toString());
        }
        assertTrue(exchanges.size() > 0);
        
        // Check Indices
    	log.info("Following Indices are available in the DB: ");
        List<Index> indices = searchService.findAllIndicesOrderedByName();
        for(Index i : indices){
        	log.info(i.toString());
        }
        assertTrue(indices.size() > 0);

        // Check Companies
    	log.info("Following Companies are available in the DB: ");
        List<Company> companies = searchService.findAllCompaniesOrderedByName();
        for(Company c : companies){
        	log.info(c.toString());
        }
        assertTrue(companies.size() > 0);
    }
}
