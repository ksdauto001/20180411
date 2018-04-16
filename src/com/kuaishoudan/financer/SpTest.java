package com.kuaishoudan.financer;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.util.Util2;
import com.kuaishoudan.financer.web.TestSP;

public class SpTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public AppiumDriver<AndroidElement> driver=null;
	String devicename="";
	@BeforeTest
	public void setUp() throws Exception {
		driver =   Util2.getdriver();
	
		Process process=Runtime.getRuntime().exec("adb devices");
		process.waitFor();
		InputStreamReader isr=new InputStreamReader(process.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		br.readLine();
		devicename=br.readLine().replaceAll("device", "").trim();
		System.out.println(devicename);
		Thread.sleep(4000);
	}
	@Test(invocationCount =1, threadPoolSize = 1)
	public void test1() throws InterruptedException, IOException{

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		System.out.println(driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_name")).size());
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_name"))
		.get(1).click();// 首页列表
		
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_product")).get(3).click();//列表
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/toolbar_loan_status")).click();//状态审批流程
		String email="";
		List<AndroidElement> statueitems=	driver.findElements(By.id("com.kuaishoudan.financer.test:id/ll_status")) ;
		for(int i=0;i<statueitems.size();i++){
		String statue=statueitems.get(i).findElement(By.id("com.kuaishoudan.financer.test:id/item_status")).getText();
		System.out.println("@@"+statueitems.size()+statue);
		if("正在处理".equals(statue)){
			String name=statueitems.get(i).findElement(By.id("com.kuaishoudan.financer.test:id/item_name")).getText();
			System.out.println(name);
			email=TestSP.HZtoPY(name);
			System.out.println("email:"+email);
			break;
		}
	}

			
		
			
	}
	public  void  testSPname(){
	System.out.println(	driver.findElements(By.id("com.kuaishoudan.financer.test:id/item_name")).size());//.getText();
	}

}
