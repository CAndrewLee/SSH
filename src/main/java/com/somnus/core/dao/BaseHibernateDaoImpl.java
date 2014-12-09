package com.somnus.core.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import com.somnus.core.exception.SystemException;

/**
 * 通用DAO的实现类，采用Hibernate方式实现
 * 
 * @author liuruo
 * 
 */
public class BaseHibernateDaoImpl<T>  implements BaseHibernateDao<T>
{
	private SessionFactory sessionFactory;
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 数据源变量
	 */
	public BasicDataSource dataSource = null;

	/**
	 * 根据类型删除全部对象
	 * 
	 * @param clazz
	 *            类型
	 * @return int
	 * @throws Exception
	 */
	public int deleteAll(final Class<T> clazz) throws Exception
	{
		if (clazz == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			int resultInt = sessionFactory.getCurrentSession().createQuery("delete " + clazz.getName()).executeUpdate();
			return resultInt;
		}
	}

	/**
	 * 删除对象,需要慎重，其将删除所有的关联对象
	 * 
	 * @param object
	 * @throws Exception
	 */

	public void deleteObject(T T) throws Exception
	{
		if (T == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			sessionFactory.getCurrentSession().delete(T);
		}
	}

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
	public int deleteObjectsByIds(Class<T> clazz, String[] ids) throws Exception
	{
		if (clazz != null && ids != null)
		{
			StringBuffer idString = new StringBuffer();
			for (int i = 0; i < ids.length - 1; i++)
			{
				idString.append("'" + ids[i] + "',");
			}
			idString = idString.append("'" + ids[ids.length - 1] + "'");

			final String sql = "delete from " + clazz.getName()
					+ " where id in (" + idString.toString() + ")";

			Object resultObject = sessionFactory.getCurrentSession().createQuery(sql).executeUpdate();
			return Integer.parseInt(resultObject.toString());
		} else
		{
			throw new SystemException("传入参数为空");
		}
	}

	/**
	 * 根据sql语句进行【增删改】操作,【只支持原生sql】
	 * 
	 * @param sql
	 *            要进行操作的sql语句
	 * @return resultInt 执行sql语句受影响的行数
	 * @throws Exception
	 */
	public int executeUpdate(final String sql) throws Exception
	{
		if (sql == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			Object resultObject = sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
			return Integer.parseInt(resultObject.toString());
		}
	}

	/**
	 * 根据sql语句进行【增删改】操作，只支持【原生sql】
	 * 
	 * @param sql
	 *            要进行操作的sql语句
	 * @param conditionMap
	 *            存放sql语句中的 标识符和实际值 对
	 * @return resultInt 执行sql语句受影响的行数
	 * @throws Exception
	 */
	public int executeUpdate(final String sql, final Map<String,Object> conditionMap)throws Exception
	{
		if (conditionMap == null)
		{
			return this.executeUpdate(sql);
		} else if (sql == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			Iterator<String> it = conditionMap.keySet().iterator();
			while (it.hasNext())
			{
				Object key = it.next();
				query.setParameter(key.toString(), conditionMap.get(key));
			}
			int resultInt = query.executeUpdate();
			return resultInt;
		}
	}

