package com.kintiger.platform.complaintReport.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.complaintReport.dao.IComplaintReportDao;
import com.kintiger.platform.complaintReport.pojo.ComplaintGoal;
import com.kintiger.platform.complaintReport.pojo.ComplaintReport;
import com.kintiger.platform.complaintReport.pojo.ProductOutput;
import com.kintiger.platform.complaintReport.service.IComplaintReportService;
import com.kintiger.platform.framework.util.LogUtil;
import com.kintiger.platform.question.service.impl.QuestionServiceImpl;
import com.sap.mw.jco.JCO;
import com.kintiger.platform.framework.sap.SAPConnectionBean;

public class ComplaintReportServiceImpl implements IComplaintReportService {
	
	private static final Log logger = LogFactory
			.getLog(QuestionServiceImpl.class);

	private IComplaintReportDao complaintReportDao;
	private SAPConnectionBean sapConnection;
	@Override
	public List<ComplaintReport> getComplaintReport(ComplaintReport com) {
	  try{
		return complaintReportDao.getComplaintReport(com);
	  } catch (Exception e) {
		logger.error(LogUtil.parserBean(com), e);
	  }
	return null;
	}
	
	public List<ProductOutput> getOutput(ProductOutput output) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String start_sap_date=sdf.format(output.getStart_sap_date());
		String end_sap_date=sdf.format(output.getEnd_sap_date());
		System.out.println("sap,"+start_sap_date+","+end_sap_date);
		List<ProductOutput> list=new ArrayList<ProductOutput>();
		JCO.Client client = null;
			client = sapConnection.getSAPClientFromPool();
			sapConnection.setFuncName("ZRFC_CLXX");		
			JCO.Function func = sapConnection.getFunction(client);
			JCO.ParameterList input = func.getImportParameterList();		
			input.getField("DATE_B").setValue(start_sap_date);//格式转换成YYYYMMDD格式后 的开始时间参数传入
			input.getField("DATE_E").setValue(end_sap_date);//格式转换成YYYYMMDD格式后 的结束时间参数传入
			client.execute(func);
			JCO.Table exportTable = func.getTableParameterList().getTable(
					"LT_PBCLXX");
			for (int i = 0; i < exportTable.getNumRows(); i++, exportTable
					.nextRow()) {
				ProductOutput out=new ProductOutput();
				String cpxl=exportTable.getValue("CPXL").toString();
				String werks=exportTable.getValue("WERKS").toString();
				double menge=Double.parseDouble(exportTable.getValue("MENGE").toString());
				String meins = exportTable.getValue("MEINS").toString();
				System.out.println(start_sap_date+","+end_sap_date);
				System.out.println(cpxl+","+werks+","+menge+","+meins);
				out.setSeries(cpxl);
				out.setFactory(werks);
				out.setOutput(menge);
				out.setUnit(meins);
				list.add(out);
					}
			return list;
    }
	
	@Override
	public int getComplaintGoalCount(ComplaintGoal com) {
		try{
			return complaintReportDao.getComplaintGoalCount(com);
		  } catch (Exception e) {
			logger.error(LogUtil.parserBean(com), e);
		  }
		return 0;
	}
	
	@Override
	public List<ComplaintGoal> getComplaintGoal(ComplaintGoal com) {
		try{
			return complaintReportDao.getComplaintGoal(com);
		  } catch (Exception e) {
			logger.error(LogUtil.parserBean(com), e);
		  }
		return null;
	}
	
	@Override
	public int updateComplaintGoal(ComplaintGoal com) {
		try{
			return complaintReportDao.updateComplaintGoal(com);
		  } catch (Exception e) {
			logger.error(LogUtil.parserBean(com), e);
		  }
		return 0;
	}
	
	@Override
	public int createGoal(ComplaintGoal com) {
		try{
			return complaintReportDao.createGoal(com);
		  } catch (Exception e) {
			logger.error(LogUtil.parserBean(com), e);
		  }
		return 0;
	}
	
	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}
	
	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}
	
	public IComplaintReportDao getComplaintReportDao() {
		return complaintReportDao;
	}
	
	public void setComplaintReportDao(IComplaintReportDao complaintReportDao) {
		this.complaintReportDao = complaintReportDao;
	}
	

}
