package com.kuaishoudan.financer.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.util.IdCardGenerator;

public class TestSP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	new TestSP().HZtoPY("黄淑贤");
		IdCardGenerator g = new IdCardGenerator();
		System.out.println(g.getItemID(17));
	}
	
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
		/*	username = properties.getProperty("username");
			pwd = properties.getProperty("password");*/
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

	public void login2(String username,String pwd) {

		driver.findElement(By.id("login_userName")).sendKeys(username);
		driver.findElement(By.id("login_passWord")).sendKeys(pwd);
		driver.findElement(By.id("login_submit")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	//	Assert.assertEquals("456", "456");
	}
	
	public static String HZtoPY(String username){
		String email="";
		Map<String, String> tempMap = new HashMap<String, String>();  
		  tempMap.put("牛娜","niun@jizhicar.com");  
		  tempMap.put("黄淑贤","huangsx@jizhicar.com");  
		  tempMap.put("李倩杰","liqj@jizhicar.com"); 
		  tempMap.put("孔令星","konglx@jizhicar.com");
		  tempMap.put("辛颖","xiny@jizhicar.com");
		  tempMap.put("沈月","sheny@jizhicar.com");
		  tempMap.put("刘浩亮","liuhl@jizhicar.com");
		  for (String key : tempMap.keySet()) {
		//	   System.out.println("key= "+ key + " and value= " + tempMap.get(key));
			if(username.contains(key)){
				email=tempMap.get(key);
				  System.out.println (email);
				
			}
		  }
		  return email;
	}
	//请款审批同意专员
	//@Test
	public void  testSP1(){
		String username="niun@jizhicar.com";
		login2(username,"@123456");
	
	clickItem("刘浩亮");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int height = driver.manage().window().getSize().height;
		System.out.println("height"+height);
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0,"+(height*2+100)+")"); // 向下滑动
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='requestpayout_detail_btn_box']/a[4]/div")).click();//同意
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("argee_sub")).click();//确认

		try {
			Thread.sleep(13000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 请款审批同意数据运营
	//@Test
	public void  testSP2(){
		String username="huangsx@jizhicar.com";
		login2(username,"@123456");
	
	clickItem("牛娜");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int height = driver.manage().window().getSize().height;
		System.out.println("height"+height);
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0,"+(height*2+100)+")"); // 向下滑动
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='requestpayout_detail_btn_box']/a[3]/div")).click();//同意
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("argee_sub")).click();//确认

		try {
			Thread.sleep(13000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//请款审批审核组长
	//@Test
	public void  testSP3(){
		String username="xiny@jizhicar.com";
		login2(username,"@123456");
	
	clickItem("黄淑贤");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int height = driver.manage().window().getSize().height;
		System.out.println("height"+height);
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0,"+(height*2+100)+")"); // 向下滑动
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='requestpayout_detail_btn_box']/a[4]/div")).click();//同意
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("argee_sub")).click();//确认

		try {
			Thread.sleep(13000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//财务专员 审批
	//@Test
	public void  testSP4(){
		String username="sheny@jizhicar.com";
		login2(username,"@123456");
	
	clickItem("辛颖");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int height = driver.manage().window().getSize().height;
		System.out.println("height"+height);
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0,"+(height*2+100)+")"); // 向下滑动
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[@class='requestpayout_detail_btn_box']/a[3]/div")).click();//同意
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.className("cancel")).click();//稍后再说

		try {
			Thread.sleep(13000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//财务专员 审批-已放款
	//@Test
	public void testSP5(){
		String username="sheny@jizhicar.com";
		login2(username,"@123456");
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("已通过")).click();
		clickItemorder("刘浩亮");
		int height = driver.manage().window().getSize().height;
		System.out.println("height"+height);
		((JavascriptExecutor) driver)
		.executeScript("window.scrollTo(0,"+(height*2+100)+")"); // 向下滑动
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
driver.findElements(By.xpath("//div[@class='requestpayout_detail_btn_box']/a/div")).get(1).click();//确认回款
		//	driver.findElement(By.xpath("//div[@class='requestpayout_detail_btn_box']/a/div")).click();//确认回款
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.linkText("确认")).click();
	}
	//财务专员 审批-已回款-归档待处理
		@Test
		public void testSP6(){
			String username="liuhl@jizhicar.com";
			login2(username,"@123456");
			driver.findElement(By.linkText("客户")).click();
			driver.findElement(By.xpath("//ul[@class='slide_nav_bar']/li[6]/a")).click();
			driver.findElement(By.linkText("待处理")).click();
			clickItemorder("刘浩亮");
			int height = driver.manage().window().getSize().height;
			System.out.println("height"+height);
			((JavascriptExecutor) driver)
			.executeScript("window.scrollTo(0,"+(height*2+100)+")"); // 向下滑动
			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//div[@class='requestpayout_detail_btn_box']/div[2]")).click();//确认归档

			driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
			driver.findElement(By.className("flied_sub")).click();
		}
	//审批待办
	
	public void clickItem(String name){
		List<WebElement> items=driver.findElements(By.className("list_item"));//className("list_item")
		//	List<WebElement> items=driver.findElements(By.xpath("//div[@class='list_item']/div[2]/div[3]/dl[6]/dd"));//className("list_item")
			
			System.out.println("项目数"+items.size());	
			for(int i=1;i<=items.size();i++){
				System.out.println(i);
			WebElement   item=	items.get(i-1).findElement(By.xpath("//ul[@class='todo_list']/li["+i+"]/div/div"));
			//	WebElement   item=	items.get(i);
				System.out.println("=="+item.getText());
					if(item.getText().contains(name)){
						System.out.println("@"+item.getText());	
						item.click();
						break;
					}
			}
	}
	//待办订单列表
		public void  clickItemorder(String name){
			List<WebElement> items=driver.findElements(By.className("list_item"));//className("list_item")
		//	List<WebElement> items=driver.findElements(By.xpath("//div[@class='list_item']/div[2]/div[3]/dl[6]/dd"));//className("list_item")
			
			System.out.println("项目数"+items.size());	
			for(int i=1;i<=items.size();i++){
				System.out.println(i);
			WebElement   item=	items.get(i-1).findElement(By.xpath("//ul[@class='finance_list']/li["+i+"]/div[2]/div[3]/dl[6]/dd"));
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
