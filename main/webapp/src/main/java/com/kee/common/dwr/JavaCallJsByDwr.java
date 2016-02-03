package com.kee.common.dwr;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;

public class JavaCallJsByDwr {

	public static void info(final String message) {
		try {
			Browser.withCurrentPageFiltered(new ScriptSessionForKcbFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("showMessage", new Object[] { message });
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void progressPercent(final String text) {
		try {
			Browser.withCurrentPageFiltered(new ScriptSessionForKcbFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("progressPercent", new Object[] { text });
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void info(final String shopid, final String message) {
		try {
			Browser.withCurrentPageFiltered(new ScriptSessionForKcbFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("showDownMessage", shopid, new Object[] { message });
				}
			});
		} catch (Exception e) {
		}
	}

	/**
	 * 更新登陆状态
	 * 
	 * @param shopid
	 * @param status
	 */
	public static void updateLoginStatus(final String clientid, final String shopid, final String status) {
		try {
			
			Browser.withAllSessionsFiltered(new ScriptSessionForDownloadOrderPageFilter(clientid), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("updateLoginStatus",shopid,status);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新登陆状态
	 * 
	 * @param shopid
	 * @param status
	 */
	public static void addShopStatusMsg(final String clientid,final String status) {
		try {
			
			Browser.withAllSessionsFiltered(new ScriptSessionForDownloadOrderPageFilter(clientid), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("addShopStatusMsg", status);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void uploadInfo(final Integer id, final String sendcode, final String msg) {
		try {

			Browser.withCurrentPageFiltered(new ScriptSessionForKcbFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("updateRow", id, sendcode, msg);
				}
			});
		} catch (Exception e) {

		}
	}

	/**
	 * indexedDB删除信息
	 * 
	 * @param sendcode
	 *            快递单号
	 */
	public static void indexedDB_remove(final String sendcode) {
		try {
			Browser.withAllSessionsFiltered(new ScriptSessionForCurrentClientFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("indexedDB_remove", new Object[] { sendcode });
				}
			});
		} catch (Exception e) {
		}
	}

	/**
	 * 本地数据库更新
	 */
	public static void indexedDB_update(final String msg) {
		try {
			Browser.withAllSessionsFiltered(new ScriptSessionForCurrentClientFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("indexedDB_update", new Object[] { msg });
				}
			});
		} catch (Exception e) {
		}
	}

	public static void shopface_indexedDB_update(final String sendcode) {
		try {
			Browser.withAllSessionsFiltered(new ScriptSessionForCurrentClientFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("shopface_indexedDB_update", new Object[] { sendcode });
				}
			});
		} catch (Exception e) {
		}
	}

	// 锁定更新
	public static void indexedDB_lock_updated(final String sendcode) {
		try {
			Browser.withAllSessionsFiltered(new ScriptSessionForPageFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("indexedDB_lock_updated", new Object[] { sendcode });
				}
			});
		} catch (Exception e) {
		}
	}

	public static void infoByMethod(final String message, final String method) {
		try {
			Browser.withCurrentPageFiltered(new ScriptSessionForKcbFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall(method, new Object[] { message });
				}
			});
		} catch (Exception e) {
		}
	}

	public static void msg(final String message) {
		try {
			Browser.withCurrentPageFiltered(new ScriptSessionForKcbFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("showInfo", new Object[] { message });
				}
			});
		} catch (Exception e) {
		}
	}

	/**
	 * 增加数量，针对客户过滤
	 * 
	 * @param menu
	 *            二级菜单名称，与前台对应.如："菜单处理"
	 * @param number
	 *            显示的数量 调用实例：JavaCallJsByDwr.addNumber("异常订单", 14);
	 */
	public static void addNumber(final String menu, final Integer number, final String targetClientid) {
		try {
			Browser.withAllSessionsFiltered(new ScriptSessionForClientFilter(targetClientid), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("kcbmain.treeAddMsg", menu, number);
				}
			});
		} catch (Exception e) {
		}
	}

	/**
	 * updateUnreadNoticeCountByDwr 减少数量，针对客户过滤
	 * 
	 * @param menu
	 *            二级菜单名称，与前台对应.如："菜单处理"
	 * @param number
	 *            显示的数量 调用实例：JavaCallJsByDwr.subNumber("异常订单", 5);
	 */
	public static void subNumber(final String menu, final Integer number) {
		try {
			Browser.withAllSessionsFiltered(new ScriptSessionForClientFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("kcbmain.treeAddMsg", menu, -number);
				}
			});
		} catch (Exception e) {
		}
	}

	/**
	 * 改变通知数据
	 */
	public static void updateUnreadNoticeCount(final Integer number) {
		try {
			Browser.withAllSessionsFiltered(new ScriptSessionForClientFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("updateUnreadNoticeCountByDwr", number);
				}
			});
		} catch (Exception e) {
		}
	}

	public static void updateLoadingMsg(final String text) {
		try {
			Browser.withAllSessionsFiltered(new ScriptSessionForClientFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("updateLoadingMsg", text);
				}
			});
		} catch (Exception e) {
		}
	}

	public static void setTreeCount() {
		try {
			Browser.withCurrentPageFiltered(new ScriptSessionForKcbFilter(), new Runnable() {
				@Override
				public void run() {
					ScriptSessions.addFunctionCall("parent.setTreeCount");
				}
			});
		} catch (Exception e) {
		}
	}
}
