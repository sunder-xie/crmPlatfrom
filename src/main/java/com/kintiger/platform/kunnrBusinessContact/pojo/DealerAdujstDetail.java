package com.kintiger.platform.kunnrBusinessContact.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * Title: ������Ŀ�����ᱨ������ϸʵ����
 * Description: crmPlatform
 * @author lu
 * @date 2016��5��19�� ����1:59:33
 */
public class DealerAdujstDetail  extends SearchInfo{
	
	private static final long serialVersionUID = 6231926085645678304L;
	
	private String detalId;//������ϸId
	private String adjustId;//����id
	private String eventId;//����id
	private String orgId;//��֯id
	private String orgName;//��֯����
	private String kunnrId;
	private String kunnrName;//����������
	private String applyYear;
	private String applyMonth;
	private String matter;//Ʒ��
	private String matterName;//Ʒ������
	private String nowTarget;//��������Ŀ����
	private String nowDealerTarget;//���о�����Ŀ����
	private String adjustTarget;//����Ŀ����
	private String userId;//�ᱨ��id
	private String maktx01;//Ʒ����
	private String matnr01;//Ʒ�Ʊ���
	
	
	public String getMaktx01() {
		return maktx01;
	}
	public void setMaktx01(String maktx01) {
		this.maktx01 = maktx01;
	}
	public String getMatnr01() {
		return matnr01;
	}
	public void setMatnr01(String matnr01) {
		this.matnr01 = matnr01;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMatterName() {
		return matterName;
	}
	public void setMatterName(String matterName) {
		this.matterName = matterName;
	}
	public String getKunnrId() {
		return kunnrId;
	}
	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
	}
	public String getDetalId() {
		return detalId;
	}
	public void setDetalId(String detalId) {
		this.detalId = detalId;
	}
	public String getAdjustId() {
		return adjustId;
	}
	public void setAdjustId(String adjustId) {
		this.adjustId = adjustId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getKunnrName() {
		return kunnrName;
	}
	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}
	public String getApplyYear() {
		return applyYear;
	}
	public void setApplyYear(String applyYear) {
		this.applyYear = applyYear;
	}
	public String getApplyMonth() {
		return applyMonth;
	}
	public void setApplyMonth(String applyMonth) {
		this.applyMonth = applyMonth;
	}
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
	public String getNowTarget() {
		return nowTarget;
	}
	public void setNowTarget(String nowTarget) {
		this.nowTarget = nowTarget;
	}
	public String getNowDealerTarget() {
		return nowDealerTarget;
	}
	public void setNowDealerTarget(String nowDealerTarget) {
		this.nowDealerTarget = nowDealerTarget;
	}
	public String getAdjustTarget() {
		return adjustTarget;
	}
	public void setAdjustTarget(String adjustTarget) {
		this.adjustTarget = adjustTarget;
	}
	
	


}
