package com.kintiger.platform.kunnr.dao;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrAcount;
import com.kintiger.platform.kunnr.pojo.KunnrAddress;
import com.kintiger.platform.kunnr.pojo.KunnrApplySave;
import com.kintiger.platform.kunnr.pojo.KunnrBrand;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnr.pojo.KunnrLicense;
import com.kintiger.platform.kunnr.pojo.KunnrLogisticsArea;
import com.kintiger.platform.kunnr.pojo.KunnrRole;
import com.kintiger.platform.kunnr.pojo.KunnrSalesArea;
import com.kintiger.platform.kunnr.pojo.KunnrSapCodeObject;
import com.kintiger.platform.org.pojo.Borg;

public interface IKunnrDao {

	/**
	 * 经销商编码序列
	 * 
	 * @return
	 */
	public String getRanKunnrCode();
	/**
	 * sap编码后四位
	 * @return
	 */
	public String getRanKunnrSapCode() ;
	/**
	 * 经销商打码号序列
	 * 
	 * @return
	 */
	public String getRanKunnrDMCode();
	/**
	 * 经销商sap编码序列：省/城市+(该城市下最大编号+1)
	 *参数：省+城市
	 * @return
	 */
	public String getRanKunnrCodeNew(String code);
	
	public Long createKunnrSapCode(KunnrSapCodeObject obj);

	/**
	 * 创建经销商基本信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public long createKunnr(Kunnr kunnr);

	/**
	 * 创建经销商详细信息
	 * 
	 * @param business
	 * @return
	 */
	public long createKunnrBusiness(KunnrBusiness business);

	/**
	 * 创建经销商送达地址
	 * 
	 * @param address
	 * @return
	 */
	public void createKunnrAddress(List<KunnrAddress> kunnrAddressList,
			String kunnr);

	/**
	 * 创建经销商经营品牌
	 * 
	 * @param brand
	 * @return
	 */
	public void createKunnrBrand(List<KunnrBrand> kunnrBrandList, String kunnr);

