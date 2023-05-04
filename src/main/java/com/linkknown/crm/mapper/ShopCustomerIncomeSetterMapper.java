package com.linkknown.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linkknown.crm.bean.dos.ShopCustomerIncomeSetter;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author zhoupeng
 */
@Repository
public interface ShopCustomerIncomeSetterMapper extends BaseMapper<ShopCustomerIncomeSetter> {

    public ShopCustomerIncomeSetter selectShopCustomerIncomeSetterById(Integer setterId);

    public List<ShopCustomerIncomeSetter> selectShopCustomerIncomeSetterList(ShopCustomerIncomeSetter shopCustomerIncomeSetter);

    public int insertShopCustomerIncomeSetter(ShopCustomerIncomeSetter shopCustomerIncomeSetter);

    public int updateShopCustomerIncomeSetter(ShopCustomerIncomeSetter shopCustomerIncomeSetter);

}
