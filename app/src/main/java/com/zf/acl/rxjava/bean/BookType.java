package com.zf.acl.rxjava.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wang.xiaotong
 * @github: https://github.com/zhufengi
 * @time: 2018/10/17
 * @description: BookType
 */
public class BookType {
    public String life;
    public String history;

    public BookType(String life,String history){
        this.life = life;
        this.history = history;
    }

    /**
     * 模拟booktype数据
     * @param n
     */
    public static BookType buildBookTypeData(int n){
        BookType bookType = null;
        for (int i=0;i<n;i++){
           bookType = new BookType("life"+i,"history"+i);
        }
        return bookType;
    }

    /**
     * 模拟booktype list数据
     * @param n
     * @return
     */
    public static List<BookType> buildBookTypeListData(int n){
        List<BookType> bookTypes = new ArrayList<>();
        for (int i=0;i<n;i++){
           bookTypes.add(new BookType("life"+i,"history"+i));
        }
        return bookTypes;
    }
}
