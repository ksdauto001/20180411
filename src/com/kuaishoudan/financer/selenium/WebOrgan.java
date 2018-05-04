package com.kuaishoudan.financer.selenium;

import java.net.MalformedURLException;
import java.util.List;
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new WebOrgan().test1();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void test1() throws MalformedURLException, InterruptedException{
		 	
		
			KSDCase ksd = RandomValue.getRandom();
			ksd.setProduct("qita22-其他22产品1");
			WebDriver webdriver = WebUtil.getdriver();
			WebUtil.login(webdriver, ksd.getLoginemail());// 登录
			webdriver.findElement(By.linkText("供应商")).click();
		String sss=	ksd.getProduct().split("-")[0];
	
		webdriver.navigate().refresh();
		webdriver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		List<WebElement>  ws =webdriver.findElements(By.className("org_name"));
	//	List<WebElement>  mask =webdriver.findElements(By.className("org_mask"));//org_mask
		System.out.println(ws.size());
	//	System.out.println(mask.size());
	   //    JavascriptExecutor j=(JavascriptExecutor)webdriver;

	    //   j.executeScript("document.getElementsByClassName('org_mask')[3].style.display='block';");
		for(int i=0;i<ws.size();i++){
			
			System.out.println("ws"+ws.get(i).getText());
			if(ws.get(i).getText().equals("qita22")){
				webdriver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
				  Actions action=new Actions(webdriver);  
				  
			        action.moveToElement(ws.get(i)).perform();
			        Thread.sleep(1000);

			     //  webdriver.findElements(By.className("org_mask")).get(i).findElement(By.xpath("a")).click();
			    	List<WebElement>  mask=    webdriver.findElements(By.className("org_mask"));
			    	System.out.println("==="+mask.size());
			    //	mask.get(i).click();
			    	mask.get(i).findElement(By.tagName("a")).click();
			}
			
		}
		webdriver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		
		/*List<WebElement> titos=  webdriver.findElements(By.className("tab_item type_others"));
		for(int i=0;i<titos.size();i++){
			System.out.println(titos.size());
		}
		*/
			Thread.sleep(15000);
			webdriver.quit();
		
	}
	
}
