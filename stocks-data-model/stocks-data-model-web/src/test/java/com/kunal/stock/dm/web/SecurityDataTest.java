/**
 * 
 */
package com.kunal.stock.dm.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.kunal.stock.dm.constants.Constants;
import com.kunal.stock.dm.service.ActivationService;

/**
 * @author kunallimaye
 *
 */
@RunWith(Arquillian.class)
public class SecurityDataTest {

	@Inject
	Logger log;
	
	@Inject
	ActivationService activationService;
	
    @Deployment
    public static Archive<?> createTestArchive() {
        return TestWebDeploymentArchive.createTestArchive("security-data-test.war");
    }
	@Test
	public void populateData() throws Exception{
		activationService.populateDB();
	}
	
    @RunAsClient
    @Test
    public void testInvokeSecuritiesRS() throws Exception{
    	ClientRequest request = new ClientRequest(
				"http://localhost:8080/security-data-test/rest/" + Constants.SECURITY_REST_SERVICE
//				"http://localhost:8080/company-data-test/rest/members"
    			);
    	request.accept(MediaType.APPLICATION_XML);
//    	request.accept(MediaType.APPLICATION_JSON);
    	ClientResponse<String> response = request.get(String.class);
    	if (response.getStatus() != 200){
    		throw new Exception("HTTP invocation failed with status code: " + response.getStatus());
    	}
    	BufferedReader br = new BufferedReader(new InputStreamReader(
    			new ByteArrayInputStream(response.getEntity().getBytes())));
     
    		String output;
    		System.out.println("Output from Server .... \n");
    		while ((output = br.readLine()) != null) {
    			System.out.println(output);
    		}
    }
}
