package com.kuaishoudan.financer.util;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



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

		TrainCase testcase=getCaseByid("TI-1-089");
			System.out.println("========"+testcase.getCaseid()+"@@"+testcase.getJpk()+testcase.getHcs());

		
/*		List<TrainCase> testcase=getCases(1);
		
		System.out.println(testcase.size());
		for(int i=0;i<testcase.size();i++) {
			TrainCase tc=testcase.get(i);
			System.out.println("==="+tc.getCaseid()+tc.getTotaltime()+tc.getStartstation()+tc.getEndstation()+tc.getTrainnum()+tc.getCjcr());
		}*/
	}

	
	/**
	 * 读取Excel具体内容
	 */
	 public static TrainCase  getCaseByid(String caseid){
			String path=System.getProperty("user.dir")+"/testcase.xls";
	        TrainCase traincase=new TrainCase();
	
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
	        
	                	  traincase.setCaseid(sheet.getCell(0,i).getContents());
		                    traincase.setCasedesc(sheet.getCell(1,i).getContents());traincase.setPhone(sheet.getCell(2,i).getContents());
		                    traincase.setPwd(sheet.getCell(3,i).getContents());traincase.setCode(sheet.getCell(4,i).getContents());
		                    traincase.setTrainnum(sheet.getCell(5,i).getContents());traincase.setKystatue(sheet.getCell(6,i).getContents());
		                    traincase.setTotaltime(sheet.getCell(7,i).getContents());traincase.setKytime(sheet.getCell(8,i).getContents());
		                    traincase.setStartstation(sheet.getCell(9,i).getContents());traincase.setEndstation(sheet.getCell(10,i).getContents());
		                    traincase.setStarttime(sheet.getCell(11,i).getContents());traincase.setEndtime(sheet.getCell(12,i).getContents());
		                    traincase.setStartdate(sheet.getCell(13,i).getContents());traincase.setEnddate(sheet.getCell(14,i).getContents());
		                    traincase.setHcs(sheet.getCell(15,i).getContents());traincase.setJpk(sheet.getCell(16,i).getContents());
		                    traincase.setCzk(sheet.getCell(19,i).getContents());
		                    traincase.setLcgg(sheet.getCell(20,i).getContents());//列车公告
		                    traincase.setCjcr(sheet.getCell(21,i).getContents());//乘接车人
	                }
	      
	                i++;
	            }
	            book.close(); 
	        }
	        catch(Exception e)  { }
	        return traincase;
	    }
	 
	 
	 /**
		 * 读取Excel具体内容
		 */
		 public static List<TrainCase>  getCases(int n){
				String path=System.getProperty("user.dir")+"/testcase.xls";
			 List<TrainCase> cells1=new ArrayList<TrainCase>();
			
		    
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
		            String a=    sheet.getCell(17,i).getContents().trim();

		          //      sheet.getCell(1,i).getContents().trim().equals("状态")
		         //       System.out.println("@"+i+sheet.getCell(1,i).getContents());
		                if(  a.equals("状态"+n)){
		                	
		               //     System.out.println("#"+sheet.getCell(14,i).getContents().length());
		                    TrainCase traincase=new TrainCase();
		                    traincase.setCaseid(sheet.getCell(0,i).getContents());
		                    traincase.setCasedesc(sheet.getCell(1,i).getContents());traincase.setPhone(sheet.getCell(2,i).getContents());
		                    traincase.setPwd(sheet.getCell(3,i).getContents());traincase.setCode(sheet.getCell(4,i).getContents());
		                    traincase.setTrainnum(sheet.getCell(5,i).getContents());traincase.setKystatue(sheet.getCell(6,i).getContents());
		                    traincase.setTotaltime(sheet.getCell(7,i).getContents());traincase.setKytime(sheet.getCell(8,i).getContents());
		                    traincase.setStartstation(sheet.getCell(9,i).getContents());traincase.setEndstation(sheet.getCell(10,i).getContents());
		                    traincase.setStarttime(sheet.getCell(11,i).getContents());traincase.setEndtime(sheet.getCell(12,i).getContents());
		                    traincase.setStartdate(sheet.getCell(13,i).getContents());traincase.setEnddate(sheet.getCell(14,i).getContents());
		                    traincase.setHcs(sheet.getCell(15,i).getContents());traincase.setJpk(sheet.getCell(16,i).getContents());
		                    traincase.setCzk(sheet.getCell(19,i).getContents());
		                    traincase.setLcgg(sheet.getCell(20,i).getContents());//列车公告
		                    traincase.setCjcr(sheet.getCell(21,i).getContents());//乘接车人
		                    cells1.add(traincase);
		                }
		                i++;
		     
		            }
		            book.close(); 
		        }
		        catch(Exception e)  { }
		        return cells1;
		    }
		 /**
			 * 读取Excel具体内容18列状态（车站管理）
			 */
			 public static List<TrainCase>  getCases_station(){
					String path=System.getProperty("user.dir")+"/testcase.xls";
				 List<TrainCase> cells1=new ArrayList<TrainCase>();
				
			    
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
			            String a=    sheet.getCell(18,i).getContents().trim();

			          //      sheet.getCell(1,i).getContents().trim().equals("状态")
			         //       System.out.println("@"+i+sheet.getCell(1,i).getContents());
			                if(  a.equals("Y")){
			                	
			               //     System.out.println("#"+sheet.getCell(14,i).getContents().length());
			                    TrainCase traincase=new TrainCase();
			                    traincase.setCaseid(sheet.getCell(0,i).getContents());
			                    traincase.setCasedesc(sheet.getCell(1,i).getContents());traincase.setPhone(sheet.getCell(2,i).getContents());
			                    traincase.setPwd(sheet.getCell(3,i).getContents());traincase.setCode(sheet.getCell(4,i).getContents());
			                    traincase.setTrainnum(sheet.getCell(5,i).getContents());traincase.setKystatue(sheet.getCell(6,i).getContents());
			                    traincase.setTotaltime(sheet.getCell(7,i).getContents());traincase.setKytime(sheet.getCell(8,i).getContents());
			                    traincase.setStartstation(sheet.getCell(9,i).getContents());traincase.setEndstation(sheet.getCell(10,i).getContents());
			                    traincase.setStarttime(sheet.getCell(11,i).getContents());traincase.setEndtime(sheet.getCell(12,i).getContents());
			                    traincase.setStartdate(sheet.getCell(13,i).getContents());traincase.setEnddate(sheet.getCell(14,i).getContents());
			                    traincase.setHcs(sheet.getCell(15,i).getContents());traincase.setJpk(sheet.getCell(16,i).getContents());
			                    traincase.setCzk(sheet.getCell(19,i).getContents());
			                    traincase.setLcgg(sheet.getCell(20,i).getContents());//列车公告
			                    cells1.add(traincase);
			                }
			                i++;
			     
			            }
			            book.close(); 
			        }
			        catch(Exception e)  { }
			        return cells1;
			    }
			public static String[] ToExpectTrainByid10(String caseid){
				TrainCase testcase=getCaseByid(caseid);
			String[] expecttrain=new String[10];
				expecttrain[0]=testcase.getTrainnum();
				expecttrain[1]=testcase.getKystatue();
				expecttrain[2]=testcase.getTotaltime();
				
				/*if(testcase.getKytime().contains("分钟"))
					expecttrain[3]="晚点";
				else*/
				expecttrain[3]=testcase.getKytime();
				
				expecttrain[4]=testcase.getStartstation();
				expecttrain[5]=testcase.getEndstation();
				expecttrain[6]=testcase.getStarttime();
				expecttrain[7]=testcase.getEndtime();
				expecttrain[8]=testcase.getStartdate();
				expecttrain[9] = testcase.getStartdate();
				
				System.out.println("expect"+expecttrain[0]+	expecttrain[1]+	expecttrain[2]+expecttrain[3]+expecttrain[4]+expecttrain[5]+expecttrain[6]+expecttrain[7]+expecttrain[8]+expecttrain[9]);
				return expecttrain;
				
			}
			public static String[] ToExpectTrainByid(String caseid) {
				TrainCase testcase = getCaseByid(caseid);
				String[] expecttrain = new String[14];
				expecttrain[0] = testcase.getTrainnum();
				expecttrain[1] = testcase.getKystatue();
				expecttrain[2] = testcase.getTotaltime();
				expecttrain[3] = testcase.getKytime();
				expecttrain[4] = testcase.getStartstation();
				expecttrain[5] = testcase.getEndstation();
				expecttrain[6] = testcase.getStarttime();
				expecttrain[7] = testcase.getEndtime();
				expecttrain[8] = testcase.getStartdate();
				expecttrain[9] = testcase.getEnddate();
				expecttrain[10]=testcase.getHcs();
				expecttrain[11]=testcase.getJpk();
				expecttrain[12]=testcase.getCzk();
				expecttrain[13]=testcase.getLcgg();
				System.out.println("expect"+expecttrain[0]+	expecttrain[1]+	expecttrain[2]+expecttrain[3]+expecttrain[4]+expecttrain[5]+expecttrain[6]+expecttrain[7]+expecttrain[8]+expecttrain[9]+expecttrain[10]+expecttrain[11]+expecttrain[12]+expecttrain[13]);
				
				return expecttrain;

			}
			public static String[] ToExpectTrainBycase(TrainCase testcase){
		
			String[] expecttrain=new String[13];
				expecttrain[0]=testcase.getTrainnum();
				expecttrain[1]=testcase.getKystatue();
				expecttrain[2]=testcase.getTotaltime();
				expecttrain[3]=testcase.getKytime();
				expecttrain[4]=testcase.getStartstation();
				expecttrain[5]=testcase.getEndstation();
				expecttrain[6]=testcase.getStarttime();
				expecttrain[7]=testcase.getEndtime();
				expecttrain[8]=testcase.getStartdate();
				expecttrain[9]=testcase.getEnddate();
				expecttrain[10]=testcase.getHcs();
				expecttrain[11]=testcase.getJpk();
				expecttrain[12]=testcase.getCzk();
				return expecttrain;
				
			}
			
			
		
}
