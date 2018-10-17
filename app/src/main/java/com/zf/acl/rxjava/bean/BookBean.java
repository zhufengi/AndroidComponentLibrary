package com.zf.acl.rxjava.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/17
 * @description: BookBean
 */
public class BookBean {
    public String name;
    public List<BookType> bookTypes = new ArrayList<>();

    public BookBean(String name,List<BookType> bookTypes){
        this.name = name;
        this.bookTypes = bookTypes;
    }

    /**
     * 模拟数据
     * @param n
     * @return
     */
    public static BookBean buildBookData(int n){
        BookBean bookBean = null;
        for (int i=0;i<n;i++){
          bookBean = new BookBean("name"+i,BookType.buildBookTypeListData(n));
        }
        return bookBean;
    }

    /**
     * 模拟数据 list
     * @param n
     * @return
     */
    public static List<BookBean> buildBookListData(int n){
        List<BookBean> bookBeans = new ArrayList<>();
        for (int i=0;i<n;i++){
         bookBeans.add(new BookBean("name"+i,BookType.buildBookTypeListData(n)));
        }
        return bookBeans;
    }
}
