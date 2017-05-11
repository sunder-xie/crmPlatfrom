package com.kintiger.platform.kunnr.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.sap.SAPConnectionBean;
import com.kintiger.platform.kunnr.dao.IKunnrAddressDao;
import com.kintiger.platform.kunnr.dao.IKunnrDao;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrAddress;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnr.pojo.KunnrSalesArea;
import com.kintiger.platform.kunnr.service.IKunnrAddressService;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.sap.mw.jco.JCO;
import com.sap.mw.jco.JCO.Structure;

public class KunnrAddressServiceImpl implements IKunnrAddressService {

	private static Log logger = LogFactory
			.getLog(KunnrAddressServiceImpl.class);
	private IKunnrAddressDao kunnrAddressDao;
	private IKunnrDao kunnrDao;
	private SAPConnectionBean sapConnection;

	public int kunnrAddressSearchCount(KunnrAddress kunnrAddress) {
		try {

			return kunnrAddressDao.kunnrAddressSearchCount(kunnrAddress);
		} catch (Exception e) {
			logger.error(e);
			return 0;

		}
	}

	public KunnrAddress getKunnrAddressById(String kunnrSd) {
		try {

			return kunnrAddressDao.getKunnrAddressById(kunnrSd);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<KunnrAddress> kunnrAddressSearch(KunnrAddress kunnrAddress) {
		try {

			return kunnrAddressDao.kunnrAddressSearch(kunnrAddress);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public BooleanResult createKunnrAddress(KunnrAddress kunnrAddress) {
		BooleanResult booleanResult = new BooleanResult();
		Kunnr k = new Kunnr();
		String rfcResult = null;
		String rfcResult1 = null;
		try {
			k.setKunnr(kunnrAddress.getKunnr());
			Kunnr kunnr = kunnrDao.getKunnrEntity(k);
			List<KunnrSalesArea> sales = new ArrayList<KunnrSalesArea>();
			sales = kunnrDao.getKunnrSalesAreaList(k);
			kunnr.setKunnrSalesAreaList(sales);
			KunnrBusiness business = kunnrDao.getKunnrBusinessEntity(k);
			rfcResult = kunnrAdressInsertOrUpdateCallRFC(kunnr, business,
					kunnrAddress);
			System.out.println("1:"+rfcResult);
			List<KunnrBusiness> blist = new ArrayList<KunnrBusiness>();
			blist.add(business);
			kunnr.setKunnrbusinessList(blist);
			List<KunnrAddress> alist = new ArrayList<KunnrAddress>();
			alist.add(kunnrAddress);
			kunnr.setKunnrAddressList(alist);
			rfcResult1 = kunnrAddressCallRFC(kunnr);
			System.out.println("2:"+rfcResult1);
			if (IKunnrService.SUCCESS.equals(rfcResult)
					&& IKunnrService.SUCCESS.equals(rfcResult1)) {
				Long n = kunnrAddressDao.createKunnrAddress(kunnrAddress);
				if (n != null) {
					booleanResult.setResult(true);
				}
			} else {
				booleanResult.setResult(false);
				booleanResult.setCode("����ͬ��SAPʧ��!");

			}
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("����ͬ�����ݿ�ʧ��!");
			logger.error(e);
		}
		return booleanResult;

	}

	public BooleanResult updateKunnrAddress(final KunnrAddress kunnrAddress) {
		BooleanResult booleanResult = new BooleanResult();
		Kunnr k = new Kunnr();
		String rfcResult = null;
		String rfcResult1 = null;
		try {
			k.setKunnr(kunnrAddress.getKunnr());
			Kunnr kunnr = kunnrDao.getKunnrEntity(k);
			KunnrBusiness business = kunnrDao.getKunnrBusinessEntity(k);
			List<KunnrSalesArea> sales = new ArrayList<KunnrSalesArea>();
			sales = kunnrDao.getKunnrSalesAreaList(k);
			kunnr.setKunnrSalesAreaList(sales);
			rfcResult = kunnrAdressInsertOrUpdateCallRFC(kunnr, business,
					kunnrAddress);
			List<KunnrBusiness> blist = new ArrayList<KunnrBusiness>();
			blist.add(business);
			kunnr.setKunnrbusinessList(blist);
			List<KunnrAddress> alist = new ArrayList<KunnrAddress>();
			alist.add(kunnrAddress);
			kunnr.setKunnrAddressList(alist);
			rfcResult1 = kunnrAddressCallRFC(kunnr);
			if (IKunnrService.SUCCESS.equals(rfcResult)
					&& IKunnrService.SUCCESS.equals(rfcResult1)) {
				kunnrAddressDao.updateKunnrAddress(kunnrAddress);
				booleanResult.setResult(true);
			} else {
				booleanResult.setResult(false);
				booleanResult.setCode("����ͬ��SAPʧ��!");

			}
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("����ͬ�����ݿ�ʧ��!");
			logger.error(e);
		}
		return booleanResult;
	}

	public StringResult deleteKunnrAddress(KunnrAddress kunnrAddress) {
		StringResult stringResult = new StringResult();
		String rfcResult = kunnrAdressFreezeCallRFC(kunnrAddress);
		try {
			if (IKunnrService.SUCCESS.equals(rfcResult)) {
				kunnrAddressDao.deleteKunnrAddress(kunnrAddress);
				stringResult.setCode("success");
			} else {
				stringResult.setResult("����ͬ��SAPʧ��!");
				stringResult.setCode("error");
			}
		} catch (Exception e) {
			stringResult.setResult("����ͬ�����ݿ�ʧ��!");
			stringResult.setCode("error");
			logger.error(e);
		}
		return stringResult;
	}

	/**
	 * �ʹ﷽�����޸�RFC
	 * 
	 */
	public String kunnrAdressInsertOrUpdateCallRFC(Kunnr kunnr,
			KunnrBusiness business, KunnrAddress kunnrAddress) {
		// ����������
		JCO.Client client = null;
		String result = "";
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_MODIFYCUSTOMER_RFC");
			// ���۷�Χ��� ѭ������
			//jcg20161124List<KunnrSalesArea> areas = new ArrayList<KunnrSalesArea>();
			//jcg20161124areas = kunnr.getKunnrSalesAreaList();
			StringBuilder build1=new StringBuilder();
			if(StringUtils.isNotEmpty(kunnrAddress.getXzAddress())){//jcg20161124�⿪ע��
			build1.append(kunnrAddress.getXzAddress());
			}
			if (StringUtils.isNotEmpty(kunnrAddress.getAddress())) {
				build1.append(kunnrAddress.getAddress());
			}
			
			//jcg20161124���۷�Χ��sapȡֵif (null != areas) {
			//jcg20161124for (KunnrSalesArea area : areas) {
					JCO.Function function = sapConnection.getFunction(client);
					// ������� =>������
					JCO.ParameterList input = function.getImportParameterList();

					Structure zust = input.getStructure("I_CUSTOMERCREATE_SH");
					zust.getField("KUNNR").setValue(kunnrAddress.getKunnrSd());// ����
					//jcg20161124ע�͵����۷�Χ
//					zust.getField("VKORG").setValue(area.getVkorg());// ������֯
//					zust.getField("VTWEG").setValue(area.getVtweg());// ��������
//					zust.getField("SPART").setValue(area.getSpart());// ��Ʒ��
//					zust.getField("VSBED").setValue(area.getVsbed()); //װ������
//					zust.getField("VWERK").setValue(area.getWerks()); //��������
					
					zust.getField("KTOKD").setValue("Z002");// �ͻ���Ŀ��
															// 002�ʹ﷽
					zust.getField("NAME1").setValue(build1.toString());// ����1
				
			      //  zust.getField("NAME2").setValue(build1.toString().substring(30, build1.toString().length()));// ����2
					zust.getField("STRAS").setValue(build1.toString());// ��ַ
					zust.getField("VSART").setValue(kunnrAddress.getMaximum()); // ����ͨ�г���

					zust.getField("C_TELF1").setValue(""/*
														 * kunnrAddress.getMobile
														 * () + "/" +
														 * kunnrAddress
														 * .getTelephone()
														 */);// �绰
					zust.getField("C_PAFKT").setValue("01");
					zust.getField("C_ABTNR").setValue("");// 0001
					StringBuilder b1 = new StringBuilder(kunnrAddress.getName());
					if (StringUtils.isNotEmpty(kunnrAddress.getMobile())) {
						b1.append(kunnrAddress.getMobile());
					}
					zust.getField("C_NAMEV").setValue(b1.toString());
					StringBuilder b = new StringBuilder(kunnrAddress.getName());
					if (StringUtils.isNotEmpty(kunnrAddress.getTelephone())) {
						b.append(kunnrAddress.getTelephone());
					}
					zust.getField("C_NAME1").setValue(b.toString());// ��ϵ��address.getName()
					zust.getField("ANRED").setValue(business.getTitleMedi());// ����
					zust.getField("LAND1").setValue(business.getCountry());// ����
					zust.getField("SPRAS").setValue(
					/* business.getLangu() */"1");// ����
					zust.getField("C_ANRED").setValue(business.getTitleMedi());
					zust.getField("WAERS").setValue(business.getWaers());
					zust.getField("VERSG").setValue(business.getVersg());
					// zust.getField("KVGR2").setValue(business.getKvgr2());
					
					zust.getField("PODKZ").setValue(business.getPodkz());
					zust.getField("LPRIO").setValue(business.getLprio());
					zust.getField("AUTLF").setValue(business.getAutlf());
					zust.getField("KZTLF").setValue(business.getKztlf());
					zust.getField("ANTLF").setValue(business.getAntlf());
					zust.getField("TAXKD").setValue(business.getTaxkd01());
					//�ʹ﷽��������
					zust.getField("ZWL01").setValue(kunnrAddress.getProvince());
					zust.getField("ZWL02").setValue(kunnrAddress.getCity());
					zust.getField("ZWL03").setValue(kunnrAddress.getArea());
					zust.getField("ZWL04").setValue(kunnrAddress.getTown());

					client.execute(function);
					// �������=>���
					JCO.ParameterList export = function
							.getExportParameterList();
					String s = export.getField("RETURNCODE").getString();
					System.out.println("1-s:"+s);
					if ("0".equals(s.trim())) {
						result = IKunnrService.SUCCESS;
					} else {
						// һ�ε���ʧ�� ������
						result = IKunnrService.ERROR;
						//jcg20161124break;
					}
					//jcg20161124}
					//jcg20161124}
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
	 * ���󷽷� RFC=>KUNNR ���ʹ﷽д���ϻ���
	 * 
	 * @param kunnr
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String kunnrAddressCallRFC(Kunnr kunnr) {
		// ����������
		KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
		// �ʹ﷽
		List<KunnrAddress> addressList = kunnr.getKunnrAddressList();

		//jcg20161124List<KunnrSalesArea> salesList = kunnr.getKunnrSalesAreaList();
		/**
		 * �칫��ַ�ο�λ��+�칫��������
		 */
		StringBuilder build1=new StringBuilder();
		/*if(StringUtils.isNotEmpty(kunnr.getBgXzAddress())){
		build1.append(kunnr.getBgXzAddress());
		}*/
		if (StringUtils.isNotEmpty(kunnr.getStreet1())) {
			build1.append(kunnr.getStreet1());
		}
		/**
		 * �ջ���������+����ĵ�ַ��Ϣ
		 */
		StringBuilder build=new StringBuilder();
		/*if(StringUtils.isNotEmpty(kunnr.getShXzAddress())){
		build.append(kunnr.getShXzAddress());
		}*/
		if (StringUtils.isNotEmpty(kunnr.getStreet())) {
			build.append(kunnr.getStreet());
		}
		
		JCO.Client client = null;
		String result = "";
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_MODIFYCUSTOMER_RFC");
			// ���۷�Χ��� ѭ������
			//jcg20161124��sapȡ���۷�Χfor (KunnrSalesArea salesArea : salesList) {

				JCO.Function function = sapConnection.getFunction(client);
				// ������� =>������������
				int i = 1;
				JCO.ParameterList input = function.getImportParameterList();
				Structure structure = input.getStructure("I_CUSTOMERCREATE");
				structure.getField("KUNNR").setValue(kunnr.getKunnr());// ������SAP����
				structure.getField("BUKRS").setValue(kunnr.getBukrs());// ��˾����
				// ���۷�Χ
				//jcg20161124
//				structure.getField("VKORG").setValue(salesArea.getVkorg());// ������֯
//				structure.getField("VTWEG").setValue(salesArea.getVtweg());// ��������
//				structure.getField("SPART").setValue(salesArea.getSpart());// ��Ʒ��
//				structure.getField("VSBED").setValue(salesArea.getVsbed());// װ������
//				structure.getField("VWERK").setValue(salesArea.getWerks());// ��������

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
				structure.getField("KVERM").setValue(kunnr.getKverm());// ��˰������
				if ("С��ģ��˰��".equals(kunnr.getKverm())) {
					structure.getField("STCEG").setValue("");// С��ģ��˰�˲���д����ֵ˰�ǼǺ�
				} else {
					structure.getField("STCEG").setValue(kunnr.getStceg());// ��ֵ˰�ǼǺ�
				}
				structure.getField("LOCCO").setValue(
						kunnr.getLocco()
								.substring(2, kunnr.getLocco().length()));// �����
				structure.getField("KUKLA").setValue(business.getKukla());// �ͻ�����
				structure.getField("NAME1_GP").setValue(kunnr.getZcAddress());// ��Ʊ��ַ����˾ע���ַ
				
				structure.getField("STR_SUPPL2").setValue(build1.toString());// kunnr.getStreet1(//�칫��ַ�ο�λ�ã�ͨ�ŵ�ַ��
				structure.getField("STREET").setValue(build.toString());// �ջ���ַ
				
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
				//ȡ�ջ���������
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
						MyStringBuilder.append(kunnr.getName102mob()); // �ջ���+��ϵ�绰
					}
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
				Map<String, KunnrAddress> tempMap = new HashMap<String, KunnrAddress>();
				KunnrAddress add = new KunnrAddress();
				add.setKunnr(kunnr.getKunnr());
				int max = kunnrAddressDao.kunnrAddressSearchCount(add);
				add.setPagination("false");
				List<KunnrAddress> aList = kunnrAddressDao
						.kunnrAddressSearch(add);
				for (int j = 0; j < aList.size(); j++) {
					tempMap.put(aList.get(j).getKunnrSd(), aList.get(j));
				}
				KunnrAddress address = addressList.get(0);
				if (null != address) {
					if (null == tempMap.get(address.getKunnrSd())) {
						// �������=>�ʹ﷽!������ͼ���ݲ��վ�����
						JCO.Table zust = function.getTableParameterList()
								.getTable("T_XKNVP");
						zust.appendRow();
						zust.setRow(i);
						//jcg20161124
//						zust.getField("VKORG").setValue(salesArea.getVkorg());// ������֯
//						zust.getField("VTWEG").setValue(salesArea.getVtweg());// ��������
//						zust.getField("SPART").setValue(salesArea.getSpart());// ��Ʒ��
						zust.getField("PARVW").setValue("WE");
						zust.getField("KUNNR").setValue(
								StringUtils.leftPad(kunnr.getKunnr(), 10, "0"));
						zust.getField("KUNN2").setValue(
								StringUtils.leftPad(address.getKunnrSd(), 10,
										"0"));
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
					//jcg20161124break;
				}
				//jcg20161124}
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
	 * �ʹ﷽ɾ��,ȡ�����ܴ﷽�Ĺ���
	 * 
	 */
	public String kunnrAdressFreezeCallRFC(KunnrAddress kunnrAddress) {
		JCO.Client client = null;
		String result = "";

		for (String sd : kunnrAddress.getCodes()) {
			try {
				client = sapConnection.getSAPClientFromPool();
				sapConnection.setFuncName("Z_MODIFYCUSTOMER_RFC");
				KunnrAddress address = kunnrAddressDao.getKunnrAddressById(sd);
				if (null != address) {
					Kunnr kunnr = new Kunnr();
					kunnr.setKunnr(address.getKunnr());
					kunnr = kunnrDao.getKunnrEntity(kunnr);
					/**
					 * �칫��ַ�ο�λ��+�칫��������
					 */
					StringBuilder build1=new StringBuilder();
					/*if(StringUtils.isNotEmpty(kunnr.getBgXzAddress())){
					build1.append(kunnr.getBgXzAddress());
					}*/
					if (StringUtils.isNotEmpty(kunnr.getStreet1())) {
						build1.append(kunnr.getStreet1());
					}
					/**
					 * �ջ���������+����ĵ�ַ��Ϣ
					 */
					StringBuilder build=new StringBuilder();
					/*if(StringUtils.isNotEmpty(kunnr.getShXzAddress())){
					build.append(kunnr.getShXzAddress());
					}*/
					if (StringUtils.isNotEmpty(kunnr.getStreet())) {
						build.append(kunnr.getStreet());
					}
					
					// ����������
					KunnrBusiness business = kunnrDao
							.getKunnrBusinessEntity(kunnr);
					/*
					 * // �ջ���ַ.�ʹ﷽ List<KunnrAddress> addressList = kunnrDao
					 * .getKunnrAddressList(kunnr);
					 */
					//jcg20161124
//					List<KunnrSalesArea> salesList = kunnrDao
//							.getKunnrSalesAreaList(kunnr);

					// ���۷�Χ��� ѭ������
					//cg.j20161124��sapȡ���۷�Χfor (KunnrSalesArea salesArea : salesList) {
						JCO.Function function = sapConnection
								.getFunction(client);
						// ������� =>������������
						// int i = 1;
						JCO.ParameterList input = function
								.getImportParameterList();
						Structure structure = input
								.getStructure("I_CUSTOMERCREATE");
						structure.getField("KUNNR").setValue(kunnr.getKunnr());// ������SAP����
						structure.getField("BUKRS").setValue(kunnr.getBukrs());// ��˾����
						// ���۷�Χ
//						structure.getField("VKORG").setValue(
//								salesArea.getVkorg());// ������֯
//						structure.getField("VTWEG").setValue(
//								salesArea.getVtweg());// ��������
//						structure.getField("SPART").setValue(
//								salesArea.getSpart());// ��Ʒ��
//						structure.getField("VSBED").setValue(
//								salesArea.getVsbed());// װ������
//						structure.getField("VWERK").setValue(
//								salesArea.getWerks());// ��������

						structure.getField("KTOKD").setValue(kunnr.getKtokd());// �ͻ��ʻ���
						structure.getField("NAME1").setValue(kunnr.getName1());// ��˾ȫ��
						// structure.getField("NAME2").setValue(kunnr.getName2());

						structure.getField("NAME3").setValue(kunnr.getName3());// ���˴���
						// structure.getField("SORT1").setValue("12");������
						// structure.getField("SORT2").setValue("32");
						structure.getField("TELF1")
								.setValue(kunnr.getKpPhone());// ��ϵ�绰kunnr.getMobNumber()�����ֻ�
																// ��Ϊ��Ʊ�绰
						structure.getField("TELF2").setValue(
								kunnr.getMobNumber());// �ƶ��绰�����ֻ�
						structure.getField("TELFX").setValue(
								kunnr.getFaxNumber());// ����
						structure.getField("KONZS").setValue(kunnr.getKonzs());// �ϼ�������
						structure.getField("KVERM").setValue(kunnr.getKverm());// ��˰������
						if ("С��ģ��˰��".equals(kunnr.getKverm())) {
							structure.getField("STCEG").setValue("");// С��ģ��˰�˲���д����ֵ˰�ǼǺ�
						} else {
							structure.getField("STCEG").setValue(
									kunnr.getStceg());// ��ֵ˰�ǼǺ�
						}
						structure.getField("LOCCO").setValue(
								kunnr.getLocco().substring(2,
										kunnr.getLocco().length()));// �����
						structure.getField("KUKLA").setValue("Z2");// �ͻ�����
						structure.getField("NAME1_GP").setValue(
								kunnr.getZcAddress());// ��Ʊ��ַ����˾ע���ַ
						structure.getField("STR_SUPPL2").setValue(build1.toString());// kunnr.getStreet1(//�칫��ַ�ο�λ�ã�ͨ�ŵ�ַ��

						structure.getField("STREET")
								.setValue(build.toString());// �ջ���ַ
						structure.getField("AKONT").setValue(
								business.getAkont());// ͳԦ��Ŀ
						structure.getField("ANRED").setValue(
								business.getTitleMedi());// ����
						structure.getField("ZTERM").setValue(
								business.getZterm());// ������������
						structure.getField("WAERS").setValue(
								business.getWaers());// ������
						structure.getField("KALKS").setValue(
								business.getKalks());// ���۹���
						structure.getField("VERSG").setValue(
								business.getVersg());// ͳ����
						structure.getField("PODKZ").setValue(
								business.getPodkz());// POD
						structure.getField("AUTLF").setValue(
								business.getAutlf());// һ���Խ���
						structure.getField("KZTLF").setValue(
								business.getKztlf());// ���ֽ���
						structure.getField("ANTLF").setValue(
								business.getAntlf());// ���ֽ��������
						structure.getField("BOKRE").setValue(
								business.getBokre());// �ؿ�
						structure.getField("KTGRD").setValue(
								business.getKtgrd());// �ͻ�����ʻ�����
						structure.getField("TAXKD_01").setValue(
								business.getTaxkd01());// �ͻ�˰����
						structure.getField("TAXKD").setValue(
								business.getTaxkd01());// �ͻ�˰����
						structure.getField("VKGRP").setValue(
								business.getBzirk());// ����ʡ��
						structure.getField("VKBUR").setValue(
								business.getVkbur());// ���۲���
						structure.getField("BZIRK").setValue(
								business.getVkgrp());// ���۴���
						// structure.getField("KVGR1").setValue(business.getKvgr1());
						// structure.getField("KVGR2").setValue(business.getKvgr2());
						structure.getField("LPRIO").setValue(
								business.getLprio());// ��������Ȩ
						
						structure.getField("ZWL01").setValue(
								kunnr.getShProvince());// ʡ
						structure.getField("ZWL02").setValue(kunnr.getShCity());// ��
						structure.getField("ZWL03").setValue(kunnr.getShArea());// ��
						structure.getField("ZWL04").setValue(kunnr.getShTown());// ��
						structure.getField("LAND1").setValue(
								business.getCountry());// ����
						structure.getField("SPRAS").setValue(/*
															 * business.getLangu(
															 * )
															 */"1");// ����
						structure.getField("AUFSD").setValue("");// �����־
						structure.getField("VSART")
								.setValue(kunnr.getMaximum()); // ����ͨ�г���
						structure.getField("BANKTEXT").setValue(
								kunnr.getBank() + kunnr.getBankAccount()); // ������Ϣ
						// �������=>�ջ���ϵ��
						JCO.Table knvk = function.getTableParameterList()
								.getTable("T_XKNVK");

						if (StringUtils.isNotEmpty(kunnr.getName102())
								&& (StringUtils.isNotEmpty(kunnr
										.getName102mob()) || StringUtils
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
							}// �ջ���+��ϵ�绰
							knvk.getField("NAMEV").setValue(
									MyStringBuilder1.toString());
							StringBuilder MyStringBuilder = new StringBuilder(
									kunnr.getName102());
							if (StringUtils.isNotEmpty(kunnr.getName102mob())) {
								MyStringBuilder.append(kunnr.getName102mob());
							}// �ջ���+��ϵ�绰
							knvk.getField("NAME1").setValue(
									MyStringBuilder.toString());// �ջ���ϵ��
							knvk.getField("ANRED").setValue(
									business.getTitleMedi());
						}
						if (StringUtils.isNotEmpty(kunnr.getTelNumber())) {
							// ��˾�绰
							knvk.appendRow();
							knvk.setRow(2);
							knvk.getField("KUNNR").setValue(kunnr.getKunnr());
							knvk.getField("TELF1").setValue("");// ��˾�绰kunnr.getTelNumber()
							knvk.getField("PAFKT").setValue("02");
							knvk.getField("ABTNR").setValue("");// "0001"
							knvk.getField("NAME1").setValue(
									kunnr.getTelNumber());//
							knvk.getField("ANRED").setValue(
									business.getTitleMedi());
						}
						// �������=>�ʹ﷽!������ͼ���ݲ��վ�����
						JCO.Table knvp = function.getTableParameterList()
								.getTable("T_YKNVP");
						knvp.appendRow();
						knvp.getField("KUNNR").setValue(
								StringUtils.leftPad(kunnr.getKunnr(), 10, "0"));
						knvp.getField("KUNN2").setValue(
								StringUtils.leftPad(sd, 10, "0"));
						//cg.j20161124ע�͵����۷�Χ
//						knvp.getField("VKORG").setValue(salesArea.getVkorg());// ������֯
//						knvp.getField("VTWEG").setValue(salesArea.getVtweg());// ��������
//						knvp.getField("SPART").setValue(salesArea.getSpart());// ��Ʒ��

						client.execute(function);
						// �������=>���
						JCO.ParameterList export = function
								.getExportParameterList();
						String s = export.getField("RETURNCODE").getString();
						if ("0".equals(s.trim())) {
							result = IKunnrService.SUCCESS;
						} else {
							result = IKunnrService.ERROR;
							break;
						}
					//cg.j20161124}
				}
			} catch (Exception e) {
				logger.error(e);
				result = IKunnrService.ERROR;
			} finally {
				if (client != null)
					JCO.releaseClient(client);
			}

		}

		return result;
	}

	public String getMaxKunnrSd(String kunnrId) {
		try {
			return kunnrAddressDao.getMaxKunnrSd(kunnrId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		KunnrAddressServiceImpl.logger = logger;
	}

	public IKunnrAddressDao getKunnrAddressDao() {
		return kunnrAddressDao;
	}

	public void setKunnrAddressDao(IKunnrAddressDao kunnrAddressDao) {
		this.kunnrAddressDao = kunnrAddressDao;
	}

	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}

	public IKunnrDao getKunnrDao() {
		return kunnrDao;
	}

	public void setKunnrDao(IKunnrDao kunnrDao) {
		this.kunnrDao = kunnrDao;
	}

}
