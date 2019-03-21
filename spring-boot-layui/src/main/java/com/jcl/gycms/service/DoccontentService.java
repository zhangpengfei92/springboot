/**
 * Copyright (C) 2009 武汉金策略信息科技有限公司
 *
 * 版权所有。
 *
 * 类名　　  : @DoccontentService.java
 * 功能概要  : 
 * 做成日期  : @2019年3月5日
 * 修改日期  :
 */
package com.jcl.gycms.service;

import java.util.List;
import java.util.Map;

import com.jcl.gycms.entity.Doccontent;

/** 
 * @author zpf
 * @version 1.0
 */
public interface DoccontentService extends IService<Doccontent>{
	
List<Doccontent> getDoccontentByColumId(Map<String, Object> parmaMap);
		
}
