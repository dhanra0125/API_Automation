package API_AutomationFirst.API_AutomationFirst;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OrderBooking 

{
	static String VelsoURL ="https://velso.thyrocare.cloud";
	static String Velso1URL ="https://velso1.thyrocare.cloud";
	static String APIKEY;


	@SuppressWarnings("unchecked")
	@Test
	void GetAccessToken()
	{
		//Specify The Base URL
		RestAssured.baseURI=VelsoURL+"/api/Login/Login";
		RequestSpecification Getrequest = RestAssured.given();		
		JSONObject RequestBody = new JSONObject();	

		RequestBody.put("UserName","9004844180");
		RequestBody.put("Password","123456");
		RequestBody.put("userType","DSA");
		RequestBody.put("portalType","DSA");

		Getrequest.header("Content-Type","application/json");

		Getrequest.body(RequestBody.toJSONString());

		Response response = Getrequest.request(Method.POST);

		APIKEY= response.jsonPath().get("apiKey");

		System.out.println(APIKEY);
		System.out.println("Here");
	}

	@SuppressWarnings("unchecked")
	@Test
	void ProductList()
	{
		//Specify The Base URL
		RestAssured.baseURI=Velso1URL+"/api/ProductsMaster/WellnessProducts";
		//Create Request Object
		RequestSpecification Getrequest = RestAssured.given();		
		JSONObject RequestBody = new JSONObject();	

		RequestBody.put("apiKey",APIKEY);
		RequestBody.put("productType","ALL");

		Getrequest.header("Content-Type","application/json");

		Getrequest.body(RequestBody.toJSONString());

		Response response = Getrequest.request(Method.POST);

		System.out.println(response);




	}


	@SuppressWarnings("unchecked")
	@Test
	void ServiceabilityCheck()
	{
		//Specify The Base URL
		RestAssured.baseURI=Velso1URL+"/api/Login/Login";
		//Create Request Object
		RequestSpecification Getrequest = RestAssured.given();		
		JSONObject RequestBody = new JSONObject();	

		RequestBody.put("apiKey",APIKEY);
		RequestBody.put("pincode","444444");
		RequestBody.put("Tests","FBS,PPBS");


		Getrequest.header("Content-Type","application/json");

		Getrequest.body(RequestBody.toJSONString());

		Response response = Getrequest.request(Method.POST);

		String ServiceCapacity= response.jsonPath().get("lastMonthsOrders");

		System.out.println(ServiceCapacity);
	}





}

