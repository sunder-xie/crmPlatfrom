package com.kintiger.platform.kunnr.pojo;

/**
 * ������������ϸ����
 * @author xxping
 *
 */
import com.kintiger.platform.base.pojo.SearchInfo;

public class KunnrBusiness extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -147542482064622932L;
	private Long id;
	private String kunnr;
	
	private String orgId;
	private Long businessId;
	private String businessName;
	private String businessType;
	private String businessEndDate;
  
	private String categories;// Ʒ��
	
	private String businessManager;   //���о���
	private String managerMobile;     //���о����ֻ�
	private String businessCompetent;   //�ͻ�����
	private String competentMobile;     //�ͻ������ֻ�
	
	private String businessHead;   //����
	private String headMobile;     //�����ֻ�
	private String businessAgent;   //ҵ��
	private String agentMobile;     //ҵ���ֻ�
	private String businessAgentId;
	private String businessHeadId;
	
	/*
	 * add  20130817
	 * ljwang
	 * start
	 */
	private String businessManagerId;   //���о���
	private String businessCompetentId;   //�ͻ�����
	private String kunnrLeader;      //�����̸�����
	private String kunnrPhone ;      //��������ϵ�˵绰
	
	//end
	
	private String werks;// ����
	private Double lastyearSales;
	private Double theyearSales;
	private String coverArea;
	private String akont;// ͳԦ��Ŀ
	private String zterm;// ���ʽ
	private String waers;// ����
	private String kalks;// ���۹���
	private String versg;// �͑�ͳ����
	private String vsbed;// װ������
	private String podkz;// POD���
	private String autlf;// ��ȫ����
	private String kztlf;// ÿ����Ŀ�Ľ���
	private String antlf;// ��󲿷ֽ���
	private String bokre;// �ؿ����
	private String ktgrd;// �˻�������
	private String taxkd01;// ˰����
	private String titleMedi;
	private String country;
	private String langu;
	private String bzirk;// ʡ
	private String vkbur;// ��
	private String vkgrp;// ����
	private String lprio;// ��������Ȩ
	private String accessFile;// �����̿���������
	private String accessFilePath;
	private String explainFile;// �����̿���˵��
	private String explainFilePath;
	private String customerFile;// �����¿��������г�����������
	private String customerFilePath;
	
    private String kunnrName;//����������
	
	private String kvgr2;  //����
	private String kvgr1;  //����
	
	private String kukla;  //sap�ͻ�����

	public String getAccessFile() {
		return accessFile;
	}

	public void setAccessFile(String accessFile) {
		this.accessFile = accessFile;
	}

	public String getAccessFilePath() {
		return accessFilePath;
	}

	public void setAccessFilePath(String accessFilePath) {
		this.accessFilePath = accessFilePath;
	}

	public String getExplainFile() {
		return explainFile;
	}

	public void setExplainFile(String explainFile) {
		this.explainFile = explainFile;
	}

	public String getExplainFilePath() {
		return explainFilePath;
	}

	public void setExplainFilePath(String explainFilePath) {
		this.explainFilePath = explainFilePath;
	}

	public String getCustomerFile() {
		return customerFile;
	}

	public void setCustomerFile(String customerFile) {
		this.customerFile = customerFile;
	}

	public String getCustomerFilePath() {
		return customerFilePath;
	}

	public void setCustomerFilePath(String customerFilePath) {
		this.customerFilePath = customerFilePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getBusinessManager() {
		return businessManager;
	}

	public void setBusinessManager(String businessManager) {
		this.businessManager = businessManager;
	}

	public String getManagerMobile() {
		return managerMobile;
	}

	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}

	public String getBusinessCompetent() {
		return businessCompetent;
	}

	public void setBusinessCompetent(String businessCompetent) {
		this.businessCompetent = businessCompetent;
	}

	public String getCompetentMobile() {
		return competentMobile;
	}

	public void setCompetentMobile(String competentMobile) {
		this.competentMobile = competentMobile;
	}

	public String getWerks() {
		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public Double getLastyearSales() {
		return lastyearSales;
	}

	public void setLastyearSales(Double lastyearSales) {
		this.lastyearSales = lastyearSales;
	}

	public Double getThheyearSales() {
		return theyearSales;
	}

	public void setThheyearSales(Double theyearSales) {
		this.theyearSales = theyearSales;
	}

	public String getCoverArea() {
		return coverArea;
	}

	public void setCoverArea(String coverArea) {
		this.coverArea = coverArea;
	}

	public String getAkont() {
		return akont;
	}

	public void setAkont(String akont) {
		this.akont = akont;
	}

	public String getZterm() {
		return zterm;
	}

	public void setZterm(String zterm) {
		this.zterm = zterm;
	}

	public String getWaers() {
		return waers;
	}

	public void setWaers(String waers) {
		this.waers = waers;
	}

	public String getKalks() {
		return kalks;
	}

	public void setKalks(String kalks) {
		this.kalks = kalks;
	}

	public String getVersg() {
		return versg;
	}

	public void setVersg(String versg) {
		this.versg = versg;
	}

	public String getVsbed() {
		return vsbed;
	}

	public void setVsbed(String vsbed) {
		this.vsbed = vsbed;
	}

	public String getPodkz() {
		return podkz;
	}

	public void setPodkz(String podkz) {
		this.podkz = podkz;
	}

	public String getAutlf() {
		return autlf;
	}

	public void setAutlf(String autlf) {
		this.autlf = autlf;
	}

	public String getKztlf() {
		return kztlf;
	}

	public void setKztlf(String kztlf) {
		this.kztlf = kztlf;
	}

	public String getAntlf() {
		return antlf;
	}

	public void setAntlf(String antlf) {
		this.antlf = antlf;
	}

	public String getBokre() {
		return bokre;
	}

	public void setBokre(String bokre) {
		this.bokre = bokre;
	}

	public String getKtgrd() {
		return ktgrd;
	}

	public void setKtgrd(String ktgrd) {
		this.ktgrd = ktgrd;
	}

	public String getTaxkd01() {
		return taxkd01;
	}

	public void setTaxkd01(String taxkd01) {
		this.taxkd01 = taxkd01;
	}

	public String getTitleMedi() {
		return titleMedi;
	}

	public void setTitleMedi(String titleMedi) {
		this.titleMedi = titleMedi;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLangu() {
		return langu;
	}

	public void setLangu(String langu) {
		this.langu = langu;
	}

	public String getBzirk() {
		return bzirk;
	}

	public void setBzirk(String bzirk) {
		this.bzirk = bzirk;
	}

	public String getVkbur() {
		return vkbur;
	}

	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}

	public String getVkgrp() {
		return vkgrp;
	}

	public void setVkgrp(String vkgrp) {
		this.vkgrp = vkgrp;
	}


	public String getLprio() {
		return lprio;
	}

	public void setLprio(String lprio) {
		this.lprio = lprio;
	}

	public Double getTheyearSales() {
		return theyearSales;
	}

	public void setTheyearSales(Double theyearSales) {
		this.theyearSales = theyearSales;
	}

	public String getBusinessManagerId() {
		return businessManagerId;
	}

	public void setBusinessManagerId(String businessManagerId) {
		this.businessManagerId = businessManagerId;
	}

	public String getBusinessCompetentId() {
		return businessCompetentId;
	}

	public void setBusinessCompetentId(String businessCompetentId) {
		this.businessCompetentId = businessCompetentId;
	}

	public String getKunnrLeader() {
		return kunnrLeader;
	}

	public void setKunnrLeader(String kunnrLeader) {
		this.kunnrLeader = kunnrLeader;
	}

	public String getKunnrPhone() {
		return kunnrPhone;
	}

	public void setKunnrPhone(String kunnrPhone) {
		this.kunnrPhone = kunnrPhone;
	}

	public String getKvgr2() {
		return kvgr2;
	}

	public void setKvgr2(String kvgr2) {
		this.kvgr2 = kvgr2;
	}

	public String getKvgr1() {
		return kvgr1;
	}

	public void setKvgr1(String kvgr1) {
		this.kvgr1 = kvgr1;
	}

	public String getKukla() {
		return kukla;
	}

	public void setKukla(String kukla) {
		this.kukla = kukla;
	}

	public String getKunnrName() {
		return kunnrName;
	}

	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}

	public String getBusinessHead() {
		return businessHead;
	}

	public void setBusinessHead(String businessHead) {
		this.businessHead = businessHead;
	}

	public String getHeadMobile() {
		return headMobile;
	}

	public void setHeadMobile(String headMobile) {
		this.headMobile = headMobile;
	}

	public String getBusinessAgent() {
		return businessAgent;
	}

	public void setBusinessAgent(String businessAgent) {
		this.businessAgent = businessAgent;
	}

	public String getAgentMobile() {
		return agentMobile;
	}

	public void setAgentMobile(String agentMobile) {
		this.agentMobile = agentMobile;
	}

	public String getBusinessAgentId() {
		return businessAgentId;
	}

	public void setBusinessAgentId(String businessAgentId) {
		this.businessAgentId = businessAgentId;
	}

	public String getBusinessHeadId() {
		return businessHeadId;
	}

	public void setBusinessHeadId(String businessHeadId) {
		this.businessHeadId = businessHeadId;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBusinessEndDate() {
		return businessEndDate;
	}

	public void setBusinessEndDate(String businessEndDate) {
		this.businessEndDate = businessEndDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
