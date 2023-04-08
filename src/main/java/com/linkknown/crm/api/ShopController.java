package com.linkknown.crm.api;

import com.linkknown.crm.bean.dos.Shop;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.paramutil.ShopParamUtils;
import com.linkknown.crm.service.IShopService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoupeng
 * @date 2022/6/10 15:42
 */
@WebExceptionService
@RestController
@RequestMapping("/crmWebApi/shop")
@Validated
public class ShopController {

    @Resource
    private IShopService shopService;

    @PostMapping(value = "/queryShopList")
    @WebParamsLog(description = "查询店铺集合")
    public BaseResponse<List<Shop>> queryShopList(Shop shop){
        List<Shop> shopList = shopService.queryShopList(shop);
        return BaseResponse.success(shopList);
    }


    @PostMapping(value = "/querySelectedShop")
    @WebParamsLog(description = "查询选中的店铺")
    public BaseResponse<Shop> querySelectedShop(){
        Shop shopDb = shopService.querySelectedShop();
        return BaseResponse.success(ResponseEnum.web_success,shopDb);
    }


    @PostMapping(value = "/selectThisShop")
    @WebParamsLog(description = "选择此店铺")
    public BaseResponse<Object> selectThisShop(Shop shop){
        Shop selectedShop = shopService.selectThisShop(shop);
        return BaseResponse.success(ResponseEnum.selected_success,selectedShop);
    }


    @PostMapping(value = "/addShop")
    @WebParamsLog(description = "添加店铺")
    public BaseResponse<Object> addShop(Shop shop){
        ShopParamUtils.addShop(shop);
        shopService.addShop(shop);
        return BaseResponse.success(ResponseEnum.add_success);
    }


    @PostMapping(value = "/deleteShop")
    @WebParamsLog(description = "删除店铺")
    public BaseResponse<Object> deleteShop(Shop shop){
        ShopParamUtils.deleteShop(shop);
        shopService.deleteShop(shop);
        return BaseResponse.success(ResponseEnum.delete_success);
    }


    @PostMapping(value = "/updateShop")
    @WebParamsLog(description = "更新店铺")
    public BaseResponse<Object> updateShop(Shop shop){
        ShopParamUtils.updateShop(shop);
        shopService.updateShop(shop);
        return BaseResponse.success(ResponseEnum.update_success);
    }
    

}
