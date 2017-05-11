package com.kintiger.platform.org.service;

import java.util.List;

import com.kintiger.platform.org.pojo.Borg;

/**
 * 組織接口
 * 
 */
public interface INewOrgService {
	/**
	 * 根据pOrgId父级组织查询所在组织（包括映射组织）
	 * 
	 * @param pOrgId
	 * @return
	 */
	List<Borg> getOrgTreeListByPorgId(String node);
	/**
	 * 根据orgId查询组织
	 * 
	 * @param orgId
	 * @return
	 */
	Borg getOrgByOrgId(String orgId);
	/**
	 * 根据UserId查询主次岗位的组织
	 * @param empId
	 * @return
	 */
	List<String> getOrgListByUserId(String userId);
	/***
	 * 检测是否查看所有组织权限
	 * 角色名：全部组织查看
	 * 角色id：org_search_all
	 * @param s
	 * @return
	 */
	public int getPermissionByUserId(String userId);
	
	/**
	 * 检测orgid是否在orgid2下
	 * @param OrgId2
	 * @param orgId
	 * @return
	 */
	int getOrgCount(String OrgId2, String orgId);


}
