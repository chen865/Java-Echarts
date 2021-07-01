package com.example.demowp.echarts;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import freemarker.template.TemplateException;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lcy
 * @date: 2021/6/30/17:47
 */
public class EchartsUtil {
    /**
     * 临时文件夹路径
     */
    public static final String TEMP_FILE_PATH = "D:/report/echart/";
    private static final String SUCCESS_CODE = "1";
    private static final Logger logger = LoggerFactory.getLogger(EchartsUtil.class);

    public static String generateEchartsBase64(String option) throws ClientProtocolException, IOException {
        String base64 = "";
        if (option == null) {
            return base64;
        }
        option = option.replaceAll("\\s+", "").replaceAll("\"", "'");

        // 将option字符串作为参数发送给echartsConvert服务器
        Map<String, String> params = new HashMap<>();
        params.put("opt", option);
        String response = HttpUtil.post("http://localhost:6666", params, "utf-8");

        // 解析echartsConvert响应
        JSONObject responseJson = JSON.parseObject(response);
        String code = responseJson.getString("code");

        // 如果echartsConvert正常返回
        if (SUCCESS_CODE.equals(code)) {
            base64 = responseJson.getString("data");
        }
        // 未正常返回
        else {
            String string = responseJson.getString("msg");
            throw new RuntimeException(string);
        }
        return base64;
    }

    public static void getImage(Map<String, Object> datas) throws IOException {
        // 生成option字符串
        String option = null;
        try {
            if (!datas.containsKey("ftl")){
                logger.error("没有指定生成图表的模板！");
            }
            option = FreemarkerUtil.generateString((String) datas.get("ftl"), "/templates", datas);
        } catch (TemplateException e) {
            logger.error(e.getMessage());
        }
        // 根据option参数
        String base64 = generateEchartsBase64(option);
        File file = new File(TEMP_FILE_PATH);
        if(!file.exists()) {
            // 如果不存在就创建文件
            file.mkdir();
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try (OutputStream out = new FileOutputStream(TEMP_FILE_PATH+"test.jpg")){
            // 解密
            byte[] b = decoder.decodeBuffer(base64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            out.write(b);
            out.flush();
        }
    }
}
