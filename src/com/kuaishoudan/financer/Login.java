package com.kuaishoudan.financer;


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
	//	login(tc.getPhone(), tc.getPwd());

		String result ="12";// Util2.getloginphone(driver);
System.out.println(tc.getPhone()+"@@!");
		Assert.assertEquals(result, tc.getPhone());
	}

	

	/**
	 * denglu
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public void login(String phone, String pwd) throws InterruptedException {

		Thread.sleep(2000);

		driver.findElement(
				By.id("com.winsion.inception:id/et_user_center_phone")).clear();
		driver.findElement(By.id("com.winsion.inception:id/et_user_center_pwd"))
				.clear();
		driver.findElement(
				By.id("com.winsion.inception:id/et_user_center_phone"))
				.sendKeys(phone);

		driver.findElement(By.id("com.winsion.inception:id/et_user_center_pwd"))
				.sendKeys(pwd);

		driver.findElement(
				By.id("com.winsion.inception:id/bt_user_center_login")).click();
		Thread.sleep(2000);
	}
}
