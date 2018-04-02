package com.kuaishoudan.financer;


import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.kuaishoudan.financer.bean.TrainCase;
import com.kuaishoudan.financer.util.*;

public class Login {

	public AppiumDriver<AndroidElement> driver;

	@BeforeTest
	public void setUp() throws Exception {

		driver = Util2.getdriver();
	
		Thread.sleep(500);
	}

	@AfterTest
	public void tearDown() throws Exception {

		driver.quit();
	}

	

	// 登录成功
	@Test
	public void test5() throws InterruptedException {
		TrainCase tc = CaseUtil.getCaseByid("TI-1-011");
		login("daiq2@kuaishoudan.com", "123456");

		String result ="1244";// Util2.getloginphone(driver);
//System.out.println(tc.getPhone()+"@@!");
		Assert.assertEquals(result, tc.getPhone());
	}

	

	/**
	 * denglu
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public void login(String username, String pwd) throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 
		
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/edit_account")).clear();
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_password"))
				.clear();
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_account")).sendKeys(username);
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_password")).sendKeys(pwd);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_login")).click();

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 

	}
}
