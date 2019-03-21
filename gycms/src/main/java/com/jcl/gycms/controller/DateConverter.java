/**
 * Copyright (C) 2009 武汉金策略信息科技有限公司
 *
 * 版权所有。
 *
 * 类名　　  : @DateConverter.java
 * 功能概要  : 
 * 做成日期  : @2019年3月1日
 * 修改日期  :
 */
package com.jcl.gycms.controller;
/** 
 * @author zpf
 * @version 1.0
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {    
	public Date convert(String source) {    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	    dateFormat.setLenient(false);    
	    try {    
	        return dateFormat.parse(source);    
	    } catch (ParseException e) {    
	        e.printStackTrace();    
	    }           
	    return null;   
	}
}
