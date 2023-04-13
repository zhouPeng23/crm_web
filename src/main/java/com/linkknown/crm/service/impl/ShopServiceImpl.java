package com.linkknown.crm.service.impl;

import com.linkknown.crm.bean.dos.Employee;
import com.linkknown.crm.bean.dos.Project;
import com.linkknown.crm.bean.dos.Shop;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.mapper.EmployeeMapper;
import com.linkknown.crm.mapper.ProjectMapper;
import com.linkknown.crm.mapper.ShopMapper;
import com.linkknown.crm.service.IShopService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    private ProjectMapper projectMapper;


    /**
     * 查询门店集合
     * @param shopIds id集合
     * @return 门店集合
     */
    @Override
    public List<Shop> queryShopList(String shopIds){
        String[] ids = shopIds.split(",");
        return shopMapper.selectShopByIds(ids);
    }


    /**
     * 添加门店
     * @param shop 门店
     */
    @Override
    public void addShop(Shop shop) {
        //设置创建人和时间
        shop.setCreateBy("SYSTEM");
        shop.setCreateTime(LocalDateTime.now());

        //插入
        shopMapper.insertShop(shop);
    }


    /**
     * 删除门店
     * @param shop 门店
     */
    @Override
    public void deleteShop(Shop shop) {
        //查看门店下是否还有员工
        Employee employee = new Employee();
        employee.setShopId(shop.getShopId());
        List<Employee> employeeList = employeeMapper.selectEmployeeList(employee);
        if (!CollectionUtils.isEmpty(employeeList)){
            throw new WebException(ResponseEnum.shop_has_employee_can_not_delete);
        }

        //查看门店下是否有项目
        Project project = new Project();
        project.setShopId(shop.getShopId());
        List<Project> projectList = projectMapper.selectProjectList(project);
        if (!CollectionUtils.isEmpty(projectList)){
            throw new WebException(ResponseEnum.shop_has_project_can_not_delete);
        }

        //删除
        shopMapper.deleteShopById(shop.getShopId());
    }


    /**
     * 更新门店
     * @param shop 门店
     */
    @Override
    public void updateShop(Shop shop) {
        //设置更新人和时间
        shop.setUpdateBy("SYSTEM");
        shop.setUpdateTime(LocalDateTime.now());
        shopMapper.updateShop(shop);
    }

}
