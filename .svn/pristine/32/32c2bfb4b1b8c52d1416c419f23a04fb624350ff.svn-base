package com.kintiger.platform.org.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.org.dao.INewOrgDao;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.INewOrgService;

public class NewOrgServiceImpl implements INewOrgService {

	private Logger logger = Logger.getLogger(INewOrgService.class);
	private INewOrgDao newOrgDao;

	public List<Borg> getOrgTreeListByPorgId(String pOrgId) {
		try {
			return newOrgDao.getOrgTreeListByPorgId(pOrgId);
		} catch (Exception e) {
			logger.error(pOrgId, e);
		}
		return null;
	}
	public Borg getOrgByOrgId(String orgId) {
		try {
			return newOrgDao.getOrgByOrgId(orgId);
		} catch (Exception e) {
			logger.error(orgId, e);
		}

		return null;
	}
	public List<String> getOrgListByUserId(String userId){
		try {
			return newOrgDao.getOrgListByUserId(userId);
		} catch (Exception e) {
			logger.error(userId, e);
			return null;
		}
	}
	public INewOrgDao getNewOrgDao() {
		return newOrgDao;
	}
	public void setNewOrgDao(INewOrgDao newOrgDao) {
		this.newOrgDao = newOrgDao;
	}
	public int getPermissionByUserId(String userId) {
		try{
			return newOrgDao.getPermissionByUserId(userId);
		}catch (Exception e) {
			logger.error(userId, e);
		}
		return 0;
	}
	@Override
	public int getOrgCount(String OrgId2, String orgId) {
		try {
			return newOrgDao.getOrgCount(OrgId2, orgId);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
}
