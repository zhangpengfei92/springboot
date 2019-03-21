package com.jcl.gycms.service;

import com.mybatis.pj.example.select.multipart.MultipartSelectExample;

import java.util.List;

public interface IService<T> {

    T selectOne(T entity);

    T selectByKey(Object key);

    List<T> selectAll();

    List<T> selectByExample(Object example);

    int save(T entity);

    int saveSelective(T entity);

    int saveList(List<T> list);

    int delete(Object key);

    int deleteByObject(T entity);

    int deleteByExample(Object example);

    int updateByKey(T entity);

    int updateByKeySelective(T entity);

    int updateByExample(T entity, Object example);

    int updateByExampleSelective(T entity, Object example);

    List<T> selectRelevance(MultipartSelectExample selectBaseExample);
    //TODO 其他...
}
