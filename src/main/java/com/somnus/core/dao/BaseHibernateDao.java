package com.somnus.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 通用DAO，用于对象的持久化、查询（分页）
 * 
 * @author liuruo
 */
public interface BaseHibernateDao<T>
{

	/**
	 * 保存对象
	 * 
	 * @param T
	 * @return Serializable
	 * @throws Exception
	 */
	public Serializable saveObject(T T) throws Exception;

	/**
	 * 保存或更新对象
	 * 
	 * @param T
	 * @throws Exception
	 */
	public void saveOrUpdateObject(T T) throws Exception;

	/**
	 * 删除对象,需要慎重，其将删除所有的关联对象
	 * 
	 * @param T
	 * @throws Exception
	 */
	public void deleteObject(T T) throws Exception;

	/**
	 * 根据类型删除指定对象
	 * 
	 * @param clazz
	 *            类型
	 * @param ids
	 *            要删除对象的主键
	 * @return int 删除对象的行数
	 * @throws Exception
	 */
	public int deleteObjectsByIds(Class<T> clazz, String[] ids) throws Exception;

	/**
	 * 根据类型删除全部对象
	 * 
	 * @param clazz
	 *            类型
	 * @return int
	 * @throws Exception
	 */
	public int deleteAll(Class<T> clazz) throws Exception;

	/**
	 * 更新对象
	 * 
	 * @param T
	 * @throws Exception
	 */
	public void updateObject(T T) throws Exception;

	/**
	 * 根据sql语句进行增删改操作，只支持原生sql
	 * 
	 * @param sql
	 *            要进行操作的sql语句
	 * @return resultInt 执行sql语句受影响的行数
	 * @throws Exception
	 */
	public int executeUpdate(String sql) throws Exception;

	/**
	 * 根据sql语句进行增删改操作，只支持原生sql
	 * 
	 * @param sql
	 *            要进行操作的sql语句
	 * @param conditionMap
	 *            存放sql语句中的 标识符和实际值 对
	 * @return resultInt 执行sql语句受影响的行数
	 * @throws Exception
	 */
	public int executeUpdate(String sql, Map<String,Object> conditionMap) throws Exception;

	/**
	 * 根据sql语句进行增删改操作，只支持原生sql
	 * 
	 * @param sql
	 *            要进行操作的sql语句
	 * @param parameters
	 *            存放sql语句中的参数值
	 * @return resultInt 执行sql语句受影响的行数
	 * @throws Exception
	 */
	public int executeUpdate(String sql, Object[] parameters) throws Exception;

	/**
	 * Load出对象 根据主键，获得数据库一条对应的记录,如果没有相应的实体，抛出异常
	 * 
	 * @param clazz
	 * @param id
	 * @return Object
	 * @throws Exception
	 */
	public T loadObject(Class<T> clazz, Serializable id) throws Exception;

	/**
	 * get出对象 根据主键，获得数据库一条对应的记录,如果没有相应的实体，返回 null
	 * 
	 * @param clazz
	 * @param id
	 * @return Object
	 * @throws Exception
	 */
	public T getObject(Class<T> clazz, Serializable id) throws Exception;

	/**
	 * 查找对象
	 * 
	 * @param queryString
	 *            要执行的语句
	 * @return List<Object> 满足查询条件的对象列表
	 * @throws Exception
	 */
	public List<T> queryObject(String queryString) throws Exception;

	/**
	 * 查找对象
	 * 
	 * @param queryString
	 *            要执行的语句
	 * @param conditionMap
	 *            存放queryString语句中的 标识符和实际值 对
	 * @return List<Object> 满足查询条件的对象列表
	 * @throws Exception
	 */
	public List<T> queryObject(String queryString, Map<String,Object> conditionMap)throws Exception;

	/**
	 * 查找对象
	 * 
	 * @param queryString
	 *            要执行的语句
	 * @param parameters
	 *            存放queryString语句中参数值
	 * @return List<Object> 满足查询条件的对象列表
	 * @throws Exception
	 */
	public List<T> queryObject(String queryString, Object[] parameters)throws Exception;

	/**
	 * 读出所有的对象
	 * 
	 * @param clazz
	 *            对象类型
	 * @return List<Object> 该类的所有对象
	 * @throws Exception
	 */
	public List<T> queryAll(Class<T> clazz) throws Exception;

	/**
	 * 读出所有的对象
	 * 
	 * @param clazz
	 *            对象类型
	 * @param ids
	 *            要读出对象的主键集合
	 * @return List<Object> 该类的所有对象
	 * @throws Exception
	 */
	public List<T> queryObjectByIds(Class<T> clazz, String[] ids)throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param startIndex
	 *            记录起始行
	 * @param endIndex
	 *            记录结束行
	 * @return List 分页结果
	 * @throws Exception
	 */
	public List<T> findPageByQuery(String queryString, int startIndex,int endIndex) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param conditionMap
	 *            存放查询语句中的 标识符和实际值 对
	 * @param startIndex
	 *            记录起始行
	 * @param endIndex
	 *            记录结束行
	 * @return List 分页结果
	 * @throws Exception
	 */
	public List<T> findPageByQuery(String queryString, Map<String,Object> conditionMap,int startIndex, int endIndex) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param parameters
	 *            存放查询语句中的参数值
	 * @param startIndex
	 *            记录起始行
	 * @param endIndex
	 *            记录结束行
	 * @return List 分页结果
	 * @throws Exception
	 */
	public List<T> findPageByQuery(String queryString, Object[] parameters,int startIndex, int endIndex) throws Exception;

	/**
	 * 获得sql语句的执行结果，该语句返回一个能转化为整形的值
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @return int sql语句的结果值
	 * @throws Exception
	 */
	public int getInt(String sql) throws Exception;

	/**
	 * 获得sql语句的执行结果，该语句返回一个能转化为整形的值
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param conditionMap
	 *            存放sql语句中的 标识符和实际值 对
	 * @return int sql语句的结果值
	 * @throws Exception
	 */
	public int getInt(String sql, Map<String,Object> conditionMap) throws Exception;

	/**
	 * 获得sql语句的执行结果，该语句返回一个能转化为整形的值
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param parameters
	 *            参数
	 * @return int sql语句的结果值
	 * @throws Exception
	 */
	public int getInt(String sql, Object[] parameters) throws Exception;


}
