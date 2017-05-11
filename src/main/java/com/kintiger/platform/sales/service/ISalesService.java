package com.kintiger.platform.sales.service;

import java.util.List;

import com.kintiger.platform.base.pojo.StringResult;
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

public interface ISalesService {
	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String SUCCESS_MESSAGE = "同步成功!";

	public static final String ERROR_MESSAGE = "同步失败!";

	/**
	 * 同步SAP销售组织数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchTvko();

	/**
	 * 查询销售组织列表总数
	 * 
	 * @param tvko
	 * @return
	 */
	public int getTvkoCount(Tvko tvko);

	/**
	 * 查询销售组织列表
	 * 
	 * @param tvko
	 * @return
	 */
	public List<Tvko> getTvkoList(Tvko tvko);

	/**
	 * 同步SAP分销渠道数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchTvtwt();

	/**
	 * 查询分销渠道列表总数
	 * 
	 * @param tvtwt
	 * @return
	 */
	public int getTvtwtCount(Tvtwt tvtwt);

	/**
	 * 查询分销渠道列表
	 * 
	 * @param tvtwt
	 * @return
	 */
	public List<Tvtwt> getTvtwtList(Tvtwt tvtwt);

	/**
	 * 同步SAP产品组数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchTspa();

	/**
	 * 查询产品组列表总数
	 * 
	 * @param tspa
	 * @return
	 */
	public int getTspaCount(Tspa tspa);

	/**
	 * 查询产品组列表
	 * 
	 * @param tspa
	 * @return
	 */
	public List<Tspa> getTspaList(Tspa tspa);

	/**
	 * 同步SAP销售部门数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchTvkbt();

	/**
	 * 查询销售部门列表总数
	 * 
	 * @param tvkbt
	 * @return
	 */
	public int getTvkbtCount(Tvkbt tvkbt);

	/**
	 * 查询销售部门列表
	 * 
	 * @param tvkbt
	 * @return
	 */
	public List<Tvkbt> getTvkbtList(Tvkbt tvkbt);

	/**
	 * 同步SAP销售组织-分销渠道-产品组-销售部门关系数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchTvkbz();

	/**
	 * 查询销售组织-分销渠道-产品组-销售部门关系列表总数
	 * 
	 * @param tvkbz
	 * @return
	 */
	public int getTvkbzCount(Tvkbz tvkbz);

	/**
	 * 查询销售部门-分销渠道-产品组-销售部门关系列表
	 * 
	 * @param tvkbz
	 * @return
	 */
	public List<Tvkbz> getTvkbzList(Tvkbz tvkbz);

	/**
	 * 同步SAP销售省份数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchTvgrt();

	/**
	 * 查询销售省份列表总数
	 * 
	 * @param tvgrt
	 * @return
	 */
	public int getTvgrtCount(Tvgrt tvgrt);

	/**
	 * 查询销售省份列表
	 * 
	 * @param tvgrt
	 * @return
	 */
	public List<Tvgrt> getTvgrtList(Tvgrt tvgrt);
	
	/**
	 * 根据大区查询销售省份列表
	 * 
	 * @param tvgrt
	 * @return
	 */
	public List<Tvgrt> getTvgrtListByZdqsf(Zdqsf zdqsf);
	/**
	 * 根据大区查询销售省份汇总
	 * @param zdqsf
	 * @return
	 */
	public int getTvgrtZdqsfCount(Zdqsf zdqsf);

	/**
	 * 同步SAP销售大区数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchT171t();

	/**
	 * 查询销售大区列表总数
	 * 
	 * @param t171t
	 * @return
	 */
	public int getT171tCount(T171t t171t);

	/**
	 * 查询销售大区列表
	 * 
	 * @param t171t
	 * @return
	 */
	public List<T171t> getT171tList(T171t t171t);

	/**
	 * 同步SAP工厂数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchT001w();

	/**
	 * 查询工厂列表总数
	 * 
	 * @return
	 */
	public int getT001wCount();

	/**
	 * 查询工厂列表
	 * 
	 * @param t001w
	 * @return
	 */
	public List<T001w> getT001wList(T001w t001w);

