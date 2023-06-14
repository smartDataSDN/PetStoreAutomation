package api.endpoints;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

//UserEndPoints.java
//Created to perform CRUD(Create, Read, Update and Delete) requests the User API. 
public class UserEndPoints {

	public static Response createUser(User payload)
	{
	
		Response response= given()
		.contentType("application/json")
		.accept("application/json")
		.body(payload)
		
		.when()
		.post(Routes.post_url);
		
		return response;
		
	}
	
	
	public static Response readUser(String username)
	{
	
		Response response= given()
				.pathParam("username", username)
		
		.when()
		.get(Routes.get_url);
		
		return response;
		
	}
	
	
	
	public static Response updateUser(String username,User payload)
	{
	
		Response response= given()
		.contentType("application/json")
		.accept("application/json")
		.pathParam("username", username)
		.body(payload)
		
		.when()
		.put(Routes.update_url);
		
		return response;
		
	}
	
	
	public static Response deleteUser(String username)
	{
	
		Response response= given()
				.pathParam("username", username)
		
		.when()
		.delete(Routes.delete_url);
		
		return response;
		
	}
}
