package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {

	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
 	public void testPostUser(String userId, String userName, String fName, String lName, String userEmail, String pwd, String phone)
	{
	
		
		User userPayload =new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(userEmail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response= UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String username)
	{
	
		
		Response response= UserEndPoints.deleteUser(username);;
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
