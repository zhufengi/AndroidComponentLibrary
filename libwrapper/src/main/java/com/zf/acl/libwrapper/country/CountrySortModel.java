package com.zf.acl.libwrapper.country;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/12/18
 * @description: 国家排序model
 */
public class CountrySortModel extends CountryModel {

    /**显示数据拼音的首字母*/
    public String sortLetters;
    public CountrySortToken sortToken = new CountrySortToken();

    /**
     *
     * @param name
     * @param number
     * @param countrySortKey
     */
    public CountrySortModel(String name, String number, String countrySortKey) {
        super(name, number, countrySortKey);
    }

}
