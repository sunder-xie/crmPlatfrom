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
	private List<Bchannel> channelList = new ArrayList<Bchannel>();;// 客户分类,渠道
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
	 * csv 导出
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
					+ new String("门店所属经销商修改模版".getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("客户编号");
			linedata.append(",");
			linedata.append("客户名称");
			linedata.append(",");
			linedata.append("经销商SAP编号(多个用'/隔开')");
			linedata.append(",");
			linedata.append("经销商名称(多个用'/隔开')");
			linedata.append(",");
			linedata.append("二阶客户编号(多个用'/隔开')");
			linedata.append(",");
			linedata.append("二阶客户名称(多个用'/隔开')");
			linedata.append("\n");
			linedata.append("2196");
			linedata.append(",");
			linedata.append("高桥李兰副食批发");
			linedata.append(",");
			linedata.append("03010001/03220001");
			linedata.append(",");
			linedata.append("经销商1/经销商2 ");
			linedata.append(",");
			linedata.append("2456/3654");
			linedata.append(",");
			linedata.append("二阶客户1/二阶客户2");
			linedata.append(",");
			linedata.append("导入时请删除此行");
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
	 * 导出客户渠道批量修改模版
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
					+ new String("客户渠道批量修改模版".getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("客户编号");
			linedata.append(",");
			linedata.append("客户名称");
			linedata.append(",");
			linedata.append("渠道id");
			linedata.append(",");
			linedata.append("渠道名称");
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
	 * 导出渠道基础信息
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
					+ new String("渠道基础信息表".getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();// linedata 拼写字符
			linedata.append("渠道id");
			linedata.append(",");
			linedata.append("渠道名称");
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
	 * jxl导出但是csv 有异常
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportqdCsv() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头

			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("上游客户批量修改表".getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			// 定义输出类型

			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);

			WritableSheet wsheet = wbook.createSheet("sheet1", 0);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);

			// WritableSheet.setColumnView(int i,int width)
			// 作用是指定第i+1列的宽度，比如：
			// 将第一列的宽度设为30
			// sheet.setColumnView(0,30)
			// wsheet.setRowView(0,10)
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.DARK_RED);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置单元格背景颜色
			cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// 设置字体格式
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// 设置居中
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置表格边框
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			// 表头
			Label label_0 = new Label(0, 0, "客户编号");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "客户名称");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			wsheet.setColumnView(1, 20);

			Label label_2 = new Label(2, 0, "经销商SAP编号");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);

			Label label_3 = new Label(3, 0, "经销商名称");
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
	 * 2007 上游客户excel模版
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportXlsx() {
		OutputStream os = null;
		XSSFWorkbook wbook = null;
		// 获取所选时间段的日期
		// 获取数据拼表格
		String report_name = "上游客户批量修改模板";
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
			XSSFSheet rs = wbook.createSheet("sheet1");
			XSSFRow row1 = rs.createRow(0);
			// 表头
			row1.createCell(0).setCellValue("客户编号");
			row1.createCell(1).setCellValue("客户名称");
			row1.createCell(2).setCellValue("经销商SAP编号");
			row1.createCell(3).setCellValue("经销商名称");
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
	 * 上游客户修改模版
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportModifyUpCustomer() {

		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("上游客户批量修改表".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			// 定义输出类型

			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);

			WritableSheet wsheet = wbook.createSheet("sheet1", 0);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);

			// WritableSheet.setColumnView(int i,int width)
			// 作用是指定第i+1列的宽度，比如：
			// 将第一列的宽度设为30
			// sheet.setColumnView(0,30)
			// wsheet.setRowView(0,10)
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.DARK_RED);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置单元格背景颜色
			cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// 设置字体格式
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// 设置居中
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置表格边框
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			// 表头
			Label label_0 = new Label(0, 0, "客户编号");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "客户名称");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			wsheet.setColumnView(1, 20);

			Label label_2 = new Label(2, 0, "经销商SAP编号");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);

			Label label_3 = new Label(3, 0, "经销商名称");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			wsheet.setColumnView(3, 20);
			Label label_4 = new Label(4, 0, "二阶客户");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);
			wsheet.setColumnView(4, 20);
			Label label_5 = new Label(5, 0, "二阶客户名称");
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
	 * 查看导入上游客户
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
								rcs = rcs + "客户编号有问题：第" + (j + 1) + "行";
							}
							if (hssfrow.getCell(i) != null) {
								String cell2 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setCustName(cell2);
							} else {
								rcs = rcs + "客户名称有问题：第" + (j + 1) + "行";
							}
							if (hssfrow.getCell(i) != null) {
								String cell3 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setCustKunnr(cell3);

							} else {
								rcs = rcs + "经销商SAP编号有问题：第" + (j + 1) + "行";
							}
							if (hssfrow.getCell(i) != null) {
								String cell4 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setRemark(cell4);
							} else {
								rcs = rcs + "经销商名称有问题：第" + (j + 1) + "行";
							}
							if (batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0) {

							} else {
								rcs = rcs + "客户不存在：第" + (j + 1) + "行";
							}
							if (batChangeService.getCustomerBySapId(
									co.getCustKunnr(), co.getRemark()) > 0) {

							} else {
								rcs = rcs + "经销商客户不存在或者冻结：第" + (j + 1) + "行";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs.toString() + "</br>");
								continue;
							}

						} else {
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}

					if (result.toString().equals("")) {
						this.getSession().setAttribute("custlist", custlist);
						this.setSuccessMessage("导入成功！导入数量为:" + custlist.size()
								+ "行");
					} else {
						this.setSuccessMessage("导入失败！   错误信息："
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
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}

					this.getSession().setAttribute("custlist", custlist);
					this.setSuccessMessage("导入成功！导入数量为:" + (rs.getLastRowNum())
							+ "行");
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
				this.setFailMessage("数据有问题");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * 查看导入上游客户
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
							.getContentFromFile(new File(upload.toString())); // 获取String数组
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
								rcs = rcs + "客户编号有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i1)&&null!=i1) {
								co.setCustName(i1);
							} else {
								rcs = rcs + "客户名称有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i2)&&null!=i2) {
								i2s = i2.split("/");
								// co.setCustKunnr(i2)
							} else {
								rcs = rcs + "经销商编号有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i3)&&null!=i3) {
								i3s = i3.split("/");
								// co.setRemark(i3)
							} else {
								rcs = rcs + "经销商名称有问题：第" + (j + 2) + "行</br>";
							}
							if (!(batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0)) {
								rcs = rcs + "客户不存在：第" + (j + 2) + "行</br>";
							}
							if ((i2s.length == i3s.length) && (i2s.length > 0)) {
								if (!lookUpType(i2s)) { // 判断是否有重复值
									rcs = rcs + "经销商编号有相同的数据在：第" + (j + 2)
											+ "行</br>";
								} else {
									String i2c = "";
									String i3c = "";
									for (int cc = 0; cc < i2s.length; cc++) {
										String i2sNum = StringUtils.leftPad(
												i2s[cc].toString(), 8, '0');
										if (!(batChangeService
												.getCustomerBySapId(i2sNum,
														i3s[cc]) > 0)) {
											rcs = rcs + "经销商客户不存在或者冻结：第"
													+ (j + 2) + "行</br>";
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
								rcs = rcs + "经销商信息格式不正确：第" + (j + 2) + "行";
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
										rcs = rcs + "二阶客户"+i4s[i]+"不存在：第" + (j + 2)
												+ "行</br>";
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
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}
					if (result.toString().equals("")) {
						this.getSession().setAttribute("custlist", custlist);
						this.setSuccessMessage("导入成功！导入数量为:" + custlist.size()
								+ "行");
					} else {
						this.setFailMessage("导入失败！  错误信息：</br></br></br>"
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else {
					this.setFailMessage("这不是CSV文件");
					return RESULT_MESSAGE;
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
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
							.getContentFromFile(new File(upload.toString())); // 获取String数组
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
								rcs = rcs + "客户编号有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i1)) {
								co.setCustName(i1);
							} else {
								rcs = rcs + "客户名称有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i2)) {
								i2s = i2.split("/");
								// co.setCustKunnr(i2);
							} else {
								rcs = rcs + "上游经销商编号有问题：第" + (j + 2) + "行</br>";
							}
							if (!"".equals(i3)) {
								i3s = i3.split("/");
								// co.setRemark(i3);
							} else {
								rcs = rcs + "上游经销商名称有问题：第" + (j + 2) + "行</br>";
							}
							if (!(batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0)) {
								rcs = rcs + "客户不存在：第" + (j + 2) + "行</br>";
							}
							if ((i2s.length == i3s.length) && (i2s.length > 0)) {
								if (!lookUpType(i2s)) { // 判断是否有重复值
									rcs = rcs + "经销商编号有相同的数据在：第" + (j + 2)
											+ "行</br>";
								} else {
									String i2c = "";
									String i3c = "";
									for (int cc = 0; cc < i2s.length; cc++) {
										String i2sNum = StringUtils.leftPad(
												i2s[cc].toString(), 8, '0');
										if (!(batChangeService
												.getCustomerBySapId(i2sNum,
														i3s[cc]) > 0)) {
											rcs = rcs + "经销商客户不存在或者冻结：第"
													+ (j + 2) + "行</br>";
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
								rcs = rcs + "经销商信息格式不正确：第" + (j + 2) + "行";
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
										rcs = rcs + "二阶客户不存在：第" + (j + 2)
												+ "行</br>";
									}
									// int n =
									// customerService.getTwoLevelCustomerCount(ct);
									/*
									 * if (0 == n) { rcs = rcs + "第" + (j + 2) +
									 * "上游客户" + i4s[i] + "不存在.</br>"; }
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
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}
					if (result.toString().equals("")) {
						String rcm = "";
						for (Customer cm : custlist) {
							if (!(batChangeService.updateByCustprentId(cm) > 0)) {
								rcm = rcm + cm.getCustNumber() + "修改失败 </br>";
							}
						}
						if (!rcm.equals("")) {
							this.setFailMessage("导入失败！  错误信息：</br></br></br>"
									+ rcm.toString());
						} else {
							this.getSession()
									.setAttribute("custlist", custlist);
							this.setSuccessMessage("导入成功！导入数量为:"
									+ custlist.size() + "行");
						}
					} else {
						this.setFailMessage("导入失败！  错误信息：</br></br></br>"
								+ result.toString());
					}
					return RESULT_MESSAGE;
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * 导入上游客户到数据库
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
								rcs = rcs + "客户编号有问题：第" + (j + 1) + "行";
							}
							if (hssfrow.getCell(i) != null) {
								String cell2 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setCustName(cell2);
							} else {
								rcs = rcs + "客户名称有问题：第" + (j + 1) + "行";
							}
							if (hssfrow.getCell(i) != null) {
								String cell3 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setCustKunnr(cell3);

							} else {
								rcs = rcs + "经销商SAP编号有问题：第" + (j + 1) + "行";
							}
							if (hssfrow.getCell(i) != null) {
								String cell4 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setRemark(cell4);
							} else {
								rcs = rcs + "经销商名称有问题：第" + (j + 1) + "行";
							}
							if (batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0) {

							} else {
								rcs = rcs + "客户不存在：第" + (j + 1) + "行";
							}
							if (batChangeService.getCustomerBySapId(
									co.getCustKunnr(), co.getRemark()) > 0) {

							} else {
								rcs = rcs + "经销商客户不存在或者冻结：第" + (j + 1) + "行";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs.toString() + "</br>");
								continue;
							}

						} else {
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("custlist", custlist);
					String rcm = "";
					for (Customer cm : custlist) {
						if (!(batChangeService.updateByCustprentId(cm) > 0)) {
							rcm = rcm + cm.getCustNumber() + "修改失败 </br>";
						}
					}
					if (!rcm.equals("")) {
						result.append(rcm);
					}
					if (result.toString().equals("")) {
						this.setSuccessMessage("保存成功！导入数量为:" + custlist.size()
								+ "行");
					} else {
						this.setSuccessMessage("保存成功！导入数量为:" + custlist.size()
								+ "行 </br>  错误信息：" + result.toString());
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
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}

					String rcm = "";
					for (Customer cm : custlist) {
						if (batChangeService.updateByCustprentId(cm) > 0) {

						} else {
							rcm = rcm + cm.getCustNumber() + "修改失败";
						}
					}
					if (!rcm.equals("")) {
						result.append(rcm);
					}
					this.getSession().setAttribute("custlist", custlist);
					if (result.toString().equals("")) {
						this.setSuccessMessage("保存客户成功！导入数量为:"
								+ custlist.size() + "行");
					} else {
						this.setSuccessMessage("保存客户成功！导入数量为:"
								+ custlist.size() + "行 </br>  错误信息："
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
				this.setFailMessage("数据有问题");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * 查看导入渠道
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
							.getContentFromFile(new File(uploadFile.toString())); // 获取String数组
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
								rcs = rcs + "客户编号有问题：第" + (j + 2) + "行";
							}
							if (!i1.equals("")) {
								co.setCustName(i1);
							} else {
								rcs = rcs + "客户名称有问题：第" + (j + 2) + "行";
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
									rcs = rcs + "渠道ID不是最底层渠道：第" + (j + 2) + "行";
								}
							} else {
								rcs = rcs + "渠道ID有问题：第" + (j + 2) + "行";
							}
							if (!i3.equals("")) {
								co.setRemark(i3);
							} else {
								rcs = rcs + "渠道名称有问题：第" + (j + 2) + "行";
							}
							if (!(batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0)) {
								rcs = rcs + "客户不存在：第" + (j + 2) + "行";
							}

							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}
					if (result.toString().equals("")) {
						this.getSession().setAttribute("qcustlist", custlist);
						this.setSuccessMessage("导入渠道成功！导入数量为:"
								+ custlist.size() + "行");
					} else {
						this.setSuccessMessage("导入渠道失败！</br> 其中有问题的有："
								+ result.toString());
					}

					return RESULT_MESSAGE;
				} else {
					this.setFailMessage("这不是CSV文件");
					return RESULT_MESSAGE;
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * 导入渠道信息进数据库
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String importCsvQdDb() {
		StringBuilder result = new StringBuilder();
		Bchannel channel = new Bchannel();
		channelList = channelService.getUnderChannel(channel);
		System.out.println("导入渠道信息进数据库channelList.size(): " + channelList);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();

			custlist = new ArrayList<Customer>();

			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // 获取String数组
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
								rcs = rcs + "客户编号有问题：第" + (j + 2) + "行";
							}
							if (!i1.equals("")) {
								co.setCustName(i1);
							} else {
								rcs = rcs + "客户名称有问题：第" + (j + 2) + "行";
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
									rcs = rcs + "渠道ID不是最底层渠道：第" + (j + 2) + "行";
								}
								else{
									String str="Z";
									Bchannel bc = new Bchannel();
									bc.setChannelId(Long.valueOf(i2));
									bc.setType("一阶");   //终端
									Bchannel channel1 = new Bchannel();
									channel1 = channelService.getChannelById(bc);
									Bchannel bc1 = new Bchannel();
									bc1.setChannelId(Long.valueOf(i2));
									bc1.setType("二阶");   //终端
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
								rcs = rcs + "渠道ID有问题：第" + (j + 2) + "行";
							}
							if (!i3.equals("")) {
								co.setRemark(i3);
							} else {
								rcs = rcs + "渠道名称有问题：第" + (j + 2) + "行";
							}
							if (!(batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0)) {
								rcs = rcs + "客户不存在：第" + (j + 2) + "行";
							}

							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}
					if (result.toString().equals("")) {
						this.getSession().setAttribute("qcustlist", custlist);
						String rcm = "";
						for (Customer cm : custlist) {
							if (!(batChangeService.updateChannelIdById(cm) > 0)) {
								rcm = rcm + cm.getCustNumber() + "修改失败</br>";
							}
						}
						if (!rcm.equals("")) {
							this.setFailMessage("导入渠道失败！</br> 其中有问题的有："
									+ rcm.toString());
							return RESULT_MESSAGE;
						}
						this.getSession().setAttribute("qcustlist", custlist);
						this.setSuccessMessage("导入渠道成功！导入数量为:"
								+ custlist.size() + "行");
					} else {
						this.setFailMessage("导入渠道失败！</br> 其中有问题的有："
								+ result.toString());
					}
					return RESULT_MESSAGE;
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * 查看导入渠道
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
								rcs = rcs + "编号有问题：第" + (j + 1) + "行";
							}
							if (hssfrow.getCell(i) != null) {
								co.setCustName(hssfrow.getCell(i)
										.getRichStringCellValue().toString());
								i++;
							} else {
								rcs = rcs + "名称有问题：第" + (i + 1) + "行";
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
									rcs = rcs + "渠道ID不是最底层渠道：第" + (j + 1) + "行";
								}
							} else {
								rcs = rcs + "渠道ID有问题：第" + (j + 1) + "行";
							}
							if (hssfrow.getCell(i) != null) {
								String cel2 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setRemark(cel2);
							} else {
								rcs = rcs + "渠道名称有问题：第" + (j + 1) + "行";
							}
							if (batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0) {
							} else {
								rcs = rcs + "客户不存在：第" + (j + 1) + "行";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("qcustlist", custlist);
					if (result.toString().equals("")) {
						this.setSuccessMessage("导入渠道成功！导入数量为:"
								+ custlist.size() + "行");
					} else {
						this.setSuccessMessage("导入渠道成功！导入数量为:"
								+ custlist.size() + "行    </br> 其中有问题的有："
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
								rcs = rcs + "编号有问题：第" + (k + 1) + "行";
							}
							if (row.getCell(f) != null) {
								co.setCustName(row.getCell(f)
										.getRichStringCellValue().toString());
								f++;
							} else {
								rcs = rcs + "名称有问题：第" + (k + 1) + "行";
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
									rcs = rcs + "渠道ID不是最底层渠道：第" + (k + 1) + "行";
								}

							} else {
								rcs = rcs + "渠道id有问题：第" + (k + 1) + "行";
							}
							if (row.getCell(f) != null) {
								co.setCustKunnr(row.getCell(f++)
										.getRichStringCellValue().toString());
							} else {
								rcs = rcs + "渠道名称有问题：第" + (k + 1) + "行";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("qcustlist", custlist);

					if (result.toString().equals("")) {
						this.setSuccessMessage("导入渠道成功！导入数量为:"
								+ custlist.size() + "行");
					} else {
						this.setSuccessMessage("导入渠道成功！导入数量为:"
								+ custlist.size() + "行    </br> 其中有问题的有："
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else {// 文件格式
					this.setFailMessage("文件格式不正确！");
					return RESULT_MESSAGE;
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
			if (out != null) {
				this.setFailMessage("数据有问题");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}

	/***
	 * 
	 * 导入渠道到数据库
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
								rcs = rcs + "编号有问题：第" + (j + 1) + "行";
							}
							if (hssfrow.getCell(i) != null) {
								co.setCustName(hssfrow.getCell(i)
										.getRichStringCellValue().toString());
								i++;
							} else {
								rcs = rcs + "名称有问题：第" + (i + 1) + "行";
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
									rcs = rcs + "渠道ID不是最底层渠道：第" + (j + 1) + "行";
								}
							} else {
								rcs = rcs + "渠道ID有问题：第" + (j + 1) + "行";
							}
							if (hssfrow.getCell(i) != null) {
								String cel2 = hssfrow.getCell(i++)
										.getRichStringCellValue().toString();
								co.setRemark(cel2);
							} else {
								rcs = rcs + "渠道名称有问题：第" + (j + 1) + "行";
							}
							if (batChangeService.getCustomerBy(
									co.getCustNumber(), co.getCustName()) > 0) {
							} else {
								rcs = rcs + "客户不存在：第" + (j + 1) + "行";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("qcustlist", custlist);
					String rcm = "";
					for (Customer cm : custlist) {
						if (!(batChangeService.updateChannelIdById(cm) > 0)) {
							rcm = rcm + cm.getCustNumber() + "修改失败</br>";
						}
					}
					if (!rcm.equals("")) {
						result.append(rcm);
					}
					if (result.toString().equals("")) {
						this.setSuccessMessage("导入渠道成功！导入数量为:"
								+ custlist.size() + "行");
					} else {
						this.setSuccessMessage("导入渠道成功！导入数量为:"
								+ custlist.size() + "行    </br> 其中有问题的有："
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
								rcs = rcs + "编号有问题：第" + (k + 1) + "行";
							}
							if (row.getCell(f) != null) {
								co.setCustName(row.getCell(f)
										.getRichStringCellValue().toString());
								f++;
							} else {
								rcs = rcs + "名称有问题：第" + (k + 1) + "行";
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
									rcs = rcs + "渠道ID不是最底层渠道：第" + (k + 1) + "行";
								}

							} else {
								rcs = rcs + "渠道id有问题：第" + (k + 1) + "行";
							}
							if (row.getCell(f) != null) {
								co.setCustKunnr(row.getCell(f++)
										.getRichStringCellValue().toString());
							} else {
								rcs = rcs + "渠道名称有问题：第" + (k + 1) + "行";
							}
							if (rcs.equals("")) {
								custlist.add(co);
							} else {
								result.append(rcs + "</br>");
								continue;
							}
						} else {
							this.setFailMessage("列数有问题");
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("qcustlist", custlist);
					String rcm = "";
					for (Customer cm : custlist) {
						if (!(batChangeService.updateByCustprentId(cm) > 0)) {
							rcm = rcm + cm.getCustNumber() + "修改失败</br>";
						}
					}
					if (!rcm.equals("")) {
						result.append(rcm);
					}
					if (result.toString().equals("")) {
						this.setSuccessMessage("保存渠道成功！导入数量为:"
								+ custlist.size() + "行");
					} else {
						this.setSuccessMessage("保存渠道成功！导入数量为:"
								+ custlist.size() + "行    </br> 其中有问题的有："
								+ result.toString());
					}
					return RESULT_MESSAGE;
				} else {// 文件格式
					this.setFailMessage("文件格式不正确！");
					return RESULT_MESSAGE;
				}
			} else {// 文件不存在
				this.setFailMessage("文件不存在");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
			if (out != null) {
				this.setFailMessage("数据有问题");
				return RESULT_MESSAGE;
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 客户渠道修改模版
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
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头

			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("客户渠道批量修改表".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("渠道模版", 0);
			WritableSheet wsheet1 = wbook.createSheet("渠道信息", 1);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);

			// WritableSheet.setColumnView(int i,int width);
			// 作用是指定第i+1列的宽度，比如：
			// 将第一列的宽度设为30
			// sheet.setColumnView(0,30);
			// wsheet.setRowView(0,10);
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.DARK_RED);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置单元格背景颜色
			cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// 设置字体格式
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// 设置居中
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置表格边框
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			// 表头
			Label label_0 = new Label(0, 0, "客户编号");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "客户名称");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			wsheet.setColumnView(1, 20);

			Label label_2 = new Label(2, 0, "渠道id");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);
			wsheet.setColumnView(2, 20);
			Label label_3 = new Label(3, 0, "渠道名称");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			wsheet.setColumnView(3, 20);

			// 表头
			Label label_01 = new Label(0, 0, "渠道id");
			label_01.setCellFormat(cellFormat_top);
			wsheet1.addCell(label_01);

			Label label_11 = new Label(1, 0, "渠道名称");
			label_11.setCellFormat(cellFormat_top);
			wsheet1.addCell(label_11);
			wsheet1.setColumnView(1, 20);
			if (null != channelList) {
				for (int x = 0; x < channelList.size(); x++) {
					Bchannel bc = new Bchannel();
					bc = channelList.get(x);
					// 表格主体
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
