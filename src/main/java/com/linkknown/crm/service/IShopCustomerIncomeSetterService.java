package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.ShopCustomerIncomeSetter;
import com.linkknown.crm.bean.req.AddShopCustomerIncomeSetterReq;
import com.linkknown.crm.bean.req.DeleteShopCustomerIncomeSetterReq;
import com.linkknown.crm.bean.req.UpdateShopCustomerIncomeSetterReq;

import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
public interface IShopCustomerIncomeSetterService {
    
    /**
     * 查询店铺顾客收益设置
     * @param setterId 店铺顾客收益设置ID
     * @return 店铺顾客收益设置
     */
    public ShopCustomerIncomeSetter selectShopCustomerIncomeSetterById(Integer setterId);

    
    /**
     * 查询店铺顾客收益设置列表
     * @param shopCustomerIncomeSetter 店铺顾客收益设置
     * @return 店铺顾客收益设置集合
     */
    public List<ShopCustomerIncomeSetter> selectShopCustomerIncomeSetterList(ShopCustomerIncomeSetter shopCustomerIncomeSetter);

    
    /**
     * 新增店铺顾客收益设置
     * @param addShopCustomerIncomeSetterReq 店铺顾客收益设置
     */
    public void addShopCustomerIncomeSetter(AddShopCustomerIncomeSetterReq addShopCustomerIncomeSetterReq);


    /**
     * 修改店铺顾客收益设置
     * @param updateShopCustomerIncomeSetterReq 店铺顾客收益设置
     */
    public void updateShopCustomerIncomeSetter(UpdateShopCustomerIncomeSetterReq updateShopCustomerIncomeSetterReq);


    /**
     * 删除店铺顾客收益设置
     * @param deleteShopCustomerIncomeSetterReq 店铺顾客收益设置
     */
    public void deleteShopCustomerIncomeSetter(DeleteShopCustomerIncomeSetterReq deleteShopCustomerIncomeSetterReq);


}
