package spider.phantomjsUtil;


import com.common.utils.SpiderUtils;

public class config {


    public static String getConfigFile(){
        if (SpiderUtils.isOSLinux()){
            System.out.println("jar包===："+"/home/phantomjs/config.ini");
            return "/home/phantomjs/config.ini";
        }else{
            System.out.println("jar包====："+"F:/phantomjs/phantomjs-2.1.1-windows/bin/config.ini");
            return "F:/phantomjs/phantomjs-2.1.1-windows/bin/config.ini";
        }
    }

}
