package com.kintiger.platform.stock.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.service.IAllUserService;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.channel.pojo.Tree4Ajax;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.JavaBeanXMLUtil;

import com.kintiger.platform.framework.util.DateUtil;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.framework.util.XMLInfo;

import com.kintiger.platform.goal.action.GoalAction;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.goal.service.IGoalService;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.stock.pojo.Instock;
import com.kintiger.platform.stock.pojo.Instock_det;
import com.kintiger.platform.stock.pojo.Stock;
import com.kintiger.platform.stock.service.IInStockService;
import com.kintiger.platform.webservice.resps.JsonUtil;
import com.kintiger.platform.wfe.pojo.UserUtil;
import com.kintiger.platform.wfe.service.IWfeService;









public class InStockAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -360521482258412701L;
	
	private static Log logger = LogFactory.getLog(GoalAction.class);
	
	private static List<Instock_det>detailList1 =  new ArrayList<Instock_det>();
	private IWfeService wfeServiceHessian;
	private IInStockService  instockService;
	private IDictService dictServiceHessian;
	private IAllUserService allUserServiceHessian;
	private IOrgService orgServiceHessian;
	private List<Instock> instocklist = new ArrayList<Instock>() ;
	private List<Instock_det> detailList = new ArrayList<Instock_det>() ;
	private List<Stock>stockList = new ArrayList<Stock>();
	private String orgId;
	private String  orgName;
	private BooleanResult executeResult;// 事务处理结束返回信息
	private List<Tree4Ajax> treeList;
	private String userId;
	private int size;
	private int year;
	private int month;
	private String appUrl;
	private String xmlFilePath;
	private String flagTemp;
	private Instock instock;
	private Instock_det  instockdet;
	private Stock stock;
	private String beginDate;
	private String endDate;
	private String id;
	private String totalId;
	private String instockdet_number;
	private String instockdet_unumber;
	private String instockdet_anumber;
	private String  instock_total_id;
	private String instock_id;
	private String  instockdetBatch;
	private String  matnr;
	private String  bezei;
	private String instock_send_place;
	private String instock_good_place;
    private String instock_provide_id;
    private String instock_provide_name;
    private String type;
    private String custId;
    private String empId;

	/**
	 * 经销商目标查询跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String StockSearchPre() {
		Calendar   ctime   =   Calendar.getInstance(); 
		int year = ctime.get(Calendar.YEAR);
	    month = ctime.get(Calendar.MONTH)+1;
		userId = this.getUser().getUserId();
		String flagTemp="Y";
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		orgName = org.getOrgName();
		this.setFlagTemp(flagTemp);
		return "StockSearchPre";
	}
	
	/**
	 * 查询入库单跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String StockSearchPre1() {
		this.setFlagTemp(flagTemp);
		return "StockSearchPre1";
	}
	
	

	
	/**
	 * 查询出库单跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String StockSearchPreO() {
		this.setFlagTemp(flagTemp);
		return "StockSearchPreO";
	}
	/**
	 * 查询入库单跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String searchStockPrepare() {
		Calendar   ctime   =   Calendar.getInstance(); 
		int year = ctime.get(Calendar.YEAR);
		int month = ctime.get(Calendar.MONTH)+1;
		this.setYear(year);
		this.setMonth(month);
		return "searchStockPrepare";
	}
	
	
	/**
	 * 入库单修改跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateStockPrepare() {	
	    this.setInstock_total_id(instock_total_id) ;
		return "updateStockPrepare";
	}
	
	/**
	 * 入库单修改跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateStockPrepare1() {	
	    
		if (StringUtils.isNotEmpty(instock_total_id)
				&& StringUtils.isNotEmpty(instock_total_id.trim())) {
			try {	
				type = "I";
				instock = instockService.getStockById(instock_total_id,type);
			} catch (Exception e) {
				logger.error(instock_total_id, e);
			}
		}
		instock = instock == null ? new Instock() : instock;
		return "updateStockPrepare1";			
	}
	
	/**
	 * 出库单修改跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateStockPrepareO() {	
	    
		if (StringUtils.isNotEmpty(instock_total_id)
				&& StringUtils.isNotEmpty(instock_total_id.trim())) {
			try {
				type = "O";
				instock = instockService.getStockById(instock_total_id,type);
			} catch (Exception e) {
				logger.error(instock_total_id, e);
			}
		}
		instock = instock == null ? new Instock() : instock;
		return "updateStockPrepareO";			
	}
	/**
	 * 创建明细维护跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createStockPrepare() {
		Calendar   ctime   =   Calendar.getInstance(); 
		int year = ctime.get(Calendar.YEAR);
		int month = ctime.get(Calendar.MONTH)+1;
		this.setYear(year);
		this.setMonth(month);
		return "createStockPrepare";
	}
	/**
	 * 创建入库明细维护跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createStockdtPrepare() {;
		return "createStockdtPrepare";
	}
	
	/**
	 * 创建出库明细维护跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createStockdtPrepareO() {;
		return "createStockdtPrepareO";
	}
	
	
	/**
	 * 创建入库总单维护跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createStockPrepare1() {
		return "createStockPrepare1";
	}
	/**
	 * 创建出库总单维护跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createStockPrepareO() {
		return "createStockPrepareO";
	}
	/**
	 * 查询入库单 add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "instocklist", total = "size", include = {"instock_id","instock_send_date",
			"instock_provide_id","instock_provide_name","instock_flag","instock_send_place",
			"instock_good_place","instock_total_id"})
	public String getInStockList() {
		if (instock == null) {
			instock = new Instock();
		}
        instock.setBeginDate(DateUtil.getDateTime(beginDate));
        instock.setEndDate(DateUtil.getDateTime(endDate));
        instock.setInstock_provide_id(custId);
        instock.setInstock_type("I");
        instock.setStart(getStart());
        instock.setEnd(getEnd());
       // instocklist = instockService.getInStockList(instock);
		size = instockService.getInStockCount(instock);
		if (size!=0) {
			instocklist = instockService.getInStockList(instock);
		}
		return JSON;
	}
	/**
	 * 查询入库单 add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "instocklist", total = "size", include = {"instock_id","instock_send_date",
			"instock_provide_id","instock_provide_name","instock_flag","instock_send_place",
			"instock_good_place","instock_total_id"})
	public String getOutStockList() {
		if (instock == null) {
			instock = new Instock();
		}
        instock.setBeginDate(DateUtil.getDateTime(beginDate));
        instock.setEndDate(DateUtil.getDateTime(endDate));
        instock.setInstock_provide_id(custId);
        instock.setStart(getStart());
        instock.setEnd(getEnd());
        instock.setInstock_type("O");
    //    instocklist = instockService.getInStockList(instock);
		size = instockService.getInStockCount(instock);
		if (size!=0){
			instocklist = instockService.getInStockList(instock);
		}
		return JSON;
	}
	/**
	 * 查询入库单明细   add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "detailList", total = "size", include = {"instockdetBatch","instockdet_id",
			"instockdet_number", "instockdet_unumber", "instockdet_anumber","bezei","matnr"})
	public String getInStockDetailListByTotalId() {
		detailList = new ArrayList<Instock_det>();
		size=detailList1.size();
		if(size!=0){
		detailList = detailList1;	
		}
		return JSON;
	}
	/**
	 * 查询出库明细   add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "detailList", total = "size", include = {"instockdetBatch","instockdet_id",
			"instockdet_number", "instockdet_unumber", "instockdet_anumber","bezei","matnr","instockdetBatch"})
	public String getInStockDetailListByTotalId1() {
		detailList = new ArrayList<Instock_det>();
		instockdet = new Instock_det();
		if (StringUtils.isNotEmpty(instock_total_id)
				&& StringUtils.isNotEmpty(instock_total_id.trim())) {
			instockdet.setInstock_total_id(instock_total_id);
	        instockdet.setInstockdetBatch(instockdetBatch);
	        instockdet.setMatnr(matnr);
	        if(type.equals("O")){
	        instockdet.setType("O");
	        }
	        else{
	        instockdet.setType("I");
	        }
	        instockdet.setStart(getStart());
	        instockdet.setEnd(getEnd());
		size=instockService.getInStockDetailListByTotalIdCount(instockdet);
		if(size!=0){
			detailList = instockService.getInStockDetailListByTotalId(instockdet);
		}
		}
		return JSON;
	}
	/**
	 * 查询出库明细   add by allen
	 * 
	 * @return
	 */
