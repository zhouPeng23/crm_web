package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.Shop;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface ShopMapper extends BaseMapper<Shop> {

    public Shop selectShopById(Integer shopId);

    public List<Shop> selectShopList(Shop shop);

    public int insertShop(Shop shop);

    public int updateShop(Shop shop);

    public int deleteShopById(Integer shopId);

    public int deleteShopByIds(String[] shopIds);
}