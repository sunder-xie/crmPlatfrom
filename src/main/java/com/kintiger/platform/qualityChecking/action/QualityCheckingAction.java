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
 * @Description:�ʼ챨�������
 * @author:xg.chen
 * @time:2017��5��8�� ����3:41:29
 * @version:1.0
 */
public class QualityCheckingAction extends BaseAction {
	private static final long serialVersionUID = -2170570538221804261L;
	private static Log logger = LogFactory.getLog(QualityCheckingAction.class);
	// id
	private String id;
	// �ʼ���
	private String qualityCheckingId;
	// ��������
	private String dateOfManufacture;
	// ʱ���
	private String productionTimeStart;
	// ʱ����
	private String productionTimeEnd;
	// ����Id
	private String materielId;
	// ��������
	private String materielName;
	// ����Ԥ��
	private String qualityChecking;
	private List<QualityChecking> qualityCheckingList;
	private QualityChecking qualityCheck;
	private int total;
	private IQualityCheckingService qualityCheckingService;
	// ���ϴ��룬�����Ϸ����)
	private String matnr;
	// ��������
	private String maktx;
	private Materiel materiel;
	private List<Materiel> materielList;
	private int size;
	private String appUrl;
	// �ϴ��ļ���
	private String uploadFileFileName;
	// �ϴ����ļ�
	private File uploadFile;
	private String dateOfManufactureStart;
	private String dateOfManufactureEnd;
	// �ϴ��ļ�·��
	private String uploadFilePath;
	
	private String userId;

	/**
	 * @Description:ҳ����ת
	 * @author:xg.chen
	 * @date:2017��5��8�� ����2:34:56
	 * @return
	 * @version:1.0
	 */
	public String qualityCheckingVeiw() {
		userId=userId!=null?userId:this.getUser().getUserId();
		boolean role1 = qualityCheckingService.getOfficeRole(userId,"quality_checking_edit");
		if(role1 || userId.equals("88647")){//�ʼ챨��༭ҳ��
			return "qualityCheckingEdit";
		}
		return "qualityCheckingVeiw";//�ʼ챨��鿴ҳ��
		
	}

