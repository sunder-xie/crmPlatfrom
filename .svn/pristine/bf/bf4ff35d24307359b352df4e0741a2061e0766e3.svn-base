package com.kintiger.platform.crmdict.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.crmdict.pojo.CrmDict;
import com.kintiger.platform.crmdict.pojo.CrmDictType;
import com.kintiger.platform.crmdict.service.ICrmDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;

public class CrmDictAction extends BaseAction {

	private static final long serialVersionUID = 5042752280539471298L;

	private List<CrmDict> crmdictList = new ArrayList<CrmDict>();

	private List<CrmDictType> crmdictTypeList = new ArrayList<CrmDictType>();
	private List<CrmDictType> crmdictTypes;

	private ICrmDictService crmdictService;
	private StringResult stringResult = new StringResult();
	private int total;

	@Decode
	private String dictTypeName;
	@Decode
	private String remark;
	@Decode
	private String dictTypeValue;

	private long dictTypeId;
	private long itemId;

	private CrmDict crmdict;

	private CrmDictType crmdictType;
	@Decode
	private String itemName;

	/**
	 * 查询CrmDict字典
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchCrmDict() {
		this.dictTypeId = dictTypeId;
		return "searchCrmDict";
	}

	/**
	 * 查询CrmDict字典
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchCrmDictTable() {
		this.dictTypeId = dictTypeId;

		return "searchCrmDict";
	}

	/**
	 * 查询Dict信息
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchDict() {

		return "searchDict";
	}

	/**
	 * CrmDict字典配置
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "crmdictList", include = { "itemId", "itemName",
			"itemValue", "parentItemId", "itemDescription", "remark",
			"lastModify", "dictTypeId" }, total = "total")
	public String getCrmDictJsonList() {
		CrmDict m = new CrmDict();
		// m = getSearchInfo(m);
		m.setStart(getStart());
		m.setEnd(getEnd());
		m.setDictTypeId(dictTypeId);
		total = crmdictService.getCrmDictCount(m);
		if (total != 0) {
			crmdictList = crmdictService.getCrmDictList(m);
		}

		return JSON;
	}

	/**
	 * CrmDict字典配置
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "crmdictList", include = { "itemId", "itemName",
			"itemValue", "parentItemId", "parentItemName", "itemDescription",
			"remark", "lastModify", "dictTypeId" }, total = "total")
	public String getDictListJson() {
		CrmDict m = new CrmDict();
		// m = getSearchInfo(m);
		m.setStart(getStart());
		m.setEnd(getEnd());
		m.setDictTypeId(dictTypeId);
		if (StringUtils.isNotEmpty(dictTypeValue)
				&& StringUtils.isNotEmpty(dictTypeValue)) {
			m.setItemName(dictTypeValue);
		}
		if (StringUtils.isNotEmpty(remark) && StringUtils.isNotEmpty(remark)) {
			m.setRemark(remark);
		}
		total = crmdictService.getCrmDictCount(m);
		if (total != 0) {
			crmdictList = crmdictService.getCrmDictList(m);
		}

		return JSON;
	}

	/**
	 * CrmDict字典配置
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "crmdictList", include = { "itemId", "itemValue",
			"itemName", "itemState", "remark" }, total = "total")
	public String getDictJsonList() {
		CrmDict m = new CrmDict();
		// m = getSearchInfo(m);
		m.setStart(getStart());
		m.setEnd(getEnd());
		if (!"".equals(dictTypeValue) && dictTypeValue != null) {
			m.setDictTypeValue(dictTypeValue);
		}
		if (!"".equals(itemName) && itemName != null) {
			m.setItemName(itemName);
		}
		total = crmdictService.getDictCount(m);
		if (total != 0) {
			crmdictList = crmdictService.getDictList(m);
		}

		return JSON;
	}

	/**
	 * 查询CrmDictType字典
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchCrmDictType() {
		return "searchCrmDictType";
	}

	/**
	 * CrmDictType字典配置
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "crmdictTypeList", include = { "dictTypeId",
			"dictTypeName", "dictTypeValue", "remark", "lastModify" }, total = "total")
	public String getCrmDictTypeJsonList() {
		CrmDictType m = new CrmDictType();
		// m = getSearchInfo(m);
		m.setStart(getStart());
		m.setEnd(getEnd());
		m.setDictTypeName(dictTypeName);
		m.setDictTypeValue(dictTypeValue);
		m.setRemark(remark);
		total = crmdictService.getCrmDictTypeCount(m);
		if (total != 0) {
			crmdictTypeList = crmdictService.getCrmDictTypeList(m);
		}

		return JSON;
	}

	@PermissionSearch
	public String toCreateDictType() {
		return "toCreateDictType";
	}

	@PermissionSearch
	public String toCreateDict() {
		this.dictTypeId = dictTypeId;
		return "toCreateDict";
	}

	@PermissionSearch
	public String toCreateCrmDict() {
		return "toCreateCrmDict";
	}

	@PermissionSearch
	@JsonResult(field = "crmdictTypes", include = { "dictTypeId",
			"dictTypeName", "dictTypeValue" })
	public String getCrmDictTypeJosn() {
		crmdictTypes = crmdictService.getCrmDictTypeListJson();
		if (crmdictTypes == null) {
			crmdictTypes = new ArrayList<CrmDictType>();
		}
		return JSON;
	}

	public String CreateDictType() {
		this.setSuccessMessage("创建成功.");

		BooleanResult booleanResult = crmdictService
				.createDictType(crmdictType);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}

		return RESULT_MESSAGE;
	}

	public String CreateDict() {
		this.setSuccessMessage("创建成功.");
		BooleanResult booleanResult = crmdictService.createDict(crmdict);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	public String toUpdateDictType() {
		CrmDictType searhCrmDictType = new CrmDictType();
		searhCrmDictType.setDictTypeId(dictTypeId);
		crmdictType = crmdictService.getCrmDictType(searhCrmDictType);
		return "toUpdateDictType";
	}

	@PermissionSearch
	public String toUpdateDict() {
		CrmDict searchCrmDict = new CrmDict();
		searchCrmDict.setItemId(itemId);
		crmdict = crmdictService.getCrmDict(searchCrmDict);
		return "toUpdateDict";
	}

	@PermissionSearch
	public String toUpdateCrmDict() {
		CrmDict searchCrmDict = new CrmDict();
		searchCrmDict.setItemId(itemId);
		crmdict = crmdictService.getCrmDict(searchCrmDict);
		dictTypeId = crmdict.getDictTypeId();
		return "toUpdateCrmDict";
	}

	public String UpdateDict() {
		crmdict.setItemName(itemName);
		crmdict.setDictTypeId(dictTypeId);
		BooleanResult booleanResult = crmdictService.updateDict(crmdict);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		this.setSuccessMessage("更新成功.");
		return RESULT_MESSAGE;
	}

	public String UpdateDictType() {
		this.setSuccessMessage("更新成功.");
		BooleanResult booleanResult = crmdictService
				.updateDictType(crmdictType);
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	public String DeleteDict() {
		this.setSuccessMessage("操作成功！");
		CrmDict deleteCrmDict = new CrmDict();
		deleteCrmDict.setItemId(itemId);
		deleteCrmDict.setItemState("D");
		BooleanResult booleanResult = crmdictService.updateDict(deleteCrmDict);
		if (!booleanResult.getResult()) {
			// stringResult.setResult("操作失败");
			// stringResult.setCode(booleanResult.getCode());
			this.setFailMessage("操作失败!");

		}
		return RESULT_MESSAGE;
	}

	public String deleteDictType() {
		this.setSuccessMessage("操作成功！");
		CrmDictType deleteCrmDictType = new CrmDictType();
		deleteCrmDictType.setDictTypeId(dictTypeId);
		crmdict = new CrmDict();
		crmdict.setDictTypeId(dictTypeId);
		int n = 0;
		n = crmdictService.getCrmDictCount(crmdict);
		if (n > 0) {
			this.setFailMessage("该类型下配置了详细条目,不能直接删除");
			return RESULT_MESSAGE;
		}
		deleteCrmDictType.setDictTypeState("D");
		BooleanResult booleanResult = crmdictService
				.updateDictType(deleteCrmDictType);
		if (!booleanResult.getResult()) {
			/*
			 * stringResult.setResult("操作失败！");
			 * stringResult.setCode(booleanResult.getCode());
			 */
			this.setFailMessage("操作失败!");

		}
		return RESULT_MESSAGE;
	}

	public List<CrmDict> getCrmdictList() {
		return crmdictList;
	}

	public void setCrmdictList(List<CrmDict> crmdictList) {
		this.crmdictList = crmdictList;
	}

	public List<CrmDictType> getCrmdictTypeList() {
		return crmdictTypeList;
	}

	public void setCrmdictTypeList(List<CrmDictType> crmdictTypeList) {
		this.crmdictTypeList = crmdictTypeList;
	}

	public ICrmDictService getCrmdictService() {
		return crmdictService;
	}

	public void setCrmdictService(ICrmDictService crmdictService) {
		this.crmdictService = crmdictService;
	}

	public StringResult getStringResult() {
		return stringResult;
	}

	public void setStringResult(StringResult stringResult) {
		this.stringResult = stringResult;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getDictTypeName() {
		return dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDictTypeValue() {
		return dictTypeValue;
	}

	public void setDictTypeValue(String dictTypeValue) {
		this.dictTypeValue = dictTypeValue;
	}

	public long getDictTypeId() {
		return dictTypeId;
	}

	public void setDictTypeId(long dictTypeId) {
		this.dictTypeId = dictTypeId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public CrmDict getCrmdict() {
		return crmdict;
	}

	public void setCrmdict(CrmDict crmdict) {
		this.crmdict = crmdict;
	}

	public CrmDictType getCrmdictType() {
		return crmdictType;
	}

	public void setCrmdictType(CrmDictType crmdictType) {
		this.crmdictType = crmdictType;
	}

	public List<CrmDictType> getCrmdictTypes() {
		return crmdictTypes;
	}

	public void setCrmdictTypes(List<CrmDictType> crmdictTypes) {
		this.crmdictTypes = crmdictTypes;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
