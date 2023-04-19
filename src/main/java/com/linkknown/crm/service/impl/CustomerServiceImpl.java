package com.linkknown.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linkknown.crm.bean.dos.Customer;
import com.linkknown.crm.bean.req.AddCustomerReq;
import com.linkknown.crm.bean.req.UpdateCustomerReq;
import com.linkknown.crm.common.enums.EnumsObject;
import com.linkknown.crm.bean.req.QueryCustomerPage;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.CustomerMassLevelEnum;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.mapper.CustomerMapper;
import com.linkknown.crm.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:21
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Resource
    private CustomerMapper customerMapper;


    /**
     * 查询顾客分页
     * @param queryCustomerPage 请求
     * @return 分页集合
     */
    @Override
    public Page<Customer> queryCustomerList(QueryCustomerPage queryCustomerPage) {
        //入参 - 门店id
        Integer shopId = queryCustomerPage.getShopId();
        //入参 - 查询条件
        String customerName = queryCustomerPage.getCustomerName().trim();
        String phoneNumber = queryCustomerPage.getPhoneNumber().trim();
        Integer sex = queryCustomerPage.getSex();
        String customerMassLevel = queryCustomerPage.getCustomerMassLevel();
        Integer belongToEmployeeId = queryCustomerPage.getBelongToEmployeeId();
        //入参 - 页码
        Integer pageNo = queryCustomerPage.getPageNo();
        Integer pageSize = queryCustomerPage.getPageSize();

        //分页查询
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("shop_id",shopId)
                .like(!StringUtils.isEmpty(customerName),"customer_name",customerName)
                .like(!StringUtils.isEmpty(phoneNumber),"phone_number",phoneNumber)
                .eq(!StringUtils.isEmpty(sex),"sex",sex)
                .eq(!StringUtils.isEmpty(customerMassLevel),"customer_mass_level",customerMassLevel)
                .eq(!StringUtils.isEmpty(belongToEmployeeId),"belong_to_employee_id",belongToEmployeeId)
                .orderByDesc("create_time");

        //返回
        return  customerMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);
    }


    /**
     * 添加顾客
     * @param addCustomerReq 添加顾客请求
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addCustomer(AddCustomerReq addCustomerReq) {
        //根据手机号,校验用户是否已存在
        Customer customerSearch = new Customer();
        customerSearch.setPhoneNumber(addCustomerReq.getPhoneNumber());
        List<Customer> customerSearchList = customerMapper.selectCustomerList(customerSearch);
        if (!CollectionUtils.isEmpty(customerSearchList)){
            throw new WebException(ResponseEnum.customer_phone_number_has_allready_exist);
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(addCustomerReq,customer);

        //如果有被介绍人，需要设置
        if ("1".equals(addCustomerReq.getHasIntroducedByCustomer())){
            //根据手机号查顾客获取customerId
            Customer introducedByCustomer = customerMapper.selectCustomerByPhoneNumber(addCustomerReq.getIntroducedByCustomerPhoneNumber());
            customer.setIntroducedByCustomerId(introducedByCustomer.getCustomerId());
            //这里小心一点，顺便校验手机号查询出来的用户名和界面传来的是否一致
            if (!introducedByCustomer.getCustomerName().equals(addCustomerReq.getIntroducedByCustomerName())){
                throw new WebException(ResponseEnum.introduced_by_customer_name_is_error);
            }
        }

        //设置创建人和时间
        customer.setCreateBy("SYSTEM");
        customer.setCreateTime(LocalDateTime.now());

        //插入
        customerMapper.insertCustomer(customer);
    }


    /**
     * 获取顾客等级
     * @return 等级集合
     */
    @Override
    public List<EnumsObject> getAllCustomerMassLevelList() {
        //源数据
        CustomerMassLevelEnum[] enums = CustomerMassLevelEnum.values();
        //返回集合
        List<EnumsObject> enumsObjectList = new ArrayList<>();
        //装配数据
        Arrays.stream(enums).forEach(customerMassLevelEnum -> {
            EnumsObject enumsObject = new EnumsObject();
            enumsObject.setCode(customerMassLevelEnum.getCode());
            enumsObject.setMsg(customerMassLevelEnum.getMsg());
            enumsObjectList.add(enumsObject);
        });
        //返回
        return enumsObjectList;
    }


    /**
     * 更新顾客
     * @param updateCustomerReq 更新顾客请求
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateCustomer(UpdateCustomerReq updateCustomerReq) {
        //校验用户是否修改了手机号
        Customer customerDb = customerMapper.selectCustomerById(updateCustomerReq.getCustomerId());
        if (!updateCustomerReq.getPhoneNumber().equals(customerDb.getPhoneNumber())){
            //修改了手机号
            Customer customerSearch = new Customer();
            customerSearch.setPhoneNumber(updateCustomerReq.getPhoneNumber());
            List<Customer> customerSearchList = customerMapper.selectCustomerList(customerSearch);
            if (!CollectionUtils.isEmpty(customerSearchList)){
                throw new WebException(ResponseEnum.customer_phone_number_has_allready_exist);
            }
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(updateCustomerReq,customer);

        //如果有被介绍人，需要设置
        if ("1".equals(updateCustomerReq.getHasIntroducedByCustomer())){
            //根据手机号查顾客获取customerId
            Customer introducedByCustomer = customerMapper.selectCustomerByPhoneNumber(updateCustomerReq.getIntroducedByCustomerPhoneNumber());
            customer.setIntroducedByCustomerId(introducedByCustomer.getCustomerId());
            //这里小心一点，顺便校验手机号查询出来的用户名和界面传来的是否一致
            if (!introducedByCustomer.getCustomerName().equals(updateCustomerReq.getIntroducedByCustomerName())){
                throw new WebException(ResponseEnum.introduced_by_customer_name_is_error);
            }
            //禁止双向绑定
            if (updateCustomerReq.getCustomerId().equals(introducedByCustomer.getIntroducedByCustomerId())){
                throw new WebException(ResponseEnum.prohibit_bidirectional_binding);
            }

        }else{
            //否则设置为空
            customer.setIntroducedByCustomerId(null);
        }

        //设置更新人和时间
        customer.setUpdateBy("SYSTEM");
        customer.setUpdateTime(LocalDateTime.now());

        //更新
        customerMapper.updateCustomer(customer);
    }


    /**
     * 删除顾客
     * @param customer 顾客
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteCustomer(Customer customer) {
        customerMapper.deleteCustomerById(customer.getCustomerId());
    }


    /**
     * 查询门店所有顾客
     * @param customer 顾客
     * @return 集合
     */
    @Override
    public List<Customer> queryShopAllCustomer(Customer customer) {
        return customerMapper.selectCustomerList(customer);
    }


    /**
     * 根据手机号查询顾客
     * @param customer 请求
     * @return 顾客
     */
    @Override
    public Customer queryCustomerByPhoneNumber(Customer customer) {
        Customer customerParam = new Customer();
        customerParam.setPhoneNumber(customer.getPhoneNumber());
        List<Customer> customerList = customerMapper.selectCustomerList(customerParam);
        if (CollectionUtils.isEmpty(customerList)){
            return null;
        }
        return customerList.get(0);

    }


}
