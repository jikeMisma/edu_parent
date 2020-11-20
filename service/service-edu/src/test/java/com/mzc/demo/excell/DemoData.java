package com.mzc.demo.excell;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author MaZhiCheng
 * @date 2020/11/20 - 13:18
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */
@Data
public class DemoData {

    //设置excel表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;




}
