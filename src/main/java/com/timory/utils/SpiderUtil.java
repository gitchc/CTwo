package com.timory.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/*
 * 爬虫工具
 * */
public class SpiderUtil {
    /*
     * @url 爬虫地址
     * @tag 图片标签
     * @store分类存储地址
     * */
    public static void DownPIC(String url, String tag, String store) {
        String result1 = HttpUtil.get(url);
        Document document = Jsoup.parse(result1);
        Elements masonry_brick_ = document.select(tag);
        for (Element link : masonry_brick_) {
            String src = link.attr("src");
            HttpUtil.downloadFile(src, FileUtil.file(store));
        }
    }

    public static void main(String[] args) {
        DownPIC("https://www.mmonly.cc/mmtp/", ".masonry_brick img", "D://SPIDER//mmonly");
    }
}
