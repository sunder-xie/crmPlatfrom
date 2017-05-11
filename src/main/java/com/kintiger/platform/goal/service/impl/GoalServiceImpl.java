package com.kintiger.platform.goal.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.goal.dao.IGoalDao;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.goal.pojo.GoalSales;
import com.kintiger.platform.goal.pojo.GoalSalesDetail;
import com.kintiger.platform.goal.pojo.MatterPriceBO;
import com.kintiger.platform.goal.pojo.MatterPriceObject;
import com.kintiger.platform.goal.pojo.OrgHelps;
import com.kintiger.platform.goal.pojo.PrintContractGoalInfo;
import com.kintiger.platform.goal.service.IGoalService;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.Materiel;

public class GoalServiceImpl implements IGoalService {
	private TransactionTemplate transactionTemplate;
	private IGoalDao goalDao;
	private static final Logger logger = Logger
			.getLogger(GoalServiceImpl.class);

	public int getGoalListCount(BCustomerTarget bct) {
		try {
			return goalDao.getGoalListCount(bct);
		} catch (Exception e) {
			logger.error(bct, e);
		}
		return 0;
	}

	public int getGoalListCount1(BCustomerTarget bct) {
		try {
			return goalDao.getGoalListCount1(bct);
		} catch (Exception e) {
			logger.error(bct, e);
		}
		return 0;
	}

	public int getMatListCount(Materiel mat) {
		try {
			return goalDao.getMatListCount(mat);
		} catch (Exception e) {
			logger.error(mat, e);
		}
		return 0;
	}

	public int getOrgCount(Kunnr kunnr) {
		try {
			return goalDao.getOrgCount(kunnr);
		} catch (Exception e) {
			logger.error(kunnr, e);
		}
		return 0;
	}

	public int getKunnrListCount(Kunnr kunnr) {
		try {
			return goalDao.getKunnrListCount(kunnr);
		} catch (Exception e) {
			logger.error(kunnr, e);
		}
		return 0;
	}

	public List<BCustomerTarget> getGoalList(BCustomerTarget bct) {
		List<BCustomerTarget> list = null;
		try {
			list = goalDao.getGoalList(bct);
		} catch (Exception e) {
			logger.error(bct, e);
		}
		return list;

	}

	public List<Materiel> getMatList(Materiel mat) {
		List<Materiel> list = null;
		try {
			list = goalDao.getMatList(mat);
		} catch (Exception e) {
			logger.error(mat, e);
		}
		return list;
	}

	public List<Materiel> getMatList1(Materiel mat) {
		List<Materiel> list = null;
		try {
			list = goalDao.getMatList1(mat);
		} catch (Exception e) {
			logger.error(mat, e);
		}
		return list;
	}

