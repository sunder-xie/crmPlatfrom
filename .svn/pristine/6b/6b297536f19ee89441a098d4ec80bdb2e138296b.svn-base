package com.kintiger.platform.stockReport.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.order.pojo.Sku;
import com.kintiger.platform.stockReport.dao.IStockReportDao;
import com.kintiger.platform.stockReport.pojo.AllUsers;
import com.kintiger.platform.stockReport.pojo.Category;
import com.kintiger.platform.stockReport.pojo.Goal;
import com.kintiger.platform.stockReport.pojo.Kunag;
import com.kintiger.platform.stockReport.pojo.KunnrInType;
import com.kintiger.platform.stockReport.pojo.KunnrManager;
import com.kintiger.platform.stockReport.pojo.OrderCheck;
import com.kintiger.platform.stockReport.pojo.OrderFollow;
import com.kintiger.platform.stockReport.pojo.Pod;
import com.kintiger.platform.stockReport.pojo.SkuDistribution;
import com.kintiger.platform.stockReport.pojo.SkuPercent;
import com.kintiger.platform.stockReport.pojo.SkuUnit;
import com.kintiger.platform.stockReport.pojo.Station;
import com.kintiger.platform.stockReport.pojo.StockDate;
import com.kintiger.platform.stockReport.pojo.StockManager;
import com.kintiger.platform.stockReport.pojo.StockManagerSap;
import com.kintiger.platform.stockReport.pojo.StockReport;

@SuppressWarnings("rawtypes")
public class StockReportDaoImpl extends BaseDaoImpl implements IStockReportDao {
	
    public int getStockReportListCount(StockReport stockReport){
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("stockReport.getStockReportListCount", stockReport);
    }
	
	@SuppressWarnings("unchecked")
	public List<StockReport> getStockReportList(StockReport stockReport){
		return (List<StockReport>)this.getSqlMapClientTemplate().queryForList("stockReport.getStockReportList", stockReport);
	}

    public int getStockStateManagerCount(StockReport stockReport){
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("stockReport.getStockStateManagerCount", stockReport);
    }
	
	@SuppressWarnings("unchecked")
	public List<StockReport> getStockStateManagerList(StockReport stockReport){
		return (List<StockReport>)this.getSqlMapClientTemplate().queryForList("stockReport.getStockStateManagerList", stockReport);
	}
    @SuppressWarnings("unchecked")
	public List<StockReport> getKunnrByStockIds(StockReport stockReport){
		return (List<StockReport>)this.getSqlMapClientTemplate().queryForList("stockReport.getKunnrByStockIds", stockReport);
    }
	
	public int getStockReportListCountTotal(StockReport stockReport){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.getStockReportListCountTotal", stockReport);
	}
	
	public String getStockReportKunnrAndKunagSum(StockReport stockReport){
		return (String) getSqlMapClientTemplate().queryForObject("stockReport.getStockReportKunnrAndKunagSum", stockReport);
	}
	
	public int updateStockReport(StockReport stockReport){
		return (Integer) getSqlMapClientTemplate().update("stockReport.updateStockReport", stockReport);
	}
	
	public int updateStockReportTotal(StockReport stockReport){
		return (Integer) getSqlMapClientTemplate().update("stockReport.updateStockReportTotal", stockReport);
	}
	
	public int createStockReport(StockReport stockReport){
		return (Integer)getSqlMapClientTemplate().insert("stockReport.createStockReport", stockReport);
	}
	
	public int createStockReportTotal(StockReport stockReport){
		getSqlMapClientTemplate().insert("stockReport.createStockReportTotal", stockReport);
		return 1;
	}
	
    public int getStockReportByCGListCount(StockReport stockReport){
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("stockReport.getStockReportByCGListCount", stockReport);
    }
	
	@SuppressWarnings("unchecked")
	public List<StockReport> getStockReportByCGList(StockReport stockReport){
		return (List<StockReport>)this.getSqlMapClientTemplate().queryForList("stockReport.getStockReportByCGList", stockReport);
	}
	
    public int getStockReportBySkuListCount(StockReport stockReport){
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("stockReport.getStockReportBySkuListCount", stockReport);
    }
	
