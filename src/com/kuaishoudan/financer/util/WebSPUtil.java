package com.kuaishoudan.financer.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

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
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.util.IdCardGenerator;
import com.kuaishoudan.financer.web.TestSP;

public class WebSPUtil {

	public static void login2(WebDriver driver, String username, String pwd) {

		driver.findElement(By.id("login_userName")).sendKeys(username);
		driver.findElement(By.id("login_passWord")).sendKeys(pwd);
		driver.findElement(By.id("login_submit")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Assert.assertEquals("456", "456");
	}

	public static String nameToemail(String username) {
		String email = "";
		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("牛娜", "niun@jizhicar.com");
		tempMap.put("黄淑贤", "huangsx@jizhicar.com");
		tempMap.put("李倩杰", "liqj@jizhicar.com");
		tempMap.put("孔令星", "konglx@jizhicar.com");
		tempMap.put("辛颖", "xiny@jizhicar.com");
		tempMap.put("沈月", "sheny@jizhicar.com");
		tempMap.put("刘浩亮", "liuhl@jizhicar.com");
		for (String key : tempMap.keySet()) {
			// System.out.println("key= "+ key + " and value= " +
			// tempMap.get(key));
			if (username.contains(key)) {
				email = tempMap.get(key);
			//	System.out.println(email);

			}
		}
		return email;
	}

	// 请款审批同意专员

	public static void testSP1(WebDriver driver,String email,String itename) {
		//		String username="niun@jizhicar.com";
		login2(driver, email, "@123456");

		clickItem(driver, itename);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int height = driver.manage().window().getSize().height;
		System.out.println("height" + height);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 2 + 150) + ")"); // 向下滑动
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(
				By.xpath("//div[@class='requestpayout_detail_btn_box']/a[4]/div"))
				.click();// 同意
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("argee_sub")).click();// 确认

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
	}

	// 请款审批同意数据运营

	public static void testSP2(WebDriver driver,String email,String itename) {
	//	String username = "huangsx@jizhicar.com";
		
		login2(driver, email, "@123456");

		clickItem(driver, itename);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int height = driver.manage().window().getSize().height;
		System.out.println("height" + height);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 2 + 100) + ")"); // 向下滑动
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(
				By.xpath("//div[@class='requestpayout_detail_btn_box']/a[3]/div"))
				.click();// 同意
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("argee_sub")).click();// 确认

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
	}

	// 请款审批审核组长

	public static void testSP3(WebDriver driver,String email,String itename) {
	//	String username = "xiny@jizhicar.com";
		login2(driver, email, "@123456");

		clickItem(driver, itename);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int height = driver.manage().window().getSize().height;
		System.out.println("height" + height);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 2 + 100) + ")"); // 向下滑动
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(
				By.xpath("//div[@class='requestpayout_detail_btn_box']/a[4]/div"))
				.click();// 同意
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("argee_sub")).click();// 确认

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
	}

	// 财务专员 审批

	public static void testSP4(WebDriver driver,String email,String itename) {
	///	String username = "sheny@jizhicar.com";
		login2(driver, email, "@123456");

		clickItem(driver, itename);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int height = driver.manage().window().getSize().height;
		System.out.println("height" + height);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 2 + 150) + ")"); // 向下滑动
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(
				By.xpath("//div[@class='requestpayout_detail_btn_box']/a[3]/div"))
				.click();// 同意
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("argee_sub")).click();//确认
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.className("cancel")).click();// 稍后再说

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
	}

	// 财务专员 审批-已放款

	public static void testSP5(WebDriver driver,String email,String itename) {
	//	String username = "sheny@jizhicar.com";
		login2(driver, email, "@123456");
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("已通过")).click();
		clickItemorder(driver, "刘浩亮");
		int height = driver.manage().window().getSize().height;
		System.out.println("height" + height);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height * 2 + 100) + ")"); // 向下滑动
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElements(
				By.xpath("//div[@class='requestpayout_detail_btn_box']/a/div"))
				.get(1).click();// 确认回款
		// driver.findElement(By.xpath("//div[@class='requestpayout_detail_btn_box']/a/div")).click();//确认回款
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.linkText("确认")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
	}

	// 财务专员 审批-已回款-归档待处理

	public static void testSP6(WebDriver driver,String email,String itename) {
	//	String username = "liuhl@jizhicar.com";
		login2(driver, email, "@123456");
		driver.findElement(By.linkText("客户")).click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//ul[@class='slide_nav_bar']/li[6]/a"))
				.click();//归档管理
		
		driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);
		driver.findElement(By.linkText("待处理")).click();
		clickItemorder(driver, itename);

		int height = driver.manage().window().getSize().height;
		System.out.println("height" + height);
