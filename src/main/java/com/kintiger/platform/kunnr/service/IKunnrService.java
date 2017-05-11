package com.kintiger.platform.kunnr.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrAcount;
import com.kintiger.platform.kunnr.pojo.KunnrAddress;
import com.kintiger.platform.kunnr.pojo.KunnrApplySave;
import com.kintiger.platform.kunnr.pojo.KunnrBrand;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnr.pojo.KunnrLicense;
import com.kintiger.platform.kunnr.pojo.KunnrLogisticsArea;
import com.kintiger.platform.kunnr.pojo.KunnrSalesArea;
import com.kintiger.platform.kunnr.pojo.KunnrSapCodeObject;
import com.kintiger.platform.org.pojo.Borg;

/**
 * ������
 * 
 * @author xxping
 * 
 */
public interface IKunnrService {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	static final String DB_SUCCESS="���ݱ������ݿ�ɹ�.";
	static final String DB_FAIL="���ݱ������ݿ�ʧ��.����ϵϵͳ����Ա!";
	static final String SAP_SUCCESS="���ݴ���SAP�ɹ�.";
	static final String SAP_FAIL="���ݴ���SAPʧ��,�����Ի���ϵϵͳ����Ա!";



	

	/**
	 * �����̱�������
	 * 
	 * @return
	 */
	public String getRanKunnrCode();
	/**
	 * sap�������λ
	 * @return
	 */
	public String getRanKunnrSapCode() ;
	
	/**
	 * �����̴��������
	 * 
	 * @return
	 */
	public String getRanKunnrDMCode();
	/**
	 * ������sap�������У�ʡ/����+(�ó����������+1)
	 *������ʡ+����
	 * @return
	 */
	public String getRanKunnrCodeNew(KunnrSapCodeObject obj);
	
	/**
	 * �޸ľ�����sap������ɱ�
	 * @param kunnrApply
	 */
	public boolean updateKunnrSapCodeStatus(KunnrSapCodeObject obj);

	/**
	 * �����̿�����������
	 * 
	 * @param business
	 * @param upload
	 * @param uploadFileName
	 * @param key
	 *            �ļ��A
	 */
	public void saveAttachments(KunnrBusiness business, File[] upload,
			String[] uploadFileName, String key);

	/**
	 * * �����̿���֤�ձ���
	 * 
	 * @param kunnrLicenseList
	 * @param licenseName
	 * @param license
	 * @param licenseFileName
	 * @param licenseValid
	 */
	public void saveLicenses(List<KunnrLicense> kunnrLicenseList,
			String[] licenseName, File[] license, String[] licenseFileName,
			Date[] licenseValid);

	/**
	 * * ���Ʊ��֤��
	 * 
	 * @param kunnrLicenseList
	 * @param licenseName
	 * @param license
	 * @param licenseFileName
	 * @param licenseValid
	 */
	public void saveCustNameFlie(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key);

	/**
	 * �����̶���ػ���������
	 * 
	 * @param kunnr
	 * @param upload
	 * @param uploadFileName
	 * @param key
	 *            �ļ��A
	 */
	public void saveAttachments(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key);
	/**
	 * ���N���_�� ���
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrOpen(Kunnr kunnr);

	/**
	 * �����̶���
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrFreeze(Kunnr kunnr);
	
	/**
	 * ȡ��sap�����̶���״̬
	 * 
	 * @param kunnr
	 * @return
	 */
	public String kunnrFreezeCallCancelRFC(Kunnr kunnr);

	/**
	 * �����̹غ�
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrClose(Kunnr kunnr);

	/**
	 * �������޸�
	 * 
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrUpdate(Kunnr kunnr);

	/**
	 * 
	 * �����̲�ѯCOUNT
	 * 
	 * @return
	 */
	public int kunnrSearchCount(Kunnr kunnr);

	/**
	 * 
	 * �����б��̲�ѯ
	 * 
	 * @return
	 */
	public List<Kunnr> kunnrSearch(Kunnr kunnr);