	/**
	 * 创建经销商折扣
	 * 
	 * @param kunnrAcountList
	 * @param kunnr
	 */
	public void createKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr);

	/**
	 * 创建证照信息
	 * 
	 * @param kunnrLicenseList
	 * @param kunnr
	 */
	public void createKunnrLicense(List<KunnrLicense> kunnrLicenseList,
			String kunnr);

	/**
	 * 修改经销商基本信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public Integer updateKunnr(Kunnr kunnr);

	/**
	 * 修改经销商详细信息
	 * 
	 * @param business
	 * @return
	 */
	public Integer updateKunnrBusiness(KunnrBusiness business);

	/**
	 * 修改经销商送达地址
	 * 
	 * @param address
	 * @return
	 */
	public void updateAndCreateKunnrAddress(
			List<KunnrAddress> kunnrAddressList, String kunnr);

	/**
	 * 修改经销商经营品牌
	 * 
	 * @param brand
	 * @return
	 */
	public void updateAndCreateKunnrBrand(List<KunnrBrand> kunnrBrandList,
			String kunnr);

	/**
	 * 修改经销商折扣
	 * 
	 * @param kunnrAcountList
	 * @param kunnr
	 */
	public void updateAndCreateKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr);

	/**
	 * 删除品牌
	 * 
	 * @param killBrand
	 */
	public void removeBrand(String killBrand);

	/**
	 * 删除折扣
	 * 
	 * @param killAcount
	 */
	public void removeAcount(String killAcount);

	/**
	 * 经销商冻结
	 * 
	 * @param kunnr
	 * @return
	 */
	public boolean kunnrFreeze(Kunnr kunnr);

	/**
	 * 经销商关乎
	 * 
	 * @param kunnr
	 * @return
	 */
	public boolean kunnrClose(Kunnr kunnr);

	/**
	 * 
	 * 经销商查询COUNT
	 * 
	 * @return
	 */
	public int kunnrSearchCount(Kunnr kunnr);

	/**
	 * 
	 * 经销商列表查询
	 * 
	 * @return
	 */
	public List<Kunnr> kunnrSearch(Kunnr kunnr);

	/**
	 * 
	 * 经销商信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public Kunnr getKunnrEntity(Kunnr kunnr);

	/**
	 * 经销商详细信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrBusiness getKunnrBusinessEntity(Kunnr kunnr);

	/**
	 * 经销商地址信息列表 即送达方
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrAddress> getKunnrAddressList(Kunnr kunnr);

	/**
	 * 经销商品牌列表
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrBrand> getKunnrBrandList(Kunnr kunnr);

	/**
	 * 经销商折扣说明列表
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrAcount> getKunnrAcountList(Kunnr kunnr);

	/**
	 * 经销商证照信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrLicense> getKunnrLicenseList(Kunnr kunnr);
	
	/**
	 * 创建销售范围
	 * 
	 * @param kunnrLicenseList
	 * @param kunnr
	 */
	public void createSaleArea(List<KunnrSalesArea> kunnrSalesAreaList,
			String kunnr);
	
	/**
	 * 创建证照信息
	 * 
	 * @param kunnrLicenseList
	 * @param kunnr
	 */
	public void createKunnrGoal(List<BCustomerTarget> bCustomerTargetList);
	
	/**
	 * 经销商目标量
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<BCustomerTarget> getBCustomerTargetList(Kunnr kunnr);
	
	/**
	 * 经销商销售范围
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrSalesArea> getKunnrSalesAreaList(Kunnr kunnr);
	
	/**
	 * 修改经销商范围
	 * 
	 * @param kunnrAcountList
	 * @param kunnr
	 */
	public void updateAndCreateSalesArea(List<KunnrSalesArea> salesAreaList,
			String kunnr);
	
	/**
	 * 删除销售范围
	 * 
	 * @param killSalesArea
	 */
	public void removeSalesArea(List<KunnrSalesArea> salesAreaList);
	

	/**
	 * 物流区域查询
	 * @return
	 */
	public List<KunnrLogisticsArea> getKunnrLogisticsArea(KunnrLogisticsArea area);
	public int getKunnrLogisticsAreaCount(KunnrLogisticsArea area);
	
	/**
	 * 修改物流区域
	 * @param areaList
	 * @return
	 */
	public void updateLogisticArea(List<KunnrLogisticsArea> areaList);
	
	/**
	 * 验证是否是此角色用户
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int getRoleOnEventByUser(String userId,String roleId);
	
	
	/**
	 * 查证照信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrLicense> getKunnrLicenseListByLicense(KunnrLicense license);
	/**
	 * 扣除占用 的预算目标量
	 * 
	 * @param kunnrAcountList
	 * @param kunnr
	 */
	public void updateKunnrTarget(Kunnr kunnr);
	
	/**
	 * 经销商开户保存
	 * @param kunnrApply
	 */
	public void kunnrApplySave(KunnrApplySave kunnrApply);
	
	
	/**
	 * 经销商提报存储表查询
	 * @param kunnrApply
	 * @return
	 */
	public int kunnrApplySaveSearchCount(KunnrApplySave kunnrApply);
	public List<KunnrApplySave> kunnrApplySaveSearch(KunnrApplySave kunnrApply);
	
	/**
	 * 修改经销商开户保存功能表
	 * @param kunnrApply
	 */
	public void updateKunnrApplySave(KunnrApplySave kunnrApply);
	
	/**
	 * 修改经销商sap编号生成表
	 * @param kunnrApply
	 */
	public void updateKunnrSapCodeStatus(KunnrSapCodeObject obj);
	/**
	 * 还原经销商状态
	 * @param kunnr
	 * @return
	 */
	public int updateKunnrStatus(Kunnr kunnr);
	/**
	 * 经销商用户创建
	 * @param kunnr
	 * @return
	 */
	public Long createKunnrUser(Kunnr kunnr);
	/**
	 * 经销商用户角色创建
	 * @param userId
	 */
	public void createKunnrUserRole(KunnrRole role);
	/**
	 * 经销商关户后禁用经销商用户及其雇员用户
	 * @param kunnr
	 */
	public void closeKunnrUser(Kunnr kunnr);
	/**
	 * 经销商雇员编制修改
	 * @param kunnr
	 * @return
	 */
	public void updateKunnrUserStaff(Kunnr kunnr);
	/**
	 * updateKunnrUser:(更新经销商用户信息)
	 * @param  @param kunnr    设定文件
	 * @return void    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void updateKunnrUser(Kunnr kunnr);
	/**
	 * 
	 * releaseKunnrTarget:(释放当前月之后的经销商目标量)
	 * @param  @return    设定文件
	 * @return boolean    DOM对象
	 * @throws 
	 * @since  CodingExample　Ver 1.1
	 */
	public void releaseKunnrTarget(Kunnr kunnr);
	/**
	 * 修改资料不全回退发起人流程变量
	 * @author cg.jiang
	 * @param eventId
	 */
	public void modifyProcessVariable(String eventId);
	/**
	 * Title: 经销商当前月之后的月份的经销商目标量清空</p>
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月10日 下午4:50:21
	 * @param kunnrTar
	 */
	public void deleteKunnrTarget(Kunnr kunnrTar);
	public int getOfficeRole(String userId, String roleId);
	public String getCityOrgId(Borg borg);
}
