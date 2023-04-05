package Common_method;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;


public class getData {
    public static ArrayList<String> getdataExcel(String testSheetName, String testCaseName) throws IOException 
    {
    	ArrayList<String> arraydata =new ArrayList<String>();
        //step 1 :- to locate the file by creating object of file input stream
        FileInputStream fis = new FileInputStream("C:\\Users\\admin\\Documents\\test_data.xlsx");

        //create the object of XSSFWorkbook to open the excel file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //step 3 : access the desired sheet 
        //step 3.1 : fetch the count of sheet available in excel file
        int countofsheet = workbook.getNumberOfSheets();

        // step 3.2 : fetch the name of sheet and compare against desired sheet name
        for(int i=0; i<countofsheet; i++)
        {
            Sheet sheet = workbook.getSheetAt(i);
            String sheetname = sheet.getSheetName();
            if(sheetname.equalsIgnoreCase(testSheetName))
            {
               /*step 4:access the sheet and iterate through rows to
               fetch the column in which test case column is mentioned*/
                Iterator<Row> rows = sheet.iterator();
                Row firstrow = rows.next();
                // step 4.2 : create iterator for cells
                Iterator<Cell> Cells = firstrow.cellIterator();
                int j=0;
                int tc_column=0;

                //step 4.3 : read the cell values of row number 1 to compare against the testcase name
                while(Cells.hasNext())
                {
                    Cell cellvalue = Cells.next();
                    if (cellvalue.getStringCellValue().equalsIgnoreCase("tc_case"))
                    {
                        tc_column =j;
                        //System.out.println(tc_column);
                    }
                    j++;
                }

                //step 5 : fetch the data for designated test-case
                while(rows.hasNext())
                {
                	
                    Row datarow = rows.next(); 
                    if (datarow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName))
                    {
                    	 
                        Iterator<Cell> cellvaluedata = datarow.cellIterator();
                        while(cellvaluedata.hasNext())
                        {
                        	Cell dataofcell = cellvaluedata.next(); 
                        	//method 1 : try and catch method
//                        	try 
//                        	{
//                        		String testdata = dataofcell.getStringCellValue();
//                        		arraydata.add(testdata);
//                        	}
//                        	catch (IllegalStateException e)
//                        	{
//                        		int inttestData = (int) dataofcell.getNumericCellValue();
//                        		String stringtestData= Integer.toString(inttestData);
//                        		arraydata.add(stringtestData);
//                        	}
                        	
                        	//method 2 :- using cell and celltype with If-else statement
//                            if(dataofcell.getCellType() == CellType.STRING)
//                            {
//                                String testdata = dataofcell.getStringCellValue();
//                                System.out.println(testdata);
//                                arraydata.add(testdata);                                
//                            }
//                            else if(dataofcell.getCellType() == CellType.NUMERIC)
//                            {
//                                int testdata = (int) dataofcell.getNumericCellValue();
//                                String stringtestData= Integer.toString(testdata);
//                                                              
//                            }
                        	
                        	 //Method 3 -- Extract the data by converting it into String
                        	
//                        	 String testData =cellvaluedata.next().toString().replaceAll("\\.\\d+$", "");//                        	
//                        	 arraydata.add(testData); 
                        	
                             //Method 4-- Extract the data by using Dataformatter
                        	
                        	 DataFormatter format =new DataFormatter();
                        	 String testData=format.formatCellValue(dataofcell);
                        	 arraydata.add(testData);
                        	 System.out.println(testData);
                        }
                    }
                }
            }
        }
        workbook.close();
        return arraydata;
    }
}