	/**
	 * 
	 * ��������Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public Kunnr getKunnrEntity(Kunnr kunnr);

	/**
	 * ��������ϸ��Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public KunnrBusiness getKunnrBusinessEntity(Kunnr kunnr);

	/**
	 * �����̵�ַ��Ϣ�б� ���ʹ﷽
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrAddress> getKunnrAddressList(Kunnr kunnr);

	/**
	 * ������Ʒ���б�
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrBrand> getKunnrBrandList(Kunnr kunnr);

	/**
	 * �������ۿ�˵���б�
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrAcount> getKunnrAcountList(Kunnr kunnr);

	/**
	 * ������֤����Ϣ
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrLicense> getKunnrLicenseList(Kunnr kunnr);
	
	/**
	 * ������Ŀ����
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<BCustomerTarget> getBCustomerTargetList(Kunnr kunnr);
	
	/**
	 * ���������۷�Χ
	 * 
	 * @param kunnr
	 * @return
	 */
	public List<KunnrSalesArea> getKunnrSalesAreaList(Kunnr kunnr);

	/**
	 * ���������ѯ
	 * @return
	 */
	public List<KunnrLogisticsArea> getKunnrLogisticsArea(KunnrLogisticsArea area);
	public int getKunnrLogisticsAreaCount(KunnrLogisticsArea area);
	
	
	public BooleanResult updateLogisticArea(List<KunnrLogisticsArea> areaList);
	
	/**
	 * ��֤�Ƿ��Ǵ˽�ɫ�û�
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int getRoleOnEventByUser(String userId,String roleId);
	
	/**
	 * �޸ľ������ۿ�
	 * @param areaList
	 * @return
	 */
	public BooleanResult updateKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr);
	/**
	 * ����������ͼ
	 * @param kunnr
	 * @return
	 */
	public BooleanResult kunnrFreezeXview(Kunnr kunnr);
	
	/**
	 * ����ռ��Ŀ����
	 * @param areaList
	 * @return
	 */
	public BooleanResult createTarget(List<BCustomerTarget> targets,
			Kunnr kunnr);
	
	/**
	 * �۳�����ʱռ�õ�Ŀ����
	 * @param areaList
	 * @return
	 */
	public BooleanResult updateKunnrTarget(Kunnr kunnr);
	/**
	 * �����̿����ݴ�
	 * @param kunnrApply
	 * @return
	 */
	public BooleanResult kunnrApplySave(KunnrApplySave kunnrApply);
	/**
	 * �������ᱨ�洢���ѯ
	 * @param kunnrApply
	 * @return
	 */
	public int kunnrApplySaveSearchCount(KunnrApplySave kunnrApply);
	public List<KunnrApplySave> kunnrApplySaveSearch(KunnrApplySave kunnrApply);
	/**
	 * �޸ľ����̿������湦�ܱ�
	 * @param kunnrApply
	 */
	public boolean updateKunnrApplySave(KunnrApplySave kunnrApply);
	/**
	 * ���¾�����״̬  status 1��������2�������˻���3���ػ���
	 * @param kunnr
	 * @return
	 */
	public boolean updateKunnrStatus(Kunnr kunnr);
	/**
	 * �����̹�Ա�����޸�
	 * @param kunnr
	 * @return
	 */
	public boolean updateKunnrUserStaff(Kunnr kunnr);
	/**
	 * �޸����ϲ�ȫ���˷��������̱���
	 * @author cg.jiang
	 * @param eventId
	 */
	public boolean modifyProcessVariable(String eventId);
	/**
	 * �жϷ����˵Ľ�ɫ
	 * @author sl.zhu
	 */
	public boolean getOfficeRole(String userId, String roleId);
	/**
	 * �������۷�Χ
	 * @author cg.jiang
	 * @param kunnr
	 * @return
	 */
	public boolean createKunnrSalesArea(Kunnr kunnr);
	/**
	 * ����borg������֯id,����֯����,���������֯ID,��ƥ��ɹ�
	 * @param borg
	 * @return
	 */
	public String getCityOrgId(Borg borg);
}
