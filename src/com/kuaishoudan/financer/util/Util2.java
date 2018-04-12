package com.kuaishoudan.financer.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

public class Util2 {

	public static AppiumDriver<AndroidElement> getdriver()
			throws MalformedURLException {
		System.out.println("**");
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "financer_ceshiPack_2.4.0.0_24000_2018-04-11.apk");//financerfinalVersionjiagusign.apk
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

	/**
	 * 创建用户
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static boolean createUser(AppiumDriver<AndroidElement> driver,
			String devicename, int k) {
		boolean flag = false;
		IdCardGenerator g = new IdCardGenerator();
		Calendar calendar = Calendar.getInstance();
		try {
			String identitynum = calendar.getTime().getTime()
					+ (int) (Math.random() * 89 + 10) + "";// 军官证号
			String phone = RandomValue.getTel();
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_custom_img"))
					.click();// +号
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/menu_manual_input"))
					.click();
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(By.id("com.kuaishoudan.financer:id/edit_name"))
					.sendKeys(RandomValue.getChineseName());//
			Runtime.getRuntime().exec(
					"adb -s " + devicename + " shell input text " + phone);
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
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text "
								+ g.generate());// 证件号adb输入
				Thread.sleep(500);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_id_code"))
						.click();// 证件号码 *****
			} else {
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_select"))
						.get(2).click();// 点击军官证
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text "
								+ identitynum);// 证件号adb输入
				Thread.sleep(100);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_id_code"))
						.click();// 证件号码 *****
			}

			Thread.sleep(500);

			Runtime.getRuntime().exec(
					"adb -s " + devicename + " shell input text address1");
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/edit_id_address"))
					.click();// 地址
			//
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
	public static void addGr(AppiumDriver<AndroidElement> driver,
			String devicename, int k) {
		double sqdk = 0;
		double cljg = 0;
		String expectstatue = "正在处理";
		DecimalFormat df = new DecimalFormat("#.00");
		boolean flag = false;
		boolean cx = false;
		for (int i = 0; i < 200; i++) {
			sqdk = Double.parseDouble(df.format(2 + Math.random() * 97));// 997
			cljg = Double.parseDouble(df.format(2 + Math.random() * 97));
			// System.out.println(cljg+"="+sqdk);
			if (cljg >= sqdk) {
				break;
			}
		}

		try {
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

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
				if ((int) (Math.random() * 2) == 0) {
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
					driver.findElements(
							By.id("com.kuaishoudan.financer:id/item_brand_item"))
							.get(4).click();// 车辆品牌（奥迪）
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
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text " + cljg);
				Thread.sleep(500);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_price"))
						.click();// 车辆价格
				Thread.sleep(800);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text " + sqdk);
				Thread.sleep(500);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_loan")).click();// 申请贷款
				Thread.sleep(500);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_periods"))
						.click();// 还款期数   / 融资期限
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_select"))
						.get((int) (Math.random() * 8)).click();// 还款期数周期    /融资期限
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_product"))
						.click();// 金融产品
				try {
					driver.findElements(
							By.id("com.kuaishoudan.financer:id/text_product"))
							.get(0).click();// 第一个产品
				} catch (java.lang.IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_back"))
							.click();
				}
				//_________
				
				driver.findElement(By.id("com.kuaishoudan.financer:id/text_feilv")).click();//费率
			try{
				driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(0).click();//费率选项
			} catch (java.lang.IndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_back"))
						.click();
			}
				
				//___________
				
				
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

				Thread.sleep(300);

				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text beizhu1");
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_remark"))
						.click();// 备注
				Thread.sleep(800);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_next"))
						.click();// 下一步
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
			String acstatue = upload(driver);
			try {
				Assert.assertEquals(acstatue, expectstatue, "" + k);
			} catch (java.lang.AssertionError e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println(k + "" + e);
			}
		}
	}

	/**
	 * 企业贷款
	 * 
	 * @param driver
	 * @param devicename
	 * @param k
	 */
	public static void addQy(AppiumDriver<AndroidElement> driver,
			String devicename, int k) {
		double sqdk = 0;
		double cljg = 0;
		String expectstatue = "正在处理";
		boolean flag = false;
		boolean cx = false;
		DecimalFormat df = new DecimalFormat("#.00");
		for (int i = 0; i < 200; i++) {
			sqdk = Double.parseDouble(df.format(2 + Math.random() * 97));// 997
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
				Thread.sleep(500);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_company_business_license"))
						.click();
				Thread.sleep(500);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				if ((int) (Math.random() * 2) == 0) {
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
					driver.findElements(
							By.id("com.kuaishoudan.financer:id/item_brand_item"))
							.get(4).click();// 车辆品牌（奥迪）
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
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text " + cljg);
				Thread.sleep(500);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_price"))
						.click();// 车辆价格
				Thread.sleep(800);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text " + sqdk);
				Thread.sleep(500);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_loan")).click();// 申请贷款
				Thread.sleep(600);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/text_periods"))
						.click();// 还款期数
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				driver.findElements(
						By.id("com.kuaishoudan.financer:id/text_select"))
						.get((int) (Math.random() * 8)).click();// 还款期数周期
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
				Util2.swipeToUp(driver, 800);// 向上滑动
//_____
				driver.findElement(By.id("com.kuaishoudan.financer:id/text_feilv")).click();//费率
			try{
				driver.findElements(By.id("com.kuaishoudan.financer:id/text_select")).get(0).click();//费率选项
			} catch (java.lang.IndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_back"))
						.click();
			}
//_____
				
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
				Thread.sleep(300);
				Runtime.getRuntime().exec(
						"adb -s " + devicename + " shell input text beizhu1");

				driver.findElement(
						By.id("com.kuaishoudan.financer:id/edit_remark"))
						.click();// 备注
				Thread.sleep(800);
				driver.findElement(
						By.id("com.kuaishoudan.financer:id/toolbar_next"))
						.click();// 下一步
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
			String acstatue = upload(driver);
			try {
				Assert.assertEquals(acstatue, expectstatue, "" + k);
			} catch (java.lang.AssertionError e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println(k + "" + e);
			}
		}
	}

	public static void addTest(AppiumDriver<AndroidElement> driver,
			String devicename, int i) {
		boolean flag = createUser(driver, devicename, i);
		if (flag) {
			if ((int) (Math.random() * 2) == 0) {// 企业贷款
				addQy(driver, devicename, i);
			} else {// 个人贷款
				addGr(driver, devicename, i);
			}
		}

	}

	// 再次进件
	public static void zcjj(AppiumDriver<AndroidElement> driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	/*	//
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
				.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_customer_algin_jinjian"))
				.click(); //大于1次进件
*/	
			driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add_loan")).click();//第一次进件3

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
			Thread.sleep(5000);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
					.click();// 上传完-确定按钮
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();// 提醒确定是

			// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			acstatue = driver
					.findElement(
							By.id("com.kuaishoudan.financer:id/item_status"))
					.getText().trim();

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
			/*
			 * driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			 * driver.findElement(
			 * By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//
			 * 返回按钮
			 */} catch (org.openqa.selenium.WebDriverException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return acstatue;
	}

	public static void addZjjtest(AppiumDriver<AndroidElement> driver,
			String devicename, int i) {
		Util2.zcjj(driver);
		if ((int) (Math.random() * 2) == 0) {// 企业贷款
			addQy(driver, devicename, i);
		} else {// 个人贷款
			addGr(driver, devicename, i);
		}
	}
}
