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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jcl.gycms.entity.Doccontent;
import com.jcl.gycms.tkMapper.TkMapper;

/** 
 * @author zpf
 * @version 1.0
 */
public interface DoccontentMapper extends TkMapper<Doccontent>{
	
	@Select("select d1.*,d2.* from doccontent d1,doccolumn d2 where "
			+ "d1.columnid=d2.id and d1.columnid = #{id} and d1.title like '%#{title}%' and createtime "
			+ "BETWEEN #{starttime}  and #{endtime}")
	List<Doccontent> getDoccontentByColumId(@Param("id") Integer id ,@Param("starttime") String starttime,@Param("endtime")String endtime,@Param("title") String title);

}
