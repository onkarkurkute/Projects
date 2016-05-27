package filter;

import org.apache.poi.hssf.usermodel.HSSFCell;// libraries for excel files
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.commons.math.stat.StatUtils;//stats and common math libraries
import org.apache.commons.math.stat.descriptive.rank.Percentile;
import org.apache.commons.math.linear.MatrixUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class PercentileFilter {
	public static void main(String[] args) {
		try 
		{
			FileInputStream fip = new FileInputStream("trainingdata.xls");//read the file
			HSSFWorkbook wkb = new HSSFWorkbook(fp);//crate workbook object of read file
			HSSFSheet wks = wkb.getSheet("trainingdata");//get required spreadsheet form workbook
			
			 int RS = Math.min(0, wks.getFirstRowNum());//get index of first row
			 int RE = Math.max(608, wks.getLastRowNum());//get index of last row
			 
			 int wMap[][];//array to insert dataset values
			    
			 for (int i = RS; i < RE; i++)//till last row
			    {
			       Row R = wks.getRow(i);//fetch rows from spreadsheet
			       int CE = Math.max(R.getLastCellNum(),5921);//get index of last column

			   
			       for (int j = 0; j < CE; j++)//till last column
			       {
			          Cell c = R.getCell(j, Row.RETURN_BLANK_AS_NULL);//get cell contents, missing policy NULL if blank
			          if (c == null) 
			          {
			             // cell is empty or blank
			        	  c=R.setCellValue(0);//if value is NULL set it to zero
			          } 
			          wMap[i][j]=c.getNumericCellValue();//insert value to array..thus we will get all values 
			          //in spreadsheet to array wMap[][] 
			       }
			    }
			 
			 BigMatrix MX = MatrixUtils.createBigMatrix(wMap);//create matrix of dataset array in order to acess each row
			 Percentile P = new Percentile();//Object of Percentile
			 
			 int pMap[];//to store percentile of each row
			   for(int i = 0; i < RE; i++)
			   {
					 double a[]= MX.getRowAsDoubleArray(0);//get row of a matrix wMap
						
				   pMap[i]=P.evaluate(a, 0, RE);//calculate percentile of row with default quantile
			   }

			   
			FileOutputStream fop = new FileOutputStream("trainingdata.xls");//file to be outputed 
			HSSFWorkbook wkb1 = new HSSFWorkbook();//create object of workbook
			HSSFSheet wks1 = wkb1.getSheet("filtereddata");
			
			int RS1 = Math.min(0, wks1.getFirstRowNum());//get index of first row
		    int RE1 = Math.max(608, wks1.getLastRowNum());//get index of last row

			 for (int i = RS1; i < RE1; i++)//till last row
			    {
			       Row R1 = wks1.getRow(i);//fetch rows from spreadsheet
				   int CE1 = Math.max(R1.getLastCellNum(),5921);//get index of last column

			       
			       for (int j = 0; j < CE1; j++)//till last column
			       {
			          Cell c = R1.getCell(j, Row.RETURN_BLANK_AS_NULL);//get cell contents..NULL if blank
			          if (c == null) 
			          {
			             // cell is empty or blank do nothing
			          } 
			          else
			          {
			        	  	int x=c.getNumericCellValue();//get current content of cell
			        	  	
			        	  	x=x/pMap[i];//divide it with its Row's percentile
			        	  	
			        	  	c=R1.setCellValue(x);//set this value to cell
			        	  
			          }
			       }
			    }

			
			wkb1.write(fop);//write values to specified spreadsheet
			fop.flush();
			fip.close();
			fop.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
}