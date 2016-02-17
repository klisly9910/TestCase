package com.Gfan;


import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.Gfan.UiAutomatorHelper;

public class OpenExitCase extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "OpenExitCase";
		String testClass = "com.Gfan.OpenExitCase";
		String testName = "testCase";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName,androidId);
	}
	public void testCase() throws UiObjectNotFoundException{
		testOpenGfan();
		testJumpSplash();
		testExitGfan();
	}
	/**
	 * 打开机锋应用商店
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testOpenGfan() throws UiObjectNotFoundException {
		getUiDevice().pressHome();
		// 点击机锋应用商店
		UiDevice dvice = getUiDevice();
		dvice.click(92, 700);
		sleep(5000);
	}
	/**
	 * 点击跳过闪屏广告,点击跳过闪屏进入机锋市场首页
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testJumpSplash() throws UiObjectNotFoundException {
		UiObject splash = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/ib_jump_advert"));
		if (splash.exists()) {
			splash.clickAndWaitForNewWindow();
		} else {
			return;
		}
	}
	/**
	 * pressback方法退出机锋市场
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testExitGfan() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.pressBack();
		device.pressBack();
	}


}