	@SuppressWarnings("unchecked")
	public List<StockReport> getStockReportBySkuList(StockReport stockReport){
		return (List<StockReport>)this.getSqlMapClientTemplate().queryForList("stockReport.getStockReportBySkuList", stockReport);
	}
	
    public int getStockReportBySalesListCount(StockReport stockReport){
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("stockReport.getStockReportBySalesListCount", stockReport);
    }
	
	@SuppressWarnings("unchecked")
	public List<StockReport> getStockReportBySalesList(StockReport stockReport){
		return (List<StockReport>)this.getSqlMapClientTemplate().queryForList("stockReport.getStockReportBySalesList", stockReport);
	}
	
    public int getCategoryCount(Category category){
    	return (Integer)this.getSqlMapClientTemplate().queryForObject("stockReport.getCategoryCount", category);
    }
	
	@SuppressWarnings("unchecked")
	public List<Category> getCategoryNameList(Category category){
		return (List<Category>) getSqlMapClientTemplate().queryForList("stockReport.getCategoryNameList", category);
	}
	
	@Override
	public int getSkuCount(Sku sku) {
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.getSkuCount",sku);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sku> getSkuNameList(Sku sku) {
		return (List<Sku>) getSqlMapClientTemplate().queryForList("stockReport.getSkuNameList", sku);
	}
	
	public Long getCategoryBySku(Long skuId){
		return (Long) getSqlMapClientTemplate().queryForObject("stockReport.getCategoryBySku",skuId);
	}
	
    @SuppressWarnings("unchecked")
	public List<StockDate> getStockDate(StockDate stockDate){
    	return (List<StockDate>) getSqlMapClientTemplate().queryForList("stockReport.getStockDate", stockDate);
    }
	
	public int updateStockDate(StockDate stockDate){
		return (Integer) getSqlMapClientTemplate().update("stockReport.updateStockDate", stockDate);
	}
	
	public String getLastStockDate(StockDate stockDate){
		return (String) getSqlMapClientTemplate().queryForObject("stockReport.getLastStockDate", stockDate);
	}
	
    @SuppressWarnings("unchecked")
	public List<KunnrInType> getKunnrInType(KunnrInType kunnrInType){
    	return (List<KunnrInType>) getSqlMapClientTemplate().queryForList("stockReport.getKunnrInType", kunnrInType);
    }
	
	public int updateKunnrInType(KunnrInType kunnrInType){
		return (Integer) getSqlMapClientTemplate().update("stockReport.updateKunnrInType", kunnrInType);
	}
	
	public int createKunnrInType(KunnrInType kunnrInType){
		getSqlMapClientTemplate().insert("stockReport.createKunnrInType", kunnrInType);
		return 1;
	}
	
	public int getKunnrInTypeCount(KunnrInType kunnrInType){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.getKunnrInTypeCount", kunnrInType);
	}
	
	public int getKunnrInTypeTmpCount(KunnrInType kunnrInType){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.getKunnrInTypeTmpCount", kunnrInType);
	}
	
    @SuppressWarnings("unchecked")
	public List<SkuPercent> searchSkuPercentList(SkuPercent skuPercent){
    	return (List<SkuPercent>) getSqlMapClientTemplate().queryForList("stockReport.searchSkuPercentList", skuPercent);
    }
    
    public int searchSkuPercentListCount(SkuPercent skuPercent){
    	return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchSkuPercentListCount", skuPercent);
    }
	
	public int updateSkuPercent(SkuPercent skuPercent){
		return (Integer) getSqlMapClientTemplate().update("stockReport.updateSkuPercent", skuPercent);
	}
	
	public SkuPercent searchSkuPercentSum(SkuPercent skuPercent){
		return (SkuPercent) getSqlMapClientTemplate().queryForObject("stockReport.searchSkuPercentSum", skuPercent);
	}
	
	public int createKunag(Kunag kunag){
		getSqlMapClientTemplate().insert("stockReport.createKunag", kunag);
		return 1;
	}
	
    public int deleteKunag(Kunag kunag){
    	return (Integer) getSqlMapClientTemplate().delete("stockReport.deleteKunag");
    }
	
	public int searchKunagCount(Kunag kunag){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchKunagCount", kunag);
	}
	
	@SuppressWarnings("unchecked")
	public List<Kunag> searchKunag(Kunag kunag){
		return (List<Kunag>) getSqlMapClientTemplate().queryForList("stockReport.searchKunag", kunag);
	}
	
