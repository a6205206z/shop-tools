package com.xmm.shoptools.backend.service;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmm.shoptools.backend.dao.TshopDao;
import com.xmm.shoptools.backend.entity.Tshop;
import com.xmm.shoptools.backend.utils.BeanVoUtil;
import com.xmm.shoptools.backend.utils.RandomUtil;
import com.xmm.shoptools.backend.vo.PageResult;
import com.xmm.shoptools.backend.vo.TshopDTO;
import com.xmm.shoptools.backend.vo.TshopQuery;
import com.xmm.shoptools.backend.vo.TshopVO;

/**
 * @author auto created
 * @version 1.0
 * @since 1.0
 */
@Service
public class TshopService{

    @Autowired
    private TshopDao tshopDao;
    
    public PageResult<TshopVO> query(TshopQuery query) {
        PageResult<TshopVO> pr = new PageResult<TshopVO>();

        // 添加符合条件的总记录数
        Integer total = this.tshopDao.count(query);
        pr.setTotal(total);
        
        // 添加当前页显示的内容
        List<Tshop> rows = this.tshopDao.query(query);
        List<TshopVO> rowsVo = BeanVoUtil.copyList(rows, TshopVO.class);
        pr.setRows(rowsVo);

        return pr;
    }

    public void addTshop(TshopDTO dto,String shopid,String sellerId) {
        Tshop tshop = new Tshop();
        BeanVoUtil.copyObject(dto, tshop);
        tshop.setShopid(Long.parseLong(shopid));
        tshop.setSellerId(Long.parseLong(sellerId));
        tshop.setId(RandomUtil.getUUID());
        tshop.setStatus("0");
        tshop.setCreated(new Date());
        tshop.setUpdated(new Date());
        this.tshopDao.insert(tshop);
    }

    public void editTshop(TshopDTO dto,String shopid,String sellerId) {
        Tshop tshop = this.getTshop(dto.getId());
        if(tshop!=null){
            BeanVoUtil.copyObject(dto, tshop);
            tshop.setShopid(Long.parseLong(shopid));
            tshop.setSellerId(Long.parseLong(sellerId));
            tshop.setUpdated(new Date());
            this.tshopDao.update(tshop);
        }
    }
    
    
    public Tshop getTshop(String id) {
        if(id!=null){
            return tshopDao.getWhitOutCache(id);
        }
        return null;
    }
    /*启用和禁用*/
    public boolean disableTshop(String id) {
        Tshop tshop = this.getTshop(id);
        if(tshop!=null&&tshop.getStatus().equals("0")){
            tshop.setStatus("1");
            tshop.setUpdated(new Date());
            this.tshopDao.update(tshop);
            return true;
        }else if (tshop!=null&&tshop.getStatus().equals("1")) {
            tshop.setStatus("0");
            tshop.setUpdated(new Date());
            this.tshopDao.update(tshop);
            return true;
        }
        return false;
    }
    
}
