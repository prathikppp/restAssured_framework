package Test_class;

import java.io.IOException;


import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_method.commonMethod_put_API;
import Common_method.commonMethodUtility;
import io.restassured.path.json.JsonPath;
import Req_repository.Put_req_repository;

public class Put_tc1 {
	@Test
	public static void orchestrator() throws IOException
	{
		String resbody="";
		int res_status_code=0;
		String baseuri =Put_req_repository.baseuri();
		String resource=Put_req_repository.resource();
		String reqbody=Put_req_repository.post_req_tc1();
		
		for(int i=0; i<5; i++)
		{
			res_status_code= commonMethod_put_API.responseoStatusCode_extractor(baseuri, resource, reqbody);
			if(res_status_code == 200)
		    {
				resbody =commonMethod_put_API.responsebody_extractor(baseuri, resource, reqbody);
				resbody_validation(resbody);
				break;
			
		    } 
			else 
			{
				System.out.println("correct status code is not found in iteration " +i);
				
			}
			
	     }
		//utility
		commonMethodUtility.evidencefile("Put_tc1", reqbody, resbody);
		Assert.assertEquals(res_status_code, 200);

		
    }
	
	private static void resbody_validation(String resbody) 
	{
		JsonPath jsp =new JsonPath(resbody);
//		// extract parameters
		String res_name =jsp.getString("name");
		String res_job=jsp.getString("job");
		String res_id = jsp.getString("id");
		String create_date= jsp.getString("updatedAt");
		String acc_date=create_date.substring(0, 10);
		String curr_date = LocalDate.now().toString();
		System.out.println("NAME: " +res_name + " \nJOB : " + res_job + "\nID : " + res_id +
				"\ncreatedAt : " + acc_date);
//		//validate rwsbody paramters 
		Assert.assertEquals(res_name, "Rajan");
		Assert.assertEquals(res_job, "QA");
		Assert.assertNotEquals(res_id, 0);
		Assert.assertEquals(acc_date, curr_date);
		
			
	}
}
	