//	@PermissionSearch
//	public String addList(){
//		//detailList1 = new ArrayList<Instock_det>();
//		instockdet = new Instock_det();
//		instockdet.setInstockdet_number(Long.valueOf(instockdet_number));
//		instockdet.setInstockdet_unumber(Long.valueOf(instockdet_unumber));
//		instockdet.setInstockdet_anumber(Long.valueOf(instockdet_anumber));
//		instockdet.setInstockdetBatch(instockdetBatch);
//		instockdet.setBezei(bezei);
//		instockdet.setMatnr(matnr);
//		detailList1.add(instockdet);
//		this.setSuccessMessage("已添加！");
//		return RESULT_MESSAGE;			
//	}
	/**
	 * 新增入库出库总单   add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String addStock(){
		instock = new Instock();
		Instock instockO= new Instock();
        instock.setInstock_provide_id(instock_provide_id);
        instock.setInstock_provide_name(instock_provide_name);
        instock.setInstock_total_id(instock_total_id);
        instock.setInstock_send_place(instock_send_place);
        instock.setInstock_good_place(instock_good_place);
        instock.setInstock_type("I");
        instockO.setInstock_provide_id(instock_provide_id);//用作验证出库是否唯一
        instockO.setInstock_provide_name(instock_provide_name);
        instockO.setInstock_total_id(instock_total_id);
        instockO.setInstock_send_place(instock_send_place);
        instockO.setInstock_good_place(instock_good_place);
        instockO.setInstock_type("O");
        if(type.equals("I")){//入库总单
        if(instockService.getInStockOnly(instock)==0){
        try{
			boolean b  = instockService.insertInstockTotal(instock);
			if (b) {
				this.setSuccessMessage("处理成功！");
			} else {
				this.setFailMessage("处理失败！");
			}
			}catch (Exception e) {
				this.setFailMessage("处理失败！");
				logger.error(instock, e);
			}
        }else{//入库总单唯一
        	this.setFailMessage("该总单已存在请勿重复提交！");
        }
        }else{//出库总单
        	 if(instockService.getInStockOnly(instockO)==0){
        	        try{
        	        	instock.setInstock_type("O");
        				boolean b  = instockService.insertInstockTotal(instock);
        				if (b) {
        					this.setSuccessMessage("处理成功！");
        				} else {
        					this.setFailMessage("处理失败！");
        				}
        				}catch (Exception e) {
        					this.setFailMessage("处理失败！");
        					logger.error(instock, e);
        				}
        	        }else if(instockService.getInStockOnly(instockO)!=0){
        	        	this.setFailMessage("出库总单号已存在！");
        	        }
        }
			return RESULT_MESSAGE;	
	}
	/**
	 * 修改总单  add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateStock(){
		instock = new Instock();
        instock.setInstock_provide_id(instock_provide_id);
        instock.setInstock_provide_name(instock_provide_name);
       // instock.setInstock_total_id(instock_total_id);
        instock.setInstock_send_place(instock_send_place);
        instock.setInstock_good_place(instock_good_place);
        instock.setInstock_total_id(instock_total_id);
        try{
			boolean b  = instockService.updateStock(instock);
			if (b) {
				this.setSuccessMessage("处理成功！");
			} else {
				this.setFailMessage("处理失败！");
			}
			}catch (Exception e) {
				this.setFailMessage("处理失败！");
				logger.error(instock, e);
			}
			return RESULT_MESSAGE;	
	}
	/**
	 * 新增明细   add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String addStockdt(){
		instockdet = new Instock_det();
		stock = new Stock();
		AllUsers user = this.getUser();
		instockdet.setInstock_total_id(instock_total_id);
		instockdet.setInstock_id(Long.valueOf(instock_id));
		instockdet.setInstockdet_number(Long.valueOf(instockdet_number));
		instockdet.setInstockdet_unumber(Long.valueOf(instockdet_unumber));
		instockdet.setInstockdet_anumber(Long.valueOf(instockdet_anumber));
		instockdet.setInstockdetBatch(instockdetBatch);
		instockdet.setInstock_good_place(instock_good_place);
		instockdet.setBezei(bezei);
		instockdet.setMatnr(matnr);
		instockdet.setInstock_provide_id(instock_provide_id);
		instockdet.setEmpId(user.getLoginId());
		if(type.equals("O")){
			instockdet.setType("O");
		}else{
		    instockdet.setType("I");	
		}
		stock.setBatch(instockdetBatch);
		stock.setInstock_total_id(instock_total_id);
		stock.setCategory_id(matnr);
		stock.setCust_id(instock_provide_id);
		stock.setStock_stock_area(instock_good_place);
		stock.setStock_quantity(Integer.parseInt(instockdet_anumber));
		stock.setStock_operator(user.getLoginId());
		stock.setStock_flag("N");
		if(instockService.getStockList(stock).size()>0&&!type.equals("O")){//入库明细
			stockList=instockService.getStockList(stock);
		int  m = stockList.get(0).getStock_quantity();
		instockdet.setSum(m);
				try{
					boolean b  = instockService.insertInstockDt1(instockdet);
					if (b) {
				this.setSuccessMessage("处理成功！");
					} else {
				this.setFailMessage("处理失败！");
					}
				}catch (Exception e) {
					this.setFailMessage("处理失败！");
					logger.error(instock, e);
				}
		}else if(instockService.getStockList(stock).size()>0&&type.equals("O")) //出库
			{
			stockList=instockService.getStockList(stock);
			int  m = stockList.get(0).getStock_quantity();
			instockdet.setSum(m);
			if(m<instockdet.getInstockdet_anumber()){
				this.setFailMessage("出库的数量大于以有库存！");
				return RESULT_MESSAGE;	
			}
			try{
				boolean b  = instockService.insertInstockDt2(instockdet);
				if (b) {
					this.setSuccessMessage("处理成功！");
				} else {
					this.setFailMessage("处理失败！");
				}
				}catch (Exception e) {
					this.setFailMessage("处理失败！");
					logger.error(instock, e);
				}
		}else if(instockService.getStockList(stock).size()==0&&type.equals("O")){//检查是否有库存
			this.setFailMessage("此经销商该批次无库存信息无法出库");
		}else{
        try{
			boolean b  = instockService.insertInstockDt(instockdet);
			if (b) {
				this.setSuccessMessage("处理成功！");
			} else {
				this.setFailMessage("处理失败！");
			}
			}catch (Exception e) {
				this.setFailMessage("处理失败！");
				logger.error(instock, e);
			}
		}
			return RESULT_MESSAGE;	
	}
        	

	
//	public String deleteList(){
//		detailList1.clear();
//		return RESULT_MESSAGE;	
//	}
	/**
	 * 新增明细   add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createInstock(){
		for (Instock_det instockdet : detailList) {
			try{    
			boolean b  = instockService.insertInstock(instockdet);
			if (b) {
				this.setSuccessMessage("处理成功！");
			} else {
				this.setFailMessage("处理失败！");
			}
			}
			catch (Exception e) {
				this.setFailMessage("处理失败！");
				logger.error(instockdet, e);
			}
		}
		return RESULT_MESSAGE;
	}
	
	
	public IWfeService getWfeServiceHessian() {
		return wfeServiceHessian;
	}
	public void setWfeServiceHessian(IWfeService wfeServiceHessian) {
		this.wfeServiceHessian = wfeServiceHessian;
	}
	public IDictService getDictServiceHessian() {
		return dictServiceHessian;
	}
	public void setDictServiceHessian(IDictService dictServiceHessian) {
		this.dictServiceHessian = dictServiceHessian;
	}
	public IAllUserService getAllUserServiceHessian() {
		return allUserServiceHessian;
	}
	public void setAllUserServiceHessian(IAllUserService allUserServiceHessian) {
		this.allUserServiceHessian = allUserServiceHessian;
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
	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}
	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}



	public IInStockService getInstockService() {
		return instockService;
	}



	public void setInstockService(IInStockService instockService) {
		this.instockService = instockService;
	}



	public BooleanResult getExecuteResult() {
		return executeResult;
	}



	public void setExecuteResult(BooleanResult executeResult) {
		this.executeResult = executeResult;
	}



	public String getAppUrl() {
		return appUrl;
	}



	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}



	public String getXmlFilePath() {
		return xmlFilePath;
	}



	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}



	public String getFlagTemp() {
		return flagTemp;
	}



	public void setFlagTemp(String flagTemp) {
		this.flagTemp = flagTemp;
	}

	public Instock getInstock() {
		return instock;
	}

	public void setInstock(Instock instock) {
		this.instock = instock;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Instock> getInstocklist() {
		return instocklist;
	}

	public void setInstocklist(List<Instock> instocklist) {
		this.instocklist = instocklist;
	}



	public List<Instock_det> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<Instock_det> detailList) {
		this.detailList = detailList;
	}

	public Instock_det getInstockdet() {
		return instockdet;
	}

	public void setInstockdet(Instock_det instockdet) {
		this.instockdet = instockdet;
	}

	public String getTotalId() {
		return totalId;
	}

	public void setTotalId(String totalId) {
		this.totalId = totalId;
	}

	public String getInstockdet_number() {
		return instockdet_number;
	}

	public void setInstockdet_number(String instockdet_number) {
		this.instockdet_number = instockdet_number;
	}

	public String getInstockdet_unumber() {
		return instockdet_unumber;
	}

	public void setInstockdet_unumber(String instockdet_unumber) {
		this.instockdet_unumber = instockdet_unumber;
	}

	public String getInstockdet_anumber() {
		return instockdet_anumber;
	}

	public void setInstockdet_anumber(String instockdet_anumber) {
		this.instockdet_anumber = instockdet_anumber;
	}

	public String getInstockdetBatch() {
		return instockdetBatch;
	}

	public void setInstockdetBatch(String instockdetBatch) {
		this.instockdetBatch = instockdetBatch;
	}

	public String getMatnr() {
		return matnr;
	}

	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}

	public String getBezei() {
		return bezei;
	}

	public void setBezei(String bezei) {
		this.bezei = bezei;
	}

	public String getInstock_total_id() {
		return instock_total_id;
	}

	public void setInstock_total_id(String instock_total_id) {
		this.instock_total_id = instock_total_id;
	}

	public String getInstock_send_place() {
		return instock_send_place;
	}

	public void setInstock_send_place(String instock_send_place) {
		this.instock_send_place = instock_send_place;
	}

	public String getInstock_good_place() {
		return instock_good_place;
	}

	public void setInstock_good_place(String instock_good_place) {
		this.instock_good_place = instock_good_place;
	}

	public String getInstock_provide_id() {
		return instock_provide_id;
	}

	public void setInstock_provide_id(String instock_provide_id) {
		this.instock_provide_id = instock_provide_id;
	}

	public String getInstock_provide_name() {
		return instock_provide_name;
	}

	public void setInstock_provide_name(String instock_provide_name) {
		this.instock_provide_name = instock_provide_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}

	public String getInstock_id() {
		return instock_id;
	}

	public void setInstock_id(String instock_id) {
		this.instock_id = instock_id;
	}
	


	
	
}
