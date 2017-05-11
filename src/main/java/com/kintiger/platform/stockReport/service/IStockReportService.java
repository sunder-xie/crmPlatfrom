package com.kintiger.platform.stockReport.service;

import java.util.Date;
import java.util.List;

import com.kintiger.platform.order.pojo.Sku;
import com.kintiger.platform.stockReport.pojo.AllUsers;
import com.kintiger.platform.stockReport.pojo.Category;
import com.kintiger.platform.stockReport.pojo.Goal;
import com.kintiger.platform.stockReport.pojo.Kunag;
import com.kintiger.platform.stockReport.pojo.KunnrInType;
import com.kintiger.platform.stockReport.pojo.KunnrManager;
import com.kintiger.platform.stockReport.pojo.OrderCheck;
import com.kintiger.platform.stockReport.pojo.OrderFollow;
import com.kintiger.platform.stockReport.pojo.SkuDistribution;
import com.kintiger.platform.stockReport.pojo.SkuPercent;
import com.kintiger.platform.stockReport.pojo.SkuUnit;
import com.kintiger.platform.stockReport.pojo.Station;
import com.kintiger.platform.stockReport.pojo.StockDate;
import com.kintiger.platform.stockReport.pojo.StockManager;
import com.kintiger.platform.stockReport.pojo.StockReport;

public interface IStockReportService {
	
    public int getStockReportListCount(StockReport stockReport);
	
	public List<StockReport> getStockReportList(StockReport stockReport);

	public int getStockStateManagerCount(StockReport stockReport);
	
	public List<StockReport> getStockStateManagerList(StockReport stockReport);
	
    public List<StockReport> getKunnrByStockIds(StockReport stockReport);
	
	public int getStockReportListCountTotal(StockReport stockReport);
	
	public String getStockReportKunnrAndKunagSum(StockReport stockReport);
	
	public int updateStockReport(StockReport stockReport);
	
	public int createStockReport(StockReport stockReport);
	
    public int getStockReportByCGListCount(StockReport stockReport);
	
	public List<StockReport> getStockReportByCGList(StockReport stockReport);
	
    public int getStockReportBySkuListCount(StockReport stockReport);
	
	public List<StockReport> getStockReportBySkuList(StockReport stockReport);
	
    public int getStockReportBySalesListCount(StockReport stockReport);
	
	public List<StockReport> getStockReportBySalesList(StockReport stockReport);
	
    public int getStockReportSalesByCGListCount(StockReport stockReport);
	
	public List<StockReport> getStockReportSalesByCGList(StockReport stockReport);
	
	public int getSkuCount(Sku sku);
	
	public List<Sku> getSkuNameList(Sku sku);
	
    public int getCategoryCount(Category category);
	
	public List<Category> getCategoryNameList(Category category);
	
	public Long getCategoryBySku(Long skuId);
	
    public List<StockDate> getStockDate(StockDate stockDate);
	
	public int updateStockDate(StockDate stockDate);
	
	public String getLastStockDate(StockDate stockDate);
	
    public List<KunnrInType> getKunnrInType(KunnrInType kunnrInType);
	
	public int updateKunnrInType(KunnrInType kunnrInType);
	
	public int getKunnrInTypeCount(KunnrInType kunnrInType);
	
    public List<SkuPercent> searchSkuPercentList(SkuPercent skuPercent);
    
    public int searchSkuPercentListCount(SkuPercent skuPercent);
	
	public int updateSkuPercent(SkuPercent skuPercent);
	
	public SkuPercent searchSkuPercentSum(SkuPercent skuPercent);
	
	public List<Kunag> createKunag();
	
    public int deleteKunag(Kunag kunag);
	
	public int searchKunagCount(Kunag kunag);
	
	public List<Kunag> searchKunag(Kunag kunag);
	
    public int createSalesGoal(Goal goal);
	
    public int searchSalesGoalCGListCount(Goal goal);
	
	public List<Goal> searchSalesGoalCGList(Goal goal);
	
	public int searchSalesGoalCGDetailListCount(Goal goal);
	
	public String searchSalesGoalCGDetailListSum(Goal goal);
	
	public List<Goal> searchSalesGoalCGDetailList(Goal goal);
	
	public int searchStockCheckCount(StockReport stockReport);
	
	public List<StockReport> searchStockCheck(StockReport stockReport);
	
	public List<StockManager> searchStockManagerList(StockManager stockManager);
	
	public int searchStockManagerCount(StockManager stockManager);
	
    public int searchStockManagerReportCount(StockManager stockManager);
	
	public List<StockManager> searchStockManagerReportList(StockManager stockManager);
		
	public int updateGoalCGDetail(Goal goal);
	
	public Double getGoalCGOrgBoxNumD(Goal goal);
	
    public int updateGoalCGDetailBoxNumDToKunnr(String kunnr, String kunnrD);
	
	public int deleteGoalCGDetail(Goal goal);
	
    public int searchOrderCheckListCount(OrderCheck orderCheck);
	
	public List<OrderCheck> searchOrderCheckList(OrderCheck orderCheck);
	
	public int createOrderCheck(OrderCheck orderCheck);
	
    public int updateOrderCheck(OrderCheck orderCheck);
	
	public List<OrderCheck> getStationIdByUserId(String userId);
	
	public List<String> getRoleIdByUserId(String userId);
	
    public int getStockTotalListCount(StockReport stockReport);
	
