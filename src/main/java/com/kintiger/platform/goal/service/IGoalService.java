package com.kintiger.platform.goal.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.goal.pojo.GoalSales;
import com.kintiger.platform.goal.pojo.GoalSalesDetail;
import com.kintiger.platform.goal.pojo.MatterPriceBO;
import com.kintiger.platform.goal.pojo.MatterPriceObject;
import com.kintiger.platform.goal.pojo.OrgHelps;
import com.kintiger.platform.goal.pojo.PrintContractGoalInfo;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.Materiel;

public interface IGoalService {

	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "����ʧ��";

	/**
	 * ��ѯĿ����Ϣ Count
	 * 
	 * @param bct
	 * @return
	 */
	public int getGoalListCount(BCustomerTarget bct);

	public int getGoalListCount1(BCustomerTarget bct);

	public int getMatListCount(Materiel mat);

	public int getOrgCount(Kunnr kunnr);

	/**
	 * ��ѯĿ����Ϣ
	 * 
	 * @param bct
	 * @return
	 */
	public List<BCustomerTarget> getGoalList(BCustomerTarget bct);

	public List<Materiel> getMatList(Materiel mat);

	public List<Materiel> getMatList1(Materiel mat);

	public BCustomerTarget getGoalById(String ctId);

	/**
	 * ��ѯ�ͻ���Ϣ Count
	 * 
	 * @param kunnr
	 * @return
	 */
	public int getKunnrListCount(Kunnr kunnr);

	/**
	 * ��ѯ�ͻ���Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<Kunnr> getKunnrsList(Kunnr kunnr);

	/**
	 * ��ѯ�ͻ���Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<Kunnr> getKunnrsList1(Kunnr kunnr);

	/**
	 * ����Ŀ���
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult insertGoal(final BCustomerTarget bct);

	public boolean insertGoal1(final BCustomerTarget bct);

	public boolean updateGoal(final BCustomerTarget bct);

	public StringResult approveGoal(final BCustomerTarget bct);

	public StringResult insertBct(final List<BCustomerTarget> bctList);

	/**
	 * ��ȡ����listCout
	 * 
	 * @param mat
	 * @return
	 */
	public int getMaterielListCount(Materiel mat);

	/**
	 * ��ȡ����list
	 * 
	 * @param mat
	 * @return
	 */
	public List<Materiel> getMaterielList(Materiel mat);
	/**
	 * �����������һ���Ŀ����
	 * @param mat
	 * @return
	 */
	public StringResult approveYearGoal(final BCustomerTarget bct);
	/**
	 * ����������еľ�����Ŀ����������֯Ŀ��������֯����Ŀ����
	 * ά�ȣ���֯���ꡢ�¡�����
	 * @param ctId
	 * @return
	 */
	public BCustomerTarget getGoalMessege(BCustomerTarget target);
	
	/**
	 * ������²���������ϵ���֯����Ŀ����
	 * ά�ȣ���֯�������ꡢ����
	 * @param ctId
	 * @return
	 */
	public BCustomerTarget getGoalMessegeOnYearAndMatter(BCustomerTarget target);
	
	/**
	 * ��Ŀ����
	 * @param ctId
	 * @return
	 */
	public List<BCustomerTarget> getGoalMessegeByALL(BCustomerTarget target);
	
	/**
	 * ��֤����count
	 * @param target
	 * @return
	 */
	public int getKunnrGoalCount(BCustomerTarget target);
	/**
	 * ��֤����
	 * @param target
	 * @return
	 */
	public List<BCustomerTarget> getKunnrGoalAll(BCustomerTarget target);
	
	/**
	 * �ж�csv�����������֯�Ƿ������ϵͳ
	 * @param kunnr
	 * @return
	 */
	public List<OrgHelps> getOrgIsExit(Kunnr kunnr);
	
	/**
	 * ��֤�û�Ȩ�޵�
	 * @return
	 */
	public int getConpointByUser(String userId,String conpointId);
	

	/**
	 * ��ȡ���ϼ��۸�listCout
	 * 
	 * @param mat
	 * @return
	 */
	public int getMaterielViewListCount(Materiel mat);

	/**
	 * ��ȡ���ϼ��۸�list
	 * 
	 * @param mat
	 * @return
	 */
	public List<Materiel> getMaterielViewList(Materiel mat);
	
	/**
	 * ���ݴ�������ά�Ȳ�Ŀ������Ϣ
	 * @param target
	 * @return
	 */
	public BCustomerTarget getGoalByCondition(BCustomerTarget target);
	
