package com.kintiger.platform.goal.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.goal.dao.IGoalDao;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.goal.pojo.GoalSales;
import com.kintiger.platform.goal.pojo.GoalSalesDetail;
import com.kintiger.platform.goal.pojo.MatterPriceBO;
import com.kintiger.platform.goal.pojo.MatterPriceObject;
import com.kintiger.platform.goal.pojo.OrgHelps;
import com.kintiger.platform.goal.pojo.PrintContractGoalInfo;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrSalesArea;
import com.kintiger.platform.master.pojo.Materiel;

public class GoalDaoImpl extends BaseDaoImpl implements IGoalDao {

	public int getGoalListCount(BCustomerTarget bct) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalListCount", bct);
	}

	public int getGoalListCount1(BCustomerTarget bct) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalListCount1", bct);
	}

	public int getMatListCount(Materiel mat) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getMatListCount", mat);

	}

	public int getOrgCount(Kunnr kunnr) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getOrgCount", kunnr);
	}

	public int getKunnrListCount(Kunnr kunnr) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getKunnrListCount", kunnr);
	}

	@SuppressWarnings("unchecked")
	public List<BCustomerTarget> getGoalList(BCustomerTarget bct) {
		return (List<BCustomerTarget>) getSqlMapClientTemplate().queryForList(
				"goal.getGoalList", bct);
	}

	@SuppressWarnings("unchecked")
	public List<Materiel> getMatList(Materiel mat) {
		return (List<Materiel>) getSqlMapClientTemplate().queryForList(
				"goal.getMatList", mat);
	}

	@SuppressWarnings("unchecked")
	public List<Materiel> getMatList1(Materiel mat) {
		return (List<Materiel>) getSqlMapClientTemplate().queryForList(
				"goal.getMatList1", mat);
	}

	public BCustomerTarget getGoalById(String ctId) {
		BCustomerTarget bct = new BCustomerTarget();
		bct.setCtId(ctId);
		return (BCustomerTarget) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalById", bct);
	}

	@SuppressWarnings("unchecked")
	public List<Kunnr> getKunnrList(Kunnr kunnr) {
		return (List<Kunnr>) getSqlMapClientTemplate().queryForList(
				"goal.getKunnrList", kunnr);
	}

	@SuppressWarnings("unchecked")
	public List<Kunnr> getKunnrList1(Kunnr kunnr) {
		return (List<Kunnr>) getSqlMapClientTemplate().queryForList(
				"goal.getKunnrList1", kunnr);
	}

	public String insertGoal(BCustomerTarget bct) {
		return (String) getSqlMapClientTemplate()
				.insert("goal.insertGoal", bct);
	}

	public int updateGoal(BCustomerTarget bct) {
		return getSqlMapClientTemplate().update("goal.updateGoal", bct);
	}

	public int approveGoal(BCustomerTarget bct) {
		return getSqlMapClientTemplate().update("goal.approveGoal", bct);
	}

	public int getMaterielListCount(Materiel mat) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getMaterielListCount", mat);
	}

	@SuppressWarnings("unchecked")
	public List<Materiel> getMaterielList(Materiel mat) {
		return (List<Materiel>) getSqlMapClientTemplate().queryForList(
				"goal.getMaterielList", mat);
	}

	public int approveYearGoal(BCustomerTarget bct) {
		return (Integer) getSqlMapClientTemplate().update(
				"goal.approveYearGoal", bct);
	}

	public BCustomerTarget getGoalMessege(BCustomerTarget target) {
		return (BCustomerTarget) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalMessege", target);
	}

	@SuppressWarnings("unchecked")
	public List<BCustomerTarget> getGoalMessegeByALL(BCustomerTarget target) {
		return (List<BCustomerTarget>) getSqlMapClientTemplate().queryForList(
				"goal.getGoalMessegeByALL", target);
	}

	public int getKunnrGoalCount(BCustomerTarget target) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getKunnrGoalCount", target);
	}

	@SuppressWarnings("unchecked")
	public List<BCustomerTarget> getKunnrGoalAll(BCustomerTarget target) {
		return (List<BCustomerTarget>) getSqlMapClientTemplate().queryForList(
				"goal.getKunnrGoalAll", target);
	}

	@SuppressWarnings("unchecked")
	public List<OrgHelps> getOrgIsExit(Kunnr kunnr) {
		return (List<OrgHelps>) getSqlMapClientTemplate().queryForList(
				"goal.getOrgIsExit", kunnr);
	}

	public int getConpointByUser(String userId, String conpointId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("conpointId", conpointId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getConpointByUser", params);
	}

	public int getMaterielListViewCount(Materiel mat) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getMaterielListViewCount", mat);
	}

	@SuppressWarnings("unchecked")
	public List<Materiel> getMaterielViewList(Materiel mat) {
		return (List<Materiel>) getSqlMapClientTemplate().queryForList(
				"goal.getMaterielViewList", mat);
	}

	public BCustomerTarget getGoalByCondition(BCustomerTarget target) {
		return (BCustomerTarget) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalByCondition", target);
	}

	public double getGoalCountNum(BCustomerTarget target) {
		return (Double) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalCountNum", target);
	}

	public BCustomerTarget getGoalMessegeOnYearAndMatter(BCustomerTarget target) {
		return (BCustomerTarget) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalMessegeOnYearAndMatter", target);
	}

	public int deleteKunnrGoalAppply(BCustomerTarget target) {
		return (Integer) getSqlMapClientTemplate().delete(
				"goal.deleteKunnrGoalAppply", target);
	}

	public int searchMatterPriceCount(MatterPriceObject object) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.searchMatterPriceCount", object);
	}

	@SuppressWarnings("unchecked")
	public List<MatterPriceObject> searchMatterPriceList(
			MatterPriceObject object) {
		return (List<MatterPriceObject>) getSqlMapClientTemplate()
				.queryForList("goal.searchMatterPriceList", object);
	}

	public MatterPriceObject getMatterPriceObject(MatterPriceObject object) {
		return (MatterPriceObject) getSqlMapClientTemplate().queryForObject(
				"goal.getMatterPriceObject", object);
	}

	public String createGoalPrice(MatterPriceObject object) {
		return (String) getSqlMapClientTemplate().insert(
				"goal.createGoalPrice", object);
	}

	public int updateGoalPrice(MatterPriceObject object) {
		return (Integer) getSqlMapClientTemplate().update(
				"goal.updateGoalPrice", object);
	}

	@Override
	public int searchMatterPriceBoCount(MatterPriceBO priceBo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.searchMatterPriceBoCount", priceBo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MatterPriceBO> searchMatterPriceBoList(MatterPriceBO priceBo) {
		return (List<MatterPriceBO>) getSqlMapClientTemplate().queryForList(
				"goal.searchMatterPriceBoList", priceBo);
	}

	public void insertMarPrice(MatterPriceBO priceBo) {
		getSqlMapClientTemplate().insert("goal.insertMarPrice", priceBo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BCustomerTarget> getGoalReportList(BCustomerTarget bct) {
		return (List<BCustomerTarget>) getSqlMapClientTemplate().queryForList(
				"goal.getGoalReportList", bct);
	}

	@Override
	public BCustomerTarget getGoalContract(BCustomerTarget c) {
		return (BCustomerTarget) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalContract", c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BCustomerTarget> getGoalDK(BCustomerTarget bct) {
		return (List<BCustomerTarget>) getSqlMapClientTemplate().queryForList(
				"goal.getGoalDK", bct);
	}

	@Override
	public int getGoalSumCount(BCustomerTarget bct) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalSumCount", bct);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BCustomerTarget> getGoalDKAll(BCustomerTarget bct) {
		return (List<BCustomerTarget>) getSqlMapClientTemplate().queryForList(
				"goal.getGoalDKAll", bct);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BCustomerTarget> getGoalSum(BCustomerTarget bct) {
		return (List<BCustomerTarget>) getSqlMapClientTemplate().queryForList(
				"goal.getGoalSum", bct);
	}

	@Override
	public BCustomerTarget getGoalContractSum(BCustomerTarget c) {
		return (BCustomerTarget) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalContractSum", c);
	}

	public Long createGoalSalesChange(GoalSales goalSales) {
		return (Long) getSqlMapClientTemplate().insert(
				"goal.createGoalSalesChange", goalSales);
	}

	public Long createGoalSalesChangeDetail(GoalSalesDetail goalSalesDetail) {
		return (Long) getSqlMapClientTemplate().insert(
				"goal.createGoalSalesChangeDetail", goalSalesDetail);
	}

	@SuppressWarnings("unchecked")
	public List<Kunnr> searchKunnrList(Kunnr kunnr) {
		return (List<Kunnr>) getSqlMapClientTemplate().queryForList(
				"goal.searchKunnrList", kunnr);
	}

	public int getSkuCount(GoalSalesDetail goalSalesDetail) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getSkuCount", goalSalesDetail);
	}

	public int searchGoalSalesChangeListCount(GoalSales goalSales) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.searchGoalSalesChangeListCount", goalSales);
	}

	@SuppressWarnings("unchecked")
	public List<GoalSales> searchGoalSalesChangeList(GoalSales goalSales) {
		return (List<GoalSales>) getSqlMapClientTemplate().queryForList(
				"goal.searchGoalSalesChangeList", goalSales);
	}

	@SuppressWarnings("unchecked")
	public List<GoalSalesDetail> searchGoalSalesChangeDetailList(
			GoalSalesDetail goalSalesDetail) {
		return (List<GoalSalesDetail>) getSqlMapClientTemplate().queryForList(
				"goal.searchGoalSalesChangeDetailList", goalSalesDetail);
	}

	public int updateGoalSalesChange(GoalSales goalSales) {
		return getSqlMapClientTemplate().update("goal.updateGoalSalesChange",
				goalSales);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> getStationIdByUserId(String userId) {
		return (List<AllUsers>) getSqlMapClientTemplate().queryForList(
				"goal.getStationIdByUserId", userId);
	}

	@SuppressWarnings("unchecked")
	public List<AllUsers> getOrgsByUserId(String userId) {
		return (List<AllUsers>) getSqlMapClientTemplate().queryForList(
				"goal.getOrgsByUserId", userId);
	}

	public Double getSalesGoalCGDetailSum(GoalSalesDetail goalSalesDetail) {
		return (Double) getSqlMapClientTemplate().queryForObject(
				"goal.getSalesGoalCGDetailSum", goalSalesDetail);
	}

	@Override
	public int getGoalDKCount(BCustomerTarget bct) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalDKCount", bct);
	}

	@Override
	public int getGoalDKAllCount(BCustomerTarget bct) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalDKAllCount", bct);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PrintContractGoalInfo> searchConGolInfo(
			PrintContractGoalInfo printConGoalInfo) {
		return (List<PrintContractGoalInfo>) getSqlMapClientTemplate()
				.queryForList("goal.searchConGolInfo", printConGoalInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PrintContractGoalInfo> searchConGolInfo2(
			PrintContractGoalInfo printConGoalInfo) {
		return (List<PrintContractGoalInfo>) getSqlMapClientTemplate()
				.queryForList("goal.searchConGolInfo2", printConGoalInfo);
	}

	@Override
	public int getKunnrListForNormalCount(Kunnr kunnr) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getKunnrListForNormalCount", kunnr);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kunnr> getKunnrListForNormal(Kunnr kunnr) {
		return (List<Kunnr>) getSqlMapClientTemplate().queryForList(
				"goal.getKunnrListForNormal", kunnr);
	}

	@Override
	public int getGoalListCountForMBL(BCustomerTarget bct) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getGoalListCountForMBL", bct);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BCustomerTarget> getGoalReportListForMBL(BCustomerTarget bct) {
		return (List<BCustomerTarget>) getSqlMapClientTemplate().queryForList(
				"goal.getGoalReportListForMBL", bct);
	}

	@Override
	public int getDistributionGoalTotal(BCustomerTarget bct) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.getDistributionGoalTotal", bct);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BCustomerTarget> getDistributionGoalList(BCustomerTarget bct) {
		return (List<BCustomerTarget>) getSqlMapClientTemplate().queryForList(
				"goal.getDistributionGoalList", bct);
	}

	@Override
	public Double getSalesGoalFXDetailSum(GoalSalesDetail gsd) {
		return (Double) getSqlMapClientTemplate().queryForObject(
				"goal.getSalesGoalFXDetailSum", gsd);
	}

	@Override
	public Long createGoalDistributionChange(GoalSales goalSales) {
		return (Long) getSqlMapClientTemplate().insert(
				"goal.createGoalDistributionChange", goalSales);
	}

	@Override
	public Long createGoalFXChangeDetail(GoalSalesDetail gsdTo) {
		return (Long) getSqlMapClientTemplate().insert(
				"goal.createGoalFXChangeDetail", gsdTo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GoalSales> searchGoalFXChangeList(GoalSales goalSales) {
		return (List<GoalSales>) getSqlMapClientTemplate().queryForList(
				"goal.searchGoalFXChangeList", goalSales);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GoalSalesDetail> searchGoalFXChangeDetailList(
			GoalSalesDetail goalSalesDetail) {
		return (List<GoalSalesDetail>) getSqlMapClientTemplate().queryForList(
				"goal.searchGoalFXChangeDetailList", goalSalesDetail);
	}

	@Override
	public int updateFXGoal(GoalSalesDetail bct) {
		return getSqlMapClientTemplate().update("goal.updateFXGoal", bct);

	}

	@Override
	public int searchGoalFXChangeListCount(GoalSales goalSales) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"goal.searchGoalFXChangeListCount", goalSales);
	}

	@Override
	public int updateGoalFXChange(GoalSales goalSales) {
		return getSqlMapClientTemplate().update("goal.updateGoalFXChange",
				goalSales);
	}

	@Override
	public Object insertFXGoal(BCustomerTarget bct) {
		return getSqlMapClientTemplate().insert("goal.insertFXGoal", bct);

	}

	@Override
	public int searchSkuText(String skuText) {
		return (Integer) getSqlMapClientTemplate().queryForObject("goal.searchSkuText",skuText);
	}

	@Override
	public int searchSku(String sku) {
		return (Integer) getSqlMapClientTemplate().queryForObject("goal.searchSku",sku);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BCustomerTarget> getMaktxAndMaknr(Materiel mat) {
		return getSqlMapClientTemplate().queryForList("goal.getMaktxAndMaknr", mat);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PrintContractGoalInfo> searchConGolInfo3(
			PrintContractGoalInfo printConGoalInfo) {
		return (List<PrintContractGoalInfo>) getSqlMapClientTemplate()
				.queryForList("goal.searchConGolInfo3", printConGoalInfo);
	}

}
