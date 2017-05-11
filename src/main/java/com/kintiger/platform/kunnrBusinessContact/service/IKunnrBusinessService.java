package com.kintiger.platform.kunnrBusinessContact.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdjustment;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdujstDetail;


/**
 * ������
 * 
 * @author xxping
 * 
 */
public interface IKunnrBusinessService {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";


	/**
	 * ��������ϸ��Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrBusiness getKunnrBusiness(Kunnr kunnr);
	public StringResult updateKunnrBusiness(KunnrBusiness kunnrBusiness);
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
	 * Title: �������ϸ����
	 * Description: crmPlatform
	 * @author: xg.chen
	 * @date 2016��7��14�� ����3:22:44
	 * @param uploadFile �ļ���
	 * @param userId �û�ID
	 * @param orgId �û���֯ID
	 * @param kunnId ��ѡ�ľ�����ID
	 * @param yearMoth �����ֵ����֮����
	 * @return map
	 */
	public Map<String, Object> importDealerAdjustmentXls(File uploadFile,String userId,String orgId,String kunnId,int yearMoth);
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
	 * Title: �������ᱨ����֮�ܵ�����
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��23�� ����11:34:56
	 * @param dealer
	 */
	public String createDealerAdjustMennt(DealerAdjustment dealerAdjustment);
	/**
	 * Title: �������ᱨ����֮��ϸ����
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016��5��23�� ����11:34:56
	 * @param dealer
	 */
	public String createDealerAdjustDetail(DealerAdujstDetail detail);
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
	 * @date 2016��6��8�� ����3:38:32
	 * @param dealerAdujstDetail
	 * @return
	 */
	public List<DealerAdujstDetail> getKunnrForCrmTarget(DealerAdujstDetail dealerAdujstDetail);	
	
}
