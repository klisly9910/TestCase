package com.Gfan;



import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LoginFailCase extends UiAutomatorTestCase{
	public static void main(String[] args) {
		String jarName = "LoginFailCase";
		String testClass = "com.Gfan.LoginFailCase";
		String testName = "testCase";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}
	public void testCase()throws UiObjectNotFoundException{
		testHeadrImage();
		testClickLoginButtton();
		testBlankPassword();
		testBlankUsername();
		testBack();
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
	}
	/**
	 * 直接点击登录按钮
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testClickLoginButtton() throws UiObjectNotFoundException {
		UiObject clicklogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		clicklogin.click();
		System.out.println("直接点击登录，登录失败");

	}
	/**
	 * 输入用户名：imopan，密码为空格，点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testBlankPassword() throws UiObjectNotFoundException {
		UiObject blankUser = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		blankUser.setText("imopan");
		UiObject blankPw = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		blankPw.setText("");
		UiObject clicklogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		clicklogin.click();
		System.out.println("密码为空，点击登录，登录失败");

	}
	/**
	 * 用户名为空，密码为：123456，点击登录
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testBlankUsername() throws UiObjectNotFoundException {
		UiObject blankUser = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_gfan"));
		blankUser.setText("");
		UiObject blankPw = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/et_password"));
		blankPw.setText("123456");
		UiObject clicklogin = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/rl_login"));
		clicklogin.click();
		System.out.println("用户名为空，点击登录，登录失败");

	}
	public void testBack()throws UiObjectNotFoundException{
		UiDevice device = getUiDevice();
		device.pressBack();
		device.pressBack();
	}

}
