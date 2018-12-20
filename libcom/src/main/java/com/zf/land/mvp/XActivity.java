package com.zf.land.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/17
 * @description: XActivity
 */
public interface XActivity {

    /**
     * 初始化 View, 如果返回 0, 框架则不会调用
     *
     * @param savedInstanceState
     * @return
     */
    int initView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    void initData(@Nullable Bundle savedInstanceState);
}
