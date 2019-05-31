package com.fung.util;

import java.util.UUID;

public class UUIDUtils {
	
	public static String getFileName(String fileName){
		//1.获取上传文件后缀
		int index = fileName.lastIndexOf(".");
		String last = fileName.substring(index);
		//2.创建一个UUID
		String uuid = UUID.randomUUID().toString();
		//3.去掉UUID中的-
		uuid = uuid.replace("-", "");
		//4.拼接
		String name = uuid+last;
		
		return name;
	}
	
	public static String getUserId(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		return uuid;
	}
	
}