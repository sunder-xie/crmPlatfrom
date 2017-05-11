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
	 * 经销商sap编码序列：省/城市+(该城市下最大编号+1)
	 *参数：省+城市(方案未启用)
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

				// 文件目录
				String folderPath = uploadFilePath + "/" + key + "/"
						+ DateUtil.datetime("yyyyMM");

				File savedir = new File(folderPath);
				// 如果目录不存在，则新建
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

				// 文件目录
				String folderPath = licenseFilePath;

				File savedir = new File(folderPath);
				// 如果目录不存在，则新建
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
			// 创建kunnr
			try {
				long kunnrId = kunnrDao.createKunnr(kunnr);
				KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
				// 创建kunnr详细信息
				business.setKunnr(kunnr.getKunnr());
				long kunnrBusinessId = kunnrDao.createKunnrBusiness(business);
				// 创建地址送达方
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
				// 创建品牌
				// List<KunnrBrand> kunnrBrandListt = kunnr.getKunnrBrandList();
				// kunnrDao.createKunnrBrand(kunnrBrandListt, kunnr.getKunnr());
				// 创建折扣
				List<KunnrAcount> kunnrAcountList = kunnr.getKunnrAcountList();
				if (kunnrAcountList != null){
					kunnrDao.createKunnrAcount(kunnrAcountList, kunnr.getKunnr());
				}
				// 创建证照
				List<KunnrLicense> kunnrLicenseList = kunnr.getKunnrLicenseList();
				if (kunnrLicenseList != null){
					kunnrDao.createKunnrLicense(kunnrLicenseList, kunnr.getKunnr());
				}

				List<KunnrSalesArea> kunnrSalesAreaList = kunnr
						.getKunnrSalesAreaList();
				if (kunnrSalesAreaList != null){
					kunnrDao.createSaleArea(kunnrSalesAreaList, kunnr.getKunnr());
				}
				//经销商用户创建
				Long userId=kunnrDao.createKunnrUser(kunnr);
				KunnrRole role=new KunnrRole();
				role.setUserId(String.valueOf(userId));
				kunnrDao.createKunnrUserRole(role);
				result.setResult(true);
				//if (kunnrId > 0 && kunnrBusinessId > 0) {
				if(result.getResult()){
					// RFC调用 经销商开户
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
				result.setCode("创建发生异常,请联系管理员!");
			}
		return result;
	}
	/**
	 * 经销商冻结
	 * 
	 * @param kunnr
	 * @return
	 */
	//TODO:
	public BooleanResult kunnrFreeze(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		//对象方法 RFC=>冻结KUNNR  C  经销商
		String rfcResult = kunnrFreezeCallRFC(kunnr, "C");
		try {
			if (IKunnrService.SUCCESS.equals(rfcResult)) {
				//设置crm_tb_kunnr表之status=2  关户
				result.setResult(true);
				result.setCode(IKunnrService.SAP_SUCCESS);
				kunnrDao.kunnrFreeze(kunnr);
				//禁用经销商用户 basis_tb_kunnremp_info之status
				Kunnr user=new Kunnr();
				user.setKunnr(kunnr.getKunnr());
				user.setStatus("N");
				kunnrDao.closeKunnrUser(user);
				//释放自当前月之后的月份经销商目标量 crm.crm_tb_target
				/*Kunnr kunnrTar=new Kunnr();
				kunnrTar.setKunnr(kunnr.getKunnr());
				kunnrDao.releaseKunnrTarget(kunnrTar);*/
				//经销商当前月之后的月份的经销商目标量清空crm.crm_tb_target
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
			// 修改kunnr
			long kunnrId = kunnrDao.updateKunnr(kunnr);
			KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
			// 修改kunnr详细信息
			business.setKunnr(kunnr.getKunnr());
			long kunnrBusinessId = kunnrDao.updateKunnrBusiness(business);
			// 修改创建地址送达方
			List<KunnrAddress> kunnrAddressList = kunnr.getKunnrAddressList();
			if (kunnrAddressList != null)
				kunnrDao.updateAndCreateKunnrAddress(kunnrAddressList,
						kunnr.getKunnr());
			// 修改创建品牌
			List<KunnrBrand> kunnrBrandListt = kunnr.getKunnrBrandList();
			if (kunnrBrandListt != null)
				kunnrDao.updateAndCreateKunnrBrand(kunnrBrandListt,
						kunnr.getKunnr());
			// 修改创建折扣
			List<KunnrAcount> kunnrAcountList = kunnr.getKunnrAcountList();
			if (kunnrAcountList != null)
				kunnrDao.updateAndCreateKunnrAcount(kunnrAcountList,
						kunnr.getKunnr());
			// 创建证照
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
			// 删除品牌及折扣
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
			//修改经销商用户信息
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
	 * 对象方法 RFC=>KUNNR
	 * 
	 * @param kunnr
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String kunnrOpenCallRFC(Kunnr kunnr) {
		// 部分主数据
		KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
		// 收货地址.送达方
		List<KunnrAddress> addressList = kunnr.getKunnrAddressList();

		List<KunnrSalesArea> salesList = kunnr.getKunnrSalesAreaList();
		/**
		 * 办公地址参考位置+办公行政区划
		 */
		StringBuilder build1 = new StringBuilder();
		/*if (StringUtils.isNotEmpty(kunnr.getBgXzAddress())) {
			build1.append(kunnr.getBgXzAddress());
		}*/
		if (StringUtils.isNotEmpty(kunnr.getStreet1())) {
			build1.append(kunnr.getStreet1());
		}
		/**
		 * 收货行政区划+补充的地址信息(取消拼接)
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
			// 销售范围多个 循环调用
			for (KunnrSalesArea salesArea : salesList) {

				JCO.Function function = sapConnection.getFunction(client);
				// 传入参数 =>经销商主数据
				int i = 1;
				JCO.ParameterList input = function.getImportParameterList();
				Structure structure = input.getStructure("I_CUSTOMERCREATE");
				structure.getField("KUNNR").setValue(kunnr.getKunnr());// 经销商SAP代码
				structure.getField("BUKRS").setValue(kunnr.getBukrs());// 公司代码
				// 销售范围
				structure.getField("VKORG").setValue(salesArea.getVkorg());// 销售组织
				structure.getField("VTWEG").setValue(salesArea.getVtweg());// 分销渠道
				structure.getField("SPART").setValue(salesArea.getSpart());// 产品组
				structure.getField("VSBED").setValue(salesArea.getVsbed());// 装运条件
				structure.getField("VWERK").setValue(salesArea.getWerks());// 发货工厂

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
				structure.getField("ADR_NOTES").setValue(kunnr.getKunag());// 上级经销商
				structure.getField("KVERM").setValue(kunnr.getKverm());// 纳税人类型
				if ("小规模纳税人".equals(kunnr.getKverm())) {
					structure.getField("STCEG").setValue("");// 小规模纳税人不用写入增值税登记号
				} else {
					structure.getField("STCEG").setValue(kunnr.getStceg());// 增值税登记号
				}
				structure.getField("LOCCO").setValue(
						kunnr.getLocco()
								.substring(2, kunnr.getLocco().length()));// 打码号
				structure.getField("KUKLA").setValue(business.getKukla());// 客户分类Z2;特约z3;直营z4
				structure.getField("NAME1_GP").setValue(kunnr.getZcAddress());// 开票地址即公司注册地址

				structure.getField("STR_SUPPL2").setValue(build1.toString());// kunnr.getStreet1(//办公地址参考位置+办公行政区划（收信地址）

				structure.getField("STREET").setValue(build.toString());// 收货地址（收货行政区划+补充的地址信息）

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
				/**
				 * 经销商主数据的物流区域取收货地址的行政区域
				 * 之前取的是办公行政地址（kunnr.getProvince(),kunnr.getCity
				 * (),kunnr.getArea(),kunnr.getTown()）
				 */
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
						MyStringBuilder.append(kunnr.getName102mob());
					} // 收货人+联系电话
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

				// 创建的时候同时创建送达方
				// 传入参数=>送达方,即收货地址!销售视图数据参照经销商
				JCO.Table zust = function.getTableParameterList().getTable(
						"ZCUSTOMER_SH_CREATE");
				if (addressList != null && addressList.size() > 0) {
					for (KunnrAddress address : addressList) {
						zust.appendRow();
						zust.setRow(i);
						address.setKunnrSd(kunnr.getKunnr() + "0" + i);
						zust.getField("KUNNR").setValue(address.getKunnrSd());// 代码
						zust.getField("VKORG").setValue(
						/* kunnr */salesArea.getVkorg());// 销售组织
						zust.getField("VTWEG").setValue(salesArea.getVtweg());// 分销渠道
						zust.getField("SPART").setValue(salesArea.getSpart());// 产品组
						zust.getField("VSBED").setValue(salesArea.getVsbed()); // 装运条件
						zust.getField("VWERK").setValue(salesArea.getWerks()); // 交货工厂

						zust.getField("KTOKD").setValue("Z002");// 客户科目组
																// 002送达方
						StringBuilder addressBuild = new StringBuilder();
						if (StringUtils.isNotEmpty(address.getXzAddress())) {
							addressBuild.append(address.getXzAddress());
						}
						if (StringUtils.isNotEmpty(address.getAddress())) {
							addressBuild.append(address.getAddress());
						}
						zust.getField("NAME1")                                //字段较长分两个存
								.setValue(addressBuild.toString());// 名称1
						/*zust.getField("NAME2")
						.setValue(addressBuild.toString().substring(30, addressBuild.toString().length()));// 名称2
*/						zust.getField("STRAS")
								.setValue(addressBuild.toString());// 地址

						zust.getField("VSART").setValue(address.getMaximum()); // 最大可通行车型
						zust.getField("C_TELF1").setValue("");// 电话address.getMobile()
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
						zust.getField("C_NAME1").setValue(b.toString());// 联系人address.getName()
						zust.getField("ANRED")
								.setValue(business.getTitleMedi());// 标题
						zust.getField("LAND1").setValue(business.getCountry());// 国家
						zust.getField("SPRAS").setValue("1");// 语言business.getLangu()
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
						// **取送达方行政区划
						zust.getField("ZWL01").setValue(address.getProvince());
						zust.getField("ZWL02").setValue(address.getCity());
						zust.getField("ZWL03").setValue(address.getArea());
						zust.getField("ZWL04").setValue(address.getTown());
						i++;
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
	 * 对象方法 RFC=>KUNNR
	 * 
	 * @param kunnr
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String kunnrModifyCallRFC(Kunnr kunnr) {
		// 部分主数据
		KunnrBusiness business = kunnr.getKunnrbusinessList().get(0);
		//List<KunnrSalesArea> salesAreaList = kunnr.getKunnrSalesAreaList();
		//List<KunnrAddress> addressList = kunnr.getKunnrAddressList();
		/**
		 * 办公地址参考位置+办公行政区划
		 */
		StringBuilder build1 = new StringBuilder();
		/*if (StringUtils.isNotEmpty(kunnr.getBgXzAddress())) {
			build1.append(kunnr.getBgXzAddress());
		}*/
		if (StringUtils.isNotEmpty(kunnr.getStreet1())) {
			build1.append(kunnr.getStreet1());
		}
		/**
		 * 收货行政区划+补充的地址信息
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
			// 销售范围多个 循环调用
			//for (KunnrSalesArea salesArea : salesAreaList) {//jcg20161124到sap取销售范围
				JCO.Function function = sapConnection.getFunction(client);
				// 传入参数 =>经销商主数据
				JCO.ParameterList input = function.getImportParameterList();
				Structure structure = input.getStructure("I_CUSTOMERCREATE");
				structure.getField("KUNNR").setValue(kunnr.getKunnr());// 经销商SAP代码
				structure.getField("BUKRS").setValue(kunnr.getBukrs());// 公司代码

				//jcg20161124 注释掉
				/**structure.getField("VKORG").setValue(salesArea.getVkorg());// 销售组织
				structure.getField("VTWEG").setValue(salesArea.getVtweg());// 分销渠道
				structure.getField("SPART").setValue(salesArea.getSpart());// 产品组
				structure.getField("VSBED").setValue(salesArea.getVsbed());// 装运条件
				structure.getField("VWERK").setValue(salesArea.getWerks());// 发货工厂
*/
				structure.getField("KTOKD").setValue(kunnr.getKtokd());// 客户帐户组
				structure.getField("NAME1").setValue(kunnr.getName1());// 公司全称
				// structure.getField("NAME2").setValue(kunnr.getName2());
				structure.getField("NAME3").setValue(kunnr.getName3());// 法人代表
				// structure.getField("SORT1").setValue("12");检索项
				// structure.getField("SORT2").setValue("32");
				structure.getField("TELF1").setValue(kunnr.getKpPhone());// 联系电话
				structure.getField("TELF2").setValue(kunnr.getMobNumber());// 移动电话
				structure.getField("TELFX").setValue(kunnr.getFaxNumber());// 传真
				structure.getField("KONZS").setValue(kunnr.getKonzs());// 上级经销商
				structure.getField("ADR_NOTES").setValue(kunnr.getKunag());// 主户经销商
				structure.getField("LOCCO").setValue(
						kunnr.getLocco()
								.substring(2, kunnr.getLocco().length()));// 打码号
				structure.getField("KUKLA").setValue(business.getKukla());// 客户分类
				structure.getField("NAME1_GP").setValue(kunnr.getZcAddress());// 开票地址即公司办公地址

				structure.getField("STR_SUPPL2").setValue(build1.toString());// kunnr.getStreet1(//办公地址参考位置（通信地址）
				structure.getField("STREET").setValue(build.toString());// 收货地址

				structure.getField("VSART").setValue(kunnr.getMaximum()); // 最大可通行车型

				structure.getField("KVERM").setValue(kunnr.getKverm());// 纳税人类型
				if ("小规模纳税人".equals(kunnr.getKverm())) {
					structure.getField("STCEG").setValue("");// 小规模纳税人不用写入增值税登记号
				} else {
					structure.getField("STCEG").setValue(kunnr.getStceg());// 增值税登记号
				}
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
				structure.getField("ZWL01").setValue(kunnr.getShProvince());// 省
				structure.getField("ZWL02").setValue(kunnr.getShCity());// 市
				structure.getField("ZWL03").setValue(kunnr.getShArea());// 区
				structure.getField("ZWL04").setValue(kunnr.getShTown());// 镇
				structure.getField("LAND1").setValue(business.getCountry());// 国家
				structure.getField("SPRAS").setValue("1");// 语言business.getLangu()
				structure.getField("AUFSD").setValue("");// 冻结标志
				structure.getField("BANKTEXT").setValue(
						kunnr.getBank() + kunnr.getBankAccount()); // 银行信息

				// 传入参数=>收货联系人
				JCO.Table knvk = function.getTableParameterList().getTable(
						"T_XKNVK");
				if (StringUtils.isNotEmpty(kunnr.getName102())
						&& (StringUtils.isNotEmpty(kunnr.getName102mob()) || StringUtils
								.isNotEmpty(kunnr.getName102tel()))) {
					knvk.appendRow();
					knvk.setRow(1);
					knvk.getField("KUNNR").setValue(kunnr.getKunnr());
					knvk.getField("TELF1").setValue("");// 收货电话
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
					knvk.getField("NAME1").setValue(builder.toString());// 收货联系人
					knvk.getField("ANRED").setValue(business.getTitleMedi());
				}
				// 公司电话
				if (StringUtils.isNotEmpty(kunnr.getTelNumber())) {
					knvk.appendRow();
					knvk.setRow(2);
					knvk.getField("KUNNR").setValue(kunnr.getKunnr());
					knvk.getField("TELF1").setValue("");// 公司电话kunnr.getTelNumber()
					knvk.getField("PAFKT").setValue("02");
					knvk.getField("ABTNR").setValue("");// 0001
					/*
					 * StringBuilder builder=new
					 * StringBuilder(kunnr.getName3());
					 * builder.append(kunnr.getTelNumber());
					 */
					knvk.getField("NAME1").setValue(kunnr.getTelNumber());// 公司固定电话
					knvk.getField("ANRED").setValue(business.getTitleMedi());
				}
//zsl
				/*
				 * // 传入参数=>送达方,即收货地址!销售视图数据参照经销商 JCO.Structure zust = input
				 * .getStructure("ZCUSTOMER_SH_CREATE");
				 * 
				 * KunnrAddress address = addressList.get(0);
				 * address.setKunnrSd(kunnr.getKunnr() + "0" + i);
				 * zust.getField("KUNNR").setValue(address.getKunnrSd());// 代码
				 * zust.getField("VKORG").setValue(kunnr.getVkorg());// 销售组织
				 * zust.getField("VTWEG").setValue(vtweg);// 分销渠道
				 * zust.getField("SPART").setValue(spart);// 产品组
				 * zust.getField("KTOKD").setValue("Z002");// 客户科目组 // 002送达方
				 * zust.getField("NAME1").setValue(address.getAddress());// 名称
				 * zust.getField("STRAS").setValue(address.getAddress());// 地址
				 * zust.getField("C_TELF1").setValue( address.getMobile() + "/"
				 * + address.getTelephone());// 电话
				 * zust.getField("C_PAFKT").setValue("01");
				 * zust.getField("C_ABTNR").setValue("0001"); //
				 * zust.getField("C_NAMEV").setValue("姓如");
				 * zust.getField("C_NAME1").setValue(address.getName());// 联系人
				 * zust.getField("ANRED").setValue(business.getTitleMedi( ));//
				 * 标题 zust.getField("LAND1").setValue(business.getCountry());//
				 * 国家 zust.getField("SPRAS").setValue(business.getLangu());// 语言
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
				// 执行
				client.execute(function);
				// 输出参数=>结果
				JCO.ParameterList export = function.getExportParameterList();
				String s = export.getField("RETURNCODE").getString();
				if ("0".equals(s)) {
					result = IKunnrService.SUCCESS;
				} else {
					// 一次调用失败 即跳出
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
	 * 对象方法 RFC=>销售范围冻结（经销商修改时删除销售范围条目）
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
				// 传入参数
				for (KunnrSalesArea area : salesAreas) {
					Function function = sapConnection.getFunction(client);
					ParameterList input = function.getImportParameterList();
					input.getField("KUNNR").setValue(kunnr.getKunnr());// 经销商SAP代码
					input.getField("FAKSD").setValue("");// 冻结客户出具发票( 销售和分销 )
					input.getField("LIFSD").setValue("");// 客户交货冻结(销售范围)
					input.getField("AUFSD").setValue("01");// 客户订单冻结(销售范围)
					input.getField("VKORG").setValue(area.getVkorg());// 销售组织
					input.getField("VTWEG").setValue(area.getVtweg());// 分销渠道
					input.getField("SPART").setValue(area.getSpart());// 产品组
					input.getField("CASSD").setValue("");// 客户的销售冻结(销售范围)
					input.getField("BUKRS").setValue(kunnr.getBukrs()); // 公司代码
					input.getField("SPERR").setValue(""); // 对公司代码过账冻结
					// 执行
					client.execute(function);
					// 输出参数=>结果
					JCO.ParameterList export = function
							.getExportParameterList();
					String s = export.getField("RETURNCODE").getString().trim();
					if ("0".equals(s)) {
						result = IKunnrService.SUCCESS;
					} else {
						// 一次调用失败 即跳出
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
	 * 对象方法 RFC=>冻结KUNNR
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
			importpara.getField("KUNNR").setValue(kunnr.getKunnr());// 经销商SAP代码
			if ("C".equals(flag)) {
				importpara.getField("AUFSD").setValue("01");// 销售冻结
				importpara.getField("SPERR").setValue("X");// 财务冻结
			} else {
				importpara.getField("AUFSD").setValue("01");// 销售冻结
				importpara.getField("SPERR").setValue("");// 财务冻结
			}
			client.execute(function);
			// 输出参数=>结果
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
	 * 对象方法 RFC=>取消冻结KUNNR
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
			importpara.getField("KUNNR").setValue(kunnr.getKunnr());// 经销商SAP代码
			importpara.getField("AUFSD").setValue("");// 销售冻结
			importpara.getField("SPERR").setValue("");// 财务冻结
			client.execute(function);
			// 输出参数=>结果
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
	 *  冻结销售视图
	 */
	//TODO:
	public BooleanResult kunnrFreezeXview(Kunnr kunnr) {
		BooleanResult result = new BooleanResult();
		//对象方法 RFC=>冻结KUNNR  X 销售
		String rfcResult = kunnrFreezeCallRFC(kunnr, "X");
		if (IKunnrService.SUCCESS.equals(rfcResult)) {
			//设置crm_tb_kunnr表之status=2  关户
			result.setResult(true);
			result.setCode(IKunnrService.SAP_SUCCESS);
			kunnrDao.kunnrFreeze(kunnr);
			//禁用经销商用户basis_tb_kunnremp_info之status
			Kunnr user=new Kunnr();
			user.setKunnr(kunnr.getKunnr());
			user.setStatus("N");
			kunnrDao.closeKunnrUser(user);
			//释放自当前月之后的月份经销商目标量 crm.crm_tb_target
			Kunnr kunnrTar=new Kunnr();
			kunnrTar.setKunnr(kunnr.getKunnr());
			kunnrDao.releaseKunnrTarget(kunnrTar);
			//TODO:销售目标量调整流程中自动生成一条调整记录（需要调用其他的功能）
			
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
		// RFC调用 物流修改rfc
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
			// 修改物流区域
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
	 * 对象方法 RFC=>KunnrLogisticsArea
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
			// 批量修改z_modify_stream_rfc
			sapConnection.setFuncName("Z_MODIFY_STREAM_RFC");
			if (null != areaList) {
				// 传入参数
				Function function = sapConnection.getFunction(client);
				Table table = function.getTableParameterList().getTable(
						"ZSTREAMMODIFY");
				for (KunnrLogisticsArea area : areaList) {
					table.appendRow();
					table.getField("KUNNR").setValue(area.getKunnr());// 经销商SAP代码
					table.getField("VKGRP").setValue(area.getBzirk());// 销售省份
					table.getField("VKBUR").setValue(area.getVkbur());// 销售部门
					table.getField("BZIRK").setValue(area.getVkgrp());// 销售大区
					// 执行

				}
				client.execute(function);

				// 输出参数=>结果
				JCO.ParameterList export = function.getExportParameterList();
				String s = export.getField("RETURNCODE").getString().trim();
				if ("0".equals(s)) {
					result = IKunnrService.SUCCESS;
				} else {
					// 一次调用失败 即跳出
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

				// 文件目录
				String folderPath = uploadFilePath + "/" + key + "/"
						+ DateUtil.datetime("yyyyMM");

				File savedir = new File(folderPath);
				// 如果目录不存在，则新建
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

			// 文件目录
			String folderPath = uploadFilePath + "/" + key + "/"
					+ DateUtil.datetime("yyyyMM");

			File savedir = new File(folderPath);
			// 如果目录不存在，则新建
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
						target.setTrFlag("T"); //s新建
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
	 * 更新kunnr之状态
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
			//如果表中有数据先删除
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
