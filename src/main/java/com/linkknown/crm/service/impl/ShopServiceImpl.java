package com.linkknown.crm.service.impl;

import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.dos.Shop;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.mapper.EmployeeMapper;
import com.linkknown.crm.mapper.ShopMapper;
import com.linkknown.crm.service.IShopService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Service
public class ShopServiceImpl implements IShopService {

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private EmployeeMapper employeeMapper;


    /**
     * 查询店铺集合
     * @param shop 店铺
     * @return 店铺集合
     */
    @Override
    public List<Shop> queryShopList(Shop shop){
        return shopMapper.selectShopList(null);
    }


    /**
     * 添加店铺
     * @param shop 店铺
     */
    @Override
    public void addShop(Shop shop) {
        //设置创建人和时间
        shop.setCreateBy("SYSTEM");
        shop.setCreateTime(System.currentTimeMillis());

        //插入
        shopMapper.insertShop(shop);
    }


    /**
     * 删除店铺
     * @param shop 店铺
     */
    @Override
    public void deleteShop(Shop shop) {
        //查看店铺下是否还有员工
        Employee employee = new Employee();
        employee.setShopId(shop.getShopId());
        List<Employee> employeeList = employeeMapper.selectEmployeeList(employee);
        if (!CollectionUtils.isEmpty(employeeList)){
            throw new WebException(ResponseEnum.shop_has_employee_can_not_delete);
        }

        //删除
        shopMapper.deleteShopById(Long.valueOf(shop.getShopId()));
    }


    /**
     * 更新店铺
     * @param shop 店铺
     */
    @Override
    public void updateShop(Shop shop) {
        //设置更新人和时间
        shop.setUpdateBy("SYSTEM");
        shop.setUpdateTime(System.currentTimeMillis());
        shopMapper.updateShop(shop);
    }

}
