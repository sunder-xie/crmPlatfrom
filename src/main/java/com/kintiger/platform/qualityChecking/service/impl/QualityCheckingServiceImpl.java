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
 * @Description:服务端实现类
 * @author:xg.chen
 * @time:2017年5月8日 下午3:01:14
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
	 * @Description:物料总数统计
	 * @author:xg.chen
	 * @date:2017年5月8日 下午4:26:58
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
	 * @Description:物料列表
	 * @author:xg.chen
	 * @date:2017年5月8日 下午4:27:07
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
	 * @Description:导入数据验证
	 * @author:xg.chen
	 * @date:2017年5月9日 上午11:12:03
	 * @param uploadFile
	 * @return
	 * @version:1.0
	 */
	@Override
	public Map<String, Object> qualityCheckingImportCsv(File uploadFile) {
		// 全局变量设置
		Map<String, Object> map = new HashMap<String, Object>();
		List<QualityChecking> qualityCheckingList = new ArrayList<QualityChecking>();
		Workbook workbook = null;
		Sheet sheet = null;
		StringBuilder errorMsgContent = new StringBuilder();
		// 读取导入列表
		try {
			// 读取工作表
			workbook = new HSSFWorkbook(new FileInputStream(uploadFile));
			// 获取表头
			String[] header = new String[5];
			header[0] = "质检报告编号";
			header[1] = "生产日期(格式：2017-05-09)";
			header[2] = "时间从(格式：10:00:01)";
			header[3] = "时间至(格式：10:00:01)";
			header[4] = "物料";
			// 开始读取数据表格
			sheet = workbook.getSheetAt(0);
			int row = sheet.getLastRowNum();
			if (row == 0) {
				errorMsgContent.append("导入的Excel为空！</br>");
			} else if (sheet.getRow(0).getLastCellNum() != header.length) {
				errorMsgContent.append("导入的Excel列数与下载文件列数不一致!</br>");
			} else {
				// 将相应的值赋值给页面
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
						// 验证质检编号
						if (StringUtils.isNotEmpty(qualityCheckingId)) {
							Pattern pattern = Pattern.compile("^[A-Z0-9]+$");
							Matcher matcher = pattern
									.matcher(qualityCheckingId);
							if (!matcher.matches()) {
								errorMsgContent.append("第" + (i)
										+ "行：质检报告编号有错误，注意字母大写！");
							}
						} else {
							errorMsgContent.append("第" + (i) + "行：质检报告编号有错误");
						}
						// 验证时间
						if (StringUtils.isNotEmpty(dateOfManufacture)) {
							Pattern pattern = Pattern
									.compile("^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$");
							Matcher matcher = pattern
									.matcher(dateOfManufacture);
							if (!matcher.matches()) {
								errorMsgContent.append("第" + (i)
										+ "行：生产日期填写错误，注意填写格式！");
							}
						} else {
							errorMsgContent.append("第" + (i) + "行：生产日期有错误");
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
								errorMsgContent.append("第" + (i)
										+ "行：时间从或者时间至填写错误，注意填写格式！");
							}
						} else {
							errorMsgContent.append("第" + (i) + "行：时间从至有错误");
						}
						if (StringUtils.isNotEmpty(materielId)) {
							Pattern pattern = Pattern.compile("^[0-9]*$");
							Matcher matcher = pattern.matcher(materielId);
							if (!matcher.matches()) {
								errorMsgContent.append("第" + (i)
										+ "行：物料编号填写错误，注意填写格式！");
							} else {
								Materiel mat = new Materiel();
								mat.setMatnr(materielId);
								int count = qualityCheckingDao
										.getMaterielListCount(mat);
								if (count == 0) {
									errorMsgContent.append("第" + (i)
											+ "行：填写的物料编号不存在，请核对后在重试！");
								}
							}
						} else {
							errorMsgContent.append("第" + (i) + "行：物料编号有错误");
						}
						// 字段赋值
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
	 * @Description: 获取表格中的值
	 * @author:xg.chen
	 * @date:2017年5月9日 上午11:21:52
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
