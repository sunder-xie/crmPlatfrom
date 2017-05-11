package com.kintiger.platform.sales.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.sap.SAPConnectionBean;
import com.kintiger.platform.sales.dao.ISalesDao;
import com.kintiger.platform.sales.pojo.A603Konp;
import com.kintiger.platform.sales.pojo.Region;
import com.kintiger.platform.sales.pojo.T001;
import com.kintiger.platform.sales.pojo.T001w;
import com.kintiger.platform.sales.pojo.T171t;
import com.kintiger.platform.sales.pojo.Tspa;
import com.kintiger.platform.sales.pojo.Tvbot;
import com.kintiger.platform.sales.pojo.Tvbvk;
import com.kintiger.platform.sales.pojo.Tvgrt;
import com.kintiger.platform.sales.pojo.Tvkbt;
import com.kintiger.platform.sales.pojo.Tvkbz;
import com.kintiger.platform.sales.pojo.Tvko;
import com.kintiger.platform.sales.pojo.Tvtwt;
import com.kintiger.platform.sales.pojo.Tvv1t;
import com.kintiger.platform.sales.pojo.Tvv2t;
import com.kintiger.platform.sales.pojo.Zdqsf;
import com.kintiger.platform.sales.pojo.Zwlqy;
import com.kintiger.platform.sales.service.ISalesService;
import com.sap.mw.jco.JCO;

public class SalesServiceImpl implements ISalesService {
	private SAPConnectionBean sapConnection;
	private ISalesDao salesDao;

