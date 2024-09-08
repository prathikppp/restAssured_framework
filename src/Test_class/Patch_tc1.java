package Test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import Common_method.commonMethodUtility;
import Common_method.commonMethod_Patch_API;
import Req_repository.Patch_req_repository;
import io.restassured.path.json.JsonPath;

public class Patch_tc1 {
	@Test
	public static void orchestrator() throws IOException
	{
		System.out.println("test1 conflicts");
		String resbody="";
		int res_status_code=0;
		String baseuri=Patch_req_repository.baseuri();
		String resource=Patch_req_repository.resource();
		String reqbody=Patch_req_repository.patch_req_tc1();
		for(int i=0; i<5; i++)
		{
			res_status_code= commonMethod_Patch_API.responsestatuscode_extractor(baseuri, resource, reqbody);
			if(res_status_code ==200)
			{
				resbody= commonMethod_Patch_API.resbody_extractor(baseuri, resource, reqbody);
				responseBodyValidator(resbody);
				break;
			}
			else
			{
				System.out.println("correct status code not found in iteration " +i);
			}
		}
		//evidencefile
		commonMethodUtility.evidencefile("patch_tc1", reqbody, resbody);
		Assert.assertEquals(res_status_code, 200);
				
	}
		
	 	private static void responseBodyValidator(String resbody)
	 	{
	 		//create jsonpath object to extract responsebody paramters
	 		JsonPath jsp = new JsonPath(resbody);
	 		
	 		//extract resbody parameters
	 		String res_name=jsp.getString("name");
	 		String res_job =jsp.getString("job");
	 		String res_id = jsp.getString("id");
	 		String acc_date=jsp.getString("updatedAt").substring(0,10);
	 		String curr_date= LocalDate.now().toString();
	 		
			System.out.println( "job : " + res_job +  "\nupdatedAt : " + acc_date);
			//validate resbody parameters
			Assert.assertEquals(res_name,"Amar");
			Assert.assertEquals(acc_date, curr_date);
			Assert.assertEquals(res_job, "TL");
						
	 	}

}
