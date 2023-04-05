package Common_method;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class commonMethodUtility 

{
	public static void evidencefile(String filename, String request , String response) throws IOException
	{
		File newtxtfile = new File("C:\\RestAssuredEvidence\\"+filename+".txt");
		if (newtxtfile.createNewFile())
		{ 			
		   FileWriter dataWriter =new FileWriter(newtxtfile);
		   dataWriter.write("requestbody is :\n" +request+ "\n\n");
		   dataWriter.write("responsebody is :\n" +response);
		   dataWriter.close();
		   System.out.println("request and response is saved in :"+newtxtfile.getName());
		}
		else
		{
			System.out.println(newtxtfile.getName()+ " already exsits take a backup of it");
		}
	}
}