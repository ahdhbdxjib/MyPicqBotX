package com.idhclub.picq.spider;


import com.idhclub.picq.pojo.Product;
import com.idhclub.picq.pojo.ShopifyStats;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 驱动爬虫
 */
public class GetProductData {

    private static Logger logger = Logger.getLogger(GetProductData.class);
    public static final String SEARCH_URL = "http://team.augustye.net/shopify/selection/stats";
//connect.sid
    /**
     *
     */
    public static String cookie = "connect.sid=s%3AwMRyFg1YnBBSVcSpEYvwjLibdcWveZFQ.5%2BRCfcvYru%2F6k%2FSOLCSkDaRuvQL%2BIeQ44%2Bip2HqctSA; SL_GWPT_Show_Hide_tmp=1; SL_wptGlobTipTmp=1";

    public static ShopifyStats getDta() {
        logger.info(" search url = " + SEARCH_URL);

        try {
            Connection connect = Jsoup.connect(SEARCH_URL);//连接网络请
            Map<String, String> header = new HashMap<String, String>();
            header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            header.put("Accept-Encoding", "gzip, deflate");
            header.put("Cache-Control", "no-cache");
            header.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            header.put("Connection", "keep-alive");
            header.put("Cookie", cookie);
            header.put("Pragma", "no-cache");
            header.put("Upgrade-Insecure-Requests", "1");
            header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
            Connection.Response response = connect.headers(header).method(Connection.Method.GET).execute();
            if (response.statusCode() == 200) {
                logger.info("数据访问");
                return parse(response.body());
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("爬虫连接错误");
            try {
                Thread.sleep(30000);
                getDta();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }

    private static ShopifyStats parse(String response) {
        Document document = Jsoup.parse(response);
        Elements elements = document.select("table[id=stats]").get(0).select("tr");
        ShopifyStats shopifyStats = new ShopifyStats();
        shopifyStats.setRegion(elements.select("td").get(0).text());
        shopifyStats.setUser(elements.select("td").get(1).text());
        shopifyStats.setOrders(elements.select("td").get(2).text());
        shopifyStats.setSold(elements.select("td").get(3).text());
//        商品详情
        Elements getProducts = elements.select("td").get(4).getAllElements();
        List<String> products = new ArrayList<String>();
        for (int i = 0; i < getProducts.size(); i++) {
            if(getProducts.get(i).toString().startsWith("<div style=\"white-space:nowrap\">")){
                products.add(getProducts.get(i).text().toString());
            }
        }
        shopifyStats.setProducts(products);
        logger.info("数据提取成功");
        return shopifyStats;


    }


    public static void main(String[] args) {
        System.out.println(getDta());
    }
}
