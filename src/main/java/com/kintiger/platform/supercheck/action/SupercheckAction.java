package com.kintiger.platform.supercheck.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.customer.service.ICustomerService;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.master.pojo.SupervisorCheckItem;
import com.kintiger.platform.order.pojo.OrderInfo;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.supercheck.pojo.AllitemConf;
import com.kintiger.platform.supercheck.pojo.WeightAndPrice;
import com.kintiger.platform.supercheck.service.SupercheckService;

public class SupercheckAction extends BaseAction {

    private SupercheckService supercheckService;

    private List<Bchannel> channelList;
    private List<Materiel> materielList;
    private int total;
//    private IOrgService orgServiceHessian;
//    private String customer_state;
//    private ICustomerService customerService;
//    private List<CmsTbDict> cmsTbDictCustStateList;
    
    @Decode
    private String  allitemName;      
    
    

    private List<WeightAndPrice> weightAndPriceList;

    private List<AllitemConf> allItemList;

    private static final long serialVersionUID = 1L;

    
//    public String toSupervisorSearchCols() {
//	AllUsers user = this.getUser();
//	String userId = user.getUserId();
//	Borg org = orgServiceHessian.getOrgByUserId(userId);
//	String orgId = org.getOrgId().toString();
//	String orgName = org.getOrgName();
//	CmsTbDict cmsTbDict = new CmsTbDict();
//	cmsTbDict.setDictTypeId(Long.valueOf(customer_state));
//	cmsTbDictCustStateList = customerService.getCmsTbDictList(cmsTbDict);
//	return "toSupervisorSearchCols";
//	
//    }
    
    
    public String toWeightAndPriceConf() {
	return "toWeightAndPriceConf";
    }

    public String toAllItemConf() {
	return "toAllItemConf";
    }

    @PermissionSearch
    @JsonResult(field = "channelList", include = { "checkChannelName" }, total = "total")
    public String getCheckChannelNames() {
	channelList = new ArrayList<Bchannel>();
	channelList = supercheckService.getCheckChannelNames();
	if (channelList != null && channelList.size() > 0) {
	    total = channelList.size();
	}
	return JSON;
    }

    @PermissionSearch
    @JsonResult(field = "materielList", include = { "matg", "bezei40" }, total = "total")
    public String getMateriels() {
	materielList = new ArrayList<Materiel>();
	materielList = supercheckService.getMateriels();
	if (materielList != null && materielList.size() > 0) {
	    total = materielList.size();
	}
	return JSON;
    }



    @PermissionSearch
    @JsonResult(field = "weightAndPriceList", include = { "checkId", "checkChannelName", "phWeight", "clWeight", "price", "matg", "bezei40", "matgs","prices" }, total = "total")
    public String getWeightAndPrices() {
	weightAndPriceList = new ArrayList<WeightAndPrice>();
	List<WeightAndPrice> list = new ArrayList<WeightAndPrice>();
//	list = supercheckService.getWeightAndPrices(null);

	channelList = new ArrayList<Bchannel>();
	channelList = supercheckService.getCheckChannelNames();

	
	
	total = channelList.size();
	for (Bchannel channel : channelList) {
	    WeightAndPrice wp = new WeightAndPrice();
	    wp.setEnd(100);
	    wp.setCheckChannelName(channel.getCheckChannelName());
	    list = supercheckService.getWeightAndPrices(wp);
	    for (WeightAndPrice weightAndPrice : list) {
//		if (null!=weightAndPrice.getPrice() && !"".equals(weightAndPrice)){
//		wp.getMatgs().add(weightAndPrice.getMatg());
//		wp.getPrices().add(weightAndPrice.getPrice());
//	        wp.setPhWeight(weightAndPrice.getPhWeight());
//	        wp.setClWeight(weightAndPrice.getClWeight());
//	        
//		}else continue;
		
		wp.getMatgs().add(weightAndPrice.getMatg());
		wp.getPrices().add(weightAndPrice.getPrice()==null?"0":weightAndPrice.getPrice());
	        wp.setPhWeight(weightAndPrice.getPhWeight());
	        wp.setClWeight(weightAndPrice.getClWeight());
	    }
	    
	    weightAndPriceList.add(wp);

	}

	return JSON;
    }
    
