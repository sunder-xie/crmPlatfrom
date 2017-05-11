package com.kintiger.platform.customer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.channel.service.IChannelService;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.customer.service.IBatChangeService;
import com.kintiger.platform.customer.service.ICustomerService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.SuperCSV;

public class CustBatchModifyAction extends BaseAction {

	/**
	 * 
	 */

	private static final long serialVersionUID = 6759982143373659768L;
	private static final Log logger = LogFactory
			.getLog(CustBatchModifyAction.class);

	private IChannelService channelService;
	private IBatChangeService batChangeService;
	private List<Bchannel> channelList = new ArrayList<Bchannel>();;// �ͻ�����,����
	private List<Customer> custlist;
	@Decode
	private String userId;
	@Decode
	private String userName;
	private String uploadFileFileName;
	private String uploadFileName;
	private File uploadFile;
	private int total;
	private String type;
	private ICustomerService customerService;

	private File upload;

	@PermissionSearch
	public String toUpdateAll() {
		userId = this.getUser().getUserId();
		userName = this.getUser().getUserName();
		this.getSession().setAttribute("custlist", null);
		this.getSession().setAttribute("qcustlist", null);

		return "toUpdateAll";
	}

	@PermissionSearch
	@JsonResult(field = "custlist", include = { "custName", "custNumber",
			"remark", "custKunnr","custParent","custParentName" }, total = "total")
	public String searchCustlistJson() {
		custlist = (List<Customer>) this.getSession().getAttribute("custlist");
		if (custlist != null) {
			total = custlist.size();
		} else {
			total = 0;
		}
		return JSON;
	}

	@SuppressWarnings("unchecked")
	@PermissionSearch
	@JsonResult(field = "custlist", include = { "custName", "custNumber",
			"channelId", "remark" }, total = "total")
	public String searchqdCustlistJson() {
		custlist = (List<Customer>) this.getSession().getAttribute("qcustlist");
		if (custlist != null) {
			total = custlist.size();
		} else {
			total = 0;
		}
		return JSON;
	}

