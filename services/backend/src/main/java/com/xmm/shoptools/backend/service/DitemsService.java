package com.xmm.shoptools.backend.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmm.shoptools.backend.dao.DitemsDao;
import com.xmm.shoptools.backend.entity.Ditems;
import com.xmm.shoptools.backend.utils.BeanVoUtil;
import com.xmm.shoptools.backend.vo.DitemsQuery;
import com.xmm.shoptools.backend.vo.DitemsVO;
import com.xmm.shoptools.backend.vo.PageResult;

/**
 * @author auto created
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
public class DitemsService{
    @Autowired
    private DitemsDao ditemsDao;
    
    public PageResult<DitemsVO> query(DitemsQuery query) {
        PageResult<DitemsVO> pr = new PageResult<DitemsVO>();

        // 添加符合条件的总记录数
        Integer total = this.ditemsDao.count(query);
        pr.setTotal(total);
        
        // 添加当前页显示的内容
        List<Ditems> rows = this.ditemsDao.query(query);
        List<DitemsVO> rowsVo = BeanVoUtil.copyList(rows, DitemsVO.class);
        pr.setRows(rowsVo);

        return pr;
    }

}
