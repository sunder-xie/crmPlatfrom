package com.kintiger.platform.stock.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormats;
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
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.ExcelUtil;
import com.kintiger.platform.goal.service.IGoalService;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.stock.pojo.Stock;
import com.kintiger.platform.stock.service.IInStockService;
import com.kintiger.platform.stock.service.IStockService;

public class StockManageAction extends BaseAction {
	private static final long serialVersionUID = 5215748232237846024L;
	@Decode
	private String categories;
	@Decode
	private String custKunnr;
	private int total;
	private String uploadFileName;
	private File upload;
	private IKunnrService kunnrService;
	private IStockService stockService;
	private IGoalService goalService;
	private IInStockService instockService;
	private String userId;
	private Long stock_id;
	private Long cust_id;
	private List<Stock> stockList = new ArrayList<Stock>();
	private String custNumber;
	private static Log logger = LogFactory.getLog(StockManageAction.class);

	/**
	 * 库存查询跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String seachStock() {

		return "seachStock";
	}

	/**
	 * 盘点跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String initStock() {
		return "initStock";
	}

	/**
	 * 库存查询
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "stockList", include = { "stock_id", "cust_id",
			"custName", "stock_stock_place", "category_id", "categoryName",
			"batch", "stock_quantity" }, total = "total")
	public String seachStockJsonList() {
		Stock m = new Stock();
		m.setStart(getStart());
		m.setEnd(getEnd());
		if (StringUtils.isNotEmpty(custKunnr)
				&& StringUtils.isNotEmpty(custKunnr)) {
			m.setCust_id(custKunnr);
		}
		if (StringUtils.isNotEmpty(categories)
				&& StringUtils.isNotEmpty(categories)) {
			m.setCategory_id(categories);
		}
		total = stockService.seachStockListCount(m);
		if (total > 0) {
			stockList = stockService.seachStockList(m);
		}
		return JSON;
	}

	/**
	 * 盘点模板下载
	 */
	public void downLoadModule() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		HttpServletResponse response = getServletResponse();
		try {
			os = response.getOutputStream();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String("库存盘点导入模板".getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			response.setContentType("application/msexcel");
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("库存盘点", 0);
			WritableCellFormat cellFormat_top = new WritableCellFormat(
					NumberFormats.TEXT);
			WritableCellFormat cellFormat = new WritableCellFormat(
					NumberFormats.TEXT);

			WritableFont font = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.DARK_RED);
			cellFormat_top.setAlignment(Alignment.CENTRE);
			cellFormat_top.setFont(font);
			cellFormat_top.setBackground(Colour.YELLOW);
			cellFormat_top.setBorder(Border.ALL, BorderLineStyle.THIN);

			Label label_0 = new Label(0, 0, "经销商SAP代码");
			label_0.setCellFormat(cellFormat_top);
			wsheet.setColumnView(0, 20, cellFormat);
			wsheet.addCell(label_0);
			Label label_1 = new Label(1, 0, "库存地");
			label_1.setCellFormat(cellFormat_top);
			wsheet.setColumnView(1, 10, cellFormat);
			wsheet.addCell(label_1);
			Label label_2 = new Label(2, 0, "物料编号");
			label_2.setCellFormat(cellFormat_top);
			wsheet.setColumnView(2, 30, cellFormat);
			wsheet.addCell(label_2);
			Label label_3 = new Label(3, 0, "批次");
			label_3.setCellFormat(cellFormat_top);
			wsheet.setColumnView(3, 10, cellFormat);
			wsheet.addCell(label_3);
			Label label_4 = new Label(4, 0, "盘点数量");
			label_4.setCellFormat(cellFormat_top);
			wsheet.setColumnView(4, 10, cellFormat);
			wsheet.addCell(label_4);
			ExcelUtil.createExcelWithBook(wbook, null, null);
		} catch (Exception e) {
			logger.error(e);
		}

	}

	/**
	 * 预览导入
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@PermissionSearch
	@JsonResult(field = "stockList", include = { "custName",
			"stock_stock_place", "category_id", "categoryName", "batch",
			"stock_quantity", "stock_pandian", "stock_differ" })
	public String scanData() {
		stockList = (List<Stock>) this.getSession().getAttribute("stockList");
		return JSON;
	}

	/***
	 * 
	 * 导入经销商库存信息
	 * 
	 * @return
	 */
	public String importScan() {
		HSSFWorkbook hssfworkbook = null;
		try {
			stockList = new ArrayList<Stock>();
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
						if (rsColumns == 5) {
							int i = 0;
							Stock stock = new Stock();
							// 经销商编号
							if (hssfrow.getCell(i) != null) {
								String kunnr = hssfrow.getCell(i)
										.getRichStringCellValue().toString();
								Kunnr k = new Kunnr();
								k.setKunnr(kunnr);
								k = kunnrService.getKunnrEntity(k);
								if (k != null) {
									stock.setCust_id(kunnr);
									stock.setCustName(k.getName1());
								}
							}
							// 库存地
							if (hssfrow.getCell(++i) != null) {
								stock.setStock_stock_place(hssfrow.getCell(i)
										.getRichStringCellValue().toString());
							}
							// 物料号
							if (hssfrow.getCell(++i) != null) {
								String categoryId = hssfrow.getCell(i)
										.getRichStringCellValue().toString();
								Materiel m = new Materiel();
								m.setMatnr(categoryId);
								List<Materiel> materiellist = goalService
										.getMatList1(m);
								if (materiellist != null
										&& materiellist.size() > 0) {
									stock.setCategory_id(categoryId);
									stock.setCategoryName(materiellist.get(0)
											.getMaktx());
								}
							}
							// 批次
							if (hssfrow.getCell(++i) != null) {
								stock.setBatch(hssfrow.getCell(i)
										.getRichStringCellValue().toString());
							}
							int now = 0;
							int pandian = 0;

							// 库存数
							List<Stock> stocklist = instockService
									.getStockList(stock);
							now = stocklist != null ? stocklist.get(0)
									.getStock_quantity() : 0;
							stock.setStock_quantity(now);

							// 盘点数
							if (hssfrow.getCell(++i) != null) {
								pandian = Integer.parseInt(hssfrow.getCell(i)
										.getRichStringCellValue().toString());
								stock.setStock_pandian(pandian);
							}

							// 差异
							stock.setStock_differ(now - pandian);

							stockList.add(stock);
						} else {
							this.setFailMessage("模板导入列数不正确,请导入正确模板格式!");
							return RESULT_MESSAGE;
						}
					}
					// 存入session
					this.getServletRequest().getSession()
							.setAttribute("stockList", stockList);
					this.setSuccessMessage("S");
					return RESULT_MESSAGE;
				} else {
					this.setFailMessage("导入盘点模板错误,请导入正确模板格式!");
					return RESULT_MESSAGE;
				}
			} else {
				this.setFailMessage("未导入盘点模板,请导入正确模板格式!");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("数据导入异常,请稍后再试!");
			return RESULT_MESSAGE;
		}
	}

	/**
	 * 模板数据导入数据库
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String importDb() {
		stockList = (List<Stock>) this.getSession().getAttribute("stockList");
		String result;
		try {
			result = stockService.pandianStock(stockList);
			if ("success".equals(result)) {
				this.setSuccessMessage("盘点数据导入成功,请查看库存");
			}
		} catch (Exception e) {
			logger.error(e);
			this.setFailMessage("盘点数据导入异常,请稍后再试");
		}
		return RESULT_MESSAGE;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}

	public Long getStock_id() {
		return stock_id;
	}

	public void setStock_id(Long stock_id) {
		this.stock_id = stock_id;
	}

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCategories() {
		return categories;
	}

	public String getCustKunnr() {
		return custKunnr;
	}

	public void setCustKunnr(String custKunnr) {
		this.custKunnr = custKunnr;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public IStockService getStockService() {
		return stockService;
	}

	public void setStockService(IStockService stockService) {
		this.stockService = stockService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	public IGoalService getGoalService() {
		return goalService;
	}

	public void setGoalService(IGoalService goalService) {
		this.goalService = goalService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public IInStockService getInstockService() {
		return instockService;
	}

	public void setInstockService(IInStockService instockService) {
		this.instockService = instockService;
	}

}
