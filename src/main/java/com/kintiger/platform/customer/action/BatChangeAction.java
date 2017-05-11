package com.kintiger.platform.customer.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.channel.pojo.Tree4Ajax;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.customer.service.IBatChangeService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;

public class BatChangeAction extends BaseAction{
	
	private String node;
	private String userId;
	private String userId1;
	private String userName1;
	private String userName;
	private String posId;
	private List<Tree4Ajax> treeList;
	private IOrgService orgServiceHessian;	
	private IBatChangeService batChangeService;
	private List<AllUsers> empList;
	private String orgId;
	private List<Customer> custlist;
	@Decode
	private  String custIdList;
	@Decode
	private  String custIds;
	@Decode
	private String fiyId;
	private static final Logger logger = Logger.getLogger(BatChangeAction.class);
	@Decode
	private String orgName;
	private String orgCity;

	
	/**
	 * 根据组织ID查找人员信息   add by allen
	 * 
	 * @return
	 */
	
	public String SaveBatChange(){
		Customer  co=new Customer();
		String[] l = custIdList.split(",");//
		String[] ls = custIds.split(",");
		if(l.length>0){//如果传过来的id 为NULL 的情况下不修改
//		co.setStationUserId(batChangeService.getStationUserId(userId));
			co.setStationUserId(userId);
		co.setCodes(l);
			batChangeService.updateStationUserIdById(co);
		}
		if(ls.length>0){//如果传过来的id 为NULL 的情况下不修改
			Customer  cd=new Customer();
//			cd.setStationUserId(batChangeService.getStationUserId(userId1));
			cd.setStationUserId(userId1);
			cd.setCodes(ls);
			batChangeService.updateStationUserIdById(cd);
		}
		this.setSuccessMessage("维护成功");
		return RESULT_MESSAGE;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 3555454679506762579L;
	@PermissionSearch
	@JsonResult(field = "empList", include = { "userId", "posName", 
	"userName","mobile" })
	public String getEmpListByOrgId(){
		//System.out.println("orgId:" + orgId);
		if(orgId!=null && !"".equals(orgId)){
			empList = batChangeService.getEmpListByOrgId(orgId);
		}	
		return JSON;		
	}
	
	@PermissionSearch
	@JsonResult(field = "empList", include = { "posId", "stationName", 
	"userName","mobile","busMobilephone","userId","orgId","orgName"})
	public String getStationListByOrgId(){
		if(orgId!=null && !"".equals(orgId)){
			AllUsers user=new AllUsers();
			user.setPosId(posId);
			user.setOrgId(orgId);
			empList = batChangeService.getStationListByOrgId(user);
		}else {
			empList = null;
		}
		return JSON;		
	}
	
	
	@PermissionSearch
	@JsonResult(field = "custlist", include = { "custId", "custName", 
	"custNumber" })
	public String getCustList(){
		Customer co=new Customer();
//		co.setStationUserId(batChangeService.getStationUserId(fiyId));
		co.setStationUserId(fiyId);
		custlist=batChangeService.getCustomert(co);
		return JSON;		
	}
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
	 * 根据组织获取该组织及其以下组织
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getOrgTreeByParentOrg(){
		treeList = new ArrayList<Tree4Ajax>();
		Tree4Ajax tree = new Tree4Ajax();
		tree.setId(node);
		try {
			orgName = new String(orgName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} 
		tree.setText(orgName);
		tree.setState("closed");
		treeList.add(tree);
		return JSON;
	}
	/**
	 * 获取当前人所在组织及以下组织
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
			orgTreeList.add(orgServiceHessian.getOrgByUserId(this.getUser().getUserId()));
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
*/
		return JSON;
	}
	
	@PermissionSearch
	public String toBatChange(){
		return "batChange";
	}
	
	
	@PermissionSearch
	public String toSearchMan(){
		fiyId=this.fiyId;
		userId=this.getUser().getUserId();
		Borg borg=orgServiceHessian.getOrgByUserId(userId);
		orgCity=borg.getOrgCity();
		return "toSearchMan";
	}
	@PermissionSearch
	public String toSearchMan1(){
		fiyId=this.fiyId;
		userId=this.getUser().getUserId();
		Borg borg=orgServiceHessian.getOrgByUserId(userId);
		orgCity=borg.getOrgCity();
		return "toSearchMan1";
	}

	public String selectOrgTree4Station(){
		node=node;
		if(StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())){
		try {
			orgName = new String(orgName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} }
		//getUserOrgTreeByAjax
		userId=this.getUser().getUserId();
		Borg borg=orgServiceHessian.getOrgByUserId(userId);
		orgCity=borg.getOrgCity();
		return  "selectOrgTree4Station";
	}
	
	public String selectOrgTreeUser(){
		node=node;
		if(StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())){
		try {
			orgName = new String(orgName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} }
        //exitMob='Y';
		return  "selectOrgTreeUser";
	}
	
	public String selectOrgTreeUserWithBusMobile(){
		node=node;
		if(StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())){
		try {
			orgName = new String(orgName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} }
        //exitMob='Y';
		return  "selectOrgTreeUserWithBusMobile";
	}
	
	public String selectOrgTreeUserAndUserIdWithBusMobile(){
		node=node;
		if(StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())){
		try {
			orgName = new String(orgName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} }
        //exitMob='Y';
		return  "selectOrgTreeUserAndUserIdWithBusMobile";
	}
	
	public String selectOrgTreeUserAndUserIdWithBusMobileAndOrg(){
		node=node;
		if(StringUtils.isNotEmpty(orgName)
				&& StringUtils.isNotEmpty(orgName.trim())){
		try {
			orgName = new String(orgName.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		} }
        //exitMob='Y';
		return  "selectOrgTreeUserAndUserIdWithBusMobileAndOrg";
	}
	
	public String getUserId() {
		return userId;
	}

	/**
	 * 根据名字查找人员信息 add by tony
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@PermissionSearch
	@JsonResult(field = "empList", include = { "userId", "stationName", "userName",
			"orgId","posId","mobile","busMobilephone","orgName" })
	public String getEmpListByUserName() throws UnsupportedEncodingException {
		// System.out.println("orgId:" + orgId);
		AllUsers user=new AllUsers();
		if (StringUtils.isNotEmpty(userName)) {
			userName = new String(userName.getBytes("ISO8859-1"), "UTF-8");
			user.setUserName(userName);
			user.setPosId(posId);
			empList = batChangeService.getEmpListByUserName(user);
		}
		return JSON;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getUserName1() {
		return userName1;
	}



	public void setUserName1(String userName1) {
		this.userName1 = userName1;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserId1() {
		return userId1;
	}



	public void setUserId1(String userId1) {
		this.userId1 = userId1;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
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

	public IBatChangeService getBatChangeService() {
		return batChangeService;
	}

	public void setBatChangeService(IBatChangeService batChangeService) {
		this.batChangeService = batChangeService;
	}

	public List<AllUsers> getEmpList() {
		return empList;
	}

	public void setEmpList(List<AllUsers> empList) {
		this.empList = empList;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<Customer> getCustlist() {
		return custlist;
	}

	public void setCustlist(List<Customer> custlist) {
		this.custlist = custlist;
	}

	public String getCustIdList() {
		return custIdList;
	}

	public void setCustIdList(String custIdList) {
		this.custIdList = custIdList;
	}

	public String getCustIds() {
		return custIds;
	}

	public void setCustIds(String custIds) {
		this.custIds = custIds;
	}

	public String getFiyId() {
		return fiyId;
	}

	public void setFiyId(String fiyId) {
		this.fiyId = fiyId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	
}
