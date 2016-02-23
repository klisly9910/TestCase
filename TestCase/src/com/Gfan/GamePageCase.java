package com.Gfan;

import java.util.ArrayList;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
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
		testClickGame();
		testTuiJian();
		testFirstRelease();
		testRank();
		testPresentCenter();

	}

	/**
	 * 后去游戏page二级标签
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testGetSecondTags() throws UiObjectNotFoundException {
		UiCollection secondTags = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/page_indicator"));
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
		ArrayList<String> listTags = new ArrayList<String>();
		if (threeTags.exists()) {
			int countTags = threeTags.getChildCount(new UiSelector()
					.className("android.widget.TextView"));
			for (int i = 0; i < countTags; i++) {
				UiObject tagName = new UiObject(new UiSelector().className(
						"android.widget.TextView").instance(i));
				if (tagName.exists()) {
					listTags.add(tagName.getText());
					tagName.click();
					sleep(3000);
					testGetAppName();
				}
			}
		}
		System.out.println("threeTags : " + listTags);
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

	}

}
