package com.xingmima.dpfx.db;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.ArrayUtils;

/**
 * xingmima.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 *
 * @author tiaotiaohu
 * @version Dbutils, v 0.1
 * @date 2016/9/1 13:58
 */
public class Dbutils {
    private static DataSource dataSource;

    private Dbutils() {
    }

    public synchronized static DataSource getDs() throws SQLException {
        if (dataSource == null) {
            // 配置dbcp数据源
            BasicDataSource dbcp = new BasicDataSource();
            // dbcp
            dbcp.setDriverClassName("com.mysql.jdbc.Driver");
            dbcp.setUrl(
                    "jdbc:mysql://192.168.105.252:3306/xmm_shop_tools?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true&zeroDateTimeBehavior=convertToNull");

            dbcp.setUsername("ams");
            dbcp.setPassword("ams_admin");

            dbcp.setDefaultAutoCommit(true);
            dbcp.setInitialSize(10); // 初始化连接
            dbcp.setMaxIdle(5); // 最大空闲连接
            dbcp.setMinIdle(5);// 最小空闲连接
            dbcp.setMaxActive(20); // 最大连接数量
            dbcp.setRemoveAbandoned(true); // 是否自动回收超时连接
            dbcp.setValidationQuery("select 1");
            // #超时时间(以秒数为单位)
            // #设置超时时间有一个要注意的地方，超时时间=现在的时间-程序中创建Connection的时间，
            // #如果
            // maxActive比较大，比如超过100，那么removeAbandonedTimeout可以设置长一点比如180，也就是三分钟无响应的连接进行回收，当然应用的不同设置长度也不同。
            dbcp.setRemoveAbandonedTimeout(180);
            dbcp.setMaxWait(1000);

            dataSource = (DataSource) dbcp;
        }
        return dataSource;
    }

    // private final static QueryRunner _g_runner = new QueryRunner();
    private final static ColumnListHandler<Object> _g_columnListHandler = new ColumnListHandler<Object>() {
        @Override
        protected Object handleRow(ResultSet rs) throws SQLException {
            Object obj = super.handleRow(rs);
            if (obj instanceof BigInteger)
                return ((BigInteger) obj).longValue();
            return obj;
        }
    };

    private final static ScalarHandler<?> _g_scaleHandler = new ScalarHandler<Object>() {
        @Override
        public Object handle(ResultSet rs) throws SQLException {
            Object obj = super.handle(rs);
            if (obj instanceof BigInteger)
                return ((BigInteger) obj).longValue();
            return obj;
        }
    };

    private final static List<Class<?>> PrimitiveClasses = new ArrayList<Class<?>>() {
        private static final long serialVersionUID = 1L;

        {
            add(Long.class);
            add(Integer.class);
            add(String.class);
            add(java.util.Date.class);
            add(java.sql.Date.class);
            add(java.sql.Timestamp.class);
        }
    };

    private final static boolean _IsPrimitive(Class<?> cls) {
        return cls.isPrimitive() || PrimitiveClasses.contains(cls);
    }

    /**
     * 获取任意列数据(只返回一列数据)
     *
     * @param beanClass
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> T get(Class<T> beanClass, String sql, Object... params)
            throws SQLException {
        try {
            return (T) new QueryRunner(getDs())
                    .query(sql,
                            _IsPrimitive(beanClass)
                                    ? _g_scaleHandler
                                    : new ScalarHandler(),
                            params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    /**
     * 返回Map结果集(多列)
     *
     * @param <T>
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T map(String sql, Object... params) throws SQLException {
        try {
            return (T) new QueryRunner(getDs()).query(sql, new MapHandler(),
                    params);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * 返回Map列表结果集
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T maplist(String sql, Object... params)
            throws SQLException {
        try {
            return (T) new QueryRunner(getDs()).query(sql, new MapListHandler(),
                    params);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * 读取某个对象
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> T read(Class<T> beanClass, String sql, Object... params)
            throws SQLException {
        try {
            return (T) new QueryRunner(getDs()).query(sql,
                    _IsPrimitive(beanClass)
                            ? _g_scaleHandler
                            : new BeanHandler(beanClass),
                    params);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public static <T> T read_cache(Class<T> beanClass, String cache,
                                   Serializable key, String sql, Object... params) {
        return null;
    }

    /**
     * 对象查询
     *
     * @param <T>
     * @param beanClass
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> List<T> query(Class<T> beanClass, String sql,
                                    Object... params) throws SQLException {
        try {
            return (List<T>) new QueryRunner(getDs()).query(sql,
                    _IsPrimitive(beanClass)
                            ? _g_columnListHandler
                            : new BeanListHandler(beanClass),
                    params);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * 支持缓存的对象查询
     *
     * @param <T>
     * @param beanClass
     * @param cache_region
     * @param key
     * @param sql
     * @param params
     * @return
     */
    public static <T> List<T> query_cache(Class<T> beanClass,
                                          String cache_region, Serializable key, String sql,
                                          Object... params) {
        return null;
    }

