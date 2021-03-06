package com.kuaishoudan.financer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.Employee;
import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.flow.TestA;
import com.kuaishoudan.financer.flow.TestB;
import com.kuaishoudan.financer.flow.TestC;
import com.kuaishoudan.financer.flow.TestD;
import com.kuaishoudan.financer.flow.TestE;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebSPUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
 
import com.kuaishoudan.financer.util.DBUtil;
import com.kuaishoudan.financer.util.RandomValue;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestUser100 {
	public AndroidDriver<WebElement> driver;
	String devicename = "";
	public WebDriver webdriver;
	static KSDCase ksd = null;
	  List<Employee> employes = null;
	String itename = "";
	String flow="";
	/**
	 *  不出合同-审批流1
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TestUser100 ct = new TestUser100();
		 ksd = RandomValue.getRandom();
		Properties properties = new Properties();
		try {
        	InputStreamReader in=new InputStreamReader(WebUtil.class.getResourceAsStream("ksd.properties"), "UTF-8");
        	properties.load(in);
        	ksd.setLoginname(properties.getProperty("login_name"));
        	ksd.setLoginemail(properties.getProperty("login_email")) ;
        	ksd.setPwd(properties.getProperty("login_password"));
        	ksd.setFlow(properties.getProperty("flow"));
        	ksd.setSp_password(properties.getProperty("sp_password"));
        	ksd.setSssh_id(Integer.parseInt(properties.getProperty("supplier")));
        	ksd.setSssh_account(Integer.parseInt(properties.getProperty("supp_account")));
        	String cartype=properties.getProperty("cartype");
        	if(!cartype.equals("")){
        		System.out.println("--------------------");
        		ksd.setCartype(Integer.parseInt(cartype));
        	}
        	ksd.setInit_statue(Integer.parseInt(properties.getProperty("init_statue")));
        } catch (IOException e) {
            e.printStackTrace();
        }
		ct.setUp2();// web启动
	//	ct.setUp();// app启动
		int count = ct.getCount();
		ksd.setSssh("南郊2区");//南郊2区  北郊庭区1
		ksd.setUsername("娄新");
		ksd.setLoginemail("xuefl@jizhicar.com");
		for (int i = 0; i < count; i++) {
		//	ct.dfp();// 待分配app
			long startTime = System.currentTimeMillis();    //获取开始时间
			switch (ksd.getInit_statue()) {
			case 1:
				ct.webYfp();// 已分配
				break;
			case 2:
				ct.webYlr();// 已录入
				break;
			case 3:
				ct.webDksp();// 已通过
				break;
			case 4:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				break;
			case 5:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				break;
			case 6:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				ct.sp2();
				break;
			case 7:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				ct.sp2();
				ct.sp3();
				break;
			case 8:
		//		ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
			/*	ct.sp1();
				ct.sp2();
				ct.sp3();*/
				ct.sp4();
				break;
			case 9:
			//	ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
			/*	ct.sp1();
				ct.sp2();
				ct.sp3();
				ct.sp4();*/
				ct.sp5();
				break;
			case 10:
				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				ct.sp2();
				ct.sp3();
				ct.sp4();
				ct.sp5();
				ct.sp6();
				break;
			case 11:
/*				ct.webDksp();// 已录
				ct.appBsqht();// App不申请合同-申请请款
				ct.sp1();
				ct.sp2();
				ct.sp3();
				ct.sp4();
				ct.sp5();
				ct.sp6();*/
			//	ct.sp7();
				ct.ff();
				break;
			default:
			}
			long endTime = System.currentTimeMillis();    //获取结束时间

			System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间

		}

		ct.tearDown();

	}
	public void ff(){
		WebSPUtil.zxSp1(webdriver, ksd,"xuefl@jizhicar.com");
	}
	public void back() {
		AppUtil.goBack1(driver);
	}

	public void setUp() throws IOException, InterruptedException {
		driver = AppUtil.getDriver();

	}

	// web
	public void setUp2() throws IOException, InterruptedException {
		webdriver = WebUtil.getDriver();
	//	webdriver = WebUtil2.getDriver2();
 
	}

	public void loginWeb(String username) {

		WebUtil.login(webdriver, ksd);// 登录
	}

	public void logoutWeb() {

		WebUtil.logout(webdriver);// 登出
	}
 
	public int getCount(){
		return WebUtil.getCount();
	}
	/**
	 * 创建用户，进件，待审批
	 */
	public void dfp() {
		ksd = AppUtil.addTest(driver, webdriver, devicename, 1);

	}

	
	/**
	 * web
	 */
	public void webYfp() {
		WebUtil.login(webdriver, ksd);// 登录
		WebUtil.testDFP(webdriver, ksd);// 待分配
		WebUtil.logout(webdriver);// 登出

	}
	public void webYlr() {
		WebUtil.login(webdriver, ksd);// 登录
		WebUtil.testYFP(webdriver, ksd);// 待分配
		WebUtil.logout(webdriver);// 登出

	}

	/**
	 * web
	 */
	public void webDksp() {
		WebUtil.login(webdriver, ksd);// 登录
		WebUtil.testDFP(webdriver, ksd);// 待分配
		WebUtil.testYFP(webdriver, ksd);// 已分配
		WebUtil.testYLR(webdriver, ksd);// 已录入
		WebUtil.logout(webdriver);// 登出

	}
	// App不申请合同
	public void appBsqht() {
		  flow = ksd.getFlow();
		switch (flow) {
		case "A":
			ksd = TestA.testBCSQQK(driver, webdriver, ksd, devicename);
			employes = UserDaoImpl.getSpNameid(ksd, 1);
			break;
		case "B":
	//		ksd = TestB.testBCSQQK(driver, webdriver, ksd, devicename);
			employes = UserDaoImpl.getSpNameid(ksd, 1);
			break;
		case "C":
			ksd = TestC.testBCSQQK(driver, webdriver, ksd, devicename);
			employes = UserDaoImpl.getSpNameid(ksd, 3);
			
			break;
		case "D":
			ksd = TestD.testBCSQQK(driver, webdriver, ksd, devicename);
			employes = UserDaoImpl.getSpNameid(ksd, 3);
			break;
		case "E":
			ksd = TestE.testBCSQQK(driver, webdriver, ksd, devicename);
			employes = UserDaoImpl.getSpNameid(ksd, 3);
			break;
		default:
			System.out.println("default");
		
		}

	}

	// 请款审批同意专员
	public void sp1() {
		itename = ksd.getLoginname();
		if("A".equals(flow)||"B".equals(flow)){
		for (Employee ep : employes) {
			if (ep.getDesc().equals("客服专员")) {

				WebSPUtil.testSP1(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				itename = ep.getUsername();
				break;
			}
			if (ep.getDesc().equals("请款审核专员")) {

				WebSPUtil.testSP1(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				itename = ep.getUsername();
				break;
			}
		}
		}else{
			WebSPUtil.testSP1(webdriver, ksd.getLoginemail(), itename, ksd); // 请款审批同意专员
			
		}
	}

	//
	public void sp2() {
 
		for (Employee ep : employes) {

			if (ep.getDesc().equals("数据运营")) {
				switch (flow) {
				case "A":
				WebSPUtil.testSP2(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				break;
				case "B":
				WebSPUtil.testSP2(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				break;
				case "C":
				TestC.testSP2(webdriver,ep.getAccount(), itename, ksd);			
				break;
				case "D":
				TestD.testSP2(webdriver,ep.getAccount(), itename, ksd);				
				break;
				case "E":
				TestE.testSP2(webdriver, ep.getAccount(), itename, ksd);
				break;
				default:
					System.out.println("default");
							
				}
				itename = ep.getUsername();
				break;
			}
		}
		
		 
	}

	// 333
	public void sp3() {
		for (Employee ep : employes) {

			if (ep.getDesc().equals("BD经理")) {
				AppSPUtil.loginBD(driver, ep.getAccount(), ksd);
				AppUtil.login(driver, devicename, ksd);// 登录
				itename = ep.getUsername();
				break;
			}

		}
		for (Employee ep : employes) {
			if (ep.getDesc().equals("请款审核组长")) {

				WebSPUtil.testSP3(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				itename = ep.getUsername();
				break;
			}
		}

	}

	public void sp4() {
		for (Employee ep : employes) {
			if (ep.getDesc().equals("财务专员")) {

				WebSPUtil.testSP4(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				itename = ep.getUsername();
				break;
			}
		}

	}

	// 状态已放款
	public void sp5() {
		for (Employee ep : employes) {
			if (ep.getDesc().equals("财务专员")) {

				WebSPUtil.testSP5(webdriver, ep.getAccount(), itename, ksd); // 请款审批同意专员
				itename = ep.getUsername();
				break;
			}
		}

	}

	// 归档--通知下发材料

	public void sp6() {

		// ksd = WebSPUtil.testSP6(webdriver, ksd); // 请款审批同意专员
		AppSPUtil.sp6App(driver,webdriver, ksd);

	}

	// 归档

	public void sp7() {

		ksd = WebSPUtil.testSP7(webdriver, ksd); // 请款审批同意专员

	}

	public void tearDown() throws Exception {

	//	driver.quit();
		webdriver.quit();
	}
}
