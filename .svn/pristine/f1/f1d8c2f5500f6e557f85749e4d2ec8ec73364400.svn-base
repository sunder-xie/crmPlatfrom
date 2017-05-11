package com.kintiger.platform.kunnrBusinessContact.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.distributionGoal.service.IDistributionGoalService;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.kunnrBusinessContact.dao.IKunnrBusinessDao;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdjustment;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdujstDetail;
import com.kintiger.platform.kunnrBusinessContact.service.IKunnrBusinessService;

public class KunnrBusinessServiceImpl implements IKunnrBusinessService {

	private static Log logger = LogFactory
			.getLog(KunnrBusinessServiceImpl.class);
	private IKunnrBusinessDao kunnrBusinessDao;

	@Override
	public KunnrBusiness getKunnrBusiness(Kunnr kunnr) {
		KunnrBusiness kbc = new KunnrBusiness();
		try {
			kbc = kunnrBusinessDao.getKunnrBusiness(kunnr);
		} catch (Exception e) {
			logger.error(kunnr, e);
			return null;
		}
		return kbc;
	}

	public IKunnrBusinessDao getKunnrBusinessDao() {
		return kunnrBusinessDao;
	}

	public void setKunnrBusinessDao(IKunnrBusinessDao kunnrBusinessDao) {
		this.kunnrBusinessDao = kunnrBusinessDao;
	}

