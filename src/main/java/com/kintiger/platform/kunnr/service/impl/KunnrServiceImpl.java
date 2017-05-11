package com.kintiger.platform.kunnr.service.impl;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.sap.SAPConnectionBean;
import com.kintiger.platform.framework.util.DateUtil;
import com.kintiger.platform.framework.util.FileUtil;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.kunnr.dao.IKunnrAddressDao;
import com.kintiger.platform.kunnr.dao.IKunnrDao;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrAcount;
import com.kintiger.platform.kunnr.pojo.KunnrAddress;
import com.kintiger.platform.kunnr.pojo.KunnrApplySave;
import com.kintiger.platform.kunnr.pojo.KunnrBrand;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnr.pojo.KunnrLicense;
import com.kintiger.platform.kunnr.pojo.KunnrLogisticsArea;
import com.kintiger.platform.kunnr.pojo.KunnrRole;
import com.kintiger.platform.kunnr.pojo.KunnrSalesArea;
import com.kintiger.platform.kunnr.pojo.KunnrSapCodeObject;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.org.pojo.Borg;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Function;
import com.sap.mw.jco.JCO.ParameterList;
import com.sap.mw.jco.JCO.Structure;
import com.sap.mw.jco.JCO.Table;

public class KunnrServiceImpl implements IKunnrService {

	private static Log logger = LogFactory.getLog(KunnrServiceImpl.class);
	private String uploadFilePath;
	private String licenseFilePath;
	private IKunnrDao kunnrDao;
	private SAPConnectionBean sapConnection;

	public String getRanKunnrCode() {
		return kunnrDao.getRanKunnrCode();
	}

	public String getRanKunnrSapCode() {
		return kunnrDao.getRanKunnrSapCode();
	}
	public String getRanKunnrDMCode() {
		return kunnrDao.getRanKunnrDMCode();
	}
	/**
	 * ������sap�������У�ʡ/����+(�ó����������+1)
	 *������ʡ+����(����δ����)
	 * @return
	 */
	public String getRanKunnrCodeNew(KunnrSapCodeObject obj){
		   String code;
		try {
			StringBuffer b=new StringBuffer();
			   b.append(obj.getVkgrp()).append(obj.getBzirk());
			   code = kunnrDao.getRanKunnrCodeNew(b.toString());
			   obj.setCode(code);
			   kunnrDao.createKunnrSapCode(obj);
		} catch (Exception e) {
			logger.error(obj,e);
			return null;
		}
		return code;
	}
	public void saveAttachments(KunnrBusiness business, File[] upload,
			String[] uploadFileName, String key) {
		for (int i = 0; i < upload.length; i++) {
			if ((null != uploadFileName[i]||!"".equals(uploadFileName[i])) && uploadFileName[i].length() > 0) {
				String newFileName = UUID.randomUUID()
						+ FileUtil.getFileExtention(uploadFileName[i]);

				// �ļ�Ŀ¼
				String folderPath = uploadFilePath + "/" + key + "/"
						+ DateUtil.datetime("yyyyMM");

				File savedir = new File(folderPath);
				// ���Ŀ¼�����ڣ����½�
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				String path = folderPath + "/" + newFileName;
				File file = new File(path);
				FileUtil.saveAsFile(upload[i], file);
				if (i == 0) {
					business.setAccessFile(uploadFileName[i]);
					business.setAccessFilePath(path);
				}else if(i == 1){
					business.setExplainFile(uploadFileName[i]);
					business.setExplainFilePath(path);
				} else {
					business.setCustomerFile(uploadFileName[i]);
					business.setCustomerFilePath(path);
				}
			}
		}
	}

	public void saveLicenses(List<KunnrLicense> kunnrLicenseList,
			String[] licenseName, File[] license, String[] licenseFileName,
			Date[] licenseValid) {
		for (int i = 0; i < license.length; i++) {
			if (licenseFileName[i] != null && licenseFileName[i].length() > 0) {
				String newFileName = UUID.randomUUID()
						+ FileUtil.getFileExtention(licenseFileName[i]);

				// �ļ�Ŀ¼
				String folderPath = licenseFilePath;

				File savedir = new File(folderPath);
				// ���Ŀ¼�����ڣ����½�
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				String path = folderPath + "/" + newFileName;
				File file = new File(path);
				FileUtil.saveAsFile(license[i], file);
				KunnrLicense kunnrLicense = new KunnrLicense();
				kunnrLicense.setLicenseName(licenseName[i]);
				kunnrLicense.setLicenseFile(licenseFileName[i]);
				kunnrLicense.setLicensePath(path);
				kunnrLicense.setLicenseValid(licenseValid[i]);
				kunnrLicenseList.add(kunnrLicense);
			}
		}
	}