	/**
	 * 根据sql语句进行【增删改】操作，【只支持原生sql】
	 * 
	 * @param sql
	 *            要进行操作的sql语句
	 * @param parameters
	 *            存放sql语句中的参数值
	 * @return resultInt 执行sql语句受影响的行数
	 * @throws Exception
	 */
	public int executeUpdate(final String sql, final Object[] parameters)throws Exception
	{
		if (parameters == null)
		{
			return this.executeUpdate(sql);
		} else if (sql == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			if (parameters != null)
			{
				for (int i = 0; i < parameters.length; i++)
				{
					query.setParameter(i, parameters[i]);
				}
			}
			int resultInt = query.executeUpdate();
			return resultInt;
		}
	}
//******************************************分页****************************************************************
	/**
	 * 分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param startIndex
	 *            记录起始行
	 * @param limit
	 *            显示条数
	 * @return rList 分页结果
	 * @throws Exception
	 */
	public List<T> findPageByQuery(final String queryString, final int startIndex,final int limit) throws Exception
	{
		if (queryString == null)
		{
			throw new SystemException("传入参数为空");
		} else if (startIndex < 0 || limit < 0)
		{
			throw new SystemException("传入startIndex或limit不符合要求");
		} else
		{
			Query query = sessionFactory.getCurrentSession().createQuery(queryString);
			query.setFirstResult(startIndex);
			query.setMaxResults(limit);
			
			List<T> resultList = query.list();
			return resultList;
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param conditionMap
	 *            存放查询语句中的 标识符和实际值 对
	 * @param startIndex
	 *            记录起始行
	 * @param limit
	 *            显示条数
	 * @return rList 分页结果
	 * @throws Exception
	 */
	public List<T> findPageByQuery(final String queryString,final Map<String,Object> conditionMap, final int startIndex, final int limit)throws Exception
	{
		if (conditionMap == null)
		{
			return this.findPageByQuery(queryString, startIndex, limit);
		} else
		{
			if (queryString == null)
			{
				throw new SystemException("传入参数为空");
			} else if (startIndex < 0 || limit < 0)
			{
				throw new SystemException("传入startIndex或limit不符合要求");
			} else
			{
				Query query = sessionFactory.getCurrentSession().createQuery(queryString);
				Iterator<String> it = conditionMap.keySet().iterator();
				while (it.hasNext())
				{
					Object key = it.next();
					query.setParameter(key.toString(),conditionMap.get(key));
				}
				query.setFirstResult(startIndex);
				query.setMaxResults(limit);
				List<T> resultList = query.list();
				return resultList;
			}
		}

	}

	/**
	 * 分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param parameters
	 *            存放查询语句中的参数值
	 * @param startIndex
	 *            记录起始行
	 * @param limit
	 *            显示条数
	 * @return rList 分页结果
	 * @throws Exception
	 */
	public List<T> findPageByQuery(final String queryString,final Object[] parameters, final int startIndex,  final int limit) throws Exception
	{
		if (parameters == null)
		{
			return this.findPageByQuery(queryString, startIndex, limit);
		} else
		{
			if (queryString == null)
			{
				throw new SystemException("传入参数为空");
			} else if (startIndex < 0 || limit < 0)
			{
				throw new SystemException("传入startIndex或limit不符合要求");
			} else
			{
				Query query = sessionFactory.getCurrentSession().createQuery(queryString);
				for (int i = 0; i < parameters.length; i++)
				{
					query.setParameter(i, parameters[i]);
				}
				query.setFirstResult(startIndex);
				query.setMaxResults(limit);
				List<T> resultList = query.list();
				return resultList;
			}
		}
	}

	/**
	 * 获得sql语句的执行结果，该语句返回一个能转化为整形的值【select count(*)】
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @return int sql语句的结果值
	 * @throws Exception
	 */
	public int getInt(String sql) throws Exception
	{
		if (sql == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			try {
				Long result = (Long) query.uniqueResult();
				return result.intValue();
			} catch (Exception e) {
				throw new SystemException("传入的sql语句不符合条件：能够返回一个能转化为整形的值");
			}
		}
	}

	/**
	 * 获得sql语句的执行结果，该语句返回一个能转化为整形的值【select count(*)】
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param conditionMap
	 *            存放sql语句中的 标识符和实际值对
	 * @return int sql语句的结果值
	 * @throws Exception
	 */
	public int getInt(String sql, Map<String,Object> conditionMap) throws Exception
	{
		if (conditionMap == null)
		{
			return this.getInt(sql);
		} else if (sql == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			Iterator<String> it = conditionMap.keySet().iterator();
			while (it.hasNext())
			{
				Object key = it.next();
				query.setParameter(key.toString(),conditionMap.get(key));
			}
			try {
				Long result = (Long) query.uniqueResult();
				return result.intValue();
			} catch (Exception e) {
				throw new SystemException("传入的sql语句不符合条件：能够返回一个能转化为整形的值");
			}
		}
	}
	
	/**
	 * 获得sql语句的执行结果，该语句返回一个能转化为整形的值【select count(*)】
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param parameters
	 *            参数
	 * @return int sql语句的结果值
	 * @throws Exception
	 */
	public int getInt(String sql, Object[] parameters) throws Exception
	{
		if (parameters == null)
		{
			return this.getInt(sql);
		} else if (sql == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			for (int i = 0; i < parameters.length; i++)
			{
				query.setParameter(i, parameters[i]);
			}
			try {
				Long result = (Long) query.uniqueResult();
				return result.intValue();
			} catch (Exception e) {
				throw new SystemException("传入的sql语句不符合条件：能够返回一个能转化为整形的值");
			}
		}

	}
	//******************************************分页****************************************************************
	
	//******************************************查找单条记录****************************************************************
	/**
	 * Load出对象 根据主键，获得数据库一条对应的记录,如果没有相应的实体，抛出异常
	 * 
	 * @param clazz
	 * @param id
	 * @return Object
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public T loadObject(Class<T> clazz, Serializable id) throws Exception
	{
		if (clazz == null || id == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			System.out.println(clazz+":"+id);
			T object = (T) sessionFactory.getCurrentSession().load(clazz, id);
			return object;
		}
	}

	/**
	 * get出对象 根据主键，获得数据库一条对应的记录,如果没有相应的实体，返回 null
	 * 
	 * @param clazz
	 * @param id
	 * @return Object
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public T getObject(Class<T> clazz, Serializable id) throws Exception
	{
		if (clazz == null || id == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			T object = (T) sessionFactory.getCurrentSession().get(clazz, id);
			return object;
		}
	}
	//******************************************查找单条记录****************************************************************
	//******************************************读出相关所有记录***************************************************************
	/**
	 * 读出所有的对象
	 * 
	 * @param clazz
	 *            对象类型
	 * @return List<Object> 该类的所有对象
	 * @throws Exception
	 */
	public List<T> queryAll(Class<T> clazz) throws Exception
	{
		if (clazz == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			List<T> resultList = sessionFactory.getCurrentSession().createQuery("from "+clazz.getSimpleName()).list();
			return resultList;
		}
	}

	/**
	 * 查找对象
	 * 
	 * @param queryString
	 *            要执行的语句
	 * @return List<Object> 满足查询条件的对象列表
	 * @throws Exception
	 */
	public List<T> queryObject(String queryString) throws Exception
	{

		if (queryString == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			List<T> resultList = sessionFactory.getCurrentSession().createQuery(queryString).list();
			return resultList;
		}
	}

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
	public List<T> queryObject(String queryString, Map<String,Object> conditionMap)throws Exception
	{
		if (conditionMap == null)
		{
			return this.queryObject(queryString);
		} else if (queryString == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			Query query = sessionFactory.getCurrentSession().createQuery(queryString);
			Iterator<String> it = conditionMap.keySet().iterator();
			while (it.hasNext())
			{
				Object key = it.next();
				query.setParameter(key.toString(),conditionMap.get(key));
			}
			List<T> resultList = query.list();
			return resultList;
		}
	}

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
	public List<T> queryObject(String queryString, Object[] parameters)throws Exception
	{
		if (parameters == null)
		{
			return this.queryObject(queryString);
		} else if (queryString == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			Query query = sessionFactory.getCurrentSession().createQuery(queryString);
			for (int i = 0; i < parameters.length; i++)
			{
				query.setParameter(i, parameters[i]);
			}
			List<T> resultList = query.list();
			return resultList;
		}
	}

	/**
	 * 读出id数组集合相关对象
	 * 
	 * @param clazz
	 *            对象类型
	 * @param ids
	 *            要读出对象的主键集合
	 * @return List<Object> 该类的所有对象
	 * @throws Exception
	 */
	public List<T> queryObjectByIds(Class<T> clazz, String[] ids)throws Exception
	{
		if (clazz != null && ids != null)
		{
			StringBuffer idString = new StringBuffer();
			for (int i = 0; i < ids.length - 1; i++)
			{
				idString.append("'" + ids[i] + "',");
			}
			idString = idString.append("'" + ids[ids.length - 1] + "'");
			String sql = "from " + clazz.getName() + " where id in ("
					+ idString.toString() + ")";
			return this.queryObject(sql);
		} else
		{
			throw new SystemException("传入参数为空");
		}
	}
	//******************************************读出相关所有记录***************************************************************
	//******************************************保存更新****************************************************************
	/**
	 * 保存对象
	 * 
	 * @param obj
	 * @return Serializable
	 * @throws Exception
	 */
	public Serializable saveObject(T T) throws Exception
	{

		if (T == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			Serializable ss = sessionFactory.getCurrentSession().save(T);
			return ss;
		}

	}

	/**
	 * 保存或更新对象
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void saveOrUpdateObject(T T) throws Exception
	{

		if (T == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			sessionFactory.getCurrentSession().saveOrUpdate(T);
		}
	}

	/**
	 * 更新对象
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void updateObject(T T) throws Exception
	{
		if (T == null)
		{
			throw new SystemException("传入参数为空");
		} else
		{
			sessionFactory.getCurrentSession().update(T);
		}
	}
	//******************************************保存更新****************************************************************

	public BasicDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

}
