package com.kintiger.platform.kunnrBusinessContact.dao;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdjustment;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdujstDetail;

public interface IKunnrBusinessDao {

	/**
	 * 经销商详细信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrBusiness getKunnrBusiness(Kunnr kunnr);
	public int updateKunnrBusiness(KunnrBusiness kunnrBusiness);
	public List<KunnrBusiness> getKunnrBusinessManagerList(KunnrBusiness kunnrBusiness);
	public List<KunnrBusiness> getKunnrBusinessHeadList(KunnrBusiness kunnrBusiness);
	public List<KunnrBusiness> getKunnrBusinessAgentList(KunnrBusiness kunnrBusiness);
	public int updateHead(KunnrBusiness kunnrBusiness);
	public int updateAgent(KunnrBusiness kunnrBusiness);
	public int updateBusinessManager(KunnrBusiness kunnrBusiness);
	public int createHead(KunnrBusiness kunnrBusiness);
	public int createBusinessManager(KunnrBusiness kunnrBusiness);
	public int createAgent(KunnrBusiness kunnrBusiness);
	
	public List<String> getKunnrIdByHeadOrAgent(String userId);
	public List<String> getKunnrIdByCompetent(String userId);
	public List<String> getKunnrIdByKunnrBusiness(String userId);
	public List<String> getKunnrIdByKunnrBusinessByKunag(String userId);
	
	public List<KunnrBusiness> exportForExcel(Kunnr kunnr);
	
	public int searchKunnrBusinessManagerListCount(KunnrBusiness KunnrBusiness);
	
	public List<KunnrBusiness> searchKunnrBusinessManagerList(KunnrBusiness KunnrBusiness);
	
	public int searchKunnrBusinessEmpListCount(KunnrBusiness KunnrBusiness);
	
	public List<KunnrBusiness> searchKunnrBusinessEmpList(KunnrBusiness KunnrBusiness);
	
	public int updateKunnrBusinessEmp(KunnrBusiness KunnrBusiness);
	/**
	 * Title: 统计数据
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月17日 下午12:30:46
	 * @param dealerAdjustment
	 * @return
	 */
	public int getDealerAdjustmentCount(DealerAdjustment dealerAdjustment);
	/**
	 * Title: 获取提报调整事务列表
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月17日 下午12:30:52
	 * @param dealerAdjustment
	 * @return
	 */
	public List<DealerAdjustment> getDealerAdjustmentList(DealerAdjustment dealerAdjustment);
	/**
	 * Title: 验证经销商名称+代码+组织是否保持一致
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月19日 下午4:54:43
	 * @param detail
	 * @return
	 */
	public int getDealerAdjustmentDetailCount(DealerAdujstDetail detail);
	/**
	 * Title: 根据导入的经销商、品项、年月份带出相应的“销售目标量”和“经销商目标量”
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月20日 上午9:48:35
	 * @param detail
	 * @return
	 */
	public List<DealerAdujstDetail> getDealerAdjustmentDetailKunnr(DealerAdujstDetail detail);
	/**
	 * Title: 品项判断
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月20日 上午10:15:31
	 * @param matter
	 * @return
	 */
	public int getMattercount(DealerAdujstDetail detail);
	/**
	 * Title: 经销商提报调整之明细保存
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月23日 上午11:34:56
	 * @param dealer
	 */
	public String createDealerAdjustDetail(DealerAdujstDetail dealer);
	/**
	 * Title: 经销商提报调整之总单保存
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月23日 上午11:34:56
	 * @param dealer
	 */
	public String createDealerAdjustMennt(DealerAdjustment dealerAdjustment);
	/**
	 * Title: 经销商详单之列表
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月24日 上午8:53:23
	 * @param dealerAdujstDetail
	 * @return
	 */
	public List<DealerAdujstDetail> getDealerAdjustDetailList(DealerAdujstDetail dealerAdujstDetail);
	/**
	 * Title: 事务提交成功后保存事务号
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月24日 下午2:37:59
	 * @param dealerAdjustment
	 * @return
	 */
	public int updateDealerAdjustment(DealerAdjustment dealerAdjustment);
	/**
	 * Title: 总单之Id查找
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月29日 下午2:31:04
	 * @param ids
	 * @return
	 */
	public DealerAdjustment getDealerAdjustmentById(String ids);
	/**
	 * Title: 明细导出
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月29日 下午3:20:02
	 * @param dealerAdjustment
	 * @return
	 */
	public List<DealerAdjustment> getDealerAdjustmentListForXls(DealerAdjustment dealerAdjustment);
	public DealerAdjustment getDealerAdjustment(DealerAdjustment dealerAdjustment1);
	public int updateDealerAdjustmentById(DealerAdjustment dealerAdjustment);
	public int updateCrmTbTarget(DealerAdujstDetail detail);
	/**
	 * Title: 获取经销商的目标量明细
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年6月8日 下午3:39:05
	 * @param dealerAdujstDetail
	 * @return
	 */
	public List<DealerAdujstDetail> getKunnrForCrmTarget(DealerAdujstDetail dealerAdujstDetail);
	/**
	 * Title: 找到userId所在组织,包括下级组织
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年7月13日 上午10:01:40
	 * @param userId
	 * @return
	 */
	public List<AllUsers> getOrgsByUserId(String userId);
	/**
	 * MethodsTitle: 根据四级科目获取品牌
	 * @author: xg.chen
	 * @date:2017年1月9日 上午11:41:30
	 * @version 1.0
	 * @param detail
	 * @return
	 */
	public DealerAdujstDetail getMatnrAndMaktx(DealerAdujstDetail detail);

}
