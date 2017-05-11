package com.kintiger.platform.kunnrBusinessContact.pojo;

/**
 * ������������ϸ����
 * @author xxping
 *
 */
import com.kintiger.platform.base.pojo.SearchInfo;

public class KunnrBusinessC extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -14762623421422932L;
	private Long id;
	private String kunnr;
	private String categories;// Ʒ��
	
	private String businessManager;   //���о���
	private String managerMobile;     //���о����ֻ�
	private String businessCompetent;   //�ͻ�����
	private String competentMobile;     //�ͻ������ֻ�
	
	private String businessManagerId;   //���о���
	private String businessCompetentId;   //�ͻ�����
	private String kunnrLeader;      //�����̸�����
	private String kunnrPhone ;      //��������ϵ�˵绰
	
	
	
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
	
}