	@SuppressWarnings("deprecation")
	public StringResult synchTvko() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Tvko> tvkoList = new ArrayList<Tvko>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_TVKO_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZTVKO");
			salesDao.deleteTvko();// 清空销售组织数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Tvko tvko = new Tvko();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String vkorg = exportTable.getValue("VKORG")==null ? "" : exportTable.getValue("VKORG").toString();// 销售组织
				String bukrs = exportTable.getValue("BUKRS")==null ? "" : exportTable.getValue("BUKRS").toString();// 销售机构的公司代码
				tvko.setMandt(mandt);
				tvko.setVkorg(vkorg);
				tvko.setBukrs(bukrs);
				tvkoList.add(tvko);
			}
			salesDao.createTvkoBatch(tvkoList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);// 释放client回连接池
		}
		return result;
	}

	public int getTvkoCount(Tvko tvko) {
		try {
			return salesDao.getTvkoCount(tvko);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Tvko> getTvkoList(Tvko tvko) {
		try {
			return salesDao.getTvkoList(tvko);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public StringResult synchTvtwt() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Tvtwt> tvtwtList = new ArrayList<Tvtwt>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_TVTWT_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZTVTWT");
			salesDao.deleteTvtwt();// 清空分销渠道数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Tvtwt tvtwt = new Tvtwt();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String spras = exportTable.getValue("SPRAS")==null ? "" : exportTable.getValue("SPRAS").toString();// 语言代码
				String vtweg = exportTable.getValue("VTWEG")==null ? "" : exportTable.getValue("VTWEG").toString();// 分销渠道
				String vtext = exportTable.getValue("VTEXT")==null ? "" : exportTable.getValue("VTEXT").toString();// 名称
				tvtwt.setMandt(mandt);
				tvtwt.setSpras(spras);
				tvtwt.setVtweg(vtweg);
				tvtwt.setVtext(vtext);
				tvtwtList.add(tvtwt);
			}
			salesDao.createTvtwtBatch(tvtwtList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);// 释放client回连接池
		}
		return result;
	}

	public int getTvtwtCount(Tvtwt tvtwt) {
		try {
			return salesDao.getTvtwtCount(tvtwt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Tvtwt> getTvtwtList(Tvtwt tvtwt) {
		try {
			return salesDao.getTvtwtList(tvtwt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public StringResult synchTspa() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Tspa> tspaList = new ArrayList<Tspa>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_TSPA_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZTSPA");
			salesDao.deleteTspa();// 清空产品组数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Tspa tspa = new Tspa();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String spart = exportTable.getValue("SPART")==null ? "" : exportTable.getValue("SPART").toString();// 产品组
				String vtext = exportTable.getValue("VTEXT")==null ? "" : exportTable.getValue("VTEXT").toString();// 描述
				tspa.setMandt(mandt);
				tspa.setSpart(spart);
				tspa.setVtext(vtext);
				tspaList.add(tspa);
			}
			salesDao.createTspaBatch(tspaList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);// 释放client回连接池
		}
		return result;
	}

	public int getTspaCount(Tspa tspa) {
		try {
			return salesDao.getTspaCount(tspa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Tspa> getTspaList(Tspa tspa) {
		try {
			return salesDao.getTspaList(tspa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public StringResult synchTvkbt() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Tvkbt> tvkbtList = new ArrayList<Tvkbt>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_TVKBT_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZTVKBT");
			salesDao.deleteTvkbt();// 清空销售部门数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Tvkbt tvkbt = new Tvkbt();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String spras = exportTable.getValue("SPRAS")==null ? "" : exportTable.getValue("SPRAS").toString();// 语言代码
				String vkbur = exportTable.getValue("VKBUR")==null ? "" : exportTable.getValue("VKBUR").toString();// 销售部门
				String bezei = exportTable.getValue("BEZEI")==null ? "" : exportTable.getValue("BEZEI").toString();// 描述
				tvkbt.setMandt(mandt);
				tvkbt.setSpras(spras);
				tvkbt.setVkbur(vkbur);
				tvkbt.setBezei(bezei);
				tvkbtList.add(tvkbt);
			}
			salesDao.createTvkbtBatch(tvkbtList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);// 释放client回连接池
		}
		return result;
	}

	public int getTvkbtCount(Tvkbt tvkbt) {
		try {
			return salesDao.getTvkbtCount(tvkbt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Tvkbt> getTvkbtList(Tvkbt tvkbt) {
		try {
			return salesDao.getTvkbtList(tvkbt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public StringResult synchTvkbz() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Tvkbz> tvkbzList = new ArrayList<Tvkbz>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_TVTA_RFC");  //原有Z_GET_MM_TVKBZ_RFC
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"TVTA");//原有ZTVKBZ
			salesDao.deleteTvkbz();// 清空销售部门-分销渠道-产品组-销售部门关系数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Tvkbz tvkbz = new Tvkbz();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String vkorg = exportTable.getValue("VKORG")==null ? "" : exportTable.getValue("VKORG").toString();// 销售组织
				String vtweg = exportTable.getValue("VTWEG")==null ? "" : exportTable.getValue("VTWEG").toString();// 分销渠道
				String spart = exportTable.getValue("SPART")==null ? "" : exportTable.getValue("SPART").toString();// 产品组
				tvkbz.setMandt(mandt);
				tvkbz.setVkorg(vkorg);
				tvkbz.setVtweg(vtweg);
				tvkbz.setSpart(spart);
				tvkbzList.add(tvkbz);
			}
			salesDao.createTvkbzBatch(tvkbzList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);// 释放client回连接池
		}
		return result;
	}

	public int getTvkbzCount(Tvkbz tvkbz) {
		try {
			return salesDao.getTvkbzCount(tvkbz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Tvkbz> getTvkbzList(Tvkbz tvkbz) {
		try {
			return salesDao.getTvkbzList(tvkbz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public StringResult synchTvgrt() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Tvgrt> tvgrtList = new ArrayList<Tvgrt>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_TVGRT_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZTVGRT");
			salesDao.deleteTvgrt();// 清空销售省份数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Tvgrt tvgrt = new Tvgrt();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String spras = exportTable.getValue("SPRAS")==null ? "" : exportTable.getValue("SPRAS").toString();// 语言代码
				String vkgrp = exportTable.getValue("VKGRP")==null ? "" : exportTable.getValue("VKGRP").toString();// 销售省份
				String bezei = exportTable.getValue("BEZEI")==null ? "" : exportTable.getValue("BEZEI").toString();// 描述
				tvgrt.setMandt(mandt);
				tvgrt.setSpras(spras);
				tvgrt.setVkgrp(vkgrp);
				tvgrt.setBezei(bezei);
				tvgrtList.add(tvgrt);
			}
			salesDao.createTvgrtBatch(tvgrtList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);// 释放client回连接池
		}
		return result;
	}

	public int getTvgrtCount(Tvgrt tvgrt) {
		try {
			return salesDao.getTvgrtCount(tvgrt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Tvgrt> getTvgrtList(Tvgrt tvgrt) {
		try {
			return salesDao.getTvgrtList(tvgrt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public StringResult synchT171t() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<T171t> t171tList = new ArrayList<T171t>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_T171T_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZT171T");
			salesDao.deleteT171t();// 清空销售省份数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				T171t t171t = new T171t();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String spras = exportTable.getValue("SPRAS")==null ? "" : exportTable.getValue("SPRAS").toString();// 语言代码
				String bzirk = exportTable.getValue("BZIRK")==null ? "" : exportTable.getValue("BZIRK").toString();// 销售大区
				String bztxt = exportTable.getValue("BZTXT")==null ? "" : exportTable.getValue("BZTXT").toString();// 区名
				t171t.setMandt(mandt);
				t171t.setSpras(spras);
				t171t.setBzirk(bzirk);
				t171t.setBztxt(bztxt);
				t171tList.add(t171t);
			}
			salesDao.createT171tBatch(t171tList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}

	public int getT171tCount(T171t t171t) {
		try {
			return salesDao.getT171tCount(t171t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<T171t> getT171tList(T171t t171t) {
		try {
			return salesDao.getT171tList(t171t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public StringResult synchT001w() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<T001w> t001wList = new ArrayList<T001w>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_T001W_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZT001W");
			salesDao.deleteT001w();// 清空工厂数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				T001w t001w = new T001w();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String werks = exportTable.getValue("WERKS")==null ? "" : exportTable.getValue("WERKS").toString();// 工厂
				String name1 = exportTable.getValue("NAME1")==null ? "" : exportTable.getValue("NAME1").toString();// 名称
				t001w.setMandt(mandt);
				t001w.setWerks(werks);
				t001w.setName1(name1);
				t001wList.add(t001w);
			}
			salesDao.createT001wBatch(t001wList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}

	public int getT001wCount() {
		try {
			return salesDao.getT001wCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<T001w> getT001wList(T001w t001w) {
		try {
			return salesDao.getT001wList(t001w);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public StringResult synchT001() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<T001> t001List = new ArrayList<T001>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_T001_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZT001");
			salesDao.deleteT001();// 清空公司代码数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				T001 t001 = new T001();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String bukrs = exportTable.getValue("BUKRS")==null ? "" : exportTable.getValue("BUKRS").toString();// 公司代码
				String butxt = exportTable.getValue("BUTXT")==null ? "" : exportTable.getValue("BUTXT").toString();// 公司代码或公司的名称
				t001.setMandt(mandt);
				t001.setBukrs(bukrs);
				t001.setButxt(butxt);
				t001List.add(t001);
			}
			salesDao.createT001Batch(t001List);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}

	public int getT001Count(T001 t001) {
		try {
			return salesDao.getT001Count(t001);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<T001> getT001List(T001 t001) {
		try {
			return salesDao.getT001List(t001);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public StringResult synchTvv1t() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Tvv1t> tvv1tList = new ArrayList<Tvv1t>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_TVV1T_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZTVV1T");
			salesDao.deleteTvv1t();// 清空城市数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Tvv1t tvv1t = new Tvv1t();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String spras = exportTable.getValue("SPRAS")==null ? "" : exportTable.getValue("SPRAS").toString();// 语言代码
				String kvgr1 = exportTable.getValue("KVGR1")==null ? "" : exportTable.getValue("KVGR1").toString();// 城市
				String bezei = exportTable.getValue("BEZEI")==null ? "" : exportTable.getValue("BEZEI").toString();// 描述
				tvv1t.setMandt(mandt);
				tvv1t.setSpras(spras);
				tvv1t.setKvgr1(kvgr1);
				tvv1t.setBezei(bezei);
				tvv1tList.add(tvv1t);
			}
			salesDao.createTvv1tBatch(tvv1tList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}

	public int getTvv1tCount(Tvv1t tvv1t) {
		try {
			return salesDao.getTvv1tCount(tvv1t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Tvv1t> getTvv1tList(Tvv1t tvv1t) {
		try {
			return salesDao.getTvv1tList(tvv1t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public StringResult synchTvv2t() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Tvv2t> tvv2tList = new ArrayList<Tvv2t>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_TVV2T_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZTVV2T");
			salesDao.deleteTvv2t();// 清空区域数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Tvv2t tvv2t = new Tvv2t();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String spras = exportTable.getValue("SPRAS")==null ? "" : exportTable.getValue("SPRAS").toString();// 语言代码
				String kvgr2 = exportTable.getValue("KVGR2")==null ? "" : exportTable.getValue("KVGR2").toString();// 区域
				String bezei = exportTable.getValue("BEZEI")==null ? "" : exportTable.getValue("BEZEI").toString();// 描述
				tvv2t.setMandt(mandt);
				tvv2t.setSpras(spras);
				tvv2t.setKvgr2(kvgr2);
				tvv2t.setBezei(bezei);
				tvv2tList.add(tvv2t);
			}
			salesDao.createTvv2tBatch(tvv2tList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}

	public int getTvv2tCount(Tvv2t tvv2t) {
		try {
			return salesDao.getTvv2tCount(tvv2t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Tvv2t> getTvv2tList(Tvv2t tvv2t) {
		try {
			return salesDao.getTvv2tList(tvv2t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public StringResult synchTvbot() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Tvbot> tvbotList = new ArrayList<Tvbot>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_TVBOT_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZTVBOT");
			salesDao.deleteTvbot();// 清空物料回扣组数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Tvbot tvbot = new Tvbot();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String spras = exportTable.getValue("SPRAS")==null ? "" : exportTable.getValue("SPRAS").toString();// 语言代码
				String bonus = exportTable.getValue("BONUS")==null ? "" : exportTable.getValue("BONUS").toString();// 物料回扣组
				String vtext = exportTable.getValue("VTEXT")==null ? "" : exportTable.getValue("VTEXT").toString();// 描述
				tvbot.setMandt(mandt);
				tvbot.setSpras(spras);
				tvbot.setBonus(bonus);
				tvbot.setVtext(vtext);
				tvbotList.add(tvbot);
			}
			salesDao.createTvbotBatch(tvbotList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}

	public int getTvbotCount(Tvbot tvbot) {
		try {
			return salesDao.getTvbotCount(tvbot);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Tvbot> getTvbotList(Tvbot tvbot) {
		try {
			return salesDao.getTvbotList(tvbot);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public StringResult synchZwlqy() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Zwlqy> zwlqyList= new ArrayList<Zwlqy>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_ZWLQY_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZZWLQY");
			salesDao.deleteZwlqy();// 清空物流行政区域数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Zwlqy zwlqy = new Zwlqy();
				String zwl00 = exportTable.getValue("ZWL00")==null ? "" : exportTable.getValue("ZWL00").toString();// 国家代码
				String zwl01 = exportTable.getValue("ZWL01")==null ? "" : exportTable.getValue("ZWL01").toString();// 省直辖市
				String zwl02 = exportTable.getValue("ZWL02")==null ? "" : exportTable.getValue("ZWL02").toString();// 地级城市
				String zwl03 = exportTable.getValue("ZWL03")==null ? "" : exportTable.getValue("ZWL03").toString();// 区县
				String zwl04 = exportTable.getValue("ZWL04")==null ? "" : exportTable.getValue("ZWL04").toString();// 乡镇
				String zwl00t = exportTable.getValue("ZWL00T")==null ? "" : exportTable.getValue("ZWL00T").toString();// 国家代码描述
				String zwl01t = exportTable.getValue("ZWL01T")==null ? "" : exportTable.getValue("ZWL01T").toString();// 省直辖市描述
				String zwl02t = exportTable.getValue("ZWL02T")==null ? "" : exportTable.getValue("ZWL02T").toString();// 地级城市描述
				String zwl03t = exportTable.getValue("ZWL03T")==null ? "" : exportTable.getValue("ZWL03T").toString();// 区县描述
				String zwl04t = exportTable.getValue("ZWL04T")==null ? "" : exportTable.getValue("ZWL04T").toString();// 乡镇描述
				zwlqy.setZwl00(zwl00);
				zwlqy.setZwl01(zwl01);
				zwlqy.setZwl02(zwl02);
				zwlqy.setZwl03(zwl03);
				zwlqy.setZwl04(zwl04);
				zwlqy.setZwl00t(zwl00t);
				zwlqy.setZwl01t(zwl01t);
				zwlqy.setZwl02t(zwl02t);
				zwlqy.setZwl03t(zwl03t);
				zwlqy.setZwl04t(zwl04t);
				zwlqyList.add(zwlqy);
			}
			salesDao.createZwlqyBatch(zwlqyList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);
		}
		return result;
	}

	public int getZwlqyCount(Zwlqy zwlqy) {
		try {
			return salesDao.getZwlqyCount(zwlqy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Zwlqy> getZwlqyList(Zwlqy zwlqy) {
		try {
			return salesDao.getZwlqyList(zwlqy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public List<Region> searchRegion(Region region) {
		try {
			return salesDao.searchRegion(region);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public StringResult synchZdqsf() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Zdqsf> zdqsfList = new ArrayList<Zdqsf>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_ZDQSF_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZZDQSF");
			salesDao.deleteZdqsf();// 清空销售省份与销售大区关系数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Zdqsf zdqsf = new Zdqsf();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String bzirk = exportTable.getValue("BZIRK")==null ? "" : exportTable.getValue("BZIRK").toString();// 销售大区
				String vkgrp = exportTable.getValue("VKGRP")==null ? "" : exportTable.getValue("VKGRP").toString();// 销售省份
				String bztxt = exportTable.getValue("BZTXT")==null ? "" : exportTable.getValue("BZTXT").toString();// 区名
				String bezei = exportTable.getValue("BEZEI")==null ? "" : exportTable.getValue("BEZEI").toString();// 描述
				zdqsf.setMandt(mandt);
				zdqsf.setBzirk(bzirk);
				zdqsf.setVkgrp(vkgrp);
				zdqsf.setBztxt(bztxt);
				zdqsf.setBezei(bezei);
				zdqsfList.add(zdqsf);
			}
			salesDao.createZdqsfBatch(zdqsfList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);// 释放client回连接池
		}
		return result;
	}

	public int getZdqsfCount(Zdqsf zdqsf) {
		try {
			return salesDao.getZdqsfCount(zdqsf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Zdqsf> getZdqsfList(Zdqsf zdqsf) {
		try {
			return salesDao.getZdqsfList(zdqsf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public StringResult synchTvbvk() {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<Tvbvk> tvbvkList = new ArrayList<Tvbvk>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_TVBVK_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZTVBVK");
			salesDao.deleteTvbvk();// 清空销售省份与销售部门关系数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				Tvbvk tvbvk = new Tvbvk();
				String mandt = exportTable.getValue("MANDT")==null ? "" : exportTable.getValue("MANDT").toString();// 客户端
				String vkbur = exportTable.getValue("VKBUR")==null ? "" : exportTable.getValue("VKBUR").toString();// 销售部门
				String vkgrp = exportTable.getValue("VKGRP")==null ? "" : exportTable.getValue("VKGRP").toString();// 销售省份
				tvbvk.setMandt(mandt);
				tvbvk.setVkbur(vkbur);
				tvbvk.setVkgrp(vkgrp);
				tvbvkList.add(tvbvk);
			}
			salesDao.createTvbvkBatch(tvbvkList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);// 释放client回连接池
		}
		return result;
	}
	
	public int getTvbvkCount(Tvbvk tvbvk) {
		try {
			return salesDao.getTvbvkCount(tvbvk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Tvbvk> getTvbvkList(Tvbvk tvbvk) {
		try {
			return salesDao.getTvbvkList(tvbvk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public StringResult synchKbetr(String key) {
		JCO.Client client = null;
		StringResult result = new StringResult();
		List<A603Konp> a603KonpList = new ArrayList<A603Konp>();
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("Z_GET_MM_KBETR_RFC");
			JCO.Function func = sapConnection.getFunction(client);
			JCO.ParameterList input = func.getImportParameterList();
			input.getField("VKORG").setValue(key);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"ZA603_KONP");
			salesDao.deleteKbetr();// 清空物料组价格数据
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				A603Konp a603Konp = new A603Konp();
				String vkorg = exportTable.getValue("VKORG")==null ? "" : exportTable.getValue("VKORG").toString();// 销售组织
				String kondm = exportTable.getValue("KONDM")==null ? "" : exportTable.getValue("KONDM").toString();// 物料定价组
				String kbetr = exportTable.getValue("KBETR")==null ? "" : exportTable.getValue("KBETR").toString();// 物料组价格
				Date datab = exportTable.getDate("DATAB");// 有效起始日期
				Date datbi = exportTable.getDate("DATBI");// 有效截止日期
				a603Konp.setVkorg(vkorg);
				a603Konp.setKondm(kondm);
				a603Konp.setKbetr(kbetr);
				a603Konp.setDatab(datab);
				a603Konp.setDatbi(datbi);
				a603KonpList.add(a603Konp);
			}
			salesDao.createKbetrBatch(a603KonpList);
			result.setCode(ISalesService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(ISalesService.ERROR);
		} finally {
			if (client != null)
				JCO.releaseClient(client);// 释放client回连接池
		}
		return result;
	}
	
	public int getKbetrCount(A603Konp a603Konp) {
		try {
			return salesDao.getKbetrCount(a603Konp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<A603Konp> getKbetrList(A603Konp a603Konp) {
		try {
			return salesDao.getKbetrList(a603Konp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}

	public ISalesDao getSalesDao() {
		return salesDao;
	}

	public void setSalesDao(ISalesDao salesDao) {
		this.salesDao = salesDao;
	}

	public List<Tvgrt> getTvgrtListByZdqsf(Zdqsf zdqsf) {
		return salesDao.getTvgrtListByZdqsf(zdqsf);
	}

	public int getTvgrtZdqsfCount(Zdqsf zdqsf) {
		return salesDao.getTvgrtZdqsfCount(zdqsf);
	}

	public List<Tvkbt> getTvkbtJsonListByTvbvk(Tvbvk tvbvk) {
		return salesDao.getTvkbtJsonListByTvbvk(tvbvk);
	}

	public int getTvkbtJsonListByTvbvkCount(Tvbvk tvbvk) {
		return salesDao.getTvkbtJsonListByTvbvkCount(tvbvk);
	}

}