	@Override
	public StringResult updateKunnrBusiness(KunnrBusiness kunnrBusiness) {
		StringResult result = new StringResult();
		result.setCode(IDistributionGoalService.ERROR);
		result.setResult(IDistributionGoalService.ERROR_MESSAGE);
		try {
			int c = kunnrBusinessDao.updateKunnrBusiness(kunnrBusiness);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionGoalService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(kunnrBusiness), e);
		}
		return result;
	}

	public List<KunnrBusiness> getKunnrBusinessManagerList(
			KunnrBusiness kunnrBusiness) {
		try {
			return kunnrBusinessDao.getKunnrBusinessManagerList(kunnrBusiness);
		} catch (Exception e) {
			logger.error(kunnrBusiness, e);
			return null;
		}
	}

	public List<KunnrBusiness> getKunnrBusinessHeadList(
			KunnrBusiness kunnrBusiness) {
		try {
			return kunnrBusinessDao.getKunnrBusinessHeadList(kunnrBusiness);
		} catch (Exception e) {
			logger.error(kunnrBusiness, e);
			return null;
		}
	}

	public List<KunnrBusiness> getKunnrBusinessAgentList(
			KunnrBusiness kunnrBusiness) {
		try {
			return kunnrBusinessDao.getKunnrBusinessAgentList(kunnrBusiness);
		} catch (Exception e) {
			logger.error(kunnrBusiness, e);
			return null;
		}
	}

	public int updateHead(KunnrBusiness kunnrBusiness) {
		try {
			return kunnrBusinessDao.updateHead(kunnrBusiness);
		} catch (Exception e) {
			logger.error(kunnrBusiness, e);
			return 0;
		}
	}

	public int updateAgent(KunnrBusiness kunnrBusiness) {
		try {
			return kunnrBusinessDao.updateAgent(kunnrBusiness);
		} catch (Exception e) {
			logger.error(kunnrBusiness, e);
			return 0;
		}
	}

	public int updateBusinessManager(KunnrBusiness kunnrBusiness) {
		try {
			return kunnrBusinessDao.updateBusinessManager(kunnrBusiness);
		} catch (Exception e) {
			logger.error(kunnrBusiness, e);
			return 0;
		}
	}

	public int createHead(KunnrBusiness kunnrBusiness) {
		try {
			return kunnrBusinessDao.createHead(kunnrBusiness);
		} catch (Exception e) {
			logger.error(kunnrBusiness, e);
			return 0;
		}
	}

	public int createBusinessManager(KunnrBusiness kunnrBusiness) {
		try {
			return kunnrBusinessDao.createBusinessManager(kunnrBusiness);
		} catch (Exception e) {
			logger.error(kunnrBusiness, e);
			return 0;
		}
	}

	public int createAgent(KunnrBusiness kunnrBusiness) {
		try {
			return kunnrBusinessDao.createAgent(kunnrBusiness);
		} catch (Exception e) {
			logger.error(kunnrBusiness, e);
			return 0;
		}
	}

	public List<String> getKunnrIdByHeadOrAgent(String userId) {
		try {
			return kunnrBusinessDao.getKunnrIdByHeadOrAgent(userId);
		} catch (Exception e) {
			logger.error(userId, e);
			return null;
		}
	}

	public List<String> getKunnrIdByCompetent(String userId) {
		try {
			return kunnrBusinessDao.getKunnrIdByCompetent(userId);
		} catch (Exception e) {
			logger.error(userId, e);
			return null;
		}
	}

	public List<String> getKunnrIdByKunnrBusiness(String userId) {
		try {
			return kunnrBusinessDao.getKunnrIdByKunnrBusiness(userId);
		} catch (Exception e) {
			logger.error(userId, e);
			return null;
		}
	}

	public List<String> getKunnrIdByKunnrBusinessByKunag(String userId) {
		try {
			return kunnrBusinessDao.getKunnrIdByKunnrBusinessByKunag(userId);
		} catch (Exception e) {
			logger.error(userId, e);
			return null;
		}
	}

	public List<KunnrBusiness> exportForExcel(Kunnr kunnr) {
		try {
			return kunnrBusinessDao.exportForExcel(kunnr);
		} catch (Exception e) {
			logger.error(kunnr, e);
			return null;
		}
	}

	public int searchKunnrBusinessManagerListCount(KunnrBusiness kunnrBusiness) {
		try {
			return kunnrBusinessDao
					.searchKunnrBusinessManagerListCount(kunnrBusiness);
		} catch (Exception e) {
			logger.error(kunnrBusiness, e);
			return 0;
		}
	}

	public List<KunnrBusiness> searchKunnrBusinessManagerList(
			KunnrBusiness kunnrBusiness) {
		try {
			List<KunnrBusiness> kunnrBusinessList = kunnrBusinessDao
					.searchKunnrBusinessManagerList(kunnrBusiness);
			for (int i = 0; i < kunnrBusinessList.size(); i++) {
				kunnrBusinessList.get(i).setBusinessEndDate(
						kunnrBusiness.getBusinessEndDate());
				List<KunnrBusiness> list1 = kunnrBusinessDao
						.getKunnrBusinessHeadList(kunnrBusinessList.get(i));
				for (int j = 0; j < list1.size(); j++) {
					if (j == 0) {
						kunnrBusinessList.get(i).setBusinessHead(
								list1.get(j).getBusinessHead());
					} else {
						kunnrBusinessList.get(i).setBusinessHead(
								kunnrBusinessList.get(i).getBusinessHead()
										+ "/" + list1.get(j).getBusinessHead());
					}
				}
				List<KunnrBusiness> list2 = kunnrBusinessDao
						.getKunnrBusinessAgentList(kunnrBusinessList.get(i));
				for (int x = 0; x < list2.size(); x++) {
					if (x == 0) {
						kunnrBusinessList.get(i).setBusinessAgent(
								list2.get(x).getBusinessAgent());
					} else {
						kunnrBusinessList.get(i)
								.setBusinessAgent(
										kunnrBusinessList.get(i)
												.getBusinessAgent()
												+ "/"
												+ list2.get(x)
														.getBusinessAgent());
					}
				}
			}
			return kunnrBusinessList;

		} catch (Exception e) {
			logger.error(kunnrBusiness, e);
			return null;
		}
	}

	public int searchKunnrBusinessEmpListCount(KunnrBusiness KunnrBusiness) {
		try {
			return kunnrBusinessDao
					.searchKunnrBusinessEmpListCount(KunnrBusiness);
		} catch (Exception e) {
			logger.error(KunnrBusiness, e);
			return 0;
		}
	}

	public List<KunnrBusiness> searchKunnrBusinessEmpList(
			KunnrBusiness KunnrBusiness) {
		try {
			return kunnrBusinessDao.searchKunnrBusinessEmpList(KunnrBusiness);
		} catch (Exception e) {
			logger.error(KunnrBusiness, e);
			return null;
		}
	}

	public int updateKunnrBusinessEmp(KunnrBusiness KunnrBusiness) {
		try {
			return kunnrBusinessDao.updateKunnrBusinessEmp(KunnrBusiness);
		} catch (Exception e) {
			logger.error(KunnrBusiness, e);
			return 0;
		}
	}

	/**
	 * Title: 统计数据 Description: crmPlatform
	 * 
	 * @author lu
	 * @date 2016年5月17日 下午12:30:46
	 * @param dealerAdjustment
	 * @return
	 */
	@Override
	public int getDealerAdjustmentCount(DealerAdjustment dealerAdjustment) {
		try {
			return kunnrBusinessDao.getDealerAdjustmentCount(dealerAdjustment);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	/**
	 * Title: 获取提报调整事务列表 Description: crmPlatform
	 * 
	 * @author lu
	 * @date 2016年5月17日 下午12:30:52
	 * @param dealerAdjustment
	 * @return
	 */
	@Override
	public List<DealerAdjustment> getDealerAdjustmentList(
			DealerAdjustment dealerAdjustment) {
		try {
			return kunnrBusinessDao.getDealerAdjustmentList(dealerAdjustment);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * Title: 导入的明细表处理 Description: crmPlatform
	 * 
	 * @author lu
	 * @date 2016年7月14日 下午3:22:44
	 * @param uploadFile
	 *            文件名
	 * @param userId
	 *            用户ID
	 * @param orgId
	 *            用户组织ID
	 * @param KunnId1
	 *            所选的经销商ID
	 * @return
	 */
	@Override
	public Map<String, Object> importDealerAdjustmentXls(File uploadFile,
			String userId, String orgId, String KunnId1, int yearMoth) {
		// 读取数据表全局设置
		Map<String, Object> map = new HashMap<String, Object>();
		Workbook workbook = null;
		Sheet sheet = null;
		StringBuilder errorMsgContent = new StringBuilder();
		List<DealerAdujstDetail> dealerAdujstDetailList = new ArrayList<DealerAdujstDetail>();
		// 读表逻辑处理
		try {
			// 读取工作表
			workbook = new HSSFWorkbook(new FileInputStream(uploadFile));
			// 获取表头
			String[] header = new String[10];
			header[0] = "调整组织";
			header[1] = "调整经销商代码(注意此处为文本格式，例如：01XX)";
			header[2] = "调整经销商名称";
			header[3] = "调整年(注意此处为文本格式，例如：2016)";
			header[4] = "调整月(注意此处为文本格式，例如：06或10)";
			header[5] = "品项";
			header[6] = "品项名称";
			header[7] = "现有销售目标量";
			header[8] = "现有协议目标量";
			header[9] = "调整至协议目标量（标箱）";
			// 开始读取数据表格
			sheet = workbook.getSheetAt(0);
			int row = sheet.getLastRowNum();
			if (row == 0) {
				errorMsgContent.append("导入的Excel为空！</br>");
			} else if (sheet.getRow(0).getLastCellNum() != header.length) {
				errorMsgContent.append("导入的Excel列数与下载文件列数不一致!</br>");
			} else {
				for (int i = 0; i < header.length; i++) {
					if (!header[i].equals(getValue(sheet.getRow(0).getCell(i)))) {
						errorMsgContent.append("第").append(i + 1).append("列")
								.append(getValue(sheet.getRow(0).getCell(i)))
								.append("与模板中").append(header[i]).append("不一致");
					}
				}
				if (errorMsgContent.length() > 0) {
					map.put("resultMessage", errorMsgContent.toString());
					return map;
				}
				// 找到userId所在组织,包括下级组织
				Map<String, String> map1 = new HashMap<String, String>();
				List<AllUsers> users = kunnrBusinessDao.getOrgsByUserId(userId);
				if (null != users) {
					for (int i = 0; i < users.size(); i++) {
						String orgOrgId1 = users.get(i).getOrgId();
						String orgName1 = users.get(i).getOrgName();
						if (null == map1.get(orgName1)) {
							map1.put(orgName1, orgOrgId1);
						}
					}
				}
				// 将相应的值赋值给页面
				for (int i = 1; i < row + 1; i++) {
					// 字段设置
					String orgName = "";
					String kunnrId = "";
					String kunnrName = "";
					String applyYear = "";
					String applyMonth = "";
					String matter = "";
					String matterName = "";
					String nowTarget = "";
					String nowDealerTarget = "";
					String adjustTarget = "";
					// 赋值
					orgName = getValue(sheet.getRow(i).getCell(
							header.length - 10));
					kunnrId = getValue(sheet.getRow(i).getCell(
							header.length - 9));
					kunnrName = getValue(sheet.getRow(i).getCell(
							header.length - 8));
					applyYear = getValue(sheet.getRow(i).getCell(
							header.length - 7));
					applyMonth = getValue(sheet.getRow(i).getCell(
							header.length - 6));
					matter = getValue(sheet.getRow(i)
							.getCell(header.length - 5));
					matterName = getValue(sheet.getRow(i).getCell(
							header.length - 4));
					nowTarget = getValue(sheet.getRow(i).getCell(
							header.length - 3));
					nowDealerTarget = getValue(sheet.getRow(i).getCell(
							header.length - 2));
					adjustTarget = getValue(sheet.getRow(i).getCell(
							header.length - 1));
					// 写入的值的判断
					if (StringUtils.isEmpty(orgName)
							&& StringUtils.isEmpty(kunnrId)
							&& StringUtils.isEmpty(kunnrName)) {// 表格误操作时候去除空数据表格
						break;
					} else {
						// 判断导入的kunnrId是否正确
						if (StringUtils.isNotEmpty(kunnrId)) {
							if (kunnrId.length() < 8) {
								for (int j = 0; j < 8 - kunnrId.length(); j++) {
									kunnrId = "0" + kunnrId;
								}
							}
						}
						// 1、经销商名称+代码+组织必须保持一致
						if (StringUtils.isNotEmpty(orgName)
								&& StringUtils.isNotEmpty(kunnrId)
								&& StringUtils.isNotEmpty(kunnrName)) {
							if (!KunnId1.equals(kunnrId)) {
								errorMsgContent
										.append("第"
												+ (i)
												+ "行：注意:1、此处只能一个一个的导入，不能批量导入同一城市下的多个经销商；</br>"
												+ "2、选择的经销商必须和导入的经销商一致。</br>");
							}
							DealerAdujstDetail detail = new DealerAdujstDetail();
							detail.setOrgId(orgId);
							detail.setOrgName(orgName);
							detail.setKunnrId(kunnrId);
							detail.setKunnrName(kunnrName);
							int count = kunnrBusinessDao
									.getDealerAdjustmentDetailCount(detail);
							if (count == 0) {
								errorMsgContent
										.append("第"
												+ (i)
												+ "行：1、经销商名称、经销商ID(注意：形如01010056)或者组织错误；"
												+ "2、申请条件中选择的组织和导入的组织不匹配。</br>");
							}
						} else {
							errorMsgContent
									.append("第"
											+ (i)
											+ "行：1、经销商名称、经销商ID(注意：形如01010056)或者组织错误！"
											+ "2、申请条件中选择的组织和导入的组织不匹配。</br>");
						}
						// 判断给用户是否有权限导入该明细
						if (map1.get(orgName) == null) {
							errorMsgContent.append("第" + (i + 1)
									+ "行：无导入该组织数据权限!");
							break;
						}
						// 2、只能导入提报当月及以后月份
						if (StringUtils.isNotEmpty(applyYear)
								&& StringUtils.isNotEmpty(applyMonth)) {
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyyMM");
							String dateNow = sdf.format(date);// 当前的时间
							String date1 = "" + applyYear + applyMonth;// 导入的时间
							Pattern p2 = Pattern.compile("^[2]\\d{3}$");// 正则表达式判断年
							Matcher matcher = p2.matcher(applyYear);
							Pattern p1 = Pattern.compile("^[0][1-9]|[1][0-2]$");// 正则表达式判断月
							Matcher matcher1 = p1.matcher(applyMonth);
							if (applyMonth.length() != 2 || !matcher1.matches()) {
								errorMsgContent.append("第" + (i + 1)
										+ "行：申请月错误！(注意：月份形如06或者10)！</br>");
							}
							if (applyYear.length() != 4 || !matcher.matches()) {
								errorMsgContent.append("第" + (i + 1)
										+ "行：申请年错误！(注意：2016或2017)！</br>");
							}
							// 配置数据字典
							if (yearMoth != 0) {
								String yearMonthBef = String.valueOf(yearMoth);// 设置数据字典中的年月
								if (date1.compareTo(yearMonthBef) < 0) {
									errorMsgContent
											.append("第"
													+ (i + 1)
													+ "行：申请年月错误！（导入的数据比设置数据字典中的年月小了，请联系管理员）"
													+ "(注意：月份形如06或者10)！</br>");
								}
							} else {
								if (date1.compareTo(dateNow) < 0) {
									errorMsgContent
											.append("第"
													+ (i + 1)
													+ "行：申请年月只能是当月以及当月以后的月份！(注意：月份形如06或者10)！</br>");
								}
							}
						} else {
							errorMsgContent.append("第" + (i + 1) + "行："
									+ header[3] + "不能为空!(注意：月份形如06或者10)</br>");
						}
						// 品项判断
						if (StringUtils.isNotEmpty(matter)
								&& StringUtils.isNotEmpty(matterName)) {
							DealerAdujstDetail detail = new DealerAdujstDetail();
							detail.setMatter(matter);
							int count = kunnrBusinessDao.getMattercount(detail);
							if (count == 0) {
								errorMsgContent.append("第" + (i + 1)
										+ "行：品项不符合要求！</br>");
							}
						} else {
							errorMsgContent.append("第" + (i + 1) + "行："
									+ header[5] + "不能为空!</br>");
						}
						// 3、调整品项、数量必须大于等于销售目标量
						if (StringUtils.isNotEmpty(nowTarget)
								&& StringUtils.isNotEmpty(nowDealerTarget)
								&& StringUtils.isNotEmpty(adjustTarget)) {
							// 首先判断是否是数字
							Pattern p2 = Pattern.compile("^\\d+(\\.\\d+)?$");
							Matcher matcher = p2.matcher(nowTarget);
							Matcher matcher1 = p2.matcher(nowDealerTarget);
							Matcher matcher2 = p2.matcher(adjustTarget);
							if (!matcher.matches() || !matcher1.matches()
									|| !matcher2.matches()) {
								errorMsgContent.append("第" + (i + 1)
										+ "行：箱数格式错误!</br>");
							}
							// 根据导入的经销商、品项、年月份带出相应的“经销商目标量”，调整目标量必须大于等于销售目标量
							DealerAdujstDetail detail = new DealerAdujstDetail();
							detail.setKunnrId(kunnrId);
							detail.setMatter(matter);
							detail.setApplyYear(applyYear);
							detail.setApplyMonth(applyMonth);
							List<DealerAdujstDetail> detailList = kunnrBusinessDao
									.getDealerAdjustmentDetailKunnr(detail);
							if (detailList.size() != 0) {
								for (DealerAdujstDetail detail1 : detailList) {
									// 2016-08-10修改：由于数据库中的现有的销售目标量小数精度问题，这里使用BigDecimal来进行比较
									BigDecimal a = new BigDecimal(
											detail1.getNowTarget());// 现有的销售目标量
									BigDecimal setScale1 = a.setScale(2,
											BigDecimal.ROUND_HALF_UP);
									BigDecimal b = new BigDecimal(adjustTarget);// 导入协议目标量
									BigDecimal setScale2 = b.setScale(2,
											BigDecimal.ROUND_HALF_UP);
									if (setScale1.compareTo(setScale2) == 1) {
										errorMsgContent.append("第" + (i + 1)
												+ "行：调整目标量不符合要求！</br>");
									}
								}
							}
						} else {
							errorMsgContent.append("第" + (i + 1) + "行："
									+ header[9] + "不能为空!</br>");
						}
						// 字段赋值
						if (StringUtils.isEmpty(errorMsgContent.toString())) {
							DealerAdujstDetail detail = new DealerAdujstDetail();
							detail.setOrgName(orgName);
							detail.setKunnrId(kunnrId);
							detail.setKunnrName(kunnrName);
							detail.setApplyYear(applyYear);
							detail.setApplyMonth(applyMonth);
							detail.setMatter(matter);
							detail.setMatterName(matterName);
							DealerAdujstDetail detail2=kunnrBusinessDao.getMatnrAndMaktx(detail);
							if(detail2 != null){
								detail.setMatnr01(detail2.getMatnr01());
								detail.setMaktx01(detail2.getMaktx01());
							} else {
								detail.setMatnr01("");
								detail.setMaktx01("");
							}
							List<DealerAdujstDetail> detailList = kunnrBusinessDao
									.getDealerAdjustmentDetailKunnr(detail);
							if (detailList.size() != 0) {
								for (DealerAdujstDetail detail1 : detailList) {
									if (detail1.getNowTarget() == null) {
										detail.setNowTarget("0");
									} else {
										detail.setNowTarget(detail1
												.getNowTarget());
									}
									if (detail1.getNowDealerTarget() == null) {
										detail.setNowDealerTarget("0");
									} else {
										detail.setNowDealerTarget(detail1
												.getNowDealerTarget());
									}
								}
							} else {
								detail.setNowTarget("0");
								detail.setNowDealerTarget("0");
							}
							detail.setAdjustTarget(adjustTarget);
							dealerAdujstDetailList.add(detail);
						}
					}
				}
			}
			map.put("resultMessage", errorMsgContent.toString());
			map.put("dealerAdujstDetailList", dealerAdujstDetailList);
			return map;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Title: 获取表格中的值 Description: crmPlatform
	 * 
	 * @author lu
	 * @date 2016年5月19日 下午4:16:57
	 * @param cell
	 * @return
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

	/**
	 * Title: 经销商详单之列表 Description: crmPlatform
	 * 
	 * @author lu
	 * @date 2016年5月24日 上午8:53:23
	 * @param dealerAdujstDetail
	 * @return
	 */
	@Override
	public List<DealerAdujstDetail> getDealerAdjustDetailList(
			DealerAdujstDetail dealerAdujstDetail) {
		try {
			return kunnrBusinessDao
					.getDealerAdjustDetailList(dealerAdujstDetail);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * Title: 事务提交成功后保存事务号 Description: crmPlatform
	 * 
	 * @author lu
	 * @date 2016年5月24日 下午2:37:59
	 * @param dealerAdjustment
	 * @return
	 */
	@Override
	public int updateDealerAdjustment(DealerAdjustment dealerAdjustment) {
		try {
			return kunnrBusinessDao.updateDealerAdjustment(dealerAdjustment);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public String createDealerAdjustMennt(DealerAdjustment dealerAdjustment) {
		try {
			return kunnrBusinessDao.createDealerAdjustMennt(dealerAdjustment);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public String createDealerAdjustDetail(DealerAdujstDetail detail) {
		try {
			return kunnrBusinessDao.createDealerAdjustDetail(detail);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public DealerAdjustment getDealerAdjustmentById(String ids) {
		try {
			return kunnrBusinessDao.getDealerAdjustmentById(ids);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<DealerAdjustment> getDealerAdjustmentListForXls(
			DealerAdjustment dealerAdjustment) {
		try {
			return kunnrBusinessDao
					.getDealerAdjustmentListForXls(dealerAdjustment);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public DealerAdjustment getDealerAdjustment(
			DealerAdjustment dealerAdjustment1) {
		try {
			return kunnrBusinessDao.getDealerAdjustment(dealerAdjustment1);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public int updateDealerAdjustmentById(DealerAdjustment dealerAdjustment) {
		try {
			return kunnrBusinessDao
					.updateDealerAdjustmentById(dealerAdjustment);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public int updateCrmTbTarget(DealerAdujstDetail detail) {
		try {
			return kunnrBusinessDao.updateCrmTbTarget(detail);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public List<DealerAdujstDetail> getKunnrForCrmTarget(
			DealerAdujstDetail dealerAdujstDetail) {
		try {
			return kunnrBusinessDao.getKunnrForCrmTarget(dealerAdujstDetail);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

}
