package com.timory.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


/*
 * 爬虫工具
 * */
public class SpiderUtil {
    /*
     * @url 爬虫地址
     * @tag 图片标签
     * @store 分类存储地址
     * */
    public static void DownPIC(String url, String tag, String store) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        String result1 = HttpUtil.get(url);
        Document document = Jsoup.parse(result1);
//        Document document = Jsoup.connect(url).get();
        Elements masonry_brick = document.select(tag);
        int i = 0;
        for (Element link : masonry_brick) {
            i++;
            String src = link.attr("src");
            String alt = link.attr("alt");
            String last = src.substring(src.lastIndexOf("."), src.length());
            String picName = i + "-" + alt + last;
            HttpUtil.downloadFile(src, FileUtil.file(store + picName));
            System.out.println("Done:" + picName);
        }
    }

    public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        DownPIC("https://www.mmonly.cc/mmtp/", ".masonry_brick img", "D://SPIDER//mmonly//");
    }
}
