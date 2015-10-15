package com.linpeng.baidunewssearch;

/**
 * Created by Administrator on 2015/10/13.
 */
public class Test {
    private static  String s = "<script type=\"text/javascript\">\n" +
            "document.write(\"Hello World!\")\n" +
            "</script>\n" +
            "1、一天，我去街上买扇子，我问多少钱，他说五块钱。<BR>";
    public static void main(String[] args){
        System.out.println(s.substring(73));
        String s ="MD5:  1A:15:21:C6:1A:F9:81:29:90:BE:A8:00:86:65:CA:0D";
        s=s.toLowerCase();
        s=s.replace(":","");
        System.out.println(s);
    }
}
