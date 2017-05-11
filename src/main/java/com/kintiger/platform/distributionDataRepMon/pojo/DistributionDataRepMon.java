package com.kintiger.platform.distributionDataRepMon.pojo;

import java.io.Serializable;
import java.util.Date;

import com.kintiger.platform.base.pojo.SearchInfo;

/**
 * 经、分销商目标达标设置实体类文
 * 
 * @author zhyiyue
 * 
 */
public class DistributionDataRepMon extends SearchInfo implements Serializable {

	private static final long serialVersionUID = 7907457232456644538L;
	private String orgId;
	private long dgId;
	private String orgRegion;
	private String orgProvince;
	private String orgCity;
	private String firstUser;
	private String secondUser;
	private String thirdUser;
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
	
	private double aOneM;
	private double aTwoM;
	private double aThreeM;
	private double aFourM;
	private double aFiveM;
	private double aSixM;
	private double aSevenM;
	private double aEightM;
	private double bOneM;
	private double bSixM;
	private double bEightM;
	private double cOneM;
	private double cSixM;
	private double cSevenM;
	private double cEightM;
	private double dNineM;
	private double dTenM;
	private double eOneM;
	private double hSevenM;
	
	private String resign_date;
	private String[] ids;
	private String lastDate;
	private String thisSysdate;
	private String createDate;
	private String itemJLTotal;
	private String itemGoalTotal;
	private String dcl;
	

	
	

	public String getItemJLTotal() {
		return itemJLTotal;
	}

	public void setItemJLTotal(String itemJLTotal) {
		this.itemJLTotal = itemJLTotal;
	}

	public String getItemGoalTotal() {
		return itemGoalTotal;
	}

	public void setItemGoalTotal(String itemGoalTotal) {
		this.itemGoalTotal = itemGoalTotal;
	}

	



	public String getDcl() {
		return dcl;
	}

	/*public void setDcl(double dcl) {
		double dl = (double) (Math.round(dcl*10000)/10000.0);
		
		this.dcl = dl;
	}*/
	
	public void setDcl(String dcl) {
		
		
		double dl = (double) (Math.round(Double.parseDouble(dcl)*10000)/100.0);
		
		this.dcl = String.valueOf(dl)+"%";
	}

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

	public double getbSix() {
		return bSix;
	}

	public void setbSix(double bSix) {
		this.bSix = bSix;
	}

	public double getdNine() {
		return dNine;
	}

	public void setdNine(double dNine) {
		this.dNine = dNine;
	}

	public double gethSeven() {
		return hSeven;
	}

	public void sethSeven(double hSeven) {
		this.hSeven = hSeven;
	}
	
	public String getResign_date() {
		return resign_date;
	}

	public void setResign_date(String resign_date) {
		this.resign_date = resign_date;
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

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getSecondUser() {
		return secondUser;
	}

	public void setSecondUser(String secondUser) {
		this.secondUser = secondUser;
	}

	public String getThirdUser() {
		return thirdUser;
	}

	public void setThirdUser(String thirdUser) {
		this.thirdUser = thirdUser;
	}

	public double getdTen() {
		return dTen;
	}

	public void setdTen(double dTen) {
		this.dTen = dTen;
	}

	public double geteOne() {
		return eOne;
	}

	public void seteOne(double eOne) {
		this.eOne = eOne;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getThisSysdate() {
		return thisSysdate;
	}

	public void setThisSysdate(String thisSysdate) {
		this.thisSysdate = thisSysdate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public double getaOneM() {
		return aOneM;
	}

	public void setaOneM(double aOneM) {
		this.aOneM = aOneM;
	}

	public double getaTwoM() {
		return aTwoM;
	}

	public void setaTwoM(double aTwoM) {
		this.aTwoM = aTwoM;
	}

	public double getaThreeM() {
		return aThreeM;
	}

	public void setaThreeM(double aThreeM) {
		this.aThreeM = aThreeM;
	}

	public double getaFourM() {
		return aFourM;
	}

	public void setaFourM(double aFourM) {
		this.aFourM = aFourM;
	}

	public double getaFiveM() {
		return aFiveM;
	}

	public void setaFiveM(double aFiveM) {
		this.aFiveM = aFiveM;
	}

	public double getaSixM() {
		return aSixM;
	}

	public void setaSixM(double aSixM) {
		this.aSixM = aSixM;
	}

	public double getaSevenM() {
		return aSevenM;
	}

	public void setaSevenM(double aSevenM) {
		this.aSevenM = aSevenM;
	}

	public double getaEightM() {
		return aEightM;
	}

	public void setaEightM(double aEightM) {
		this.aEightM = aEightM;
	}

	public double getbOneM() {
		return bOneM;
	}

	public void setbOneM(double bOneM) {
		this.bOneM = bOneM;
	}

	public double getbSixM() {
		return bSixM;
	}

	public void setbSixM(double bSixM) {
		this.bSixM = bSixM;
	}

	public double getbEightM() {
		return bEightM;
	}

	public void setbEightM(double bEightM) {
		this.bEightM = bEightM;
	}

	public double getcOneM() {
		return cOneM;
	}

	public void setcOneM(double cOneM) {
		this.cOneM = cOneM;
	}

	public double getcSixM() {
		return cSixM;
	}

	public void setcSixM(double cSixM) {
		this.cSixM = cSixM;
	}

	public double getcSevenM() {
		return cSevenM;
	}

	public void setcSevenM(double cSevenM) {
		this.cSevenM = cSevenM;
	}

	public double getcEightM() {
		return cEightM;
	}

	public void setcEightM(double cEightM) {
		this.cEightM = cEightM;
	}

	public double getdNineM() {
		return dNineM;
	}

	public void setdNineM(double dNineM) {
		this.dNineM = dNineM;
	}

	public double getdTenM() {
		return dTenM;
	}

	public void setdTenM(double dTenM) {
		this.dTenM = dTenM;
	}

	public double geteOneM() {
		return eOneM;
	}

	public void seteOneM(double eOneM) {
		this.eOneM = eOneM;
	}

	public double gethSevenM() {
		return hSevenM;
	}

	public void sethSevenM(double hSevenM) {
		this.hSevenM = hSevenM;
	}
	
	
	
}
