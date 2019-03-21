/**
 * Copyright (C) 2009 武汉金策略信息科技有限公司
 *
 * 版权所有。
 *
 * 类名　　  : @DoccontentMapper.java
 * 功能概要  : 
 * 做成日期  : @2019年3月5日
 * 修改日期  :
 */
package com.jcl.gycms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jcl.gycms.entity.Doccontent;
import com.jcl.gycms.tkMapper.TkMapper;

/** 
 * @author zpf
 * @version 1.0
 */
public interface DoccontentMapper extends TkMapper<Doccontent>{
	
	List<Doccontent> getDoccontentByColumId(@Param("parmaMap") Map<String, Object> parmaMap);

}
