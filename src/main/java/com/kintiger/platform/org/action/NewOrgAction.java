package com.kintiger.platform.org.action;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.org.pojo.Tree4Ajax;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.INewOrgService;

public class NewOrgAction extends BaseAction{

	private static final Log logger = LogFactory.getLog(NewOrgAction.class);
	private static final long serialVersionUID = 1L;
	private INewOrgService newOrgService;
	private List<Tree4Ajax> treeList;
	private List<Borg> companyList;
	private String flag;
	private String node;
	/**
	 * 新的组织树，支持多选
	 * zhusiliang
	 * @return
	 */
	public String newOrgTree(){
		return "newOrgTree";
	}
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getOrgTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		companyList = new  ArrayList<Borg>();
		try {
		//flag=Y 首次打开的组织，也就是个人能选择到的组织
		if("Y".equals(flag)){
			//检测人员是否拥有查看所有组织的权限  i!=0 表示有权限
			int  i = newOrgService.getPermissionByUserId(this.getUser().getUserId());
			if(i!=0){
				Borg borg = newOrgService.getOrgByOrgId("50919");
				companyList.add(borg);
			}else{
				//获取人员主次岗位的组织
				List<String> orgList = newOrgService.getOrgListByUserId(this.getUser().getUserId());
				for(String orgId:orgList){
					Borg borg = newOrgService.getOrgByOrgId(orgId);
					companyList.add(borg);
				}
			}
		}else{
			    companyList = newOrgService.getOrgTreeListByPorgId(node);
		}
		} catch (Exception e) {
			logger.error(node, e);
		}
		if (companyList == null || companyList.size() == 0){
			return JSON;
		}
		for (Borg borg : companyList) {
			Tree4Ajax tree = new Tree4Ajax();
			tree.setId(String.valueOf(borg.getOrgId()));
			tree.setText(borg.getOrgName());
			tree.setState("closed");
			treeList.add(tree);
		}
		return JSON;
	}


	public INewOrgService getNewOrgService() {
		return newOrgService;
	}
	public void setNewOrgService(INewOrgService newOrgService) {
		this.newOrgService = newOrgService;
	}
	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public List<Borg> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Borg> companyList) {
		this.companyList = companyList;
	}

	public static Log getLogger() {
		return logger;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	
}
