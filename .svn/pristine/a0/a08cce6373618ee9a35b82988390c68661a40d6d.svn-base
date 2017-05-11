package com.kintiger.platform.batOrgUpdate.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.batOrgUpdate.service.IBatOrgUpdateService;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.customer.service.ICustomerService;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;

public class BatOrgUpdateAction extends BaseAction {

	/**
	 * 
	 */

	private static final long serialVersionUID = 6759982143373659768L;
	private static final Log logger = LogFactory
			.getLog(BatOrgUpdateAction.class);

	private List<Kunnr> kunnrList;
	private List<Customer> custList;
	@Decode
	private String userId;
	@Decode
	private String userName;
	private String uploadFileFileName;
	private String uploadFileName;
	private File uploadFile;
	private int total;
	private String type;

	private File upload;
	private ICustomerService customerService;
	private IBatOrgUpdateService batOrgUpdateService;
	private IDictService dictServiceHessian;
	private IKunnrService kunnrService;
	private IOrgService orgServiceHessian;

	/**
	 * 跳转到批量组织调整页面
	 * 
	 * @return
	 */
	public String toBatOrgUpdatePre() {
		userId = this.getUser().getUserId();
		userName = this.getUser().getUserName();
		this.getSession().setAttribute("kunnrs", null);
		return "toBatOrgUpdatePre";
	}

	/**
	 * 加载批量终端客户组织调整页签
	 * 
	 * @return
	 */
	public String toBatCustOrgUpdatePre() {
		userId = this.getUser().getUserId();
		userName = this.getUser().getUserName();
		this.getSession().setAttribute("custs", null);
		return "toBatCustOrgUpdatePre";
	}

