/**
 * Copyright (C) 2009 武汉金策略信息科技有限公司
 *
 * 版权所有。
 *
 * 类名　　  : @DoccontentServiceImpl.java
 * 功能概要  : 
 * 做成日期  : @2019年3月5日
 * 修改日期  :
 */
package com.jcl.gycms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcl.gycms.entity.Doccontent;
import com.jcl.gycms.mapper.DoccontentMapper;
import com.jcl.gycms.service.DoccolumnService;
import com.jcl.gycms.service.DoccontentService;

/** 
 * @author zpf
 * @version 1.0
 */
@Service
public class DoccontentServiceImpl extends BaseService<Doccontent> implements DoccontentService{
	
	@Autowired
	private  DoccontentMapper doccontentMapper;
	
	@Override
	public List<Doccontent> getDoccontentByColumId(Map<String, Object> parmaMap) {
		
		List<Doccontent> list=doccontentMapper.getDoccontentByColumId(parmaMap);
		
		return list;
	}
}
