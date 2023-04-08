package com.linkknown.crm.common.util.paramutil;

import com.linkknown.crm.bean.dos.Shop;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.util.RegexUtils;
import org.springframework.util.StringUtils;

/**
 * @author zhoupeng
 * @date 2023/4/4 14:55
 */
public class ShopParamUtils {


    /**
     * 添加门店校验参数
     * @param shop shop
     */
    public static void addShop(Shop shop) {
        //门店名称不能为空
        if (StringUtils.isEmpty(shop.getShopName())){
            throw new WebException(ResponseEnum.shop_name_can_not_be_empty);
        }
        //门店位置不能为空
        if (StringUtils.isEmpty(shop.getShopWeizhi())){
            throw new WebException(ResponseEnum.shop_weizhi_can_not_be_enpty);
        }
        //门店经营类型不能为空
        if (StringUtils.isEmpty(shop.getShopJingyingType())){
            throw new WebException(ResponseEnum.shop_jingying_type_can_not_be_enpty);
        }
        //门店经营品牌不能为空
        if (StringUtils.isEmpty(shop.getShopJingyingBrand())){
            throw new WebException(ResponseEnum.shop_jingying_brand_can_not_be_enpty);
        }
        //门店开业日期不能为空
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
        if (!RegexUtils.validatePhoneNumber(shop.getShopLeaderPhoneNumber())){
            throw new WebException(ResponseEnum.shop_leader_phone_number_style_error);
        }
    }


    /**
     * 更新门店校验参数
     * @param shop shop
     */
    public static void updateShop(Shop shop) {
        //门店名称不能为空
        if (StringUtils.isEmpty(shop.getShopName())){
            throw new WebException(ResponseEnum.shop_name_can_not_be_empty);
        }
        //门店id不能为空
        if (StringUtils.isEmpty(shop.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
        //门店位置不能为空
        if (StringUtils.isEmpty(shop.getShopWeizhi())){
            throw new WebException(ResponseEnum.shop_weizhi_can_not_be_enpty);
        }
        //门店经营类型不能为空
        if (StringUtils.isEmpty(shop.getShopJingyingType())){
            throw new WebException(ResponseEnum.shop_jingying_type_can_not_be_enpty);
        }
        //门店经营品牌不能为空
        if (StringUtils.isEmpty(shop.getShopJingyingBrand())){
            throw new WebException(ResponseEnum.shop_jingying_brand_can_not_be_enpty);
        }
        //门店开业日期不能为空
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
        if (!RegexUtils.validatePhoneNumber(shop.getShopLeaderPhoneNumber())){
            throw new WebException(ResponseEnum.shop_leader_phone_number_style_error);
        }
    }


    /**
     * 删除门店校验参数
     * @param shop shop
     */
    public static void deleteShop(Shop shop) {
        //门店id不能为空
        if (StringUtils.isEmpty(shop.getShopId())){
            throw new WebException(ResponseEnum.shop_id_can_not_be_empty);
        }
    }



}
