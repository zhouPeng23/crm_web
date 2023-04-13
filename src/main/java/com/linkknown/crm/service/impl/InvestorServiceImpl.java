package com.linkknown.crm.service.impl;

import com.linkknown.crm.bean.dos.Investor;
import com.linkknown.crm.mapper.InvestorMapper;
import com.linkknown.crm.service.IInvestorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资方Service业务层处理
 * @author zhoupeng
 */
@Service
public class InvestorServiceImpl implements IInvestorService {

    @Resource
    private InvestorMapper investorMapper;

    /**
     * 查询资方
     * @param investorId 资方ID
     * @return 资方
     */
    @Override
    public Investor selectInvestorById(Long investorId) {
        return investorMapper.selectInvestorById(investorId);
    }

    /**
     * 查询资方列表
     * @param investor 资方
     * @return 资方
     */
    @Override
    public List<Investor> selectInvestorList(Investor investor) {
        return investorMapper.selectInvestorList(investor);
    }

    /**
     * 新增资方
     * @param investor 资方
     * @return 结果
     */
    @Override
    public int insertInvestor(Investor investor) {
        return investorMapper.insertInvestor(investor);
    }

    /**
     * 修改资方
     * @param investor 资方
     * @return 结果
     */
    @Override
    public int updateInvestor(Investor investor) {
        return investorMapper.updateInvestor(investor);
    }


}