    @PermissionSearch
    @JsonResult(field = "allItemList", include = { "allitemName","confId", "phFlag", "clFlag", "hlFlag", "jgFlag", "allitemList", }, total = "total")
    public String getAllitemConfs() {
	allItemList = new ArrayList<AllitemConf>();
	List<AllitemConf> list = new ArrayList<AllitemConf>();
	List<AllitemConf> listByAllitemName = new ArrayList<AllitemConf>();
	
	// get distinct allitemnams
	list = supercheckService.getDistinctAllitemNames();
	for (AllitemConf allitemConf : list) {
	    AllitemConf a = new AllitemConf();
	    a.setAllitemName(allitemConf.getAllitemName());
	    listByAllitemName = supercheckService.getAllitemConfs(a );
	    
	    for (AllitemConf allitemConf2 : listByAllitemName) {
		a.getAllitemList().add(allitemConf2.getAllitems());
		a.setPhFlag(allitemConf2.getPhFlag());
		a.setClFlag(allitemConf2.getClFlag());
		a.setHlFlag(allitemConf2.getHlFlag());
		a.setJgFlag(allitemConf2.getJgFlag());
	    }
	    allItemList.add(a);
	}
	
	
	
//	list = supercheckService.getAllitemConfs(new AllitemConf());
//	String allitemName = "";
//	  AllitemConf allitemConf=null;
//	for(int i =0 ;i<list.size() ;i++){
//	    AllitemConf item = list.get(i);
//	    if (!item.getAllitemName().equals(allitemName)){
//		allitemConf = new AllitemConf();
//		allItemList.add(allitemConf);
//		allitemConf.setAllitemName(list.get(i).getAllitemName());
//		allitemConf.setPhFlag(list.get(i).getPhFlag());
//		allitemConf.setClFlag(list.get(i).getClFlag());
//		allitemConf.setHlFlag(list.get(i).getHlFlag());
//		allitemConf.setJgFlag(list.get(i).getJgFlag());
//		allitemConf.getAllitemList().add(list.get(i).getAllitems());
//		allitemConf.getConfidList().add(String.valueOf(list.get(i).getConfId()));
//	    }else{
//		allitemConf.getAllitemList().add(list.get(i).getAllitems());
//	    }
//	    allitemName = item.getAllitemName();
//	}
	
	total = allItemList.size();
	
	return JSON;
    }
    
    @PermissionSearch
    @JsonResult(field = "allItemList", include = { "allitemName","confId", "phFlag", "clFlag", "hlFlag", "jgFlag", "allitemList", }, total = "total")
    public String changeAllitemConf() {
	
	if (null!=this.allItemList && allItemList.size()>0){
	    this.supercheckService.changeAllitemConf(allItemList);
	}
	total = allItemList.size();
	
	return JSON;
    }
    
    @PermissionSearch
    @JsonResult(field = "allItemList", include = { "allitemName","confId", "phFlag", "clFlag", "hlFlag", "jgFlag", "allitemList", }, total = "total")
    public String delAllitemConf() {
	allItemList = new ArrayList<AllitemConf>();
	if (null!=this.allitemName && allitemName.length()>0){
	    this.supercheckService.delAllitemConf(allitemName);
	}
	total = allItemList.size();
	
	return JSON;
    }
    
