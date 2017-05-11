package com.kintiger.platform.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.customer.dao.IBatChangeDao;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.org.pojo.Borg;

public class BatChangeDaoImpl  extends BaseDaoImpl implements IBatChangeDao {

	
	
	@SuppressWarnings("unchecked")
	public List<AllUsers> getEmpListByOrgId(String orgId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("org_id", Long.parseLong(orgId.trim()));
//		Long org_id=Long.valueOf(orgId);
		return (List<AllUsers>) getSqlMapClientTemplate().queryForList(
				"customer.getEmpListByOrgId", params);
	}
	@SuppressWarnings("unchecked")
	public List<Customer> getCustomert(Customer customer) {
		return (List<Customer>) getSqlMapClientTemplate().queryForList(
				"customer.getCustomertlist",customer);
	}
	public int updateStationUserIdById(Customer customer) {
		return getSqlMapClientTemplate().update("customer.updateStationUserIdById", customer);
	}
	@SuppressWarnings("unchecked")
	public List<AllUsers> getEmpListByUserName(AllUsers  user) {
		return getSqlMapClientTemplate().queryForList("customer.getEmpListByUserName",user);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getOrgIds(String userId) {
		return getSqlMapClientTemplate().queryForList("customer.getOrgIds", userId);
	}

	@SuppressWarnings("unchecked")
	public List<Borg> getOrgsByOrgIds(String orgIds) {
		return getSqlMapClientTemplate().queryForList("customer.getOrgsByOrgIds", orgIds);
	}
	
}
