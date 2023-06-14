package api.endpoints;

import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

public class UserEndPoints2 {

	// UserEndPoints.java
	// Created to perform CRUD(Create, Read, Update and Delete) requests the User
	// API.

	
	//method created for getting URL's from properties file
	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // Load properties file
		return routes;

	}

	public static Response createUser(User payload) {
		
		String post_url=getURL().getString("post_url");

		Response response = given().contentType("application/json").accept("application/json").body(payload)

				.when().post(post_url);

		return response;

	}

	public static Response readUser(String username) {
		
		String get_url=getURL().getString("get_url");

		Response response = given().pathParam("username", username)

				.when().get(get_url);

		return response;

	}

	public static Response updateUser(String username, User payload) {
		
		String update_url=getURL().getString("update_url");

		Response response = given().contentType("application/json").accept("application/json")
				.pathParam("username", username).body(payload)

				.when().put(update_url);

		return response;

	}

	public static Response deleteUser(String username) {
		
		String delete_url=getURL().getString("delete_url");

		Response response = given().pathParam("username", username)

				.when().delete(delete_url);

		return response;

	}
}
