package com.kintiger.platform.stockReport.pojo;

import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 用戶信息對象
 * 
 * @author xujiakun
 * 
 */
public class AllUsers extends SearchInfo {

	private static final long serialVersionUID = 1L;

	private String userId;
    
	private String passWd;

	/**
	 * emp_name
	 */
	private String userName;

	/**
	 * emp_showname 显示名
	 */
	private String userShowName;

	/**
	 * EMP_STATE 人员状态
	 */
	private String userState;

	private String custType;

	/**
	 * emp_code 人员在OA上的ID
	 */
	private String loginId;
	/**
	 * 区分工厂 还是行政
	 */
	private String type;
	
	/**
	 * 用作入职 离职时勾选状态
	 */
	private String flag;

	/**
	 * emp_mobile_phone 人员手机号码 / 因原通讯录中使用bus_mobilephone作为手机号码 此共用一个字段
	 */
	private String mobile;

	/**
	 * emp_shortmsgbook 手机短信订阅
	 */
	private String empShortmsgbook;

	private String smsUserTypeId;

	private Long chargeId;

	/**
	 * emp_phone 人员电话号码
	 */
	private String phone;
	/**
	 * bus_mobilephone 短信手机号码
	 */
	private String busMobilephone;

	/**
	 * 是否总部人员 0:否;1:是
	 */
	private String hqSign;

	private String kunnrSign;

	private String isOffice;

	private Date paswdSignDate;

	private String orgId;

	private String orgName;

	/**
	 * emp_id_card 销售人员身份证号码
	 */
	private String idCard;

	/**
	 * emp_workfax 传真
	 */
	private String workFax;

	/**
	 * emp_homephone
	 */
	private String homePhone;

	/**
	 * emp_start_date 开始时间
	 */
	private String startDate;
	/**
	 * 结束时间
	 */
    private String endDate;
	/**
	 * emp_address 地址
	 */
	private String address;

	/**
	 * emp_email 电子邮件
	 */
	private String email;

	/**
	 * emp_sex 人员性别
	 */
	private String sex;

	/**
	 * pos_name
	 */
	private String posName;
	/**
	 * 职务ID
	 */
	private String posId;

	/**
	 * havemail 是否开通邮箱（0否1是）
	 */
	private String haveMail;

	/**
	 * emp_remark 备注
	 */
	private String remark;

	/**
	 * position_type_name
	 */
	private String positionTypeName;

	private String[] orgIds;

	private Long id;

	private String orgStr;

	/**
	 * 內部内部供应商编号
	 */
	private String supplierNumber;

	private String questionLinkId;

	private String reason;
	
	private String roleIds;//岗位ID
	
	private String roleName;
	
	private String stationState;//岗位状态
		
	private String ids;//station_user表的ID 主键
	
	private String[] userIds;
	
	private String[] empPostIds;
	  
	private String empUserId;//员工编号
	
	private String empPostId;//职务ID
	
	private String empPostName;//职务ID
	
	private String expressly;//明文密码
	 
	private String birthDate;
	
	private String empBirthPlace;
	
	private String addHealthcerDate;
	
	private String addTempResPerDate;
	private String  company;//当前登陆人所在的公司
	private String   companyId;//所在公司ID
	private String orgCity;
	private String isMainStation;//是否主岗  Y：是，N：否
    
	private String objective;
	 
	private String addCadetDate;
	
	private String remindState;//到期类型：合同，生日等
	
	
	private String empMaritalState;
	
	
	private String empSex;
	private String addMeducation;
	private String addHousehold;
	private String addRank;
	
	private String busMoilePhone;

	private String addGradeLevel;
	
	
	private String nowDate;
	
	
	private String empStartDateStart;
	private String empStartDateEnd;
	private String addDepartureTimeStart;
	private String addDepartureTimeEnd;
	
	public String getIsMainStation() {
		return isMainStation;
	}

