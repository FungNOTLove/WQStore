package com.fung.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fung.domain.User;
import com.fung.service.UserService;
import com.fung.service.impl.UserServiceImpl;
import com.fung.util.UUIDUtils;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取发送过来的method的值,判断是注册信息还是验证名字
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		System.out.println(method);
		if (method.equals("regist")) {//代表注册
			register(request,response);
		}else if(method.equals("checkname")){//检查名字
			checkName(request,response);
		}
		

	}

	private void checkName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*
		 * 想要检查名字,必须获取名字
		 */
		String username = request.getParameter("username");
		UserService us = new UserServiceImpl();
		User user = us.checkUserName(username);
		if (user==null) {//用户名可用
			response.getWriter().write("true");
		}else{
			response.getWriter().write("false");
			
		}
		
	}
	
	/**
	 * 进行注册
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void register(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File temp = new File(this.getServletContext().getRealPath("/temp"));
		if (!temp.exists()) {
			temp.mkdirs();
		}
		factory.setRepository(temp);
		factory.setSizeThreshold(1024*1024*50);
		//servletfileupload
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		//判断表单是否是多组件
		boolean flag = fileUpload.isMultipartContent(request);
		if (!flag) {//如果不是多组件  那么 return
			return;
		}
		User user = new User();
		try {
			//是多组件,获取每一个组件
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			//fileItem  代表的就是 这个集合中的每一个元素
			for (FileItem fileItem : fileItems) {
				if (fileItem.isFormField()) {
					//普通组件
					isNormalField(fileItem,user);
					
				}else{
					//上传组件
					isUploadField(fileItem,user);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		String uid = UUIDUtils.getUserId();
		user.setUid(uid);//所有信息封装完毕
		//将数据保存到数据库中
		UserService us = new UserServiceImpl();
		int rows = us.registerUser(user);
		HttpSession session = request.getSession();
		if (rows!=0) {
			//注册成功的页面
			//向session中保存一个数据   为了方便判断是注册成功还是失败
			/*
			 * 1.获取session对象
			 * 2.保存数据
			 */
			//第一个参数  key   第二个参数就是value
			session.setAttribute("msg", "success");
			response.sendRedirect(request.getContextPath()+"/jsp/success_fail.jsp");
		}else{
			//注册失败的页面
			session.setAttribute("msg", "fail");
			response.sendRedirect(request.getContextPath()+"/jsp/success_fail.jsp");
		}
	}

	private void isNormalField(FileItem fileItem, User user) {
		String fieldName = fileItem.getFieldName();
		String value="";
		try {
			value = fileItem.getString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (fieldName.equals("username")) {
			user.setUsername(value);
		}else if (fieldName.equals("password")) {
			user.setPassword(value);
		}else if (fieldName.equals("name")) {
			user.setName(value);
		}else if (fieldName.equals("email")) {
			user.setEmail(value);
		}else if (fieldName.equals("sex")) {
			user.setSex(value);
		}
	}

	private void isUploadField(FileItem fileItem, User user) {
		//找到头像所在的文件夹
		String dirPath = this.getServletContext().getRealPath("/photo");
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		/**
		 * 先要把图片储存起来
		 * 将图片路径 赋值给user的photo属性
		 */
		//获取图片名字
		String fileName = fileItem.getName();
		String photoName = UUIDUtils.getFileName(fileName);
		File photoFile = new File(dir, photoName);
		try {
			fileItem.write(photoFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setPhoto("/photo"+File.separator+photoName);
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}









