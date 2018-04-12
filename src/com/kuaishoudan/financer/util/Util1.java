package com.kuaishoudan.financer.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class Util1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
}
