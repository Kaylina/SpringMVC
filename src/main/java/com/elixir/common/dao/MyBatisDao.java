package com.elixir.common.dao;

import java.util.List;
import java.util.Map;

/**
* @ClassName: MyBatisDao
* @Description:
* @author Jingyan
* @date 2016年3月21日 下午5:48:06
* @param <T>
*/ 
public interface MyBatisDao<T> {

	public int insert(T t);

	public int update(T t);

	public int updateByMap(Map<String, Object> parameter);

	public int delete(Object obj);

	public int deleteById(Object objKey);

	public T findById(Object objKey);
	
	public T findByObject(Object objKey);

	public List<T> findBy(Map<String, Object> map);

	public List<T> findBy(T t);

	public List<T> findAll();

	public List<T> findAll(T t);

}