	@Transactional(rollbackFor = {Exception.class,SQLException.class}, propagation = Propagation.REQUIRED)
	public BooleanResult kunnrOpen(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
			// ����kunnr
			try {
				long kunnrId = kunnrDao.createKunnr(kunnr);
				KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
				// ����kunnr��ϸ��Ϣ
				business.setKunnr(kunnr.getKunnr());
				long kunnrBusinessId = kunnrDao.createKunnrBusiness(business);
				// ������ַ�ʹ﷽
				List<KunnrAddress> kunnrAddressList = kunnr.getKunnrAddressList();
				if (kunnrAddressList != null){
					for (int i = 0; i < kunnrAddressList.size(); i++) {
						String kunnrSd=kunnr.getKunnr() + "01";
						if(i==0){
							kunnrSd=kunnr.getKunnr() + "01";
						}else if(i>0&&i<10){
							kunnrSd = kunnr.getKunnr()+"0"+(i+1);
						}else{
							kunnrSd=kunnr.getKunnr()+(i+1);
						}
						kunnrAddressList.get(i).setKunnrSd(kunnrSd);
					}
					kunnr.setKunnrAddressList(kunnrAddressList);
					kunnrDao.createKunnrAddress(kunnrAddressList, kunnr.getKunnr());
				}
				// ����Ʒ��
				// List<KunnrBrand> kunnrBrandListt = kunnr.getKunnrBrandList();
				// kunnrDao.createKunnrBrand(kunnrBrandListt, kunnr.getKunnr());
				// �����ۿ�
				List<KunnrAcount> kunnrAcountList = kunnr.getKunnrAcountList();
				if (kunnrAcountList != null){
					kunnrDao.createKunnrAcount(kunnrAcountList, kunnr.getKunnr());
				}
				// ����֤��
				List<KunnrLicense> kunnrLicenseList = kunnr.getKunnrLicenseList();
				if (kunnrLicenseList != null){
					kunnrDao.createKunnrLicense(kunnrLicenseList, kunnr.getKunnr());
				}

				List<KunnrSalesArea> kunnrSalesAreaList = kunnr
						.getKunnrSalesAreaList();
				if (kunnrSalesAreaList != null){
					kunnrDao.createSaleArea(kunnrSalesAreaList, kunnr.getKunnr());
				}
				//�������û�����
				Long userId=kunnrDao.createKunnrUser(kunnr);
				KunnrRole role=new KunnrRole();
				role.setUserId(String.valueOf(userId));
				kunnrDao.createKunnrUserRole(role);
				result.setResult(true);
				//if (kunnrId > 0 && kunnrBusinessId > 0) {
				if(result.getResult()){
					// RFC���� �����̿���
					String rfcResult = kunnrOpenCallRFC(kunnr);
					if (IKunnrService.SUCCESS.equals(rfcResult)) {
						result.setResult(true);
						result.setCode(IKunnrService.SAP_SUCCESS);
						result.setCode(result.getCode() + "'\n" + "+'"
								+ IKunnrService.DB_SUCCESS);
						} else {
							result.setResult(false);
							result.setCode(IKunnrService.SAP_FAIL);
						}
				} else {
					result.setResult(false);
					result.setCode(result.getCode() + "'\n" + "+'"
							+ IKunnrService.DB_FAIL);
				}
			} catch (Exception e) {
				logger.error(e);
				result.setResult(false);
				result.setCode("���������쳣,����ϵ����Ա!");
			}
		return result;
	}
	/**
	 * �����̶���
	 * 
	 * @param kunnr
	 * @return
	 */
	//TODO:
	public BooleanResult kunnrFreeze(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		//���󷽷� RFC=>����KUNNR  C  ������
		String rfcResult = kunnrFreezeCallRFC(kunnr, "C");
		try {
			if (IKunnrService.SUCCESS.equals(rfcResult)) {
				//����crm_tb_kunnr��֮status=2  �ػ�
				result.setResult(true);
				result.setCode(IKunnrService.SAP_SUCCESS);
				kunnrDao.kunnrFreeze(kunnr);
				//���þ������û� basis_tb_kunnremp_info֮status
				Kunnr user=new Kunnr();
				user.setKunnr(kunnr.getKunnr());
				user.setStatus("N");
				kunnrDao.closeKunnrUser(user);
				//�ͷ��Ե�ǰ��֮����·ݾ�����Ŀ���� crm.crm_tb_target
				/*Kunnr kunnrTar=new Kunnr();
				kunnrTar.setKunnr(kunnr.getKunnr());
				kunnrDao.releaseKunnrTarget(kunnrTar);*/
				//�����̵�ǰ��֮����·ݵľ�����Ŀ�������crm.crm_tb_target
				Kunnr kunnrTar=new Kunnr();
				kunnrTar.setKunnr(kunnr.getKunnr());
				kunnrDao.deleteKunnrTarget(kunnrTar);
				
				result.setCode(result.getCode() + "'\n" + "+'"+ IKunnrService.DB_SUCCESS);
			} else {
				result.setResult(false);
				result.setCode(IKunnrService.SAP_FAIL);
			}
		} catch (Exception e) {
			logger.error(e);
			result.setResult(false);
			result.setCode(result.getCode() + "'\n" + "+'"+ IKunnrService.DB_FAIL);
		}
		return result;

	}

