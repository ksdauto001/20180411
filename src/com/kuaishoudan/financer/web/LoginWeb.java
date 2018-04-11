package com.kuaishoudan.financer.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginWeb {

	public  WebDriver driver;
	public String baseUrl,baseUrl2;
	public Properties properties = new Properties();
	//private Testlogin tlogin=new Testlogin();
	   public String username,pwd;
	
	public void loginWeb(){
		System.setProperty("webdriver.chrome.driver", "chromedriver235.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();//


        try {
        	InputStreamReader in=new InputStreamReader(TestLogin.class.getResourceAsStream("dzw.properties"), "UTF-8");
            properties.load(in);
            baseUrl = properties.getProperty("weburl");
            username=properties.getProperty("username");
            pwd=properties.getProperty("password");
            System.out.println("+++++++++++"+baseUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 
		driver.findElement(By.id("login_userName")).sendKeys(username);
		driver.findElement(By.id("login_passWord")).sendKeys(pwd);
		driver.findElement(By.id("login_submit")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//	driver.findElement(By.xpath("//div[@id='header']/div/ul/li[2]")).click();//客户
		driver.findElement(By.linkText("客户")).click();
		driver.findElement(By.linkText("已分配")).click();
		driver.findElement(By.linkText("合同管理")).click();
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
		driver.quit();
	}
	public static void main(String[] args) {
		new LoginWeb().loginWeb();
	}
}
