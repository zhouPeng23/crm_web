package com.linkknown.crm.service;

import com.linkknown.crm.bean.dos.Investor;

import java.util.List;

/**
 * 资方
 */
public interface IInvestorService {
    /**
     * 查询资方
     * @param investorId 资方ID
     * @return 资方
     */
    public Investor selectInvestorById(Long investorId);

    /**
     * 查询资方列表
     * @param investor 资方
     * @return 资方集合
     */
    public List<Investor> selectInvestorList(Investor investor);

    /**
     * 新增资方
     * @param investor 资方
     * @return 结果
     */
    public int insertInvestor(Investor investor);

    /**
     * 修改资方
     * @param investor 资方
     * @return 结果
     */
    public int updateInvestor(Investor investor);

}
