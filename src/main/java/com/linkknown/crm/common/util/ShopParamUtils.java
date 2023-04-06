package com.linkknown.crm.common.util;

import com.linkknown.crm.bean.dos.Shop;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import org.springframework.util.StringUtils;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class ShopParamUtils {


    /**
     * 添加或者更新店铺校验参数
     * @param shop shop
     */
    public static void addOrUpdateShopValidate(Shop shop) {
        //店铺名称不能为空
        if (StringUtils.isEmpty(shop.getShopName())){
            throw new WebException(ResponseEnum.shop_name_can_not_be_empty);
        }
        //店铺位置不能为空
        if (StringUtils.isEmpty(shop.getShopWeizhi())){
            throw new WebException(ResponseEnum.shop_weizhi_can_not_be_enpty);
        }
        //店铺经营类型不能为空
        if (StringUtils.isEmpty(shop.getShopJingyingType())){
            throw new WebException(ResponseEnum.shop_jingying_type_can_not_be_enpty);
        }
        //店铺经营品牌不能为空
        if (StringUtils.isEmpty(shop.getShopJingyingBrand())){
            throw new WebException(ResponseEnum.shop_jingying_brand_can_not_be_enpty);
        }
        //店铺开业日期不能为空
        if (StringUtils.isEmpty(shop.getShopOpeningDate())){
            throw new WebException(ResponseEnum.shop_opening_date_can_not_be_enpty);
        }
        //店长名称不能为空
        if (StringUtils.isEmpty(shop.getShopLeaderName())){
            throw new WebException(ResponseEnum.shop_leader_name_can_not_be_enpty);
        }
        //店长性别不能为空
        if (StringUtils.isEmpty(shop.getShopLeaderSex())){
            throw new WebException(ResponseEnum.shop_leader_sex_can_not_be_enpty);
        }
        //店长手机号格式错误
        if (!RegexUtils.checkPhoneNumber(shop.getShopLeaderPhoneNumber())){
            throw new WebException(ResponseEnum.shop_leader_phone_number_style_error);
        }
    }



}
