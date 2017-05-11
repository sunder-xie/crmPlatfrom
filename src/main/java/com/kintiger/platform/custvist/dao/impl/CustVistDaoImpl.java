package com.kintiger.platform.custvist.dao.impl;



import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientOperations;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.custvist.dao.ICustVistDao;
import com.kintiger.platform.custvist.pojo.BCustVwDetail;





public class CustVistDaoImpl extends BaseDaoImpl  implements
		ICustVistDao {

	
	
@SuppressWarnings("unchecked")
public List<BCustVwDetail> getCustVistList(BCustVwDetail bCustVwDetail) {
		
		return (List<BCustVwDetail>) getSqlMapClientTemplate().queryForList(
				"custVist.showCustVistList",bCustVwDetail);
	}
public int getCustVistListTotal(BCustVwDetail bCustVwDetail) {
	return  (Integer)getSqlMapClientTemplate().queryForObject(
			"custVist.showCustVistListTotal",bCustVwDetail);
}
public boolean updateCustVistOrderDao(final List<BCustVwDetail> bcdList) {
	for (int i = 0; i < bcdList.size(); i++) {
		BCustVwDetail bCustVwDetail = bcdList.get(i);
		int update = getSqlMapClientTemplate().update(
				"custVist.updatacustomerdetail",bCustVwDetail);
		if(update!=1)
			return false;
	}

	return  true;
}





}


