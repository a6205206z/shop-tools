package com.xmm.shoptools.backend.dao.base;

import java.util.List;
import java.util.Map;

import com.xmm.shoptools.backend.entity.BaseModel;

interface BaseDao<T extends BaseModel> {

	void insert(T object);

	void insert(T object, boolean cache);

	void insertBatch(String method, List<T> param);

	void delete(T t);

	int delete(String method, Object param);

	int update(String method);

	int update(T t);

	int update(T t, boolean cache);

	int update(String method, String param);

	int update(String method, Object param);

	void saveOrupdate(T t);

	T get(Long id);

	T getWhitOutCache(Object id);

	Integer cntByMap(Map<String, Object> map);

	List<T> getByMap(Map<String, Object> map);

	List<T> getByMap(Map<String, Object> map, String method);

	List<T> getListByMap(Map<String, Object> map, String method);

	List<T> getListAndCacheResult(Map<String, Object> map, String method, String key, Long timeout);

	List<T> getObjectsByIds(List<?> list);

	Integer cntByMap(Map<String, Object> map, String method);

	Integer cntByMapAndCache(Map<String, Object> map, String method);

	List<T> getByMapAndCache(Map<String, Object> map, String method);

	Integer cntByMapAndCache(Map<String, Object> map, String method, Long timeout);

	List<T> getByMapAndCache(Map<String, Object> map, String method, Long timeout);

	Object getAnyObject(Class<?> alass, Map<String, Object> map, String method, String key, Long timeout);

	Object getAnyList(Class<?> alass, Map<String, Object> map, String method, String key, Long timeout);

	void set(String key, Object value);

	void set(String key, Object value, Long timeout);

	Object getCache(String key);

	Object getEntityObject(Class<?> entity, String key);

	List<T> getCacheArray(Class<?> entity, String key);

	List<Long> getCacheIds(String key);

	void deleteByKey(String key);

	void deleteById(Object id);

	void deleteByKeys(List<String> keys);

	void del(String pattern);
}
