package com.linkknown.crm.api;

import com.linkknown.crm.bean.dos.ShopCustomerIncomeSetter;
import com.linkknown.crm.bean.req.AddShopCustomerIncomeSetterReq;
import com.linkknown.crm.bean.req.DeleteShopCustomerIncomeSetterReq;
import com.linkknown.crm.bean.req.UpdateShopCustomerIncomeSetterReq;
import com.linkknown.crm.common.aspect.exception.WebExceptionService;
import com.linkknown.crm.common.aspect.paramslog.WebParamsLog;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.response.BaseResponse;
import com.linkknown.crm.common.util.paramutil.ShopCustomerIncomeSetterUtils;
import com.linkknown.crm.service.IShopCustomerIncomeSetterService;
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
@RequestMapping("/crmWebApi/shopCustomerIncomeSetter")
@Validated
public class ShopCustomerIncomeSetterController {

    @Resource
    private IShopCustomerIncomeSetterService shopCustomerIncomeSetterService;

    @PostMapping(value = "/selectShopCustomerIncomeSetterList")
    @WebParamsLog(description = "查询门店里顾客收益设置集合")
    public BaseResponse<List<ShopCustomerIncomeSetter>> selectShopCustomerIncomeSetterList(ShopCustomerIncomeSetter shopCustomerIncomeSetter){
        ShopCustomerIncomeSetterUtils.selectShopCustomerIncomeSetterList(shopCustomerIncomeSetter);
        List<ShopCustomerIncomeSetter> shopCustomerIncomeSetterList = shopCustomerIncomeSetterService.selectShopCustomerIncomeSetterList(shopCustomerIncomeSetter);
        return BaseResponse.success(shopCustomerIncomeSetterList);
    }


    @PostMapping(value = "/addShopCustomerIncomeSetter")
    @WebParamsLog(description = "添加门店里顾客收益设置")
    public BaseResponse<List<ShopCustomerIncomeSetter>> addShopCustomerIncomeSetter(AddShopCustomerIncomeSetterReq addShopCustomerIncomeSetterReq){
        ShopCustomerIncomeSetterUtils.addShopCustomerIncomeSetter(addShopCustomerIncomeSetterReq);
        shopCustomerIncomeSetterService.addShopCustomerIncomeSetter(addShopCustomerIncomeSetterReq);
        return BaseResponse.success(ResponseEnum.add_success);
    }


    @PostMapping(value = "/updateShopCustomerIncomeSetter")
    @WebParamsLog(description = "更新门店里顾客收益设置")
    public BaseResponse<List<ShopCustomerIncomeSetter>> updateShopCustomerIncomeSetter(UpdateShopCustomerIncomeSetterReq updateShopCustomerIncomeSetterReq){
        ShopCustomerIncomeSetterUtils.updateShopCustomerIncomeSetter(updateShopCustomerIncomeSetterReq);
        shopCustomerIncomeSetterService.updateShopCustomerIncomeSetter(updateShopCustomerIncomeSetterReq);
        return BaseResponse.success(ResponseEnum.update_success);
    }


    @PostMapping(value = "/deleteShopCustomerIncomeSetter")
    @WebParamsLog(description = "删除门店里顾客收益设置")
    public BaseResponse<List<ShopCustomerIncomeSetter>> deleteShopCustomerIncomeSetter(DeleteShopCustomerIncomeSetterReq deleteShopCustomerIncomeSetterReq){
        ShopCustomerIncomeSetterUtils.deleteShopCustomerIncomeSetter(deleteShopCustomerIncomeSetterReq);
        shopCustomerIncomeSetterService.deleteShopCustomerIncomeSetter(deleteShopCustomerIncomeSetterReq);
        return BaseResponse.success(ResponseEnum.delete_success);
    }


}
