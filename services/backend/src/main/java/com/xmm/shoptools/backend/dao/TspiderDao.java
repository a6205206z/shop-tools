package com.xmm.shoptools.backend.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmm.shoptools.backend.dao.base.BaseDaoImpl;
import com.xmm.shoptools.backend.entity.Tspider;
import com.xmm.shoptools.backend.vo.TspiderQuery;

/**
 * @author auto created
 * @version 1.0
 * @since 1.0
 */
@Repository
public class TspiderDao extends BaseDaoImpl<Tspider>{

    public Integer count(TspiderQuery query) {
        return super.getSqlSession().selectOne("Tspider.count", query);
    }

    public List<Tspider> query(TspiderQuery query) {
        return super.getSqlSession().selectList("Tspider.query", query);
    }

}
