package com.kuaishoudan.financer.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class AppSPUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 申请合同**
	public static void testSQHT(AppiumDriver<AndroidElement> driver) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_name"))
				.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_product"))
				.get(0).click();// 常规产品列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/tv_apply_compact")).click();// 申请合同
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/check_group"))
				.get(2).click();// 不安装 选择GPS安装方式
		AppUtil.swipeToUp(driver, 1000);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.findElement(By.id("com.kuaishoudan.financer.test:id/btn_add")).click();//添加照片
		upload(driver);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/toolbar_submit"))
				.click();// 提交按钮
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/dialog_custom_confirm"))
				.click();// 是按钮
	}

	// (申请合同)-申请请款
	public  static void testHTSQQK(AppiumDriver<AndroidElement> driver) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_name"))
				.get(0).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_product"))
				.get(0).click();// 常规产品列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/btn_apply_request")).click();// 申请请款
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/text_request_pay_name"))
				.click();
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/iv_is_show"))
				.click();// xia
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/ll_chekuan_shangpaidiya"))
				.click();// 上牌抵押地
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/options3"))
				.click();// 城市
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 2 / 3, height - 80, width * 2 / 3, height - 280,
				800);
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/btnSubmit"))
				.click();// 城市确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/tv_chekuan_shangpaifang"))
				.click();// 上牌方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_select"))
				.get(0).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/tv_chekuan_diyafang"))
				.click();// 抵押方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_select"))
				.get(0).click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

/*		driver.findElement(By.id("com.kuaishoudan.financer.test:id/iv_check"))
				.click();// 勾选
*/
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/tv_toolbar_confirm"))
				.click();// 确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/tv_confirm"))
				.click();// 申请请款确定

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/tv_countdown"))
				.click();
	}

	// 不出合同申请请款
	public void testSQQK(AppiumDriver<AndroidElement> driver) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_name"))
				.get(1).click();// 首页列表
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		/*
		 * driver.findElement(By.id(
		 * "com.kuaishoudan.financer.test:id/tv_not_apply_compact")).click();//不出合同
		 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 * driver
		 * .findElement(By.id("com.kuaishoudan.financer.test:id/check_group")).click
		 * ();//不安装
		 * driver.findElement(By.id("com.kuaishoudan.financer.test:id/toolbar_submit"
		 * )).click();//提交 driver.manage().timeouts().implicitlyWait(5,
		 * TimeUnit.SECONDS); driver.findElement(By.id(
		 * "com.kuaishoudan.financer.test:id/dialog_custom_confirm")).click();//是按钮
		 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 */
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/btn_apply_request")).click();// 申请请款445整数进位

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/text_request_pay_name"))
				.click();// dian
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/iv_is_show"))
				.click();// xia
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/et_chekuan_chejia"))
				.sendKeys("AAAAASSSSSFFFFFEE");// 车架号
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/ll_chekuan_shangpaidiya"))
				.click();// 上牌抵押地
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/options3"))
				.click();// 城市
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width * 2 / 3, height - 80, width * 2 / 3, height - 280,
				800);
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/btnSubmit"))
				.click();// 城市确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/tv_chekuan_shangpaifang"))
				.click();// 上牌方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_select"))
				.get(0).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/tv_chekuan_diyafang"))
				.click();// 抵押方
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElements(By.id("com.kuaishoudan.financer.test:id/text_select"))
				.get(0).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/iv_check"))
				.click();// 勾选
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(
				By.id("com.kuaishoudan.financer.test:id/tv_toolbar_confirm"))
				.click();// 确定
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/tv_confirm"))
				.click();// 申请请款确定

		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("com.kuaishoudan.financer.test:id/tv_countdown"))
				.click();// 倒计时确认
	}
	/**
	 * 上传照片
	 * 申请合同
	 * @param driver
	 * @return
	 */
	public static String upload(AppiumDriver<AndroidElement> driver) {
		String acstatue = "";
		try {
			/*
			 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 * driver.findElement(By.id("com.kuaishoudan.financer.test:id/btn_add"))
			 * .click();// 上传照片
			 */
			driver.findElement(
					By.id("com.kuaishoudan.financer.test:id/dialog_photo_select_btn_gallery"))
					.click();// 从相册选择
			driver.findElements(By.id("com.kuaishoudan.financer.test:id/iv_thumb"))
					.get(0).click();// 添加图片（身份证）
			driver.findElements(By.id("com.kuaishoudan.financer.test:id/iv_thumb"))
					.get(1).click();// 添加图片（驾驶证）
			driver.findElements(By.id("com.kuaishoudan.financer.test:id/iv_thumb"))
					.get(1).click();// 添加图片（驾驶证）
			driver.findElements(By.id("com.kuaishoudan.financer.test:id/iv_thumb"))
			.get(2).click();// 添加图片（驾驶证）
	driver.findElements(By.id("com.kuaishoudan.financer.test:id/iv_thumb"))
			.get(2).click();// 添加图片（驾驶证）
	driver.findElements(By.id("com.kuaishoudan.financer.test:id/iv_thumb"))
	.get(3).click();// 添加图片（驾驶证）
	driver.findElements(By.id("com.kuaishoudan.financer.test:id/iv_thumb"))
	.get(3).click();// 添加图片（驾驶证）


			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//

			Thread.sleep(1000);
			driver.findElement(By.id("com.kuaishoudan.financer.test:id/btn_ok"))
			.click();// 两种证上传——确定按钮
			Thread.sleep(8000);
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer.test:id/toolbar_submit"))
					.click();// 上传完-提交按钮
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer.test:id/dialog_custom_confirm"))
					.click();// 提醒确定是

			// driver.findElement(By.id("com.kuaishoudan.financer.test:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer.test:id/tv_guide_know")).click();//我知道了
			// driver.findElement(By.id("com.kuaishoudan.financer.test:id/toolbar_back")).click();//返回按钮
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			acstatue = driver
					.findElement(
							By.id("com.kuaishoudan.financer.test:id/item_status"))
					.getText().trim();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (org.openqa.selenium.WebDriverException e) {
			System.out.println(e);
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer.test:id/toolbar_back")).click();// 返回
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer.test:id/dialog_custom_confirm"))
					.click();// 是
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			driver.findElement(
					By.id("com.kuaishoudan.financer.test:id/toolbar_back")).click();// 从客户页面返回
		}

		try {
			driver.findElement(
					By.id("com.kuaishoudan.financer.test:id/toolbar_back")).click();// 返回按钮
			/*
			 * driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
			 * driver.findElement(
			 * By.id("com.kuaishoudan.financer.test:id/toolbar_back")).click();//
			 * 返回按钮
			 */} catch (org.openqa.selenium.WebDriverException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return acstatue;
	}

}
