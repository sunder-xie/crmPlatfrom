package com.kintiger.platform.channel.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.channel.pojo.Tree4Ajax;
import com.kintiger.platform.channel.service.IChannelService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;

public class ChannelTreeAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(ChannelTreeAction.class);
	private List<Tree4Ajax> treeList;
	private String node;
	private IChannelService channelService;
	private String companyName;
	private String channelId;
	@Decode
	private String channelName;//渠道名称
	private String  channelState;//渠道状态
	private String  companyMark;//公司名称
	private String  channelParentId;//父级渠道ID
	private int total;
	private List<Bchannel> channelList;
	private Bchannel bchannel;
	
	/**
	 * 渠道组织树预览
	 * @return
	 */
	@PermissionSearch
	public String channelTreePage(){
		return "channelTreePage";
	}
	
	/**
	 * 渠道组织树预览
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text","state" })
	public String getChannelTreeListByAjax(){
		if(node != "1" || !"1".equals(node)){
			String[] nodeSplit = node.split(",");
			node = nodeSplit[0];
		}
		treeList = new ArrayList<Tree4Ajax>();
		List<Bchannel> channelTreeList = null;
		try {
			if (StringUtils.isNotEmpty(node)) {
				channelTreeList = channelService.getChannelTreeList(node);
			}
		} catch (Exception e) {
			logger.error(node, e);
		}

		if (channelTreeList == null || channelTreeList.size() == 0) {
			return JSON;
		}

		for (Bchannel channel : channelTreeList) {
			Tree4Ajax tree = new Tree4Ajax();
			//tree.setId(String.valueOf(channel.getChannelId()));
//			companyName = channelService.getCompanyName(channel.getCompanyMark());
			tree.setId(String.
					valueOf(channel.getChannelId())+","
					+ channel.getCompanyMark() + "," 
//					+ companyName + "," 
					+ channel.getChannelGrade() + "," 
					+ channel.getChannelLevel());
			tree.setText(channel.getChannelName()+"【"+channel.getChannelId()+"】");
			tree.setState("closed");
			treeList.add(tree);
		}

		return JSON;
	}

	/**
	 * 渠道管理页面
	 * @return
	 */
	@PermissionSearch
	public String channelSearchPage(){
		return "channelSearchPage";
	}
	
	@PermissionSearch
	@JsonResult(field = "channelList", include = { "channelId", "channelName","channelParentId","parentChannelName","remark",
			"lastModify","channelState"},total="total")
	public String getChannelList(){
		Bchannel  bchannel = new Bchannel();
		if(StringUtils.isNotEmpty(channelName) && channelName!=null){
			bchannel.setChannelName(channelName);
		}
		if(StringUtils.isNotEmpty(channelState) && channelState!=null){
			bchannel.setChannelState(channelState);
		}
		if(StringUtils.isNotEmpty(channelParentId) && channelParentId!=null){
			bchannel.setChannelParentId(channelParentId);
		}
		bchannel.setStart(getStart());
		bchannel.setEnd(getEnd());
		total = channelService.getChannelListCount(bchannel);
		if(total != 0){
			channelList = channelService.getChannelList(bchannel);
		}
		return JSON;
	}
	
	public String startup(){
		Bchannel bchannel = new Bchannel();
		if(StringUtils.isNotEmpty(channelId) && channelId != null){
			bchannel.setChannelId(Long.valueOf(channelId));
			//设置状态，调用方法和禁用一样，只是这里传参不一样
			bchannel.setChannelState("Y");
			BooleanResult booleanResult = channelService.startup(bchannel);
			if(booleanResult.getResult()){
				this.setSuccessMessage("渠道启用成功");
			}else{
				this.setFailMessage("渠道启用失败");
			}
		}else{
			this.setFailMessage("请选择要修改的渠道");
		}
		return RESULT_MESSAGE;
	}
	
	public String forbidden(){
		Bchannel bchannel = new Bchannel();
		if(StringUtils.isNotEmpty(channelId) && channelId != null){
			bchannel.setChannelId(Long.valueOf(channelId));
			//设置状态，调用方法和禁用一样，只是这里传参不一样
			bchannel.setChannelState("N");
			BooleanResult booleanResult = channelService.startup(bchannel);
			if(booleanResult.getResult()){
				this.setSuccessMessage("渠道禁用成功");
			}else{
				this.setFailMessage("渠道禁用失败");
			}
		}else{
			this.setFailMessage("请选择要修改的渠道");
		}
		return RESULT_MESSAGE;
	}
	@PermissionSearch
	public String toCreateChannel(){
		return "toCreateChannel";
	}
	
	public String creatChannel(){
		this.setFailMessage("");
		this.setSuccessMessage("");
		bchannel.setChannelState("Y");
		BooleanResult booleanResult = channelService.creatChannel(bchannel);
		if(booleanResult.getResult()){
			this.setSuccessMessage("渠道创建成功");
		}else{
			this.setFailMessage("渠道创建失败");
		}
		return RESULT_MESSAGE;
	}
	
	@PermissionSearch
	public String toUpdateChannelInfo(){
		Bchannel channel = new Bchannel();
		channel.setChannelId(Long.valueOf(channelId));
		bchannel = channelService.getChannelById(channel);
		return "toUpdateChannelInfo";
	}
	
	public String updateChannelInfo(){
		this.setFailMessage("");
		this.setSuccessMessage("");
		if(bchannel.getChannelId() != null){
			BooleanResult booleanResult = channelService.updateChannelInfo(bchannel);
			if(booleanResult.getResult()){
				this.setSuccessMessage("渠道修改成功,是否关闭当前页面");
			}else{
				this.setFailMessage("渠道修改失败");
			}
		}else{
			this.setFailMessage("渠道修改失败,渠道ID有误");
		}
		return RESULT_MESSAGE;
	}
	
	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public IChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelState() {
		return channelState;
	}

	public void setChannelState(String channelState) {
		this.channelState = channelState;
	}

	public String getCompanyMark() {
		return companyMark;
	}

	public void setCompanyMark(String companyMark) {
		this.companyMark = companyMark;
	}

	public String getChannelParentId() {
		return channelParentId;
	}

	public void setChannelParentId(String channelParentId) {
		this.channelParentId = channelParentId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setChannelList(List<Bchannel> channelList) {
		this.channelList = channelList;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Bchannel getBchannel() {
		return bchannel;
	}

	public void setBchannel(Bchannel bchannel) {
		this.bchannel = bchannel;
	}
	
}
