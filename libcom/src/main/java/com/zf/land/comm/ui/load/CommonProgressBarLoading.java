package com.zf.land.comm.ui.load;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zf.land.R;
import com.zf.land.comm.views.FadeInTextView;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/15
 * @description: BounceLoading
 * ProgressBar 通用加载动画
 */
public class CommonProgressBarLoading {
    private FadeInTextView mFadeInTextView;
    private Dialog loadingDialog;
    private Context mContext;

    /**
     *
     * @param context
     */
    public CommonProgressBarLoading(Context context) {
        mContext = context;
        createLoadingDialog(mContext,"拼命加载中...");
    }

    /**
     *
     * @param context
     * @param loadingMessage
     */
    public CommonProgressBarLoading(Context context, String loadingMessage) {
        mContext = context;
        createLoadingDialog(mContext,loadingMessage);
    }


    public Dialog createLoadingDialog(Context context,String loadingMessage) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_loading_view);// 加载布局
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(loadingMessage);// 设置加载信息
        loadingDialog = new Dialog(context, R.style.Dialog_ProgressBar_Comm_Style);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(true); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /**
         *将显示Dialog的方法封装在这里面
         */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        return loadingDialog;
    }

    /**
     * 关闭dialog
     * http://blog.csdn.net/qq_21376985
     */
    public void closeLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public void showLoading() {
        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }
}