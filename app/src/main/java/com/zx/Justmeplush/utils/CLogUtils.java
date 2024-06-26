package com.zx.Justmeplush.utils;

import android.util.Log;

import com.zx.Justmeplush.config.MainConfig;

import de.robv.android.xposed.XposedBridge;


public class CLogUtils {

	//规定每段显示的长度
	private static int LOG_MAXLENGTH = 2000;

	private static String TAG = "XposedInto";

	public static void e(String msg){
		XposedBridge.log(msg);
			if(MainConfig.isDebug) {
				InfiniteLog(TAG, msg);
			}
	}

	public static void e(String TAG, String msg){
		if(!MainConfig.isDebug) {
			InfiniteLog(TAG, msg);
		}
	}

	/**
	 * log最多 4*1024 长度 这个 方法 可以解决 这个问题
	 * @param TAG
	 * @param msg
	 */
	private static void InfiniteLog(String TAG, String msg) {
		int strLength = msg.length();
		int start = 0;
		int end = LOG_MAXLENGTH;
		for (int i = 0; i < 100; i++) {
			//剩下的文本还是大于规定长度则继续重复截取并输出
			if (strLength > end) {
				Log.e(TAG + i, msg.substring(start, end));
				start = end;
				end = end + LOG_MAXLENGTH;
			} else {
				Log.e(TAG, msg.substring(start, strLength));
				break;
			}
		}
	}


}
