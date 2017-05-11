package com.kintiger.platform.org.service;

import java.util.List;

import com.kintiger.platform.org.pojo.Borg;

/**
 * �M���ӿ�
 * 
 */
public interface INewOrgService {
	/**
	 * ����pOrgId������֯��ѯ������֯������ӳ����֯��
	 * 
	 * @param pOrgId
	 * @return
	 */
	List<Borg> getOrgTreeListByPorgId(String node);
	/**
	 * ����orgId��ѯ��֯
	 * 
	 * @param orgId
	 * @return
	 */
	Borg getOrgByOrgId(String orgId);
	/**
	 * ����UserId��ѯ���θ�λ����֯
	 * @param empId
	 * @return
	 */
	List<String> getOrgListByUserId(String userId);
	/***
	 * ����Ƿ�鿴������֯Ȩ��
	 * ��ɫ����ȫ����֯�鿴
	 * ��ɫid��org_search_all
	 * @param s
	 * @return
	 */
	public int getPermissionByUserId(String userId);
	
	/**
	 * ���orgid�Ƿ���orgid2��
	 * @param OrgId2
	 * @param orgId
	 * @return
	 */
	int getOrgCount(String OrgId2, String orgId);


}
