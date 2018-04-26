package com.kuaishoudan.financer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebSPUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.selenium.ZcjjUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class ControlTest {
	public AppiumDriver<AndroidElement> driver;
	String devicename = "";
	public WebDriver webdriver;
	KSDCase ksd = null;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ControlTest ct = new ControlTest();

		System.out.println("***@");
		ct.setUp();// app启动
		ct.setUp2();// web启动

		for (int i = 0; i < 1; i++) {

			ct.dfp();// 待分配app
			ct.webDksp();// 已录入到申请合同

			ct.appSqht();// App申请合同

			ct.webSpht();// web审核

			ct.appSqqk();// app请款

			ct.sp1();
			ct.sp2();
			ct.sp3();

			ct.back();
		}
		/*
		 * ct.sp4(); ct.sp5(); ct.sp6();
		 */
		ct.tearDown();
		// ct.back()
	}

	public void printparam() {

	}

	public void back() {
		AppUtil.goback1(driver);
	}

	public void setUp() throws IOException, InterruptedException {
		driver = AppUtil.getdriver();

		Process process = Runtime.getRuntime().exec("adb devices");
		process.waitFor();
		InputStreamReader isr = new InputStreamReader(process.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		br.readLine();
		devicename = br.readLine().replaceAll("device", "").trim();
		System.out.println(devicename);
		Thread.sleep(3000);
		// AppUtil.login(driver,devicename, "liuhl@jizhicar.com");//登录
		// AppSPUtil.loginBD(driver, "konglx@jizhicar.com");
		// AppUtil.logout(driver);
	}

	// web
	public void setUp2() throws IOException, InterruptedException {
		webdriver = WebUtil.getdriver();

	}

	public void loginWeb(String username) {
		// String username="liuhl@jizhicar.com";

		WebUtil.login(webdriver, username);// 登录
	}

	public void logoutWeb() {
		// String username="liuhl@jizhicar.com";

		WebUtil.logout(webdriver);// 登出
	}

	/**
	 * 创建用户，进件，待审批
	 */
	public void dfp() {
		ksd = AppUtil.addTest(driver, devicename, 1);

		System.out.println("##==dfp" + ksd.getStatue());
	}

	/**
	 * web
	 */
	public void webDksp() {
		WebUtil.login(webdriver, "liuhl@jizhicar.com");// 登录
		WebUtil.testDFP(webdriver);// 待分配
		WebUtil.testYFP(webdriver);// 已分配
		WebUtil.testYLR(webdriver, ksd);// 已录入
		WebUtil.logout(webdriver);// 登出

	}

	// App申请合同
	public void appSqht() {

		ksd = AppSPUtil.testSQHT(driver, ksd);

		System.out.println("##===" + ksd.getStatue());

	}

	// web审批合同
	public void webSpht() {
		WebUtil.login(webdriver, "liuhl@jizhicar.com");// 登录
		WebUtil.testYSQHT(webdriver, ksd);// 申请合同审批
		WebUtil.logout(webdriver);// 登出
		System.out.println("##===" + AppSPUtil.getActstatue(driver));

	}

	// 申请请款
	public void appSqqk() {
		AppSPUtil.testHTSQQK(driver, ksd);// 请款
		// System.out.println(AppUtil.getStatue(driver));
		// AppUtil.look_status(driver);//查看进度
		System.out.println("##===sqqk" + AppSPUtil.getActstatue(driver));
	}

	// 请款审批同意专员
	public void sp1() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP1(webdriver, email, itename); // 请款审批同意专员
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//
	public void sp2() {
		try {
			Map<String, String> map = AppSPUtil.getSPname(driver);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));

			WebSPUtil.testSP2(webdriver, email, itename); // 请款审批同意专员

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 333
	public void sp3() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver);// 从app获取审批人名字
			if (map.size() == 1) {
				// bd操作

				String email = WebSPUtil.nameToemail(map.get("name"));
				AppSPUtil.loginBD(driver, email);
				AppUtil.login(driver, devicename, "liuhl@jizhicar.com");// 登录
				Thread.sleep(1000);
				Map<String, String> map2 = AppSPUtil.getSPname(driver);// 从app获取审批人名字
				String itename2 = map2.get("prename");
				String email2 = WebSPUtil.nameToemail(map2.get("name"));
				WebSPUtil.testSP3(webdriver, email2, itename2); // 请款审批同意专员
			} else {
				String itename = map.get("prename");
				String email = WebSPUtil.nameToemail(map.get("name"));
				WebSPUtil.testSP3(webdriver, email, itename); // 请款审批同意专员
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sp4() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP4(webdriver, email, itename); // 请款审批同意专员
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 状态已放款
	public void sp5() {
		try {

			Map<String, String> map = AppSPUtil.getSPname(driver);// 从app获取审批人名字
			String itename = map.get("prename");
			String email = WebSPUtil.nameToemail(map.get("name"));
			WebSPUtil.testSP5(webdriver, email, itename); // 请款审批同意专员
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 归档
	public void sp6() {

		WebSPUtil.testSP6(webdriver, "liuhl@jizhicar.com", "刘浩亮"); // 请款审批同意专员
	}

	public void tearDown() throws Exception {

		driver.quit();
		webdriver.quit();
	}
}
