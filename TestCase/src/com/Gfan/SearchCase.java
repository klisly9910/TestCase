package com.Gfan;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class SearchCase extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "SearchCase";
		String testClass = "com.Gfan.SearchCase";
		String testName = "testCase";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	public void testCase() throws UiObjectNotFoundException {
		testDefaultTex();
		testSearButton();
		teestAppManageBtn();
		testClickSearch();
		testSetWord();
		testClickWord();
		testSearResult();
		testAllPage();
		testApplicationPage();
		testGamePage();
		testBackButton();
		testSearButn();
	}

	/**
	 * 获取默认搜索关键
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testDefaultTex() throws UiObjectNotFoundException {
		UiObject text = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_search"));
		System.out.println("搜索默认关键字为： " + text.getText());
	}

	/**
	 * 检查搜索按钮是否存在
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testSearButton() throws UiObjectNotFoundException {
		UiObject searButton = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/image_button_search"));
		Assert.assertEquals(true, searButton.exists());
		System.out.println("搜索按钮是否为true ：" + searButton.exists());
	}

	/**
	 * 检查应用管理按钮是否存在
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void teestAppManageBtn() throws UiObjectNotFoundException {
		UiObject appManageBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/app_update"));
		Assert.assertEquals(true, appManageBtn.exists());
		System.out.println("应用管理按钮是否为true ：" + appManageBtn.exists());
	}

	/**
	 * 点击搜索框,判断左侧←是否存在，右侧搜索按钮是否存在 获取默认默认关键词：搜索机锋应用商店
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testClickSearch() throws UiObjectNotFoundException {
		UiObject search = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_search"));
		search.click();
		UiObject backButton = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/home_search_back"));
		Assert.assertEquals(true, backButton.exists());
		System.err.println("返回箭头是否存在： " + backButton.exists());
		UiObject searButton = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/home_search_button"));
		Assert.assertEquals(true, searButton.exists());
		System.err.println("搜索按钮是否存在： " + searButton.exists());
		UiObject searTex = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_search"));
		Assert.assertEquals("搜索机锋应用商店", searTex.getText());
		System.err.println("默认关键词为： " + searTex.getText());

	}

	/**
	 * 点击搜索框后，输入搜索关键词,获取搜索提示词个数、名称
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testSetWord() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		/** 判断输入框是否存在--输入QQ */
		UiObject etSearch = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_search"));
		Assert.assertEquals(true, etSearch.exists());
		etSearch.setText("QQ");
		device.pressBack();// 收起键盘
		UiCollection collection = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/list_view"));
		Assert.assertEquals(true, collection.exists());
		System.out.println("搜索提示词是否存在： " + collection.exists());
		ArrayList<String> array = new ArrayList<String>();
		if (collection.exists()) {
			int count = collection.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_his_name"));
			System.out.println("共有 " + count + "个搜索提示");
			Assert.assertEquals(5, count);
			for (int i = 0; i < count; i++) {
				UiObject keyword = collection.getChild(new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_his_name")
						.instance(i));
				if (keyword.exists()) {
					String name = keyword.getText();
					array.add(name);
					System.out.println("搜索提示词语为 ： " + name);
				}
			}
		}
	}

	/**
	 * 点击list关键字进行搜索
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testClickWord() throws UiObjectNotFoundException {
		UiObject clickWord = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_search_his"));
		clickWord.click();
	}

	/**
	 * 搜索结果页面：检查搜索栏关键词、返回按钮、搜索按钮；搜索结果页面：全部、应用、游戏
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testSearResult() throws UiObjectNotFoundException {
		UiObject backButton = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/back"));
		Assert.assertEquals(true, backButton.exists());
		System.out.println("搜索结果页面返回箭头是否存在： " + backButton.exists());
		UiObject searButton = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/search"));
		Assert.assertEquals(true, searButton.exists());
		System.out.println("搜索结果页面搜索按钮是否存在： " + searButton.exists());
		searButton.click();
		UiObject searWord = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_search"));
		System.out.println(" 搜索结果页面搜索关键词为： " + searWord.getText());
		UiCollection collection = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/page_indicator"));
		ArrayList<String> lists = new ArrayList<String>();
		if (collection.exists()) {
			int count = collection.getChildCount(new UiSelector()
					.className("android.widget.TextView"));
			System.out.println("搜索结果有 " + count + "个页面");
			for (int i = 0; i < count; i++) {
				UiObject searPageName = new UiObject(new UiSelector()
						.className("android.widget.TextView").instance(i));
				if (searPageName.exists()) {
					String pageName = searPageName.getText();
					lists.add(pageName);
					// searPageName.click();
					System.out.println("搜索结果页面为： " + pageName);
				}

			}
		}
	}

	/**
	 * 滚动全部页面，获取全部页面应用
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testAllPage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(200, 200);
		UiScrollable scroll = new UiScrollable(
				new UiSelector().scrollable(true));
		scroll.setAsVerticalList();
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/app_lv"));
		ArrayList<String> lists = new ArrayList<String>();
		if (coll.exists()) {
			int count = coll.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/common_list_item_name"));
			System.out.println("全部列表有 " + count + "个应用");
			for (int i = 0; i < count; i++) {
				UiObject appName = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/common_list_item_name").instance(i));
				if (appName.exists()) {
					String appNam = appName.getText();
					lists.add(appNam);
					System.out.println("全部页面app的名称为： " + appNam);
				}

			}

		}

	}

	/**
	 * 点击应用页面，滚动-判断页面是否有app-获取应用
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testApplicationPage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(300, 220);
		UiScrollable scorll = new UiScrollable(
				new UiSelector().scrollable(true));
		scorll.setAsVerticalList();
		UiObject noapp = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_app"));
		if (noapp.exists()) {
			String mess = noapp.getText();
			System.out.println("应用页面没有app提示： " + mess);
			return;
		}
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/app_lv"));
		ArrayList<String> lists = new ArrayList<String>();
		if (coll.exists()) {
			int count = coll.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/common_list_item_name"));
			System.out.println("应用列表有 " + count + "个应用");
			for (int i = 0; i < count; i++) {
				UiObject appName = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/common_list_item_name").instance(i));
				if (appName.exists()) {
					String appNam = appName.getText();
					lists.add(appNam);
					System.out.println("应用页面app的名称为： " + appNam);
				}

			}

		}

	}

	/**
	 * 点击游戏页面-滚动-判断页面是否有app-获取应用
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testGamePage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(600, 235);
		UiScrollable scorll = new UiScrollable(
				new UiSelector().scrollable(true));
		scorll.setAsVerticalList();
		UiObject noapp = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_app"));
		if (noapp.exists()) {
			String mess = noapp.getText();
			// Assert.assertEquals("哎呀，没有搜索到结果，请重新搜索一下吧~", noapp.getText());
			System.out.println("游戏页面没有app提示： " + mess);
			return;
		}
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/app_lv"));
		ArrayList<String> lists = new ArrayList<String>();
		if (coll.exists()) {
			int count = coll.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/common_list_item_name"));
			System.out.println("游戏列表有 " + count + "个应用");
			for (int i = 0; i < count; i++) {
				UiObject appName = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/common_list_item_name").instance(i));
				if (appName.exists()) {
					String appNam = appName.getText();
					lists.add(appNam);
					System.out.println("游戏页面app的名称为： " + appNam);
				}
			}

		}

	}

	/**
	 * 点击返回按钮返回首页
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testBackButton() throws UiObjectNotFoundException {
		UiObject backButton = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/back"));
		backButton.click();
	}

	/**
	 * 返回首页后，点击左侧搜索按钮
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testSearButn() throws UiObjectNotFoundException {
		UiObject defaultText = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_search"));
		String defaultTex = defaultText.getText();
		System.out.println("defaultTex = " + defaultText.getText());
		UiObject searBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/image_button_search"));
		searBtn.click();
		UiObject keyword = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_search"));
		String keyWordTex = keyword.getText();
		System.out.println("keyWordTex = " + keyword.getText());
		/** 判断defaultTex中是否包含keyWordTex */
		if (defaultTex.indexOf(keyWordTex) != -1) {
			System.out.println("defaultTex包含keyWordTex :"
					+ defaultTex.contains(keyWordTex));
			System.out.println("keyWordTex = " + keyWordTex);
		}
		/** 调用testBackButton()方法返回首页 */
		testBackButton();
	}

}
