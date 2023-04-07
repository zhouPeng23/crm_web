package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.Shop;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IShopService {

    /**
     * 查询店铺集合
     * @param shop 店铺
     * @return 店铺集合
     */
    List<Shop> queryShopList(Shop shop);


    /**
     * 查询选中的店铺
     * @return 选中的店铺
     */
    Shop querySelectedShop();


    /**
     * 选择这个店铺
     * @param shop 店铺
     * @return 店铺
     */
    Shop selectThisShop(Shop shop);


    /**
     * 添加店铺
     * @param shop 店铺
     */
    void addShop(Shop shop);


    /**
     * 删除店铺
     * @param shop 店铺
     */
    void deleteShop(Shop shop);


    /**
     * 更新店铺
     * @param shop 店铺
     */
    void updateShop(Shop shop);

}
