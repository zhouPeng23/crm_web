package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.Shop;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IShopService {

    /**
     * 查询门店集合
     * @param shop 门店
     * @return 门店集合
     */
    List<Shop> queryShopList(Shop shop);


    /**
     * 添加门店
     * @param shop 门店
     */
    void addShop(Shop shop);


    /**
     * 删除门店
     * @param shop 门店
     */
    void deleteShop(Shop shop);


    /**
     * 更新门店
     * @param shop 门店
     */
    void updateShop(Shop shop);

}
