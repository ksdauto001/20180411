package com.kuaishoudan.financer.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestLogin {

	/**
	 * @param args
	 */

	private WebDriver driver;
	private String baseUrl, baseUrl2;
	private Properties properties = new Properties();
	// private Testlogin tlogin=new Testlogin();
	public String username, pwd;

	@BeforeTest
	public void dylc() {
		System.setProperty("webdriver.chrome.driver", "chromedriver235.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();//

		try {
			InputStreamReader in = new InputStreamReader(
					TestLogin.class.getResourceAsStream("dzw.properties"),
					"UTF-8");
			properties.load(in);
			baseUrl = properties.getProperty("weburl");
			username = properties.getProperty("username");
			pwd = properties.getProperty("password");
			System.out.println("++++++++" + baseUrl);

		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

	}

	@Test
	public void login2() {

		driver.findElement(By.id("login_userName")).sendKeys(username);
		driver.findElement(By.id("login_passWord")).sendKeys(pwd);
		driver.findElement(By.id("login_submit")).click();

	//	testDFP(driver);//待分配
	//	testYFP(driver);//已分配
		testYLR(driver);//已录入
		// System.out.println(a.get(0).findElement(By.id(id)));
		// driver.findElement(By.linkText("客户")).click();
		// driver.findElement(By.linkText("已分配")).click();
		// driver.findElement(By.linkText("合同管理")).click();
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals("456", "456");
	}

	@AfterTest
	public void afend() {

		// new Testzhuanti().dylc(driver);
		System.out.println("finished");
		driver.close();
		driver.quit();

	}

	// 待分配
	public void testDFP(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("首页")).click();
		// 首页待办
		List<WebElement> items = driver
				.findElements(By
						.xpath("//ul[@class='todo_list']/li/div[@class='item_detail']/div[@class='last_person']"));
		System.out.println(items.size());
		for (int i = 0; i < items.size(); i++) {
			String name = items.get(i).getText();
			System.out.println(name);
			if (name.contains("刘浩亮")) {
				System.out.println("包含");
				items.get(i).click();
				break;
			}

		}
		//
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.findElement(By.linkText("分配任务")).click(); List<WebElement>
		driver.findElement(
				By.xpath("//div[@class='requestpayout_detail_btn_box']/div[2]"))
				.click();// 分配任务

		List<WebElement> ss = driver.findElements(By.className("personName"));
		for (int i = 0; i < ss.size(); i++) {
			System.out.println(i + ss.get(i).getText());
			WebElement fpr = ss.get(i);
			if (fpr.getText().contains("刘浩亮")) {
				System.out.println(i);
				if (i < 25) {
					System.out.println("<25");
					fpr.click();
					driver.manage().timeouts()
							.implicitlyWait(8, TimeUnit.SECONDS);
					((JavascriptExecutor) driver)
							.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // 向下滑动

					break;
				} else {
					System.out.println(">25");
					driver.manage().timeouts()
							.implicitlyWait(8, TimeUnit.SECONDS);
					((JavascriptExecutor) driver)
							.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // 向下滑动
					driver.manage().timeouts()
							.implicitlyWait(8, TimeUnit.SECONDS);
					fpr.click();
					break;
				}

			}

		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		// System.out.println(driver.findElement(By.id("allotBtnY")).getAttribute("value"));
		 driver.findElement(By.id("allotBtnY")).click();//分配按钮
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	// 	driver.findElement(By.id("delQDBtn")).click() ;//分配提醒确定
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("success_allot")).click();//确定按钮
		
	}
	//已分配
	public void  testYFP(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("已分配")).click();
	/*	List<WebElement> items=driver.findElements(By.className("list_item"));//className("list_item")
		System.out.println(items.size());	
		for(int i=0;i<items.size();i++){
		WebElement   item=	items.get(i).findElement(By.xpath("//div[2]/div[3]/dl[6]/dd"));
				if(item.getText().contains("刘浩亮")){
					item.click();
					break;
				}
		}*/
		clickItem("刘浩亮");
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(
				By.xpath("//div[@class='requestpayout_detail_btn_box']/div"))
				.click();// 开始录入
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.findElement(By.id("requestpayout_apply")).click();//确认申请
		
		
		
		
	}
	//已录入
	public void testYLR(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.linkText("已录入")).click();
		
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		clickItem("刘浩亮");
		//driver.findElement(By.id("sendAuditResult")).click();//通知审核结果
	}

	public void  clickItem(String name){
		List<WebElement> items=driver.findElements(By.className("list_item"));//className("list_item")
		System.out.println(items.size());	
		for(int i=0;i<items.size();i++){
		WebElement   item=	items.get(i).findElement(By.xpath("//div[2]/div[3]/dl[6]/dd"));
				if(item.getText().contains(name)){
					item.click();
					break;
				}
		}
	}
}
