package com.kintiger.platform.stockReport.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.pojo.Tree4Ajax;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.stockReport.pojo.AllUsers;
import com.kintiger.platform.stockReport.pojo.Station;
import com.kintiger.platform.stockReport.service.IStockReportService;

public class TreeAction extends BaseAction{
	private List<Tree4Ajax> treeList;
	private IOrgService orgServiceHessian;	
	private String node;
	private String orgId;
	private List<AllUsers> empList;
	private IStockReportService stockReportService;
    private	String orgNames;
    private List<Station> stationList;
    private @Decode String userName1;
    private int total;
	private static final Logger logger = Logger.getLogger(TreeAction.class);
	
	
//	public String toAuthorizeAddMain(){
//		return "toAuthorizeAddMain";
//	}
//
//	public String orgTreePage() {
//		return "orgtree";
//	}
	/*异步加载组织树   add by allen*/
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getOrgTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		List<Borg> orgTreeList = null;
		try {
			orgTreeList = orgServiceHessian.getOrgListByOrgParentId(node);
		} catch (Exception e) {
			logger.error(e);
		}
		if (orgTreeList == null || orgTreeList.size() == 0) {
			return JSON;
		}
		for (Borg borg : orgTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(borg.getOrgId()));
			tree.setText(borg.getOrgName());
			tree.setState("closed");
			treeList.add(tree);
		}
		return JSON;
	}
	/**
	 * 根据组织ID查找人员信息   add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "empList", include = { "userId", "posName", 
	"userName" })
	public String getEmpListByOrgId(){
		//System.out.println("orgId:" + orgId);
		if(orgId!=null && !"".equals(orgId)){
			empList=stockReportService.getEmpListByOrgId(orgId);
		}	
		return JSON;		
	}
	
	@JsonResult(field = "stationList", include = { "id","userId","stationId", "posName", "orgName", "userName" }, total = "total")
	public String getEmpListByUserName(){
		Station station=new Station();
		station.setUserName(userName1);
		stationList=stockReportService.getEmpListByUserName(station);
		return JSON;
	}
	
	 //查询组织串名字
	@JsonResult(field = "orgNames" )
    public String selectedNames(){
    	Borg org=orgServiceHessian.getParentOrgByOrgId(orgId);
    	if(org==null){
    	      return JSON;
    	}
    	orgNames=org.getOrgName();
    	while(true){
             if(org.getOrgCity().equals("C")||org.getOrgCity().equals("A")){
            	 break;
             }
    		org=orgServiceHessian.getParentOrgByOrgId(org.getOrgId()+"");
        	orgNames=org.getOrgName()+"/"+orgNames;
    	}	
      return JSON;
    }

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<AllUsers> getEmpList() {
		return empList;
	}

	public void setEmpList(List<AllUsers> empList) {
		this.empList = empList;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public IStockReportService getStockReportService() {
		return stockReportService;
	}

	public void setStockReportService(IStockReportService stockReportService) {
		this.stockReportService = stockReportService;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public String getUserName1() {
		return userName1;
	}

	public void setUserName1(String userName1) {
		this.userName1 = userName1;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void addActionError(String anErrorMessage){
		 String s=anErrorMessage;
		 System.out.println(s);
		 }
		 public void addActionMessage(String aMessage){
		 String s=aMessage;
		 System.out.println(s);
		
		 }
		 public void addFieldError(String fieldName, String errorMessage){
		 String s=errorMessage;
		 String f=fieldName;
		 System.out.println(s);
		 System.out.println(f);
		
		 }
	
}