	public BooleanResult kunnrClose(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		try {
			result.setResult(kunnrDao.kunnrClose(kunnr));
			result.setCode(result.getCode() + "'\n" + "+'"
					+ IKunnrService.DB_SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			result.setResult(false);
			result.setCode(result.getCode() + "'\n" + "+'"
					+ IKunnrService.DB_FAIL);
		}
		return result;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public BooleanResult kunnrUpdate(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		String rfcResult = kunnrModifyCallRFC(kunnr);
		String rfcDeleteAreaResult = "";
		List<KunnrSalesArea> kills = kunnr.getKillSalesArea();
		if (kills.size() > 0) {
			rfcDeleteAreaResult = kunnrDelSalesAreaCallRFC(kunnr);
		}
		// String rfcResult="success";
		if ((IKunnrService.SUCCESS.equals(rfcResult) && IKunnrService.SUCCESS
				.equals(rfcDeleteAreaResult))
				|| (IKunnrService.SUCCESS.equals(rfcResult) && 0 == kills
						.size())) {
			result.setResult(true);
			result.setCode(IKunnrService.SAP_SUCCESS);
			// �޸�kunnr
			long kunnrId = kunnrDao.updateKunnr(kunnr);
			KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
			// �޸�kunnr��ϸ��Ϣ
			business.setKunnr(kunnr.getKunnr());
			long kunnrBusinessId = kunnrDao.updateKunnrBusiness(business);
			// �޸Ĵ�����ַ�ʹ﷽
			List<KunnrAddress> kunnrAddressList = kunnr.getKunnrAddressList();
			if (kunnrAddressList != null)
				kunnrDao.updateAndCreateKunnrAddress(kunnrAddressList,
						kunnr.getKunnr());
			// �޸Ĵ���Ʒ��
			List<KunnrBrand> kunnrBrandListt = kunnr.getKunnrBrandList();
			if (kunnrBrandListt != null)
				kunnrDao.updateAndCreateKunnrBrand(kunnrBrandListt,
						kunnr.getKunnr());
			// �޸Ĵ����ۿ�
			List<KunnrAcount> kunnrAcountList = kunnr.getKunnrAcountList();
			if (kunnrAcountList != null)
				kunnrDao.updateAndCreateKunnrAcount(kunnrAcountList,
						kunnr.getKunnr());
			// ����֤��
			List<KunnrLicense> kunnrLicenseList = kunnr.getKunnrLicenseList();
			if (kunnrLicenseList != null)
				kunnrDao.createKunnrLicense(kunnrLicenseList, kunnr.getKunnr());

			List<KunnrSalesArea> salesAreaList = kunnr.getKunnrSalesAreaList();
			if (salesAreaList != null)
				kunnrDao.updateAndCreateSalesArea(salesAreaList,
						kunnr.getKunnr());
			/*
			 * List<BCustomerTarget> targetList=kunnr.getTargetList();
			 * if(targetList!=null)
			 * kunnrDao.updateAndCreateBCustomerTarget(targetList, kunnr);
			 */
			// ɾ��Ʒ�Ƽ��ۿ�
			String killBrand = kunnr.getKillBrand();
			if (StringUtils.isNotEmpty(killBrand)) {
				kunnrDao.removeBrand(killBrand);
			}
			String killAcount = kunnr.getKillAcount();
			if (StringUtils.isNotEmpty(killAcount)) {
				kunnrDao.removeAcount(killAcount);
			}
			List<KunnrSalesArea> killSalesArea = kunnr.getKillSalesArea();
			if (null != killSalesArea) {
				kunnrDao.removeSalesArea(killSalesArea);
			}
			//�޸ľ������û���Ϣ
			kunnrDao.updateKunnrUser(kunnr);
			if (kunnrId > 0 && kunnrBusinessId > 0) {
				result.setResult(true);
				result.setCode(result.getCode() + "'\n" + "+'"
						+ IKunnrService.DB_SUCCESS);
			} else {
				result.setResult(false);
				result.setCode(result.getCode() + "'\n" + "+'"
						+ IKunnrService.DB_FAIL);
			}
		} else {
			result.setResult(false);
			result.setCode(IKunnrService.SAP_FAIL);
		}
		return result;
	}

	public int kunnrSearchCount(Kunnr kunnr) {
		try {
			return kunnrDao.kunnrSearchCount(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return 0;

		}
	}

	public List<Kunnr> kunnrSearch(Kunnr kunnr) {
		try {
			return kunnrDao.kunnrSearch(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public Kunnr getKunnrEntity(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrEntity(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public KunnrBusiness getKunnrBusinessEntity(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrBusinessEntity(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<KunnrAddress> getKunnrAddressList(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrAddressList(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<KunnrBrand> getKunnrBrandList(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrBrandList(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<KunnrAcount> getKunnrAcountList(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrAcountList(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<KunnrLicense> getKunnrLicenseList(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrLicenseList(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<BCustomerTarget> getBCustomerTargetList(Kunnr kunnr) {
		try {
			return kunnrDao.getBCustomerTargetList(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<KunnrSalesArea> getKunnrSalesAreaList(Kunnr kunnr) {
		try {
			return kunnrDao.getKunnrSalesAreaList(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * ���󷽷� RFC=>KUNNR
	 * 
	 * @param kunnr
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String kunnrOpenCallRFC(Kunnr kunnr) {
		// ����������
		KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
		// �ջ���ַ.�ʹ﷽
		List<KunnrAddress> addressList = kunnr.getKunnrAddressList();

		List<KunnrSalesArea> salesList = kunnr.getKunnrSalesAreaList();
		/**
		 * �칫��ַ�ο�λ��+�칫��������
		 */
		StringBuilder build1 = new StringBuilder();
		/*if (StringUtils.isNotEmpty(kunnr.getBgXzAddress())) {
			build1.append(kunnr.getBgXzAddress());
		}*/
		if (StringUtils.isNotEmpty(kunnr.getStreet1())) {
			build1.append(kunnr.getStreet1());
		}
		/**
		 * �ջ���������+����ĵ�ַ��Ϣ(ȡ��ƴ��)
		 */
		StringBuilder build = new StringBuilder();
		/*if (StringUtils.isNotEmpty(kunnr.getShXzAddress())) {
			build.append(kunnr.getShXzAddress());
		}*/
		if (StringUtils.isNotEmpty(kunnr.getStreet())) {
			build.append(kunnr.getStreet());
		}

		JCO.Client client = null;
		String result = "";
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_CREATE_CUSTOMER_RFC");
			// ���۷�Χ��� ѭ������
			for (KunnrSalesArea salesArea : salesList) {

				JCO.Function function = sapConnection.getFunction(client);
				// ������� =>������������
				int i = 1;
				JCO.ParameterList input = function.getImportParameterList();
				Structure structure = input.getStructure("I_CUSTOMERCREATE");
				structure.getField("KUNNR").setValue(kunnr.getKunnr());// ������SAP����
				structure.getField("BUKRS").setValue(kunnr.getBukrs());// ��˾����
				// ���۷�Χ
				structure.getField("VKORG").setValue(salesArea.getVkorg());// ������֯
				structure.getField("VTWEG").setValue(salesArea.getVtweg());// ��������
				structure.getField("SPART").setValue(salesArea.getSpart());// ��Ʒ��
				structure.getField("VSBED").setValue(salesArea.getVsbed());// װ������
				structure.getField("VWERK").setValue(salesArea.getWerks());// ��������

				structure.getField("KTOKD").setValue(kunnr.getKtokd());// �ͻ��ʻ���
				structure.getField("NAME1").setValue(kunnr.getName1());// ��˾ȫ��
				// structure.getField("NAME2").setValue(kunnr.getName2());

				structure.getField("NAME3").setValue(kunnr.getName3());// ���˴���
				// structure.getField("SORT1").setValue("12");������
				// structure.getField("SORT2").setValue("32");
				structure.getField("TELF1").setValue(kunnr.getKpPhone());// ��ϵ�绰kunnr.getMobNumber()�����ֻ�
																			// ��Ϊ��Ʊ�绰
				structure.getField("TELF2").setValue(kunnr.getMobNumber());// �ƶ��绰�����ֻ�
				structure.getField("TELFX").setValue(kunnr.getFaxNumber());// ����
				structure.getField("KONZS").setValue(kunnr.getKonzs());// �ϼ�������
				structure.getField("ADR_NOTES").setValue(kunnr.getKunag());// �ϼ�������
				structure.getField("KVERM").setValue(kunnr.getKverm());// ��˰������
				if ("С��ģ��˰��".equals(kunnr.getKverm())) {
					structure.getField("STCEG").setValue("");// С��ģ��˰�˲���д����ֵ˰�ǼǺ�
				} else {
					structure.getField("STCEG").setValue(kunnr.getStceg());// ��ֵ˰�ǼǺ�
				}
				structure.getField("LOCCO").setValue(
						kunnr.getLocco()
								.substring(2, kunnr.getLocco().length()));// �����
				structure.getField("KUKLA").setValue(business.getKukla());// �ͻ�����Z2;��Լz3;ֱӪz4
				structure.getField("NAME1_GP").setValue(kunnr.getZcAddress());// ��Ʊ��ַ����˾ע���ַ

				structure.getField("STR_SUPPL2").setValue(build1.toString());// kunnr.getStreet1(//�칫��ַ�ο�λ��+�칫�������������ŵ�ַ��

				structure.getField("STREET").setValue(build.toString());// �ջ���ַ���ջ���������+����ĵ�ַ��Ϣ��

				structure.getField("AKONT").setValue(business.getAkont());// ͳԦ��Ŀ
				structure.getField("ANRED").setValue(business.getTitleMedi());// ����
				structure.getField("ZTERM").setValue(business.getZterm());// ������������
				structure.getField("WAERS").setValue(business.getWaers());// ������
				structure.getField("KALKS").setValue(business.getKalks());// ���۹���
				structure.getField("VERSG").setValue(business.getVersg());// ͳ����
				structure.getField("PODKZ").setValue(business.getPodkz());// POD
				structure.getField("AUTLF").setValue(business.getAutlf());// һ���Խ���
				structure.getField("KZTLF").setValue(business.getKztlf());// ���ֽ���
				structure.getField("ANTLF").setValue(business.getAntlf());// ���ֽ��������
				structure.getField("BOKRE").setValue(business.getBokre());// �ؿ�
				structure.getField("KTGRD").setValue(business.getKtgrd());// �ͻ�����ʻ�����
				structure.getField("TAXKD_01").setValue(business.getTaxkd01());// �ͻ�˰����
				structure.getField("TAXKD").setValue(business.getTaxkd01());// �ͻ�˰����
				structure.getField("VKGRP").setValue(business.getBzirk());// ����ʡ��
				structure.getField("VKBUR").setValue(business.getVkbur());// ���۲���
				structure.getField("BZIRK").setValue(business.getVkgrp());// ���۴���
				// structure.getField("KVGR1").setValue(business.getKvgr1());
				// structure.getField("KVGR2").setValue(business.getKvgr2());
				structure.getField("LPRIO").setValue(business.getLprio());// ��������Ȩ
				/**
				 * �����������ݵ���������ȡ�ջ���ַ����������
				 * ֮ǰȡ���ǰ칫������ַ��kunnr.getProvince(),kunnr.getCity
				 * (),kunnr.getArea(),kunnr.getTown()��
				 */
				structure.getField("ZWL01").setValue(kunnr.getShProvince());// ʡ
				structure.getField("ZWL02").setValue(kunnr.getShCity());// ��
				structure.getField("ZWL03").setValue(kunnr.getShArea());// ��
				structure.getField("ZWL04").setValue(kunnr.getShTown());// ��

				structure.getField("LAND1").setValue(business.getCountry());// ����
				structure.getField("SPRAS").setValue(
						/* business.getLangu() */"1");// ����
				structure.getField("AUFSD").setValue("");// �����־
				structure.getField("VSART").setValue(kunnr.getMaximum()); // ����ͨ�г���
				structure.getField("BANKTEXT").setValue(
						kunnr.getBank() + kunnr.getBankAccount()); // ������Ϣ
				// �������=>�ջ���ϵ��
				JCO.Table knvk = function.getTableParameterList().getTable(
						"T_XKNVK");

				if (StringUtils.isNotEmpty(kunnr.getName102())
						&& (StringUtils.isNotEmpty(kunnr.getName102mob()) || StringUtils
								.isNotEmpty(kunnr.getName102tel()))) {
					// �ջ���ϵ��
					knvk.appendRow();
					knvk.setRow(1);
					knvk.getField("KUNNR").setValue(kunnr.getKunnr());
					knvk.getField("TELF1").setValue("");// �ջ��绰kunnr.getName102mob()
														// + "/" +
														// kunnr.getName102tel()
					knvk.getField("PAFKT").setValue("01");
					knvk.getField("ABTNR").setValue("");// "0001"
					StringBuilder MyStringBuilder1 = new StringBuilder(
							kunnr.getName102());
					if (StringUtils.isNotEmpty(kunnr.getName102tel())) {
						MyStringBuilder1.append(kunnr.getName102tel());
					} // �ջ���+��ϵ�绰
					knvk.getField("NAMEV")
							.setValue(MyStringBuilder1.toString());
					StringBuilder MyStringBuilder = new StringBuilder(
							kunnr.getName102());
					if (StringUtils.isNotEmpty(kunnr.getName102mob())) {
						MyStringBuilder.append(kunnr.getName102mob());
					} // �ջ���+��ϵ�绰
					knvk.getField("NAME1").setValue(MyStringBuilder.toString());// �ջ���ϵ��
					knvk.getField("ANRED").setValue(business.getTitleMedi());
				}
				if (StringUtils.isNotEmpty(kunnr.getTelNumber())) {
					// ��˾�绰
					knvk.appendRow();
					knvk.setRow(2);
					knvk.getField("KUNNR").setValue(kunnr.getKunnr());
					knvk.getField("TELF1").setValue("");// ��˾�绰kunnr.getTelNumber()
					knvk.getField("PAFKT").setValue("02");
					knvk.getField("ABTNR").setValue("");// "0001"
					/*
					 * StringBuilder MyStringBuilder = new
					 * StringBuilder(kunnr.getName3());
					 * MyStringBuilder.append(kunnr.getTelNumber());
					 */// ���˴���+��˾��ϵ�绰
					knvk.getField("NAME1").setValue(kunnr.getTelNumber());//
					knvk.getField("ANRED").setValue(business.getTitleMedi());
				}

				// ������ʱ��ͬʱ�����ʹ﷽
				// �������=>�ʹ﷽,���ջ���ַ!������ͼ���ݲ��վ�����
				JCO.Table zust = function.getTableParameterList().getTable(
						"ZCUSTOMER_SH_CREATE");
				if (addressList != null && addressList.size() > 0) {
					for (KunnrAddress address : addressList) {
						zust.appendRow();
						zust.setRow(i);
						address.setKunnrSd(kunnr.getKunnr() + "0" + i);
						zust.getField("KUNNR").setValue(address.getKunnrSd());// ����
						zust.getField("VKORG").setValue(
						/* kunnr */salesArea.getVkorg());// ������֯
						zust.getField("VTWEG").setValue(salesArea.getVtweg());// ��������
						zust.getField("SPART").setValue(salesArea.getSpart());// ��Ʒ��
						zust.getField("VSBED").setValue(salesArea.getVsbed()); // װ������
						zust.getField("VWERK").setValue(salesArea.getWerks()); // ��������

						zust.getField("KTOKD").setValue("Z002");// �ͻ���Ŀ��
																// 002�ʹ﷽
						StringBuilder addressBuild = new StringBuilder();
						if (StringUtils.isNotEmpty(address.getXzAddress())) {
							addressBuild.append(address.getXzAddress());
						}
						if (StringUtils.isNotEmpty(address.getAddress())) {
							addressBuild.append(address.getAddress());
						}
						zust.getField("NAME1")                                //�ֶνϳ���������
								.setValue(addressBuild.toString());// ����1
						/*zust.getField("NAME2")
						.setValue(addressBuild.toString().substring(30, addressBuild.toString().length()));// ����2
*/						zust.getField("STRAS")
								.setValue(addressBuild.toString());// ��ַ

						zust.getField("VSART").setValue(address.getMaximum()); // ����ͨ�г���
						zust.getField("C_TELF1").setValue("");// �绰address.getMobile()
																// + "/" +
																// address.getTelephone()
						zust.getField("C_PAFKT").setValue("01");
						zust.getField("C_ABTNR").setValue("");// "0001"
						StringBuilder b1 = new StringBuilder(address.getName());
						if (StringUtils.isNotEmpty(address.getMobile())) {
							b1.append(address.getMobile());
						}
						zust.getField("C_NAMEV").setValue(b1.toString());
						StringBuilder b = new StringBuilder(address.getName());
						if (StringUtils.isNotEmpty(address.getTelephone())) {
							b.append(address.getTelephone());
						}
						zust.getField("C_NAME1").setValue(b.toString());// ��ϵ��address.getName()
						zust.getField("ANRED")
								.setValue(business.getTitleMedi());// ����
						zust.getField("LAND1").setValue(business.getCountry());// ����
						zust.getField("SPRAS").setValue("1");// ����business.getLangu()
						zust.getField("C_ANRED").setValue(
								business.getTitleMedi());
						zust.getField("WAERS").setValue(business.getWaers());
						zust.getField("VERSG").setValue(business.getVersg());
						// zust.getField("KVGR2").setValue(business.getKvgr2());

						zust.getField("PODKZ").setValue(business.getPodkz());
						zust.getField("LPRIO").setValue(business.getLprio());
						zust.getField("AUTLF").setValue(business.getAutlf());
						zust.getField("KZTLF").setValue(business.getKztlf());
						zust.getField("ANTLF").setValue(business.getAntlf());
						zust.getField("TAXKD").setValue(business.getTaxkd01());
						// **ȡ�ʹ﷽��������
						zust.getField("ZWL01").setValue(address.getProvince());
						zust.getField("ZWL02").setValue(address.getCity());
						zust.getField("ZWL03").setValue(address.getArea());
						zust.getField("ZWL04").setValue(address.getTown());
						i++;
					}
				}
				client.execute(function);

				// �������=>���
				JCO.ParameterList export = function.getExportParameterList();
				String s = export.getField("RETURNCODE").getString();
				if ("0".equals(s)) {
					result = IKunnrService.SUCCESS;
				} else {
					// һ�ε���ʧ�� ������
					result = IKunnrService.ERROR;
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e);
			result = IKunnrService.ERROR;
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}

	/**
	 * ���󷽷� RFC=>KUNNR
	 * 
	 * @param kunnr
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String kunnrModifyCallRFC(Kunnr kunnr) {
		// ����������
		KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
		//List<KunnrSalesArea> salesAreaList = kunnr.getKunnrSalesAreaList();
		//List<KunnrAddress> addressList = kunnr.getKunnrAddressList();
		/**
		 * �칫��ַ�ο�λ��+�칫��������
		 */
		StringBuilder build1 = new StringBuilder();
		/*if (StringUtils.isNotEmpty(kunnr.getBgXzAddress())) {
			build1.append(kunnr.getBgXzAddress());
		}*/
		if (StringUtils.isNotEmpty(kunnr.getStreet1())) {
			build1.append(kunnr.getStreet1());
		}
		/**
		 * �ջ���������+����ĵ�ַ��Ϣ
		 */
		StringBuilder build = new StringBuilder();
		/*if (StringUtils.isNotEmpty(kunnr.getShXzAddress())) {
			build.append(kunnr.getShXzAddress());
		}*/
		if (StringUtils.isNotEmpty(kunnr.getStreet())) {
			build.append(kunnr.getStreet());
		}

		// String rfcResult="success";
		JCO.Client client = null;
		String result = "";
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_MODIFYCUSTOMER_RFC");
			// ���۷�Χ��� ѭ������
			//for (KunnrSalesArea salesArea : salesAreaList) {//jcg20161124��sapȡ���۷�Χ
				JCO.Function function = sapConnection.getFunction(client);
				// ������� =>������������
				JCO.ParameterList input = function.getImportParameterList();
				Structure structure = input.getStructure("I_CUSTOMERCREATE");
				structure.getField("KUNNR").setValue(kunnr.getKunnr());// ������SAP����
				structure.getField("BUKRS").setValue(kunnr.getBukrs());// ��˾����

				//jcg20161124 ע�͵�
				/**structure.getField("VKORG").setValue(salesArea.getVkorg());// ������֯
				structure.getField("VTWEG").setValue(salesArea.getVtweg());// ��������
				structure.getField("SPART").setValue(salesArea.getSpart());// ��Ʒ��
				structure.getField("VSBED").setValue(salesArea.getVsbed());// װ������
				structure.getField("VWERK").setValue(salesArea.getWerks());// ��������
*/
				structure.getField("KTOKD").setValue(kunnr.getKtokd());// �ͻ��ʻ���
				structure.getField("NAME1").setValue(kunnr.getName1());// ��˾ȫ��
				// structure.getField("NAME2").setValue(kunnr.getName2());
				structure.getField("NAME3").setValue(kunnr.getName3());// ���˴���
				// structure.getField("SORT1").setValue("12");������
				// structure.getField("SORT2").setValue("32");
				structure.getField("TELF1").setValue(kunnr.getKpPhone());// ��ϵ�绰
				structure.getField("TELF2").setValue(kunnr.getMobNumber());// �ƶ��绰
				structure.getField("TELFX").setValue(kunnr.getFaxNumber());// ����
				structure.getField("KONZS").setValue(kunnr.getKonzs());// �ϼ�������
				structure.getField("ADR_NOTES").setValue(kunnr.getKunag());// ����������
				structure.getField("LOCCO").setValue(
						kunnr.getLocco()
								.substring(2, kunnr.getLocco().length()));// �����
				structure.getField("KUKLA").setValue(business.getKukla());// �ͻ�����
				structure.getField("NAME1_GP").setValue(kunnr.getZcAddress());// ��Ʊ��ַ����˾�칫��ַ

				structure.getField("STR_SUPPL2").setValue(build1.toString());// kunnr.getStreet1(//�칫��ַ�ο�λ�ã�ͨ�ŵ�ַ��
				structure.getField("STREET").setValue(build.toString());// �ջ���ַ

				structure.getField("VSART").setValue(kunnr.getMaximum()); // ����ͨ�г���

				structure.getField("KVERM").setValue(kunnr.getKverm());// ��˰������
				if ("С��ģ��˰��".equals(kunnr.getKverm())) {
					structure.getField("STCEG").setValue("");// С��ģ��˰�˲���д����ֵ˰�ǼǺ�
				} else {
					structure.getField("STCEG").setValue(kunnr.getStceg());// ��ֵ˰�ǼǺ�
				}
				structure.getField("AKONT").setValue(business.getAkont());// ͳԦ��Ŀ
				structure.getField("ANRED").setValue(business.getTitleMedi());// ����
				structure.getField("ZTERM").setValue(business.getZterm());// ������������
				structure.getField("WAERS").setValue(business.getWaers());// ������
				structure.getField("KALKS").setValue(business.getKalks());// ���۹���
				structure.getField("VERSG").setValue(business.getVersg());// ͳ����

				structure.getField("PODKZ").setValue(business.getPodkz());// POD
				structure.getField("AUTLF").setValue(business.getAutlf());// һ���Խ���
				structure.getField("KZTLF").setValue(business.getKztlf());// ���ֽ���
				structure.getField("ANTLF").setValue(business.getAntlf());// ���ֽ��������
				structure.getField("BOKRE").setValue(business.getBokre());// �ؿ�
				structure.getField("KTGRD").setValue(business.getKtgrd());// �ͻ�����ʻ�����
				structure.getField("TAXKD_01").setValue(business.getTaxkd01());// �ͻ�˰����
				structure.getField("TAXKD").setValue(business.getTaxkd01());// �ͻ�˰����
				structure.getField("VKGRP").setValue(business.getBzirk());// ����ʡ��
				structure.getField("VKBUR").setValue(business.getVkbur());// ���۲���
				structure.getField("BZIRK").setValue(business.getVkgrp());// ���۴���
				// structure.getField("KVGR1").setValue(business.getKvgr1());
				// structure.getField("KVGR2").setValue(business.getKvgr2());
				structure.getField("LPRIO").setValue(business.getLprio());// ��������Ȩ
				structure.getField("ZWL01").setValue(kunnr.getShProvince());// ʡ
				structure.getField("ZWL02").setValue(kunnr.getShCity());// ��
				structure.getField("ZWL03").setValue(kunnr.getShArea());// ��
				structure.getField("ZWL04").setValue(kunnr.getShTown());// ��
				structure.getField("LAND1").setValue(business.getCountry());// ����
				structure.getField("SPRAS").setValue("1");// ����business.getLangu()
				structure.getField("AUFSD").setValue("");// �����־
				structure.getField("BANKTEXT").setValue(
						kunnr.getBank() + kunnr.getBankAccount()); // ������Ϣ

				// �������=>�ջ���ϵ��
				JCO.Table knvk = function.getTableParameterList().getTable(
						"T_XKNVK");
				if (StringUtils.isNotEmpty(kunnr.getName102())
						&& (StringUtils.isNotEmpty(kunnr.getName102mob()) || StringUtils
								.isNotEmpty(kunnr.getName102tel()))) {
					knvk.appendRow();
					knvk.setRow(1);
					knvk.getField("KUNNR").setValue(kunnr.getKunnr());
					knvk.getField("TELF1").setValue("");// �ջ��绰
					knvk.getField("PAFKT").setValue("01");
					knvk.getField("ABTNR").setValue("");// "0001"
					StringBuilder builder1 = new StringBuilder(
							kunnr.getName102());
					if (StringUtils.isNotEmpty(kunnr.getName102tel())) {
						builder1.append(kunnr.getName102tel());
					}
					knvk.getField("NAMEV").setValue(builder1.toString());
					StringBuilder builder = new StringBuilder(
							kunnr.getName102());
					if (StringUtils.isNotEmpty(kunnr.getName102mob())) {
						builder.append(kunnr.getName102mob());
					}
					knvk.getField("NAME1").setValue(builder.toString());// �ջ���ϵ��
					knvk.getField("ANRED").setValue(business.getTitleMedi());
				}
				// ��˾�绰
				if (StringUtils.isNotEmpty(kunnr.getTelNumber())) {
					knvk.appendRow();
					knvk.setRow(2);
					knvk.getField("KUNNR").setValue(kunnr.getKunnr());
					knvk.getField("TELF1").setValue("");// ��˾�绰kunnr.getTelNumber()
					knvk.getField("PAFKT").setValue("02");
					knvk.getField("ABTNR").setValue("");// 0001
					/*
					 * StringBuilder builder=new
					 * StringBuilder(kunnr.getName3());
					 * builder.append(kunnr.getTelNumber());
					 */
					knvk.getField("NAME1").setValue(kunnr.getTelNumber());// ��˾�̶��绰
					knvk.getField("ANRED").setValue(business.getTitleMedi());
				}
//zsl
				/*
				 * // �������=>�ʹ﷽,���ջ���ַ!������ͼ���ݲ��վ����� JCO.Structure zust = input
				 * .getStructure("ZCUSTOMER_SH_CREATE");
				 * 
				 * KunnrAddress address = addressList.get(0);
				 * address.setKunnrSd(kunnr.getKunnr() + "0" + i);
				 * zust.getField("KUNNR").setValue(address.getKunnrSd());// ����
				 * zust.getField("VKORG").setValue(kunnr.getVkorg());// ������֯
				 * zust.getField("VTWEG").setValue(vtweg);// ��������
				 * zust.getField("SPART").setValue(spart);// ��Ʒ��
				 * zust.getField("KTOKD").setValue("Z002");// �ͻ���Ŀ�� // 002�ʹ﷽
				 * zust.getField("NAME1").setValue(address.getAddress());// ����
				 * zust.getField("STRAS").setValue(address.getAddress());// ��ַ
				 * zust.getField("C_TELF1").setValue( address.getMobile() + "/"
				 * + address.getTelephone());// �绰
				 * zust.getField("C_PAFKT").setValue("01");
				 * zust.getField("C_ABTNR").setValue("0001"); //
				 * zust.getField("C_NAMEV").setValue("����");
				 * zust.getField("C_NAME1").setValue(address.getName());// ��ϵ��
				 * zust.getField("ANRED").setValue(business.getTitleMedi( ));//
				 * ���� zust.getField("LAND1").setValue(business.getCountry());//
				 * ���� zust.getField("SPRAS").setValue(business.getLangu());// ����
				 * zust.getField("C_ANRED").setValue(business.getTitleMedi ());
				 * zust.getField("WAERS").setValue(business.getWaers());
				 * zust.getField("VERSG").setValue(business.getVersg()); //
				 * zust.getField("KVGR2").setValue(business.getKvgr2());
				 * zust.getField("VSBED").setValue(business.getVsbed());
				 * zust.getField("VWERK").setValue(business.getWerks());
				 * zust.getField("PODKZ").setValue(business.getPodkz());
				 * zust.getField("LPRIO").setValue(business.getLprio());
				 * zust.getField("AUTLF").setValue(business.getAutlf());
				 * zust.getField("KZTLF").setValue(business.getKztlf());
				 * zust.getField("ANTLF").setValue(business.getAntlf());
				 * zust.getField("TAXKD").setValue(business.getTaxkd01());
				 * zust.getField("ZWL01").setValue(kunnr.getProvince());
				 * zust.getField("ZWL02").setValue(kunnr.getCity());
				 * zust.getField("ZWL03").setValue(kunnr.getArea());
				 * zust.getField("ZWL04").setValue(kunnr.getTown());
				 */
				// ִ��
				client.execute(function);
				// �������=>���
				JCO.ParameterList export = function.getExportParameterList();
				String s = export.getField("RETURNCODE").getString();
				if ("0".equals(s)) {
					result = IKunnrService.SUCCESS;
				} else {
					// һ�ε���ʧ�� ������
					result = IKunnrService.ERROR;
					//break;
				}
				// }
			//}
				} catch (Exception e) {
			logger.error(e);
			result = IKunnrService.ERROR;
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}

	/**
	 * ���󷽷� RFC=>���۷�Χ���ᣨ�������޸�ʱɾ�����۷�Χ��Ŀ��
	 * 
	 * @param kunnr
	 * @param flag
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String kunnrDelSalesAreaCallRFC(Kunnr kunnr) {
		JCO.Client client = null;
		String result = "";

		List<KunnrSalesArea> salesAreas = kunnr.getKillSalesArea();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_FREEZECUSTOMERLINE_RFC");
			if (null != salesAreas) {
				// �������
				for (KunnrSalesArea area : salesAreas) {
					Function function = sapConnection.getFunction(client);
					ParameterList input = function.getImportParameterList();
					input.getField("KUNNR").setValue(kunnr.getKunnr());// ������SAP����
					input.getField("FAKSD").setValue("");// ����ͻ����߷�Ʊ( ���ۺͷ��� )
					input.getField("LIFSD").setValue("");// �ͻ���������(���۷�Χ)
					input.getField("AUFSD").setValue("01");// �ͻ���������(���۷�Χ)
					input.getField("VKORG").setValue(area.getVkorg());// ������֯
					input.getField("VTWEG").setValue(area.getVtweg());// ��������
					input.getField("SPART").setValue(area.getSpart());// ��Ʒ��
					input.getField("CASSD").setValue("");// �ͻ������۶���(���۷�Χ)
					input.getField("BUKRS").setValue(kunnr.getBukrs()); // ��˾����
					input.getField("SPERR").setValue(""); // �Թ�˾������˶���
					// ִ��
					client.execute(function);
					// �������=>���
					JCO.ParameterList export = function
							.getExportParameterList();
					String s = export.getField("RETURNCODE").getString().trim();
					if ("0".equals(s)) {
						result = IKunnrService.SUCCESS;
					} else {
						// һ�ε���ʧ�� ������
						result = IKunnrService.ERROR;

					}
				}
			}

		} catch (Exception e) {
			logger.error(e);
			result = IKunnrService.ERROR;
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;

	}

	/**
	 * ���󷽷� RFC=>����KUNNR
	 * 
	 * @param kunnr
	 * @param flag
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String kunnrFreezeCallRFC(Kunnr kunnr, String flag) {
		JCO.Client client = null;
		String result = "";
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_FREEZECUSTOMER_RFC");
			JCO.Function function = sapConnection.getFunction(client);
			JCO.ParameterList importpara = function.getImportParameterList();
			importpara.getField("KUNNR").setValue(kunnr.getKunnr());// ������SAP����
			if ("C".equals(flag)) {
				importpara.getField("AUFSD").setValue("01");// ���۶���
				importpara.getField("SPERR").setValue("X");// ���񶳽�
			} else {
				importpara.getField("AUFSD").setValue("01");// ���۶���
				importpara.getField("SPERR").setValue("");// ���񶳽�
			}
			client.execute(function);
			// �������=>���
			JCO.ParameterList export = function.getExportParameterList();
			String s = export.getField("RETURNCODE").getString();
			if (StringUtils.isNotEmpty(s) && "X".equals(s)) {
				result = IKunnrService.SUCCESS;
			} else {
				result = IKunnrService.ERROR;
			}
		} catch (Exception e) {
			logger.error(e);
			result = IKunnrService.ERROR;
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}
	
	/**
	 * ���󷽷� RFC=>ȡ������KUNNR
	 * 
	 * @param kunnr
	 * @param flag
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String kunnrFreezeCallCancelRFC(Kunnr kunnr) {
		JCO.Client client = null;
		String result = "";
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_FREEZECUSTOMER_RFC");
			JCO.Function function = sapConnection.getFunction(client);
			JCO.ParameterList importpara = function.getImportParameterList();
			importpara.getField("KUNNR").setValue(kunnr.getKunnr());// ������SAP����
			importpara.getField("AUFSD").setValue("");// ���۶���
			importpara.getField("SPERR").setValue("");// ���񶳽�
			client.execute(function);
			// �������=>���
			JCO.ParameterList export = function.getExportParameterList();
			String s = export.getField("RETURNCODE").getString();
			if (StringUtils.isNotEmpty(s) && "X".equals(s)) {
				result = IKunnrService.SUCCESS;
			} else {
				result = IKunnrService.ERROR;
			}
		} catch (Exception e) {
			logger.error(e);
			result = IKunnrService.ERROR;
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}
	/**
	 *  ����������ͼ
	 */
	//TODO:
	public BooleanResult kunnrFreezeXview(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		//���󷽷� RFC=>����KUNNR  X ����
		String rfcResult = kunnrFreezeCallRFC(kunnr, "X");
		if (IKunnrService.SUCCESS.equals(rfcResult)) {
			//����crm_tb_kunnr��֮status=2  �ػ�
			result.setResult(true);
			result.setCode(IKunnrService.SAP_SUCCESS);
			kunnrDao.kunnrFreeze(kunnr);
			//���þ������û�basis_tb_kunnremp_info֮status
			Kunnr user=new Kunnr();
			user.setKunnr(kunnr.getKunnr());
			user.setStatus("N");
			kunnrDao.closeKunnrUser(user);
			//�ͷ��Ե�ǰ��֮����·ݾ�����Ŀ���� crm.crm_tb_target
			Kunnr kunnrTar=new Kunnr();
			kunnrTar.setKunnr(kunnr.getKunnr());
			kunnrDao.releaseKunnrTarget(kunnrTar);
			//TODO:����Ŀ���������������Զ�����һ��������¼����Ҫ���������Ĺ��ܣ�
			
			result.setCode(result.getCode() + "'\n" + "+'"+ IKunnrService.DB_SUCCESS);
		} else {
			result.setResult(false);
			result.setCode(IKunnrService.SAP_FAIL);
		}
		return result;

	}

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	public IKunnrDao getKunnrDao() {
		return kunnrDao;
	}

	public void setKunnrDao(IKunnrDao kunnrDao) {
		this.kunnrDao = kunnrDao;
	}

	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}

	public String getLicenseFilePath() {
		return licenseFilePath;
	}

	public void setLicenseFilePath(String licenseFilePath) {
		this.licenseFilePath = licenseFilePath;
	}

	public List<KunnrLogisticsArea> getKunnrLogisticsArea(
			KunnrLogisticsArea area) {
		return kunnrDao.getKunnrLogisticsArea(area);
	}

	public int getKunnrLogisticsAreaCount(KunnrLogisticsArea area) {
		return kunnrDao.getKunnrLogisticsAreaCount(area);
	}

	public BooleanResult updateLogisticArea(List<KunnrLogisticsArea> areaList) {

		BooleanResult result = new BooleanResult();
		boolean bl = false;
		// RFC���� �����޸�rfc
		String rfcResult = "";
		if (areaList.size() > 0)
			for (int i = 0; i < areaList.size(); i++) {
				String rfc = kunnrModifyCallRFC(areaList.get(i)
						.getKunnrObject());
				if (IKunnrService.ERROR.equals(rfc)) {
					rfcResult = IKunnrService.ERROR;
				}
			}

		if ("".equals(rfcResult)) {
			rfcResult = "success";
		}
		// kunnrLogisticsAreaModifyCallRFC(areaList);
		// String rfcResult = "success";
		if (IKunnrService.SUCCESS.equals(rfcResult)) {
			result.setResult(true);
			result.setCode(IKunnrService.SAP_SUCCESS);
			// �޸���������
			if (areaList != null)
				try {
					kunnrDao.updateLogisticArea(areaList);
					result.setCode(result.getCode() + "'\n" + "+'"
							+ IKunnrService.DB_SUCCESS);
				} catch (Exception e) {
					logger.error(e);
					result.setCode(result.getCode() + "\n" + "+'"
							+ IKunnrService.DB_FAIL);
				}
		} else {
			result.setResult(false);
			result.setCode(IKunnrService.SAP_FAIL);
		}
		return result;

	}

	/**
	 * ���󷽷� RFC=>KunnrLogisticsArea
	 * 
	 * @param kunnr
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String kunnrLogisticsAreaModifyCallRFC(
			List<KunnrLogisticsArea> areaList) {
		JCO.Client client = null;
		String result = "";
		try {
			client = sapConnection.getSAPClientFromPool();
			// �����޸�z_modify_stream_rfc
			sapConnection.setFuncName("Z_MODIFY_STREAM_RFC");
			if (null != areaList) {
				// �������
				Function function = sapConnection.getFunction(client);
				Table table = function.getTableParameterList().getTable(
						"ZSTREAMMODIFY");
				for (KunnrLogisticsArea area : areaList) {
					table.appendRow();
					table.getField("KUNNR").setValue(area.getKunnr());// ������SAP����
					table.getField("VKGRP").setValue(area.getBzirk());// ����ʡ��
					table.getField("VKBUR").setValue(area.getVkbur());// ���۲���
					table.getField("BZIRK").setValue(area.getVkgrp());// ���۴���
					// ִ��

				}
				client.execute(function);

				// �������=>���
				JCO.ParameterList export = function.getExportParameterList();
				String s = export.getField("RETURNCODE").getString().trim();
				if ("0".equals(s)) {
					result = IKunnrService.SUCCESS;
				} else {
					// һ�ε���ʧ�� ������
					result = IKunnrService.ERROR;

				}
			}

		} catch (Exception e) {
			logger.error(e);
			result = IKunnrService.ERROR;
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}

	public void saveAttachments(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key) {

		for (int i = 0; i < upload.length; i++) {
			if (uploadFileName[i] != null && uploadFileName[i].length() > 0) {
				String newFileName = UUID.randomUUID()
						+ FileUtil.getFileExtention(uploadFileName[i]);

				// �ļ�Ŀ¼
				String folderPath = uploadFilePath + "/" + key + "/"
						+ DateUtil.datetime("yyyyMM");

				File savedir = new File(folderPath);
				// ���Ŀ¼�����ڣ����½�
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				String path = folderPath + "/" + newFileName;
				File file = new File(path);
				FileUtil.saveAsFile(upload[i], file);
				if (i == 0) {
					kunnr.setNoticeFile1(uploadFileName[i]);
					kunnr.setNoticeFilePath1(path);
				} else if (i == 1) {
					kunnr.setNoticeFile2(uploadFileName[i]);
					kunnr.setNoticeFilePath2(path);
				} else if(i==2){
					kunnr.setNoticeFile3(uploadFileName[i]);
					kunnr.setNoticeFilePath3(path);
				}else{
					kunnr.setNoticeFile4(uploadFileName[i]);
					kunnr.setNoticeFilePath4(path);
				}
			}
		}

	}

	public int getRoleOnEventByUser(String userId, String roleId) {
		try {
			return kunnrDao.getRoleOnEventByUser(userId, roleId);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public void saveCustNameFlie(Kunnr kunnr, File[] upload,
			String[] uploadFileName, String key) {

		if (uploadFileName != null) {
			String newFileName = UUID.randomUUID()
					+ FileUtil.getFileExtention(uploadFileName[0]);

			// �ļ�Ŀ¼
			String folderPath = uploadFilePath + "/" + key + "/"
					+ DateUtil.datetime("yyyyMM");

			File savedir = new File(folderPath);
			// ���Ŀ¼�����ڣ����½�
			if (!savedir.exists()) {
				savedir.mkdirs();
			}
			String path = folderPath + "/" + newFileName;
			File file = new File(path);
			FileUtil.saveAsFile(upload[0], file);
			kunnr.setNameUpdateFile(uploadFileName[0]);
			kunnr.setNameUpdatePath(path);

		}
	}

	public BooleanResult updateKunnrAcount(List<KunnrAcount> kunnrAcountList,
			String kunnr) {
		BooleanResult result = new BooleanResult();
		result.setResult(true);
		if (null != kunnrAcountList)
			try {
				kunnrDao.updateAndCreateKunnrAcount(kunnrAcountList, kunnr);
				result.setCode(IKunnrService.DB_SUCCESS);
			} catch (Exception e) {
				logger.error(e);
				result.setResult(false);
				result.setCode(IKunnrService.DB_FAIL);
			}
		return result;
	}

	public BooleanResult createTarget(List<BCustomerTarget> targets, Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		result.setResult(true);
		if (null != targets)
			try {
				List<BCustomerTarget> targetList = new ArrayList<BCustomerTarget>();
					for (BCustomerTarget target : targets) {
						target.setCustNumber(kunnr.getKunnr());
						target.setOrgId(kunnr.getOrgId());
						target.setCustId(kunnr.getKunnr());
						target.setCtState("0");
						target.setTrFlag("T"); //s�½�
						target.setMark("Y");
					    target.setEventId(kunnr.getEventId());
						target.setKunnrCode(kunnr.getKunnrCode());
						target.setKunnrGoalType("B");
						target.setOpId(String.valueOf(kunnr.getCreateUserId()));
						target.setCheckOpId(kunnr.getCheckOpId());
						target.setTheMonth(StringUtils.leftPad(
								target.getTheMonth(), 2, "0"));
						targetList.add(target);
					}
				kunnrDao.createKunnrGoal(targetList);
				result.setCode(IKunnrService.DB_SUCCESS);
			} catch (Exception e) {
				logger.error(e);
				result.setResult(false);
				result.setCode(IKunnrService.DB_FAIL);
			}
		return result;
	}

	public BooleanResult updateKunnrTarget(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		result.setResult(true);
		try{
				kunnrDao.updateKunnrTarget(kunnr);
				result.setCode(IKunnrService.DB_SUCCESS);
			} catch (Exception e) {
				logger.error(e);
				result.setResult(false);
				result.setCode(IKunnrService.DB_FAIL);
			}
		return result;
	}
	
	public BooleanResult kunnrApplySave(KunnrApplySave kunnrApply) {
		BooleanResult result = new BooleanResult();
		     try {
				kunnrDao.kunnrApplySave(kunnrApply);
				 result.setCode(IKunnrService.DB_SUCCESS);
			} catch (Exception e) {
				logger.error(e);
				result.setResult(false);
				result.setCode(IKunnrService.DB_FAIL);
			}
		return result;
	}

	public int kunnrApplySaveSearchCount(KunnrApplySave kunnrApply) {
		try {
			return kunnrDao.kunnrApplySaveSearchCount(kunnrApply);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public List<KunnrApplySave> kunnrApplySaveSearch(KunnrApplySave kunnrApply) {
		try {
			return kunnrDao.kunnrApplySaveSearch(kunnrApply);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean updateKunnrApplySave(KunnrApplySave kunnrApply) {
		boolean result=true;
		try {
			kunnrDao.updateKunnrApplySave(kunnrApply);
		} catch (Exception e) {
			logger.error(e);
			result=false;
		}
		return result;
	}

	public boolean updateKunnrSapCodeStatus(KunnrSapCodeObject obj) {
		boolean result=true;
		try {
			kunnrDao.updateKunnrSapCodeStatus(obj);
		} catch (Exception e) {
			logger.error(e);
			result=false;
		}
		return result;
	}
	/**
	 * ����kunnr֮״̬
	 */
	public boolean updateKunnrStatus(Kunnr kunnr) {
		boolean bool=false;
		try {
			int i=kunnrDao.updateKunnrStatus(kunnr);
			Kunnr user=new Kunnr();
			user.setKunnr(kunnr.getKunnr());
			user.setStatus("Y");
			kunnrDao.closeKunnrUser(user);
			if(i>0){
				bool=true;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return bool;
	}

	public boolean updateKunnrUserStaff(Kunnr kunnr) {
		boolean bool=true;
		try {
			kunnrDao.updateKunnrUserStaff(kunnr);
		} catch (Exception e) {
			logger.error(e);
			bool=false;
		}
		return bool;
	}

	@Override
	public boolean modifyProcessVariable(String eventId) {
		try {
			kunnrDao.modifyProcessVariable(eventId);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean getOfficeRole(String userId, String roleId) {
		int i=kunnrDao.getOfficeRole(userId,roleId);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean createKunnrSalesArea(Kunnr kunnr) {
		try {
			//���������������ɾ��
			List<KunnrSalesArea> list=kunnrDao.getKunnrSalesAreaList(kunnr);
			if(list!=null && list.size()>0){
				kunnrDao.removeSalesArea(list);
			}
			kunnrDao.createSaleArea(kunnr.getKunnrSalesAreaList(), kunnr.getKunnr());
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;
	}

	@Override
	public String getCityOrgId(Borg borg) {
		return kunnrDao.getCityOrgId(borg);
	}
}