    /**
     * 分页查询
     *
     * @param <T>
     * @param beanClass
     * @param sql
     * @param page
     * @param count
     * @param params
     * @return
     * @throws SQLException
     */
    public static <T> List<T> query_slice(Class<T> beanClass, String sql,
                                          int page, int count, Object... params) throws SQLException {
        if (page < 0 || count < 0)
            throw new IllegalArgumentException(
                    "Illegal parameter of 'page' or 'count', Must be positive.");
        int from = (page - 1) * count;
        count = (count > 0) ? count : Integer.MAX_VALUE;
        return query(beanClass, sql + " LIMIT ?,?",
                ArrayUtils.addAll(params, new Integer[]{from, count}));
    }

    /**
     * 支持缓存的分页查询
     *
     * @param <T>
     * @param beanClass
     * @param cache
     * @param cache_key
     * @param cache_obj_count
     * @param sql
     * @param page
     * @param count
     * @param params
     * @return
     */
    public static <T> List<T> query_slice_cache(Class<T> beanClass,
                                                String cache, Serializable cache_key, int cache_obj_count,
                                                String sql, int page, int count, Object... params) {
        return null;
    }

    /**
     * 执行统计查询语句，语句的执行结果必须只返回一个数值
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static long cnt(String sql, Object... params) throws SQLException {
        try {
            Number num = (Number) new QueryRunner(getDs()).query(sql,
                    _g_scaleHandler, params);
            return (num != null) ? num.longValue() : -1;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * 执行统计查询语句，语句的执行结果必须只返回一个数值
     *
     * @param cache_region
     * @param key
     * @param sql
     * @param params
     * @return
     */
    public static long cnt_cache(String cache_region, Serializable key,
                                 String sql, Object... params) {
        return 0L;
    }

    /**
     * 执行INSERT/UPDATE/DELETE语句
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static int update(String sql, Object... params) throws SQLException {
        try {
            return new QueryRunner(getDs()).update(sql, params);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    /**
     * 按对象更新数据
     *
     * @param sql
     * @param bean
     * @param column
     * @throws SQLException
     */
    public static void update(String sql, Object bean, String... column)
            throws SQLException {
        Connection conn = null;
        PreparedStatement psts = null;
        try {
            conn = getDs().getConnection();
            psts = conn.prepareStatement(sql);
            new QueryRunner().fillStatementWithBean(psts, bean, column);
            psts.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            DbUtils.close(psts);
            DbUtils.close(conn);
        }
    }

    /**
     * 按对象插入并返回ID
     *
     * @param sql
     * @param bean
     * @param column
     * @return
     * @throws SQLException
     */
    public static Long insert(String sql, Object bean, String... column)
            throws SQLException {
        Connection conn = null;
        PreparedStatement psts = null;
        ResultSet rs = null;
        try {
            conn = getDs().getConnection();
            psts = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            new QueryRunner().fillStatementWithBean(psts, bean, column);
            psts.executeUpdate();
            rs = psts.getGeneratedKeys();
            return rs.next() ? rs.getLong(1) : -1;
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            DbUtils.close(rs);
            DbUtils.close(psts);
            DbUtils.close(conn);
        }
    }

    /**
     * 批量执行指定的SQL语句
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public static int[] batch(String sql, Object[][] params)
            throws SQLException {
        try {
            return new QueryRunner(getDs()).batch(sql, params);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
