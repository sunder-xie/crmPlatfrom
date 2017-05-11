package com.kintiger.platform.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.customer.dao.ICustBatchModifyDao;
import com.kintiger.platform.customer.pojo.Customer;

public class CustBatchModifyDaoImpl  extends BaseDaoImpl implements ICustBatchModifyDao {

	
	
	public int getCustomerBy(String custNumber, String custName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custName", custName.trim());
		params.put("custNumber", custNumber.trim());
		return (Integer)getSqlMapClientTemplate().queryForObject("customer.getCustomerBy", params);
	}

	public int updateByCustprentId(Customer customer) {
		return getSqlMapClientTemplate().update("customer.updateByCustprentId", customer);
	}

	public int getCustomerBySapId(String custNumber, String custName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("custName", custName.trim());
		params.put("custNumber", custNumber.trim());
		return (Integer)getSqlMapClientTemplate().queryForObject("customer.getCustomerBySapId", params);
	}
	public int updateStationUserIdById(Customer customer) {
		return getSqlMapClientTemplate().update("customer.updateStationUserIdById", customer);
	}
	public int updateChannelIdById(Customer customer) {
		return getSqlMapClientTemplate().update("customer.updateChannelIdById", customer);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> getStationListByOrgId(AllUsers user) {
		return getSqlMapClientTemplate().queryForList("customer.getStationListByOrgId", user);
	}

	public String getStationUserId(String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId.trim());
		return (String)getSqlMapClientTemplate().queryForObject("customer.getStationUserId", params);

	}

	

}
