package com.jcl.gycms.controller;

import com.github.pagehelper.PageHelper;
import com.jcl.gycms.entity.Integrals;
import com.jcl.gycms.entity.Msg;
import com.jcl.gycms.entity.Userinfo;
import com.jcl.gycms.service.IntegralsService;
import com.jcl.gycms.service.MsgService;
import com.jcl.gycms.service.UserinfoService;
import com.jcl.gycms.uitl.exception.GlobalException;
import com.jcl.gycms.uitl.page.PaginationContext;
import com.jcl.gycms.uitl.page.cPageInfo;
import com.jcl.gycms.uitl.result.CodeMsg;
import com.jcl.gycms.uitl.result.Result;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserinfoService userinfoService;
    
    @Autowired
    private MsgService  msgService;
    
    @Autowired
    private IntegralsService integralsService;
    
    @RequestMapping("/skip")
    public String Skip() {
        return "user/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public cPageInfo<Userinfo> list(Userinfo userinfo, String startDate, String endDate) {
        Example example = new Example(Userinfo.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(userinfo.getUsername())){
            criteria.andLike("username","%"+userinfo.getUsername()+"%");
        }
        if (StringUtils.isNotBlank(userinfo.getUcname())){
            criteria.andLike("ucname","%"+userinfo.getUcname()+"%");
        }
        if (!StringUtils.isBlank(startDate)&&!StringUtils.isBlank(endDate)) {
        	criteria.andBetween("updatedat", startDate, endDate);
        }else if(!StringUtils.isBlank(startDate)&&StringUtils.isBlank(endDate)){
        	criteria.andBetween("updatedat", startDate, new Date());
        }else if(StringUtils.isBlank(startDate)&&!StringUtils.isBlank(endDate)){
        	criteria.andBetween("updatedat", "1970-1-1 8:0:0",endDate);
        }       
        example.setOrderByClause("updatedAt desc");
        PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
        List<Userinfo> list = userinfoService.selectByExample(example);
        return new cPageInfo<Userinfo>(list);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(@Valid Userinfo userinfo, String startDate, String endDate,Integer id) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startParse = sdf.parse(startDate);
        Date endParse = sdf.parse(endDate);
        if (startParse.getTime() == endParse.getTime()) {
            return Result.error(CodeMsg.INFO(400, "结束日期等于开始日期"));
        }
        if (startParse.getTime() > endParse.getTime()) {
            return Result.error(CodeMsg.INFO(400, "结束日期小于开始日期"));
        }
        if(userinfo.getTelephone() == null){
            return Result.error(CodeMsg.INFO(400,"手机号不能为空"));
        }
        if (userinfo.getUsername() == null){
            return  Result.error(CodeMsg.INFO(400,"用户名不能为空"));
        }
        Example example = new Example(Userinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orEqualTo("telephone",userinfo.getTelephone());
        criteria.orEqualTo("username",userinfo.getUsername());
        List<Userinfo> userList = userinfoService.selectByExample(example);
        if(id == null){ // 新增
            if(userList != null && userList.size()>0){
                throw GlobalException.error(CodeMsg.INFO(400,"用户名或手机号已存在"));
            }else{
                userinfo.setStarttime(startParse);
                userinfo.setEndtime(endParse);
                userinfo.setUpdatedat(new Date());
                int i = userinfoService.saveSelective(userinfo);
                if (i > 0) {
                    return Result.success();
                } else {
                    return Result.error(CodeMsg.FAIL);
                }
            }
        }else{ //修改
            userinfo.setStarttime(startParse);
            userinfo.setEndtime(endParse);
            int i = userinfoService.updateByKeySelective(userinfo);
            if (i > 0) {
                return Result.success();
            } else {
                return Result.error(CodeMsg.FAIL);
            }
        }
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result<Object> delete(@PathVariable String id){
        int delete = userinfoService.delete(Integer.valueOf(id));
        if (delete > 0){
            return Result.success();
        }else{
            return Result.error(CodeMsg.INFO(400,"删除失败"));
        }
    }

    @RequestMapping("/alert/{ty}")
    public String Alert(@PathVariable(value = "ty") String ty, Model model, String id) {
        if("sendmsg".equals(ty)||"updataIntegral".equals(ty)||"updata".equals(ty)){        
			Userinfo user = userinfoService.selectByKey(Integer.parseInt(id));			
            model.addAttribute("user",user);
        }
        return "user/alert/user-" + ty;
    }
    
    //发送消息
    @RequestMapping("/sendMsg")
    @ResponseBody
    public Result sendMsg(Msg msg){
    	if(StringUtils.isBlank(msg.getTitel())){
    		
    		return Result.error(CodeMsg.INFO(400, "发送消息内容不能为空"));
    		
    	  }else if(StringUtils.isBlank(msg.getUserid().toString())) {
    		
    		return Result.error(CodeMsg.INFO(400, "发送用户对象不能为空"));  
    		  
    	  }
    	msg.setSendpeople("-1");
    	msg.setStatecode("0");
    	msg.setSendtime(new Date());
    	int num = msgService.saveSelective(msg);
    	if(num>0) {
    		return Result.success();
    	}else {
    		return Result.error(CodeMsg.INFO(400, "发送消息失败"));
    	}	   	
    }
    
    //增加积分
    @RequestMapping("/addintegration")
    @ResponseBody
    public Result addintegration(Userinfo userinfo,Integer integration1,Integer integration2) {
		if(StringUtils.isBlank(integration2.toString())){
		    		
		    return Result.error(CodeMsg.INFO(400, "输入的积分不能为空"));
		    		
		}else if(StringUtils.isBlank(userinfo.getId().toString())) {
		    		
		    return Result.error(CodeMsg.INFO(400, "增加积分用户不能为空"));  
		    		  
		}
		userinfo.setIntegration(integration1+integration2);
		int num = userinfoService.updateByKeySelective(userinfo);
		if(num>0) {
			Integrals inte=new Integrals();
			inte.setCreatetime(new Date());
			inte.setDelflag(0);
			inte.setDescription("排名奖励");
			inte.setIntegraldate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			inte.setUsername(userinfo.getUsername());
			inte.setIntegraltype("2");
			inte.setIntegralnum(integration2);
			inte.setSystemuser("admin");
			inte.setIscheck("0");//0为未审核
			int i = integralsService.saveSelective(inte);   		
    		if(i>0) {
        		return Result.success();
        	}else {
        		return Result.error(CodeMsg.INFO(400, "增加用户积分失败"));
        	}
    	}else {
    		return Result.error(CodeMsg.INFO(400, "增加用户积分失败"));
    	}   	
    } 
    
    //修改密码
    @RequestMapping("/updatapwd")
    @ResponseBody
    public Result updatapwd(Userinfo userinfo,String oldPwd,String newPwd) {
    	
    	if(StringUtils.isBlank(newPwd)){
    		
		    return Result.error(CodeMsg.INFO(400, "输入新密码不能为空"));
		    		
		}else if(StringUtils.isBlank(userinfo.getId().toString())) {
		    		
		    return Result.error(CodeMsg.INFO(400, "修改密码的用户不存在"));  
		    		  
		}else if(oldPwd.equals(newPwd)){
			return Result.error(CodeMsg.INFO(400, "输入新密码不能和原密码相同"));
		}
    	userinfo.setPassword(newPwd);
    	int num = userinfoService.updateByKeySelective(userinfo);  	
		if(num>0) {
    		return Result.success();
    	}else {
    		return Result.error(CodeMsg.INFO(400, "修改用户密码失败"));
    	}     	
    }
}
