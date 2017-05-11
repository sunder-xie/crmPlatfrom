package com.kintiger.platform.master.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.customer.service.ICustomerService;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.master.pojo.CrmTbSku;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.master.pojo.SupervisorCheckItem;
import com.kintiger.platform.master.pojo.SupervisorLineCheckItem;
import com.kintiger.platform.master.service.MasterService;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.webservice.resps.JsonUtil;

public class MasterAction extends BaseAction {
    private MasterService masterService;
    private List<Materiel> materielList;
    private int total;
    private String matnr;
    @Decode
    private String maktx;
    /**
     * 菜单权限：A ：admin（用于admin同步物料至销售品项）
     */
    private String opType;
    /**
     * 口径-品类
     */
    @Decode
    private String skuType;
    /**
     * 口径编码-品类sap编码
     */
    @Decode
    private String skuTypeId;
    /**
     * // 字典接口
     */
    private IDictService dictServiceHessian;
    /**
     * skuId-物料编码
     */
    private String skuId;
    /**
     * skuName-物料短文本
     */
    @Decode
    private String skuName;

    private String uploadFileFileName;
    private File uploadFile;
    private SupervisorCheckItem supervisorCheckItem;
    private List<SupervisorLineCheckItem> supervisorLineCheckItemList;
    private List<SupervisorCheckItem> supervisorCheckItemList;
    private String supervisorLineCheckItemListjson;
    private String supervisorCheckItemListjson;

    private String userId;
    private String orgId;
    private String orgName;
    private CmsTbDict cmsTbDict;
    private List<CmsTbDict> cmsTbDictCustStateList;
    private String customer_state;
    private IOrgService orgServiceHessian; // 调组织接口
    private ICustomerService customerService;
    private String bhxjFlag;
    private String custNumber;
    @Decode
    private String custName;
    private String channelId;
    
    @Decode
	private String allChannelName;
    private String stationUserId;
    @Decode
    private String contactName;
    private String custState;
    private String custKunnr;
    private String custType;

    private String createOrgId;
    private String createDateStart;
    private String createDateEnd;
    private String custParent;

    private Kunnr kunnr;
    private String[] kunnrs;
    private String kunnrId;
    @Decode
    private String name1;
    private String businessManager;
    private String businessCompetent;
    private String status;
    private String codes;
    private List<Kunnr> kunnrList;

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
     * 调转到督导考核页面
     */
    public String toSupervisorCheck() {
	AllUsers user = this.getUser();
	userId = user.getUserId();
	Borg org = orgServiceHessian.getOrgByUserId(userId);
	orgId = org.getOrgId().toString();
	orgName = org.getOrgName();
	cmsTbDict = new CmsTbDict();
	cmsTbDict.setDictTypeId(Long.valueOf(customer_state));
	cmsTbDictCustStateList = customerService.getCmsTbDictList(cmsTbDict);
	return "toSupervisorCheck";
    }

    /**
     * 新增 督导考核数据
     * 
     * @return
     */
    public String toAddSupervisorItems() {
	return "toAddSupervisorItems";
    }

    public String toSupervisorSearchCols() {
	// 所有用户
	AllUsers user = this.getUser();
	userId = user.getUserId();
	Borg org = orgServiceHessian.getOrgByUserId(userId);
	orgId = org.getOrgId().toString();
	orgName = org.getOrgName();
	cmsTbDict = new CmsTbDict();
	cmsTbDict.setDictTypeId(Long.valueOf(customer_state));
	cmsTbDictCustStateList = customerService.getCmsTbDictList(cmsTbDict);

	return "toSupervisorSearchCols";
    }

    /**
     * 
     * 經銷商查询结果
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "kunnrList", include = { "kunnr", "name1", "name3", "orgName" }, total = "total")
    public String kunnrSearchFromMaster() {
	kunnr = new Kunnr();
	kunnr.setStart(getStart());
	kunnr.setEnd(getEnd());
	if (StringUtils.isNotEmpty(kunnrId)) {
	    kunnr.setKunnr(kunnrId);
	} else if (kunnrs != null && !kunnrs[0].equals("") && kunnrs[0] != null) {
	    kunnr.setKunnrs(kunnrs);
	}
	if (StringUtils.isNotEmpty(name1)) {
	    kunnr.setName1(name1);
	}
	if (StringUtils.isNotEmpty(businessManager)) {
	    kunnr.setBusinessManager(businessManager);
	}
	if (StringUtils.isNotEmpty(businessCompetent)) {
	    kunnr.setBusinessCompetent(businessCompetent);
	}
	if (channelId != null) {
	    kunnr.setChannelId(Long.valueOf(channelId));
	}
	if (StringUtils.isNotEmpty(status)) {
	    kunnr.setStatus(status);
	}
	if (StringUtils.isNotEmpty(orgId)) {
	    String[] str = orgId.split(", ");
	    if (str.length > 1) {
		kunnr.setOrgId(str[1]);
	    } else {
		kunnr.setOrgId(orgId);
	    }
	}
	if (StringUtils.isNotEmpty(bhxjFlag)) {
	    kunnr.setBhxjFlag(bhxjFlag);
	}
	if (StringUtils.isNotEmpty(codes)) {
	    String[] str = codes.split(", ");
	    kunnr.setCodes(str);
	}
	total = masterService.kunnrSearchCount(kunnr);
	if (total != 0) {
	    kunnrList = masterService.kunnrSearchFromMaster(kunnr);
	}
	return JSON;
    }

    /**
     * 查询督导标准数据zpf
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "supervisorLineCheckItemList", include = { "checkId", "custId", "custName", "orgName", "channelName", "createUserId", "createName", "createTime", "checkId1", "matId1", "matNam1", "checkValue1", "minPrice1", "maxPrice1", "matType1", "checkId2", "matId2", "matNam2", "checkValue2", "minPrice2", "maxPrice2", "matType2", "checkId3", "matId3", "matNam3", "checkValue3", "minPrice3", "maxPrice3", "matType3", "checkId4", "matId4", "matNam4", "checkValue4", "minPrice4", "maxPrice4", "matType4", "checkId5", "matId5", "matNam5", "checkValue5", "minPrice5", "maxPrice5", "matType5", "checkId6", "matId6", "matNam6", "checkValue6", "minPrice6", "maxPrice6", "matType6", "checkId7", "matId7", "matNam7", "checkValue7", "minPrice7", "maxPrice7", "matType7", "checkId8", "matId8",
	    "matNam8", "checkValue8", "minPrice8", "maxPrice8", "matType8", "checkId9", "matId9", "matNam9", "checkValue9", "minPrice9", "maxPrice9", "matType9", "checkId10", "matId10", "matNam10", "checkValue10", "minPrice10", "maxPrice10", "matType10", "checkId11", "matId11", "matNam11", "checkValue11", "minPrice11", "maxPrice11", "matType11", "checkId12", "matId12", "matNam12", "checkValue12", "minPrice12", "maxPrice12", "matType12", "checkId13", "matId13", "matNam13", "checkValue13", "minPrice13", "maxPrice13", "matType13", "checkId14", "matId14", "matNam14", "checkValue14", "minPrice14", "maxPrice14", "matType14", "checkId15", "matId15", "matNam15", "checkValue15", "minPrice15", "maxPrice15", "matType15", "checkId16", "matId16", "matNam16", "checkValue16", "minPrice16", "maxPrice16",
	    "matType16", "checkId17", "matId17", "matNam17", "checkValue17", "minPrice17", "maxPrice17", "matType17", "checkId18", "matId18", "matNam18", "checkValue18", "minPrice18", "maxPrice18", "matType18", "checkId19", "matId19", "matNam19", "checkValue19", "minPrice19", "maxPrice19", "matType19", "checkId20", "matId20", "matNam20", "checkValue20", "minPrice20", "maxPrice20", "matType20", "checkId21", "matId21", "matNam21", "checkValue21", "minPrice21", "maxPrice21", "matType21", "checkId22", "matId22", "matNam22", "checkValue22", "minPrice22", "maxPrice22", "matType22", "checkId23", "matId23", "matNam23", "checkValue23", "minPrice23", "maxPrice23", "matType23", "checkId24", "matId24", "matNam24", "checkValue24", "minPrice24", "maxPrice24", "matType24", "checkId25", "matId25",
	    "matNam25", "checkValue25", "minPrice25", "maxPrice25", "matType25", "checkId26", "matId26", "matNam26", "checkValue26", "minPrice26", "maxPrice26", "matType26", "checkId27", "matId27", "matNam27", "checkValue27", "minPrice27", "maxPrice27", "matType27", "checkId28", "matId28", "matNam28", "checkValue28", "minPrice28", "maxPrice28", "matType28", "checkId29", "matId29", "matNam29", "checkValue29", "minPrice29", "maxPrice29", "matType29", "checkId30", "matId30", "matNam30", "checkValue30", "minPrice30", "maxPrice30", "matType30", "checkId31", "matId31", "matNam31", "checkValue31", "minPrice31", "maxPrice31", "matType31", "checkId32", "matId32", "matNam32", "checkValue32", "minPrice32", "maxPrice32", "matType32", "checkId33", "matId33", "matNam33", "checkValue33", "minPrice33",
	    "maxPrice33", "matType33", "checkId34", "matId34", "matNam34", "checkValue34", "minPrice34", "maxPrice34", "matType34", "checkId35", "matId35", "matNam35", "checkValue35", "minPrice35", "maxPrice35", "matType35", "checkId36", "matId36", "matNam36", "checkValue36", "minPrice36", "maxPrice36", "matType36" }, total = "total")
    public String getSupervisorItems() {

	supervisorLineCheckItemList = new ArrayList<SupervisorLineCheckItem>();
	SupervisorLineCheckItem supervisorLineCheckItem = new SupervisorLineCheckItem();
	supervisorLineCheckItem.setStart(getStart() * 36);
	supervisorLineCheckItem.setEnd(getEnd() * 36);

	if (StringUtils.isNotEmpty(custNumber)) {
	    supervisorLineCheckItem.setCustNumber(custNumber);
	}
	if (StringUtils.isNotEmpty(custName) && StringUtils.isNotEmpty(custName.trim())) {
	    supervisorLineCheckItem.setCustName(custName);
	}
	if (StringUtils.isNotEmpty(channelId)) {
	    supervisorLineCheckItem.setChannelId(channelId);
	}
	if (StringUtils.isNotEmpty(orgId)) {
	    // orgId
	    supervisorLineCheckItem.setOrgId(orgId);
	} else {
	    AllUsers user = this.getUser();
	    supervisorLineCheckItem.setOrgId(user.getOrgId());
	}
	if (StringUtils.isNotEmpty(custState)) {
	    supervisorLineCheckItem.setCustState(custState);
	}
	if (StringUtils.isNotEmpty(contactName) && StringUtils.isNotEmpty(contactName.trim())) {
	    supervisorLineCheckItem.setContactName(contactName);
	}
	if (StringUtils.isNotEmpty(stationUserId) && StringUtils.isNotEmpty(stationUserId.trim())) {
	    try {
		stationUserId = new String(stationUserId.getBytes("ISO8859-1"), "UTF-8");
	    } catch (UnsupportedEncodingException e) {

	    }
	    supervisorLineCheckItem.setStationUserId(stationUserId);
	}
	if (StringUtils.isNotEmpty(custKunnr) && StringUtils.isNotEmpty(custKunnr.trim())) {
	    try {
		custKunnr = new String(custKunnr.getBytes("ISO8859-1"), "UTF-8");
	    } catch (UnsupportedEncodingException e) {
	    }
	    supervisorLineCheckItem.setCustKunnr(custKunnr);
	}
	if (StringUtils.isNotEmpty(custParent) && StringUtils.isNotEmpty(custParent.trim())) {
	    supervisorLineCheckItem.setCustParent(custParent);
	}
	if (StringUtils.isNotEmpty(custType) && StringUtils.isNotEmpty(custType.trim())) {
	    supervisorLineCheckItem.setCustType(custType);
	}
	if (StringUtils.isNotEmpty(createOrgId)) {
	    supervisorLineCheckItem.setCreateOrgId(createOrgId);
	}
	if (StringUtils.isNotEmpty(createDateStart)) {
	    supervisorLineCheckItem.setCreateDateStart(createDateStart);
	}
	if (StringUtils.isNotEmpty(createDateEnd)) {
	    supervisorLineCheckItem.setCreateDateEnd(createDateEnd);
	}

	total = masterService.getSupervisorItemsCount(supervisorLineCheckItem);

	if (total != 0) {
	    total = total / 36;
	    supervisorLineCheckItemList = masterService.getSupervisorItems(supervisorLineCheckItem);

	}
	return JSON;
    }

    /**
     * 查询督导标准数据zpf cols
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "supervisorCheckItemList", include = { "checkId", "custId", "custName", "orgName", "channelName", "createUserId", "createName", "createTime", "bezei40", "matName", "checkValue", "minPrice", "maxPrice" }, total = "total")
    public String getSupervisorItemsCols() {

	List<Materiel> list = masterService.getMasterCols(new Materiel());
	int colsnum = list.size();

	supervisorCheckItemList = new ArrayList<SupervisorCheckItem>();

	SupervisorLineCheckItem supervisorLineCheckItem = new SupervisorLineCheckItem();

	supervisorLineCheckItem.setStart(getStart());
	supervisorLineCheckItem.setEnd(getEnd());

	if (StringUtils.isNotEmpty(custNumber)) {
	    supervisorLineCheckItem.setCustNumber(custNumber);
	}
	if (StringUtils.isNotEmpty(custName) && StringUtils.isNotEmpty(custName.trim())) {
	    supervisorLineCheckItem.setCustName(custName);
	}
	if (StringUtils.isNotEmpty(channelId)) {
	    supervisorLineCheckItem.setChannelId(channelId);
	}
	if (StringUtils.isNotEmpty(allChannelName)) {
		supervisorLineCheckItem.setAllChannelName(allChannelName);
	}
	if (StringUtils.isNotEmpty(orgId)) {
	    // orgId
	    supervisorLineCheckItem.setOrgId(orgId);
	} else {
	    AllUsers user = this.getUser();
	    supervisorLineCheckItem.setOrgId(user.getOrgId());
	}
	if (StringUtils.isNotEmpty(custState)) {
	    supervisorLineCheckItem.setCustState(custState);
	}
	if (StringUtils.isNotEmpty(contactName) && StringUtils.isNotEmpty(contactName.trim())) {
	    supervisorLineCheckItem.setContactName(contactName);
	}
	if (StringUtils.isNotEmpty(stationUserId) && StringUtils.isNotEmpty(stationUserId.trim())) {
	    try {
		stationUserId = new String(stationUserId.getBytes("ISO8859-1"), "UTF-8");
	    } catch (UnsupportedEncodingException e) {

	    }
	    supervisorLineCheckItem.setStationUserId(stationUserId);
	}
	if (StringUtils.isNotEmpty(custKunnr) && StringUtils.isNotEmpty(custKunnr.trim())) {
	    try {
		custKunnr = new String(custKunnr.getBytes("ISO8859-1"), "UTF-8");
	    } catch (UnsupportedEncodingException e) {
	    }
	    supervisorLineCheckItem.setCustKunnr(custKunnr);
	}
	if (StringUtils.isNotEmpty(custParent) && StringUtils.isNotEmpty(custParent.trim())) {
	    supervisorLineCheckItem.setCustParent(custParent);
	}
	if (StringUtils.isNotEmpty(custType) && StringUtils.isNotEmpty(custType.trim())) {
	    supervisorLineCheckItem.setCustType(custType);
	}
	if (StringUtils.isNotEmpty(createOrgId)) {
	    supervisorLineCheckItem.setCreateOrgId(createOrgId);
	}
	if (StringUtils.isNotEmpty(createDateStart)) {
	    supervisorLineCheckItem.setCreateDateStart(createDateStart);
	}
	if (StringUtils.isNotEmpty(createDateEnd)) {
	    supervisorLineCheckItem.setCreateDateEnd(createDateEnd);
	}

	total = masterService.getSupervisorItemsColsCount(supervisorLineCheckItem);

	if (total != 0) {

	    List<Customer> clist = new ArrayList<Customer>();
	    System.out.println("1");
	    clist = masterService.getCustomerListCols(supervisorLineCheckItem);

	    supervisorCheckItemList = new ArrayList<SupervisorCheckItem>();
	    System.out.println("2");
	    for (Customer customer : clist) {
		List<SupervisorCheckItem> list1 = new ArrayList<SupervisorCheckItem>();
		list1 = (List<SupervisorCheckItem>) masterService.getSupervisorItemsByCustId(customer.getCustId().toString());
		supervisorCheckItemList.addAll(list1);
	    }
	    System.out.println("3");

	    //
	    // supervisorCheckItemList =
	    // masterService.getSupervisorItemsCols(supervisorCheckItem);

	}
	return JSON;
    }

    /**
     * 保存修改数据
     */

