package com.kintiger.platform.distributionDataAppendRepMon.action;

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

import com.kintiger.platform.distributionDataAppendRepMon.pojo.DistributionDataAppendRepMon;
import com.kintiger.platform.distributionDataAppendRepMon.service.IDistributionDataAppendRepMonService;

import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;

public class DistributionDataAppendRepMonAction extends BaseAction {

	private static final long serialVersionUID = -532423459213585547L;
	private static Log logger = LogFactory
			.getLog(DistributionDataAppendRepMonAction.class);
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
	private String state;

	private String startDate;

	private String endDate;
	private int boxNum;
	private String inputDate;
	private String checkName;
	private String trFlag;
	private double aOne;
	private double aTwo;
	private double aThree;
	private double aFour;
	private double aFive;
	private double aSix;
	private double aSeven;
	private double aEight;
	private double bOne;
	private double bSix;
	private double bEight;
	private double cOne;
	private double cSix;
	private double cSeven;
	private double cEight;
	private double dNine;
	private double dTen;
	private double eOne;
	private double hSeven;
	private String userId;
	private long dgId;
	private String channel;
	private String store_category;
	private String resign_date;

	private String ids;
	private int size;
	private IOrgService orgServiceHessian;
	private IDistributionDataAppendRepMonService distributionDataAppendRepMonService;
	private List<DistributionDataAppendRepMon> distributionDataAppendRepMonLists;
	private String uploadFileFileName;
	private File uploadFile;
	private String xmlFilePath;
	private DistributionDataAppendRepMon dGoal;
	private IKunnrService kunnrService;

	public String distributionDataAppendRepMonSearch() {
		userId = this.getUser().getUserId();
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		return "distributionDataAppendRepMonSearch";
	}

