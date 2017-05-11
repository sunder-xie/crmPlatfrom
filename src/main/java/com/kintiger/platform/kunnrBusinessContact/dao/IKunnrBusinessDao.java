package com.kintiger.platform.kunnrBusinessContact.dao;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdjustment;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdujstDetail;

public interface IKunnrBusinessDao {

	/**
	 * ��������ϸ��Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrBusiness getKunnrBusiness(Kunnr kunnr);
	public int updateKunnrBusiness(KunnrBusiness kunnrBusiness);
	public List<KunnrBusiness> getKunnrBusinessManagerList(KunnrBusiness kunnrBusiness);
	public List<KunnrBusiness> getKunnrBusinessHeadList(KunnrBusiness kunnrBusiness);
	public List<KunnrBusiness> getKunnrBusinessAgentList(KunnrBusiness kunnrBusiness);
	public int updateHead(KunnrBusiness kunnrBusiness);
	public int updateAgent(KunnrBusiness kunnrBusiness);
	public int updateBusinessManager(KunnrBusiness kunnrBusiness);
	public int createHead(KunnrBusiness kunnrBusiness);
	public int createBusinessManager(KunnrBusiness kunnrBusiness);
	public int createAgent(KunnrBusiness kunnrBusiness);
	
	public List<String> getKunnrIdByHeadOrAgent(String userId);
	public List<String> getKunnrIdByCompetent(String userId);
	public List<String> getKunnrIdByKunnrBusiness(String userId);
	public List<String> getKunnrIdByKunnrBusinessByKunag(String userId);
	
	public List<KunnrBusiness> exportForExcel(Kunnr kunnr);
	
	public int searchKunnrBusinessManagerListCount(KunnrBusiness KunnrBusiness);
	
	public List<KunnrBusiness> searchKunnrBusinessManagerList(KunnrBusiness KunnrBusiness);
	
	public int searchKunnrBusinessEmpListCount(KunnrBusiness KunnrBusiness);
	
	public List<KunnrBusiness> searchKunnrBusinessEmpList(KunnrBusiness KunnrBusiness);
	
	public int updateKunnrBusinessEmp(KunnrBusiness KunnrBusiness);
	/**
	 * Title: ͳ������
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��17�� ����12:30:46
	 * @param dealerAdjustment
	 * @return
	 */
	public int getDealerAdjustmentCount(DealerAdjustment dealerAdjustment);
	/**
	 * Title: ��ȡ�ᱨ���������б�
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��17�� ����12:30:52
	 * @param dealerAdjustment
	 * @return
	 */
	public List<DealerAdjustment> getDealerAdjustmentList(DealerAdjustment dealerAdjustment);
	/**
	 * Title: ��֤����������+����+��֯�Ƿ񱣳�һ��
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��19�� ����4:54:43
	 * @param detail
	 * @return
	 */
	public int getDealerAdjustmentDetailCount(DealerAdujstDetail detail);
	/**
	 * Title: ���ݵ���ľ����̡�Ʒ����·ݴ�����Ӧ�ġ�����Ŀ�������͡�������Ŀ������
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��20�� ����9:48:35
	 * @param detail
	 * @return
	 */
	public List<DealerAdujstDetail> getDealerAdjustmentDetailKunnr(DealerAdujstDetail detail);
	/**
	 * Title: Ʒ���ж�
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��20�� ����10:15:31
	 * @param matter
	 * @return
	 */
	public int getMattercount(DealerAdujstDetail detail);
	/**
	 * Title: �������ᱨ����֮��ϸ����
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��23�� ����11:34:56
	 * @param dealer
	 */
	public String createDealerAdjustDetail(DealerAdujstDetail dealer);
	/**
	 * Title: �������ᱨ����֮�ܵ�����
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��23�� ����11:34:56
	 * @param dealer
	 */
	public String createDealerAdjustMennt(DealerAdjustment dealerAdjustment);
	/**
	 * Title: �������굥֮�б�
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��24�� ����8:53:23
	 * @param dealerAdujstDetail
	 * @return
	 */
	public List<DealerAdujstDetail> getDealerAdjustDetailList(DealerAdujstDetail dealerAdujstDetail);
	/**
	 * Title: �����ύ�ɹ��󱣴������
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��24�� ����2:37:59
	 * @param dealerAdjustment
	 * @return
	 */
	public int updateDealerAdjustment(DealerAdjustment dealerAdjustment);
	/**
	 * Title: �ܵ�֮Id����
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��29�� ����2:31:04
	 * @param ids
	 * @return
	 */
	public DealerAdjustment getDealerAdjustmentById(String ids);
	/**
	 * Title: ��ϸ����
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��29�� ����3:20:02
	 * @param dealerAdjustment
	 * @return
	 */
	public List<DealerAdjustment> getDealerAdjustmentListForXls(DealerAdjustment dealerAdjustment);
	public DealerAdjustment getDealerAdjustment(DealerAdjustment dealerAdjustment1);
	public int updateDealerAdjustmentById(DealerAdjustment dealerAdjustment);
	public int updateCrmTbTarget(DealerAdujstDetail detail);
	/**
	 * Title: ��ȡ�����̵�Ŀ������ϸ
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��6��8�� ����3:39:05
	 * @param dealerAdujstDetail
	 * @return
	 */
	public List<DealerAdujstDetail> getKunnrForCrmTarget(DealerAdujstDetail dealerAdujstDetail);
	/**
	 * Title: �ҵ�userId������֯,�����¼���֯
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��7��13�� ����10:01:40
	 * @param userId
	 * @return
	 */
	public List<AllUsers> getOrgsByUserId(String userId);
	/**
	 * MethodsTitle: �����ļ���Ŀ��ȡƷ��
	 * @author: xg.chen
	 * @date:2017��1��9�� ����11:41:30
	 * @version 1.0
	 * @param detail
	 * @return
	 */
	public DealerAdujstDetail getMatnrAndMaktx(DealerAdujstDetail detail);

}
