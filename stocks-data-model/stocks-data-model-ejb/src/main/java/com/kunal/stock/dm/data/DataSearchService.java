/**
 * This is an ApplicationScopped bean, which provides a helper services for searching through the data stored within
 * the application. It allows searching through Exchange, Security, Index, Company, etc.
 * 
 * Technical:
 * This is a good example of (JPA 2.0) specification based type-safe search using criteria query.
 * @author kunallimaye
 */
package com.kunal.stock.dm.data;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.kunal.stock.dm.model.Company;
import com.kunal.stock.dm.model.Company_;
import com.kunal.stock.dm.model.Exchange;
import com.kunal.stock.dm.model.ExchangeEnum;
import com.kunal.stock.dm.model.Exchange_;
import com.kunal.stock.dm.model.Index;
import com.kunal.stock.dm.model.Index_;
import com.kunal.stock.dm.model.Security;
import com.kunal.stock.dm.model.Security_;

@ApplicationScoped
public class DataSearchService {

	@Inject
	private EntityManager em;
	
	@Inject
	Logger log;

	/**
	 * Allows searching through all the exchanges by the exchange ID
	 * 
	 * @param exchangeId
	 * @return
	 */
	public Exchange findExchangeById(Long exchangeId) {
		return em.find(Exchange.class, exchangeId);
	}

	/**
	 * A helper query to return NULL if no result is returned.
	 * 
	 * @param typedQuery
	 * @return
	 */
	private <E>E getSingleResult(TypedQuery<E> typedQuery){
		if (typedQuery != null && typedQuery.getResultList() != null
				&& typedQuery.getResultList().size() > 0) {
			return typedQuery.getSingleResult();
		} else {
			return null;
		}
	}
	
	/**
	 * Allows searching through all the exchanges given the exchange symbol
	 * 
	 * @param exchangeSymbol
	 * @return
	 */
	public Exchange findExchangeBySymbol(String exchangeSymbol) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Exchange> criteria = cb.createQuery(Exchange.class);
		Root<Exchange> exchange = criteria.from(Exchange.class);
		criteria.select(exchange);
		criteria.where(cb.equal(exchange.get(Exchange_.symbol), exchangeSymbol));
		TypedQuery<Exchange> exchangeQuery = em.createQuery(criteria);
//		if (exchangeQuery != null && exchangeQuery.getResultList() != null
//				&& exchangeQuery.getResultList().size() > 0) {
//			return exchangeQuery.getSingleResult();
//		} else {
//			return null;
//		}
		
		return this.getSingleResult(exchangeQuery);
	}

	/**
	 * Returns a list of all the exchanges ordered by name
	 * 
	 * @return
	 */
	public List<Exchange> findAllExchangesOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Exchange> criteria = cb.createQuery(Exchange.class);
		Root<Exchange> exchange = criteria.from(Exchange.class);
		criteria.select(exchange).orderBy(cb.asc(exchange.get(Exchange_.name)));
		return em.createQuery(criteria).getResultList();
	}

	/**
	 * Allows searching through all the indices by the index ID
	 * 
	 * @param indexId
	 * @return
	 */
	public Index findIndexById(Long indexId) {
		return em.find(Index.class, indexId);
	}

	/**
	 * Allows searching through all the indices given the index symbol
	 * 
	 * @param indexSymbol
	 * @return
	 */
	public Index findIndexBySymbol(String indexSymbol) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Index> criteria = cb.createQuery(Index.class);
		Root<Index> index = criteria.from(Index.class);
		criteria.select(index);
		criteria.where(cb.equal(index.get(Index_.symbol), indexSymbol));
		TypedQuery<Index> indexQuery = em.createQuery(criteria);

		return this.getSingleResult(indexQuery);
	}

	/**
	 * Returns a list of all the indices ordered by name
	 * 
	 * @return
	 */
	public List<Index> findAllIndicesOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Index> criteria = cb.createQuery(Index.class);
		Root<Index> index = criteria.from(Index.class);
		criteria.select(index).orderBy(cb.asc(index.get(Index_.name)));
		return em.createQuery(criteria).getResultList();
	}

	/**
	 * Allows searching through all the companies by the company ID
	 * 
	 * @param companyId
	 * @return
	 */
	public Company findCompanyById(Long companyId) {
		return em.find(Company.class, companyId);
	}

	/**
	 * Allows searching through all the companies given the company symbol.
	 * Only one exchange can be supplied.
	 * @param companySymbol
	 * @return
	 */
	public Company findCompanyBySymbol(String companySymbol, String... exchangeSymbols) {
		assert exchangeSymbols.length <= 1;
		String exchangeSymbol = exchangeSymbols.length>0 ? exchangeSymbols[0] : new String(ExchangeEnum.ASX.getSymbol());
		Exchange exchange = this.findExchangeBySymbol(exchangeSymbol);
		if(exchange == null){
			log.info("Exchange with symbol[" + exchangeSymbol + "] not found in the DB");
		}
		log.info("Searching for company[symbol: "+ companySymbol +"] with exchange[symbol: " + exchange.getSymbol() + "]");
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = cb.createQuery(Company.class);
		Root<Company> company = criteria.from(Company.class);
		criteria.select(company);
		criteria.where(
					cb.and(
							cb.equal(company.get(Company_.symbol), companySymbol),
							cb.equal(company.get(Company_.exchange), exchange)
					)
				);

		return this.getSingleResult(em.createQuery(criteria));
		
	}

	/**
	 * Returns a list of all the companies ordered by name
	 * 
	 * @return
	 */
	public List<Company> findAllCompaniesOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = cb.createQuery(Company.class);
		Root<Company> company = criteria.from(Company.class);
		criteria.select(company).orderBy(cb.asc(company.get(Company_.name)));
		
		return em.createQuery(criteria).getResultList();
	}

	/**
	 * Allows searching through all the securities by the security ID
	 * 
	 * @param securityId
	 * @return
	 */
	public Security findSecurityById(Long securityId) {
		return em.find(Security.class, securityId);
	}

	/**
	 * Allows searching through all the securities given the security symbol
	 * 
	 * @param securitySymbol
	 * @return
	 */
	public Security findSecurityBySymbol(String securitySymbol) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Security> criteria = cb.createQuery(Security.class);
		Root<Security> security = criteria.from(Security.class);
		criteria.select(security);
		criteria.where(cb.equal(security.get(Security_.symbol), securitySymbol));
//		return em.createQuery(criteria).getSingleResult();
		return this.getSingleResult(em.createQuery(criteria));
	}

	/**
	 * Returns a list of all the securities ordered by name
	 * 
	 * @return
	 */
	public List<Security> findAllSecuritysOrderedByName() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Security> criteria = cb.createQuery(Security.class);
		Root<Security> security = criteria.from(Security.class);
		criteria.select(security).orderBy(cb.asc(security.get(Security_.name)));
		
		return em.createQuery(criteria).getResultList();
	}
}
