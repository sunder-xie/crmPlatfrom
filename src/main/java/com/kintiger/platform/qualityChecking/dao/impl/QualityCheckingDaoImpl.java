package com.kintiger.platform.qualityChecking.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.qualityChecking.dao.IQualityCheckingDao;
import com.kintiger.platform.qualityChecking.pojo.Materiel;
import com.kintiger.platform.qualityChecking.pojo.QualityChecking;

/**
 * @Description 数据层实现类
 * @author:xg.chen
 * @time:2017年5月8日 下午3:35:30
 * @version:1.0
 */
public class QualityCheckingDaoImpl extends BaseDaoImpl implements
		IQualityCheckingDao {

	@Override
	public int getMaterielListCount(Materiel mat) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"qualityChecking.getMaterielListCount", mat);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Materiel> getMaterielList(Materiel mat) {
		return (List<Materiel>) getSqlMapClientTemplate().queryForList(
				"qualityChecking.getMaterielList", mat);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Materiel> getMaterielNameList(Materiel mat) {
		return (List<Materiel>) getSqlMapClientTemplate().queryForList(
				"qualityChecking.getMaterielNameList", mat);
	}

	@Override
	public void creatQualityChecking(QualityChecking qualityChecking1) {
		getSqlMapClientTemplate().insert(
				"qualityChecking.creatQualityChecking", qualityChecking1);

	}

	@Override
	public int getQualityCheckingCount(QualityChecking qualityCheck) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"qualityChecking.getQualityCheckingCount", qualityCheck);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QualityChecking> getQualityCheckingJsonList(
			QualityChecking qualityCheck) {
		return (List<QualityChecking>) getSqlMapClientTemplate().queryForList(
				"qualityChecking.getQualityCheckingJsonList", qualityCheck);
	}

	@Override
	public void updateQualityCheckingUploadFile(QualityChecking qualityCheck) {
		getSqlMapClientTemplate()
				.update("qualityChecking.updateQualityCheckingUploadFile",
						qualityCheck);
	}

	@Override
	public QualityChecking getQualityCheckingName(String id) {
		return (QualityChecking) getSqlMapClientTemplate().queryForObject(
				"qualityChecking.getQualityCheckingName", id);
	}

	@Override
	public int getOfficeRole(String userId, String roleId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		return  (Integer) getSqlMapClientTemplate().queryForObject(
				"qualityChecking.getOfficeRole", map);
	}

}
