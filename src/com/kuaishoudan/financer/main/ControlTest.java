package com.kuaishoudan.financer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kuaishoudan.financer.util.AppSPUtil;
import com.kuaishoudan.financer.util.AppUtil;
import com.kuaishoudan.financer.util.WebSPUtil;
import com.kuaishoudan.financer.util.WebUtil;
import com.kuaishoudan.financer.web.LoginWeb;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class ControlTest {
	public AppiumDriver<AndroidElement> driver;
	String devicename="";
	public  WebDriver webdriver;
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ControlTest ct=	new 	ControlTest();
		
		System.out.println("***@");
		ct.setUp();//准备数据
		ct.setUp2();
	//  ct.loginWeb("liuhl@jizhicar.com");//登录
	//	ct.dfp();//待分配
	//	ct.webDksp();//已录入到申请合同
	//	ct.appSqht();
	//	ct.webSpht();
	//	ct.appSqqk();
		
	//	ct.sp1();
	//	ct.sp2();
	//	ct.sp3();
	//	ct.sp4();
		ct.sp6();
		ct.tearDown();
	}
	public void setUp() throws IOException, InterruptedException{
		driver =   AppUtil.getdriver();
		
		Process process=Runtime.getRuntime().exec("adb devices");
		process.waitFor();
		InputStreamReader isr=new InputStreamReader(process.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		br.readLine();
		devicename=br.readLine().replaceAll("device", "").trim();
		System.out.println(devicename);
		Thread.sleep(3000);
	}
	//web
	public void setUp2() throws IOException, InterruptedException{
		webdriver=WebUtil.getdriver();
		
	}
	public void loginWeb(String username){
	//	String username="liuhl@jizhicar.com";

		WebUtil.login(webdriver, username);//登录
	}
	/**
	 * 创建用户，进件，待审批
	 */
	public  void  dfp(){
		AppUtil.addTest(driver, devicename,1);
	}
	/**
	 * web
	 */
	public void webDksp(){
	
	
		//	WebUtil.testDFP(webdriver);//待分配
		//	WebUtil.testYFP(webdriver);//已分配
		//	WebUtil.testYLR(webdriver);//已录入
	
		//AppUtil.addTest(driver, devicename,1);
	}
	//App申请合同
	public void appSqht(){
		//
		AppSPUtil.testSQHT(driver);
		
	}
	//web审批合同
	public void webSpht(){
		//
		WebUtil.testYSQHT(webdriver);//申请合同审批
		
	}
	//申请请款
	public void appSqqk(){
		AppSPUtil.testHTSQQK(driver);
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/toolbar_back")).click();//返回按钮
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/toolbar_loan_status")).click();//流程。。。
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/text_customer_look_status")).click();//查看进度
		
	}
	
	//请款审批同意专员
	public void sp1(){


			
			try {
			String	spname = WebSPUtil.getSPname(driver);//从app获取审批人名字			
			System.out.println("###"+spname);
			String[]  strs=spname.split(",");
			 String itename=strs[1];
			String email=WebSPUtil.nameToemail(strs[0]);
			WebSPUtil.testSP1(webdriver, email, itename);	//请款审批同意专员
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	//
	public void sp2(){
		try {
			String	spname = WebSPUtil.getSPname(driver);//从app获取审批人名字			
			System.out.println("###"+spname);
			String[]  strs=spname.split(",");
			 String itename=strs[1];
			String email=WebSPUtil.nameToemail(strs[0]);
			WebSPUtil.testSP2(webdriver, email, itename);	//请款审批同意专员
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void sp3(){
		try {
			String	spname = WebSPUtil.getSPname(driver);//从app获取审批人名字			
			System.out.println("###"+spname);
			String[]  strs=spname.split(",");
			 String itename=strs[1];
			String email=WebSPUtil.nameToemail(strs[0]);
			WebSPUtil.testSP3(webdriver, email, itename);	//请款审批同意专员
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void sp4(){
		try {
			String	spname = WebSPUtil.getSPname(driver);//从app获取审批人名字			
			System.out.println("###"+spname);
			String[]  strs=spname.split(",");
			 String itename=strs[1];
			String email=WebSPUtil.nameToemail(strs[0]);
			WebSPUtil.testSP4(webdriver, email, itename);	//请款审批同意专员
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void sp6(){
		try {
			String	spname = WebSPUtil.getSPname(driver);//从app获取审批人名字			
			System.out.println("###"+spname);
			String[]  strs=spname.split(",");
			 String itename=strs[1];
			String email=WebSPUtil.nameToemail(strs[0]);
			WebSPUtil.testSP5(webdriver, email, itename);	//请款审批同意专员
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void tearDown() throws Exception {
		
		driver.quit();
		webdriver.quit();
	}
}
