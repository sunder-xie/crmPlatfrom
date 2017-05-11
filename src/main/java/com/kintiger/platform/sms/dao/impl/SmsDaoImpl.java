package com.kintiger.platform.sms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.sms.dao.ISmsDao;
import com.kintiger.platform.sms.pojo.PaymentNoticeInfo;
import com.kintiger.platform.sms.pojo.Sms;
import com.kintiger.platform.sms.pojo.Station;

public class SmsDaoImpl extends BaseDaoImpl implements ISmsDao {

	@Override
	public int getEmpInfoCount(Sms sms) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sms.getEmpInfoCount",sms);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sms> getEmpInfoList(Sms sms) {
		return (List<Sms>)this.getSqlMapClientTemplate().queryForList(
				"sms.getEmpInfoList",sms);
	}

	@Override
	public int getStationJsonListCountForSelect(Station station) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				("sms.getStationJsonListCountForSelect"), station);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Station> getStationJsonListForSelect(Station station) {
		return getSqlMapClientTemplate().queryForList(
				"sms.getStationJsonListForSelect", station);
	}

	@Override
	public int getPaymentNoticeInfoCount(PaymentNoticeInfo paymentNoticeInfo) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sms.getPaymentNoticeInfoCount",paymentNoticeInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentNoticeInfo> getPaymentNoticeInfoList(PaymentNoticeInfo paymentNoticeInfo) {
		return (List<PaymentNoticeInfo>)this.getSqlMapClientTemplate().queryForList(
				"sms.getPaymentNoticeInfoList",paymentNoticeInfo);
	}

	@Override
	public Integer updatePaymentSendDate(PaymentNoticeInfo paymentNoticeInfo) {
		return (Integer) getSqlMapClientTemplate().update(
		"sms.updatePaymentSendDate", paymentNoticeInfo);
	}

	@Override
	public PaymentNoticeInfo getMaxBelnrByPayment(PaymentNoticeInfo paymentNoticeInfo) {
		return (PaymentNoticeInfo)this.getSqlMapClientTemplate().queryForObject(
				"sms.getMaxBelnrByPayment",paymentNoticeInfo);
	}

	@Override
	public void insertPayment(final List<PaymentNoticeInfo> paymentNoticeInfoList2) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (PaymentNoticeInfo paymentNoticeInfo : paymentNoticeInfoList2) {
					System.out.println(paymentNoticeInfo.getKunnr()+","+paymentNoticeInfo.getBank_etydat()+","+
							paymentNoticeInfo.getBank_belnr());
					executor.insert("sms.insertPayment", paymentNoticeInfo);
				}
				executor.executeBatch();
				return null;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<KunnrBusiness> getKunnrBusinessByKunnrId(String kunnr){
		return (List<KunnrBusiness>)this.getSqlMapClientTemplate().queryForList(
				"sms.getKunnrBusinessByKunnrId",kunnr);
	}

	@Override
	public Integer updateEmpMobile(Sms sms) {
		return (Integer) getSqlMapClientTemplate().update(
		"sms.updateEmpMobile", sms);
	}

	@Override
	public Integer updateKunnrMobile1(Sms sms) {
		return (Integer) getSqlMapClientTemplate().update(
		"sms.updateKunnrMobile1", sms);
	}

	@Override
	public Integer updateKunnrMobile2(Sms sms) {
		return (Integer) getSqlMapClientTemplate().update(
		"sms.updateKunnrMobile2", sms);
	}

	@Override
	public Integer getCountInKunnrEmpInfo(Sms sms) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sms.getCountInKunnrEmpInfo",sms);
	}

	@Override
	public void saveGroup(final List<Sms> groupList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Sms group : groupList) {
					executor.insert("sms.saveGroup", group);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sms> getGroups(Sms group) {
		return getSqlMapClientTemplate().queryForList(
				"sms.getGroups", group);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sms> getSelectedGroupInfo(Sms group) {
		return getSqlMapClientTemplate().queryForList(
				"sms.getSelectedGroupInfo", group);
	}

	@Override
	public Integer checkGroupName(Sms group) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sms.checkGroupName",group);
	}

	@Override
	public Long deleteGroup(Sms group) {
		return (long) getSqlMapClientTemplate().delete(
				"sms.deleteGroup", group);
	}

}
