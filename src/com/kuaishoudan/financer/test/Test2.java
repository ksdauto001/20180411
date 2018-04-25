package com.kuaishoudan.financer.test;

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

import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebUtil;

public class Test2 {

	public AppiumDriver<AndroidElement> driver = null;
	String devicename = "";
	public WebDriver webdriver = null;

	@BeforeTest
	public void setUp() throws Exception {

		webdriver = WebUtil.getdriver();
	}

	@AfterTest
	public void tearDown() throws Exception {

		webdriver.quit();
	}

	// 创建用户
	@Test(invocationCount = 1, threadPoolSize = 1)
	public void test1() throws InterruptedException, IOException {

		System.out.println("***1@");

		Assert.assertEquals(true, true);
	}

}
