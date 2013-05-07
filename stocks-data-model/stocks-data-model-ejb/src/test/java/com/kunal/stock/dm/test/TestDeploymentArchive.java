/**
 * 
 */
package com.kunal.stock.dm.test;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.kunal.stock.dm.data.DataSearchService;
import com.kunal.stock.dm.model.Company;
import com.kunal.stock.dm.model.CompanyEnum;
import com.kunal.stock.dm.model.Company_;
import com.kunal.stock.dm.model.EarningsPerShare;
import com.kunal.stock.dm.model.EarningsPerShare_;
import com.kunal.stock.dm.model.Exchange;
import com.kunal.stock.dm.model.ExchangeEnum;
import com.kunal.stock.dm.model.Exchange_;
import com.kunal.stock.dm.model.Index;
import com.kunal.stock.dm.model.IndexEnum;
import com.kunal.stock.dm.model.Index_;
import com.kunal.stock.dm.model.Quote;
import com.kunal.stock.dm.model.Security;
import com.kunal.stock.dm.model.Security_;
import com.kunal.stock.dm.service.ActivationService;
import com.kunal.stock.dm.service.RegistrationService;
import com.kunal.stock.dm.util.Resources;

/**
 * @author kunallimaye
 *
 */
public class TestDeploymentArchive {

	public static Archive<?> createTestArchive(String deploymentUnitName) {
        return ShrinkWrap.create(WebArchive.class, deploymentUnitName)
                .addClasses(
                		ActivationService.class, 
                		Company.class,
                		Company_.class,
                		CompanyEnum.class,
                		EarningsPerShare.class,
                		EarningsPerShare_.class,
                		Exchange.class,
                		Exchange_.class,
                		ExchangeEnum.class, 
                		Index.class,
                		Index_.class,
                		IndexEnum.class,
                		Quote.class, 
                		Security.class,
                		Security_.class,
                		RegistrationService.class, 
                		Resources.class,
                		DataSearchService.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml", "test-ds.xml");
    }

}