	/***
	 * 
	 * csv ����
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportToCSV() {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter print = null;
		try {
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("�ŵ������������޸�ģ��".getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("�ͻ����");
			linedata.append(",");
			linedata.append("�ͻ�����");
			linedata.append(",");
			linedata.append("������SAP���(�����'/����')");
			linedata.append(",");
			linedata.append("����������(�����'/����')");
			linedata.append(",");
			linedata.append("���׿ͻ����(�����'/����')");
			linedata.append(",");
			linedata.append("���׿ͻ�����(�����'/����')");
			linedata.append("\n");
			linedata.append("2196");
			linedata.append(",");
			linedata.append("����������ʳ����");
			linedata.append(",");
			linedata.append("03010001/03220001");
			linedata.append(",");
			linedata.append("������1/������2 ");
			linedata.append(",");
			linedata.append("2456/3654");
			linedata.append(",");
			linedata.append("���׿ͻ�1/���׿ͻ�2");
			linedata.append(",");
			linedata.append("����ʱ��ɾ������");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (print != null)
				print.close();
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * �����ͻ����������޸�ģ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportQdCSV() {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter print = null;
		try {
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("�ͻ����������޸�ģ��".getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("�ͻ����");
			linedata.append(",");
			linedata.append("�ͻ�����");
			linedata.append(",");
			linedata.append("����id");
			linedata.append(",");
			linedata.append("��������");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (print != null)
				print.close();
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * ��������������Ϣ
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportQdTypeCSV() {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter print = null;
		Bchannel channel = new Bchannel();
		channelList = channelService.getUnderChannel(channel);
		try {
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("����������Ϣ��".getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();// linedata ƴд�ַ�
			linedata.append("����id");
			linedata.append(",");
			linedata.append("��������");
			linedata.append("\n");
			if (null != channelList) {
				for (int x = 0; x < channelList.size(); x++) {
					Bchannel bc = new Bchannel();
					bc = channelList.get(x);
					linedata.append(bc.getChannelId().toString());
					linedata.append(",");
					String channame = "";
					if (bc.getChannelName() != null) {
						channame = bc.getChannelName().toString();
					}
					linedata.append(channame);
					linedata.append("\n");
				}
			}
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (print != null)
				print.close();
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * jxl��������csv ���쳣
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportqdCsv() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// ȡ�������
			os = response.getOutputStream();
			// ��������
			response.reset();
			// �趨����ļ�ͷ

			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("���οͻ������޸ı�".getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			// �����������

			response.setContentType("application/msexcel");
			// ����excel�ļ�
			wbook = Workbook.createWorkbook(os);

			WritableSheet wsheet = wbook.createSheet("sheet1", 0);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);

			// WritableSheet.setColumnView(int i,int width)
			// ������ָ����i+1�еĿ�ȣ����磺
			// ����һ�еĿ����Ϊ30
			// sheet.setColumnView(0,30)
			// wsheet.setRowView(0,10)
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.DARK_RED);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// ���õ�Ԫ�񱳾���ɫ
			cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// ���������ʽ
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// ���þ���
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// ���ñ��߿�
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			// ��ͷ
			Label label_0 = new Label(0, 0, "�ͻ����");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "�ͻ�����");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			wsheet.setColumnView(1, 20);

			Label label_2 = new Label(2, 0, "������SAP���");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);

			Label label_3 = new Label(3, 0, "����������");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			wsheet.setColumnView(3, 20);
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
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * 2007 ���οͻ�excelģ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportXlsx() {
		OutputStream os = null;
		XSSFWorkbook wbook = null;
		// ��ȡ��ѡʱ��ε�����
		// ��ȡ����ƴ���
		String report_name = "���οͻ������޸�ģ��";
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
			XSSFSheet rs = wbook.createSheet("sheet1");
			XSSFRow row1 = rs.createRow(0);
			// ��ͷ
			row1.createCell(0).setCellValue("�ͻ����");
			row1.createCell(1).setCellValue("�ͻ�����");
			row1.createCell(2).setCellValue("������SAP���");
			row1.createCell(3).setCellValue("����������");
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

	/**
	 * ���οͻ��޸�ģ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportModifyUpCustomer() {

		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// ȡ�������
			os = response.getOutputStream();
			// ��������
			response.reset();
			// �趨����ļ�ͷ
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("���οͻ������޸ı�".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			// �����������

			response.setContentType("application/msexcel");
			// ����excel�ļ�
			wbook = Workbook.createWorkbook(os);

			WritableSheet wsheet = wbook.createSheet("sheet1", 0);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);

			// WritableSheet.setColumnView(int i,int width)
			// ������ָ����i+1�еĿ�ȣ����磺
			// ����һ�еĿ����Ϊ30
			// sheet.setColumnView(0,30)
			// wsheet.setRowView(0,10)
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.DARK_RED);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// ���õ�Ԫ�񱳾���ɫ
			cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// ���������ʽ
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// ���þ���
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// ���ñ��߿�
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			// ��ͷ
			Label label_0 = new Label(0, 0, "�ͻ����");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "�ͻ�����");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			wsheet.setColumnView(1, 20);

			Label label_2 = new Label(2, 0, "������SAP���");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);

			Label label_3 = new Label(3, 0, "����������");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			wsheet.setColumnView(3, 20);
			Label label_4 = new Label(4, 0, "���׿ͻ�");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);
			wsheet.setColumnView(4, 20);
			Label label_5 = new Label(5, 0, "���׿ͻ�����");
			label_5.setCellFormat(cellFormat_top);
			wsheet.addCell(label_5);
			wsheet.setColumnView(5, 20);
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
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * �鿴�������οͻ�
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String importUp() {
		HSSFWorkbook hssfworkbook = null;
		XSSFWorkbook wb = null;
		StringBuffer result = new StringBuffer();
		PrintWriter out = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			custlist = new ArrayList<Customer>();
			response.setContentType("text/html; charset=GBK");
			out = response.getWriter();
			if (StringUtils.isNotEmpty(uploadFileName)) {
				String end = StringUtils.substring(uploadFileName,
						StringUtils.lastIndexOf(uploadFileName, '.'));
				if (end != null && (end.equals(".xls"))) {
					hssfworkbook = new HSSFWorkbook(new FileInputStream(
							new File(upload.toString())));
					HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);
					for (int j = 1; j < hssfsheet.getLastRowNum() + 1; j++) {
						HSSFRow hssfrow = hssfsheet.getRow(j);
						int rsColumns = hssfrow.getLastCellNum();
						String rcs = "";
						if (rsColumns == 4) {
							Customer co = new Customer();
							int i = 0;
							if (hssfrow.getCell(i) != null) {
								String cell1 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setCustNumber(cell1);
							} else {
								rcs = rcs + "�ͻ���������⣺��" + (j + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								String cell2 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setCustName(cell2);
							} else {
								rcs = rcs + "�ͻ����������⣺��" + (j + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								String cell3 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setCustKunnr(cell3);

							} else {
								rcs = rcs + "������SAP��������⣺��" + (j + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								String cell4 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setRemark(cell4);
							} else {
								rcs = rcs + "���������������⣺��" + (j + 1) + "��";
							}
							if (batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0) {

							} else {
								rcs = rcs + "�ͻ������ڣ���" + (j + 1) + "��";
							}
							if (batChangeService.getCustomerBySapId(
									co.getCustKunnr(), co.getRemark()) > 0) {

							} else {
								rcs = rcs + "�����̿ͻ������ڻ��߶��᣺��" + (j + 1) + "��";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs.toString() + "</br>");
								continue;
							}

						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}

					if (result.toString().equals("")) {
						this.getSession().setAttribute("custlist", custlist);
						this.setSuccessMessage("����ɹ�����������Ϊ:" + custlist.size()
								+ "��");
					} else {
						this.setSuccessMessage("����ʧ�ܣ�   ������Ϣ��"
								+ result.toString());
					}

					return RESULT_MESSAGE;
				} else if (end.equals(".xlsx")) {
					wb = new XSSFWorkbook(new FileInputStream(new File(
							upload.toString())));
					XSSFSheet rs = wb.getSheetAt(0);
					for (int j = 1; j < rs.getLastRowNum() + 1; j++) {
						XSSFRow row = rs.getRow(j);
						int rsColumns = row.getLastCellNum();
						Customer co = new Customer();
						if (rsColumns == 4) {
							int i = 0;
							if (row.getCell(i) != null) {
								co.setCustNumber(row.getCell(i++)
										.getRichStringCellValue().toString());
							}
							if (row.getCell(i) != null) {
								co.setCustName(row.getCell(i++)
										.getRichStringCellValue().toString());
							}
							if (row.getCell(i) != null) {
								co.setRemark(row.getCell(i++)
										.getRichStringCellValue().toString());
							}
							if (row.getCell(i) != null) {
								co.setCustKunnr(row.getCell(i++)
										.getRichStringCellValue().toString());
							}
							custlist.add(co);

						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}

					this.getSession().setAttribute("custlist", custlist);
					this.setSuccessMessage("����ɹ�����������Ϊ:" + (rs.getLastRowNum())
							+ "��");
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
				this.setFailMessage("����������");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * �鿴�������οͻ�
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String importCsvUp() {
		StringBuffer result = new StringBuffer();
		PrintWriter out = null;
		try {
			custlist = new ArrayList<Customer>();
			if (StringUtils.isNotEmpty(uploadFileName)) {
				String end = StringUtils.substring(uploadFileName,
						StringUtils.lastIndexOf(uploadFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(upload.toString())); // ��ȡString����
					for (int j = 0; j < content.size(); j++) {
						String[] s = content.get(j);
						int ij;
						String rcs = "";
						String[] i2s = null;
						String[] i3s = null;
						String[] i4s = null;
						String[] i5s = null;
						if (s.length == 6) {
							Customer co = new Customer();
							ij = 0;
							String i0 = s[ij++];
							String i1 = s[ij++];
							String i2 = s[ij++];
							String i3 = s[ij++];
							String i4 = s[ij++];
							String i5 = s[ij++];
							if (!"".equals(i0)&&null!=i0) {
								co.setCustNumber(i0);
							} else {
								rcs = rcs + "�ͻ���������⣺��" + (j + 2) + "��</br>";
							}
							if (!"".equals(i1)&&null!=i1) {
								co.setCustName(i1);
							} else {
								rcs = rcs + "�ͻ����������⣺��" + (j + 2) + "��</br>";
							}
							if (!"".equals(i2)&&null!=i2) {
								i2s = i2.split("/");
								// co.setCustKunnr(i2)
							} else {
								rcs = rcs + "�����̱�������⣺��" + (j + 2) + "��</br>";
							}
							if (!"".equals(i3)&&null!=i3) {
								i3s = i3.split("/");
								// co.setRemark(i3)
							} else {
								rcs = rcs + "���������������⣺��" + (j + 2) + "��</br>";
							}
							if (!(batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0)) {
								rcs = rcs + "�ͻ������ڣ���" + (j + 2) + "��</br>";
							}
							if ((i2s.length == i3s.length) && (i2s.length > 0)) {
								if (!lookUpType(i2s)) { // �ж��Ƿ����ظ�ֵ
									rcs = rcs + "�����̱������ͬ�������ڣ���" + (j + 2)
											+ "��</br>";
								} else {
									String i2c = "";
									String i3c = "";
									for (int cc = 0; cc < i2s.length; cc++) {
										String i2sNum = StringUtils.leftPad(
												i2s[cc].toString(), 8, '0');
										if (!(batChangeService
												.getCustomerBySapId(i2sNum,
														i3s[cc]) > 0)) {
											rcs = rcs + "�����̿ͻ������ڻ��߶��᣺��"
													+ (j + 2) + "��</br>";
										} else {
											if (cc == (i2s.length - 1)) {
												i2c += i2sNum;
												i3c += i3s[cc];
											} else {
												i2c += i2sNum + ", ";
												i3c += i3s[cc] + ",";
											}
										}
									}
									co.setCustKunnr(i2c);
									co.setRemark(i3c);

								}
							} else {
								rcs = rcs + "��������Ϣ��ʽ����ȷ����" + (j + 2) + "��";
							}
							if (!"".equals(i4)&&null!=i4&&null!=i5) {
								i4s = i4.split("/");
								i5s = i5.split("/");
								String str = "";
								String str1="";
								for (int i = 0; i < i4s.length; i++) {
									Customer ct = new Customer();
									ct.setCustNumber(i4s[i]);
									ct.setCustName(i5s[i]);
									if (!(batChangeService.getCustomerBy(
											ct.getCustNumber(),
											ct.getCustName()) > 0)) {
										rcs = rcs + "���׿ͻ�"+i4s[i]+"�����ڣ���" + (j + 2)
												+ "��</br>";
									} else {
										if ("".equals(str)) {
											str = i4s[i].trim();
										} else {
											str = str + ", " + i4s[i].trim();
										}
										if ("".equals(str1)) {
											str1 = i5s[i].trim();
										} else {
											str1 = str1 + "," + i5s[i].trim();
										}
										co.setCustParent(str);
										co.setCustParentName(str1);
									}
								}
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs.toString() + "</br>");
								continue;
							}

						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}
					if (result.toString().equals("")) {
						this.getSession().setAttribute("custlist", custlist);
						this.setSuccessMessage("����ɹ�����������Ϊ:" + custlist.size()
								+ "��");
					} else {
						this.setFailMessage("����ʧ�ܣ�  ������Ϣ��</br></br></br>"
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else {
					this.setFailMessage("�ⲻ��CSV�ļ�");
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

	@SuppressWarnings("deprecation")
	public String importCsvUpDb() {
		StringBuffer result = new StringBuffer();
		try {
			custlist = new ArrayList<Customer>();
			if (StringUtils.isNotEmpty(uploadFileName)) {
				String end = StringUtils.substring(uploadFileName,
						StringUtils.lastIndexOf(uploadFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(upload.toString())); // ��ȡString����
					for (int j = 0; j < content.size(); j++) {
						String[] s = content.get(j);
						int ij;
						String rcs = "";
						String[] i2s = null;
						String[] i3s = null;
						String[] i4s = null;
						String[] i5s = null;
						if (s.length == 6) {
							Customer co = new Customer();
							ij = 0;
							String i0 = s[ij++];
							String i1 = s[ij++];
							String i2 = s[ij++];
							String i3 = s[ij++];
							String i4 = s[ij++];
							String i5 = s[ij++];

							if (!"".equals(i0)) {
								co.setCustNumber(i0);
							} else {
								rcs = rcs + "�ͻ���������⣺��" + (j + 2) + "��</br>";
							}
							if (!"".equals(i1)) {
								co.setCustName(i1);
							} else {
								rcs = rcs + "�ͻ����������⣺��" + (j + 2) + "��</br>";
							}
							if (!"".equals(i2)) {
								i2s = i2.split("/");
								// co.setCustKunnr(i2);
							} else {
								rcs = rcs + "���ξ����̱�������⣺��" + (j + 2) + "��</br>";
							}
							if (!"".equals(i3)) {
								i3s = i3.split("/");
								// co.setRemark(i3);
							} else {
								rcs = rcs + "���ξ��������������⣺��" + (j + 2) + "��</br>";
							}
							if (!(batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0)) {
								rcs = rcs + "�ͻ������ڣ���" + (j + 2) + "��</br>";
							}
							if ((i2s.length == i3s.length) && (i2s.length > 0)) {
								if (!lookUpType(i2s)) { // �ж��Ƿ����ظ�ֵ
									rcs = rcs + "�����̱������ͬ�������ڣ���" + (j + 2)
											+ "��</br>";
								} else {
									String i2c = "";
									String i3c = "";
									for (int cc = 0; cc < i2s.length; cc++) {
										String i2sNum = StringUtils.leftPad(
												i2s[cc].toString(), 8, '0');
										if (!(batChangeService
												.getCustomerBySapId(i2sNum,
														i3s[cc]) > 0)) {
											rcs = rcs + "�����̿ͻ������ڻ��߶��᣺��"
													+ (j + 2) + "��</br>";
										} else {
											if (cc == (i2s.length - 1)) {
												i2c += i2sNum;
												i3c += i3s[cc];
											} else {
												i2c += i2sNum + ", ";
												i3c += i3s[cc] + ",";
											}
										}
									}
									co.setCustKunnr(i2c);
									co.setRemark(i3c);

								}
							} else {
								rcs = rcs + "��������Ϣ��ʽ����ȷ����" + (j + 2) + "��";
							}
							if (!"".equals(i4)&&null!=i4&&null!=i5) {
								i4s = i4.split("/");
								i5s = i5.split("/");
								String str = "";
								String str1="";
								for (int i = 0; i < i4s.length; i++) {
									Customer ct = new Customer();
									ct.setCustNumber(i4s[i]);
									ct.setCustName(i5s[i]);
									if (!(batChangeService.getCustomerBy(
											ct.getCustNumber(),
											ct.getCustName()) > 0)) {
										rcs = rcs + "���׿ͻ������ڣ���" + (j + 2)
												+ "��</br>";
									}
									// int n =
									// customerService.getTwoLevelCustomerCount(ct);
									/*
									 * if (0 == n) { rcs = rcs + "��" + (j + 2) +
									 * "���οͻ�" + i4s[i] + "������.</br>"; }
									 */else {
										if ("".equals(str)) {
											str = i4s[i].trim();
										} else {
											str = str + ", " + i4s[i].trim();
										}
										if ("".equals(str1)) {
											str1 = i5s[i].trim();
										} else {
											str1 = str1 + "," + i5s[i].trim();
										}
										co.setCustParent(str);
										co.setCustParentName(str1);
									}
								}
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs.toString() + "</br>");
								continue;
							}

						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}
					if (result.toString().equals("")) {
						String rcm = "";
						for (Customer cm : custlist) {
							if (!(batChangeService.updateByCustprentId(cm) > 0)) {
								rcm = rcm + cm.getCustNumber() + "�޸�ʧ�� </br>";
							}
						}
						if (!rcm.equals("")) {
							this.setFailMessage("����ʧ�ܣ�  ������Ϣ��</br></br></br>"
									+ rcm.toString());
						} else {
							this.getSession()
									.setAttribute("custlist", custlist);
							this.setSuccessMessage("����ɹ�����������Ϊ:"
									+ custlist.size() + "��");
						}
					} else {
						this.setFailMessage("����ʧ�ܣ�  ������Ϣ��</br></br></br>"
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

	/***
	 * 
	 * �������οͻ������ݿ�
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String importUpDb() {
		// InputStream in=null;
		HSSFWorkbook hssfworkbook = null;
		XSSFWorkbook wb = null;
		//
		StringBuffer result = new StringBuffer();
		PrintWriter out = null;
		// String resultMsg=null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			custlist = new ArrayList<Customer>();
			response.setContentType("text/html; charset=GBK");
			out = response.getWriter();
			if (StringUtils.isNotEmpty(uploadFileName)) {
				String end = StringUtils.substring(uploadFileName,
						StringUtils.lastIndexOf(uploadFileName, '.'));
				if (end != null && (end.equals(".xls"))) {
					hssfworkbook = new HSSFWorkbook(new FileInputStream(
							new File(upload.toString())));
					HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);
					for (int j = 1; j < hssfsheet.getLastRowNum() + 1; j++) {
						HSSFRow hssfrow = hssfsheet.getRow(j);
						int rsColumns = hssfrow.getLastCellNum();
						String rcs = "";
						if (rsColumns == 4) {
							Customer co = new Customer();
							int i = 0;
							if (hssfrow.getCell(i) != null) {
								String cell1 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setCustNumber(cell1);
							} else {
								rcs = rcs + "�ͻ���������⣺��" + (j + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								String cell2 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setCustName(cell2);
							} else {
								rcs = rcs + "�ͻ����������⣺��" + (j + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								String cell3 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setCustKunnr(cell3);

							} else {
								rcs = rcs + "������SAP��������⣺��" + (j + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								String cell4 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setRemark(cell4);
							} else {
								rcs = rcs + "���������������⣺��" + (j + 1) + "��";
							}
							if (batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0) {

							} else {
								rcs = rcs + "�ͻ������ڣ���" + (j + 1) + "��";
							}
							if (batChangeService.getCustomerBySapId(
									co.getCustKunnr(), co.getRemark()) > 0) {

							} else {
								rcs = rcs + "�����̿ͻ������ڻ��߶��᣺��" + (j + 1) + "��";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs.toString() + "</br>");
								continue;
							}

						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("custlist", custlist);
					String rcm = "";
					for (Customer cm : custlist) {
						if (!(batChangeService.updateByCustprentId(cm) > 0)) {
							rcm = rcm + cm.getCustNumber() + "�޸�ʧ�� </br>";
						}
					}
					if (!rcm.equals("")) {
						result.append(rcm);
					}
					if (result.toString().equals("")) {
						this.setSuccessMessage("����ɹ�����������Ϊ:" + custlist.size()
								+ "��");
					} else {
						this.setSuccessMessage("����ɹ�����������Ϊ:" + custlist.size()
								+ "�� </br>  ������Ϣ��" + result.toString());
					}

					return RESULT_MESSAGE;
				} else if (end.equals(".xlsx")) {
					wb = new XSSFWorkbook(new FileInputStream(new File(
							upload.toString())));
					XSSFSheet rs = wb.getSheetAt(0);
					for (int j = 1; j < rs.getLastRowNum() + 1; j++) {
						XSSFRow row = rs.getRow(j);
						int rsColumns = row.getLastCellNum();
						Customer co = new Customer();
						if (rsColumns == 4) {
							int i = 0;
							if (row.getCell(i) != null) {
								co.setCustNumber(row.getCell(i++)
										.getRichStringCellValue().toString());
							}
							if (row.getCell(i) != null) {
								co.setCustName(row.getCell(i++)
										.getRichStringCellValue().toString());
							}
							if (row.getCell(i) != null) {
								co.setRemark(row.getCell(i++)
										.getRichStringCellValue().toString());
							}
							if (row.getCell(i) != null) {
								co.setCustKunnr(row.getCell(i++)
										.getRichStringCellValue().toString());
							}
							custlist.add(co);

						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}

					String rcm = "";
					for (Customer cm : custlist) {
						if (batChangeService.updateByCustprentId(cm) > 0) {

						} else {
							rcm = rcm + cm.getCustNumber() + "�޸�ʧ��";
						}
					}
					if (!rcm.equals("")) {
						result.append(rcm);
					}
					this.getSession().setAttribute("custlist", custlist);
					if (result.toString().equals("")) {
						this.setSuccessMessage("����ͻ��ɹ�����������Ϊ:"
								+ custlist.size() + "��");
					} else {
						this.setSuccessMessage("����ͻ��ɹ�����������Ϊ:"
								+ custlist.size() + "�� </br>  ������Ϣ��"
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
				this.setFailMessage("����������");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * �鿴��������
	 * 
	 * @return
	 */

	public String importCsvQd() {
		StringBuilder result = new StringBuilder();
		Bchannel channel = new Bchannel();
		channelList = channelService.getUnderChannel(channel);
		System.out.println("importCsvQd() channelList.size(): "
				+ channelList.size());
		try {

			HttpServletResponse response = ServletActionContext.getResponse();

			custlist = new ArrayList<Customer>();

			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // ��ȡString����
					for (int j = 0; j < content.size(); j++) {
						String[] s = content.get(j);
						int ij;
						String rcs = "";
						if (s.length == 4) {
							Customer co = new Customer();
							ij = 0;
							String i0 = s[ij++];
							String i1 = s[ij++];
							String i2 = s[ij++];
							String i3 = s[ij++];

							if (!i0.equals("")) {
								co.setCustNumber(i0);
							} else {
								rcs = rcs + "�ͻ���������⣺��" + (j + 2) + "��";
							}
							if (!i1.equals("")) {
								co.setCustName(i1);
							} else {
								rcs = rcs + "�ͻ����������⣺��" + (j + 2) + "��";
							}
							System.out.println("i2: " + i2);
							if (!i2.equals("")) {

								int intcel = Integer.parseInt(i2);
								System.out.println("intcel: " + intcel);
								String res = "";
								for (Bchannel bh : channelList) {
									if (bh.getChannelId() == intcel) {
										res = i2;
										co.setChannelId(intcel);
										break;
									}
								}
								if (res.equals("")) {
									rcs = rcs + "����ID������ײ���������" + (j + 2) + "��";
								}
							} else {
								rcs = rcs + "����ID�����⣺��" + (j + 2) + "��";
							}
							if (!i3.equals("")) {
								co.setRemark(i3);
							} else {
								rcs = rcs + "�������������⣺��" + (j + 2) + "��";
							}
							if (!(batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0)) {
								rcs = rcs + "�ͻ������ڣ���" + (j + 2) + "��";
							}

							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}
					if (result.toString().equals("")) {
						this.getSession().setAttribute("qcustlist", custlist);
						this.setSuccessMessage("���������ɹ�����������Ϊ:"
								+ custlist.size() + "��");
					} else {
						this.setSuccessMessage("��������ʧ�ܣ�</br> ������������У�"
								+ result.toString());
					}

					return RESULT_MESSAGE;
				} else {
					this.setFailMessage("�ⲻ��CSV�ļ�");
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

	/***
	 * 
	 * ����������Ϣ�����ݿ�
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String importCsvQdDb() {
		StringBuilder result = new StringBuilder();
		Bchannel channel = new Bchannel();
		channelList = channelService.getUnderChannel(channel);
		System.out.println("����������Ϣ�����ݿ�channelList.size(): " + channelList);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();

			custlist = new ArrayList<Customer>();

			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // ��ȡString����
					for (int j = 0; j < content.size(); j++) {
						String[] s = content.get(j);
						int ij;
						String rcs = "";
						if (s.length == 4) {
							Customer co = new Customer();
							ij = 0;
							String i0 = s[ij++];
							String i1 = s[ij++];
							String i2 = s[ij++];
							String i3 = s[ij++];

							if (!i0.equals("")) {
								co.setCustNumber(i0);
							} else {
								rcs = rcs + "�ͻ���������⣺��" + (j + 2) + "��";
							}
							if (!i1.equals("")) {
								co.setCustName(i1);
							} else {
								rcs = rcs + "�ͻ����������⣺��" + (j + 2) + "��";
							}
							System.out.println("i2: " + i2);
							if (!i2.equals("")) {

								int intcel = Integer.parseInt(i2);
								System.out.println("intcel: " + intcel);
								String res = "";

								for (Bchannel bh : channelList) {
									if (bh.getChannelId() == intcel) {
										res = i2;
										co.setChannelId(intcel);
									}
								}
								if (res.equals("")) {
									rcs = rcs + "����ID������ײ���������" + (j + 2) + "��";
								}
								else{
									String str="Z";
									Bchannel bc = new Bchannel();
									bc.setChannelId(Long.valueOf(i2));
									bc.setType("һ��");   //�ն�
									Bchannel channel1 = new Bchannel();
									channel1 = channelService.getChannelById(bc);
									Bchannel bc1 = new Bchannel();
									bc1.setChannelId(Long.valueOf(i2));
									bc1.setType("����");   //�ն�
									Bchannel channel2 = new Bchannel();
									channel2 = channelService.getChannelById(bc1);
									if(null!=channel1){
										str="Z";
									}
									if(null!=channel2){
										str="T";
									}
									co.setCustType(str);
									
								}
							} else {
								rcs = rcs + "����ID�����⣺��" + (j + 2) + "��";
							}
							if (!i3.equals("")) {
								co.setRemark(i3);
							} else {
								rcs = rcs + "�������������⣺��" + (j + 2) + "��";
							}
							if (!(batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0)) {
								rcs = rcs + "�ͻ������ڣ���" + (j + 2) + "��";
							}

							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}
					if (result.toString().equals("")) {
						this.getSession().setAttribute("qcustlist", custlist);
						String rcm = "";
						for (Customer cm : custlist) {
							if (!(batChangeService.updateChannelIdById(cm) > 0)) {
								rcm = rcm + cm.getCustNumber() + "�޸�ʧ��</br>";
							}
						}
						if (!rcm.equals("")) {
							this.setFailMessage("��������ʧ�ܣ�</br> ������������У�"
									+ rcm.toString());
							return RESULT_MESSAGE;
						}
						this.getSession().setAttribute("qcustlist", custlist);
						this.setSuccessMessage("���������ɹ�����������Ϊ:"
								+ custlist.size() + "��");
					} else {
						this.setFailMessage("��������ʧ�ܣ�</br> ������������У�"
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

	/***
	 * 
	 * �鿴��������
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String importQd() {
		// InputStream in=null;
		HSSFWorkbook hssfworkbook = null;
		XSSFWorkbook wb = null;
		//
		StringBuilder result = new StringBuilder();
		PrintWriter out = null;
		// String resultMsg=null;
		Bchannel channel = new Bchannel();
		channelList = channelService.getUnderChannel(channel);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();

			response.setContentType("text/html; charset=GBK");
			out = response.getWriter();
			custlist = new ArrayList<Customer>();

			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if (end != null && (end.equals(".xls"))) {
					hssfworkbook = new HSSFWorkbook(new FileInputStream(
							new File(uploadFile.toString())));
					HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);
					for (int j = 1; j < hssfsheet.getLastRowNum() + 1; j++) {
						String rcs = "";
						HSSFRow hssfrow = hssfsheet.getRow(j);
						int rsColumns = hssfrow.getLastCellNum();
						if (rsColumns == 4) {
							Customer co = new Customer();

							int i = 0;
							if (hssfrow.getCell(i) != null) {
								co.setCustNumber(hssfrow.getCell(i)
										.getRichStringCellValue().toString());
								i++;
							} else {
								rcs = rcs + "��������⣺��" + (j + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								co.setCustName(hssfrow.getCell(i)
										.getRichStringCellValue().toString());
								i++;
							} else {
								rcs = rcs + "���������⣺��" + (i + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								String cel = hssfrow.getCell(i).toString();
								int intcel = Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(Double.valueOf(cel)));
								co.setChannelId(intcel);
								String res = "";
								for (Bchannel bh : channelList) {
									if (bh.getChannelId() == intcel) {
										res = cel;
									}
								}
								i++;
								if (res.equals("")) {
									rcs = rcs + "����ID������ײ���������" + (j + 1) + "��";
								}
							} else {
								rcs = rcs + "����ID�����⣺��" + (j + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								String cel2 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setRemark(cel2);
							} else {
								rcs = rcs + "�������������⣺��" + (j + 1) + "��";
							}
							if (batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0) {
							} else {
								rcs = rcs + "�ͻ������ڣ���" + (j + 1) + "��";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("qcustlist", custlist);
					if (result.toString().equals("")) {
						this.setSuccessMessage("���������ɹ�����������Ϊ:"
								+ custlist.size() + "��");
					} else {
						this.setSuccessMessage("���������ɹ�����������Ϊ:"
								+ custlist.size() + "��    </br> ������������У�"
								+ result.toString());
					}

					return RESULT_MESSAGE;
				} else if (end.equals(".xlsx")) {
					wb = new XSSFWorkbook(new FileInputStream(new File(
							uploadFile.toString())));
					XSSFSheet rs = wb.getSheetAt(0);
					String rcs = "";
					for (int k = 1; k < rs.getLastRowNum() + 1; k++) {
						XSSFRow row = rs.getRow(k);
						int rsColumns = row.getLastCellNum();
						Customer co = new Customer();
						if (rsColumns == 4) {
							int f = 0;
							if (row.getCell(f) != null) {
								co.setCustNumber(row.getCell(f)
										.getRichStringCellValue().toString());
								f++;
							} else {
								rcs = rcs + "��������⣺��" + (k + 1) + "��";
							}
							if (row.getCell(f) != null) {
								co.setCustName(row.getCell(f)
										.getRichStringCellValue().toString());
								f++;
							} else {
								rcs = rcs + "���������⣺��" + (k + 1) + "��";
							}
							if (row.getCell(f) != null) {
								String cel = row.getCell(f).toString();
								int intcel = Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(Double.valueOf(cel)));
								co.setChannelId(intcel);
								String res = "";
								f++;
								for (Bchannel bh : channelList) {
									if (bh.getChannelId() == intcel) {
										res = cel;
										break;
									}
								}
								if (res.equals("")) {
									rcs = rcs + "����ID������ײ���������" + (k + 1) + "��";
								}

							} else {
								rcs = rcs + "����id�����⣺��" + (k + 1) + "��";
							}
							if (row.getCell(f) != null) {
								co.setCustKunnr(row.getCell(f++)
										.getRichStringCellValue().toString());
							} else {
								rcs = rcs + "�������������⣺��" + (k + 1) + "��";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("qcustlist", custlist);

					if (result.toString().equals("")) {
						this.setSuccessMessage("���������ɹ�����������Ϊ:"
								+ custlist.size() + "��");
					} else {
						this.setSuccessMessage("���������ɹ�����������Ϊ:"
								+ custlist.size() + "��    </br> ������������У�"
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else {// �ļ���ʽ
					this.setFailMessage("�ļ���ʽ����ȷ��");
					return RESULT_MESSAGE;
				}
			} else {// �ļ�������
				this.setFailMessage("�ļ�������");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
			if (out != null) {
				this.setFailMessage("����������");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * �������������ݿ�
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String importQdDb() {
		HSSFWorkbook hssfworkbook = null;
		XSSFWorkbook wb = null;
		StringBuilder result = new StringBuilder();
		PrintWriter out = null;
		Bchannel channel = new Bchannel();
		channelList = channelService.getUnderChannel(channel);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();

			response.setContentType("text/html; charset=GBK");
			out = response.getWriter();
			custlist = new ArrayList<Customer>();

			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if (end != null && (end.equals(".xls"))) {
					hssfworkbook = new HSSFWorkbook(new FileInputStream(
							new File(uploadFile.toString())));
					HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);
					for (int j = 1; j < hssfsheet.getLastRowNum() + 1; j++) {
						String rcs = "";
						HSSFRow hssfrow = hssfsheet.getRow(j);
						int rsColumns = hssfrow.getLastCellNum();
						if (rsColumns == 4) {
							Customer co = new Customer();

							int i = 0;
							if (hssfrow.getCell(i) != null) {
								co.setCustNumber(hssfrow.getCell(i)
										.getRichStringCellValue().toString());
								i++;
							} else {
								rcs = rcs + "��������⣺��" + (j + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								co.setCustName(hssfrow.getCell(i)
										.getRichStringCellValue().toString());
								i++;
							} else {
								rcs = rcs + "���������⣺��" + (i + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								String cel = hssfrow.getCell(i).toString();
								int intcel = Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(Double.valueOf(cel)));
								co.setChannelId(intcel);
								String res = "";
								for (Bchannel bh : channelList) {
									if (bh.getChannelId() == intcel) {
										res = cel;
										break;
									}
								}
								i++;
								if (res.equals("")) {
									rcs = rcs + "����ID������ײ���������" + (j + 1) + "��";
								}
							} else {
								rcs = rcs + "����ID�����⣺��" + (j + 1) + "��";
							}
							if (hssfrow.getCell(i) != null) {
								String cel2 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setRemark(cel2);
							} else {
								rcs = rcs + "�������������⣺��" + (j + 1) + "��";
							}
							if (batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0) {
							} else {
								rcs = rcs + "�ͻ������ڣ���" + (j + 1) + "��";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("qcustlist", custlist);
					String rcm = "";
					for (Customer cm : custlist) {
						if (!(batChangeService.updateChannelIdById(cm) > 0)) {
							rcm = rcm + cm.getCustNumber() + "�޸�ʧ��</br>";
						}
					}
					if (!rcm.equals("")) {
						result.append(rcm);
					}
					if (result.toString().equals("")) {
						this.setSuccessMessage("���������ɹ�����������Ϊ:"
								+ custlist.size() + "��");
					} else {
						this.setSuccessMessage("���������ɹ�����������Ϊ:"
								+ custlist.size() + "��    </br> ������������У�"
								+ result.toString());
					}

					return RESULT_MESSAGE;
				} else if (end.equals(".xlsx")) {
					wb = new XSSFWorkbook(new FileInputStream(new File(
							uploadFile.toString())));
					XSSFSheet rs = wb.getSheetAt(0);
					String rcs = "";
					for (int k = 1; k < rs.getLastRowNum() + 1; k++) {
						XSSFRow row = rs.getRow(k);
						int rsColumns = row.getLastCellNum();
						Customer co = new Customer();
						if (rsColumns == 4) {
							int f = 0;
							if (row.getCell(f) != null) {
								co.setCustNumber(row.getCell(f)
										.getRichStringCellValue().toString());
								f++;
							} else {
								rcs = rcs + "��������⣺��" + (k + 1) + "��";
							}
							if (row.getCell(f) != null) {
								co.setCustName(row.getCell(f)
										.getRichStringCellValue().toString());
								f++;
							} else {
								rcs = rcs + "���������⣺��" + (k + 1) + "��";
							}
							if (row.getCell(f) != null) {
								String cel = row.getCell(f).toString();
								int intcel = Integer
										.parseInt(new java.text.DecimalFormat(
												"0").format(Double.valueOf(cel)));
								co.setChannelId(intcel);
								String res = "";
								f++;
								for (Bchannel bh : channelList) {
									if (bh.getChannelId() == intcel) {
										res = cel;
										break;
									}
								}
								if (res.equals("")) {
									rcs = rcs + "����ID������ײ���������" + (k + 1) + "��";
								}

							} else {
								rcs = rcs + "����id�����⣺��" + (k + 1) + "��";
							}
							if (row.getCell(f) != null) {
								co.setCustKunnr(row.getCell(f++)
										.getRichStringCellValue().toString());
							} else {
								rcs = rcs + "�������������⣺��" + (k + 1) + "��";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("����������");
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("qcustlist", custlist);
					String rcm = "";
					for (Customer cm : custlist) {
						if (!(batChangeService.updateByCustprentId(cm) > 0)) {
							rcm = rcm + cm.getCustNumber() + "�޸�ʧ��</br>";
						}
					}
					if (!rcm.equals("")) {
						result.append(rcm);
					}
					if (result.toString().equals("")) {
						this.setSuccessMessage("���������ɹ�����������Ϊ:"
								+ custlist.size() + "��");
					} else {
						this.setSuccessMessage("���������ɹ�����������Ϊ:"
								+ custlist.size() + "��    </br> ������������У�"
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else {// �ļ���ʽ
					this.setFailMessage("�ļ���ʽ����ȷ��");
					return RESULT_MESSAGE;
				}
			} else {// �ļ�������
				this.setFailMessage("�ļ�������");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
			if (out != null) {
				this.setFailMessage("����������");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * �ͻ������޸�ģ��
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportModifyCustomer() {
		Bchannel channel = new Bchannel();
		channelList = channelService.getUnderChannel(channel);

		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// ȡ�������
			os = response.getOutputStream();
			// ��������
			response.reset();
			// �趨����ļ�ͷ

			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("�ͻ����������޸ı�".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			// �����������
			response.setContentType("application/msexcel");
			// ����excel�ļ�
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("����ģ��", 0);
			WritableSheet wsheet1 = wbook.createSheet("������Ϣ", 1);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);

			// WritableSheet.setColumnView(int i,int width);
			// ������ָ����i+1�еĿ�ȣ����磺
			// ����һ�еĿ����Ϊ30
			// sheet.setColumnView(0,30);
			// wsheet.setRowView(0,10);
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.DARK_RED);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// ���õ�Ԫ�񱳾���ɫ
			cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// ���������ʽ
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// ���þ���
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// ���ñ��߿�
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			// ��ͷ
			Label label_0 = new Label(0, 0, "�ͻ����");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "�ͻ�����");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			wsheet.setColumnView(1, 20);

			Label label_2 = new Label(2, 0, "����id");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);
			wsheet.setColumnView(2, 20);
			Label label_3 = new Label(3, 0, "��������");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			wsheet.setColumnView(3, 20);

			// ��ͷ
			Label label_01 = new Label(0, 0, "����id");
			label_01.setCellFormat(cellFormat_top);
			wsheet1.addCell(label_01);

			Label label_11 = new Label(1, 0, "��������");
			label_11.setCellFormat(cellFormat_top);
			wsheet1.addCell(label_11);
			wsheet1.setColumnView(1, 20);
			if (null != channelList) {
				for (int x = 0; x < channelList.size(); x++) {
					Bchannel bc = new Bchannel();
					bc = channelList.get(x);
					// �������
					// Label label_x_0 = new Label(0, x + 1,
					// bc.getChannelId().toString());
					Label label_x_0 = new Label(0, x + 1, bc.getChannelId()
							.toString(), cellFormat_top);
					label_x_0.setCellFormat(cellFormat_bottom_1);
					wsheet1.addCell(label_x_0);
					wsheet1.setColumnView(0, 20);
					Label label_x_1 = new Label(1, x + 1, bc.getChannelName());
					label_x_1.setCellFormat(cellFormat_bottom);
					wsheet1.addCell(label_x_1);
					wsheet1.setColumnView(1, 20);
				}
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
		return RESULT_MESSAGE;
	}

	public IChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}

	public List<Bchannel> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<Bchannel> channelList) {
		this.channelList = channelList;
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
		return custlist;
	}

	public void setCustlist(List<Customer> custlist) {
		this.custlist = custlist;
	}

	public IBatChangeService getBatChangeService() {
		return batChangeService;
	}

	public void setBatChangeService(IBatChangeService batChangeService) {
		this.batChangeService = batChangeService;
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

	public ICustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

}
