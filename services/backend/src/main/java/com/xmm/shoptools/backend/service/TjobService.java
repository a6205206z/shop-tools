package com.xmm.shoptools.backend.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmm.shoptools.backend.dao.TjobDao;
import com.xmm.shoptools.backend.entity.Tjob;
import com.xmm.shoptools.backend.utils.BeanVoUtil;
import com.xmm.shoptools.backend.vo.PageResult;
import com.xmm.shoptools.backend.vo.TjobQuery;
import com.xmm.shoptools.backend.vo.TjobVO;

/**
 * @author auto created
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
public class TjobService{
    @Autowired
    private TjobDao tjobDao ;
    
    public PageResult<TjobVO> query(TjobQuery query) {
        PageResult<TjobVO> pr = new PageResult<TjobVO>();

        // 添加符合条件的总记录数
        Integer total = this.tjobDao.count(query);
        pr.setTotal(total);
        
        
        // 添加当前页显示的内容
        List<Tjob> rows = this.tjobDao.query(query);
        List<TjobVO> rowsVo = BeanVoUtil.copyList(rows, TjobVO.class);
        
        for (TjobVO tjobVO : rowsVo) {
            if(tjobVO!=null&&tjobVO.getFinishtime()!=null){
                tjobVO.setCompletedStatus("1");
            }else if (tjobVO!=null&&tjobVO.getFinishtime()==null) {
                tjobVO.setCompletedStatus("0");
            }
        }
        pr.setRows(rowsVo);

        return pr;
    }

}
