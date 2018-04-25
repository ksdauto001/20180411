package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.util.IdCardGenerator;
import com.kuaishoudan.financer.util.RandomValue;

public class AppUtil {

	public static AppiumDriver<AndroidElement> getdriver()
			throws MalformedURLException {
		System.out.println("**");
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir,
				"financer_ceshi_2.4.0.2_24002_2018-04-18.apk");// financerfinalVersionjiagusign.apk
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

	// 向上滑动
	public static void swipeToUp(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < 2; i++)
			driver.swipe(width / 2, height * 3 / 4, width / 2, height / 4,
					during);
		// wait for page loading12801321
	}

	// 向下滑动
	public static void swipeToDown(AppiumDriver driver, int during) {
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		// System.out.print(width+"@"+height);
		for (int i = 0; i < 2; i++)
			driver.swipe(width / 2, height * 3 / 4, width / 2, height - 20,
					during);
		// wait for page loading12801321
	}

	/**
	 * 创建用户
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static boolean createUser(AppiumDriver<AndroidElement> driver,
			String devicename, int k,KSDCase ksd) {
		boolean flag = false;
		try {
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);

			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_custom_img"))
					.click();// +号
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/menu_manual_input"))
					.click();// 手动输入

			// driver.findElement(By.id("com.kuaishoudan.financer:id/ll_input")).click();//第一次手动输入

			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(By.id("com.kuaishoudan.financer:id/edit_name"))
					.sendKeys(ksd.getUsername());// 名字
			Runtime.getRuntime().exec(
					"adb -s " + devicename + " shell input text " + ksd.getPhone());
			Thread.sleep(500);
			driver.findElement(By.id("com.kuaishoudan.financer:id/edit_phone"))
					.click();// 手机
			Thread.sleep(800);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/text_id_type")).click();// 点击身份证
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if ((int) (Math.random() * 2) == 0) {
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_select"))
						.get(1).click();// 点击身份证
				Thread.sleep(500);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text "
								+ ksd.getIdentitynum());// 证件号adb输入
				Thread.sleep(600);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_id_code"))
						.click();// 证件号码 *****
			} else {
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_select"))
						.get(2).click();// 点击军官证
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text "
								+ ksd.getJgid());// 证件号adb输入
				Thread.sleep(600);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_id_code"))
						.click();// 证件号码 *****
			}

			Thread.sleep(500);

			Runtime.getRuntime().exec(
					"adb -s " + devicename + " shell input text "+ksd.getAddress());
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/edit_id_address"))
					.click();// 地址
			Thread.sleep(1000);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
					.click();// 确认
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();// 马上进件
			flag = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (InterruptedException e) {
			// e.printStackTrace();
			System.out.println("InterruptedException");
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			System.out.println(k + "createuser  " + "NoSuchElementException");
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();
		}
		return flag;
	}

	/**
	 * 个人贷款
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static String addGr(AppiumDriver<AndroidElement> driver,
			String devicename, int k,KSDCase ksd) {


		String actualstatue = "";

		boolean flag = false;
		boolean cx = false;


		try {
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);

			driver.findElement(
					By.id("com.kuaishoudan.financer:id/btn_select_order_type_individual"))
					.click();// 去申请
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			// TODO Auto-generated catch block
			System.out.println(ex);
			flag = true;
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();
		}
		if (!flag) {
			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				int ran = ksd.getCartype();
				ran = 0;// ------------
				if (ran == 0) {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/check_old_car"))
							.click();// 二手车
				} else {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/layout_new_car"))
							.click();// 新车
				}
			driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_brand")).click();// 品牌车系
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				try {
					List<AndroidElement> clpps=	driver.findElements(
							By.id("com.kuaishoudan.financer:id/item_brand_item"));// 车辆品牌（奥迪）
					for(int i=0;i<clpps.size();i++){
						String brand=clpps.get(i).findElement(By.id("com.kuaishoudan.financer:id/text_brand")).getText();
						System.out.println(brand);
						if(ksd.getCarbrand().equals(brand)){
							clpps.get(i).click();
							break;
						}
						
					}
			//		clpps.get(2).click();//车辆品牌点
					cx = true;
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				try {
					if (cx) {
					/*	driver.findElements(
								By.id("com.kuaishoudan.financer:id/item_series_item"))
								.get(1).click();// 车辆型号
								*/
						List<AndroidElement> seriess=	driver.findElements(
								By.id("com.kuaishoudan.financer:id/text_series"));// 车辆品牌（奥迪）
						for(int i=0;i<seriess.size();i++){
							String series=seriess.get(i).getText();
							System.out.println(series);
							if(ksd.getCarseries().equals(series)){
								seriess.get(i).click();//车辆型号
								break;
							}
							
						}
					}
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				//Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text " + ksd.getCarprice());
				Thread.sleep(800);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_price"))
						.click();// 车辆价格
				Thread.sleep(800);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text " + ksd.getSqdk());
				Thread.sleep(800);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_loan")).click();// 申请贷款
				Thread.sleep(800);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_periods"))
						.click();// 还款期数 / 融资期限
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_select"))
						.get(ksd.getHkqs()).click();// 还款期数周期 /融资期限
		driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_product"))
						.click();// 金融产品
				try {
				List<	AndroidElement> producs=  driver.findElements(
							By.id("com.kuaishoudan.financer:id/text_product"));
					for(int i=0;i<producs.size();i++){
						if(!(producs.get(i).getText().contains("平安银行"))){
							ksd.setProduct(producs.get(i).getText() );
							producs.get(i).click();// 第一个产品
							break;
						}
					}
					
							//.get(0).click();// 第一个产品
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				// _________

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_feilv"))
						.click();// 费率
				try {
					List<AndroidElement> rates=	driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"));
					ksd.setRate( rates.get(0).getText());		
					rates.get(0).click();// 费率选项
							
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}

				// ___________

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_supplier"))
						.click();// 所属商户
				try {
				AndroidElement  supplier=	driver.findElements(
							By.id("com.kuaishoudan.financer:id/tv_name"))
							.get(0);
				ksd.setSssh(supplier.getText());
				supplier.click();// 所属商户列表
				
					
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}

				Thread.sleep(500);

				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text "+ksd.getRemark());
				Thread.sleep(200);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_remark"))
						.click();// 备注
				Thread.sleep(1000);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_next"))
						.click();// 下一步
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				
				/*  if(ran==0){ //二手车
					  driver.findElement(By.id(
				  
				 "com.kuaishoudan.financer:id/dialog_custom_confirm"
				  )).click();//订单常规 
					  }*/
				 
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
						.click();// 上传照片
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("IOException");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.openqa.selenium.NoSuchElementException ex) {
				System.out.println(k + "NoSuchElementException");
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_back"))
						.click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
						.click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_back"))
						.click();

			}
			actualstatue = upload(driver);

		}
		return actualstatue;
	}

	/**
	 * 企业贷款
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static String addQy(AppiumDriver<AndroidElement> driver,
			String devicename, int k,KSDCase ksd) {
		double sqdk = 0;
		double cljg = 0;
		String actualstatue = "";
		boolean flag = false;
		boolean cx = false;
		DecimalFormat df = new DecimalFormat("#.000");
		for (int i = 0; i < 200; i++) {
			sqdk = Double.parseDouble(df.format(2 + Math.random() * 17));// Math.random()
																			// *
																			// 97));//
																			// 997
			cljg = Double.parseDouble(df.format(2 + Math.random() * 97));
			// System.out.println(cljg+"="+sqdk);
			if (cljg >= sqdk) {
				break;
			}
		}
		try {

			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/btn_select_order_type_company"))
					.click();// 去申请
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			// TODO Auto-generated catch block
			System.out.println(ex);
			flag = true;
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();
		}
		if (!flag) {
			try {

				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

				/*
				 * driver.findElement(
				 * By.id("com.kuaishoudan.financer:id/edit_company_name"))
				 * .sendKeys("企业名称1");// 企业名称 driver.findElement(
				 * By.id("com.kuaishoudan.financer:id/edit_company_business_license"
				 * )) .sendKeys("营业执照号1");// 营业执照号
				 */
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text "
								+ "qiyemc");
				Thread.sleep(600);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_company_name"))
						.click();
				Thread.sleep(600);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text "
								+ "yingyezz");
				Thread.sleep(600);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_company_business_license"))
						.click();
				Thread.sleep(300);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				int ran =ksd.getCartype();
				ran =0;// ------------
				if (ran == 0) {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/check_old_car"))
							.click();// 二手车
				} else {
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/layout_new_car"))
							.click();// 新车
				}
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_brand"))
						.click();// 品牌车系
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				try {
					List<AndroidElement> clpps=	driver.findElements(
							By.id("com.kuaishoudan.financer:id/item_brand_item"));// 车辆品牌（奥迪）
					for(int i=0;i<clpps.size();i++){
						String brand=clpps.get(i).findElement(By.id("com.kuaishoudan.financer:id/text_brand")).getText();
						System.out.println(brand);
						if(ksd.getCarbrand().equals(brand)){
							clpps.get(i).click();
							break;
						}
						
					}
					cx = true;
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				try {
					if (cx) {
						driver.findElements(
								By.id("com.kuaishoudan.financer:id/item_series_item"))
								.get(1).click();// 车辆型号
					}
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
			//	Thread.sleep(1000);
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text " +ksd.getCarprice());
				Thread.sleep(500);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_price"))
						.click();// 车辆价格
				Thread.sleep(800);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text " + ksd.getSqdk());
				Thread.sleep(500);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_loan")).click();// 申请贷款
				Thread.sleep(1000);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_periods"))
						.click();// 还款期数
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_select"))
						.get(ksd.getHkqs()).click();// 还款期数周期
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_product"))
						.click();// 金融产品
				try {
					driver.findElements(
							By.id("com.kuaishoudan.financer:id/text_product"))
							.get(0).click();// 第一个产品
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				Thread.sleep(300);
				AppUtil.swipeToUp(driver, 800);// 向上滑动
				// _____
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_feilv"))
						.click();// 费率
				try {
					driver.findElements(
							By.id("com.kuaishoudan.financer:id/text_select"))
							.get(0).click();// 费率选项
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				// _____

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_supplier"))
						.click();// 所属商户
				try {
					driver.findElements(
							By.id("com.kuaishoudan.financer:id/tv_name"))
							.get(0).click();// 所属商户列表
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				Thread.sleep(600);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text beizhu1");
				Thread.sleep(200);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_remark"))
						.click();// 备注
				Thread.sleep(1000);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_next"))
						.click();// 下一步
				/*if (ran == 0) {
					// 二手车
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
							.click();// 订单常规
				}*/
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
						.click();// 上传照片
			} catch (IOException e) {
				// e.printStackTrace();
				System.out.println("IOException");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("InterruptedException");
				// e.printStackTrace();

			} catch (org.openqa.selenium.NoSuchElementException ex) {
				System.out.println(k + "NoSuchElementException");

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_back"))
						.click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
						.click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_back"))
						.click();

			} catch (org.openqa.selenium.WebDriverException e) {
				System.out.println(k + "WebDriverException");

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_cancel"))
						.click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
						.click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_back"))
						.click();
			}
			actualstatue = upload(driver);

		}
		return actualstatue;
	}

	public static void addTest(AppiumDriver<AndroidElement> driver,
			String devicename, int i) {
		KSDCase ksd=RandomValue.getRandom();
		System.out.println(ksd.getUsername()+ksd.getPhone()+ksd.getIdentitynum()
				+ksd.getJgid()+ksd.getQygr()+ksd.getCartype()+ksd.getCarbrand()
				+ksd.getCarseries()+ksd.getCarprice()+ksd.getSqdk()+ksd.getHkqs());
		  int gq=ksd.getQygr();
		boolean flag = createUser(driver, devicename, i,ksd);
		if (flag) {
			if (gq == 0) {// 企业贷款
				addQy(driver, devicename, i,ksd);
			} else {// 个人贷款
				addGr(driver, devicename, i,ksd);
			//
			}
		}

	}

	// 再次进件
	public static void zcjj(AppiumDriver<AndroidElement> driver) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String titletext = driver
				.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title"))
				.getText().trim();// 标题文本
		System.out.println(titletext);
		if ("贷款详情".equals(titletext)) {
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
					.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/text_customer_algin_jinjian"))
					.click(); // 大于1次进件
		} else {
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/btn_add_loan")).click();// 第3次进件3
		}
	}

	/**
	 * 上传照片
	 * 
	 * @param driver
	 * @return
	 */
	public static String upload(AppiumDriver<AndroidElement> driver) {
		String acstatue = "";
		try {
			/*
			 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 * driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
			 * .click();// 上传照片
			 */
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
					.click();// 从相册选择
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(0).click();// 添加图片（身份证）
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(1).click();// 添加图片（驾驶证）
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(1).click();// 添加图片（驾驶证）
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//
			driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok"))
					.click();// 两种证上传——确定按钮
			Thread.sleep(12000);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
					.click();// 上传完照片-确认按钮
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();// 提醒确定是

			// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			/*
			 * acstatue = driver .findElement(
			 * By.id("com.kuaishoudan.financer:id/item_status"))
			 * .getText().trim();
			 */

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (org.openqa.selenium.WebDriverException e) {
			System.out.println(e);
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();// 是
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 从客户页面返回
		}

		try {
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回按钮

			acstatue = AppSPUtil.getActstatue(driver);// 状态值
		} catch (org.openqa.selenium.WebDriverException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return acstatue;
	}

	public static void addZjjtest(AppiumDriver<AndroidElement> driver,
			String devicename, int i) {
		KSDCase ksd=RandomValue.getRandom();
		System.out.println(ksd.getUsername()+ksd.getPhone()+ksd.getIdentitynum()
				+ksd.getJgid()+ksd.getQygr()+ksd.getCartype()+ksd.getCarbrand()
				+ksd.getCarseries()+ksd.getCarprice()+ksd.getSqdk()+ksd.getHkqs());
		  int gq=ksd.getQygr();
		AppUtil.zcjj(driver);
		if (gq == 0) {// 企业贷款
			addQy(driver, devicename, i,ksd);
		} else {// 个人贷款
			addGr(driver, devicename, i,ksd);
		}
	}

	public static void login(AppiumDriver<AndroidElement> driver,
			String devicename, String username) {
		/*
		 * driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/edit_account")).clear();
		 * driver.findElement
		 * (By.id("com.kuaishoudan.financer:id/edit_password")) .clear();
		 */
		try {
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/edit_account")).clear();
			Runtime.getRuntime().exec(
					"adb -s " + devicename + " shell input text " + username);
			Thread.sleep(600);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/edit_account")).click();
			Thread.sleep(600);
			Runtime.getRuntime().exec(
					"adb -s " + devicename + " shell input text " + "@123456");
			Thread.sleep(600);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/edit_password")).click();
			driver.findElement(By.id("com.kuaishoudan.financer:id/btn_login"))
					.click();
			Thread.sleep(3000);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// driver.findElement(By.id("com.kuaishoudan.financer:id/edit_account")).sendKeys(username);
		// driver.findElement(By.id("com.kuaishoudan.financer:id/edit_password")).sendKeys("@123456");

	}

	// 登出
	public static void logout(AppiumDriver<AndroidElement> driver) {
		System.out.println("logout");
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_menu"))
				.click();// 菜单
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/header_img"))
				.click();// 头像
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/account_logout"))
				.click();// 退出登录
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 确定)
	}

	public static boolean ElementExist(AppiumDriver<AndroidElement> driver,
			By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			return false;
		}

	}

	/**
	 * 已请款-----返回查看状态
	 * 
	 * @param driver
	 */
	public static String getStatue(AppiumDriver<AndroidElement> driver) {
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		String statue = AppSPUtil.getActstatue(driver);// 状态值
		return statue;

	}

	/**
	 * (未使用) 查看进度
	 * 
	 * @param driver
	 */
	public static void look_status(AppiumDriver<AndroidElement> driver) {
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		// driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 第一个客户
		driver.manage().timeouts().implicitlyWait(28, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
				.click();// 流程。。。
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_customer_look_status"))
				.click();// 查看进度
	}

	/**
	 * 两次返回
	 * 
	 * @param driver
	 */
	public static void goback1(AppiumDriver<AndroidElement> driver) {
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回按钮
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回按钮
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 一次返回
	 * 
	 * @param driver
	 */
	public static void goback0(AppiumDriver<AndroidElement> driver) {
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回按钮
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
