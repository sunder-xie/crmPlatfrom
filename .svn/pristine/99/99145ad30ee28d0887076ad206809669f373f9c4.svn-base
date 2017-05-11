package com.kintiger.platform.org.dao.impl;

import java.util.HashMap;
import java.util.List;





import java.util.Map;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.org.dao.INewOrgDao;
import com.kintiger.platform.org.pojo.Borg;

public class NewOrgDaoImpl extends BaseDaoImpl implements INewOrgDao {


	@SuppressWarnings("unchecked")
	public List<Borg> getOrgTreeListByPorgId(String pOrgId) {
		Borg org = new Borg();
		org.setOrgParentId(Long.valueOf(pOrgId));
		return (List<Borg>) getSqlMapClientTemplate().queryForList(
				"newOrg.getOrgTreeListByOrgId", org);
	}
	public Borg getOrgByOrgId(String orgId) {
		Borg org = new Borg();
		org.setOrgId(Long.valueOf(orgId));
		return (Borg) getSqlMapClientTemplate().queryForObject(
				"newOrg.getOrgByOrgId", org);
	}
	@SuppressWarnings("unchecked")
	public List<String> getOrgListByUserId(String userId) {
		return getSqlMapClientTemplate().queryForList(
				"newOrg.getOrgListByUserId", userId);
	}
	public int getPermissionByUserId(String userId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"newOrg.getPermissionByUserId", userId);
	}
	@Override
	public int getOrgCount(String orgId2, String orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("baseOrgId", orgId2);
		map.put("orgId", orgId);
		return (Integer) getSqlMapClientTemplate().queryForObject("newOrg.getOrgCount", map);
	}
}
