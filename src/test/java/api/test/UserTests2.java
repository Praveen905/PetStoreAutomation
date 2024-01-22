package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpointsProperties;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests2 {
   User userpayload;
	Faker faker;
	public Logger logger;
	@BeforeClass
	public void setUpData() {
		faker = new Faker();
	   userpayload =  new User();
		System.out.println(faker.idNumber().hashCode());
     	userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		logger.info("*******************User Creation**********************");
		Response response =UserEndpointsProperties.createUser(userpayload);
	   response.then().log().all();
	   Assert.assertEquals(response.getStatusCode(), 200);
	   logger.info("*******************User is Created**********************");
	   
	}
	
	@Test(priority = 2)
	public void testReadUser() {
		logger.info("*******************Read User**********************");
		Response response =UserEndpointsProperties.readUser(userpayload.getUsername());
	   response.then().log().all();
	   Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*******************User info obtained**********************");
	}
}
