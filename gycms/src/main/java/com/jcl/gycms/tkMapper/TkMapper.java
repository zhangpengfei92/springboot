package com.jcl.gycms.tkMapper;

import com.mybatis.pj.mapper.BaseExampleMapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface TkMapper<T> extends /*Mapper<T>,*/ MySqlMapper<T>, BaseExampleMapper<T> {
}
