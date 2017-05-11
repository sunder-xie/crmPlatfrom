package com.kintiger.platform.saleItems.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.channel.service.IChannelService;
import com.kintiger.platform.crmdict.service.ICrmDictService;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.master.service.MasterService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.saleItems.pojo.SaleItems;
import com.kintiger.platform.saleItems.pojo.Sku;
import com.kintiger.platform.saleItems.service.ISaleItemsService;
import com.kintiger.platform.sales.service.ISalesService;


public class SaleItemsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(SaleItemsAction.class);
	private SaleItems saleItems;
	private String userName;

	private ISaleItemsService saleItemsService;
	private ICrmDictService crmDictService; // 数据配置接口

	private CmsTbDict cmsTbDict;

	private IKunnrService kunnrService;
	private IChannelService channelService;
	private MasterService masterService; 
	@Decode

	private List<SaleItems> saleItemsList;
	@Decode
	
	private ISalesService salesService;
	private String userId;
	private IOrgService orgServiceHessian;
	private String orgId;
	private String orgName;
	
	private int size;


	private String  saleItemsSkuName;
	private String channelId;
	private String custKunnrName;
	private String custSystemName;
	private Sku sku;
	private List<Sku> skuList;
	private @Decode String skuName;
	private String saleKunnrName;
	private String saleSystemName;
	private String saleKunnr;
	private String saleSystem;
	private Long saleItemsId;
	private String ids;
	private String saleItemsKunnrName;
	private String saleItemsSystemName;
	private String channelName;
	private String sku_id;
	private String saleItemsSystem;
	private String saleItemsKunnr; 
	
	
	public String getSaleItemsSystem() {
		return saleItemsSystem;
	}

	public void setSaleItemsSystem(String saleItemsSystem) {
		this.saleItemsSystem = saleItemsSystem;
	}

	public String getSaleItemsKunnr() {
		return saleItemsKunnr;
	}

	public void setSaleItemsKunnr(String saleItemsKunnr) {
		this.saleItemsKunnr = saleItemsKunnr;
	}

	@PermissionSearch
	public String toSaleItemsApply() {
		AllUsers user = this.getUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		saleItems = new SaleItems();
		saleItems.setCreateUser(user.getUserId());
		saleItems.setCreateDate(sdf.format(new Date()));
		userName = user.getUserName();

		Borg org = orgServiceHessian.getOrgByUserId(user.getUserId());
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		saleItems.setOrgId(orgId);
		saleItems.setOrgName(orgName);
		return "toSaleItemsApply";
	}

	// 组织树
	public String orgTreePage() {
		userId=this.getUser().getUserId();
		Borg borg=orgServiceHessian.getOrgByUserId(userId);
		if("B".equals(borg.getOrgCity())){
			return "orgAllTree";
		}
		return "orgtree";
	}



	public String addSaleItems(){
		return "toAddSaleItems";		
	}
	public String createSaleItems(){
		
		this.setSuccessMessage("创建成功.");

		BooleanResult booleanResult = saleItemsService
				.createSaleItems(saleItems);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}

		return RESULT_MESSAGE;
		
	}
	@PermissionSearch
	@JsonResult(field = "skuList",include = { "skuId",
			"skuName" },total = "size")
	public String getSkuName(){
		if (sku == null) {
			sku = new Sku();
		}
		if (StringUtils.isNotEmpty(skuName)) {
			sku.setSkuName(skuName);
		}
		
		sku.setStart(getStart());
		sku.setEnd(getEnd());
		size = saleItemsService.getSkuCount(sku);
		if (size != 0) {
			skuList = saleItemsService.getSkuNameList(sku);
			System.out.println(skuList.size());
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "saleItemsList", include = { "channelName",
			"saleItemsKunnrName","saleItemsSystemName","skuNames","saleItemsId"}, total = "size")
	public String saleItemsSearch(){
		
		if (saleItems == null) {
			saleItems = new SaleItems();
		}
		if (StringUtils.isNotEmpty(channelId)) {
			saleItems.setChannelId(Integer.parseInt(channelId));
		}
		if (StringUtils.isNotEmpty(saleKunnr)) {
			saleItems.setSaleItemsKunnr(saleKunnr);
		}
		if (StringUtils.isNotEmpty(saleSystem)) {
			saleItems.setSaleItemsSystem(saleSystem);
		}
		saleItems.setStart(getStart());
		saleItems.setEnd(getEnd());
		size = saleItemsService.getSaleItemsCount(saleItems);
		if (size != 0) {
			saleItemsList = saleItemsService.getSaleItemsList(saleItems);
			for(int i=0;i<saleItemsList.size();i++){
				String skus=saleItemsList.get(i).getSku_id();
				String[] sku=skus.split(",");
				for(int j=0;j<sku.length;j++){
					Sku s=new Sku();
					s.setSkuId(Integer.parseInt(sku[j].trim()));
					s=saleItemsService.getSkuNameById(s);
					if(saleItemsList.get(i).getSkuNames()!=null){
					    saleItemsList.get(i).setSkuNames(saleItemsList.get(i).getSkuNames()+","+s.getSkuName());
					}else{
						saleItemsList.get(i).setSkuNames(s.getSkuName());
					}
				}
			}
		}
		return JSON;
	}
	
	public String deleteSaleItems() {
		this.setSuccessMessage("操作成功！");
		SaleItems deleteSaleItems = new SaleItems();
		try{
			String[] l = ids.split(",");
			deleteSaleItems.setCodes(l);
			deleteSaleItems.setSaleItemsState("D");
			StringResult result = saleItemsService.delSaleItems(deleteSaleItems);
			if (ISaleItemsService.ERROR.equals(result.getCode())) {
				this.setFailMessage(result.getResult());
			} else {
				this.setSuccessMessage("处理成功");
			}
		}catch (Exception e) {
			this.setFailMessage("处理失败！");
			logger.error(deleteSaleItems, e);
		}

		return RESULT_MESSAGE;
	}
	


	public String toUpdateSaleItems() {
		SaleItems searhSaleItems = new SaleItems();
		searhSaleItems.setSaleItemsId(saleItemsId);
//		if (StringUtils.isNotEmpty(searhSaleItems.getSaleItemsKunnr())
//				&& StringUtils.isNotEmpty(searhSaleItems.getSaleItemsKunnr().trim())) {
//			searhSaleItems.setSaleItemsKunnr(searhSaleItems.getSaleItemsKunnr().trim());
//			String [] str=searhSaleItems.getSaleItemsKunnr().trim().split(", ");
//			String kunnrName="";
//			for (int i = 0; i < str.length; i++) {
//				Kunnr kn = new Kunnr();
//				kn.setKunnr(str[i].trim());
//				if (kn != null) {
//					if (!"".equals(kunnrName)) {
//						kunnrName = kunnrName + "," + kn.getName1();
//					} else {
//						kunnrName = kn.getName1();
//					}
//				}
//			}
//			saleItems.setSaleItemsKunnrName(kunnrName);
//		}
		
		
		saleItems = saleItemsService.getSaleItems(searhSaleItems);
		saleItems.setSaleItemsId(saleItemsId);
		String skus=saleItems.getSku_id();
		String[] sku=skus.split(",");
		for(int j=0;j<sku.length;j++){
			Sku s=new Sku();
			s.setSkuId(Integer.parseInt(sku[j].trim()));
			s=saleItemsService.getSkuNameById(s);
			if(saleItems.getSkuNames()!=null){
				saleItems.setSkuNames(saleItems.getSkuNames()+","+s.getSkuName());
			}else{
				saleItems.setSkuNames(s.getSkuName());
			}
		}
		return "toUpdateSaleItems";
	
	}
	
	public String updateSaleItems() {
		this.setSuccessMessage("");
		BooleanResult result = null;
		BooleanResult booleanResult = saleItemsService.updateSaleItems(saleItems);
				
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		this.setSuccessMessage("更新成功.");
		return RESULT_MESSAGE;
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
	public SaleItems getSaleItems() {
		return saleItems;
	}

	public void setSaleItems(SaleItems saleItems) {
		this.saleItems = saleItems;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ISaleItemsService getSaleItemsService() {
		return saleItemsService;
	}

	public void setSaleItemsService(ISaleItemsService saleItemsService) {
		this.saleItemsService = saleItemsService;
	}

	public ICrmDictService getCrmDictService() {
		return crmDictService;
	}

	public void setCrmDictService(ICrmDictService crmDictService) {
		this.crmDictService = crmDictService;
	}

	public CmsTbDict getCmsTbDict() {
		return cmsTbDict;
	}

	public void setCmsTbDict(CmsTbDict cmsTbDict) {
		this.cmsTbDict = cmsTbDict;
	}



	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	public IChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}

	public List<SaleItems> getSaleItemsList() {
		return saleItemsList;
	}

	public void setSaleItemsList(List<SaleItems> saleItemsList) {
		this.saleItemsList = saleItemsList;
	}

	public ISalesService getSalesService() {
		return salesService;
	}

	public void setSalesService(ISalesService salesService) {
		this.salesService = salesService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
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

	public MasterService getMasterService() {
		return masterService;
	}

	public void setMasterService(MasterService masterService) {
		this.masterService = masterService;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSaleItemsSkuName() {
		return saleItemsSkuName;
	}

	public void setSaleItemsSkuName(String saleItemsSkuName) {
		this.saleItemsSkuName = saleItemsSkuName;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getCustKunnrName() {
		return custKunnrName;
	}

	public void setCustKunnrName(String custKunnrName) {
		this.custKunnrName = custKunnrName;
	}

	public String getCustSystemName() {
		return custSystemName;
	}

	public void setCustSystemName(String custSystemName) {
		this.custSystemName = custSystemName;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	
	public List<Sku> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<Sku> skuList) {
		this.skuList = skuList;
	}

	public String getSaleKunnrName() {
		return saleKunnrName;
	}

	public void setSaleKunnrName(String saleKunnrName) {
		this.saleKunnrName = saleKunnrName;
	}

	public String getSaleSystemName() {
		return saleSystemName;
	}

	public void setSaleSystemName(String saleSystemName) {
		this.saleSystemName = saleSystemName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getSaleKunnr() {
		return saleKunnr;
	}

	public void setSaleKunnr(String saleKunnr) {
		this.saleKunnr = saleKunnr;
	}

	public String getSaleSystem() {
		return saleSystem;
	}

	public void setSaleSystem(String saleSystem) {
		this.saleSystem = saleSystem;
	}

	public Long getSaleItemsId() {
		return saleItemsId;
	}

	public void setSaleItemsId(Long saleItemsId) {
		this.saleItemsId = saleItemsId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSaleItemsKunnrName() {
		return saleItemsKunnrName;
	}

	public void setSaleItemsKunnrName(String saleItemsKunnrName) {
		this.saleItemsKunnrName = saleItemsKunnrName;
	}

	public String getSaleItemsSystemName() {
		return saleItemsSystemName;
	}

	public void setSaleItemsSystemName(String saleItemsSystemName) {
		this.saleItemsSystemName = saleItemsSystemName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}



}
