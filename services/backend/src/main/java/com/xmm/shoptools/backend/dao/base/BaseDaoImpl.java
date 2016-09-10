package com.xmm.shoptools.backend.dao.base;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.query.SortQuery;
import org.springframework.util.StringUtils;

import com.xmm.shoptools.backend.entity.BaseModel;

public class BaseDaoImpl<T extends BaseModel> extends SqlSessionDaoSupport implements BaseDao<T> {
	// 实体类映射
	private Class<T> entityClass;

	private String prefix = "";
	// 类名
	private String className = "";

	// redis模版类
	@Autowired
	protected RedisTemplate<String, Object> redisTemplate;

	public static Long TWO_MIN = 2L;
	public static Long DEFTIME = 5L;
	public static Long TENTIME = 10L;
	public static Long FIFTEEN_TIME = 15L;
	public static Long HALF_HOUR = 30L;
	public static Long ONE_HOUR = 60L;
	public static Long TWO_HOUR = 120L;
	public static Long HALF_DAY = 720L;
	public static Long ONE_DAY = 1440L;

	/**
	 * 构造初始
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		// 类路径
		className = entityClass.getName();
		// namaspace
		prefix = className.substring(className.lastIndexOf(".") + 1);
	}

	// 类统一标识符
	public String getPrefixLower() {
		return prefix.toLowerCase();
	}

	/**
	 * 根据id生成key 如:brand:id:1
	 * 
	 * @param id
	 * @return
	 */
	public String getIdKey(Object id) {
		// return DigestUtils.md5Hex(className + "@id=" + id);
		return prefix.toLowerCase() + ":id:" + id;
	}

	/**
	 * 根据map条件生成redis风格的key 如： brandlist:cid:pid:status:1:2:normal
	 * 
	 * @param prefix
	 * @param map
	 * @return
	 */
	public static String mapToKeys(String prefix, Map<String, Object> map) {
		if (null == map)
			map = new HashMap<String, Object>();

		StringBuffer sb = new StringBuffer(prefix + ":");

		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);// 按key字典排序

		int size = keys.size();