	/**
	 * 查询目标 add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "distributionDataAppendRepMonLists", total = "size", include = {
			"orgRegion", "orgProvince", "orgCity", "firstUser", "position",
			"dire_super_name", "dire_super_position", "kunnrName", "kunnrId",
			"inputDate", "aOne","aTwo", "dgId", "aThree", "aFour", "aFive", "aSix",
			"aSeven", "aEight", "bOne","bSix", "bEight", "cOne", "cSix", "cSeven",
			"cEight", "dNine","dTen","eOne", "hSeven", "inputName", "checkName", "trFlag",
			"channel", "store_category", "resign_date","createDate","startDate","endDate",
			"itemZGTotal","itemGoalTotal","dcl", "aOneZ", "aTwoZ", "aThreeZ", "aFourZ",
			"aFiveZ", "aSixZ", "aSevenZ", "aEightZ", "bOneZ","bSixZ", "bEightZ", "cOneZ",
			"cSixZ", "cSevenZ", "cEightZ", "dNineZ", "dTenZ", "eOneZ", "hSevenZ" })
	public String getDistributionDataAppendRepMonList() {
		distributionDataAppendRepMonLists = new ArrayList<DistributionDataAppendRepMon>();
		DistributionDataAppendRepMon dGoal = new DistributionDataAppendRepMon();
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
		size = distributionDataAppendRepMonService
				.getDistributionDataAppendRepMonCount(dGoal);
		if (size != 0) {
			distributionDataAppendRepMonLists = distributionDataAppendRepMonService
					.getDistributionDataAppendRepMonList(dGoal);
		}
		return JSON;
	}

	

	private void List2Excel(List<DistributionDataAppendRepMon> dbList) {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "主管业代分销数据目标量数据及达成率报表.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("第一页", 0);
			wsheet.setRowView(0, 300);
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 11);
			wsheet.setColumnView(2, 9);
			wsheet.setColumnView(3, 9);
			wsheet.setColumnView(4, 10);
			wsheet.setColumnView(5, 12);
			wsheet.setColumnView(6, 35);
			wsheet.setColumnView(7, 9);
			wsheet.setColumnView(8, 16);
			wsheet.setColumnView(9, 14);
			wsheet.setColumnView(10, 14);
			wsheet.setColumnView(11, 10);
			wsheet.setColumnView(12, 16);
			wsheet.setColumnView(13, 10);
			wsheet.setColumnView(14, 12);
			wsheet.setColumnView(15, 15);
			wsheet.setColumnView(16, 12);
			wsheet.setColumnView(17, 17);
			wsheet.setColumnView(18, 14);
			wsheet.setColumnView(19, 14);
			wsheet.setColumnView(20, 19);
			wsheet.setColumnView(21, 25);
			wsheet.setColumnView(22, 19);
			wsheet.setColumnView(23, 25);
			wsheet.setColumnView(24, 14);
			wsheet.setColumnView(25, 14);
			wsheet.setColumnView(26, 14);
			wsheet.setColumnView(27, 19);
			wsheet.setColumnView(28, 16);
			wsheet.setColumnView(29, 14);
			wsheet.setColumnView(30, 16);
			wsheet.setColumnView(31, 16);
			wsheet.setColumnView(32, 19);
			
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
			wsheet.mergeCells(11, 0, 11, 1);
			wsheet.mergeCells(12, 0, 12, 1);
			wsheet.mergeCells(13, 0, 13, 1);

			wsheet.mergeCells(29, 0, 29, 1);
			
			wsheet.mergeCells(32, 0, 32, 1);
			
			
			
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

			wsheet.addCell(new Label(0, 0, "创建人", wcfFG));
			wsheet.addCell(new Label(1, 0, "创建日期", wcfFG));
			wsheet.addCell(new Label(2, 0, "大区", wcfFG));
			wsheet.addCell(new Label(3, 0, "省份", wcfFG));
			wsheet.addCell(new Label(4, 0, "城市", wcfFG));
			wsheet.addCell(new Label(5, 0, "经销商编号", wcfFG));
			wsheet.addCell(new Label(6, 0, "经销商名称 ", wcfFG));
			wsheet.addCell(new Label(7, 0, "分销日期", wcfFG));
			wsheet.addCell(new Label(8, 0, "业务姓名", wcfFG));
			wsheet.addCell(new Label(9, 0, "岗位(主管/业代)", wcfFG));
			wsheet.addCell(new Label(10, 0, "直接上级姓名", wcfFG));
			wsheet.addCell(new Label(11, 0, "直接上级岗位", wcfFG));
			wsheet.addCell(new Label(12, 0, "渠道类别", wcfFG));
			wsheet.addCell(new Label(13, 0, "负责的门店类型", wcfFG));
			
			wsheet.addCell(new Label(14, 0, "A1椰果单杯", wcfFT));
			wsheet.addCell(new Label(14, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(15, 0, "A3椰果经典装", wcfFT));
			wsheet.addCell(new Label(15, 1, "80g*30", wcfFT));
			wsheet.addCell(new Label(16, 0, "B1红豆单杯", wcfFT));
			wsheet.addCell(new Label(16, 1, "64g*30", wcfFT));
			wsheet.addCell(new Label(17, 0, "C1桂圆红枣单杯", wcfFT));
			wsheet.addCell(new Label(17, 1, "65g*30", wcfFT));
			wsheet.addCell(new Label(18, 0, "A4椰果礼盒装", wcfFT));
			wsheet.addCell(new Label(18, 1, "80g*12*8", wcfFT));
			wsheet.addCell(new Label(19, 0, "A5椰果礼盒装", wcfFT));
			wsheet.addCell(new Label(19, 1, "80g*8*10", wcfFT));
			wsheet.addCell(new Label(20, 0, "A6椰果家庭分享装", wcfFT));
			wsheet.addCell(new Label(20, 1, "80g*16*6", wcfFT));
			wsheet.addCell(new Label(21, 0, "C6桂圆红枣家庭分享装", wcfFT));
			wsheet.addCell(new Label(21, 1, "65g*16*6", wcfFT));
			wsheet.addCell(new Label(22, 0, "A7椰果家庭分享装", wcfFT));
			wsheet.addCell(new Label(22, 1, "80g*15*6", wcfFT));
			wsheet.addCell(new Label(23, 0, "C7桂圆红枣家庭分享装", wcfFT));
			wsheet.addCell(new Label(23, 1, "65g*15*6", wcfFT));
			wsheet.addCell(new Label(24, 0, "A2椰果联杯装", wcfFT));
			wsheet.addCell(new Label(24, 1, "80g*6*6", wcfFT));
			wsheet.addCell(new Label(25, 0, "A8椰果联杯装", wcfFT));
			wsheet.addCell(new Label(25, 1, "80g*3*10", wcfFT));
			wsheet.addCell(new Label(26, 0, "B8红豆联杯装", wcfFT));
			wsheet.addCell(new Label(26, 1, "80g*3*10", wcfFT));
			wsheet.addCell(new Label(27, 0, "C8桂圆红枣联杯装", wcfFT));
			wsheet.addCell(new Label(27, 1, "65g*3*10", wcfFT));
			wsheet.addCell(new Label(28, 0, "H7功夫奶茶单瓶", wcfFT));
			wsheet.addCell(new Label(28, 1, "400ml*15", wcfFT));
			wsheet.addCell(new Label(29, 0, "D9超值组合装", wcfFT));
			wsheet.addCell(new Label(30, 0, "D10豪华礼盒装", wcfFT));
			wsheet.addCell(new Label(30, 1, "15*6", wcfFT));
			wsheet.addCell(new Label(31, 0, "E1豪华礼盒装", wcfFT));
			wsheet.addCell(new Label(31, 1, "12*6", wcfFT));
			wsheet.addCell(new Label(32, 0, "业务人员离职日期", wcfFG));
			
			for (int i = 1; i <= dbList.size(); i++) {
				
				wsheet.addCell(new Label(0, i+1, dbList.get(i - 1)
						.getInputName(), wcfF));
				wsheet.addCell(new Label(1, i+1, dbList.get(i - 1)
						.getCreateDate(), wcfF));
				wsheet.addCell(new Label(2, i+1,
						dbList.get(i - 1).getOrgRegion(), wcfF));
				wsheet.addCell(new Label(3, i+1, dbList.get(i - 1)
						.getOrgProvince(), wcfF));
				wsheet.addCell(new Label(4, i+1, dbList.get(i - 1).getOrgCity(),
						wcfF));
				wsheet.addCell(new Label(5, i+1, dbList.get(i - 1).getKunnrId(),
						wcfF));
				wsheet.addCell(new Label(6, i+1,dbList.get(i - 1).getKunnrName(), 
						wcfF));
				wsheet.addCell(new Label(7, i+1, dbList.get(i - 1)
						.getInputDate(), wcfF));
				wsheet.addCell(new Label(8, i+1,
						dbList.get(i - 1).getFirstUser(), wcfF));
				wsheet.addCell(new Label(9, i+1, dbList.get(i - 1).getPosition(),
						wcfF));
				wsheet.addCell(new Label(10, i+1, dbList.get(i - 1)
						.getDire_super_name(), wcfF));
				wsheet.addCell(new Label(11, i+1, dbList.get(i - 1)
						.getDire_super_position(), wcfF));
				wsheet.addCell(new Label(12, i+1, dbList.get(i - 1).getChannel(),
						wcfF));
				wsheet.addCell(new Label(13, i+1, dbList.get(i - 1)
						.getStore_category(), wcfF));
				
				wsheet.addCell(new Number(14, i+1, Double.valueOf(dbList.get(
						i - 1).getaOne()), wcfF));
				wsheet.addCell(new Number(15, i+1, Double.valueOf(dbList.get(
						i - 1).getaThree()), wcfF));
				wsheet.addCell(new Number(16, i+1, Double.valueOf(dbList.get(
						i - 1).getbOne()), wcfF));
				wsheet.addCell(new Number(17, i+1, Double.valueOf(dbList.get(
						i - 1).getcOne()), wcfF));
				wsheet.addCell(new Number(18, i+1, Double.valueOf(dbList.get(
						i - 1).getaFour()), wcfF));
				wsheet.addCell(new Number(19, i+1, Double.valueOf(dbList.get(
						i - 1).getaFive()), wcfF));
				wsheet.addCell(new Number(20, i+1, Double.valueOf(dbList.get(
						i - 1).getaSix()), wcfF));
				wsheet.addCell(new Number(21, i+1, Double.valueOf(dbList.get(
						i - 1).getcSix()), wcfF));
				wsheet.addCell(new Number(22, i+1, Double.valueOf(dbList.get(
						i - 1).getaSeven()), wcfF));
				wsheet.addCell(new Number(23, i+1, Double.valueOf(dbList.get(
						i - 1).getcSeven()), wcfF));
				wsheet.addCell(new Number(24, i+1, Double.valueOf(dbList.get(
						i - 1).getaTwo()), wcfF));
				wsheet.addCell(new Number(25, i+1, Double.valueOf(dbList.get(
						i - 1).getaEight()), wcfF));
				wsheet.addCell(new Number(26, i+1, Double.valueOf(dbList.get(
						i - 1).getbEight()), wcfF));
				wsheet.addCell(new Number(27, i+1, Double.valueOf(dbList.get(
						i - 1).getcEight()), wcfF));
				wsheet.addCell(new Number(28, i+1, Double.valueOf(dbList.get(
						i - 1).gethSeven()), wcfF));
				wsheet.addCell(new Number(29, i+1, Double.valueOf(dbList.get(
						i - 1).getdNine()), wcfF));
				wsheet.addCell(new Number(30, i+1, Double.valueOf(dbList.get(
						i - 1).getdTen()), wcfF));
				wsheet.addCell(new Number(31, i+1, Double.valueOf(dbList.get(
						i - 1).geteOne()), wcfF));
				wsheet.addCell(new Label(32, i+1, dbList.get(i - 1)
						.getResign_date(), wcfF));
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

	public String exportDistributionDataAppendRepMon() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		distributionDataAppendRepMonLists = new ArrayList<DistributionDataAppendRepMon>();
		DistributionDataAppendRepMon dbDataAppendRep = new DistributionDataAppendRepMon();
		dbDataAppendRep.setPagination("false");// 不使用分页 全部查出来
		HttpServletResponse response = getServletResponse();

		ServletActionContext.getRequest().getSession()
				.setAttribute("DownLoad", "Ing");
		try {
			distributionDataAppendRepMonLists = new ArrayList<DistributionDataAppendRepMon>();
			if (StringUtils.isNotEmpty(kunnrId)) {
				dbDataAppendRep.setKunnrId(kunnrId);
			}
			if (StringUtils.isNotEmpty(kunnrName)) {
				dbDataAppendRep.setKunnrName(kunnrName);
			}
			if (StringUtils.isNotEmpty(startDate)) {
				dbDataAppendRep.setStartDate(startDate);
			}
			if (StringUtils.isNotEmpty(endDate)) {
				dbDataAppendRep.setEndDate(endDate);
			}

			if (StringUtils.isNotEmpty(trFlag)) {
				dbDataAppendRep.setTrFlag(trFlag);
			}
			if (StringUtils.isNotEmpty(orgId)) {
				String[] str = orgId.split(", ");
				if (str.length > 1) {
					dbDataAppendRep.setOrgId(str[1]);
				} else {
					dbDataAppendRep.setOrgId(orgId);
				}
			}
			dbDataAppendRep.setStart(0);
			dbDataAppendRep.setEnd(100000000);
			distributionDataAppendRepMonLists = distributionDataAppendRepMonService
					.getDistributionDataAppendRepMonList(dbDataAppendRep);

			if (distributionDataAppendRepMonLists.size() == 0) {
				this.setFailMessage("Excel数据导出出错,请不要导出数据为空的列表");
			}
			List2Excel(distributionDataAppendRepMonLists);
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
	
	public String dateChange(String date){
		String dateRen = null;
		String year = "";
		String month = "";
		String day = "";
		if(date.indexOf("-")!=-1){
			String [] date_arr1 = date.split("\\-");
			year = date_arr1[0];
			if(date_arr1[1].length()==1)
				month = "0"+date_arr1[1];
			else
				month = date_arr1[1];
			if(date_arr1[2].length()==1)
				day = "0"+date_arr1[2];
			else
				day = date_arr1[2];
			dateRen = year + "-" + month + "-" + day ;
			
		}else if(date.indexOf("/")!=-1){
			String [] date_arr2 = date.split("\\/");
			year = date_arr2[0];
			if(date_arr2[1].length()==1)
				month = "0"+date_arr2[1];
			else
				month = date_arr2[1];
			if(date_arr2[2].length()==1)
				day = "0"+date_arr2[2];
			else
				day = date_arr2[2];
			dateRen = year + "-" + month + "-" + day ;
		}
		return dateRen;
	}

	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		DistributionDataAppendRepMonAction.logger = logger;
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

	public Double getaOne() {
		return aOne;
	}

	public void setaOne(Double aOne) {
		this.aOne = aOne;
	}

	public Double getaTwo() {
		return aTwo;
	}

	public void setaTwo(Double aTwo) {
		this.aTwo = aTwo;
	}

	public Double getaThree() {
		return aThree;
	}

	public void setaThree(Double aThree) {
		this.aThree = aThree;
	}

	public Double getaFour() {
		return aFour;
	}

	public void setaFour(Double aFour) {
		this.aFour = aFour;
	}

	public Double getaFive() {
		return aFive;
	}

	public void setaFive(Double aFive) {
		this.aFive = aFive;
	}

	public Double getaSix() {
		return aSix;
	}

	public void setaSix(Double aSix) {
		this.aSix = aSix;
	}

	public Double getaSeven() {
		return aSeven;
	}

	public void setaSeven(Double aSeven) {
		this.aSeven = aSeven;
	}

	public Double getaEight() {
		return aEight;
	}

	public void setaEight(Double aEight) {
		this.aEight = aEight;
	}

	public Double getbOne() {
		return bOne;
	}

	public void setbOne(Double bOne) {
		this.bOne = bOne;
	}

	public Double getbSix() {
		return bSix;
	}

	public void setbSix(Double bSix) {
		this.bSix = bSix;
	}

	public Double getdNine() {
		return dNine;
	}

	public void setdNine(Double dNine) {
		this.dNine = dNine;
	}

	public Double gethSeven() {
		return hSeven;
	}

	public void sethSeven(Double hSeven) {
		this.hSeven = hSeven;
	}

	public Double getcOne() {
		return cOne;
	}

	public void setcOne(Double cOne) {
		this.cOne = cOne;
	}

	public Double getcSix() {
		return cSix;
	}

	public void setcSix(Double cSix) {
		this.cSix = cSix;
	}

	public Double getcSeven() {
		return cSeven;
	}

	public void setcSeven(Double cSeven) {
		this.cSeven = cSeven;
	}

	public Double getcEight() {
		return cEight;
	}

	public void setcEight(Double cEight) {
		this.cEight = cEight;
	}
	
	

	public Double getbEight() {
		return bEight;
	}

	public void setbEight(Double bEight) {
		this.bEight = bEight;
	}

	public Double getdTen() {
		return dTen;
	}

	public void setdTen(Double dTen) {
		this.dTen = dTen;
	}
	
	

	public Double geteOne() {
		return eOne;
	}

	public void seteOne(Double eOne) {
		this.eOne = eOne;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public IDistributionDataAppendRepMonService getDistributionDataAppendRepMonService() {
		return distributionDataAppendRepMonService;
	}

	public void setDistributionDataAppendRepMonService(
			IDistributionDataAppendRepMonService distributionDataAppendRepMonService) {
		this.distributionDataAppendRepMonService = distributionDataAppendRepMonService;
	}

	public void setDistributionDataAppendRepMonLists(
			List<DistributionDataAppendRepMon> distributionDataAppendRepMonLists) {
		this.distributionDataAppendRepMonLists = distributionDataAppendRepMonLists;
	}

	public List<DistributionDataAppendRepMon> getDistributionDataAppendRepMonLists() {
		return distributionDataAppendRepMonLists;
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStore_category() {
		return store_category;
	}

	public void setStore_category(String store_category) {
		this.store_category = store_category;
	}

	public String getResign_date() {
		return resign_date;
	}

	public void setResign_date(String resign_date) {
		this.resign_date = resign_date;
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

	public DistributionDataAppendRepMon getdGoal() {
		return dGoal;
	}

	public void setdGoal(DistributionDataAppendRepMon dGoal) {
		this.dGoal = dGoal;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

}
