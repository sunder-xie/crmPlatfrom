package com.kintiger.platform.cuanhuoQuery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.cuanhuoQuery.dao.ICuanhuoQueryDao;
import com.kintiger.platform.cuanhuoQuery.pojo.CuanhuoQuery;
import com.kintiger.platform.cuanhuoQuery.pojo.CuanhuoSKU;
import com.kintiger.platform.cuanhuoQuery.service.ICuanhuoQueryService;
import com.kintiger.platform.framework.sap.SAPConnectionBean;
import com.sap.mw.jco.JCO;

public class CuanhuoQueryServiceImpl implements ICuanhuoQueryService{

	private static final Logger logger = Logger.getLogger(CuanhuoQueryServiceImpl.class);
	private ICuanhuoQueryDao cuanhuoQueryDao;
	private SAPConnectionBean sapConnection;
	
	
	public List<CuanhuoQuery> serchCuanhuoQueryList(CuanhuoQuery cuanhuoQuery) {
			StringResult result = new StringResult();
			List<CuanhuoQuery> cuanhuoQuerys = new ArrayList<CuanhuoQuery>();
			JCO.Client client = null;
			try {
				client = sapConnection.getSAPClientFromPool();
				sapConnection.setFuncName("ZGET_INFO_CUANHUO");
				JCO.Function func = sapConnection.getFunction(client);
				JCO.ParameterList input = func.getImportParameterList();
					input.getField("IV_WERKS").setValue(cuanhuoQuery.getIV_WERKS().toString());
					input.getField("IV_MATNR").setValue(cuanhuoQuery.getIV_MATNR().toString());
					input.getField("IV_LOCCO").setValue(cuanhuoQuery.getIV_LOCCO().toString());
					input.getField("IV_DATUM").setValue(cuanhuoQuery.getIV_DATUM().toString()); 
				client.execute(func);
				JCO.Table output = func.getTableParameterList().getTable(
						"ET_DATA");
				for (int i=0;i<output.getNumRows();i++,output.nextRow()) {
					CuanhuoQuery cq = new CuanhuoQuery();
					cq.setWERKS(output.getField("WERKS").getValue()
							.toString());
					cq.setWERKS_NAME(output.getField("WERKS_NAME").getValue()
							.toString());
					cq.setLOCCO(output.getField("LOCCO").getValue()
							.toString());
					cq.setDATUM(output.getField("DATUM").getValue()
							.toString());
					cq.setMATNR(output.getField("MATNR").getValue()
							.toString());
					cq.setMAKTX(output.getField("MAKTX").getValue()
							.toString());
					cq.setCHARG(output.getField("CHARG").getValue()
							.toString());
					cq.setVBELN_VL(output.getField("VBELN_VL").getValue()
							.toString());
					cq.setVBELN_VA(output.getField("VBELN_VA").getValue()
							.toString());
					cq.setKUNAG(output.getField("KUNAG").getValue()
							.toString());
					cq.setKUNAG_NAME(output.getField("KUNAG_NAME").getValue()
							.toString());
					cq.setKUNWE(output.getField("KUNWE").getValue()
							.toString());
					cq.setKUNWE_NAME(output.getField("KUNWE_NAME").getValue()
							.toString());
					if(output.getField("PODAT").getValue() !=null){
					    cq.setPODAT(output.getField("PODAT").getValue().toString());
					}else{
						cq.setPODAT("");
					}
					cuanhuoQuerys.add(cq);
				}
				return cuanhuoQuerys;
			} catch (Exception e) {
				e.printStackTrace();
				result.setCode(ICuanhuoQueryService.ERROR);
				return null;
			}
	}

	public int getCuanhuoSKUCount(CuanhuoQuery cuanhuoQuery) {
		try {
			return cuanhuoQueryDao.getCuanhuoSKUCount(cuanhuoQuery);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;
	}
	
	
	public List<CuanhuoQuery> getCuanhuoSKUs(CuanhuoQuery cuanhuoQuery) {
		List<CuanhuoQuery> cuanhuoQuerys = null;
		try {
			cuanhuoQuerys = cuanhuoQueryDao.getCuanhuoSKUs(cuanhuoQuery);
		} catch (Exception e) {
			logger.error(e);
		}
		return cuanhuoQuerys;
	}
	
	
	
	
	public ICuanhuoQueryDao getCuanhuoQueryDao() {
		return cuanhuoQueryDao;
	}


	public void setCuanhuoQueryDao(ICuanhuoQueryDao cuanhuoQueryDao) {
		this.cuanhuoQueryDao = cuanhuoQueryDao;
	}


	public SAPConnectionBean getSapConnection() {
		return sapConnection;
	}


	public void setSapConnection(SAPConnectionBean sapConnection) {
		this.sapConnection = sapConnection;
	}


	
	
	
}