	/**
	 * 同步SAP公司代码数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchT001();

	/**
	 * 查询公司代码列表总数
	 * 
	 * @return
	 */
	public int getT001Count(T001 t001);

	/**
	 * 查询公司代码列表
	 * 
	 * @param t001
	 * @return
	 */
	public List<T001> getT001List(T001 t001);

	/**
	 * 同步SAP城市数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchTvv1t();

	/**
	 * 查询城市列表总数
	 * 
	 * @return
	 */
	public int getTvv1tCount(Tvv1t tvv1t);

	/**
	 * 查询城市列表
	 * 
	 * @param tvv1t
	 * @return
	 */
	public List<Tvv1t> getTvv1tList(Tvv1t tvv1t);

	/**
	 * 同步SAP区域数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchTvv2t();

	/**
	 * 查询区域列表总数
	 * 
	 * @return
	 */
	public int getTvv2tCount(Tvv2t tvv2t);

	/**
	 * 查询区域列表
	 * 
	 * @param tvv2t
	 * @return
	 */
	public List<Tvv2t> getTvv2tList(Tvv2t tvv2t);

	/**
	 * 同步SAP物料回扣组数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchTvbot();

	/**
	 * 查询物料回扣组列表总数
	 * 
	 * @return
	 */
	public int getTvbotCount(Tvbot tvbot);

	/**
	 * 查询物料回扣组列表
	 * 
	 * @param tvbot
	 * @return
	 */
	public List<Tvbot> getTvbotList(Tvbot tvbot);

	/**
	 * 同步物流行政区域数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchZwlqy();

	/**
	 * 查询物流行政区域列表总数
	 * 
	 * @return
	 */
	public int getZwlqyCount(Zwlqy zwlqy);

	/**
	 * 查询物流行政区域列表
	 * 
	 * @param zwlqy
	 * @return
	 */
	public List<Zwlqy> getZwlqyList(Zwlqy zwlqy);
	
	/**
	 * 级联查行政区域
	 * @param region
	 * @return
	 */
	public List<Region> searchRegion(Region region);
	
	/**
	 * 同步SAP销售省份与销售大区关系数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchZdqsf();

	/**
	 * 查询销售省份与销售大区关系列表总数
	 * 
	 * @param zdqsf
	 * @return
	 */
	public int getZdqsfCount(Zdqsf zdqsf);

	/**
	 * 查询销售省份与销售大区关系列表
	 * 
	 * @param zdqsf
	 * @return
	 */
	public List<Zdqsf> getZdqsfList(Zdqsf zdqsf);
	
	/**
	 * 同步SAP销售省份与销售部门关系数据到oracle数据库
	 * 
	 * @return
	 */
	public StringResult synchTvbvk();

	/**
	 * 查询销售省份与销售部门关系列表总数
	 * 
	 * @param tvbvk
	 * @return
	 */
	public int getTvbvkCount(Tvbvk tvbvk);

	/**
	 * 查询销售省份与销售部门关系列表
	 * 
	 * @param tvbvk
	 * @return
	 */
	public List<Tvbvk> getTvbvkList(Tvbvk tvbvk);
	/**
	 * 根据省份查询销售部门列表
	 * 
	 * @param 
	 * @return
	 */
	public List<Tvkbt> getTvkbtJsonListByTvbvk(Tvbvk tvbvk);
	/**
	 * 根据省份查询销售部门汇总
	 * @param 
	 * @return
	 */
	public int getTvkbtJsonListByTvbvkCount(Tvbvk tvbvk);
	
	/**
	 * 同步SAP物料组价格数据到oracle数据库
	 * 
	 * @param key
	 * @return
	 */
	public StringResult synchKbetr(String key);
	
	/**
	 * 查询物料组价格列表总数
	 * 
	 * @param a603Konp
	 * @return
	 */
	public int getKbetrCount(A603Konp a603Konp);

	/**
	 * 查询物料组价格列表
	 * 
	 * @param a603Konp
	 * @return
	 */
	public List<A603Konp> getKbetrList(A603Konp a603Konp);

}
