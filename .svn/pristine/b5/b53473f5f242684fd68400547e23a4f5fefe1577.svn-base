package com.kintiger.platform.pos.dao.impl;


import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.distributionData.pojo.DistributionData;
import com.kintiger.platform.pos.dao.IPosDao;
import com.kintiger.platform.pos.pojo.Pos;

public class PosDaoImpl extends BaseDaoImpl implements IPosDao{



	@Override
	public int getPosSize(Pos pGoal) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"pos.getposSize", pGoal);
	}

	@Override
	public Pos getOrgByOrgName(String org_city) {
		return  (Pos) getSqlMapClientTemplate().queryForObject(
				"pos.getOrgIdByOrgName", org_city);
	}

	@Override
	public long insertPosData(Pos pos) {
		return  (Long) getSqlMapClientTemplate().insert(
				"pos.insertPosData", pos);
	}

	@Override
	public Pos getSystemBySystemName(String systemName) {
		return  (Pos) getSqlMapClientTemplate().queryForObject(
				"pos.getSystemBySystemName", systemName);
	}

	@Override
	public List<Pos> getPosList(Pos dGoal) {
		return (List<Pos>) getSqlMapClientTemplate().queryForList(
				"pos.getPosList", dGoal);
	}

	@Override
	public int getPosCount(Pos dGoal) {
		return (Integer) getSqlMapClientTemplate().queryForObject("pos.getPosCount",
				dGoal);
	}

	@Override
	public int deletePos(Pos dGoal) {
			return getSqlMapClientTemplate().delete("pos.deletePos", dGoal);
	}

	@Override
	public int updatePos(Pos pos) {
		return getSqlMapClientTemplate().update("pos.updatePos", pos);
	}

}
