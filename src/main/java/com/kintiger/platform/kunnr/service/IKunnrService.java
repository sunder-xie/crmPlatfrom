package com.kintiger.platform.kunnr.service;

import java.io.File;
import java.util.Date;
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
import com.kintiger.platform.kunnr.pojo.KunnrSalesArea;
import com.kintiger.platform.kunnr.pojo.KunnrSapCodeObject;
import com.kintiger.platform.org.pojo.Borg;

/**
 * 经销商
 * 
 * @author xxping
 * 
 */
public interface IKunnrService {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	static final String DB_SUCCESS="数据保存数据库成功.";
	static final String DB_FAIL="数据保存数据库失败.请联系系统管理员!";
	static final String SAP_SUCCESS="数据传输SAP成功.";
	static final String SAP_FAIL="数据传输SAP失败,请重试或联系系统管理员!";



	

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
	public String getRanKunnrCodeNew(KunnrSapCodeObject obj);
	
	/**
	 * 修改经销商sap编号生成表
	 * @param kunnrApply
	 */
	public boolean updateKunnrSapCodeStatus(KunnrSapCodeObject obj);

	/**
	 * 经销商开户附件保存
	 * 
	 * @param business
	 * @param upload
	 * @param uploadFileName
	 * @param key
	 *            文件夾
	 */
	public void saveAttachments(KunnrBusiness business, File[] upload,
			String[] uploadFileName, String key);

	/**
	 * * 经销商开户证照保存
	 * 
	 * @param kunnrLicenseList
	 * @param licenseName
	 * @param license
	 * @param licenseFileName
	 * @param licenseValid
	 */
	public void saveLicenses(List<KunnrLicense> kunnrLicenseList,
			String[] licenseName, File[] license, String[] licenseFileName,
			Date[] licenseValid);

	/**
	 * * 名称变更证明
	 * 
	 * @param kunnrLicenseList
	 * @param licenseName
	 * @param license
	 * @param licenseFileName
	 * @param licenseValid
	 */
	public void saveCustNameFlie(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key);

	/**
	 * 经销商冻结关户附件保存
	 * 
	 * @param kunnr
	 * @param upload
	 * @param uploadFileName
	 * @param key
	 *            文件夾
	 */
	public void saveAttachments(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key);
	/**
	 * 經銷商開戶 入庫
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrOpen(Kunnr kunnr);

	/**
	 * 经销商冻结
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrFreeze(Kunnr kunnr);
	
	/**
	 * 取消sap经销商冻结状态
	 * 
	 * @param kunnr
	 * @return
	 */
	public String kunnrFreezeCallCancelRFC(Kunnr kunnr);

	/**
	 * 经销商关乎
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrClose(Kunnr kunnr);

	/**
	 * 经销商修改
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrUpdate(Kunnr kunnr);

	/**
	 * 
	 * 经销商查询COUNT
	 * 
	 * @return
	 */
	public int kunnrSearchCount(Kunnr kunnr);

	/**
	 * 
	 * 经销列表商查询
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
	 * 物流区域查询
	 * @return
	 */
	public List<KunnrLogisticsArea> getKunnrLogisticsArea(KunnrLogisticsArea area);
	public int getKunnrLogisticsAreaCount(KunnrLogisticsArea area);
	
	
	public BooleanResult updateLogisticArea(List<KunnrLogisticsArea> areaList);
	
	/**
	 * 验证是否是此角色用户
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int getRoleOnEventByUser(String userId,String roleId);
	
	/**
	 * 修改经销商折扣
	 * @param areaList
	 * @return
	 */
	public BooleanResult updateKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr);
	/**
	 * 冻结销售视图
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrFreezeXview(Kunnr kunnr);
	
	/**
	 * 开户占用目标量
	 * @param areaList
	 * @return
	 */
	public BooleanResult createTarget(List<BCustomerTarget> targets,
			Kunnr kunnr);
	
	/**
	 * 扣除开户时占用的目标量
	 * @param areaList
	 * @return
	 */
	public BooleanResult updateKunnrTarget(Kunnr kunnr);
	/**
	 * 经销商开户暂存
	 * @param kunnrApply
	 * @return
	 */
	public BooleanResult kunnrApplySave(KunnrApplySave kunnrApply);
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
	public boolean updateKunnrApplySave(KunnrApplySave kunnrApply);
	/**
	 * 更新经销商状态  status 1：正常，2：冻结账户，3：关户中
	 * @param kunnr
	 * @return
	 */
	public boolean updateKunnrStatus(Kunnr kunnr);
	/**
	 * 经销商雇员编制修改
	 * @param kunnr
	 * @return
	 */
	public boolean updateKunnrUserStaff(Kunnr kunnr);
	/**
	 * 修改资料不全回退发起人流程变量
	 * @author cg.jiang
	 * @param eventId
	 */
	public boolean modifyProcessVariable(String eventId);
	/**
	 * 判断发起人的角色
	 * @author sl.zhu
	 */
	public boolean getOfficeRole(String userId, String roleId);
	/**
	 * 保存销售范围
	 * @author cg.jiang
	 * @param kunnr
	 * @return
	 */
	public boolean createKunnrSalesArea(Kunnr kunnr);
	/**
	 * 传入borg包含组织id,和组织性质,如果返回组织ID,则匹配成功
	 * @param borg
	 * @return
	 */
	public String getCityOrgId(Borg borg);
}
