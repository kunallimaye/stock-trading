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
import com.kunal.stock.dm.model.Exchange;
import com.kunal.stock.dm.model.Index;
import com.kunal.stock.dm.service.ActivationService;
import com.kunal.stock.dm.service.RegistrationService;

import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class IndexRegistrationTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return TestDeploymentArchive.createTestArchive("index-registration-test.war");
    }

    @Inject
    RegistrationService registrationService;

    @Inject
    DataSearchService searchService;
    
    @Inject
    ActivationService activationService;
    
    @Inject
    Logger log;
    
    private Exchange createExchange(int suffix) throws Exception{
    	Exchange exchange = new Exchange();
        exchange.setName("Index Registration Test Exchange" + suffix);
        exchange.setSymbol("IRTE" + suffix);
        
        registrationService.register(exchange);
    	return exchange;
    }

    private Index createIndex(int suffix) throws Exception{
    	// 1. Register an exchange
    	Exchange exchange = this.createExchange(suffix);
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
    @Test
    public void testSecurityRegistration() throws Exception {
    	int counter = 1;
        Index index = this.createIndex(counter);
        
        assertNotNull(index.getId());
        log.info(index.getName() + " was persisted with id " + index.getId());
    }

    @Test
    public void testIndexSearchById() throws Exception{
        int counter = 2;
        Index newIndex = createIndex(counter);
    	log.info("Searching for index with id: " + newIndex.getId());
        Index searchIndex = searchService.findIndexById(newIndex.getId());
        assertEquals(searchIndex.getId(), newIndex.getId());
    }
    
    @Test
    public void testIndexSearchBySymbol() throws Exception{
        int counter = 3;
        Index newIndex = createIndex(counter);
    	log.info("Searching for index with symbol: " + newIndex.getSymbol());
        Index searchIndex = searchService.findIndexBySymbol(newIndex.getSymbol());
        assertEquals(searchIndex.getId(), newIndex.getId());
    }

    @Test
    public void testfindAllIndicesOrderedByName() throws Exception{
        int counter = 4;

        createIndex(counter);
    	log.info("Searching for all the indices ordered by name");
        List<Index> indices = searchService.findAllIndicesOrderedByName();
        for(Index i : indices){
        	log.info(i.toString());
        }
        assertTrue(indices.size() > 0);
    }
}
