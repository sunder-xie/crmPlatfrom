package com.kintiger.platform.cuanhuoQuery.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.cuanhuoQuery.pojo.CuanhuoQuery;
import com.kintiger.platform.cuanhuoQuery.service.ICuanhuoQueryService;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;

public class CuanhuoQueryAction extends BaseAction {
	private static final long serialVersionUID = 5721234244589561L;
	private static final Logger logger = Logger.getLogger(CuanhuoQueryAction.class);
	private ICuanhuoQueryService cuanhuoQueryService;
	private CuanhuoQuery cuanhuoQuery;
	private List<CuanhuoQuery> cuanhuoQueryList;
	private int total;
	private String IV_WERKS;//工厂
	private String IV_MATNR;//物料编码
	private String IV_LOCCO;//打码号
	private String IV_DATUM;//生产日期
	private String IV_MATNR_NAME;//物料名称
	/**
	 * 跳转到查询页面
	 * 
	 * @return
	 */
	@PermissionSearch
	public String toSearchCuanhuoQuery() {
		return "toSearchCuanhuoQuery";
	}
	
	/**
	 * 获取辅料库存的数据 byws
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "cuanhuoQueryList", include = { "WERKS", "LOCCO",
			"WERKS_NAME","DATUM", "MATNR", "MAKTX","CHARG", "VBELN_VL",
			"VBELN_VA", "KUNAG","KUNAG_NAME", "KUNWE","KUNWE_NAME", 
			"PODAT"}, total = "total")
	public String searchCuanhuoQueryList() {
		cuanhuoQuery = new CuanhuoQuery();
		if (StringUtils.isNotEmpty(IV_WERKS)&&StringUtils.isNotEmpty(IV_MATNR)
			&&StringUtils.isNotEmpty(IV_LOCCO)&&StringUtils.isNotEmpty(IV_DATUM)) {
			IV_DATUM=IV_DATUM.replace("-", "");
			cuanhuoQuery.setIV_WERKS(IV_WERKS);
			cuanhuoQuery.setIV_MATNR(IV_MATNR);
			cuanhuoQuery.setIV_LOCCO(IV_LOCCO);
			cuanhuoQuery.setIV_DATUM(IV_DATUM);
			cuanhuoQueryList= cuanhuoQueryService.serchCuanhuoQueryList(cuanhuoQuery);
			total = cuanhuoQueryList.size();
		}else{
			total=0;
		}
		/*if (StringUtils.isNotEmpty(IV_MATNR)) {
			cuanhuoQuery.setIV_MATNR(IV_MATNR);
		}
		if (StringUtils.isNotEmpty(IV_LOCCO)) {
			cuanhuoQuery.setIV_LOCCO(IV_LOCCO);
		}
		if (StringUtils.isNotEmpty(IV_DATUM)) {
			cuanhuoQuery.setIV_DATUM(IV_DATUM);
		}
		if(StringUtils.isEmpty(IV_WERKS)||StringUtils.isEmpty(IV_MATNR)
		   ||StringUtils.isEmpty(IV_LOCCO)||StringUtils.isEmpty(IV_DATUM)){
			total=0;
		}else{
		    cuanhuoQueryList= cuanhuoQueryService.serchCuanhuoQueryList(cuanhuoQuery);
		    total = cuanhuoQueryList.size();
		}*/
		return JSON;
	}

	/**
	 * SKU品项查询
	 */

	@JsonResult(field = "cuanhuoQueryList", include = { "SKU_ID",
			"IV_MATNR","IV_MATNR_NAME" }, total = "total")
	public String getCuanhuoSKUs() {
		 cuanhuoQuery = new CuanhuoQuery();
		if (StringUtils.isNotEmpty(IV_MATNR_NAME)) {
			try {
				IV_MATNR_NAME = new String(getServletRequest().getParameter(
						"IV_MATNR_NAME").getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e);
			}
			cuanhuoQuery.setIV_MATNR_NAME(IV_MATNR_NAME);
		}
		cuanhuoQuery.setStart(getStart());
		cuanhuoQuery.setEnd(getEnd());
		total = cuanhuoQueryService.getCuanhuoSKUCount(cuanhuoQuery);
		if(total !=0){
			cuanhuoQueryList = cuanhuoQueryService.getCuanhuoSKUs(cuanhuoQuery);
		} 
		return JSON;
	}
	

	public ICuanhuoQueryService getCuanhuoQueryService() {
		return cuanhuoQueryService;
	}

	public void setCuanhuoQueryService(ICuanhuoQueryService cuanhuoQueryService) {
		this.cuanhuoQueryService = cuanhuoQueryService;
	}

	public CuanhuoQuery getCuanhuoQuery() {
		return cuanhuoQuery;
	}

	public void setCuanhuoQuery(CuanhuoQuery cuanhuoQuery) {
		this.cuanhuoQuery = cuanhuoQuery;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<CuanhuoQuery> getCuanhuoQueryList(){
		return cuanhuoQueryList;
	}
	
	public void setCuanhuoQueryList(List<CuanhuoQuery> cuanhuoQueryList) {
		this.cuanhuoQueryList = cuanhuoQueryList;
	}

	public String getIV_WERKS() {
		return IV_WERKS;
	}

	public void setIV_WERKS(String iV_WERKS) {
		IV_WERKS = iV_WERKS;
	}

	public String getIV_MATNR() {
		return IV_MATNR;
	}

	public void setIV_MATNR(String iV_MATNR) {
		IV_MATNR = iV_MATNR;
	}

	public String getIV_LOCCO() {
		return IV_LOCCO;
	}

	public void setIV_LOCCO(String iV_LOCCO) {
		IV_LOCCO = iV_LOCCO;
	}

	public String getIV_DATUM() {
		return IV_DATUM;
	}

	public void setIV_DATUM(String iV_DATUM) {
		IV_DATUM = iV_DATUM;
	}

	public static Logger getLogger() {
		return logger;
	}

	public String getIV_MATNR_NAME() {
		return IV_MATNR_NAME;
	}

	public void setIV_MATNR_NAME(String iV_MATNR_NAME) {
		IV_MATNR_NAME = iV_MATNR_NAME;
	}
	
	
}
