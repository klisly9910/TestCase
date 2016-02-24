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
	 * 获取活动页面的app,点击app进入机锋活动页面
	 * 
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
					Assert.assertEquals(true, caimpApp.isClickable());
					caimpApp.clickAndWaitForNewWindow();
					testCaimpTask();
				}
			}
		}
		System.out.println("活动页面appname：" + listApps);
	}

	/**
	 * 进入某个活动页面 获取活动任务列表的appname
	 * 
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
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		if ((activetime.substring(11, 21)).compareTo(df.format(new Date()))<0) {
			UiObject appname = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/common_list_item_name"));
			Assert.assertEquals(false, appname.isClickable());
		}else{
			System.out.println("点击任务右侧按钮：" );
		}
	}
}
