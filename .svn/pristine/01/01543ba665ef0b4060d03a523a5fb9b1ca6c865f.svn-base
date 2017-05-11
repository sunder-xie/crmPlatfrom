package com.kintiger.platform.distributionDataRepMon.action;

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

import com.kintiger.platform.distributionDataRepMon.pojo.DistributionDataRepMon;
import com.kintiger.platform.distributionDataRepMon.service.IDistributionDataRepMonService;
//import com.kintiger.platform.distributionGoal.pojo.DistributionGoal;
//import com.kintiger.platform.distributionInventory.pojo.DistributionInventory;

import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;

public class DistributionDataRepMonAction extends BaseAction {

	private static final long serialVersionUID = -532423459213585547L;
	private static Log logger = LogFactory.getLog(DistributionDataRepMonAction.class);
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
	@Decode
	private String startDate;
	@Decode
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
	private String resign_date;

	private String ids;
	private int size;
	private IOrgService orgServiceHessian;
	private IDistributionDataRepMonService distributionDataRepMonService;
	private List<DistributionDataRepMon> distributionDataRepMonLists;
	private String uploadFileFileName;
	private File uploadFile;
	private String xmlFilePath;
	private DistributionDataRepMon dGoal;
	private IKunnrService kunnrService;

	public String distributionDataRepMonSearch() {
		userId = this.getUser().getUserId();
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		//if (org.getOrgId() == 51240) {
			return "distributionDataRepMonSearch";
		//} else {
			//return "toDistributionDataRepMonSearch";
		//}
	}