    public int createSalesGoal(Goal goal){
    	getSqlMapClientTemplate().insert("stockReport.createSalesGoal", goal);
    	return 0;
    }
	
	public int searchSalesGoalCGListCount(Goal goal){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchSalesGoalCGListCount", goal);
	}
	
	@SuppressWarnings("unchecked")
	public List<Goal> searchSalesGoalCGList(Goal goal){
		return (List<Goal>) getSqlMapClientTemplate().queryForList("stockReport.searchSalesGoalCGList", goal);
	}
	
    public int searchSalesGoalCGDetailListCount(Goal goal){
    	return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchSalesGoalCGDetailListCount", goal);
    }
    
    public String searchSalesGoalCGDetailListSum(Goal goal){
    	return (String) getSqlMapClientTemplate().queryForObject("stockReport.searchSalesGoalCGDetailListSum", goal);
    }
	
	@SuppressWarnings("unchecked")
	public List<Goal> searchSalesGoalCGDetailList(Goal goal){
		return (List<Goal>) getSqlMapClientTemplate().queryForList("stockReport.searchSalesGoalCGDetailList", goal);
	}
	
	public Integer getLastStockByCG(StockReport stockReport){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.getLastStockByCG", stockReport);
	}
	
	public String getLastCheckTime(StockReport stockReport){
		return (String) getSqlMapClientTemplate().queryForObject("stockReport.getLastCheckTime", stockReport);
	}
	
	public Integer getStockManagerSapListCount(StockManagerSap stockManagerSap){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.getStockManagerSapListCount", stockManagerSap);
	}
	
	@SuppressWarnings("unchecked")
	public List<StockManagerSap> getStockManagerSapList(StockManagerSap stockManagerSap){
		return (List<StockManagerSap>) getSqlMapClientTemplate().queryForList("stockReport.getStockManagerSapList", stockManagerSap);
	}
	
	public StockManagerSap getStockManagerSap(StockManagerSap stockManagerSap){
		return (StockManagerSap) getSqlMapClientTemplate().queryForObject("stockReport.getStockManagerSap", stockManagerSap);
	}
	
	public Integer createStockManagerSap(StockManagerSap kunnrManagerSap){
//		SqlMapClient smc = this.getSqlMapClient();
//		   smc.startBatch();
//		  for(int i=0;i<list.size();i++) { 
//		              this.insert("addMessage",(Message)list.get(i)); 
//		        } 
//		   temp = smc.executeBatch();
		return (Integer) getSqlMapClientTemplate().insert("stockReport.createStockManagerSap", kunnrManagerSap);
	}
	
    public Integer searchPodListCount(Pod pod){
    	return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchPodListCount", pod);
    }
	
	public void createPod(Pod pod){
		getSqlMapClientTemplate().insert("stockReport.createPod", pod);
	}

    public int getStockReportSalesByCGListCount(StockReport stockReport){
    	return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.getStockReportSalesByCGListCount", stockReport);
    }
	
	@SuppressWarnings("unchecked")
	public List<StockReport> getStockReportSalesByCGList(StockReport stockReport){
		return (List<StockReport>)getSqlMapClientTemplate().queryForList("stockReport.getStockReportSalesByCGList", stockReport);
	}
	

	public int searchStockCheckCount(StockReport stockReport){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchStockCheckCount", stockReport);
	}
	
	@SuppressWarnings("unchecked")
	public List<StockReport> searchStockCheck(StockReport stockReport){
		return (List<StockReport>)getSqlMapClientTemplate().queryForList("stockReport.searchStockCheck", stockReport);
	}
	
	public int searchStockManagerCount(StockManager stockManager){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchStockManagerCount", stockManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<StockManager> searchStockManager(StockManager stockManager){
		return (List<StockManager>)getSqlMapClientTemplate().queryForList("stockReport.searchStockManager", stockManager);
	}
	
    public int searchStockManagerReportCount(StockManager stockManager){
    	return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchStockManagerReportCount", stockManager);
    }
	
	@SuppressWarnings("unchecked")
	public List<StockManager> searchStockManagerReport(StockManager stockManager){
		return (List<StockManager>)getSqlMapClientTemplate().queryForList("stockReport.searchStockManagerReport", stockManager);
	}
	
