package com.kintiger.platform.sales.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
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

public class SalesAction extends BaseAction {
	private static final long serialVersionUID = 404251433948485364L;
	private ISalesService salesService;
	@Decode
	private String vkorg;// ������֯����
	@Decode
	private String bukrs;// ���ۻ����Ĺ�˾����
	@Decode
	private String vtweg;// ��������
	@Decode
	private String vtext;// ����
	@Decode
	private String spart;// ��Ʒ��
	@Decode
	private String vkbur;// ���۲���
	@Decode
	private String bezei;// ����
	@Decode
	private String vkgrp;// ����ʡ��
	@Decode
	private String bzirk;// ���۴���
	@Decode
	private String bztxt;// ����

	@Decode
	private String butxt;// ��˾

	@Decode
	private String werks;// ��������

	@Decode
	private String name1;// ����
	@Decode
	private String bonus; // ������ؿ۴���

	@Decode
	private String zwl0;

	private String kondm;// ���϶�����
	/*
	 * @Decode private String zwl01;
	 * 
	 * @Decode private String zwl02;
	 * 
	 * @Decode private String zwl03;
	 * 
	 * @Decode private String zwl04;
	 */

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
	private List<Zdqsf> zdqsfList;
	private List<Tvbvk> tvbvkList;
	private String pid;// �ϼ�ID
	private int level;// ��������
	private List<Region> regionList;// �������б�
	private List<A603Konp> a603KonpList;
	private int total;

