package com.jcl.gycms.controller;

import com.jcl.gycms.entity.Doccolumn;
import com.jcl.gycms.service.DoccolumnService;
import com.jcl.gycms.uitl.exception.GlobalException;
import com.jcl.gycms.uitl.result.CodeMsg;
import com.jcl.gycms.uitl.result.Result;
import com.jcl.gycms.uitl.validation.RandomValidateCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class IndexController {
	
	
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private DoccolumnService doccolumnService;
	
    @Value(value = "${gycms.username}")
    private String eusername;

    @Value(value = "${gycms.password}")
    private String epassword;

    @RequestMapping("/index")
    public String index(HttpSession session, Model model) {
        String isLogin = (String) session.getAttribute("isLogin");
        if (isLogin == null) {
            return "login";
        }
        List<Doccolumn> doclist = doccolumnService.selectAll();
        session.setAttribute("doclist", doclist);
        return "index";
    }

    /**
     * 跳转到首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/home")
    public String home(Model model) {


        return "home";
    }

    /**
     * 跳转到消息页面
     *
     * @return
     */
    @RequestMapping("/message")
    public String message() {
        return "message";
    }

    /**
     * 跳转到搜索模块
     *
     * @param keywords
     * @param model
     * @return
     */
    @RequestMapping("/serach")
    public String serach(String keywords, Model model) {
        model.addAttribute("keywords", keywords);
        return "serach";
    }

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {

        }
    }

    /**
     * 后台登录
     *
     * @return
     */
    @RequestMapping("/adminLogin")
    @ResponseBody
    public Result adminLogin(String username, String password, String code, HttpSession session) {
        if (StringUtils.isBlank(username)) {
            throw GlobalException.error(CodeMsg.INFO(400, "请输入帐号"));
        }
        if (StringUtils.isBlank(password)) {
            throw GlobalException.error(CodeMsg.INFO(400, "请输入密码"));
        }
        if (StringUtils.isBlank(code)) {
            throw GlobalException.error(CodeMsg.INFO(400, "请输入验证码"));
        }
       
        String sessionCode = (String) session.getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
        //logger.info("验证码 ： " +code+"---------------"+sessionCode);
        if (!code.equalsIgnoreCase(sessionCode)) {
            throw GlobalException.error(CodeMsg.INFO(400, "验证码输入错误"));
        }
        if (!eusername.equals(username)) {
            throw GlobalException.error(CodeMsg.INFO(400, "账号不存在"));
        }
        if (!epassword.equals(password)) {
            throw GlobalException.error(CodeMsg.INFO(400, "密码错误"));
        }
        session.setAttribute("isLogin", username);
        return Result.success();
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("isLogin", null);
        return "login";
    }

}
