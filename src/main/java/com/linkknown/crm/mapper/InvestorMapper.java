package com.linkknown.crm.mapper;

import com.linkknown.crm.bean.dos.Investor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 资方Mapper接口
 */
@Repository
public interface InvestorMapper{

    public Investor selectInvestorById(Long investorId);

    public List<Investor> selectInvestorList(Investor investor);

    public int insertInvestor(Investor investor);

    public int updateInvestor(Investor investor);

}
