package com.kintiger.platform.distributionDataAppendRep.pojo;

import java.io.Serializable;
import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 经、分销商目标达标设置实体类文件
 * 
 * @author zhyiyue
 * 
 */
public class DistributionDataAppendRep extends SearchInfo implements Serializable {

	private static final long serialVersionUID = 7907457232456644538L;
	private String orgId;
	private long dgId;
	private String orgRegion;
	private String orgProvince;
	private String orgCity;
	private String firstUser;
	private String position;
	private String dire_super_name;
	private String dire_super_position;
	private String orgName;
	private String kunnrId;
	private String kunnrName;
	private String matter;
	private String state;
	private int boxNum;
	private String inputDate;
	private String startDate;
	private String endDate;
	private String checkName;
	private String trFlag;
	private String userId;
	private String inputName;
	private Date lastModify;
	private double aOne;
	private double aTwo;
	private double aThree;
	private double aFour;
	private double aFive;
	private double aSix;
	private double aSeven;
	private double aEight;
	private double bOne;
	private double bSix;
	private double bEight;
	private double cOne;
	private double cSix;
	private double cSeven;
	private double cEight;
	private double dNine;
	private double dTen;
	private double eOne;
	private double hSeven;
	private String channel;
	private String store_category;
	private String resign_date;
	private String createDate;
	
	private String[] ids;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgRegion() {
		return orgRegion;
	}

	public void setOrgRegion(String orgRegion) {
		this.orgRegion = orgRegion;
	}

	public String getOrgProvince() {
		return orgProvince;
	}

	public void setOrgProvince(String orgProvince) {
		this.orgProvince = orgProvince;
	}

	public String getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	public String getFirstUser() {
		return firstUser;
	}

	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getDire_super_name() {
		return dire_super_name;
	}

	public void setDire_super_name(String dire_super_name) {
		this.dire_super_name = dire_super_name;
	}

	public String getDire_super_position() {
		return dire_super_position;
	}

	public void setDire_super_position(String dire_super_position) {
		this.dire_super_position = dire_super_position;
	}

	public String getKunnrId() {
		return kunnrId;
	}

	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
	}

	public String getKunnrName() {
		return kunnrName;
	}

	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getBoxNum() {
		return boxNum;
	}

	public void setBoxNum(int boxNum) {
		this.boxNum = boxNum;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getTrFlag() {
		return trFlag;
	}

	public void setTrFlag(String trFlag) {
		this.trFlag = trFlag;
	}

	public double getaOne() {
		return aOne;
	}

	public void setaOne(double aOne) {
		this.aOne = aOne;
	}

	public double getaTwo() {
		return aTwo;
	}

	public void setaTwo(double aTwo) {
		this.aTwo = aTwo;
	}

	public double getaThree() {
		return aThree;
	}

	public void setaThree(double aThree) {
		this.aThree = aThree;
	}

	public double getaFour() {
		return aFour;
	}

	public void setaFour(double aFour) {
		this.aFour = aFour;
	}

	public double getaFive() {
		return aFive;
	}

	public void setaFive(double aFive) {
		this.aFive = aFive;
	}

	public double getaSix() {
		return aSix;
	}

	public void setaSix(double aSix) {
		this.aSix = aSix;
	}

	public double getaSeven() {
		return aSeven;
	}

	public void setaSeven(double aSeven) {
		this.aSeven = aSeven;
	}

	public double getaEight() {
		return aEight;
	}

	public void setaEight(double aEight) {
		this.aEight = aEight;
	}

	public double getbOne() {
		return bOne;
	}

	public void setbOne(double bOne) {
		this.bOne = bOne;
	}
	
	public double getbSix() {
		return bSix;
	}

	public void setbSix(double bSix) {
		this.bSix = bSix;
	}
	
	public double getbEight() {
		return bEight;
	}

	public void setbEight(double bEight) {
		this.bEight = bEight;
	}

	public double getcOne() {
		return cOne;
	}

	public void setcOne(double cOne) {
		this.cOne = cOne;
	}

	public double getcSix() {
		return cSix;
	}

	public void setcSix(double cSix) {
		this.cSix = cSix;
	}

	public double getcSeven() {
		return cSeven;
	}

	public void setcSeven(double cSeven) {
		this.cSeven = cSeven;
	}

	public double getcEight() {
		return cEight;
	}

	public void setcEight(double cEight) {
		this.cEight = cEight;
	}
	
	

	public double geteOne() {
		return eOne;
	}
	
	public void seteOne(double eOne) {
		this.eOne = eOne;
	}
	
	public double getdNine() {
		return dNine;
	}

	public void setdNine(double dNine) {
		this.dNine = dNine;
	}
	
	public double getdTen() {
		return dTen;
	}

	public void setdTen(double dTen) {
		this.dTen = dTen;
	}

	public double gethSeven() {
		return hSeven;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getDgId() {
		return dgId;
	}

	public void setDgId(long dgId) {
		this.dgId = dgId;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}
	
	

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStore_category() {
		return store_category;
	}

	public void setStore_category(String store_category) {
		this.store_category = store_category;
	}

	public String getResign_date() {
		return resign_date;
	}

	public void setResign_date(String resign_date) {
		this.resign_date = resign_date;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void sethSeven(double hSeven) {
		this.hSeven = hSeven;
	}

	

	
	

	
}
