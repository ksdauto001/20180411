package com.kuaishoudan.financer.selenium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.kuaishoudan.financer.bean.KSDCase;
import com.kuaishoudan.financer.dao.UserDaoImpl;
import com.kuaishoudan.financer.util.IdCardGenerator;

public class AppSPUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 申请合同**
	public static KSDCase testSQHT(AppiumDriver<AndroidElement> driver,
			KSDCase ksd) {
		String actualstatue = "";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * driver.findElements(By.id("com.kuaishoudan.financer:id/text_product"))
		 * .get(0).click();// 常规产品列表 }
		 */
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_apply_compact")).click();// 申请合同
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		int gxs = driver.findElements(
				By.id("com.kuaishoudan.financer:id/check_group")).size();// 勾选数
		System.out.println("gxs" + gxs);
		driver.findElements(By.id("com.kuaishoudan.financer:id/check_group"))
				.get(gxs - 1).click();// 不安装 选择GPS安装方式
		AppUtil.swipeToUp(driver, 1000);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_add"))
				.click();// 添加照片
		upload(driver);
	
		//		Thread.sleep(500);
	//	getActstatue(driver);// 状态值
		String statue=getActstatue(driver);
		ksd.setStatue(statue);
		return ksd;
	}

	// (申请合同)-申请请款
	public static KSDCase testHTSQQK(AppiumDriver<AndroidElement> driver,WebDriver webdriver,
			KSDCase ksd) {
		String actualstatue = "";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		WebUtil.login(webdriver, ksd.getLoginemail());// 登录
		List<Integer> list = WebOrgan.getImge2(webdriver, ksd);
		WebUtil.logout(webdriver);
		int aa=0,countImg=0;
		for(int i=0;i<list.size();i++){
			if(list.get(i)<9){
				aa=UserDaoImpl.getImgType(list.get(i)+7,list);
				countImg=aa+countImg;
			}
		}
		
		System.out.println("$$$"+countImg);
		ksd.setImgcount(countImg);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
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
				.get(ksd.getRegisttype() - 1).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();// 抵押方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getPledge() - 1).click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		/*
		 * driver.findElement(By.id("com.kuaishoudan.financer:id/iv_check"))
		 * .click();// 勾选
		 */

		AppUtil.swipeToUp(driver, 1000);// 向上滑动
		
	
		
		AppUtil.uploadQk(driver,ksd.getImgcount());
		
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
				.click();// 信息确认按钮
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actualstatue = AppUtil.getStatue(driver);
		ksd.setStatue(actualstatue);
		return ksd;
	}

	// 不出合同申请请款
	public static KSDCase testBCSQQK(AppiumDriver<AndroidElement> driver,WebDriver webdriver,
			KSDCase ksd) {
		WebUtil.login(webdriver, ksd.getLoginemail());// 登录
		List<Integer> list = WebOrgan.getImge2(webdriver, ksd);
		WebUtil.logout(webdriver);
		int aa=0,countImg=0;
		for(int i=0;i<list.size();i++){
			if(list.get(i)<9){
				aa=UserDaoImpl.getImgType(list.get(i)+7,list);
				countImg=aa+countImg;
			}
		}
		
		System.out.println("$$$"+countImg);
		ksd.setImgcount(countImg);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
				.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_not_apply_compact"))
				.click();// 不出合同
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		int gxs = driver.findElements(
				By.id("com.kuaishoudan.financer:id/check_group")).size();// 勾选数
		System.out.println("gxs" + gxs);
		driver.findElements(By.id("com.kuaishoudan.financer:id/check_group"))
				.get(gxs - 1).click();// 不安装 选择GPS安装方式
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_submit"))
				.click();// 提交
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 是按钮

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		/*
		 * driver.findElement(
		 * By.id("com.kuaishoudan.financer:id/btn_apply_request")).click();//
		 * 申请请款445整数进位
		 */
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/text_request_pay_name"))
				.click();// dian账号名
		driver.findElement(By.id("com.kuaishoudan.financer:id/iv_is_show"))
				.click();// xia下标
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/et_chekuan_chejia"))
				.sendKeys(ksd.getVin());// 车架号
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
				.get(ksd.getRegisttype() - 1).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/tv_chekuan_diyafang"))
				.click();// 抵押方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_select"))
				.get(ksd.getPledge() - 1).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		/*
		 * driver.findElement(By.id("com.kuaishoudan.financer:id/iv_check"))
		 * .click();// 勾选
		 */
		AppUtil.swipeToUp(driver, 1000);// 向上滑动		
		AppUtil.uploadQk(driver,ksd.getImgcount());
		
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
				.click();// 倒计时确认

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		String actualstatue = getActstatue(driver);
		ksd.setStatue(actualstatue);
		return ksd;
	}

	/**
	 * 上传照片 申请合同
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
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(2).click();// 添加图片（驾驶证）
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(2).click();// 添加图片（驾驶证）
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(3).click();// 添加图片（驾驶证）
			driver.findElements(By.id("com.kuaishoudan.financer:id/iv_thumb"))
					.get(3).click();// 添加图片（驾驶证）

			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//

			Thread.sleep(2000);
			driver.findElement(By.id("com.kuaishoudan.financer:id/btn_ok"))
					.click();// 两种证上传——确定按钮
			AppUtil.swipeToUp(driver, 1000);// 向上滑动
			Thread.sleep(15000);
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_submit"))
					.click();// 上传完-提交按钮
			driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
			Thread.sleep(1000);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
					.click();// 提醒确定是
			Thread.sleep(3000);
			// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回按钮

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
	
		} catch (org.openqa.selenium.WebDriverException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		
		return acstatue;
	}

	// BD经理登录审批
	public static boolean loginBD(AppiumDriver<AndroidElement> driver,
			String username) {
		boolean flag = false;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back"))
				.click();// 返回
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String titletext = driver
				.findElement(By.id("com.kuaishoudan.financer:id/toolbar_title"))
				.getText().trim();// 标题文本

		if ("贷款详情".equals(titletext)) {
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_back")).click();// 返回
		}
		AppUtil.logout(driver);// 退出登录
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_account"))
				.clear();
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_password"))
				.clear();
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_account"))
				.sendKeys(username);
		driver.findElement(By.id("com.kuaishoudan.financer:id/edit_password"))
				.sendKeys("@123456");
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_login"))
				.click();
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_menu"))
				.click();// 菜单

		driver.findElements(
				By.id("com.kuaishoudan.financer:id/design_menu_item_text"))
				.get(7).click();// 消息
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/message_body"))
				.get(0).click();// 点进同意贷款详情
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);

		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_check_agree"))
				.click();// 同意按钮
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_finish"))
				.click();// 确定按钮
		driver.manage().timeouts().implicitlyWait(18, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer:id/dialog_custom_confirm"))
				.click();// 提醒----确定按钮

		AppUtil.logout(driver);// 退出登录
		flag = true;
		return flag;

	}

	public static Map<String, String> getSPname(
			AppiumDriver<AndroidElement> driver) throws InterruptedException,
			IOException {
		Map<String, String> map = new HashMap<String, String>();
		;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String spname = "";
		String title = driver.findElement(
				By.id("com.kuaishoudan.financer:id/toolbar_title")).getText();
		if ("查看状态".equals(title)) {
			AppUtil.swipeToDown(driver, 1000);// 向下滑动
			System.out.println("下-----------" + title);

		} else {

			driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
					.get(0).click();// 首页列表

			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer:id/toolbar_loan_status"))
					.click();// 状态审批流程

			driver.findElement(
					By.id("com.kuaishoudan.financer:id/text_customer_look_status"))
					.click();// 查看进度
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
			System.out.println("@@" + statueitems.size());
			if ("正在处理".equals(statue)) {
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer:id/item_name"))
						.getText();
				System.out.println("!!!!"+name);
		/*		if(name.equals("")){
					name="测试工程师-刘浩亮";
				}*/
				String[] strs = name.split("-");
				if (strs[0].contains("BD")) {
					// ///////////////////////////////

					spname = strs[1];
					System.out.println("BD经理处理" + spname);
					map.put("name", spname);
					break;
				} else {
					String prename = statueitems
							.get(i + 1)
							.findElement(
									By.id("com.kuaishoudan.financer:id/item_name"))
							.getText();
					String[] strspre = prename.split("-");
					spname = strs[1] + "," + strspre[1];
					System.out.println("正在处理" + spname);
					map.put("name", strs[1]);
					map.put("prename", strspre[1]);
					break;
				}
			} else if ("放款审批/已放款".equals(statue)) {
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer:id/item_name"))
						.getText();
				String[] strs = name.split("-");
				spname = strs[1] + "," + "刘浩亮";
				map.put("name", strs[1]);
				map.put("prename", "刘浩亮");
			} else if ("回款结果/已回款".equals(statue)) {
				String name = statueitems
						.get(i)
						.findElement(
								By.id("com.kuaishoudan.financer:id/item_name"))
						.getText();
				String[] strs = name.split("-");
				spname = strs[1] + "," + "刘浩亮";
				map.put("name", strs[1]);
				map.put("prename", "刘浩亮");
			}
		}
		// return spname;
		return map;
	}

	// 状态实际值
	public static String getActstatue(AppiumDriver<AndroidElement> driver) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AppUtil.swipeToDown(driver, 1000);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actu = driver
				.findElement(
						By.id("com.kuaishoudan.financer:id/tv_laster_status"))
				.getText().trim();
		return actu;

	}
	public static void sp6App(AppiumDriver<AndroidElement> driver,KSDCase ksd){
		driver.findElements(By.id("com.kuaishoudan.financer:id/text_name"))
		.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/btn_archive")).click();//归档
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//确定
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer:id/check_group")).get(0).click();//当面交付
		AppUtil.uploadQk(driver,ksd.getImgcount());
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_submit")).click();//提交
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer:id/dialog_custom_confirm")).click();//是
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer:id/toolbar_back")).click();//返回
		
	}

}
