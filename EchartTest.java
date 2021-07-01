package com.example.demowp.echarts;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lcy
 * @date: 2021/6/30/18:08
 */
public class EchartTest {
    public static void main(String[] args) {
        //制造数据
        String industry="通信行业";
        String oneself="中兴";
        double ivalue=33.0;
        double ivalueOther=100-ivalue;
        double ovalue=70.0;
        double ovalueOther=100-ovalue;
        String text="天气情况";

        //    phantomjs d:\phantomjs\phantomjs-2.1.1-windows\echartsconvert\echarts-convert.js -s -p 6666
        // 模板参数
        //HashMap<String, Object> datas = new HashMap<>();
        Map<String,Object> datas=new HashMap<>();
        //aohour、aoList、aoxList对应模板/template/option.ftl中的x和y轴的名字,模板可自行修改，
        //ftl 为设置模板的名字。路径在/templates下
        datas.put("text",JSON.toJSONString(text));
        datas.put("industry", JSON.toJSONString(industry));
        datas.put("oneself", JSON.toJSONString(oneself));
        datas.put("ivalue", JSON.toJSONString(ivalue));
        datas.put("ivalueOther", JSON.toJSONString(ivalueOther));
        datas.put("ovalue",JSON.toJSONString(ovalue));
        datas.put("ovalueOther",JSON.toJSONString(ovalueOther));
        datas.put("ftl", "option.ftl");
        try {
            EchartsUtil.getImage(datas);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