	public List<StockReport> getStockTotalList(StockReport stockReport);

	public int searchSalesGoalChallengeListCount(Goal goal);
	
	public List<Goal> searchSalesGoalChallengeList(Goal goal);
	
	public String searchSalesGoalChallengeListSum(Goal goal);
	
	public int updateGoalChallenge(Goal goal);
	
	public int createSalesGoalChallenge(Goal goal);
	
	public int searchKunnrManagerDateCount(StockDate stockDate);
	
    public List<StockDate> searchKunnrManagerDate(StockDate stockDate);
	
	public int updateKunnrManagerDate(StockDate stockDate);
	
	public int searchKunnrManagerListCount(KunnrManager kunnrManager);
	
	public int searchKunnrManagerCount(KunnrManager kunnrManager);
	
	public List<KunnrManager> searchKunnrManagerList(KunnrManager kunnrManager);
	
	public List<KunnrManager> searchKunnrManagerListForExcel(KunnrManager kunnrManager);

	public List<StockReport> getStockListFromSAP(List<StockReport> list);
	
	public int createStockWarning(final List<StockReport> list);
	
	public int modifyStockWarning(final List<StockReport> list);
	
	public int getStockWarningListCount(StockReport stockReport);
	
	public List<StockReport> getStockWarningList(StockReport stockReport);
	
	public List<StockReport> getStockWarningDetailList(StockReport stockReport);
	
	public List<StockReport> getOrderQuantityFromSAP(List<StockReport> list);
	
	public int searchKunnrManagerKunnrSumListCount(KunnrManager kunnrManager);
	
	public List<KunnrManager> searchKunnrManagerKunnrSumList(KunnrManager kunnrManager);
	
    public int searchKunnrManagerSkuListCount(KunnrManager kunnrManager);
	
	public List<KunnrManager> searchKunnrManagerSkuList(KunnrManager kunnrManager);
	
	public int createKunnrManager(KunnrManager kunnrManager);
	
	public void createUnitCoefficientRFC();
	
	public int searchSkuUnitCoefficientListCount(SkuUnit skuUnit);
	
	public List<SkuUnit> searchSkuUnitCoefficientList(SkuUnit skuUnit);
	
	public KunnrManager getKunnrManagerListSum(KunnrManager kunnrManager);
	
	public void createOrderFollowRFC(List<OrderFollow> list);
	
	public int searchOrderFollowListCount(OrderFollow orderFollow);
	
	public List<OrderFollow> searchOrderFollowList(OrderFollow orderFollow);
	
	public OrderFollow getOrderFollowSum(OrderFollow orderFollow);
	
	public int createStockStateManager(final List<StockReport> list);
	
    public int searchStockStateManagerCount(StockReport stockReport);
	
	public List<StockReport> searchStockStateManagerList(StockReport stockReport);
	
	public StockReport searchStockStateManagerTotal(StockReport stockReport);

	public List<StockReport> searchKunnrManagerMessage(List<StockReport> list);
	
	public int createSalesPlanChangeDetail(SkuDistribution skuDistribution);
	
	public int updateSalesPlanChangeDetail(SkuDistribution skuDistribution);
	
	public SkuDistribution getSalesPlanChangeDetail(SkuDistribution skuDistribution);
	
	public List<SkuDistribution> searchSalesPlanChangeDetail(SkuDistribution skuDistribution);
	
	public int searchSalesPlanChangeDetailCount(SkuDistribution skuDistribution);

	public Long getSkuDistributionDetailId();
	
	public List<AllUsers> getEmpListByOrgId(String orgId);
	
	public List<Station> getEmpListByUserName(Station station);
	
	public List<AllUsers> getWorkFlowStation(Long eventId);
	
	public int searchKunnrManagerKunnrByOrgSumListCount(KunnrManager kunnrManager);
	
	public List<KunnrManager> searchKunnrManagerKunnrByOrgSumList(KunnrManager kunnrManager);
	
	public int getOrgCityByOrgId(Long orgId);
	
	public int getCgCount(Sku sku);
	
    public int searchCustomerStockListCount(StockReport stockReport);
	
	public List<StockReport> searchCustomerStockList(StockReport stockReport);
	
	public int createCustomerStock(StockReport stockReport);
	
	public int updateCustomerStock(StockReport stockReport);
	
	public int createOrUpdateCustomerStock(StockReport stockReport);
	
	public void createUnitSapRFC();

	public int searchSkuUnitListCount(SkuUnit skuUnit);
	
	public List<SkuUnit> searchSkuUnitList(SkuUnit skuUnit);

	public List<SkuUnit> searchSkuUnitSap(SkuUnit skuUnit);
	
	public int updateSkuUnit(SkuUnit skuUnit);

	public int searchCustomerStockByDateListCount(StockReport stockReport);

	public List<StockReport> searchCustomerStockByDateList(StockReport stockReport);
	
	public int searchCustomerStockByDateDescListCount(StockReport stockReport);

	public List<StockReport> searchCustomerStockByDateDescList(StockReport stockReport);
	
	public List<String> getKunnrIdByKunag(String kunnr);
	
	public Date getSysdate();
	
	public String getKunnrImportant(String kunnr);
	/**
	 * MethodsTitle: 更新删除操作人
	 * @author: xg.chen
	 * @date:2016年12月27日 下午2:03:06
	 * @version 1.0
	 * @param goal1
	 */
	public void updateOperationUser(Goal goal1);
}

