/**
 * Copyright (C) 2009 武汉金策略信息科技有限公司
 *
 * 版权所有。
 *
 * 类名　　  : @DocController.java
 * 功能概要  : 
 * 做成日期  : @2019年2月27日
 * 修改日期  :
 */
package com.jcl.gycms.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.jcl.gycms.entity.Dockeyword;
import com.jcl.gycms.entity.Region;
import com.jcl.gycms.entity.Userinfo;
import com.jcl.gycms.service.DockeywordService;
import com.jcl.gycms.uitl.page.PaginationContext;
import com.jcl.gycms.uitl.page.cPageInfo;
import com.jcl.gycms.uitl.result.CodeMsg;
import com.jcl.gycms.uitl.result.Result;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/** 
 * @author zpf
 * @version 1.0
 */
@RequestMapping("/keyword")
@Controller
public class KeywordController {
	
	private static Logger logger = LoggerFactory.getLogger(KeywordController.class);
	
	@Autowired
	private DockeywordService dockeywordService;
	
	@RequestMapping("/skip")
    public String Skip() {
        return "keyword/list";
    }
	
	@RequestMapping("/list")
    @ResponseBody
    public cPageInfo<Dockeyword> list(Dockeyword dockeyword, String startDate, String endDate) {
		
		Example example = new Example(Dockeyword.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(dockeyword.getKeyword())){
            criteria.andLike("keyword","%"+dockeyword.getKeyword()+"%");
        }
        if (!StringUtils.isBlank(startDate)&&!StringUtils.isBlank(endDate)) {
        	criteria.andBetween("createtime", startDate, endDate);
        }else if(!StringUtils.isBlank(startDate)&&StringUtils.isBlank(endDate)){
        	criteria.andBetween("createtime", startDate, new Date());
        }else if(StringUtils.isBlank(startDate)&&!StringUtils.isBlank(endDate)){
        	criteria.andBetween("createtime", "1970-1-1 8:0:0",endDate);
        }				
        example.setOrderByClause("createtime desc");
        PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
		List<Dockeyword> list = dockeywordService.selectByExample(example);
		return new cPageInfo<Dockeyword>(list);       
    }
	
	@RequestMapping("/alert/{path}/{id}")
    public String Alert(@PathVariable(value = "path") String path, Model model,@PathVariable(value = "id") String id) {
		if("add".equals(path)){
			List<Dockeyword> list = dockeywordService.selectAll();
			if(list!=null&&list.size()>0) {
				return "keyword/alert/error";
			}		
		}		
        if("edit".equals(path)){        
			Dockeyword dockeyword = dockeywordService.selectByKey(Integer.parseInt(id));
            model.addAttribute("dockeyword",dockeyword);
        }
       if("edit".equals(path)){
    	   path="add" ;
       }
        return "keyword/alert/keyword-" + path;
    }
	
	@RequestMapping("/editkeyword")
    @ResponseBody
    public  Result editkeyword(Dockeyword dockeyword) {
		
		if(StringUtils.isBlank(dockeyword.getKeyword())) {
			return Result.error(CodeMsg.INFO(400, "关键字内容不能为空"));
		}
		dockeyword.setCreatetime(new Date());
		if(dockeyword.getId()!=null&&dockeyword.getId()>0){
			int num = dockeywordService.updateByKeySelective(dockeyword);
			if(num>0) {
	    		return Result.success();
	    	}else {
	    		return Result.error(CodeMsg.INFO(400, "修改关键字失败"));
	    	}
		}else {			
			int num=  dockeywordService.saveSelective(dockeyword);
			if(num>0) {
	    		return Result.success();
	    	}else {
	    		return Result.error(CodeMsg.INFO(400, "添加关键字失败"));
	    	}
		}		
	}
	
	@RequestMapping("/delkeyword/{id}")
    @ResponseBody
    public  Result delkeyword(@PathVariable("id") Integer id) {		
		if(StringUtils.isBlank(id.toString())||id<0){
			return Result.error(CodeMsg.INFO(400, "删除关键字不存在"));
		}else {
			int num = dockeywordService.delete(id);
			if(num>0) {
	    		return Result.success();
	    	}else {
	    		return Result.error(CodeMsg.INFO(400, "删除关键字失败"));
	    	}
		}
	}
	
}
