/********************************************
 * 文件名称: DistributionGoalDaoImpl.java
 * 系统名称: EXP平台 V1.0
 * 模块名称: 经销商分销目标量管理
 * 软件版权: 香飘飘股份有限公司
 * 功能说明: 
 * 系统版本: 1.0.0.1
 * 开发人员: xly
 * 开发时间: 2013-12-21
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.kintiger.platform.distributionGoal.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.distributionGoal.dao.IDistributionGoalDao;
import com.kintiger.platform.distributionGoal.pojo.DistributionGoal;

public class DistributionGoalDaoImpl extends BaseDaoImpl implements
		IDistributionGoalDao {

	public int getDistributionGoalCount(DistributionGoal dGoal) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionGoal.getDistributionGoalCount", dGoal);
	}

	@SuppressWarnings("unchecked")
	public List<DistributionGoal> getDistributionGoalList(DistributionGoal dGoal) {
		return (List<DistributionGoal>) getSqlMapClientTemplate().queryForList(
				"distributionGoal.getDistributionGoalList", dGoal);
	}

	public long insertDistributionGoal(DistributionGoal dGoal) {
		return (Long) getSqlMapClientTemplate().insert(
				"distributionGoal.insertDistributionGoal", dGoal);
	}

	public int deleteDistributionGoal(DistributionGoal dGoal) {
		return getSqlMapClientTemplate().delete(
				"distributionGoal.deleteDistributionGoal", dGoal);

	}

	public int getDistributionGoalSize(DistributionGoal dGoal) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"distributionGoal.getDistributionGoalSize", dGoal);
	}

	public int checkDistributionGoal(DistributionGoal dGoal) {
		return getSqlMapClientTemplate().update(
				"distributionGoal.checkDistributionGoal", dGoal);
	}

	public DistributionGoal getOrgByOrgName(String org_city) {
		return  (DistributionGoal) getSqlMapClientTemplate().queryForObject(
				"distributionGoal.getOrgIdByOrgName", org_city);
	}

	public int updateDistributionGoal(DistributionGoal dGoal) {
		return getSqlMapClientTemplate().update(
				"distributionGoal.updateDistributionGoal", dGoal);
	}
}
