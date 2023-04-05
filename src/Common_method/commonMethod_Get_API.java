package Common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class commonMethod_Get_API {
	public static int responsestatuscode(String baseuri, String resource)
	{
		RestAssured.baseURI=baseuri;
		int rest_status_code= given().when().get(resource).then().extract().statusCode();
		return rest_status_code;
		
	}
	public static String responsebody_extractor(String baseuri, String resource)
	{
		RestAssured.baseURI=baseuri;
		String res_body=given().when().get(resource).then().extract().response().asString();
		return res_body;
		
	}
	
}
