package com.kuaishoudan.mgccar;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.bean.Supplier;
import com.kuaishoudan.financer.dao.SpplierDaoImpl;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.DBUtil;
import com.kuaishoudan.financer.util.RandomValue;

public class Mgcc {
	static AndroidDriver<WebElement> driver;
	static KSDCase ksd;
	DBUtil db = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mgcc mc = new Mgcc();
		try {
			mc.setUp();
 	
	
			Thread.sleep(400);
 
	 
			//  mgcGJBXX(driver,ksd); mgcGCXX(driver,ksd);
 

			  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mc.tearDown();
	}
	public void tearDown(){
		driver.quit();
		
		db.closeConn1();
	}
	public void setUp() throws IOException, InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {

				db = new DBUtil();
				ksd = RandomValue.getRandom();
				  DecimalFormat df2 = new DecimalFormat("#.00");
				 ksd.setCarprice( Double.parseDouble(df2.format(ksd.getCarprice())));
				 ksd.setSqdk( Double.parseDouble(df2.format(ksd.getSqdk())));
				    
				System.out.println("名称" + ksd.getUsername() + "手机"
						+ ksd.getPhone() + "身份证号" + ksd.getIdentitynum()
						+ "身份类型" + ksd.getIdentitytype() + "军官" + ksd.getJgid()
						+ "企业个人" + ksd.getQygr() + "车类型" + ksd.getCartype()
						+ "车品牌" + ksd.getCarbrand() + "车系" + ksd.getCarseries()
						+ "车价格" + ksd.getCarprice() + "贷款价格" + ksd.getSqdk()
						+ "融资期限" + ksd.getHkqs() + "\n  "
						+ ksd.getPurchase_tax() + " " + ksd.getInsurance()
						+ " " + ksd.getGps_charge() + " "
						+ ksd.getService_charge() + "," + ksd.getRegisttype()
						+ "," + ksd.getPledge());
				System.out.println(ksd.getFlow() + "," + ksd.getLoginemail()+","+ksd.getMgccar_phone());

			}

		}).start();
		driver = getDriver();
		Thread.sleep(300);
		try {
			new WebDriverWait(driver, 2)
					.until(new ExpectedCondition<WebElement>() {
						@Override
						public WebElement apply(WebDriver d) {
							return d.findElement(By
									.id("com.kuaishoudan.mgccar:id/tv_toolbar_title")); // 标题

						}
					});


		} catch (org.openqa.selenium.TimeoutException e) {
			int status=SpplierDaoImpl.mgccStatus(ksd.getMgccar_phone());
			if(status==1){
				mgcLogin(ksd);// 登录

			}else{
				mgcRegist();//注册
				mgcLogin(ksd);// 登录
				mgcBind();//绑定信息
			}
		}
	}

	public static AndroidDriver<WebElement> getDriver()
			throws MalformedURLException {

		String apkName = "";
		Properties properties = new Properties();
		try {
			InputStreamReader in = new InputStreamReader(
					AppUtil.class.getResourceAsStream("ksd.properties"),
					"UTF-8");
			properties.load(in);
			apkName = properties.getProperty("mgccar_Name");

			System.out.println("+++++++++++" + apkName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, apkName);// financerfinalVersionjiagusign.apk
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("automationName", "uiautomator2");
		capabilities.setCapability("newCommandTimeout", 4000);
		capabilities.setCapability("device", "Android");
		capabilities.setCapability("platformName", "Android");
		// 虚拟机
		// capabilities.setCapability("deviceName", "Android Emulator");
		// 真机
		capabilities.setCapability("deviceName", "Android");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(CapabilityType.VERSION, "4.4");

		// support Chinese
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);

		// capabilities.setCapability("autoLaunch", false);

		capabilities.setCapability("noSign", true);
		capabilities.setCapability("noReset", true);

		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("app-package", "com.kuaishoudan.mgccar");
		capabilities.setCapability("app-activity",
				"com.kuaishoudan.mgccar.personal.activity.WelcomeActivity");
		AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(
				new URL("http://127.0.0.1:4725/wd/hub"), capabilities);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver,
		 * new AlertListener(), new ElementListener());
		 */

		return driver;

	}
	public static void mgcBind() {
		List<Supplier> spls = SpplierDaoImpl.getSuplier(ksd);
		String splcode=spls.get(ksd.getSssh_id()).getSupplier_code();
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/bind_no")).sendKeys(splcode );//绑定账号
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/tv_next")).click();//下一步
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/dialog_custom_confirm")).click();//确定
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/tv_department")).click();//选择部门
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElements(By.id("com.kuaishoudan.mgccar:id/tv_title")).get(1).click();//金融部
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/tv_role")).click();//选择角色
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElements(By.id("com.kuaishoudan.mgccar:id/tv_title")).get(1).click();//销售经理

		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/tv_succed")).click();//下一步
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/bind_no")).sendKeys(ksd.getUsername());//请输入姓名
		AppUtil.df(driver,By.id("com.kuaishoudan.mgccar:id/tv_succed")).click();//完成
	}
	public static void mgcRegist() {

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/btn_registered"))
				.click();// 注册
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_phone")).sendKeys(ksd.getMgccar_phone());// 手机号
		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/btn_captcha")).click();//获取验证码
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/edit_captcha"))
				.sendKeys(SpplierDaoImpl.mgccCaptcha(ksd.getMgccar_phone()));// 验证码
		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/edit_pwd")).sendKeys(ksd.getMgccar_pwd());//密码
		
		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/btn_register")).click();//注册按钮
	}
	public static void mgcLogout() {

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/tv_main_mine_text"))
				.click();// 我的
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/tv_exit")).click();// 退出登录

		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/dialog_custom_confirm"))
				.click();// 确定
	}

	public static void mgcLogin(KSDCase ksd) {
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_account"))
				.clear();
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_account"))
				.sendKeys(ksd.getMgccar_phone());
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_password"))
				.sendKeys(ksd.getMgccar_pwd());

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/btn_login"))
				.click();
	}

	public static void mgcGJBXX(AndroidDriver<WebElement> driver, KSDCase ksd) {
		AppUtil.df(driver,
				By.id("com.kuaishoudan.mgccar:id/tv_toolbar_confirm")).click();
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_name"))
				.sendKeys(ksd.getUsername());
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_phone"))
				.sendKeys(ksd.getPhone());

		if (ksd.getIdentitytype() == 1) {
			// 点击身份证

			// 证件号码 *****
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_id_code"))
					.sendKeys(ksd.getIdentitynum());
		} else if (ksd.getIdentitytype() == 2) {
			// 点击军官证
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_id_type"))
					.click();// 点身份证
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElements(By.id("com.kuaishoudan.mgccar:id/text_select"))
					.get(1).click();// 点军官证
			// 证件号码 *****
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_id_code"))
					.sendKeys(ksd.getJgid());
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_id_address"))
				.sendKeys(ksd.getAddress()); // 地址
	}

	public static void mgcGCXX(AndroidDriver<WebElement> driver, KSDCase ksd) {

		int ran = ksd.getCartype();
		try {
			Thread.sleep(100);

			AppUtil.swipeToUpJj(driver, 1000);// 向上滑动

			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.openqa.selenium.WebDriverException e) {
			AppUtil.swipeToUpJj(driver, 1000);// 向上滑动
		}
		if (ran == 1) {
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_car_type"))
					.click();// 新车或二手车
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/check_old_car")).get(1)
					.click();
			// 二手车
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_brand"))
				.click();

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_brand"))
				.click();
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_series"))
				.click();// 车系
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_price"))
				.sendKeys("" + ksd.getCarprice());// 开票价

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_loan"))
				.sendKeys("" + ksd.getSqdk());// 预估贷款额
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_periods"))
				.click();

		driver.findElements(By.id("com.kuaishoudan.mgccar:id/text_select"))
				.get(ksd.getHkqs()).click();// 还款期数
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_remark"))
				.sendKeys(ksd.getRemark());// 备注.
		int gq = ksd.getQygr();
		if (gq == 2) {
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/cb_main"))
					.click();// 企业进件
			AppUtil.df(driver,
					By.id("com.kuaishoudan.mgccar:id/edit_company_name"))
					.sendKeys(ksd.getBusinessname());// 企业名称.
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/tv_company_no"))
					.sendKeys(ksd.getBusinessid());// 营业执照.
		}
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/btn_next")).click();// 下一步

	}
	/**
	 *进件购车信息 ( 未完成)，新
	 * 
	 */
	public static  void  mgcGCXX2(AndroidDriver<WebElement> driver, KSDCase ksd){
		


		int ran = ksd.getCartype();
		try {
			Thread.sleep(100);

			AppUtil.swipeToUpJj(driver, 1000);// 向上滑动

			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.openqa.selenium.WebDriverException e) {
			AppUtil.swipeToUpJj(driver, 1000);// 向上滑动
		}

		/*if (ran == 1) {
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_car_type"))
					.click();// 新车或二手车
			driver.findElements(
					By.id("com.kuaishoudan.financer:id/check_old_car")).get(1)
					.click();
			// 二手车
		}*/
		//品牌车系
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_brand"))
				.click();

		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_brand"))
				.click();
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_series"))
				.click();// 车系
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//开票价
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_price"))
				.sendKeys("" + ksd.getCarprice());
		//申请贷款
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_loan"))
				.sendKeys("" + ksd.getSqdk());
		
		//融资期限
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/text_periods"))
				.click();
	 
		driver.findElements(By.id("com.kuaishoudan.mgccar:id/text_select"))
				.get(ksd.getHkqs()).click();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 费率
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_remark"))
				.sendKeys(ksd.getRemark());
		
		// 备注
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_remark"))
				.sendKeys(ksd.getRemark());
		
		//个人企业进件
		int gq = ksd.getQygr();
		if (gq == 2) {
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/cb_main"))
					.click();// 企业进件
			AppUtil.df(driver,
					By.id("com.kuaishoudan.mgccar:id/edit_company_name"))
					.sendKeys(ksd.getBusinessname());// 企业名称.
			AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/tv_company_no"))
					.sendKeys(ksd.getBusinessid());// 营业执照.
		}
		
		// 下一步
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/btn_next")).click();
		
	}
	/**
	 * app请款(未完成)
	 */
	public static  void  mgcQk(AndroidDriver<WebElement> driver, KSDCase ksd){
		//账户名
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_remark"))
		.sendKeys(ksd.getRemark());
		//账户 
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_remark"))
		.sendKeys(ksd.getRemark());
		//车价贷款额
		AppUtil.df(driver, By.id(""))
		.sendKeys(ksd.getRemark());
		//GPS费
		AppUtil.df(driver, By.id(""))
		.sendKeys("0");
	

		//保险费
		if (Double.parseDouble(ksd.getInsurance()) == 0) {
			AppUtil.df(driver, By.id("")).sendKeys("0");// 保险费
		}
		//购置税
		
		if (Double.parseDouble(ksd.getPurchase_tax()) == 0) {
			AppUtil.df(driver, By.id("")).sendKeys("0");// 购置税
		}	
		
		//上牌抵押地
		AppUtil.df(driver, By.id("")).click();	
		//备注
		AppUtil.df(driver, By.id("com.kuaishoudan.mgccar:id/edit_remark"))
		.sendKeys(ksd.getRemark());	
		
	}
	/**
	 * 上传照片
	 * 
	 * @param driver
	 * @return
	 */
	public static String upload(final AndroidDriver<WebElement> driver,
			KSDCase ksd) throws org.openqa.selenium.TimeoutException {
		String acstatue = "";
		int imgcount = ksd.getImgcount();
		// imgcount=1;
		int count1 = imgcount / 20;
		int count2 = imgcount % 20;
		try {

			for (int j = 0; j < count1; j++) {// 20

				if (j > 0) {

					AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/btn_add"))
							.click();// 上传照片;

				}

				AppUtil.df(
						driver,
						By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
						.click();
				// 从相册选择
				List<WebElement> imgs =	AppUtil.dfs(driver,
						By.id("com.kuaishoudan.financer:id/iv_thumb"));
				for (int i = 0; i < 12; i++) {
					if (i == 0 && j > 0) {
						for (int k = 0; k < (j * 20); k++)

							imgs.get(i).click();// 添加图片（驾驶证）

					} else if (i == 0 && j == 0) {

					} else {

						imgs.get(i).click();// 添加图片（驾驶证）
					}

					imgs.get(i).click();// 添加图片（驾驶证）
				}
				AppUtil.swipeToUp3(driver, 800);// 向上滑动
				Thread.sleep(200);
				List<WebElement> ivts = AppUtil.dfs(driver,
						By.id("com.kuaishoudan.financer:id/iv_check"));

				List<WebElement> imgsss = AppUtil.dfs(driver,
						By.id("com.kuaishoudan.financer:id/iv_thumb"));
				System.out.println(ivts.size() + ",," + imgsss.size());
				int ccc = imgsss.size() - ivts.size();
				int m = 0;
				for (int i = 0; i < ivts.size(); i++) {
					if (m == 8) {
						break;
					}
					if (ivts.get(i).getAttribute("selected").equals("false")) {
						imgsss.get(i + ccc).click();
						// 添加图片（驾驶证）

						imgsss.get(i + ccc).click();// 添加图片（驾驶证）
						m++;

					}
				}
				AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/btn_ok")).click();
				// 两种证上传——确定按钮

				AppUtil.swipeToUp2(driver, 1000);// 向上滑动
				Thread.sleep(8500);

			}

			if (count2 == 0 && count1 > 0) {

			} else {

				if (count1 != 0) {
					AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/btn_add"))
							.click();// 上传照片;

				}
				AppUtil.df(
						driver,
						By.id("com.kuaishoudan.financer:id/dialog_photo_select_btn_gallery"))
						.click();
				// 从相册选择
				
				Thread.sleep(200);
				List<WebElement> imgs = AppUtil.dfs(driver,
						By.id("com.kuaishoudan.financer:id/iv_thumb"));
				if (count2 == 0 && count1 == 0) {

					imgs.get(0).click();// 添加图片（驾驶证）
					// 添加图片（驾驶证）
				} else if (count2 > 0 && count2 <= 12) {

					for (int i = 0; i < count2; i++) {
						if (i == 0) {
							for (int k = 0; k < (count1 * 20); k++)

								imgs.get(i).click();// 添加图片（驾驶证）

						} else {

							imgs.get(i).click();// 添加图片（驾驶证）
						}

						imgs.get(i).click();// 添加图片（驾驶证）
					}
				} else if (count2 > 12 && count2 < 21) {

					for (int i = 0; i < 12; i++) {
						if (i != 0)

							imgs.get(i).click();// 添加图片（驾驶证）

						imgs.get(i).click();// 添加图片（驾驶证）
					}
					AppUtil.swipeToUp3(driver, 800);// 向上滑动
					Thread.sleep(200);
					List<WebElement> ivts = AppUtil.dfs(driver,
							By.id("com.kuaishoudan.financer:id/iv_check"));

					List<WebElement> imgsss = AppUtil.dfs(driver,
							By.id("com.kuaishoudan.financer:id/iv_thumb"));
					// System.out.println(ivts.size()+",,"+imgsss.size());
					int ccc = imgsss.size() - ivts.size();
					int n = 0;
					for (int i = 0; i < ivts.size(); i++) {
						if (n == (count2 - 12)) {
							break;
						}
						if (ivts.get(i).getAttribute("selected")
								.equals("false")) {

							imgsss.get(i + ccc).click();
							// 添加图片（驾驶证）

							imgsss.get(i + ccc).click();// 添加图片（驾驶证）
							n++;
						}
					}

				}

				//
				AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/btn_ok")).click();
				// 两种证上传——确定按钮

				Thread.sleep(count2 * 400);

			}
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
					.click();
			// 上传完照片-确认按钮
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();
			// 提醒确定是

			Thread.sleep(1200);

			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.openqa.selenium.TimeoutException e) {
			// System.out.println(e);
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			// df(driver,By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮
			// System.out.println(ex);
			/*
			 * String title=
			 * driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title"
			 * )).getText().trim(); if (title.equals("拍摄资料")) {
			 */
			for (int j = 0; j < 5; j++) {
				// System.out.println("@@@@@@@@@@@");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	int usestatue = UserDaoImpl.getFinanStatue_id(ksd);

			  	if (usestatue == 1) {
			/*	int ss= driver.findElements(By.id("com.kuaishoudan.financer:id/toolbar_confirm")).size();
				if (ss==1) {*/
					driver.findElement(
							By.id("com.kuaishoudan.financer:id/toolbar_confirm"))
							.click();
					// 上传完照片-确认按钮
					int size = driver
							.findElements(
									By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
							.size();
					// System.out.println("size"+size);
					if (size == 1) {
						driver.findElement(
								By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
								.click();
						// 提醒确定是
					}

				} else {

					break;
				}
				// }
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (org.openqa.selenium.WebDriverException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		int countR = driver.findElements(
				By.className("android.widget.RelativeLayout")).size();
		// System.out.println("--==="+countR);
		if (countR == 8) {

			AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/tv_guide_know"))
					.click();// 我知道了
			AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/tv_guide_know"))
					.click();// 我知道了

		}
		AppUtil.df(driver, By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回按钮
		return acstatue;
	}
}