	public void setIsMainStation(String isMainStation) {
		this.isMainStation = isMainStation;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getExpressly() {
		return expressly;
	}

	public void setExpressly(String expressly) {
		this.expressly = expressly;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserShowName() {
		return userShowName;
	}

	public void setUserShowName(String userShowName) {
		this.userShowName = userShowName;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHqSign() {
		return hqSign;
	}

	public void setHqSign(String hqSign) {
		this.hqSign = hqSign;
	}

	public String getKunnrSign() {
		return kunnrSign;
	}

	public void setKunnrSign(String kunnrSign) {
		this.kunnrSign = kunnrSign;
	}

	public String getIsOffice() {
		return isOffice;
	}

	public void setIsOffice(String isOffice) {
		this.isOffice = isOffice;
	}

	public Date getPaswdSignDate() {
		return paswdSignDate;
	}

	public void setPaswdSignDate(Date paswdSignDate) {
		this.paswdSignDate = paswdSignDate;
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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getWorkFax() {
		return workFax;
	}

	public void setWorkFax(String workFax) {
		this.workFax = workFax;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	public String getHaveMail() {
		return haveMail;
	}

	public void setHaveMail(String haveMail) {
		this.haveMail = haveMail;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPositionTypeName() {
		return positionTypeName;
	}

	public void setPositionTypeName(String positionTypeName) {
		this.positionTypeName = positionTypeName;
	}

	public String[] getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String[] orgIds) {
		this.orgIds = orgIds;
	}

	public String getBusMobilephone() {
		return busMobilephone;
	}

	public void setBusMobilephone(String busMobilephone) {
		this.busMobilephone = busMobilephone;
	}

	public String getEmpShortmsgbook() {
		return empShortmsgbook;
	}

	public void setEmpShortmsgbook(String empShortmsgbook) {
		this.empShortmsgbook = empShortmsgbook;
	}

	public String getSmsUserTypeId() {
		return smsUserTypeId;
	}

	public void setSmsUserTypeId(String smsUserTypeId) {
		this.smsUserTypeId = smsUserTypeId;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgStr() {
		return orgStr;
	}

	public void setOrgStr(String orgStr) {
		this.orgStr = orgStr;
	}

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getQuestionLinkId() {
		return questionLinkId;
	}

	public void setQuestionLinkId(String questionLinkId) {
		this.questionLinkId = questionLinkId;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getStationState() {
		return stationState;
	}

	public void setStationState(String stationState) {
		this.stationState = stationState;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public String getEmpPostId() {
		return empPostId;
	}

	public void setEmpPostId(String empPostId) {
		this.empPostId = empPostId;
	}

	public String getEmpPostName() {
		return empPostName;
	}

	public void setEmpPostName(String empPostName) {
		this.empPostName = empPostName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddHealthcerDate() {
		return addHealthcerDate;
	}

	public void setAddHealthcerDate(String addHealthcerDate) {
		this.addHealthcerDate = addHealthcerDate;
	}

	public String getAddTempResPerDate() {
		return addTempResPerDate;
	}

	public void setAddTempResPerDate(String addTempResPerDate) {
		this.addTempResPerDate = addTempResPerDate;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getEmpUserId() {
		return empUserId;
	}

	public void setEmpUserId(String empUserId) {
		this.empUserId = empUserId;
	}

	public String getAddCadetDate() {
		return addCadetDate;
	}

	public void setAddCadetDate(String addCadetDate) {
		this.addCadetDate = addCadetDate;
	}

	public String getRemindState() {
		return remindState;
	}

	public void setRemindState(String remindState) {
		this.remindState = remindState;
	}

	public String getEmpBirthPlace() {
		return empBirthPlace;
	}

	public void setEmpBirthPlace(String empBirthPlace) {
		this.empBirthPlace = empBirthPlace;
	}

	public String getEmpMaritalState() {
		return empMaritalState;
	}

	public void setEmpMaritalState(String empMaritalState) {
		this.empMaritalState = empMaritalState;
	}

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public String getAddMeducation() {
		return addMeducation;
	}

	public void setAddMeducation(String addMeducation) {
		this.addMeducation = addMeducation;
	}

	public String getAddHousehold() {
		return addHousehold;
	}

	public void setAddHousehold(String addHousehold) {
		this.addHousehold = addHousehold;
	}

	public String getAddRank() {
		return addRank;
	}

	public void setAddRank(String addRank) {
		this.addRank = addRank;
	}

	public String getAddGradeLevel() {
		return addGradeLevel;
	}

	public void setAddGradeLevel(String addGradeLevel) {
		this.addGradeLevel = addGradeLevel;
	}

	public String[] getEmpPostIds() {
		return empPostIds;
	}

	public void setEmpPostIds(String[] empPostIds) {
		this.empPostIds = empPostIds;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	public String getBusMoilePhone() {
		return busMoilePhone;
	}

	public void setBusMoilePhone(String busMoilePhone) {
		this.busMoilePhone = busMoilePhone;
	}

	public String getEmpStartDateStart() {
		return empStartDateStart;
	}

	public void setEmpStartDateStart(String empStartDateStart) {
		this.empStartDateStart = empStartDateStart;
	}

	public String getEmpStartDateEnd() {
		return empStartDateEnd;
	}

	public void setEmpStartDateEnd(String empStartDateEnd) {
		this.empStartDateEnd = empStartDateEnd;
	}

	public String getAddDepartureTimeStart() {
		return addDepartureTimeStart;
	}

	public void setAddDepartureTimeStart(String addDepartureTimeStart) {
		this.addDepartureTimeStart = addDepartureTimeStart;
	}

	public String getAddDepartureTimeEnd() {
		return addDepartureTimeEnd;
	}

	public void setAddDepartureTimeEnd(String addDepartureTimeEnd) {
		this.addDepartureTimeEnd = addDepartureTimeEnd;
	}
    
}
