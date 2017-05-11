/********************************************
 * �ļ�����: IDistributionGoalService.java
 * ϵͳ����: EXPƽ̨ V1.0
 * ģ������: �����̷���Ŀ��������
 * �����Ȩ: ��ƮƮ�ɷ����޹�˾
 * ����˵��: 
 * ϵͳ�汾: 1.0.0.1
 * ������Ա: xly
 * ����ʱ��: 2013-12-21
 * �����Ա:
 * ����ĵ�:
 * �޸ļ�¼: �޸�����    �޸���Ա    �޸�˵��
 *********************************************/
package com.kintiger.platform.distributionGoal.service;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionGoal.pojo.DistributionGoal;



public interface IDistributionGoalService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "����ʧ��";

    public int getDistributionGoalCount(DistributionGoal dGoal);
    public List<DistributionGoal> getDistributionGoalList(DistributionGoal dGoal);
    public BooleanResult insertDistributionGoal(DistributionGoal dGoal);
    public StringResult deleteDistributionGoal(DistributionGoal dGoal);
    public int getDistributionGoalSize(DistributionGoal dGoal);
    public StringResult checkDistributionGoal(DistributionGoal dGoal);
    public DistributionGoal getOrgByOrgName(String org_city);
    public StringResult updateDistributionGoal(DistributionGoal dGoal);
}
