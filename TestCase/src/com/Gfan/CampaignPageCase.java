package com.Gfan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.Assert;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class CampaignPageCase extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "CampaignPageCase";
		String testClass = "com.Gfan.CampaignPageCase";
		String testName = "testCase";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	public void testCase() throws UiObjectNotFoundException {
		testClickCampaign();
		testGetCaimpAPP();
		testClickCaimpApp();
		testAppContains();
		testSearchBtn();
	}

	/**
	 * 点击[活动]，进入活动页面
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testClickCampaign() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(170, 334);
		

	}

	/**
	 * 获取活动页面的app
	 * 获取活动页面某个活动的元素
	 * @throws UiObjectNotFoundException
	 */
	public void testGetCaimpAPP() throws UiObjectNotFoundException {
		UiScrollable scorScrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scorScrollable.setAsVerticalList();
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/baselistview"));
		sleep(3000);
		Assert.assertEquals(true, coll.exists());
		ArrayList<String> listApps = new ArrayList<String>();
		if (coll.exists()) {
			int count = coll.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_title"));
			System.out.println("活动页面有 " + count + "个app");
			for (int i = 0; i < count; i++) {
				UiObject caimpApp = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/tv_title").instance(i));
				if (caimpApp.exists()) {
					listApps.add(caimpApp.getText());
					// Assert.assertEquals(true, caimpApp.isClickable());
					// caimpApp.clickAndWaitForNewWindow();
				}
			}
		}
		System.out.println("活动页面appname：" + listApps);

		UiObject desc = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_description"));
		Assert.assertEquals(true, desc.exists());
		System.out.println("活动描述：" + desc.getText());

		UiObject logoImage = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_logo"));
		Assert.assertEquals(true, logoImage.exists());
		System.out.println("活动logo是否存在：" + logoImage.exists());

		UiObject activate = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_activate"));
		Assert.assertEquals(true, activate.exists());
		System.out.println("活动状态标志是否存在：" + activate.exists());
		sleep(2000);
	}
	/**
	 * 点击某个活动进入机锋活动页面
	 * @throws UiObjectNotFoundException
	 */
	public void testClickCaimpApp() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(300, 500);
		sleep(3000);
		testCaimpTask();
	}

	/**
	 * 进入某个活动页面 获取活动任务列表的appname
	 * 截取活动结束时间，与当前系统时间做比较，输出活动是否结束
	 * @throws UiObjectNotFoundException
	 */
	public void testCaimpTask() throws UiObjectNotFoundException {
		UiObject taskCount = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_app_count"));
		Assert.assertEquals(true, taskCount.exists());
		/** 获取活动时间区间，并截取结束时间 */
		UiObject activeTime = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_all_time"));
		String activetime = activeTime.getText();
		System.out.println("活动结束时间：" + activetime.substring(11, 21));
		/** 获取当前系统时间 */
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		System.out.println("当前系统时间：" + df.format(new Date()));// new
																// Date()为获取当前系统时间
		if ((activetime.substring(11, 21)).compareTo(df.format(new Date())) < 0) {
			UiObject appname = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/common_list_item_name"));
			Assert.assertEquals(false, appname.isClickable());
			System.out.println("活动时间已经结束");
		} else {
			System.out.println("点击任务右侧按钮：");
		}
	}

	/**
	 * 获取任务中app的控件
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testAppContains() throws UiObjectNotFoundException {
		UiObject icon = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_icon"));
		System.out.println("任务的icon是否存在： " + icon.exists());
		
		UiObject name = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_name"));
		System.out.println("任务的name是否存在： " + name.exists() + " ;appname :" + name.getText());
		
		UiObject amount = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_download_amount"));
		System.out.println("任务的amount是否存在： " + amount.exists() + " ;amount :" + amount.getText());
		
		UiObject score = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_score"));
		System.out.println("任务的score是否存在： " + score.exists() + " ;score :" + score.getText());
		
		UiObject size = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_size"));
		System.out.println("任务的size是否存在： " + size.exists()+ " ;size :" + size.getText());
		
		UiObject des = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_description"));
		System.out.println("任务的des是否存在： " + des.exists()  +  " ;des :" + des.getText());
		
		UiObject hot = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_hot"));
		System.out.println("任务的hot是否存在： " + hot.exists());

	}
	/**
	 * 机锋活动页面 右上角搜索按钮
	 * 返回机锋活动
	 * 返回到首页
	 * @throws UiObjectNotFoundException
	 */
	public void testSearchBtn()throws UiObjectNotFoundException{
		UiDevice device = getUiDevice();
		UiObject search = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/iv_right1"));
		System.out.println("任务的search是否存在： " + search.exists());
		search.click();
		device.pressBack();
		device.pressBack();
		device.pressBack();
	}
}
