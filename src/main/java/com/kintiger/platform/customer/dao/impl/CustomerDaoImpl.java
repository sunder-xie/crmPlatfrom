package com.kintiger.platform.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.citydict.pojo.CityDict;
import com.kintiger.platform.customer.dao.ICustomerDao;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.sales.pojo.Zwlqy;

public class CustomerDaoImpl  extends BaseDaoImpl implements ICustomerDao {

	@SuppressWarnings("unchecked")
	public List<CityDict> getCityList(CityDict cityDict) {
		return getSqlMapClientTemplate().queryForList("customer.getCityList",
				cityDict);
	}

	@SuppressWarnings("unchecked")
	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict) {
		return getSqlMapClientTemplate().queryForList("customer.getCmsTbDictList",
				cmsTbDict);
	}

	public Long customerOpen(Customer customer) {
		return (Long) getSqlMapClientTemplate().insert("customer.customerOpen",customer);
	}

	public String getChannelName(int channelId) {
		return (String) getSqlMapClientTemplate().queryForObject("customer.getChannelName",
				channelId);
	}

	public String getStationUserNameById(String stationUserId) {
		return (String) getSqlMapClientTemplate().queryForObject("customer.getStationUserNameById",
				stationUserId);
	}

	public String getCustomerProvince(String string) {
		return (String) getSqlMapClientTemplate().queryForObject("customer.getCustomerProvince",
				string);
	}

	public Long getCustomerNumberId() {
		 return (Long) getSqlMapClientTemplate().queryForObject("customer.getCustomerNumberId");
	}

	public String updateCustLine(Long n, String stationUserId,
			Integer visitFreq, String visitDays) {
		Map<String, String> map = new HashMap<String, String>();
			map.put("custId", n.toString());
			map.put("posId", stationUserId);
			map.put("visitFreq", visitFreq.toString());
			map.put("visitDays", visitDays);
			map.put("visitOrder", "1");
		return (String) getSqlMapClientTemplate().insert("customer.updateCustLine",
				map);
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getCustomerList(Customer customer) {
		return  getSqlMapClientTemplate().queryForList("customer.getCustomerList",
				customer);
	}

	public int getCustomerListCount(Customer customer) {
		return (Integer) getSqlMapClientTemplate().queryForObject("customer.getCustomerListCount",
				customer);
	}

	public int closeCustomer(Customer cust) {
		return (Integer) getSqlMapClientTemplate().update("customer.closeCustomer", cust);
	}

	public Customer getCustomerObjectByCustId(Customer customer) {
		return (Customer) getSqlMapClientTemplate().queryForObject("customer.getCustomerObjectByCustId", customer);
	}

	public Kunnr getKunnrByKunnrId(Kunnr k) {
		return (Kunnr) getSqlMapClientTemplate().queryForObject("customer.getKunnrByKunnrId",k);
	}

	public int updateCustomer(Customer cust) {
		return (Integer) getSqlMapClientTemplate().update("customer.updateCustomer",cust);
	}


/*	public String getCustomerXZAreaStringByCust(String dict) {
		return (String) getSqlMapClientTemplate().queryForObject("customer.getCustomerXZAreaStringByCust", dict);
	}*/

	public Zwlqy getCustomerXZArea(Zwlqy zwlqy) {
		return (Zwlqy) getSqlMapClientTemplate().queryForObject("customer.getCustomerXZArea", zwlqy);
	}

	public int getCustomerByName(Customer cust) {
		return (Integer) getSqlMapClientTemplate().queryForObject("customer.getCustomerByName",cust);
	}

	public Borg gerOrgIsExit(Borg org) {
		return (Borg) getSqlMapClientTemplate().queryForObject("customer.gerOrgCountIsExit",org);
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getTwoLevelCustomer(Customer cust) {
		return (List<Customer>) getSqlMapClientTemplate().queryForList("customer.getTwoLevelCustomer",cust);
	}

	public int getTwoLevelCustomerCount(Customer cust) {
		return (Integer) getSqlMapClientTemplate().queryForObject("customer.getTwoLevelCustomerCount",cust);
	}
	
	public String getKunnrByEmpId(String userId) {
		return (String) getSqlMapClientTemplate().queryForObject("customer.getKunnrByEmpId",Long.valueOf(userId));
	}

	@Override
	public int getCustKunnrByIdCount(AllUsers allUser) {
		return (Integer) getSqlMapClientTemplate().queryForObject("customer.getCustKunnrByIdCount",allUser);
	}

	@Override
	public List<AllUsers> getCustKunnrById(AllUsers allUser) {
		return (List<AllUsers>) getSqlMapClientTemplate().queryForList("customer.getCustKunnrById",allUser);
	}

	@Override
	public int saveChagKunnrUser(Customer customer) {
		return (Integer) getSqlMapClientTemplate().update("customer.saveChagKunnrUser",customer);
	}

	@Override
	public int getCustomerByNumber(Customer cust) {
		return (Integer) getSqlMapClientTemplate().queryForObject("customer.getCustomerByNumber",cust);
	}

	@Override
	public int updateImportCustomer(Customer cust) {
		return (Integer) getSqlMapClientTemplate().update("customer.updateImportCustomer",cust);
	}
	
	public Long getCustomerStationUserId(AllUsers user){
		return (Long) getSqlMapClientTemplate().queryForObject("customer.getCustomerStationUserId",user);
	}
	
	public int updateCustomerStationUserId(Customer cust){
		return (Integer) getSqlMapClientTemplate().update("customer.updateCustomerStationUserId",cust);
	}

	@Override
	public int updateCustomerImportance4CustNumber(Customer customer) {
		return (Integer) getSqlMapClientTemplate().update("customer.updateCustomerImportance4CustNumber",customer);
		
	}

	@Override
	public int updateCustomerImportance4CustNumber1(Customer customer) {
		return (Integer) getSqlMapClientTemplate().update("customer.updateCustomerImportance4CustNumber1",customer);
	}


}
