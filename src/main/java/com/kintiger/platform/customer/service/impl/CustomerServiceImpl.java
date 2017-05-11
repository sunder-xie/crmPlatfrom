package com.kintiger.platform.customer.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.citydict.pojo.CityDict;
import com.kintiger.platform.customer.dao.ICustomerDao;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.customer.service.ICustomerService;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.sales.pojo.Zwlqy;

public class CustomerServiceImpl implements ICustomerService {

	private ICustomerDao customerDao;
	private TransactionTemplate transactionTemplate;
	private static final Logger logger = Logger
	.getLogger(CustomerServiceImpl.class);

	public List<CityDict> getCityList(CityDict cityDict) {
		try {
			return customerDao.getCityList(cityDict);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict) {
		try {
			return customerDao.getCmsTbDictList(cmsTbDict);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * 客户开户
	 */
	public BooleanResult customerOpen(final Customer customer) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult = (BooleanResult) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						BooleanResult booleanResult = new BooleanResult();
						Long n;
						booleanResult.setResult(true);
						try {
							booleanResult.setResult(false);
							n = customerDao.customerOpen(customer); // 写入客户表
							if (n > 0 && n != null) {
								booleanResult.setResult(true);
								booleanResult.setCode("终端门店" + "‘"
										+ customer.getCustName() + "’"
										+ "已经创建成功！");
							}
						} catch (Exception e) {
							logger.error(e);
							booleanResult.setResult(false);
							booleanResult.setCode("创建失败，请检查！");
							ts.setRollbackOnly();
							return booleanResult;
						}
						try {
							// 明细表写入（拜访次序，在此默认为1）
							String[] days = customer.getVisitDays().split(", ");
							for (int i = 0; i < days.length; i++) {
								customerDao.updateCustLine(n,
										customer.getStationUserId(),
										customer.getVisitFreq(),
										days[i]);
							}
						} catch (Exception e) {
							e.printStackTrace();
							booleanResult.setResult(false);
							booleanResult.setCode("创建失败，请检查！");
							ts.setRollbackOnly();
						}
						return booleanResult;
					}
				});
		return booleanResult;
	}

	public String getChannelName(int channelId) {
		try {
			return customerDao.getChannelName(channelId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public String getStationUserNameById(String stationUserId) {
		try {
			return customerDao.getStationUserNameById(stationUserId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public String getCustomerProvince(String string) {
		try {
			return customerDao.getCustomerProvince(string);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Long getCustomerNumberId() {
		try {
			return customerDao.getCustomerNumberId();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public int getCustomerListCount(Customer customer) {
		try {
			return customerDao.getCustomerListCount(customer);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public List<Customer> getCustomerList(Customer customer) {
		try {
			return customerDao.getCustomerList(customer);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ICustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public int closeCustomer(Customer cust) {
		try {
			return customerDao.closeCustomer(cust);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}

	}

	public Customer getCustomerObjectByCustId(Customer customer) {
		return customerDao.getCustomerObjectByCustId(customer);
	}

	public Kunnr getKunnrByKunnrId(Kunnr k) {
		return customerDao.getKunnrByKunnrId(k);
	}

	public BooleanResult updateCustomer(Customer customer) {
		BooleanResult booleanResult = new BooleanResult();
		int n = 0;
		try {
			booleanResult.setResult(false);
			n = customerDao.updateCustomer(customer); // 写入客户表
			if (n > 0) {
				booleanResult.setResult(true);
				booleanResult.setCode("终端门店" + "‘" + customer.getCustName()
						+ "’" + "修改成功！");
			}
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("修改失败，请检查！");
			return booleanResult;
		}
		return booleanResult;
	}

	/*
	 * public String getCustomerXZAreaStringByCust(String dict) { return
	 * customerDao.getCustomerXZAreaStringByCust(dict); }
	 */

	public Zwlqy getCustomerXZArea(Zwlqy zwlqy) {
		return customerDao.getCustomerXZArea(zwlqy);
	}

	public int getCustomerByName(Customer cust) {
		try {
			return customerDao.getCustomerByName(cust);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public  Borg gerOrgIsExit(Borg org) {
		try {
			return customerDao.gerOrgIsExit(org);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Customer> getTwoLevelCustomer(Customer cust) {
		try {
			return customerDao.getTwoLevelCustomer(cust);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public int getTwoLevelCustomerCount(Customer cust) {
		try {
			return customerDao.getTwoLevelCustomerCount(cust);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	
	public String getKunnrByEmpId(String userId){
		try {
			return customerDao.getKunnrByEmpId(userId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public int getCustKunnrByIdCount(AllUsers allUser) {
		try {
			return customerDao.getCustKunnrByIdCount(allUser);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public List<AllUsers> getCustKunnrById(AllUsers allUser) {
		try {
			return customerDao.getCustKunnrById(allUser);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public BooleanResult saveChagKunnrUser(List<Customer> customerList) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult.setResult(false);
		booleanResult.setCode("error");
		try {
			int n ;
			for (Customer customer : customerList) {
				int flag =customerDao.saveChagKunnrUser(customer);
				if (flag==1){
						booleanResult.setResult(true);
						booleanResult.setCode("修改信息成功！");
				}
			}
			
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(customerList), e);
			booleanResult.setResult(true);
			booleanResult.setCode("修改信息失败！");
		}
		return booleanResult;
	}

	@Override
	public int getCustomerByNumber(Customer cust) {
		try {
			return customerDao.getCustomerByNumber(cust);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public int updateImportCustomer(Customer cust) {
		try {
			return customerDao.updateImportCustomer(cust);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public Long getCustomerStationUserId(AllUsers user){
		try {
			return customerDao.getCustomerStationUserId(user);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public int updateCustomerStationUserId(Customer cust){
		try {
			return customerDao.updateCustomerStationUserId(cust);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public int updateCustomerImportance4CustNumber(Customer customer) {
		try {
			return customerDao.updateCustomerImportance4CustNumber(customer);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public int updateCustomerImportance4CustNumber1(Customer customer) {
		try {
			return customerDao.updateCustomerImportance4CustNumber1(customer);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

}