    @PermissionSearch
    @JsonResult(field = "allItemList", include = { "confId", "phFlag", "clFlag", "hlFlag", "jgFlag", "allitemList", }, total = "total")
    public String saveAllitemConf() {
	allItemList = new ArrayList<AllitemConf>();
	
	
	if (null!=this.allitemName && allitemName.length()>0){
	    supercheckService.saveAllitemConf(allitemName);
	}
	

	
	total = 0;
	
	return JSON;
    }

 
    @PermissionSearch
    @JsonResult(field = "weightAndPriceList", include = { "checkId", "checkChannelName", "phWeight", "clWeight", "price", "matg", "bezei40", "matgs","prices" }, total = "total")
    public String saveWeightprices() {
	
	if (channelList!=null &&  channelList.size()>0){
	    supercheckService.delWpByChannel(channelList);
	}
	if (weightAndPriceList != null && weightAndPriceList.size()>0) {
	    supercheckService.saveWeightprices(weightAndPriceList);
	}
	total = weightAndPriceList.size();
	
	return JSON;
    }
    
    
	public String excelDownload() {
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		weightAndPriceList = new ArrayList<WeightAndPrice>();
		this.weightAndPriceList = supercheckService.getWeightAndPrices(null);
		
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			
			os = response.getOutputStream();
		
			response.reset();
			
			String fileName = "市场渠道铺货考核权重&价格配置表.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
		
			response.setContentType("application/msexcel");
			
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("page1", 0);
			wsheet.setRowView(0, 300);
			
			wsheet.addCell(new Label(0, 0, "考核渠道"));
			wsheet.addCell(new Label(1, 0, "铺货权重"));
			wsheet.addCell(new Label(2, 0, "陈列权重"));
			
			materielList = supercheckService.getMateriels();
			Map<String, Integer> map = new HashMap<String,Integer>();
			
			for (int  i = 0 ;i< materielList.size() ;i++){
			    wsheet.addCell(new Label(3+i, 0, materielList.get(i).getBezei40()));
			    map.put(materielList.get(i).getMatg(), 3+i);
			}
			
			
			channelList = supercheckService.getCheckChannelNames();
			for (int j = 0; j<channelList.size() ;j++) {
			    weightAndPriceList= new ArrayList<WeightAndPrice>();
			    WeightAndPrice w = new WeightAndPrice();
			    w.setEnd(100);
			    w.setCheckChannelName(channelList.get(j).getCheckChannelName());
			    weightAndPriceList  = supercheckService.getWeightAndPrices(w);
			    
			    for (int jj =0 ;jj< weightAndPriceList.size() ;jj++) {
				WeightAndPrice wp = weightAndPriceList.get(jj);
				Integer index = map.get(wp.getMatg());
				wsheet.addCell(new Label(index, j+1, wp.getPrice()));
				wsheet.addCell(new Label(0, j+1, wp.getCheckChannelName()));
				wsheet.addCell(new Label(1, j+1, wp.getPhWeight()));
				wsheet.addCell(new Label(2, j+1, wp.getClWeight()));
			    }
			  
			    
			}
			
			
			
			wbook.write();
			
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
			return SUCCESS;
			
			
		}catch (Exception e) {
//			logger.error(e);
		} finally {
		    try {
			wbook.close();
			os.close();
			 os = null;
			 wbook=null;
		    } catch (Exception e) {
		    
		    }
		   
		}
		
	    
		return SUCCESS;
		
	}
	public String exportAllitems() {
	    ServletActionContext.getRequest().getSession()
	    .setAttribute("DownLoad", "Ing");
	    this.allItemList = new ArrayList<AllitemConf>();
	    this.allItemList = supercheckService.getAllitemConfs(new AllitemConf());
	    
	    OutputStream os = null;
	    WritableWorkbook wbook = null;
	    try {
		HttpServletResponse response = ServletActionContext.getResponse();
		
		os = response.getOutputStream();
		
		response.reset();
		
		String fileName = "市场渠道部综合品项配置表.xls";
		fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
		response.setHeader("Content-disposition", "attachment; filename="
			+ fileName);
		
		response.setContentType("application/msexcel");
		
		wbook = Workbook.createWorkbook(os);
		WritableSheet wsheet = wbook.createSheet("page1", 0);
		wsheet.setRowView(0, 300);
		
		wsheet.addCell(new Label(0, 0, "综合品项"));
		wsheet.addCell(new Label(1, 0, "铺货考核综合品项"));
		wsheet.addCell(new Label(2, 0, "陈列考核综合品项"));
		wsheet.addCell(new Label(3, 0, "货龄考核综合品项"));
		wsheet.addCell(new Label(4, 0, "价格考核综合品项"));
		
		materielList = supercheckService.getMateriels();
		Map<String, Integer> map = new HashMap<String,Integer>();
		
		for (int  i = 0 ;i< materielList.size() ;i++){
		    wsheet.addCell(new Label(5+i, 0, materielList.get(i).getBezei40()));
		    map.put(materielList.get(i).getMatg(), 5+i);
		}
		
		List<AllitemConf> distinctAllitemNames = new ArrayList<AllitemConf>();
		List<AllitemConf> list =null;
		
		
		distinctAllitemNames = supercheckService.getDistinctAllitemNames();
		if (distinctAllitemNames.size()>0){
		for (int j = 0; j<distinctAllitemNames.size() ;j++) {
		    list= new ArrayList<AllitemConf>();
		    AllitemConf w = new AllitemConf();
		    w.setAllitemName(distinctAllitemNames.get(j).getAllitemName());
		    w.setEnd(100);
		    list  = supercheckService.getAllitemConfs(w);
		    
		    for (int jj =0 ;jj< list.size() ;jj++) {
			AllitemConf a = list.get(jj);
			Integer index = map.get(a.getAllitems());
			
			wsheet.addCell(new Label(index, j+1, "1"));
			wsheet.addCell(new Label(0, j+1, a.getAllitemName()));
			wsheet.addCell(new Label(1, j+1, a.getPhFlag().equals("0")?"":a.getPhFlag()));
			wsheet.addCell(new Label(2, j+1, a.getClFlag().equals("0")?"":a.getClFlag()));
			wsheet.addCell(new Label(3, j+1, a.getHlFlag().equals("0")?"":a.getHlFlag()));
			wsheet.addCell(new Label(4, j+1, a.getJgFlag().equals("0")?"":a.getJgFlag()));
		    }
		    
		    
		}
		}
		
		
		
		wbook.write();
		
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Over");
		return RESULT_MESSAGE;
		
		
	    }catch (Exception e) {
//			logger.error(e);
	    } finally {
		try {
		    wbook.close();
		    os.close();
		    os = null;
		    wbook=null;
		} catch (Exception e) {
		    
		}
		
	    }
	    
	    
	    return RESULT_MESSAGE;
	    
	}
	
   
    

    public SupercheckService getSupercheckService() {
	return supercheckService;
    }

    public void setSupercheckService(SupercheckService supercheckService) {
	this.supercheckService = supercheckService;
    }

    public List<Bchannel> getChannelList() {
	return channelList;
    }

    public void setChannelList(List<Bchannel> channelList) {
	this.channelList = channelList;
    }

    public int getTotal() {
	return total;
    }

    public void setTotal(int total) {
	this.total = total;
    }

    public List<Materiel> getMaterielList() {
	return materielList;
    }

    public void setMaterielList(List<Materiel> materielList) {
	this.materielList = materielList;
    }

    public List<WeightAndPrice> getWeightAndPriceList() {
	return weightAndPriceList;
    }

    public void setWeightAndPriceList(List<WeightAndPrice> weightAndPriceList) {
	this.weightAndPriceList = weightAndPriceList;
    }

    public String getAllitemName() {
        return allitemName;
    }

    public void setAllitemName(String allitemName) {
        this.allitemName = allitemName;
    }

    public List<AllitemConf> getAllItemList() {
        return allItemList;
    }

    public void setAllItemList(List<AllitemConf> allItemList) {
        this.allItemList = allItemList;
    }


//    public IOrgService getOrgServiceHessian() {
//        return orgServiceHessian;
//    }
//
//
//    public void setOrgServiceHessian(IOrgService orgServiceHessian) {
//        this.orgServiceHessian = orgServiceHessian;
//    }
//
//
//    public String getCustomer_state() {
//        return customer_state;
//    }
//
//
//    public void setCustomer_state(String customer_state) {
//        this.customer_state = customer_state;
//    }
//
//
//    public ICustomerService getCustomerService() {
//        return customerService;
//    }
//
//
//    public void setCustomerService(ICustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//
//    public List<CmsTbDict> getCmsTbDictCustStateList() {
//        return cmsTbDictCustStateList;
//    }
//
//
//    public void setCmsTbDictCustStateList(List<CmsTbDict> cmsTbDictCustStateList) {
//        this.cmsTbDictCustStateList = cmsTbDictCustStateList;
//    }

}