//		Long aa=(Long) ((JavascriptExecutor) driver).executeScript("return  document.body.scrollHeight");
//		System.out.println(aa);

	//	((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 


		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"
				+ (height*2+400) + ")"); // 向下滑动
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);

		driver.findElements(
				By.xpath("//div[@class='requestpayout_detail_btn_box']/div[2]")).get(1)
				.click();// 确认归档

		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.className("flied_sub")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebUtil.logout(driver);
	}

	// 审批待办

	public static void clickItem(WebDriver driver, String name) {
		List<WebElement> items = driver.findElements(By.className("list_item"));// className("list_item")
		// List<WebElement>
		// items=driver.findElements(By.xpath("//div[@class='list_item']/div[2]/div[3]/dl[6]/dd"));//className("list_item")

		System.out.println("项目数" + items.size());
		for (int i = 1; i <= items.size(); i++) {
			System.out.println(i);
			WebElement item = items.get(i - 1).findElement(
					By.xpath("//ul[@class='todo_list']/li[" + i + "]/div/div"));
			// WebElement item= items.get(i);
			System.out.println("==" + item.getText());
			if (item.getText().contains(name)) {
				System.out.println("@" + item.getText());
				item.click();
				break;
			}
		}
	}

	// 待办订单列表
	public static void clickItemorder(WebDriver driver, String name) {
		List<WebElement> items = driver.findElements(By.className("list_item"));// className("list_item")
		// List<WebElement>
		// items=driver.findElements(By.xpath("//div[@class='list_item']/div[2]/div[3]/dl[6]/dd"));//className("list_item")

		System.out.println("项目数" + items.size());
		for (int i = 1; i <= items.size(); i++) {
			System.out.println(i);
			WebElement item = items.get(i - 1).findElement(
					By.xpath("//ul[@class='finance_list']/li[" + i
							+ "]/div[2]/div[3]/dl[6]/dd"));
			// WebElement item= items.get(i);
			System.out.println("==" + item.getText());
			if (item.getText().contains(name)) {
				System.out.println("@" + item.getText());
				item.click();
				break;
			}
		}
	}

	public static String getSPname(AppiumDriver<AndroidElement> driver) throws InterruptedException,
			IOException {

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String spname = "";
		String title=driver.findElement(By.id("com.kuaishoudan.financer.test:id/toolbar_title")).getText();
		if("查看状态".equals(title)){
			AppUtil.swipeToDown(driver, 1000);//向下滑动
			System.out.println("下-----------"+title);
			
		}else{
	
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_name"))
				.get(0).click();// 首页列表

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/toolbar_loan_status"))
				.click();// 状态审批流程
		
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/text_customer_look_status")).click();//查看进度
		}
		Thread.sleep(1000);
		List<AndroidElement> statueitems = driver.findElements(By
				.id("com.kuaishoudan.financer.test:id/ll_status"));
		for (int i = 0; i < statueitems.size(); i++) {
			String statue = statueitems
					.get(i)
					.findElement(
							By.id("com.kuaishoudan.financer.test:id/item_status"))
					.getText();
			System.out.println("@@" + statueitems.size() );
			if ("正在处理".equals(statue)) {
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer.test:id/item_name"))
						.getText();	
				String[]  strs=name.split("-");
				if(strs[0].contains("BD")){
					/////////////////////////////////
					////////////////////////////////////
				}
				String prename = statueitems
						.get(i+1)
						.findElement(
								By.id("com.kuaishoudan.financer.test:id/item_name"))
						.getText();
				String[]  strspre=prename.split("-");
				spname=strs[1]+","+strspre[1];
				System.out.println("正在处理"+spname);
			//	email = HZtoPY(name);
			//	System.out.println("email:" + name);

				break;
			}else if("放款审批/已放款".equals(statue)){
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer.test:id/item_name"))
						.getText();	
				String[]  strs=name.split("-");
				spname=strs[1]+","+"刘浩亮";
			}else if("回款结果/已回款".equals(statue)){
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer.test:id/item_name"))
						.getText();	
				String[]  strs=name.split("-");
				spname=strs[1]+","+"刘浩亮";
			}
		}
		return spname;
	}
	//状态实际值
	public static String getActual(AppiumDriver<AndroidElement> driver){
		String actu=driver.findElement(By.id("com.kuaishoudan.financer.test:id/tv_laster_status")).getText();
		return actu;
		
	}


}
