package com.kintiger.platform.qualityChecking.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.DateUtil;
import com.kintiger.platform.framework.util.FileUtil;
import com.kintiger.platform.qualityChecking.pojo.Materiel;
import com.kintiger.platform.qualityChecking.pojo.QualityChecking;
import com.kintiger.platform.qualityChecking.service.IQualityCheckingService;

/**
 * @Description:质检报告控制类
 * @author:xg.chen
 * @time:2017年5月8日 下午3:41:29
 * @version:1.0
 */
public class QualityCheckingAction extends BaseAction {
	private static final long serialVersionUID = -2170570538221804261L;
	private static Log logger = LogFactory.getLog(QualityCheckingAction.class);
	// id
	private String id;
	// 质检编号
	private String qualityCheckingId;
	// 生产日期
	private String dateOfManufacture;
	// 时间从
	private String productionTimeStart;
	// 时间至
	private String productionTimeEnd;
	// 物料Id
	private String materielId;
	// 物料描述
	private String materielName;
	// 报告预览
	private String qualityChecking;
	private List<QualityChecking> qualityCheckingList;
	private QualityChecking qualityCheck;
	private int total;
	private IQualityCheckingService qualityCheckingService;
	// 物料代码，（物料服务号)
	private String matnr;
	// 物料描述
	private String maktx;
	private Materiel materiel;
	private List<Materiel> materielList;
	private int size;
	private String appUrl;
	// 上传文件名
	private String uploadFileFileName;
	// 上传的文件
	private File uploadFile;
	private String dateOfManufactureStart;
	private String dateOfManufactureEnd;
	// 上传文件路径
	private String uploadFilePath;
	
	private String userId;

	/**
	 * @Description:页面跳转
	 * @author:xg.chen
	 * @date:2017年5月8日 下午2:34:56
	 * @return
	 * @version:1.0
	 */
	public String qualityCheckingVeiw() {
		userId=userId!=null?userId:this.getUser().getUserId();
		boolean role1 = qualityCheckingService.getOfficeRole(userId,"quality_checking_edit");
		if(role1 || userId.equals("88647")){//质检报告编辑页面
			return "qualityCheckingEdit";
		}
		return "qualityCheckingVeiw";//质检报告查看页面
		
	}

	/**
	 * @Description:主数据加载
	 * @author:xg.chen
	 * @date:2017年5月8日 下午2:47:22
	 * @return
	 * @version:1.0
	 * @throws UnsupportedEncodingException
	 */
	@JsonResult(field = "qualityCheckingList", total = "total", include = {
			"id", "qualityCheckingId", "dateOfManufacture",
			"productionTimeStart", "productionTimeEnd", "materielId",
			"materielName", "qualityChecking" })
	public String qualityCheckingList() throws UnsupportedEncodingException {
		qualityCheck = new QualityChecking();
		if (StringUtils.isNotEmpty(qualityCheckingId)) {
			qualityCheck.setQualityCheckingId(qualityCheckingId);
		}
		if (StringUtils.isNotEmpty(dateOfManufactureStart)) {
			String dateOfManufactureStart1 = java.net.URLDecoder.decode(
					dateOfManufactureStart, "UTF-8");// 解码（encodeURIComponent()编码）
			qualityCheck.setDateOfManufactureStart(dateOfManufactureStart1);
		}
		if (StringUtils.isNotEmpty(dateOfManufactureEnd)) {
			String dateOfManufactureEnd1 = java.net.URLDecoder.decode(
					dateOfManufactureEnd, "UTF-8");
			qualityCheck.setDateOfManufactureEnd(dateOfManufactureEnd1);
		}
		if (StringUtils.isNotEmpty(materielId)) {
			qualityCheck.setMaterielId(materielId);
		}
		qualityCheck.setStart(getStart());
		qualityCheck.setEnd(getEnd());
		total = qualityCheckingService.getQualityCheckingCount(qualityCheck);
		if (total != 0) {
			qualityCheckingList = qualityCheckingService
					.getQualityCheckingJsonList(qualityCheck);
		}
		return JSON;
	}

