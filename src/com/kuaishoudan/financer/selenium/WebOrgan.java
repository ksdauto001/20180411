package com.kuaishoudan.financer.selenium;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.util.RandomValue;

public class WebOrgan {


	/**
	 * 供应商机构管理
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			WebDriver driver = WebUtil.getdriver();
			KSDCase ksd = RandomValue.getRandom();
			getImge3(driver,ksd);
			Thread.sleep(25000);
			driver.quit();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
	}

	public  static void test1(	WebDriver driver,KSDCase ksd ) throws MalformedURLException, InterruptedException {


		ksd.setProduct("李氏产品-那家店");// qita22-其他22产品1
		WebUtil.login(driver, ksd.getLoginemail());// 登录
		driver.findElement(By.linkText("供应商")).click();
		String sss = ksd.getProduct().split("-")[0];

		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		// List<WebElement> mask
		// =driver.findElements(By.className("org_mask"));//org_mask
		List<WebElement> pages=	driver.findElements(
				By.xpath("//ul[@class='page_list']/li"));
		loop: for (int j = 0; j <(pages.size()-4); j++) {

			List<WebElement> ws = driver.findElements(By.className("org_name"));
			System.out.println(ws.size());
			for (int i = 0; i < ws.size(); i++) {

				System.out.println("ws" + ws.get(i).getText());
				if (ws.get(i).getText().equals("qita22")) {
					driver.manage().timeouts()
							.implicitlyWait(13, TimeUnit.SECONDS);
					Actions action = new Actions(driver);

					action.moveToElement(ws.get(i)).perform();
					Thread.sleep(1000);

					List<WebElement> mask = driver.findElements(By
							.className("org_mask"));
					System.out.println("@===" + mask.size());
					// mask.get(i).click();
					mask.get(i).findElement(By.tagName("a")).click();
					break loop;
				}

			}
			Thread.sleep(500);
			swipeTodown(driver);
			driver.findElement(
					By.xpath("//ul[@class='page_list']/li[" + (j + 4) + "]"))
					.click();
		}
		Thread.sleep(1000);
		//testQdzl(driver);

		/*Thread.sleep(25000);
		driver.quit();*/

	}
	public static void testJjzl(WebDriver driver) throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		WebElement titos = driver.findElement(By
				.xpath("//ul[@class='tab_list inline_block']/li[2]"));//进件资料
		titos.click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("editOrg_jump")).click();//编辑
		// swipeTodown(driver);
		driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);
	
	/*	List<WebElement> checkalls=		driver.findElements(By.className("check_all")) ;//全选
		checkalls.get(3).click();*/

		List<Integer> list1=	RandomValue.getImg1();
		List<WebElement> ws =driver.findElements(By.tagName("label")) ;
		for(int k=0;k<ws.size();k++){
			String labels=ws.get(k).getAttribute("for");
			String classes=ws.get(k).getAttribute("class");		
					if((labels!=null)){
						if(classes.equals("checked")){
				
							ws.get(k).click();
						}		
						
					}			
		}
		Thread.sleep(500);
		for(int i=0;i<6;i++){
		driver.findElements(By.className("must_nosend")).get(i).click();//非必填
		}
		Thread.sleep(500);
		for(Integer musttype:list1){
			if(musttype<9){
				driver.findElements(By.className("must_send")).get(musttype).click();//必填
			}
		}
	
	//	driver.findElements(By.className("check_all")).get(11).click();
		Thread.sleep(1000);
		for(int k=0;k<ws.size();k++){
			String labels=ws.get(k).getAttribute("for");
			//System.out.println(labels);

			for(int imgtype:list1){
				
			 	if((labels!=null)&&labels.equals(""+imgtype)){
					ws.get(k).click();//选项
					
				}
			}
		}
		Thread.sleep(1000);
		/*for(int musttype:list1){
			if(musttype>9&&musttype<19){
			  switch(musttype){
		        case 10:
		       	 System.out.println("default10");
		            driver.findElements(By.className("check_all")).get(1).click();
		    		Thread.sleep(2000);break;
		        case 11:
		        		 System.out.println("default11");
		        	 driver.findElements(By.className("check_all")).get(3).click();
		     		Thread.sleep(2000);break;
		        case 12:
		       	 System.out.println("default12");
		        	 driver.findElements(By.className("check_all")).get(5).click();
		        	 Thread.sleep(2000);break;
		        case 13:
		       	 System.out.println("default13");
		        	driver.findElements(By.className("check_all")).get(7).click();
		    		Thread.sleep(2000);break;
		        case 14:
		       	 System.out.println("default14");
		        	driver.findElements(By.className("check_all")).get(9).click();
		    		Thread.sleep(2000);break;
		        case 15:
		        	driver.findElements(By.className("check_all")).get(11).click();
		        	 System.out.println("default15");
		     		Thread.sleep(2000);break;
		        default:
		            System.out.println("default");break;
		        }
			}
		}*/
		driver.findElement(By.linkText("保存")).click();//保存
		Thread.sleep(2000);
		driver.findElement(By.linkText("确定")).click();//确定
		
	}
	public static void testQkzl(WebDriver driver) throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		WebElement titos = driver.findElement(By
				.xpath("//ul[@class='tab_list inline_block']/li[3]"));//请款资料
		titos.click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("editOrg_jump")).click();//编辑
		// swipeTodown(driver);
		driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);
	
	/*	List<WebElement> checkalls=		driver.findElements(By.className("check_all")) ;//全选
		checkalls.get(3).click();*/

		List<Integer> list1=	RandomValue.getImg2();
		List<WebElement> ws =driver.findElements(By.tagName("label")) ;
		for(int k=0;k<ws.size();k++){
			String labels=ws.get(k).getAttribute("for");
			String classes=ws.get(k).getAttribute("class");		
					if((labels!=null)){
					//	System.out.println("=@@"+labels);
						if(classes.equals("checked")){
				
							ws.get(k).click();
						}		
						
					}			
		}
		Thread.sleep(500);
		for(int i=0;i<=6;i++){
		driver.findElements(By.className("must_nosend")).get(i).click();//非必填
		}
		Thread.sleep(500);
		for(Integer musttype:list1){
			if(musttype<9){
				driver.findElements(By.className("must_send")).get(musttype).click();//必填
			}
		}
		//driver.findElements(By.className("must_send")).get(1).click();//必填
		Thread.sleep(1000);
		for(int k=0;k<ws.size();k++){
			String labels=ws.get(k).getAttribute("for");
			//System.out.println(labels);

			for(int imgtype:list1){
				
			 	if((labels!=null)&&labels.equals(""+imgtype)){
					ws.get(k).click();//选项
					
				}
			}
		}
		driver.findElement(By.linkText("保存")).click();//保存
		Thread.sleep(2000);
		driver.findElement(By.linkText("确定")).click();//确定
		
	}
	public static void testQdzl(WebDriver driver) throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		WebElement titos = driver.findElement(By
				.xpath("//ul[@class='tab_list inline_block']/li[4]"));//归档资料
		titos.click();
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("editOrg_jump")).click();//编辑
		// swipeTodown(driver);
		driver.manage().timeouts().implicitlyWait(23, TimeUnit.SECONDS);
	
	/*	List<WebElement> checkalls=		driver.findElements(By.className("check_all")) ;//全选
		checkalls.get(3).click();*/

		List<Integer> list1=	RandomValue.getImg3();
		List<WebElement> ws =driver.findElements(By.tagName("label")) ;
		for(int k=0;k<ws.size();k++){
			String labels=ws.get(k).getAttribute("for");
			String classes=ws.get(k).getAttribute("class");		
					if((labels!=null)){
						//System.out.println("=@@"+labels);
						if(classes.equals("checked")){
				
							ws.get(k).click();
						}		
						
					}			
		}
		for(int i=0;i<6;i++){
		driver.findElements(By.className("must_nosend")).get(i).click();//非必填
		}
		for(Integer musttype:list1){
			if(musttype<9){
				driver.findElements(By.className("must_send")).get(musttype).click();//必填
			}
		}
		Thread.sleep(1000);
		for(int k=0;k<ws.size();k++){
			String labels=ws.get(k).getAttribute("for");
			//System.out.println(labels);

			for(int imgtype:list1){
				
			 	if((labels!=null)&&labels.equals(""+imgtype)){
					ws.get(k).click();//选项
					
				}
			}
		}
		driver.findElement(By.linkText("保存")).click();//保存
		Thread.sleep(2000);
		driver.findElement(By.linkText("确定")).click();//确定
		
	}
	public static void swipeTodown(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		((JavascriptExecutor) driver)
				.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // 向下滑动
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 进件资料-上传图片
	 */
	public  static void  getImge1(	WebDriver driver,KSDCase ksd ){
		 try {
			
			test1(driver,ksd);
			testJjzl(driver);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 请款资料-上传图片
	 */
	public  static void  getImge2(	WebDriver driver,KSDCase ksd ){
		 try {
			
			test1(driver,ksd);
			testQkzl(driver);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 归档资料-上传图片
	 */
	public  static void  getImge3(	WebDriver driver,KSDCase ksd ){
		 try {

			test1(driver,ksd);
			testQdzl(driver);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
