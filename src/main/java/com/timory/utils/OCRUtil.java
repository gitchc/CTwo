package com.timory.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class OCRUtil {


    private static String OCRUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
    private static final String ACCESS_TOKEN = getAccessToken();

    /**
     * 获取AccessToken
     * 百度开发
     * AppId:10028388
     * APIKey:kdZU5aOeI7FguVfWzql7LOGM
     * SecretKey:Xxcze1I2RLUhB8NFd7T4u4fHdBGundrn
     *
     * @return
     */
    public static String getAccessToken() {
        String accessToken = "";
        HashMap<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", "rCq6W0TLjRbtYFjcITdomHKW");
        params.put("client_secret", "jp4B2vbVjL2oEEsxDZWug80o464P9mYg");
        String url = "https://aip.baidubce.com/oauth/2.0/token";
        String json = HttpUtil.get(url, params);
        JSONObject jsonObject = JSONObject.parseObject(json);
        if (jsonObject != null && !jsonObject.isEmpty()) {
            accessToken = jsonObject.getString("access_token");
        }
        return accessToken;
    }

    /**
     * 获取识别验证码
     *
     * @param imageUrl
     * @return
     */
    public static String OCRVCode(String imageUrl) {
        String VCode = "";

        if (StringUtils.isBlank(ACCESS_TOKEN)) {
            return VCode;
        }
        OCRUrl = OCRUrl + "?access_token=" + ACCESS_TOKEN;

        HashMap<String, Object> params = new HashMap<>();
        imageUrl = encodeImgageToBase64(imageUrl);
        params.put("image", imageUrl);
        String json = HttpUtil.post(OCRUrl, params);
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray wordsResult = jsonObject.getJSONArray("words_result");
        VCode = wordsResult.getJSONObject(0).getString("words");
        return VCode;
    }

    /**
     * 将本地图片进行Base64位编码
     *
     * @param imageFile
     * @return
     */
    public static String encodeImgageToBase64(String imageFile) {
        // 其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imageFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码
        return Base64.encode(data);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(OCRVCode("D://ygrcode.png"));
    }
}