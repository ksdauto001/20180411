package com.kuaishoudan.financer.util;



import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;



import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Alignment;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import com.kuaishoudan.financer.bean.*;

public class CaseUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//	String spath="C:\\12a.xls";

/*		KSDCase ksd=getCaseByid("TI-1-001");
			System.out.println("========"+ksd.getCaseid()+"@@"+ksd.getRemark()+ksd.getPhone());
*/
		
		/*List<KSDCase> testcase=getCases(1);
		
		System.out.println(testcase.size());
		for(int i=0;i<testcase.size();i++) {
			KSDCase ksd=testcase.get(i);
			System.out.println(ksd.getCarprice()+"==="+ksd.getCaseid()+ksd.getUsername()+ksd.getCartype()+ksd.getBusinessname()+ksd.getPhone() );
		}*/
		/*DecimalFormat df = new DecimalFormat("#.00");
		for(int i=0;i<100;i++){
			//double x=2+(double)(Math.random()*2.0);
		

			double sqdk=Double.parseDouble(df.format(2+Math.random() * 997));
			double cljg=Double.parseDouble(df.format(2+Math.random() * 997));
			System.out.println(cljg+"="+sqdk);
			if(cljg<=sqdk){
				System.out.println(sqdk);break;
			}else{
				System.out.println("**"+i);
			}
			
		}*/
	/*	for(int i=0;i<20;i++){
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			System.out.println(uuid);
			Calendar calendar = Calendar.getInstance();
			System.out.println("现在："+calendar.getTime().getTime()+ (int)(Math.random()*89+10));	
		}*/
	
		/* for (int i = 0; i < 9; i++) {//9代表循环九次，产生九个随机号码
			 
	            String number = "1";//定义电话号码以139开头
	            Random random = new Random();//定义random，产生随机数
	            for (int j = 0; j < 10; j++) {
	                //生成0~9 随机数
	                number += random.nextInt(9);
	            }
	            System.out.println(number);//输出一个电话号码
	        }*/
		for(int q=0;q<10;q++)
		System.out.println(RandomValue.getChineseName());
		
		
	}

	
	/**
	 * 读取Excel具体内容
	 */
	 public static KSDCase  getCaseByid(String caseid){
			String path=System.getProperty("user.dir")+"/testcase.xls";
	   
			KSDCase ksd=new KSDCase();
	    	int i;
	        Sheet sheet;
	        Workbook book;
	        Cell cell1,cell2,cell3;
	        try { 
	            //t.xls为要读取的excel文件名
	            book= Workbook.getWorkbook(new File(path)); 
	             
	            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
	            sheet=book.getSheet(0); 
	            //获取左上角的单元格
	          
	            	i=0;
	            while(true)
	            {
	                //获取每一行的单元格 
	                cell1=sheet.getCell(0,i);//（列，行）
	
	                
	                if("".equals(cell1.getContents())==true)    //如果读取的数据为空
	                    break;
	        
               
	                if(cell1.getContents().equals(caseid)){
	        
	                	  ksd.setCaseid(sheet.getCell(0,i).getContents());
	                	  ksd.setCasedesc(sheet.getCell(1,i).getContents());
	                	  ksd.setUsername(sheet.getCell(2,i).getContents());
	                	  ksd.setPhone(sheet.getCell(3,i).getContents());
	                	  ksd.setIdentitytype(sheet.getCell(4,i).getContents());
	                	  ksd.setIdentitynum(sheet.getCell(5,i).getContents());
	                	  ksd.setCartype(sheet.getCell(6,i).getContents());
	                	  ksd.setCarbrand(sheet.getCell(7,i).getContents());
	                	  ksd.setCarprice(sheet.getCell(8,i).getContents());
	                	  ksd.setSqdk(sheet.getCell(9,i).getContents());
	                	  ksd.setHkqs(sheet.getCell(10,i).getContents());
	                	  ksd.setJrcp(sheet.getCell(11,i).getContents());
	                	  ksd.setSssh(sheet.getCell(12,i).getContents());//  
	                	  ksd.setBusinessname(sheet.getCell(13,i).getContents());//
	                	  ksd.setBusinessid(sheet.getCell(14,i).getContents());//
	                  	  ksd.setRemark(sheet.getCell(15,i).getContents());//
	                  	  ksd.setInit_statue(sheet.getCell(16,i).getContents());
	                   	  ksd.setLoginname (sheet.getCell(17,i).getContents());
	                   	  ksd.setPwd(sheet.getCell(18,i).getContents());
	                	  ksd.setGrhqy(sheet.getCell(19,i).getContents());
	                }
	      
	                i++;
	            }
	            book.close(); 
	        }
	        catch(Exception e)  { }
	        return ksd;
	    }
	 
	 
	 /**
		 * 读取Excel具体内容
		 */
		 public static List<KSDCase>  getCases(int n){
				String path=System.getProperty("user.dir")+"/testcase.xls";
			 List<KSDCase> cells1=new ArrayList<KSDCase>();
			
		    
			 int i;
		        Sheet sheet;
		        Workbook book;
		        Cell cell1,cell2,cell3;
		        try { 
		            //t.xls为要读取的excel文件名
		            book= Workbook.getWorkbook(new File(path)); 
		             
		            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
		            sheet=book.getSheet(0); 
		      

		            	i=0;
		            while(true)
		            {
		                //获取每一行的单元格 
		                cell1=sheet.getCell(0,i);//（列，行）
		
		                
		                if("".equals(cell1.getContents())==true)    //如果读取的数据为空
		                    break;
		            String a=    sheet.getCell(16,i).getContents().trim();

		          //      sheet.getCell(1,i).getContents().trim().equals("状态")
		         //       System.out.println("@"+i+sheet.getCell(1,i).getContents());
		                if(  a.equals("状态"+n)){
		                	
		               //     System.out.println("#"+sheet.getCell(14,i).getContents().length());
		        			KSDCase ksd=new KSDCase();
		        			 ksd.setCaseid(sheet.getCell(0,i).getContents());
		                	  ksd.setCasedesc(sheet.getCell(1,i).getContents());
		                	  ksd.setUsername(sheet.getCell(2,i).getContents());
		                	  ksd.setPhone(sheet.getCell(3,i).getContents());
		                	  ksd.setIdentitytype(sheet.getCell(4,i).getContents());
		                	  ksd.setIdentitynum(sheet.getCell(5,i).getContents());
		                	  ksd.setCartype(sheet.getCell(6,i).getContents());
		                	  ksd.setCarbrand(sheet.getCell(7,i).getContents());
		                	  ksd.setCarprice(sheet.getCell(8,i).getContents());
		                	  ksd.setSqdk(sheet.getCell(9,i).getContents());
		                	  ksd.setHkqs(sheet.getCell(10,i).getContents());
		                	  ksd.setJrcp(sheet.getCell(11,i).getContents());
		                	  ksd.setSssh(sheet.getCell(12,i).getContents());//  
		                	  ksd.setBusinessname(sheet.getCell(13,i).getContents());//
		                	  ksd.setBusinessid(sheet.getCell(14,i).getContents());//
		                  	  ksd.setRemark(sheet.getCell(15,i).getContents());//
		                  	  ksd.setInit_statue(sheet.getCell(16,i).getContents());
		                   	  ksd.setLoginname (sheet.getCell(17,i).getContents());
		                   	  ksd.setPwd(sheet.getCell(18,i).getContents());
		                	  ksd.setGrhqy(sheet.getCell(19,i).getContents());
		                    cells1.add(ksd);
		                }
		                i++;
		     
		            }
		            book.close(); 
		        }
		        catch(Exception e)  { }
		        return cells1;
		    }
		
		
		
		
			
			
		
}
