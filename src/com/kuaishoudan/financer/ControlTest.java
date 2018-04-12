package com.kuaishoudan.financer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.util.CaseUtil;
import com.kuaishoudan.financer.util.IdCardGenerator;
import com.kuaishoudan.financer.util.Util1;
import com.kuaishoudan.financer.util.Util2;
import com.kuaishoudan.financer.web.LoginWeb;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyMetastate;

public class ControlTest {
	
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
		Thread.sleep(2000);
	}

	@AfterTest
	public void tearDown() throws Exception {
		
		driver.quit();
	}
	static int p=1;
	
	@Test(invocationCount =1, threadPoolSize = 1)
	public void test1() throws InterruptedException, IOException{

	
			//Util2.addTest(driver, devicename,p);
	//	Util2.addZjjtest(driver, devicename, p);
	//	testSQHT(driver);
	//	testHTSQQK(driver);
		testSQQK(driver);
			p++;

	}
	//@Test(invocationCount = 2, threadPoolSize = 1)
	public void test2() throws InterruptedException, IOException{
		Util2.addZjjtest(driver, devicename, p);
		p++;
	}
	//@Test
	public void test3() throws InterruptedException, IOException{
	
		//ControlTest.testgr(driver,devicename,testcase.get(i));
	}
	
	//申请合同
	public void testSQHT(AppiumDriver<AndroidElement> driver){
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_product"))
		.get(0).click();//常规产品列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_apply_compact"))
		.click();//申请合同
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/check_group")).get(2).click();//不安装   选择GPS安装方式
		Util2.swipeToUp(driver, 1000);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	//	driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add")).click();//添加照片
	//	Util1.upload(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_submit")).click();//提交按钮
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//是按钮
	}
	//申请合同-申请请款
	public void testHTSQQK(AppiumDriver<AndroidElement> driver){
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_product"))
		.get(0).click();//常规产品列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_apply_request"))
		.click();//申请请款
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/text_request_pay_name")).click();
		driver.findElement(By.id("com.kuaishoudan.financer:id/iv_is_show")).click();//xia
		driver.findElement(By.id("com.kuaishoudan.financer:id/ll_chekuan_shangpaidiya")).click();//上牌抵押地
driver.findElement(By.id("com.kuaishoudan.financer:id/options3")).click();//城市
int width = driver.manage().window().getSize().width;
int height = driver.manage().window().getSize().height;
	driver.swipe(width*2/3 , height-80, width *2/ 3,height-280,
		800);
	driver.findElement(By.id("com.kuaishoudan.financer:id/btnSubmit")).click();//城市确定
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang")).click();//上牌方
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(0).click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang")).click();//抵押方
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(0).click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.id("com.kuaishoudan.financer:id/iv_check")).click();//勾选
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm")).click();//确定
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	//
	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_confirm")).click();//申请请款确定
	
	
	try {
		Thread.sleep(7000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_countdown")).click();
	}
	//不出合同申请请款
	public void testSQQK(AppiumDriver<AndroidElement> driver){
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(1).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	/*	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_not_apply_compact")).click();//不出合同
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/check_group")).click();//不安装
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_submit")).click();//提交
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//是按钮
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		*/
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_apply_request"))
		.click();//申请请款445整数进位

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/text_request_pay_name")).click();//dian
		driver.findElement(By.id("com.kuaishoudan.financer:id/iv_is_show")).click();//xia
		driver.findElement(By.id("com.kuaishoudan.financer:id/et_chekuan_chejia")).sendKeys("AAAAASSSSSFFFFFEE");//车架号
		driver.findElement(By.id("com.kuaishoudan.financer:id/ll_chekuan_shangpaidiya")).click();//上牌抵押地
driver.findElement(By.id("com.kuaishoudan.financer:id/options3")).click();//城市
int width = driver.manage().window().getSize().width;
int height = driver.manage().window().getSize().height;
	driver.swipe(width*2/3 , height-80, width *2/ 3,height-280,
		800);
	driver.findElement(By.id("com.kuaishoudan.financer:id/btnSubmit")).click();//城市确定
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang")).click();//上牌方
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(0).click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang")).click();//抵押方
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(0).click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.id("com.kuaishoudan.financer:id/iv_check")).click();//勾选
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm")).click();//确定
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	//
	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_confirm")).click();//申请请款确定
	
	
	try {
		Thread.sleep(7000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_countdown")).click();
	}
	
	
}
