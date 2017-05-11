package com.kintiger.platform.sms.dao;

import java.util.List;

import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.sms.pojo.PaymentNoticeInfo;
import com.kintiger.platform.sms.pojo.Sms;
import com.kintiger.platform.sms.pojo.Station;


public interface ISmsDao {

	int getEmpInfoCount(Sms sms);

	List<Sms> getEmpInfoList(Sms sms);

	int getStationJsonListCountForSelect(Station station);

	List<Station> getStationJsonListForSelect(Station station);

	int getPaymentNoticeInfoCount(PaymentNoticeInfo paymentNoticeInfo);

	List<PaymentNoticeInfo> getPaymentNoticeInfoList(PaymentNoticeInfo paymentNoticeInfo);

	Integer updatePaymentSendDate(PaymentNoticeInfo paymentNoticeInfo);

	PaymentNoticeInfo getMaxBelnrByPayment(PaymentNoticeInfo paymentNoticeInfo);

	void insertPayment(List<PaymentNoticeInfo> paymentNoticeInfoList2);
	
	List<KunnrBusiness> getKunnrBusinessByKunnrId(String kunnr);

	Integer updateEmpMobile(Sms sms);

	Integer updateKunnrMobile1(Sms sms);

	Integer updateKunnrMobile2(Sms sms);

	Integer getCountInKunnrEmpInfo(Sms sms);

	void saveGroup(List<Sms> groupList);

	List<Sms> getGroups(Sms group);

	List<Sms> getSelectedGroupInfo(Sms group);

	Integer checkGroupName(Sms group);

	Long deleteGroup(Sms group);

}
