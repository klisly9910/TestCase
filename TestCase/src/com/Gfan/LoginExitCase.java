package com.Gfan;

import junit.framework.Assert;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.Gfan.UiAutomatorHelper;

public class LoginExitCase extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "LoginExitCase";
		String testClass = "com.Gfan.LoginExitCase";
		String testName = "testCase";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	public void testCase() throws UiObjectNotFoundException {
		testClickMy();
		testHeadrImage();
		testUIelement();
		testLogin();
		testLogout();
	}

	/**
	 * 点击“我的”进入个人中心
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testClickMy() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(550, 350);
	}

	/**
	 * 点击用户登录头像
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testHeadrImage() throws UiObjectNotFoundException {
		UiObject userLogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		userLogin.click();
		// 收起键盘
		UiDevice device = getUiDevice();
		device.pressBack();
	}

	/**
	 * 检查登录界面元素
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testUIelement() throws UiObjectNotFoundException {

		/** 判断搜索按钮是否存在 */
		UiObject searchButton = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_right1"));
		Assert.assertEquals(true, searchButton.exists());
		System.out.println("搜索按钮存在");

		/** 获取username默认文本-判断默认值是否正确 */
		UiObject etgfan = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		String text = etgfan.getText();
		Assert.assertEquals("请输入登录账号", text);
		System.out.println("默认文本：" + text);

		/** 判断password栏位是否存在 */
		UiObject etpassword = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		Assert.assertEquals(true, etpassword.exists());
		System.out.println("密码框存在");

		/** 获取username默认文本-判断默认值是否正确 */
		UiObject register = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_register"));
		Assert.assertEquals("注册机锋账号", register.getText());
		System.out.println("默认文本：" + register.getText());

	}

	/**
	 * 输入正确的用户名、密码点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testLogin() throws UiObjectNotFoundException {
		UiObject userName = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		userName.click();
		userName.setText("imopan");
		UiObject password = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		password.click();
		password.setText("654321");
		UiObject login = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		login.clickAndWaitForNewWindow();
		/** 判断登录成功后是否返回个人中心页面 */
		UiObject personCenterPage = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/menu_top_rl"));
		Assert.assertEquals(true, personCenterPage.exists());
		/** 获取登录后的用户名 */
		UiObject afterLogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_user_name"));
		String name = afterLogin.getText();
		System.out.println("登录后用户昵称为： " + name);

	}

	/**
	 * 注销-取消-确定,判断注销提示框信息是否正确
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testLogout() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		/** 点击用户头像 */
		UiObject userHead = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_user_head"));
		userHead.click();

		/** 点击注销的坐标 */
		device.click(450, 700);

		/** 判断注销提示框信息是否正确 */
		UiObject tvTitle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/textview_title"));// 提示框标题
		String titleMeg = tvTitle.getText();
		System.out.println("标题： " + titleMeg);
		UiObject tvMesg = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/textview_middle"));// 提示框内容
		String tvMess = tvMesg.getText();
		System.out.println("提示信息： " + tvMess);

		/** 点击取消-注销-确定，判断按钮名称是否正确 */
		UiObject cancle = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_left"));
		cancle.click();

		device.click(450, 700);

		UiObject confirm = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		confirm.click();

		UiObject obj = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_user_name"));
		Assert.assertEquals("请登录", obj.getText());
		System.out.println("用户登录状态为： "+ obj.getText());
	}

}
