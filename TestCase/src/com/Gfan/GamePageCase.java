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

public class GamePageCase extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "GamePageCase";
		String testClass = "com.Gfan.GamePageCase";
		String testName = "testCase";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	public void testCase() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		testClickGame();
		testTuiJian();
		testFirstRelease();
		testRank();
		testPresentCenter();
		testClassify();
		device.pressBack();// 运行完所有test后返回首页

	}

	/**
	 * 获取游戏page二级标签
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testGetSecondTags() throws UiObjectNotFoundException {
		UiCollection secondTags = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/page_indicator"));
		System.out.println("有 " + secondTags.getChildCount() + "个二级标签");
		ArrayList<String> listTags = new ArrayList<String>();
		if (secondTags.exists()) {
			int countTags = secondTags.getChildCount(new UiSelector()
					.className("android.widget.TextView"));
			System.out.println("有 " + countTags + "个二级标签");
			for (int i = 0; i < countTags; i++) {
				UiObject tagName = new UiObject(new UiSelector().className(
						"android.widget.TextView").instance(i));
				if (tagName.exists()) {
					listTags.add(tagName.getText());
				}
			}
		}
		System.out.println("secondTags :" + listTags);
	}

	/**
	 * 获取：推荐、首发、排行、礼品中心的[全部][单击][网游] 点击[全部][单击][网游]并获取对应列表中的appname
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testGetThreeTags() throws UiObjectNotFoundException {
		UiCollection threeTags = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/baselistview"));
		// Assert.assertEquals(true, threeTags.exists());
		System.out.println("有" + threeTags.getChildCount() + "个三级标签");
		ArrayList<String> listTags = new ArrayList<String>();
		if (threeTags.exists()) {
			int countTags = threeTags.getChildCount(new UiSelector()
					.className("android.widget.TextView"));
			for (int i = 0; i < countTags; i++) {
				UiObject tagName = new UiObject(new UiSelector().className(
						"android.widget.TextView").instance(i));
				if (tagName.exists()) {
					listTags.add(tagName.getText());
					System.out.println("三级标签为： " + tagName.getText());
					tagName.click();
					sleep(3000);
					testGetAppName();
				}
			}
		}
		System.out.println("threeTags : " + listTags);
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
	}

	/**
	 * 获取列表中appname
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testGetAppName() throws UiObjectNotFoundException {
		UiCollection listAppname = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/baselistview"));
		ArrayList<String> listApps = new ArrayList<String>();
		if (listAppname.exists()) {
			int countApp = listAppname.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/common_list_item_name"));
			for (int i = 0; i < countApp; i++) {
				UiObject appname = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/common_list_item_name").instance(i));
				if (appname.exists()) {
					listApps.add(appname.getText());
				}
			}
		}
		System.out.println("列表中appname： " + listApps);
	}

	/**
	 * 点击[游戏]，进入游戏页面，获取游戏页面二级标签
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testClickGame() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(400, 250);
		testGetSecondTags();
	}

	/**
	 * 推荐：通过testGetThreeTags()[全部][单击][网游]获取列表中的appname
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testTuiJian() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(318, 250);
		testGetThreeTags();
		testGetAppName();
		testJudgeAppName();

	}

	/**
	 * 推荐：通过testGetThreeTags()[全部][单击][网游]获取列表中的appname
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testFirstRelease() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(336, 250);
		testGetThreeTags();
		testGetAppName();
		testJudgeAppName();

	}

	/**
	 * 推荐：通过testGetThreeTags()[全部][单击][网游]获取列表中的appname
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testRank() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(384, 250);
		testGetThreeTags();
		testGetAppName();
		testJudgeAppName();
	}

	/**
	 * 推荐：通过testGetThreeTags()[新手礼包][独家礼包][超级礼包]获取列表中的appname
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testPresentCenter() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(432, 250);
		testGetThreeTags();
		testGetAppName();
		testJudgeAppName();

	}

	/**
	 * 分类：获取分类列表并且挨个点击
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
					testGetClassifyApp();
					device.pressBack();
				}
			}
		}
		System.out.println("分类列表 ：" + lists);

	}

	/**
	 * 获取分类页面每个分类下的子分类 如：[动作游戏]下的排行、新品、上升最快、推荐列表中的app
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testGetClassifyApp() throws UiObjectNotFoundException {
		testGetSecondTags();
		testGetAppName();
		testRiseQuick();
		testNewApp();
		testRank();
		testTuiJian();
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
