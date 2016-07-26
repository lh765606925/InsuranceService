package com.insurance.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.insurance.model.PageBean;
import com.insurance.model.Pager;

/**
 * 基础数据库操作类
 * 
 * @author fly 2014-11-17
 * @param <T>
 */
public interface BaseDao<T, PK extends Serializable> {
	/**
	 * 保存一个对象
	 * 
	 * @param o
	 * 
	 */
	public Serializable save(T o);

	/**
	 * 删除一个对象
	 * 
	 * @param o
	 */
	public void delete(T o);

	/**
	 * 更新一个对象
	 * 
	 * @param o
	 */
	public void update(T o);

	/**
	 * 保存或更新 合并bean对象<br>
	 * <font color="red">可将数据从游离状态转换为托管状态</font>
	 * 
	 * @param o
	 */
	public void mergo(T o);

	/**
	 * 保存或更新 <font color="red">可将数据从游离状态转换为持久化状态</font>
	 * 
	 * @param o
	 */
	public void saveorUpdate(T o);

	/**
	 * 查询
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 * 查询集合
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, List<Object> param);

	/**
	 * 查询集合 （带分页）
	 * 
	 * @return
	 */
	public List<T> find(String hql, PageBean pageBean, Object param[]);

	/**
	 * 获得一个对象
	 * 
	 * @param hql
	 * @param param
	 * @return Object
	 */
	public T get(String hql, Object[] param);

	/**
	 * 根据id获取model类
	 * 
	 * @param c
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T get(Class<T> c, Serializable id) throws Exception;

	/**
	 * 获取数量
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * 获得数量
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Long count(String hql, Object[] param);

	/**
	 * 执行hql，返回影响行数
	 * 
	 * @param hql
	 * @return
	 */
	public Integer executeHql(String hql);

	/**
	 * 执行hql，返回影响行数
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, Object[] param);

	/**
	 * 执行hql，返回影响行数
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public Integer executeHql(String hql, List<Object> param);

	/**
	 * 执行sql，返回影响行数
	 * 
	 * @param sql
	 * @return
	 */
	public Integer executeSql(String sql, Object[] param);

	/**
	 * 根据字段检查是否唯一
	 * @param str字段名称
	 * @param strvalue字段值
	 * @param c 类
	 * @return
	 */
	public boolean isOnly(String str, String strvalue, Class<T> c);

	/**
	 * 是否存在
	 * @param table //查询表
	 * @param str //条件字段
	 * @param strval //字段值
	 * @return true:存在 false:不存在
	 */
	public boolean isExit(String table, String str, String strval);

	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T get(PK id);

	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T load(PK id);

	/**
	 * 根据ID数组获取实体对象集合.
	 * 
	 * @param ids
	 *            ID对象数组
	 * 
	 * @return 实体对象集合
	 */
	public List<T> get(PK[] ids);

	/**
	 * 根据属性名和属性值获取实体对象.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象
	 */
	public T get(String propertyName, Object value);

	/**
	 * 根据属性名和属性值获取实体对象集合.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象集合
	 */
	public List<T> getList(String propertyName, Object value);

	/**
	 * 获取所有实体对象集合.
	 * 
	 * @return 实体对象集合
	 */
	public List<T> getAll();

	/**
	 * 获取所有实体对象总数.
	 * 
	 * @return 实体对象总数
	 */
	public Long getTotalCount();

	/**
	 * 根据属性名、修改前后属性值判断在数据库中是否唯一(若新修改的值与原来值相等则直接返回true).
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param oldValue
	 *            修改前的属性值
	 * @param oldValue
	 *            修改后的属性值
	 * @return boolean
	 */
	public boolean isUnique(String propertyName, Object oldValue, Object newValue);

	/**
	 * 根据属性名判断数据是否已存在.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            值
	 * @return boolean
	 */
	public boolean isExist(String propertyName, Object value);

	/**
	 * 根据ID删除实体对象.
	 * 
	 * @param id
	 *            记录ID
	 */
	public void delete(PK id);

	/**
	 * 根据ID数组删除实体对象.
	 * 
	 * @param ids
	 *            ID数组
	 */
	public void delete(PK[] ids);

	/**
	 * 刷新session.
	 * 
	 */
	public void flush();

	/**
	 * 清除Session.
	 * 
	 */
	public void clear();

	/**
	 * 清除某一对象.
	 * 
	 * @param object
	 *            需要清除的对象
	 */
	public void evict(Object object);

	/**
	 * 根据Pager对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pager
	 *            Pager对象
	 * @return Pager对象
	 */
	public Pager findByPager(Pager pager);

	/**
	 * 根据Pager和DetachedCriteria对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pager
	 *            Pager对象
	 * @return Pager对象
	 */
	public Pager findByPager(Pager pager, DetachedCriteria detachedCriteria);

	public List<T> find(PageBean pageBean);
}
