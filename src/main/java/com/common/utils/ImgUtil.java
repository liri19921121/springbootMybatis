package com.common.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ImgUtil {

	@Value("${upload.root.path}")
	public static String root_fold;

	 /**
	 * 上传文件
	 * @param filePath 文件名 
	 * @param in   io流
	 * @return  返回最终的路径
	 * @throws IOException 
	 */
	public static String uploadImg(String rootPATH,String filePath,InputStream in) throws IOException{
		System.out.println("rootPATH="+rootPATH);
		System.out.println("filePath="+filePath);
		String rusultPath = rootPATH+filePath;
		createFile(rusultPath);
		File realFile =new File(rusultPath);
		FileUtils.copyInputStreamToFile(in, realFile);
		return "/upload"+filePath;
	}
	
	
	 /**
     * 文件或文件夹不存在则创建
     * @param dir 文件夹
     * @param filepath 文件名
     */
	public static void createFile(String dir,String filepath){
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		file = new File(dir+filepath);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("文件创建失败");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 创建文件，如果文件夹不存在将被创建
	 * 
	 * @param destFileName
	 *            文件路径
	 */
	public static File createFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists())
			return null;
		if (destFileName.endsWith(File.separator))
			return null;
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs())
				return null;
		}
		try {
			if (file.createNewFile())
				return file;
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 替换base64的前缀
	 * @param pics
	 * @return
	 */
	public static String replaceBase64Before(String pics){
		pics = pics.replace("data:image/png;base64,", "");
		pics = pics.replace("data:image/jpeg;base64,", "");
		pics = pics.replace("data:image/bmp;base64,", "");
		pics = pics.replace("data:image/x-icon;base64,", "");
		pics = pics.replace("data:image/gif;base64,", "");
		return pics;
	}

	public static void main(String[] args) throws Exception {
		String imgUrl = "https://gss0.bdstatic.com/5bVWsj_p_tVS5dKfpU_Y_D3/res/r/image/2018-12-13/53b9dada603935ee60d089c6ca53d66a.png";
		//获得图片名字
		String imgName = imgUrl.substring(imgUrl.lastIndexOf("/"),imgUrl.length());
		//new一个URL对象
		URL url = new URL(imgUrl);
		//打开链接
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		//设置请求方式为"GET"
		conn.setRequestMethod("GET");
		//超时响应时间为5秒
		conn.setConnectTimeout(5 * 1000);
		//通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		//得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = readInputStream(inStream);
		//new一个文件对象用来保存图片，默认保存当前工程根目录
		createFile(root_fold);
		File imageFile = new File(root_fold+imgName);
		//创建输出流
		FileOutputStream outStream = new FileOutputStream(imageFile);
		//写入数据
		outStream.write(data);
		//关闭输出流
		outStream.close();
	}

	//把文件保存在本地
	public static String saveLocal(String title,String imgUrl){
		try {
			//获得图片名字
			String imgName = imgUrl.substring(imgUrl.lastIndexOf("/")+1,imgUrl.length());
			//new一个URL对象
			URL url = new URL(imgUrl);
			//打开链接
			HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
			/*HttpURLConnection conn = (HttpURLConnection)url.openConnection();*/
			//设置请求方式为"GET"
			conn.setRequestMethod("GET");
			//超时响应时间为5秒
			conn.setConnectTimeout(50 * 1000);
			//通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			//得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);
			//new一个文件对象用来保存图片，默认保存当前工程根目录
			String path = "G:/upload/"+title;
			createFile(path);
			File imageFile = new File(path+imgName);
			//创建输出流
			FileOutputStream outStream = new FileOutputStream(imageFile);
			//写入数据
			outStream.write(data);
			//关闭输出流
			outStream.close();
			return imgName;
		}catch (Exception e){
			return null;
		}
	}


	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		//使用一个输入流从buffer里把数据读取出来
		while( (len=inStream.read(buffer)) != -1 ){
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		//关闭输入流
		inStream.close();
		//把outStream里的数据写入内存
		return outStream.toByteArray();
	}
}
