package com.kintiger.platform.kunnr.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.kunnr.dao.IKunnrAddressDao;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrAcount;
import com.kintiger.platform.kunnr.pojo.KunnrAddress;
import com.kintiger.platform.kunnr.pojo.KunnrBrand;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;

public class KunnrAddressDaoImpl extends BaseDaoImpl implements
		IKunnrAddressDao {


	public KunnrAddress getKunnrAddressById(String kunnrSd) {
		KunnrAddress kunnrAddress = new KunnrAddress();
		kunnrAddress.setKunnrSd(kunnrSd);
		return (KunnrAddress) getSqlMapClientTemplate().queryForObject(
				"kunnr.getKunnrAddressById", kunnrAddress);
	}

	public int kunnrAddressSearchCount(KunnrAddress kunnrAddress) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"kunnr.kunnrAddressSearchCount", kunnrAddress);
	}

	@SuppressWarnings("unchecked")
	public List<KunnrAddress> kunnrAddressSearch(KunnrAddress kunnrAddress) {

		return (List<KunnrAddress>) getSqlMapClientTemplate().queryForList(
				"kunnr.kunnrAddressSearch", kunnrAddress);
	}

	public int updateKunnrAddress(KunnrAddress kunnrAddress) {
		return getSqlMapClientTemplate().update("kunnr.updateKunnrAddress1",
				kunnrAddress);
	}

	public Long deleteKunnrAddress(KunnrAddress kunnrAddress) {
		return Long.valueOf((Integer) getSqlMapClientTemplate().update(
				"kunnr.deleteKunnrAddress", kunnrAddress));
	}

	public Long createKunnrAddress(KunnrAddress kunnrAddress) {
		return (Long) getSqlMapClientTemplate().insert(
				"kunnr.createKunnrAddress", kunnrAddress);
	}

	public String getMaxKunnrSd(String kunnrId) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"kunnr.getMaxKunnrSd", kunnrId);
	}

}
