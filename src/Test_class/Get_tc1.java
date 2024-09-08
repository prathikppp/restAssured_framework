package Test_class;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import Common_method.commonMethodUtility;
import Common_method.commonMethod_Get_API;
import Req_repository.Get_req_repository;
import io.restassured.path.json.JsonPath;

public class Get_tc1 {
	@Test
	public static void orchestrator() throws IOException
	{
		String resbody="";
		int res_status_code=0;
		String baseuri=Get_req_repository.baseuri();
		String reource=Get_req_repository.resource();
		
		// changes
		System.out.println("add 1");
		System.out.println("add 2");
		
		
		// changes to branch 
		System.out.println("b1 test");
		System.out.println("conflicts3  test");

		for(int i=0; i<5; i++)
		{
			res_status_code =commonMethod_Get_API.responsestatuscode(baseuri, reource);
			if(res_status_code==200)
			{
				resbody=commonMethod_Get_API.responsebody_extractor(baseuri, reource);
				responseBodyValidator(resbody);
				break;
			}
			else
			{
				System.out.println("correct status code not found in iteration " +i);
				
			}
		}
		//evidence file
		commonMethodUtility.evidencefile("Get_tc1",null, resbody);
		Assert.assertEquals(res_status_code, 200);
	}
	
	private static void responseBodyValidator(String resbody)
	{
		JsonPath jsp=new JsonPath(resbody);
		
		int array_length = jsp.getInt("data.size()");
		System.out.println("the daat array length is "+array_length);
		int exp_id[] = {7,8,9,10,11,12};
	    String exp_email[] = {"michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in",
	    		               "george.edwards@reqres.in","rachel.howell@reqres.in"};
	    String exp_fname[] = {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
	    String exp_lname[] = {"Lawson","Ferguson","Funke","Fields","Edwards","Howell"};
	    String exp_avatar[] = {"https://reqres.in/img/faces/7-image.jpg","https://reqres.in/img/faces/8-image.jpg",
	    					"https://reqres.in/img/faces/9-image.jpg",
	    						"https://reqres.in/img/faces/10-image.jpg","https://reqres.in/img/faces/11-image.jpg",
	    							"https://reqres.in/img/faces/12-image.jpg"};
	    for(int i = 0; i<array_length; i++)
	    {
	    	int res_id =jsp.getInt("data["+i+"].id");
	    	String res_fname=jsp.getString("data["+i+"].first_name");
	    	String res_lname= jsp.getString("data["+i+"].last_name");
			String res_email= jsp.getString("data["+i+"].email");
			String res_avatar= jsp.getString("data["+i+"].avatar");
			System.out.println(res_id); 
			System.out.println(res_email);
	        System.out.println(res_fname);
	        System.out.println(res_lname);
	        System.out.println(res_avatar);
	        //validation
	        Assert.assertEquals(res_id,exp_id[i]);
			Assert.assertEquals(res_email,exp_email[i]);
			Assert.assertEquals(res_fname,exp_fname[i]);
			Assert.assertEquals(res_lname,exp_lname[i]);
			Assert.assertEquals(res_avatar,exp_avatar[i]); 
	    }
	}
}
