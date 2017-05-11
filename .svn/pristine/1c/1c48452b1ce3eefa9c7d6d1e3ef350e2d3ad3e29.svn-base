package com.kintiger.platform.goal.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.goal.pojo.GoalSales;
import com.kintiger.platform.goal.pojo.GoalSalesDetail;
import com.kintiger.platform.goal.pojo.MatterPriceBO;
import com.kintiger.platform.goal.pojo.MatterPriceObject;
import com.kintiger.platform.goal.pojo.OrgHelps;
import com.kintiger.platform.goal.pojo.PrintContractGoalInfo;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.Materiel;

public interface IGoalService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "操作失败";

	/**
	 * 查询目标信息 Count
	 * 
	 * @param bct
	 * @return
	 */
	public int getGoalListCount(BCustomerTarget bct);

	public int getGoalListCount1(BCustomerTarget bct);

	public int getMatListCount(Materiel mat);

	public int getOrgCount(Kunnr kunnr);

	/**
	 * 查询目标信息
	 * 
	 * @param bct
	 * @return
	 */
	public List<BCustomerTarget> getGoalList(BCustomerTarget bct);

	public List<Materiel> getMatList(Materiel mat);

	public List<Materiel> getMatList1(Materiel mat);

	public BCustomerTarget getGoalById(String ctId);

	/**
	 * 查询客户信息 Count
	 * 
	 * @param kunnr
	 * @return
	 */
	public int getKunnrListCount(Kunnr kunnr);

	/**
	 * 查询客户信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<Kunnr> getKunnrsList(Kunnr kunnr);

	/**
	 * 查询客户信息
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<Kunnr> getKunnrsList1(Kunnr kunnr);

	/**
	 * 插入目标表
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult insertGoal(final BCustomerTarget bct);

	public boolean insertGoal1(final BCustomerTarget bct);

	public boolean updateGoal(final BCustomerTarget bct);

	public StringResult approveGoal(final BCustomerTarget bct);

	public StringResult insertBct(final List<BCustomerTarget> bctList);

	/**
	 * 获取物料listCout
	 * 
	 * @param mat
	 * @return
	 */
	public int getMaterielListCount(Materiel mat);

	/**
	 * 获取物料list
	 * 
	 * @param mat
	 * @return
	 */
	public List<Materiel> getMaterielList(Materiel mat);
	/**
	 * 财务批量审核一年的目标量
	 * @param mat
	 * @return
	 */
	public StringResult approveYearGoal(final BCustomerTarget bct);
	/**
	 * 查城市下已有的经销商目标量或者组织目标量：组织待开目标量
	 * 维度：组织、年、月、物料
	 * @param ctId
	 * @return
	 */
	public BCustomerTarget getGoalMessege(BCustomerTarget target);
	
	/**
	 * 查城市下财务年分物料的组织待开目标量
	 * 维度：组织、财务年、物料
	 * @param ctId
	 * @return
	 */
	public BCustomerTarget getGoalMessegeOnYearAndMatter(BCustomerTarget target);
	
	/**
	 * 查目标量
	 * @param ctId
	 * @return
	 */
	public List<BCustomerTarget> getGoalMessegeByALL(BCustomerTarget target);
	
	/**
	 * 验证待开count
	 * @param target
	 * @return
	 */
	public int getKunnrGoalCount(BCustomerTarget target);
	/**
	 * 验证待开
	 * @param target
	 * @return
	 */
	public List<BCustomerTarget> getKunnrGoalAll(BCustomerTarget target);
	
	/**
	 * 判断csv批量导入的组织是否存在于系统
	 * @param kunnr
	 * @return
	 */
	public List<OrgHelps> getOrgIsExit(Kunnr kunnr);
	
	/**
	 * 验证用户权限点
	 * @return
	 */
	public int getConpointByUser(String userId,String conpointId);
	

	/**
	 * 获取物料及价格listCout
	 * 
	 * @param mat
	 * @return
	 */
	public int getMaterielViewListCount(Materiel mat);

	/**
	 * 获取物料及价格list
	 * 
	 * @param mat
	 * @return
	 */
	public List<Materiel> getMaterielViewList(Materiel mat);
	
	/**
	 * 根据传进来的维度查目标量信息
	 * @param target
	 * @return
	 */
	public BCustomerTarget getGoalByCondition(BCustomerTarget target);
	
	/**
	 * 目标量合计
	 * @return
	 */
	public double getGoalCountNum(BCustomerTarget target);
	/**
	 * 物理删除开户时提报的经销商占用目标量
	 * @param target
	 * @return
	 */
	public int deleteKunnrGoalAppply(BCustomerTarget target);
	
	/**
	 * 查询预算物料定价
	 * @param object
	 * @return
	 */
	public int searchMatterPriceCount(MatterPriceObject object);
	public List<MatterPriceObject> searchMatterPriceList(MatterPriceObject object);
	
	public MatterPriceObject getMatterPriceObject(MatterPriceObject object);
	
	public StringResult createOrUpdateGoal(MatterPriceObject object);

	/**
	 * 查询预算物料定价(BO报表) 
	 */
	public int searchMatterPriceBoCount(MatterPriceBO priceBo);

	public List<MatterPriceBO> searchMatterPriceBoList(MatterPriceBO priceBo);
	/**
	 * 保存导入的文件
	 * @param 
	 * @return
	 */

	public void insertMarPrice(MatterPriceBO priceBo);

	public List<BCustomerTarget> getGoalReportList(BCustomerTarget bct);

	public BCustomerTarget getGoalContract(BCustomerTarget c);

	public List<BCustomerTarget> getGoalDK(BCustomerTarget c);

	public int getGoalSumCount(BCustomerTarget bct);

	public List<BCustomerTarget> getGoalDKAll(BCustomerTarget bct);

	public List<BCustomerTarget> getGoalSum(BCustomerTarget bct);

	public BCustomerTarget getGoalContractSum(BCustomerTarget c);
	
	public Map<String,Object> importGoalSalesXls(File fileContent,String userId);
	public List<AllUsers> getStationIdByUserId(String userId);
	public Long createGoalSalesChange(GoalSales goalSales);
	public int searchGoalSalesChangeListCount(GoalSales goalSales);
	public List<GoalSales> searchGoalSalesChangeList(GoalSales goalSales);
	public List<GoalSalesDetail> searchGoalSalesChangeDetailList(GoalSalesDetail goalSalesDetail);
	public Double getSalesGoalCGDetailSum(GoalSalesDetail goalSalesDetail);
	public int updateGoalSalesChange(GoalSales goalSales);
	public Long createGoalSalesChangeDetail(GoalSalesDetail goalSalesDetail);
	public int getGoalDKCount(BCustomerTarget bct);
	public int getGoalDKAllCount(BCustomerTarget bct);
	/**
	 * 香飘飘协议目标量打印第一页
	 * @param printConGoalInfo
	 * @return
	 */
	public List<PrintContractGoalInfo> searchConGolInfo(PrintContractGoalInfo printConGoalInfo);
	/**
	 * 香飘飘协议目标量打印第二页
	 * @param printConGoalInfo
	 * @return
	 */
	public List<PrintContractGoalInfo> searchConGolInfo2(
			PrintContractGoalInfo printConGoalInfo);
	/**
	 * MECO兰芳园协议目标量打印第一页
	 * @param printConGoalInfo
	 * @return
	 */
	public List<PrintContractGoalInfo> searchConGolInfo3(
			PrintContractGoalInfo printConGoalInfo);
	public int getKunnrListForNormalCount(Kunnr kunnr);
	public List<Kunnr> getKunnrListForNormal(Kunnr kunnr);

	public int getGoalListCountForMBL(BCustomerTarget bct);

	public List<BCustomerTarget> getGoalReportListForMBL(BCustomerTarget bct);

	public int getDistributionGoalTotal(BCustomerTarget bct);

	public List<BCustomerTarget> getDistributionGoalList(BCustomerTarget bct);

	public Double getSalesGoalFXDetailSum(GoalSalesDetail gsd);

	public Long createGoalDistributionChange(GoalSales goalSales);

	public Long createGoalFXChangeDetail(GoalSalesDetail gsdTo);

	public List<GoalSales> searchGoalFXChangeList(GoalSales goalSales);

	public List<GoalSalesDetail> searchGoalFXChangeDetailList(
			GoalSalesDetail goalSalesDetail);

	public boolean updateFXGoal(GoalSalesDetail bct);

	public int searchGoalFXChangeListCount(GoalSales goalSales);

	public int updateGoalFXChange(GoalSales goalSales);

	public boolean insertFXGoal(BCustomerTarget bt);

	public int searchSku(String sku);

	public int searchSkuText(String skuText);

	public List<BCustomerTarget> getMaktxAndMaknr(Materiel mat);

}
