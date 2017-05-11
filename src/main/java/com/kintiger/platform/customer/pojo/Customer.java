package com.kintiger.platform.customer.pojo;

import java.io.Serializable;

import com.kintiger.platform.base.pojo.SearchInfo;

public class Customer extends SearchInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long custId;     //�ŵ��ڲ�ID������
	private String custName;  //�ŵ�����
	private String custNumber;     //�ŵ���루����ʡ������ĸ+ϵͳ��ˮ�ţ�
	private Integer  channelId;       //����ID
	private String  stationUserId;  //ҵ�����ְλID��
	private String contactName;    //��ϵ��
	private String contactMobile;   //��ϵ���ֻ�
	private String contactPhone;  //��ϵ�˵绰
	private String contactSex;  //��ϵ�˳�ν(��/Ů)	
	private String createUser;    //������(��ID)
	private String createOrgId;  //��������֯(��OrgID)
	private String createOrgName;//��������֯����(��OrgName)
	private String createDateStart;//������ʼʱ��
	private String createDateEnd;//��������ʱ��
	
	private String custProvince;    //ʡ/ֱϽ��/������
	private String custCity;          //�м�
	private String custDistrict;      //�ؼ�
	private String villages;       //����
	private String custTown;      //�塢����
	private String custStreet;    //·/��/��/��/Ū
	private String custHouserNumber;    //���ƺ�
	private Integer custState;        //�ŵ�״̬ 0��������1����Ъ��2���¿ͻ���3���ѹػ���
	private String custLhy;       //���Ա
	private Integer visitFreq;        //�ݷ�Ƶ��
	private String visitDays;     //�ݷ�����
	
	private String custAddress;    //�ο�λ��
	private String shopArea;       //�۵����
	private String corporateDeputy;   //���˴���
	private String idCopy;        //���֤����
	private String businessLicense;    //Ӫҵִ�պ���
	private String licenceValidity;    //Ӫҵִ����Ч��
	private String registerCapital;    //�ʲ���Ϣ��ע���ʱ���
	private Integer custLevel;       //�ȼ�
	private Integer custReceive;     //���ͷ�ʽ 0�� ���������ͣ�1�����������ͣ�2��ֱӪ��3���ܲ�����
	private String custSystem;     //����ϵͳ   ����������������ȣ�ka�ͻ���
	private String createDate;
	private String lastModify;
	private String modifyUser;
	private String remark;
	private String custKunnr; //������������
	private String channelName;//������
	private String stationName;//��λ����
	private String custKunnrName;
	private String visitFreqName;
	private String custStateName;
	private String orgId;
	
	private String empName;  //ҵ���������
	private String custReceiveName;
	private String visitDaysName;
	private String custParentName;
	
	private String  orgName;
	
	private String custPhone;
	private String kunnrUser;			// �����̸�����
	private String custParent;
	private String custType;
	
	private String custSystemName;
	
	private String longitude; //����
	private String latitude; //γ��
	private String allChannelName; //       �ۺ�����
	
	private String customerImportance;//�ŵ���Ҫ��
	private String customerAnnualSale;//�ŵ����۽��
	
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public String getCustNumber() {
		return custNumber;
	}
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	
	public String getStationUserId() {
		return stationUserId;
	}
	public void setStationUserId(String stationUserId) {
		this.stationUserId = stationUserId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactSex() {
		return contactSex;
	}
	public void setContactSex(String contactSex) {
		this.contactSex = contactSex;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCustProvince() {
		return custProvince;
	}
	public void setCustProvince(String custProvince) {
		this.custProvince = custProvince;
	}
	public String getCustTown() {
		return custTown;
	}
	public void setCustTown(String custTown) {
		this.custTown = custTown;
	}
	public String getCustStreet() {
		return custStreet;
	}
	public void setCustStreet(String custStreet) {
		this.custStreet = custStreet;
	}
	public String getCustHouserNumber() {
		return custHouserNumber;
	}
	public void setCustHouserNumber(String custHouserNumber) {
		this.custHouserNumber = custHouserNumber;
	}
	
	public String getCustLhy() {
		return custLhy;
	}
	public void setCustLhy(String custLhy) {
		this.custLhy = custLhy;
	}
	
	public String getVisitDays() {
		return visitDays;
	}
	public void setVisitDays(String visitDays) {
		this.visitDays = visitDays;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getShopArea() {
		return shopArea;
	}
	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}
	public String getCorporateDeputy() {
		return corporateDeputy;
	}
	public void setCorporateDeputy(String corporateDeputy) {
		this.corporateDeputy = corporateDeputy;
	}
	public String getIdCopy() {
		return idCopy;
	}
	public void setIdCopy(String idCopy) {
		this.idCopy = idCopy;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getLicenceValidity() {
		return licenceValidity;
	}
	public void setLicenceValidity(String licenceValidity) {
		this.licenceValidity = licenceValidity;
	}
	
	
	public String getRegisterCapital() {
		return registerCapital;
	}
	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public Integer getCustState() {
		return custState;
	}
	public void setCustState(Integer custState) {
		this.custState = custState;
	}
	public Integer getVisitFreq() {
		return visitFreq;
	}
	public void setVisitFreq(Integer visitFreq) {
		this.visitFreq = visitFreq;
	}
	public Integer getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(Integer custLevel) {
		this.custLevel = custLevel;
	}
	public Integer getCustReceive() {
		return custReceive;
	}
	public void setCustReceive(Integer custReceive) {
		this.custReceive = custReceive;
	}
	
	public String getCustSystem() {
		return custSystem;
	}
	public void setCustSystem(String custSystem) {
		this.custSystem = custSystem;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastModify() {
		return lastModify;
	}
	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustKunnr() {
		return custKunnr;
	}
	public void setCustKunnr(String custKunnr) {
		this.custKunnr = custKunnr;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getCustKunnrName() {
		return custKunnrName;
	}
	public void setCustKunnrName(String custKunnrName) {
		this.custKunnrName = custKunnrName;
	}
	public String getVisitFreqName() {
		return visitFreqName;
	}
	public void setVisitFreqName(String visitFreqName) {
		this.visitFreqName = visitFreqName;
	}
	public String getCustStateName() {
		return custStateName;
	}
	public void setCustStateName(String custStateName) {
		this.custStateName = custStateName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCustCity() {
		return custCity;
	}
	public void setCustCity(String custCity) {
		this.custCity = custCity;
	}
	public String getCustDistrict() {
		return custDistrict;
	}
	public void setCustDistrict(String custDistrict) {
		this.custDistrict = custDistrict;
	}
	public String getCustReceiveName() {
		return custReceiveName;
	}
	public void setCustReceiveName(String custReceiveName) {
		this.custReceiveName = custReceiveName;
	}

	public String getVisitDaysName() {
		return visitDaysName;
	}
	public void setVisitDaysName(String visitDaysName) {
		this.visitDaysName = visitDaysName;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getVillages() {
		return villages;
	}
	public void setVillages(String villages) {
		this.villages = villages;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getKunnrUser() {
		return kunnrUser;
	}
	public void setKunnrUser(String kunnrUser) {
		this.kunnrUser = kunnrUser;
	}
	public String getCustParent() {
		return custParent;
	}
	public void setCustParent(String custParent) {
		this.custParent = custParent;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getCustParentName() {
		return custParentName;
	}
	public void setCustParentName(String custParentName) {
		this.custParentName = custParentName;
	}
	public String getCustSystemName() {
		return custSystemName;
	}
	public void setCustSystemName(String custSystemName) {
		this.custSystemName = custSystemName;
	}
	public String getCreateOrgId() {
		return createOrgId;
	}
	public void setCreateOrgId(String createOrgId) {
		this.createOrgId = createOrgId;
	}
	public String getCreateOrgName() {
		return createOrgName;
	}
	public void setCreateOrgName(String createOrgName) {
		this.createOrgName = createOrgName;
	}
	public String getCreateDateStart() {
		return createDateStart;
	}
	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}
	public String getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void AllChannelName(String allChannelName) {
	    // TODO Auto-generated method stub
	    
	}
	public String getAllChannelName() {
	    return allChannelName;
	}
	public void setAllChannelName(String allChannelName) {
	    this.allChannelName = allChannelName;
	}
	public String getCustomerImportance() {
		return customerImportance;
	}
	public void setCustomerImportance(String customerImportance) {
		this.customerImportance = customerImportance;
	}
	public String getCustomerAnnualSale() {
		return customerAnnualSale;
	}
	public void setCustomerAnnualSale(String customerAnnualSale) {
		this.customerAnnualSale = customerAnnualSale;
	} 
	
	
}
