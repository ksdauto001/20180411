package com.kuaishoudan.financer;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.kuaishoudan.financer.util.CaseUtil;
import com.kuaishoudan.financer.util.Util2;

public class GRxjdk {

	public AppiumDriver<AndroidElement> driver;

	@BeforeTest
	public void setUp() throws Exception {

		driver = Util2.getdriver();
	
		Thread.sleep(500);
	}

	@AfterTest
	public void tearDown() throws Exception {

		driver.quit();
	}

	

	// 个人新建贷款
	@Test
	public void test5() throws InterruptedException {
	//	TrainCase tc = CaseUtil.getCaseByid("TI-1-011");


		String result ="1244";// Util2.getloginphone(driver);
//System.out.println(tc.getPhone()+"@@!");
		Assert.assertEquals(result, "");
	}
	//个人申请
	public void grxjdk(){
			driver.findElements(By.id("com.kuaishoudan.financer:id/text_name")).get(0).click();//首页列表
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
	}
}
