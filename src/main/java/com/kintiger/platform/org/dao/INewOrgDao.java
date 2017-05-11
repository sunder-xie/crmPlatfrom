package com.kintiger.platform.org.dao;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.org.pojo.Borg;

public interface INewOrgDao {
	/**
	 * ����pOrgId��ѯ������֯������ӳ����֯��
	 * 
	 * @param pOrgId
	 * @return List<Borg>
	 */
	public List<Borg> getOrgTreeListByPorgId(String pOrgId);
	/**
	 * ����orgId��ѯ��֯��
	 * 
	 * @param orgId
	 * @return Borg
	 */

	public Borg getOrgByOrgId(String orgId);
	/**
	 * ����UserId��ѯ���θ�λ����֯
	 * @param empId
	 * @return
	 */
	public List<String> getOrgListByUserId(String userId);
	
	public int getPermissionByUserId(String userId);

	/**
	 * ���orgid�Ƿ���orgid2��
	 * @param OrgId2
	 * @param orgId
	 * @return
	 */
	public int getOrgCount(String orgId2, String orgId);

}
