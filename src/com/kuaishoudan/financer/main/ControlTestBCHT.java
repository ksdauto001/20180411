package com.kuaishoudan.financer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kuaishoudan.financer.util.AppSPUtil;
import com.kuaishoudan.financer.util.AppUtil;
import com.kuaishoudan.financer.util.WebSPUtil;
import com.kuaishoudan.financer.util.WebUtil;
import com.kuaishoudan.financer.web.LoginWeb;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class ControlTestBCHT {
	public AppiumDriver<AndroidElement> driver;
	String devicename = "";
	public WebDriver webdriver;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ControlTestBCHT ct = new ControlTestBCHT();

		System.out.println("***@");
		ct.setUp();// app启动
		ct.setUp2();// web启动

		ct.dfp();// 待分配app

		ct.webDksp();// 已录

		ct.appBsqht();// App不申请合同-申请请款

		ct.sp1();
		ct.sp2();
		ct.sp3();

		/*
		 * ct.sp4(); ct.sp5(); ct.sp6();
		 */
		ct.tearDown();
		// ct.back()
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
		AppUtil.addTest(driver, devicename, 1);

	}

	/**
	 * web
	 */
	public void webDksp() {
		WebUtil.login(webdriver, "liuhl@jizhicar.com");// 登录
		WebUtil.testDFP(webdriver);// 待分配
		WebUtil.testYFP(webdriver);// 已分配
		WebUtil.testYLR(webdriver);// 已录入
		WebUtil.logout(webdriver);// 登出

	}

	// App不申请合同
	public void appBsqht() {

		AppSPUtil.testBCSQQK(driver);

	}

	// web审批合同
	public void webSpht() {
		WebUtil.login(webdriver, "liuhl@jizhicar.com");// 登录
		WebUtil.testYSQHT(webdriver);// 申请合同审批
		WebUtil.logout(webdriver);// 登出

	}

	// 申请请款
	public void appSqqk() {
		AppSPUtil.testHTSQQK(driver);// 请款
		// System.out.println(AppUtil.getStatue(driver));
		// AppUtil.look_status(driver);//查看进度

	}

	// 请款审批同意专员
	public void sp1() {
		try {

			String spname = AppSPUtil.getSPname(driver);// 从app获取审批人名字
			System.out.println("###" + spname);
			String[] strs = spname.split(",");
			String itename = strs[1];
			String email = WebSPUtil.nameToemail(strs[0]);
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
			String spname = AppSPUtil.getSPname(driver);// 从app获取审批人名字
			System.out.println("###" + spname);
			if (spname.indexOf(",") == -1) {
				// bd操作
				String email = WebSPUtil.nameToemail(spname);
				AppSPUtil.loginBD(driver, email);
				AppUtil.login(driver, devicename, "liuhl@jizhicar.com");// 登录
			} else {
				String[] strs = spname.split(",");
				String itename = strs[1];
				String email = WebSPUtil.nameToemail(strs[0]);
				WebSPUtil.testSP2(webdriver, email, itename); // 请款审批同意专员
			}
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

			String spname = AppSPUtil.getSPname(driver);// 从app获取审批人名字
			if (spname.indexOf(",") == -1) {
				// bd操作

				String email = WebSPUtil.nameToemail(spname);
				AppSPUtil.loginBD(driver, email);
				AppUtil.login(driver, devicename, "liuhl@jizhicar.com");// 登录
			} else {
				String[] strs = spname.split(",");
				String itename = strs[1];
				String email = WebSPUtil.nameToemail(strs[0]);
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

			String spname = AppSPUtil.getSPname(driver);// 从app获取审批人名字
			System.out.println("###" + spname);
			String[] strs = spname.split(",");
			String itename = strs[1];
			String email = WebSPUtil.nameToemail(strs[0]);
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

			String spname = AppSPUtil.getSPname(driver);// 从app获取审批人名字
			System.out.println("###" + spname);
			String[] strs = spname.split(",");
			String itename = strs[1];
			String email = WebSPUtil.nameToemail(strs[0]);
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