	public BCustomerTarget getGoalById(String ctId) {
		try {
			return goalDao.getGoalById(ctId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public List<Kunnr> getKunnrsList(Kunnr kunnr) {
		List<Kunnr> list = null;
		try {
			list = goalDao.getKunnrList(kunnr);
		} catch (Exception e) {
			logger.error(kunnr, e);
		}
		return list;

	}

	public List<Kunnr> getKunnrsList1(Kunnr kunnr) {
		List<Kunnr> list = null;
		try {
			list = goalDao.getKunnrList1(kunnr);
		} catch (Exception e) {
			logger.error(kunnr, e);
		}
		return list;

	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public BooleanResult insertGoal(final BCustomerTarget bct) {
		BooleanResult result = new BooleanResult();
		// if (goalDao.getGoalListCount1(bct) != 0l) {
		// result.setResult(false);
		// result.setCode(bct.getTheYear() + "年" + bct.getTheMonth() + "月"
		// + "物料号" + bct.getMatter()/* .substring(10, 18) */
		// + "目标值已在数据库存在");
		// } else {
		String goalId = goalDao.insertGoal(bct);
		if (Long.valueOf(goalId) > 0) {
			result.setResult(true);
			result.setCode("数据保存数据库成功");
		} else {
			result.setResult(false);
			result.setCode("数据保存数据库失败.请联系系统管理员");
		}
		// }
		return result;
	}

	public boolean insertGoal1(final BCustomerTarget bct) {
		return (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Boolean doInTransaction(TransactionStatus tran) {
				boolean b = false;
				try {
					goalDao.insertGoal(bct);
					b = true;
				} catch (Exception e) {
					logger.error(bct, e);
				}
				return b;
			}
		});
	}

	public boolean updateGoal(final BCustomerTarget bct) {
		return (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Boolean doInTransaction(TransactionStatus tran) {
				boolean b = false;
				try {
					goalDao.updateGoal(bct);
					b = true;
				} catch (Exception e) {
					logger.error(bct, e);
				}
				return b;
			}
		});
	}

	public StringResult approveGoal(BCustomerTarget bct) {
		StringResult result = new StringResult();
		result.setCode(IGoalService.ERROR);
		result.setResult(IGoalService.ERROR_MESSAGE);
		try {
			int c = goalDao.approveGoal(bct);
			result.setResult(String.valueOf(c));
			result.setCode(IGoalService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(bct), e);
		}
		return result;
	}

	public StringResult insertBct(final List<BCustomerTarget> bctList) {
		StringResult result = new StringResult();
		result = (StringResult) transactionTemplate
				.execute(new TransactionCallback() {
					public StringResult doInTransaction(TransactionStatus ts) {
						StringResult result = new StringResult();
						result.setCode(IGoalService.ERROR);
						result.setResult(IGoalService.ERROR_MESSAGE);
						try {
							for (BCustomerTarget bct : bctList) {
								goalDao.insertGoal(bct);
							}
						} catch (Exception e) {
							result.setCode(IGoalService.ERROR);
							ts.setRollbackOnly();
							logger.error("预算信息保存出错", e);
						}
						return result;
					}
				});
		return result;
	}

	public IGoalDao getGoalDao() {
		return goalDao;
	}

	public void setGoalDao(IGoalDao goalDao) {
		this.goalDao = goalDao;
	}

	public List<Bchannel> getChannelTreeList(String node) {
		return null;
	}

	public String getCompanyName(String companyMark) {
		return null;
	}

	public int getChannelListCount(Bchannel bchannel) {
		return 0;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public int getMaterielListCount(Materiel mat) {
		try {
			return goalDao.getMaterielListCount(mat);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<Materiel> getMaterielList(Materiel mat) {
		try {
			return goalDao.getMaterielList(mat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public StringResult approveYearGoal(BCustomerTarget bct) {
		StringResult result = new StringResult();
		result.setCode(IGoalService.ERROR);
		result.setResult(IGoalService.ERROR_MESSAGE);
		try {
			int c = goalDao.approveYearGoal(bct);
			result.setResult(String.valueOf(c));
			result.setCode(IGoalService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(bct), e);
		}
		return result;

	}

	public BCustomerTarget getGoalMessege(BCustomerTarget target) {
		return goalDao.getGoalMessege(target);
	}

	public List<BCustomerTarget> getGoalMessegeByALL(BCustomerTarget target) {
		return goalDao.getGoalMessegeByALL(target);
	}

	public int getKunnrGoalCount(BCustomerTarget target) {
		return goalDao.getKunnrGoalCount(target);
	}

	public List<BCustomerTarget> getKunnrGoalAll(BCustomerTarget target) {
		return goalDao.getKunnrGoalAll(target);
	}

	public List<OrgHelps> getOrgIsExit(Kunnr kunnr) {
		return goalDao.getOrgIsExit(kunnr);
	}

	public int getConpointByUser(String userId, String conpointId) {
		try {
			return goalDao.getConpointByUser(userId, conpointId);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public int getMaterielViewListCount(Materiel mat) {
		try {
			return goalDao.getMaterielListViewCount(mat);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<Materiel> getMaterielViewList(Materiel mat) {
		try {
			return goalDao.getMaterielViewList(mat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public BCustomerTarget getGoalByCondition(BCustomerTarget target) {
		try {
			return goalDao.getGoalByCondition(target);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public double getGoalCountNum(BCustomerTarget target) {
		try {
			return goalDao.getGoalCountNum(target);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public BCustomerTarget getGoalMessegeOnYearAndMatter(BCustomerTarget target) {
		try {
			return goalDao.getGoalMessegeOnYearAndMatter(target);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public int deleteKunnrGoalAppply(BCustomerTarget target) {
		try {
			return goalDao.deleteKunnrGoalAppply(target);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}

	}

	public int searchMatterPriceCount(MatterPriceObject object) {
		try {
			return goalDao.searchMatterPriceCount(object);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}

	}

	public List<MatterPriceObject> searchMatterPriceList(
			MatterPriceObject object) {
		try {
			return goalDao.searchMatterPriceList(object);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	public MatterPriceObject getMatterPriceObject(MatterPriceObject object) {
		try {
			return goalDao.getMatterPriceObject(object);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public StringResult createOrUpdateGoal(MatterPriceObject object) {
		StringResult result = new StringResult();
		result.setCode(IGoalService.ERROR);
		result.setResult(IGoalService.ERROR_MESSAGE);
		try {
			if (null != object.getMatPriceId()
					&& !"".equals(object.getMatPriceId())) {

				int c = goalDao.updateGoalPrice(object);
				if (c != 0) {
					result.setResult(String.valueOf(c));
					result.setCode(IGoalService.SUCCESS);
				}
			} else {
				String id = goalDao.createGoalPrice(object);
				if (null != id) {
					result.setResult(id);
					result.setCode(IGoalService.SUCCESS);
				}
			}

		} catch (Exception e) {
			logger.error(LogUtil.parserBean(object), e);
		}
		return result;
	}

	public int searchMatterPriceBoCount(MatterPriceBO priceBo) {
		try {
			return goalDao.searchMatterPriceBoCount(priceBo);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public List<MatterPriceBO> searchMatterPriceBoList(MatterPriceBO priceBo) {
		try {
			return goalDao.searchMatterPriceBoList(priceBo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public void insertMarPrice(MatterPriceBO priceBo) {
		try {
			goalDao.insertMarPrice(priceBo);
		} catch (Exception e) {
			logger.error(e);
		}

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

	public Map<String, Object> importGoalSalesXls(File fileContent,
			String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Workbook workbook = null;
		Sheet sheet = null;
		// StringResult result = new StringResult();
		StringBuilder errorMsgContent = new StringBuilder();
		List<GoalSalesDetail> goalSalesDetailList = new LinkedList<GoalSalesDetail>();
		Map<String, String> kunnrMap = new HashMap<String, String>();
		Map<String, String> skuMap = new HashMap<String, String>();

		try {
			workbook = new HSSFWorkbook(new FileInputStream(fileContent));
			String[] header = new String[11];

			header[0] = "组织";
			header[1] = "经销商编号";
			header[2] = "经销商名称";
			header[3] = "年月（yyyyMM）";
			header[4] = "组织";
			header[5] = "经销商编号";
			header[6] = "经销商名称";
			header[7] = "年月（yyyyMM）";
			header[8] = "品项编号";
			header[9] = "品项名称";
			header[10] = "标箱";

			sheet = workbook.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			if (rows == 0) {
				errorMsgContent.append("导入的Excel为空！</br>");
			} else if (sheet.getRow(0).getLastCellNum() != header.length) {
				errorMsgContent.append("导入的Excel列数与下载文件列数不一致!</br>");
			} else {
				// for (int i = 0; i < header.length; i++) {
				// if (!header[i].equals(getValue(sheet.getRow(1).getCell(i))))
				// {
				// errorMsgContent.append("第").append(i + 1).append("列")
				// .append(getValue(sheet.getRow(0).getCell(i)))
				// .append("与模板中").append(header[i]).append("不一致</br>");
				// }
				// }
				if (errorMsgContent.length() > 0) {
					map.put("resultMessage", errorMsgContent.toString());
					return map;
				}

				List<AllUsers> users = goalDao.getOrgsByUserId(userId);

				for (int i = 2; i <= rows; i++) {
					String kunnrOrgName = "";
					String kunnrId = "";
					String kunnrName = "";
					String yearMonth = "";
					String kunnrOrgNameTo = "";
					String kunnrIdTo = "";
					String kunnrNameTo = "";
					String yearMonthTo = "";
					String matter = "";
					String matterName = "";
					String quantity = "";

					kunnrOrgName = getValue(sheet.getRow(i).getCell(
							header.length - 11));
					kunnrId = getValue(sheet.getRow(i).getCell(
							header.length - 10));
					kunnrName = getValue(sheet.getRow(i).getCell(
							header.length - 9));
					yearMonth = getValue(sheet.getRow(i).getCell(
							header.length - 8));
					kunnrOrgNameTo = getValue(sheet.getRow(i).getCell(
							header.length - 7));
					kunnrIdTo = getValue(sheet.getRow(i).getCell(
							header.length - 6));
					kunnrNameTo = getValue(sheet.getRow(i).getCell(
							header.length - 5));
					yearMonthTo = getValue(sheet.getRow(i).getCell(
							header.length - 4));
					matter = getValue(sheet.getRow(i)
							.getCell(header.length - 3));
					matterName = getValue(sheet.getRow(i).getCell(
							header.length - 2));
					quantity = getValue(sheet.getRow(i).getCell(
							header.length - 1));

					String kunnrOrgId = "";
					String kunnrOrgIdTo = "";
					String maktx01 = ""; 

					if (StringUtils.isNotEmpty(kunnrId)) {
						if (kunnrId.length() < 8) {
							for (int j = 0; j < 8 - kunnrId.length(); j++) {
								kunnrId = "0" + kunnrId;
							}
						}
					}
					if (StringUtils.isNotEmpty(kunnrIdTo)) {
						if (kunnrIdTo.length() < 8) {
							for (int j = 0; j < 8 - kunnrIdTo.length(); j++) {
								kunnrIdTo = "0" + kunnrIdTo;
							}
						}
					}

					if (StringUtils.isNotEmpty(kunnrOrgName)
							&& StringUtils.isNotEmpty(kunnrOrgNameTo)) {
						int f1 = 0;
						int f2 = 0;
						for (AllUsers user : users) {
							if (kunnrOrgName.equals(user.getOrgName())) {
								f1 = 1;
							}
							if (kunnrOrgNameTo.equals(user.getOrgName())) {
								f2 = 1;
							}
						}
						if (f1 == 0 || f2 == 0) {
							errorMsgContent.append("第" + (i + 1)
									+ "行：无导入该组织数据权限!</br>");
							break;
						}
					}

					if (StringUtils.isNotEmpty(kunnrOrgName)) {
						if (StringUtils.isNotEmpty(kunnrId)
								&& StringUtils.isNotEmpty(kunnrName)) {
							String name = kunnrMap.get(kunnrId);
							if (StringUtils.isNotEmpty(name)) {
								if (!kunnrName.equals(name.split(",")[0])
										|| !kunnrOrgName
												.equals(name.split(",")[1])) {
									errorMsgContent
											.append("第"
													+ (i + 1)
													+ "行：调出经销商ID、名称或组织错误，如调整至组织待开，请勿填写经销商编号及名称!</br>");
								} else {
									kunnrOrgId = name.split(",")[2];
								}
							} else {
								Kunnr kunnr = new Kunnr();
								kunnr.setKunnr(kunnrId);
								kunnr.setName1(kunnrName);
								kunnr.setOrgName(kunnrOrgName);
								List<Kunnr> kunnrList = goalDao
										.searchKunnrList(kunnr);

								if (kunnrList != null && kunnrList.size() > 0) {
									kunnrMap.put(kunnrId, kunnrName + ","
											+ kunnrList.get(0).getOrgName()
											+ "," + kunnrList.get(0).getOrgId());
									kunnrOrgId = kunnrList.get(0).getOrgId();
								} else {
									errorMsgContent
											.append("第"
													+ (i + 1)
													+ "行：调出经销商ID、名称或组织错误，如调整至组织待开，请勿填写经销商编号及名称!</br>");
								}
							}
						} else if (StringUtils.isNotEmpty(kunnrOrgName)) {
							for (AllUsers u : users) {
								if (kunnrOrgName.equals(u.getOrgName())) {
									kunnrOrgId = u.getOrgId();
								}
							}
						}
					} else {
						errorMsgContent.append("第" + (i + 1)
								+ "行：调出组织不能为空!</br>");
					}

					if (StringUtils.isNotEmpty(kunnrOrgNameTo)) {
						if (StringUtils.isNotEmpty(kunnrIdTo)
								&& StringUtils.isNotEmpty(kunnrNameTo)) {
							String name = kunnrMap.get(kunnrIdTo);
							if (StringUtils.isNotEmpty(name)) {
								if (!kunnrNameTo.equals(name.split(",")[0])
										|| !kunnrOrgNameTo.equals(name
												.split(",")[1])) {
									errorMsgContent
											.append("第"
													+ (i + 1)
													+ "行：调入经销商ID、名称或组织错误，如调整至组织待开，请勿填写经销商编号及名称!</br>");
								} else {
									kunnrOrgIdTo = name.split(",")[2];
								}
							} else {
								Kunnr kunnr = new Kunnr();
								kunnr.setKunnr(kunnrIdTo);
								kunnr.setName1(kunnrNameTo);
								kunnr.setOrgName(kunnrOrgNameTo);
								List<Kunnr> kunnrList = goalDao
										.searchKunnrList(kunnr);

								if (kunnrList != null && kunnrList.size() > 0) {
									kunnrMap.put(kunnrIdTo, kunnrNameTo + ","
											+ kunnrList.get(0).getOrgName()
											+ "," + kunnrList.get(0).getOrgId());
									kunnrOrgIdTo = kunnrList.get(0).getOrgId();
								} else {
									errorMsgContent
											.append("第"
													+ (i + 1)
													+ "行：调入经销商ID、名称或组织错误，如调整至组织待开，请勿填写经销商编号及名称!</br>");
								}
							}
						} else if (StringUtils.isNotEmpty(kunnrOrgNameTo)) {
							for (AllUsers u : users) {
								if (kunnrOrgNameTo.equals(u.getOrgName())) {
									kunnrOrgIdTo = u.getOrgId();
								}
							}
						}
					} else {
						errorMsgContent.append("第" + (i + 1)
								+ "行：调入组织不能为空!</br>");
					}

					if (StringUtils.isNotEmpty(yearMonth)) {
						Pattern p2 = Pattern
								.compile("^[2]\\d{3}([0][1-9]|[1][0-2])$");
						Matcher matcher = p2.matcher(yearMonth);
						if (!matcher.matches()) {
							errorMsgContent.append("第" + (i + 1)
									+ "行：调出年月格式错误!</br>");
						}
					} else {
						errorMsgContent.append("第" + (i + 1)
								+ "行：调出年月不能为空!</br>");
					}

					if (StringUtils.isNotEmpty(yearMonthTo)) {
						Pattern p2 = Pattern
								.compile("^[2]\\d{3}([0][1-9]|[1][0-2])$");
						Matcher matcher = p2.matcher(yearMonthTo);
						if (!matcher.matches()) {
							errorMsgContent.append("第" + (i + 1)
									+ "行：调入年月格式错误!</br>");
						}
					} else {
						errorMsgContent.append("第" + (i + 1)
								+ "行：调入年月不能为空!</br>");
					}

					if (StringUtils.isNotEmpty(matter)
							|| StringUtils.isNotEmpty(matterName)) {
						String names = skuMap.get(matter);
						if (StringUtils.isNotEmpty(names)) {
							String name=names.split(",")[0];
							String brand=names.split(",")[1];
							if (!matterName.equals(name)) {
								errorMsgContent.append("第" + (i + 1)
										+ "行：品项编号或名称错误!</br>");
							}else{
								maktx01=brand;
							}
						} else {
							Materiel materiel = new Materiel();
							materiel.setMvgr1(matter);
							materiel.setBezei(matterName);
							materiel.setStart(0);
							materiel.setEnd(100);
							List<Materiel> materielList = goalDao.getMaterielList(materiel);
							if (materielList == null || materielList.size() == 0) {
								errorMsgContent.append("第" + (i + 1)
										+ "行：品项编号或名称错误!</br>");
							}else{
								maktx01=materielList.get(0).getMaktx01();
								skuMap.put(matter, matterName+","+maktx01);
							}
						}
					} else {
						errorMsgContent.append("第" + (i + 1)
								+ "行：品项编号或名称不能为空!</br>");
					}

					if (StringUtils.isNotEmpty(quantity)) {
						Pattern p2 = Pattern.compile("^\\d+(\\.\\d+)?$");
						Matcher matcher = p2.matcher(quantity);
						if (!matcher.matches()) {
							errorMsgContent.append("第" + (i + 1)
									+ "行：箱数格式错误!</br>");
						}
					} else {
						errorMsgContent
								.append("第" + (i + 1) + "行：箱数不能为空!</br>");
					}

					if (StringUtils.isEmpty(errorMsgContent.toString())) {
						GoalSalesDetail goalSalesDetail = new GoalSalesDetail();
						goalSalesDetail.setKunnr(kunnrId);
						goalSalesDetail.setKunnrName(kunnrName);
						goalSalesDetail.setOrgName(kunnrOrgName);
						goalSalesDetail.setYearMonth(yearMonth);
						goalSalesDetail.setKunnrTo(kunnrIdTo);
						goalSalesDetail.setKunnrNameTo(kunnrNameTo);
						goalSalesDetail.setOrgNameTo(kunnrOrgNameTo);
						goalSalesDetail.setYearMonthTo(yearMonthTo);
						goalSalesDetail.setMatter(matter);
						goalSalesDetail.setMatterName(matterName);
						goalSalesDetail.setQuantity(Double.parseDouble(quantity));
						goalSalesDetail.setOrgId(Long.parseLong(kunnrOrgId));
						goalSalesDetail.setOrgIdTo(Long.parseLong(kunnrOrgIdTo));
						goalSalesDetail.setBrand(maktx01);
						goalSalesDetail.setMaktx01(maktx01);
						goalSalesDetailList.add(goalSalesDetail);
					}
				}
			}
			map.put("resultMessage", errorMsgContent.toString());
			map.put("goalSalesDetailList", goalSalesDetailList);
			return map;
		} catch (Exception e) {
			if (errorMsgContent.length() == 0) {
				map.put("resultMessage", "导入模板出错，请联系管理员！");
			} else {
				map.put("resultMessage", errorMsgContent.toString());
			}
			e.printStackTrace();
			return map;
		}
	}

	public List<AllUsers> getStationIdByUserId(String userId) {
		try {
			return goalDao.getStationIdByUserId(userId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<BCustomerTarget> getGoalReportList(BCustomerTarget bct) {
		try {
			return goalDao.getGoalReportList(bct);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Long createGoalSalesChange(GoalSales goalSales) {
		try {
			return goalDao.createGoalSalesChange(goalSales);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public BCustomerTarget getGoalContract(BCustomerTarget c) {
		try {
			return goalDao.getGoalContract(c);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public int searchGoalSalesChangeListCount(GoalSales goalSales) {
		try {
			return goalDao.searchGoalSalesChangeListCount(goalSales);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public List<BCustomerTarget> getGoalDK(BCustomerTarget bct) {
		try {
			return goalDao.getGoalDK(bct);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public int getGoalSumCount(BCustomerTarget bct) {
		try {
			return goalDao.getGoalSumCount(bct);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public List<BCustomerTarget> getGoalDKAll(BCustomerTarget bct) {
		try {
			return goalDao.getGoalDKAll(bct);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<BCustomerTarget> getGoalSum(BCustomerTarget bct) {
		try {
			return goalDao.getGoalSum(bct);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public BCustomerTarget getGoalContractSum(BCustomerTarget c) {
		try {
			return goalDao.getGoalContractSum(c);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<GoalSales> searchGoalSalesChangeList(GoalSales goalSales) {
		try {
			return goalDao.searchGoalSalesChangeList(goalSales);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<GoalSalesDetail> searchGoalSalesChangeDetailList(
			GoalSalesDetail goalSalesDetail) {
		try {
			return goalDao.searchGoalSalesChangeDetailList(goalSalesDetail);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public Double getSalesGoalCGDetailSum(GoalSalesDetail goalSalesDetail) {
		try {
			return goalDao.getSalesGoalCGDetailSum(goalSalesDetail);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public int updateGoalSalesChange(GoalSales goalSales) {
		try {
			return goalDao.updateGoalSalesChange(goalSales);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public Long createGoalSalesChangeDetail(GoalSalesDetail goalSalesDetail) {
		try {
			return goalDao.createGoalSalesChangeDetail(goalSalesDetail);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public int getGoalDKCount(BCustomerTarget bct) {
		try {
			return goalDao.getGoalDKCount(bct);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public int getGoalDKAllCount(BCustomerTarget bct) {
		try {
			return goalDao.getGoalDKAllCount(bct);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public List<PrintContractGoalInfo> searchConGolInfo(
			PrintContractGoalInfo printConGoalInfo) {
		return goalDao.searchConGolInfo(printConGoalInfo);
	}

	@Override
	public List<PrintContractGoalInfo> searchConGolInfo2(
			PrintContractGoalInfo printConGoalInfo) {
		return goalDao.searchConGolInfo2(printConGoalInfo);
	}

	@Override
	public int getKunnrListForNormalCount(Kunnr kunnr) {
		return goalDao.getKunnrListForNormalCount(kunnr);
	}

	@Override
	public List<Kunnr> getKunnrListForNormal(Kunnr kunnr) {
		return goalDao.getKunnrListForNormal(kunnr);
	}

	@Override
	public int getGoalListCountForMBL(BCustomerTarget bct) {
		try {
			return goalDao.getGoalListCountForMBL(bct);
		} catch (Exception e) {
			logger.error(bct, e);
		}
		return 0;
	}

	@Override
	public List<BCustomerTarget> getGoalReportListForMBL(BCustomerTarget bct) {
		try {
			return goalDao.getGoalReportListForMBL(bct);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public int getDistributionGoalTotal(BCustomerTarget bct) {
		try {
			return goalDao.getDistributionGoalTotal(bct);
		} catch (Exception e) {
			logger.error(bct, e);
		}
		return 0;
	}

	@Override
	public List<BCustomerTarget> getDistributionGoalList(BCustomerTarget bct) {
		try {
			return goalDao.getDistributionGoalList(bct);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Double getSalesGoalFXDetailSum(GoalSalesDetail gsd) {
		try {
			return goalDao.getSalesGoalFXDetailSum(gsd);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Long createGoalDistributionChange(GoalSales goalSales) {
		try {
			return goalDao.createGoalDistributionChange(goalSales);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public Long createGoalFXChangeDetail(GoalSalesDetail gsdTo) {
		try {
			return goalDao.createGoalFXChangeDetail(gsdTo);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<GoalSales> searchGoalFXChangeList(GoalSales goalSales) {
		try {
			return goalDao.searchGoalFXChangeList(goalSales);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<GoalSalesDetail> searchGoalFXChangeDetailList(
			GoalSalesDetail goalSalesDetail) {
		try {
			return goalDao.searchGoalFXChangeDetailList(goalSalesDetail);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public boolean updateFXGoal(final GoalSalesDetail bct) {
		return (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Boolean doInTransaction(TransactionStatus tran) {
				boolean b = false;
				try {
					goalDao.updateFXGoal(bct);
					b = true;
				} catch (Exception e) {
					logger.error(bct, e);
				}
				return b;
			}
		});

	}

	@Override
	public int searchGoalFXChangeListCount(GoalSales goalSales) {
		try {
			return goalDao.searchGoalFXChangeListCount(goalSales);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public int updateGoalFXChange(GoalSales goalSales) {
		try {
			return goalDao.updateGoalFXChange(goalSales);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}

	@Override
	public boolean insertFXGoal(final BCustomerTarget bct) {

		return (Boolean) transactionTemplate.execute(new TransactionCallback() {
			public Boolean doInTransaction(TransactionStatus tran) {

				boolean b = false;
				try {
					goalDao.insertFXGoal(bct);
					b = true;
				} catch (Exception e) {
					logger.error(bct, e);
				}
				return b;

			}
		});

	}

	@Override
	public int searchSku(String sku) {
		try{
			return goalDao.searchSku(sku);
		}catch(Exception e){
			
		}
		return 0;
	}

	@Override
	public int searchSkuText(String skuText) {
		try{
			return goalDao.searchSkuText(skuText);
		}catch(Exception e){
			
		}
		return 0;
	}

	@Override
	public List<BCustomerTarget> getMaktxAndMaknr(Materiel mat) {
		try {
			return goalDao.getMaktxAndMaknr(mat);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<PrintContractGoalInfo> searchConGolInfo3(
			PrintContractGoalInfo printConGoalInfo) {
		return goalDao.searchConGolInfo3(printConGoalInfo);
	}
}
