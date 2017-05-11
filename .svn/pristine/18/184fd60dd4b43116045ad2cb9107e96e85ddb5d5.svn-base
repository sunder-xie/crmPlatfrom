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
				booleanResult.setCode("数据同步SAP失败!");

			}
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("数据同步数据库失败!");
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
				booleanResult.setCode("数据同步SAP失败!");

			}
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("数据同步数据库失败!");
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
				stringResult.setResult("数据同步SAP失败!");
				stringResult.setCode("error");
			}
		} catch (Exception e) {
			stringResult.setResult("数据同步数据库失败!");
			stringResult.setCode("error");
			logger.error(e);
		}
		return stringResult;
	}

	/**
	 * 送达方新增修改RFC
	 * 
	 */
	public String kunnrAdressInsertOrUpdateCallRFC(Kunnr kunnr,
			KunnrBusiness business, KunnrAddress kunnrAddress) {
		// 部分主数据
		JCO.Client client = null;
		String result = "";
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_MODIFYCUSTOMER_RFC");
			// 销售范围多个 循环调用
			//jcg20161124List<KunnrSalesArea> areas = new ArrayList<KunnrSalesArea>();
			//jcg20161124areas = kunnr.getKunnrSalesAreaList();
			StringBuilder build1=new StringBuilder();
			if(StringUtils.isNotEmpty(kunnrAddress.getXzAddress())){//jcg20161124解开注释
			build1.append(kunnrAddress.getXzAddress());
			}
			if (StringUtils.isNotEmpty(kunnrAddress.getAddress())) {
				build1.append(kunnrAddress.getAddress());
			}
			
			//jcg20161124销售范围到sap取值if (null != areas) {
			//jcg20161124for (KunnrSalesArea area : areas) {
					JCO.Function function = sapConnection.getFunction(client);
					// 传入参数 =>主数据
					JCO.ParameterList input = function.getImportParameterList();

					Structure zust = input.getStructure("I_CUSTOMERCREATE_SH");
					zust.getField("KUNNR").setValue(kunnrAddress.getKunnrSd());// 代码
					//jcg20161124注释掉销售范围
//					zust.getField("VKORG").setValue(area.getVkorg());// 销售组织
//					zust.getField("VTWEG").setValue(area.getVtweg());// 分销渠道
//					zust.getField("SPART").setValue(area.getSpart());// 产品组
//					zust.getField("VSBED").setValue(area.getVsbed()); //装运条件
//					zust.getField("VWERK").setValue(area.getWerks()); //发货工厂
					
					zust.getField("KTOKD").setValue("Z002");// 客户科目组
															// 002送达方
					zust.getField("NAME1").setValue(build1.toString());// 名称1
				
			      //  zust.getField("NAME2").setValue(build1.toString().substring(30, build1.toString().length()));// 名称2
					zust.getField("STRAS").setValue(build1.toString());// 地址
					zust.getField("VSART").setValue(kunnrAddress.getMaximum()); // 最大可通行车型

					zust.getField("C_TELF1").setValue(""/*
														 * kunnrAddress.getMobile
														 * () + "/" +
														 * kunnrAddress
														 * .getTelephone()
														 */);// 电话
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
					zust.getField("C_NAME1").setValue(b.toString());// 联系人address.getName()
					zust.getField("ANRED").setValue(business.getTitleMedi());// 标题
					zust.getField("LAND1").setValue(business.getCountry());// 国家
					zust.getField("SPRAS").setValue(
					/* business.getLangu() */"1");// 语言
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
					//送达方行政区划
					zust.getField("ZWL01").setValue(kunnrAddress.getProvince());
					zust.getField("ZWL02").setValue(kunnrAddress.getCity());
					zust.getField("ZWL03").setValue(kunnrAddress.getArea());
					zust.getField("ZWL04").setValue(kunnrAddress.getTown());

					client.execute(function);
					// 输出参数=>结果
					JCO.ParameterList export = function
							.getExportParameterList();
					String s = export.getField("RETURNCODE").getString();
					System.out.println("1-s:"+s);
					if ("0".equals(s.trim())) {
						result = IKunnrService.SUCCESS;
					} else {
						// 一次调用失败 即跳出
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
	 * 对象方法 RFC=>KUNNR 将送达方写进合伙人
	 * 
	 * @param kunnr
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String kunnrAddressCallRFC(Kunnr kunnr) {
		// 部分主数据
		KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
		// 送达方
		List<KunnrAddress> addressList = kunnr.getKunnrAddressList();

		//jcg20161124List<KunnrSalesArea> salesList = kunnr.getKunnrSalesAreaList();
		/**
		 * 办公地址参考位置+办公行政区划
		 */
		StringBuilder build1=new StringBuilder();
		/*if(StringUtils.isNotEmpty(kunnr.getBgXzAddress())){
		build1.append(kunnr.getBgXzAddress());
		}*/
		if (StringUtils.isNotEmpty(kunnr.getStreet1())) {
			build1.append(kunnr.getStreet1());
		}
		/**
		 * 收货行政区划+补充的地址信息
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
			// 销售范围多个 循环调用
			//jcg20161124到sap取销售范围for (KunnrSalesArea salesArea : salesList) {

				JCO.Function function = sapConnection.getFunction(client);
				// 传入参数 =>经销商主数据
				int i = 1;
				JCO.ParameterList input = function.getImportParameterList();
				Structure structure = input.getStructure("I_CUSTOMERCREATE");
				structure.getField("KUNNR").setValue(kunnr.getKunnr());// 经销商SAP代码
				structure.getField("BUKRS").setValue(kunnr.getBukrs());// 公司代码
				// 销售范围
				//jcg20161124
//				structure.getField("VKORG").setValue(salesArea.getVkorg());// 销售组织
//				structure.getField("VTWEG").setValue(salesArea.getVtweg());// 分销渠道
//				structure.getField("SPART").setValue(salesArea.getSpart());// 产品组
//				structure.getField("VSBED").setValue(salesArea.getVsbed());// 装运条件
//				structure.getField("VWERK").setValue(salesArea.getWerks());// 发货工厂

				structure.getField("KTOKD").setValue(kunnr.getKtokd());// 客户帐户组
				structure.getField("NAME1").setValue(kunnr.getName1());// 公司全称
				// structure.getField("NAME2").setValue(kunnr.getName2());

				structure.getField("NAME3").setValue(kunnr.getName3());// 法人代表
				// structure.getField("SORT1").setValue("12");检索项
				// structure.getField("SORT2").setValue("32");
				structure.getField("TELF1").setValue(kunnr.getKpPhone());// 联系电话kunnr.getMobNumber()法人手机
																			// 改为开票电话
				structure.getField("TELF2").setValue(kunnr.getMobNumber());// 移动电话法人手机
				structure.getField("TELFX").setValue(kunnr.getFaxNumber());// 传真
				structure.getField("KONZS").setValue(kunnr.getKonzs());// 上级经销商
				structure.getField("KVERM").setValue(kunnr.getKverm());// 纳税人类型
				if ("小规模纳税人".equals(kunnr.getKverm())) {
					structure.getField("STCEG").setValue("");// 小规模纳税人不用写入增值税登记号
				} else {
					structure.getField("STCEG").setValue(kunnr.getStceg());// 增值税登记号
				}
				structure.getField("LOCCO").setValue(
						kunnr.getLocco()
								.substring(2, kunnr.getLocco().length()));// 打码号
				structure.getField("KUKLA").setValue(business.getKukla());// 客户分类
				structure.getField("NAME1_GP").setValue(kunnr.getZcAddress());// 开票地址即公司注册地址
				
				structure.getField("STR_SUPPL2").setValue(build1.toString());// kunnr.getStreet1(//办公地址参考位置（通信地址）
				structure.getField("STREET").setValue(build.toString());// 收货地址
				
				structure.getField("AKONT").setValue(business.getAkont());// 统驭科目
				structure.getField("ANRED").setValue(business.getTitleMedi());// 标题
				structure.getField("ZTERM").setValue(business.getZterm());// 付款条件代码
				structure.getField("WAERS").setValue(business.getWaers());// 货币码
				structure.getField("KALKS").setValue(business.getKalks());// 定价过程
				structure.getField("VERSG").setValue(business.getVersg());// 统计组
				structure.getField("PODKZ").setValue(business.getPodkz());// POD
				structure.getField("AUTLF").setValue(business.getAutlf());// 一次性交货
				structure.getField("KZTLF").setValue(business.getKztlf());// 部分交货
				structure.getField("ANTLF").setValue(business.getAntlf());// 部分交货最大数
				structure.getField("BOKRE").setValue(business.getBokre());// 回扣
				structure.getField("KTGRD").setValue(business.getKtgrd());// 客户组的帐户分配
				structure.getField("TAXKD_01").setValue(business.getTaxkd01());// 客户税分类
				structure.getField("TAXKD").setValue(business.getTaxkd01());// 客户税分类
				structure.getField("VKGRP").setValue(business.getBzirk());// 销售省份
				structure.getField("VKBUR").setValue(business.getVkbur());// 销售部门
				structure.getField("BZIRK").setValue(business.getVkgrp());// 销售大区
				// structure.getField("KVGR1").setValue(business.getKvgr1());
				// structure.getField("KVGR2").setValue(business.getKvgr2());
				structure.getField("LPRIO").setValue(business.getLprio());// 交货优先权
				//取收货行政区划
				structure.getField("ZWL01").setValue(kunnr.getShProvince());// 省
				structure.getField("ZWL02").setValue(kunnr.getShCity());// 市
				structure.getField("ZWL03").setValue(kunnr.getShArea());// 区
				structure.getField("ZWL04").setValue(kunnr.getShTown());// 镇
				structure.getField("LAND1").setValue(business.getCountry());// 国家
				structure.getField("SPRAS").setValue(
				/* business.getLangu() */"1");// 语言
				structure.getField("AUFSD").setValue("");// 冻结标志
				structure.getField("VSART").setValue(kunnr.getMaximum()); // 最大可通行车型
				structure.getField("BANKTEXT").setValue(
						kunnr.getBank() + kunnr.getBankAccount()); // 银行信息
				// 传入参数=>收货联系人
				JCO.Table knvk = function.getTableParameterList().getTable(
						"T_XKNVK");

				if (StringUtils.isNotEmpty(kunnr.getName102())
						&& (StringUtils.isNotEmpty(kunnr.getName102mob()) || StringUtils
								.isNotEmpty(kunnr.getName102tel()))) {
					// 收货联系人
					knvk.appendRow();
					knvk.setRow(1);
					knvk.getField("KUNNR").setValue(kunnr.getKunnr());
					knvk.getField("TELF1").setValue("");// 收货电话kunnr.getName102mob()
														// + "/" +
														// kunnr.getName102tel()
					knvk.getField("PAFKT").setValue("01");
					knvk.getField("ABTNR").setValue("");// "0001"
					StringBuilder MyStringBuilder1 = new StringBuilder(
							kunnr.getName102());
					if (StringUtils.isNotEmpty(kunnr.getName102tel())) {
						MyStringBuilder1.append(kunnr.getName102tel());
					} // 收货人+联系电话
					knvk.getField("NAMEV")
							.setValue(MyStringBuilder1.toString());
					StringBuilder MyStringBuilder = new StringBuilder(
							kunnr.getName102());
					if (StringUtils.isNotEmpty(kunnr.getName102mob())) {
						MyStringBuilder.append(kunnr.getName102mob()); // 收货人+联系电话
					}
					knvk.getField("NAME1").setValue(MyStringBuilder.toString());// 收货联系人
					knvk.getField("ANRED").setValue(business.getTitleMedi());
				}
				if (StringUtils.isNotEmpty(kunnr.getTelNumber())) {
					// 公司电话
					knvk.appendRow();
					knvk.setRow(2);
					knvk.getField("KUNNR").setValue(kunnr.getKunnr());
					knvk.getField("TELF1").setValue("");// 公司电话kunnr.getTelNumber()
					knvk.getField("PAFKT").setValue("02");
					knvk.getField("ABTNR").setValue("");// "0001"
					/*
					 * StringBuilder MyStringBuilder = new
					 * StringBuilder(kunnr.getName3());
					 * MyStringBuilder.append(kunnr.getTelNumber());
					 */// 法人代表+公司联系电话
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
						// 传入参数=>送达方!销售视图数据参照经销商
						JCO.Table zust = function.getTableParameterList()
								.getTable("T_XKNVP");
						zust.appendRow();
						zust.setRow(i);
						//jcg20161124
//						zust.getField("VKORG").setValue(salesArea.getVkorg());// 销售组织
//						zust.getField("VTWEG").setValue(salesArea.getVtweg());// 分销渠道
//						zust.getField("SPART").setValue(salesArea.getSpart());// 产品组
						zust.getField("PARVW").setValue("WE");
						zust.getField("KUNNR").setValue(
								StringUtils.leftPad(kunnr.getKunnr(), 10, "0"));
						zust.getField("KUNN2").setValue(
								StringUtils.leftPad(address.getKunnrSd(), 10,
										"0"));
					}
				}

				client.execute(function);

				// 输出参数=>结果
				JCO.ParameterList export = function.getExportParameterList();
				String s = export.getField("RETURNCODE").getString();
				if ("0".equals(s)) {
					result = IKunnrService.SUCCESS;
				} else {
					// 一次调用失败 即跳出
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
	 * 送达方删除,取消与受达方的关联
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
					 * 办公地址参考位置+办公行政区划
					 */
					StringBuilder build1=new StringBuilder();
					/*if(StringUtils.isNotEmpty(kunnr.getBgXzAddress())){
					build1.append(kunnr.getBgXzAddress());
					}*/
					if (StringUtils.isNotEmpty(kunnr.getStreet1())) {
						build1.append(kunnr.getStreet1());
					}
					/**
					 * 收货行政区划+补充的地址信息
					 */
					StringBuilder build=new StringBuilder();
					/*if(StringUtils.isNotEmpty(kunnr.getShXzAddress())){
					build.append(kunnr.getShXzAddress());
					}*/
					if (StringUtils.isNotEmpty(kunnr.getStreet())) {
						build.append(kunnr.getStreet());
					}
					
					// 部分主数据
					KunnrBusiness business = kunnrDao
							.getKunnrBusinessEntity(kunnr);
					/*
					 * // 收货地址.送达方 List<KunnrAddress> addressList = kunnrDao
					 * .getKunnrAddressList(kunnr);
					 */
					//jcg20161124
//					List<KunnrSalesArea> salesList = kunnrDao
//							.getKunnrSalesAreaList(kunnr);

					// 销售范围多个 循环调用
					//cg.j20161124到sap取销售范围for (KunnrSalesArea salesArea : salesList) {
						JCO.Function function = sapConnection
								.getFunction(client);
						// 传入参数 =>经销商主数据
						// int i = 1;
						JCO.ParameterList input = function
								.getImportParameterList();
						Structure structure = input
								.getStructure("I_CUSTOMERCREATE");
						structure.getField("KUNNR").setValue(kunnr.getKunnr());// 经销商SAP代码
						structure.getField("BUKRS").setValue(kunnr.getBukrs());// 公司代码
						// 销售范围
//						structure.getField("VKORG").setValue(
//								salesArea.getVkorg());// 销售组织
//						structure.getField("VTWEG").setValue(
//								salesArea.getVtweg());// 分销渠道
//						structure.getField("SPART").setValue(
//								salesArea.getSpart());// 产品组
//						structure.getField("VSBED").setValue(
//								salesArea.getVsbed());// 装运条件
//						structure.getField("VWERK").setValue(
//								salesArea.getWerks());// 发货工厂

						structure.getField("KTOKD").setValue(kunnr.getKtokd());// 客户帐户组
						structure.getField("NAME1").setValue(kunnr.getName1());// 公司全称
						// structure.getField("NAME2").setValue(kunnr.getName2());

						structure.getField("NAME3").setValue(kunnr.getName3());// 法人代表
						// structure.getField("SORT1").setValue("12");检索项
						// structure.getField("SORT2").setValue("32");
						structure.getField("TELF1")
								.setValue(kunnr.getKpPhone());// 联系电话kunnr.getMobNumber()法人手机
																// 改为开票电话
						structure.getField("TELF2").setValue(
								kunnr.getMobNumber());// 移动电话法人手机
						structure.getField("TELFX").setValue(
								kunnr.getFaxNumber());// 传真
						structure.getField("KONZS").setValue(kunnr.getKonzs());// 上级经销商
						structure.getField("KVERM").setValue(kunnr.getKverm());// 纳税人类型
						if ("小规模纳税人".equals(kunnr.getKverm())) {
							structure.getField("STCEG").setValue("");// 小规模纳税人不用写入增值税登记号
						} else {
							structure.getField("STCEG").setValue(
									kunnr.getStceg());// 增值税登记号
						}
						structure.getField("LOCCO").setValue(
								kunnr.getLocco().substring(2,
										kunnr.getLocco().length()));// 打码号
						structure.getField("KUKLA").setValue("Z2");// 客户分类
						structure.getField("NAME1_GP").setValue(
								kunnr.getZcAddress());// 开票地址即公司注册地址
						structure.getField("STR_SUPPL2").setValue(build1.toString());// kunnr.getStreet1(//办公地址参考位置（通信地址）

						structure.getField("STREET")
								.setValue(build.toString());// 收货地址
						structure.getField("AKONT").setValue(
								business.getAkont());// 统驭科目
						structure.getField("ANRED").setValue(
								business.getTitleMedi());// 标题
						structure.getField("ZTERM").setValue(
								business.getZterm());// 付款条件代码
						structure.getField("WAERS").setValue(
								business.getWaers());// 货币码
						structure.getField("KALKS").setValue(
								business.getKalks());// 定价过程
						structure.getField("VERSG").setValue(
								business.getVersg());// 统计组
						structure.getField("PODKZ").setValue(
								business.getPodkz());// POD
						structure.getField("AUTLF").setValue(
								business.getAutlf());// 一次性交货
						structure.getField("KZTLF").setValue(
								business.getKztlf());// 部分交货
						structure.getField("ANTLF").setValue(
								business.getAntlf());// 部分交货最大数
						structure.getField("BOKRE").setValue(
								business.getBokre());// 回扣
						structure.getField("KTGRD").setValue(
								business.getKtgrd());// 客户组的帐户分配
						structure.getField("TAXKD_01").setValue(
								business.getTaxkd01());// 客户税分类
						structure.getField("TAXKD").setValue(
								business.getTaxkd01());// 客户税分类
						structure.getField("VKGRP").setValue(
								business.getBzirk());// 销售省份
						structure.getField("VKBUR").setValue(
								business.getVkbur());// 销售部门
						structure.getField("BZIRK").setValue(
								business.getVkgrp());// 销售大区
						// structure.getField("KVGR1").setValue(business.getKvgr1());
						// structure.getField("KVGR2").setValue(business.getKvgr2());
						structure.getField("LPRIO").setValue(
								business.getLprio());// 交货优先权
						
						structure.getField("ZWL01").setValue(
								kunnr.getShProvince());// 省
						structure.getField("ZWL02").setValue(kunnr.getShCity());// 市
						structure.getField("ZWL03").setValue(kunnr.getShArea());// 区
						structure.getField("ZWL04").setValue(kunnr.getShTown());// 镇
						structure.getField("LAND1").setValue(
								business.getCountry());// 国家
						structure.getField("SPRAS").setValue(/*
															 * business.getLangu(
															 * )
															 */"1");// 语言
						structure.getField("AUFSD").setValue("");// 冻结标志
						structure.getField("VSART")
								.setValue(kunnr.getMaximum()); // 最大可通行车型
						structure.getField("BANKTEXT").setValue(
								kunnr.getBank() + kunnr.getBankAccount()); // 银行信息
						// 传入参数=>收货联系人
						JCO.Table knvk = function.getTableParameterList()
								.getTable("T_XKNVK");

						if (StringUtils.isNotEmpty(kunnr.getName102())
								&& (StringUtils.isNotEmpty(kunnr
										.getName102mob()) || StringUtils
										.isNotEmpty(kunnr.getName102tel()))) {
							// 收货联系人
							knvk.appendRow();
							knvk.setRow(1);
							knvk.getField("KUNNR").setValue(kunnr.getKunnr());
							knvk.getField("TELF1").setValue("");// 收货电话kunnr.getName102mob()
																// + "/" +
																// kunnr.getName102tel()
							knvk.getField("PAFKT").setValue("01");
							knvk.getField("ABTNR").setValue("");// "0001"
							StringBuilder MyStringBuilder1 = new StringBuilder(
									kunnr.getName102());
							if (StringUtils.isNotEmpty(kunnr.getName102tel())) {
								MyStringBuilder1.append(kunnr.getName102tel());
							}// 收货人+联系电话
							knvk.getField("NAMEV").setValue(
									MyStringBuilder1.toString());
							StringBuilder MyStringBuilder = new StringBuilder(
									kunnr.getName102());
							if (StringUtils.isNotEmpty(kunnr.getName102mob())) {
								MyStringBuilder.append(kunnr.getName102mob());
							}// 收货人+联系电话
							knvk.getField("NAME1").setValue(
									MyStringBuilder.toString());// 收货联系人
							knvk.getField("ANRED").setValue(
									business.getTitleMedi());
						}
						if (StringUtils.isNotEmpty(kunnr.getTelNumber())) {
							// 公司电话
							knvk.appendRow();
							knvk.setRow(2);
							knvk.getField("KUNNR").setValue(kunnr.getKunnr());
							knvk.getField("TELF1").setValue("");// 公司电话kunnr.getTelNumber()
							knvk.getField("PAFKT").setValue("02");
							knvk.getField("ABTNR").setValue("");// "0001"
							knvk.getField("NAME1").setValue(
									kunnr.getTelNumber());//
							knvk.getField("ANRED").setValue(
									business.getTitleMedi());
						}
						// 传入参数=>送达方!销售视图数据参照经销商
						JCO.Table knvp = function.getTableParameterList()
								.getTable("T_YKNVP");
						knvp.appendRow();
						knvp.getField("KUNNR").setValue(
								StringUtils.leftPad(kunnr.getKunnr(), 10, "0"));
						knvp.getField("KUNN2").setValue(
								StringUtils.leftPad(sd, 10, "0"));
						//cg.j20161124注释掉销售范围
//						knvp.getField("VKORG").setValue(salesArea.getVkorg());// 销售组织
//						knvp.getField("VTWEG").setValue(salesArea.getVtweg());// 分销渠道
//						knvp.getField("SPART").setValue(salesArea.getSpart());// 产品组

						client.execute(function);
						// 输出参数=>结果
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
