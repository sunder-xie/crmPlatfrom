package com.kintiger.platform.batOrgUpdate.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.batOrgUpdate.dao.IBatOrgUpdateDao;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.kunnr.pojo.Kunnr;

public class BatOrgUpdateDaoImpl  extends BaseDaoImpl implements IBatOrgUpdateDao {

	
	
	public int getCustomerIsExit(String custNumber, String custName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custName", custName.trim());
		params.put("custNumber", custNumber.trim());
		return (Integer)getSqlMapClientTemplate().queryForObject("customer.getCustomerBy", params);
	}

	public int getKunnrIsExit(String custNumber, String custName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custName", custName.trim());
		params.put("custNumber", custNumber.trim());
		return (Integer)getSqlMapClientTemplate().queryForObject("customer.getCustomerBySapId", params);
	}
	public int updateCustomerOrgs(Customer customer) {
		return getSqlMapClientTemplate().update("batOrg.updateCustomerOrgs", customer);
	}
	public int updateKunnrOrgs(Kunnr kunnr) {
		return getSqlMapClientTemplate().update("batOrg.updateKunnrOrgs", kunnr);
	}

	

}
