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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
			Thread.sleep(1000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
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
	//	testYLR(driver);//已录入
		testYSQHT(driver);//已出合同
		// System.out.println(a.get(0).findElement(By.id(id)));
		// driver.findElement(By.linkText("客户")).click();
		// driver.findElement(By.linkText("已分配")).click();
		// driver.findElement(By.linkText("合同管理")).click();
		try {
			Thread.sleep(22000);
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
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("已录入")).click();	
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		clickItem("刘浩亮");	
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.className("requestpayout_detail_btn_box")).findElement(By.xpath("//a/div")).click();//通知审核结果
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.name("purchase_tax")).sendKeys("12000.12");//购置税
		driver.findElement(By.name("gps_charge")).sendKeys("13000.13");//GPS费
		driver.findElement(By.name("insurance")).sendKeys("14000.14");//GPS费
		driver.findElement(By.name("service_charge")).sendKeys("15000.15");//服务费
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("review_sub")).click();//确定按钮
		
		
		
	}
	//已通过
	public void  testYTG(WebDriver driver){
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("已通过")).click();
		
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	}
	//已申请合同 （上传图片还没做）
	public void testYSQHT(WebDriver driver){
		
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("合同管理")).click();
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		clickItem("刘浩亮");
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		
		
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0,1250)"); // 向下滑动
	
		System.out.println(driver.findElement(
				By.xpath("//div[@class='requestpayout_detail_container'][3]/div/div[4]/a[2]/div")).getText()
				);
		driver.findElement(
				By.xpath("//div[@class='requestpayout_detail_container'][3]/div/div[4]/a[2]/div"))
				.click();// 同意按钮
		
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.name("vin")).sendKeys("AAAAASSSSSDDDDDQQ");
		
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		
		((JavascriptExecutor) driver)
		.executeScript("	document.getElementsByClassName('file_upload_btn')[0].style.display='block';"); 
		driver.findElement(By.className("file_upload_layer")).click();///上传
		//driver.findElement(By.name("file")).;
		String path=System.getProperty("user.dir");
		System.out.println(path);
		driver.findElement(By.name("file")).sendKeys(path+"/20180409104900.jpg");
		
		try {
			//Thread.sleep(2000);
			Runtime.getRuntime().exec("autoit1.exe");
			//Thread.sleep(2000);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	

		 driver.findElement(By.partialLinkText("确认")).click();//确认按钮
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 driver.manage().timeouts().implicitlyWait(308, TimeUnit.SECONDS);
		 driver.findElement(By.linkText("确定")).click();//确定按钮
		
		
		
	}
/*	public void  clickItem1(String name){
		List<WebElement> items=driver.findElements(By.xpath("//ul[@class='finance_list']/li"));//className("list_item")
	//	List<WebElement> items=driver.findElements(By.xpath("//div[@class='list_item']/div[2]/div[3]/dl[6]/dd"));//className("list_item")
		
		System.out.println("项目数"+items.size());	
		for(int i=0;i<4;i++){
			System.out.println(i);
		WebElement   item=	items.get(i).findElement(By.xpath("//div[2]/div[3]/dl[6]/dd"));
		//	WebElement   item=	items.get(i);
			System.out.println("=="+item.getText());
				if(item.getText().contains(name)){
					System.out.println("@"+item.getText());	
					item.click();
					break;
				}
		}
	}*/
	public void  clickItem(String name){
		List<WebElement> items=driver.findElements(By.className("list_item"));//className("list_item")
	//	List<WebElement> items=driver.findElements(By.xpath("//div[@class='list_item']/div[2]/div[3]/dl[6]/dd"));//className("list_item")
		
		System.out.println("项目数"+items.size());	
		for(int i=1;i<=items.size();i++){
			System.out.println(i);
		WebElement   item=	items.get(i).findElement(By.xpath("//ul[@class='finance_list']/li["+i+"]/div[2]/div[3]/dl[6]/dd"));
		//	WebElement   item=	items.get(i);
			System.out.println("=="+item.getText());
				if(item.getText().contains(name)){
					System.out.println("@"+item.getText());	
					item.click();
					break;
				}
		}
	}
}
