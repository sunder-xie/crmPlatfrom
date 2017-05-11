package com.kintiger.platform.customer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.customer.dao.IBatChangeDao;
import com.kintiger.platform.customer.dao.ICustBatchModifyDao;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.customer.service.IBatChangeService;
import com.kintiger.platform.org.pojo.Borg;

public class BatChangeServiceImpl implements IBatChangeService {

	private IBatChangeDao batChangeDao;
	private ICustBatchModifyDao custBatchModifyDao;
	private static final Logger logger = Logger
	.getLogger(BatChangeServiceImpl.class);
	public List<AllUsers> getEmpListByOrgId(String orgId) {
		try {
			return batChangeDao.getEmpListByOrgId(orgId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public int updateByCustprentId(Customer customer) {
		try {
			return custBatchModifyDao.updateByCustprentId(customer);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	public List<Customer> getCustomert(Customer customer) {
		try {
			return batChangeDao.getCustomert(customer);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public int getCustomerBySapId(String custNumber, String custName) {
		try {
			return custBatchModifyDao.getCustomerBySapId(custNumber, custName);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	public int getCustomerBy(String custNumber, String custName) {
		try {
			return custBatchModifyDao.getCustomerBy(custNumber, custName);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	public int updateStationUserIdById(Customer customer) {
		try {
			return custBatchModifyDao.updateStationUserIdById(customer);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public int updateChannelIdById(Customer customer) {
		try {
			return custBatchModifyDao.updateChannelIdById(customer);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	public String getStationUserId(String userId) {
		try {
			return custBatchModifyDao.getStationUserId(userId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<AllUsers> getStationListByOrgId(AllUsers user) {
		try {
			return custBatchModifyDao.getStationListByOrgId(user);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public IBatChangeDao getBatChangeDao() {
		return batChangeDao;
	}

	public void setBatChangeDao(IBatChangeDao batChangeDao) {
		this.batChangeDao = batChangeDao;
	}
	public ICustBatchModifyDao getCustBatchModifyDao() {
		return custBatchModifyDao;
	}
	public void setCustBatchModifyDao(ICustBatchModifyDao custBatchModifyDao) {
		this.custBatchModifyDao = custBatchModifyDao;
	}
	
	public List<AllUsers> getEmpListByUserName(AllUsers user) {
		try {
			return batChangeDao.getEmpListByUserName(user);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}
	public List<String> getOrgIds(String userId) {
		try {
			return batChangeDao.getOrgIds(userId);
		}catch(Exception e){
			logger.error(e);
		}
		return null;
	}

	public List<Borg> getOrgsByOrgIds(List<String> allOrg) {
		StringBuilder orgIds = new StringBuilder();
		try {
			if (allOrg != null) {
				for (String orgId : allOrg) {
					if (orgIds.length() > 0) {
						orgIds.append(",");
					}
					orgIds.append(orgId);
				}
				return batChangeDao.getOrgsByOrgIds(orgIds.toString());
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
}