	/**
	 * @Description:�����ݼ���
	 * @author:xg.chen
	 * @date:2017��5��8�� ����2:47:22
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
					dateOfManufactureStart, "UTF-8");// ���루encodeURIComponent()���룩
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
	 * @Description:��������ģ�嵼��
	 * @author:xg.chen
	 * @date:2017��5��8�� ����2:41:07
	 * @version:1.0
	 */
	public String exportTemplate() {
		setSuccessMessage("");
		setFailMessage("");
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// ȡ�������
			os = response.getOutputStream();
			// ��������
			response.reset();
			// �趨����ļ�ͷ
			String fileName = "�ʼ챨�浼��ģ��.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// �����������
			response.setContentType("application/msexcel");
			// ����excel�ļ�
			wbook = Workbook.createWorkbook(os);

			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
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
			WritableCellFormat cellFormat_bottom_left = new WritableCellFormat();
			cellFormat_bottom_left.setAlignment(jxl.format.Alignment.LEFT);
			cellFormat_bottom_left.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			WritableSheet wsheet = wbook.createSheet("�ʼ챨�浼��ģ��", 0);

			wsheet.setRowView(0, 300);
			wsheet.setRowView(1, 300);
			wsheet.setColumnView(0, 20);
			wsheet.setColumnView(1, 30);
			wsheet.setColumnView(2, 30);
			wsheet.setColumnView(3, 30);
			wsheet.setColumnView(4, 30);

			Label label_0 = new Label(0, 0, "�ʼ챨����");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "��������(�ı���ʽ��2017-05-09)");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);

			Label label_2 = new Label(2, 0, "ʱ���(�ı���ʽ��10:00:01)");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);

			Label label_3 = new Label(3, 0, "ʱ����(�ı���ʽ��18:00:01)");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);

			Label label_4 = new Label(4, 0, "����");
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
	 * @Description:�ʼ챨�浼��ҳ��
	 * @author:xg.chen
	 * @date:2017��5��9�� ����10:16:52
	 * @return
	 * @version:1.0
	 */
	public String toAddQualityCheking() {
		return "toAddQualityCheking";
	}

	/**
	 * @Description:�ʼ챨�浼��
	 * @author:xg.chen
	 * @date:2017��5��8�� ����2:38:49
	 * @version:1.0
	 */
	@SuppressWarnings("unchecked")
	public String qualityCheckingImportCsv() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		if (StringUtils.isEmpty(uploadFileFileName)) {
			setFailMessage("����ʧ�ܣ�");
		}
		String fileName = this.uploadFileFileName.substring(
				this.uploadFileFileName.length() - 3,
				this.uploadFileFileName.length());
		if ("xls".equalsIgnoreCase(fileName)) {
			Map<String, Object> map = qualityCheckingService
					.qualityCheckingImportCsv(uploadFile);
			String mags = (String) map.get("resultMessage");
			if (StringUtils.isNotEmpty(mags)) {
				setFailMessage(mags);// ���ش�����Ϣ
			} else {
				qualityCheckingList = (List<QualityChecking>) map
						.get("qualityCheckingList");
				this.getSession().setAttribute("qualityCheckingList",
						qualityCheckingList);
				setSuccessMessage("����ɹ�");
			}
		} else {
			setFailMessage("������ļ���ʽ����");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * @Description:��ȡ�����е��������
	 * @author:xg.chen
	 * @date:2017��5��9�� ����4:14:31
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
	 * @Description:�����ύ����
	 * @author:xg.chen
	 * @date:2017��5��9�� ����4:33:04
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
					setFailMessage("����ɹ����������󱣴�ʧ�ܣ�");
				}
			}
			setSuccessMessage("����ɹ�");
			this.getSession().removeAttribute("qualityCheckingList");
		} catch (Exception e) {
			logger.error(e);
		}
		return RESULT_MESSAGE;
	}

	/**
	 * @Description:�ʼ챨���ϴ�
	 * @author:xg.chen
	 * @date:2017��5��10�� ����10:22:59
	 * @return
	 * @version:1.0
	 */
	public String qualityCheckingUploadtFile() {
		this.setSuccessMessage("");
		this.setFailMessage("");
		if (StringUtils.isEmpty(uploadFileFileName)) {
			setFailMessage("����ʧ�ܣ�");
		}
		qualityCheck = new QualityChecking();
		String imageFile = "";
		if (uploadFile != null) {
			if (uploadFileFileName != null) {
				imageFile = UUID.randomUUID()
						+ FileUtil.getFileExtention(uploadFileFileName);
				File savedir = new File(uploadFilePath);
				// ���Ŀ¼�����ڣ����½�
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
					setFailMessage("�ϴ�����ʧ��!");
				}
				setSuccessMessage("�ϴ��ɹ�!");
			}
		}
		return RESULT_MESSAGE;
	}

	/**
	 * @Description:���ظ���
	 * @author:xg.chen
	 * @date:2017��5��10�� ����2:24:49
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
				// path��ָ�����ص��ļ���·����
				File file = new File(path);
				// ȡ���ļ�����
				String filename = file.getName();
				// ȡ���ļ��ĺ�׺����
				String ext = filename.substring(filename.lastIndexOf(".") + 1)
						.toUpperCase();
				// ��������ʽ�����ļ���
				InputStream fis = new BufferedInputStream(new FileInputStream(
						path));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				// ���response
				response.reset();
				// ����response��Header
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
				setSuccessMessage("���سɹ�!");
			} else {
				setSuccessMessage("ϵͳ��æ�����Ժ�!");
			}
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * @Description:��ѯ����
	 * @author:xg.chen
	 * @date:2017��5��8�� ����4:21:00
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