	public Double searchGoalCGByMatterNum(StockManager stockManager){
		return (Double) getSqlMapClientTemplate().queryForObject("stockReport.searchGoalCGByMatterNum", stockManager);
	}
	
	public Double searchGoalCGByKunnr(StockManager stockManager){
		return (Double) getSqlMapClientTemplate().queryForObject("stockReport.searchGoalCGByKunnr", stockManager);
	}
	
	public Double getGoalCGOrgBoxNumD(Goal goal){
		return (Double) getSqlMapClientTemplate().queryForObject("stockReport.getGoalCGOrgBoxNumD", goal);
	}
	
	public int updateGoalCGDetail(Goal goal){
		return getSqlMapClientTemplate().update("stockReport.updateGoalCGDetail", goal);
	}
	
    public int updateGoalCGDetailBoxNumDToKunnr(String kunnr, String kunnrD){
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("kunnr", kunnr);
    	map.put("kunnrD", kunnrD);
    	return getSqlMapClientTemplate().update("stockReport.updateGoalCGDetailBoxNumDToKunnr", map);
    }
	
	public int deleteGoalCGDetail(Goal goal){
		return getSqlMapClientTemplate().delete("stockReport.deleteGoalCGDetail", goal);
	}
	
    public int searchOrderCheckListCount(OrderCheck orderCheck){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchOrderCheckListCount", orderCheck);
    }
	
	@SuppressWarnings("unchecked")
	public List<OrderCheck> searchOrderCheckList(OrderCheck orderCheck){
		return (List<OrderCheck>) getSqlMapClientTemplate().queryForList("stockReport.searchOrderCheckList", orderCheck);
	}
	
	public int createOrderCheck(OrderCheck orderCheck){
		getSqlMapClientTemplate().insert("stockReport.createOrderCheck", orderCheck);
		return 1;
	}
	
    public int updateOrderCheck(OrderCheck orderCheck){
    	return getSqlMapClientTemplate().update("stockReport.updateOrderCheck", orderCheck);
    }
	
