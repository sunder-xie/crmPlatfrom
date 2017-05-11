package com.kintiger.platform.kunnr.pojo;

import com.kintiger.platform.base.pojo.SearchInfo;

public class KunnrAcount extends SearchInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6726563582267976275L;
	private Long id;
	private String kunnr;
	private String acountType;
	private String typeA;// �·�
	private String typeB;// ��
	private String typeC;// ��
	private String typeMoney;//Э���
	private Long typeAId;
	private Long typeBId;
	private Long typeCId;
	private String type2A;// 2=>1�·�
	private String type2B;// 2=>1��
	private String type2C;// 2=>1��
	//private String type2Money;//Э���
	private Long type2AId;
	private Long type2BId;
	private Long type2CId;
	private String acount;
	private String acountSap;
	private String bonus;// ����
	private String vtext;// ����
	private String flag;// �Ƿ������һ��

	
	private String text0;   //sap Э���
	private String startDate;   //Э����Ч����
	private String endDate;   //��Ч��ֹ����
	private String acountSapA;  //Э����·�
	private String acountSapB;  //Э��ż���
	private String acountSapC;  //Э����귵
	
	public String getVtext() {
		return vtext;
	}

	public void setVtext(String vtext) {
		this.vtext = vtext;
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

	public String getTypeA() {
		return typeA;
	}

	public void setTypeA(String typeA) {
		this.typeA = typeA;
	}

	public String getTypeB() {
		return typeB;
	}

	public void setTypeB(String typeB) {
		this.typeB = typeB;
	}

	public String getTypeC() {
		return typeC;
	}

	public void setTypeC(String typeC) {
		this.typeC = typeC;
	}

	public String getAcount() {
		return acount;
	}

	public void setAcount(String acount) {
		this.acount = acount;
	}

	public String getAcountSap() {
		return acountSap;
	}

	public void setAcountSap(String acountSap) {
		this.acountSap = acountSap;
	}

	public String getBonus() {
		return bonus;
	}

	public Long getTypeAId() {
		return typeAId;
	}

	public void setTypeAId(Long typeAId) {
		this.typeAId = typeAId;
	}

	public Long getTypeBId() {
		return typeBId;
	}

	public void setTypeBId(Long typeBId) {
		this.typeBId = typeBId;
	}

	public Long getTypeCId() {
		return typeCId;
	}

	public void setTypeCId(Long typeCId) {
		this.typeCId = typeCId;
	}

	public Long getType2AId() {
		return type2AId;
	}

	public void setType2AId(Long type2aId) {
		type2AId = type2aId;
	}

	public Long getType2BId() {
		return type2BId;
	}

	public void setType2BId(Long type2bId) {
		type2BId = type2bId;
	}

	public Long getType2CId() {
		return type2CId;
	}

	public void setType2CId(Long type2cId) {
		type2CId = type2cId;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getAcountType() {
		return acountType;
	}

	public void setAcountType(String acountType) {
		this.acountType = acountType;
	}

	public String getType2A() {
		return type2A;
	}

	public void setType2A(String type2a) {
		type2A = type2a;
	}

	public String getType2B() {
		return type2B;
	}

	public void setType2B(String type2b) {
		type2B = type2b;
	}

	public String getType2C() {
		return type2C;
	}

	public void setType2C(String type2c) {
		type2C = type2c;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getText0() {
		return text0;
	}

	public void setText0(String text0) {
		this.text0 = text0;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTypeMoney() {
		return typeMoney;
	}

	public void setTypeMoney(String typeMoney) {
		this.typeMoney = typeMoney;
	}

	public String getAcountSapA() {
		return acountSapA;
	}

	public void setAcountSapA(String acountSapA) {
		this.acountSapA = acountSapA;
	}

	public String getAcountSapB() {
		return acountSapB;
	}

	public void setAcountSapB(String acountSapB) {
		this.acountSapB = acountSapB;
	}

	public String getAcountSapC() {
		return acountSapC;
	}

	public void setAcountSapC(String acountSapC) {
		this.acountSapC = acountSapC;
	}

	/*public String getType2Money() {
		return type2Money;
	}

	public void setType2Money(String type2Money) {
		this.type2Money = type2Money;
	}*/

	
}
