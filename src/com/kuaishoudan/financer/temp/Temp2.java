package com.kuaishoudan.financer.temp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Temp2 {
	public AppiumDriver<AndroidElement> driver;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Temp2().aa();
	}
	public  void aa(){
		try {
			driver = getdriver();
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 
/*			driver.findElement(By.id("com.kuaishoudan.financer:id/edit_account")).sendKeys("daiq2@kuaishoudan.com");
			driver.findElement(By.id("com.kuaishoudan.financer:id/edit_password")).sendKeys("123456");
			driver.findElement(By.id("com.kuaishoudan.financer:id/btn_login")).click();*/
		//	Thread.sleep(1000);
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
		//	driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//马上进件
		//	Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 
		//	driver.findElements(By.id("com.kuaishoudan.financer:id/text_name")).get(0).click();//首页列表
		//	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add_loan")).click();//进件
			driver.findElement(By.id("com.kuaishoudan.financer:id/btn_select_order_type_individual")).click();//去申请
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("com.kuaishoudan.financer:id/check_old_car")).click();//二手车
			driver.findElement(By.id("com.kuaishoudan.financer:id/text_brand")).click();//品牌车系
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElements(By.id("com.kuaishoudan.financer:id/item_brand_item")).get(2).click();//车辆品牌（奥迪）
			driver.findElements(By.id("com.kuaishoudan.financer:id/item_series_item")).get(1).click();//车辆型号
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("com.kuaishoudan.financer:id/edit_price")).sendKeys("13.58");//车辆价格
			driver.findElement(By.id("com.kuaishoudan.financer:id/edit_loan")).sendKeys("13.58");//申请贷款
			driver.findElement(By.id("com.kuaishoudan.financer:id/text_periods")).click();//还款期数
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(2).click();//还款期数周期
			driver.findElement(By.id("com.kuaishoudan.financer:id/text_product")).click();//金融产品
	    	driver.findElements(By.id("com.kuaishoudan.financer:id/text_product")).get(0).click();//第一个产品
	    	driver.findElement(By.id("com.kuaishoudan.financer:id/text_supplier")).click();//所属商户
	    	driver.findElements(By.id("com.kuaishoudan.financer:id/tv_name")).get(0).click();//所属商户列表
	    	driver.findElement(By.id("com.kuaishoudan.financer:id/edit_remark")).sendKeys("备注1");//备注
	    	driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_next")).click();//下一步
	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    	driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add")).click();//上传照片
	    	driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery")).click();//从相册选择
	    	driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb")).get(0).click();//添加图片（身份证）
	    	driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb")).get(1).click();//添加图片（驾驶证）
	    	driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok")).click();//确定按钮
	    	
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
	
	}

	public static AppiumDriver<AndroidElement> getdriver()
			throws MalformedURLException {
		System.out.println("**");
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "financerfinalVersionjiagusign.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("device", "Android");

		capabilities.setCapability("platformName", "Android");
		// 虚拟机
		capabilities.setCapability("deviceName", "Android Emulator");
		// 真机
		capabilities.setCapability("deviceName", "Android");

		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(CapabilityType.VERSION, "4.4");
		capabilities.setCapability(CapabilityType.PLATFORM, "WINDOWS");
		capabilities.setCapability("app", app.getAbsolutePath());

		// support Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");

		capabilities.setCapability("noSign", "True");

		capabilities.setCapability("app-package", "com.kuaishoudan.financer");
		capabilities.setCapability("app-activity",
				"com.kuaishoudan.financer.activity.act.WelcomeActivity");

		return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);
		

	}

	
}
