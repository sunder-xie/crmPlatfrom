package com.kintiger.platform.custvist.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringArrayResult;
import com.kintiger.platform.channel.pojo.Tree4Ajax;
import com.kintiger.platform.customer.service.IBatChangeService;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;

/**
 * OrgTreeAjax
 * 
 * @author 
 * 
 */
public class OrgTreeAjaxAction extends BaseAction {
	
	private static final Log logger = LogFactory.getLog(OrgTreeAjaxAction.class);

	private static final long serialVersionUID = 9124918976690173831L;

	private String node;

	private String orgId;

	private String orgName;

	private String sapOrgId;

	private String actionName;

	private List<Tree4Ajax> treeList;

	private IOrgService orgService;

	private StringArrayResult companyInfo = new StringArrayResult();

	private String userId;
	
	private IBatChangeService batChangeService;

	public String getOrgTreeMain() {
		return SUCCESS;
	}

	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getOrgTreeListByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		List<Borg> orgTreeList = null;
		try {
			orgTreeList = orgService.getOrgListByOrgParentId(node);
		} catch (Exception e) {
			logger.error(node, e);
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
 * 取登录人以下组织
 * @return
 */
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getUserOrgTreeByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		List<String> orgIds = batChangeService.getOrgIds(this.getUser().getUserId());
		List<Borg> orgTreeList = batChangeService.getOrgsByOrgIds(orgIds);
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
		
		/*treeList = new ArrayList<Tree4Ajax>();
		List<Borg> orgTreeList = new ArrayList<Borg>();
		try {
			orgTreeList.add(orgService.getOrgByUserId(this.getUser().getUserId()));
		} catch (Exception e) {
			logger.error(node, e);
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
		}*/

		return JSON;
	}

	public String getOrgNameByOrgid() {

		return "orgInfo";
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSapOrgId() {
		return sapOrgId;
	}

	public void setSapOrgId(String sapOrgId) {
		this.sapOrgId = sapOrgId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public IOrgService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrgService orgService) {
		this.orgService = orgService;
	}

	public StringArrayResult getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(StringArrayResult companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public static Log getLogger() {
		return logger;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public IBatChangeService getBatChangeService() {
		return batChangeService;
	}

	public void setBatChangeService(IBatChangeService batChangeService) {
		this.batChangeService = batChangeService;
	}

}