    @SuppressWarnings("unchecked")
    public String saveChagCheckItem() {

	this.setSuccessMessage("");
	this.setFailMessage("");

	System.out.println(supervisorLineCheckItemListjson);
	this.supervisorLineCheckItemList = JsonUtil.getDTOList(this.supervisorLineCheckItemListjson, SupervisorLineCheckItem.class);

	if (null != supervisorLineCheckItemList) {
	    System.out.println(supervisorLineCheckItemList.size());
	    masterService.saveChagCheckItem(supervisorLineCheckItemList);

	}

	return RESULT_MESSAGE;
    }

    @SuppressWarnings("unchecked")
    public String saveChagCheckItemCols() {

	this.setSuccessMessage("");
	this.setFailMessage("");

	this.supervisorCheckItemList = JsonUtil.getDTOList(this.supervisorCheckItemListjson, SupervisorCheckItem.class);

	String createName = this.getUser().getUserName();
	String createId = this.getUser().getUserId();
	Calendar ca = Calendar.getInstance();
	String createDate = ca.get(Calendar.YEAR) + "-" + (ca.get(Calendar.MONTH) + 1) + "-" + ca.get(Calendar.DATE);

//	supervisorCheckItemList =null;
	if (null != supervisorCheckItemList) {

	    for (SupervisorCheckItem supervisorCheckItem : supervisorCheckItemList) {
		supervisorCheckItem.setCreateUserId(createId);
		supervisorCheckItem.setCreateName(createName);
		supervisorCheckItem.setCreateTime(createDate);
		// custname ,channelId,channelname by custid
		Customer cust = this.masterService.validateCustId(supervisorCheckItem.getCustId());
		supervisorCheckItem.setCustName(cust.getCustName());
		supervisorCheckItem.setChannelId(cust.getChannelId().toString());
		supervisorCheckItem.setChannelName(cust.getChannelName());
		
	    }

      	    masterService.clearAndSaveItems(supervisorCheckItemList);
	}

	return RESULT_MESSAGE;
    }

    /**
     * 导出 督导数据
     */

    public String itemsExport() {

	this.setSuccessMessage("");
	this.setFailMessage("");

	this.supervisorLineCheckItemList = new ArrayList<SupervisorLineCheckItem>();
	SupervisorLineCheckItem supervisorLineCheckItem = new SupervisorLineCheckItem();

	supervisorLineCheckItem.setStart(0);
	supervisorLineCheckItem.setEnd(50 * 10000);

	if (masterService.getSupervisorItemsCount(supervisorLineCheckItem) > 0) {
	    supervisorLineCheckItemList = masterService.getSupervisorItems(supervisorLineCheckItem);
	    List2Excel(supervisorLineCheckItemList);
	    this.setSuccessMessage("督导考核标准导出成功");
	} else
	    this.setFailMessage("请不要导出空数据");

	return RESULT_MESSAGE;
    }

    /**
     * 导出 督导数据
     */

    public String itemsExportCols() {

	this.setSuccessMessage("");
	this.setFailMessage("");

	List<Customer> clist = new ArrayList<Customer>();
	// Customer cust = new Customer();
	// cust.setStart(0);
	// cust.setEnd(10000*5);

	SupervisorLineCheckItem supervisorLineCheckItem = new SupervisorLineCheckItem();

	supervisorLineCheckItem.setStart(0);
	supervisorLineCheckItem.setEnd(5 * 10000 * 10000);

	if (StringUtils.isNotEmpty(custNumber)) {
	    supervisorLineCheckItem.setCustNumber(custNumber);
	}
	if (StringUtils.isNotEmpty(custName) && StringUtils.isNotEmpty(custName.trim())) {
	    supervisorLineCheckItem.setCustName(custName);
	}
	if (StringUtils.isNotEmpty(channelId)) {
	    supervisorLineCheckItem.setChannelId(channelId);
	}
	if (StringUtils.isNotEmpty(orgId)) {
	    // orgId
	    supervisorLineCheckItem.setOrgId(orgId);
	} else {
	    AllUsers user = this.getUser();
	    supervisorLineCheckItem.setOrgId(user.getOrgId());
	}
	if (StringUtils.isNotEmpty(custState)) {
	    supervisorLineCheckItem.setCustState(custState);
	}
	if (StringUtils.isNotEmpty(contactName) && StringUtils.isNotEmpty(contactName.trim())) {
	    supervisorLineCheckItem.setContactName(contactName);
	}
	if (StringUtils.isNotEmpty(stationUserId) && StringUtils.isNotEmpty(stationUserId.trim())) {
	    try {
		stationUserId = new String(stationUserId.getBytes("ISO8859-1"), "UTF-8");
	    } catch (UnsupportedEncodingException e) {

	    }
	    supervisorLineCheckItem.setStationUserId(stationUserId);
	}
	if (StringUtils.isNotEmpty(custKunnr) && StringUtils.isNotEmpty(custKunnr.trim())) {
	    try {
		custKunnr = new String(custKunnr.getBytes("ISO8859-1"), "UTF-8");
	    } catch (UnsupportedEncodingException e) {
	    }
	    supervisorLineCheckItem.setCustKunnr(custKunnr);
	}
	if (StringUtils.isNotEmpty(custParent) && StringUtils.isNotEmpty(custParent.trim())) {
	    supervisorLineCheckItem.setCustParent(custParent);
	}
	if (StringUtils.isNotEmpty(custType) && StringUtils.isNotEmpty(custType.trim())) {
	    supervisorLineCheckItem.setCustType(custType);
	}
	if (StringUtils.isNotEmpty(createOrgId)) {
	    supervisorLineCheckItem.setCreateOrgId(createOrgId);
	}
	if (StringUtils.isNotEmpty(createDateStart)) {
	    supervisorLineCheckItem.setCreateDateStart(createDateStart);
	}
	if (StringUtils.isNotEmpty(createDateEnd)) {
	    supervisorLineCheckItem.setCreateDateEnd(createDateEnd);
	}

	clist = masterService.getCustomerListCols(supervisorLineCheckItem);
	List2ExcelCols(clist);

	return RESULT_MESSAGE;
    }

