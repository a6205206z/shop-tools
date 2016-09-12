package com.xmm.shoptools.backend.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmm.shoptools.backend.dao.base.BaseDaoImpl;
import com.xmm.shoptools.backend.entity.Tshop;
import com.xmm.shoptools.backend.vo.TshopQuery;

/**
 * @author auto created
 * @version 1.0
 * @since 1.0
 */
@Repository
public class TshopDao extends BaseDaoImpl<Tshop>{

    public Integer count(TshopQuery query) {
        return super.getSqlSession().selectOne("Tshop.count", query);
    }

    public List<Tshop> query(TshopQuery query) {
        return super.getSqlSession().selectList("Tshop.query", query);
    }

}
