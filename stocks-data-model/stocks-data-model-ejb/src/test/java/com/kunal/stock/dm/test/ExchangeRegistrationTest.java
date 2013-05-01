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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import com.kunal.stock.dm.data.DataSearchService;
import com.kunal.stock.dm.model.Exchange;
import com.kunal.stock.dm.service.RegistrationService;

import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ExchangeRegistrationTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return TestDeploymentArchive.createTestArchive("exchange-registration-test.war");
    }

    @Inject
    RegistrationService exchangeRegistration;
    
    @Inject
    DataSearchService searchService;

    @Inject
    Logger log;

    private Exchange createExchange(int suffix) throws Exception{
    	Exchange exchange = new Exchange();
        exchange.setName("Australian Stock Exchange" + suffix);
        exchange.setSymbol("ASX" + suffix);
        
        exchangeRegistration.register(exchange);
    	return exchange;
    }
    
	@Test
    public void testExchangeRegistration() throws Exception {
        int counter = 1;
        Exchange newExchange = createExchange(counter);
        assertNotNull(newExchange.getId());
        log.info(newExchange.getName() + " was persisted with id " + newExchange.getId());
    }

    @Test
    public void testExchangeSearchById() throws Exception{
        int counter = 2;
        Exchange newExchange = createExchange(counter);
    	log.info("Searching for exchange with id: " + newExchange.getId());
        Exchange searchExchange = searchService.findExchangeById(newExchange.getId());
        assertEquals(searchExchange.getId(), newExchange.getId());
    }
    
    @Test
    public void testExchangeSearchBySymbol() throws Exception{
        int counter = 3;
        Exchange newExchange = createExchange(counter);
    	log.info("Searching for exchange with symbol: " + newExchange.getSymbol());
        Exchange searchExchange = searchService.findExchangeBySymbol(newExchange.getSymbol());
        assertEquals(searchExchange.getId(), newExchange.getId());
    }

    @Test
    public void testfindAllExchangesOrderedByName() throws Exception{
        int counter = 4;
        createExchange(counter);
    	log.info("Searching for all the exchanges ordered by name");
        List<Exchange> exchanges = searchService.findAllExchangesOrderedByName();
        for(Exchange e : exchanges){
        	log.info(e.toString());
        }
        assertTrue(exchanges.size() > 0);
    }
}
