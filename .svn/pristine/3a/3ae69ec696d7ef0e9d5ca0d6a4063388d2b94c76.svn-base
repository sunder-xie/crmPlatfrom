package com.kintiger.platform.org.dao;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.org.pojo.Borg;

public interface INewOrgDao {
	/**
	 * 根据pOrgId查询所在组织（包括映射组织）
	 * 
	 * @param pOrgId
	 * @return List<Borg>
	 */
	public List<Borg> getOrgTreeListByPorgId(String pOrgId);
	/**
	 * 根据orgId查询组织（
	 * 
	 * @param orgId
	 * @return Borg
	 */

	public Borg getOrgByOrgId(String orgId);
	/**
	 * 根据UserId查询主次岗位的组织
	 * @param empId
	 * @return
	 */
	public List<String> getOrgListByUserId(String userId);
	
	public int getPermissionByUserId(String userId);

	/**
	 * 检测orgid是否在orgid2下
	 * @param OrgId2
	 * @param orgId
	 * @return
	 */
	public int getOrgCount(String orgId2, String orgId);

}
