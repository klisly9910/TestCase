package com.Gfan;

import java.util.ArrayList;

import junit.framework.Assert;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class AppManageCase extends UiAutomatorTestCase {
	public static void main(String[] args) {
		String jarName = "AppManageCase";
		String testClass = "com.Gfan.AppManageCase";
		String testName = "testCase";
		String androidId = "1";
		new UiAutomatorHelper(jarName, testClass, testName, androidId);
	}

	public void testCase() throws UiObjectNotFoundException {
		testUpdateNum();
		testClickManagement();
		testSecondTag();
		testTaskPage();
		testTaskOperationBtn();
		testInstalledPage();
		testInstalledOperationBtn();
		testUpdatePage();
		testUpdateOperationBtn();
		testDownAllBar();
	}

	/**
	 * 获取应用管理按钮右上角更新number
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testUpdateNum() throws UiObjectNotFoundException {
		UiObject updateNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/num"));
		String number = updateNum.getText();
		System.out.println("应用管理右上角更新数量为： " + number);

	}

	/**
	 * 点击应用管理
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testClickManagement() throws UiObjectNotFoundException {
		UiObject clickManagement = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/app_update"));
		clickManagement.click();
		UiObject title = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_title"));
		String titleName = title.getText();
		System.out.println("页面名称为： " + titleName);
	}

	/**
	 * 获取应用管理页面的二级标签：任务、已安装、升级、安装包管理
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testSecondTag() throws UiObjectNotFoundException {
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("com.mappn.gfan:id/page_indicator"));
		ArrayList<String> lists = new ArrayList<String>();
		if (coll.exists()) {
			int count = coll.getChildCount(new UiSelector()
					.className("android.widget.TextView"));
			System.out.println("应用管理界面有 " + count + "个二级标签");
			for (int i = 0; i < count; i++) {
				UiObject secondTag = new UiObject(new UiSelector()
						.resourceId("com.mappn.gfan:id/page_indicator")
						.childSelector(
								new UiSelector()
										.className("android.widget.TextView"))
						.instance(i));
				if (secondTag.exists()) {
					lists.add(secondTag.getText());
					secondTag.click();
					System.out.println("二级标签名称： " + secondTag.getText());
				}

			}

		}

	}

	/**
	 * 点击[任务]，任务列表没有数据
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testTaskNoData() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(120, 200);
		UiObject nodata = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (nodata.exists()) {
			System.out.println("你怎么忍心让我空着呢，快去精品推荐看看吧~");
			return;
		}
	}

	/**
	 * 任务页面：任务列表为空，获取提示信息；获取页面分类模块名称;任务列表不为空：获取任务列表的应用；
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testTaskPage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(120, 200);
		testTaskNoData();
		UiScrollable scroll = new UiScrollable(
				new UiSelector().scrollable(true));
		scroll.setAsVerticalList();
		UiCollection collTitle = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		ArrayList<String> listTitle = new ArrayList<String>();
		if (collTitle.exists()) {
			int countTitle = collTitle.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/title"));
			System.out.println("页面分类模块有 " + countTitle + "个");
			for (int i = 0; i < countTitle; i++) {
				UiObject title = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/title").instance(i));
				if (title.exists()) {
					listTitle.add(title.getText());
				}
				// System.out.println("页面分类模块名称： " + title.getText());
			}

		}
		System.out.println("页面分类模块名称： " + listTitle);// [手动开始(点击可删除),
														// 下载完成(已存到SD卡/gfan/market)]
		UiCollection collApp = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		ArrayList<String> listApp = new ArrayList<String>();
		if (collApp.exists()) {
			int countApp = collApp.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_name"));
			System.out.println("任务页面有 " + countApp + "个应用");
			for (int j = 0; j < countApp; j++) {
				UiObject appName = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/tv_name").instance(j));
				if (appName.exists()) {
					listApp.add(appName.getText());
				}

			}

		}
		System.out.println("任务页面app名称： " + listApp);

	}

	/**
	 * 任务列表app：进入详情；删除；删除完成后重新获取任务页面的app
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testTaskOperationBtn() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(120, 200);
		testTaskNoData();
		UiObject operationBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_operation"));
		operationBtn.click();
		UiObject detailBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_detail"));
		detailBtn.click();
		device.pressBack();

		UiObject deleteBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_delete"));
		deleteBtn.click();

		UiObject delMess = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/textview_title"));
		Assert.assertEquals(true, delMess.exists());
		System.out.println("点击删除弹出提示框： " + delMess.getText());

		UiObject confirmBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		confirmBtn.click();
		System.out.println("====分割线====删除后重新获取应用====");
		testTaskPage();// 删除后重新获取任务页面的app

	}

	/**
	 * 已安装页面：获取列表app
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testInstalledPage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(240, 200);
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		ArrayList<String> listApp = new ArrayList<String>();
		if (coll.exists()) {
			int countApp = coll.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_item_name"));
			System.out.println("已安装页面有 " + countApp + "个应用");
			for (int i = 0; i < countApp; i++) {
				UiObject appName = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/tv_item_name").instance(i));
				if (appName.exists()) {
					listApp.add(appName.getText());
				}
			}
		}
		System.out.println("已安装页面appname：" + listApp);
	}

	/**
	 * 已安装页面：详情、卸载、打开；卸载完成后重新获取已安装页面的app
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testInstalledOperationBtn() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(240, 200);
		UiObject operationBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_item_detail"));
		operationBtn.click();

		UiObject detailBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_deatil"));
		detailBtn.click();
		device.pressBack();

		UiObject openBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_option_open"));
		openBtn.click();
		device.pressBack();
		// device.pressBack();

		UiObject uninstallBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_uninstall"));
		uninstallBtn.click();

		UiObject uninstallOKBtn = new UiObject(
				new UiSelector()
						.resourceId("com.android.packageinstaller:id/ok_button"));
		uninstallOKBtn.click();
		sleep(5000);
		System.out.println("====分割线====卸载后重新获取应用====");
		testInstalledPage();// 卸载完成后重新获取已安装页面的app
	}

	/**
	 * 点击[升级]，升级列表没有数据
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testUpdateNoData() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(420, 200);
		UiObject nodata = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (nodata.exists()) {
			System.out.println("你很勤快嘛，继续保持哦~");
			return;
		}
	}

	/**
	 * 升级页面：获取升级页面的应用;判断升级页面是否有应用
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testUpdatePage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(420, 200);
		testUpdateNoData();
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		ArrayList<String> listApp = new ArrayList<String>();
		if (coll.exists()) {
			int countApp = coll.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_item_name"));
			System.out.println("升级页面有 " + countApp + "个应用");
			for (int i = 0; i < countApp; i++) {
				UiObject appName = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/tv_item_name").instance(i));
				if (appName.exists()) {
					listApp.add(appName.getText());
				}
			}
		}
		System.out.println("升级页面appname：" + listApp);
	}

	/**
	 * 升级页面：详情、卸载、忽略、打开
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testUpdateOperationBtn() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(420, 200);
		testUpdateNoData();
		UiObject operationBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_item_detail"));
		operationBtn.click();

		UiObject detailBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_deatil"));
		detailBtn.click();
		device.pressBack();

		UiObject openBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_option_open"));
		openBtn.click();
		device.pressBack();
		// device.pressBack();

		UiObject uninstallBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_uninstall"));
		uninstallBtn.click();

		UiObject uninstallOKBtn = new UiObject(
				new UiSelector()
						.resourceId("com.android.packageinstaller:id/ok_button"));
		uninstallOKBtn.click();
		sleep(5000);
		System.out.println("====分割线====卸载后重新获取升级页面应用====");
		testInstalledPage();// 卸载完成后重新获取已安装页面的app
		
		device.click(420, 200);
		if (operationBtn.exists()) {
			operationBtn.click();
		} else {
			return;
		}
		UiObject ignoreBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_ignore"));
		ignoreBtn.click();
		System.out.println("====分割线====忽略后重新获取升级页面应用====");
		testInstalledPage();// 卸载完成后重新获取已安装页面的app
	}

	/**
	 * 升级页面：底部更新应用大小;一键全部更新
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testDownAllBar() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(420, 200);
		UiObject updateSize = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/manager_tv_total_update_size"));
		System.out.println(updateSize.getText());

		UiObject nodata = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (nodata.exists()) {
			System.out.println("你很勤快嘛，继续保持哦~");

		} else {
			UiObject updateNum = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/tv_upgrade_num"));
			System.out.println("可更新数量： " + updateNum);

		}
		UiObject updateAll = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/manager_down_all_button"));
		updateAll.click();

	}
	/**
	 * 忽略页面：取消忽略;全部忽略
	 * @throws UiObjectNotFoundException
	 */
	public void testIgnorePage()throws UiObjectNotFoundException{
		
	}

}
