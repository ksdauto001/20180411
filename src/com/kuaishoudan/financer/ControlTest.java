package com.kuaishoudan.financer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.util.Util2;
import com.kuaishoudan.financer.web.LoginWeb;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyMetastate;

public class ControlTest {
	public AppiumDriver<AndroidElement> driver=null;
	String devicename="";
	@BeforeTest
	public void setUp() throws Exception {
		driver =   Util2.getdriver();

		Thread.sleep(1000);
		Process process=Runtime.getRuntime().exec("adb devices");
		process.waitFor();
		InputStreamReader isr=new InputStreamReader(process.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		br.readLine();
		devicename=br.readLine().replaceAll("device", "").trim();
		System.out.println(devicename);
	
	}

	@AfterTest
	public void tearDown() throws Exception {
		
		driver.quit();
	}

	
	@Test
	public void test1() throws InterruptedException, IOException{

		ControlTest.testgr(driver,devicename,18);
		
	}
	//@Test
	public void test2() throws InterruptedException, IOException{
 
		ControlTest.testgr(driver,devicename,18);
	}
	//@Test
	public void test3() throws InterruptedException, IOException{
	
		ControlTest.testgr(driver,devicename,19);
	}
	/**
	 * 个人建贷款
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public static void testgr(AppiumDriver<AndroidElement> driver,String devicename,int i) throws InterruptedException, IOException{

	
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 

		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_custom_img")).click();//+号
		driver.findElement(By.id("com.kuaishoudan.financer:id/menu_manual_input")).click();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_name")).sendKeys("张研"+i);//
		Runtime.getRuntime().exec("adb -s "+devicename+" shell input text 15022002068");
		Thread.sleep(500);
	driver.findElement(By.id("com.kuaishoudan.financer:id/edit_phone")).click();//手机

	
		driver.findElement(By.id("com.kuaishoudan.financer:id/text_id_type")).click();//点击身份证
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(2).click();//点击军官证 
		Runtime.getRuntime().exec("adb -s "+devicename+" shell input text 789014"+i);//证件号adb输入
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_id_code")).click();//证件号码	*****	

		Thread.sleep(500);
		Runtime.getRuntime().exec("adb -s "+devicename+" shell input text aaaasss");
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_id_address")).click();//地址
	//	
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_confirm")).click();//确认
		driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_cancel")).click();//以后再说
	//	driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//马上进件

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add_loan")).click();//进件
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_select_order_type_individual")).click();//去申请
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/check_old_car")).click();//二手车
		driver.findElement(By.id("com.kuaishoudan.financer:id/text_brand")).click();//品牌车系
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/item_brand_item")).get(2).click();//车辆品牌（奥迪）
		driver.findElements(By.id("com.kuaishoudan.financer:id/item_series_item")).get(1).click();//车辆型号
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Runtime.getRuntime().exec("adb -s "+devicename+" shell input text 13.58");
		Thread.sleep(300);
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_price")).click();//车辆价格
		Thread.sleep(500);
		Runtime.getRuntime().exec("adb -s "+devicename+" shell input text 13.58");
		Thread.sleep(300);
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_loan")).click();//申请贷款
		driver.findElement(By.id("com.kuaishoudan.financer:id/text_periods")).click();//还款期数
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(2).click();//还款期数周期
		driver.findElement(By.id("com.kuaishoudan.financer:id/text_product")).click();//金融产品
    	driver.findElements(By.id("com.kuaishoudan.financer:id/text_product")).get(0).click();//第一个产品
    	driver.findElement(By.id("com.kuaishoudan.financer:id/text_supplier")).click();//所属商户
    	driver.findElements(By.id("com.kuaishoudan.financer:id/tv_name")).get(0).click();//所属商户列表
    	Runtime.getRuntime().exec("adb -s "+devicename+" shell input text beizhu1");
		Thread.sleep(300);
    	driver.findElement(By.id("com.kuaishoudan.financer:id/edit_remark")).click();//备注
    	driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_next")).click();//下一步
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add")).click();//上传照片
    	driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery")).click();//从相册选择
    	driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb")).get(0).click();//添加图片（身份证）
    	driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb")).get(1).click();//添加图片（驾驶证）
    	driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb")).get(1).click();//添加图片（驾驶证）
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
   // 	
    	driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok")).click();//两种证上传——确定按钮
    Thread.sleep(4000);
    	driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_confirm")).click();//上传完-确定按钮
    	driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    	driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//提醒确定是
    	
   // 	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
   // 	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
   //	driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
    	String expectstatue="正在处理";
    	String  acstatue=driver.findElement(By.id("com.kuaishoudan.financer:id/item_status")).getText().trim();
	Assert.assertEquals(acstatue, expectstatue);
	driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
	driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
	}
	/**
	 * 企业建贷款
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	//@Test
	public void testqy() throws MalformedURLException, InterruptedException{
		driver = Util2.getdriver();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 
/*			driver.findElement(By.id("com.kuaishoudan.financer:id/edit_account")).sendKeys("daiq2@kuaishoudan.com");
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_password")).sendKeys("123456");
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_login")).click();*/
	//	Thread.sleep(1000);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_custom_img")).click();//+号
		driver.findElement(By.id("com.kuaishoudan.financer:id/menu_manual_input")).click();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_name")).sendKeys("张研14");//
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_phone")).sendKeys("15022002074");//
		driver.findElement(By.id("com.kuaishoudan.financer:id/text_id_type")).click();//点击身份证
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(2).click();//点击军官证 
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_id_code")).sendKeys("123224");//证件号码	*****	
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_id_address")).sendKeys("户籍地址9");
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_confirm")).click();//确认
		driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_cancel")).click();//以后再说
	//	driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//马上进件

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); 

		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add_loan")).click();//进件
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_select_order_type_company")).click();//去申请
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_company_name")).sendKeys("企业名称1");//企业名称
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_company_business_license")).sendKeys("营业执照号1");//营业执照号
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


			Util2.swipeToUp(driver, 800);//向上滑动

		driver.findElement(By.id("com.kuaishoudan.financer:id/text_supplier")).click();//所属商户
		driver.findElements(By.id("com.kuaishoudan.financer:id/tv_name")).get(0).click();//所属商户列表
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_remark")).sendKeys("备注1");//备注
    	driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_next")).click();//下一步
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add")).click();//上传照片
    	driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery")).click();//从相册选择
    	driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb")).get(0).click();//添加图片（身份证）
    	driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb")).get(1).click();//添加图片（驾驶证）
    	driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb")).get(1).click();//添加图片（驾驶证）
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
   // 	
    	driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok")).click();//两种证上传——确定按钮
    Thread.sleep(4000);
    	driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_confirm")).click();//上传完-确定按钮
    	driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    	driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//提醒确定是
    	
   // 	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
   // 	driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
   //	driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
    	
    	String expectstatue="正在处理";
    	String  acstatue=driver.findElement(By.id("com.kuaishoudan.financer:id/item_status")).getText().trim();
	Assert.assertEquals(acstatue, expectstatue);
	}
}