    /*
     * 导出excel
     */
    @PermissionSearch
    private void List2ExcelCols(List<Customer> clist) {
	OutputStream os = null;
	WritableWorkbook wbook = null;
	try {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    // 取得输出流
	    os = response.getOutputStream();
	    // 清空输出流
	    response.reset();
	    // 设定输出文件头
	    String fileName = "督导标准导出列表.xls";
	    fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
	    response.setHeader("Content-disposition", "attachment; filename=" + fileName);
	    // 定义输出类型
	    response.setContentType("application/msexcel");
	    // 建立excel文件
	    wbook = Workbook.createWorkbook(os);
	    WritableSheet wsheet = wbook.createSheet("第一页", 0);
	    // 设置行高、列宽
	    wsheet.setRowView(0, 300);

	    wsheet.addCell(new Label(0, 0, "门店ID"));
	    wsheet.addCell(new Label(1, 0, "门店名称"));
	    wsheet.addCell(new Label(2, 0, "门店组织"));
	    wsheet.addCell(new Label(3, 0, "渠道"));
	    wsheet.addCell(new Label(4, 0, "创建人"));
	    wsheet.addCell(new Label(5, 0, "创建时间"));
	    //
	    // wsheet.addCell(new Label(6, 1,"铺货"));wsheet.addCell(new Label(7,
	    // 1,"价格最小值"));wsheet.addCell(new Label(8, 1,"价格最大值"));

	    WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
	    WritableCellFormat wcfF = new WritableCellFormat(wfc);
	    wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	    wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	    wcfF.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.HAIR);

	    // 获取sku口味列表
	    List<Materiel> mlist = masterService.getMasterCols(new Materiel());

	    List<String> l = new ArrayList<String>();
	    for (Materiel m : mlist) {
		// l.add(m.getMatg());
		l.add(m.getBezei40());
	    }

	    for (int i = 0; i < mlist.size(); i++) {
		wsheet.addCell(new Label(6 + i * 3, 0, mlist.get(i).getMatg()));
		wsheet.addCell(new Label(6 + i * 3 + 1, 0, "价格最小值"));
		wsheet.addCell(new Label(6 + i * 3 + 2, 0, "价格最大值"));
	    }

	    int clistLen = clist.size();
	    for (int i = 0; i < clistLen; i++) {
		List<SupervisorCheckItem> slist = new ArrayList<SupervisorCheckItem>();
		slist = masterService.getSupervisorItemsByCustId(clist.get(i).getCustId().toString());
		for (SupervisorCheckItem supervisorCheckItem : slist) {
		    String matName = supervisorCheckItem.getMatName();
		    int index = l.indexOf(matName);
		    wsheet.addCell(new Label(0, i + 1, supervisorCheckItem.getCustId()));
		    wsheet.addCell(new Label(1, i + 1, supervisorCheckItem.getCustName()));
		    wsheet.addCell(new Label(2, i + 1, supervisorCheckItem.getOrgName()));
		    wsheet.addCell(new Label(3, i + 1, supervisorCheckItem.getChannelName()));
		    wsheet.addCell(new Label(4, i + 1, supervisorCheckItem.getCreateName()));
		    wsheet.addCell(new Label(5, i + 1, supervisorCheckItem.getCreateTime()));

		    wsheet.addCell(new Label(6 + index * 3, i + 1, supervisorCheckItem.getCheckValue()));
		    wsheet.addCell(new Label(6 + index * 3 + 1, i + 1, supervisorCheckItem.getMinPrice()));
		    wsheet.addCell(new Label(6 + index * 3 + 2, i + 1, supervisorCheckItem.getMaxPrice()));

		}

	    }

	    // wsheet.addCell(new Label(0, i+1, itemList.get(i - 1).getCustId(),
	    // wcfF));
	    // wsheet.addCell(new Label(1, i+1, itemList.get(i -
	    // 1).getCustName(), wcfF));
	    // wsheet.addCell(new Label(2, i+1, itemList.get(i -
	    // 1).getOrgName(), wcfF));
	    // wsheet.addCell(new Label(3, i+1, itemList.get(i -
	    // 1).getChannelName(), wcfF));
	    // wsheet.addCell(new Label(4, i+1, itemList.get(i -
	    // 1).getCreateName(), wcfF));
	    // wsheet.addCell(new Label(5, i+1, itemList.get(i -
	    // 1).getCreateTime(), wcfF));
	    //
	    // wsheet.addCell(new Label(6, i+1, itemList.get(i -
	    // 1).getCheckValue1(), wcfF));wsheet.addCell(new Label(7, i+1,
	    // itemList.get(i - 1).getMinPrice1(), wcfF));wsheet.addCell(new
	    // Label(8, i+1, itemList.get(i - 1).getMaxPrice1(), wcfF));

	    wbook.write();
	} catch (Exception e) {
	    this.setFailMessage("督导标准导出列表失败");
	} finally {
	    if (wbook != null) {
		try {
		    wbook.close();
		} catch (Exception e) {
		    this.setFailMessage("导出资源未关闭");
		}
		wbook = null;
	    }
	    if (os != null) {
		try {
		    os.close();
		} catch (Exception e) {
		    this.setFailMessage("导出资源未关闭");
		}
		os = null;
	    }
	}

    }

    /*
     * 导出excel
     */
    @PermissionSearch
    private void List2Excel(List<SupervisorLineCheckItem> itemList) {
	OutputStream os = null;
	WritableWorkbook wbook = null;
	try {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    // 取得输出流
	    os = response.getOutputStream();
	    // 清空输出流
	    response.reset();
	    // 设定输出文件头
	    String fileName = "督导标准导出列表.xls";
	    fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
	    response.setHeader("Content-disposition", "attachment; filename=" + fileName);
	    // 定义输出类型
	    response.setContentType("application/msexcel");
	    // 建立excel文件
	    wbook = Workbook.createWorkbook(os);
	    WritableSheet wsheet = wbook.createSheet("第一页", 0);
	    // 设置行高、列宽
	    wsheet.setRowView(0, 300);
	    /*
	     * wsheet.setRowView(0, 300); wsheet.setRowView(1, 300);
	     * wsheet.setColumnView(0, 9); wsheet.setColumnView(1, 9);
	     * wsheet.setColumnView(2, 9); wsheet.setColumnView(3, 10); //
	     * wsheet.setColumnView(4, 9); // wsheet.setColumnView(5, 9);
	     * wsheet.setColumnView(6, 15); wsheet.setColumnView(7, 15);
	     * wsheet.setColumnView(8, 15); wsheet.setColumnView(9, 25);
	     * wsheet.setColumnView(10, 15); wsheet.setColumnView(11, 15);
	     * wsheet.setColumnView(12, 22); // wsheet.setColumnView(13, 15);
	     * wsheet.setColumnView(14, 15); wsheet.setColumnView(15, 15);
	     * wsheet.setColumnView(16, 15); wsheet.setColumnView(17, 15);
	     */

	    wsheet.addCell(new Label(6, 0, "椰果原味(单杯)"));
	    wsheet.addCell(new Label(9, 0, "椰果香芋(单杯)"));
	    wsheet.addCell(new Label(12, 0, "椰果草莓(单杯)"));
	    wsheet.addCell(new Label(15, 0, "椰果麦香(单杯)"));
	    wsheet.addCell(new Label(18, 0, "椰果咖啡(单杯)"));
	    wsheet.addCell(new Label(21, 0, "椰果巧克力(单杯)"));
	    wsheet.addCell(new Label(24, 0, "桂圆红枣(单杯)"));
	    wsheet.addCell(new Label(27, 0, "红豆(单杯)"));
	    wsheet.addCell(new Label(30, 0, "芝士燕麦(单杯)"));
	    wsheet.addCell(new Label(33, 0, "黑米椰浆(单杯)"));
	    wsheet.addCell(new Label(36, 0, "焦糖仙草(单杯)"));
	    wsheet.addCell(new Label(39, 0, "原汁红豆(单杯)"));
	    wsheet.addCell(new Label(42, 0, "芒果布丁(单杯)"));
	    wsheet.addCell(new Label(45, 0, "蓝莓(单杯)"));
	    wsheet.addCell(new Label(48, 0, "雪糕椰浆(单杯)"));
	    wsheet.addCell(new Label(51, 0, "原味(3连杯)"));
	    wsheet.addCell(new Label(54, 0, "香芋(3连杯)"));
	    wsheet.addCell(new Label(57, 0, "草莓(3连杯)"));
	    wsheet.addCell(new Label(60, 0, "麦香(3连杯)"));
	    wsheet.addCell(new Label(63, 0, "桂圆红枣(3连杯)"));
	    wsheet.addCell(new Label(66, 0, "红豆(3连杯)"));
	    wsheet.addCell(new Label(69, 0, "芝士燕麦(3连杯)"));
	    wsheet.addCell(new Label(72, 0, "黑米椰浆(3连杯)"));
	    wsheet.addCell(new Label(75, 0, "焦糖仙草(3连杯)"));
	    wsheet.addCell(new Label(78, 0, "芒果布丁(3连杯)"));
	    wsheet.addCell(new Label(81, 0, "蓝莓(3连杯)"));
	    wsheet.addCell(new Label(84, 0, "雪糕椰浆(3连杯)"));
	    wsheet.addCell(new Label(87, 0, "椰果16杯装(家庭分享装)"));
	    wsheet.addCell(new Label(90, 0, "椰果12杯装(家庭分享装)"));
	    wsheet.addCell(new Label(93, 0, "美味16杯装(家庭分享装)"));
	    wsheet.addCell(new Label(96, 0, "美味12杯装(家庭分享装)"));
	    wsheet.addCell(new Label(99, 0, "椰果12杯装(礼盒装)"));
	    wsheet.addCell(new Label(102, 0, "新品礼盒装(礼盒装)"));
	    wsheet.addCell(new Label(105, 0, "椰果经典(组合装)"));
	    wsheet.addCell(new Label(108, 0, "美味新品(组合装)"));
	    wsheet.addCell(new Label(111, 0, "家庭礼盒(家庭礼盒装)"));

	    wsheet.addCell(new Label(0, 1, "门店ID"));
	    wsheet.addCell(new Label(1, 1, "门店名称"));
	    wsheet.addCell(new Label(2, 1, "门店组织"));
	    wsheet.addCell(new Label(3, 1, "渠道"));
	    wsheet.addCell(new Label(4, 1, "创建人"));
	    wsheet.addCell(new Label(5, 1, "创建时间"));

	    wsheet.addCell(new Label(6, 1, "铺货"));
	    wsheet.addCell(new Label(7, 1, "价格最小值"));
	    wsheet.addCell(new Label(8, 1, "价格最大值"));
	    wsheet.addCell(new Label(9, 1, "铺货"));
	    wsheet.addCell(new Label(10, 1, "价格最小值"));
	    wsheet.addCell(new Label(11, 1, "价格最大值"));
	    wsheet.addCell(new Label(12, 1, "铺货"));
	    wsheet.addCell(new Label(13, 1, "价格最小值"));
	    wsheet.addCell(new Label(14, 1, "价格最大值"));
	    wsheet.addCell(new Label(15, 1, "铺货"));
	    wsheet.addCell(new Label(16, 1, "价格最小值"));
	    wsheet.addCell(new Label(17, 1, "价格最大值"));
	    wsheet.addCell(new Label(18, 1, "铺货"));
	    wsheet.addCell(new Label(19, 1, "价格最小值"));
	    wsheet.addCell(new Label(20, 1, "价格最大值"));
	    wsheet.addCell(new Label(21, 1, "铺货"));
	    wsheet.addCell(new Label(22, 1, "价格最小值"));
	    wsheet.addCell(new Label(23, 1, "价格最大值"));
	    wsheet.addCell(new Label(24, 1, "铺货"));
	    wsheet.addCell(new Label(25, 1, "价格最小值"));
	    wsheet.addCell(new Label(26, 1, "价格最大值"));
	    wsheet.addCell(new Label(27, 1, "铺货"));
	    wsheet.addCell(new Label(28, 1, "价格最小值"));
	    wsheet.addCell(new Label(29, 1, "价格最大值"));
	    wsheet.addCell(new Label(30, 1, "铺货"));
	    wsheet.addCell(new Label(31, 1, "价格最小值"));
	    wsheet.addCell(new Label(32, 1, "价格最大值"));
	    wsheet.addCell(new Label(33, 1, "铺货"));
	    wsheet.addCell(new Label(34, 1, "价格最小值"));
	    wsheet.addCell(new Label(35, 1, "价格最大值"));
	    wsheet.addCell(new Label(36, 1, "铺货"));
	    wsheet.addCell(new Label(37, 1, "价格最小值"));
	    wsheet.addCell(new Label(38, 1, "价格最大值"));
	    wsheet.addCell(new Label(39, 1, "铺货"));
	    wsheet.addCell(new Label(40, 1, "价格最小值"));
	    wsheet.addCell(new Label(41, 1, "价格最大值"));
	    wsheet.addCell(new Label(42, 1, "铺货"));
	    wsheet.addCell(new Label(43, 1, "价格最小值"));
	    wsheet.addCell(new Label(44, 1, "价格最大值"));
	    wsheet.addCell(new Label(45, 1, "铺货"));
	    wsheet.addCell(new Label(46, 1, "价格最小值"));
	    wsheet.addCell(new Label(47, 1, "价格最大值"));
	    wsheet.addCell(new Label(48, 1, "铺货"));
	    wsheet.addCell(new Label(49, 1, "价格最小值"));
	    wsheet.addCell(new Label(50, 1, "价格最大值"));
	    wsheet.addCell(new Label(51, 1, "铺货"));
	    wsheet.addCell(new Label(52, 1, "价格最小值"));
	    wsheet.addCell(new Label(53, 1, "价格最大值"));
	    wsheet.addCell(new Label(54, 1, "铺货"));
	    wsheet.addCell(new Label(55, 1, "价格最小值"));
	    wsheet.addCell(new Label(56, 1, "价格最大值"));
	    wsheet.addCell(new Label(57, 1, "铺货"));
	    wsheet.addCell(new Label(58, 1, "价格最小值"));
	    wsheet.addCell(new Label(59, 1, "价格最大值"));
	    wsheet.addCell(new Label(60, 1, "铺货"));
	    wsheet.addCell(new Label(61, 1, "价格最小值"));
	    wsheet.addCell(new Label(62, 1, "价格最大值"));
	    wsheet.addCell(new Label(63, 1, "铺货"));
	    wsheet.addCell(new Label(64, 1, "价格最小值"));
	    wsheet.addCell(new Label(65, 1, "价格最大值"));
	    wsheet.addCell(new Label(66, 1, "铺货"));
	    wsheet.addCell(new Label(67, 1, "价格最小值"));
	    wsheet.addCell(new Label(68, 1, "价格最大值"));
	    wsheet.addCell(new Label(69, 1, "铺货"));
	    wsheet.addCell(new Label(70, 1, "价格最小值"));
	    wsheet.addCell(new Label(71, 1, "价格最大值"));
	    wsheet.addCell(new Label(72, 1, "铺货"));
	    wsheet.addCell(new Label(73, 1, "价格最小值"));
	    wsheet.addCell(new Label(74, 1, "价格最大值"));
	    wsheet.addCell(new Label(75, 1, "铺货"));
	    wsheet.addCell(new Label(76, 1, "价格最小值"));
	    wsheet.addCell(new Label(77, 1, "价格最大值"));
	    wsheet.addCell(new Label(78, 1, "铺货"));
	    wsheet.addCell(new Label(79, 1, "价格最小值"));
	    wsheet.addCell(new Label(80, 1, "价格最大值"));
	    wsheet.addCell(new Label(81, 1, "铺货"));
	    wsheet.addCell(new Label(82, 1, "价格最小值"));
	    wsheet.addCell(new Label(83, 1, "价格最大值"));
	    wsheet.addCell(new Label(84, 1, "铺货"));
	    wsheet.addCell(new Label(85, 1, "价格最小值"));
	    wsheet.addCell(new Label(86, 1, "价格最大值"));
	    wsheet.addCell(new Label(87, 1, "铺货"));
	    wsheet.addCell(new Label(88, 1, "价格最小值"));
	    wsheet.addCell(new Label(89, 1, "价格最大值"));
	    wsheet.addCell(new Label(90, 1, "铺货"));
	    wsheet.addCell(new Label(91, 1, "价格最小值"));
	    wsheet.addCell(new Label(92, 1, "价格最大值"));
	    wsheet.addCell(new Label(93, 1, "铺货"));
	    wsheet.addCell(new Label(94, 1, "价格最小值"));
	    wsheet.addCell(new Label(95, 1, "价格最大值"));
	    wsheet.addCell(new Label(96, 1, "铺货"));
	    wsheet.addCell(new Label(97, 1, "价格最小值"));
	    wsheet.addCell(new Label(98, 1, "价格最大值"));
	    wsheet.addCell(new Label(99, 1, "铺货"));
	    wsheet.addCell(new Label(100, 1, "价格最小值"));
	    wsheet.addCell(new Label(101, 1, "价格最大值"));
	    wsheet.addCell(new Label(102, 1, "铺货"));
	    wsheet.addCell(new Label(103, 1, "价格最小值"));
	    wsheet.addCell(new Label(104, 1, "价格最大值"));
	    wsheet.addCell(new Label(105, 1, "铺货"));
	    wsheet.addCell(new Label(106, 1, "价格最小值"));
	    wsheet.addCell(new Label(107, 1, "价格最大值"));
	    wsheet.addCell(new Label(108, 1, "铺货"));
	    wsheet.addCell(new Label(109, 1, "价格最小值"));
	    wsheet.addCell(new Label(110, 1, "价格最大值"));
	    wsheet.addCell(new Label(111, 1, "铺货"));
	    wsheet.addCell(new Label(112, 1, "价格最小值"));
	    wsheet.addCell(new Label(113, 1, "价格最大值"));

	    WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, jxl.format.UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
	    WritableCellFormat wcfF = new WritableCellFormat(wfc);
	    wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	    wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	    wcfF.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.HAIR);

	    for (int i = 1; i <= itemList.size(); i++) {
		if (i >= 50 * 10000) {
		    break;
		}

		wsheet.addCell(new Label(0, i + 1, itemList.get(i - 1).getCustId(), wcfF));
		wsheet.addCell(new Label(1, i + 1, itemList.get(i - 1).getCustName(), wcfF));
		wsheet.addCell(new Label(2, i + 1, itemList.get(i - 1).getOrgName(), wcfF));
		wsheet.addCell(new Label(3, i + 1, itemList.get(i - 1).getChannelName(), wcfF));
		wsheet.addCell(new Label(4, i + 1, itemList.get(i - 1).getCreateName(), wcfF));
		wsheet.addCell(new Label(5, i + 1, itemList.get(i - 1).getCreateTime(), wcfF));

		wsheet.addCell(new Label(6, i + 1, itemList.get(i - 1).getCheckValue1(), wcfF));
		wsheet.addCell(new Label(7, i + 1, itemList.get(i - 1).getMinPrice1(), wcfF));
		wsheet.addCell(new Label(8, i + 1, itemList.get(i - 1).getMaxPrice1(), wcfF));
		wsheet.addCell(new Label(9, i + 1, itemList.get(i - 1).getCheckValue2(), wcfF));
		wsheet.addCell(new Label(10, i + 1, itemList.get(i - 1).getMinPrice2(), wcfF));
		wsheet.addCell(new Label(11, i + 1, itemList.get(i - 1).getMaxPrice2(), wcfF));
		wsheet.addCell(new Label(12, i + 1, itemList.get(i - 1).getCheckValue3(), wcfF));
		wsheet.addCell(new Label(13, i + 1, itemList.get(i - 1).getMinPrice3(), wcfF));
		wsheet.addCell(new Label(14, i + 1, itemList.get(i - 1).getMaxPrice3(), wcfF));
		wsheet.addCell(new Label(15, i + 1, itemList.get(i - 1).getCheckValue4(), wcfF));
		wsheet.addCell(new Label(16, i + 1, itemList.get(i - 1).getMinPrice4(), wcfF));
		wsheet.addCell(new Label(17, i + 1, itemList.get(i - 1).getMaxPrice4(), wcfF));
		wsheet.addCell(new Label(18, i + 1, itemList.get(i - 1).getCheckValue5(), wcfF));
		wsheet.addCell(new Label(19, i + 1, itemList.get(i - 1).getMinPrice5(), wcfF));
		wsheet.addCell(new Label(20, i + 1, itemList.get(i - 1).getMaxPrice5(), wcfF));
		wsheet.addCell(new Label(21, i + 1, itemList.get(i - 1).getCheckValue6(), wcfF));
		wsheet.addCell(new Label(22, i + 1, itemList.get(i - 1).getMinPrice6(), wcfF));
		wsheet.addCell(new Label(23, i + 1, itemList.get(i - 1).getMaxPrice6(), wcfF));
		wsheet.addCell(new Label(24, i + 1, itemList.get(i - 1).getCheckValue7(), wcfF));
		wsheet.addCell(new Label(25, i + 1, itemList.get(i - 1).getMinPrice7(), wcfF));
		wsheet.addCell(new Label(26, i + 1, itemList.get(i - 1).getMaxPrice7(), wcfF));
		wsheet.addCell(new Label(27, i + 1, itemList.get(i - 1).getCheckValue8(), wcfF));
		wsheet.addCell(new Label(28, i + 1, itemList.get(i - 1).getMinPrice8(), wcfF));
		wsheet.addCell(new Label(29, i + 1, itemList.get(i - 1).getMaxPrice8(), wcfF));
		wsheet.addCell(new Label(30, i + 1, itemList.get(i - 1).getCheckValue9(), wcfF));
		wsheet.addCell(new Label(31, i + 1, itemList.get(i - 1).getMinPrice9(), wcfF));
		wsheet.addCell(new Label(32, i + 1, itemList.get(i - 1).getMaxPrice9(), wcfF));
		wsheet.addCell(new Label(33, i + 1, itemList.get(i - 1).getCheckValue10(), wcfF));
		wsheet.addCell(new Label(34, i + 1, itemList.get(i - 1).getMinPrice10(), wcfF));
		wsheet.addCell(new Label(35, i + 1, itemList.get(i - 1).getMaxPrice10(), wcfF));
		wsheet.addCell(new Label(36, i + 1, itemList.get(i - 1).getCheckValue11(), wcfF));
		wsheet.addCell(new Label(37, i + 1, itemList.get(i - 1).getMinPrice11(), wcfF));
		wsheet.addCell(new Label(38, i + 1, itemList.get(i - 1).getMaxPrice11(), wcfF));
		wsheet.addCell(new Label(39, i + 1, itemList.get(i - 1).getCheckValue12(), wcfF));
		wsheet.addCell(new Label(40, i + 1, itemList.get(i - 1).getMinPrice12(), wcfF));
		wsheet.addCell(new Label(41, i + 1, itemList.get(i - 1).getMaxPrice12(), wcfF));
		wsheet.addCell(new Label(42, i + 1, itemList.get(i - 1).getCheckValue13(), wcfF));
		wsheet.addCell(new Label(43, i + 1, itemList.get(i - 1).getMinPrice13(), wcfF));
		wsheet.addCell(new Label(44, i + 1, itemList.get(i - 1).getMaxPrice13(), wcfF));
		wsheet.addCell(new Label(45, i + 1, itemList.get(i - 1).getCheckValue14(), wcfF));
		wsheet.addCell(new Label(46, i + 1, itemList.get(i - 1).getMinPrice14(), wcfF));
		wsheet.addCell(new Label(47, i + 1, itemList.get(i - 1).getMaxPrice14(), wcfF));
		wsheet.addCell(new Label(48, i + 1, itemList.get(i - 1).getCheckValue15(), wcfF));
		wsheet.addCell(new Label(49, i + 1, itemList.get(i - 1).getMinPrice15(), wcfF));
		wsheet.addCell(new Label(50, i + 1, itemList.get(i - 1).getMaxPrice15(), wcfF));
		wsheet.addCell(new Label(51, i + 1, itemList.get(i - 1).getCheckValue16(), wcfF));
		wsheet.addCell(new Label(52, i + 1, itemList.get(i - 1).getMinPrice16(), wcfF));
		wsheet.addCell(new Label(53, i + 1, itemList.get(i - 1).getMaxPrice16(), wcfF));
		wsheet.addCell(new Label(54, i + 1, itemList.get(i - 1).getCheckValue17(), wcfF));
		wsheet.addCell(new Label(55, i + 1, itemList.get(i - 1).getMinPrice17(), wcfF));
		wsheet.addCell(new Label(56, i + 1, itemList.get(i - 1).getMaxPrice17(), wcfF));
		wsheet.addCell(new Label(57, i + 1, itemList.get(i - 1).getCheckValue18(), wcfF));
		wsheet.addCell(new Label(58, i + 1, itemList.get(i - 1).getMinPrice18(), wcfF));
		wsheet.addCell(new Label(59, i + 1, itemList.get(i - 1).getMaxPrice18(), wcfF));
		wsheet.addCell(new Label(60, i + 1, itemList.get(i - 1).getCheckValue19(), wcfF));
		wsheet.addCell(new Label(61, i + 1, itemList.get(i - 1).getMinPrice19(), wcfF));
		wsheet.addCell(new Label(62, i + 1, itemList.get(i - 1).getMaxPrice19(), wcfF));
		wsheet.addCell(new Label(63, i + 1, itemList.get(i - 1).getCheckValue20(), wcfF));
		wsheet.addCell(new Label(64, i + 1, itemList.get(i - 1).getMinPrice20(), wcfF));
		wsheet.addCell(new Label(65, i + 1, itemList.get(i - 1).getMaxPrice20(), wcfF));
		wsheet.addCell(new Label(66, i + 1, itemList.get(i - 1).getCheckValue21(), wcfF));
		wsheet.addCell(new Label(67, i + 1, itemList.get(i - 1).getMinPrice21(), wcfF));
		wsheet.addCell(new Label(68, i + 1, itemList.get(i - 1).getMaxPrice21(), wcfF));
		wsheet.addCell(new Label(69, i + 1, itemList.get(i - 1).getCheckValue22(), wcfF));
		wsheet.addCell(new Label(70, i + 1, itemList.get(i - 1).getMinPrice22(), wcfF));
		wsheet.addCell(new Label(71, i + 1, itemList.get(i - 1).getMaxPrice22(), wcfF));
		wsheet.addCell(new Label(72, i + 1, itemList.get(i - 1).getCheckValue23(), wcfF));
		wsheet.addCell(new Label(73, i + 1, itemList.get(i - 1).getMinPrice23(), wcfF));
		wsheet.addCell(new Label(74, i + 1, itemList.get(i - 1).getMaxPrice23(), wcfF));
		wsheet.addCell(new Label(75, i + 1, itemList.get(i - 1).getCheckValue24(), wcfF));
		wsheet.addCell(new Label(76, i + 1, itemList.get(i - 1).getMinPrice24(), wcfF));
		wsheet.addCell(new Label(77, i + 1, itemList.get(i - 1).getMaxPrice24(), wcfF));
		wsheet.addCell(new Label(78, i + 1, itemList.get(i - 1).getCheckValue25(), wcfF));
		wsheet.addCell(new Label(79, i + 1, itemList.get(i - 1).getMinPrice25(), wcfF));
		wsheet.addCell(new Label(80, i + 1, itemList.get(i - 1).getMaxPrice25(), wcfF));
		wsheet.addCell(new Label(81, i + 1, itemList.get(i - 1).getCheckValue26(), wcfF));
		wsheet.addCell(new Label(82, i + 1, itemList.get(i - 1).getMinPrice26(), wcfF));
		wsheet.addCell(new Label(83, i + 1, itemList.get(i - 1).getMaxPrice26(), wcfF));
		wsheet.addCell(new Label(84, i + 1, itemList.get(i - 1).getCheckValue27(), wcfF));
		wsheet.addCell(new Label(85, i + 1, itemList.get(i - 1).getMinPrice27(), wcfF));
		wsheet.addCell(new Label(86, i + 1, itemList.get(i - 1).getMaxPrice27(), wcfF));
		wsheet.addCell(new Label(87, i + 1, itemList.get(i - 1).getCheckValue28(), wcfF));
		wsheet.addCell(new Label(88, i + 1, itemList.get(i - 1).getMinPrice28(), wcfF));
		wsheet.addCell(new Label(89, i + 1, itemList.get(i - 1).getMaxPrice28(), wcfF));
		wsheet.addCell(new Label(90, i + 1, itemList.get(i - 1).getCheckValue29(), wcfF));
		wsheet.addCell(new Label(91, i + 1, itemList.get(i - 1).getMinPrice29(), wcfF));
		wsheet.addCell(new Label(92, i + 1, itemList.get(i - 1).getMaxPrice29(), wcfF));
		wsheet.addCell(new Label(93, i + 1, itemList.get(i - 1).getCheckValue30(), wcfF));
		wsheet.addCell(new Label(94, i + 1, itemList.get(i - 1).getMinPrice30(), wcfF));
		wsheet.addCell(new Label(95, i + 1, itemList.get(i - 1).getMaxPrice30(), wcfF));
		wsheet.addCell(new Label(96, i + 1, itemList.get(i - 1).getCheckValue31(), wcfF));
		wsheet.addCell(new Label(97, i + 1, itemList.get(i - 1).getMinPrice31(), wcfF));
		wsheet.addCell(new Label(98, i + 1, itemList.get(i - 1).getMaxPrice31(), wcfF));
		wsheet.addCell(new Label(99, i + 1, itemList.get(i - 1).getCheckValue32(), wcfF));
		wsheet.addCell(new Label(100, i + 1, itemList.get(i - 1).getMinPrice32(), wcfF));
		wsheet.addCell(new Label(101, i + 1, itemList.get(i - 1).getMaxPrice32(), wcfF));
		wsheet.addCell(new Label(102, i + 1, itemList.get(i - 1).getCheckValue33(), wcfF));
		wsheet.addCell(new Label(103, i + 1, itemList.get(i - 1).getMinPrice33(), wcfF));
		wsheet.addCell(new Label(104, i + 1, itemList.get(i - 1).getMaxPrice33(), wcfF));
		wsheet.addCell(new Label(105, i + 1, itemList.get(i - 1).getCheckValue34(), wcfF));
		wsheet.addCell(new Label(106, i + 1, itemList.get(i - 1).getMinPrice34(), wcfF));
		wsheet.addCell(new Label(107, i + 1, itemList.get(i - 1).getMaxPrice34(), wcfF));
		wsheet.addCell(new Label(108, i + 1, itemList.get(i - 1).getCheckValue35(), wcfF));
		wsheet.addCell(new Label(109, i + 1, itemList.get(i - 1).getMinPrice35(), wcfF));
		wsheet.addCell(new Label(110, i + 1, itemList.get(i - 1).getMaxPrice35(), wcfF));
		wsheet.addCell(new Label(111, i + 1, itemList.get(i - 1).getCheckValue36(), wcfF));
		wsheet.addCell(new Label(112, i + 1, itemList.get(i - 1).getMinPrice36(), wcfF));
		wsheet.addCell(new Label(113, i + 1, itemList.get(i - 1).getMaxPrice36(), wcfF));

	    }
	    wbook.write();
	} catch (Exception e) {
	    this.setFailMessage("督导标准导出列表失败");
	} finally {
	    if (wbook != null) {
		try {
		    wbook.close();
		} catch (Exception e) {
		    this.setFailMessage("导出资源未关闭");
		}
		wbook = null;
	    }
	    if (os != null) {
		try {
		    os.close();
		} catch (Exception e) {
		    this.setFailMessage("导出资源未关闭");
		}
		os = null;
	    }
	}

    }

    /**
     * csv 导入督导考核数据 ing
     * 
     * @return
     * @throws IOException
     */

    @PermissionSearch
    public String ImportItems() throws IOException {

	// BufferedWriter writer = new BufferedWriter(new FileWriter(new
	// File("f:\\0.txt")));
	//
	// // writer.write("\"101\",\"英语\",\"english\",\"100001\"\r\n");
	// //
	// // writer.close();
	// writer.write( "进入导入action"+String.valueOf(System.currentTimeMillis()
	// )+"\r\n");

	StringBuffer result = new StringBuffer();
	if (StringUtils.isNotEmpty(uploadFileFileName)) {
	    String end = StringUtils.substring(uploadFileFileName, StringUtils.lastIndexOf(uploadFileFileName, '.'));
	    if ((end != null && (end.equals(".csv"))) || (end != null && (end.equals(".CSV")))) {

		List<SupervisorLineCheckItem> listSupervisorLineCheckItem = new ArrayList<SupervisorLineCheckItem>();
		String[] header;
		try {
		    header = SuperCSV.getHeaderFromFile(new File(uploadFile.toString()));
		    if (header[2].equals("椰果原味(单杯)") && header[5].equals("椰果香芋(单杯)")) {
			// 去除头部，直接拿数据
			List<String[]> content = SuperCSV.getContentFromFile(new File(uploadFile.toString())); // 获取String数组

			// masterService.clearItems();

			// ing ................................

			SupervisorLineCheckItem supervisorLineCheckItem;

			// 创建人 创建时间
			String createName = this.getUser().getUserName();
			String createId = this.getUser().getUserId();
			Calendar ca = Calendar.getInstance();
			String createDate = ca.get(Calendar.YEAR) + "-" + (ca.get(Calendar.MONTH) + 1) + "-" + ca.get(Calendar.DATE);

			if (content.size() > 0) {
			    for (int j = 1; j < content.size(); j++) {

				// Long starttime = System.currentTimeMillis();
				// Long linedbstarttime = (long) 0;

				List<SupervisorLineCheckItem> batchGetlines = null;

				String[] s = content.get(j); // 获取每一行
				supervisorLineCheckItem = new SupervisorLineCheckItem();

				supervisorLineCheckItem.setCreateName(createName);
				supervisorLineCheckItem.setCreateUserId(createId);
				supervisorLineCheckItem.setCreateTime(createDate);
				Customer cust = null;

				if (StringUtils.isNotEmpty(s[0]) && StringUtils.isNotEmpty(s[1])) {
				    // 逻辑验证

				    cust = this.masterService.validateCustId(s[0].trim());

				    SupervisorLineCheckItem line1 = new SupervisorLineCheckItem();
				    line1.setCustId(s[0]);
				    line1.setStart(0);
				    line1.setEnd(40 * 36);
				    batchGetlines = this.masterService.getSupervisorItems(line1);

				    if (null != cust) {
					if (null != batchGetlines && batchGetlines.size() > 0) {
					    batchGetlines = this.packline(batchGetlines, s);
					}

					// 验证通过
				    } else
					result.append((j + 2) + "行，请检查门店ID是否有效！ ");
				}

				// 通过Y/N 判定是否 需要检查
				String checkCols = "";
				for (int l = 0; l < 36; l++) {
				    if (StringUtils.isNotEmpty(s[l * 3 + 2]) && (s[l * 3 + 2].equals("Y") || s[l * 3 + 2].equals("N"))) {

				    } else
					checkCols = checkCols + (l * 3 + 2) + " 、";
				}
				if (checkCols.trim().length() > 0)
				    result.append((j + 2) + "行" + checkCols + " 列，品项数据不合规范，请填写Y 或者 N！ ");

				// 检验价格
				String checkPrice = "";
				for (int l = 0; l < 36; l++) {
				    if (StringUtils.isNotEmpty(s[l * 3 + 3]) && StringUtils.isNotEmpty(s[l * 3 + 4])) {

				    } else
					checkPrice = checkPrice + (l * 4 + 4) + " " + (l * 4 + 5) + "、";
				}
				if (checkPrice.trim().length() > 0)
				    // result.append((j + 1) + "行" + checkPrice
				    // + " 列价格，不能为空！ ");

				    if (result.toString().length() > 0) {
					this.setFailMessage(result.toString());
				    } else {
					// 单行验证通过
					if (null != batchGetlines && batchGetlines.size() > 0) {
					    masterService.saveChagCheckItem(batchGetlines);
					} else {
					    for (int l = 0; l < 36; l++) {

						SupervisorCheckItem supervisorCheckItem = new SupervisorCheckItem();
						supervisorCheckItem.setCustId(String.valueOf(cust.getCustId()));
						supervisorCheckItem.setCustName(cust.getCustName());

						supervisorCheckItem.setChannelId(String.valueOf(cust.getChannelId()));
						supervisorCheckItem.setChannelName(cust.getChannelName());

						supervisorCheckItem.setCheckValue(s[l * 3 + 2]); // Y
												 // N
						supervisorCheckItem.setCreateUserId(createId);
						supervisorCheckItem.setCreateName(createName);
						supervisorCheckItem.setCreateTime(createDate);
						// supervisorCheckItem
						// .setMatType(s[l * 3 + 3]); //
						// 口味类别
						supervisorCheckItem.setMinPrice(s[l * 3 + 3]);
						supervisorCheckItem.setMaxPrice(s[l * 3 + 4]);

						switch (l) {
						case 0:
						    supervisorCheckItem.setMatName("椰果原味(单杯)");
						    break;
						case 1:
						    supervisorCheckItem.setMatName("椰果香芋(单杯)");
						    break;
						case 2:
						    supervisorCheckItem.setMatName("椰果草莓(单杯)");
						    break;
						case 3:
						    supervisorCheckItem.setMatName("椰果麦香(单杯)");
						    break;
						case 4:
						    supervisorCheckItem.setMatName("椰果咖啡(单杯)");
						    break;
						case 5:
						    supervisorCheckItem.setMatName("椰果巧克力(单杯)");
						    break;
						case 6:
						    supervisorCheckItem.setMatName("桂圆红枣(单杯)");
						    break;
						case 7:
						    supervisorCheckItem.setMatName("红豆(单杯)");
						    break;
						case 8:
						    supervisorCheckItem.setMatName("芝士燕麦(单杯)");
						    break;
						case 9:
						    supervisorCheckItem.setMatName("黑米椰浆(单杯)");
						    break;
						case 10:
						    supervisorCheckItem.setMatName("焦糖仙草(单杯)");
						    break;
						case 11:
						    supervisorCheckItem.setMatName("原汁红豆(单杯)");
						    break;
						case 12:
						    supervisorCheckItem.setMatName("芒果布丁(单杯)");
						    break;
						case 13:
						    supervisorCheckItem.setMatName("蓝莓(单杯)");
						    break;
						case 14:
						    supervisorCheckItem.setMatName("雪糕椰浆(单杯)");
						    break;
						case 15:
						    supervisorCheckItem.setMatName("原味(3连杯)");
						    break;
						case 16:
						    supervisorCheckItem.setMatName("香芋(3连杯)");
						    break;
						case 17:
						    supervisorCheckItem.setMatName("草莓(3连杯)");
						    break;
						case 18:
						    supervisorCheckItem.setMatName("麦香(3连杯)");
						    break;
						case 19:
						    supervisorCheckItem.setMatName("桂圆红枣(3连杯)");
						    break;
						case 20:
						    supervisorCheckItem.setMatName("红豆(3连杯)");
						    break;
						case 21:
						    supervisorCheckItem.setMatName("芝士燕麦(3连杯)");
						    break;
						case 22:
						    supervisorCheckItem.setMatName("黑米椰浆(3连杯)");
						    break;
						case 23:
						    supervisorCheckItem.setMatName("焦糖仙草(3连杯)");
						    break;
						case 24:
						    supervisorCheckItem.setMatName("芒果布丁(3连杯)");
						    break;
						case 25:
						    supervisorCheckItem.setMatName("蓝莓(3连杯)");
						    break;
						case 26:
						    supervisorCheckItem.setMatName("雪糕椰浆(3连杯)");
						    break;
						case 27:
						    supervisorCheckItem.setMatName("椰果16杯装(家庭分享装)");
						    break;
						case 28:
						    supervisorCheckItem.setMatName("椰果12杯装(家庭分享装)");
						    break;
						case 29:
						    supervisorCheckItem.setMatName("美味16杯装(家庭分享装)");
						    break;
						case 30:
						    supervisorCheckItem.setMatName("美味12杯装(家庭分享装)");
						    break;
						case 31:
						    supervisorCheckItem.setMatName("椰果12杯装(礼盒装)");
						    break;
						case 32:
						    supervisorCheckItem.setMatName("新品礼盒装(礼盒装)");
						    break;
						case 33:
						    supervisorCheckItem.setMatName("椰果经典(组合装)");
						    break;
						case 34:
						    supervisorCheckItem.setMatName("美味新品(组合装)");
						    break;
						case 35:
						    supervisorCheckItem.setMatName("家庭礼盒(家庭礼盒装)");
						    break;

						}

						// Long saveonestart
						// =System.currentTimeMillis();
						StringResult sr = this.masterService.saveSupervisorCheckItem(supervisorCheckItem);
						// linedbstarttime +=
						// System.currentTimeMillis()-saveonestart;

					    }
					}

				    }

				// 单行提示
				if (result.toString().length() != 0) {
				    this.setFailMessage(result.toString());
				    return RESULT_MESSAGE;
				}

				// Long endtime = System.currentTimeMillis();
				// writer.write(
				// "保存行数据时间:"+String.valueOf(endtime-starttime
				// )+"其中数据库保存行时间："+linedbstarttime+"\r\n");

			    }
			} else {
			    this.setSuccessMessage("督导考核项目导入数据为空，请重新导入 ");
			}
		    } else {
			this.setFailMessage("导入模板错误");
			return RESULT_MESSAGE;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}

	// writer.close();

	if (result.toString().length() == 0) {
	    this.setSuccessMessage("督导考核项目导入成功");
	} else
	    this.setFailMessage(result.toString());

	return RESULT_MESSAGE;

    }

    /**
     * csv 导入督导考核数据 cols
     * 
     * @return
     * @throws IOException
     */

    @PermissionSearch
    public String ImportItemsCols() throws IOException {
         

	materielList = masterService.getMasterCols(new Materiel());

	StringBuffer result = new StringBuffer();
	if (StringUtils.isNotEmpty(uploadFileFileName)) {
	    String end = StringUtils.substring(uploadFileFileName, StringUtils.lastIndexOf(uploadFileFileName, '.'));
	    if ((end != null && (end.equals(".csv"))) || (end != null && (end.equals(".CSV")))) {

		List<SupervisorLineCheckItem> listSupervisorLineCheckItem = new ArrayList<SupervisorLineCheckItem>();
		String[] header;
		header = SuperCSV.getHeaderFromFile(new File(uploadFile.toString()));
		try {
		    List<String[]> content = SuperCSV.getContentFromFile(new File(uploadFile.toString())); // 获取String数组
		    String[] ss = content.get(0);
		    if (ss[0].equals("门店ID") && ss[1].equals("门店")) {

			SupervisorLineCheckItem supervisorLineCheckItem;

			// 创建人 创建时间
			String createName = this.getUser().getUserName();
			String createId = this.getUser().getUserId();
			Calendar ca = Calendar.getInstance();
			String createDate = ca.get(Calendar.YEAR) + "-" + (ca.get(Calendar.MONTH) + 1) + "-" + ca.get(Calendar.DATE);

			if (content.size() > 0) {
			    for (int j = 1; j < content.size(); j++) {
				
				String[] s = content.get(j); // 获取每一行
							     // ，判断每一行的列是否与模板相同
				
				
				if (s.length == 3 * materielList.size() + 2) {

				    System.out.println("Y1" + new Date());
				    Customer cust = null;

				    if (StringUtils.isNotEmpty(s[0]) && StringUtils.isNotEmpty(s[1])) {
					// 逻辑验证

					cust = this.masterService.validateCustId(s[0].trim());

					if (null != cust) {
					    // 验证通过
					    int custExistflag = masterService.validateCustIdExist(s[0].trim());
					    if (custExistflag > 0) {
						masterService.delSupervisorCheckItemByCustId(s[0].trim());
					    }

					} else
					    result.append((j + 2) + "行，请检查门店ID是否有效！ ");
				    }

				    // 通过Y/N 判定是否 需要检查
				    
				    System.out.println( "" );
				    String checkCols = "";
				    for (int l = 0; l < materielList.size(); l++) {
					if (StringUtils.isNotEmpty(s[l * 3 + 2]) && (s[l * 3 + 2].equals("Y") || s[l * 3 + 2].equals("N"))) {

					} else
					    checkCols = checkCols + (l * 3 + 2) + " 、";
				    }
				    if (checkCols.trim().length() > 0)
					result.append((j + 2) + "行" + checkCols + " 列，品项数据不合规范，请填写Y 或者 N！ ");

				    System.out.println("Y2" + new Date());
				    if (result.toString().length() > 0) {
					this.setFailMessage(result.toString());
				    } else {
					// 单行验证通过
					// if (null!=batchGetlines &&
					// batchGetlines.size()>0){
					// //
					// masterService.saveChagCheckItem(batchGetlines);
					// }else{
					for (int l = 0; l < materielList.size(); l++) {
					    System.out.println("10"+ new Date());
					    SupervisorCheckItem supervisorCheckItem = new SupervisorCheckItem();
					    supervisorCheckItem.setCustId(String.valueOf(cust.getCustId()));
					    supervisorCheckItem.setCustName(cust.getCustName());

					    supervisorCheckItem.setChannelId(String.valueOf(cust.getChannelId()));
					    supervisorCheckItem.setChannelName(cust.getChannelName());

					    supervisorCheckItem.setCheckValue(s[l * 3 + 2]); // Y
					    // N
					    supervisorCheckItem.setCreateUserId(createId);
					    supervisorCheckItem.setCreateName(createName);
					    supervisorCheckItem.setCreateTime(createDate);
					    // supervisorCheckItem
					    // .setMatType(s[l * 3 + 3]); //
					    // 口味类别
					    supervisorCheckItem.setMinPrice(s[l * 3 + 3]);
					    supervisorCheckItem.setMaxPrice(s[l * 3 + 4]);

					    // supervisorCheckItem.setMatName(header[l*3+2]);
					    supervisorCheckItem.setMatName(header[l * 3 + 3]);

					    // Long saveonestart
					    // =System.currentTimeMillis();
					    
					    System.out.println("11"+ new Date());
					    @SuppressWarnings("unused")
					    StringResult sr = this.masterService.saveSupervisorCheckItem(supervisorCheckItem);
					    System.out.println("12" + new Date());
					    System.out.println("save one ");
					    // linedbstarttime +=
					    // System.currentTimeMillis()-saveonestart;

					}
					System.out.println("s1" + new Date());
				    }

				} else {
				    this.setFailMessage((j + 2) + "行，不能有空列，请填写完整");
				}

				// 单行提示
				if (result.toString().length() != 0) {
				    this.setFailMessage(result.toString());
				    return RESULT_MESSAGE;
				}

				// Long endtime = System.currentTimeMillis();
				// writer.write(
				// "保存行数据时间:"+String.valueOf(endtime-starttime
				// )+"其中数据库保存行时间："+linedbstarttime+"\r\n");

			    }
			} else {
			    this.setSuccessMessage("督导考核项目导入数据为空，请重新导入 ");
			}
		    } else {
			this.setFailMessage("导入模板错误");
			return RESULT_MESSAGE;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}

	// writer.close();

	if (result.toString().length() == 0) {
	    this.setSuccessMessage("督导考核项目导入成功");
	} else
	    this.setFailMessage(result.toString());

	return RESULT_MESSAGE;

    }
    
    /**
     * csv 导入督导考核数据 cols
     * 1. 导入顺序变化
     * 2. 导入字段增加（可不关注，custid enough）
     */
    
    /**
     * 
     * 模板需求变化 2015-11-16 by zpf
     * @throws IOException
     */

    @PermissionSearch
    public String ImportItemsCols2() throws IOException {
         

	materielList = masterService.getMasterCols(new Materiel());

	StringBuffer result = new StringBuffer();
	if (StringUtils.isNotEmpty(uploadFileFileName)) {
	    String end = StringUtils.substring(uploadFileFileName, StringUtils.lastIndexOf(uploadFileFileName, '.'));
	    if ((end != null && (end.equals(".csv"))) || (end != null && (end.equals(".CSV")))) {

		List<SupervisorLineCheckItem> listSupervisorLineCheckItem = new ArrayList<SupervisorLineCheckItem>();
		String[] header;
		header = SuperCSV.getHeaderFromFile(new File(uploadFile.toString()));
		try {
		    List<String[]> content = SuperCSV.getContentFromFile(new File(uploadFile.toString())); // 获取String数组
		    String[] ss = content.get(0);
		    if (ss[0].equals("门店ID") && ss[1].equals("门店")) {

			SupervisorLineCheckItem supervisorLineCheckItem;

			// 创建人 创建时间
			String createName = this.getUser().getUserName();
			String createId = this.getUser().getUserId();
			Calendar ca = Calendar.getInstance();
			String createDate = ca.get(Calendar.YEAR) + "-" + (ca.get(Calendar.MONTH) + 1) + "-" + ca.get(Calendar.DATE);

			if (content.size() > 0) {
			    for (int j = 1; j < content.size(); j++) {
				
				String[] s = content.get(j); // 获取每一行
							     // ，判断每一行的列是否与模板相同
//				if (s.length == 3 * materielList.size() + 5) {
				if (s.length ==   materielList.size() + 5) {
				    Customer cust = null;
				    if (StringUtils.isNotEmpty(s[0]) && StringUtils.isNotEmpty(s[1])) {
					// 逻辑验证
					cust = this.masterService.validateCustId(s[0].trim());
					if (null != cust) {
					    // 验证通过
					    int custExistflag = masterService.validateCustIdExist(s[0].trim());
					    if (custExistflag > 0) {
						masterService.delSupervisorCheckItemByCustId(s[0].trim());
					    }

					} else
					    result.append((j + 2) + "行，请检查门店ID是否有效！ ");
				    }

				    // 通过Y/N 判定是否 需要检查
				    
				    String checkCols = "";
				    for (int l = 0; l < materielList.size(); l++) {
//					if (StringUtils.isNotEmpty(s[l * 3 + 5]) && (s[l * 3 +5 ].equals("Y") || s[l * 3 + 5].equals("N"))) {
					if (StringUtils.isNotEmpty(s[l + 5]) && (s[l +5 ].equals("Y") || s[l + 5].equals("N"))) {
					} else
//					    checkCols = checkCols + (l * 3 + 5) + " 、";
					    checkCols = checkCols + (l + 5) + " 、";
				    }
				    if (checkCols.trim().length() > 0)
					result.append((j + 5) + "行" + checkCols + " 列，品项数据不合规范，请填写Y 或者 N！ ");

				    if (result.toString().length() > 0) {
					this.setFailMessage(result.toString());
				    } else {
				
					for (int l = 0; l < materielList.size(); l++) {
					    SupervisorCheckItem supervisorCheckItem = new SupervisorCheckItem();
					    supervisorCheckItem.setCustId(String.valueOf(cust.getCustId()));
					    supervisorCheckItem.setCustName(cust.getCustName());

					    supervisorCheckItem.setChannelId(String.valueOf(cust.getChannelId()));
					    supervisorCheckItem.setChannelName(cust.getChannelName());
					    
					    

//					    supervisorCheckItem.setCheckValue(s[l * 3 + 5]); // Y
					    supervisorCheckItem.setCheckValue(s[l + 5]); // Y
					    // N
					    supervisorCheckItem.setCreateUserId(createId);
					    supervisorCheckItem.setCreateName(createName);
					    supervisorCheckItem.setCreateTime(createDate);
					    // supervisorCheckItem
					    // .setMatType(s[l * 3 + 3]); //
					    // 口味类别
//					    supervisorCheckItem.setMinPrice(s[l * 3 + 3]);
//					    supervisorCheckItem.setMaxPrice(s[l * 3 + 4]);

//					    supervisorCheckItem.setMatName(header[l * 3 + 6]);
					    
					    String sku_id_name = header[l + 5];
					    String sku_id = sku_id_name.substring(0, 3);
					    supervisorCheckItem.setMatName(sku_id);

					    @SuppressWarnings("unused")
					    StringResult sr = this.masterService.saveSupervisorCheckItem(supervisorCheckItem);

					}
				    }

				} else {
				    this.setFailMessage((j + 2) + "行，不能有空列，请填写完整");
				}

				// 单行提示
				if (result.toString().length() != 0) {
				    this.setFailMessage(result.toString());
				    return RESULT_MESSAGE;
				}

			    }
			} else {
			    this.setSuccessMessage("督导考核项目导入数据为空，请重新导入 ");
			}
		    } else {
			this.setFailMessage("导入模板错误");
			return RESULT_MESSAGE;
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}

	// writer.close();

	if (result.toString().length() == 0) {
	    this.setSuccessMessage("督导考核项目导入成功");
	} else
	    this.setFailMessage(result.toString());

	return RESULT_MESSAGE;

    }

    private List<SupervisorLineCheckItem> packline(List<SupervisorLineCheckItem> batchGetlines, String[] s) {

	if (null != batchGetlines && s.length == 110) {

	    batchGetlines.get(0).setCheckValue1(s[2]);
	    batchGetlines.get(0).setMinPrice1(s[3]);
	    batchGetlines.get(0).setMaxPrice1(s[4]);
	    batchGetlines.get(0).setCheckValue2(s[5]);
	    batchGetlines.get(0).setMinPrice2(s[6]);
	    batchGetlines.get(0).setMaxPrice2(s[7]);
	    batchGetlines.get(0).setCheckValue3(s[8]);
	    batchGetlines.get(0).setMinPrice3(s[9]);
	    batchGetlines.get(0).setMaxPrice3(s[10]);
	    batchGetlines.get(0).setCheckValue4(s[11]);
	    batchGetlines.get(0).setMinPrice4(s[12]);
	    batchGetlines.get(0).setMaxPrice4(s[13]);
	    batchGetlines.get(0).setCheckValue5(s[14]);
	    batchGetlines.get(0).setMinPrice5(s[15]);
	    batchGetlines.get(0).setMaxPrice5(s[16]);
	    batchGetlines.get(0).setCheckValue6(s[17]);
	    batchGetlines.get(0).setMinPrice6(s[18]);
	    batchGetlines.get(0).setMaxPrice6(s[19]);
	    batchGetlines.get(0).setCheckValue7(s[20]);
	    batchGetlines.get(0).setMinPrice7(s[21]);
	    batchGetlines.get(0).setMaxPrice7(s[22]);
	    batchGetlines.get(0).setCheckValue8(s[23]);
	    batchGetlines.get(0).setMinPrice8(s[24]);
	    batchGetlines.get(0).setMaxPrice8(s[25]);
	    batchGetlines.get(0).setCheckValue9(s[26]);
	    batchGetlines.get(0).setMinPrice9(s[27]);
	    batchGetlines.get(0).setMaxPrice9(s[28]);
	    batchGetlines.get(0).setCheckValue10(s[29]);
	    batchGetlines.get(0).setMinPrice10(s[30]);
	    batchGetlines.get(0).setMaxPrice10(s[31]);
	    batchGetlines.get(0).setCheckValue11(s[32]);
	    batchGetlines.get(0).setMinPrice11(s[33]);
	    batchGetlines.get(0).setMaxPrice11(s[34]);
	    batchGetlines.get(0).setCheckValue12(s[35]);
	    batchGetlines.get(0).setMinPrice12(s[36]);
	    batchGetlines.get(0).setMaxPrice12(s[37]);
	    batchGetlines.get(0).setCheckValue13(s[38]);
	    batchGetlines.get(0).setMinPrice13(s[39]);
	    batchGetlines.get(0).setMaxPrice13(s[40]);
	    batchGetlines.get(0).setCheckValue14(s[41]);
	    batchGetlines.get(0).setMinPrice14(s[42]);
	    batchGetlines.get(0).setMaxPrice14(s[43]);
	    batchGetlines.get(0).setCheckValue15(s[44]);
	    batchGetlines.get(0).setMinPrice15(s[45]);
	    batchGetlines.get(0).setMaxPrice15(s[46]);
	    batchGetlines.get(0).setCheckValue16(s[47]);
	    batchGetlines.get(0).setMinPrice16(s[48]);
	    batchGetlines.get(0).setMaxPrice16(s[49]);
	    batchGetlines.get(0).setCheckValue17(s[50]);
	    batchGetlines.get(0).setMinPrice17(s[51]);
	    batchGetlines.get(0).setMaxPrice17(s[52]);
	    batchGetlines.get(0).setCheckValue18(s[53]);
	    batchGetlines.get(0).setMinPrice18(s[54]);
	    batchGetlines.get(0).setMaxPrice18(s[55]);
	    batchGetlines.get(0).setCheckValue19(s[56]);
	    batchGetlines.get(0).setMinPrice19(s[57]);
	    batchGetlines.get(0).setMaxPrice19(s[58]);
	    batchGetlines.get(0).setCheckValue20(s[59]);
	    batchGetlines.get(0).setMinPrice20(s[60]);
	    batchGetlines.get(0).setMaxPrice20(s[61]);
	    batchGetlines.get(0).setCheckValue21(s[62]);
	    batchGetlines.get(0).setMinPrice21(s[63]);
	    batchGetlines.get(0).setMaxPrice21(s[64]);
	    batchGetlines.get(0).setCheckValue22(s[65]);
	    batchGetlines.get(0).setMinPrice22(s[66]);
	    batchGetlines.get(0).setMaxPrice22(s[67]);
	    batchGetlines.get(0).setCheckValue23(s[68]);
	    batchGetlines.get(0).setMinPrice23(s[69]);
	    batchGetlines.get(0).setMaxPrice23(s[70]);
	    batchGetlines.get(0).setCheckValue24(s[71]);
	    batchGetlines.get(0).setMinPrice24(s[72]);
	    batchGetlines.get(0).setMaxPrice24(s[73]);
	    batchGetlines.get(0).setCheckValue25(s[74]);
	    batchGetlines.get(0).setMinPrice25(s[75]);
	    batchGetlines.get(0).setMaxPrice25(s[76]);
	    batchGetlines.get(0).setCheckValue26(s[77]);
	    batchGetlines.get(0).setMinPrice26(s[78]);
	    batchGetlines.get(0).setMaxPrice26(s[79]);
	    batchGetlines.get(0).setCheckValue27(s[80]);
	    batchGetlines.get(0).setMinPrice27(s[81]);
	    batchGetlines.get(0).setMaxPrice27(s[82]);
	    batchGetlines.get(0).setCheckValue28(s[83]);
	    batchGetlines.get(0).setMinPrice28(s[84]);
	    batchGetlines.get(0).setMaxPrice28(s[85]);
	    batchGetlines.get(0).setCheckValue29(s[86]);
	    batchGetlines.get(0).setMinPrice29(s[87]);
	    batchGetlines.get(0).setMaxPrice29(s[88]);
	    batchGetlines.get(0).setCheckValue30(s[89]);
	    batchGetlines.get(0).setMinPrice30(s[90]);
	    batchGetlines.get(0).setMaxPrice30(s[91]);
	    batchGetlines.get(0).setCheckValue31(s[92]);
	    batchGetlines.get(0).setMinPrice31(s[93]);
	    batchGetlines.get(0).setMaxPrice31(s[94]);
	    batchGetlines.get(0).setCheckValue32(s[95]);
	    batchGetlines.get(0).setMinPrice32(s[96]);
	    batchGetlines.get(0).setMaxPrice32(s[97]);
	    batchGetlines.get(0).setCheckValue33(s[98]);
	    batchGetlines.get(0).setMinPrice33(s[99]);
	    batchGetlines.get(0).setMaxPrice33(s[100]);
	    batchGetlines.get(0).setCheckValue34(s[101]);
	    batchGetlines.get(0).setMinPrice34(s[102]);
	    batchGetlines.get(0).setMaxPrice34(s[103]);
	    batchGetlines.get(0).setCheckValue35(s[104]);
	    batchGetlines.get(0).setMinPrice35(s[105]);
	    batchGetlines.get(0).setMaxPrice35(s[106]);
	    batchGetlines.get(0).setCheckValue36(s[107]);
	    batchGetlines.get(0).setMinPrice36(s[108]);
	    batchGetlines.get(0).setMaxPrice36(s[109]);

	    return batchGetlines;
	} else
	    return null;
    }

    /**
     * 导出模板 带条件
     * 
     */

    @PermissionSearch
    public String exportMouldCsvWithCons() {

	OutputStream os = null;
	String report_name = "督导流通特通考核导入模板";
	PrintWriter print = null;
	try {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    os = response.getOutputStream();
	    response.reset();
	    response.setContentType("application/csv;charset=gb18030");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(report_name.getBytes("GBK"), ("ISO8859-1")) + ".csv\"");
	    print = response.getWriter();
	    StringBuffer linedata = new StringBuffer();
	    // linedata.append("说明：当前SKU标准，需要通过不同类别来标识，如果是同一类别请用同一数字标注");
	    // linedata.append("\n");

	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");

	    linedata.append("椰果原味(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果香芋(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果草莓(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果麦香(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果咖啡(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果巧克力(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("桂圆红枣(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("红豆(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("芝士燕麦(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("黑米椰浆(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("焦糖仙草(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("原汁红豆(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("芒果布丁(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("蓝莓(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("雪糕椰浆(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("原味(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("香芋(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("草莓(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("麦香(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("桂圆红枣(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("红豆(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("芝士燕麦(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("黑米椰浆(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("焦糖仙草(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("芒果布丁(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("蓝莓(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("雪糕椰浆(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果16杯装(家庭分享装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果12杯装(家庭分享装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("美味16杯装(家庭分享装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("美味12杯装(家庭分享装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果12杯装(礼盒装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("新品礼盒装(礼盒装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果经典(组合装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("美味新品(组合装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("家庭礼盒(家庭礼盒装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");

	    linedata.append("\n");

	    linedata.append("门店ID");
	    linedata.append(",");
	    linedata.append("门店");
	    linedata.append(",");

	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");

	    linedata.append("\n");

	    supervisorLineCheckItemList = new ArrayList<SupervisorLineCheckItem>();
	    SupervisorLineCheckItem supervisorLineCheckItem = new SupervisorLineCheckItem();
	    supervisorLineCheckItem.setStart(0);
	    supervisorLineCheckItem.setEnd(50 * 10000);

	    if (StringUtils.isNotEmpty(custNumber)) {
		supervisorLineCheckItem.setCustNumber(custNumber);
	    }
	    if (StringUtils.isNotEmpty(custName) && StringUtils.isNotEmpty(custName.trim())) {
		supervisorLineCheckItem.setCustName(custName);
	    }
	    if (StringUtils.isNotEmpty(channelId)) {
		supervisorLineCheckItem.setChannelId(channelId);
	    }
	    if (StringUtils.isNotEmpty(orgId)) {
		// orgId
		supervisorLineCheckItem.setOrgId(orgId);
	    } else {
		AllUsers user = this.getUser();
		supervisorLineCheckItem.setOrgId(user.getOrgId());
	    }
	    if (StringUtils.isNotEmpty(custState)) {
		supervisorLineCheckItem.setCustState(custState);
	    }
	    if (StringUtils.isNotEmpty(contactName) && StringUtils.isNotEmpty(contactName.trim())) {
		supervisorLineCheckItem.setContactName(contactName);
	    }
	    if (StringUtils.isNotEmpty(stationUserId) && StringUtils.isNotEmpty(stationUserId.trim())) {
		try {
		    stationUserId = new String(stationUserId.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
		supervisorLineCheckItem.setStationUserId(stationUserId);
	    }
	    if (StringUtils.isNotEmpty(custKunnr) && StringUtils.isNotEmpty(custKunnr.trim())) {
		try {
		    custKunnr = new String(custKunnr.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		supervisorLineCheckItem.setCustKunnr(custKunnr);
	    }
	    if (StringUtils.isNotEmpty(custParent) && StringUtils.isNotEmpty(custParent.trim())) {
		supervisorLineCheckItem.setCustParent(custParent);
	    }
	    if (StringUtils.isNotEmpty(custType) && StringUtils.isNotEmpty(custType.trim())) {
		supervisorLineCheckItem.setCustType(custType);
	    }
	    if (StringUtils.isNotEmpty(createOrgId)) {
		supervisorLineCheckItem.setCreateOrgId(createOrgId);
	    }
	    if (StringUtils.isNotEmpty(createDateStart)) {
		supervisorLineCheckItem.setCreateDateStart(createDateStart);
	    }
	    if (StringUtils.isNotEmpty(createDateEnd)) {
		supervisorLineCheckItem.setCreateDateEnd(createDateEnd);
	    }

	    List<Customer> clist = new ArrayList<Customer>();
	    clist = masterService.getExportMouldCsvCustWithCons(supervisorLineCheckItem);
	    for (Customer customer : clist) {
		linedata.append(customer.getCustId());
		linedata.append(",");
		linedata.append(customer.getCustName());
		linedata.append(",");
		linedata.append("\n");
	    }

	    if (linedata.length() > 0) {
		print.write(linedata.toString());
	    }
	} catch (Exception e) {
	    setFailMessage("模板导出失败");
	} finally {
	    if (print != null) {
		try {
		    print.close();
		} catch (Exception e) {
		    setFailMessage("模板导出失败");
		}
		print = null;
	    }
	    if (os != null) {
		try {
		    os.close();
		} catch (Exception e) {
		    setFailMessage("模板导出失败");
		}
		os = null;
	    }
	}
	return RESULT_MESSAGE;
    }

    /**
     * 导出模板 带条件cols
     *  
     */

    @PermissionSearch
    public String exportMouldCsvWithConsCols() {

	OutputStream os = null;
	String report_name = "督导流通特通考核导入模板";
	PrintWriter print = null;
	try {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    os = response.getOutputStream();
	    response.reset();
	    response.setContentType("application/csv;charset=gb18030");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(report_name.getBytes("GBK"), ("ISO8859-1")) + ".csv\"");
	    print = response.getWriter();
	    StringBuffer linedata = new StringBuffer();
	    // linedata.append("说明：当前SKU标准，需要通过不同类别来标识，如果是同一类别请用同一数字标注");
	    // linedata.append("\n");

	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");

	    List<Materiel> list = masterService.getMasterCols(new Materiel());
	    for (Materiel materiel : list) {
		linedata.append(materiel.getMatg());
		linedata.append(",");
		linedata.append(materiel.getBezei40());
		linedata.append(",");
		linedata.append("");
		linedata.append(",");
	    }

	    linedata.append("\n");

	    linedata.append("门店ID");
	    linedata.append(",");
	    linedata.append("门店");
	    linedata.append(",");

	    for (Materiel materiel : list) {
		linedata.append("铺货");
		linedata.append(",");
		linedata.append("价格最小值");
		linedata.append(",");
		linedata.append("价格最大值");
		linedata.append(",");
	    }

	    linedata.append("\n");

	    supervisorLineCheckItemList = new ArrayList<SupervisorLineCheckItem>();
	    SupervisorLineCheckItem supervisorLineCheckItem = new SupervisorLineCheckItem();
	    supervisorLineCheckItem.setStart(0);
	    supervisorLineCheckItem.setEnd(50 * 10000);

	    if (StringUtils.isNotEmpty(custNumber)) {
		supervisorLineCheckItem.setCustNumber(custNumber);
	    }
	    if (StringUtils.isNotEmpty(custName) && StringUtils.isNotEmpty(custName.trim())) {
		supervisorLineCheckItem.setCustName(custName);
	    }
	    if (StringUtils.isNotEmpty(channelId)) {
		supervisorLineCheckItem.setChannelId(channelId);
	    }
	    if (StringUtils.isNotEmpty(orgId)) {
		// orgId
		supervisorLineCheckItem.setOrgId(orgId);
	    } else {
		AllUsers user = this.getUser();
		supervisorLineCheckItem.setOrgId(user.getOrgId());
	    }
	    if (StringUtils.isNotEmpty(custState)) {
		supervisorLineCheckItem.setCustState(custState);
	    }
	    if (StringUtils.isNotEmpty(contactName) && StringUtils.isNotEmpty(contactName.trim())) {
		supervisorLineCheckItem.setContactName(contactName);
	    }
	    if (StringUtils.isNotEmpty(stationUserId) && StringUtils.isNotEmpty(stationUserId.trim())) {
		try {
		    stationUserId = new String(stationUserId.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
		supervisorLineCheckItem.setStationUserId(stationUserId);
	    }
	    if (StringUtils.isNotEmpty(custKunnr) && StringUtils.isNotEmpty(custKunnr.trim())) {
		try {
		    custKunnr = new String(custKunnr.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		supervisorLineCheckItem.setCustKunnr(custKunnr);
	    }
	    if (StringUtils.isNotEmpty(custParent) && StringUtils.isNotEmpty(custParent.trim())) {
		supervisorLineCheckItem.setCustParent(custParent);
	    }
	    if (StringUtils.isNotEmpty(custType) && StringUtils.isNotEmpty(custType.trim())) {
		supervisorLineCheckItem.setCustType(custType);
	    }
	    if (StringUtils.isNotEmpty(createOrgId)) {
		supervisorLineCheckItem.setCreateOrgId(createOrgId);
	    }
	    if (StringUtils.isNotEmpty(createDateStart)) {
		supervisorLineCheckItem.setCreateDateStart(createDateStart);
	    }
	    if (StringUtils.isNotEmpty(createDateEnd)) {
		supervisorLineCheckItem.setCreateDateEnd(createDateEnd);
	    }

	    List<Customer> clist = new ArrayList<Customer>();
	    clist = masterService.getExportMouldCsvCustWithCons(supervisorLineCheckItem);
	    for (Customer customer : clist) {
		linedata.append(customer.getCustId());
		linedata.append(",");
		// cutname 财贸校3,4号
		String custName1 = customer.getCustName().replace(',', '/');
		linedata.append(custName1);
		linedata.append(",");
		linedata.append("\n");
	    }

	    if (linedata.length() > 0) {
		print.write(linedata.toString());
	    }
	} catch (Exception e) {
	    setFailMessage("模板导出失败");
	} finally {
	    if (print != null) {
		try {
		    print.close();
		} catch (Exception e) {
		    setFailMessage("模板导出失败");
		}
		print = null;
	    }
	    if (os != null) {
		try {
		    os.close();
		} catch (Exception e) {
		    setFailMessage("模板导出失败");
		}
		os = null;
	    }
	}
	return RESULT_MESSAGE;
    }
    
    /**
     * 导出模板 带条件cols ,
     * 1. 去除最大最小值 
     *  2. 添加 组织、渠道、地址
     */
    
    /**
     *  合并口味 代码和名称 2015.11.16 by zpf 
     * @return
     */

    @PermissionSearch
    public String exportMouldCsvWithConsCols2() {

	OutputStream os = null;
	String report_name = "督导流通特通考核导入模板";
	PrintWriter print = null;
	try {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    os = response.getOutputStream();
	    response.reset();
	    response.setContentType("application/csv;charset=gb18030");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(report_name.getBytes("GBK"), ("ISO8859-1")) + ".csv\"");
	    print = response.getWriter();
	    StringBuffer linedata = new StringBuffer();
	    // linedata.append("说明：当前SKU标准，需要通过不同类别来标识，如果是同一类别请用同一数字标注");
	    // linedata.append("\n");

	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");

	    List<Materiel> list = masterService.getMasterCols(new Materiel());
	    for (Materiel materiel : list) {
//		linedata.append(materiel.getMatg());
//		linedata.append(",");
//		linedata.append(materiel.getBezei40());
//		linedata.append(",");
//		linedata.append("");
//		linedata.append(",");
		// like :A30椰果草莓味
		linedata.append(materiel.getBezei40()+materiel.getMatg());
		linedata.append(",");
		
	    }

	    linedata.append("\n");

	    linedata.append("门店ID");
	    linedata.append(",");
	    linedata.append("门店");
	    linedata.append(",");
	    linedata.append("组织");
	    linedata.append(",");
	    linedata.append("渠道");
	    linedata.append(",");
	    linedata.append("地址");
	    linedata.append(",");

	    for (Materiel materiel : list) {
		linedata.append("铺货");
		linedata.append(",");
//		linedata.append("");
//		linedata.append(",");
//		linedata.append("");
//		linedata.append(",");
	    }

	    linedata.append("\n");

	    supervisorLineCheckItemList = new ArrayList<SupervisorLineCheckItem>();
	    SupervisorLineCheckItem supervisorLineCheckItem = new SupervisorLineCheckItem();
	    supervisorLineCheckItem.setStart(0);
	    supervisorLineCheckItem.setEnd(50 * 10000);

	    if (StringUtils.isNotEmpty(custNumber)) {
		supervisorLineCheckItem.setCustNumber(custNumber);
	    }
	    if (StringUtils.isNotEmpty(custName) && StringUtils.isNotEmpty(custName.trim())) {
		supervisorLineCheckItem.setCustName(custName);
	    }
	    if (StringUtils.isNotEmpty(channelId)) {
		supervisorLineCheckItem.setChannelId(channelId);
	    }
	    if (StringUtils.isNotEmpty(orgId)) {
		// orgId
		supervisorLineCheckItem.setOrgId(orgId);
	    } else {
		AllUsers user = this.getUser();
		supervisorLineCheckItem.setOrgId(user.getOrgId());
	    }
	    if (StringUtils.isNotEmpty(custState)) {
		supervisorLineCheckItem.setCustState(custState);
	    }
	    if (StringUtils.isNotEmpty(contactName) && StringUtils.isNotEmpty(contactName.trim())) {
		supervisorLineCheckItem.setContactName(contactName);
	    }
	    if (StringUtils.isNotEmpty(stationUserId) && StringUtils.isNotEmpty(stationUserId.trim())) {
		try {
		    stationUserId = new String(stationUserId.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
		supervisorLineCheckItem.setStationUserId(stationUserId);
	    }
	    if (StringUtils.isNotEmpty(custKunnr) && StringUtils.isNotEmpty(custKunnr.trim())) {
		try {
		    custKunnr = new String(custKunnr.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		supervisorLineCheckItem.setCustKunnr(custKunnr);
	    }
	    if (StringUtils.isNotEmpty(custParent) && StringUtils.isNotEmpty(custParent.trim())) {
		supervisorLineCheckItem.setCustParent(custParent);
	    }
	    if (StringUtils.isNotEmpty(custType) && StringUtils.isNotEmpty(custType.trim())) {
		supervisorLineCheckItem.setCustType(custType);
	    }
	    if (StringUtils.isNotEmpty(createOrgId)) {
		supervisorLineCheckItem.setCreateOrgId(createOrgId);
	    }
	    if (StringUtils.isNotEmpty(createDateStart)) {
		supervisorLineCheckItem.setCreateDateStart(createDateStart);
	    }
	    if (StringUtils.isNotEmpty(createDateEnd)) {
		supervisorLineCheckItem.setCreateDateEnd(createDateEnd);
	    }

	    List<Customer> clist = new ArrayList<Customer>();
	    clist = masterService.getExportMouldCsvCustWithCons(supervisorLineCheckItem);
	    for (Customer customer : clist) {
		linedata.append(customer.getCustId());
		linedata.append(",");
		// cutname 财贸校3,4号
		String custName1 = customer.getCustName().replace(',', '/');
		linedata.append(custName1);
		linedata.append(",");
		linedata.append(customer.getOrgName());
		linedata.append(",");
		linedata.append(customer.getChannelName());
		linedata.append(",");
		linedata.append(customer.getCustProvince());
		linedata.append(",");
		linedata.append("\n");
	    }

	    if (linedata.length() > 0) {
		print.write(linedata.toString());
	    }
	} catch (Exception e) {
	    setFailMessage("模板导出失败");
	} finally {
	    if (print != null) {
		try {
		    print.close();
		} catch (Exception e) {
		    setFailMessage("模板导出失败");
		}
		print = null;
	    }
	    if (os != null) {
		try {
		    os.close();
		} catch (Exception e) {
		    setFailMessage("模板导出失败");
		}
		os = null;
	    }
	}
	return RESULT_MESSAGE;
    }

    /**
     * 导出模板
     */
    @PermissionSearch
    public String exportMouldCsv() {

	OutputStream os = null;
	String report_name = "督导流通特通考核导入模板";
	PrintWriter print = null;
	try {
	    HttpServletResponse response = ServletActionContext.getResponse();
	    os = response.getOutputStream();
	    response.reset();
	    response.setContentType("application/csv;charset=gb18030");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(report_name.getBytes("GBK"), ("ISO8859-1")) + ".csv\"");
	    print = response.getWriter();
	    StringBuffer linedata = new StringBuffer();
	    // linedata.append("说明：当前SKU标准，需要通过不同类别来标识，如果是同一类别请用同一数字标注");
	    // linedata.append("\n");

	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");

	    linedata.append("椰果原味(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果香芋(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果草莓(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果麦香(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果咖啡(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果巧克力(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("桂圆红枣(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("红豆(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("芝士燕麦(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("黑米椰浆(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("焦糖仙草(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("原汁红豆(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("芒果布丁(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("蓝莓(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("雪糕椰浆(单杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("原味(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("香芋(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("草莓(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("麦香(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("桂圆红枣(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("红豆(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("芝士燕麦(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("黑米椰浆(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("焦糖仙草(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("芒果布丁(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("蓝莓(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("雪糕椰浆(3连杯)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果16杯装(家庭分享装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果12杯装(家庭分享装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("美味16杯装(家庭分享装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("美味12杯装(家庭分享装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果12杯装(礼盒装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("新品礼盒装(礼盒装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("椰果经典(组合装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("美味新品(组合装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("家庭礼盒(家庭礼盒装)");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");
	    linedata.append("");
	    linedata.append(",");

	    linedata.append("\n");

	    linedata.append("门店ID");
	    linedata.append(",");
	    linedata.append("门店");
	    linedata.append(",");

	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");
	    linedata.append("铺货");
	    linedata.append(",");
	    linedata.append("价格最小值");
	    linedata.append(",");
	    linedata.append("价格最大值");
	    linedata.append(",");

	    linedata.append("\n");

	    Customer c = new Customer();
	    c.setStart(0);
	    // c.setEnd(1*1000);
	    c.setEnd(50 * 10000);
	    List<Customer> clist = new ArrayList<Customer>();
	    clist = masterService.getExportMouldCsvCust(c);
	    for (Customer customer : clist) {
		linedata.append(customer.getCustId());
		linedata.append(",");
		linedata.append(customer.getCustName());
		linedata.append(",");
		linedata.append("\n");
	    }

	    if (linedata.length() > 0) {
		print.write(linedata.toString());
	    }
	} catch (Exception e) {
	    setFailMessage("模板导出失败");
	} finally {
	    if (print != null) {
		try {
		    print.close();
		} catch (Exception e) {
		    setFailMessage("模板导出失败");
		}
		print = null;
	    }
	    if (os != null) {
		try {
		    os.close();
		} catch (Exception e) {
		    setFailMessage("模板导出失败");
		}
		os = null;
	    }
	}
	return RESULT_MESSAGE;
    }

    /**
     * 跳转到物料查询页面
     */
    public String toSearchMateriel() {
	if ("A".equals(opType)) {// 有同步至销售品项的按钮的查询页面
	    return "toSearchMaterielHaveSkuBtn";
	}
	return "toSearchMateriel";
    }

    /**
     * 同步SAP物料到本地
     */
    public String synchMateriel() {
	StringResult result = masterService.synchMateriel();
	;
	if ("success".equals(result.getCode())) {
	    this.setSuccessMessage("同步成功");
	} else {
	    this.setFailMessage("同步失败");
	}
	return RESULT_MESSAGE;
    }

    /**
     * 查询数据
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "materielList", include = { "matnr", "matkl","maktx","meins","wgbez",
			"matnr03","maktx03","matnr02","maktx02","matnr01","maktx01"}, total = "total")
    public String getMaterielJsonList() {
	materielList = new ArrayList<Materiel>();
	Materiel materiel = new Materiel();
	if (StringUtils.isNotEmpty(matnr)) {
	    materiel.setMatnr(matnr);
	}
	if (StringUtils.isNotEmpty(maktx)) {
	    materiel.setMaktx(maktx);
	}
	materiel.setStart(getStart());
	materiel.setEnd(getEnd());
	total = masterService.getMaterielCount(materiel);
	if (total != 0) {
	    materielList = masterService.getMaterielList(materiel);
	}
	return JSON;
    }

    /**
     * 查询物料督导分组数据
     * 
     * @return
     */
    @PermissionSearch
    @JsonResult(field = "materielList", include = { "matg", "bezei40" }, total = "total")
    public String getMasterCols() {
	materielList = new ArrayList<Materiel>();
	Materiel materiel = new Materiel();

	materielList = masterService.getMasterCols(materiel);
	total = (materielList != null && materielList.size() > 0) ? materielList.size() : 0;

	return JSON;
    }

    /**
     * 单个品项同步至销售品项表 synchToSku
     * 
     * @param @return 设定文件
     * @return String DOM对象
     * @throws
     * @since CodingExample　Ver 1.1
     */
    public String synchToSku() {
	// 取字典的本品单位
	CmsTbDictType dictType = new CmsTbDictType();
	dictType.setPagination("false");
	dictType.setDictTypeName("香飘飘sku基本单位");
	@SuppressWarnings("unchecked")
	List<CmsTbDict> dlist = dictServiceHessian.getDictListByDictType(dictType);
	String unitId = "";
	String unitName = "";
	if (null != dlist) {
	    if (dlist.size() > 0 && dlist.size() == 1) {
		unitId = dlist.get(0).getItemValue();
		unitName = dlist.get(0).getItemName();
	    }
	}
	CrmTbSku sku = new CrmTbSku();
	sku.setSkuUnitId(unitId);
	sku.setSkuUnit(unitName);
	sku.setSkuName(skuName);
	sku.setSkuSapCode(skuId);// code/sapcode
	sku.setSkuCategory(skuType);
	sku.setSkuCategoryId(skuTypeId);
	sku.setCreateUser(this.getUser().getUserId());
	sku.setCloudid("0000000000");
	StringResult result = masterService.synchToSku(sku);
	;
	if ("success".equals(result.getCode())) {
	    this.setSuccessMessage("同步成功");
	} else {
	    this.setFailMessage("同步失败");
	}
	return RESULT_MESSAGE;
    }

    public MasterService getMasterService() {
	return masterService;
    }

    public void setMasterService(MasterService masterService) {
	this.masterService = masterService;
    }

    public List<Materiel> getMaterielList() {
	return materielList;
    }

    public void setMaterielList(List<Materiel> materielList) {
	this.materielList = materielList;
    }

    public int getTotal() {
	return total;
    }

    public void setTotal(int total) {
	this.total = total;
    }

    public String getMatnr() {
	return matnr;
    }

    public void setMatnr(String matnr) {
	this.matnr = matnr;
    }

    public String getMaktx() {
	return maktx;
    }

    public void setMaktx(String maktx) {
	this.maktx = maktx;
    }

    public String getOpType() {
	return opType;
    }

    public void setOpType(String opType) {
	this.opType = opType;
    }

    public String getSkuType() {
	return skuType;
    }

    public void setSkuType(String skuType) {
	this.skuType = skuType;
    }

    public String getSkuId() {
	return skuId;
    }

    public void setSkuId(String skuId) {
	this.skuId = skuId;
    }

    public String getSkuName() {
	return skuName;
    }

    public void setSkuName(String skuName) {
	this.skuName = skuName;
    }

    public IDictService getDictServiceHessian() {
	return dictServiceHessian;
    }

    public void setDictServiceHessian(IDictService dictServiceHessian) {
	this.dictServiceHessian = dictServiceHessian;
    }

    public String getSkuTypeId() {
	return skuTypeId;
    }

    public void setSkuTypeId(String skuTypeId) {
	this.skuTypeId = skuTypeId;
    }

    public String getUploadFileFileName() {
	return uploadFileFileName;
    }

    public void setUploadFileFileName(String uploadFileFileName) {
	this.uploadFileFileName = uploadFileFileName;
    }

    public File getUploadFile() {
	return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
	this.uploadFile = uploadFile;
    }

    public SupervisorCheckItem getSupervisorCheckItem() {
	return supervisorCheckItem;
    }

    public void setSupervisorCheckItem(SupervisorCheckItem supervisorCheckItem) {
	this.supervisorCheckItem = supervisorCheckItem;
    }

    public List<SupervisorLineCheckItem> getSupervisorLineCheckItemList() {
	return supervisorLineCheckItemList;
    }

    public void setSupervisorLineCheckItemList(List<SupervisorLineCheckItem> supervisorLineCheckItemList) {
	this.supervisorLineCheckItemList = supervisorLineCheckItemList;
    }

    public String getSupervisorLineCheckItemListjson() {
	return supervisorLineCheckItemListjson;
    }

    public void setSupervisorLineCheckItemListjson(String supervisorLineCheckItemListjson) {
	this.supervisorLineCheckItemListjson = supervisorLineCheckItemListjson;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
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

    public CmsTbDict getCmsTbDict() {
	return cmsTbDict;
    }

    public void setCmsTbDict(CmsTbDict cmsTbDict) {
	this.cmsTbDict = cmsTbDict;
    }

    public List<CmsTbDict> getCmsTbDictCustStateList() {
	return cmsTbDictCustStateList;
    }

    public void setCmsTbDictCustStateList(List<CmsTbDict> cmsTbDictCustStateList) {
	this.cmsTbDictCustStateList = cmsTbDictCustStateList;
    }

    public String getCustomer_state() {
	return customer_state;
    }

    public void setCustomer_state(String customer_state) {
	this.customer_state = customer_state;
    }

    public IOrgService getOrgServiceHessian() {
	return orgServiceHessian;
    }

    public void setOrgServiceHessian(IOrgService orgServiceHessian) {
	this.orgServiceHessian = orgServiceHessian;
    }

    public ICustomerService getCustomerService() {
	return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
	this.customerService = customerService;
    }

    public String getBhxjFlag() {
	return bhxjFlag;
    }

    public void setBhxjFlag(String bhxjFlag) {
	this.bhxjFlag = bhxjFlag;
    }

    public String getCustNumber() {
	return custNumber;
    }

    public void setCustNumber(String custNumber) {
	this.custNumber = custNumber;
    }

    public String getCustName() {
	return custName;
    }

    public void setCustName(String custName) {
	this.custName = custName;
    }

    public String getChannelId() {
	return channelId;
    }

    public void setChannelId(String channelId) {
	this.channelId = channelId;
    }

    public String getStationUserId() {
	return stationUserId;
    }

    public void setStationUserId(String stationUserId) {
	this.stationUserId = stationUserId;
    }

    public String getContactName() {
	return contactName;
    }

    public void setContactName(String contactName) {
	this.contactName = contactName;
    }

    public String getCustState() {
	return custState;
    }

    public void setCustState(String custState) {
	this.custState = custState;
    }

    public String getCustKunnr() {
	return custKunnr;
    }

    public void setCustKunnr(String custKunnr) {
	this.custKunnr = custKunnr;
    }

    public String getCustType() {
	return custType;
    }

    public void setCustType(String custType) {
	this.custType = custType;
    }

    public String getCreateOrgId() {
	return createOrgId;
    }

    public void setCreateOrgId(String createOrgId) {
	this.createOrgId = createOrgId;
    }

    public String getCreateDateStart() {
	return createDateStart;
    }

    public void setCreateDateStart(String createDateStart) {
	this.createDateStart = createDateStart;
    }

    public String getCreateDateEnd() {
	return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
	this.createDateEnd = createDateEnd;
    }

    public String getCustParent() {
	return custParent;
    }

    public void setCustParent(String custParent) {
	this.custParent = custParent;
    }

    public Kunnr getKunnr() {
	return kunnr;
    }

    public void setKunnr(Kunnr kunnr) {
	this.kunnr = kunnr;
    }

    public String[] getKunnrs() {
	return kunnrs;
    }

    public void setKunnrs(String[] kunnrs) {
	this.kunnrs = kunnrs;
    }

    public String getKunnrId() {
	return kunnrId;
    }

    public void setKunnrId(String kunnrId) {
	this.kunnrId = kunnrId;
    }

    public String getName1() {
	return name1;
    }

    public void setName1(String name1) {
	this.name1 = name1;
    }

    public String getBusinessManager() {
	return businessManager;
    }

    public void setBusinessManager(String businessManager) {
	this.businessManager = businessManager;
    }

    public String getBusinessCompetent() {
	return businessCompetent;
    }

    public void setBusinessCompetent(String businessCompetent) {
	this.businessCompetent = businessCompetent;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getCodes() {
	return codes;
    }

    public void setCodes(String codes) {
	this.codes = codes;
    }

    public List<Kunnr> getKunnrList() {
	return kunnrList;
    }

    public void setKunnrList(List<Kunnr> kunnrList) {
	this.kunnrList = kunnrList;
    }

    public List<SupervisorCheckItem> getSupervisorCheckItemList() {
	return supervisorCheckItemList;
    }

    public void setSupervisorCheckItemList(List<SupervisorCheckItem> supervisorCheckItemList) {
	this.supervisorCheckItemList = supervisorCheckItemList;
    }

    public String getSupervisorCheckItemListjson() {
	return supervisorCheckItemListjson;
    }

    public void setSupervisorCheckItemListjson(String supervisorCheckItemListjson) {
	this.supervisorCheckItemListjson = supervisorCheckItemListjson;
    }

    public String getAllChannelName() {
        return allChannelName;
    }

    public void setAllChannelName(String allChannelName) {
        this.allChannelName = allChannelName;
    }

}
