package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	Faker faker;
	User userPayload;
	public Logger logger;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// logs
		logger = LogManager.getLogger(this.getClass());

		logger.debug("Debugging............");

	}

	@Test(priority = 1)
	public void testPostuser() {

		logger.info("***************** Creating User *****************");
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);

		logger.info("***************** User is created *****************");
	}

	@Test(priority = 2)
	public void testGetuserByName() {

		logger.info("***************** Reading User Info *****************");
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);

		logger.info("***************** User info is displayed *****************");
	}

	@Test(priority = 3)
	public void testUpdateuserByName() {

		logger.info("***************** Udating User *****************");
		// Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);

		// checking data after update
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername());
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);

		logger.info("***************** User is updated *****************");
	}

	@Test(priority = 4)
	public void testDeleteuserByName() {

		logger.info("***************** Deleting User *****************");
		Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		;
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);

		logger.info("***************** User is Deleted *****************");
	}
}
