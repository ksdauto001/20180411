package com.kuaishoudan.financer.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.selenium.AppSPUtil;
import com.kuaishoudan.financer.selenium.AppUtil;
import com.kuaishoudan.financer.selenium.WebUtil;
import com.kuaishoudan.financer.util.CaseUtil;
import com.kuaishoudan.financer.util.RandomValue;

public class Test2 {

	public AppiumDriver<AndroidElement> driver = null;
	String devicename = "";
	public WebDriver webdriver = null;

	@BeforeTest
	public void setUp() throws Exception {

		webdriver = WebUtil.getdriver();
	}

	@AfterTest
	public void tearDown() throws Exception {

		webdriver.quit();
	}

	// 创建用户
	//@Test(invocationCount = 1, threadPoolSize = 1)
	public void test1() throws InterruptedException, IOException {

		System.out.println("***1@");
		  KSDCase ksd=RandomValue.getRandom();;
			 ksd.setIdentitynum("620921195909158870"); 
			  ksd.setIdentitytype(1);
			  ksd.setPhone("13301757675");
			  Map<String,String> map1=UserDaoImpl.getCustomer(ksd);
			  Map<String,String> map2=CaseUtil.getCustomer(ksd);
			/*  Map<String,String> map1=new HashMap<String,String>();
			  Map<String,String> map2=new HashMap<String,String>();
			  map1.put("name", "康娣");
			  map2.put("name", "康娣2");*/
			  Assert.assertEquals(map1, map2);
	}
	// 创建用户
		@Test(invocationCount = 1, threadPoolSize = 1)
		public void test2() throws InterruptedException, IOException {

			System.out.println("***1@");
			  KSDCase ksd=RandomValue.getRandom();;
				 ksd.setIdentitynum("620921195909158870"); 
				  ksd.setIdentitytype(1);
				  ksd.setStatue("10");
				  
				  ksd.setPhone("13301757675");
				  ksd.setUsername("康娣");
				  ksd.setCartype(1);
				  ksd.setCarbrand("奥迪");
				  ksd.setCarseries("奥迪Q9");
				  ksd.setProduct("中安金控-抵押贷");
				double a=16;
				;
				  ksd.setCarprice(Double.parseDouble(	new DecimalFormat("#.000").format(a)));
				  ksd.setSqdk(12);
				  ksd.setHkqs(12);
				  ksd.setSssh("北京京东瑞翔商贸有限公司（丰台分店）");
				  ksd.setRemark("remark");
				  ksd.setQygr(2);
				  ksd.setBusinessid("yy");
				  ksd.setBusinessname("ying");
					ksd.setVin("55566882585625772") ;
					String sss=new BigDecimal(Double.parseDouble(new DecimalFormat("#.00")
					.format(0)) + "").toString();
				ksd.setPurchase_tax(sss);
				ksd.setGps_charge(sss);
				ksd.setInsurance(sss);
			
				System.out.println("####"+sss);
				ksd.setService_charge(sss);
				ksd.setRate("19");
					
				  Map<String,String> map1=UserDaoImpl.getFinance(ksd);
				  Map<String,String> map2=CaseUtil.getFinance(ksd);
				/*  Map<String,String> map1=new HashMap<String,String>();
				  Map<String,String> map2=new HashMap<String,String>();
				  map1.put("name", "康娣");
				  map2.put("name", "康娣2");*/
				  Assert.assertEquals(map1, map2);
		}

	//	@Test(invocationCount = 1, threadPoolSize = 1)
		public void test3() throws InterruptedException, IOException {

			System.out.println("***1@");
			  KSDCase ksd=RandomValue.getRandom();;
				 ksd.setIdentitynum("620921195909158870"); 
				  ksd.setIdentitytype(1);
				  ksd.setStatue("2");
				  ksd.setPhone("13301757675");
				  ksd.setUsername("康娣");
				  ksd.setCartype(1);
				  ksd.setCarbrand("奥迪");
				  ksd.setCarseries("奥迪Q9");
				  ksd.setProduct("中安金控-抵押贷");
				  ksd.setSqdk(12);
				  ksd.setHkqs(12);
				  ksd.setSssh("北京京东瑞翔商贸有限公司（丰台分店）");
				  ksd.setRemark("remark");
				  ksd.setQygr(2);
				  ksd.setBusinessid("yy");
				  ksd.setBusinessname("ying");
					ksd.setVin("87KKGTT103B8S8JP1") ;
				ksd.setPurchase_tax("0.00");
				ksd.setGps_charge("0.00");
				ksd.setInsurance("0.00");
				ksd.setService_charge("0.00");
				ksd.setRate("19");
					
				  Map<String,String> map1=UserDaoImpl.getAdvance(ksd);
				  Map<String,String> map2=CaseUtil.getAdvance(ksd);
				/*  Map<String,String> map1=new HashMap<String,String>();
				  Map<String,String> map2=new HashMap<String,String>();
				  map1.put("name", "康娣");
				  map2.put("name", "康娣2");*/
				  Assert.assertEquals(map1, map2);
		}
}
