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
		testIgnorePage();
		testIgnoreOperation();
		testPackageMana();
		testPackageOperation();
	}

	/**
	 * 获取应用管理按钮右上角更新number
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testUpdateNum() throws UiObjectNotFoundException {
		UiObject updateNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/num"));
		if (updateNum.exists()) {
			String number = updateNum.getText();
			System.out.println("应用管理右上角更新数量为： " + number);
			return;
		}

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

				}

			}

		}
		System.out.println("二级标签名称： " + lists);

	}

	/**
	 * 单独抽取出[已安装]、[升级]页面的打开按钮
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testOpenAppBtn() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		UiObject openBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_option_open"));
		openBtn.click();
		device.pressBack();
		// device.pressBack();
	}

	/**
	 * 点击[任务]，任务列表没有数据
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testTaskNoData() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(120, 200);
		UiObject taskNoData = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (taskNoData.exists()) {
			System.out.println("任务页面没有数据： " + taskNoData.getText());
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
		UiObject taskNoData = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (taskNoData.exists()) {
			System.out.println("任务页面没有数据： " + taskNoData.getText());
			return;
		}
		UiScrollable scroll = new UiScrollable(
				new UiSelector().scrollable(true));
		scroll.setAsVerticalList();
		UiCollection collTitle = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		ArrayList<String> listTitle = new ArrayList<String>();
		if (collTitle.exists()) {
			int countTitle = collTitle.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/title"));
			System.out.println("[任务]页面分类模块有 " + countTitle + "个");
			for (int i = 0; i < countTitle; i++) {
				UiObject title = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/title").instance(i));
				if (title.exists()) {
					listTitle.add(title.getText());
				}
				// System.out.println("页面分类模块名称： " + title.getText());
			}

		}
		System.out.println("[任务]页面分类模块名称： " + listTitle);// [手动开始(点击可删除),
															// 下载完成(已存到SD卡/gfan/market)]
		UiCollection collApp = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		ArrayList<String> listApp = new ArrayList<String>();
		if (collApp.exists()) {
			int countApp = collApp.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_name"));
			System.out.println("[任务]页面有 " + countApp + "个应用");
			for (int j = 0; j < countApp; j++) {
				UiObject appName = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/tv_name").instance(j));
				if (appName.exists()) {
					listApp.add(appName.getText());
				}

			}

		}
		System.out.println("[任务]页面app名称： " + listApp);

	}

	/**
	 * 任务列表app：进入详情；删除；删除完成后重新获取任务页面的app
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testTaskOperationBtn() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(120, 200);
		UiObject taskNoData = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (taskNoData.exists()) {
			System.out.println("任务页面没有数据： " + taskNoData.getText());
			return;
		}
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
		System.out.println("[任务]点击删除弹出提示框： " + delMess.getText());

		UiObject confirmBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		confirmBtn.click();
		System.out.println("====分割线====删除后重新获取[任务]页面应用====");
		if (taskNoData.exists()) {
			System.out.println("任务页面没有数据： " + taskNoData.getText());
			return;
		}
		testTaskPage();// 删除后重新获取任务页面的app

	}

	/**
	 * 已安装：没有数据
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testInstalledNoData() throws UiObjectNotFoundException {
		UiObject installenNodata = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (installenNodata.exists()) {
			System.out.println("已安装没有数据： " + installenNodata.getText());
			return;
		}

	}

	/**
	 * 已安装页面：获取列表app
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testInstalledPage() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(240, 200);
		UiObject installenNodata = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (installenNodata.exists()) {
			System.out.println("已安装没有数据： " + installenNodata.getText());
			return;
		}
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		ArrayList<String> listApp = new ArrayList<String>();
		if (coll.exists()) {
			int countApp = coll.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_item_name"));
			System.out.println("[已安装]页面有 " + countApp + "个应用");
			for (int i = 0; i < countApp; i++) {
				UiObject appName = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/tv_item_name").instance(i));
				if (appName.exists()) {
					listApp.add(appName.getText());
				}
			}
		}
		System.out.println("[已安装]页面appname：" + listApp);
	}

	/**
	 * 已安装页面：详情、卸载、打开；卸载完成后重新获取已安装页面的app
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testInstalledOperationBtn() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(240, 200);
		UiObject installenNodata = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (installenNodata.exists()) {
			System.out.println("已安装没有数据： " + installenNodata.getText());
			return;
		}
		UiObject operationBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_item_detail"));
		operationBtn.click();

		UiObject detailBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_deatil"));
		detailBtn.click();
		device.pressBack();

		// testOpenAppBtn();

		UiObject uninstallBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_uninstall"));
		uninstallBtn.click();

		UiObject uninstallOKBtn = new UiObject(
				new UiSelector()
						.resourceId("com.android.packageinstaller:id/ok_button"));
		uninstallOKBtn.click();
		sleep(5000);
		System.out.println("====分割线====卸载后重新获取[已安装]页面应用====");
		if (installenNodata.exists()) {
			System.out.println("已安装没有数据： " + installenNodata.getText());
			return;
		}
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
		UiObject updateNoData = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (updateNoData.exists()) {
			System.out.println("升级页面没有数据： " + updateNoData.getText());
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
		UiObject updateNoData = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (updateNoData.exists()) {
			System.out.println("升级页面没有数据： " + updateNoData.getText());
			return;
		}
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		ArrayList<String> listApp = new ArrayList<String>();
		if (coll.exists()) {
			int countApp = coll.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/tv_item_name"));
			System.out.println("[升级]页面有 " + countApp + "个应用");
			for (int i = 0; i < countApp; i++) {
				UiObject appName = new UiObject(new UiSelector().resourceId(
						"com.mappn.gfan:id/tv_item_name").instance(i));
				if (appName.exists()) {
					listApp.add(appName.getText());
				}
			}
		}
		System.out.println("[升级]页面appname：" + listApp);
	}

	/**
	 * 升级页面：详情、卸载、忽略、打开
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testUpdateOperationBtn() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(420, 200);
		UiObject updateNoData = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (updateNoData.exists()) {
			System.out.println("升级页面没有数据： " + updateNoData.getText());
			return;
		}
		UiObject operationBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_item_detail"));
		operationBtn.click();

		UiObject detailBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_deatil"));
		detailBtn.click();
		device.pressBack();

		// testOpenAppBtn();

		UiObject uninstallBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_uninstall"));
		uninstallBtn.click();

		UiObject uninstallOKBtn = new UiObject(
				new UiSelector()
						.resourceId("com.android.packageinstaller:id/ok_button"));
		uninstallOKBtn.click();
		sleep(5000);
		System.out.println("====分割线====卸载后重新获取[升级]页面应用====");
		// device.click(420, 200);
		testUpdatePage();// 卸载完成后重新获取[升级]页面页面的app

		if (operationBtn.exists()) {
			operationBtn.click();
		} else {
			return;
		}
		UiObject ignoreBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_ignore"));
		ignoreBtn.click();
		System.out.println("====分割线====忽略后重新获取[升级]页面应用====");
		if (updateNoData.exists()) {
			System.out.println("升级页面没有数据： " + updateNoData.getText());
			return;
		}
		// device.click(420, 200);
		testUpdatePage();// 卸载完成后重新获取[升级]页面的app

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
			System.out.println("[升级]页面可更新数量： " + updateNum.getText());

		}
		UiObject updateAll = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/manager_down_all_button"));
		updateAll.click();
		device.click(420, 200);

	}

	/**
	 * 忽略页面：获取忽略列表数量;全部忽略 查看忽略按钮number、忽略页面、 全部取消忽略按钮number 忽略数量是否一致
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testIgnorePage() throws UiObjectNotFoundException {
		UiObject ignoreNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_ignore_num"));
		if (ignoreNum.exists()) {
			String ignoreNumber = ignoreNum.getText();
			int ignoreIntNumb = Integer.parseInt(ignoreNumber);
			System.out.println("忽略更新数： " + ignoreNumber);
			UiObject ignoreBtn = new UiObject(
					new UiSelector()
							.resourceId("com.mappn.gfan:id/manager_check_ignore_button"));
			ignoreBtn.click();
			UiCollection coll = new UiCollection(
					new UiSelector().resourceId("android:id/list"));
			ArrayList<String> listsIgnore = new ArrayList<String>();
			if (coll.exists()) {
				int count = coll.getChildCount(new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_item_name"));
				System.out.println("忽略页面列表数量： " + count);
				/** 判断列表中的忽略数是否正确 */
				Assert.assertSame("判断列表中的忽略数是否正确 ", count, ignoreIntNumb);
				UiObject ignoreUpgrade = new UiObject(
						new UiSelector()
								.resourceId("com.mappn.gfan:id/tv_upgrade_num"));
				String ignore = ignoreUpgrade.getText();
				int ignoreIntNumber = Integer.parseInt(ignore);
				/** 判断忽略页面底部按钮显示的忽略数是否正确 */
				Assert.assertSame("判断忽略页面底部按钮显示的忽略数是否正确", count,
						ignoreIntNumber);

				for (int i = 0; i < count; i++) {
					UiObject ignoreName = new UiObject(new UiSelector()
							.resourceId("com.mappn.gfan:id/tv_item_name")
							.instance(i));
					if (ignoreName.exists()) {
						String appName = ignoreName.getText();
						listsIgnore.add(appName);
					}
				}

			}
			System.out.println("忽略的appname：" + listsIgnore);
		}

	}

	/**
	 * 忽略页面：取消忽略;全部取消忽略
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testIgnoreOperation() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		UiObject ignoreBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/manager_check_ignore_button"));
		ignoreBtn.click();
		UiObject clickIgnore = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_operation"));
		if (clickIgnore.exists()) {
			clickIgnore.click();

		} else {
			device.pressBack();// 返回升级页面
			UiObject nodata = new UiObject(
					new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
			System.out.println("忽略页面没有数据 ："  + nodata.getText());
			return;
		}
		device.pressBack();// 返回升级页面
		System.out.println("====分割线====单击忽略后重新获取忽略数量====");
		testIgnorePage();// 重新获取忽略数量

		UiObject ignoreALL = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/manager_cancel_ignore_all_button"));
		ignoreALL.click();
		sleep(2000);
		UiObject nodata = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		Assert.assertEquals(true, nodata.exists());
		System.out.println("全部取消忽略：" + nodata.getText());
		device.pressBack();// 返回升级页面
		System.out.println("====分割线====全部取消忽略后忽略数不存在====");
		UiObject ignoreNum = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_ignore_num"));
		Assert.assertEquals(false, ignoreNum.exists());
		ignoreBtn.click();
		UiObject ignoreUpgrade = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/tv_upgrade_num"));
		Assert.assertEquals(false, ignoreUpgrade.exists());
		Assert.assertEquals(true, nodata.exists());
		System.out.println("全部取消忽略：" + nodata.getText());
		device.pressBack();// 返回升级页面
		System.out.println("====分割线====全部取消忽略后重新获取升级可升级数量=====");
		testUpdatePage();

	}

	/**
	 * 安装包管理：获取安装包数量
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testPackageMana() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		device.click(660, 200);
		UiObject nodata = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (nodata.exists()) {
			System.out.println("安装包管理没有数据： " + nodata.getText());
			return;
		}
		UiCollection coll = new UiCollection(
				new UiSelector().resourceId("android:id/list"));
		ArrayList<String> lists = new ArrayList<String>();
		if (coll.exists()) {
			int count = coll.getChildCount(new UiSelector()
					.resourceId("com.mappn.gfan:id/not_tv_item_name"));
			System.out.println("有 " + count + "个安装包");
			for (int i = 0; i < count; i++) {
				UiObject packageName = new UiObject(new UiSelector()
						.resourceId("com.mappn.gfan:id/not_tv_item_name")
						.instance(i));
				if (packageName.exists()) {
					String packagename = packageName.getText();
					lists.add(packagename);
				}

			}
		}
		System.out.println("安装包： " + lists);
	}

	/**
	 * 安装包管理：选中;删除;全选;全部删除
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public void testPackageOperation() throws UiObjectNotFoundException {
		UiDevice device = getUiDevice();
		UiObject nodata = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/no_data"));
		if (nodata.exists()) {
			System.out.println("安装包管理没有数据： " + nodata.getText());
			return;
		}
		UiObject allDelete = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/all_delete_cb"));
		allDelete.click();
		UiObject checkedStatus = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/all_delete_cb"));
		Assert.assertEquals(true, checkedStatus.isChecked());
		System.out.println("点击[全选]√是否选中 ： " + checkedStatus.isChecked());
		allDelete.click();
		Assert.assertEquals(false, checkedStatus.isChecked());
		System.out.println("点击[全选]√是否选中 ： " + checkedStatus.isChecked());

		UiObject checked = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/all_delete_cb"));
		checked.click();
		Assert.assertEquals(true, checked.isChecked());
		System.out.println("点击√是否选中 ： " + checked.isChecked());
		checked.click();
		Assert.assertEquals(false, checked.isChecked());
		System.out.println("点击√是否选中 ： " + checked.isChecked());

		UiObject deleteBtn = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/rl_package_delete"));
		deleteBtn.click();
		UiObject messMillde = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/textview_middle"));
		System.out.println("提示框信息 ： " + messMillde.getText());

		UiObject confirm = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/button_right"));
		confirm.click();
		System.out.println("====分割线====删除一个安装包后重新获取安装包数量=====");
		testPackageMana();

		UiObject operation = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_operation"));
		if (operation.exists()) {
			operation.click();
		}
		UiObject install = new UiObject(
				new UiSelector()
						.resourceId("com.mappn.gfan:id/tv_option_install_state"));
		install.click();
		device.click(230, 1100);// 取消安装
		sleep(1000);
		UiObject backBtn = new UiObject(
				new UiSelector().resourceId("com.mappn.gfan:id/iv_left"));
		backBtn.click();

	}

}
