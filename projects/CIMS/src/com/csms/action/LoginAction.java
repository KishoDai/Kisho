package com.csms.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.csms.common.CommonAction;
import com.csms.common.JSON;
import com.csms.pojo.User;
import com.csms.utils.Constants;
import com.csms.utils.MD5Util;
import com.opensymphony.xwork2.ActionContext;

@Component("loginAction")
public class LoginAction extends CommonAction<User> {
	
	private static final long serialVersionUID = -982747095799056192L;

	private User user;
	
	private String validatecode;
	
	protected Map<String, Object> paramMap = new HashMap<String, Object>();
	
	public void check() {
		log.debug("*********************登录开始***********************");
//		log.debug("ur_id=" + user.getUr_id());
//		String code = (String) ActionContext.getContext().getSession().get(Constants.VALIDATE_CODE);
//		log.debug("validatecode=" + validatecode);
//		log.debug("code=" + code);
//		if (!code.equalsIgnoreCase(validatecode)) {
//			log.debug("验证码输入错误!");
//			writeJson(new JSON("2", "验证码输入错误!").toString());
//			return;
//		}
		user.setUr_password(MD5Util.getMD5(user.getUr_password()));
		User trueUser =  (User) sqlMap.selectOne("LoginAction.queryUser",user);
		if (trueUser == null) {
			writeToJson(new JSON("1", getText("LoginAction_check_failure_1")));
			return;
		} else {
			if(!"0".equals(trueUser.getUr_status())){
				writeToJson(new JSON("3", getText("LoginAction_check_failure_2")));
				return;
			}
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put(Constants.LOGIN_USER, trueUser);
			ActionContext.getContext().setSession(session);
			writeToJson(new JSON("0", getText("LoginAction_check_success")));
		}
		log.info("*********************登录结束***********************");
	}

	public void validateCode() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		try {
			int width = 52;
			int height = 22;
			// 取得一个4位随机字母数字字符串
			String code = RandomStringUtils.random(4, true, true);
			// 保存入session,用于与用户的输入进行比较.
			// 注意比较完之后清除session.
			session.setAttribute(Constants.VALIDATE_CODE, code);

			response.setContentType("images/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ServletOutputStream out = response.getOutputStream();
			BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			// 设定背景色
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);

			// 设定字体
			Font mFont = new Font("Times New Roman", Font.BOLD, 18);// 设置字体
			g.setFont(mFont);

			// 画边框
			// g.setColor(Color.BLACK);
			// g.drawRect(0, 0, width - 1, height - 1);

			// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
			g.setColor(getRandColor(160, 200));
			// 生成随机类
			Random random = new Random();
			for (int i = 0; i < 155; i++) {
				int x2 = random.nextInt(width);
				int y2 = random.nextInt(height);
				int x3 = random.nextInt(12);
				int y3 = random.nextInt(12);
				g.drawLine(x2, y2, x2 + x3, y2 + y3);
			}

			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(code, 2, 16);

			// 图象生效
			g.dispose();
			// 输出图象到页面
			ImageIO.write((BufferedImage) image, "JPEG", out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}


	public String getValidatecode() {
		return validatecode;
	}

	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
