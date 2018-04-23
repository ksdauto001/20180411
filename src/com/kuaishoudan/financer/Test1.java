package com.kuaishoudan.financer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.util.AppSPUtil;
import com.kuaishoudan.financer.util.AppUtil;
import com.kuaishoudan.financer.util.WebSPUtil;
import com.kuaishoudan.financer.util.WebUtil;

public class Test1 {

	public AppiumDriver<AndroidElement> driver=null;
	String devicename="";
	public  WebDriver webdriver=null;
	@BeforeTest
	public void setUp() throws Exception {
		driver =   AppUtil.getdriver();
	
		Process process=Runtime.getRuntime().exec("adb devices");
		process.waitFor();
		InputStreamReader isr=new InputStreamReader(process.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		br.readLine();
		devicename=br.readLine().replaceAll("device", "").trim();
		System.out.println(devicename);
		Thread.sleep(3000);
		webdriver=WebUtil.getdriver();
	}
	@AfterTest
	public void tearDown() throws Exception {	
		driver.quit();
		webdriver.quit();
	}
	//创建用户
	@Test(invocationCount =1, threadPoolSize = 1)
	public void test1() throws InterruptedException, IOException{
		
		System.out.println("***1@");
	boolean flag=	AppUtil.createUser(driver, devicename, 1);
		Assert.assertEquals(flag, true);
	}
	//个人进件
	@Test(invocationCount =1, threadPoolSize = 1)
	public void test2() throws InterruptedException, IOException{
			
			System.out.println("***2@");
		String str=	AppUtil.addGr(driver, devicename, 1);
			Assert.assertEquals(str, "待分配");
	}
	//企业进件
	//@Test(invocationCount =1, threadPoolSize = 1)
	public void test3() throws InterruptedException, IOException{
				
			System.out.println("***3@");
			String str=		AppUtil.addQy(driver, devicename, 1);
			Assert.assertEquals(str, "待分配");
	}
	//web审批待分配
	@Test(invocationCount =1, threadPoolSize = 1)
	public void test4() throws InterruptedException, IOException{
					
				System.out.println("***4@");
				WebUtil.login(webdriver, "liuhl@jizhicar.com");//登录
				WebUtil.testDFP(webdriver);//待分配
				WebUtil.logout(webdriver );//登出
	}
	//web审批已分配
	@Test(invocationCount =1, threadPoolSize = 1)
	public void test5() throws InterruptedException, IOException{
						
					System.out.println("***5@");
					WebUtil.login(webdriver, "liuhl@jizhicar.com");//登录
					WebUtil.testYFP(webdriver);//已分配
					WebUtil.logout(webdriver );//登出
	}
	//web审批已录入
	@Test(invocationCount =1, threadPoolSize = 1)
	public void test6() throws InterruptedException, IOException{
							
						System.out.println("***5@");
						WebUtil.login(webdriver, "liuhl@jizhicar.com");//登录
						WebUtil.testYLR(webdriver);//已分配
						WebUtil.logout(webdriver );//登出
	}
	//app申请合同
	@Test(invocationCount =1, threadPoolSize = 1)
	public void test7() throws InterruptedException, IOException{
								
		String str=	AppSPUtil.testSQHT(driver);
		Assert.assertEquals(str, "已申请合同");
	}
	//web审批合同
	@Test(invocationCount =1, threadPoolSize = 1)
	public void test8(){
			WebUtil.login(webdriver, "liuhl@jizhicar.com");//登录
			WebUtil.testYSQHT(webdriver);//申请合同审批
			WebUtil.logout(webdriver );//登出
			
	}
	//app申请请款
	@Test(invocationCount =1, threadPoolSize = 1)
	public void test9(){
		String str=AppSPUtil.testHTSQQK(driver);//请款
		Assert.assertEquals(str, "已请款");
	}
	//web请款审批同意专员1
	@Test
	public void test11(){
			
		try {				
			String	spname = AppSPUtil.getSPname(driver);//从app获取审批人名字				
			String[]  strs=spname.split(",");				 
			String itename=strs[1];				
			String email=WebSPUtil.nameToemail(strs[0]);				
		boolean flag=	WebSPUtil.testSP1(webdriver, email, itename);	//请款审批同意专员	
		Assert.assertEquals(flag, true);
		} catch (InterruptedException e) {				
			// TODO Auto-generated catch block					
			e.printStackTrace();				
		} catch (IOException e) {		
			// TODO Auto-generated catch block					
			e.printStackTrace();				
		}

	}
	@Test
	public void test12(){
		boolean flag=false;
		try {
			String	spname = AppSPUtil.getSPname(driver);//从app获取审批人名字			
			System.out.println("###"+spname);
			if(spname.indexOf(",")==-1){
				//bd操作
				String email=WebSPUtil.nameToemail(spname);
				flag=AppSPUtil.loginBD(driver,email);
				AppUtil.login(driver,devicename, "liuhl@jizhicar.com");//登录
				
			}else{
			String[]  strs=spname.split(",");
			 String itename=strs[1];
			String email=WebSPUtil.nameToemail(strs[0]);
		flag=	WebSPUtil.testSP2(webdriver, email, itename);	//请款审批同意专员
			
			}
			Assert.assertEquals(flag, true);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	//---
	@Test
	public void test13(){

		try {
			boolean flag=false;
			System.out.println(AppUtil.getStatue(driver));//状态获取
			String	spname = AppSPUtil.getSPname(driver);//从app获取审批人名字			
			if(spname.indexOf(",")==-1){
				//bd操作
				String email=WebSPUtil.nameToemail(spname);
				flag=AppSPUtil.loginBD(driver,email);
				AppUtil.login(driver,devicename, "liuhl@jizhicar.com");//登录	
			}else{
			String[]  strs=spname.split(",");
			 String itename=strs[1];
			String email=WebSPUtil.nameToemail(strs[0]);
			flag=WebSPUtil.testSP3(webdriver, email, itename);	//请款审批同意专员
			
			}
			Assert.assertEquals(flag, true);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Test
	public void test14(){
		try {
		
			String	spname = AppSPUtil.getSPname(driver);//从app获取审批人名字			
			System.out.println("###"+spname);
			String[]  strs=spname.split(",");
			 String itename=strs[1];
			String email=WebSPUtil.nameToemail(strs[0]);
			WebSPUtil.testSP4(webdriver, email, itename);	//请款审批同意专员	
			String str=AppSPUtil.getActstatue(driver);
			Assert.assertEquals(str, "已放款");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
