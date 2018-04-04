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
		Thread.sleep(1000);
		Process process=Runtime.getRuntime().exec("adb devices");
		process.waitFor();
		InputStreamReader isr=new InputStreamReader(process.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		br.readLine();
		devicename=br.readLine().replaceAll("device", "").trim();
		System.out.println(devicename);
	
	}

	@AfterTest
	public void tearDown() throws Exception {
		
		driver.quit();
	}
	static int p=1;
	
	@Test(invocationCount =3, threadPoolSize = 1)
	public void test1() throws InterruptedException, IOException{

	
			Util2.addTest(driver, devicename,p);
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
	
}
