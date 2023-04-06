package com.linkknown.crm.service.impl;

import com.linkknown.crm.bean.dos.Shop;
import com.linkknown.crm.common.aspect.exception.WebException;
import com.linkknown.crm.common.enums.ResponseEnum;
import com.linkknown.crm.common.enums.SelectedEnum;
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

    @Override
    public List<Shop> queryShopList(Shop shop){
        return shopMapper.selectShopList(null);
    }


    @Override
    public Shop querySelectedShop(){
        Shop selectedShop = null;
        List<Shop> shopList = shopMapper.selectShopList(null).stream()
                .filter(s -> s.getSelected().equals(SelectedEnum.yes.getCode()))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(shopList)){
            if (shopList.size()==1){
                selectedShop = shopList.get(0);
            }else{
                throw new WebException(ResponseEnum.selected_shop_number_in_db_error);
            }
        }
        //返回已选择的shop，如果没有选择的返回null
        return selectedShop;
    }


    @Override
    public Shop selectThisShop(Shop shop) {
        //传过来的shopId参数
        Integer shopIdParam = shop.getShopId();

        //查询现有的已选择的店铺 设置为未选择
        Shop selectedShop = this.querySelectedShop();
        if (selectedShop!=null){
            //设置为未选择状态
            Shop toNotChooseShop = new Shop();
            toNotChooseShop.setShopId(selectedShop.getShopId());
            toNotChooseShop.setSelected(SelectedEnum.no.getCode());
            shopMapper.updateShop(toNotChooseShop);
        }

        //将选中的app设置为已选择
        Shop toChooseShop = new Shop();
        toChooseShop.setShopId(shopIdParam);
        toChooseShop.setSelected(SelectedEnum.yes.getCode());
        shopMapper.updateShop(toChooseShop);

        //返回库里已选择的店铺
        return this.querySelectedShop();
    }


    @Override
    public void addShop(Shop shop) {
        //默认设置未选择
        shop.setSelected(SelectedEnum.no.getCode());
        //设置创建人和时间
        shop.setCreateBy("SYSTEM");
        shop.setCreateTime(System.currentTimeMillis());

        //如果是插入的第一条数据，那么设置为“已选择”
        List<Shop> shopList = shopMapper.selectShopList(null);
        if (CollectionUtils.isEmpty(shopList)){
            shop.setSelected(SelectedEnum.yes.getCode());
        }

        shopMapper.insertShop(shop);
    }


    @Override
    public void deleteShop(Shop shop) {
        Shop shopDb = shopMapper.selectShopById(Long.valueOf(shop.getShopId()));
        if (SelectedEnum.yes.getCode().equals(shopDb.getSelected())){
            throw new WebException(ResponseEnum.shop_is_selected_can_not_delete);
        }
        shopMapper.deleteShopById(Long.valueOf(shop.getShopId()));
    }


    @Override
    public void updateShop(Shop shop) {
        //设置更新人和时间
        shop.setUpdateBy("SYSTEM");
        shop.setUpdateTime(System.currentTimeMillis());
        shopMapper.updateShop(shop);
    }

}
