package com.kintiger.platform.distributionData.action;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;

import com.kintiger.platform.distributionData.pojo.DistributionData;
import com.kintiger.platform.distributionData.service.IDistributionDataService;
import com.kintiger.platform.distributionGoal.pojo.DistributionGoal;
import com.kintiger.platform.distributionInventory.pojo.DistributionInventory;

import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;

public class DistributionDataAction extends BaseAction {

	private static final long serialVersionUID = -532423459213585547L;
	private static Log logger = LogFactory.getLog(DistributionDataAction.class);
	private String orgId;
	@Decode
	private String orgName;
	private String orgRegion;
	private String orgProvince;
	private String orgCity;
	private String firstUser;
	private String position;
	private String dire_super_name;
	private String dire_super_position;
	private String kunnrId;
	@Decode
	private String kunnrName;
	private String matter;
	private String matterNum;
	private String state;

	private String startDate;

	private String endDate;
	private int boxNum;
	private String inputDate;
	private String checkName;
	private String trFlag;
	private int aOneYW;
	private int aOneXY;
	private int aOneMX;
	private int aOneCM;
	private int aOneQKL;
	private int aOneKF;
	private int aOneLC;
	
	private int aTwoQKL;
	private int aTwoXY;
	private int aTwoYW;
	private int aThree;
	private int aFour;
	private int aFive;
	private int aSix;
	private int aSeven;
	private int aEightYW;
	private int aEightXY;
	private int bOne;
	private int bSix;
	private int bEight;
	private int cOne;
	private int cSix;
	private int cSeven;
	private int cEight;
	private int dNine9;
	private int dNine12;
	private int dNine20;
	private int dNine30;
	private int dTen;
	private int eOne;
	private int hSeven;
	private String userId;
	private long dgId;
	private String resign_date;

	private String ids;
	private int size;
	private IOrgService orgServiceHessian;
	private IDistributionDataService distributionDataService;
	private List<DistributionData> distributionDataLists;
	private String uploadFileFileName;
	private File uploadFile;
	private String xmlFilePath;
	private DistributionData dGoal;
	private IKunnrService kunnrService;

	public String distributionDataSearch() {
		userId = this.getUser().getUserId();
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		if (org.getOrgId() == 51240) {
			return "distributionDataSearch";
		} else {
			return "toDistributionDataSearch";
		}
	}

