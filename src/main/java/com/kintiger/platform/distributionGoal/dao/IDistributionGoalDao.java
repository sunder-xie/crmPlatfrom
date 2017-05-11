/********************************************
 * 文件名称: IDistributionGoalDao.java
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
package com.kintiger.platform.distributionGoal.dao;

import java.util.List;

import com.kintiger.platform.distributionGoal.pojo.DistributionGoal;


public interface IDistributionGoalDao {

	public int getDistributionGoalCount(DistributionGoal dGoal);
	
	public List<DistributionGoal> getDistributionGoalList(DistributionGoal dGoal);
	
	public long insertDistributionGoal(DistributionGoal dGoal);
	public int deleteDistributionGoal(DistributionGoal dGoal);
	public int getDistributionGoalSize(DistributionGoal dGoal);
	public int checkDistributionGoal(DistributionGoal dGoal);	
	public DistributionGoal getOrgByOrgName(String org_city);
	public int updateDistributionGoal(DistributionGoal dGoal);
}