	/**
	 * @Description:导入数据模板导出
	 * @author:xg.chen
	 * @date:2017年5月8日 下午2:41:07
	 * @version:1.0
	 */
	public String exportTemplate() {
		setSuccessMessage("");
		setFailMessage("");
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "质检报告导入模板.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);

			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
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
			WritableCellFormat cellFormat_bottom_left = new WritableCellFormat();
			cellFormat_bottom_left.setAlignment(jxl.format.Alignment.LEFT);
			cellFormat_bottom_left.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			WritableSheet wsheet = wbook.createSheet("质检报告导入模板", 0);

			wsheet.setRowView(0, 300);
			wsheet.setRowView(1, 300);
			wsheet.setColumnView(0, 20);
			wsheet.setColumnView(1, 30);
			wsheet.setColumnView(2, 30);
			wsheet.setColumnView(3, 30);
			wsheet.setColumnView(4, 30);

			Label label_0 = new Label(0, 0, "质检报告编号");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "生产日期(文本格式：2017-05-09)");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);

			Label label_2 = new Label(2, 0, "时间从(文本格式：10:00:01)");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);

			Label label_3 = new Label(3, 0, "时间至(文本格式：18:00:01)");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);

			Label label_4 = new Label(4, 0, "物料");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);

			wbook.write();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
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

	/**
	 * @Description:质检报告导入页面
	 * @author:xg.chen
	 * @date:2017年5月9日 上午10:16:52
	 * @return
	 * @version:1.0
	 */
	public String toAddQualityCheking() {
		return "toAddQualityCheking";
	}

	/**
	 * @Description:质检报告导入
	 * @author:xg.chen
	 * @date:2017年5月8日 下午2:38:49
	 * @version:1.0
	 */
	@SuppressWarnings("unchecked")
	public String qualityCheckingImportCsv() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		if (StringUtils.isEmpty(uploadFileFileName)) {
			setFailMessage("导入失败！");
		}
		String fileName = this.uploadFileFileName.substring(
				this.uploadFileFileName.length() - 3,
				this.uploadFileFileName.length());
		if ("xls".equalsIgnoreCase(fileName)) {
			Map<String, Object> map = qualityCheckingService
					.qualityCheckingImportCsv(uploadFile);
			String mags = (String) map.get("resultMessage");
			if (StringUtils.isNotEmpty(mags)) {
				setFailMessage(mags);// 返回错误信息
			} else {
				qualityCheckingList = (List<QualityChecking>) map
						.get("qualityCheckingList");
				this.getSession().setAttribute("qualityCheckingList",
						qualityCheckingList);
				setSuccessMessage("导入成功");
			}
		} else {
			setFailMessage("导入的文件格式错误");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * @Description:获取缓存中导入的数据
	 * @author:xg.chen
	 * @date:2017年5月9日 下午4:14:31
	 * @return
	 * @version:1.0
	 */
	@SuppressWarnings("unchecked")
	@JsonResult(field = "qualityCheckingList", total = "total", include = {
			"qualityCheckingId", "dateOfManufacture", "productionTimeStart",
			"productionTimeEnd", "materielId", "materielName" })
	public String getAddQualityCheckingJsonList() {
		qualityCheckingList = (List<QualityChecking>) this.getSession()
				.getAttribute("qualityCheckingList");
		return JSON;
	}

	/**
	 * @Description:保存提交数据
	 * @author:xg.chen
	 * @date:2017年5月9日 下午4:33:04
	 * @return
	 * @version:1.0
	 */
	@SuppressWarnings("unchecked")
	public String saveQualityChecking() {
		try {
			qualityCheckingList = (List<QualityChecking>) this.getSession()
					.getAttribute("qualityCheckingList");
			for (QualityChecking qualityChecking : qualityCheckingList) {
				QualityChecking qualityChecking1 = new QualityChecking();
				qualityChecking1.setQualityCheckingId(qualityChecking
						.getQualityCheckingId());
				qualityChecking1.setDateOfManufacture(qualityChecking
						.getDateOfManufacture());
				qualityChecking1.setProductionTimeStart(qualityChecking
						.getProductionTimeStart());
				qualityChecking1.setProductionTimeEnd(qualityChecking
						.getProductionTimeEnd());
				qualityChecking1.setMaterielId(qualityChecking.getMaterielId());
				int count = qualityCheckingService
						.creatQualityChecking(qualityChecking1);
				if (count == 0) {
					setFailMessage("保存成功，数据有误保存失败！");
				}
			}
			setSuccessMessage("保存成功");
			this.getSession().removeAttribute("qualityCheckingList");
		} catch (Exception e) {
			logger.error(e);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * @Description:质检报告上传
	 * @author:xg.chen
	 * @date:2017年5月10日 上午10:22:59
	 * @return
	 * @version:1.0
	 */
	public String qualityCheckingUploadtFile() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		if (StringUtils.isEmpty(uploadFileFileName)) {
			setFailMessage("导入失败！");
		}
		qualityCheck = new QualityChecking();
		String imageFile = "";
		if (uploadFile != null) {
			if (uploadFileFileName != null) {
				imageFile = UUID.randomUUID()
						+ FileUtil.getFileExtention(uploadFileFileName);
				File savedir = new File(uploadFilePath);
				// 如果目录不存在，则新建
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				File imageFiles = new File(uploadFilePath + "/" + imageFile);
				FileUtil.saveAsFile(uploadFile, imageFiles);

				qualityCheck.setId(id);
				qualityCheck.setQualityChecking(imageFile);

				int count = qualityCheckingService
						.updateQualityCheckingUploadFile(qualityCheck);
				if (count == 0) {
					setFailMessage("上传附件失败!");
				}
				setSuccessMessage("上传成功!");
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * @Description:下载附件
	 * @author:xg.chen
	 * @date:2017年5月10日 下午2:24:49
	 * @return
	 * @version:1.0
	 * @throws IOException
	 */
	public String dowmloadQualityCheckingFile() throws IOException {
		try {
			QualityChecking qualityChecking1 = qualityCheckingService
					.getQualityCheckingName(id);
			if (qualityChecking1 != null) {
				String imageFile = qualityChecking1.getQualityChecking();
				String path = uploadFilePath + "/" + imageFile;
				HttpServletResponse response = ServletActionContext
						.getResponse();
				// path是指欲下载的文件的路径。
				File file = new File(path);
				// 取得文件名。
				String filename = file.getName();
				// 取得文件的后缀名。
				String ext = filename.substring(filename.lastIndexOf(".") + 1)
						.toUpperCase();
				// 以流的形式下载文件。
				InputStream fis = new BufferedInputStream(new FileInputStream(
						path));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				// 清空response
				response.reset();
				// 设置response的Header
				response.addHeader("Content-Disposition",
						"attachment;filename="
								+ new String(filename.getBytes()));
				response.addHeader("Content-Length", "" + file.length());
				OutputStream toClient = new BufferedOutputStream(
						response.getOutputStream());
				response.setContentType("application/octet-stream");
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
				setSuccessMessage("下载成功!");
			} else {
				setSuccessMessage("系统繁忙，请稍后!");
			}
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * @Description:查询物料
	 * @author:xg.chen
	 * @date:2017年5月8日 下午4:21:00
	 * @return
	 * @version:1.0
	 */
	@PermissionSearch
	@JsonResult(field = "materielList", total = "size", include = { "matnr",
			"maktx" })
	public String getMaterielList() {
		if (materiel == null) {
			materiel = new Materiel();
		}
		if (StringUtils.isNotEmpty(matnr)) {
			materiel.setMatnr(matnr);
		}
		if (StringUtils.isNotEmpty(maktx)) {
			materiel.setMaktx(maktx);
		}
		materiel.setStart(getStart());
		materiel.setEnd(getEnd());
		size = qualityCheckingService.getMaterielListCount(materiel);
		if (size != 0) {
			materielList = qualityCheckingService.getMaterielList(materiel);
		}
		return JSON;
	}

	public String getQualityCheckingId() {
		return qualityCheckingId;
	}

	public void setQualityCheckingId(String qualityCheckingId) {
		this.qualityCheckingId = qualityCheckingId;
	}

	public String getDateOfManufacture() {
		return dateOfManufacture;
	}

	public void setDateOfManufacture(String dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public String getProductionTimeStart() {
		return productionTimeStart;
	}

	public void setProductionTimeStart(String productionTimeStart) {
		this.productionTimeStart = productionTimeStart;
	}

	public String getProductionTimeEnd() {
		return productionTimeEnd;
	}

	public void setProductionTimeEnd(String productionTimeEnd) {
		this.productionTimeEnd = productionTimeEnd;
	}

	public String getMaterielId() {
		return materielId;
	}

	public void setMaterielId(String materielId) {
		this.materielId = materielId;
	}

	public String getMaterielName() {
		return materielName;
	}

	public void setMaterielName(String materielName) {
		this.materielName = materielName;
	}

	public String getQualityChecking() {
		return qualityChecking;
	}

	public void setQualityChecking(String qualityChecking) {
		this.qualityChecking = qualityChecking;
	}

	public List<QualityChecking> getQualityCheckingList() {
		return qualityCheckingList;
	}

	public void setQualityCheckingList(List<QualityChecking> qualityCheckingList) {
		this.qualityCheckingList = qualityCheckingList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public IQualityCheckingService getQualityCheckingService() {
		return qualityCheckingService;
	}

	public void setQualityCheckingService(
			IQualityCheckingService qualityCheckingService) {
		this.qualityCheckingService = qualityCheckingService;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
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

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getMaktx() {
		return maktx;
	}

	public void setMaktx(String maktx) {
		this.maktx = maktx;
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setMaterielList(List<Materiel> materielList) {
		this.materielList = materielList;
	}

	public String getDateOfManufactureStart() {
		return dateOfManufactureStart;
	}

	public void setDateOfManufactureStart(String dateOfManufactureStart) {
		this.dateOfManufactureStart = dateOfManufactureStart;
	}

	public String getDateOfManufactureEnd() {
		return dateOfManufactureEnd;
	}

	public void setDateOfManufactureEnd(String dateOfManufactureEnd) {
		this.dateOfManufactureEnd = dateOfManufactureEnd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public QualityChecking getQualityCheck() {
		return qualityCheck;
	}

	public void setQualityCheck(QualityChecking qualityCheck) {
		this.qualityCheck = qualityCheck;
	}

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
