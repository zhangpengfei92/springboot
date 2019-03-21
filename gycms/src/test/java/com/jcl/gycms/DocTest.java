/**
 * Copyright (C) 2009 武汉金策略信息科技有限公司
 *
 * 版权所有。
 *
 * 类名　　  : @DocTest.java
 * 功能概要  : 
 * 做成日期  : @2019年3月14日
 * 修改日期  :
 */
package com.jcl.gycms;
/** 
 * @author zpf
 * @version 1.0
 */

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jcl.gycms.entity.Doccontent;
import com.jcl.gycms.service.DoccontentService;

public class DocTest extends GycmsApplicationTests{
	
	@Autowired
	private  DoccontentService doccontentService;
	
	@Test
	public void testdoc() {
		List<Doccontent> list = doccontentService.getDoccontentByColumId(8, "", "", "");
				
		if(list!=null&&list.size()>0) {
			System.out.println(list.size());
			list.forEach(doc ->System.out.println(doc));
		}
	}
}
