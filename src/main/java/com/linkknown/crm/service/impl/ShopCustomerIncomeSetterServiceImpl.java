package com.linkknown.crm.service.impl;

import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.dos.ShopCustomerIncomeSetter;
import com.linkknown.crm.bean.req.AddShopCustomerIncomeSetterReq;
import com.linkknown.crm.bean.req.DeleteShopCustomerIncomeSetterReq;
import com.linkknown.crm.bean.req.UpdateShopCustomerIncomeSetterReq;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.mapper.EmployeeMapper;
import com.linkknown.crm.mapper.ShopCustomerIncomeSetterMapper;
import com.linkknown.crm.service.IShopCustomerIncomeSetterService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Service
public class ShopCustomerIncomeSetterServiceImpl implements IShopCustomerIncomeSetterService {

    @Resource
    private ShopCustomerIncomeSetterMapper shopCustomerIncomeSetterMapper;

    @Resource
    private EmployeeMapper employeeMapper;


    /**
     * 查询店铺顾客收益设置
     * @param setterId 店铺顾客收益设置ID
     * @return 店铺顾客收益设置
     */
    @Override
    public ShopCustomerIncomeSetter selectShopCustomerIncomeSetterById(Integer setterId) {
        return shopCustomerIncomeSetterMapper.selectShopCustomerIncomeSetterById(setterId);
    }


    /**
     * 查询店铺顾客收益设置列表
     * @param shopCustomerIncomeSetter 店铺顾客收益设置
     * @return 店铺顾客收益设置
     */
    @Override
    public List<ShopCustomerIncomeSetter> selectShopCustomerIncomeSetterList(ShopCustomerIncomeSetter shopCustomerIncomeSetter) {
        return shopCustomerIncomeSetterMapper.selectShopCustomerIncomeSetterList(shopCustomerIncomeSetter);
    }


    /**
     * 新增店铺顾客收益设置
     * @param addShopCustomerIncomeSetterReq 店铺顾客收益设置
     */
    @Override
    public void addShopCustomerIncomeSetter(AddShopCustomerIncomeSetterReq addShopCustomerIncomeSetterReq) {
        //入参
        String loginUserPhoneNumber = addShopCustomerIncomeSetterReq.getLoginUserPhoneNumber();
        String password = addShopCustomerIncomeSetterReq.getPassword();

        //校验操作员密码
        Employee employee = employeeMapper.selectEmployeeByPhoneNumber(loginUserPhoneNumber);
        if (employee==null){
            throw new WebException(ResponseEnum.not_this_shop_employee_cannot_do_this);
        }
        if (!employee.getPassword().equals(password)){
            throw new WebException(ResponseEnum.password_is_wrong);
        }

        //一个门店只能有一种顾客收益方案
        ShopCustomerIncomeSetter setterParam = new ShopCustomerIncomeSetter();
        setterParam.setShopId(addShopCustomerIncomeSetterReq.getShopId());
        List<ShopCustomerIncomeSetter> dbSetterList = shopCustomerIncomeSetterMapper.selectShopCustomerIncomeSetterList(setterParam);
        if (!CollectionUtils.isEmpty(dbSetterList)){
            throw new WebException(ResponseEnum.shop_can_only_have_one_income_method);
        }

        //添加
        ShopCustomerIncomeSetter shopCustomerIncomeSetter = new ShopCustomerIncomeSetter();
        BeanUtils.copyProperties(addShopCustomerIncomeSetterReq,shopCustomerIncomeSetter);
        shopCustomerIncomeSetterMapper.insertShopCustomerIncomeSetter(shopCustomerIncomeSetter);
    }


    /**
     * 修改店铺顾客收益设置
     * @param updateShopCustomerIncomeSetterReq 店铺顾客收益设置
     */
    @Override
    public void updateShopCustomerIncomeSetter(UpdateShopCustomerIncomeSetterReq updateShopCustomerIncomeSetterReq) {
        //入参
        String loginUserPhoneNumber = updateShopCustomerIncomeSetterReq.getLoginUserPhoneNumber();
        String password = updateShopCustomerIncomeSetterReq.getPassword();

        //校验操作员密码
        Employee employee = employeeMapper.selectEmployeeByPhoneNumber(loginUserPhoneNumber);
        if (employee==null){
            throw new WebException(ResponseEnum.not_this_shop_employee_cannot_do_this);
        }
        if (!employee.getPassword().equals(password)){
            throw new WebException(ResponseEnum.password_is_wrong);
        }

        //更新
        ShopCustomerIncomeSetter shopCustomerIncomeSetter = new ShopCustomerIncomeSetter();
        BeanUtils.copyProperties(updateShopCustomerIncomeSetterReq,shopCustomerIncomeSetter);
        shopCustomerIncomeSetterMapper.updateShopCustomerIncomeSetter(shopCustomerIncomeSetter);
    }


    /**
     * 删除店铺顾客收益设置
     * @param deleteShopCustomerIncomeSetterReq 店铺顾客收益设置
     */
    @Override
    public void deleteShopCustomerIncomeSetter(DeleteShopCustomerIncomeSetterReq deleteShopCustomerIncomeSetterReq) {
        //入参
        String loginUserPhoneNumber = deleteShopCustomerIncomeSetterReq.getLoginUserPhoneNumber();
        String password = deleteShopCustomerIncomeSetterReq.getPassword();

        //校验操作员密码
        Employee employee = employeeMapper.selectEmployeeByPhoneNumber(loginUserPhoneNumber);
        if (employee==null){
            throw new WebException(ResponseEnum.not_this_shop_employee_cannot_do_this);
        }
        if (!employee.getPassword().equals(password)){
            throw new WebException(ResponseEnum.password_is_wrong);
        }

        //删除
        shopCustomerIncomeSetterMapper.deleteById(deleteShopCustomerIncomeSetterReq.getSetterId());
    }



}
