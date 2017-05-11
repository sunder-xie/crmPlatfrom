package com.kintiger.platform.sms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.sap.SAPConnectionBean;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnr.service.impl.KunnrServiceImpl;
import com.kintiger.platform.sms.dao.ISmsDao;
import com.kintiger.platform.sms.pojo.PaymentNoticeInfo;
import com.kintiger.platform.sms.pojo.Sms;
import com.kintiger.platform.sms.pojo.Station;
import com.kintiger.platform.sms.service.ISmsService;
import com.sap.mw.jco.JCO;

public class SmsServiceImpl implements ISmsService {
	private TransactionTemplate transactionTemplate;	
	private SAPConnectionBean sapConnection;
	private static Log logger = LogFactory.getLog(KunnrServiceImpl.class);
	private ISmsDao smsDao;

	public ISmsDao getSmsDao() {
		return smsDao;
	}

	public void setSmsDao(ISmsDao smsDao) {
		this.smsDao = smsDao;
	}

	@Override
	public int getEmpInfoCount(Sms sms) {
		try {
			return smsDao.getEmpInfoCount(sms);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public List<Sms> getEmpInfoList(Sms sms) {
		try {
			return smsDao.getEmpInfoList(sms);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public int getStationJsonListCountForSelect(Station station) {
		try {
			return smsDao.getStationJsonListCountForSelect(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
			return 0;
		}
	}

	@Override
	public List<Station> getStationJsonListForSelect(Station station) {
		try {
			return smsDao.getStationJsonListForSelect(station);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(station), e);
			return null;
		}
	}

	@Override
	public int getPaymentNoticeInfoCount(PaymentNoticeInfo paymentNoticeInfo) {
		try {
			return smsDao.getPaymentNoticeInfoCount(paymentNoticeInfo);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(paymentNoticeInfo), e);
			return 0;
		}
	}

	@Override
	public List<PaymentNoticeInfo> getPaymentNoticeInfoList(PaymentNoticeInfo paymentNoticeInfo) {
		try {
			return smsDao.getPaymentNoticeInfoList(paymentNoticeInfo);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(paymentNoticeInfo), e);
			return null;
		}
	}

	@Override
	public BooleanResult updatePaymentSendDate(
			final List<PaymentNoticeInfo> paymentIdList) {
		BooleanResult booleanResult = new BooleanResult();
		booleanResult = (BooleanResult) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						BooleanResult booleanResult = new BooleanResult();
						booleanResult.setResult(true);						
						try {
							for(PaymentNoticeInfo paymentNoticeInfo:paymentIdList){
								Integer n = smsDao.updatePaymentSendDate(paymentNoticeInfo);
								if(n==null||n==0){
									ts.setRollbackOnly();
									booleanResult.setResult(false);
									booleanResult.setCode("短信发送成功，但发送状态更新失败！");
									return booleanResult;
								}
							}
						} catch (Exception e) {
							logger.error(e);
							ts.setRollbackOnly();
							booleanResult.setResult(false);
							booleanResult.setCode("短信发送成功，但发送状态更新失败！");
							return booleanResult;
						}
						booleanResult.setCode("短信发送成功！");
						return booleanResult;
					}
					
				});
		return booleanResult;
	}



	@Override
	public PaymentNoticeInfo getMaxBelnrByPayment(PaymentNoticeInfo paymentNoticeInfo) {
		try {
			return smsDao.getMaxBelnrByPayment(paymentNoticeInfo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	


	@Override
	public StringResult synchPayment(
			List<PaymentNoticeInfo> paymentNoticeInfoList) {
		JCO.Client client = null;
		StringResult result = new StringResult();

		List<PaymentNoticeInfo>	paymentNoticeInfoList2 = new ArrayList<PaymentNoticeInfo>();
		
			for(PaymentNoticeInfo paymentNoticeInfo:paymentNoticeInfoList){
				try {
					client = sapConnection.getSAPClientFromPool();
					sapConnection.setFuncName("ZRFC_EXP_DXPT");
					JCO.Function func = sapConnection.getFunction(client);
					JCO.ParameterList input = func.getImportParameterList(); 
					JCO.ParameterList output = func.getTableParameterList(); 
					input.getField("P_BUKRS").setValue(paymentNoticeInfo.getBukrs());
					input.getField("P_GJAHR").setValue(paymentNoticeInfo.getBank_etydat());
					input.getField("P_BELNR").setValue(paymentNoticeInfo.getBank_belnr());
					client.execute(func);
					JCO.Table exportTable = output.getTable(0);
							//.getTableParameterList().getTable(
							//"ZEXP_DXPTDATA");
					if(exportTable!=null){
						for (int i = 0; i < exportTable.getNumRows(); i++, exportTable.nextRow() ) {
							String bukrs = exportTable.getValue("BUKRS")==null ? "" : exportTable.getValue("BUKRS").toString();// 客户端
							String kunnr = exportTable.getValue("KUNNR")==null ? "" : exportTable.getValue("KUNNR").toString();// 语言代码
							Date etydat = exportTable.getDate("ETYDAT");
							String trsamtd = exportTable.getValue("TRSAMTD")==null ? "" : exportTable.getValue("TRSAMTD").toString();// 名称
							String belnr = exportTable.getValue("BELNR")==null ? "" : exportTable.getValue("BELNR").toString();
							PaymentNoticeInfo paymentNoticeInfo2 = new PaymentNoticeInfo();
							paymentNoticeInfo2.setBukrs(bukrs);
							if(kunnr!=null){
								String KK = kunnr.substring(2);
								paymentNoticeInfo2.setKunnr(KK);
							}
							if(etydat!=null){
								SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
								String date = sdf.format(etydat);
								paymentNoticeInfo2.setBank_etydat(date);	
							}
							BigDecimal bd=new BigDecimal(trsamtd); 
							paymentNoticeInfo2.setBank_trsamtd(bd);
							paymentNoticeInfo2.setBank_belnr(belnr);
							paymentNoticeInfoList2.add(paymentNoticeInfo2);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					result.setCode(ISmsService.ERROR);
					e.printStackTrace();
					logger.error(e);
					return result;
				} finally {
					if (client != null)
						JCO.releaseClient(client);// 释放client回连接池
				}
			}
			try {
				if(paymentNoticeInfoList2.size()>0){
					smsDao.insertPayment(paymentNoticeInfoList2);
				}
				result.setCode(ISmsService.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				result.setCode(ISmsService.ERROR);
			}
		return result;
	}
	
	public List<KunnrBusiness> getKunnrBusinessByKunnrId(String kunnr){
		try {
			return smsDao.getKunnrBusinessByKunnrId(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	@Override
	public StringResult importEmpMobile(String fileName, File fileContent) {
		StringResult result = new StringResult();
		List<Sms> smsList = new ArrayList<Sms>();
		Workbook workbook = null;
		Sheet sheet = null;
		try {
			if ("xls".equalsIgnoreCase(fileName.substring(fileName
					.lastIndexOf(".") + 1))) {
				workbook = new HSSFWorkbook(new FileInputStream(fileContent));
			} else if ("xlsx".equalsIgnoreCase(fileName.substring(fileName
					.lastIndexOf(".") + 1))) {
				workbook = new XSSFWorkbook(new FileInputStream(fileContent));
			} else {
				result.setCode(ISmsService.ERROR);
				result.setResult("不支持的文件类型!");
				return result;
			}

			sheet = workbook.getSheetAt(0);
			String sheetName = sheet.getSheetName();
			if("经销商手机号修改模板".equals(sheetName)){
				result =  importKunnrMobile(fileName,fileContent);
				return result;
			}
			
			
			String[] header = new String[6];

			header[0] = "组织";
			header[1] = "岗位";
			header[2] = "员工ID";
			header[3] = "员工名称";
			header[4] = "公司手机号码";
			header[5] = "私人手机号码";
			


			int rows = sheet.getLastRowNum();
			if (rows == 0) {
				result.setCode(ISmsService.ERROR);
				result.setResult("导入的Excel为空！");
				return result;
			} else if (sheet.getRow(0).getLastCellNum() != header.length) {
				result.setCode(ISmsService.ERROR);
				result.setResult("导入的Excel列数与下载文件列数不一致!");
				return result;
			} else {
				StringBuilder headResult = new StringBuilder();
				for (int i = 0; i < header.length; i++) {
					if (!header[i].equals(getValue(sheet.getRow(0).getCell(i)))) {
						if (headResult.length() > 0) {
							headResult.append("</br>");
						}
						headResult.append("第").append(i + 1).append("列")
								.append(getValue(sheet.getRow(0).getCell(i)))
								.append("与模板中").append(header[i]).append("不一致");
					}
				}
				if (headResult.length() > 0) {
					result.setCode(ISmsService.ERROR);
					result.setResult(headResult.toString());
					return result;
				}
				StringBuilder errorMsgContent = new StringBuilder();
				// StringBuilder infoMsgContent = new StringBuilder();
				for (int i = 1; i < rows+1; i++) {
					Sms smsInsert = new Sms();

					String orgName = "";
					String stationName = "";
					String userId = "";
					String userName = "";
					String bus_mobilephone = "";
					String pri_mobilephone = "";
					
					
					orgName = getValue(sheet.getRow(i).getCell(
							header.length - 6));					
					stationName = getValue(sheet.getRow(i).getCell(
							header.length - 5));
					userId = getValue(sheet.getRow(i).getCell(
							header.length - 4));
					userName = getValue(sheet.getRow(i).getCell(
							header.length - 3));
					bus_mobilephone = getValue(sheet.getRow(i).getCell(
							header.length - 2));
					pri_mobilephone = getValue(sheet.getRow(i).getCell(
							header.length - 1));

					if (StringUtils.isEmpty(userId)) {
						if (errorMsgContent.length() > 0) {
							errorMsgContent.append("</br>");
						}
						errorMsgContent.append("第" + (i + 1)
								+ "行,员工ID必须填写，请确认!");
					}
					if (StringUtils.isNotEmpty(bus_mobilephone)){
						if(!StringUtils.isNumeric(bus_mobilephone)||bus_mobilephone.length()!=11){
							if (errorMsgContent.length() > 0) {
								errorMsgContent.append("</br>");
							}
							errorMsgContent.append("第" + (i + 1)
									+ "行,公司手机号码格式或位数不正确（11位数字），请确认!");
						}
					}
					if (StringUtils.isNotEmpty(pri_mobilephone)){
						if(!StringUtils.isNumeric(pri_mobilephone)||pri_mobilephone.length()!=11){
							if (errorMsgContent.length() > 0) {
								errorMsgContent.append("</br>");
							}
							errorMsgContent.append("第" + (i + 1)
									+ "行,私人手机号码格式或位数不正确（11位数字），请确认!");
						}
					}
					if (errorMsgContent.length() == 0) {
						smsInsert.setOrgName(orgName);
						smsInsert.setStationName(stationName);
						smsInsert.setUserId(Integer.valueOf(userId));
						smsInsert.setUserName(userName);
						smsInsert.setBus_mobilephone(bus_mobilephone);
						smsInsert.setPri_mobilephone(pri_mobilephone);
						smsList.add(smsInsert);
					}
				}
				if (errorMsgContent.length() > 0) {
					result.setCode(ISmsService.ERROR);
					result.setResult(errorMsgContent.toString());
					return result;
				}
				result = importEmpMobile(smsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result.setCode(ISmsService.ERROR);
			result.setResult("导入失败!");
			return result;
		}
		return result;
	}
	
	private StringResult importKunnrMobile(String fileName, File fileContent) {
		StringResult result = new StringResult();
		List<Sms> smsList = new ArrayList<Sms>();
		Workbook workbook = null;
		Sheet sheet = null;
		try {
			if ("xls".equalsIgnoreCase(fileName.substring(fileName
					.lastIndexOf(".") + 1))) {
				workbook = new HSSFWorkbook(new FileInputStream(fileContent));
			} else if ("xlsx".equalsIgnoreCase(fileName.substring(fileName
					.lastIndexOf(".") + 1))) {
				workbook = new XSSFWorkbook(new FileInputStream(fileContent));
			} else {
				result.setCode(ISmsService.ERROR);
				result.setResult("不支持的文件类型!");
				return result;
			}

			String[] header = new String[3];

			header[0] = "客户代码";
			header[1] = "客户名称";
			header[2] = "联系手机号码";
			

			sheet = workbook.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			if (rows == 0) {
				result.setCode(ISmsService.ERROR);
				result.setResult("导入的Excel为空！");
				return result;
			} else if (sheet.getRow(0).getLastCellNum() != header.length) {
				result.setCode(ISmsService.ERROR);
				result.setResult("导入的Excel列数与下载文件列数不一致!");
				return result;
			} else {
				StringBuilder headResult = new StringBuilder();
				for (int i = 0; i < header.length; i++) {
					if (!header[i].equals(getValue(sheet.getRow(0).getCell(i)))) {
						if (headResult.length() > 0) {
							headResult.append("</br>");
						}
						headResult.append("第").append(i + 1).append("列")
								.append(getValue(sheet.getRow(0).getCell(i)))
								.append("与模板中").append(header[i]).append("不一致");
					}
				}
				if (headResult.length() > 0) {
					result.setCode(ISmsService.ERROR);
					result.setResult(headResult.toString());
					return result;
				}
				StringBuilder errorMsgContent = new StringBuilder();
				// StringBuilder infoMsgContent = new StringBuilder();
				for (int i = 1; i < rows+1; i++) {
					Sms smsInsert = new Sms();

					String kunnr = "";
					String kunnrName = "";
					String mobile = "";
					
					
					kunnr = getValue(sheet.getRow(i).getCell(
							header.length - 3));
					kunnrName = getValue(sheet.getRow(i).getCell(
							header.length - 2));
					mobile = getValue(sheet.getRow(i).getCell(
							header.length - 1));

					if (StringUtils.isEmpty(kunnr)) {
						if (errorMsgContent.length() > 0) {
							errorMsgContent.append("</br>");
						}
						errorMsgContent.append("第" + (i + 1)
								+ "行,客户代码必须填写，请确认!");
					}
					if (StringUtils.isNotEmpty(mobile)){
						if(!StringUtils.isNumeric(mobile)||mobile.length()!=11){
							if (errorMsgContent.length() > 0) {
								errorMsgContent.append("</br>");
							}
							errorMsgContent.append("第" + (i + 1)
									+ "行,手机号码格式或位数不正确（11位数字），请确认!");
						}
					}
					if (errorMsgContent.length() == 0) {
						smsInsert.setKunnr(kunnr);
						smsInsert.setUserName(kunnrName);
						smsInsert.setMobile(mobile);
						smsList.add(smsInsert);
					}
				}
				if (errorMsgContent.length() > 0) {
					result.setCode(ISmsService.ERROR);
					result.setResult(errorMsgContent.toString());
					return result;
				}
				result = importKunnrMobile(smsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			result.setCode(ISmsService.ERROR);
			result.setResult("导入失败!");
			return result;
		}
		return result;
	}

	private StringResult importKunnrMobile(final List<Sms> smsList) {
		StringResult stringResult = new StringResult();
		stringResult = (StringResult) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						StringResult stringResult = new StringResult();
						stringResult.setResult("手机号码修改成功！");
						try {
							for (Sms sms : smsList) {
								Integer a = smsDao.updateKunnrMobile1(sms);
								if (a == null || a == 0) {
									stringResult.setCode(ISmsService.ERROR);
									stringResult.setResult("数据写入失败！请检查客户代码为"
											+ sms.getKunnr()
											+ "的数据！（注意！经销商状态为修改中的，无法修改手机号！）");
									ts.setRollbackOnly();
									return stringResult;
								}
								// 查询basis.basis_tb_kunnremp_info表中是否有此经销商代码
								Integer count = smsDao.getCountInKunnrEmpInfo(sms);
								if (count != null && count > 0) {
									Integer b = smsDao.updateKunnrMobile2(sms);
									if (b == null || b == 0) {
										stringResult.setCode(ISmsService.ERROR);
										stringResult
												.setResult("数据写入失败！请检查客户代码为"
														+ sms.getKunnr()
														+ "的数据！");
										ts.setRollbackOnly();
										return stringResult;
									}
								}
							}
						} catch (Exception e) {
							ts.setRollbackOnly();
							stringResult.setCode(ISmsService.ERROR);
							stringResult.setResult("数据写入失败！");
							e.printStackTrace();
							logger.error(e);
							return stringResult;
						}
						stringResult.setCode(ISmsService.SUCCESS);
						return stringResult;
					}
				});
		return stringResult;
	}

	private StringResult importEmpMobile(final List<Sms> smsList) {
		StringResult stringResult = new StringResult();
		stringResult = (StringResult) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						StringResult stringResult = new StringResult();
						stringResult.setResult("手机号码修改成功！");
						try {
							for (Sms sms : smsList) {
								Integer a = smsDao.updateEmpMobile(sms);
								if(a==null||a==0){
									stringResult.setCode(ISmsService.ERROR);
									stringResult.setResult("数据写入失败！请检查员工ID为"+
									sms.getUserId()+"的数据！");
									ts.setRollbackOnly();
									return stringResult;
								}
							}
						} catch (Exception e) {
							ts.setRollbackOnly();
							stringResult.setCode(ISmsService.ERROR);
							stringResult.setResult("数据写入失败！");
							e.printStackTrace();
							logger.error(e);
							return stringResult;
						}
						stringResult.setCode(ISmsService.SUCCESS);
						return stringResult;
					}
				});
		return stringResult;
	}

	private String getValue(Cell cell) {
		if (cell != null) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(cell.getStringCellValue().replace("\n", "")
					.trim());
		} else {
			return "";
		}

	}
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}

	@Override
	public StringResult saveGroup(final List<Sms> groupList) {
		StringResult stringResult = new StringResult();
		try {
			smsDao.saveGroup(groupList);
			stringResult.setCode(ISmsService.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			stringResult.setCode(ISmsService.ERROR);
			logger.error(e);
		}
		return stringResult;
	}

	@Override
	public List<Sms> getGroups(Sms group) {
		try {
			return smsDao.getGroups(group);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<Sms> getSelectedGroupInfo(Sms group) {
		try {
			return smsDao.getSelectedGroupInfo(group);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public StringResult checkGroupName(Sms group) {
		StringResult stringResult = new StringResult();
		try {
			Integer count = smsDao.checkGroupName(group);
			if(count>0){
				stringResult.setCode(ISmsService.ERROR);
			}else{
				stringResult.setCode(ISmsService.SUCCESS);
			}
			stringResult.setResult(ISmsService.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			stringResult.setResult(ISmsService.ERROR);
			stringResult.setCode(ISmsService.ERROR);
		}
		
		return stringResult;
	}

	@Override
	public StringResult deleteGroup(Sms group) {
		StringResult stringResult = new StringResult();
		try {
			Long count = smsDao.deleteGroup(group);
			if(count==0){
				stringResult.setCode(ISmsService.ERROR);
			}else{
				stringResult.setCode(ISmsService.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(e);
			stringResult.setCode(ISmsService.ERROR);
		}
		return stringResult;
	}

	@Override
	public StringResult modifyGroup(final Sms group, final List<Sms> groupList) {
		StringResult stringResult = new StringResult();
		stringResult = (StringResult) transactionTemplate
				.execute(new TransactionCallback() {
					public Object doInTransaction(TransactionStatus ts) {
						StringResult stringResult = new StringResult();
						try {
							Long count = smsDao.deleteGroup(group);
							if(count==0){
								ts.setRollbackOnly();
								stringResult.setCode(ISmsService.ERROR);
								return stringResult;
							}else{
								smsDao.saveGroup(groupList);
							}
						} catch (Exception e) {
							ts.setRollbackOnly();
							stringResult.setCode(ISmsService.ERROR);
							e.printStackTrace();
							logger.error(e);
							return stringResult;
						}
						stringResult.setCode(ISmsService.SUCCESS);
						return stringResult;
					}
				});
		return stringResult;
	}

	
}
