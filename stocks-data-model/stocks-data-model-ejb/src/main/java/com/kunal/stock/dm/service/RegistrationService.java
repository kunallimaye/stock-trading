/**
 * Stateless session bean to register (create) various records related to this application.
 * @author kunallimaye
 */
package com.kunal.stock.dm.service;

import com.kunal.stock.dm.model.Company;
import com.kunal.stock.dm.model.EarningsPerShare;
import com.kunal.stock.dm.model.Exchange;
import com.kunal.stock.dm.model.Index;
import com.kunal.stock.dm.model.Security;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class RegistrationService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Exchange> exchangeEventSrc;

    @Inject
    private Event<Company> companyEventSrc;
    
    @Inject
    private Event<Security> securityEventSrc;
    
    @Inject
    private Event<EarningsPerShare> epsEventSrc;
    
    @Inject
    private Event<Index> indexEventSrc;

    public void register(Exchange exchange) throws Exception {
        log.info("Registering exchange: " + exchange.getName());
        em.persist(exchange);
        exchangeEventSrc.fire(exchange);
    }

    public void register(Company company) throws Exception {
        log.info("Registering company: " + company.getName());
        em.persist(company);
        companyEventSrc.fire(company);
    }

    public void register(Security security) throws Exception {
        log.info("Registering security: " + security.getName());
        em.persist(security);
        securityEventSrc.fire(security);
    }
    
    public void register(Index index) throws Exception {
        log.info("Registering security: " + index.getName());
        em.persist(index);
        indexEventSrc.fire(index);
    }
    
}
