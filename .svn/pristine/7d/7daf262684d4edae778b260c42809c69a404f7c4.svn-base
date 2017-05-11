package com.kintiger.platform.stockReport.service.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.framework.sap.SAPConnectionBean;
import com.kintiger.platform.order.pojo.Sku;
import com.kintiger.platform.stockReport.dao.IStockReportDao;
import com.kintiger.platform.stockReport.pojo.AllUsers;
import com.kintiger.platform.stockReport.pojo.Category;
import com.kintiger.platform.stockReport.pojo.Goal;
import com.kintiger.platform.stockReport.pojo.Kunag;
import com.kintiger.platform.stockReport.pojo.KunnrInType;
import com.kintiger.platform.stockReport.pojo.KunnrManager;
import com.kintiger.platform.stockReport.pojo.OrderCheck;
import com.kintiger.platform.stockReport.pojo.OrderFollow;
import com.kintiger.platform.stockReport.pojo.Pod;
import com.kintiger.platform.stockReport.pojo.SkuDistribution;
import com.kintiger.platform.stockReport.pojo.SkuPercent;
import com.kintiger.platform.stockReport.pojo.SkuUnit;
import com.kintiger.platform.stockReport.pojo.Station;
import com.kintiger.platform.stockReport.pojo.StockDate;
import com.kintiger.platform.stockReport.pojo.StockManager;
import com.kintiger.platform.stockReport.pojo.StockManagerSap;
import com.kintiger.platform.stockReport.pojo.StockReport;
import com.kintiger.platform.stockReport.service.IStockReportService;
import com.sap.mw.jco.IFunctionTemplate;
import com.sap.mw.jco.JCO;

public class StockReportServiceImpl implements IStockReportService {

	IStockReportDao stockReportDao;
	private SAPConnectionBean sapConnection;
	private static Log logger = LogFactory.getLog(StockReportServiceImpl.class);

