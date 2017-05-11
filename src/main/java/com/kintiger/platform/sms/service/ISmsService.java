package com.kintiger.platform.sms.service;

import java.io.File;
import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.sms.pojo.PaymentNoticeInfo;
import com.kintiger.platform.sms.pojo.Sms;
import com.kintiger.platform.sms.pojo.Station;


public interface ISmsService {
	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String SUCCESS_MESSAGE = "同步成功!";

	public static final String ERROR_MESSAGE = "同步失败!";

	int getEmpInfoCount(Sms sms);

	List<Sms> getEmpInfoList(Sms sms);

	int getStationJsonListCountForSelect(Station station);

	List<Station> getStationJsonListForSelect(Station station);

	int getPaymentNoticeInfoCount(PaymentNoticeInfo paymentNoticeInfo);

	List<PaymentNoticeInfo> getPaymentNoticeInfoList(PaymentNoticeInfo paymentNoticeInfo);

	BooleanResult updatePaymentSendDate(List<PaymentNoticeInfo> paymentIdList);

	PaymentNoticeInfo getMaxBelnrByPayment(PaymentNoticeInfo paymentNoticeInfo);

	StringResult synchPayment(List<PaymentNoticeInfo> paymentNoticeInfoList);
	
	List<KunnrBusiness> getKunnrBusinessByKunnrId(String kunnr);

	StringResult importEmpMobile(String fileName, File fileContent);

	StringResult saveGroup(List<Sms> groupList);

	List<Sms> getGroups(Sms group);

	List<Sms> getSelectedGroupInfo(Sms group);

	StringResult checkGroupName(Sms group);

	StringResult deleteGroup(Sms group);

	StringResult modifyGroup(Sms group, List<Sms> groupList);
	

}
