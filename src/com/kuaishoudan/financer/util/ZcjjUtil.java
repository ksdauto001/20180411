package com.kuaishoudan.financer.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class ZcjjUtil {
	
	public static String sqhtZCJJ(AppiumDriver<AndroidElement> driver){
		String actualstatue="";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	/*	driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
*/		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElements(By.id("com.kuaishoudan.financer:id/text_product"))
				.get(0).click();// 常规产品列表
		
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_apply_compact")).click();// 申请合同
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		int gxs=driver.findElements(By.id("com.kuaishoudan.financer:id/check_group")).size();//勾选数
		System.out.println("gxs"+gxs);
		driver.findElements(By.id("com.kuaishoudan.financer:id/check_group"))
				.get(gxs-1).click();// 不安装 选择GPS安装方式
		AppUtil.swipeToUp(driver, 1000);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		 driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add")).click();//添加照片
		 actualstatue=AppSPUtil.upload(driver);
		return actualstatue;
	}
	public  static String zcjjHTSQQK(AppiumDriver<AndroidElement> driver) {
		String actualstatue="";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
/*		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
*/		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_product"))
				.get(0).click();// 常规产品列表
	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/btn_apply_request")).click();// 申请请款
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_request_pay_name"))
				.click();
		driver.findElement(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.click();// xia
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/ll_chekuan_shangpaidiya"))
				.click();// 上牌抵押地
		driver.findElement(By.id("com.kuaishoudan.financer:id/options3"))
				.click();// 城市
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 2 / 3, height - 80, width * 2 / 3, height - 280,
				800);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btnSubmit"))
				.click();// 城市确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_shangpaifang"))
				.click();// 上牌方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(0).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();// 抵押方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(0).click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

/*		driver.findElement(By.id("com.kuaishoudan.financer:id/iv_check"))
				.click();// 勾选
*/
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_toolbar_confirm"))
				.click();// 确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_confirm"))
				.click();// 申请请款确定

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/tv_countdown"))
				.click();//信息确认按钮
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	actualstatue=	AppUtil.getStatue(driver);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回
		return actualstatue;
	}
	public static String getSPname(AppiumDriver<AndroidElement> driver) throws InterruptedException,
	IOException {

driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
String spname = "";
String title=driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
if("查看状态".equals(title)){
	AppUtil.swipeToDown(driver, 1000);//向下滑动
	System.out.println("下-----------"+title);
	
}else{

/*driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
*/
	driver.findElements(By.id("com.kuaishoudan.financer:id/text_product"))
	.get(0).click();// 常规产品列表
driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
driver.findElement(
		By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
		.click();// 状态审批流程

driver.findElement(By.id("com.kuaishoudan.financer:id/text_customer_look_status")).click();//查看进度
}
Thread.sleep(1000);
List<AndroidElement> statueitems = driver.findElements(By
		.id("com.kuaishoudan.financer:id/ll_status"));
for (int i = 0; i < statueitems.size(); i++) {
	String statue = statueitems
			.get(i)
			.findElement(
					By.id("com.kuaishoudan.financer:id/item_status"))
			.getText();
	System.out.println("@@" + statueitems.size() );
	if ("正在处理".equals(statue)) {
		String name = statueitems
				.get(i)
				.findElement(
						By.id("com.kuaishoudan.financer:id/item_name"))
				.getText();	
		String[]  strs=name.split("-");
		if(strs[0].contains("BD")){
			/////////////////////////////////

			spname=strs[1];
			System.out.println("BD经理处理"+spname);
			break;
		}else{
		String prename = statueitems
				.get(i+1)
				.findElement(
						By.id("com.kuaishoudan.financer:id/item_name"))
				.getText();
		String[]  strspre=prename.split("-");
		spname=strs[1]+","+strspre[1];
		System.out.println("正在处理"+spname);

		break;
		}
	}else if("放款审批/已放款".equals(statue)){
		String name = statueitems
				.get(i)
				.findElement(
						By.id("com.kuaishoudan.financer:id/item_name"))
				.getText();	
		String[]  strs=name.split("-");
		spname=strs[1]+","+"刘浩亮";
	}else if("回款结果/已回款".equals(statue)){
		String name = statueitems
				.get(i)
				.findElement(
						By.id("com.kuaishoudan.financer:id/item_name"))
				.getText();	
		String[]  strs=name.split("-");
		spname=strs[1]+","+"刘浩亮";
	}
}
return spname;
}

}
