package Req_repository;

import java.io.IOException;
import java.util.ArrayList;

import Common_method.getData;

public class Put_req_repository {
	
	public static String baseuri()
	{
		String baseuri="https://reqres.in/";
		return baseuri;
	}
	public static String resource()
	{
		String resource="api/users/2";
		return resource;
	}
	public static String post_req_tc1( ) throws IOException 
	{
		ArrayList<String> data = getData.getdataExcel("put_data", "tc3");	
		String Name = data.get(2);
		String Job = data.get(3); 
		String reqBody="{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		return reqBody;
	}
	
}