	/**
	 * Ŀ�����ϼ�
	 * @return
	 */
	public double getGoalCountNum(BCustomerTarget target);
	/**
	 * ����ɾ������ʱ�ᱨ�ľ�����ռ��Ŀ����
	 * @param target
	 * @return
	 */
	public int deleteKunnrGoalAppply(BCustomerTarget target);
	
	/**
	 * ��ѯԤ�����϶���
	 * @param object
	 * @return
	 */
	public int searchMatterPriceCount(MatterPriceObject object);
	public List<MatterPriceObject> searchMatterPriceList(MatterPriceObject object);
	
	public MatterPriceObject getMatterPriceObject(MatterPriceObject object);
	
	public StringResult createOrUpdateGoal(MatterPriceObject object);

	/**
	 * ��ѯԤ�����϶���(BO����) 
	 */
	public int searchMatterPriceBoCount(MatterPriceBO priceBo);

	public List<MatterPriceBO> searchMatterPriceBoList(MatterPriceBO priceBo);
	/**
	 * ���浼����ļ�
	 * @param 
	 * @return
	 */

	public void insertMarPrice(MatterPriceBO priceBo);

	public List<BCustomerTarget> getGoalReportList(BCustomerTarget bct);

	public BCustomerTarget getGoalContract(BCustomerTarget c);

	public List<BCustomerTarget> getGoalDK(BCustomerTarget c);

	public int getGoalSumCount(BCustomerTarget bct);

	public List<BCustomerTarget> getGoalDKAll(BCustomerTarget bct);

	public List<BCustomerTarget> getGoalSum(BCustomerTarget bct);

	public BCustomerTarget getGoalContractSum(BCustomerTarget c);
	
	public Map<String,Object> importGoalSalesXls(File fileContent,String userId);
	public List<AllUsers> getStationIdByUserId(String userId);
	public Long createGoalSalesChange(GoalSales goalSales);
	public int searchGoalSalesChangeListCount(GoalSales goalSales);
	public List<GoalSales> searchGoalSalesChangeList(GoalSales goalSales);
	public List<GoalSalesDetail> searchGoalSalesChangeDetailList(GoalSalesDetail goalSalesDetail);
	public Double getSalesGoalCGDetailSum(GoalSalesDetail goalSalesDetail);
	public int updateGoalSalesChange(GoalSales goalSales);
	public Long createGoalSalesChangeDetail(GoalSalesDetail goalSalesDetail);
	public int getGoalDKCount(BCustomerTarget bct);
	public int getGoalDKAllCount(BCustomerTarget bct);
	/**
	 * ��ƮƮЭ��Ŀ������ӡ��һҳ
	 * @param printConGoalInfo
	 * @return
	 */
	public List<PrintContractGoalInfo> searchConGolInfo(PrintContractGoalInfo printConGoalInfo);
	/**
	 * ��ƮƮЭ��Ŀ������ӡ�ڶ�ҳ
	 * @param printConGoalInfo
	 * @return
	 */
	public List<PrintContractGoalInfo> searchConGolInfo2(
			PrintContractGoalInfo printConGoalInfo);
	/**
	 * MECO����԰Э��Ŀ������ӡ��һҳ
	 * @param printConGoalInfo
	 * @return
	 */
	public List<PrintContractGoalInfo> searchConGolInfo3(
			PrintContractGoalInfo printConGoalInfo);
	public int getKunnrListForNormalCount(Kunnr kunnr);
	public List<Kunnr> getKunnrListForNormal(Kunnr kunnr);

	public int getGoalListCountForMBL(BCustomerTarget bct);

	public List<BCustomerTarget> getGoalReportListForMBL(BCustomerTarget bct);

	public int getDistributionGoalTotal(BCustomerTarget bct);

	public List<BCustomerTarget> getDistributionGoalList(BCustomerTarget bct);

	public Double getSalesGoalFXDetailSum(GoalSalesDetail gsd);

	public Long createGoalDistributionChange(GoalSales goalSales);

	public Long createGoalFXChangeDetail(GoalSalesDetail gsdTo);

	public List<GoalSales> searchGoalFXChangeList(GoalSales goalSales);

	public List<GoalSalesDetail> searchGoalFXChangeDetailList(
			GoalSalesDetail goalSalesDetail);

	public boolean updateFXGoal(GoalSalesDetail bct);

	public int searchGoalFXChangeListCount(GoalSales goalSales);

	public int updateGoalFXChange(GoalSales goalSales);

	public boolean insertFXGoal(BCustomerTarget bt);

	public int searchSku(String sku);

	public int searchSkuText(String skuText);

	public List<BCustomerTarget> getMaktxAndMaknr(Materiel mat);

}
