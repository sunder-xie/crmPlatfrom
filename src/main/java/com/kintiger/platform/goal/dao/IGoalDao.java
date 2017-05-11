package com.kintiger.platform.goal.dao;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.goal.pojo.GoalSales;
import com.kintiger.platform.goal.pojo.GoalSalesDetail;
import com.kintiger.platform.goal.pojo.MatterPriceBO;
import com.kintiger.platform.goal.pojo.MatterPriceObject;
import com.kintiger.platform.goal.pojo.OrgHelps;
import com.kintiger.platform.goal.pojo.PrintContractGoalInfo;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.Materiel;



public interface IGoalDao {

	public int getGoalListCount(BCustomerTarget bct);
	
	public int getGoalListCount1(BCustomerTarget bct);
	
	public int getMatListCount(Materiel mat);
	
	public int getOrgCount(Kunnr kunnr);
	
	public int getKunnrListCount(Kunnr kunnr);
	
	public List<BCustomerTarget> getGoalList(BCustomerTarget bct);
	
	public List<Materiel>getMatList(Materiel mat);
	
	public List<Materiel>getMatList1(Materiel mat);
	
	public BCustomerTarget getGoalById(String ctId);
	
	public List<Kunnr> getKunnrList(Kunnr kunnr);
	
	public List<Kunnr> getKunnrList1(Kunnr kunnr);
	
	public String insertGoal(BCustomerTarget bct);
	
	public int updateGoal(BCustomerTarget bct);
	
	public int approveGoal(BCustomerTarget bct);
	/**
	 * 获取物料listCout
	 * @param mat
	 * @return
	 */
	public int getMaterielListCount(Materiel mat);
	/**
	 * 获取物料list
	 * @param mat
	 * @return
	 */
	public List<Materiel> getMaterielList(Materiel mat);
	/**
	 * 财务批量审核一年的目标量
	 * @param mat
	 * @return
	 */
	public int approveYearGoal(BCustomerTarget bct);
	/**
	 * 查城市下已有的经销商目标量或者组织目标量
	 * @param ctId
	 * @return
	 */
	public BCustomerTarget getGoalMessege(BCustomerTarget target);
	
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
	public int getMaterielListViewCount(Materiel mat);

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
	 * 查城市下财务年分物料的组织待开目标量
	 * 维度：组织、财务年、物料
	 * @param 
	 * @return
	 */
	public BCustomerTarget getGoalMessegeOnYearAndMatter(BCustomerTarget target);
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
	/**
	 * 查询预算物料定价对象
	 * @param object
	 * @return
	 */
	public MatterPriceObject getMatterPriceObject(MatterPriceObject object);
	/**
	 * 新增物料定价
	 * @param object
	 * @return
	 */
    public String createGoalPrice(MatterPriceObject object);
    /**
     * 修改物料定价信息
     * @param object
     * @return
     */
	public int updateGoalPrice(MatterPriceObject object);
	
	/**
	 * 查询预算物料定价(BO报表) 
	 */
	public int searchMatterPriceBoCount(MatterPriceBO priceBo);
	public List<MatterPriceBO> searchMatterPriceBoList(MatterPriceBO priceBo);

	public void insertMarPrice(MatterPriceBO priceBo);

	public List<BCustomerTarget> getGoalReportList(BCustomerTarget bct);

	public BCustomerTarget getGoalContract(BCustomerTarget c);

	public List<BCustomerTarget> getGoalDK(BCustomerTarget bct);

	public int getGoalSumCount(BCustomerTarget bct);

	public List<BCustomerTarget> getGoalDKAll(BCustomerTarget bct);

	public List<BCustomerTarget> getGoalSum(BCustomerTarget bct);

	public BCustomerTarget getGoalContractSum(BCustomerTarget c);
	
	public Long createGoalSalesChange(GoalSales goalSales);
	public Long createGoalSalesChangeDetail(GoalSalesDetail goalSalesDetail);
	public List<Kunnr> searchKunnrList(Kunnr kunnr);
	public int getSkuCount(GoalSalesDetail goalSalesDetail);
	public int searchGoalSalesChangeListCount(GoalSales goalSales);
	public List<GoalSales> searchGoalSalesChangeList(GoalSales goalSales);
	public List<GoalSalesDetail> searchGoalSalesChangeDetailList(GoalSalesDetail goalSalesDetail);
	public int updateGoalSalesChange(GoalSales goalSales);
	public List<AllUsers> getStationIdByUserId(String userId);
	public List<AllUsers> getOrgsByUserId(String userId);
	public Double getSalesGoalCGDetailSum(GoalSalesDetail goalSalesDetail);

	public int getGoalDKCount(BCustomerTarget bct);

	public int getGoalDKAllCount(BCustomerTarget bct);

	public List<PrintContractGoalInfo> searchConGolInfo(PrintContractGoalInfo printConGoalInfo);

	public List<PrintContractGoalInfo> searchConGolInfo2(
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

	public int updateFXGoal(GoalSalesDetail bct);

	public int searchGoalFXChangeListCount(GoalSales goalSales);

	public int updateGoalFXChange(GoalSales goalSales);

	public Object insertFXGoal(BCustomerTarget bct);

	public int searchSkuText(String skuText);

	public int searchSku(String sku);

	public List<BCustomerTarget> getMaktxAndMaknr(Materiel mat);

	public List<PrintContractGoalInfo> searchConGolInfo3(
			PrintContractGoalInfo printConGoalInfo);
}
