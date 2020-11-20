package com.mzc.demo.excell;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author MaZhiCheng
 * @date 2020/11/20 - 13:32
 * @motto 腹有诗书气自华
 * @博客地址 https://blog.csdn.net/mzc_love
 */
public class ExcelLister extends AnalysisEventListener<DemoData> {

    //一行一行读取Excel中的内容
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        System.out.println("***" + data);
    }

    //读取完成之后做的事情
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //读取表头的方法
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头" + headMap);
    }

    //读取完成之后
//    @Override
//    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//
//    }


}
