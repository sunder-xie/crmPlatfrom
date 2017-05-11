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
									booleanResult.setCode("���ŷ��ͳɹ���������״̬����ʧ�ܣ�");
									return booleanResult;
								}
							}
						} catch (Exception e) {
							logger.error(e);
							ts.setRollbackOnly();
							booleanResult.setResult(false);
							booleanResult.setCode("���ŷ��ͳɹ���������״̬����ʧ�ܣ�");
							return booleanResult;
						}
						booleanResult.setCode("���ŷ��ͳɹ���");
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
							String bukrs = exportTable.getValue("BUKRS")==null ? "" : exportTable.getValue("BUKRS").toString();// �ͻ���
							String kunnr = exportTable.getValue("KUNNR")==null ? "" : exportTable.getValue("KUNNR").toString();// ���Դ���
							Date etydat = exportTable.getDate("ETYDAT");
							String trsamtd = exportTable.getValue("TRSAMTD")==null ? "" : exportTable.getValue("TRSAMTD").toString();// ����
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
						JCO.releaseClient(client);// �ͷ�client�����ӳ�
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
				result.setResult("��֧�ֵ��ļ�����!");
				return result;
			}

			sheet = workbook.getSheetAt(0);
			String sheetName = sheet.getSheetName();
			if("�������ֻ����޸�ģ��".equals(sheetName)){
				result =  importKunnrMobile(fileName,fileContent);
				return result;
			}
			
			
			String[] header = new String[6];

			header[0] = "��֯";
			header[1] = "��λ";
			header[2] = "Ա��ID";
			header[3] = "Ա������";
			header[4] = "��˾�ֻ�����";
			header[5] = "˽���ֻ�����";
			


			int rows = sheet.getLastRowNum();
			if (rows == 0) {
				result.setCode(ISmsService.ERROR);
				result.setResult("�����ExcelΪ�գ�");
				return result;
			} else if (sheet.getRow(0).getLastCellNum() != header.length) {
				result.setCode(ISmsService.ERROR);
				result.setResult("�����Excel�����������ļ�������һ��!");
				return result;
			} else {
				StringBuilder headResult = new StringBuilder();
				for (int i = 0; i < header.length; i++) {
					if (!header[i].equals(getValue(sheet.getRow(0).getCell(i)))) {
						if (headResult.length() > 0) {
							headResult.append("</br>");
						}
						headResult.append("��").append(i + 1).append("��")
								.append(getValue(sheet.getRow(0).getCell(i)))
								.append("��ģ����").append(header[i]).append("��һ��");
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
						errorMsgContent.append("��" + (i + 1)
								+ "��,Ա��ID������д����ȷ��!");
					}
					if (StringUtils.isNotEmpty(bus_mobilephone)){
						if(!StringUtils.isNumeric(bus_mobilephone)||bus_mobilephone.length()!=11){
							if (errorMsgContent.length() > 0) {
								errorMsgContent.append("</br>");
							}
							errorMsgContent.append("��" + (i + 1)
									+ "��,��˾�ֻ������ʽ��λ������ȷ��11λ���֣�����ȷ��!");
						}
					}
					if (StringUtils.isNotEmpty(pri_mobilephone)){
						if(!StringUtils.isNumeric(pri_mobilephone)||pri_mobilephone.length()!=11){
							if (errorMsgContent.length() > 0) {
								errorMsgContent.append("</br>");
							}
							errorMsgContent.append("��" + (i + 1)
									+ "��,˽���ֻ������ʽ��λ������ȷ��11λ���֣�����ȷ��!");
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
			result.setResult("����ʧ��!");
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
				result.setResult("��֧�ֵ��ļ�����!");
				return result;
			}

			String[] header = new String[3];

			header[0] = "�ͻ�����";
			header[1] = "�ͻ�����";
			header[2] = "��ϵ�ֻ�����";
			

			sheet = workbook.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			if (rows == 0) {
				result.setCode(ISmsService.ERROR);
				result.setResult("�����ExcelΪ�գ�");
				return result;
			} else if (sheet.getRow(0).getLastCellNum() != header.length) {
				result.setCode(ISmsService.ERROR);
				result.setResult("�����Excel�����������ļ�������һ��!");
				return result;
			} else {
				StringBuilder headResult = new StringBuilder();
				for (int i = 0; i < header.length; i++) {
					if (!header[i].equals(getValue(sheet.getRow(0).getCell(i)))) {
						if (headResult.length() > 0) {
							headResult.append("</br>");
						}
						headResult.append("��").append(i + 1).append("��")
								.append(getValue(sheet.getRow(0).getCell(i)))
								.append("��ģ����").append(header[i]).append("��һ��");
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
						errorMsgContent.append("��" + (i + 1)
								+ "��,�ͻ����������д����ȷ��!");
					}
					if (StringUtils.isNotEmpty(mobile)){
						if(!StringUtils.isNumeric(mobile)||mobile.length()!=11){
							if (errorMsgContent.length() > 0) {
								errorMsgContent.append("</br>");
							}
							errorMsgContent.append("��" + (i + 1)
									+ "��,�ֻ������ʽ��λ������ȷ��11λ���֣�����ȷ��!");
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
			result.setResult("����ʧ��!");
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
						stringResult.setResult("�ֻ������޸ĳɹ���");
						try {
							for (Sms sms : smsList) {
								Integer a = smsDao.updateKunnrMobile1(sms);
								if (a == null || a == 0) {
									stringResult.setCode(ISmsService.ERROR);
									stringResult.setResult("����д��ʧ�ܣ�����ͻ�����Ϊ"
											+ sms.getKunnr()
											+ "�����ݣ���ע�⣡������״̬Ϊ�޸��еģ��޷��޸��ֻ��ţ���");
									ts.setRollbackOnly();
									return stringResult;
								}
								// ��ѯbasis.basis_tb_kunnremp_info�����Ƿ��д˾����̴���
								Integer count = smsDao.getCountInKunnrEmpInfo(sms);
								if (count != null && count > 0) {
									Integer b = smsDao.updateKunnrMobile2(sms);
									if (b == null || b == 0) {
										stringResult.setCode(ISmsService.ERROR);
										stringResult
												.setResult("����д��ʧ�ܣ�����ͻ�����Ϊ"
														+ sms.getKunnr()
														+ "�����ݣ�");
										ts.setRollbackOnly();
										return stringResult;
									}
								}
							}
						} catch (Exception e) {
							ts.setRollbackOnly();
							stringResult.setCode(ISmsService.ERROR);
							stringResult.setResult("����д��ʧ�ܣ�");
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
						stringResult.setResult("�ֻ������޸ĳɹ���");
						try {
							for (Sms sms : smsList) {
								Integer a = smsDao.updateEmpMobile(sms);
								if(a==null||a==0){
									stringResult.setCode(ISmsService.ERROR);
									stringResult.setResult("����д��ʧ�ܣ�����Ա��IDΪ"+
									sms.getUserId()+"�����ݣ�");
									ts.setRollbackOnly();
									return stringResult;
								}
							}
						} catch (Exception e) {
							ts.setRollbackOnly();
							stringResult.setCode(ISmsService.ERROR);
							stringResult.setResult("����д��ʧ�ܣ�");
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
