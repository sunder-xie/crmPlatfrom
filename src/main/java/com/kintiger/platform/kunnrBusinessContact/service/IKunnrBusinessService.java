package com.kintiger.platform.kunnrBusinessContact.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdjustment;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdujstDetail;


/**
 * 经销商
 * 
 * @author xxping
 * 
 */
public interface IKunnrBusinessService {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";


	/**
	 * 经销商详细信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrBusiness getKunnrBusiness(Kunnr kunnr);
	public StringResult updateKunnrBusiness(KunnrBusiness kunnrBusiness);
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
	 * Title: 导入的明细表处理
	 * Description: crmPlatform
	 * @author: xg.chen
	 * @date 2016年7月14日 下午3:22:44
	 * @param uploadFile 文件名
	 * @param userId 用户ID
	 * @param orgId 用户组织ID
	 * @param kunnId 所选的经销商ID
	 * @param yearMoth 数据字典控制之日期
	 * @return map
	 */
	public Map<String, Object> importDealerAdjustmentXls(File uploadFile,String userId,String orgId,String kunnId,int yearMoth);
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
	 * Title: 经销商提报调整之总单保存
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月23日 上午11:34:56
	 * @param dealer
	 */
	public String createDealerAdjustMennt(DealerAdjustment dealerAdjustment);
	/**
	 * Title: 经销商提报调整之明细保存
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月23日 上午11:34:56
	 * @param dealer
	 */
	public String createDealerAdjustDetail(DealerAdujstDetail detail);
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
	 * @date 2016年6月8日 下午3:38:32
	 * @param dealerAdujstDetail
	 * @return
	 */
	public List<DealerAdujstDetail> getKunnrForCrmTarget(DealerAdujstDetail dealerAdujstDetail);	
	
}