	/**
	 * 终端客户组织调整查询结果列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PermissionSearch
	@JsonResult(field = "custList", include = { "custName", "custNumber",
			"orgId", "orgName" }, total = "total")
	public String searchCustListJson() {
		custList = (List<Customer>) this.getSession().getAttribute("custs");
		if (custList != null) {
			total = custList.size();
		} else {
			total = 0;
		}
		return JSON;
	}

	/**
	 * 经销商组织调整查询结果列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PermissionSearch
	@JsonResult(field = "kunnrList", include = { "kunnr", "name1", "orgId",
			"orgName" }, total = "total")
	public String searchKunnrListJson() {
		kunnrList = (List<Kunnr>) this.getSession().getAttribute("kunnrs");
		if (kunnrList != null) {
			total = kunnrList.size();
		} else {
			total = 0;
		}
		return JSON;
	}

	/***
	 * 
	 * 2007 经销商组织修改excel模版导出
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportExcel() {
		OutputStream os = null;
		XSSFWorkbook wbook = null;
		// String dateStr=DateUtil.getNowDateStr(); //获取当前年月日字符串
		// 获取所选时间段的日期
		// 获取数据拼表格
		String report_name = "";
		if (type.equals("kunnr")) {
			report_name = "经销商组织批量调整模板";
		} else if (type.equals("cust")) {
			report_name = "终端客户组织批量调整模板";
		}
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".xlsx\"");
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = new XSSFWorkbook();

			XSSFCellStyle cellStyle = wbook.createCellStyle();

			cellStyle.setWrapText(true);// 指定单元格自动换行

			cellStyle.setWrapText(true);//

			XSSFSheet rs = null;
			if (type.equals("kunnr")) {
				rs = wbook.createSheet("经销商组织调整");
			} else if (type.equals("cust")) {
				rs = wbook.createSheet("终端客户组织调整");
			}
			XSSFRow row1 = rs.createRow(0);
			// 表头
			if (type.equals("kunnr")) {
				row1.createCell(0).setCellValue("经销商编码");
				row1.createCell(1).setCellValue("经销商名称");
			} else if (type.equals("cust")) {
				row1.createCell(0).setCellValue("客户编号");
				row1.createCell(1).setCellValue("客户名称");
			}
			row1.createCell(2).setCellValue("组织名称");
			// 设置表格输入提示
			XSSFRow row2 = rs.createRow(5);
			row2.createCell(0).setCellValue(
					"温馨提示:编码、名称需与EXP系统一致,编码列格式请设为文本格式;组织名称填写"
							+ "新的所属组织,组织名称需与EXP组织树上的名称一致!导入时请将此提示删除后再导入!");

			wbook.write(os);
		} catch (Exception e) {
			logger.error(e);
		} finally {
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

	/***
	 * 
	 * 查看经销商、客户批量组织导入的信息
	 * 
	 * @return
	 */
	public String checkExcel() {
		HSSFWorkbook hssfworkbook = null; // 03版
		XSSFWorkbook wb = null; // 07版
		StringBuffer result = new StringBuffer();
		PrintWriter out = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			custList = new ArrayList<Customer>();
			kunnrList = new ArrayList<Kunnr>();
			response.setContentType("text/html; charset=GBK");
			out = response.getWriter();
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if (end != null && (end.equals(".xls"))) {
					hssfworkbook = new HSSFWorkbook(new FileInputStream(
							new File(uploadFile.toString())));
					HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);
					for (int j = 1; j < hssfsheet.getLastRowNum() + 1; j++) {
						HSSFRow hssfrow = hssfsheet.getRow(j);
						int rsColumns = hssfrow.getLastCellNum();
						String rcs = "";
						int i = 0;
						Customer cust = new Customer();
						Customer custHelp = new Customer();
						Kunnr kunnr = new Kunnr();
						Kunnr kunnrHelp = new Kunnr();
						if (type.equals("cust")) {
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell1 = hssfrow.getCell(i).toString();
								custHelp.setCustNumber(cell1);
							} else {
								rcs = rcs + "第" + (j + 1) + "行客户编号为空.</br>";
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell2 = hssfrow.getCell(i)
										.getRichStringCellValue().toString();
								custHelp.setCustName(cell2);
							} else {
								rcs = rcs + "第" + (j + 1) + "行客户名称为空.</br>";
							}
							int count = 0;
							if (null != custHelp.getCustNumber()) {
								count = batOrgUpdateService.getCustomerIsExit(
										custHelp.getCustNumber(),
										custHelp.getCustName());
							}
							if (count == 0) {
								rcs = rcs + "第" + (j + 1) + "行客户系统不存在.</br>";
							}else{
								cust.setCustNumber(custHelp.getCustNumber());
								cust.setCustName(custHelp.getCustName());
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell3 = hssfrow.getCell(i).toString().trim();
								Borg borg=new Borg();
								borg.setOrgName(cell3);
								borg=customerService.gerOrgIsExit(borg);
								if(null!=borg){
									Long orgId=borg.getOrgId();
									cust.setOrgId(String.valueOf(orgId));
									cust.setOrgName(cell3);
								}else{
									rcs = rcs + "第" + (j + 1) + "行组织名称系统不存在.</br>";
								}
								
							} else {
								rcs = rcs + "第" + (j + 1) + "行组织名称为空.</br>";
							}
						} else if (type.equals("kunnr")) {
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell1 = hssfrow.getCell(i).toString();
								kunnrHelp.setKunnr(cell1);

							} else {
								rcs = rcs + "第" + (j + 1) + "行经销商编号为空.</br>";
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell2 = hssfrow.getCell(i).toString();
								kunnrHelp.setName1(cell2);
							} else {
								rcs = rcs + "第" + (j + 1) + "行经销商名称为空.</br>";
							}
							int count = 0;
							if (null != kunnrHelp.getKunnr()) {
								count = batOrgUpdateService.getKunnrIsExit(
										StringUtils.leftPad(kunnrHelp.getKunnr(),
												8, '0'), kunnrHelp.getName1());
							}
							if (count == 0) {
								rcs = rcs + "第" + (j + 1) + "行经销商系统不存在.</br>";
							}else{
								kunnr.setKunnr(StringUtils.leftPad(kunnrHelp.getKunnr(),8, '0'));
								kunnr.setName1(kunnrHelp.getName1());
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell3 = hssfrow.getCell(i).toString().trim();
								Borg borg=new Borg();
								borg.setOrgName(cell3);
								borg=customerService.gerOrgIsExit(borg);
								if(null!=borg){
									Long orgId=borg.getOrgId();
									kunnr.setOrgId(String.valueOf(orgId));
									kunnr.setOrgName(borg.getOrgName());
								}else{
									rcs = rcs + "第" + (j + 1) + "行组织名称系统不存在.</br>";
								}
								
							} else {
								rcs = rcs + "第" + (j + 1) + "行组织名称为空.</br>";
							}
						}
						if (rcs.equals("")) {
							if (type.equals("kunnr")) {
								kunnrList.add(kunnr);
							} else if (type.equals("cust")) {
								custList.add(cust);
							}
						} else {
							result.append(rcs.toString() + "</br>");
							continue;
						}
					}

					if (result.toString().equals("")) {
						if(type.equals("cust")){
							this.getSession().setAttribute("custs", custList);
							this.setSuccessMessage("导入成功！导入数量为:" + custList.size()
									+ "行");
						}else if(type.equals("kunnr")){
							this.getSession().setAttribute("kunnrs", kunnrList);
							this.setSuccessMessage("导入成功！导入数量为:" + kunnrList.size()
									+ "行");
						}
						
					} else {
						this.setSuccessMessage("导入失败！   错误信息："
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else if (end.equals(".xlsx")) {
					wb = new XSSFWorkbook(new FileInputStream(new File(
							uploadFile.toString())));
					XSSFSheet rs = wb.getSheetAt(0);
					for (int j = 1; j < rs.getLastRowNum() + 1; j++) {
						XSSFRow xssfrow = rs.getRow(j);
						int rsColumns = xssfrow.getLastCellNum();
						String rcs = "";
						int i = 0;
						Customer cust = new Customer();
						Customer custHelp = new Customer();
						Kunnr kunnr = new Kunnr();
						Kunnr kunnrHelp = new Kunnr();
						if (type.equals("cust")) {
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell1 = xssfrow.getCell(i).toString();
								custHelp.setCustNumber(cell1);
							} else {
								rcs = rcs + "第" + (j + 1) + "行客户编号为空.</br>";
							}
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell2 = xssfrow.getCell(i).toString();
								custHelp.setCustName(cell2);
							} else {
								rcs = rcs + "第" + (j + 1) + "行客户名称为空.</br>";
							}
							int count = 0;
							if (null != custHelp.getCustNumber()) {
								count = batOrgUpdateService.getCustomerIsExit(
										custHelp.getCustNumber(),
										custHelp.getCustName());
							}
							if (count == 0) {
								rcs = rcs + "第" + (j + 1) + "行客户系统不存在.</br>";
							}else{
								cust.setCustNumber(custHelp.getCustNumber());
								cust.setCustName(custHelp.getCustName());
							}
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell3 = xssfrow.getCell(i).toString().trim();
								Borg borg=new Borg();
								borg.setOrgName(cell3);
								borg=customerService.gerOrgIsExit(borg);
								if(null!=borg){
									Long orgId=borg.getOrgId();
									cust.setOrgId(String.valueOf(orgId));
									cust.setOrgName(cell3);
								}else{
									rcs = rcs + "第" + (j + 1) + "行组织名称系统不存在.</br>";
								}
								
							} else {
								rcs = rcs + "第" + (j + 1) + "行组织名称为空.</br>";
							}
						} else if (type.equals("kunnr")) {
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell1 = xssfrow.getCell(i).toString();
								kunnrHelp.setKunnr(cell1);

							} else {
								rcs = rcs + "第" + (j + 1) + "行经销商编号为空.</br>";
							}
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell2 = xssfrow.getCell(i).toString();
								kunnrHelp.setName1(cell2);
							} else {
								rcs = rcs + "第" + (j + 1) + "行经销商名称为空.</br>";
							}
							int count = 0;
							if (null != kunnrHelp.getKunnr()) {
								count = batOrgUpdateService.getKunnrIsExit(
										StringUtils.leftPad(kunnrHelp.getKunnr(),
												8, '0'), kunnrHelp.getName1());
							}
							if (count == 0) {
								rcs = rcs + "第" + (j + 1) + "行经销商系统不存在.</br>";
							}else{
								kunnr.setKunnr(StringUtils.leftPad(kunnrHelp.getKunnr(),8, '0'));
								kunnr.setName1(kunnrHelp.getName1());
						    }
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell3 = xssfrow.getCell(i).toString().trim();
								Borg borg=new Borg();
								borg.setOrgName(cell3);
								borg=customerService.gerOrgIsExit(borg);
								if(null!=borg){
									Long orgId=borg.getOrgId();
									kunnr.setOrgId(String.valueOf(orgId));
									kunnr.setOrgName(borg.getOrgName());
								}else{
									rcs = rcs + "第" + (j + 1) + "行组织名称系统不存在.</br>";
								}
								
							} else {
								rcs = rcs + "第" + (j + 1) + "行组织名称为空.</br>";
							}
						}
						if (rcs.equals("")) {
							if (type.equals("kunnr")) {
								kunnrList.add(kunnr);
							} else if (type.equals("cust")) {
								custList.add(cust);
							}
						} else {
							result.append(rcs.toString() + "</br>");
							continue;
						}
					}

					if (result.toString().equals("")) {
						if(type.equals("cust")){
							this.getSession().setAttribute("custs", custList);
							this.setSuccessMessage("导入成功！导入数量为:" + custList.size()
									+ "行");
						}else if(type.equals("kunnr")){
							this.getSession().setAttribute("kunnrs", kunnrList);
							this.setSuccessMessage("导入成功！导入数量为:" + kunnrList.size()
									+ "行");
						}
						
					} else {
						this.setSuccessMessage("导入失败！   错误信息："
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else {// 文件格式
					this.setFailMessage("文件格式不正确！");
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
			}
		} catch (Exception e) {
			logger.error(e);
			if (out != null) {
				this.setFailMessage("上传的数据可能存在格式转换问题,请联系系统管理员!");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}

	public boolean lookUpType(String[] s) {
		for (int bb = 0; bb < s.length; bb++) {
			if (s.length == 1 || bb == (s.length - 1)) {
				break;
			} else if (s[bb].equals(s[bb + 1])) {
				return false;
			}

		}
		return true;
	}

	/***
	 * 
	 * 经销商、客户批量组织调整导入
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String importExcel() {
		HSSFWorkbook hssfworkbook = null; // 03版
		XSSFWorkbook wb = null; // 07版
		StringBuffer result = new StringBuffer();
		PrintWriter out = null;
		BooleanResult bResult=new BooleanResult();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			custList = new ArrayList<Customer>();
			kunnrList = new ArrayList<Kunnr>();
			response.setContentType("text/html; charset=GBK");
			out = response.getWriter();
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if (end != null && (end.equals(".xls"))) {
					hssfworkbook = new HSSFWorkbook(new FileInputStream(
							new File(uploadFile.toString())));
					HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);
					for (int j = 1; j < hssfsheet.getLastRowNum() + 1; j++) {
						HSSFRow hssfrow = hssfsheet.getRow(j);
						int rsColumns = hssfrow.getLastCellNum();
						String rcs = "";
						int i = 0;
						Customer cust = new Customer();
						Customer custHelp = new Customer();
						Kunnr kunnr = new Kunnr();
						Kunnr kunnrHelp = new Kunnr();
						if (type.equals("cust")) {
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell1 = hssfrow.getCell(i).toString();
								custHelp.setCustNumber(cell1);
							} else {
								rcs = rcs + "第" + (j + 1) + "行客户编号为空.</br>";
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell2 = hssfrow.getCell(i)
										.getRichStringCellValue().toString();
								custHelp.setCustName(cell2);
							} else {
								rcs = rcs + "第" + (j + 1) + "行客户名称为空.</br>";
							}
							int count = 0;
							if (null != custHelp.getCustNumber()) {
								count = batOrgUpdateService.getCustomerIsExit(
										custHelp.getCustNumber(),
										custHelp.getCustName());
							}
							if (count == 0) {
								rcs = rcs + "第" + (j + 1) + "行客户系统不存在.</br>";
							}else{
								cust.setCustNumber(custHelp.getCustNumber());
								cust.setCustName(custHelp.getCustName());
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell3 = hssfrow.getCell(i).toString().trim();
								Borg borg=new Borg();
								borg.setOrgName(cell3);
								borg=customerService.gerOrgIsExit(borg);
								if(null!=borg){
									Long orgId=borg.getOrgId();
									cust.setOrgId(String.valueOf(orgId));
									cust.setOrgName(cell3);
								}else{
									rcs = rcs + "第" + (j + 1) + "行组织名称系统不存在.</br>";
								}
								
							} else {
								rcs = rcs + "第" + (j + 1) + "行组织名称为空.</br>";
							}
						} else if (type.equals("kunnr")) {
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell1 = hssfrow.getCell(i).toString();
								kunnrHelp.setKunnr(cell1);

							} else {
								rcs = rcs + "第" + (j + 1) + "行经销商编号为空.</br>";
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell2 = hssfrow.getCell(i).toString();
								kunnrHelp.setName1(cell2);
							} else {
								rcs = rcs + "第" + (j + 1) + "行经销商名称为空.</br>";
							}
							int count = 0;
							if (null != kunnrHelp.getKunnr()) {
								count = batOrgUpdateService.getKunnrIsExit(
										StringUtils.leftPad(kunnrHelp.getKunnr(),
												8, '0'), kunnrHelp.getName1());
							}
							if (count == 0) {
								rcs = rcs + "第" + (j + 1) + "行经销商系统不存在.</br>";
							}else{
								kunnr.setKunnr(StringUtils.leftPad(kunnrHelp.getKunnr(),8, '0'));
								kunnr.setName1(kunnrHelp.getName1());
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell3 = hssfrow.getCell(i).toString().trim();
								Borg borg=new Borg();
								borg.setOrgName(cell3);
								borg=customerService.gerOrgIsExit(borg);
								if(null!=borg){
									Long orgId=borg.getOrgId();
									kunnr.setOrgId(String.valueOf(orgId));
									kunnr.setOrgName(borg.getOrgName());
								}else{
									rcs = rcs + "第" + (j + 1) + "行组织名称系统不存在.</br>";
								}
								
							} else {
								rcs = rcs + "第" + (j + 1) + "行组织名称为空.</br>";
							}
						}
						if (rcs.equals("")) {
							if (type.equals("kunnr")) {
								kunnrList.add(kunnr);
							} else if (type.equals("cust")) {
								custList.add(cust);
							}
						} else {
							result.append(rcs.toString() + "</br>");
							continue;
						}
					}

					if (result.toString().equals("")) {
						if(type.equals("cust")){
							//客户组织批量调整
							//this.getSession().setAttribute("custs", custList);
							bResult=batOrgUpdateService.updateCustomerOrgs(custList);
							if(bResult.getResult()){
							this.setSuccessMessage("导入成功！导入数量为:" + custList.size()
									+ "行");}
							else{
								this.setSuccessMessage("导入失败!请联系管理员!");
							}
						}else if(type.equals("kunnr")){
							//经销商组织批量调整
							//this.getSession().setAttribute("kunnrs", kunnrList);
							bResult=batOrgUpdateService.updateKunnrOrgs(kunnrList);
							if(bResult.getResult()){
							this.setSuccessMessage("导入成功！导入数量为:" + kunnrList.size()
									+ "行");}
							else{
								this.setSuccessMessage("导入失败!请联系管理员!");
							}
						}
						
					} else {
						this.setSuccessMessage("导入失败！   错误信息："
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else if (end.equals(".xlsx")) {
					wb = new XSSFWorkbook(new FileInputStream(new File(
							uploadFile.toString())));
					XSSFSheet rs = wb.getSheetAt(0);
					for (int j = 1; j < rs.getLastRowNum() + 1; j++) {
						XSSFRow xssfrow = rs.getRow(j);
						int rsColumns = xssfrow.getLastCellNum();
						String rcs = "";
						int i = 0;
						Customer cust = new Customer();
						Customer custHelp = new Customer();
						Kunnr kunnr = new Kunnr();
						Kunnr kunnrHelp = new Kunnr();
						if (type.equals("cust")) {
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell1 = xssfrow.getCell(i).toString();
								custHelp.setCustNumber(cell1);
							} else {
								rcs = rcs + "第" + (j + 1) + "行客户编号为空.</br>";
							}
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell2 = xssfrow.getCell(i).toString();
								custHelp.setCustName(cell2);
							} else {
								rcs = rcs + "第" + (j + 1) + "行客户名称为空.</br>";
							}
							int count = 0;
							if (null != custHelp.getCustNumber()) {
								count = batOrgUpdateService.getCustomerIsExit(
										custHelp.getCustNumber(),
										custHelp.getCustName());
							}
							if (count == 0) {
								rcs = rcs + "第" + (j + 1) + "行客户系统不存在.</br>";
							}else{
								cust.setCustNumber(custHelp.getCustNumber());
								cust.setCustName(custHelp.getCustName());
							}
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell3 = xssfrow.getCell(i).toString().trim();
								Borg borg=new Borg();
								borg.setOrgName(cell3);
								borg=customerService.gerOrgIsExit(borg);
								if(null!=borg){
									Long orgId=borg.getOrgId();
									cust.setOrgId(String.valueOf(orgId));
									cust.setOrgName(cell3);
								}else{
									rcs = rcs + "第" + (j + 1) + "行组织名称系统不存在.</br>";
								}
								
							} else {
								rcs = rcs + "第" + (j + 1) + "行组织名称为空.</br>";
							}
						} else if (type.equals("kunnr")) {
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell1 = xssfrow.getCell(i).toString();
								kunnrHelp.setKunnr(cell1);

							} else {
								rcs = rcs + "第" + (j + 1) + "行经销商编号为空.</br>";
							}
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell2 = xssfrow.getCell(i).toString();
								kunnrHelp.setName1(cell2);
							} else {
								rcs = rcs + "第" + (j + 1) + "行经销商名称为空.</br>";
							}
							int count = 0;
							if (null != kunnrHelp.getKunnr()) {
								count = batOrgUpdateService.getKunnrIsExit(
										StringUtils.leftPad(kunnrHelp.getKunnr(),
												8, '0'), kunnrHelp.getName1());
							}
							if (count == 0) {
								rcs = rcs + "第" + (j + 1) + "行经销商系统不存在.</br>";
							}else{
								kunnr.setKunnr(StringUtils.leftPad(kunnrHelp.getKunnr(),8, '0'));
								kunnr.setName1(kunnrHelp.getName1());
						    }
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell3 = xssfrow.getCell(i).toString().trim();
								Borg borg=new Borg();
								borg.setOrgName(cell3);
								borg=customerService.gerOrgIsExit(borg);
								if(null!=borg){
									Long orgId=borg.getOrgId();
									kunnr.setOrgId(String.valueOf(orgId));
									kunnr.setOrgName(borg.getOrgName());
								}else{
									rcs = rcs + "第" + (j + 1) + "行组织名称系统不存在.</br>";
								}
								
							} else {
								rcs = rcs + "第" + (j + 1) + "行组织名称为空.</br>";
							}
						}
						if (rcs.equals("")) {
							if (type.equals("kunnr")) {
								kunnrList.add(kunnr);
							} else if (type.equals("cust")) {
								custList.add(cust);
							}
						} else {
							result.append(rcs.toString() + "</br>");
							continue;
						}
					}

					if (result.toString().equals("")) {
						if(type.equals("cust")){
							//客户组织批量调整
							//this.getSession().setAttribute("custs", custList);
							bResult=batOrgUpdateService.updateCustomerOrgs(custList);
							if(bResult.getResult()){
							this.setSuccessMessage("导入成功！导入数量为:" + custList.size()
									+ "行");}
							else{
								this.setSuccessMessage("导入失败!请联系管理员!");
							}
						}else if(type.equals("kunnr")){
							//经销商组织批量调整
							//this.getSession().setAttribute("kunnrs", kunnrList);
							bResult=batOrgUpdateService.updateKunnrOrgs(kunnrList);
							if(bResult.getResult()){
							this.setSuccessMessage("导入成功！导入数量为:" + kunnrList.size()
									+ "行");}
							else{
								this.setSuccessMessage("导入失败!请联系管理员!");
							}
						}
						
					} else {
						this.setSuccessMessage("导入失败！   错误信息："
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else {// 文件格式
					this.setFailMessage("文件格式不正确！");
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
			}
		} catch (Exception e) {
			logger.error(e);
			if (out != null) {
				this.setFailMessage("上传的数据可能存在格式转换问题,请联系系统管理员!");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<Customer> getCustlist() {
		return custList;
	}

	public void setCustlist(List<Customer> custlist) {
		this.custList = custlist;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Kunnr> getKunnrList() {
		return kunnrList;
	}

	public void setKunnrList(List<Kunnr> kunnrList) {
		this.kunnrList = kunnrList;
	}

	public IBatOrgUpdateService getBatOrgUpdateService() {
		return batOrgUpdateService;
	}

	public void setBatOrgUpdateService(IBatOrgUpdateService batOrgUpdateService) {
		this.batOrgUpdateService = batOrgUpdateService;
	}

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public IDictService getDictServiceHessian() {
		return dictServiceHessian;
	}

	public void setDictServiceHessian(IDictService dictServiceHessian) {
		this.dictServiceHessian = dictServiceHessian;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

}
