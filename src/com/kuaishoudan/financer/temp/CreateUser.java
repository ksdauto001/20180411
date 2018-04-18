package com.kuaishoudan.financer.temp;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.util.CaseUtil;
import com.kuaishoudan.financer.util.AppUtil;

public class CreateUser {

	public AppiumDriver<AndroidElement> driver;

	@BeforeTest
	public void setUp() throws Exception {

		driver = AppUtil.getdriver();
	
		Thread.sleep(500);
	}

	@AfterTest
	public void tearDown() throws Exception {

		driver.quit();
	}

	

	// 创建用户
	@Test
	public void test5() throws InterruptedException {
	//	TrainCase tc = CaseUtil.getCaseByid("TI-1-011");


		String result ="1244";// Util2.getloginphone(driver);
//System.out.println(tc.getPhone()+"@@!");
		Assert.assertEquals(result,"");
	}
	

	// 创建用户
	public void createUser(String username, String pwd) throws InterruptedException {

		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_custom_img")).click();//+号
		driver.findElement(By.id("com.kuaishoudan.financer:id/menu_manual_input")).click();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_name")).sendKeys("张研2");
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_phone")).sendKeys("15022002063");
		driver.findElement(By.id("com.kuaishoudan.financer:id/text_id_type")).click();//点击身份证
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(2).click();//点击军官证 
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_id_code")).sendKeys("12321");//证件号码		
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_id_address")).sendKeys("户籍地址1");
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_confirm")).click();//确认
		driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_cancel")).click();//以后再说

	}

}
