package com.mzc.demo.excell;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MaZhiCheng
 * @date 2020/11/20 - 13:37
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */
public class TestExcelReadDemo {

    public static void main(String[] args) {

        //实现读操作
        String filename = "E:\\write.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelLister()).sheet().doRead();
    }

    //创建一个方法，返回list
    private  static List<DemoData> getData(){

        List<DemoData> list = new ArrayList<>();
        for(int i = 0;i <10;i++){
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);

        }
        return list;

    }
}
