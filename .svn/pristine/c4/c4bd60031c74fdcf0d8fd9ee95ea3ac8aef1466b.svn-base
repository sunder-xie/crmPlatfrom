package com.kintiger.platform.batOrgUpdate.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.batOrgUpdate.dao.IBatOrgUpdateDao;
import com.kintiger.platform.batOrgUpdate.service.IBatOrgUpdateService;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.kunnr.pojo.Kunnr;

public class BatOrgUpdateServiceImpl implements IBatOrgUpdateService {

	private TransactionTemplate transactionTemplate;
	private static final Logger logger = Logger
			.getLogger(BatOrgUpdateServiceImpl.class);
	private IBatOrgUpdateDao batOrgUpdateDao;

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IBatOrgUpdateDao getBatOrgUpdateDao() {
		return batOrgUpdateDao;
	}

	public void setBatOrgUpdateDao(IBatOrgUpdateDao batOrgUpdateDao) {
		this.batOrgUpdateDao = batOrgUpdateDao;
	}

	public int getCustomerIsExit(String custNumber, String custName) {
		try {
			return batOrgUpdateDao.getCustomerIsExit(custNumber, custName);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public int getKunnrIsExit(String custNumber, String custName) {
		try {
			return batOrgUpdateDao.getKunnrIsExit(custNumber, custName);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	/**
	 * 调整经销商组织
	 */
	public BooleanResult updateKunnrOrgs(final List<Kunnr> kunnr) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult = (BooleanResult) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						BooleanResult booleanResult = new BooleanResult();
						String res = "";
						booleanResult.setResult(true);
						try {
							for (int i = 0; i < kunnr.size(); i++) {
								Kunnr k = kunnr.get(i);
								booleanResult.setResult(false);
								int n=0;
								n = batOrgUpdateDao.updateKunnrOrgs(k);
								if (n == 0) {
									res = "写入失败!";
								}
							}

							if ("".equals(res)) {
								booleanResult.setResult(true);
								booleanResult.setCode("批量调整成功！");
							} else {
								booleanResult.setResult(false);
								booleanResult.setCode("批量调整失败！");
								ts.setRollbackOnly();
							}
						} catch (Exception e) {
							booleanResult.setResult(false);
							booleanResult.setCode("批量调整失败，请检查！");
							ts.setRollbackOnly();
							return booleanResult;
						}
						return booleanResult;
					}
				});
		return booleanResult;
	}

	/**
	 * 客户组织调整
	 */
	public BooleanResult updateCustomerOrgs(final List<Customer> cust) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult = (BooleanResult) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						BooleanResult booleanResult = new BooleanResult();
						String res = "";
						booleanResult.setResult(true);
						try {
							for (int i = 0; i < cust.size(); i++) {
								Customer c = cust.get(i);
								booleanResult.setResult(false);
								int n=0;
								n = batOrgUpdateDao.updateCustomerOrgs(c);
								if (n == 0) {
									res = "写入失败!";
								}
							}

							if ("".equals(res)) {
								booleanResult.setResult(true);
								booleanResult.setCode("批量调整成功！");
							} else {
								booleanResult.setResult(false);
								booleanResult.setCode("批量调整失败！");
								ts.setRollbackOnly();
							}
						} catch (Exception e) {
							booleanResult.setResult(false);
							booleanResult.setCode("批量调整失败，请检查！");
							ts.setRollbackOnly();
							return booleanResult;
						}
						return booleanResult;
					}

				});
		return booleanResult;
	}
}
