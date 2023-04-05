package Common_method;

import io.restassured.RestAssured;

import static  io.restassured.RestAssured.given;

public class commonMethod_Patch_API {
	public static int responsestatuscode_extractor(String baseuri,String resource, String reqbody)
	{
		RestAssured.baseURI=baseuri;
		int res_status_code=given().header("Content-Type","application/json").body(reqbody).when().patch(resource).then().extract()
				.statusCode();
		return res_status_code;
		
	}
	public static String resbody_extractor(String baseuri, String resource, String reqbody)
	{
		RestAssured.baseURI=baseuri;
		String res_body= given().header("Content-Type","application/json").body(reqbody).when().patch(resource).then().extract().response()
				.asString();
		return res_body;
		
	}

}
