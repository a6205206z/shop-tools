package com.xmm.shoptools.backend.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmm.shoptools.backend.dao.DshopDao;
import com.xmm.shoptools.backend.entity.Dshop;
import com.xmm.shoptools.backend.utils.BeanVoUtil;
import com.xmm.shoptools.backend.vo.DshopQuery;
import com.xmm.shoptools.backend.vo.DshopVO;
import com.xmm.shoptools.backend.vo.PageResult;

/**
 * @author auto created
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
public class DshopService{
    @Autowired
    private DshopDao dshopDao;
    
    public PageResult<DshopVO> query(DshopQuery query) {
        PageResult<DshopVO> pr = new PageResult<DshopVO>();

        // 添加符合条件的总记录数
        Integer total = this.dshopDao.count(query);
        pr.setTotal(total);
        
        // 添加当前页显示的内容
        List<Dshop> rows = this.dshopDao.query(query);
        List<DshopVO> rowsVo = BeanVoUtil.copyList(rows, DshopVO.class);
        pr.setRows(rowsVo);

        return pr;
    }

}
