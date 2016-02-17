package com.Gfan;

import java.util.ArrayList;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LeftPageCase extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "LeftPageCase";
		String testClass = "com.Gfan.LeftPageCase";
		String testName = "testCase";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	public void testCase()throws UiObjectNotFoundException{
		testSwipe();
		testHomeMenu();
		testSpeed();
		testClickSetup();
		testSetupPage();
		testFeedBack();
		testEvaluate();
		testCheckUpdate();
		testCommunity();
	}

	/**
	 * swipe()方法向左向右滑动 步骤：①向右滑动打开侧拉菜单 ②向左滑动关闭侧拉菜单
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testSwipe() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.swipe(0, 650, 650, 650, 4);
		sleep(2000);
		device.click(710, 1080);
		sleep(2000);

	}

	/**
	 * 点击menu打开侧拉菜单 步骤：通过UiObject获取对象，点击对象打开侧拉菜单
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testHomeMenu() throws UiObjectNotFoundException {
		UiObject clickMenu = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/home_menu"));
		clickMenu.click();

	}

	/**
	 * 点击加速 步骤：①点击加速-获取加速中的文本 ②加速完成后，获取加速后的文本
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testSpeed() throws UiObjectNotFoundException {
		UiObject clickSpeed = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/sv_go_speed"));
		clickSpeed.click();
		UiObject speedTex = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_go_speed"));
		String tex = speedTex.getText();
		System.out.println("*****执行清理中  =  " + tex + " *****");
		sleep(10000);
		UiObject afterSpeed = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_go_speed"));
		String text = afterSpeed.getText();
		System.out.println("*****您的手机已经达到最佳状态  =  " + text + " *****");

	}

	/**
	 * 打开设置 步骤：①点击[设置]-进入设置page
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testClickSetup() throws UiObjectNotFoundException {
		UiObject clickSetup = new UiObject(new UiSelector().text("设置"));
		clickSetup.click();
	}

	/**
	 * 设置页面 使用ArrayList取出listviews的子控件，挨个点击子控件 步骤：①UiCollection获取父控件
	 * ②如果父控件存在，获取父控件下的子控件数量getChildCount() ③如果子控件存在，将子控件取出放入lists并依次点击子控件
	 * ④将子控件在控制台打印出来 ⑤device退出设置page
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testSetupPage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		UiObject cancleRoot = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/btn_negative"));
		UiCollection listview = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		ArrayList<String> lists = new ArrayList<String>();
		if (listview.exists()) {
			int tvnamecount = listview.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_name"));
			System.out.println("设置页面子控件个数 = " + tvnamecount);
			for (int j = 0; j < tvnamecount; j++) {
				UiObject tvname = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/tv_name").instance(j));
				if (tvname.exists()) {
					lists.add(tvname.getText());// 取出子控件放入集合中
					tvname.click();// 挨个点击子控件
					if (cancleRoot.exists()) {
						cancleRoot.click();
					}
				} else {
					break;
				}
			}
		}
		System.out.println("list页面子控件名称为 ：" + lists);
		device.pressBack();
	}
	/**
	 * 反馈
	 * 步骤：①进入反馈page
	 * ②直接点击submit按钮
	 * ③点击输入反馈内容
	 * ④输入联系方式
	 * ⑤判断页面是否查找到submit
	 * ⑥如果查找到submit直接点击
	 * ⑦如果未查找到submit，pressback()后再点击submit
	 * @throws UiObjectNotFoundException
	 */
	public void testFeedBack() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		testHomeMenu();
		UiObject feddback = new UiObject(new UiSelector().text("反馈"));
		feddback.click();
		UiObject submit = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/bt_submit"));
		submit.click();
		System.out.println("直接点击提交按钮，提示：内容不能为空");
		UiObject contentTex = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_content"));
		System.out.println("编辑栏默认文本为： " + contentTex.getText());
		UiObject contactTex = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_contact"));
		System.out.println("联系方式栏默认文本为： " + contactTex.getText());
		contentTex.click();
		contentTex.setText("123456789");
		contactTex.click();
		contactTex.setText("784521啊");
		sleep(1000);
		if (submit.exists()) {
			submit.click();
		} else {
			device.pressBack();
			submit.click();
		}
		device.pressBack();
	}

	/**
	 * 评价
	 * 步骤：①点击[评价]
	 * ②获取应用商店列表父控件
	 * ③如果父控件存在，获取子控件数量getChildCount()
	 * ④如果子控件存在，instance()遍历子控件
	 * ⑤将取出的子控件放入lists中并依次点击控件后返回列表
	 * ⑥将所有控件打印在控制台
	 * @throws UiObjectNotFoundException
	 */
	public void testEvaluate() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		testHomeMenu();
		UiObject evaluate = new UiObject(new UiSelector().text("评价"));
		evaluate.click();
		UiCollection collection = new UiCollection(
				new UiSelector().resourceId("android:id/resolver_grid"));
		ArrayList<String> lists = new ArrayList<String>();
		if (collection.exists()) {
			int textviewCount = collection.getChildCount(new UiSelector()
					.className("android.widget.LinearLayout").childSelector(
							new UiSelector().resourceId("android:id/text1")));
			System.out.println("应用商店个数 = " + textviewCount);
			for (int i = 0; i < textviewCount; i++) {
				UiObject textview = new UiObject(new UiSelector().resourceId(
						"android:id/text1").instance(i));
				if (textview.exists()) {
					lists.add(textview.getText());
					textview.click();
				}
				device.pressBack();
			}
			System.out.println("应用商店名称为 ： " + lists);
		}
		sleep(1000);
		device.pressBack();
	}

	/**
	 * 检查更新
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testCheckUpdate() throws UiObjectNotFoundException {
		testHomeMenu();
		UiObject update = new UiObject(new UiSelector().text("检查更新"));
		update.click();
		sleep(2000);
		System.out.println("检查更新：正在更新，请稍等片刻....已经是最新版本了");
	}

	/**
	 * 论坛
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testCommunity() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		testHomeMenu();
		UiObject community = new UiObject(new UiSelector().text("论坛"));
		community.click();
		sleep(2000);
		System.out.println("进入论坛啦");
		device.pressBack();
	}


}
