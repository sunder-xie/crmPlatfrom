package com.kintiger.platform.pos.dao;

import java.util.List;

import com.kintiger.platform.distributionData.pojo.DistributionData;
import com.kintiger.platform.pos.pojo.Pos;

public interface IPosDao {
	


	public int getPosSize(Pos pGoal);

	public Pos getOrgByOrgName(String org_city);

	public long insertPosData(Pos pos);

	public Pos getSystemBySystemName(String systemName);

	public List<Pos> getPosList(Pos dGoal);

	public int getPosCount(Pos dGoal);

	public int deletePos(Pos dGoal);

	public int updatePos(Pos pos);

	
}
