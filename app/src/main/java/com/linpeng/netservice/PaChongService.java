package com.linpeng.netservice;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Administrator on 2015/10/9.
 */
public class PaChongService {


    private static HttpResponse hr;
    public static void main(String[] args){
        String qiangxie = "http://www.qiangxie.cn/";
        HttpClient hc = new DefaultHttpClient();
//        HttpGet get = new HttpGet(qiangxie);
//        try {
//            hr=hc.execute(get);
//            System.out.println(hr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("hello,world");
    }

}
