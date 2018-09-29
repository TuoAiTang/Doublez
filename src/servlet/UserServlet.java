package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dao.UserDAO;
import entity.User;

public class UserServlet extends HttpServlet{
	
	public UserDAO ud = new UserDAO();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String method = request.getParameter("method");
		PrintWriter out = response.getWriter();
		
		String email;
		String password;
		
		switch(method){
			
		case "login":
			email = request.getParameter("email");
			password = request.getParameter("password");
			
			try {
				JSONObject jsonobj = new JSONObject();
				if(ud.getUserByEmail(email) == null) {
					jsonobj.put("code", 1);
					jsonobj.put("msg", "该用户名不存在");
					out.println(jsonobj);
					return;
				}
				if(!ud.checkPassword(email, password)) {
					jsonobj.put("code", 2);
					jsonobj.put("msg", "密码错误");
					out.println(jsonobj);
					return;
				}
				else {
					jsonobj.put("code", 0);
					jsonobj.put("msg", "登录成功");
					out.println(jsonobj);
					return;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "register":
			email = request.getParameter("email");
			password = request.getParameter("password");
			String avatar = request.getParameter("avatar");
			User u = new User();
			u.setEmail(email);
			u.setPassword(password);
			u.setAvatar(avatar);
			try {
				JSONObject jsonobj = new JSONObject();
				if(ud.insert(u) != 0) {
					jsonobj.put("code", 1);
					jsonobj.put("msg", "注册失败");
					out.println(jsonobj);
				}else {
					jsonobj.put("code", 0);
					jsonobj.put("msg", "注册成功");
					out.println(jsonobj);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
			
		default:
			break;			
		}
	}

}
