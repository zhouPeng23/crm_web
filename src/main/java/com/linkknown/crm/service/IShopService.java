package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.Shop;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IShopService {

    List<Shop> queryShopList(Shop shop);

    Shop querySelectedShop();

    Shop selectThisShop(Shop shop);

    void addShop(Shop shop);

    void deleteShop(Shop shop);

    void updateShop(Shop shop);

}
