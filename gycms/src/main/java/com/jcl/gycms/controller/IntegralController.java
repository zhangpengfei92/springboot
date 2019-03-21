/**
 * Copyright (C) 2009 武汉金策略信息科技有限公司
 *
 * 版权所有。
 *
 * 类名　　  : @IntegralController.java
 * 功能概要  : 
 * 做成日期  : @2019年2月27日
 * 修改日期  :
 */
package com.jcl.gycms.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.jcl.gycms.entity.Integrals;
import com.jcl.gycms.entity.Userinfo;
import com.jcl.gycms.service.IntegralsService;
import com.jcl.gycms.uitl.page.PaginationContext;
import com.jcl.gycms.uitl.page.cPageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/** 
 * @author zpf
 * @version 1.0
 */
@RequestMapping("/integral")
@Controller
public class IntegralController {
	
	@Autowired
	private IntegralsService integralsService;
	
	@RequestMapping("/skip")
    public String Skip() {
        return "integral/list";
    }
	
	@RequestMapping("/list")
    @ResponseBody
    public cPageInfo<Integrals> list(Integrals integrals, String startDate, String endDate) {
		Example example = new Example(Integrals.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(integrals.getUsername())){
            criteria.andLike("username","%"+integrals.getUsername()+"%");
        }
        if (StringUtils.isNotBlank(integrals.getIntegraltype())&&Integer.parseInt(integrals.getIntegraltype())!=0){
            criteria.andEqualTo("integraltype", integrals.getIntegraltype());
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
        List<Integrals> list = integralsService.selectByExample(example);
        return new cPageInfo<Integrals>(list);
    }
}
