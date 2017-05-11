package com.kintiger.platform.distributionDataRep.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.distributionDataRep.dao.IDistributionDataRepDao;
import com.kintiger.platform.distributionDataRep.pojo.DistributionDataRep;
import com.kintiger.platform.distributionDataRep.service.IDistributionDataRepService;
import com.kintiger.platform.framework.util.LogUtil;
import com.sap.mw.jco.JCO;
import com.kintiger.platform.framework.sap.SAPConnectionBean;




public class DistributionDataRepServiceImpl implements IDistributionDataRepService {
	private IDistributionDataRepDao distributionDataRepDao;
	private SAPConnectionBean sapConnection;
	private static final Logger logger = Logger
			.getLogger(DistributionDataRepServiceImpl.class);

	public int getDistributionDataRepCount(DistributionDataRep dGoal) {
		try {
			return distributionDataRepDao
					.getDistributionDataRepCount(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	public int getDistributionDataRepSize(DistributionDataRep dGoal) {
		try {
			return distributionDataRepDao
					.getDistributionDataRepSize(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	@Override
	public DistributionDataRep getOrgByOrgName(String org_city) {
		try{
			return distributionDataRepDao.getOrgByOrgName(org_city);
		}catch(Exception e){
			logger.error(org_city, e);
		}
		return null;
	}
	
	public List<DistributionDataRep> getDistributionDataRepList(
			DistributionDataRep dGoal) {
		List<DistributionDataRep> list = null;
		try {
			list = distributionDataRepDao
					.getDistributionDataRepList(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return list;

	}

	public BooleanResult insertDistributionDataRep(
			DistributionDataRep dGoal) {
		BooleanResult result = new BooleanResult();

		long distributionDataRepId = distributionDataRepDao
				.insertDistributionDataRep(dGoal);
		if (distributionDataRepId > 0) {
			result.setResult(true);
			result.setCode("数据保存数据库成功");
		} else {
			result.setResult(false);
			result.setCode("数据保存数据库失败.请联系系统管理员");
		}
		return result;
	}

	public StringResult deleteDistributionDataRep(DistributionDataRep dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataRepService.ERROR);
		result.setResult(IDistributionDataRepService.ERROR_MESSAGE);
		try {
			int c = distributionDataRepDao.deleteDistributionDataRep(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionDataRepService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
	public IDistributionDataRepDao getDistributionDataRepDao() {
		return distributionDataRepDao;
	}

	public void setDistributionDataRepDao(IDistributionDataRepDao distributionDataRepDao) {
		this.distributionDataRepDao = distributionDataRepDao;
	}
	public StringResult updateDistributionDataRep(DistributionDataRep dGoal) {
		StringResult result = new StringResult();
		result.setCode(IDistributionDataRepService.ERROR);
		result.setResult(IDistributionDataRepService.ERROR_MESSAGE);
		try {
			int c = distributionDataRepDao.updateDistributionDataRep(dGoal);
			result.setResult(String.valueOf(c));
			result.setCode(IDistributionDataRepService.SUCCESS);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(dGoal), e);
		}
		return result;
	}
	
	public int getDistributionDataRepMatterBoxNum(DistributionDataRep dGoal) {
		try {
			return distributionDataRepDao
					.getDistributionDataRepMatterBoxNum(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return 0;
	}
	
	public DistributionDataRep getDistributionDataRepMaxDateBoxNum(DistributionDataRep dGoal) {
		try {
			return distributionDataRepDao
					.getDistributionDataRepMaxDateBoxNum(dGoal);
		} catch (Exception e) {
			logger.error(dGoal, e);
		}
		return null;
	}

	@Override
	public DistributionDataRep synchBoxNum(String start_sap_date, String end_sap_date,
			DistributionDataRep dDataRep) {
		StringResult result = new StringResult();
		JCO.Client client = null;
		dDataRep.setaOneC(0);
		dDataRep.setaTwoC(0);
		dDataRep.setaThreeC(0);
		dDataRep.setaFourC(0);
		dDataRep.setaFiveC(0);
		dDataRep.setaSixC(0);
		dDataRep.setaSevenC(0);
		dDataRep.setaEightC(0);
		dDataRep.setbOneC(0);
		dDataRep.setbSixC(0);
		dDataRep.setbEightC(0);
		dDataRep.setcOneC(0);
		dDataRep.setcSixC(0);
		dDataRep.setcSevenC(0);
		dDataRep.setcEightC(0);
		dDataRep.setdNineC(0);
		dDataRep.setdTenC(0);
		dDataRep.seteOneC(0);
		dDataRep.sethSevenC(0);
		try {
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("ZRFC_BI_PODAT");		
			JCO.Function func = sapConnection.getFunction(client);
			JCO.ParameterList input = func.getImportParameterList();		

			input.getField("KUNNR").setValue(dDataRep.getKunnrId());//经销商编号传入
			
//			input.getField("KUNNR").setValue(01010015);
//			input.getField("KUNNR").setValue(5250001);

			input.getField("PODAT_B").setValue(start_sap_date);//格式转换成YYYYMMDD格式后 的开始时间参数传入
			input.getField("PODAT_E").setValue(end_sap_date);//格式转换成YYYYMMDD格式后 的结束时间参数传入
//			input.getField("PODAT_B").setValue("20130901");//开始固定时间传入
//			input.getField("PODAT_E").setValue("20140131");//结束固定时间传入		
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"T_PODAT");
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				
				String[] m = { "A1", "A2","A3", "A4", "A5", "A6", "A7", "A8", "B1", "B6","B8",
						"C1", "C6", "C7", "C8", "D9","D10","E1", "H7" };
				
				String mvg = exportTable.getValue("MVGR1").toString();
				

				String lfimgt = exportTable.getValue("LFIMG").toString();
				Integer temp =(int) Math.floor(Double.parseDouble(lfimgt));
				String lfimg = temp.toString();
				int g = 20;
				int j =0;
				for (j= 0; j < m.length;j++)
				{					
					if(m[j].compareTo(mvg)==0)
					{
						g=j;
//						System.out.print("\n");
//						System.out.print(g);
//						System.out.print("\n");
					}								  
				}
//				System.out.print("\n");
//				System.out.print(mvg);
//				System.out.print("\n");
//				System.out.print(lfimg);
//				System.out.print("\n");
				

				switch (g) {
				case 0:
					dDataRep.setaOneC(Integer.parseInt(lfimg));
					continue;
				case 1:
					dDataRep.setaTwoC(Integer.parseInt(lfimg));
					continue;
				case 2:
					dDataRep.setaThreeC(Integer.parseInt(lfimg));
					continue;
				case 3:
					dDataRep.setaFourC(Integer.parseInt(lfimg));
					continue;
				case 4:
					dDataRep.setaFiveC(Integer.parseInt(lfimg));
					continue;
				case 5:
					dDataRep.setaSixC(Integer.parseInt(lfimg));
					continue;
				case 6:
					dDataRep.setaSevenC(Integer.parseInt(lfimg));
					continue;
				case 7:
					dDataRep.setaEightC(Integer.parseInt(lfimg));
					continue;
				case 8:
					dDataRep.setbOneC(Integer.parseInt(lfimg));
					continue;
				case 9:
					dDataRep.setbSixC(Integer.parseInt(lfimg));
					continue;
				case 10:
					dDataRep.setbEightC(Integer.parseInt(lfimg));
					continue;
				case 11:
					dDataRep.setcOneC(Integer.parseInt(lfimg));
					continue;
				case 12:
					dDataRep.setcSixC(Integer.parseInt(lfimg));
					continue;
				case 13:
					dDataRep.setcSevenC(Integer.parseInt(lfimg));
					continue;
				case 14:
					dDataRep.setcEightC(Integer.parseInt(lfimg));
					continue;
				case 15:
					dDataRep.setdNineC(Integer.parseInt(lfimg));
					continue;
				case 16:
					dDataRep.setdTenC(Integer.parseInt(lfimg));
					continue;
				case 17:
					dDataRep.seteOneC(Integer.parseInt(lfimg));
					continue;
				case 18:
					dDataRep.sethSevenC(Integer.parseInt(lfimg));
					continue;
				default:
					continue;
				}
				
				
			}
			result.setCode("success");

		} catch (Exception e) {
			result.setCode("error");
			logger.error(e);
		} finally {
			try{
				if (client != null) 
					JCO.releaseClient(client);
			}catch(Exception e) {
				result.setCode("error");
				logger.error(e);
			} 
		}
		return dDataRep;
	}

	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}

	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}
}
