package com.kintiger.platform.master.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.framework.sap.SAPConnectionBean;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.dao.MasterDao;
import com.kintiger.platform.master.pojo.CrmTbCg;
import com.kintiger.platform.master.pojo.CrmTbSku;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.master.pojo.SupervisorCheckItem;
import com.kintiger.platform.master.pojo.SupervisorLineCheckItem;
import com.kintiger.platform.master.service.MasterService;
import com.sap.mw.jco.JCO;

public class MasterServiceImpl implements MasterService {
	private SAPConnectionBean sapConnection;
	private MasterDao masterDao;
	private static final Logger logger = Logger
			.getLogger(MasterServiceImpl.class);
	private TransactionTemplate transactionTemplate;

	/**
	 * 同步物料到本地
	 * 20170110修改同步字段 by jcg
	 */
	@SuppressWarnings("deprecation")
	public StringResult synchMateriel() {
		StringResult result = new StringResult();
		JCO.Client client = null;
		try {
			client = sapConnection.getSAPClientFromPool();
			//sapConnection.setFuncName("ZRFC_MM_GET_MATNR");
			sapConnection.setFuncName("ZRFC_MM_GET_MATNR_FINAL");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZTMATNR_OUT");
			masterDao.deleteMateriel(); // 清空物料主数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Materiel materiel = new Materiel();
				String matnr = exportTable.getValue("MATNR04").toString();// 物料编码(四级)
				//String matkl = exportTable.getValue("MATKL").toString();// 物料组
				String maktx = exportTable.getValue("MAKTX04").toString();// 物料短文本(sku)
				String meins = exportTable.getValue("MEINS").toString();// 物料单位
				String matnr03 = exportTable.getValue("MATNR03").toString();// 三级编码
				String maktx03 = exportTable.getValue("MAKTX03").toString();// 包装
				String matnr02 = exportTable.getValue("MATNR02").toString();// 二级编码
				String maktx02 = exportTable.getValue("MAKTX02").toString();// 系列
				String matnr01 = exportTable.getValue("MATNR01").toString();// 一级编码
				String maktx01 = exportTable.getValue("MAKTX01").toString();// 品牌
				/**String bezei = exportTable.getValue("BEZEI").toString();// 物料组描述
				
				// ---------------------------zpf 物料同步 start----------------------------------------------
				String bezei40 = exportTable.getValue("BEZEI40").toString();// 物料组描述
				if (!bezei40.isEmpty()) {
					System.out.println(bezei40);
				}
				String matg = exportTable.getValue("MVGR2").toString();// 物料组2 
				// -----------------------zpf 物料同步 end--------------------------------------------------
				*/
				materiel.setMatnr(matnr);
				//materiel.setMatkl(matkl);
				materiel.setMaktx(maktx);
				materiel.setMeins(meins);
				materiel.setMatnr03(matnr03);
				materiel.setMaktx03(maktx03);
				materiel.setMatnr02(matnr02);
				materiel.setMaktx02(maktx02);
				materiel.setMatnr01(matnr01);
				materiel.setMaktx01(maktx01);
//				materiel.setWgbez(wgbez);
				if(matnr.length()>4){  //20170224 判断四级编码>4为新物料设预算口径不为空
					materiel.setMvgr1("a");
					materiel.setBezei("b");
				}else{
					materiel.setMvgr1("");
					materiel.setBezei("");
				}
//				
//				materiel.setBezei40(bezei40);
//				materiel.setMatg(matg);
				
				masterDao.createMateriel(materiel); // 创建物料主数据
			}
			result.setCode("success");

		} catch (Exception e) {
			result.setCode("error");
			logger.error(e);
		} finally {
			try{
				if (client != null) 
					JCO.releaseClient(client);
			}catch(Exception e) {
				result.setCode("error");
				logger.error(e);
			} 
		}
		return result;
	}
	
	public int getMaterielCount(Materiel materiel) {
		return masterDao.getMaterielCount(materiel);
	}

	public List<Materiel> getMaterielList(Materiel materiel) {
		return masterDao.getMaterielList(materiel);
	}

	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}

	public MasterDao getMasterDao() {
		return masterDao;
	}

	public void setMasterDao(MasterDao masterDao) {
		this.masterDao = masterDao;
	}

	public StringResult synchToSku(final CrmTbSku sku) {
		StringResult result=new StringResult();
		result.setCode(MasterService.ERROR);
		BooleanResult bool=(BooleanResult) transactionTemplate.execute(new TransactionCallback() {
			public BooleanResult doInTransaction(TransactionStatus ts) {
				BooleanResult bresult=new BooleanResult();
				bresult.setResult(true);
				//根据物料主数据的口径名称，判断品类是否已存在，不存在则新增
				CrmTbCg cg=new CrmTbCg();
				cg.setSapCode(sku.getSkuCategoryId());
				List<CrmTbCg> cgList=masterDao.getSkuCategoryIdByName(cg);
				boolean cgTrue=false;
				String cgTxt="";
				String cateId="";
				String skuTxt="";
				if(null==cgList){
					cgTrue=true;
				}else if(cgList.size()==0){
					cgTrue=true;
				}else if(cgList.size()==1){
					cateId=String.valueOf(cgList.get(0).getCategoryId());
				}else{
					cgTxt="口径在销售品项品类处存在重复,请核对!";
				}
				//写入sku品类表
				if(cgTrue){
				  try {
					  cg.setCreateUser(sku.getCreateUser());
					  cg.setCompanyId("1");
					  cg.setCloudid(sku.getCloudid());
					  cg.setCategoryName(sku.getSkuCategory());
                      cateId=String.valueOf(masterDao.createCrmTbCg(cg)); 
				      } catch (Exception e) {
					       logger.error(e);
					       ts.isRollbackOnly();
					       bresult.setResult(false);
					       bresult.setCode("同步失败.写入销售品项品类表出现异常.");
				      }
				}else if(!"".equals(cateId)){
					//修改品类表
					 try {
					  cg.setCreateUser(sku.getCreateUser());
					  cg.setCategoryId(Long.valueOf(cateId));
					  cg.setCategoryName(sku.getSkuCategory());
					  masterDao.updateCrmTbcg(cg);
					 } catch (Exception e) {
					       logger.error(e);
					       ts.isRollbackOnly();
					       bresult.setResult(false);
					       bresult.setCode("同步失败.修改销售品项品类出现异常.");
				      }
				}else if(!"".equals(cgTxt)){
					bresult.setResult(false);
					bresult.setCode(cgTxt);
				}
				//判断sku表是否已存在
				String sapCode=sku.getSkuSapCode().substring(sku.getSkuSapCode().length()-8, sku.getSkuSapCode().length());
				CrmTbSku sk=new CrmTbSku();
				sk.setSkuSapCode(sapCode);
				sk.setSkuCategoryId(cateId);
				boolean skuTrue=false;
				String skuId="";
				List<CrmTbSku> skuList=masterDao.getSkuIdByNameAndCompany(sk);
				if(null==skuList){
					skuTrue=true;
				}else if(skuList.size()==0){
					skuTrue=true;
				}else if(skuList.size()==1){
					skuId=String.valueOf(skuList.get(0).getSkuId());
				}else{
					skuTxt="物料在销售品项处存在重复,请核对!";
				}
				//写入sku表
				if(skuTrue){
					try {
						sk.setCreateUser(sku.getCreateUser());
						sk.setCloudid(sku.getCloudid());
						sk.setProductType("1");
						sk.setSkuCompany("1");
						sk.setSkuUnitId(sku.getSkuUnitId());
						sk.setSkuUnit(sku.getSkuUnit());
						sk.setSkuName(sku.getSkuName());
						sk.setSkuCode(sapCode);
						masterDao.createCrmTbSku(sk);
					} catch (Exception e) {
						logger.error(e);
						ts.isRollbackOnly();
						 bresult.setResult(false);
					     bresult.setCode("同步失败.写入销售品项品项表出现异常.");
					}
				}else if(!"".equals(skuId)){
					//修改sku
					try {
						sk.setCreateUser(sku.getCreateUser());
						sk.setSkuName(sku.getSkuName());
						sk.setSkuUnitId(sku.getSkuUnitId());
						sk.setSkuUnit(sku.getSkuUnit());
						sk.setSkuCode(sapCode);
						sk.setSkuId(Long.valueOf(skuId));
						masterDao.updateCrmTbSku(sk);
					} catch (Exception e) {
						 logger.error(e);
						 ts.isRollbackOnly();
						 bresult.setResult(false);
					     bresult.setCode("同步失败.修改销售品项品项出现异常.");
					}
				}else if(!"".equals(skuTxt)){
					bresult.setResult(false);
					bresult.setCode(skuTxt);
				}
				return bresult;
			}
		});
		if(bool.getResult()){
			result.setCode(MasterService.SUCCESS);
		}else{
			result.setCode(MasterService.ERROR);
			result.setResult(bool.getCode());
		}
		return result;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}


	public Customer validateChanelAndCust(String custName, String channelName) {
		return masterDao.validateChanelAndCust(custName,channelName);  
	}

	public StringResult saveSupervisorCheckItem(
			SupervisorCheckItem supervisorCheckItem) {
		StringResult sr = new StringResult();
		Long i = masterDao.saveSupervisorCheckItem(supervisorCheckItem);
		if (i == null || i == 0) {
			sr.setResult(MasterService.SUCCESS);
		}else{
			sr.setResult(MasterService.ERROR);
		}
		return sr;
	}


	public int getSupervisorItemsCount(
			SupervisorLineCheckItem supervisorLineCheckItem) {
		int count = masterDao.getSupervisorItemsCount(supervisorLineCheckItem);
		return count;
	}

	public List<SupervisorLineCheckItem> getSupervisorItems(
			SupervisorLineCheckItem supervisorLineCheckItem) {
	
		List<SupervisorLineCheckItem> supervisorLineCheckItemList = new ArrayList<SupervisorLineCheckItem>();
		
		List<SupervisorCheckItem> list = new ArrayList<SupervisorCheckItem>();
		list =masterDao.getSupervisorItems(supervisorLineCheckItem);
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		Integer lines=0;
		
		SupervisorLineCheckItem  line = null;
		for (SupervisorCheckItem supervisorCheckItem : list) {
			if ((line == null) || (!line.getCustName().equals(supervisorCheckItem.getCustName()))) {
				line  = new SupervisorLineCheckItem();
				supervisorLineCheckItemList.add(line);
			}
			line.setChannelName(supervisorCheckItem.getChannelName());
			line.setCustName(supervisorCheckItem.getCustName());
			line.setCustId(supervisorCheckItem.getCustId());
			line.setOrgName(supervisorCheckItem.getOrgName());
			

			
			Long checkId = supervisorCheckItem.getCheckId();
			String   minp= supervisorCheckItem.getMinPrice();
			String maxp = supervisorCheckItem.getMaxPrice();
			String matType=supervisorCheckItem.getMatType()==null?"":supervisorCheckItem.getMatType();
			String matName=supervisorCheckItem.getMatName();
			String checkValue = supervisorCheckItem.getCheckValue();
			
			line.setCreateUserId(supervisorCheckItem.getCreateUserId());
			line.setCreateName(supervisorCheckItem.getCreateName());
			line.setCreateTime(supervisorCheckItem.getCreateTime());
			
			if (matName.equals("椰果原味(单杯)")){line.setCheckId1(checkId);line.setMatName1(matName);line.setMinPrice1(minp);line.setMaxPrice1(maxp);line.setMatType1(matType);line.setCheckValue1(checkValue);}
			if (matName.equals("椰果香芋(单杯)")){line.setCheckId2(checkId);line.setMatName2(matName);line.setMinPrice2(minp);line.setMaxPrice2(maxp);line.setMatType2(matType);line.setCheckValue2(checkValue);}
			if (matName.equals("椰果草莓(单杯)")){line.setCheckId3(checkId);line.setMatName3(matName);line.setMinPrice3(minp);line.setMaxPrice3(maxp);line.setMatType3(matType);line.setCheckValue3(checkValue);}
			if (matName.equals("椰果麦香(单杯)")){line.setCheckId4(checkId);line.setMatName4(matName);line.setMinPrice4(minp);line.setMaxPrice4(maxp);line.setMatType4(matType);line.setCheckValue4(checkValue);}
			if (matName.equals("椰果咖啡(单杯)")){line.setCheckId5(checkId);line.setMatName5(matName);line.setMinPrice5(minp);line.setMaxPrice5(maxp);line.setMatType5(matType);line.setCheckValue5(checkValue);}
			if (matName.equals("椰果巧克力(单杯)")){line.setCheckId6(checkId);line.setMatName6(matName);line.setMinPrice6(minp);line.setMaxPrice6(maxp);line.setMatType6(matType);line.setCheckValue6(checkValue);}
			if (matName.equals("桂圆红枣(单杯)")){line.setCheckId7(checkId);line.setMatName7(matName);line.setMinPrice7(minp);line.setMaxPrice7(maxp);line.setMatType7(matType);line.setCheckValue7(checkValue);}
			if (matName.equals("红豆(单杯)")){line.setCheckId8(checkId);line.setMatName8(matName);line.setMinPrice8(minp);line.setMaxPrice8(maxp);line.setMatType8(matType);line.setCheckValue8(checkValue);}
			if (matName.equals("芝士燕麦(单杯)")){line.setCheckId9(checkId);line.setMatName9(matName);line.setMinPrice9(minp);line.setMaxPrice9(maxp);line.setMatType9(matType);line.setCheckValue9(checkValue);}
			if (matName.equals("黑米椰浆(单杯)")){line.setCheckId10(checkId);line.setMatName10(matName);line.setMinPrice10(minp);line.setMaxPrice10(maxp);line.setMatType10(matType);line.setCheckValue10(checkValue);}
			if (matName.equals("焦糖仙草(单杯)")){line.setCheckId11(checkId);line.setMatName11(matName);line.setMinPrice11(minp);line.setMaxPrice11(maxp);line.setMatType11(matType);line.setCheckValue11(checkValue);}
			if (matName.equals("原汁红豆(单杯)")){line.setCheckId12(checkId);line.setMatName12(matName);line.setMinPrice12(minp);line.setMaxPrice12(maxp);line.setMatType12(matType);line.setCheckValue12(checkValue);}
			if (matName.equals("芒果布丁(单杯)")){line.setCheckId13(checkId);line.setMatName13(matName);line.setMinPrice13(minp);line.setMaxPrice13(maxp);line.setMatType13(matType);line.setCheckValue13(checkValue);}
			if (matName.equals("蓝莓(单杯)")){line.setCheckId14(checkId);line.setMatName14(matName);line.setMinPrice14(minp);line.setMaxPrice14(maxp);line.setMatType14(matType);line.setCheckValue14(checkValue);}
			if (matName.equals("雪糕椰浆(单杯)")){line.setCheckId15(checkId);line.setMatName15(matName);line.setMinPrice15(minp);line.setMaxPrice15(maxp);line.setMatType15(matType);line.setCheckValue15(checkValue);}
			if (matName.equals("原味(3连杯)")){line.setCheckId16(checkId);line.setMatName16(matName);line.setMinPrice16(minp);line.setMaxPrice16(maxp);line.setMatType16(matType);line.setCheckValue16(checkValue);}
			if (matName.equals("香芋(3连杯)")){line.setCheckId17(checkId);line.setMatName17(matName);line.setMinPrice17(minp);line.setMaxPrice17(maxp);line.setMatType17(matType);line.setCheckValue17(checkValue);}
			if (matName.equals("草莓(3连杯)")){line.setCheckId18(checkId);line.setMatName18(matName);line.setMinPrice18(minp);line.setMaxPrice18(maxp);line.setMatType18(matType);line.setCheckValue18(checkValue);}
			if (matName.equals("麦香(3连杯)")){line.setCheckId19(checkId);line.setMatName19(matName);line.setMinPrice19(minp);line.setMaxPrice19(maxp);line.setMatType19(matType);line.setCheckValue19(checkValue);}
			if (matName.equals("桂圆红枣(3连杯)")){line.setCheckId20(checkId);line.setMatName20(matName);line.setMinPrice20(minp);line.setMaxPrice20(maxp);line.setMatType20(matType);line.setCheckValue20(checkValue);}
			if (matName.equals("红豆(3连杯)")){line.setCheckId21(checkId);line.setMatName21(matName);line.setMinPrice21(minp);line.setMaxPrice21(maxp);line.setMatType21(matType);line.setCheckValue21(checkValue);}
			if (matName.equals("芝士燕麦(3连杯)")){line.setCheckId22(checkId);line.setMatName22(matName);line.setMinPrice22(minp);line.setMaxPrice22(maxp);line.setMatType22(matType);line.setCheckValue22(checkValue);}
			if (matName.equals("黑米椰浆(3连杯)")){line.setCheckId23(checkId);line.setMatName23(matName);line.setMinPrice23(minp);line.setMaxPrice23(maxp);line.setMatType23(matType);line.setCheckValue23(checkValue);}
			if (matName.equals("焦糖仙草(3连杯)")){line.setCheckId24(checkId);line.setMatName24(matName);line.setMinPrice24(minp);line.setMaxPrice24(maxp);line.setMatType24(matType);line.setCheckValue24(checkValue);}
			if (matName.equals("芒果布丁(3连杯)")){line.setCheckId25(checkId);line.setMatName25(matName);line.setMinPrice25(minp);line.setMaxPrice25(maxp);line.setMatType25(matType);line.setCheckValue25(checkValue);}
			if (matName.equals("蓝莓(3连杯)")){line.setCheckId26(checkId);line.setMatName26(matName);line.setMinPrice26(minp);line.setMaxPrice26(maxp);line.setMatType26(matType);line.setCheckValue26(checkValue);}
			if (matName.equals("雪糕椰浆(3连杯)")){line.setCheckId27(checkId);line.setMatName27(matName);line.setMinPrice27(minp);line.setMaxPrice27(maxp);line.setMatType27(matType);line.setCheckValue27(checkValue);}
			if (matName.equals("椰果16杯装(家庭分享装)")){line.setCheckId28(checkId);line.setMatName28(matName);line.setMinPrice28(minp);line.setMaxPrice28(maxp);line.setMatType28(matType);line.setCheckValue28(checkValue);}
			if (matName.equals("椰果12杯装(家庭分享装)")){line.setCheckId29(checkId);line.setMatName29(matName);line.setMinPrice29(minp);line.setMaxPrice29(maxp);line.setMatType29(matType);line.setCheckValue29(checkValue);}
			if (matName.equals("美味16杯装(家庭分享装)")){line.setCheckId30(checkId);line.setMatName30(matName);line.setMinPrice30(minp);line.setMaxPrice30(maxp);line.setMatType30(matType);line.setCheckValue30(checkValue);}
			if (matName.equals("美味12杯装(家庭分享装)")){line.setCheckId31(checkId);line.setMatName31(matName);line.setMinPrice31(minp);line.setMaxPrice31(maxp);line.setMatType31(matType);line.setCheckValue31(checkValue);}
			if (matName.equals("椰果12杯装(礼盒装)")){line.setCheckId32(checkId);line.setMatName32(matName);line.setMinPrice32(minp);line.setMaxPrice32(maxp);line.setMatType32(matType);line.setCheckValue32(checkValue);}
			if (matName.equals("新品礼盒装(礼盒装)")){line.setCheckId33(checkId);line.setMatName33(matName);line.setMinPrice33(minp);line.setMaxPrice33(maxp);line.setMatType33(matType);line.setCheckValue33(checkValue);}
			if (matName.equals("椰果经典(组合装)")){line.setCheckId34(checkId);line.setMatName34(matName);line.setMinPrice34(minp);line.setMaxPrice34(maxp);line.setMatType34(matType);line.setCheckValue34(checkValue);}
			if (matName.equals("美味新品(组合装)")){line.setCheckId35(checkId);line.setMatName35(matName);line.setMinPrice35(minp);line.setMaxPrice35(maxp);line.setMatType35(matType);line.setCheckValue35(checkValue);}
			if (matName.equals("家庭礼盒(家庭礼盒装)")){line.setCheckId36(checkId);line.setMatName36(matName);line.setMinPrice36(minp);line.setMaxPrice36(maxp);line.setMatType36(matType);line.setCheckValue36(checkValue);}
		
		}
		return supervisorLineCheckItemList;	
	}

	public void clearItems() {
		masterDao.clearItems();
		
	}

	public void saveChagCheckItem(List<SupervisorLineCheckItem> supervisorLineCheckItemList) {
		List<SupervisorCheckItem> supervisorCheckItemList = new ArrayList<SupervisorCheckItem>();
		for (SupervisorLineCheckItem supervisorLineCheckItem : supervisorLineCheckItemList) {
			
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId1(),supervisorLineCheckItem.getCheckValue1(),supervisorLineCheckItem.getMinPrice1(),supervisorLineCheckItem.getMaxPrice1()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId2(),supervisorLineCheckItem.getCheckValue2(),supervisorLineCheckItem.getMinPrice2(),supervisorLineCheckItem.getMaxPrice2()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId3(),supervisorLineCheckItem.getCheckValue3(),supervisorLineCheckItem.getMinPrice3(),supervisorLineCheckItem.getMaxPrice3()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId4(),supervisorLineCheckItem.getCheckValue4(),supervisorLineCheckItem.getMinPrice4(),supervisorLineCheckItem.getMaxPrice4()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId5(),supervisorLineCheckItem.getCheckValue5(),supervisorLineCheckItem.getMinPrice5(),supervisorLineCheckItem.getMaxPrice5()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId6(),supervisorLineCheckItem.getCheckValue6(),supervisorLineCheckItem.getMinPrice6(),supervisorLineCheckItem.getMaxPrice6()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId7(),supervisorLineCheckItem.getCheckValue7(),supervisorLineCheckItem.getMinPrice7(),supervisorLineCheckItem.getMaxPrice7()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId8(),supervisorLineCheckItem.getCheckValue8(),supervisorLineCheckItem.getMinPrice8(),supervisorLineCheckItem.getMaxPrice8()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId9(),supervisorLineCheckItem.getCheckValue9(),supervisorLineCheckItem.getMinPrice9(),supervisorLineCheckItem.getMaxPrice9()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId10(),supervisorLineCheckItem.getCheckValue10(),supervisorLineCheckItem.getMinPrice10(),supervisorLineCheckItem.getMaxPrice10()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId11(),supervisorLineCheckItem.getCheckValue11(),supervisorLineCheckItem.getMinPrice11(),supervisorLineCheckItem.getMaxPrice11()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId12(),supervisorLineCheckItem.getCheckValue12(),supervisorLineCheckItem.getMinPrice12(),supervisorLineCheckItem.getMaxPrice12()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId13(),supervisorLineCheckItem.getCheckValue13(),supervisorLineCheckItem.getMinPrice13(),supervisorLineCheckItem.getMaxPrice13()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId14(),supervisorLineCheckItem.getCheckValue14(),supervisorLineCheckItem.getMinPrice14(),supervisorLineCheckItem.getMaxPrice14()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId15(),supervisorLineCheckItem.getCheckValue15(),supervisorLineCheckItem.getMinPrice15(),supervisorLineCheckItem.getMaxPrice15()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId16(),supervisorLineCheckItem.getCheckValue16(),supervisorLineCheckItem.getMinPrice16(),supervisorLineCheckItem.getMaxPrice16()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId17(),supervisorLineCheckItem.getCheckValue17(),supervisorLineCheckItem.getMinPrice17(),supervisorLineCheckItem.getMaxPrice17()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId18(),supervisorLineCheckItem.getCheckValue18(),supervisorLineCheckItem.getMinPrice18(),supervisorLineCheckItem.getMaxPrice18()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId19(),supervisorLineCheckItem.getCheckValue19(),supervisorLineCheckItem.getMinPrice19(),supervisorLineCheckItem.getMaxPrice19()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId20(),supervisorLineCheckItem.getCheckValue20(),supervisorLineCheckItem.getMinPrice20(),supervisorLineCheckItem.getMaxPrice20()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId21(),supervisorLineCheckItem.getCheckValue21(),supervisorLineCheckItem.getMinPrice21(),supervisorLineCheckItem.getMaxPrice21()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId22(),supervisorLineCheckItem.getCheckValue22(),supervisorLineCheckItem.getMinPrice22(),supervisorLineCheckItem.getMaxPrice22()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId23(),supervisorLineCheckItem.getCheckValue23(),supervisorLineCheckItem.getMinPrice23(),supervisorLineCheckItem.getMaxPrice23()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId24(),supervisorLineCheckItem.getCheckValue24(),supervisorLineCheckItem.getMinPrice24(),supervisorLineCheckItem.getMaxPrice24()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId25(),supervisorLineCheckItem.getCheckValue25(),supervisorLineCheckItem.getMinPrice25(),supervisorLineCheckItem.getMaxPrice25()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId26(),supervisorLineCheckItem.getCheckValue26(),supervisorLineCheckItem.getMinPrice26(),supervisorLineCheckItem.getMaxPrice26()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId27(),supervisorLineCheckItem.getCheckValue27(),supervisorLineCheckItem.getMinPrice27(),supervisorLineCheckItem.getMaxPrice27()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId28(),supervisorLineCheckItem.getCheckValue28(),supervisorLineCheckItem.getMinPrice28(),supervisorLineCheckItem.getMaxPrice28()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId29(),supervisorLineCheckItem.getCheckValue29(),supervisorLineCheckItem.getMinPrice29(),supervisorLineCheckItem.getMaxPrice29()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId30(),supervisorLineCheckItem.getCheckValue30(),supervisorLineCheckItem.getMinPrice30(),supervisorLineCheckItem.getMaxPrice30()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId31(),supervisorLineCheckItem.getCheckValue31(),supervisorLineCheckItem.getMinPrice31(),supervisorLineCheckItem.getMaxPrice31()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId32(),supervisorLineCheckItem.getCheckValue32(),supervisorLineCheckItem.getMinPrice32(),supervisorLineCheckItem.getMaxPrice32()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId33(),supervisorLineCheckItem.getCheckValue33(),supervisorLineCheckItem.getMinPrice33(),supervisorLineCheckItem.getMaxPrice33()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId34(),supervisorLineCheckItem.getCheckValue34(),supervisorLineCheckItem.getMinPrice34(),supervisorLineCheckItem.getMaxPrice34()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId35(),supervisorLineCheckItem.getCheckValue35(),supervisorLineCheckItem.getMinPrice35(),supervisorLineCheckItem.getMaxPrice35()) );
			supervisorCheckItemList.add(new SupervisorCheckItem(supervisorLineCheckItem.getCheckId36(),supervisorLineCheckItem.getCheckValue36(),supervisorLineCheckItem.getMinPrice36(),supervisorLineCheckItem.getMaxPrice36()) );


		}
		
		for (SupervisorCheckItem supervisorCheckItem : supervisorCheckItemList) {
			masterDao.saveChagCheckItem(supervisorCheckItem);
			//System.out.println(supervisorCheckItem.getCheckId()+"   " +supervisorCheckItem.getMinPrice()+"  "+supervisorCheckItem.getMaxPrice());
		}
		
	}



	public int kunnrSearchCount(Kunnr kunnr) {
		try {
			return masterDao.kunnrSearchCount(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return 0;

		}
	}



	public List<Kunnr> kunnrSearchFromMaster(Kunnr kunnr) {
		try {
			return masterDao.kunnrSearch(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}


	public Customer validateCustId(String custId) {
		try {
			return masterDao.validateCustId(custId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Customer> getExportMouldCsvCust(Customer c) {
	  
	     try {
	    	   return masterDao.getExportMouldCsvCust( c);
			} catch (Exception e) {
				logger.error(e);
				return null;
			}
	}


	public List<Customer> getExportMouldCsvCustWithCons(
			SupervisorLineCheckItem supervisorLineCheckItem) {
		 try {
	    	   return masterDao.getExportMouldCsvCustWithCons(supervisorLineCheckItem);
			} catch (Exception e) {
				logger.error(e);
				return null;
			}
	}

	public List<Materiel> getMasterCols(Materiel materiel) {
		 try {
	    	   return masterDao.getMasterCols(materiel);
			} catch (Exception e) {
				logger.error(e);
				return null;
			}
	}

	public int getSupervisorItemsColsCount(
			SupervisorLineCheckItem supervisorLineCheckItem) {
		try {
			return masterDao.getSupervisorItemsColsCount(supervisorLineCheckItem);
		} catch (Exception e) {
			logger.error(e);
			return 0;

		}
	}

	public List<Customer> getCustomerListCols(SupervisorLineCheckItem supervisorLineCheckItem) {
		 try {
	    	   return masterDao.getCustomerListCols(supervisorLineCheckItem);
			} catch (Exception e) {
				logger.error(e);
				return null;
			}
	}

	public int validateCustIdExist(String custId) {
		try {
			return masterDao.validateCustIdExist(custId);
		} catch (Exception e) {
			logger.error(e);
			return 0;

		}
	}

	public void delSupervisorCheckItemByCustId(String custId) {
		 masterDao.delSupervisorCheckItemByCustId(custId);
	}

	public List<SupervisorCheckItem> getSupervisorItemsByCustId(String custId) {
		 try {
	    	   return masterDao.getSupervisorItemsByCustId(custId);
			} catch (Exception e) {
				logger.error(e);
				return null;
			}
	}

	public void clearAndSaveItems(
			List<SupervisorCheckItem> supervisorCheckItemList) {
		for (SupervisorCheckItem supervisorCheckItem : supervisorCheckItemList) {
			masterDao.delSupervisorCheckItemByCustId(supervisorCheckItem.getCustId());
		}
		
		for (SupervisorCheckItem supervisorCheckItem : supervisorCheckItemList) {
			masterDao.saveSupervisorCheckItem(supervisorCheckItem);
		  
		}
		
		
	}

	


//	public StringResult updateSupervisorCheckItem(
//			SupervisorCheckItem item) {
//		
//		StringResult stringResult = new StringResult();
//		try{
//			masterDao.updateSupervisorCheckItem(item);
//			stringResult.setCode(SUCCESS);
//		}catch (Exception e) {
//			stringResult.setCode("ERROR");
//			logger.error(LogUtil.parserBean(item), e);
//		}
//		return stringResult;
//	}



	
}