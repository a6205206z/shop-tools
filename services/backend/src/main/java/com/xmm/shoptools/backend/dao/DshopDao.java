package com.xmm.shoptools.backend.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmm.shoptools.backend.dao.base.BaseDaoImpl;
import com.xmm.shoptools.backend.entity.Dshop;
import com.xmm.shoptools.backend.vo.DshopQuery;

/**
 * @author auto created
 * @version 1.0
 * @since 1.0
 */
@Repository
public class DshopDao extends BaseDaoImpl<Dshop>{

    public Integer count(DshopQuery query) {
        return super.getSqlSession().selectOne("Dshop.count", query);
    }

    public List<Dshop> query(DshopQuery query) {
        return super.getSqlSession().selectList("Dshop.query", query);
    }

}
