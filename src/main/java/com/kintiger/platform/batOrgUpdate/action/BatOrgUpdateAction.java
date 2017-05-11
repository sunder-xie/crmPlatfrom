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
	 * ��ת��������֯����ҳ��
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
	 * ���������ն˿ͻ���֯����ҳǩ
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
	 * �ն˿ͻ���֯������ѯ����б�
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
	 * ��������֯������ѯ����б�
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
	 * 2007 ��������֯�޸�excelģ�浼��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportExcel() {
		OutputStream os = null;
		XSSFWorkbook wbook = null;
		// String dateStr=DateUtil.getNowDateStr(); //��ȡ��ǰ�������ַ���
		// ��ȡ��ѡʱ��ε�����
		// ��ȡ����ƴ���
		String report_name = "";
		if (type.equals("kunnr")) {
			report_name = "��������֯��������ģ��";
		} else if (type.equals("cust")) {
			report_name = "�ն˿ͻ���֯��������ģ��";
		}
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// ȡ�������
			os = response.getOutputStream();
			// ��������
			response.reset();
			// �趨����ļ�ͷ
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".xlsx\"");
			// �����������
			response.setContentType("application/msexcel");
			// ����excel�ļ�
			wbook = new XSSFWorkbook();

			XSSFCellStyle cellStyle = wbook.createCellStyle();

			cellStyle.setWrapText(true);// ָ����Ԫ���Զ�����

			cellStyle.setWrapText(true);//

			XSSFSheet rs = null;
			if (type.equals("kunnr")) {
				rs = wbook.createSheet("��������֯����");
			} else if (type.equals("cust")) {
				rs = wbook.createSheet("�ն˿ͻ���֯����");
			}
			XSSFRow row1 = rs.createRow(0);
			// ��ͷ
			if (type.equals("kunnr")) {
				row1.createCell(0).setCellValue("�����̱���");
				row1.createCell(1).setCellValue("����������");
			} else if (type.equals("cust")) {
				row1.createCell(0).setCellValue("�ͻ����");
				row1.createCell(1).setCellValue("�ͻ�����");
			}
			row1.createCell(2).setCellValue("��֯����");
			// ���ñ��������ʾ
			XSSFRow row2 = rs.createRow(5);
			row2.createCell(0).setCellValue(
					"��ܰ��ʾ:���롢��������EXPϵͳһ��,�����и�ʽ����Ϊ�ı���ʽ;��֯������д"
							+ "�µ�������֯,��֯��������EXP��֯���ϵ�����һ��!����ʱ�뽫����ʾɾ�����ٵ���!");

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
	 * �鿴�����̡��ͻ�������֯�������Ϣ
	 * 
	 * @return
	 */
	public String checkExcel() {
		HSSFWorkbook hssfworkbook = null; // 03��
		XSSFWorkbook wb = null; // 07��
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
								rcs = rcs + "��" + (j + 1) + "�пͻ����Ϊ��.</br>";
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell2 = hssfrow.getCell(i)
										.getRichStringCellValue().toString();
								custHelp.setCustName(cell2);
							} else {
								rcs = rcs + "��" + (j + 1) + "�пͻ�����Ϊ��.</br>";
							}
							int count = 0;
							if (null != custHelp.getCustNumber()) {
								count = batOrgUpdateService.getCustomerIsExit(
										custHelp.getCustNumber(),
										custHelp.getCustName());
							}
							if (count == 0) {
								rcs = rcs + "��" + (j + 1) + "�пͻ�ϵͳ������.</br>";
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
									rcs = rcs + "��" + (j + 1) + "����֯����ϵͳ������.</br>";
								}
								
							} else {
								rcs = rcs + "��" + (j + 1) + "����֯����Ϊ��.</br>";
							}
						} else if (type.equals("kunnr")) {
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell1 = hssfrow.getCell(i).toString();
								kunnrHelp.setKunnr(cell1);

							} else {
								rcs = rcs + "��" + (j + 1) + "�о����̱��Ϊ��.</br>";
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell2 = hssfrow.getCell(i).toString();
								kunnrHelp.setName1(cell2);
							} else {
								rcs = rcs + "��" + (j + 1) + "�о���������Ϊ��.</br>";
							}
							int count = 0;
							if (null != kunnrHelp.getKunnr()) {
								count = batOrgUpdateService.getKunnrIsExit(
										StringUtils.leftPad(kunnrHelp.getKunnr(),
												8, '0'), kunnrHelp.getName1());
							}
							if (count == 0) {
								rcs = rcs + "��" + (j + 1) + "�о�����ϵͳ������.</br>";
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
									rcs = rcs + "��" + (j + 1) + "����֯����ϵͳ������.</br>";
								}
								
							} else {
								rcs = rcs + "��" + (j + 1) + "����֯����Ϊ��.</br>";
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
							this.setSuccessMessage("����ɹ�����������Ϊ:" + custList.size()
									+ "��");
						}else if(type.equals("kunnr")){
							this.getSession().setAttribute("kunnrs", kunnrList);
							this.setSuccessMessage("����ɹ�����������Ϊ:" + kunnrList.size()
									+ "��");
						}
						
					} else {
						this.setSuccessMessage("����ʧ�ܣ�   ������Ϣ��"
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
								rcs = rcs + "��" + (j + 1) + "�пͻ����Ϊ��.</br>";
							}
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell2 = xssfrow.getCell(i).toString();
								custHelp.setCustName(cell2);
							} else {
								rcs = rcs + "��" + (j + 1) + "�пͻ�����Ϊ��.</br>";
							}
							int count = 0;
							if (null != custHelp.getCustNumber()) {
								count = batOrgUpdateService.getCustomerIsExit(
										custHelp.getCustNumber(),
										custHelp.getCustName());
							}
							if (count == 0) {
								rcs = rcs + "��" + (j + 1) + "�пͻ�ϵͳ������.</br>";
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
									rcs = rcs + "��" + (j + 1) + "����֯����ϵͳ������.</br>";
								}
								
							} else {
								rcs = rcs + "��" + (j + 1) + "����֯����Ϊ��.</br>";
							}
						} else if (type.equals("kunnr")) {
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell1 = xssfrow.getCell(i).toString();
								kunnrHelp.setKunnr(cell1);

							} else {
								rcs = rcs + "��" + (j + 1) + "�о����̱��Ϊ��.</br>";
							}
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell2 = xssfrow.getCell(i).toString();
								kunnrHelp.setName1(cell2);
							} else {
								rcs = rcs + "��" + (j + 1) + "�о���������Ϊ��.</br>";
							}
							int count = 0;
							if (null != kunnrHelp.getKunnr()) {
								count = batOrgUpdateService.getKunnrIsExit(
										StringUtils.leftPad(kunnrHelp.getKunnr(),
												8, '0'), kunnrHelp.getName1());
							}
							if (count == 0) {
								rcs = rcs + "��" + (j + 1) + "�о�����ϵͳ������.</br>";
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
									rcs = rcs + "��" + (j + 1) + "����֯����ϵͳ������.</br>";
								}
								
							} else {
								rcs = rcs + "��" + (j + 1) + "����֯����Ϊ��.</br>";
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
							this.setSuccessMessage("����ɹ�����������Ϊ:" + custList.size()
									+ "��");
						}else if(type.equals("kunnr")){
							this.getSession().setAttribute("kunnrs", kunnrList);
							this.setSuccessMessage("����ɹ�����������Ϊ:" + kunnrList.size()
									+ "��");
						}
						
					} else {
						this.setSuccessMessage("����ʧ�ܣ�   ������Ϣ��"
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else {// �ļ���ʽ
					this.setFailMessage("�ļ���ʽ����ȷ��");
				}
			} else {// �ļ�������
				this.setFailMessage("�ļ�������");
			}
		} catch (Exception e) {
			logger.error(e);
			if (out != null) {
				this.setFailMessage("�ϴ������ݿ��ܴ��ڸ�ʽת������,����ϵϵͳ����Ա!");
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
	 * �����̡��ͻ�������֯��������
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String importExcel() {
		HSSFWorkbook hssfworkbook = null; // 03��
		XSSFWorkbook wb = null; // 07��
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
								rcs = rcs + "��" + (j + 1) + "�пͻ����Ϊ��.</br>";
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell2 = hssfrow.getCell(i)
										.getRichStringCellValue().toString();
								custHelp.setCustName(cell2);
							} else {
								rcs = rcs + "��" + (j + 1) + "�пͻ�����Ϊ��.</br>";
							}
							int count = 0;
							if (null != custHelp.getCustNumber()) {
								count = batOrgUpdateService.getCustomerIsExit(
										custHelp.getCustNumber(),
										custHelp.getCustName());
							}
							if (count == 0) {
								rcs = rcs + "��" + (j + 1) + "�пͻ�ϵͳ������.</br>";
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
									rcs = rcs + "��" + (j + 1) + "����֯����ϵͳ������.</br>";
								}
								
							} else {
								rcs = rcs + "��" + (j + 1) + "����֯����Ϊ��.</br>";
							}
						} else if (type.equals("kunnr")) {
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell1 = hssfrow.getCell(i).toString();
								kunnrHelp.setKunnr(cell1);

							} else {
								rcs = rcs + "��" + (j + 1) + "�о����̱��Ϊ��.</br>";
							}
							i++;
							if (null != hssfrow.getCell(i)
									&& !"".equals(hssfrow.getCell(i))) {
								String cell2 = hssfrow.getCell(i).toString();
								kunnrHelp.setName1(cell2);
							} else {
								rcs = rcs + "��" + (j + 1) + "�о���������Ϊ��.</br>";
							}
							int count = 0;
							if (null != kunnrHelp.getKunnr()) {
								count = batOrgUpdateService.getKunnrIsExit(
										StringUtils.leftPad(kunnrHelp.getKunnr(),
												8, '0'), kunnrHelp.getName1());
							}
							if (count == 0) {
								rcs = rcs + "��" + (j + 1) + "�о�����ϵͳ������.</br>";
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
									rcs = rcs + "��" + (j + 1) + "����֯����ϵͳ������.</br>";
								}
								
							} else {
								rcs = rcs + "��" + (j + 1) + "����֯����Ϊ��.</br>";
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
							//�ͻ���֯��������
							//this.getSession().setAttribute("custs", custList);
							bResult=batOrgUpdateService.updateCustomerOrgs(custList);
							if(bResult.getResult()){
							this.setSuccessMessage("����ɹ�����������Ϊ:" + custList.size()
									+ "��");}
							else{
								this.setSuccessMessage("����ʧ��!����ϵ����Ա!");
							}
						}else if(type.equals("kunnr")){
							//��������֯��������
							//this.getSession().setAttribute("kunnrs", kunnrList);
							bResult=batOrgUpdateService.updateKunnrOrgs(kunnrList);
							if(bResult.getResult()){
							this.setSuccessMessage("����ɹ�����������Ϊ:" + kunnrList.size()
									+ "��");}
							else{
								this.setSuccessMessage("����ʧ��!����ϵ����Ա!");
							}
						}
						
					} else {
						this.setSuccessMessage("����ʧ�ܣ�   ������Ϣ��"
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
								rcs = rcs + "��" + (j + 1) + "�пͻ����Ϊ��.</br>";
							}
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell2 = xssfrow.getCell(i).toString();
								custHelp.setCustName(cell2);
							} else {
								rcs = rcs + "��" + (j + 1) + "�пͻ�����Ϊ��.</br>";
							}
							int count = 0;
							if (null != custHelp.getCustNumber()) {
								count = batOrgUpdateService.getCustomerIsExit(
										custHelp.getCustNumber(),
										custHelp.getCustName());
							}
							if (count == 0) {
								rcs = rcs + "��" + (j + 1) + "�пͻ�ϵͳ������.</br>";
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
									rcs = rcs + "��" + (j + 1) + "����֯����ϵͳ������.</br>";
								}
								
							} else {
								rcs = rcs + "��" + (j + 1) + "����֯����Ϊ��.</br>";
							}
						} else if (type.equals("kunnr")) {
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell1 = xssfrow.getCell(i).toString();
								kunnrHelp.setKunnr(cell1);

							} else {
								rcs = rcs + "��" + (j + 1) + "�о����̱��Ϊ��.</br>";
							}
							i++;
							if (null != xssfrow.getCell(i)
									&& !"".equals(xssfrow.getCell(i))) {
								String cell2 = xssfrow.getCell(i).toString();
								kunnrHelp.setName1(cell2);
							} else {
								rcs = rcs + "��" + (j + 1) + "�о���������Ϊ��.</br>";
							}
							int count = 0;
							if (null != kunnrHelp.getKunnr()) {
								count = batOrgUpdateService.getKunnrIsExit(
										StringUtils.leftPad(kunnrHelp.getKunnr(),
												8, '0'), kunnrHelp.getName1());
							}
							if (count == 0) {
								rcs = rcs + "��" + (j + 1) + "�о�����ϵͳ������.</br>";
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
									rcs = rcs + "��" + (j + 1) + "����֯����ϵͳ������.</br>";
								}
								
							} else {
								rcs = rcs + "��" + (j + 1) + "����֯����Ϊ��.</br>";
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
							//�ͻ���֯��������
							//this.getSession().setAttribute("custs", custList);
							bResult=batOrgUpdateService.updateCustomerOrgs(custList);
							if(bResult.getResult()){
							this.setSuccessMessage("����ɹ�����������Ϊ:" + custList.size()
									+ "��");}
							else{
								this.setSuccessMessage("����ʧ��!����ϵ����Ա!");
							}
						}else if(type.equals("kunnr")){
							//��������֯��������
							//this.getSession().setAttribute("kunnrs", kunnrList);
							bResult=batOrgUpdateService.updateKunnrOrgs(kunnrList);
							if(bResult.getResult()){
							this.setSuccessMessage("����ɹ�����������Ϊ:" + kunnrList.size()
									+ "��");}
							else{
								this.setSuccessMessage("����ʧ��!����ϵ����Ա!");
							}
						}
						
					} else {
						this.setSuccessMessage("����ʧ�ܣ�   ������Ϣ��"
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else {// �ļ���ʽ
					this.setFailMessage("�ļ���ʽ����ȷ��");
				}
			} else {// �ļ�������
				this.setFailMessage("�ļ�������");
			}
		} catch (Exception e) {
			logger.error(e);
			if (out != null) {
				this.setFailMessage("�ϴ������ݿ��ܴ��ڸ�ʽת������,����ϵϵͳ����Ա!");
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
