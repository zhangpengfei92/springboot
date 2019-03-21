package com.jcl.gycms.service.impl;

import com.jcl.gycms.service.IService;
import com.jcl.gycms.tkMapper.TkMapper;
import com.mybatis.pj.example.select.multipart.MultipartSelectExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected TkMapper<T> mapper;
    public TkMapper<T> getTkMapper() {
        return mapper;
    }

    @Override
    public T selectByKey(Object key) {
        //说明：根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
    	/*if(key instanceof Integer) {
    		return mapper.selectByPrimaryKey(Integer.parseInt(key.toString()));
    	}*/
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public List<T> selectByExample(Object example) {
        //说明：根据Example条件进行查询
        //重点：这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> selectAll(){
        //说明：查询所有的数据
        return mapper.selectAll();
    }

    @Override
    public int save(T entity) {
        //说明：保存一个实体，null的属性也会保存，不会使用数据库默认值
        return mapper.insert(entity);
    }

    @Override
    public int saveSelective(T entity) {
        //说明：保存一个实体，属性不为null的值
        return mapper.insertSelective(entity);
    }

    @Override
    public int saveList(List<T> list){
        //说明：保存实体集合
        return mapper.insertList(list);
    }

    @Override
    public int delete(Object key) {
        //说明：根据主键字段进行删除，方法参数必须包含完整的主键属性
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int deleteByObject(T entity){
        //说明：根据对象删除
        return mapper.delete(entity);
    }

    @Override
    public int deleteByExample(Object example){
        //说明：根据Example条件进行查询
        //重点：这个删除支持通过Example类指定查询列，通过selectProperties方法指定删除
        return mapper.deleteByExample(example);
    }

    @Override
    public int updateByKey(T entity) {
        //说明：根据主键更新实体全部字段，null值会被更新
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateByKeySelective(T entity) {
        //根据主键更新属性不为null的值
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int updateByExample(T entity,Object example) {

        return mapper.updateByExample(entity,example);
    }

    @Override
    public int updateByExampleSelective(T entity,Object example) {

        return mapper.updateByExampleSelective(entity,example);
    }

    @Override
    public List<T> selectRelevance(MultipartSelectExample selectBaseExample){
        //关联查询通用方法
        return mapper.selectPage(selectBaseExample);
    }

    @Override
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }
}
