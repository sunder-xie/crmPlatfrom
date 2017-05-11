package com.kintiger.platform.qualityChecking.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;
import org.apache.velocity.exception.ParseErrorException;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.qualityChecking.dao.IQualityCheckingDao;
import com.kintiger.platform.qualityChecking.pojo.Materiel;
import com.kintiger.platform.qualityChecking.pojo.QualityChecking;
import com.kintiger.platform.qualityChecking.service.IQualityCheckingService;

/**
 * @Description:�����ʵ����
 * @author:xg.chen
 * @time:2017��5��8�� ����3:01:14
 * @version:1.0
 */
public class QualityCheckingServiceImpl implements IQualityCheckingService {

	private static final Log logger = LogFactory
			.getLog(QualityCheckingServiceImpl.class);

	private IQualityCheckingDao qualityCheckingDao;

	private TransactionTemplate transactionTemplate;

	public IQualityCheckingDao getQualityCheckingDao() {
		return qualityCheckingDao;
	}

	public void setQualityCheckingDao(IQualityCheckingDao qualityCheckingDao) {
		this.qualityCheckingDao = qualityCheckingDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * @Description:��������ͳ��
	 * @author:xg.chen
	 * @date:2017��5��8�� ����4:26:58
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	@Override
	public int getMaterielListCount(Materiel mat) {
		try {
			return qualityCheckingDao.getMaterielListCount(mat);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(""), e);
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @Description:�����б�
	 * @author:xg.chen
	 * @date:2017��5��8�� ����4:27:07
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	@Override
	public List<Materiel> getMaterielList(Materiel mat) {
		try {
			return qualityCheckingDao.getMaterielList(mat);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(""), e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description:����������֤
	 * @author:xg.chen
	 * @date:2017��5��9�� ����11:12:03
	 * @param uploadFile
	 * @return
	 * @version:1.0
	 */
	@Override
	public Map<String, Object> qualityCheckingImportCsv(File uploadFile) {
		// ȫ�ֱ�������
		Map<String, Object> map = new HashMap<String, Object>();
		List<QualityChecking> qualityCheckingList = new ArrayList<QualityChecking>();
		Workbook workbook = null;
		Sheet sheet = null;
		StringBuilder errorMsgContent = new StringBuilder();
		// ��ȡ�����б�
		try {
			// ��ȡ������
			workbook = new HSSFWorkbook(new FileInputStream(uploadFile));
			// ��ȡ��ͷ
			String[] header = new String[5];
			header[0] = "�ʼ챨����";
			header[1] = "��������(��ʽ��2017-05-09)";
			header[2] = "ʱ���(��ʽ��10:00:01)";
			header[3] = "ʱ����(��ʽ��10:00:01)";
			header[4] = "����";
			// ��ʼ��ȡ���ݱ��
			sheet = workbook.getSheetAt(0);
			int row = sheet.getLastRowNum();
			if (row == 0) {
				errorMsgContent.append("�����ExcelΪ�գ�</br>");
			} else if (sheet.getRow(0).getLastCellNum() != header.length) {
				errorMsgContent.append("�����Excel�����������ļ�������һ��!</br>");
			} else {
				// ����Ӧ��ֵ��ֵ��ҳ��
				for (int i = 1; i < row + 1; i++) {
					String qualityCheckingId = "";
					String dateOfManufacture = "";
					String productionTimeStart = "";
					String productionTimeEnd = "";
					String materielId = "";

					qualityCheckingId = getValue(sheet.getRow(i).getCell(
							header.length - 5));
					dateOfManufacture = getValue(sheet.getRow(i).getCell(
							header.length - 4));
					productionTimeStart = getValue(sheet.getRow(i).getCell(
							header.length - 3));
					productionTimeEnd = getValue(sheet.getRow(i).getCell(
							header.length - 2));
					materielId = getValue(sheet.getRow(i).getCell(
							header.length - 1));

					if (StringUtils.isEmpty(qualityCheckingId)
							&& StringUtils.isEmpty(dateOfManufacture)
							&& StringUtils.isEmpty(materielId)) {
						break;
					} else {
						// ��֤�ʼ���
						if (StringUtils.isNotEmpty(qualityCheckingId)) {
							Pattern pattern = Pattern.compile("^[A-Z0-9]+$");
							Matcher matcher = pattern
									.matcher(qualityCheckingId);
							if (!matcher.matches()) {
								errorMsgContent.append("��" + (i)
										+ "�У��ʼ챨�����д���ע����ĸ��д��");
							}
						} else {
							errorMsgContent.append("��" + (i) + "�У��ʼ챨�����д���");
						}
						// ��֤ʱ��
						if (StringUtils.isNotEmpty(dateOfManufacture)) {
							Pattern pattern = Pattern
									.compile("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$");
							Matcher matcher = pattern
									.matcher(dateOfManufacture);
							if (!matcher.matches()) {
								errorMsgContent.append("��" + (i)
										+ "�У�����������д����ע����д��ʽ��");
							}
						} else {
							errorMsgContent.append("��" + (i) + "�У����������д���");
						}
						if (StringUtils.isNotEmpty(productionTimeStart)
								&& StringUtils.isNotEmpty(productionTimeEnd)) {
							Pattern pattern1 = Pattern
									.compile("([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$");
							Matcher matcher1 = pattern1
									.matcher(productionTimeStart);
							Pattern pattern2 = Pattern
									.compile("([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$");
							Matcher matcher2 = pattern2
									.matcher(productionTimeEnd);
							if (!matcher1.matches() && !matcher2.matches()) {
								errorMsgContent.append("��" + (i)
										+ "�У�ʱ��ӻ���ʱ������д����ע����д��ʽ��");
							}
						} else {
							errorMsgContent.append("��" + (i) + "�У�ʱ������д���");
						}
						if (StringUtils.isNotEmpty(materielId)) {
							Pattern pattern = Pattern.compile("^[0-9]*$");
							Matcher matcher = pattern.matcher(materielId);
							if (!matcher.matches()) {
								errorMsgContent.append("��" + (i)
										+ "�У����ϱ����д����ע����д��ʽ��");
							} else {
								Materiel mat = new Materiel();
								mat.setMatnr(materielId);
								int count = qualityCheckingDao
										.getMaterielListCount(mat);
								if (count == 0) {
									errorMsgContent.append("��" + (i)
											+ "�У���д�����ϱ�Ų����ڣ���˶Ժ������ԣ�");
								}
							}
						} else {
							errorMsgContent.append("��" + (i) + "�У����ϱ���д���");
						}
						// �ֶθ�ֵ
						if (StringUtils.isEmpty(errorMsgContent.toString())) {
							QualityChecking qualityChecking = new QualityChecking();
							qualityChecking
									.setQualityCheckingId(qualityCheckingId);
							qualityChecking
									.setDateOfManufacture(dateOfManufacture);
							qualityChecking
									.setProductionTimeStart(productionTimeStart);
							qualityChecking
									.setProductionTimeEnd(productionTimeEnd);
							qualityChecking.setMaterielId(materielId);
							Materiel mat = new Materiel();
							mat.setMatnr(materielId);
							List<Materiel> materiels = qualityCheckingDao
									.getMaterielNameList(mat);
							if (materiels.size() != 0) {
								for (Materiel materiel : materiels) {
									qualityChecking.setMaterielName(materiel
											.getMaktx());
								}
							} else {
								qualityChecking.setMaterielName("");
							}
							qualityCheckingList.add(qualityChecking);
						}
					}
				}
			}
			map.put("resultMessage", errorMsgContent.toString());
			map.put("qualityCheckingList", qualityCheckingList);
			return map;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @Description: ��ȡ����е�ֵ
	 * @author:xg.chen
	 * @date:2017��5��9�� ����11:21:52
	 * @param cell
	 * @return
	 * @version:1.0
	 */
	private String getValue(Cell cell) {
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(cell.getStringCellValue().replace("\n", "")
					.trim());
		} else {
			return "";
		}
	}

	@Override
	public int creatQualityChecking(QualityChecking qualityChecking1) {
		try{
			qualityCheckingDao.creatQualityChecking(qualityChecking1);
			return 1;
		}catch(Exception e){
			logger.error(e);
		}
		return 0;
	}

	@Override
	public int getQualityCheckingCount(QualityChecking qualityCheck) {
		try {
			return qualityCheckingDao.getQualityCheckingCount(qualityCheck);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public List<QualityChecking> getQualityCheckingJsonList(
			QualityChecking qualityCheck) {
		try {
			return qualityCheckingDao.getQualityCheckingJsonList(qualityCheck);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public int updateQualityCheckingUploadFile(QualityChecking qualityCheck) {
		try {
			qualityCheckingDao.updateQualityCheckingUploadFile(qualityCheck);
			return 1;
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public QualityChecking getQualityCheckingName(String id) {
		try {
			return qualityCheckingDao.getQualityCheckingName(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public boolean getOfficeRole(String userId, String string) {
		try {
			int i = qualityCheckingDao.getOfficeRole(userId,string);
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

}