	@Override
	public int getStockReportListCount(StockReport stockReport) {
		try {
			return stockReportDao.getStockReportListCount(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<StockReport> getStockReportList(StockReport stockReport) {
		try {
			return stockReportDao.getStockReportList(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getStockStateManagerCount(StockReport stockReport) {
		try {
			return stockReportDao.getStockStateManagerCount(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<StockReport> getStockStateManagerList(StockReport stockReport) {
		try {
			return stockReportDao.getStockStateManagerList(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getStockWarningListCount(StockReport stockReport) {
		try {
			return stockReportDao.getStockWarningListCount(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<StockReport> getStockWarningList(StockReport stockReport) {
		try {
			return stockReportDao.getStockWarningList(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	public List<StockReport> getStockWarningDetailList(StockReport stockReport) {
		try {
			return stockReportDao.getStockWarningDetailList(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	public int createStockWarning(final List<StockReport> list) {
		try {
			return stockReportDao.createStockWarning(list);
		} catch (Exception e) {
			logger.error(list, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int modifyStockWarning(final List<StockReport> list) {
		try {
			return stockReportDao.modifyStockWarning(list);
		} catch (Exception e) {
			logger.error(list, e);
			e.printStackTrace();
			return 0;
		}
	}

	@SuppressWarnings("deprecation")
	public List<StockReport> getStockListFromSAP(List<StockReport> searchList) {
		// List<StockReport> list=new ArrayList<StockReport>();
		JCO.Client client = null;
		try {
			client = sapConnection.getSAPClientFromPool();
			client.connect();
			JCO.Repository myRepository = new JCO.Repository("Repository",
					client);
			// 要调用的SAP函数名称
			String strFunc = "ZRFC_EXP_JXSGLPT1";
			// 从“仓库”中获得一个指定函数名的函数模板
			IFunctionTemplate ft = myRepository.getFunctionTemplate(strFunc
					.toUpperCase());
			// 从这个函数模板获得该SAP函数的对象
			JCO.Function func1 = ft.getFunction();
			// 获得函数的import参数列表
			JCO.Table input = func1.getTableParameterList().getTable("T_MATNR");
			String str = "";
			// Long time0=System.currentTimeMillis();
			// searchList=stockReportDao.searchFactQuantity(searchList);
			Long time2 = System.currentTimeMillis();
			for (StockReport s : searchList) {
				if (str.indexOf(s.getSkuSapCode()) == -1) {
					input.appendRow();
					// System.out.println("skuId: "+s.getSkuSapCode());
					input.getField("MATNR").setValue(s.getSkuSapCode());
					str += s.getSkuSapCode();
					str += ",";
				}
			}
			// System.out.println(str);
			Long time1 = System.currentTimeMillis();
			// System.out.println(time2-time0);
			client.execute(func1);
			JCO.Table exportTable = func1.getTableParameterList().getTable(
					"TABLE");
			// Long time1=System.currentTimeMillis();
			System.out.println("sap请求时间："
					+ (System.currentTimeMillis() - time1));
			// System.out.println("总记录数"+exportTable.getNumRows());
			DecimalFormat df = new DecimalFormat("#.00");
			String sapKunnr = "0000000000";
			String sapSku = "000000000000000000";
			Double rule = 30.0;// 标箱杯数
			for (int i = 0; i < searchList.size(); i++) {
				boolean isMatched = false;// sap返回值匹配
				Double coefficient = searchList.get(i).getCoefficient();
				exportTable.firstRow();
				for (int j = 0; j < exportTable.getNumRows(); j++, exportTable
						.nextRow()) {
					String kunnr = exportTable.getValue("KUNNR").toString();
					String matnr = exportTable.getValue("MATNR").toString();
					String notPlan = exportTable.getValue("WPC").toString();
					String planNotSend = exportTable.getValue("WFH").toString();
					String onWayNum = exportTable.getValue("WDH").toString();
					String expKunnr = sapKunnr.substring(searchList.get(i)
							.getKunnr().length())
							+ searchList.get(i).getKunnr();
					String expSku = sapSku.substring(searchList.get(i)
							.getSkuSapCode().length())
							+ searchList.get(i).getSkuSapCode();
					// System.out.println(expKunnr+"<-kunnr:sku->"+expSku);
					// System.out.println(kunnr+"<-sapkunnr:sku->"+matnr);
					if (expKunnr.equals(kunnr) && expSku.equals(matnr)) {
						isMatched = true;
						Double yszt = Double.parseDouble(notPlan);
						Double pcwck = Double.parseDouble(planNotSend);
						Double syjydd = Double.parseDouble(onWayNum);
						if (coefficient != null && !(coefficient == 0)) {
							searchList.get(i).setOnWayNum(
									yszt == null ? 0.0 : yszt / rule
											/ coefficient);
							searchList.get(i).setPlanNotSend(
									pcwck == null ? 0.0 : pcwck / rule
											/ coefficient);
							searchList.get(i).setNotPlan(
									syjydd == null ? 0.0 : syjydd / rule
											/ coefficient);
						} else {
							searchList.get(i).setOnWayNum(
									yszt == null ? 0.0 : yszt / rule);
							searchList.get(i).setPlanNotSend(
									pcwck == null ? 0.0 : pcwck / rule);
							searchList.get(i).setNotPlan(
									syjydd == null ? 0.0 : syjydd / rule);
						}
						break;
					}
				}
				if (!isMatched) {
					searchList.get(i).setOnWayNum(0.0);
					searchList.get(i).setPlanNotSend(0.0);
					searchList.get(i).setNotPlan(0.0);
				}
				double num1 = searchList.get(i).getQuantity()
						+ searchList.get(i).getOnWayNum()
						+ searchList.get(i).getPlanNotSend();
				double num2 = searchList.get(i).getSafeQuantityMax() - num1;
				// System.out.println("num1: "+num1+"num2: "+num2);
				if (num1 < searchList.get(i).getSafeQuantityMin()) {
					if (num2 <= searchList.get(i).getNotPlan()) {
						searchList.get(i).setNeedPlan(
								Double.parseDouble(df.format(num2)));
						searchList.get(i).setNeedOrder(0.0);
					} else {
						searchList.get(i).setNeedPlan(
								Double.parseDouble(df.format(searchList.get(i)
										.getNotPlan())));
						searchList.get(i).setNeedOrder(
								Double.parseDouble(df.format(num2
										- searchList.get(i).getNotPlan())));
					}
				} else {
					searchList.get(i).setNeedPlan(0.0);
					searchList.get(i).setNeedOrder(0.0);
				}
			}
			Long time3 = System.currentTimeMillis();
			// System.out.println("sap请求时间："+(time1-time2));
			// System.out.println("结果匹配时间："+(time3-time1));
			return searchList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (client != null)
					JCO.releaseClient(client);
			} catch (Exception e) {
				// result.setResult("释放连接异常");
				// result.setCode(IReimburseService.ERROR);
				logger.error(e);
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public List<StockReport> getOrderQuantityFromSAP(
			List<StockReport> searchList) {
		// List<StockReport> list=new ArrayList<StockReport>();
		JCO.Client client = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat df1 = DateFormat.getDateInstance();
		DateFormat df2 = DateFormat.getTimeInstance();
		try {
			client = sapConnection.getSAPClientFromPool();
			client.connect();
			JCO.Repository myRepository = new JCO.Repository("Repository",
					client);
			String strFunc = "ZRFC_EXP_YJB";
			IFunctionTemplate ft = myRepository.getFunctionTemplate(strFunc
					.toUpperCase());
			JCO.Function func = ft.getFunction();
			JCO.Table input1 = func.getTableParameterList().getTable("T_KUNNR");
			for (StockReport s : searchList) {
				input1.appendRow();
				input1.getField("KUNNR").setValue(s.getKunnr());
			}
			JCO.ParameterList input = func.getImportParameterList();

			if (searchList.size() > 0) {
				Date searchDate = df.parse(searchList.get(0).getEndDate());
				input.getField("DATE1").setValue(df1.format(searchDate));// CHAR8
				input.getField("TIME1").setValue(df2.format(searchDate));// CHAR6
				// System.out.println(df1.format(searchDate)+"***"+df2.format(searchDate));
			}

			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"TABLE");
			List<StockReport> resultList = new ArrayList<StockReport>();
			for (int j = 0; j < exportTable.getNumRows(); j++, exportTable
					.nextRow()) {
				StockReport s = new StockReport();
				String kunnr = exportTable.getValue("KUNNR").toString();
				Date date = (Date) exportTable.getValue("ERDAT");
				String quantity = exportTable.getValue("KWMENG").toString();
				String matnr = exportTable.getValue("MATNR").toString();
				// System.out.println("kunnr: "+kunnr+"matnr: "+matnr+"quantity: "+quantity+"date: "+date);
				s.setKunnr(kunnr);
				s.setSkuSapCode(matnr);
				s.setFactOrder(Double.parseDouble(quantity));
				s.setOrderDate(df.format(date).toString());
				resultList.add(s);
			}
			// 模拟取到的数
			/**
			 * for(StockReport s:searchList){ s.setFactOrder(60.0);
			 * s.setOrderDate("2014-05-27"); }
			 */
			// return searchList;
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (client != null)
					JCO.releaseClient(client);
			} catch (Exception e) {
				// result.setResult("释放连接异常");
				// result.setCode(IReimburseService.ERROR);
				logger.error(e);
			}
		}
		return null;
	}

	public List<StockReport> getKunnrByStockIds(StockReport stockReport) {
		try {
			return stockReportDao.getKunnrByStockIds(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	public int getStockReportListCountTotal(StockReport stockReport) {
		try {
			return stockReportDao.getStockReportListCountTotal(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	public String getStockReportKunnrAndKunagSum(StockReport stockReport) {
		try {
			return stockReportDao.getStockReportKunnrAndKunagSum(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	public int updateStockReport(StockReport stockReport) {
		try {
			int count = stockReportDao.updateStockReport(stockReport);
			stockReportDao.updateStockReportTotal(stockReport);
			return count;
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int createStockReport(StockReport stockReport) {
		try {
			int stockId = stockReportDao.createStockReport(stockReport);

			if (!stockReport.getFlag().equals("sales_day") && !stockReport.getFlag().equals("kunnr_day")) {
				Kunag kunag = new Kunag();
				kunag.setKunnr(stockReport.getKunnr());
				kunag.setStart(0);
				kunag.setEnd(99999);
				List<Kunag> kList = stockReportDao.searchKunag(kunag);

				StockReport sr = new StockReport();
				if (kList != null && kList.size() != 0
						&& StringUtils.isNotEmpty(kList.get(0).getKunag())) {
					sr.setKunag(kList.get(0).getKunag());
				} else {
					sr.setKunnr(stockReport.getKunnr());
				}
				sr.setCheckTime(stockReport.getCheckTime());
				sr.setFlag(stockReport.getFlag());
				stockReport.setStatus("U");
				stockReport.setStockId(stockId);
				if (stockReport.getUserType().equals("D")) {
					sr.setUserType("C");
					sr.setStatus("U");
					int num = stockReportDao.getStockReportListCountTotal(sr);
					if (num == 0) {
						sr.setUserType("A");
						int num1 = stockReportDao
								.getStockReportListCountTotal(sr);
						if (num1 > 0) {
							sr.setStatus("F");
							sr.setStatusBefore("U");
							stockReportDao.updateStockReportTotal(sr);
						}
					} else {
						stockReport.setStatus("F");
					}
					stockReportDao.createStockReportTotal(stockReport);
				} else if (stockReport.getUserType().equals("C")) {
					sr.setStatus("F");
					sr.setStatusBefore("U");
					sr.setUserType("A");
					stockReportDao.updateStockReportTotal(sr);
					sr.setUserType("D");
					stockReportDao.updateStockReportTotal(sr);
					stockReportDao.createStockReportTotal(stockReport);
				} else if (stockReport.getUserType().equals("A")) {
					sr.setStatus("U");
					sr.setUserType("D");
					int num = stockReportDao.getStockReportListCountTotal(sr);
					sr.setUserType("C");
					int num1 = stockReportDao.getStockReportListCountTotal(sr);
					if (num != 0 || num1 != 0) {
						stockReport.setStatus("F");
					}
					stockReportDao.createStockReportTotal(stockReport);
				}
			}
			return stockId;
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int getStockReportByCGListCount(StockReport stockReport) {
		try {
			return stockReportDao.getStockReportByCGListCount(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<StockReport> getStockReportByCGList(StockReport stockReport) {
		try {
			return stockReportDao.getStockReportByCGList(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	public int getStockReportBySkuListCount(StockReport stockReport) {
		try {
			return stockReportDao.getStockReportBySkuListCount(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<StockReport> getStockReportBySkuList(StockReport stockReport) {
		try {
			return stockReportDao.getStockReportBySkuList(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	public int getStockReportBySalesListCount(StockReport stockReport) {
		try {
			return stockReportDao.getStockReportBySalesListCount(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<StockReport> getStockReportBySalesList(StockReport stockReport) {
		try {
			return stockReportDao.getStockReportBySalesList(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	public int getStockReportSalesByCGListCount(StockReport stockReport) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(stockReport.getProductionDate());
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, -1);
			cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			String date1 = sdf.format(cal.getTime()); // date1 - 上月最后一天
			cal.add(Calendar.DATE, 1);
			String date2 = sdf.format(cal.getTime()); // date2 - 当月1日
			String date3 = date2.substring(0, 4) + date2.substring(5, 7)
					+ date2.substring(8, 10); // date2 sap 格式

			StockReport s1 = new StockReport();
			s1.setCheckTime(stockReport.getProductionDate());
			String date5 = stockReportDao.getLastCheckTime(s1); // date5 -
																// 最新库存日期
			if (date5 != null && !date5.equals("")) {
				String date4 = date5.substring(0, 4) + date5.substring(5, 7)
						+ date5.substring(8, 10);// date5 sap 格式

				Pod pod = new Pod();
				pod.setCreateDate(date5);
				Integer count = stockReportDao.searchPodListCount(pod);
				if (count == 0) {
					int dateTmp3 = Integer.parseInt(date4);
					if (dateTmp3 >= 20140401 && dateTmp3 < 20140630) {

					}
					JCO.Client client = null;
					client = sapConnection.getSAPClientFromPool();
					sapConnection.setFuncName("ZRFC_BI_PODAT");
					JCO.Function func = sapConnection.getFunction(client);
					JCO.ParameterList input = func.getImportParameterList();
					input.getField("PODAT_B").setValue(date3);
					input.getField("PODAT_E").setValue(date4);
					client.execute(func);
					JCO.Table exportTable = func.getTableParameterList()
							.getTable("T_PODAT");
					System.out.println(exportTable.getNumRows());
					for (int x = 0; x < exportTable.getNumRows(); x++, exportTable
							.nextRow()) {
						Pod pod1 = new Pod();
						String kunnr = exportTable.getValue("KUNNR").toString();
						if (kunnr.length() == 10) {
							kunnr = kunnr.substring(2, 10);
						}
						pod1.setKunnr(kunnr);
						pod1.setMvgr1(exportTable.getValue("MVGR1").toString());
						pod1.setLfimg(Double.parseDouble(exportTable.getValue(
								"LFIMG").toString()));
						pod1.setVrkme(exportTable.getValue("VRKME").toString());
						pod1.setCreateDate(date5);
						stockReportDao.createPod(pod1);
					}
				}
				StockReport s2 = stockReport;
				s2.setProductionDate(date1);
				s2.setCheckTime(date5);
				return stockReportDao.getStockReportSalesByCGListCount(s2);
			} else {
				return 0;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public List<StockReport> getStockReportSalesByCGList(StockReport stockReport) {
		List<StockReport> list = stockReportDao
				.getStockReportSalesByCGList(stockReport);
		for (int i = 0; i < list.size(); i++) {
			StockReport s3 = list.get(i);
			list.get(i).setCheckTime(stockReport.getCheckTime());
			list.get(i).setUnitDesc("箱");
			list.get(i).setTotal1(
					s3.getPod1() + s3.getLastStock1() - s3.getCg1() >= 0 ? s3
							.getPod1() + s3.getLastStock1() - s3.getCg1() : 0);
			list.get(i).setTotal2(
					s3.getPod2() + s3.getLastStock2() - s3.getCg2() >= 0 ? s3
							.getPod2() + s3.getLastStock2() - s3.getCg2() : 0);
			list.get(i).setTotal3(
					s3.getPod3() + s3.getLastStock3() - s3.getCg3() >= 0 ? s3
							.getPod3() + s3.getLastStock3() - s3.getCg3() : 0);
			list.get(i).setTotal4(
					s3.getPod4() + s3.getLastStock4() - s3.getCg4() >= 0 ? s3
							.getPod4() + s3.getLastStock4() - s3.getCg4() : 0);
			list.get(i).setTotal5(
					s3.getPod5() + s3.getLastStock5() - s3.getCg5() >= 0 ? s3
							.getPod5() + s3.getLastStock5() - s3.getCg5() : 0);
			list.get(i).setTotal6(
					s3.getPod6() + s3.getLastStock6() - s3.getCg6() >= 0 ? s3
							.getPod6() + s3.getLastStock6() - s3.getCg6() : 0);
			list.get(i).setTotal7(
					s3.getPod7() + s3.getLastStock7() - s3.getCg7() >= 0 ? s3
							.getPod7() + s3.getLastStock7() - s3.getCg7() : 0);
			list.get(i).setTotal8(
					s3.getPod8() + s3.getLastStock8() - s3.getCg8() >= 0 ? s3
							.getPod8() + s3.getLastStock8() - s3.getCg8() : 0);
			list.get(i).setTotal9(
					s3.getPod9() + s3.getLastStock9() - s3.getCg9() >= 0 ? s3
							.getPod9() + s3.getLastStock9() - s3.getCg9() : 0);
			list.get(i)
					.setTotal10(
							s3.getPod10() + s3.getLastStock10() - s3.getCg10() >= 0 ? s3
									.getPod10()
									+ s3.getLastStock10()
									- s3.getCg10() : 0);
			list.get(i)
					.setTotal11(
							s3.getPod11() + s3.getLastStock11() - s3.getCg11() >= 0 ? s3
									.getPod11()
									+ s3.getLastStock11()
									- s3.getCg11() : 0);
			list.get(i)
					.setTotal12(
							s3.getPod12() + s3.getLastStock12() - s3.getCg12() >= 0 ? s3
									.getPod12()
									+ s3.getLastStock12()
									- s3.getCg12() : 0);
			list.get(i)
					.setTotal13(
							s3.getPod13() + s3.getLastStock13() - s3.getCg13() >= 0 ? s3
									.getPod13()
									+ s3.getLastStock13()
									- s3.getCg13() : 0);
			list.get(i)
					.setTotal14(
							s3.getPod14() + s3.getLastStock14() - s3.getCg14() >= 0 ? s3
									.getPod14()
									+ s3.getLastStock14()
									- s3.getCg14() : 0);
			list.get(i)
					.setTotal15(
							s3.getPod15() + s3.getLastStock15() - s3.getCg15() >= 0 ? s3
									.getPod15()
									+ s3.getLastStock15()
									- s3.getCg15() : 0);
			list.get(i)
					.setTotal16(
							s3.getPod16() + s3.getLastStock16() - s3.getCg16() >= 0 ? s3
									.getPod16()
									+ s3.getLastStock16()
									- s3.getCg16() : 0);
			list.get(i)
					.setTotal17(
							s3.getPod17() + s3.getLastStock17() - s3.getCg17() >= 0 ? s3
									.getPod17()
									+ s3.getLastStock17()
									- s3.getCg17() : 0);
			list.get(i)
					.setTotal18(
							s3.getPod18() + s3.getLastStock18() - s3.getCg18() >= 0 ? s3
									.getPod18()
									+ s3.getLastStock18()
									- s3.getCg18() : 0);
			list.get(i)
					.setTotal19(
							s3.getPod19() + s3.getLastStock19() - s3.getCg19() >= 0 ? s3
									.getPod19()
									+ s3.getLastStock19()
									- s3.getCg19() : 0);
			list.get(i)
					.setTotal20(
							s3.getPod20() + s3.getLastStock20() - s3.getCg20() >= 0 ? s3
									.getPod20()
									+ s3.getLastStock20()
									- s3.getCg20() : 0);
		}

		// for(int i=0;i<list.size();i++){
		// String[] categoryId =
		// {"1002","1869","1007","1895","1008","1894","1893","1006","1003","1890",
		// "1891","1892","1004","1889","1888","1887","1883","1886","1884","1009"};
		// String[] categoryIdSap =
		// {"A1","A2","A3","A4","A5","A6","A7","A8","B1","B3",
		// "B6","B8","C1","C6","C7","C8","D10","D9","E1","H7"};
		//
		// }
		return list;
	}

	public int getSkuCount(Sku sku) {
		try {
			return stockReportDao.getSkuCount(sku);
		} catch (Exception e) {
			logger.error(sku, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<Sku> getSkuNameList(Sku sku) {
		try {
			return stockReportDao.getSkuNameList(sku);
		} catch (Exception e) {
			logger.error(sku, e);
			e.printStackTrace();
			return null;
		}
	}

	public int getCategoryCount(Category category) {
		try {
			return stockReportDao.getCategoryCount(category);
		} catch (Exception e) {
			logger.error(category, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<Category> getCategoryNameList(Category category) {
		try {
			return stockReportDao.getCategoryNameList(category);
		} catch (Exception e) {
			logger.error(category, e);
			e.printStackTrace();
			return null;
		}
	}

	public Long getCategoryBySku(Long skuId) {
		try {
			return stockReportDao.getCategoryBySku(skuId);
		} catch (Exception e) {
			logger.error(skuId, e);
			e.printStackTrace();
			return null;
		}
	}

	public List<StockDate> getStockDate(StockDate stockDate) {
		try {
			return stockReportDao.getStockDate(stockDate);
		} catch (Exception e) {
			logger.error(stockDate, e);
			e.printStackTrace();
			return null;
		}
	}

	public int updateStockDate(StockDate stockDate) {
		try {
			return stockReportDao.updateStockDate(stockDate);
		} catch (Exception e) {
			logger.error(stockDate, e);
			e.printStackTrace();
			return 0;
		}
	}

	public String getLastStockDate(StockDate stockDate) {
		try {
			return stockReportDao.getLastStockDate(stockDate);
		} catch (Exception e) {
			logger.error(stockDate, e);
			e.printStackTrace();
			return null;
		}
	}

	public List<KunnrInType> getKunnrInType(KunnrInType kunnrInType) {
		try {
			return stockReportDao.getKunnrInType(kunnrInType);
		} catch (Exception e) {
			logger.error(kunnrInType, e);
			e.printStackTrace();
			return null;
		}
	}

	public int updateKunnrInType(KunnrInType kunnrInType) {
		try {
			KunnrInType k = new KunnrInType();
			k.setKunnr(kunnrInType.getKunnr());
			int count = stockReportDao.getKunnrInTypeTmpCount(k);
			if (count > 0) {
				return stockReportDao.updateKunnrInType(kunnrInType);
			} else {
				return stockReportDao.createKunnrInType(kunnrInType);
			}
		} catch (Exception e) {
			logger.error(kunnrInType, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int getKunnrInTypeCount(KunnrInType kunnrInType) {
		try {
			return stockReportDao.getKunnrInTypeCount(kunnrInType);
		} catch (Exception e) {
			logger.error(kunnrInType, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<SkuPercent> searchSkuPercentList(SkuPercent skuPercent) {
		try {
			return stockReportDao.searchSkuPercentList(skuPercent);
		} catch (Exception e) {
			logger.error(skuPercent, e);
			e.printStackTrace();
			return null;
		}
	}

	public int searchSkuPercentListCount(SkuPercent skuPercent) {
		try {
			return stockReportDao.searchSkuPercentListCount(skuPercent);
		} catch (Exception e) {
			logger.error(skuPercent, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int updateSkuPercent(SkuPercent skuPercent) {
		try {
			return stockReportDao.updateSkuPercent(skuPercent);
		} catch (Exception e) {
			logger.error(skuPercent, e);
			e.printStackTrace();
			return 0;
		}
	}

	public SkuPercent searchSkuPercentSum(SkuPercent skuPercent) {
		try {
			return stockReportDao.searchSkuPercentSum(skuPercent);
		} catch (Exception e) {
			logger.error(skuPercent, e);
			e.printStackTrace();
			return null;
		}
	}

	// 获取主分户
	public List<Kunag> createKunag() {
		try {
			List<Kunag> list = new ArrayList<Kunag>();
			JCO.Client client = null;
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("ZRFC_KUNNR_KUNAG");
			JCO.Function func = sapConnection.getFunction(client);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"TABLE");
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				String nr = exportTable.getValue("KUNNR").toString();
				String ag = exportTable.getValue("KUNAG").toString();
				if (nr.length() == 10) {
					nr = nr.substring(2, 10);
				}
				if (ag.length() == 10) {
					ag = ag.substring(2, 10);
				}
				Kunag kunag = new Kunag();
				kunag.setKunnr(nr);
				kunag.setKunag(ag);
				kunag.setName1(exportTable.getValue("NAME1").toString());
				kunag.setName2(exportTable.getValue("NAME2").toString());
				stockReportDao.createKunag(kunag);
				list.add(kunag);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public int deleteKunag(Kunag kunag) {
		try {
			return stockReportDao.deleteKunag(kunag);
		} catch (Exception e) {
			logger.error(kunag, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int searchKunagCount(Kunag kunag) {
		try {
			if ("0000".equals(kunag.getKunnr())) {
				createOrderFollowRFCTmp(kunag.getName1(), kunag.getName2());
			}
			return stockReportDao.searchKunagCount(kunag);
		} catch (Exception e) {
			logger.error(kunag, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<Kunag> searchKunag(Kunag kunag) {
		try {
			return stockReportDao.searchKunag(kunag);
		} catch (Exception e) {
			logger.error(kunag, e);
			e.printStackTrace();
			return null;
		}
	}

	public int createSalesGoal(Goal goal) {
		try {
			return stockReportDao.createSalesGoal(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int searchSalesGoalCGListCount(Goal goal) {
		try {
			return stockReportDao.searchSalesGoalCGListCount(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return 0;
		}
	}

	public String searchSalesGoalCGDetailListSum(Goal goal) {
		try {
			return stockReportDao.searchSalesGoalCGDetailListSum(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return null;
		}
	}

	public List<Goal> searchSalesGoalCGList(Goal goal) {
		try {
			return stockReportDao.searchSalesGoalCGList(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return null;
		}
	}

	public int searchSalesGoalCGDetailListCount(Goal goal) {
		try {
			return stockReportDao.searchSalesGoalCGDetailListCount(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<Goal> searchSalesGoalCGDetailList(Goal goal) {
		try {
			List<Goal> list = stockReportDao.searchSalesGoalCGDetailList(goal);
//			if ("N".equals(goal.getGoalMark())) {
//				for (int i = 0; i < list.size(); i++) {
//					Goal g = new Goal();
//					g.setOrgId(list.get(i).getOrgId());
//					g.setGoalYear(list.get(i).getGoalYear());
//					g.setGoalMonth(list.get(i).getGoalMonth());
//					g.setMatterNum(list.get(i).getMatterNum());
//					Double boxNumD = stockReportDao.getGoalCGOrgBoxNumD(g);
//					if (boxNumD != null) {
//						list.get(i).setBoxNumD(boxNumD);
//					}
//				}
//			}
			return list;
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int searchStockCheckCount(StockReport stockReport) {
		try {
			return stockReportDao.searchStockCheckCount(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<StockReport> searchStockCheck(StockReport stockReport) {
		try {
			return stockReportDao.searchStockCheck(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	public int searchStockManagerCount(StockManager stockManager) {
		createStockManagerRFC(stockManager.getCheckTime(),
				stockManager.getDateNow());
		return stockReportDao.searchStockManagerCount(stockManager);
	}

	public List<StockManager> searchStockManagerList(StockManager stockManager) {
		try {
			List<StockManager> list = stockReportDao
					.searchStockManager(stockManager);
			int year = stockManager.getYear();
			int month = stockManager.getMonth();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getLastStock() != null
						&& list.get(i).getLastStock() != 0) {
					StockManagerSap sap = new StockManagerSap();
					sap.setKunnr(list.get(i).getKunnr());
					sap.setMatterNum(list.get(i).getMatterNum());
					sap.setCreateDate(stockManager.getDateNow());
					sap = stockReportDao.getStockManagerSap(sap);
					if (sap != null && sap.getStockPod() != null) {
						list.get(i).setStockPod(sap.getStockPod());
					}
				}
				if (list.get(i).getSkuName() != null) {
					StockManager sm = new StockManager();
					sm.setKunnr(list.get(i).getKunnr());
					sm.setMatterNum(list.get(i).getMatterNum());
					if (month == 12) {
						sm.setYear(year + 1);
						sm.setMonth(1);
					} else {
						sm.setYear(year);
						sm.setMonth(month + 1);
					}
					Double salesGoalA1Next = stockReportDao
							.searchGoalCGByMatterNum(sm);
					if (salesGoalA1Next == null) {
						salesGoalA1Next = 0.0;
					}
					list.get(i).setNextSalesGoalA1(salesGoalA1Next);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.setTime(sdf.parse(stockManager.getDateNow()));
					DecimalFormat df = new DecimalFormat(".00");
					int dayNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
					list.get(i).setSalesDayAvg(
							Double.parseDouble(df.format(list.get(i)
									.getSalesGoalA1() / dayNum)));
					if (list.get(i).getSalesDayAvg() != 0) {
						list.get(i).setSalesDayNum(
								Double.parseDouble(df.format((list.get(i)
										.getLastStock() + list.get(i)
										.getOnWayNum())
										/ list.get(i).getSalesDayAvg())));
					} else {
						list.get(i).setSalesDayNum(0.0);
					}
					if (list.get(i).getSalesDayNum() > 21) {
						list.get(i).setStockStatus("高");
					} else if (list.get(i).getSalesDayNum() > 14
							&& list.get(i).getSalesDayNum() <= 21) {
						list.get(i).setStockStatus("正常");
					} else {
						list.get(i).setStockStatus("低");
					}
					list.get(i).setStockSafe(salesGoalA1Next / 2);
					if (list.get(i).getLastStock() + list.get(i).getOnWayNum() < list
							.get(i).getStockSafe()) {
						list.get(i).setOutPutNeeds("需排产");
					}
					Double takeOrderNeedNum = list.get(i).getStockSafe()
							- (list.get(i).getLastStock()
									+ list.get(i).getOnWayNum() + list.get(i)
									.getOutPutNotNum());
					if (takeOrderNeedNum >= 0) {
						list.get(i).setTakeOrderNeedNum(takeOrderNeedNum);
					} else {
						list.get(i).setTakeOrderNeedNum(0.0);
					}
				}
			}
			return list;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int searchStockManagerReportCount(StockManager stockManager) {
		createStockManagerRFC(stockManager.getCheckTime(),
				stockManager.getDateNow());
		return stockReportDao.searchStockManagerReportCount(stockManager);
	}

	public List<StockManager> searchStockManagerReportList(
			StockManager stockManager) {
		try {
			List<StockManager> list = stockReportDao
					.searchStockManagerReport(stockManager);
			int year = stockManager.getYear();
			int month = stockManager.getMonth();
			for (int i = 0; i < list.size(); i++) {
				StockManager sm = new StockManager();
				sm.setKunnr(list.get(i).getKunnr());
				sm.setMatterNum(list.get(i).getMatterNum());
				if (month == 12) {
					sm.setYear(year + 1);
					sm.setMonth(1);
				} else {
					sm.setYear(year);
					sm.setMonth(month + 1);
				}
				Double salesGoalA1Next = stockReportDao.searchGoalCGByKunnr(sm);
				if (salesGoalA1Next == null) {
					salesGoalA1Next = 0.0;
				}
				list.get(i).setNextSalesGoalA1(salesGoalA1Next);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				DecimalFormat df = new DecimalFormat(".00");
				Calendar cal = Calendar.getInstance();
				cal.setTime(sdf.parse(stockManager.getDateNow()));
				int dayNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				int dayNow = cal.get(Calendar.DAY_OF_MONTH);
				cal.add(Calendar.MONTH, 1);
				int dayNextNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				if (dayNum - dayNow >= 15) {
					list.get(i).setStockSafe1(
							Double.parseDouble(df.format(list.get(i)
									.getSalesGoalA1() / 2)));
				} else {
					double num1 = ((dayNum - dayNow) / (double) dayNum)
							* list.get(i).getSalesGoalA1();
					double num2 = ((15 - (dayNum - dayNow)) / (double) dayNextNum)
							* salesGoalA1Next;
					list.get(i).setStockSafe1(
							Double.parseDouble(df.format(num1 + num2)));
				}
				list.get(i).setStockNotSafe(
						Double.parseDouble(df.format(list.get(i).getLastStock()
								- list.get(i).getStockSafe1())));
				if (list.get(i).getStockNotSafe() < 0) {
					list.get(i).setStockSafeFlag("Y");
				}
				if (dayNum - dayNow >= 21) {
					list.get(i).setStockSafeExpect(
							Double.parseDouble(df.format(list.get(i)
									.getSalesGoalA1() / 30 * 21)));
				} else {
					double num1 = ((dayNum - dayNow) / (double) dayNum)
							* list.get(i).getSalesGoalA1();
					double num2 = ((21 - (dayNum - dayNow)) / (double) dayNextNum)
							* salesGoalA1Next;
					list.get(i).setStockSafeExpect(
							Double.parseDouble(df.format(num1 + num2)));
				}
				list.get(i).setStockRealExpect(
						Double.parseDouble(df.format(list.get(i).getLastStock()
								+ list.get(i).getOnWayNum()
								+ list.get(i).getOutPutNotCheck())));
				list.get(i).setStockNotSafeExpect(
						Double.parseDouble(df.format(list.get(i)
								.getStockRealExpect()
								- list.get(i).getStockSafeExpect())));
			}
			return list;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void createStockManagerRFC(String checkTime, String dateNow) {
		try {
			StockManagerSap stockManagerSap = new StockManagerSap();
			stockManagerSap.setCreateDate(dateNow);
			if (stockReportDao.getStockManagerSapListCount(stockManagerSap) == 0) {
				JCO.Client client = null;
				client = sapConnection.getSAPClientFromPool();
				sapConnection.setFuncName("ZRFC_EXP_JXSGLPT");
				JCO.Function func = sapConnection.getFunction(client);
				JCO.ParameterList input = func.getImportParameterList();
				checkTime = checkTime.substring(0, 4)
						+ checkTime.substring(5, 7)
						+ checkTime.substring(8, 10);
				input.getField("DATE1").setValue(checkTime);
				// input.getField("P_KUNNR").setValue(list.get(i).getKunnr());
				// input.getField("P_MATNR").setValue(list.get(i).getMatterNum());

				client.execute(func);
				JCO.Table exportTable = func.getTableParameterList().getTable(
						"TABLE");
				System.out.println(exportTable.getNumRows());
				for (int j = 0; j < exportTable.getNumRows(); j++, exportTable
						.nextRow()) {
					Double onWayNum = Double.parseDouble(exportTable.getValue(
							"YSZT").toString());
					Double outPutNotNum = Double.parseDouble(exportTable
							.getValue("WPCDD").toString());
					Double tbpod = Double.parseDouble(exportTable.getValue(
							"TBPOD").toString());
					Double pcwgz = Double.parseDouble(exportTable.getValue(
							"PCWGZ").toString());
					String kunnr = exportTable.getValue("KUNNR").toString();
					String matnr = exportTable.getValue("MATNR").toString();
					StockManagerSap sap = new StockManagerSap();
					if (kunnr.length() > 8) {
						kunnr = kunnr.substring(kunnr.length() - 8,
								kunnr.length());
					}
					if (matnr.length() > 8) {
						matnr = matnr.substring(matnr.length() - 8,
								matnr.length());
					}

					if (onWayNum != 0 || outPutNotNum != 0 || pcwgz != 0
							|| tbpod != 0) {
						sap.setKunnr(kunnr);
						sap.setMatterNum(matnr);
						sap.setOnWayNum(onWayNum);
						sap.setOutPutNotNum(outPutNotNum);
						sap.setCreateDate(dateNow);
						sap.setOutPutNotCheck(pcwgz);
						sap.setStockPod(tbpod);
						stockReportDao.createStockManagerSap(sap);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int updateGoalCGDetail(Goal goal) {
		try {
			return stockReportDao.updateGoalCGDetail(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return 0;
		}
	}

	public Double getGoalCGOrgBoxNumD(Goal goal) {
		try {
			return stockReportDao.getGoalCGOrgBoxNumD(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return null;
		}
	}

	public int updateGoalCGDetailBoxNumDToKunnr(String kunnr, String kunnrD) {
		try {
			return stockReportDao.updateGoalCGDetailBoxNumDToKunnr(kunnr,
					kunnrD);
		} catch (Exception e) {
			logger.error(kunnr, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int deleteGoalCGDetail(Goal goal) {
		try {
			return stockReportDao.deleteGoalCGDetail(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int searchOrderCheckListCount(OrderCheck orderCheck) {
		try {
			return stockReportDao.searchOrderCheckListCount(orderCheck);
		} catch (Exception e) {
			logger.error(orderCheck, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<OrderCheck> searchOrderCheckList(OrderCheck orderCheck) {
		try {
			return stockReportDao.searchOrderCheckList(orderCheck);
		} catch (Exception e) {
			logger.error(orderCheck, e);
			e.printStackTrace();
			return null;
		}
	}

	public int createOrderCheck(OrderCheck orderCheck) {
		try {
			return stockReportDao.createOrderCheck(orderCheck);
		} catch (Exception e) {
			logger.error(orderCheck, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int updateOrderCheck(OrderCheck orderCheck) {
		try {
			return stockReportDao.updateOrderCheck(orderCheck);
		} catch (Exception e) {
			logger.error(orderCheck, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<OrderCheck> getStationIdByUserId(String userId) {
		try {
			return stockReportDao.getStationIdByUserId(userId);
		} catch (Exception e) {
			logger.error(userId, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getRoleIdByUserId(String userId){
		try {
			return stockReportDao.getRoleIdByUserId(userId);
		} catch (Exception e) {
			logger.error(userId, e);
			e.printStackTrace();
			return null;
		}
	}

	public int getStockTotalListCount(StockReport stockReport) {
		try {
			return stockReportDao.getStockTotalListCount(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<StockReport> getStockTotalList(StockReport stockReport) {
		try {
			return stockReportDao.getStockTotalList(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	public int searchSalesGoalChallengeListCount(Goal goal) {
		try {
			return stockReportDao.searchSalesGoalChallengeListCount(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<Goal> searchSalesGoalChallengeList(Goal goal) {
		try {
			return stockReportDao.searchSalesGoalChallengeList(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return null;
		}
	}

	public String searchSalesGoalChallengeListSum(Goal goal) {
		try {
			return stockReportDao.searchSalesGoalChallengeListSum(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return null;
		}
	}

	public int updateGoalChallenge(Goal goal) {
		try {
			return stockReportDao.updateGoalChallenge(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int createSalesGoalChallenge(Goal goal) {
		try {
			return stockReportDao.createSalesGoalChallenge(goal);
		} catch (Exception e) {
			logger.error(goal, e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<StockDate> searchKunnrManagerDate(StockDate stockDate) {
		try {
			return stockReportDao.searchKunnrManagerDate(stockDate);
		} catch (Exception e) {
			logger.error(stockDate, e);
			e.printStackTrace();
			return null;
		}
	}

	public int searchKunnrManagerDateCount(StockDate stockDate) {
		try {
			return stockReportDao.searchKunnrManagerDateCount(stockDate);
		} catch (Exception e) {
			logger.error(stockDate, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int updateKunnrManagerDate(StockDate stockDate) {
		try {
			return stockReportDao.updateKunnrManagerDate(stockDate);
		} catch (Exception e) {
			logger.error(stockDate, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int searchKunnrManagerListCount(KunnrManager kunnrManager) {
		try {
			return stockReportDao.searchKunnrManagerListCount(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int searchKunnrManagerCount(KunnrManager kunnrManager) {
		try {
			return stockReportDao.searchKunnrManagerCount(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<KunnrManager> searchKunnrManagerList(KunnrManager kunnrManager) {
		try {
			List<KunnrManager> list = stockReportDao
					.searchKunnrManagerList(kunnrManager);
			DecimalFormat df = new DecimalFormat("0.0");
			for (KunnrManager km : list) {
				KunnrManager kmQuery = new KunnrManager();
				kmQuery.setKunnr(km.getKunnr());
				kmQuery.setCgId(km.getCgId());
				int year = km.getYear();
				int month = km.getMonth();
				double skuUnitCoefficient = 1;
				if (km.getSkuUnitCoefficient() != null) {
					skuUnitCoefficient = km.getSkuUnitCoefficient();
				}

				if (km.getSalesYearGoalEstimate() == null) {
					kmQuery.setYearQuery(year);
					kmQuery.setMonth(month);
					kmQuery.setMonthQuery(null);
					KunnrManager tmp = new KunnrManager();
					tmp = stockReportDao.getKunnrManagerSum(kmQuery);
					if (tmp != null) {
						km.setSalesYearGoalEstimate(tmp.getSalesPlan());
					}
				}

				if (km.getSalesYear() == null) {
					kmQuery.setYearQuery(year);
					kmQuery.setMonth(month);
					kmQuery.setMonthQuery(null);
					Double quantity = stockReportDao
							.getHanaDistributionSkuSum(kmQuery);
					if (quantity != null) {
						km.setSalesYear(quantity);
					}
				}

				if (km.getSalesYear() != null
						&& km.getSalesYearGoalEstimate() != null) {
					km.setSalesYearPercent(df.format(km.getSalesYear()
							/ km.getSalesYearGoalEstimate() * 100)
							+ "%");
				}
			}
			return list;
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}

	public List<KunnrManager> searchKunnrManagerListForExcel(
			KunnrManager kunnrManager) {
		try {
			return stockReportDao.searchKunnrManagerListForExcel(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}

	public int searchKunnrManagerKunnrSumListCount(KunnrManager kunnrManager) {
		try {
			return stockReportDao
					.searchKunnrManagerKunnrSumListCount(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<KunnrManager> searchKunnrManagerKunnrSumList(
			KunnrManager kunnrManager) {
		try {
			List<KunnrManager> list = stockReportDao
					.searchKunnrManagerKunnrSumList(kunnrManager);
			DecimalFormat df = new DecimalFormat("0.0");
			for (KunnrManager km : list) {
				KunnrManager kmQuery = new KunnrManager();
				kmQuery.setKunnr(km.getKunnr());
				int year = km.getYear();
				int month = km.getMonth();

				if (km.getSalesYearGoalEstimate() == null) {
					kmQuery.setYearQuery(year);
					kmQuery.setMonth(month);
					kmQuery.setMonthQuery(null);
					KunnrManager tmp = new KunnrManager();
					tmp = stockReportDao.getKunnrManagerSum(kmQuery);
					if (tmp != null) {
						km.setSalesYearGoalEstimate(tmp.getSalesPlan());
					}
				}

				if (km.getSalesYear() == null) {
					kmQuery.setYearQuery(year);
					kmQuery.setMonth(month);
					kmQuery.setMonthQuery(null);
					Double quantity = stockReportDao
							.getHanaDistributionSkuSum(kmQuery);
					if (quantity != null) {
						km.setSalesYear(quantity);
					}
				}

				if (km.getSalesYear() != null
						&& km.getSalesYearGoalEstimate() != null) {
					km.setSalesYearPercent(df.format(km.getSalesYear()
							/ km.getSalesYearGoalEstimate() * 100)
							+ "%");
				}
			}
			return list;
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}

	public int searchKunnrManagerSkuListCount(KunnrManager kunnrManager) {
		try {
			return stockReportDao.searchKunnrManagerSkuListCount(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<KunnrManager> searchKunnrManagerSkuList(
			KunnrManager kunnrManager) {
		try {
			List<KunnrManager> list = stockReportDao
					.searchKunnrManagerSkuList(kunnrManager);
			DecimalFormat df = new DecimalFormat("0.0");
			for (KunnrManager km : list) {
				KunnrManager kmQuery = new KunnrManager();
				kmQuery.setKunnr(km.getKunnr());
				kmQuery.setSkuId(km.getSkuId());
				kmQuery.setCgId(km.getCgId());
				int year = km.getYear();
				int month = km.getMonth();

				if (km.getSalesYearGoalEstimate() == null) {
					kmQuery.setYearQuery(year);
					kmQuery.setMonth(month);
					kmQuery.setMonthQuery(null);
					KunnrManager tmp = new KunnrManager();
					tmp = stockReportDao.getKunnrManagerSum(kmQuery);
					if (tmp != null && tmp.getSalesPlan() != null) {
						km.setSalesYearGoalEstimate(Double.parseDouble(df
								.format(tmp.getSalesPlan() * km.getPercent()
										/ 100 / km.getSkuUnitCoefficient())));
					}
				}

				if (km.getSalesYear() == null) {
					kmQuery.setYearQuery(year);
					kmQuery.setMonth(month);
					kmQuery.setMonthQuery(null);
					Double quantity = stockReportDao
							.getHanaDistributionSkuSum(kmQuery);
					if (quantity != null) {
						km.setSalesYear(Double.parseDouble(df.format(quantity
								/ km.getSkuUnitCoefficient())));
					}
				}

				if (km.getSalesYear() != null
						&& km.getSalesYearGoalEstimate() != null) {
					km.setSalesYearPercent(df.format(km.getSalesYear()
							/ km.getSalesYearGoalEstimate() * 100)
							+ "%");
				}
			}
			return list;
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}

	public int createKunnrManager(KunnrManager kunnrManager) {
		try {
			return stockReportDao.createKunnrManager(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return 0;
		}
	}

	public void createUnitCoefficientRFC() {
		try {
			Map<String, Double> unitBox = new HashMap<String, Double>();
			Map<String, Double> unitBBox = new HashMap<String, Double>();
			JCO.Client client = null;
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("ZRFC_MATNR_DWZH");
			JCO.Function func = sapConnection.getFunction(client);
			JCO.Table input = func.getTableParameterList().getTable("IT_MATNR");
			// JCO.ParameterList input = func.getImportParameterList();
			SkuUnit skuUnit = new SkuUnit();
			skuUnit.setStart(0);
			skuUnit.setEnd(99999);
			List<SkuUnit> skuList = stockReportDao
					.searchSkuUnitCoefficientList(skuUnit);
			for (SkuUnit sku : skuList) {
				input.appendRow();
				input.getField("MATNR").setValue(sku.getMatterNum());
			}
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"OT_DWZH");
			System.out.println(exportTable.getNumRows());
			for (int j = 0; j < exportTable.getNumRows(); j++, exportTable
					.nextRow()) {
				String matnr = exportTable.getValue("MATNR").toString();
				String unit1 = exportTable.getValue("MEINH").toString();
				String unit2 = exportTable.getValue("MEINS").toString();
				Double quantity1 = Double.parseDouble(exportTable.getValue(
						"UMREZ").toString());
				Double quantity2 = Double.parseDouble(exportTable.getValue(
						"UMREN").toString());
				if (matnr.length() > 8) {
					matnr = matnr.substring(matnr.length() - 8, matnr.length());
				}
				
				if ("箱".equals(unit1) && "杯".equals(unit2)) {
					unitBox.put(matnr, quantity1 / quantity2);
				} else if ("标箱".equals(unit1) && "杯".equals(unit2)) {
					unitBBox.put(matnr, quantity1 / quantity2);
				}
				
				if ("箱".equals(unit1) && "包".equals(unit2)) {
					unitBox.put(matnr, quantity1 / quantity2);
				} else if ("标箱".equals(unit1) && "包".equals(unit2)) {
					unitBBox.put(matnr, quantity1 / quantity2);
				}
			}
			List<SkuUnit> list = new ArrayList<SkuUnit>();
			for (SkuUnit sku : skuList) {
				Double num1 = unitBox.get(sku.getMatterNum());
				Double num2 = unitBBox.get(sku.getMatterNum());
				if (num1 != null && num2 != null) {
					SkuUnit s = new SkuUnit();
					s.setMatterNum(sku.getMatterNum());
					s.setSkuId(sku.getSkuId());
					s.setUnitCoefficient(num1 / num2);
					list.add(s);
				}
			}
			createSkuVolumRFC(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createSkuVolumRFC(List<SkuUnit> list) {
		try {
			Map<String, Double> volumMap = new HashMap<String, Double>();
			JCO.Client client = null;
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("ZRFC_MATNR");
			JCO.Function func = sapConnection.getFunction(client);
//			JCO.Table input = func.getTableParameterList().getTable("IT_MATNR");
			JCO.ParameterList input = func.getImportParameterList();
			input.getField("MATNR_1").setValue("10100000");
			input.getField("MATNR_2").setValue("12100000");
//			SkuUnit skuUnit = new SkuUnit();
//			skuUnit.setStart(0);
//			skuUnit.setEnd(99999);
//			List<SkuUnit> skuList = stockReportDao
//					.searchSkuUnitCoefficientList(skuUnit);
//			for (SkuUnit sku : skuList) {
//				input.appendRow();
//				input.getField("MATNR").setValue(sku.getMatterNum());
//			}
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"WLZSJ");
			System.out.println(exportTable.getNumRows());
			for (int j = 0; j < exportTable.getNumRows(); j++, exportTable
					.nextRow()) {
				String matnr = exportTable.getValue("MATNR").toString();
				String volum = exportTable.getValue("VOLUM").toString();
				if (matnr.length() > 8) {
					matnr = matnr.substring(matnr.length() - 8, matnr.length());
				}
				volumMap.put(matnr, Double.valueOf(volum));
			}
			stockReportDao.deleteSkuUnitCoefficient();
			for (SkuUnit sku : list) {
				Double volum = volumMap.get(sku.getMatterNum());
				if (volum != null) {
					sku.setVolum(volum);
					stockReportDao.createSkuUnitCoefficient(sku);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createUnitSapRFC() {
		try {
			JCO.Client client = null;
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("ZRFC_MATNR_DWZH");
			JCO.Function func = sapConnection.getFunction(client);
			JCO.Table input = func.getTableParameterList().getTable("IT_MATNR");
			// JCO.ParameterList input = func.getImportParameterList();
			SkuUnit skuUnit = new SkuUnit();
			skuUnit.setStart(0);
			skuUnit.setEnd(99999);
			List<SkuUnit> skuList = stockReportDao
					.searchSkuUnitCoefficientList(skuUnit);
			for (SkuUnit sku : skuList) {
				input.appendRow();
				input.getField("MATNR").setValue(sku.getMatterNum());
			}
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"OT_DWZH");
			System.out.println(exportTable.getNumRows());
			stockReportDao.deleteSkuUnitSap();
			for (int j = 0; j < exportTable.getNumRows(); j++, exportTable
					.nextRow()) {
				String matnr = exportTable.getValue("MATNR").toString();
				String unit1 = exportTable.getValue("MEINH").toString();
				String unit2 = exportTable.getValue("MEINS").toString();
				Double quantity1 = Double.parseDouble(exportTable.getValue(
						"UMREZ").toString());
				Double quantity2 = Double.parseDouble(exportTable.getValue(
						"UMREN").toString());
				if (matnr.length() > 8) {
					matnr = matnr.substring(matnr.length() - 8, matnr.length());
				}
				SkuUnit s = new SkuUnit();
				s.setMatterNum(matnr);
				s.setMEINH(unit1);
				s.setMEINS(unit2);
				s.setUMREZ(quantity1);
				s.setUMREN(quantity2);
				stockReportDao.createSkuUnitSap(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int searchSkuUnitCoefficientListCount(SkuUnit skuUnit) {
		try {
			return stockReportDao.searchSkuUnitCoefficientListCount(skuUnit);
		} catch (Exception e) {
			logger.error(skuUnit, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<SkuUnit> searchSkuUnitCoefficientList(SkuUnit skuUnit) {
		try {
			return stockReportDao.searchSkuUnitCoefficientList(skuUnit);
		} catch (Exception e) {
			logger.error(skuUnit, e);
			e.printStackTrace();
			return null;
		}
	}

	public KunnrManager getKunnrManagerListSum(KunnrManager kunnrManager) {
		try {
			KunnrManager km = stockReportDao
					.getKunnrManagerListSum(kunnrManager);
			DecimalFormat df = new DecimalFormat("0.0");

			if (km.getSalesPlan() != null) {
				km.setSalesPlanFinal(km.getSalesPlan());
				if (km.getSalesReal() != null) {
					km.setSalesPlanPercent(df.format(km.getSalesReal()
							/ km.getSalesPlan() * 100)
							+ "%");
				}
			}
			if (km.getSalesLastMonth() != null
					&& km.getSalesLastMonthFinal() != null) {
				km.setSalesLastMonthPercent(df.format(km.getSalesLastMonth()
						/ km.getSalesLastMonthFinal() * 100)
						+ "%");
			}
			if (km.getSalesYear() != null
					&& km.getSalesYearGoalEstimate() != null) {
				km.setSalesYearPercent(df.format(km.getSalesYear()
						/ km.getSalesYearGoalEstimate() * 100)
						+ "%");
			}
			return km;
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}

	public void createOrderFollowRFC(List<OrderFollow> list) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -2);
			String dateStart = sdf.format(cal.getTime());
			String dateNow = sdf.format(new Date());

			JCO.Client client = null;
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("ZRFC_ORDER_STATUS_TRACK");
			JCO.Function func = sapConnection.getFunction(client);
			JCO.ParameterList input = func.getImportParameterList();
			JCO.Table inputTable = func.getTableParameterList().getTable(
					"IT_KUNNR_MATNR");
			input.getField("BEGIN_DATE").setValue(dateStart);
			input.getField("END_DATE").setValue(dateNow);
			for (OrderFollow of : list) {
				inputTable.appendRow();
				inputTable.getField("KUNNR").setValue("00" + of.getKunnr());
			}
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"LT_ORDER_STATUS");
			System.out.println(exportTable.getNumRows());
			for (int j = 0; j < exportTable.getNumRows(); j++, exportTable
					.nextRow()) {
				OrderFollow of = new OrderFollow();
				String matnr = exportTable.getValue("MATNR").toString();
				String kunnr = exportTable.getValue("KUNNR").toString();
				Date ERDAT = (Date) exportTable.getValue("ERDAT");
				Date AUDAT = (Date) exportTable.getValue("AUDAT");
				Date PEDTR = (Date) exportTable.getValue("PEDTR");
				Date WADAT_IST = (Date) exportTable.getValue("WADAT_IST");
				Date PODAT = (Date) exportTable.getValue("PODAT");

				if (matnr.length() > 8) {
					matnr = matnr.substring(matnr.length() - 8, matnr.length());
				}
				if (kunnr.length() > 8) {
					kunnr = kunnr.substring(kunnr.length() - 8, kunnr.length());
				}
				of.setKunnr(kunnr);
				of.setMATNR(matnr);
				if (ERDAT != null) {
					of.setERDAT(sdf.format(ERDAT));
				}
				if (AUDAT != null) {
					of.setAUDAT(sdf.format(AUDAT));
				}
				if (PEDTR != null) {
					of.setPEDTR(sdf.format(PEDTR));
				}
				if (WADAT_IST != null) {
					of.setWADAT_IST(sdf.format(WADAT_IST));
				}
				if (PODAT != null) {
					of.setPODAT(sdf.format(PODAT));
				}

				of.setPOSNR1(exportTable.getValue("POSNR1").toString());
				of.setKWMENG(Double.parseDouble(exportTable.getValue("KWMENG")
						.toString()));
				of.setVBELN2(exportTable.getValue("VBELN2").toString());
				of.setTKNUM(exportTable.getValue("TKNUM").toString());
				of.setVBELN3(exportTable.getValue("VBELN3").toString());
				of.setWBSTK(exportTable.getValue("WBSTK").toString());
				of.setVBELN1(exportTable.getValue("VBELN1").toString());
				of.setPOSNR2(exportTable.getValue("POSNR2").toString());
				of.setPOSNR3(exportTable.getValue("POSNR3").toString());
				of.setPDSTK(exportTable.getValue("PDSTK").toString());
				of.setCreateDateStr(sdf1.format(new Date()));
				stockReportDao.deleteOrderStatusSap(of);
				stockReportDao.createOrderStatusSap(of);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createOrderFollowRFCTmp(String startDate, String endDate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			JCO.Client client = null;
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("ZRFC_ORDER_STATUS_TRACK");
			JCO.Function func = sapConnection.getFunction(client);
			JCO.ParameterList input = func.getImportParameterList();
			input.getField("BEGIN_DATE").setValue(startDate);
			input.getField("END_DATE").setValue(endDate);
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"LT_ORDER_STATUS");
			System.out.println(exportTable.getNumRows());
			for (int j = 0; j < exportTable.getNumRows(); j++, exportTable
					.nextRow()) {
				OrderFollow of = new OrderFollow();
				String matnr = exportTable.getValue("MATNR").toString();
				String kunnr = exportTable.getValue("KUNNR").toString();
				Date ERDAT = (Date) exportTable.getValue("ERDAT");
				Date AUDAT = (Date) exportTable.getValue("AUDAT");
				Date PEDTR = (Date) exportTable.getValue("PEDTR");
				Date WADAT_IST = (Date) exportTable.getValue("WADAT_IST");
				Date PODAT = (Date) exportTable.getValue("PODAT");

				if (matnr.length() > 8) {
					matnr = matnr.substring(matnr.length() - 8, matnr.length());
				}
				if (kunnr.length() > 8) {
					kunnr = kunnr.substring(kunnr.length() - 8, kunnr.length());
				}
				of.setKunnr(kunnr);
				of.setMATNR(matnr);
				if (ERDAT != null) {
					of.setERDAT(sdf.format(ERDAT));
				}
				if (AUDAT != null) {
					of.setAUDAT(sdf.format(AUDAT));
				}
				if (PEDTR != null) {
					of.setPEDTR(sdf.format(PEDTR));
				}
				if (WADAT_IST != null) {
					of.setWADAT_IST(sdf.format(WADAT_IST));
				}
				if (PODAT != null) {
					of.setPODAT(sdf.format(PODAT));
				}

				of.setPOSNR1(exportTable.getValue("POSNR1").toString());
				of.setKWMENG(Double.parseDouble(exportTable.getValue("KWMENG")
						.toString()));
				of.setVBELN2(exportTable.getValue("VBELN2").toString());
				of.setTKNUM(exportTable.getValue("TKNUM").toString());
				of.setVBELN3(exportTable.getValue("VBELN3").toString());
				of.setWBSTK(exportTable.getValue("WBSTK").toString());
				of.setVBELN1(exportTable.getValue("VBELN1").toString());
				of.setPOSNR2(exportTable.getValue("POSNR2").toString());
				of.setPOSNR3(exportTable.getValue("POSNR3").toString());
				of.setPDSTK(exportTable.getValue("PDSTK").toString());
				of.setCreateDateStr(sdf1.format(new Date()));
				stockReportDao.createOrderStatusSap(of);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int searchOrderFollowListCount(OrderFollow orderFollow) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateNow = sdf.format(new Date());
			List<OrderFollow> list = new ArrayList<OrderFollow>();
			if (orderFollow.getOrgId() != null
					|| StringUtils.isNotEmpty(orderFollow.getKunnr())) {
				orderFollow.setCreateDateStr(dateNow);
				list = stockReportDao.searchOrderStatusSapCount(orderFollow);
				if (list.size() > 0) {
					createOrderFollowRFC(list);
				}
			}
			return stockReportDao.searchOrderFollowListCount(orderFollow);
		} catch (Exception e) {
			logger.error(orderFollow, e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<OrderFollow> searchOrderFollowList(OrderFollow orderFollow) {
		try {
			return stockReportDao.searchOrderFollowList(orderFollow);
		} catch (Exception e) {
			logger.error(orderFollow, e);
			e.printStackTrace();
			return null;
		}
	}

	public OrderFollow getOrderFollowSum(OrderFollow orderFollow) {
		try {
			return stockReportDao.getOrderFollowSum(orderFollow);
		} catch (Exception e) {
			logger.error(orderFollow, e);
			e.printStackTrace();
			return null;
		}
	}

	public int createSalesPlanChangeDetail(SkuDistribution skuDistribution) {
		try {
			return stockReportDao.createSalesPlanChangeDetail(skuDistribution);
		} catch (Exception e) {
			logger.error(skuDistribution, e);
			e.printStackTrace();
			return 0;
		}
	}

	public int updateSalesPlanChangeDetail(SkuDistribution skuDistribution) {
		try {
			return stockReportDao.updateSalesPlanChangeDetail(skuDistribution);
		} catch (Exception e) {
			logger.error(skuDistribution, e);
			e.printStackTrace();
			return 0;
		}
	}

	public SkuDistribution getSalesPlanChangeDetail(
			SkuDistribution skuDistribution) {
		try {
			return stockReportDao.getSalesPlanChangeDetail(skuDistribution);
		} catch (Exception e) {
			logger.error(skuDistribution, e);
			e.printStackTrace();
			return null;
		}
	}

	public List<SkuDistribution> searchSalesPlanChangeDetail(
			SkuDistribution skuDistribution) {
		try {
			return stockReportDao.searchSalesPlanChangeDetail(skuDistribution);
		} catch (Exception e) {
			logger.error(skuDistribution, e);
			e.printStackTrace();
			return null;
		}
	}

	public int searchSalesPlanChangeDetailCount(SkuDistribution skuDistribution) {
		try {
			return stockReportDao
					.searchSalesPlanChangeDetailCount(skuDistribution);
		} catch (Exception e) {
			logger.error(skuDistribution, e);
			e.printStackTrace();
			return 0;
		}
	}

	public Long getSkuDistributionDetailId() {
		try {
			return stockReportDao.getSkuDistributionDetailId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public IStockReportDao getStockReportDao() {
		return stockReportDao;
	}

	public void setStockReportDao(IStockReportDao stockReportDao) {
		this.stockReportDao = stockReportDao;
	}

	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}

	public int createStockStateManager(final List<StockReport> list) {
		try {
			return stockReportDao.createStockStateManager(list);
		} catch (Exception e) {
			logger.error(list, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int searchStockStateManagerCount(StockReport stockReport) {
		try {
			return stockReportDao.searchStockStateManagerCount(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<StockReport> searchStockStateManagerList(StockReport stockReport) {
		try {
			return stockReportDao.searchStockStateManagerList(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public StockReport searchStockStateManagerTotal(StockReport stockReport) {
		try {
			return stockReportDao.searchStockStateManagerTotal(stockReport);
		} catch (Exception e) {
			logger.error(stockReport, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<StockReport> searchKunnrManagerMessage(List<StockReport> list) {
		try {
			return stockReportDao.searchKunnrManagerMessage(list);
		} catch (Exception e) {
			logger.error(list, e);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据组织ID,获取用户列表
	 * 
	 * @param 根据orgId查找用户信息
	 * @return
	 */
	public List<AllUsers> getEmpListByOrgId(String orgId) {
		try {
			return stockReportDao.getEmpListByOrgId(orgId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Station> getEmpListByUserName(Station station) {
		try {
			return stockReportDao.getEmpListByUserName(station);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<AllUsers> getWorkFlowStation(Long eventId) {
		try {
			return stockReportDao.getWorkFlowStation(eventId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public int searchKunnrManagerKunnrByOrgSumListCount(
			KunnrManager kunnrManager) {
		try {
			return stockReportDao
					.searchKunnrManagerKunnrByOrgSumListCount(kunnrManager);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public List<KunnrManager> searchKunnrManagerKunnrByOrgSumList(
			KunnrManager kunnrManager) {
		try {
			List<KunnrManager> list = stockReportDao
					.searchKunnrManagerKunnrByOrgSumList(kunnrManager);
			DecimalFormat df = new DecimalFormat("0.0");
			for (KunnrManager km : list) {
				KunnrManager kmQuery = new KunnrManager();
				kmQuery.setKunnr(km.getKunnr());
				int year = km.getYear();
				int month = km.getMonth();

				if (km.getSalesYearGoalEstimate() == null) {
					kmQuery.setYearQuery(year);
					kmQuery.setMonth(month);
					kmQuery.setMonthQuery(null);
					KunnrManager tmp = new KunnrManager();
					tmp = stockReportDao.getKunnrManagerSum(kmQuery);
					if (tmp != null) {
						km.setSalesYearGoalEstimate(tmp.getSalesPlan());
					}
				}

				if (km.getSalesYear() == null) {
					kmQuery.setYearQuery(year);
					kmQuery.setMonth(month);
					kmQuery.setMonthQuery(null);
					Double quantity = stockReportDao
							.getHanaDistributionSkuSum(kmQuery);
					if (quantity != null) {
						km.setSalesYear(quantity);
					}
				}

				if (km.getSalesYear() != null
						&& km.getSalesYearGoalEstimate() != null) {
					km.setSalesYearPercent(df.format(km.getSalesYear()
							/ km.getSalesYearGoalEstimate() * 100)
							+ "%");
				}
			}
			return list;
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}

	public int getOrgCityByOrgId(Long orgId) {
		try {
			return stockReportDao.getOrgCityByOrgId(orgId);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public int getCgCount(Sku sku) {
		try {
			return stockReportDao.getCgCount(sku);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public int searchCustomerStockListCount(StockReport stockReport) {
		try {
			return stockReportDao.searchCustomerStockListCount(stockReport);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public List<StockReport> searchCustomerStockList(StockReport stockReport) {
		try {
			return stockReportDao.searchCustomerStockList(stockReport);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public int createCustomerStock(StockReport stockReport) {
		try {
			return stockReportDao.createCustomerStock(stockReport);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public int updateCustomerStock(StockReport stockReport) {
		try {
			return stockReportDao.updateCustomerStock(stockReport);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	
	public int createOrUpdateCustomerStock(StockReport stockReport){
		try {
			return stockReportDao.createOrUpdateCustomerStock(stockReport);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public int searchSkuUnitListCount(SkuUnit skuUnit){
		try {
			return stockReportDao.searchSkuUnitListCount(skuUnit);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public List<SkuUnit> searchSkuUnitList(SkuUnit skuUnit){
		try {
			return stockReportDao.searchSkuUnitList(skuUnit);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<SkuUnit> searchSkuUnitSap(SkuUnit skuUnit){
		try {
			return stockReportDao.searchSkuUnitSap(skuUnit);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public int updateSkuUnit(SkuUnit skuUnit){
		try {
			int count=stockReportDao.updateSkuUnit(skuUnit);
			if(count==0){
				return stockReportDao.createSkuUnit(skuUnit);
			}else{
				return count;
			}
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public int searchCustomerStockByDateListCount(StockReport stockReport){
		try {
			return stockReportDao.searchCustomerStockByDateListCount(stockReport);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public List<StockReport> searchCustomerStockByDateList(StockReport stockReport){
		try {
			return stockReportDao.searchCustomerStockByDateList(stockReport);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public int searchCustomerStockByDateDescListCount(StockReport stockReport){
		try {
			return stockReportDao.searchCustomerStockByDateDescListCount(stockReport);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	public List<StockReport> searchCustomerStockByDateDescList(StockReport stockReport){
		try {
			return stockReportDao.searchCustomerStockByDateDescList(stockReport);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<String> getKunnrIdByKunag(String kunnr) {
		try {
			return stockReportDao.getKunnrIdByKunag(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public Date getSysdate(){
		try {
			return stockReportDao.getSysdate();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public String getKunnrImportant(String kunnr) {
		try {
			return stockReportDao.getKunnrImportant(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public void updateOperationUser(Goal goal1) {
		try {
			stockReportDao.updateOperationUser(goal1);
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