	@SuppressWarnings("unchecked")
	public List<OrderCheck> getStationIdByUserId(String userId){
		return (List<OrderCheck>) getSqlMapClientTemplate().queryForList("stockReport.getStationIdByUserId", userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getRoleIdByUserId(String userId){
		return (List<String>) getSqlMapClientTemplate().queryForList("stockReport.getRoleIdByUserId", userId);
	}
	
    public int getStockTotalListCount(StockReport stockReport){
    	return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.getStockTotalListCount", stockReport);
    }
	
	@SuppressWarnings("unchecked")
	public List<StockReport> getStockTotalList(StockReport stockReport){
		return (List<StockReport>) getSqlMapClientTemplate().queryForList("stockReport.getStockTotalList", stockReport);
	}

	public int searchSalesGoalChallengeListCount(Goal goal){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchSalesGoalChallengeListCount", goal);
	}
	
	@SuppressWarnings("unchecked")
	public List<Goal> searchSalesGoalChallengeList(Goal goal){
		return (List<Goal>) getSqlMapClientTemplate().queryForList("stockReport.searchSalesGoalChallengeList", goal);
	}
	
	public String searchSalesGoalChallengeListSum(Goal goal){
		return (String) getSqlMapClientTemplate().queryForObject("stockReport.searchSalesGoalChallengeListSum", goal);
	}
	
	public int updateGoalChallenge(Goal goal){
		return getSqlMapClientTemplate().update("stockReport.updateGoalChallenge", goal);
	}
	

	public int createSalesGoalChallenge(Goal goal){
		getSqlMapClientTemplate().insert("stockReport.createSalesGoalChallenge", goal);
		return 1;
	}
	
	public int createStockWarning(final List<StockReport> list){
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (StockReport stockReport : list) {
					executor.insert("stockReport.createStockWarning", stockReport);
				}
				executor.executeBatch();
				return null;
			}
		});
		return 1;
	}
	public int modifyStockWarning(final List<StockReport> list){
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (StockReport stockReport : list) {
					executor.update("stockReport.modifyStockWarning", stockReport);
				}
				executor.executeBatch();
				return null;
			}
		});
		return 1;
	}
	public int getStockWarningListCount(StockReport stockReport){
    	return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.getStockWarningListCount", stockReport);
    }
	
	@SuppressWarnings("unchecked")
	public List<StockReport> getStockWarningList(StockReport stockReport){
		return (List<StockReport>) getSqlMapClientTemplate().queryForList("stockReport.getStockWarningList", stockReport);
	}
	
	@SuppressWarnings("unchecked")
	public List<StockReport> getStockWarningDetailList(StockReport stockReport){
		return (List<StockReport>) getSqlMapClientTemplate().queryForList("stockReport.getStockWarningDetailList", stockReport);
	}
	
    @SuppressWarnings("unchecked")
	public List<StockDate> searchKunnrManagerDate(StockDate stockDate){
		return getSqlMapClientTemplate().queryForList("stockReport.searchKunnrManagerDate", stockDate);
    }
    
    public int searchKunnrManagerDateCount(StockDate stockDate){
		return (Integer)getSqlMapClientTemplate().queryForObject("stockReport.searchKunnrManagerDateCount", stockDate);
    }
	
	public int updateKunnrManagerDate(StockDate stockDate){
		return getSqlMapClientTemplate().update("stockReport.updateKunnrManagerDate", stockDate);
	}

	public int searchKunnrManagerListCount(KunnrManager kunnrManager){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchKunnrManagerListCount", kunnrManager);
	}
	
	public int searchKunnrManagerCount(KunnrManager kunnrManager){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchKunnrManagerCount", kunnrManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<KunnrManager> searchKunnrManagerList(KunnrManager kunnrManager){
		return (List<KunnrManager>) getSqlMapClientTemplate().queryForList("stockReport.searchKunnrManagerList", kunnrManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<KunnrManager> searchKunnrManagerListForExcel(KunnrManager kunnrManager){
		return (List<KunnrManager>) getSqlMapClientTemplate().queryForList("stockReport.searchKunnrManagerListForExcel", kunnrManager);
	}

	public int searchKunnrManagerKunnrSumListCount(KunnrManager kunnrManager){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchKunnrManagerKunnrSumListCount", kunnrManager);
	}
	
	@SuppressWarnings("unchecked")
	public List<KunnrManager> searchKunnrManagerKunnrSumList(KunnrManager kunnrManager){
		return (List<KunnrManager>) getSqlMapClientTemplate().queryForList("stockReport.searchKunnrManagerKunnrSumList", kunnrManager);
	}

    public int searchKunnrManagerSkuListCount(KunnrManager kunnrManager){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchKunnrManagerSkuListCount", kunnrManager);
    }
	
	@SuppressWarnings("unchecked")
	public List<KunnrManager> searchKunnrManagerSkuList(KunnrManager kunnrManager){
		return (List<KunnrManager>) getSqlMapClientTemplate().queryForList("stockReport.searchKunnrManagerSkuList", kunnrManager);
	}
	
	public int createKunnrManager(KunnrManager kunnrManager){
		getSqlMapClientTemplate().insert("stockReport.createKunnrManager", kunnrManager);
		return 1;
	}
	
	public int updateKunnrManager(KunnrManager kunnrManager){
		return getSqlMapClientTemplate().update("stockReport.updateKunnrManager", kunnrManager);
	}

	public int getKunnrManagerCount(KunnrManager kunnrManager){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.getKunnrManagerCount", kunnrManager);
	}
	
	public int createSkuUnitCoefficient(SkuUnit skuUnit){
		getSqlMapClientTemplate().insert("stockReport.createSkuUnitCoefficient", skuUnit);
		return 1;
	}
	
	public int deleteSkuUnitCoefficient(){
		return getSqlMapClientTemplate().delete("stockReport.deleteSkuUnitCoefficient");
	}

	public int searchSkuUnitCoefficientListCount(SkuUnit skuUnit){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchSkuUnitCoefficientListCount", skuUnit);
	}
	
	@SuppressWarnings("unchecked")
	public List<SkuUnit> searchSkuUnitCoefficientList(SkuUnit skuUnit){
		return (List<SkuUnit>) getSqlMapClientTemplate().queryForList("stockReport.searchSkuUnitCoefficientList", skuUnit);
	}
	
	public List<StockReport> searchFactQuantity(List<StockReport> list){
		String str="0000000000";
		for(int i=0;i<list.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			String expKunnr=str.substring(list.get(i).getKunnr().length())+list.get(i).getKunnr();
	    	map.put("kunnr", expKunnr);
	    	map.put("skuId", list.get(i).getSkuId().toString());
	    	map.put("year", list.get(i).getSearchYear().toString());
	    	map.put("month", list.get(i).getSearchMonth().toString());
			StockReport ss=(StockReport)getSqlMapClientTemplate().queryForObject("stockReport.searchFactQuantity", map);
			if(ss!=null&&list.get(i).getCoefficient()!=null&&!(list.get(i).getCoefficient()==0)){
				//System.out.println(ss.getQuantity()+"--------"+(list.get(i).getCoefficient()));
				list.get(i).setQuantity(ss.getQuantity()/(list.get(i).getCoefficient()));
			}else{
				list.get(i).setQuantity(0.0);
			}
		}
		return list;
	}

	public KunnrManager getKunnrManagerListSum(KunnrManager kunnrManager){
		return (KunnrManager) getSqlMapClientTemplate().queryForObject("stockReport.getKunnrManagerListSum", kunnrManager);
	}
	
	public int searchOrderFollowListCount(OrderFollow orderFollow){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchOrderFollowListCount", orderFollow);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderFollow> searchOrderFollowList(OrderFollow orderFollow){
		return (List<OrderFollow>) getSqlMapClientTemplate().queryForList("stockReport.searchOrderFollowList", orderFollow);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderFollow> searchOrderStatusSapCount(OrderFollow orderFollow){
		return (List<OrderFollow>) getSqlMapClientTemplate().queryForList("stockReport.searchOrderStatusSapCount", orderFollow);
	}
	
	public int createOrderStatusSap(OrderFollow orderFollow){
		getSqlMapClientTemplate().insert("stockReport.createOrderStatusSap", orderFollow);
		return 1;
	}
	
	public int deleteOrderStatusSap(OrderFollow orderFollow){
		return getSqlMapClientTemplate().delete("stockReport.deleteOrderStatusSap", orderFollow);
	}
	
	public OrderFollow getOrderFollowSum(OrderFollow orderFollow){
		return (OrderFollow) getSqlMapClientTemplate().queryForObject("stockReport.getOrderFollowSum", orderFollow);
	}
	
	public int createStockStateManager(final List<StockReport> list){
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				executor.delete("stockReport.deleteStockStateManager");
				for (StockReport stockReport : list) {
					executor.insert("stockReport.createStockStateManager", stockReport);
				}
				executor.executeBatch();
				return null;
			}
		});
		return 1;
	}

	public int searchStockStateManagerCount(StockReport stockReport){
		return (Integer) getSqlMapClientTemplate().queryForObject("stockReport.searchStockStateManagerCount", stockReport);
	}
	
	@SuppressWarnings("unchecked")
	public List<StockReport> searchStockStateManagerList(StockReport stockReport){
		return (List<StockReport>) getSqlMapClientTemplate().queryForList("stockReport.searchStockStateManagerList", stockReport);
	}

	public StockReport searchStockStateManagerTotal(StockReport stockReport){
		return (StockReport) getSqlMapClientTemplate().queryForObject("stockReport.searchStockStateManagerTotal", stockReport);
	}
	@SuppressWarnings("unchecked")
	public List<StockReport> searchKunnrManagerMessage(List<StockReport> list){
		for(StockReport s:list){
			List<StockReport> list1=(List<StockReport>) getSqlMapClientTemplate().queryForList("stockReport.searchBusinessManager", s);
			if(list1.size()>0){
				s.setBusinessManager(list1.get(0).getBusinessManager());
				s.setManagerMobile(list1.get(0).getManagerMobile());
			}
		}
		for(StockReport s:list){
			List<StockReport> list2=(List<StockReport>) getSqlMapClientTemplate().queryForList("stockReport.searchProvinceManager", s);
			if(list2.size()>0){
				s.setProvinceManager(list2.get(0).getProvinceManager());
				s.setProvinceMobile(list2.get(0).getProvinceMobile());
			}
		}
		return list;
	}
	

	public KunnrManager getStockSafetyByKunnr(KunnrManager kunnrManager){
		return (KunnrManager) getSqlMapClientTemplate().queryForObject("stockReport.getStockSafetyByKunnr", kunnrManager);
	}
	
	public Double getHanaDistributionSku(KunnrManager kunnrManager){
		return (Double) getSqlMapClientTemplate().queryForObject("stockReport.getHanaDistributionSku", kunnrManager);
	}
	
	public KunnrManager getKunnrManager(KunnrManager kunnrManager){
		return (KunnrManager) getSqlMapClientTemplate().queryForObject("stockReport.getKunnrManager", kunnrManager);
	}

	public Double getStockSafetySum(KunnrManager kunnrManager){
		return (Double) getSqlMapClientTemplate().queryForObject("stockReport.getStockSafetySum", kunnrManager);
	}
	
	public Double getHanaDistributionSkuSum(KunnrManager kunnrManager){
		return (Double) getSqlMapClientTemplate().queryForObject("stockReport.getHanaDistributionSkuSum", kunnrManager);
	}
	
	public KunnrManager getKunnrManagerSum(KunnrManager kunnrManager){
		return (KunnrManager) getSqlMapClientTemplate().queryForObject("stockReport.getKunnrManagerSum", kunnrManager);
	}

	public int createSalesPlanChangeDetail(SkuDistribution skuDistribution){
		getSqlMapClientTemplate().insert("stockReport.createSalesPlanChangeDetail", skuDistribution);
		return 1;
	}
	
	public int updateSalesPlanChangeDetail(SkuDistribution skuDistribution){
		return getSqlMapClientTemplate().update("stockReport.updateSalesPlanChangeDetail", skuDistribution);
	}
	
	public SkuDistribution getSalesPlanChangeDetail(SkuDistribution skuDistribution){
		return (SkuDistribution)getSqlMapClientTemplate().queryForObject("stockReport.getSalesPlanChangeDetail", skuDistribution);
	}
	
	public int searchSalesPlanChangeDetailCount(SkuDistribution skuDistribution){
		return (Integer)getSqlMapClientTemplate().queryForObject("stockReport.searchSalesPlanChangeDetailCount", skuDistribution);
	}
	
	@SuppressWarnings("unchecked")
	public List<SkuDistribution> searchSalesPlanChangeDetail(SkuDistribution skuDistribution){
		return (List<SkuDistribution>)getSqlMapClientTemplate().queryForList("stockReport.searchSalesPlanChangeDetail", skuDistribution);
	}

	public Long getSkuDistributionDetailId(){
		return (Long)getSqlMapClientTemplate().queryForObject("stockReport.getSkuDistributionDetailId");
	}
	
	@SuppressWarnings("unchecked")
	public List<AllUsers> getEmpListByOrgId(String orgId){
		Long org_id=Long.valueOf(orgId);
		return (List<AllUsers>)getSqlMapClientTemplate().queryForList("stockReport.getEmpListByOrgId",org_id);
	}

	@SuppressWarnings("unchecked")
	public List<Station> getEmpListByUserName(Station station){
		return (List<Station>)getSqlMapClientTemplate().queryForList("stockReport.getEmpListByUserName",station);
	}
	
	@SuppressWarnings("unchecked")
	public List<AllUsers> getWorkFlowStation(Long eventId){
		return (List<AllUsers>)getSqlMapClientTemplate().queryForList("stockReport.getWorkFlowStation",eventId);
	}
	
    public int createKunnrManagerSku(KunnrManager kunnrManager){
		getSqlMapClientTemplate().insert("stockReport.createKunnrManagerSku",kunnrManager);
		return 1;
    }
	
	public int updateKunnrManagerSku(KunnrManager kunnrManager){
		return getSqlMapClientTemplate().update("stockReport.updateKunnrManagerSku",kunnrManager);
	}
	
    public int searchKunnrManagerKunnrByOrgSumListCount(KunnrManager kunnrManager){
		return (Integer)getSqlMapClientTemplate().queryForObject("stockReport.searchKunnrManagerKunnrByOrgSumListCount",kunnrManager);
    }
	
	@SuppressWarnings("unchecked")
	public List<KunnrManager> searchKunnrManagerKunnrByOrgSumList(KunnrManager kunnrManager){
		return (List<KunnrManager>)getSqlMapClientTemplate().queryForList("stockReport.searchKunnrManagerKunnrByOrgSumList",kunnrManager);
	}
	
	public int getOrgCityByOrgId(Long orgId){
		return (Integer)getSqlMapClientTemplate().queryForObject("stockReport.getOrgCityByOrgId",orgId);
	}
	
	public int getCgCount(Sku sku){
		return (Integer)getSqlMapClientTemplate().queryForObject("stockReport.getCgCount",sku);
	}
	
    public int searchCustomerStockListCount(StockReport stockReport){
		return (Integer)getSqlMapClientTemplate().queryForObject("stockReport.searchCustomerStockListCount",stockReport);
    }
	
	@SuppressWarnings("unchecked")
	public List<StockReport> searchCustomerStockList(StockReport stockReport){
		return (List<StockReport>)getSqlMapClientTemplate().queryForList("stockReport.searchCustomerStockList",stockReport);
	}
	
	public int createCustomerStock(StockReport stockReport){
		getSqlMapClientTemplate().insert("stockReport.createCustomerStock",stockReport);
		return 1;
	}
	
	public int updateCustomerStock(StockReport stockReport){
		return getSqlMapClientTemplate().update("stockReport.updateCustomerStock",stockReport);
	}
	
	public int createOrUpdateCustomerStock(StockReport stockReport){
		return (Integer)getSqlMapClientTemplate().insert("stockReport.createOrUpdateCustomerStock",stockReport);
	}
	
    public int createSkuUnitSap(SkuUnit skuUnit){
    	getSqlMapClientTemplate().insert("stockReport.createSkuUnitSap",skuUnit);
		return 1;
    }
	
	public int deleteSkuUnitSap(){
		return getSqlMapClientTemplate().update("stockReport.deleteSkuUnitSap");
	}

	public int searchSkuUnitListCount(SkuUnit skuUnit){
		return (Integer)getSqlMapClientTemplate().queryForObject("stockReport.searchSkuUnitListCount",skuUnit);
	}
	
	@SuppressWarnings("unchecked")
	public List<SkuUnit> searchSkuUnitList(SkuUnit skuUnit){
		return (List<SkuUnit>)getSqlMapClientTemplate().queryForList("stockReport.searchSkuUnitList",skuUnit);
	}

	@SuppressWarnings("unchecked")
	public List<SkuUnit> searchSkuUnitSap(SkuUnit skuUnit){
		return (List<SkuUnit>)getSqlMapClientTemplate().queryForList("stockReport.searchSkuUnitSap",skuUnit);
	}
	
	public int updateSkuUnit(SkuUnit skuUnit){
		return getSqlMapClientTemplate().update("stockReport.updateSkuUnit",skuUnit);
	}

	public int createSkuUnit(SkuUnit skuUnit){
		getSqlMapClientTemplate().insert("stockReport.createSkuUnit",skuUnit);
		return 1;
	}

	public int searchCustomerStockByDateListCount(StockReport stockReport){
		return (Integer)getSqlMapClientTemplate().queryForObject("stockReport.searchCustomerStockByDateListCount",stockReport);
	}

	@SuppressWarnings("unchecked")
	public List<StockReport> searchCustomerStockByDateList(StockReport stockReport){
		return (List<StockReport>)getSqlMapClientTemplate().queryForList("stockReport.searchCustomerStockByDateList",stockReport);
	}
	
	public int searchCustomerStockByDateDescListCount(StockReport stockReport){
		return (Integer)getSqlMapClientTemplate().queryForObject("stockReport.searchCustomerStockByDateDescListCount",stockReport);
	}

	@SuppressWarnings("unchecked")
	public List<StockReport> searchCustomerStockByDateDescList(StockReport stockReport){
		return (List<StockReport>)getSqlMapClientTemplate().queryForList("stockReport.searchCustomerStockByDateDescList",stockReport);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getKunnrIdByKunag(String kunnr) {
		return (List<String>)getSqlMapClientTemplate().queryForList("stockReport.getKunnrIdByKunag",kunnr);
	}
	
	public Date getSysdate(){
		return (Date)getSqlMapClientTemplate().queryForObject("stockReport.getSysdate");
	}

	@Override
	public String getKunnrImportant(String kunnr) {
		return (String)getSqlMapClientTemplate().queryForObject("stockReport.getKunnrImportant",kunnr);
	}

	@Override
	public void updateOperationUser(Goal goal1) {
		getSqlMapClientTemplate().update("stockReport.updateOperationUser", goal1);
	}
}
