package com.zf.acl.libwrapper.country;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.zf.acl.libwrapper.R;
import com.zf.land.base.BaseActivity;
import com.zf.land.comm.utils.LogUtils;
import com.zf.land.comm.views.ClearEditText;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/18
 * @description: 国家和地方主页面
 */
public class CountryActivity extends BaseActivity {

    private static final String TAG = "CountryActivity";
    private ClearEditText cetCountry;
    private ListView countryLvList;
    private TextView countryDialog;
    private SideBar countrySidebar;

    private List<CountrySortModel> mAllCountryList;
    private CountrySortAdapter adapter;
    private CountryComparator pinyinComparator;
    private GetCountryNameSort countryChangeUtil;
    private CharacterParserUtil characterParserUtil;
    /**
     * 需要返回的activity
     */
    private static Activity mResultActivity = null;


    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_country;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        findViews();
        initView();
        setListener();
        getCountryList();
    }

    private void findViews(){
        cetCountry = findViewById(R.id.cetCountry);
        countryLvList = findViewById(R.id.country_lv_list);
        countryDialog = findViewById(R.id.country_dialog);
        countrySidebar = findViewById(R.id.country_sidebar);
    }

    /**
     * 初始化注册需要返回的activity
     *
     * @param activity
     */
    public static void initIntentActivity(Activity activity) {
        mResultActivity = activity;
    }

    /**
     * 初始化界面
     */
    private void initView() {
        countrySidebar.setTextView(countryDialog);
        mAllCountryList = new ArrayList<CountrySortModel>();
        pinyinComparator = new CountryComparator();
        countryChangeUtil = new GetCountryNameSort();
        characterParserUtil = new CharacterParserUtil();
        // 将联系人进行排序，按照A~Z的顺序
        Collections.sort(mAllCountryList, pinyinComparator);
        adapter = new CountrySortAdapter(this, mAllCountryList);
        countryLvList.setAdapter(adapter);

    }

    /**
     * 添加监听
     */
    private void setListener() {
        cetCountry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cetCountry.setText("");
                Collections.sort(mAllCountryList, pinyinComparator);
                adapter.updateListView(mAllCountryList);
            }
        });

        cetCountry.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchContent = cetCountry.getText().toString();
                if (searchContent.length() > 0) {
                    // 按照输入内容进行匹配
                    ArrayList<CountrySortModel> fileterList = (ArrayList<CountrySortModel>) countryChangeUtil
                            .search(searchContent, mAllCountryList);

                    adapter.updateListView(fileterList);
                } else {
                    adapter.updateListView(mAllCountryList);
                }
                countryLvList.setSelection(0);
            }
        });

        // 右侧sideBar监听
        countrySidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    countryLvList.setSelection(position);
                }
            }
        });

        countryLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
                String countryName = null;
                String countryNumber = null;
                String searchContent = cetCountry.getText().toString();

                if (searchContent.length() > 0) {
                    // 按照输入内容进行匹配
                    ArrayList<CountrySortModel> fileterList = (ArrayList<CountrySortModel>) countryChangeUtil
                            .search(searchContent, mAllCountryList);
                    countryName = fileterList.get(position).countryName;
                    countryNumber = fileterList.get(position).countryNumber;
                } else {
                    // 点击后返回
                    countryName = mAllCountryList.get(position).countryName;
                    countryNumber = mAllCountryList.get(position).countryNumber;
                }
                if (mResultActivity == null) {
                    throw new UnsupportedOperationException("mResultActivity cannot be null");
                } else {
                    Intent intent = new Intent();
                    intent.setClass(CountryActivity.this, mResultActivity.getClass());
                    intent.putExtra("countryName", countryName);
                    intent.putExtra("countryNumber", countryNumber);
                    setResult(RESULT_OK, intent);
                    LogUtils.i(TAG, "countryName: + " + countryName + "countryNumber: " + countryNumber);
                    finish();
                }
            }
        });

    }


    /**
     * 获取国家列表
     */
    private void getCountryList() {
        String[] countryList = getResources().getStringArray(R.array.country_code_list_ch);

        for (int i = 0, length = countryList.length; i < length; i++) {
            String[] country = countryList[i].split("\\*");

            String countryName = country[0];
            String countryNumber = country[1];
            String countrySortKey = characterParserUtil.getSelling(countryName);
            CountrySortModel countrySortModel = new CountrySortModel(countryName, countryNumber,
                    countrySortKey);
            String sortLetter = countryChangeUtil.getSortLetterBySortKey(countrySortKey);
            if (sortLetter == null) {
                sortLetter = countryChangeUtil.getSortLetterBySortKey(countryName);
            }

            countrySortModel.sortLetters = sortLetter;
            mAllCountryList.add(countrySortModel);
        }

        Collections.sort(mAllCountryList, pinyinComparator);
        adapter.updateListView(mAllCountryList);
        LogUtils.i(TAG, "getCountryList size :" + mAllCountryList.size());
    }


//    @Override
//    protected void setStatusBar() {
//        super.setStatusBar();
//        StatusBarUtil.setLightMode(this);
////        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
//    }
}