	/**
	 * 查询目标 add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "distributionDataRepMonLists", total = "size", include = {
			"orgRegion", "orgProvince", "orgCity", "firstUser", "secondUser",
			"thirdUser", "dire_super_name", "dire_super_position", "kunnrName",
			"kunnrId", "inputDate", "aOne", "aTwo", "dgId", "aThree", "aFour",
			"aFive", "aSix", "aSeven", "aEight", "bOne","bSix", "bEight", "cOne",
			"cSix", "cSeven", "cEight", "dNine", "dTen", "eOne", "hSeven",
			"inputName", "checkName", "trFlag", "resign_date" ,
			"lastDate","thisSysdate","createDate","startDate","endDate","itemJLTotal",
			"itemGoalTotal","dcl", "aOneM", "aTwoM", "aThreeM", "aFourM",
			"aFiveM", "aSixM", "aSevenM", "aEightM", "bOneM","bSixM", "bEightM", "cOneM",
			"cSixM", "cSevenM", "cEightM", "dNineM", "dTenM", "eOneM", "hSevenM"})
	public String getDistributionDataRepMonList() {
		distributionDataRepMonLists = new ArrayList<DistributionDataRepMon>();
		DistributionDataRepMon dGoal = new DistributionDataRepMon();
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
			//dGoal.setStartDate(startDate.replaceAll("年","-").replaceAll("月",""));
		}
		if (StringUtils.isNotEmpty(endDate)) {
			dGoal.setEndDate(endDate);
			//dGoal.setEndDate(endDate.replaceAll("年","-").replaceAll("月",""));
		}
		if (StringUtils.isNotEmpty(trFlag)) {
			dGoal.setTrFlag(trFlag);
		}
		dGoal.setStart(this.getStart());
		dGoal.setEnd(this.getEnd());
		size = distributionDataRepMonService.getDistributionDataRepMonCount(dGoal);
		if (size != 0) {
			distributionDataRepMonLists = distributionDataRepMonService
					.getDistributionDataRepMonList(dGoal);
		}
		return JSON;
	}

	/**
	 * 页面删除目标add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String deleteDistributionDataRepMon() {
		DistributionDataRepMon dGoal = new DistributionDataRepMon();
		try {
			String[] l = ids.split(",");
			dGoal.setIds(l);

			StringResult result = distributionDataRepMonService
					.deleteDistributionDataRepMon(dGoal);

			if (distributionDataRepMonService.ERROR.equals(result.getCode())) {
				this.setFailMessage(result.getResult());
			} else {
				this.setSuccessMessage("处理成功");
			}
		} catch (Exception e) {
			this.setFailMessage("处理失败！");
			logger.error(dGoal, e);
		}
		return RESULT_MESSAGE;
	}

	
	private void List2Excel(List<DistributionDataRepMon> unMiantList) {
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
			String fileName = "城市经理分销数据月报统计列表.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("第一页", 0);
			//wsheet.setColumnView(0, 13);
			//wsheet.setColumnView(1, 13);
			wsheet.setColumnView(0, 9);
			wsheet.setColumnView(1, 9);
			wsheet.setColumnView(2, 13);
			wsheet.setColumnView(3, 12);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 11);
			wsheet.setColumnView(6, 11);
			wsheet.setColumnView(7, 11);
			wsheet.setColumnView(8, 10);
			wsheet.setColumnView(9, 19);
			wsheet.setColumnView(10, 22);
			wsheet.setColumnView(11, 18);
			wsheet.setColumnView(12, 23);
			wsheet.setColumnView(13, 24);
			wsheet.setColumnView(14, 24);
			wsheet.setColumnView(15, 27);
			wsheet.setColumnView(16, 31);
			wsheet.setColumnView(17, 28);
			wsheet.setColumnView(18, 30);
			wsheet.setColumnView(19, 24);
			wsheet.setColumnView(20, 23);
			wsheet.setColumnView(21, 23);
			wsheet.setColumnView(22, 27);
			wsheet.setColumnView(23, 25);
			wsheet.setColumnView(24, 15);
			wsheet.setColumnView(25, 21);
			wsheet.setColumnView(26, 20);
			
			wsheet.setColumnView(27, 19);
			wsheet.setColumnView(28, 22);
			wsheet.setColumnView(29, 18);
			wsheet.setColumnView(30, 23);
			wsheet.setColumnView(31, 24);
			wsheet.setColumnView(32, 24);
			wsheet.setColumnView(33, 27);
			wsheet.setColumnView(34, 31);
			wsheet.setColumnView(35, 28);
			wsheet.setColumnView(36, 30);
			wsheet.setColumnView(37, 24);
			wsheet.setColumnView(38, 23);
			wsheet.setColumnView(39, 23);
			wsheet.setColumnView(40, 27);
			wsheet.setColumnView(41, 25);
			wsheet.setColumnView(42, 15);
			wsheet.setColumnView(43, 21);
			wsheet.setColumnView(44, 20);
			
			wsheet.setColumnView(45, 32);
			wsheet.setColumnView(46, 32);
			wsheet.setColumnView(47, 32);
			
			wsheet.mergeCells(0, 0, 0, 1);
			wsheet.mergeCells(1, 0, 1, 1);
			wsheet.mergeCells(2, 0, 2, 1);
			wsheet.mergeCells(3, 0, 3, 1);
			wsheet.mergeCells(4, 0, 4, 1);
			wsheet.mergeCells(5, 0, 5, 1);
			wsheet.mergeCells(6, 0, 6, 1);
			wsheet.mergeCells(7, 0, 7, 1);
			wsheet.mergeCells(8, 0, 8, 1);

			wsheet.mergeCells(9, 0, 26, 0);
			wsheet.mergeCells(27, 0, 44, 0);
			
			
			
			wsheet.mergeCells(45, 0, 45, 1);
			wsheet.mergeCells(46, 0, 46, 1);
			wsheet.mergeCells(47, 0, 47, 1);
			

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
			
			wsheet.addCell(new Label(0, 0, "大区", wcfFG));
			wsheet.addCell(new Label(1, 0, "省份", wcfFG));
			wsheet.addCell(new Label(2, 0, "城市", wcfFG));
			wsheet.addCell(new Label(3, 0, "经销商编号", wcfFG));
			wsheet.addCell(new Label(4, 0, "经销商名称 ", wcfFG));
			wsheet.addCell(new Label(5, 0, "分销日期", wcfFG));
			wsheet.addCell(new Label(6, 0, "城市经理", wcfFG));
			wsheet.addCell(new Label(7, 0, "省级经理", wcfFG));
			wsheet.addCell(new Label(8, 0, "大区经理", wcfFG));
			
			wsheet.addCell(new Label(9, 0, "城市经理分销数据", wcfFT));
			wsheet.addCell(new Label(9, 1, "A1椰果单杯-80g*30", wcfFT));
			wsheet.addCell(new Label(10, 1, "A3椰果经典装-80g*30", wcfFT));
			wsheet.addCell(new Label(11, 1, "B1红豆单杯-64g*30", wcfFT));
			wsheet.addCell(new Label(12, 1, "C1桂圆红枣单杯-65g*30", wcfFT));
			wsheet.addCell(new Label(13, 1, "A4椰果礼盒装-80g*12*8", wcfFT));
			wsheet.addCell(new Label(14, 1, "A5椰果礼盒装-80g*8*10", wcfFT));
			wsheet.addCell(new Label(15, 1, "A6椰果家庭分享装-80g*16*6", wcfFT));
			wsheet.addCell(new Label(16, 1, "C6桂圆红枣家庭分享装-65g*16*6", wcfFT));
			wsheet.addCell(new Label(17, 1, "A7椰果家庭分享装-80g*15*6", wcfFT));
			wsheet.addCell(new Label(18, 1, "C7桂圆红枣家庭分享装-65g*15*6", wcfFT));
			wsheet.addCell(new Label(19, 1, "A2椰果联杯装-80g*6*6", wcfFT));
			wsheet.addCell(new Label(20, 1, "A8椰果联杯装-80g*3*10", wcfFT));
			wsheet.addCell(new Label(21, 1, "B8红豆联杯装-80g*3*10", wcfFT));
			wsheet.addCell(new Label(22, 1, "C8桂圆红枣联杯装-65g*3*10", wcfFT));
			wsheet.addCell(new Label(23, 1, "H7功夫奶茶单瓶-400ml*15", wcfFT));
			wsheet.addCell(new Label(24, 1, "D9超值组合装", wcfFT));
			wsheet.addCell(new Label(25, 1, "D10豪华礼盒装-15*6", wcfFT));
			wsheet.addCell(new Label(26, 1, "E1豪华礼盒装-12*6", wcfFT));
			
			wsheet.addCell(new Label(27, 0, "城市经理目标量数据", wcfFY));
			wsheet.addCell(new Label(27, 1, "A1椰果单杯-80g*30", wcfFY));
			wsheet.addCell(new Label(28, 1, "A3椰果经典装-80g*30", wcfFY));
			wsheet.addCell(new Label(29, 1, "B1红豆单杯-64g*30", wcfFY));
			wsheet.addCell(new Label(30, 1, "C1桂圆红枣单杯-65g*30", wcfFY));
			wsheet.addCell(new Label(31, 1, "A4椰果礼盒装-80g*12*8", wcfFY));
			wsheet.addCell(new Label(32, 1, "A5椰果礼盒装-80g*8*10", wcfFY));
			wsheet.addCell(new Label(33, 1, "A6椰果家庭分享装-80g*16*6", wcfFY));
			wsheet.addCell(new Label(34, 1, "C6桂圆红枣家庭分享装-65g*16*6", wcfFY));
			wsheet.addCell(new Label(35, 1, "A7椰果家庭分享装-80g*15*6", wcfFY));
			wsheet.addCell(new Label(36, 1, "C7桂圆红枣家庭分享装-65g*15*6", wcfFY));
			wsheet.addCell(new Label(37, 1, "A2椰果联杯装-80g*6*6", wcfFY));
			wsheet.addCell(new Label(38, 1, "A8椰果联杯装-80g*3*10", wcfFY));
			wsheet.addCell(new Label(39, 1, "B8红豆联杯装-80g*3*10", wcfFY));
			wsheet.addCell(new Label(40, 1, "C8桂圆红枣联杯装-65g*3*10", wcfFY));
			wsheet.addCell(new Label(41, 1, "H7功夫奶茶单瓶-400ml*15", wcfFY));
			wsheet.addCell(new Label(42, 1, "D9超值组合装", wcfFY));
			wsheet.addCell(new Label(43, 1, "D10豪华礼盒装-15*6", wcfFY));
			wsheet.addCell(new Label(44, 1, "E1豪华礼盒装-12*6", wcfFY));
			
			wsheet.addCell(new Label(45, 0, "达成合计标箱（除去功夫奶茶）", wcfFT));
			wsheet.addCell(new Label(46, 0, "目标合计标箱（除去功夫奶茶）", wcfFT));
			wsheet.addCell(new Label(47, 0, "目标达成率（除去功夫奶茶）", wcfFT));

			for (int i = 1; i <= unMiantList.size(); i++) {
				
				wsheet.addCell(new Label(0, i + 1, unMiantList.get(i - 1)
						.getOrgRegion(), wcfF));
				wsheet.addCell(new Label(1, i + 1, unMiantList.get(i - 1)
						.getOrgProvince(), wcfF));
				wsheet.addCell(new Label(2, i + 1, unMiantList.get(i - 1)
						.getOrgCity(), wcfF));
				wsheet.addCell(new Label(3, i + 1, unMiantList.get(i - 1)
						.getKunnrId(), wcfF));
				wsheet.addCell(new Label(4, i + 1, unMiantList.get(i - 1)
						.getKunnrName(), wcfF));
				wsheet.addCell(new Label(5, i + 1, unMiantList.get(i - 1)
						.getInputDate(), wcfF));
				wsheet.addCell(new Label(6, i + 1, unMiantList.get(i - 1)
						.getFirstUser(), wcfF));
				wsheet.addCell(new Label(7, i + 1, unMiantList.get(i - 1)
						.getSecondUser(), wcfF));
				wsheet.addCell(new Label(8, i + 1, unMiantList.get(i - 1)
						.getThirdUser(), wcfF));
				wsheet.addCell(new Number(9, i + 1, unMiantList.get(i - 1)
						.getaOne(), wcfF));
				wsheet.addCell(new Number(10, i + 1, unMiantList.get(i - 1)
						.getaThree(), wcfF));
				wsheet.addCell(new Number(11, i + 1, unMiantList.get(i - 1)
						.getbOne(), wcfF));
				wsheet.addCell(new Number(12, i + 1, unMiantList.get(i - 1)
						.getcOne(), wcfF));
				wsheet.addCell(new Number(13, i + 1, unMiantList.get(i - 1)
						.getaFour(), wcfF));
				wsheet.addCell(new Number(14, i + 1, unMiantList.get(i - 1)
						.getaFive(), wcfF));
				wsheet.addCell(new Number(15, i + 1, unMiantList.get(i - 1)
						.getaSix(), wcfF));
				wsheet.addCell(new Number(16, i + 1, unMiantList.get(i - 1)
						.getcSix(), wcfF));
				wsheet.addCell(new Number(17, i + 1, unMiantList.get(i - 1)
						.getaSeven(), wcfF));
				wsheet.addCell(new Number(18, i + 1, unMiantList.get(i - 1)
						.getcSeven(), wcfF));
				wsheet.addCell(new Number(19, i + 1, unMiantList.get(i - 1)
						.getaTwo(), wcfF));
				wsheet.addCell(new Number(20, i + 1, unMiantList.get(i - 1)
						.getaEight(), wcfF));
				wsheet.addCell(new Number(21, i + 1, unMiantList.get(i - 1)
						.getbEight(), wcfF));
				wsheet.addCell(new Number(22, i + 1, unMiantList.get(i - 1)
						.getcEight(), wcfF));
				wsheet.addCell(new Number(23, i + 1, unMiantList.get(i - 1)
						.gethSeven(), wcfF));
				wsheet.addCell(new Number(24, i + 1, unMiantList.get(i - 1)
						.getdNine(), wcfF));
				wsheet.addCell(new Number(25, i + 1, unMiantList.get(i - 1)
						.getdTen(), wcfF));
				wsheet.addCell(new Number(26, i + 1, unMiantList.get(i - 1)
						.geteOne(), wcfF));
				
				wsheet.addCell(new Number(27, i + 1, unMiantList.get(i - 1)
						.getaOneM(), wcfF));
				wsheet.addCell(new Number(28, i + 1, unMiantList.get(i - 1)
						.getaThreeM(), wcfF));
				wsheet.addCell(new Number(29, i + 1, unMiantList.get(i - 1)
						.getbOneM(), wcfF));
				wsheet.addCell(new Number(30, i + 1, unMiantList.get(i - 1)
						.getcOneM(), wcfF));
				wsheet.addCell(new Number(31, i + 1, unMiantList.get(i - 1)
						.getaFourM(), wcfF));
				wsheet.addCell(new Number(32, i + 1, unMiantList.get(i - 1)
						.getaFiveM(), wcfF));
				wsheet.addCell(new Number(33, i + 1, unMiantList.get(i - 1)
						.getaSixM(), wcfF));
				wsheet.addCell(new Number(34, i + 1, unMiantList.get(i - 1)
						.getcSixM(), wcfF));
				wsheet.addCell(new Number(35, i + 1, unMiantList.get(i - 1)
						.getaSevenM(), wcfF));
				wsheet.addCell(new Number(36, i + 1, unMiantList.get(i - 1)
						.getcSevenM(), wcfF));
				wsheet.addCell(new Number(37, i + 1, unMiantList.get(i - 1)
						.getaTwoM(), wcfF));
				wsheet.addCell(new Number(38, i + 1, unMiantList.get(i - 1)
						.getaEightM(), wcfF));
				wsheet.addCell(new Number(39, i + 1, unMiantList.get(i - 1)
						.getbEightM(), wcfF));
				wsheet.addCell(new Number(40, i + 1, unMiantList.get(i - 1)
						.getcEightM(), wcfF));
				wsheet.addCell(new Number(41, i + 1, unMiantList.get(i - 1)
						.gethSevenM(), wcfF));
				wsheet.addCell(new Number(42, i + 1, unMiantList.get(i - 1)
						.getdNineM(), wcfF));
				wsheet.addCell(new Number(43, i + 1, unMiantList.get(i - 1)
						.getdTenM(), wcfF));
				wsheet.addCell(new Number(44, i + 1, unMiantList.get(i - 1)
						.geteOneM(), wcfF));
				
				wsheet.addCell(new Label(45, i + 1, unMiantList.get(i - 1)
						.getItemJLTotal(), wcfF));
				wsheet.addCell(new Label(46, i + 1, unMiantList.get(i - 1)
						.getItemGoalTotal(), wcfF));
				wsheet.addCell(new Label(47, i + 1, unMiantList.get(i - 1)
						.getDcl(), wcfF));
				
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

	public String exportDistributionDataRepMon() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		List<String> props = new ArrayList<String>();
		distributionDataRepMonLists = new ArrayList<DistributionDataRepMon>();
		DistributionDataRepMon dbData = new DistributionDataRepMon();
		dbData.setPagination("false");// 不使用分页 全部查出来
		HttpServletResponse response = getServletResponse();

		ServletActionContext.getRequest().getSession()
				.setAttribute("DownLoad", "Ing");
		try {
			distributionDataRepMonLists = new ArrayList<DistributionDataRepMon>();
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
			distributionDataRepMonLists = distributionDataRepMonService
					.getDistributionDataRepMonList(dbData);

			if (distributionDataRepMonLists.size() == 0) {
				this.setFailMessage("Excel数据导出出错,请不要导出数据为空的列表");
			}
			List2Excel(distributionDataRepMonLists);
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
		DistributionDataRepMonAction.logger = logger;
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

	public double getaOne() {
		return aOne;
	}

	public void setaOne(double aOne) {
		this.aOne = aOne;
	}

	public double getaTwo() {
		return aTwo;
	}

	public void setaTwo(double aTwo) {
		this.aTwo = aTwo;
	}

	public double getaThree() {
		return aThree;
	}

	public void setaThree(double aThree) {
		this.aThree = aThree;
	}

	public double getaFour() {
		return aFour;
	}

	public void setaFour(double aFour) {
		this.aFour = aFour;
	}

	public double getaFive() {
		return aFive;
	}

	public void setaFive(double aFive) {
		this.aFive = aFive;
	}

	public double getaSix() {
		return aSix;
	}

	public void setaSix(double aSix) {
		this.aSix = aSix;
	}

	public double getaSeven() {
		return aSeven;
	}

	public void setaSeven(double aSeven) {
		this.aSeven = aSeven;
	}

	public double getaEight() {
		return aEight;
	}

	public void setaEight(double aEight) {
		this.aEight = aEight;
	}

	public double getbOne() {
		return bOne;
	}

	public void setbOne(double bOne) {
		this.bOne = bOne;
	}

	public double getbEight() {
		return bEight;
	}

	public void setbEight(double bEight) {
		this.bEight = bEight;
	}

	public double getcOne() {
		return cOne;
	}

	public void setcOne(double cOne) {
		this.cOne = cOne;
	}

	public double getcSix() {
		return cSix;
	}

	public void setcSix(double cSix) {
		this.cSix = cSix;
	}

	public double getcSeven() {
		return cSeven;
	}

	public void setcSeven(double cSeven) {
		this.cSeven = cSeven;
	}

	public double getcEight() {
		return cEight;
	}

	public void setcEight(double cEight) {
		this.cEight = cEight;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public IDistributionDataRepMonService getDistributionDataRepMonService() {
		return distributionDataRepMonService;
	}

	public void setDistributionDataRepMonService(
			IDistributionDataRepMonService distributionDataRepMonService) {
		this.distributionDataRepMonService = distributionDataRepMonService;
	}

	public void setDistributionDataRepMonLists(
			List<DistributionDataRepMon> distributionDataRepMonLists) {
		this.distributionDataRepMonLists = distributionDataRepMonLists;
	}

	public List<DistributionDataRepMon> getDistributionDataRepMonLists() {
		return distributionDataRepMonLists;
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

	public DistributionDataRepMon getdGoal() {
		return dGoal;
	}

	public void setdGoal(DistributionDataRepMon dGoal) {
		this.dGoal = dGoal;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	public double getbSix() {
		return bSix;
	}

	public void setbSix(double bSix) {
		this.bSix = bSix;
	}

	public double getdNine() {
		return dNine;
	}

	public void setdNine(double dNine) {
		this.dNine = dNine;
	}

	public double gethSeven() {
		return hSeven;
	}

	public void sethSeven(double hSeven) {
		this.hSeven = hSeven;
	}

	public double getdTen() {
		return dTen;
	}

	public void setdTen(double dTen) {
		this.dTen = dTen;
	}

	public double geteOne() {
		return eOne;
	}

	public void seteOne(double eOne) {
		this.eOne = eOne;
	}

}
