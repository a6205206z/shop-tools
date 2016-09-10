package com.xmm.shoptools.backend.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmm.shoptools.backend.dao.base.BaseDaoImpl;
import com.xmm.shoptools.backend.entity.Tjob;
import com.xmm.shoptools.backend.vo.TjobQuery;

/**
 * @author auto created
 * @version 1.0
 * @since 1.0
 */
@Repository
public class TjobDao extends BaseDaoImpl<Tjob>{

    public Integer count(TjobQuery query) {
        return super.getSqlSession().selectOne("Tjob.count", query);
    }

    public List<Tjob> query(TjobQuery query) {
        return super.getSqlSession().selectList("Tjob.query", query);
    }

}
