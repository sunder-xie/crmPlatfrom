package com.kintiger.platform.pos.service;


import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.pos.pojo.Pos;

public interface IPosService {



	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "²Ù×÷Ê§°Ü";

	public int getPosSize(Pos pGoal);

	public Pos getOrgByOrgName(String org_city);

	public BooleanResult insertPosData(Pos pos);

	public Pos getSystemBySystemName(String systemName);

	public int getPosCount(Pos dGoal);

	public List<Pos> getPosList(Pos dGoal);

	public StringResult deletePos(Pos dGoal);

	public BooleanResult updatePos(Pos pos);
}
