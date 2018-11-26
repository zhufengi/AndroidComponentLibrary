package com.zf.land.comm.root;


import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/11/26
 * @description: RootUtil
 * 检测设备root工具类
 */
public class RootUtil {
	private final static String TAG = "RootUtil";
	public static boolean isDeviceRooted() {
		//如果当前的版本是master版本B170，即使手机root了通知栏也不显示root提示
		String moblie_version = SystemProperties.get("ro.build.version.incremental");
		if(!"".equals(moblie_version )&& moblie_version != null){
			if("C01B170".equalsIgnoreCase(moblie_version)){
				return  false;
			}
		}
		if("1".equals(SystemProperties.get("ro.debuggable"))){
			return  true;
		}
		if (isRoot()){return true;}
		return false;
	}
	/** 判断手机是否root，不弹出root请求框<br/> */
	private static boolean isRoot() {
		String binPath = "/system/bin/su";
		String xBinPath = "/system/xbin/su";
		if (new File(binPath).exists() && isExecutable(binPath))
			return true;
		if (new File(xBinPath).exists() && isExecutable(xBinPath))
			return true;
		return false;
	}

	private static boolean isExecutable(String filePath) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("ls -l " + filePath);
			// 获取返回内容
			BufferedReader in = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String str = in.readLine();
			Log.i(TAG, str);
			if (str != null && str.length() >= 4) {
				char flag = str.charAt(3);
				if (flag == 's' || flag == 'x')
					return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(p!=null){
				p.destroy();
			}
		}
		return false;
	}
}
