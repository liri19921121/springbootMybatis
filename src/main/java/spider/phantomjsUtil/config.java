package spider.phantomjsUtil;


import java.util.Properties;

public class config {


    public static String getConfigFile(){
        if (isOSLinux()){
            System.out.println("jar包===："+"/home/phantomjs/config.ini");
            return "/home/phantomjs/config.ini";
        }else{
            System.out.println("jar包====："+"F:/phantomjs/phantomjs-2.1.1-windows/bin/config.ini");
            return "F:/phantomjs/phantomjs-2.1.1-windows/bin/config.ini";
        }

    }
    /**
     * linux返回true
     * @return
     */
    public static boolean isOSLinux() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            return true;
        } else {
            return false;
        }
    }

}
