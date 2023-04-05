package Req_repository;

import java.io.IOException;
import java.util.ArrayList;

import Common_method.getData;

public class post_request_repository { 
	 
	public static String baseuri()
	
	{
		String baseuri="https://reqres.in/";
		return baseuri;
	}
	public static String resource()
	{
		String resource="api/users";
		return resource;
	}
	public static String post_request_tc1() throws IOException 
	{		
		ArrayList<String> data = getData.getdataExcel("post_data", "tc1");	
		String Name = data.get(2);
		String Job = data.get(3); 
		String reqBody="{\r\n"
				+ "    \"name\": \""+Name+"\",\r\n"
				+ "    \"job\": \""+Job+"\"\r\n"
				+ "}";
		return reqBody;
	}
//    public static String post_request_tc2() 
//    {
//				String reqBody="{\r\n"
//				+ "    \"name\": \"prathik\",\r\n"
//				+ "    \"job\": \"qa\"\r\n"
//				+ "}";
//				return reqBody;
//    }
    
}