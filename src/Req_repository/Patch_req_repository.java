package Req_repository;

import java.io.IOException;
import java.util.ArrayList;

import Common_method.getData;

public class Patch_req_repository {
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
	public static String patch_req_tc1() throws IOException
	{
		ArrayList<String> data = getData.getdataExcel("patch_data", "tc2");	
		String Name = data.get(2);
		String Job = data.get(3); 
		String reqBody="{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		return reqBody;
	}
	/*public static String header_content()
	{
		String content_type="Content-Type\",\"application/json";
		return content_type;
	}*/
}
