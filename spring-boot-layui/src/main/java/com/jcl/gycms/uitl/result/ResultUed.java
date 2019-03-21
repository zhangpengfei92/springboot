/**
 * Copyright (C) 2009 武汉金策略信息科技有限公司
 *
 * 版权所有。
 *
 * 类名　　  : @ResultUed.java
 * 功能概要  : 
 * 做成日期  : @2019年3月11日
 * 修改日期  :
 */
package com.jcl.gycms.uitl.result;

import java.io.Serializable;

import lombok.Data;

/** 
 * @author zpf
 * @version 1.0
 */
@Data
public class ResultUed implements Serializable{
	
	private static ResultUed resultUed;
	
	private String state="SUCCESS";
	
	private String original="0x00.jpg";
	
	private String size="-1";
	
	private String title="";
	
	private String type=".jpg";
	
	private String url;
	
	
	private ResultUed() {};
	
	
	public static ResultUed getInstance(){	
		if(resultUed==null) {
			synchronized (ResultUed.class) {
				if(resultUed==null) {
					resultUed=new ResultUed();	
				}				
			}
		}		
		return resultUed;		
	}
	
	public ResultUed(String state, String original, String size, String title, String type, String url) {
		super();
		this.state = state;
		this.original = original;
		this.size = size;
		this.title = title;
		this.type = type;
		this.url = url;
	}	
	
	public ResultUed uploadSuccess(String url) {
		
		return new ResultUed(state, original, size, title, type, url);
	}
	
public ResultUed uploadError() {
		
		return new ResultUed("Error", original, size, title, type, "");
	}
}
