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

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

import com.kunal.stock.dm.model.Exchange;
import com.kunal.stock.dm.model.Security;
import com.kunal.stock.dm.service.RegistrationService;

import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SecurityRegistrationTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return TestDeploymentArchive.createTestArchive("security-registration-test.war");
    }

    @Inject
    RegistrationService registrationService;

    @Inject
    Logger log;

    @Test
    public void testSecurityRegistration() throws Exception {
    	// 1. Register an exchange
        Exchange newExchange = new Exchange();
        newExchange.setName("Australian Stock Exchange");
        newExchange.setSymbol("ASX");
        registrationService.register(newExchange);
        
        // 2. Register a security
        Security security = new Security();
        security.setName("BHP Billiton");
        security.setSymbol("BHP");
        security.setExchange(newExchange);
        security.setLowestValueIn52Weeks(new Double(30.09));
        security.setMaxValueIn52Weeks(new Double(39.34));
        
        registrationService.register(security);
        
        assertNotNull(security.getId());
        log.info(newExchange.getName() + " was persisted with id " + newExchange.getId());
    }

}
