package com.Gfan;

import java.io.File;
import java.util.ArrayList;

import junit.framework.Assert;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class ApplicationPageCase extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "ApplicationPageCase";
		String testClass = "com.Gfan.ApplicationPageCase";
		String testName = "testCase";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	public void testCase() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		testClickApplication();
		testRank();
		testTuiJianPage();
		testNewApp();
		testFsBang();
		testClassify();
		device.pressBack();//运行完所有test后返回首页
	}

	/**
	 * 通过arraylist数组取出[应用]中的二级标签 并依次点击 输出到控制台
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testGetSecondTags() throws UiObjectNotFoundException {
		System.out.println("获取分类列表二级标签-并依次点击");
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/page_indicator"));
		ArrayList<String> array = new ArrayList<String>();
		if (coll.exists()) {
			int textviewCount = coll.getChildCount(new UiSelector()
					.className("android.widget.TextView"));
			System.out.println("共有 " + textviewCount + "个二级标签");
			for (int i = 0; i < textviewCount; i++) {
				UiObject textview = coll.getChild(new UiSelector().className(
						"android.widget.TextView").instance(i));
				if (textview.exists()) {
					String textName = textview.getText();
					array.add(textName);
					textview.click();
					sleep(2000);
				}
			}
		}
		System.out.println("二级标签为 ： " + array);
	}

	/**
	 * 通过arraylist获取列表页面的app 输出到控制台
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testGetAppName() throws UiObjectNotFoundException {
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollable.setAsVerticalList();
		UiCollection collection = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/baselistview"));
		ArrayList<String> arraylist = new ArrayList<String>();
		if (collection.exists()) {
			int framCount = collection.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/common_list_item_name"));
			for (int i = 0; i < framCount; i++) {
				UiObject textview = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/common_list_item_name").instance(i));
				if (textview.exists()) {
					arraylist.add(textview.getText());
				}
			}
		}
		System.out.println("列表中的app为 ： " + arraylist);
	}

	/**
	 * getItemName()获取app名称 点击app进入应用详情--返回 如果更新/安装/暂停/继续打开存在==点击
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testJudgeAppName() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		UiObject appname = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_name"));
		sleep(5000);
		String appName = null;
		try {
			appName = appname.getText();
			System.out.println("点击的appname名称为 ： " + appName);
		} catch (Exception e1) {
			System.out.println("分类页面未加载出来");
		}
		appname.click();
		sleep(2000);
		UiObject name = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/app_name"));
		try {
			if (appName.equals(name.getText())) {
				System.out.println("列表中的appname与详情页的appname是否一致： "
						+ appName.equals(name.getText()));
			}
		} catch (Exception e) {
			File path = new File("sdcard/Download/filename.png");
			device.takeScreenshot(path);
			e.printStackTrace();
			System.out.println("列表中的appname与详情页的appname不一致");
		}
		device.pressBack();
		/*
		 * UiObject updateBtn = new UiObject(new UiSelector().text("更新"));
		 * UiObject downloadBtn = new UiObject(new UiSelector().text("下载"));
		 * UiObject install = new UiObject(new UiSelector().text("安装"));
		 * UiObject open = new UiObject(new UiSelector().text("打开")); UiObject
		 * contin = new UiObject(new UiSelector().text("暂停")); UiObject stop =
		 * new UiObject(new UiSelector().text("继续")); if (updateBtn.exists()) {
		 * updateBtn.click(); } else if (install.exists()) { install.click();
		 * UiObject cancleinstall = new UiObject( new UiSelector()
		 * .resourceId("com.android.packageinstaller:id/cancel_button"));
		 * cancleinstall.click(); } else if (contin.exists()) { contin.click();
		 * } else if (downloadBtn.exists()) { downloadBtn.click(); } else if
		 * (stop.exists()) { stop.click(); } else if (open.exists()) {
		 * open.click(); sleep(1000); device.pressBack(); }
		 */
	}

	/**
	 * 点击应用管理-进入应用管理页面 ①getTags()获取二级标签
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testClickApplication() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(200, 260);
		testGetSecondTags();
	}

	/**
	 * 排行页面 通过testPageLists()方法获取列表页面的app名称 通过testGetItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testRank() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		System.out.println("[排行]");
		device.click(250, 250);
		testGetAppName();
		testJudgeAppName();
	}

	/**
	 * 推荐页面 通过testPageLists()方法获取列表页面的app名称 通过testGetItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testTuiJianPage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		System.out.println("[推荐]");
		device.click(400, 250);
		testGetAppName();
		testJudgeAppName();
	}

	/**
	 * 新品页面 通过testPageLists()方法获取列表页面的app名称 通过testGetItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testNewApp() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		System.out.println("[新品]");
		device.click(530, 250);
		testGetAppName();
		testJudgeAppName();
	}

	/**
	 * 锋神榜 通过device点击进入应用详情 获取appname
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testFsBang() throws UiObjectNotFoundException {
		System.out.println("[锋神榜]");
		UiDevice device = getUiDevice();
		device.click(700, 250);
		UiObject fs = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/common_list_item_name"));
		System.out.println("锋神榜appname：" + fs.getText());
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollable.setAsVerticalList();
	}

	/**
	 * 分类 获取分类列表并且挨个点击
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testClassify() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(100, 250);
		UiScrollable scrollable = new UiScrollable(
				new UiSelector().scrollable(true));
		scrollable.setAsVerticalList();
		ArrayList<String> lists = new ArrayList<String>();
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/baselistview"));
		if (coll.exists()) {
			try {
				UiObject category = new UiObject(
						new UiSelector()
								.resourceId("com.mappn.gfan:id/tv_category_1"));
				Assert.assertEquals(true, category.exists());
				System.out.println("分类模块是否存在： " + category.exists());
			} catch (Exception e) {
				e.printStackTrace();
			}

			int linearCount = coll.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_category_1"));
			for (int i = 0; i < linearCount; i++) {
				UiObject textview = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/tv_category_1").instance(i));
				if (textview.exists()) {
					lists.add(textview.getText());
					sleep(2000);
					System.out.println("每个分类模块下的子分类名称： " + textview.getText());
					textview.click();
					testClassifyApp();
					device.pressBack();
				}
			}
		}
		System.out.println("分类列表 ：" + lists);
	}

	/**
	 * 获取分类页面每个分类下的子分类
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testClassifyApp() throws UiObjectNotFoundException {
		testGetSecondTags();
		testJudgeAppName();
		testRiseQuick();
		testNewApp();
		testRank();
		testTuiJianPage();
	}

	/**
	 * 分类页面子类页面-上升最快页面，通过listPage()方法获取列表页面的app名称 通过getItemName()方法点击进入应用详情并返回
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testRiseQuick() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		System.out.println("分类子类-[上升最快]");
		device.click(620, 250);
		testGetAppName();
		testJudgeAppName();
	}

}
