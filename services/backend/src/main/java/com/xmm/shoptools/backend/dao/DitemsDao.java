package com.xmm.shoptools.backend.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmm.shoptools.backend.dao.base.BaseDaoImpl;
import com.xmm.shoptools.backend.entity.Ditems;
import com.xmm.shoptools.backend.vo.DitemsQuery;

/**
 * @author auto created
 * @version 1.0
 * @since 1.0
 */
@Repository
public class DitemsDao  extends BaseDaoImpl<Ditems>{

    public List<Ditems> query(DitemsQuery query) {
        return super.getSqlSession().selectList("Ditems.query", query);
    }

    public Integer count(DitemsQuery query) {
        return super.getSqlSession().selectOne("Ditems.count", query);
    }

    public Map<Long,Integer> dateCount() {
        return super.getSqlSession().selectMap("Ditems.dateCount","date");
    }

}