		for (int i = 0; i < size; i++) {
			sb.append(keys.get(i) + ":");
		}
		for (int i = 0; i < size; i++) {
			String key = keys.get(i);
			if (i == size - 1) {
				sb.append(map.get(key));
			} else {
				sb.append(map.get(key) + ":");
			}
		}
		return sb.toString().replaceAll("\\s", "").toLowerCase();
	}

	/**
	 * 插入数据
	 * 
	 * @param object
	 */
	public void insert(T object) {
		this.insert(object, false);
	}

	/**
	 * 插入数据
	 * 
	 * @param object
	 * @param cache
	 * @return
	 */
	public void insert(T object, boolean cache) {
		super.getSqlSession().insert(prefix + ".insert", object);

		if (cache) {
			String key = getIdKey(object.getId());
			this.set(key, object);
		}
	}

	/**
	 * 插入数据 for 数据抓取
	 * 
	 * @param object
	 * @param cache
	 * @return
	 */
	public int insertUpdate(String mehtod, Object param) {
		return super.getSqlSession().update(prefix + "." + mehtod, param);
	}

	// <insert id="id" parameterType="List">
	// insert into table (col1 ,col2) values
	// <foreach collection="list" item="item" index="index" separator=",">
	// (#{item.col1},#{item.col2})
	// </foreach>
	// </insert>
	// 批量插入
	public void insertBatch(String method, List<T> param) {
		super.getSqlSession().insert(method, param);
	}

	/**
	 * 条件删除
	 * 
	 * @param method
	 * @param objs
	 * @return
	 */
	public int delete(String method, Object param) {
		return super.getSqlSession().delete(prefix + "." + method, param);
	}

	/**
	 * 按对象删除
	 * 
	 * @param t
	 */
	public void delete(T t) {
		super.getSqlSession().delete(prefix + ".delete", t.getId());
		this.deleteById(t.getId());
	}

	/**
	 * 直接调用SQL进行更新，无参
	 * 
	 * @param map
	 * @param method
	 * @return
	 */
	public int update(String method) {
		return super.getSqlSession().update(prefix + "." + method);
	}

	/**
	 * 更新实体对象
	 * 
	 * @param t
	 */
	public int update(T t) {
		return this.update(t, false);
	}

	/**
	 * 更新实体对象，并更新到缓存
	 * 
	 * @param t
	 * @param cache
	 */
	public int update(T t, boolean cache) {
		return this.update(t, "update", cache);
	}

	public int update(T t, String method, boolean cache) {
		int f = super.getSqlSession().update(prefix + "." + method, t);
		if (f > 0) {
			if (cache) {
				this.set(getIdKey(t.getId()), t);
			}
		}
		return f;
	}

	public int update(String method, String param) {
		return super.getSqlSession().update(prefix + "." + method, param);
	}

	public int update(String method, Object param) {
		return super.getSqlSession().update(prefix + "." + method, param);
	}

	/**
	 * 更新或添加
	 * 
	 * @param t
	 */
	public void saveOrupdate(T t) {
		if (null != t.getId()) {
			this.update(t);
		} else {
			this.insert(t);
		}
	}

	/**
	 * 查询缓存->查询db->放入缓存->返回对象
	 * 
	 * @param id
	 * @return
	 */
	public T get(Long id) {
		return this.get(id, HALF_HOUR);
	}

	public T get(Integer id) {
		return this.get(new Long(id), HALF_HOUR);
	}

	public T getKeystring(String id) {
		return this.get(id, HALF_HOUR);
	}

	/**
	 * 查询缓存->查询db->放入指定时间缓存->返回对象
	 * 
	 * @param id
	 * @return
	 */
	public T get(Object id, Long timeout) {
		String key = getIdKey(id);
		@SuppressWarnings("unchecked")
		T object = (T) this.getEntityObject(entityClass, key);

		if (object != null)
			return object;

		object = this.getWhitOutCache(id);
		if (object != null)
			this.set(key, object, timeout);
		return object;
	}

	public T getWhitOutCache(Object id) {
		return super.getSqlSession().selectOne(prefix + ".select", id);
	}

	public T getWhitOutCache(Map<String, Object> map, String mehtod) {
		return super.getSqlSession().selectOne(prefix + "." + mehtod, map);
	}

	/**
	 * 
	 * 直接从db加载
	 * 
	 * @param map
	 * @return
	 */
	public Integer cntByMap(Map<String, Object> map) {
		return (Integer) super.getSqlSession().selectOne(prefix + ".cntByMap", map);
	}

	/**
	 * 直接从db加载
	 * 
	 * @param map
	 * @return
	 */
	public List<T> getByMap(Map<String, Object> map) {
		return this.getByMap(map, "getByMap");
	}

	/**
	 * 直接从db加载列表数据
	 * 
	 * @param map
	 * @param method
	 * @return
	 */
	public List<T> getByMap(Map<String, Object> map, String method) {
		return this.getObjectsByIds(super.getSqlSession().selectList(prefix + "." + method, map));
	}

	/**
	 * 不走缓存 获取对象序列
	 * 
	 * @param map
	 * @param method
	 * @return
	 */
	public List<T> getListByMap(Map<String, Object> map, String method) {
		return super.getSqlSession().selectList(prefix + "." + method, map);
	}

	/**
	 * 实体对象list, 直接缓存整个结果集，常用于变化少的列表数据
	 * 
	 * @param map
	 * @param method
	 * @param key
	 * @param timeout
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getListAndCacheResult(Map<String, Object> map, String method, String key, Long timeout) {
		return (List<T>) this.getAnyList(entityClass, map, method, key, timeout);
	}

	/**
	 * multiGet也可实现，后期优化
	 * 
	 * @param list
	 * @return
	 */
	public List<T> getObjectsByIds(List<?> list) {
		return this.getObjectsByIds(list, HALF_HOUR);
	}

	public List<T> getObjectsByIds(List<?> list, Long timeout) {
		List<T> re = new ArrayList<T>();
		if (list == null)
			return re;
		for (int i = 0; i < list.size(); i++) {
			if (!StringUtils.isEmpty(list.get(i)))
				re.add(this.get(list.get(i), timeout));
		}
		return re;
	}

	public Integer cntByMap(Map<String, Object> map, String method) {
		return super.getSqlSession().selectOne(prefix + "." + method, map);
	}

	public Integer cntByMapAndCache(Map<String, Object> map, String method) {
		return this.cntByMapAndCache(map, method, DEFTIME);
	}

	public Integer cntByMapAndCache(Map<String, Object> map, String method, Long timeout) {
		if (null == map)
			map = new HashMap<String, Object>();
		map.put("m", "cnt");
		String key = mapToKeys(prefix, map);
		return this.cntByMapAndCache(map, method, key, timeout);
	}

	/**
	 * 用于实体表：统计，汇总的地方
	 * 
	 * @param map
	 * @param method
	 * @return
	 */
	public Integer cntByMapAndCache(Map<String, Object> map, String method, String key, Long timeout) {
		return (Integer) this.getAnyObject(Integer.class, map, method, key, timeout);
	}

	public List<T> getByMapAndCache(Map<String, Object> map, String method) {
		return this.getByMapAndCache(map, method, DEFTIME);
	}

	public List<T> getByMapAndCache(Map<String, Object> map, String method, Long timeout) {
		if (null == map)
			map = new HashMap<String, Object>();
		map.put("m", "list");
		String key = mapToKeys(prefix, map);
		return this.getByMapAndCache(map, method, key, timeout);
	}

	/**
	 * 用于实体表：列表数据----------查询缓存->查询db->返回id列表->返回列表集
	 * 
	 * @param map
	 * @param method
	 * @return
	 */
	public List<T> getByMapAndCache(Map<String, Object> map, String method, String key, Long timeout) {
		List<Long> list = (List<Long>) this.getCacheIds(key);
		if (list == null) {
			list = super.getSqlSession().selectList(prefix + "." + method, map);
			if (null != list) {
				this.set(key, list, timeout);
			}
		}
		return this.getObjectsByIds(list);
	}

	/**
	 * 加载一个任意对象,实体，也可Long, String普通对象 同时加入缓存
	 * 
	 * @param map
	 * @param method
	 * @param key
	 * @param timeout
	 * @return
	 */
	public Object getAnyObject(Class<?> alass, Map<String, Object> map, String method, String key, Long timeout) {
		Object object = (Object) this.getEntityObject(alass, key);
		if (object != null)
			return object;
		object = (Object) super.getSqlSession().selectOne(prefix + "." + method, map);
		if (object != null) {
			this.set(key, object, timeout);
		}
		return object;
	}

	/**
	 * 加载任意对象列表，完整结果集，
	 * 
	 * @param alass
	 * @param map
	 * @param method
	 * @param key
	 * @param timeout
	 * @return
	 */
	public Object getAnyList(Class<?> alass, Map<String, Object> map, String method, String key, Long timeout) {
		Object object = (Object) this.getCacheArray(alass, key);
		if (object != null)
			return object;
		object = (List<Object>) super.getSqlSession().selectList(prefix + "." + method, map);
		if (object != null) {
			this.set(key, object, timeout);
		}
		return object;
	}

	/**
	 * 保存至缓存，默认保存2分钟，严格控制缓存key跟时间
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		this.set(key, value, DEFTIME);
	}

	/**
	 * 对象转义成json，保存至缓存。
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 */
	public void set(String key, Object value, Long timeout) {
		if (timeout == null)
			timeout = DEFTIME;
		this.redisTemplate.opsForValue().set(key, JsonRedisSeriaziler.seriazileAsString(value), timeout,
				TimeUnit.MINUTES);
	}

	/**
	 * 根据key返回结果对象（json串）
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(String key) {
		Object obj = this.redisTemplate.opsForValue().get(key);
		if (obj == null)
			return null;
		return obj;
	}

	/**
	 * 从缓存中加载对象
	 * 
	 * @param key
	 * @return
	 */
	public Object getEntityObject(Class<?> entity, String key) {
		Object obj = this.getCache(key);
		if (obj == null)
			return null;
		return JsonRedisSeriaziler.deserializeAsObject(obj.toString(), entity);
	}

	/**
	 * 转换成对象list
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getCacheArray(Class<?> entity, String key) {
		Object obj = (Object) this.getCache(key);
		if (obj == null)
			return null;
		return (List<T>) JsonRedisSeriaziler.deserializeAsList(obj.toString(), entity);
	}

	// @SuppressWarnings("unchecked")
	// public List<Object> getCacheObjectArray(Class<?> entity, String key) {
	// Object obj = (Object) this.get(key);
	// if (obj == null)
	// return null;
	// return (List<Object>)
	// JsonRedisSeriaziler.deserializeAsList(obj.toString(), entity);
	// }

	/**
	 * 缓存结果集id列表
	 * 
	 * @param key
	 * @return
	 */
	public List<Long> getCacheIds(String key) {
		Object obj = this.getCache(key);
		if (obj == null)
			return null;
		return JsonRedisSeriaziler.deserializeAsList(obj.toString(), Long.class);
	}

	/**
	 * 按key删除缓存
	 * 
	 * @param key
	 */
	public void deleteByKey(String key) {
		this.redisTemplate.delete(key);
	}

	/**
	 * 按id删除缓存
	 * 
	 * @param id
	 */
	public void deleteById(Object id) {
		this.deleteByKey(getIdKey(id));
	}

	/**
	 * 删除一组key缓存
	 * 
	 * @param keys
	 */
	public void deleteByKeys(List<String> keys) {
		this.redisTemplate.delete(keys);
	}

	/**
	 * spring-data-redis模版类
	 * 
	 * @return
	 */
	public RedisTemplate<String, Object> getRedisTemplate() {
		return this.redisTemplate;
	}

	public boolean exists(String key) {
		return this.redisTemplate.hasKey(key);
	}

	public Long forSetSize(String key) {
		return this.redisTemplate.opsForSet().size(key);
	}

	@Deprecated
	public long del(final String... keys) {
		return this.redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				long result = 0;
				for (int i = 0; i < keys.length; i++) {
					result = connection.del(keys[i].getBytes());
				}
				return result;
			}
		});
	}

	/**
	 * 通配key并删除
	 * 
	 * @param pattern
	 */
	public void del(String pattern) {
		Set<String> keys = this.redisTemplate.keys(pattern.toLowerCase());
		if (!keys.isEmpty()) {
			this.redisTemplate.delete(keys);
		}
	}

	/** 功能演示： spring通过callback直接与redis通信 */
	@Deprecated
	void clearRedisdb() {
		this.redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				connection.flushDb();
				return null;
			}
		});
	}

	/**
	 * redis 查询，返回固定实体对象
	 * 
	 * 
	 * @param query
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> get(SortQuery<String> query, Class<?> entity) {
		List<Object> list = this.redisTemplate.sort(query);
		List<T> back = new ArrayList<T>();
		if (!list.isEmpty()) {
			for (Object str : list) {
				if (null != str)
					back.add((T) JsonRedisSeriaziler.deserializeAsObject(str.toString(), entity));
			}
		}
		return back;
	}

	/**
	 * 追加id至缓存序列
	 */
	public void insertIdToCache(String key, String id) {
		this.redisTemplate.opsForSet().add(key, id);
	}

	/**
	 * 设置排序字段 缓存序列
	 */
	public void setSortColumn(String sortKey, Object sort, Long timeout) {
		this.set(sortKey, sort, timeout);
	}

	/**
	 * 缓存计数器+1
	 * 
	 * @param key
	 * @version v1.0
	 */
	public void increment(String key) {
		this.redisTemplate.boundValueOps(key).increment(1L);
	}

	// 金额+
	public void increment(String key, double d) {
		this.redisTemplate.boundValueOps(key).increment(d);
	}

	/**
	 * 直接存，非json
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 * @version v1.0
	 */
	public void setNoJson(String key, Object value, Long timeout) {
		this.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MINUTES);
	}

	public List<T> selectSqlCacheSet(Map<String, Object> map, String method, String key, Long timeout) {
		return this.getObjectsByIds(this.selectSqlCacheSetId(map, method, key, timeout));
	}

	/**
	 * 将数据放入Set集合，数据变更时，只更新部分值，不删除整体缓存(无序集合)
	 * 
	 * @return
	 * @version v1.0
	 */
	public List<Object> selectSqlCacheSetId(Map<String, Object> map, String method, String key, Long timeout) {
		// 从缓存中取出
		if (this.exists(key)) {
			Set<Object> cs = this.redisTemplate.opsForSet().members(key);
			if (null != cs) {// && !cs.isEmpty()
				return new ArrayList<Object>(cs);
			}
		}
		/* 读库 */
		List<Object> source = super.getSqlSession().selectList(prefix + "." + method, map);
		if (source != null && !source.isEmpty()) {
			for (Object id : source) {
				/* 添加进set */
				this.redisTemplate.opsForSet().add(key, id.toString());
			}
			/* 过期设置，分钟 */
			this.redisTemplate.expire(key, timeout, TimeUnit.MINUTES);
			return source;
		} else {
			this.redisTemplate.opsForSet().add(key, "");
		}
		return new ArrayList<Object>();
	}

	/**
	 * Set集合检查元素是否存在
	 * 
	 * @param key
	 * @param val
	 * @return
	 * @version v1.0
	 */
	public boolean isMember(String key, String val) {
		return this.redisTemplate.opsForSet().isMember(key, val);
	}

	/**
	 * 添加Set集合元素
	 * 
	 * @param key
	 * @param val
	 * @return
	 * @version v1.0
	 */
	public Long addSet(String key, String val) {
		return this.redisTemplate.opsForSet().add(key, val);
	}

	/**
	 * 删除集合中元素
	 * 
	 * @param key
	 * @param val
	 * @return
	 * @version v1.0
	 */
	public Long remove(String key, String val) {
		return this.redisTemplate.boundSetOps(key).remove(val);
	}

	/**
	 * 差集，在集合x中而不在集合y中的元素。
	 * 
	 * @param leftkey
	 * @param rightkey
	 * @return
	 * @version v1.0
	 */
	public Set<Object> diff(String leftkey, String rightkey) {
		return this.redisTemplate.boundSetOps(leftkey).diff(rightkey);
	}

	/**
	 * 随机取Set集合中值
	 * 
	 * @param key
	 * @param val
	 * @return
	 * @version v1.0
	 */
	public Long randomMember(String key, String val) {
		return (Long) this.redisTemplate.boundSetOps(key).randomMember();
	}
}
