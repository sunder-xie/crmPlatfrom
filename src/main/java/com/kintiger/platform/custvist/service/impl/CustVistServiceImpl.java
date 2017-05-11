package com.kintiger.platform.custvist.service.impl;



import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.custvist.dao.ICustVistDao;
import com.kintiger.platform.custvist.pojo.BCustVwDetail;
import com.kintiger.platform.custvist.service.ICustVistService;

public class CustVistServiceImpl implements ICustVistService {

	private static final Logger logger = Logger
			.getLogger(CustVistServiceImpl.class);

	private ICustVistDao iCustVistDao;
	/**
	 * 得到客户拜访次序显示页面的信息
	 * 
	 */
	public List<BCustVwDetail> getCustVistList(BCustVwDetail bCustVwDetail) {
		try {
			return iCustVistDao.getCustVistList(bCustVwDetail);
		} catch (RuntimeException e) {
			logger.error(e);
			return null;
		}

	}
	/**
	 * 得到客户拜访次序显示页面的Size
	 * 
	 */
	public int getCustVistListTotal(BCustVwDetail bCustVwDetail) {
		try {
			return iCustVistDao.getCustVistListTotal(bCustVwDetail);
		} catch (RuntimeException e) {
			logger.error(e);
			return 0;
		}
	}

	/**
	 *批量更新客户拜访次序
	 * 
	 */
	public boolean updateCustVistOrder(List<BCustVwDetail> bcdList) {
		try {
			iCustVistDao.updateCustVistOrderDao(bcdList);
			return true;
		} catch (RuntimeException e) {
			logger.error(e);
			return false;
		}
	}

	public ICustVistDao getiCustVistDao() {
		return iCustVistDao;
	}

	public void setiCustVistDao(ICustVistDao iCustVistDao) {
		this.iCustVistDao = iCustVistDao;
	}


}