	/**
	 * ��ѯĿ�� add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "distributionDataLists", total = "size", include = {
			"orgRegion", "orgProvince", "orgCity", "firstUser", "secondUser",
			"thirdUser", "dire_super_name", "dire_super_position", "kunnrName",
			"kunnrId", "inputDate", "aOneYW","aOneXY","aOneMX","aOneCM","aOneQKL",
			"aOneKF","aOneLC","aTwoQKL","aTwoXY","aTwoYW", "dgId", "aThree", "aFour",
			"aFive", "aSix", "aSeven", "aEightYW","aEightXY", "bOne","bSix", "bEight", 
			"cOne",	"cSix", "cSeven", "cEight", "dNine9","dNine12","dNine20","dNine30",
			"dTen", "eOne", "hSeven","inputName", "checkName", "trFlag", "resign_date" ,
			"lastDate","thisSysdate","createDate"})
	public String getDistributionDataList() {
		distributionDataLists = new ArrayList<DistributionData>();
		DistributionData dGoal = new DistributionData();
		dGoal.setOrgId(this.getUser().getOrgId());
		if (StringUtils.isNotEmpty(orgId)) {
			dGoal.setOrgId(orgId);
		}
		if (StringUtils.isNotEmpty(orgName)) {
			dGoal.setOrgName(orgName);
		}
		if (StringUtils.isNotEmpty(kunnrId)) {
			dGoal.setKunnrId(kunnrId);
		}
		if (StringUtils.isNotEmpty(kunnrName)) {
			dGoal.setKunnrName(kunnrName);
		}
		if (StringUtils.isNotEmpty(startDate)) {
			dGoal.setStartDate(startDate);
		}
		if (StringUtils.isNotEmpty(endDate)) {
			dGoal.setEndDate(endDate);
		}
		if (StringUtils.isNotEmpty(trFlag)) {
			dGoal.setTrFlag(trFlag);
		}
		dGoal.setStart(this.getStart());
		dGoal.setEnd(this.getEnd());
		size = distributionDataService.getDistributionDataCount(dGoal);
		if (size != 0) {
			distributionDataLists = distributionDataService
					.getDistributionDataList(dGoal);
		}
		return JSON;
	}

	/**
	 * ҳ��ɾ��Ŀ��add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String deleteDistributionData() {
		DistributionData dGoal = new DistributionData();
		try {
			String[] l = ids.split(",");
			dGoal.setIds(l);

			StringResult result = distributionDataService
					.deleteDistributionData(dGoal);

			if (distributionDataService.ERROR.equals(result.getCode())) {
				this.setFailMessage(result.getResult());
			} else {
				this.setSuccessMessage("����ɹ�");
			}
		} catch (Exception e) {
			this.setFailMessage("����ʧ�ܣ�");
			logger.error(dGoal, e);
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String updateDistributionData() {
		DistributionData dGoal;
		List<DistributionData> dGoals = new ArrayList<DistributionData>();
		String[] m = { "A1","A1","A1","A1","A1","A1","A1", "A3", "B1", "C1", "A4", "A5", 
				"A6","C6", "A7","C7", "A2","A2","A2", "A8","A8", "B8", "C8", "H7", "D9",
				"D9","D9","D9","D10", "E1" };
		String[] m_num = {"10100001","10100002","10100003","10100004","10100005","10100006",
				"10100007","10100009","10600001","10800003","10400002","10400001","10100012",
				"10800002","10100013","10800004","10500001","10500003","10500004","10500005",
				"10500006","10500008","10500007","10900001","10100014","10100015","10100016",
				"10800005","10800006","10800007"	
		};
		int[] b = { aOneYW,aOneXY,aOneMX,aOneCM,aOneQKL,aOneKF,aOneLC,aThree,bOne,cOne,
				aFour,aFive,aSix,cSix,aSeven,cSeven,aTwoQKL,aTwoXY,aTwoYW,aEightYW,
				aEightXY,bEight,cEight,hSeven, dNine9,dNine12,dNine20,dNine30, dTen, eOne };
		
		for (int i = 0; i < m.length; i++) {
			dGoal = new DistributionData();
			dGoal.setDgId(dgId);
			dGoal.setState("1");
			dGoal.setMatter(m[i]);
			dGoal.setBoxNum(b[i]);
			dGoal.setMatterNum(m_num[i]);
			dGoals.add(dGoal);
		}
		for (int i = 0; i < dGoals.size(); i++) {
			try {
				StringResult result = distributionDataService
						.updateDistributionData(dGoals.get(i));
				if (distributionDataService.ERROR.equals(result.getCode())) {
					this.setFailMessage(result.getResult());
				} else {
					this.setSuccessMessage("����ɹ�");
				}
			} catch (Exception e) {
				this.setFailMessage("����ʧ�ܣ�");
				logger.error(dGoals, e);
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ����CSVģ�� add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportMouldCsv() {
		OutputStream os = null;
		String report_name = "���о���������ݵ���ģ��";
		PrintWriter print = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("��������");
			linedata.append(",");
			linedata.append("�����̱��");
			linedata.append(",");
			linedata.append("����������");
			linedata.append(",");
			linedata.append("��������(yyyy/mm/dd)");
			linedata.append(",");
			linedata.append("���о���(����.û��������)");
			linedata.append(",");
			linedata.append("ʡ������(����.û��������)");
			linedata.append(",");
			linedata.append("��������(�Ǳ���)");
			linedata.append(",");
			linedata.append("A1Ҭ������(ԭζ)-80g*30");
			linedata.append(",");
			linedata.append("A1Ҭ������(����)-80g*30");
			linedata.append(",");
			linedata.append("A1Ҭ������(����)-80g*30");
			linedata.append(",");
			linedata.append("A1Ҭ������(��ݮ)-80g*30");
			linedata.append(",");
			linedata.append("A1Ҭ������(�ɿ���)-80g*30");
			linedata.append(",");
			linedata.append("A1Ҭ������(����)-80g*30");
			linedata.append(",");
			linedata.append("A1Ҭ������(�̲�)-80g*30");
			linedata.append(",");
			linedata.append("A3Ҭ������װ-80g*30");
			linedata.append(",");
			linedata.append("B1�춹����-64g*30");
			linedata.append(",");
			linedata.append("C1��Բ���浥��-65g*30");
			linedata.append(",");
			linedata.append("A4Ҭ�����װ-80g*12*8");
			linedata.append(",");
			linedata.append("A5Ҭ�����װ-80g*8*10");
			linedata.append(",");
			linedata.append("A6Ҭ����ͥ����װ-80g*16*6");
			linedata.append(",");
			linedata.append("C6��Բ�����ͥ����װ-65g*16*6");
			linedata.append(",");
			linedata.append("A7Ҭ����ͥ����װ-80g*15*6");
			linedata.append(",");
			linedata.append("C7��Բ�����ͥ����װ-65g*15*6");
			linedata.append(",");
			linedata.append("A2Ҭ������װ(�ɿ���)-80g*6*6");
			linedata.append(",");
			linedata.append("A2Ҭ������װ(����)-80g*6*6");
			linedata.append(",");
			linedata.append("A2Ҭ������װ(ԭζ)-80g*6*6");
			linedata.append(",");
			linedata.append("A8Ҭ������װ(ԭζ)-80g*3*10");
			linedata.append(",");
			linedata.append("A8Ҭ������װ(����)-80g*3*10");
			linedata.append(",");
			linedata.append("B8�춹����װ-80g*3*10");
			linedata.append(",");
			linedata.append("C8��Բ��������װ-65g*3*10");
			linedata.append(",");
			linedata.append("H7�����̲赥ƿ-400ml*15");
			linedata.append(",");
			linedata.append("D9��ֵ���װ(ȫ�Ҹ���Ͼ������ר��1*9)");
			linedata.append(",");
			linedata.append("D9��ֵ���װ(�μ��˺춹��Բ�������ר��1*12)");
			linedata.append(",");
			linedata.append("D9��ֵ���װ(����ζ�ͷ�Ҭ��ϵ�е���ר��1*20)");
			linedata.append(",");
			linedata.append("D9��ֵ���װ(��ֵ���װ1*30)");
			linedata.append(",");
			linedata.append("D10�������װ-15*6");
			linedata.append(",");
			linedata.append("E1�������װ-12*6");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (print != null) {
				try {
					print.close();
				} catch (Exception e) {
					logger.error(e);
				}
				print = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error(e);
				}
				os = null;
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * ��������Ŀ��By CSV add by allen Ŀ����ά�ȣ���֯���ꡢ�¡����� 1����֯�Ƿ�����֯Ŀ���� 2���ж�ϵͳ��֯����Ŀ�����Ƿ����
	 * 3�������ľ�����Ŀ�������ܳ�����֯����Ŀ���� 4����ͬ��Ŀ�����Ƿ��Ѿ����� 4��ͬһ��֯�µľ�����Ŀ����֮�Ͳ��ܳ�����֯����Ŀ����
	 * 
	 * @return
	 */
	@PermissionSearch
	public String importDistributionDataCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		try {
			distributionDataLists = new ArrayList<DistributionData>();
			String rcs = "";
			String rcs2 = "";
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					String[] header = SuperCSV.getHeaderFromFile(new File(
							uploadFile.toString()));
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // ��ȡString����
					String[] m = { "A1","A1","A1","A1","A1","A1","A1", "A3", "B1", "C1", "A4", "A5", 
							"A6","C6", "A7","C7", "A2","A2","A2", "A8","A8", "B8", "C8", "H7", "D9",
							"D9","D9","D9","D10", "E1" };
					String[] m_num = {"10100001","10100002","10100003","10100004","10100005","10100006",
							"10100007","10100009","10600001","10800003","10400002","10400001","10100012",
							"10800002","10100013","10800004","10500001","10500003","10500004","10500005",
							"10500006","10500008","10500007","10900001","10100014","10100015","10100016",
							"10800005","10800006","10800007"	
					};
					DistributionData dGoal;
					DistributionData dGoal1;
					for (int j = 0; j < content.size(); j++) {
						dGoal1 = new DistributionData();
						String[] s = content.get(j);
						if (s.length == header.length) {
							int ij;
							rcs = "";
							if (s.length > 37) {
								this.setFailMessage("��" + (j + 2) + "����������ȷ.");
								return RESULT_MESSAGE;
							}
							ij = 0;
							String org_city = s[ij++];
							String kunnrId = s[ij++];
							String kunnr_Name = s[ij++];
							String input_date = s[ij++];
							String firstuser = s[ij++];
							String seconduser = s[ij++];
							String thirduser = s[ij++];
							if ((null != kunnr_Name) && (null != input_date)
									&& (null != org_city)
									&& (null != firstuser)) {
								DistributionData dG = new DistributionData();
								dG.setKunnrName(kunnr_Name);
								dG.setInputDate(input_date);
								dG.setOrgCity(org_city);
								dG.setFirstUser(firstuser);
								int size = distributionDataService
										.getDistributionDataSize(dG);
								if (size != 0) {
									rcs = rcs + "��" + (j + 2) + "������     ���У�"
											+ org_city + " �����̱�ţ�" + kunnrId
											+ " ���о���" + firstuser + " �����ڣ� "
											+ input_date + "�Ѿ������,�����ظ��ύ.</br>";
								}
							}
							if (null != firstuser) {
								dGoal1.setFirstUser(firstuser);
							} else {
								rcs = rcs + "��" + (j + 2)
										+ "�г��о���Ϊ��,û��������.</br>";
							}
							if (null != seconduser) {
								dGoal1.setSecondUser(seconduser);
							} else {
								rcs = rcs + "��" + (j + 2)
										+ "��ʡ������Ϊ��,û��������.</br>";
							}
							dGoal1.setThirdUser(thirduser);

							if (null != kunnrId) {
								String[] ki = kunnrId.split("/");
								Kunnr kunnr = null;
								List<Kunnr> kunnr1 = new ArrayList<Kunnr>();
								for (int i = 0; i < ki.length; i++) {
									kunnr = new Kunnr();
									kunnr.setKunnr(StringUtils.leftPad(
											String.valueOf(ki[i].trim()), 8,
											'0'));
									kunnr.setPagination("false");
								}
								kunnr1 = kunnrService.kunnrSearch(kunnr);

								if (0 == kunnr1.size()) {
									rcs = rcs + "��" + (j + 2) + "������������:"
											+ kunnrId + "������.</br>";
								} else if (!kunnr_Name.equals(kunnr1.get(0)
										.getName1())) {
									rcs = rcs + "��" + (j + 2) + "����������������:"
											+ kunnr_Name + "����ȷ.</br>";
								} else {
									dGoal1.setKunnrId(StringUtils.leftPad(
											String.valueOf(kunnrId.trim()), 8,
											'0'));
									dGoal1.setKunnrName(kunnr_Name);

									if (null != org_city) {
										DistributionData org = distributionDataService
												.getOrgByOrgName(org_city);
										if (org == null) {
											rcs = rcs + "��" + (j + 2)
													+ "�г���  ��" + org_city
													+ " ����ȷ.</br>";
										} else if (org.getOrgId() != null) {
											if (!org.getOrgId().equals(
													kunnr1.get(0).getOrgId())) {
												rcs = rcs + "��" + (j + 2)
														+ "������������ ��" + kunnrId
														+ "�����  ��ƥ��.</br>";
											} else {
												dGoal1.setOrgId(org.getOrgId());
												dGoal1.setOrgCity(org_city);
											}
										} else {
											rcs = rcs + "��" + (j + 2)
													+ "�г���  ��" + org_city
													+ " ����ȷ.</br>";
										}
									} else {
										rcs = rcs + "��" + (j + 2)
												+ "�г���: Ϊ��.</br>";
									}
								}
							} else if (kunnr_Name != null) {
								if (org_city != null) {
									DistributionData org = distributionDataService
											.getOrgByOrgName(org_city);
									if (org == null) {
										rcs = rcs + "��" + (j + 2)
												+ "�г���: Ϊ����ȷ.</br>";
									} else {
										dGoal1.setOrgId(org.getOrgId());
										dGoal1.setOrgCity(org_city);

									}
								} else {
									rcs = rcs + "��" + (j + 2) + "�г���: Ϊ��.</br>";
								}
								dGoal1.setKunnrName(kunnr_Name);
							} else {
								rcs = rcs + "��" + (j + 2)
										+ "����������������: Ϊ��.</br>";
							}

							if (null != input_date) {
								String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]? ((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
								Pattern p = Pattern.compile(eL);
								Matcher ma = p.matcher(input_date);
								boolean flag = ma.matches();
								if (flag) {
									input_date = this.dateChange(input_date);
									dGoal1.setInputDate(input_date);
								} else {
									rcs = rcs
											+ "��"
											+ (j + 2)
											+ "�����ڸ�ʽ����,�밴��2014/01/01��2014-01-01��ʽ��д.</br>";
								}
							} else {
								rcs = rcs + "��" + (j + 2) + "������: Ϊ��.</br>";
							}

							/*
							 * if (null != resign_date) { String eL =
							 * "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]? ((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))"
							 * ; Pattern p = Pattern.compile(eL); Matcher ma =
							 * p.matcher(resign_date); boolean flag =
							 * ma.matches(); if (flag) {
							 * dGoal1.setResign_date(resign_date); } else { rcs
							 * = rcs + "��" + (j + 2) +
							 * "��ҵ����Ա��ְ���ڸ�ʽ����,�밴��2014/01/01��2014-01-01��ʽ��д.</br>"
							 * ; } }
							 */

							for (int i = 7; i < header.length; i++) {
								dGoal = new DistributionData();
								dGoal.setUserId(this.getUser().getUserId());
								dGoal.setOrgId(dGoal1.getOrgId());
								dGoal.setOrgCity(dGoal1.getOrgCity());
								dGoal.setKunnrId(dGoal1.getKunnrId());
								dGoal.setKunnrName(dGoal1.getKunnrName());
								dGoal.setFirstUser(dGoal1.getFirstUser());
								dGoal.setSecondUser(dGoal1.getSecondUser());
								dGoal.setThirdUser(dGoal1.getThirdUser());
								dGoal.setInputDate(dGoal1.getInputDate());
								dGoal.setInputName(this.getUser().getUserId());
								matter = m[i - 7];
								matterNum = m_num[i - 7];
								dGoal.setMatter(matter);
								dGoal.setMatterNum(matterNum);
								String box_num = s[i];
								if (null != box_num) {
									int k = 0;
									for (int m_digit = 0; m_digit < box_num
											.length(); m_digit++) {
										char c = box_num.charAt(m_digit);
										if ((c < '0' || c > '9')) {
											k = 1;
											rcs = rcs + "��" + (j + 2) + "��"
													+ header[i]
													+ ": Ϊ�����ֵ�ֵ</br>";
										}
									}
									if (k == 0)
										dGoal.setBoxNum(Integer
												.parseInt(box_num));
								} else {
									rcs = rcs + "��" + (j + 2) + "��" + header[i]
											+ ": Ϊ��,����������0</br>";
								}
								distributionDataLists.add(dGoal);
							}
						} else {
							rcs = rcs + "��" + (j + 2) + "���������ԣ�</br>";
						}
						rcs2 = rcs2 + rcs;
					}
					if (!"".equals(rcs2)) {
						result.append(rcs2.toString() + "</br>");
						this.setFailMessage(result.toString());
						return RESULT_MESSAGE;
					} else {
						if (distributionDataLists.size() != 0l
								&& result.toString().equals("")) {
							for (int q = 0; q < distributionDataLists.size(); q++) {

								try {
									rcs2 = "";
									result1 = distributionDataService
											.insertDistributionData(distributionDataLists
													.get(q));
									rcs2 += result1.getCode();
									result.append(rcs2.toString() + "</br>");
								} catch (Exception e) {
									logger.error(e);
									rcs2 += "��" + (q + 2)
											+ "�����ݱ������ݿ�ʧ��.����ϵϵͳ����Ա.</br>";
									result.append(rcs2.toString() + "</br>");
								}
							}
						} else {

							this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br></br>"
									+ rcs2.toString());
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("distributionDataLists",
							distributionDataLists);
					if (result1.getResult()) {
						this.setSuccessMessage("����ɹ�����������Ϊ:" + content.size()
								+ "��");
					} else if (!result1.getResult()) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br></br>"
								+ result.toString());
					}
					return RESULT_MESSAGE;
				}

			} else {// �ļ�������
				this.setFailMessage("�ļ�������");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return RESULT_MESSAGE;
	}

	private void List2Excel(List<DistributionData> unMiantList) {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// ȡ�������
			os = response.getOutputStream();
			// ��������
			response.reset();
			// �趨����ļ�ͷ
			String fileName = "���о����������ͳ���б�.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// �����������
			response.setContentType("application/msexcel");
			// ����excel�ļ�
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("��һҳ", 0);
			wsheet.setColumnView(0, 13);
			wsheet.setColumnView(1, 13);
			wsheet.setColumnView(2, 9);
			wsheet.setColumnView(3, 9);
			wsheet.setColumnView(4, 13);
			wsheet.setColumnView(5, 12);
			wsheet.setColumnView(6, 30);
			wsheet.setColumnView(7, 11);
			wsheet.setColumnView(8, 11);
			wsheet.setColumnView(9, 11);
			wsheet.setColumnView(10, 10);
			wsheet.setColumnView(11, 17);
			wsheet.setColumnView(12, 17);
			wsheet.setColumnView(13, 17);
			wsheet.setColumnView(14, 17);
			wsheet.setColumnView(15, 17);
			wsheet.setColumnView(16, 17);
			wsheet.setColumnView(17, 17);
			wsheet.setColumnView(18, 19);
			wsheet.setColumnView(19, 13);
			wsheet.setColumnView(20, 17);
			wsheet.setColumnView(21, 15);
			wsheet.setColumnView(22, 15);
			wsheet.setColumnView(23, 19);
			wsheet.setColumnView(24, 25);
			wsheet.setColumnView(25, 19);
			wsheet.setColumnView(26, 25);
			wsheet.setColumnView(27, 21);
			wsheet.setColumnView(28, 18);
			wsheet.setColumnView(29, 18);
			wsheet.setColumnView(30, 20);
			wsheet.setColumnView(31, 18);
			wsheet.setColumnView(32, 15);
			wsheet.setColumnView(33, 19);
			wsheet.setColumnView(34, 20);
			wsheet.setColumnView(35, 38);
			wsheet.setColumnView(36, 42);
			wsheet.setColumnView(37, 42);
			wsheet.setColumnView(38, 28);
			wsheet.setColumnView(39, 15);
			wsheet.setColumnView(40, 15);
			
			wsheet.mergeCells(0, 0, 0, 1);
			wsheet.mergeCells(1, 0, 1, 1);
			wsheet.mergeCells(2, 0, 2, 1);
			wsheet.mergeCells(3, 0, 3, 1);
			wsheet.mergeCells(4, 0, 4, 1);
			wsheet.mergeCells(5, 0, 5, 1);
			wsheet.mergeCells(6, 0, 6, 1);
			wsheet.mergeCells(7, 0, 7, 1);
			wsheet.mergeCells(8, 0, 8, 1);
			wsheet.mergeCells(9, 0, 9, 1);
			wsheet.mergeCells(10, 0, 10, 1);

			wsheet.mergeCells(35, 0, 35, 1);
			wsheet.mergeCells(36, 0, 36, 1);
			wsheet.mergeCells(37, 0, 37, 1);
			wsheet.mergeCells(38, 0, 38, 1);
			
			

			WritableFont wfcb = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat wcfFG = new WritableCellFormat(wfcb);
			wcfFG.setBackground(jxl.format.Colour.GRAY_25);
			wcfFG.setAlignment(jxl.format.Alignment.CENTRE);
			wcfFG.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfFG.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.MEDIUM);

			WritableCellFormat wcfFY = new WritableCellFormat(wfcb);
			wcfFY.setBackground(jxl.format.Colour.YELLOW);
			wcfFY.setAlignment(jxl.format.Alignment.CENTRE);
			wcfFY.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfFY.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.MEDIUM);

			WritableCellFormat wcfFT = new WritableCellFormat(wfcb);
			wcfFT.setBackground(jxl.format.Colour.TAN);
			wcfFT.setAlignment(jxl.format.Alignment.CENTRE);
			wcfFT.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfFT.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.MEDIUM);

			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.NO_BOLD, false,
					jxl.format.UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat wcfF = new WritableCellFormat(wfc);
			wcfF.setAlignment(jxl.format.Alignment.CENTRE);
			wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfF.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.HAIR);
			
			wsheet.addCell(new Label(0, 0, "������", wcfFG));
			wsheet.addCell(new Label(1, 0, "��������", wcfFG));
			wsheet.addCell(new Label(2, 0, "����", wcfFG));
			wsheet.addCell(new Label(3, 0, "ʡ��", wcfFG));
			wsheet.addCell(new Label(4, 0, "����", wcfFG));
			wsheet.addCell(new Label(5, 0, "�����̱��", wcfFG));
			wsheet.addCell(new Label(6, 0, "���������� ", wcfFG));
			wsheet.addCell(new Label(7, 0, "��������", wcfFG));
			wsheet.addCell(new Label(8, 0, "���о���", wcfFG));
			wsheet.addCell(new Label(9, 0, "ʡ������", wcfFG));
			wsheet.addCell(new Label(10, 0, "��������", wcfFG));
			wsheet.addCell(new Label(11, 0, "A1Ҭ������(ԭζ)", wcfFT));
			wsheet.addCell(new Label(11, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(12, 0, "A1Ҭ������(����)", wcfFT));
			wsheet.addCell(new Label(12, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(13, 0, "A1Ҭ������(����)", wcfFT));
			wsheet.addCell(new Label(13, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(14, 0, "A1Ҭ������(��ݮ)", wcfFT));
			wsheet.addCell(new Label(14, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(15, 0, "A1Ҭ������(�ɿ���)", wcfFT));
			wsheet.addCell(new Label(15, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(16, 0, "A1Ҭ������(����)", wcfFT));
			wsheet.addCell(new Label(16, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(17, 0, "A1Ҭ������(�̲�)", wcfFT));
			wsheet.addCell(new Label(17, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(18, 0, "A3Ҭ������װ", wcfFT));
			wsheet.addCell(new Label(18, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(19, 0, "B1�춹����", wcfFT));
			wsheet.addCell(new Label(19, 1, "64g*30", wcfFT));
			wsheet.addCell(new Label(20, 0, "C1��Բ���浥��", wcfFT));
			wsheet.addCell(new Label(20, 1, "65g*30", wcfFT));
			wsheet.addCell(new Label(21, 0, "A4Ҭ�����װ", wcfFT));
			wsheet.addCell(new Label(21, 1, "80g*12*8", wcfFT));
			wsheet.addCell(new Label(22, 0, "A5Ҭ�����װ", wcfFT));
			wsheet.addCell(new Label(22, 1, "80g*8*10", wcfFT));
			wsheet.addCell(new Label(23, 0, "A6Ҭ����ͥ����װ", wcfFT));
			wsheet.addCell(new Label(23, 1, "80g*16*6", wcfFT));
			wsheet.addCell(new Label(24, 0, "C6��Բ�����ͥ����װ", wcfFT));
			wsheet.addCell(new Label(24, 1, "65g*16*6", wcfFT));
			wsheet.addCell(new Label(25, 0, "A7Ҭ����ͥ����װ", wcfFT));
			wsheet.addCell(new Label(25, 1, "80g*15*6", wcfFT));
			wsheet.addCell(new Label(26, 0, "C7��Բ�����ͥ����װ", wcfFT));
			wsheet.addCell(new Label(26, 1, "65g*15*6", wcfFT));
			wsheet.addCell(new Label(27, 0, "A2Ҭ������װ(�ɿ���)", wcfFT));
			wsheet.addCell(new Label(27, 1, "80g*6*6", wcfFT));
			wsheet.addCell(new Label(28, 0, "A2Ҭ������װ(����)", wcfFT));
			wsheet.addCell(new Label(28, 1, "80g*6*6", wcfFT));
			wsheet.addCell(new Label(29, 0, "A2Ҭ������װ(ԭζ)", wcfFT));
			wsheet.addCell(new Label(29, 1, "80g*6*6", wcfFT));
			wsheet.addCell(new Label(30, 0, "A8Ҭ������װ(ԭζ)", wcfFT));
			wsheet.addCell(new Label(30, 1, "80g*3*10", wcfFT));
			wsheet.addCell(new Label(31, 0, "A8Ҭ������װ(����)", wcfFT));
			wsheet.addCell(new Label(31, 1, "80g*3*10", wcfFT));
			wsheet.addCell(new Label(32, 0, "B8�춹����װ", wcfFT));
			wsheet.addCell(new Label(32, 1, "80g*3*10", wcfFT));
			wsheet.addCell(new Label(33, 0, "C8��Բ��������װ", wcfFT));
			wsheet.addCell(new Label(33, 1, "65g*3*10", wcfFT));
			wsheet.addCell(new Label(34, 0, "H7�����̲赥ƿ", wcfFT));
			wsheet.addCell(new Label(34, 1, "400ml*15", wcfFT));
			wsheet.addCell(new Label(35, 0, "D9��ֵ���װ(ȫ�Ҹ���Ͼ������ר��1*9)", wcfFT));
			wsheet.addCell(new Label(36, 0, "D9��ֵ���װ(�μ��˺춹��Բ�������ר��1*12)", wcfFT));
			wsheet.addCell(new Label(37, 0, "D9��ֵ���װ(����ζ�ͷ�Ҭ��ϵ�е���ר��1*20)", wcfFT));
			wsheet.addCell(new Label(38, 0, "D9��ֵ���װ(��ֵ���װ1*30)", wcfFT));
			wsheet.addCell(new Label(39, 0, "D10�������װ", wcfFT));
			wsheet.addCell(new Label(39, 1, "15*6", wcfFT));
			wsheet.addCell(new Label(40, 0, "E1�������װ", wcfFT));
			wsheet.addCell(new Label(40, 1, "12*6", wcfFT));
			

			for (int i = 1; i <= unMiantList.size(); i++) {
				wsheet.addCell(new Label(0, i + 1, unMiantList.get(i - 1)
						.getInputName(), wcfF));
				wsheet.addCell(new Label(1, i + 1, unMiantList.get(i - 1)
						.getCreateDate(), wcfF));
				wsheet.addCell(new Label(2, i + 1, unMiantList.get(i - 1)
						.getOrgRegion(), wcfF));
				wsheet.addCell(new Label(3, i + 1, unMiantList.get(i - 1)
						.getOrgProvince(), wcfF));
				wsheet.addCell(new Label(4, i + 1, unMiantList.get(i - 1)
						.getOrgCity(), wcfF));
				wsheet.addCell(new Label(5, i + 1, unMiantList.get(i - 1)
						.getKunnrId(), wcfF));
				wsheet.addCell(new Label(6, i + 1, unMiantList.get(i - 1)
						.getKunnrName(), wcfF));
				wsheet.addCell(new Label(7, i + 1, unMiantList.get(i - 1)
						.getInputDate(), wcfF));
				wsheet.addCell(new Label(8, i + 1, unMiantList.get(i - 1)
						.getFirstUser(), wcfF));
				wsheet.addCell(new Label(9, i + 1, unMiantList.get(i - 1)
						.getSecondUser(), wcfF));
				wsheet.addCell(new Label(10, i + 1, unMiantList.get(i - 1)
						.getThirdUser(), wcfF));
				wsheet.addCell(new Number(11, i + 1, unMiantList.get(i - 1)
						.getaOneYW(), wcfF));
				wsheet.addCell(new Number(12, i + 1, unMiantList.get(i - 1)
						.getaOneXY(), wcfF));
				wsheet.addCell(new Number(13, i + 1, unMiantList.get(i - 1)
						.getaOneMX(), wcfF));
				wsheet.addCell(new Number(14, i + 1, unMiantList.get(i - 1)
						.getaOneCM(), wcfF));
				wsheet.addCell(new Number(15, i + 1, unMiantList.get(i - 1)
						.getaOneQKL(), wcfF));
				wsheet.addCell(new Number(16, i + 1, unMiantList.get(i - 1)
						.getaOneKF(), wcfF));
				wsheet.addCell(new Number(17, i + 1, unMiantList.get(i - 1)
						.getaOneLC(), wcfF));
				wsheet.addCell(new Number(18, i + 1, unMiantList.get(i - 1)
						.getaThree(), wcfF));
				wsheet.addCell(new Number(19, i + 1, unMiantList.get(i - 1)
						.getbOne(), wcfF));
				wsheet.addCell(new Number(20, i + 1, unMiantList.get(i - 1)
						.getcOne(), wcfF));
				wsheet.addCell(new Number(21, i + 1, unMiantList.get(i - 1)
						.getaFour(), wcfF));
				wsheet.addCell(new Number(22, i + 1, unMiantList.get(i - 1)
						.getaFive(), wcfF));
				wsheet.addCell(new Number(23, i + 1, unMiantList.get(i - 1)
						.getaSix(), wcfF));
				wsheet.addCell(new Number(24, i + 1, unMiantList.get(i - 1)
						.getcSix(), wcfF));
				wsheet.addCell(new Number(25, i + 1, unMiantList.get(i - 1)
						.getaSeven(), wcfF));
				wsheet.addCell(new Number(26, i + 1, unMiantList.get(i - 1)
						.getcSeven(), wcfF));
				wsheet.addCell(new Number(27, i + 1, unMiantList.get(i - 1)
						.getaTwoQKL(), wcfF));
				wsheet.addCell(new Number(28, i + 1, unMiantList.get(i - 1)
						.getaTwoXY(), wcfF));
				wsheet.addCell(new Number(29, i + 1, unMiantList.get(i - 1)
						.getaTwoYW(), wcfF));
				wsheet.addCell(new Number(30, i + 1, unMiantList.get(i - 1)
						.getaEightYW(), wcfF));
				wsheet.addCell(new Number(31, i + 1, unMiantList.get(i - 1)
						.getaEightXY(), wcfF));
				wsheet.addCell(new Number(32, i + 1, unMiantList.get(i - 1)
						.getbEight(), wcfF));
				wsheet.addCell(new Number(33, i + 1, unMiantList.get(i - 1)
						.getcEight(), wcfF));
				wsheet.addCell(new Number(34, i + 1, unMiantList.get(i - 1)
						.gethSeven(), wcfF));
				wsheet.addCell(new Number(35, i + 1, unMiantList.get(i - 1)
						.getdNine9(), wcfF));
				wsheet.addCell(new Number(36, i + 1, unMiantList.get(i - 1)
						.getdNine12(), wcfF));
				wsheet.addCell(new Number(37, i + 1, unMiantList.get(i - 1)
						.getdNine20(), wcfF));
				wsheet.addCell(new Number(38, i + 1, unMiantList.get(i - 1)
						.getdNine30(), wcfF));
				wsheet.addCell(new Number(39, i + 1, unMiantList.get(i - 1)
						.getdTen(), wcfF));
				wsheet.addCell(new Number(40, i + 1, unMiantList.get(i - 1)
						.geteOne(), wcfF));
				
			}
			wbook.write();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error(e);
				}
				os = null;
			}
		}
	}

	public String exportDistributionData() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		distributionDataLists = new ArrayList<DistributionData>();
		DistributionData dbData = new DistributionData();
		dbData.setPagination("false");// ��ʹ�÷�ҳ ȫ�������
		HttpServletResponse response = getServletResponse();

		ServletActionContext.getRequest().getSession()
				.setAttribute("DownLoad", "Ing");
		try {
			distributionDataLists = new ArrayList<DistributionData>();
			if (StringUtils.isNotEmpty(kunnrId)) {
				dbData.setKunnrId(kunnrId);
			}
			if (StringUtils.isNotEmpty(kunnrName)) {
				dbData.setKunnrName(kunnrName);
			}
			if (StringUtils.isNotEmpty(startDate)) {
				dbData.setStartDate(startDate);
			}
			if (StringUtils.isNotEmpty(endDate)) {
				dbData.setEndDate(endDate);
			}

			if (StringUtils.isNotEmpty(trFlag)) {
				dbData.setTrFlag(trFlag);
			}
			if (StringUtils.isNotEmpty(orgId)) {
				String[] str = orgId.split(", ");
				if (str.length > 1) {
					dbData.setOrgId(str[1]);
				} else {
					dbData.setOrgId(orgId);
				}
			}
			dbData.setStart(0);
			dbData.setEnd(100000000);
			distributionDataLists = distributionDataService
					.getDistributionDataList(dbData);

			if (distributionDataLists.size() == 0) {
				this.setFailMessage("Excel���ݵ�������,�벻Ҫ��������Ϊ�յ��б�");
			}
			List2Excel(distributionDataLists);
			ServletActionContext.getRequest().getSession()
					.setAttribute("DownLoad", "Over");

		} catch (Exception e) {
			logger.error(e);
		}
		return RESULT_MESSAGE;

	}

	public String orgTreePage() {
		userId = this.getUser().getUserId();
		Borg borg = orgServiceHessian.getOrgByUserId(userId);
		if ("B".equals(borg.getOrgCity())) {
			return "orgAllTree";
		}
		return "orgtree";
	}

	public String dateChange(String date) {
		String dateRen = null;
		String year = "";
		String month = "";
		String day = "";
		if (date.indexOf("-") != -1) {
			String[] date_arr1 = date.split("\\-");
			year = date_arr1[0];
			if (date_arr1[1].length() == 1)
				month = "0" + date_arr1[1];
			else
				month = date_arr1[1];
			if (date_arr1[2].length() == 1)
				day = "0" + date_arr1[2];
			else
				day = date_arr1[2];
			dateRen = year + "-" + month + "-" + day;

		} else if (date.indexOf("/") != -1) {
			String[] date_arr2 = date.split("\\/");
			year = date_arr2[0];
			if (date_arr2[1].length() == 1)
				month = "0" + date_arr2[1];
			else
				month = date_arr2[1];
			if (date_arr2[2].length() == 1)
				day = "0" + date_arr2[2];
			else
				day = date_arr2[2];
			dateRen = year + "-" + month + "-" + day;
		}
		return dateRen;
	}

	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		DistributionDataAction.logger = logger;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgRegion() {
		return orgRegion;
	}

	public void setOrgRegion(String orgRegion) {
		this.orgRegion = orgRegion;
	}

	public String getOrgProvince() {
		return orgProvince;
	}

	public void setOrgProvince(String orgProvince) {
		this.orgProvince = orgProvince;
	}

	public String getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	public String getFirstUser() {
		return firstUser;
	}

	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDire_super_name() {
		return dire_super_name;
	}

	public void setDire_super_name(String dire_super_name) {
		this.dire_super_name = dire_super_name;
	}

	public String getDire_super_position() {
		return dire_super_position;
	}

	public void setDire_super_position(String dire_super_position) {
		this.dire_super_position = dire_super_position;
	}

	public String getKunnrId() {
		return kunnrId;
	}

	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
	}

	public String getKunnrName() {
		return kunnrName;
	}

	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getBoxNum() {
		return boxNum;
	}

	public void setBoxNum(int boxNum) {
		this.boxNum = boxNum;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getTrFlag() {
		return trFlag;
	}

	public void setTrFlag(String trFlag) {
		this.trFlag = trFlag;
	}

	public int getaOneYW() {
		return aOneYW;
	}

	public void setaOneYW(int aOneYW) {
		this.aOneYW = aOneYW;
	}

	public int getaOneXY() {
		return aOneXY;
	}

	public void setaOneXY(int aOneXY) {
		this.aOneXY = aOneXY;
	}

	public int getaOneMX() {
		return aOneMX;
	}

	public void setaOneMX(int aOneMX) {
		this.aOneMX = aOneMX;
	}

	public int getaOneCM() {
		return aOneCM;
	}

	public void setaOneCM(int aOneCM) {
		this.aOneCM = aOneCM;
	}

	public int getaOneQKL() {
		return aOneQKL;
	}

	public void setaOneQKL(int aOneQKL) {
		this.aOneQKL = aOneQKL;
	}

	public int getaOneKF() {
		return aOneKF;
	}

	public void setaOneKF(int aOneKF) {
		this.aOneKF = aOneKF;
	}

	public int getaOneLC() {
		return aOneLC;
	}

	public void setaOneLC(int aOneLC) {
		this.aOneLC = aOneLC;
	}

	public int getaTwoQKL() {
		return aTwoQKL;
	}

	public void setaTwoQKL(int aTwoQKL) {
		this.aTwoQKL = aTwoQKL;
	}

	public int getaTwoXY() {
		return aTwoXY;
	}

	public void setaTwoXY(int aTwoXY) {
		this.aTwoXY = aTwoXY;
	}

	public int getaTwoYW() {
		return aTwoYW;
	}

	public void setaTwoYW(int aTwoYW) {
		this.aTwoYW = aTwoYW;
	}

	public int getaThree() {
		return aThree;
	}

	public void setaThree(int aThree) {
		this.aThree = aThree;
	}

	public int getaFour() {
		return aFour;
	}

	public void setaFour(int aFour) {
		this.aFour = aFour;
	}

	public int getaFive() {
		return aFive;
	}

	public void setaFive(int aFive) {
		this.aFive = aFive;
	}

	public int getaSix() {
		return aSix;
	}

	public void setaSix(int aSix) {
		this.aSix = aSix;
	}

	public int getaSeven() {
		return aSeven;
	}

	public void setaSeven(int aSeven) {
		this.aSeven = aSeven;
	}

	
	public int getaEightYW() {
		return aEightYW;
	}

	public void setaEightYW(int aEightYW) {
		this.aEightYW = aEightYW;
	}

	public int getaEightXY() {
		return aEightXY;
	}

	public void setaEightXY(int aEightXY) {
		this.aEightXY = aEightXY;
	}

	public int getbOne() {
		return bOne;
	}

	public void setbOne(int bOne) {
		this.bOne = bOne;
	}

	public int getbEight() {
		return bEight;
	}

	public void setbEight(int bEight) {
		this.bEight = bEight;
	}

	public int getcOne() {
		return cOne;
	}

	public void setcOne(int cOne) {
		this.cOne = cOne;
	}

	public int getcSix() {
		return cSix;
	}

	public void setcSix(int cSix) {
		this.cSix = cSix;
	}

	public int getcSeven() {
		return cSeven;
	}

	public void setcSeven(int cSeven) {
		this.cSeven = cSeven;
	}

	public int getcEight() {
		return cEight;
	}

	public void setcEight(int cEight) {
		this.cEight = cEight;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public IDistributionDataService getDistributionDataService() {
		return distributionDataService;
	}

	public void setDistributionDataService(
			IDistributionDataService distributionDataService) {
		this.distributionDataService = distributionDataService;
	}

	public void setDistributionDataLists(
			List<DistributionData> distributionDataLists) {
		this.distributionDataLists = distributionDataLists;
	}

	public List<DistributionData> getDistributionDataLists() {
		return distributionDataLists;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public long getDgId() {
		return dgId;
	}

	public void setDgId(long dgId) {
		this.dgId = dgId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getResign_date() {
		return resign_date;
	}

	public void setResign_date(String resign_date) {
		this.resign_date = resign_date;
	}

	public DistributionData getdGoal() {
		return dGoal;
	}

	public void setdGoal(DistributionData dGoal) {
		this.dGoal = dGoal;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	public int getbSix() {
		return bSix;
	}

	public void setbSix(int bSix) {
		this.bSix = bSix;
	}

	public int getdNine9() {
		return dNine9;
	}

	public void setdNine9(int dNine9) {
		this.dNine9 = dNine9;
	}

	public int getdNine12() {
		return dNine12;
	}

	public void setdNine12(int dNine12) {
		this.dNine12 = dNine12;
	}

	public int getdNine20() {
		return dNine20;
	}

	public void setdNine20(int dNine20) {
		this.dNine20 = dNine20;
	}

	public int getdNine30() {
		return dNine30;
	}

	public void setdNine30(int dNine30) {
		this.dNine30 = dNine30;
	}

	public int gethSeven() {
		return hSeven;
	}

	public void sethSeven(int hSeven) {
		this.hSeven = hSeven;
	}

	public int getdTen() {
		return dTen;
	}

	public void setdTen(int dTen) {
		this.dTen = dTen;
	}

	public int geteOne() {
		return eOne;
	}

	public void seteOne(int eOne) {
		this.eOne = eOne;
	}

}
