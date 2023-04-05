package Test_class;
import java.io.IOException;
import java.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common_method.commonMethodUtility;
import Common_method.common_method_Post_API;
import Req_repository.post_request_repository;
import io.restassured.path.json.JsonPath;

public class post_tc1
{ 
	    @Test
		public static void orchestrator() throws IOException
		{
			String resbody="";
			int res_status_code=0;
			String baseuri=post_request_repository.baseuri();
			String resource =post_request_repository.resource();
			String reqbody=post_request_repository.post_request_tc1();
			
		for(int i=0; i<5; i++)
		{
							
		    res_status_code =common_method_Post_API.responseStatusCode_extracter(baseuri,resource, reqbody);
		    if(res_status_code == 201)
		    { 
		    	resbody =common_method_Post_API.responsebody_extracter(baseuri, resource, reqbody);
		    	responseBodyValidator(resbody);
		    	break;
		    	  
		    }
		    else
		    {
		    	//System.out.println("correct status code is not found in the iteration " + i);
		    	
		    }
	    }
		
		commonMethodUtility.evidencefile("Post_tc1", reqbody, resbody);
		Assert.assertEquals(res_status_code, 201);
		   						
	}

		private static void responseBodyValidator(String resbody) 
		{
			// TODO Auto-generated method stub
			// create jsonPath object to extract responsebody parameters
			JsonPath jsp = new JsonPath(resbody);

			// extract responsebody parameters
			String res_name = jsp.getString("name");
			String res_job = jsp.getString("job");
			String res_id = jsp.getString("id");
			String res_createdAt = jsp.getString("createdAt");
			
			//System.out.println("name : " + res_name + "\njob : " + res_job + "\nid : " + res_id + "\ncreatedAt : " + res_createdAt);
			// validate responsebody parameter
			Assert.assertEquals(res_name, "morpheus");
			Assert.assertEquals(res_job, "leader");
			Assert.assertNotNull(res_id, "assertion error , id parameter is null");

			// extract date from createdAt parameter
			String actual_date = res_createdAt.substring(0, 10);
			// Create a date object
			String current_date = LocalDate.now().toString(); 
			//Assert.assertEquals(actual_date, current_date);
			//System.out.println("Actual DATE : " + actual_date + "\nCURRENT DATE : " + current_date);
	
	  }
}