	/**
	 * ��ת������֯��ѯҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchTvko() {
		return "toSearchTvko";
	}

	/**
	 * ͬ��SAP������֯���ݵ�oracle���ݿ�
	 * 
	 * @return
	 */
	public String synchTvko() {
		StringResult result = salesService.synchTvko();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ������֯
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvkoList", include = { "mandt", "vkorg", "bukrs" })
	public String getTvkoJsonList() {
		tvkoList = new ArrayList<Tvko>();
		Tvko tvko = new Tvko();
		// tvko.setPagination("false");
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
	 * ��ת����������ѯҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchTvtwt() {
		return "toSearchTvtwt";
	}

	/**
	 * ͬ��SAP�����������ݵ�oracle���ݿ�
	 * 
	 * @return
	 */
	public String synchTvtwt() {
		StringResult result = salesService.synchTvtwt();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ��������
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvtwtList", include = { "mandt", "spras", "vtweg", "vtext" }, total = "total")
	public String getTvtwtJsonList() {
		tvtwtList = new ArrayList<Tvtwt>();
		Tvtwt tvtwt = new Tvtwt();
		// tvtwt.setPagination("false");
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
	 * ��ת��Ʒ���ѯҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchTspa() {
		return "toSearchTspa";
	}

	/**
	 * ͬ��SAP��Ʒ�����ݵ�oracle���ݿ�
	 * 
	 * @return
	 */
	public String synchTspa() {
		StringResult result = salesService.synchTspa();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ��Ʒ��
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tspaList", include = { "mandt", "spart", "vtext" }, total = "total")
	public String getTspaJsonList() {
		tspaList = new ArrayList<Tspa>();
		Tspa tspa = new Tspa();
		if (StringUtils.isNotEmpty(spart) && StringUtils.isNotEmpty(spart.trim()))
			tspa.setSpart(spart);
		if (StringUtils.isNotEmpty(vtext) && StringUtils.isNotEmpty(vtext.trim()))
			tspa.setVtext(vtext);
		// tspa.setPagination("false");
		tspa.setStart(getStart());
		tspa.setEnd(getEnd());
		total = salesService.getTspaCount(tspa);
		if (total != 0)
			tspaList = salesService.getTspaList(tspa);
		return JSON;
	}

	/**
	 * ��ת���۲��Ų�ѯҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchTvkbt() {
		return "toSearchTvkbt";
	}

	/**
	 * ͬ��SAP���۲������ݵ�oracle���ݿ�
	 * 
	 * @return
	 */
	public String synchTvkbt() {
		StringResult result = salesService.synchTvkbt();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ���۲���
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvkbtList", include = { "mandt", "spras", "vkbur", "bezei" }, total = "total")
	public String getTvkbtJsonList() {
		tvkbtList = new ArrayList<Tvkbt>();
		Tvkbt tvkbt = new Tvkbt();
		if (StringUtils.isNotEmpty(vkbur))
			tvkbt.setVkbur(vkbur);
		if (StringUtils.isNotEmpty(bezei))
			tvkbt.setBezei(bezei);
		tvkbt.setStart(getStart());
		tvkbt.setEnd(getEnd());
		total = salesService.getTvkbtCount(tvkbt);
		if (total != 0)
			tvkbtList = salesService.getTvkbtList(tvkbt);
		return JSON;
	}

	/**
	 * ��ת������֯-��������-��Ʒ��-���۲��Ź�ϵ��ѯҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchTvkbz() {
		return "toSearchTvkbz";
	}

	/**
	 * ͬ��SAP������֯-��������-��Ʒ��-���۲��Ź�ϵ���ݵ�oracle���ݿ�
	 * 
	 * @return
	 */
	public String synchTvkbz() {
		StringResult result = salesService.synchTvkbz();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ������֯-��������-��Ʒ��-���۲��Ź�ϵ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvkbzList", include = { "mandt", "vkorg", "vtweg", "spart", "vkbur" }, total = "total")
	public String getTvkbzJsonList() {
		tvkbzList = new ArrayList<Tvkbz>();
		Tvkbz tvkbz = new Tvkbz();
		if (StringUtils.isNotEmpty(vkorg))
			tvkbz.setVkorg(vkorg);
		if (StringUtils.isNotEmpty(vtweg))
			tvkbz.setVtweg(vtweg);
		if (StringUtils.isNotEmpty(spart))
			tvkbz.setSpart(spart);
		if (StringUtils.isNotEmpty(vkbur))
			tvkbz.setVkbur(vkbur);
		tvkbz.setStart(getStart());
		tvkbz.setEnd(getEnd());
		total = salesService.getTvkbzCount(tvkbz);
		if (total != 0)
			tvkbzList = salesService.getTvkbzList(tvkbz);
		return JSON;
	}

	/**
	 * ��ת����ʡ�ݲ�ѯҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchTvgrt() {
		return "toSearchTvgrt";
	}

	/**
	 * ͬ��SAP����ʡ�����ݵ�oracle���ݿ�
	 * 
	 * @return
	 */
	public String synchTvgrt() {
		StringResult result = salesService.synchTvgrt();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ����ʡ��
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
		if (total != 0)
			tvgrtList = salesService.getTvgrtList(tvgrt);
		return JSON;
	}

	/**
	 * ��ת���۴�����ѯҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchT171t() {
		return "toSearchT171t";
	}

	/**
	 * ͬ��SAP���۴������ݵ�oracle���ݿ�
	 * 
	 * @return
	 */
	public String synchT171t() {
		StringResult result = salesService.synchT171t();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ���۴���
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "t171tList", include = { "mandt", "spras", "bzirk", "bztxt" }, total = "total")
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
	 * ��������ҳ���ʼ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchT001w() {
		return "toSearchT001w";
	}

	/**
	 * ��ѯ��������
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "t001wList", include = { "mandt", "werks", "name1" }, total = "total")
	public String getT001wJsonList() {
		t001wList = new ArrayList<T001w>();
		T001w t001w = new T001w();
		t001w.setStart(getStart());
		t001w.setEnd(getEnd());
		// t001w.setPagination("false");
		if (StringUtils.isNotEmpty(werks))
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
	 * ͬ��SAP�����������ݵ�oracle���ݿ�
	 * 
	 * @return
	 */
	public String synchT001w() {
		StringResult result = salesService.synchT001w();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��˾����ҳ���ʼ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchT001() {
		return "toSearchT001";
	}

	/**
	 * ��ѯ��˾����
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "t001List", include = { "mandt", "bukrs", "butxt" }, total = "total")
	public String getT001JsonList() {
		t001List = new ArrayList<T001>();
		T001 t001 = new T001();
		t001.setStart(getStart());
		t001.setEnd(getEnd());
		if (StringUtils.isNotEmpty(bukrs))
			t001.setBukrs(bukrs);
		if (StringUtils.isNotEmpty(butxt))
			t001.setButxt(butxt);
		total = salesService.getT001Count(t001);
		if (total != 0)
			t001List = salesService.getT001List(t001);
		return JSON;
	}

	/**
	 * ͬ��Sap��˾����
	 * 
	 * @return
	 */
	public String synchT001() {
		StringResult result = salesService.synchT001();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ����
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvv1tList", include = { "mandt", "spras", "kvgr1", "bezei" }, total = "total")
	public String getTvv1tJsonList() {
		tvv1tList = new ArrayList<Tvv1t>();
		Tvv1t tvv1t = new Tvv1t();
		tvv1t.setStart(getStart());
		tvv1t.setEnd(getEnd());
		if (StringUtils.isNotEmpty(bezei))
			tvv1t.setBezei(bezei);
		total = salesService.getTvv1tCount(tvv1t);
		if (total != 0)
			tvv1tList = salesService.getTvv1tList(tvv1t);
		return JSON;
	}

	/**
	 * ��ѯ����
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvv2tList", include = { "mandt", "spras", "kvgr2", "bezei" }, total = "total")
	public String getTvv2tJsonList() {
		tvv2tList = new ArrayList<Tvv2t>();
		Tvv2t tvv2t = new Tvv2t();
		tvv2t.setStart(getStart());
		tvv2t.setEnd(getEnd());
		if (StringUtils.isNotEmpty(bezei))
			tvv2t.setBezei(bezei);
		total = salesService.getTvv2tCount(tvv2t);
		if (total != 0)
			tvv2tList = salesService.getTvv2tList(tvv2t);
		return JSON;
	}

	/**
	 * ���ϻؿ���ҳ���ʼ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchTvbot() {
		return "toSearchTvbot";
	}

	/**
	 * ��ѯ���ϻؿ���
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvbotList", include = { "mandt", "spras", "bonus", "vtext" }, total = "total")
	public String getTvbotJsonList() {
		tvbotList = new ArrayList<Tvbot>();
		Tvbot tvbot = new Tvbot();
		tvbot.setStart(getStart());
		tvbot.setEnd(getEnd());
		// tvbot.setPagination("false");
		if (StringUtils.isNotEmpty(bonus))
			tvbot.setBonus(bonus);
		if (StringUtils.isNotEmpty(vtext))
			tvbot.setVtext(vtext);
		total = salesService.getTvbotCount(tvbot);
		if (total != 0) {
			tvbotList = salesService.getTvbotList(tvbot);
		}
		return JSON;
	}

	/**
	 * ͬ��Sap������ؿ�
	 * 
	 * @return
	 */
	public String synchTvbot() {
		StringResult result = salesService.synchTvbot();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��������ҳ���ʼ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchZwlqy() {
		return "toSearchZwlqy";
	}

	/**
	 * ��ѯ������������
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "zwlqyList", include = { "zwl00", "zwl01", "zwl02", "zwl03", "zwl04", "zwl00t", "zwl01t",
		"zwl02t", "zwl03t", "zwl04t" }, total = "total")
	public String getZwlqyJsonList() {
		zwlqyList = new ArrayList<Zwlqy>();
		Zwlqy zwlqy = new Zwlqy();
		zwlqy.setStart(getStart());
		zwlqy.setEnd(getEnd());
		// zwlqy.setPagination("false");
		if (StringUtils.isNotEmpty(zwl0)) {
			if (level == 1) {
				zwlqy.setZwl00(zwl0);
			} else if (level == 2) {
				zwlqy.setZwl01(zwl0);
			} else if (level == 3) {
				zwlqy.setZwl02(zwl0);
			} else if (level == 4) {
				zwlqy.setZwl03(zwl0);
			} else if (level == 5) {
				zwlqy.setZwl04(zwl0);
			}
		}
		total = salesService.getZwlqyCount(zwlqy);
		if (total != 0)
			zwlqyList = salesService.getZwlqyList(zwlqy);
		return JSON;
	}

	/**
	 * ͬ��Sap��������
	 * 
	 * @return
	 */
	public String synchZwlqy() {
		StringResult result = salesService.synchZwlqy();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ������ѯ��������
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
	 * ����������������ѯ����������ѯ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "regionList", include = { "id", "text" })
	public String searchRegion01() {
		String idColumn = "ZWL0" + level;
		String textColumn = "ZWL0" + level + "T";
		String paraColumn = level == 0 ? "" : "ZWL0" + (level - 1);
		Region region = new Region();
		region.setIdColumn(idColumn);
		region.setTextColumn(textColumn);
		region.setParaColumn(paraColumn);
		region.setPid(pid);
		regionList = salesService.searchRegion(region);
		return JSON;
	}

	/**
	 * ��ת����ʡ�������۴�����ϵ��ѯҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchZdqsf() {
		return "toSearchZdqsf";
	}

	/**
	 * ͬ��SAP����ʡ�������۴�����ϵ���ݵ�oracle���ݿ�
	 * 
	 * @return
	 */
	public String synchZdqsf() {
		StringResult result = salesService.synchZdqsf();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ����ʡ�������۴�����ϵ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "zdqsfList", include = { "mandt", "vkgrp", "bzirk", "bztxt", "bezei" }, total = "total")
	public String getZdqsfJsonList() {
		zdqsfList = new ArrayList<Zdqsf>();
		Zdqsf zdqsf = new Zdqsf();
		if (StringUtils.isNotEmpty(vkgrp))
			zdqsf.setVkgrp(vkgrp);
		if (StringUtils.isNotEmpty(bzirk))
			zdqsf.setBzirk(bzirk);
		zdqsf.setStart(getStart());
		zdqsf.setEnd(getEnd());
		total = salesService.getZdqsfCount(zdqsf);
		if (total != 0)
			zdqsfList = salesService.getZdqsfList(zdqsf);
		return JSON;
	}

	/**
	 * ��ת����ʡ�������۲��Ź�ϵ��ѯҳ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchTvbvk() {
		return "toSearchTvbvk";
	}

	/**
	 * ͬ��SAP����ʡ�������۲��Ź�ϵ���ݵ�oracle���ݿ�
	 * 
	 * @return
	 */
	public String synchTvbvk() {
		StringResult result = salesService.synchTvbvk();
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ����ʡ�������۲��Ź�ϵ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "tvbvkList", include = { "mandt", "vkgrp", "vkbur" }, total = "total")
	public String getTvbvkJsonList() {
		tvbvkList = new ArrayList<Tvbvk>();
		Tvbvk tvbvk = new Tvbvk();
		if (StringUtils.isNotEmpty(vkgrp))
			tvbvk.setVkgrp(vkgrp);
		if (StringUtils.isNotEmpty(vkbur))
			tvbvk.setVkbur(vkbur);
		tvbvk.setStart(getStart());
		tvbvk.setEnd(getEnd());
		total = salesService.getTvbvkCount(tvbvk);
		if (total != 0)
			tvbvkList = salesService.getTvbvkList(tvbvk);
		return JSON;
	}

	/**
	 * ͬ��������۸�
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchKbetr() {
		return "toSearchKbetr";
	}

	/**
	 * ͬ��SAP������۸�oracle���ݿ�
	 * 
	 * @return
	 */
	public String synchKbetr() {
		StringResult result = salesService.synchKbetr(businessEnv.getProperty("crm.vkorg"));
		if (ISalesService.SUCCESS.equals(result.getCode())) {
			this.setSuccessMessage(ISalesService.SUCCESS_MESSAGE);
		} else {
			this.setFailMessage(ISalesService.ERROR_MESSAGE);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��ѯ����ʡ�������۲��Ź�ϵ
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "a603KonpList", include = { "vkorg", "kondm", "kbetr", "datab", "datbi" }, total = "total")
	public String getKbetrJsonList() {
		a603KonpList = new ArrayList<A603Konp>();
		A603Konp a603Konp = new A603Konp();
		if (StringUtils.isNotEmpty(kondm))
			a603Konp.setKondm(kondm);
		a603Konp.setStart(getStart());
		a603Konp.setEnd(getEnd());
		total = salesService.getKbetrCount(a603Konp);
		if (total != 0)
			a603KonpList = salesService.getKbetrList(a603Konp);
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

	public List<Zdqsf> getZdqsfList() {
		return zdqsfList;
	}

	public void setZdqsfList(List<Zdqsf> zdqsfList) {
		this.zdqsfList = zdqsfList;
	}

	public List<Tvbvk> getTvbvkList() {
		return tvbvkList;
	}

	public void setTvbvkList(List<Tvbvk> tvbvkList) {
		this.tvbvkList = tvbvkList;
	}

	public String getKondm() {
		return kondm;
	}

	public void setKondm(String kondm) {
		this.kondm = kondm;
	}

	public List<A603Konp> getA603KonpList() {
		return a603KonpList;
	}

	public void setA603KonpList(List<A603Konp> a603KonpList) {
		this.a603KonpList = a603KonpList;
	}

}
