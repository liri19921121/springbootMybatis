package com.common.utils;

import com.alibaba.druid.util.StringUtils;
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

	/**
	 * 文件或文件夹不存在则创建
	 * @param dir 文件夹
	 * @param filepath 文件名
	 */
	public static void createDirFile(String dir){
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
	}

	/**
	 * @param dir 子目录
	 * @param fileUrl 图片链接
	 * @return
	 */
	public static String uploadQianURL(String dir,String fileUrl) {
		//获取文件名，文件名实际上在URL中可以找到
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/")+1,fileUrl.length());
		//这里服务器上要将此图保存的路径
		String savePath = "F:/uoload/sh/";
		if (!StringUtils.isEmpty(dir)){
			savePath = savePath +dir+"/";
		}
		createDirFile(savePath);
		try {
			/*将网络资源地址传给,即赋值给url*/
			URL url = new URL(fileUrl);
			/*此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流*/
			HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
			//服务器的安全设置不接受Java程序作为客户端访问，解决方案是设置客户端的User Agent
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			DataInputStream in = new DataInputStream(connection.getInputStream());
			/*此处也可用BufferedInputStream与BufferedOutputStream*/
			DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath+fileName));
			/*将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址*/
			byte[] buffer = new byte[4096];
			int count = 0;
			/*将输入流以字节的形式读取并写入buffer中*/
			while ((count = in.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}
			out.close();/*后面三行为关闭输入输出流以及网络资源的固定格式*/
			in.close();
			connection.disconnect();
			//返回内容是保存后的完整的URL
			/*网络资源截取并存储本地成功返回true*/
			return savePath+fileName;
		} catch (Exception e) {
			System.out.println(e + fileUrl + savePath);
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
