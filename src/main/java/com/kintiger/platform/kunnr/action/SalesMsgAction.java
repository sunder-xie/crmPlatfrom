package com.kintiger.platform.kunnr.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
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

/**
 * 经分销商里面的主数据调用
 * @author ljwang
 *
 */
public class SalesMsgAction extends BaseAction {
	private static final long serialVersionUID = 404251433948485364L;
	private ISalesService salesService;
	@Decode
	private String vkorg;// 销售组织代码
	@Decode
	private String bukrs;// 销售机构的公司代码
	@Decode
	private String vtweg;// 分销渠道
	@Decode
	private String vtext;// 名称
	@Decode
	private String spart;// 产品组
	@Decode
	private String vkbur;// 销售部门
	@Decode
	private String bezei;// 描述
	@Decode
	private String vkgrp;// 销售大区
	@Decode
	private String bzirk;// 销售省份
	@Decode
	private String bztxt;// 区名

	@Decode
	private String butxt;// 公司
	
	@Decode
	private String werks;// 工厂代码

	@Decode
	private String name1;// 工厂
	@Decode
	private String bonus; // 物料组回扣代码

	@Decode
	private String zwl0;
	
	private List<Tvko> tvkoList;
	private List<Tvtwt> tvtwtList;
	private List<Tspa> tspaList;
	private List<Tvkbt> tvkbtList;
	private List<Tvkbz> tvkbzList;
	private List<Tvgrt> tvgrtList;
	private List<T171t> t171tList;
	private List<T001w> t001wList;
	private List<T001> t001List;
	private List<Tvv1t> tvv1tList;
	private List<Tvv2t> tvv2tList;
	private List<Tvbot> tvbotList;
	private List<Zwlqy> zwlqyList;
	private String pid;// 上级ID
	private int level;// 级联级别
	private List<Region> regionList;// 区域级联列表
	private int total;
	private int num;

	
	/**
	 * 查询销售组织
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvkoList", include = { "mandt", "vkorg", "bukrs" })
	public String getTvkoJsonList() {
		tvkoList = new ArrayList<Tvko>();
		Tvko tvko = new Tvko();
    	tvko.setPagination("false");
		if (StringUtils.isNotEmpty(vkorg))
			tvko.setVkorg(vkorg);
		if (StringUtils.isNotEmpty(bukrs))
			tvko.setBukrs(bukrs);
		tvko.setStart(getStart());
		tvko.setEnd(getEnd());
		total = salesService.getTvkoCount(tvko);
		if (total != 0)
			tvkoList = salesService.getTvkoList(tvko);
		return JSON;
	}

	/**
	 * 查询分销渠道
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvtwtList", include = { "mandt", "spras", "vtweg",
			"vtext" })
	public String getTvtwtJsonList() {
		tvtwtList = new ArrayList<Tvtwt>();
		Tvtwt tvtwt = new Tvtwt();
		tvtwt.setPagination("false");
		if (StringUtils.isNotEmpty(vtweg))
			tvtwt.setVtweg(vtweg);
		if (StringUtils.isNotEmpty(vtext))
			tvtwt.setVtext(vtext);
		tvtwt.setStart(getStart());
		tvtwt.setEnd(getEnd());
		total = salesService.getTvtwtCount(tvtwt);
		if (total != 0)
			tvtwtList = salesService.getTvtwtList(tvtwt);
		return JSON;
	}
	
	/**
	 * 查询产品组
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tspaList", include = { "mandt", "spart", "vtext" })
	public String getTspaJsonList() {
		tspaList = new ArrayList<Tspa>();
		Tspa tspa = new Tspa();
		if(StringUtils.isNotEmpty(spart) && StringUtils.isNotEmpty(spart.trim()))
			tspa.setSpart(spart);
		if(StringUtils.isNotEmpty(vtext) && StringUtils.isNotEmpty(vtext.trim()))
			tspa.setVtext(vtext);
		tspa.setPagination("false");
		tspa.setStart(getStart());
		tspa.setEnd(getEnd());
		total = salesService.getTspaCount(tspa);
		if (total != 0)
			tspaList = salesService.getTspaList(tspa);
		return JSON;
	}

	/**
	 * 查询销售部门
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvkbtList", include = { "mandt", "spras", "vkbur",
			"bezei" })
	public String getTvkbtJsonList() {
		tvkbtList = new ArrayList<Tvkbt>();
		Tvkbt tvkbt = new Tvkbt();
		if (StringUtils.isNotEmpty(vkbur))
			tvkbt.setVkbur(vkbur);
		if (StringUtils.isNotEmpty(bezei))
			tvkbt.setBezei(bezei);
		 tvkbt.setPagination("false");
		 tvkbt.setStart(getStart());
		 tvkbt.setEnd(getEnd());
		 total = salesService.getTvkbtCount(tvkbt);
		 if (total != 0)
		tvkbtList = salesService.getTvkbtList(tvkbt);
		return JSON;
	}
	/**
	 * 跳转销售省份查询页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchTvgrt() {
		return "toSearchTvgrt";
	}
	/**
	 * 查询销售省份
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvgrtList", include = { "mandt", "vkgrp", "bezei" }, total = "total")
	public String getTvgrtJsonList() {
		tvgrtList = new ArrayList<Tvgrt>();
		Tvgrt tvgrt = new Tvgrt();
		if (StringUtils.isNotEmpty(vkgrp))
			tvgrt.setVkgrp(vkgrp);
		if (StringUtils.isNotEmpty(bezei))
			tvgrt.setBezei(bezei);
		tvgrt.setStart(getStart());
		tvgrt.setEnd(getEnd());
		 total = salesService.getTvgrtCount(tvgrt);
		 if(total!=0)
			 tvgrtList = salesService.getTvgrtList(tvgrt);
		return JSON;
	}

	/**
	 * 跳转销售大区查询页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchT171t() {
		return "toSearchT171t";
	}

	/**
	 * 查询销售大区
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "t171tList", include = { "mandt", "spras", "bzirk",
			"bztxt" }, total = "total")
	public String getT171tJsonList() {
		t171tList = new ArrayList<T171t>();
		T171t t171t = new T171t();
		if (StringUtils.isNotEmpty(bzirk))
			t171t.setBzirk(bzirk);
		if (StringUtils.isNotEmpty(bztxt))
			t171t.setBztxt(bztxt);
		t171t.setStart(getStart());
		t171t.setEnd(getEnd());
		total = salesService.getT171tCount(t171t);
		if (total != 0)
			t171tList = salesService.getT171tList(t171t);
		return JSON;
	}


	/**
	 * 查询发货工厂
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "t001wList", include = { "mandt", "werks", "name1" })
	public String getT001wJsonList() {
		t001wList = new ArrayList<T001w>();
		T001w t001w = new T001w();
		t001w.setStart(getStart());
		t001w.setEnd(getEnd());
     	t001w.setPagination("false");
		if(StringUtils.isNotEmpty(werks))
			t001w.setWerks(werks);
		if (StringUtils.isNotEmpty(name1)) {
			t001w.setName1(name1);
		}
		total = salesService.getT001wCount();
		if (total != 0) {
			t001wList = salesService.getT001wList(t001w);
		}
		return JSON;
	}

	/**
	 * 查询物料回扣组
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvbotList", include = { "mandt", "spras", "bonus",
			"vtext" })
	public String getTvbotJsonList() {
		tvbotList = new ArrayList<Tvbot>();
		Tvbot tvbot = new Tvbot();
		tvbot.setStart(getStart());
		tvbot.setEnd(getEnd());
 		tvbot.setPagination("false");
		if (StringUtils.isNotEmpty(bonus))
			tvbot.setBonus(bonus);
		if (StringUtils.isNotEmpty(vtext))
			tvbot.setVtext(vtext);
		tvbot.setFlag("K");
		total = salesService.getTvbotCount(tvbot);
		if (total != 0) {
			tvbotList = salesService.getTvbotList(tvbot);
		}
		return JSON;
	}


	/**
	 * 行政区划页面初始化
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchZwlqy() {
		return "toSearchZwlqy";
	}

	/**
	 * 级联查询行政区域
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "regionList", include = { "id", "text" })
	public String searchRegion() {
		String idColumn = "ZWL0" + level;
		String textColumn = "ZWL0" + level + "T";
		String paraColumn = level == 1 ? "" : "ZWL0" + (level - 1);
		Region region = new Region();
		region.setIdColumn(idColumn);
		region.setTextColumn(textColumn);
		region.setParaColumn(paraColumn);
		region.setPid(pid);
		regionList = salesService.searchRegion(region);
		return JSON;
	}
	/**
	 * 跳转销售组织-分销渠道-产品组-销售部门关系查询页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchTvkbz() {
		return "toSearchTvkbz";
	}

	/**
	 * 查询销售组织-分销渠道-产品组-销售部门关系
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvkbzList", include = { "mandt", "vkorg", "vkorgTxt","vtweg","vtwegTxt",
			"spart","spartTxt", "vkbur","vkburTxt" }, total = "total")
	public String getTvkbzJsonList() {
		tvkbzList = new ArrayList<Tvkbz>();
		Tvkbz tvkbz = new Tvkbz();
		if (StringUtils.isNotEmpty(vkorg))
			tvkbz.setVkorg(vkorg.trim());
		if (StringUtils.isNotEmpty(vtweg))
			tvkbz.setVtweg(vtweg);
		if (StringUtils.isNotEmpty(spart))
			tvkbz.setSpart(spart);
		tvkbz.setStart(getStart());
		tvkbz.setEnd(getEnd());
		total = salesService.getTvkbzCount(tvkbz);
		if (total != 0)
			tvkbzList = salesService.getTvkbzList(tvkbz);
		return JSON;
	}
	
	/**
	 * 根据大区查省份
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvgrtList", include = { "mandt", "vkgrp", "bezei" }/*, total = "total"*/)
	public String getTvgrtListByZdqsf() {
		Zdqsf zdqsf = new Zdqsf();
		if (StringUtils.isNotEmpty(bzirk))
			zdqsf.setBzirk(bzirk);
		
		if (StringUtils.isNotEmpty(bezei))
		zdqsf.setSearch(bezei);
		zdqsf.setStart(getStart());
		zdqsf.setEnd(getEnd());
		zdqsf.setPagination("false");
		tvgrtList = new ArrayList<Tvgrt>();
		 total = salesService.getTvgrtZdqsfCount(zdqsf);
		 if(total!=0)
			 tvgrtList = salesService.getTvgrtListByZdqsf(zdqsf);
		return JSON;
	}

	/**
	 * 根据省份查部门
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvkbtList", include = { "mandt", "spras", "vkbur",
	"bezei" })
	public String getTvkbtJsonListByTvbvk() {
		Tvbvk tvbvk = new Tvbvk();
		if (StringUtils.isNotEmpty(vkgrp))
			tvbvk.setVkgrp(vkgrp);
		tvbvk.setPagination("false");
		tvbvk.setStart(getStart());
		tvbvk.setEnd(getEnd());
		tvkbtList = new ArrayList<Tvkbt>();
		 total = salesService.getTvkbtJsonListByTvbvkCount(tvbvk);
		 if(total!=0)
			 tvkbtList= salesService.getTvkbtJsonListByTvbvk(tvbvk);
		return JSON;
	}
	
	
	
	public ISalesService getSalesService() {
		return salesService;
	}

	public void setSalesService(ISalesService salesService) {
		this.salesService = salesService;
	}

	public String getVkorg() {
		return vkorg;
	}

	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}

	public String getBukrs() {
		return bukrs;
	}

	public void setBukrs(String bukrs) {
		this.bukrs = bukrs;
	}

	public List<Tvko> getTvkoList() {
		return tvkoList;
	}

	public void setTvkoList(List<Tvko> tvkoList) {
		this.tvkoList = tvkoList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getVtweg() {
		return vtweg;
	}

	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}

	public String getVtext() {
		return vtext;
	}

	public void setVtext(String vtext) {
		this.vtext = vtext;
	}

	public List<Tvtwt> getTvtwtList() {
		return tvtwtList;
	}

	public void setTvtwtList(List<Tvtwt> tvtwtList) {
		this.tvtwtList = tvtwtList;
	}

	public String getSpart() {
		return spart;
	}

	public void setSpart(String spart) {
		this.spart = spart;
	}

	public List<Tspa> getTspaList() {
		return tspaList;
	}

	public void setTspaList(List<Tspa> tspaList) {
		this.tspaList = tspaList;
	}

	public String getVkbur() {
		return vkbur;
	}

	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}

	public String getBezei() {
		return bezei;
	}

	public void setBezei(String bezei) {
		this.bezei = bezei;
	}

	public List<Tvkbt> getTvkbtList() {
		return tvkbtList;
	}

	public void setTvkbtList(List<Tvkbt> tvkbtList) {
		this.tvkbtList = tvkbtList;
	}

	public List<Tvkbz> getTvkbzList() {
		return tvkbzList;
	}

	public void setTvkbzList(List<Tvkbz> tvkbzList) {
		this.tvkbzList = tvkbzList;
	}

	public String getVkgrp() {
		return vkgrp;
	}

	public void setVkgrp(String vkgrp) {
		this.vkgrp = vkgrp;
	}


	public List<Tvgrt> getTvgrtList() {
		return tvgrtList;
	}

	public void setTvgrtList(List<Tvgrt> tvgrtList) {
		this.tvgrtList = tvgrtList;
	}

	public String getBzirk() {
		return bzirk;
	}

	public void setBzirk(String bzirk) {
		this.bzirk = bzirk;
	}

	public String getBztxt() {
		return bztxt;
	}

	public void setBztxt(String bztxt) {
		this.bztxt = bztxt;
	}

	public List<T171t> getT171tList() {
		return t171tList;
	}

	public void setT171tList(List<T171t> t171tList) {
		this.t171tList = t171tList;
	}

	public List<T001w> getT001wList() {
		return t001wList;
	}

	public void setT001wList(List<T001w> t001wList) {
		this.t001wList = t001wList;
	}

	public List<T001> getT001List() {
		return t001List;
	}

	public void setT001List(List<T001> t001List) {
		this.t001List = t001List;
	}

	public List<Tvv1t> getTvv1tList() {
		return tvv1tList;
	}

	public void setTvv1tList(List<Tvv1t> tvv1tList) {
		this.tvv1tList = tvv1tList;
	}

	public List<Tvv2t> getTvv2tList() {
		return tvv2tList;
	}

	public void setTvv2tList(List<Tvv2t> tvv2tList) {
		this.tvv2tList = tvv2tList;
	}

	public List<Tvbot> getTvbotList() {
		return tvbotList;
	}

	public void setTvbotList(List<Tvbot> tvbotList) {
		this.tvbotList = tvbotList;
	}

	public String getButxt() {
		return butxt;
	}

	public void setButxt(String butxt) {
		this.butxt = butxt;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public List<Zwlqy> getZwlqyList() {
		return zwlqyList;
	}

	public void setZwlqyList(List<Zwlqy> zwlqyList) {
		this.zwlqyList = zwlqyList;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Region> getRegionList() {
		return regionList;
	}

	public void setRegionList(List<Region> regionList) {
		this.regionList = regionList;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getZwl0() {
		return zwl0;
	}

	public void setZwl0(String zwl0) {
		this.zwl0 = zwl0;
	}

	public String getWerks() {
		return werks;
	}

	public void setWerks(String werks) {
		this.werks = werks;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
