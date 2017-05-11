package com.kintiger.platform.kunnrManager.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.framework.sap.SAPConnectionBean;
import com.kintiger.platform.kunnrManager.dao.IKunnrManagerDao;
import com.kintiger.platform.kunnrManager.pojo.DateControl;
import com.kintiger.platform.kunnrManager.pojo.KunnrManager;
import com.kintiger.platform.kunnrManager.pojo.Sku;
import com.kintiger.platform.kunnrManager.pojo.StockSafety;
import com.kintiger.platform.kunnrManager.service.IKunnrManagerService;
import com.sap.mw.jco.JCO;

public class KunnrManagerServiceImpl implements IKunnrManagerService {
	private IKunnrManagerDao kunnrManagerDao;
	private SAPConnectionBean sapConnection;
	private static Log logger = LogFactory.getLog(KunnrManagerServiceImpl.class);

	public IKunnrManagerDao getKunnrManagerDao() {
		return kunnrManagerDao;
	}

	public void setKunnrManagerDao(IKunnrManagerDao kunnrManagerDao) {
		this.kunnrManagerDao = kunnrManagerDao;
	}

	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}
	
	@Override
	public int searchKunnrManagerListCount(KunnrManager kunnrManager) {
		try {
			return kunnrManagerDao.searchKunnrManagerListCount(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<KunnrManager> searchKunnrManagerList(KunnrManager kunnrManager) {
		try {
			return kunnrManagerDao.searchKunnrManagerList(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Sku> searchSkuList(Sku sku) {
		try {
			return kunnrManagerDao.searchSkuList(sku);
		} catch (Exception e) {
			logger.error(sku, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int searchKunnrManagerDateCount(DateControl dateControl) {
		try {
			return kunnrManagerDao.searchKunnrManagerDateCount(dateControl);
		} catch (Exception e) {
			logger.error(dateControl, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateKunnrManager(KunnrManager kunnrManager) {
		try {
			int num = kunnrManagerDao.searchKunnrManagerListCount(kunnrManager);

			if (num > 0) {
				kunnrManagerDao.updateKunnrManager(kunnrManager);
			} else {
				kunnrManagerDao.createKunnrManager(kunnrManager);
			}
			return num;
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public int deleteKunnrManager(KunnrManager kunnrManager) {
		try {
			int num = kunnrManagerDao.deleteKunnrManager(kunnrManager);
			return num;
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateKunnrImportant(KunnrManager kunnrManager) {
		try {
			return kunnrManagerDao.updateKunnrImportant(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public KunnrManager getKunnrManagerListSum(KunnrManager kunnrManager) {
		try {
			return kunnrManagerDao.getKunnrManagerListSum(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int searchStockSafetyListCount(StockSafety stockSafety) {
		try {
			return kunnrManagerDao.searchStockSafetyListCount(stockSafety);
		} catch (Exception e) {
			logger.error(stockSafety, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<StockSafety> searchStockSafetyList(StockSafety stockSafety) {
		try {
			return kunnrManagerDao.searchStockSafetyList(stockSafety);
		} catch (Exception e) {
			logger.error(stockSafety, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int createStockSafety(StockSafety stockSafety) {
		try {
			return kunnrManagerDao.createStockSafety(stockSafety);
		} catch (Exception e) {
			logger.error(stockSafety, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateStockSafety(StockSafety stockSafety) {
		try {
			int count=kunnrManagerDao.searchStockSafetyListCount(stockSafety);
			int num=0;
			if(count>0){
				kunnrManagerDao.updateStockSafety(stockSafety);
			}else{
				num=kunnrManagerDao.createStockSafety(stockSafety);
			}
			return num;
		} catch (Exception e) {
			logger.error(stockSafety, e);
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public String getKunagByKunnr(String kunnr){
		try {
			return kunnrManagerDao.getKunagByKunnr(kunnr);
		} catch (Exception e) {
			logger.error(kunnr, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int searchProductionPlanListCount(KunnrManager kunnrManager) {
		try {
			return kunnrManagerDao.searchProductionPlanListCount(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<KunnrManager> searchProductionPlanList(KunnrManager kunnrManager) {
		try {
			Map<String,Map<String,Double>> kunnrMap=new HashMap<String,Map<String,Double>>();
			Map<String,Map<Long,KunnrManager>> orderMap=new HashMap<String,Map<Long,KunnrManager>>();
			Map<String,Map<String,Double>> actualMap=new HashMap<String,Map<String,Double>>();
			List<KunnrManager> list=kunnrManagerDao.searchProductionPlanList(kunnrManager);
			for(KunnrManager km:list){
				if(km.getSalesPlan()!=null && km.getStockSafety()!=null
						&& km.getEstimateStockLast()!=null){
					BigDecimal b1 = new BigDecimal(handleNull(km.getSalesPlan()));
					BigDecimal b2 = new BigDecimal(handleNull(km.getStockSafety()));
					BigDecimal b3 = new BigDecimal(handleNull(km.getEstimateStockLast()));
					km.setProductionPlan(b1.add(b2).subtract(b3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
					if(km.getProductionPlan()<0){
						km.setProductionPlan(0.0);
					}
				}
				
				if(km.getSalesPlanNext()!=null && km.getStockSafetyNext()!=null
						&& km.getEstimateStock()!=null){
					BigDecimal b1 = new BigDecimal(handleNull(km.getSalesPlanNext()));
					BigDecimal b2 = new BigDecimal(handleNull(km.getStockSafetyNext()));
					BigDecimal b3 = new BigDecimal(handleNull(km.getEstimateStock()));
					km.setProductionPlanNext(b1.add(b2).subtract(b3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
					if(km.getProductionPlanNext()<0){
						km.setProductionPlanNext(0.0);
					}
				}
				if(km.getYear()!=null && km.getMonth()!=null){
//					if(km.getSalesTotal()==null){
//						String kunnr = km.getKunnr();
//						String start=km.getYear()+"0101";
//						String end=km.getYear()+"1231";
//						
//						while(kunnr.length()<10){
//							kunnr="0"+kunnr;
//						}
//						Map<String,Double> map=kunnrMap.get(kunnr+","+start+","+end);
//						if(map!=null){
//							km.setSalesTotal(map.get(km.getSkuId()+","+Integer.valueOf(km.getMonth())));
//						}else{
//							map = getSalesHanaByDate(kunnr,start,end);
//							km.setSalesTotal(map.get(km.getSkuId()+","+Integer.valueOf(km.getMonth())));
//							kunnrMap.put(kunnr+","+start+","+end, map);
//						}
//					}
//					
//					if(km.getSalesYear()==null){
//						String kunnr = km.getKunnr();
//						String date = km.getYear()+km.getMonth();
//						
////						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
////						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
////						Date endDate=sdf.parse(date);
////						Calendar c1= Calendar.getInstance();
////						c1.setTime(endDate);
////						c1.set(Calendar.DAY_OF_MONTH, c1.getActualMaximum(Calendar.DAY_OF_MONTH));
////						endDate=c1.getTime();
//						String start=km.getYear()+"0101";
//						String end=km.getYear()+"1231";
//						
//						while(kunnr.length()<10){
//							kunnr="0"+kunnr;
//						}
//						Map<String,Double> map=kunnrMap.get(kunnr+","+start+","+end);
//						if(map!=null){
//							Double salesYear=0.0;
//							int month = Integer.valueOf(km.getMonth());
//							for(int i=1;i<=month;i++){
//								if(map.get(km.getSkuId()+","+i)!=null){
//									salesYear+=map.get(km.getSkuId()+","+i);
//								}
//							}
//							km.setSalesYear(salesYear);
//						}else{
//							map = getSalesHanaByDate(kunnr,start,end);
//							kunnrMap.put(kunnr+","+start+","+end, map);
//							Double salesYear=0.0;
//							int month = Integer.valueOf(km.getMonth());
//							for(int i=1;i<=month;i++){
//								salesYear+=map.get(km.getSkuId()+","+i);
//							}
//							km.setSalesYear(salesYear);
//						}
//					}
					if(km.getNotPutOrder()==null){
						String kunnr = km.getKunnr();
						String date = km.getYear()+km.getMonth();
						
						while(kunnr.length()<10){
							kunnr="0"+kunnr;
						}
						Map<Long,KunnrManager> map=orderMap.get(kunnr+","+date);
						if(map!=null){
							KunnrManager tmp=map.get(km.getSkuId());
							if(tmp!=null){
								km.setNotPutOrder(tmp.getNotPutOrder());
								km.setPutNotTakeOrder(tmp.getPutNotTakeOrder());
								km.setOrderOnWay(tmp.getOrderOnWay());
							}
						}else{
							map = getJXSGLPTRFC(kunnr,date);
							KunnrManager tmp=map.get(km.getSkuId());
							if(tmp!=null){
								km.setNotPutOrder(tmp.getNotPutOrder());
								km.setPutNotTakeOrder(tmp.getPutNotTakeOrder());
								km.setOrderOnWay(tmp.getOrderOnWay());
							}
							orderMap.put(kunnr+","+date, map);
						}
					}
					
					if(km.getProductionPlanTotal()==null){
						String kunnr = km.getKunnr();
						String startDate = kunnrManager.getStartDate();
						String endDate = kunnrManager.getEndDate();
						
						while(kunnr.length()<10){
							kunnr="0"+kunnr;
						}
						
						Map<String,Double> map=actualMap.get(kunnr);
						Double actual=0.0;
						if(map!=null){
							actual=map.get(km.getSkuId()+","+km.getYear()+","+Integer.parseInt(km.getMonth()));
						}else{
							map = getSJCKRFC(kunnr,startDate,endDate);;
							actual=map.get(km.getSkuId()+","+km.getYear()+","+Integer.parseInt(km.getMonth()));
							actualMap.put(kunnr, map);
						}
						
						BigDecimal b1 = new BigDecimal(handleNull(km.getNotPutOrder()));
						BigDecimal b2 = new BigDecimal(handleNull(km.getPutNotTakeOrder()));
						BigDecimal b3 = new BigDecimal(handleNull(actual));
						km.setProductionPlanTotal(b1.add(b2).add(b3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
					}
					
					if(km.getSalesYearGoal()==null){
						KunnrManager tmp = new KunnrManager();
						tmp.setKunnr(km.getKunnr());
						tmp.setSkuId(km.getSkuId());
						tmp.setYear(km.getYear());
						tmp.setMonth(km.getMonth());
						Double sum=kunnrManagerDao.getKunnrManagerYear(tmp);
						km.setSalesYearGoal(handleNull(sum));
					}
					
					if(km.getSalesYear()==null){
						String kunnr = km.getKunnr();
						
						while(kunnr.length()<10){
							kunnr="0"+kunnr;
						}
						
						KunnrManager tmp = new KunnrManager();
						tmp.setKunnr(kunnr);
						tmp.setSkuId(km.getSkuId());
						tmp.setYear(km.getYear());
						tmp.setMonth(km.getMonth());
						Double sum=kunnrManagerDao.getSalesYear(tmp);
						km.setSalesYear(handleNull(sum));
					}
				}
			}
			return list;
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public static Map<String,Double> getSalesHanaByDate(String kunnr,String startDate,String endDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,Double> map = new HashMap<String,Double>();
		
		final String DRIVER = "com.sap.db.jdbc.Driver";  //jdbc 4.0
		final String URL = "jdbc:sap://10.0.3.20:30015?Catalog=_SYS_BIC&reconnect=true";
		
		try {
		
		String sql="SELECT * FROM ("
				+ "SELECT YEAR,"  //年
				+ "MONTH,"  //月
				+ "ORG_ID,"  //组织ID
				+ "KUNNR,"  //经销商
				+ "SKU_ID,"  //skuid
				+ "SUM(ZXKC) AS ZXKC,"  //最新库存(标箱)
				+ "SUM(ZXKC_MONEY) AS ZXKC_MONEY,"  //最新库存金额
				+ "SUM(ACTUAL) AS ACTUAL,"  //实际分销量(标箱)
				+ "SUM(ACTUAL_MONEY) AS ACTUAL_MONEY "  //实际分销量金额
				+ "FROM \"_SYS_BIC\".\"pg-sd.sd-sap/CAL_ECC_SD_LATEST_ACTUAL_STOCK\" "
				+ "(PLACEHOLDER.\"$$V_STARTDATE$$\" => '"+startDate+"', "
				+ "PLACEHOLDER.\"$$V_ENDDATE$$\" => '"+endDate+"') "
				+ "GROUP BY YEAR,MONTH,ORG_ID,KUNNR,SKU_ID ) "
				+ "where kunnr = '"+kunnr+"' ";
		
		Class.forName(DRIVER);
		con = DriverManager.getConnection(URL, "system", "Sap123456");
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			map.put(rs.getLong(5)+","+rs.getLong(2), rs.getDouble(8));
			System.out.println(rs.getString(1)+","+rs.getString(2)+","+
					rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+
				    rs.getString(8));
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
				if(rs!=null){rs.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	public Map<Long,KunnrManager> getJXSGLPTRFC(String kunnr,String date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<Long,KunnrManager> map = new HashMap<Long,KunnrManager>();
		
		final String DRIVER = "com.sap.db.jdbc.Driver";  //jdbc 4.0
		final String URL = "jdbc:sap://10.0.3.20:30015?Catalog=_SYS_BIC&reconnect=true";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			Date endDate=sdf.parse(date);
			Calendar c1= Calendar.getInstance();
			c1.setTime(endDate);
			c1.set(Calendar.DAY_OF_MONTH, c1.getActualMaximum(Calendar.DAY_OF_MONTH));
			endDate=c1.getTime();
			c1.set(Calendar.DAY_OF_MONTH, c1.getActualMinimum(Calendar.DAY_OF_MONTH));
			Date startDate=c1.getTime();
			String start=sdf1.format(startDate);
			String end=sdf1.format(endDate);
			
			while(kunnr.length()<10){
				kunnr="0"+kunnr;
			}
			
			String sql= "SELECT * FROM ( "
					   +"SELECT DATUM," //日期
					   +"KUNNR," //经销商
					   +"SKU_ID,"  //skuid
					   +"MVGR1," //物料组1
					   +"ORG_ID," //城市ID
					   +"CUP_TYPE," //杯型--1-普通杯，2-高级杯
					   +"ORDERED_UNSCHEDULED," //已下单未排产数量
					   +"SCHEDULED_UNDELIVERED," //截止今日已排未出库订单(已排产未发货)
					   +"SCHEDULED_UNDELIVERED_RT," //全部已排为出库订单(实时已排产未发货)
					   +"DELIVERED_UNPOD," //已发货未POD（在途）数量
					   +"POD_UNBILLED," //已发货未开票数量
					   +"SCHEDULED_UNDELIVERED_LM," //上月遗留已排未出库订单数量
					   +"FIRM_ORDER," //今日有效订单数量
					   +"ORDERED_UNSCHEDULED_MONEY," // 对应字段金额
					   +"SCHEDULED_UNDELIVERED_MONEY," //对应字段金额
					   +"SCHEDULED_UNDELIVERED_RT_MONEY," //对应字段金额
					   +"DELIVERED_UNPOD_MONEY," //对应字段金额
					   +"POD_UNBILLED_MONEY," //对应字段金额
					   +"SCHEDULED_UNDELIVERED_LM_MONEY," //对应字段金额
					   +"FIRM_ORDER_MONEY " //对应字段金额
					   +"FROM \"_SYS_BIC\".\"pg-sd.sd-sap/CAL_ECC_SD_ORDER_STATUS\" "
					   +"(PLACEHOLDER.\"$$V_DATE$$\" => '"+start+"') "
					   +" ) WHERE KUNNR = '"+kunnr+"'";
     
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, "system", "Sap123456");
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
//				ResultSetMetaData rsmd = rs.getMetaData();
//				int colNum = rsmd.getColumnCount();
//				for (int i = 1; i <= colNum; i++) {
					KunnrManager kunnrManager=new KunnrManager();
					kunnrManager.setKunnr(kunnr);
					kunnrManager.setSkuId(rs.getLong(3));
					kunnrManager.setOrderOnWay(rs.getDouble(10));
					kunnrManager.setPutNotTakeOrder(rs.getDouble(8));
					kunnrManager.setNotPutOrder(rs.getDouble(7));
					map.put(rs.getLong(3), kunnrManager);
					System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+
					rs.getString(7)+","+rs.getString(8)+","+rs.getString(10));
//				}
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
				if(rs!=null){rs.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
			
//			JCO.Client client = null;
//			client = sapConnection.getSAPClientFromPool();
//			sapConnection.setFuncName("ZRFC_EXP_JXSGLPT1");
//			JCO.Function func = sapConnection.getFunction(client);
//			JCO.Table kunnr_table = func.getTableParameterList().getTable("T_KUNNR_IN");
//			kunnr_table.appendRow();
//			kunnr_table.getField("KUNNR").setValue("");
//			
//			JCO.Table date_table = func.getTableParameterList().getTable("T_WADAT_IN");
//			date_table.appendRow();
//			date_table.getField("WADAT_IST_L").setValue(start);
//			date_table.getField("WADAT_IST_H").setValue(end);
//			
//			client.execute(func);
//			JCO.Table exportTable = func.getTableParameterList().getTable(
//					"TABLE");
//			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
//					.nextRow()) {
//				String kunnr_out = exportTable.getValue("KUNNR").toString();
//				String matnr = exportTable.getValue("MATNR").toString();
//				String notPutOrder = exportTable.getValue("WPC").toString();
//				String putNotTakeOrder = exportTable.getValue("WFH").toString();
//				String orderOnWay = exportTable.getValue("WDH").toString();
//				
//				KunnrManager kunnrManager=new KunnrManager();
//				kunnrManager.setKunnr(kunnr);
//				kunnrManager.setMatter(matnr);
//				kunnrManager.setNotPutOrder(Double.valueOf(notPutOrder));
//				kunnrManager.setPutNotTakeOrder(Double.valueOf(putNotTakeOrder));
//				kunnrManager.setOrderOnWay(Double.valueOf(orderOnWay));
//				map.put(matnr, kunnrManager);
//			}
		
	}
	
	public Map<String,Double> getSJCKRFC(String kunnr,String startDate,String endDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,Double> map = new HashMap<String,Double>();
		
		final String DRIVER = "com.sap.db.jdbc.Driver";  //jdbc 4.0
		final String URL = "jdbc:sap://10.0.3.20:30015?Catalog=_SYS_BIC&reconnect=true";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
			Date eDate=sdf.parse(endDate);
			Calendar c1= Calendar.getInstance();
			c1.setTime(eDate);
			c1.set(Calendar.DAY_OF_MONTH, c1.getActualMaximum(Calendar.DAY_OF_MONTH));
			eDate=c1.getTime();
			
			String start=sdf1.format(sdf.parse(startDate));
			String end=sdf1.format(eDate);
			
			while(kunnr.length()<10){
				kunnr="0"+kunnr;
			}
			
			String sql= "SELECT * FROM ( "
					   +"SELECT YEAR," //年
					   +"MONTH," //月
					   +"ORG_ID,"  //组织ID
					   +"KUNNR," //经销商
					   +"SKU_ID," //SKUID
					   +"MVGR1," //物料组1
					   +"CUP_TYPE," //杯型
					   +"SUM(ACTUAL) AS ACTUAL," //实际出库量
					   +"SUM(ACTUAL_MONEY) AS ACTUAL_MONEY " //实际出库金额
					   +"FROM \"_SYS_BIC\".\"pg-sd.sd-sap/CAL_ECC_SD_ACTUAL_OUTBOUND\" "
					   +"(PLACEHOLDER.\"$$V_STARTDATE$$\" => '"+start+"', "
					   +"PLACEHOLDER.\"$$V_ENDDATE$$\" => '"+end+"') "
					   +"GROUP BY YEAR,"
					   +"MONTH,"
                       +"ORG_ID,"
                       +"KUNNR,"
                       +"SKU_ID,"
                       +"MVGR1,"
                       +"CUP_TYPE "
					   +" ) WHERE KUNNR = '"+kunnr+"'";
     
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, "system", "Sap123456");
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getLong(5)+","+rs.getInt(1)+","+rs.getInt(2), rs.getDouble(8));
				System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+
						rs.getString(4)+","+rs.getString(5)+","+rs.getString(8));
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
				if(rs!=null){rs.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public int searchProductionNeedListCount(KunnrManager kunnrManager){
		try {
			return kunnrManagerDao.searchProductionNeedListCount(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<KunnrManager> searchProductionNeedList(KunnrManager kunnrManager){
		try {
			Map<String,Map<Long,KunnrManager>> orderMap=new HashMap<String,Map<Long,KunnrManager>>();
			List<KunnrManager> list=kunnrManagerDao.searchProductionNeedList(kunnrManager);
			for(KunnrManager km:list){
				if(km.getStockWarning()!=null && km.getSalesPlanNextWeek()!=null){
					BigDecimal b1 = new BigDecimal(handleNull(km.getStockWarning()));
					BigDecimal b2 = new BigDecimal(handleNull(km.getSalesPlanNextWeek()));
					km.setStockStandard(b1.add(b2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
					km.setStockStandardNextWeek(b1.add(b2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
				}
				
				if(km.getNotPutOrder()==null){
					km.setNotPutOrder(0.0);
					km.setPutNotTakeOrder(0.0);
					km.setOrderOnWay(0.0);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
					String date=sdf.format(new Date());
					String kunnr = km.getKunnr();
					
					while(kunnr.length()<10){
						kunnr="0"+kunnr;
					}
					Map<Long,KunnrManager> map=orderMap.get(kunnr);
					if(map!=null){
						KunnrManager tmp=map.get(km.getSkuId());
						if(tmp!=null){
							km.setNotPutOrder(handleNull(tmp.getNotPutOrder()));
							km.setPutNotTakeOrder(handleNull(tmp.getPutNotTakeOrder()));
							km.setOrderOnWay(handleNull(tmp.getOrderOnWay()));
						}
					}else{
						map = getJXSGLPTRFC(kunnr,date);
						KunnrManager tmp=map.get(km.getSkuId());
						if(tmp!=null){
							km.setNotPutOrder(handleNull(tmp.getNotPutOrder()));
							km.setPutNotTakeOrder(handleNull(tmp.getPutNotTakeOrder()));
							km.setOrderOnWay(handleNull(tmp.getOrderOnWay()));
						}
						orderMap.put(kunnr, map);
					}
					
					BigDecimal b1 = new BigDecimal(handleNull(km.getEstimateStock()));
					BigDecimal b2 = new BigDecimal(handleNull(km.getPutNotTakeOrder()));
					BigDecimal b3 = new BigDecimal(handleNull(km.getOrderOnWay()));
					km.setStockTotal(b1.add(b2).add(b3).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
				}
				
				if(km.getProductNeed()==null){
					BigDecimal b1 = new BigDecimal(handleNull(km.getStockWarning()));
					BigDecimal b2 = new BigDecimal(handleNull(km.getStockTotal()));
					if(b1.subtract(b2).doubleValue()<0){
						km.setProductNeed(0.0);
					}else{
						km.setProductNeed(b1.subtract(b2).doubleValue());
					}
				}
				
				if(km.getTakeOrder()==null){
					BigDecimal b1 = new BigDecimal(handleNull(km.getProductNeed()));
					BigDecimal b2 = new BigDecimal(handleNull(km.getNotPutOrder()));
					if(b1.subtract(b2).doubleValue()<0){
						km.setTakeOrder(0.0);
					}else{
						km.setTakeOrder(b1.subtract(b2).doubleValue());
					}
					
					BigDecimal b3 = new BigDecimal(handleNull(km.getTakeOrder()));
					BigDecimal b4 = new BigDecimal(handleNull(km.getSkuPrice()));
					km.setTakeOrderPrice(b3.multiply(b4).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
				}
				
				if(km.getStockWarningTwoWeek()==null){
					km.setStockWarningTwoWeek(km.getStockWarning());
				}
				
				if(km.getTakeOrderFirst()==null){
					BigDecimal b1 = new BigDecimal(handleNull(km.getStockWarningTwoWeek()));
					BigDecimal b2 = new BigDecimal(handleNull(km.getStockTotal()));
					if(b1.subtract(b2).doubleValue()<0){
						km.setTakeOrderFirst(0.0);
					}else{
						km.setTakeOrderFirst(b1.subtract(b2).doubleValue());
					}
				}
				
				if(km.getProductNeedVolume()==null){
					BigDecimal b1 = new BigDecimal(handleNull(km.getSkuVolume()));
					BigDecimal b2 = new BigDecimal(handleNull(km.getProductNeed()));
					km.setProductNeedVolume(b1.multiply(b2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
				}
				
				if(km.getTakeOrderPriceNextWeek()==null){
					BigDecimal b1 = new BigDecimal(handleNull(km.getSkuPrice()));
					BigDecimal b2 = new BigDecimal(handleNull(km.getProductNeedNextWeek()));
					km.setTakeOrderPriceNextWeek(b1.multiply(b2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
				}
				
				if(km.getProductNeedNextWeek()==null){
					BigDecimal b1 = new BigDecimal(handleNull(km.getProductSum()));
					BigDecimal b2 = new BigDecimal(handleNull(km.getStockTotal()));
					BigDecimal b3 = new BigDecimal(handleNull(km.getStockStandard()));
					BigDecimal b4 = new BigDecimal(handleNull(km.getStockStandardNextWeek()));
					BigDecimal b5 = new BigDecimal(handleNull(km.getSalesPlanNextWeek()));
					
					double num=b1.add(b2).subtract(b3).add(b4).subtract(b3).add(b5).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					if(num<0){
						km.setProductNeedNextWeek(0.0);
					}else{
						km.setProductNeedNextWeek(num);
					}
				}
			}
			return list;
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}

	public Double getKunnrManagerYear(KunnrManager kunnrManager){
		try {
			return kunnrManagerDao.getKunnrManagerYear(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public Double getSalesYear(KunnrManager kunnrManager){
		try {
			return kunnrManagerDao.getSalesYear(kunnrManager);
		} catch (Exception e) {
			logger.error(kunnrManager, e);
			e.printStackTrace();
			return null;
		}
	}
	
	public Double handleNull(Double num){
		if(num==null){
			return 0.0;
		}else{
			return num;
		}
	}

}
