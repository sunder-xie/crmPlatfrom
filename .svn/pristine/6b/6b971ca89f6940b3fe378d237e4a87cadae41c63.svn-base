package com.kintiger.platform.kunnrBusinessContact.action;

import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.TokenProccessor;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.goal.pojo.GoalSales;
import com.kintiger.platform.goal.service.IGoalService;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdjustment;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdujstDetail;
import com.kintiger.platform.kunnrBusinessContact.service.IKunnrBusinessService;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.wfe.pojo.UserUtil;
import com.kintiger.platform.wfe.service.IWfeService;

public class KunnrBusinessAction extends BaseAction {
	private static final long serialVersionUID = -63872346683585547L;
	private static Log logger = LogFactory.getLog(KunnrBusinessAction.class);
	private IKunnrBusinessService kunnrBusinessService;
	private IKunnrService kunnrService;
	private IOrgService orgServiceHessian;
	private IWfeService wfeServiceHessian;
	private String appUrl;
	private Kunnr kunnr;
	private String kunnrId;// kunnrSAP代码
	private String kunnrName;//经销商名称
	private @Decode
	String businessManager;
	private @Decode
	String businessCompetent;
	private KunnrBusiness kunnrBusiness;
	private List<Kunnr> kunnrList;
	@Decode
	private String orgName;
	private String resultJson;
	private String[] businessManagerIds;
	private String[] businessManagers;
	private String[] managerMobiles;
	private String[] businessHeadIds;
	private String[] businessHeads;
	private String[] headMobiles;
	private String[] businessAgentIds;
	private String[] businessAgents;
	private String[] agentMobiles;
	private List<KunnrBusiness> kunnrBusinessList;
	private int total;
	private Long channelId;
	@Decode
	private String name1;
	private String status;
	private String bhxjFlag;
	private String codes;
	private String[] kunnrs;
	@Decode
	private String businessHead;
	@Decode
	private String businessAgent;
	private Long businessId;
	@Decode
	private String businessName;
	private Date businessEndDate;
	private String orgId;
	private Long id;//调整id
	private String ids;
	private String eventId;//事务id
	private String applyUser;//流程申请人
	private String eventStatus;//事务状态  D：已完成；Y：流程中；N：未提交
	private String eventTitle;//事务主题
	private String eventType;//事务类型   A：（Apply）提报；B：（adjustment）调整
	private List<DealerAdjustment> dealerAdjustmentList;
	private DealerAdjustment dealerAdjustment;
	private String detalId;//调整明细Id
	private String adjustId;//调整id
	private String applyYear;
	private String applyMonth;
	private String theYear;//申请年
	private String theMonth;//申请月
	private String matter;//品项
	private String matterName;//品项名称
	private String nowTarget;//现有销售目标量
	private String nowDealerTarget;//现有经销商目标量
	private String adjustTarget;//调整目标量
	private String userId;//申请人ID
	@Decode
	private String startDate;//开始时间
	@Decode
	private String endDate;//结束时间
	private DealerAdujstDetail dealerAdujstDetail;
	private List<DealerAdujstDetail> dealerAdujstDetailList;
	private String uploadFileFileName;
	private File uploadFile;
	private IGoalService goalService;
	private Materiel mat;
	private List<Materiel> matList = new ArrayList<Materiel>();
	@Decode
	private String value;
	@Decode
	private String bezei;
	private String mvgr1;
	private String matnr; //物料编码
	private String maktx01;//品牌名
	private String matnr01;//品牌编码
	private String index;//删除行号
	private UserUtil userUtil;// 下个处理人列表
	private BooleanResult executeResult;
	private String nextUserId;
	private String token;
	private List<Kunnr> kunnrlist;
	private List<CmsTbDict> dictlist = new ArrayList<CmsTbDict>();
	private IDictService dictServiceHessian;
	
	/**
	 * Title: 查询经销商
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年6月8日 上午9:02:47
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "kunnrList", total = "total", include = { "id", "kunnr",
			"name1", "mobNumber", "orgId", "orgName","status" })
	public String getKunner() {
		Kunnr kunnr = new Kunnr();
		if (StringUtils.isNotEmpty(orgId)) {
			kunnr.setOrgId(orgId);
		}
		try {
			if (StringUtils.isNotEmpty(value)
					&& StringUtils.isNotEmpty(value.trim())) {
				value = new String(this.getValue().getBytes("ISO8859-1"),"utf-8");
				boolean isNum = value.matches("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");// 判断是否为汉字
				if (isNum) {
					kunnr.setName1(value.trim());
				} else {
					kunnr.setKunnr(value.trim());
				}
			}

		} catch (Exception e) {
			logger.error(e);
		}
		kunnr.setStart(getStart());
		kunnr.setEnd(getEnd());
		total = goalService.getKunnrListForNormalCount(kunnr);
		if (total != 0) {
			kunnrList = goalService.getKunnrListForNormal(kunnr);
		}
		return JSON;

	}
	/**
	 * Title: 搜索经销商
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年7月8日 上午10:14:40
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "kunnrList", total = "total", include = { "id", "kunnr",
			"name1", "mobNumber", "orgId", "orgName","status" })
	public String getKunnerJsonList() {
		Kunnr kunnr = new Kunnr();
		kunnrList = new ArrayList<Kunnr>();
		try {
			kunnr.setStart(getStart());
			kunnr.setEnd(getEnd());
			if (StringUtils.isNotEmpty(value)
					&& StringUtils.isNotEmpty(value.trim())) {
				value = new String(this.getValue().getBytes("ISO8859-1"),
						"utf-8");
				// String chinese = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
				boolean isNum = value.matches("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");// 判断是否为汉字
				if (isNum) {
					kunnr.setName1(value.trim());
				} else {
					kunnr.setKunnr(value.trim());
				}
			}

		} catch (Exception e) {
			logger.error(e);
		}
		total = goalService.getKunnrListForNormalCount(kunnr);
		if (total != 0) {
			kunnrList = goalService.getKunnrListForNormal(kunnr);
		} else {
			kunnrList = null;
		}
		return JSON;
	}
	/**
	 * Title: 判断客户端提交上来的令牌和服务器端生成的令牌是否一致</br>
	 * Description: crmPlatform</br>
	 * @author lu
	 * @date 2016年7月22日 上午9:34:18
	 * @return boolean
	 * @return false 用户重复提交了表单 
	 *         true 用户没有重复提交表单
	 */
	private boolean isRepeatSubmit() {
		String client_token = token;
		//1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
		if(client_token==null){
			return false;
		}
		//取出存储在Session中的token
		String server_token = (String) this.getSession().getAttribute("token");
		//2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
		if(server_token==null){
			return false;
		}
		//3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
		if(!client_token.equals(server_token)){
			return false;
		}
		
		return true;
	}
	/**
	 * Title: 经销商总单明细之保存
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月21日 下午2:20:05
	 * @return
	 */
	public String saveToCreateDealerAdjustDetail(){
		try {
			String totalId=null;
			if(dealerAdjustment==null){
				DealerAdjustment dealerAdjustment1=new DealerAdjustment();
				dealerAdjustment1.setAdjustId("");
				dealerAdjustment1.setEventTitle(eventTitle);
				dealerAdjustment1.setApplyYear(applyYear);
				dealerAdjustment1.setApplyMonth(applyMonth);
				dealerAdjustment1.setApplyUser(applyUser);
				dealerAdjustment1.setEventStatus("N");
				dealerAdjustment1.setEventType(eventType);
				dealerAdjustment1.setUserId(this.getUser().getUserId());
				totalId=kunnrBusinessService.createDealerAdjustMennt(dealerAdjustment1);
			}
			for (DealerAdujstDetail dealerAdujstDetail : dealerAdujstDetailList) {
				DealerAdujstDetail dealer=new DealerAdujstDetail();
				dealer.setDetalId("");
				dealer.setAdjustId(totalId);
				dealer.setOrgId(this.getUser().getOrgId());
				dealer.setOrgName(dealerAdujstDetail.getOrgName());
				dealer.setKunnrId(dealerAdujstDetail.getKunnrId());
				dealer.setKunnrName(dealerAdujstDetail.getKunnrName());
				dealer.setApplyYear(dealerAdujstDetail.getApplyYear());
				dealer.setApplyMonth(dealerAdujstDetail.getApplyMonth());
				dealer.setMatter(dealerAdujstDetail.getMatter());
				dealer.setMatterName(dealerAdujstDetail.getMatterName());
				dealer.setNowTarget(dealerAdujstDetail.getNowTarget());
				dealer.setNowDealerTarget(dealerAdujstDetail.getNowDealerTarget());
				dealer.setAdjustTarget(dealerAdujstDetail.getAdjustTarget());
				kunnrBusinessService.createDealerAdjustDetail(dealer);
			}
			setSuccessMessage("保存成功");
			this.getSession().removeAttribute("dealerAdujstDetailList");
		} catch (Exception e) {
			logger.error(e);
		}
		return RESULT_MESSAGE;
	}
	/**
	 * Title: 发起事务之下一个处理人
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月21日 下午2:04:22
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "userUtil", include = { "processInstanceId", "result",
			"reason" })
	public String selectNextUserDealerAdjust(){
		String officeRole="cityManager";
		List<AllUsers> users=goalService.getStationIdByUserId(this.getUser().getUserId());
		for(AllUsers user: users){
			if(user.getPosId().equals("provincesManager")){
				officeRole="provincesManager";
				break;
			}
		}
		String processId = "fix_dealerAdjustment";
		Object[] res = new Object[4];
		res[0] = processId;
		res[1] = getUser().getUserId();
		res[2] = "executeAction,refuseAction,officeRole";
		res[3] = (appUrl + "/kunnrBusinessContact/kunnrBusinessAction!createDealerAdjustDone.jspa"
				+ "," + appUrl
				+ "/kunnrBusinessContact/kunnrBusinessAction!createDealerAdjustRefuse.jspa" + "," + officeRole);
		userUtil = wfeServiceHessian.startWorkflowFix(res);
		return JSON;
	}
	/**
	 * Title: 事务创建之保存
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月21日 下午2:14:46
	 * @return
	 */
	public String createDealerAdjust(){
		if(isRepeatSubmit()){
			this.getSession().removeAttribute("token");
			String processId = "fix_dealerAdjustment";
			this.setSuccessMessage("");
			this.setFailMessage("");
			Object[] res = new Object[7];
			res[0] = eventId;
			res[1] = this.getUser().getUserId();
			res[2] = nextUserId;//下一个处理人
			res[3] = eventTitle;//事务标题
			res[4] = appUrl + "/kunnrBusinessContact/kunnrBusinessAction!toCreateDealerAdjustView.jspa";
			res[5] = processId;
			res[6] = "";
			String result = wfeServiceHessian.processWorkflowFix(res);
			if ("success".equals(result)) {
				/*DealerAdjustment dealerAdjustment1=kunnrBusinessService.getDealerAdjustmentById(String.valueOf(id));
				if (dealerAdjustment1!=null) {
					dealerAdjustment=new DealerAdjustment();
					dealerAdjustment.setEventId(eventId);
					dealerAdjustment.setEventStatus("Y");
					dealerAdjustment.setId(id);
					int count=kunnrBusinessService.updateDealerAdjustmentById(dealerAdjustment);
					if(count==0){
						this.setFailMessage("启动失败,请重试！");
					}
				} else {*/
					if (dealerAdjustment==null) {
						dealerAdjustment=new DealerAdjustment();
						dealerAdjustment.setEventId(eventId);
						dealerAdjustment.setEventStatus("Y");
						dealerAdjustment.setAdjustId("");
						dealerAdjustment.setEventTitle(eventTitle);
						dealerAdjustment.setApplyYear(applyYear);
						dealerAdjustment.setApplyMonth(applyMonth);
						dealerAdjustment.setApplyUser(applyUser);
						dealerAdjustment.setEventType(eventType);
						dealerAdjustment.setUserId(this.getUser().getUserId());
						String totalId=kunnrBusinessService.createDealerAdjustMennt(dealerAdjustment);
						if(dealerAdujstDetailList!=null&&dealerAdujstDetailList.size()>0){
							for (DealerAdujstDetail dealerAdujstDetail : dealerAdujstDetailList) {
								DealerAdujstDetail detail=new DealerAdujstDetail();
								detail.setAdjustId(totalId);
								if (orgId!=null) {
									detail.setOrgId(orgId);
								} else {
									detail.setOrgId(this.getUser().getOrgId());
								}
								detail.setOrgName(dealerAdujstDetail.getOrgName());
								detail.setKunnrId(dealerAdujstDetail.getKunnrId());
								detail.setKunnrName(dealerAdujstDetail.getKunnrName());
								detail.setApplyYear(dealerAdujstDetail.getApplyYear());
								detail.setApplyMonth(dealerAdujstDetail.getApplyMonth());
								detail.setMatter(dealerAdujstDetail.getMatter());
								detail.setMatterName(dealerAdujstDetail.getMatterName());
								detail.setNowTarget(dealerAdujstDetail.getNowTarget());
								detail.setNowDealerTarget(dealerAdujstDetail.getNowDealerTarget());
								detail.setAdjustTarget(dealerAdujstDetail.getAdjustTarget());
								kunnrBusinessService.createDealerAdjustDetail(detail);
							}
						}
					}
				//}
				this.setSuccessMessage("事务启动成功,事务号为：" + eventId);
			} else {
				this.setFailMessage("启动失败,请重试！");
			}
			this.getSession().removeAttribute("dealerAdujstDetailList");
		}
		return RESULT_MESSAGE;
	}
	/**
	 * Title: 事务添加成功
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月21日 下午2:09:20
	 * @return
	 */
	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String createDealerAdjustDone() {
		BooleanResult result = new BooleanResult();
		if (StringUtils.isNotEmpty(eventId)) {
			dealerAdjustment=new DealerAdjustment();
			dealerAdjustment.setEventId(eventId);
			dealerAdjustment.setEventStatus("D");
			int count = kunnrBusinessService.updateDealerAdjustment(dealerAdjustment);
			if (count > 0) {
				result.setResult(true);
				result.setCode("流程审批完成！</br>");
				
				 //将调整的经销商目标更新到目标量表中
				total=kunnrBusinessService.getDealerAdjustmentCount(dealerAdjustment);
				dealerAdjustment.setStart(getStart());
				dealerAdjustment.setEnd(total);
				List<DealerAdjustment> dealerAdjustments=kunnrBusinessService.getDealerAdjustmentList(dealerAdjustment);
				Long totalId=dealerAdjustments.get(0).getId();
				dealerAdujstDetail=new DealerAdujstDetail();
				dealerAdujstDetail.setAdjustId(String.valueOf(totalId));
				dealerAdujstDetailList=kunnrBusinessService.getDealerAdjustDetailList(dealerAdujstDetail);
				if(dealerAdujstDetailList!=null&&dealerAdujstDetailList.size()>0){
					for (DealerAdujstDetail dealerAdujstDetail : dealerAdujstDetailList) {
						DealerAdujstDetail detail=new DealerAdujstDetail();
						detail.setApplyYear(dealerAdujstDetail.getApplyYear());
						detail.setApplyMonth(dealerAdujstDetail.getApplyMonth());
						detail.setKunnrId(dealerAdujstDetail.getKunnrId());
						detail.setMatter(dealerAdujstDetail.getMatter());
						detail.setAdjustTarget(dealerAdujstDetail.getAdjustTarget());
						int count1=kunnrBusinessService.updateCrmTbTarget(detail);
						if(count1==0){//如果在目标报表中的经销商销售目标量更新为0条的时候，说明原有数据中没有，创建一条经销商调整销售目标量记录
							//创建新的经销商调整的目标量记录
							BCustomerTarget bt = new BCustomerTarget();
							bt.setCustId(dealerAdujstDetail.getKunnrId());
							bt.setOrgId(dealerAdujstDetail.getOrgId());
							bt.setOrgName(dealerAdujstDetail.getOrgName());
							bt.setMatter(dealerAdujstDetail.getMatter());
							bt.setBox(dealerAdujstDetail.getAdjustTarget());
							bt.setEventId(dealerAdujstDetail.getEventId());
							bt.setTheYear(Long.parseLong(dealerAdujstDetail.getApplyYear()));
							bt.setTheMonth(dealerAdujstDetail.getApplyMonth());
							bt.setOpId(dealerAdujstDetail.getUserId());
							bt.setCtState("0");
							bt.setTrFlag("S");
							bt.setKunnrGoalType("C");//合同目标量
							bt.setMark("Y");
							BooleanResult flag=goalService.insertGoal(bt);
							if(!flag.getResult()){
								result.setCode("目标量保存失败，请联系管理员！</br>");
								break;
							}
						}
					}
				}
			} else {
				result.setResult(true);
				result.setCode("审批流程出错，请联系管理员！</br>");
			}
		}
		setExecuteResult(result);
		return JSON;
	}
	/**
	 * Title: 取消事务
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月21日 下午2:09:20
	 * @return
	 */
	@JsonResult(field = "executeResult", responseFormat = "jsonp")
	public String createDealerAdjustRefuse() {
		BooleanResult result = new BooleanResult();
		if (StringUtils.isNotEmpty(eventId)) {
			dealerAdjustment=new DealerAdjustment();
			dealerAdjustment.setEventId(eventId);
			dealerAdjustment.setEventStatus("S");
			int count = kunnrBusinessService.updateDealerAdjustment(dealerAdjustment);
			if (count > 0) {
				result.setResult(true);
				result.setCode("流程审批完成！</br>");
			} else {
				result.setResult(false);
				result.setCode("审批流程出错，请联系管理员！</br>");
			}
		}
		setExecuteResult(result);
		return JSON;
	}
	/**
	 * Title: 事务处理页面之跳转
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月25日 下午3:47:10
	 * @return
	 */
	public String toCreateDealerAdjustView(){
		DealerAdjustment dealerAdjustment1=new DealerAdjustment();
		if (StringUtils.isNotEmpty(eventId)) {
			dealerAdjustment1.setEventId(eventId);
		}
		DealerAdjustment dealerAdjustment2=kunnrBusinessService.getDealerAdjustment(dealerAdjustment1);
		if(null!=dealerAdjustment2){
			dealerAdjustment=new DealerAdjustment();
			dealerAdjustment.setEventTitle(dealerAdjustment2.getEventTitle());
			dealerAdjustment.setEventType(dealerAdjustment2.getEventType());
			dealerAdjustment.setApplyYear(dealerAdjustment2.getApplyYear());
			dealerAdjustment.setApplyMonth(dealerAdjustment2.getApplyMonth());
			dealerAdjustment.setApplyUser(dealerAdjustment2.getApplyUser());
			dealerAdjustment.setId(dealerAdjustment2.getId());
			dealerAdjustment.setEventStatus(dealerAdjustment2.getEventStatus());
		}
		return "toCreateDealerAdjustView";
	}
	/**
	 * Title: 事务处理页面之数据显示
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月30日 上午10:16:43
	 * @return
	 */
	@JsonResult(field="dealerAdujstDetailList",include={
			"detalId","adjustId","eventId","orgId","orgName","kunnrId","kunnrName",
			"applyYear","applyMonth","maktx01","matnr01","matter","matterName",
			"nowTarget","nowDealerTarget","adjustTarget"})
	public String getDealerAdjustDelJsonList(){
		if(id!=null){
			dealerAdujstDetail=new DealerAdujstDetail();
			if(StringUtils.isNotEmpty(String.valueOf(id))){
				dealerAdujstDetail.setAdjustId(String.valueOf(id));
			}
			dealerAdujstDetailList=kunnrBusinessService.getDealerAdjustDetailList(dealerAdujstDetail);
		}
		return JSON;
	}
	/**
	 * Title: 提交未完成调整页面
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月23日 下午4:55:16
	 * @return
	 */
	public String toDealerAdjustmentSub(){
		token = TokenProccessor.getInstance().makeToken();//创建令牌
		this.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)
		ids=String.valueOf(id);
		dealerAdjustment=new DealerAdjustment();
		DealerAdjustment dealerAdjustment1=kunnrBusinessService.getDealerAdjustmentById(ids);
		dealerAdjustment.setEventTitle(dealerAdjustment1.getEventTitle());
		dealerAdjustment.setEventType(dealerAdjustment1.getEventType());
		dealerAdjustment.setApplyUser(dealerAdjustment1.getApplyUser());
		dealerAdjustment.setApplyYear(dealerAdjustment1.getApplyYear());
		dealerAdjustment.setApplyMonth(dealerAdjustment1.getApplyMonth());
		dealerAdjustment.setEventStatus(dealerAdjustment1.getEventStatus());;
		orgName=this.getUser().getOrgName();
		orgId=this.getUser().getOrgId();
		return "toDealerAdjustmentSub";
	}
	/**
	 * Title: 查看已完成或者是流程中的调整的页面
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月23日 下午4:55:16
	 * @return
	 */
	public String toDealerAdjustmentView(){
		token = TokenProccessor.getInstance().makeToken();//创建令牌
		this.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)
		dealerAdjustment=new DealerAdjustment();
		DealerAdjustment dealerAdjustment1=kunnrBusinessService.getDealerAdjustmentById(String.valueOf(id));
		if (null!=dealerAdjustment1) {
			dealerAdjustment.setEventTitle(dealerAdjustment1.getEventTitle());
			dealerAdjustment.setEventType(dealerAdjustment1.getEventType());
			dealerAdjustment.setApplyUser(dealerAdjustment1.getApplyUser());
			dealerAdjustment.setApplyYear(dealerAdjustment1.getApplyYear());
			dealerAdjustment.setApplyMonth(dealerAdjustment1.getApplyMonth());
			dealerAdjustment.setEventStatus(dealerAdjustment1.getEventStatus());
			dealerAdjustment.setId(dealerAdjustment1.getId());
			orgName=this.getUser().getOrgName();
			orgId=this.getUser().getOrgId();
		}
		return "toDealerAdjustmentView";
	}
	/**
	 * Title: 新增经销商调整
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月17日 下午3:44:12
	 * @return
	 */
	public String toAddDealerAdjustment(){
		token = TokenProccessor.getInstance().makeToken();//创建令牌
		this.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		String dateNow=sdf.format(date);
		String year=dateNow.substring(0, 4);
		String month=dateNow.substring(4,6);
		dealerAdjustment=new DealerAdjustment();
		dealerAdjustment.setApplyUser(this.getUser().getUserName());
		dealerAdjustment.setApplyYear(year);
		dealerAdjustment.setApplyMonth(month);
		orgName=this.getUser().getOrgName();
		orgId=this.getUser().getOrgId();
		return "toAddDealerAdjustment";
	}
	/**
	 * MethodsTitle: 经销商目标量提报调整下载
	 * @author: xg.chen
	 * @date:2016年11月3日  version1.1  导出数据优化
	 * @return
	 */
	public String exportMouldCsv() {
		setSuccessMessage("");
		setFailMessage("");
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "协议目标量提报调整模板.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);

			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置单元格背景颜色
			cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// 设置字体格式
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// 设置居中
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置表格边框
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			WritableCellFormat cellFormat_bottom_left = new WritableCellFormat();  
			cellFormat_bottom_left.setAlignment(jxl.format.Alignment.LEFT);
			cellFormat_bottom_left.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
			WritableSheet wsheet = wbook.createSheet("协议目标量提报调整模板", 0);

			wsheet.setRowView(0, 300);
			wsheet.setRowView(1, 300);
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 55);
			wsheet.setColumnView(2, 35);
			wsheet.setColumnView(3, 40);
			wsheet.setColumnView(4, 40);
			wsheet.setColumnView(5, 25);
			wsheet.setColumnView(6, 20);
			wsheet.setColumnView(7, 30);
			wsheet.setColumnView(8, 30);
			wsheet.setColumnView(9, 30);

			Label label_0 = new Label(0, 0, "调整组织");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "调整经销商代码(注意此处为文本格式，例如：01XX)");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);

			Label label_2 = new Label(2, 0, "调整经销商名称");
			label_2.setCellFormat(cellFormat_top);
			wsheet.addCell(label_2);

			Label label_3 = new Label(3, 0, "调整年(注意此处为文本格式，例如：2016)");
			label_3.setCellFormat(cellFormat_top);
			wsheet.addCell(label_3);
			
			Label label_4 = new Label(4, 0, "调整月(注意此处为文本格式，例如：06或10)");
			label_4.setCellFormat(cellFormat_top);
			wsheet.addCell(label_4);

			Label label_5 = new Label(5, 0, "品项");
			label_5.setCellFormat(cellFormat_top);
			wsheet.addCell(label_5);

			Label label_6 = new Label(6, 0, "品项名称");
			label_6.setCellFormat(cellFormat_top);
			wsheet.addCell(label_6);
			
			Label label_7 = new Label(7, 0, "现有销售目标量");
			label_7.setCellFormat(cellFormat_top);
			wsheet.addCell(label_7);
			
			Label label_8 = new Label(8, 0, "现有协议目标量");
			label_8.setCellFormat(cellFormat_top);
			wsheet.addCell(label_8);
			
			Label label_9 = new Label(9, 0, "调整至协议目标量（标箱）");
			label_9.setCellFormat(cellFormat_top);
			wsheet.addCell(label_9);
			//根据KunnrId在目标量表中找到相关的信息并写入
			dealerAdujstDetail=new DealerAdujstDetail();
			if (StringUtils.isNotEmpty(kunnrId)&&StringUtils.isNotEmpty(kunnrId.trim())) {
				dealerAdujstDetail.setKunnrId(kunnrId);
			}
			dealerAdujstDetailList=kunnrBusinessService.getKunnrForCrmTarget(dealerAdujstDetail);
			if (dealerAdujstDetailList.size()!=0) {
				for (int i = 0; i < dealerAdujstDetailList.size(); i++) {
					DealerAdujstDetail detail = new DealerAdujstDetail();
					detail=dealerAdujstDetailList.get(i);
					Label label_00 = new Label(0, (i+1), detail.getOrgName()==null?"":detail.getOrgName());
					label_00.setCellFormat(cellFormat_bottom_1);
					wsheet.addCell(label_00);
					Label label_01 = new Label(1, (i+1), detail.getKunnrId()==null?"":detail.getKunnrId());
					label_01.setCellFormat(cellFormat_bottom_left);
					wsheet.addCell(label_01);
					Label label_02 = new Label(2, (i+1), detail.getKunnrName()==null?"":detail.getKunnrName());
					label_02.setCellFormat(cellFormat_bottom_left);
					wsheet.addCell(label_02);
					Label label_03 = new Label(3, (i+1), detail.getApplyYear()==null?"":detail.getApplyYear());
					label_03.setCellFormat(cellFormat_bottom_left);
					wsheet.addCell(label_03);
					Label label_04 = new Label(4, (i+1), detail.getApplyMonth()==null?"":detail.getApplyMonth());
					label_04.setCellFormat(cellFormat_bottom_left);
					wsheet.addCell(label_04);
					Label label_05 = new Label(5, (i+1), detail.getMatter()==null?"": detail.getMatter());
					label_05.setCellFormat(cellFormat_bottom_left);
					wsheet.addCell(label_05);
					Label label_06 = new Label(6, (i+1), detail.getMatterName()==null?"": detail.getMatterName());
					label_06.setCellFormat(cellFormat_bottom_left);
					wsheet.addCell(label_06);
					Label label_07 = new Label(7, (i+1), detail.getNowTarget()==null?"0": detail.getNowTarget());
					label_07.setCellFormat(cellFormat_bottom_left);
					wsheet.addCell(label_07);
					Label label_08 = new Label(8, (i+1), detail.getNowDealerTarget()==null?"0": detail.getNowDealerTarget());
					label_08.setCellFormat(cellFormat_bottom_left);
					wsheet.addCell(label_08);
					Label label_09 = new Label(9, (i+1), detail.getAdjustTarget()==null?"": detail.getAdjustTarget());
					label_09.setCellFormat(cellFormat_bottom_left);
					wsheet.addCell(label_09);
				}
			}
			wbook.write();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error(e);
				}
				os = null;
			}
		}
		return RESULT_MESSAGE;
	}
	/**
	 * MethodsTitle: 协议目标量调整数据之excel数据模板导入 
	 * @author: xg.chen
	 * @date:2016年11月3日 Version1.1 数据导入优化
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String importDealerAdjustmentCsv(){
		setSuccessMessage("");
		setFailMessage("");
		if (StringUtils.isEmpty(uploadFileFileName)) {
			setFailMessage("导入失败！");
			return RESULT_MESSAGE;
		}
		if (StringUtils.isEmpty(orgId) || StringUtils.isEmpty(kunnrId)) {
			setFailMessage("请选择导入的经销商组织和组织中所对应的经销商！");
			return RESULT_MESSAGE;
		}
		String fileName = this.uploadFileFileName.substring(this.uploadFileFileName.length() - 3,
				this.uploadFileFileName.length());
		if("xls".equalsIgnoreCase(fileName)){
			//设置数据导入控制年月
			CmsTbDictType cm = new CmsTbDictType();
			cm.setDictTypeName("目标量调整提报年月");
			cm.setPagination("false");
			dictlist = dictServiceHessian.getDictListByDictType(cm);
			int yearMoth=0;
			if(dictlist!=null && dictlist.size()>0){
				yearMoth=Integer.parseInt(dictlist.get(0).getItemValue());
			}
			//导入模板
			Map<String, Object> map=kunnrBusinessService.
					importDealerAdjustmentXls(uploadFile,this.getUser().getUserId(),orgId,kunnrId,yearMoth);
			String msg=(String)map.get("resultMessage");
			if (StringUtils.isNotEmpty(msg)) {
				setFailMessage(msg);//返回错误信息
			}else{
				dealerAdujstDetailList=(List<DealerAdujstDetail>)map.get("dealerAdujstDetailList");
				this.getSession().setAttribute("dealerAdujstDetailList", dealerAdujstDetailList);
				setSuccessMessage("导入成功");
			}
		}else {
			setFailMessage("导入的文件格式错误");
		}
		return RESULT_MESSAGE;
	}
	/**
	 * Title: 获取新增调整列表
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月18日 下午4:19:53
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@JsonResult(field="dealerAdujstDetailList",include={
			"detalId","adjustId","eventId","orgId","orgName","kunnrId","kunnrName",
			"applyYear","applyMonth","matnr01","maktx01","matter","matterName","nowTarget","nowDealerTarget",
			"adjustTarget"})
	public String getAddDealerAdjustmentJsonList(){
		dealerAdujstDetailList=(List<DealerAdujstDetail>) this.getSession().getAttribute("dealerAdujstDetailList");
		if(dealerAdujstDetailList==null && id!=null){
			dealerAdujstDetail=new DealerAdujstDetail();
			if(StringUtils.isNotEmpty(String.valueOf(id))){
				dealerAdujstDetail.setAdjustId(String.valueOf(id));
			}
			dealerAdujstDetailList=kunnrBusinessService.getDealerAdjustDetailList(dealerAdujstDetail);
		}
		return JSON;
	}
	/**
	 * Title: 品项列表下载
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月19日 上午10:57:00
	 * @return
	 */
	public String exportDealerAdjustmentMaterCsv(){
		setSuccessMessage("");
		setFailMessage("");
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "品项列表.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);

			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);

			// WritableSheet.setColumnView(int i,int width)
			// 作用是指定第i+1列的宽度，比如：
			// 将第一列的宽度设为30
			// sheet.setColumnView(0,30)
			// wsheet.setRowView(0,10)
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置单元格背景颜色
			cellFormat_top.setBackground(Colour.LIGHT_BLUE);
			// 设置字体格式
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom = new WritableCellFormat();
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
			// 设置居中
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置表格边框
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			WritableCellFormat cellFormat_bottom_left = new WritableCellFormat();  
			// 设置居中
			cellFormat_bottom_left.setAlignment(jxl.format.Alignment.LEFT);
			// 设置表格边框
			cellFormat_bottom_left.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			WritableSheet wsheet = wbook.createSheet("品项列表", 0);

			wsheet.setRowView(0, 300);
			wsheet.setRowView(1, 300);
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 50);

			Label label_0 = new Label(0, 0, "品项");
			label_0.setCellFormat(cellFormat_top);
			wsheet.addCell(label_0);

			Label label_1 = new Label(1, 0, "品项名称");
			label_1.setCellFormat(cellFormat_top);
			wsheet.addCell(label_1);
			//填入数据
			mat=new Materiel();
			if (StringUtils.isNotEmpty(bezei)) {
				mat.setBezei(bezei);
			}
			if (StringUtils.isNotEmpty(mvgr1)) {
				mat.setMvgr1(mvgr1);
			}
			if (StringUtils.isNotEmpty(value)) {
				try {
					value = new String(this.getValue().getBytes("ISO8859-1"),
							"utf-8");
					mat.setSearch(value);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			total = goalService.getMaterielListCount(mat);
			mat.setStart(getStart());
			mat.setEnd(total);
			matList = goalService.getMaterielViewList(mat);
			if (null!=matList) {
				for (int i = 0; i < matList.size(); i++) {
					Materiel materiel=new Materiel();
					materiel=matList.get(i);
					Label label_00 = new Label(0, (i+1), materiel.getMvgr1()==null?"":materiel.getMvgr1());
					label_00.setCellFormat(cellFormat_bottom_1);
					wsheet.addCell(label_00);
					Label label_01 = new Label(1, (i+1), materiel.getBezei()==null?"":materiel.getBezei());
					label_01.setCellFormat(cellFormat_bottom_left);
					wsheet.addCell(label_01);
				}
			}
			wbook.write();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error(e);
				}
				os = null;
			}
		}
		return RESULT_MESSAGE;
	}
	/**
	 * Title: 经销商基础页面跳转
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月17日 上午10:03:57
	 * @return toDealerAdjustment
	 */
	@PermissionSearch
	public String toDealerAdjustment(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		String dateNow=sdf.format(date);
		applyYear=dateNow.substring(0, 4);
		applyMonth=dateNow.substring(4,6);
		orgId = this.getUser().getOrgId();		
		orgName = orgServiceHessian.getOrgNameByOrgid(orgId);
		return "toDealerAdjustment";
	}
	/**
	 * MethodsTitle: Title: 获取经销商目标量提报调整事务列表
	 * Description: crmPlatform
	 * @author: xg.chen
	 * @date:2016年11月17日 上午10:08:47
	 * @version 1.1     优化时间查询条件
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@PermissionSearch
	@JsonResult(field = "dealerAdjustmentList", 
	include = {"id","kunnrId","eventId","applyUser","applyYear","applyMonth","eventStatus","eventTitle",
			"eventType","orgId"}, 
	total = "total")
	public String getDealerAdjustmentJsonList() throws UnsupportedEncodingException {
		dealerAdjustment=new DealerAdjustment();
		/*//销售部的人员登录，根据组织树追溯，非销售部的查看所有事务
		String orgs=orgServiceHessian.getAllChildOrgidByOrgId("51235");
		String[] orgs1 = orgs.split(",");
		for(int i=0;i<orgs1.length;i++){
			if(orgs1[i].equals(this.getUser().getOrgId())){
				orgId=this.getUser().getOrgId();
				break;
			}
		}
		if(orgId!=null){
			dealerAdjustment.setOrgId(orgId);
		}*/
		/*根据登录人员的userId在sql中控制查看数据的权限*/
		dealerAdjustment.setUserId(this.getUser().getUserId());
		if (StringUtils.isNotEmpty(eventId)&&StringUtils.isNotEmpty(eventId.trim())) {
			dealerAdjustment.setEventId(eventId);
		}
		if (StringUtils.isNotEmpty(applyUser)&&StringUtils.isNotEmpty(applyUser.trim())) {
			String  applyUser1=java.net.URLDecoder.decode(applyUser,"UTF-8");//解码（encodeURIComponent()编码）
			dealerAdjustment.setApplyUser(applyUser1);
		}
		if (StringUtils.isNotEmpty(startDate)) {
			dealerAdjustment.setStartDate(startDate.replace("年", "-").replace("月", ""));
		}
		if (StringUtils.isNotEmpty(endDate)) {
			dealerAdjustment.setEndDate(endDate.replace("年", "-").replace("月", ""));
		}
		if (StringUtils.isNotEmpty(eventStatus)&&StringUtils.isNotEmpty(eventStatus.trim())) {
			dealerAdjustment.setEventStatus(eventStatus);
		}
		if (StringUtils.isNotEmpty(eventTitle)&&StringUtils.isNotEmpty(eventTitle.trim())) {
			dealerAdjustment.setEventTitle(eventTitle);
		}
		if (StringUtils.isNotEmpty(eventType)&&StringUtils.isNotEmpty(eventType.trim())) {
			dealerAdjustment.setEventType(eventType);
		}
		dealerAdjustment.setStart(getStart());//分页值设置
		dealerAdjustment.setEnd(getEnd());//分页值设置
		total=kunnrBusinessService.getDealerAdjustmentCount(dealerAdjustment);
		if (total!=0) {
			dealerAdjustmentList=kunnrBusinessService.getDealerAdjustmentList(dealerAdjustment);
		}
		return JSON;
	}
	/**
	 * MethodsTitle: 导出经销商目标量调整表
	 * @author: xg.chen
	 * @date:2016年11月3日 version1.1 导出经销商目标量调整表数据优化
	 * @return
	 */
	public String exportDealerAdjustData(){
		setSuccessMessage("");
		setFailMessage("");
		OutputStream os=null;
		WritableWorkbook wbook = null;
		String report_name = "协议目标量调整表";
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".xls\"");
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = jxl.Workbook.createWorkbook(os);
			//WritableSheet wsheet = wbook.createSheet("协议目标量调整表", 0);
			
			//设置行高度
			//wsheet.setRowView(0,400);
			
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			//表头
			WritableCellFormat cellFormat_top = new WritableCellFormat();
			cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置单元格背景颜色
			 cellFormat_top.setBackground(Colour.SKY_BLUE);
			// 设置字体格式
			cellFormat_top.setFont(font1);
			cellFormat_top.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
            
			WritableCellFormat cellFormat_bottom = new WritableCellFormat(NumberFormats.TEXT);
			cellFormat_bottom.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
            //主体
			WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();  
			// 设置居中
			cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
			// 设置表格边框
			cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			WritableCellFormat cellFormat_bottom_left = new WritableCellFormat();  
			// 设置居中
			cellFormat_bottom_left.setAlignment(jxl.format.Alignment.LEFT);
			// 设置表格边框
			cellFormat_bottom_left.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			//填写说明表格格式设置
			WritableCellFormat formatTxt = new WritableCellFormat();
			// 设置单元格背景颜色
			formatTxt.setBackground(Colour.SKY_BLUE);
			// 设置字体格式
			formatTxt.setFont(font1);
			formatTxt.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			formatTxt.setWrap(true);  

			//1、写入数据
			dealerAdjustment=new DealerAdjustment();
			//根据登录人员的userId在sql中控制查看数据的权限
			dealerAdjustment.setUserId(this.getUser().getUserId());
			if (StringUtils.isNotEmpty(eventId) && StringUtils.isNotEmpty(eventId.trim())) {
				dealerAdjustment.setEventId(eventId.trim());
			}
			if(StringUtils.isNotEmpty(applyYear) && StringUtils.isNotEmpty(applyYear.trim())){
				dealerAdjustment.setApplyYear(applyYear);
			}
			if (StringUtils.isNotEmpty(startDate)) {
				dealerAdjustment.setStartDate(startDate.replace("年", "-").replace("月", ""));
			}
			if (StringUtils.isNotEmpty(endDate)) {
				dealerAdjustment.setEndDate(endDate.replace("年", "-").replace("月", ""));
			}
			if(StringUtils.isNotEmpty(eventStatus) && StringUtils.isNotEmpty(eventStatus.trim())){
				dealerAdjustment.setEventStatus(eventStatus);
			}
			if(StringUtils.isNotEmpty(eventType) && StringUtils.isNotEmpty(eventType.trim())){
				dealerAdjustment.setEventType(eventType);
			}
			total=kunnrBusinessService.getDealerAdjustmentCount(dealerAdjustment);
			dealerAdjustment.setStart(getStart());
			dealerAdjustment.setEnd(total);
			dealerAdjustmentList=kunnrBusinessService.getDealerAdjustmentListForXls(dealerAdjustment);
			
			WritableSheet ws = wbook.createSheet("协议目标量调整表", 0); //创建一个可写入的工作表
			
			//2、导出的表头表头
			Label label_0 = new Label(0, 0, "事务编号");
			label_0.setCellFormat(cellFormat_top);
			ws.addCell(label_0);
			ws.setColumnView(0, 10);
			Label label_1 = new Label(1, 0, "标题");
			label_1.setCellFormat(cellFormat_top);
			ws.addCell(label_1);
			ws.setColumnView(1, 25);
			Label label_2 = new Label(2, 0, "申请年");
			label_2.setCellFormat(cellFormat_top);
			ws.addCell(label_2);
			ws.setColumnView(2, 10);
			Label label_3 = new Label(3, 0, "申请月");
			label_3.setCellFormat(cellFormat_top);
			ws.addCell(label_3);
			ws.setColumnView(3, 10);
			Label label_4 = new Label(4, 0, "申请人");
			label_4.setCellFormat(cellFormat_top);
			ws.addCell(label_4);
			ws.setColumnView(4, 10);
			Label label_5 = new Label(5, 0, "事务状态");
			label_5.setCellFormat(cellFormat_top);
			ws.addCell(label_5);
			ws.setColumnView(5, 10);
			Label label_6 = new Label(6, 0, "事务类型");
			label_6.setCellFormat(cellFormat_top);
			ws.addCell(label_6);
			ws.setColumnView(6, 20);
			Label label_7 = new Label(7, 0, "组织");
			label_7.setCellFormat(cellFormat_top);
			ws.addCell(label_7);
			ws.setColumnView(7, 18);
			Label label_8 = new Label(8, 0, "经销商代码");
			label_8.setCellFormat(cellFormat_top);
			ws.addCell(label_8);
			ws.setColumnView(8, 10);
			Label label_9 = new Label(9, 0, "经销商名称");
			label_9.setCellFormat(cellFormat_top);
			ws.addCell(label_9);
			ws.setColumnView(9, 30);
			Label label_10 = new Label(10, 0, "提报/调整年");
			label_10.setCellFormat(cellFormat_top);
			ws.addCell(label_10);
			ws.setColumnView(10, 15);
			Label label_11 = new Label(11, 0, "提报/调整月");
			label_11.setCellFormat(cellFormat_top);
			ws.addCell(label_11);
			ws.setColumnView(11, 15);
			Label label_12 = new Label(12, 0, "品牌");
			label_12.setCellFormat(cellFormat_top);
			ws.addCell(label_12);
			Label label_13 = new Label(13, 0, "品项");
			label_13.setCellFormat(cellFormat_top);
			ws.addCell(label_13);
			ws.setColumnView(13, 15);
			Label label_14 = new Label(14, 0, "提报标箱");
			label_14.setCellFormat(cellFormat_top);
			ws.addCell(label_14);
			ws.setColumnView(14, 15);
			Label label_15 = new Label(15, 0, "现有销售目标量");
			label_15.setCellFormat(cellFormat_top);
			ws.addCell(label_15);
			ws.setColumnView(15, 15);
			Label label_16 = new Label(16, 0, "现有协议目标量");
			label_16.setCellFormat(cellFormat_top);
			ws.addCell(label_16);
			ws.setColumnView(16, 15);
			Label label_17= new Label(17, 0, "调整后协议目标量");
			label_17.setCellFormat(cellFormat_top);
			ws.addCell(label_17);
			ws.setColumnView(17, 15);
			
			//3.写入模板数据实例
			for (int m = 0; m < dealerAdjustmentList.size(); m++) {
				DealerAdjustment dealer=new DealerAdjustment();
				dealer=dealerAdjustmentList.get(m);
				Label label_00 = new Label(0, (m+1), dealer.getEventId()==null?"":String.valueOf(dealer.getEventId()));
				label_00.setCellFormat(cellFormat_bottom_1);
				ws.addCell(label_00);
				Label label_01 = new Label(1, (m+1), dealer.getEventTitle()==null?"":dealer.getEventTitle());
				label_01.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_01);
				Label label_02 = new Label(2, (m+1), dealer.getApplyYear()==null?"":dealer.getApplyYear());
				label_02.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_02);
				Label label_03 = new Label(3, (m+1), dealer.getApplyMonth()==null?"":dealer.getApplyMonth());
				label_03.setCellFormat(cellFormat_bottom_1);
				ws.addCell(label_03);
				Label label_04 = new Label(4, (m+1), dealer.getApplyUser()==null?"":dealer.getApplyUser());
				label_04.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_04);
				String eventStatus="";
				if(null!=dealer.getEventStatus()){
					eventStatus=dealer.getEventStatus().replace("D", "已完成").replace("Y", "流程中").replace("N", "未提交").replace("S", "已取消");
				}
				Label label_05 = new Label(5, (m+1), dealer.getEventStatus()==null?"":eventStatus);
				label_05.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_05);
				String eventType="";
				if(null!=dealer.getEventType()){
					eventType=dealer.getEventType().replace("A", "协议提报").replace("B", "协议调整");
				}
				Label label_06 = new Label(6, (m+1), dealer.getEventType()==null?"":eventType);
				label_06.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_06);
				Label label_07 = new Label(7, (m+1), dealer.getOrgName()==null?"":dealer.getOrgName());
				label_07.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_07);
				Label label_08 = new Label(8, (m+1), dealer.getKunnrId()==null?"":dealer.getKunnrId());
				label_08.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_08);
				Label label_09 = new Label(9, (m+1), dealer.getKunnrName()==null?"":dealer.getKunnrName());
				label_09.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_09);
				Label label_010 = new Label(10, (m+1), dealer.getTheYear()==null?"":dealer.getTheYear());
				label_010.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_010);
				Label label_011 = new Label(11, (m+1), dealer.getTheMonth()==null?"":dealer.getTheMonth());
				label_011.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_011);
				Label label_012 = new Label(12, (m+1), dealer.getMaktx01()==null?"":dealer.getMaktx01());
				label_012.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_012);
				Label label_013 = new Label(13, (m+1), dealer.getMatterName()==null?"":dealer.getMatterName());
				label_013.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_013);
				Label label_014 = new Label(14, (m+1), dealer.getAdjustTarget()==null?"":dealer.getAdjustTarget());
				label_014.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_014);
				Label label_015 = new Label(15, (m+1), dealer.getNowTarget()==null?"":dealer.getNowTarget());
				label_015.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_015);
				Label label_016 = new Label(16, (m+1), dealer.getNowDealerTarget()==null?"":dealer.getNowDealerTarget());
				label_016.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_016);
				Label label_017 = new Label(17, (m+1), dealer.getAdjustTarget()==null?"":dealer.getAdjustTarget());
				label_017.setCellFormat(cellFormat_bottom_left);
				ws.addCell(label_017);
			}
			//4.从内存中写入文件中
			wbook.write();
			setSuccessMessage("导出成功");
		} catch (Exception e) {
			logger.error(e);
			setFailMessage("导出失败，请与管理员联系！");
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error(e);
				}
				os = null;
			}
		}
		return RESULT_MESSAGE;
	}
	/**
	 * 经销商查询维护跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String kunnrBusinessSearch() {
		orgId = this.getUser().getOrgId();		
		orgName = orgServiceHessian.getOrgNameByOrgid(orgId);
		return "kunnrBusinessSearch";

	}
	
	public String toSearchKunnrBusinessReport(){
		return "toSearchKunnrBusinessReport";
	}
	
	public String toSearchKunnrBusinessEmp(){
		return "toSearchKunnrBusinessEmp";
	}
	
	@PermissionSearch
	@JsonResult(field = "kunnrList", include = { "kunnr", "name1", "name3",
			"mobNumber", "bukrs", "channelName", "konzs", "street1",
			"telNumber", "businessManager", "businessCompetent", "status",
			"orgId", "orgName","staffNumber","staffNumberY","type" }, total = "total")
	public String kunnrSearch() {
		kunnrBusiness = new KunnrBusiness();
		List<KunnrBusiness> kunnrHeads = new ArrayList<KunnrBusiness>();
		List<KunnrBusiness> kunnrAgents = new ArrayList<KunnrBusiness>();
		
		if(StringUtils.isNotEmpty(businessHead) || StringUtils.isNotEmpty(businessAgent)){
			if(StringUtils.isNotEmpty(businessHead)){
				kunnrBusiness.setBusinessHead(businessHead);
				kunnrHeads=kunnrBusinessService.getKunnrBusinessHeadList(kunnrBusiness);
			}
			if(StringUtils.isNotEmpty(businessAgent)){
				kunnrBusiness.setBusinessAgent(businessAgent);
				kunnrAgents=kunnrBusinessService.getKunnrBusinessAgentList(kunnrBusiness);
			}
			if((kunnrHeads!=null && kunnrHeads.size()!=0) || 
					(kunnrAgents!=null && kunnrAgents.size()!=0)){
				kunnrs=new String[kunnrHeads.size()+kunnrAgents.size()];
				if(kunnrHeads.size()!=0){
					for(int i=0;i<kunnrHeads.size();i++){
						kunnrs[i]=kunnrHeads.get(i).getKunnr();
					}
				}
				if(kunnrAgents.size()!=0){
					for(int i=0;i<kunnrAgents.size();i++){
						kunnrs[i+kunnrHeads.size()]=kunnrAgents.get(i).getKunnr();
					}
				}
			}else if(kunnrHeads.size()==0 && kunnrAgents.size()==0){
				kunnrs=new String[]{""};
			}
		}
		
		kunnr = new Kunnr();
		kunnr.setStart(getStart());
		kunnr.setEnd(getEnd());
		if (StringUtils.isNotEmpty(kunnrId)) {
			kunnr.setKunnr(kunnrId);
		}else if(kunnrs!=null&&kunnrs[0]!=null){
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
			kunnr.setChannelId(channelId);
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
		total = kunnrService.kunnrSearchCount(kunnr);
		if (total != 0) {
			kunnrList = kunnrService.kunnrSearch(kunnr);
		}
		return JSON;
	}
	
	/**
	 * 查看经销商业务联系
	 * 
	 * @return
	 */
	public String updateKunnrBusiness() {
		kunnr= new Kunnr();
		kunnr.setKunnr(kunnrId);
		kunnrBusiness = kunnrBusinessService.getKunnrBusiness(kunnr);
		return "updateKunnrBusiness";
	}
	
	@PermissionSearch
	@JsonResult(field = "kunnrBusinessList", include = { "businessManagerId", "businessManager", "managerMobile"})
	public String searchbusinessManager(){
		kunnrBusiness = new KunnrBusiness();
		kunnrBusiness.setKunnr(kunnrId);
		kunnrBusinessList = kunnrBusinessService.getKunnrBusinessManagerList(kunnrBusiness);
		return JSON;	
	}
	@PermissionSearch
	@JsonResult(field = "kunnrBusinessList", include = { "businessHeadId", "businessHead", "headMobile"})
	public String searchHead(){
		kunnrBusiness = new KunnrBusiness();
		kunnrBusiness.setKunnr(kunnrId);
		kunnrBusinessList = kunnrBusinessService.getKunnrBusinessHeadList(kunnrBusiness);
		return JSON;	
	}
	@PermissionSearch
	@JsonResult(field = "kunnrBusinessList", include = {"businessAgentId", "businessAgent", "agentMobile"})
	public String searchAgent(){
		kunnrBusiness = new KunnrBusiness();
		kunnrBusiness.setKunnr(kunnrId);
		kunnrBusinessList = kunnrBusinessService.getKunnrBusinessAgentList(kunnrBusiness);
		return JSON;	
	}

	public String saveKunnrBusiness(){	
		try {
			kunnrBusinessService.updateHead(kunnrBusiness);
			kunnrBusinessService.updateAgent(kunnrBusiness);
			kunnrBusinessService.updateBusinessManager(kunnrBusiness);
			for(int i=1;i<businessManagerIds.length;i++){
				if(StringUtils.isNotEmpty(businessManagerIds[i])){
					kunnrBusiness.setBusinessManagerId(businessManagerIds[i]);
					kunnrBusiness.setBusinessManager(businessManagers[i]);
					kunnrBusiness.setManagerMobile(managerMobiles[i]);
					kunnrBusinessService.createBusinessManager(kunnrBusiness);
				}
			}
			
			for(int i=0;i<businessHeadIds.length;i++){
				if(StringUtils.isNotEmpty(businessHeadIds[i])){
					kunnrBusiness.setBusinessHeadId(businessHeadIds[i]);
					kunnrBusiness.setBusinessHead(businessHeads[i]);
					kunnrBusiness.setHeadMobile(headMobiles[i]);
					kunnrBusinessService.createHead(kunnrBusiness);
				}
			}
			for(int i=0;i<businessAgentIds.length;i++){
				if(StringUtils.isNotEmpty(businessAgentIds[i])){
					kunnrBusiness.setBusinessAgentId(businessAgentIds[i]);
					kunnrBusiness.setBusinessAgent(businessAgents[i]);
					kunnrBusiness.setAgentMobile(agentMobiles[i]);
					kunnrBusinessService.createAgent(kunnrBusiness);
				}
			}
			kunnrBusiness.setBusinessManagerId(businessManagerIds[0]);
			kunnrBusiness.setBusinessManager(businessManagers[0]);
			kunnrBusiness.setManagerMobile(managerMobiles[0]);
			StringResult result = kunnrBusinessService
					.updateKunnrBusiness(kunnrBusiness);
			if (IKunnrBusinessService.ERROR.equals(result.getCode())) {
				this.setFailMessage(result.getResult());
			} else {
				this.setSuccessMessage("处理成功");
			}
		} catch (Exception e) { 
			this.setFailMessage("处理失败！");
			logger.error(kunnrBusiness, e);
		}
	return RESULT_MESSAGE;
	}
	
	public void exportForExcel(){
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		kunnr = new Kunnr();
		if (StringUtils.isNotEmpty(kunnrId)) {
			kunnr.setKunnr(kunnrId);
		}
		if (StringUtils.isNotEmpty(name1)) {
			kunnr.setName1(name1);
		}
		if (channelId != null) {
			kunnr.setChannelId(channelId);
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
		
		List<KunnrBusiness> list = kunnrBusinessService.exportForExcel(kunnr);
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "经销商业务联系.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("第一页", 0);
			wsheet.setRowView(0, 300);
			wsheet.setRowView(1, 300);
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 10);
			wsheet.setColumnView(7, 10);
			
			
			wsheet.addCell(new Label(0, 0, "大区"));
			wsheet.addCell(new Label(1, 0, "省份"));
			wsheet.addCell(new Label(2, 0, "城市"));
			wsheet.addCell(new Label(3, 0, "经销商编号"));
            wsheet.addCell(new Label(4, 0, "经销商"));
			wsheet.addCell(new Label(5, 0, "高级经理"));
            wsheet.addCell(new Label(6, 0, "客户经理"));
			wsheet.addCell(new Label(7, 0, "业代"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= list.size(); i++) {
				if(list.get(i-1).getVkgrp()==null){
					list.get(i-1).setVkgrp("");
				}
				if(list.get(i-1).getBzirk()==null){
					list.get(i-1).setBzirk("");
				}
				if(list.get(i-1).getVkbur()==null){
					list.get(i-1).setVkbur("");
				}
				if(list.get(i-1).getKunnr()==null){
					list.get(i-1).setKunnr("");
				}
				if(list.get(i-1).getKunnrName()==null){
					list.get(i-1).setKunnrName("");
				}
				if(list.get(i-1).getBusinessManager()==null){
					list.get(i-1).setBusinessManager("");
				}
				if(list.get(i-1).getBusinessHead()==null){
					list.get(i-1).setBusinessHead("");
				}
				if(list.get(i-1).getBusinessAgent()==null){
					list.get(i-1).setBusinessAgent("");
				}
				wsheet.addCell(new Label(0,i,list.get(i-1).getVkgrp(),wcfF));
				wsheet.addCell(new Label(1,i,list.get(i-1).getBzirk(),wcfF));
				wsheet.addCell(new Label(2,i,list.get(i-1).getVkbur(),wcfF));
				wsheet.addCell(new Label(3,i,list.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(4,i,list.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Label(5,i,list.get(i-1).getBusinessManager(),wcfF));
				wsheet.addCell(new Label(6,i,list.get(i-1).getBusinessHead(),wcfF));
				wsheet.addCell(new Label(7,i,list.get(i-1).getBusinessAgent(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	@PermissionSearch
	@JsonResult(field = "kunnrBusinessList", include = { "kunnr", "kunnrName",
			"bzirk", "vkbur", "vkgrp","businessManager",
			"businessHead", "businessAgent"}, total = "total")
	public String searchKunnrBusinessReport(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		kunnrBusiness = new KunnrBusiness();
		if (StringUtils.isNotEmpty(kunnrId)) {
			kunnrBusiness.setKunnr(kunnrId);
		}
		if (StringUtils.isNotEmpty(orgId)) {
			kunnrBusiness.setOrgId(orgId);
		}
		if (businessEndDate!=null) {
			kunnrBusiness.setBusinessEndDate(sdf.format(businessEndDate));
		}else{
			kunnrBusiness.setBusinessEndDate(sdf.format(new Date()));
		}
		kunnrBusiness.setStart(getStart());
		kunnrBusiness.setEnd(getEnd());
		total = kunnrBusinessService.searchKunnrBusinessManagerListCount(kunnrBusiness);
		if(total>0){
			kunnrBusinessList=kunnrBusinessService.searchKunnrBusinessManagerList(kunnrBusiness);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "kunnrBusinessList", include = { "kunnr", "kunnrName",
			"businessId","businessName", "businessType","businessEndDate"}, total = "total")
	public String searchKunnrBusinessEmp(){
		kunnrBusiness = new KunnrBusiness();
		if (StringUtils.isNotEmpty(kunnrId)) {
			kunnrBusiness.setKunnr(kunnrId);
		}
		if (StringUtils.isNotEmpty(businessName)) {
			kunnrBusiness.setBusinessName(businessName);
		}
		if (StringUtils.isNotEmpty(orgId)) {
			kunnrBusiness.setOrgId(orgId);
		}
		kunnrBusiness.setStart(getStart());
		kunnrBusiness.setEnd(getEnd());
		total = kunnrBusinessService.searchKunnrBusinessEmpListCount(kunnrBusiness);
		if(total>0){
			kunnrBusinessList=kunnrBusinessService.searchKunnrBusinessEmpList(kunnrBusiness);
		}
		return JSON;
	}
	
	public void updateKunnrBusinessEmp(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		kunnrBusiness = new KunnrBusiness();
		if(businessEndDate!=null){
			kunnrBusiness.setBusinessEndDate(sdf.format(businessEndDate));
		}
		if(businessId!=null){
			kunnrBusiness.setBusinessId(businessId);
		}
		kunnrBusinessService.updateKunnrBusinessEmp(kunnrBusiness);
	}
	
	public void exportForExcelReport(){
		ServletActionContext.getRequest().getSession()
		.setAttribute("DownLoad", "Ing");
		
		kunnrBusiness = new KunnrBusiness();
		if (StringUtils.isNotEmpty(kunnrId)) {
			kunnrBusiness.setKunnr(kunnrId);
		}
		if (StringUtils.isNotEmpty(orgId)) {
			kunnrBusiness.setOrgId(orgId);
		}
		kunnrBusiness.setStart(0);
		kunnrBusiness.setEnd(65000);
		List<KunnrBusiness> list = kunnrBusinessService.searchKunnrBusinessManagerList(kunnrBusiness);
		OutputStream os = null;
		WritableWorkbook wbook = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			// 取得输出流
			os = response.getOutputStream();
			// 清空输出流
			response.reset();
			// 设定输出文件头
			String fileName = "经销商业务联系.xls";
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			// 定义输出类型
			response.setContentType("application/msexcel");
			// 建立excel文件
			wbook = Workbook.createWorkbook(os);
			WritableSheet wsheet = wbook.createSheet("第一页", 0);
			wsheet.setRowView(0, 300);
			wsheet.setRowView(1, 300);
			wsheet.setColumnView(0, 10);
			wsheet.setColumnView(1, 10);
			wsheet.setColumnView(2, 10);
			wsheet.setColumnView(3, 10);
			wsheet.setColumnView(4, 30);
			wsheet.setColumnView(5, 10);
			wsheet.setColumnView(6, 30);
			wsheet.setColumnView(7, 30);
			
			
			wsheet.addCell(new Label(0, 0, "大区"));
			wsheet.addCell(new Label(1, 0, "省份"));
			wsheet.addCell(new Label(2, 0, "城市"));
			wsheet.addCell(new Label(3, 0, "经销商编号"));
            wsheet.addCell(new Label(4, 0, "经销商"));
			wsheet.addCell(new Label(5, 0, "高级经理"));
            wsheet.addCell(new Label(6, 0, "客户经理"));
			wsheet.addCell(new Label(7, 0, "业代"));
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10 ,WritableFont.NO_BOLD, false ,jxl.format.UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        WritableCellFormat wcfF = new  WritableCellFormat(wfc);   
	        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
	        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	        wcfF.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.HAIR);
			for(int i=1;i<= list.size(); i++) {
				if(list.get(i-1).getVkgrp()==null){
					list.get(i-1).setVkgrp("");
				}
				if(list.get(i-1).getBzirk()==null){
					list.get(i-1).setBzirk("");
				}
				if(list.get(i-1).getVkbur()==null){
					list.get(i-1).setVkbur("");
				}
				if(list.get(i-1).getKunnr()==null){
					list.get(i-1).setKunnr("");
				}
				if(list.get(i-1).getKunnrName()==null){
					list.get(i-1).setKunnrName("");
				}
				if(list.get(i-1).getBusinessManager()==null){
					list.get(i-1).setBusinessManager("");
				}
				if(list.get(i-1).getBusinessHead()==null){
					list.get(i-1).setBusinessHead("");
				}
				if(list.get(i-1).getBusinessAgent()==null){
					list.get(i-1).setBusinessAgent("");
				}
				wsheet.addCell(new Label(0,i,list.get(i-1).getVkgrp(),wcfF));
				wsheet.addCell(new Label(1,i,list.get(i-1).getBzirk(),wcfF));
				wsheet.addCell(new Label(2,i,list.get(i-1).getVkbur(),wcfF));
				wsheet.addCell(new Label(3,i,list.get(i-1).getKunnr(),wcfF));
				wsheet.addCell(new Label(4,i,list.get(i-1).getKunnrName(),wcfF));
				wsheet.addCell(new Label(5,i,list.get(i-1).getBusinessManager(),wcfF));
				wsheet.addCell(new Label(6,i,list.get(i-1).getBusinessHead(),wcfF));
				wsheet.addCell(new Label(7,i,list.get(i-1).getBusinessAgent(),wcfF));
			}
			wbook.write();
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		} catch (Exception e) {
//			logger.error(e);
		} finally {
			if (wbook != null) {
				try {
					wbook.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				wbook = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
//					logger.error(e);
				}
				os = null;
			}
		}
	}
	
	
	

	public IKunnrBusinessService getKunnrBusinessService() {
		return kunnrBusinessService;
	}

	public void setKunnrBusinessService(IKunnrBusinessService kunnrBusinessService) {
		this.kunnrBusinessService = kunnrBusinessService;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public Kunnr getKunnr() {
		return kunnr;
	}

	public void setKunnr(Kunnr kunnr) {
		this.kunnr = kunnr;
	}

	public String getKunnrId() {
		return kunnrId;
	}

	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
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

	public KunnrBusiness getKunnrBusiness() {
		return kunnrBusiness;
	}

	public void setKunnrBusiness(KunnrBusiness kunnrBusiness) {
		this.kunnrBusiness = kunnrBusiness;
	}

	public List<Kunnr> getKunnrList() {
		return kunnrList;
	}

	public void setKunnrList(List<Kunnr> kunnrList) {
		this.kunnrList = kunnrList;
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

	public String getResultJson() {
		return resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		KunnrBusinessAction.logger = logger;
	}

	public String getKunnrName() {
		return kunnrName;
	}

	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}

	public String[] getBusinessHeadIds() {
		return businessHeadIds;
	}

	public void setBusinessHeadIds(String[] businessHeadIds) {
		this.businessHeadIds = businessHeadIds;
	}

	public String[] getBusinessHeads() {
		return businessHeads;
	}

	public void setBusinessHeads(String[] businessHeads) {
		this.businessHeads = businessHeads;
	}

	public String[] getHeadMobiles() {
		return headMobiles;
	}

	public void setHeadMobiles(String[] headMobiles) {
		this.headMobiles = headMobiles;
	}

	public String[] getBusinessAgentIds() {
		return businessAgentIds;
	}

	public void setBusinessAgentIds(String[] businessAgentIds) {
		this.businessAgentIds = businessAgentIds;
	}

	public String[] getBusinessAgents() {
		return businessAgents;
	}

	public void setBusinessAgents(String[] businessAgents) {
		this.businessAgents = businessAgents;
	}

	public String[] getAgentMobiles() {
		return agentMobiles;
	}

	public void setAgentMobiles(String[] agentMobiles) {
		this.agentMobiles = agentMobiles;
	}

	public List<KunnrBusiness> getKunnrBusinessList() {
		return kunnrBusinessList;
	}

	public void setKunnrBusinessList(List<KunnrBusiness> kunnrBusinessList) {
		this.kunnrBusinessList = kunnrBusinessList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBhxjFlag() {
		return bhxjFlag;
	}

	public void setBhxjFlag(String bhxjFlag) {
		this.bhxjFlag = bhxjFlag;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	public String[] getKunnrs() {
		return kunnrs;
	}

	public void setKunnrs(String[] kunnrs) {
		this.kunnrs = kunnrs;
	}

	public String getBusinessHead() {
		return businessHead;
	}

	public void setBusinessHead(String businessHead) {
		this.businessHead = businessHead;
	}

	public String getBusinessAgent() {
		return businessAgent;
	}

	public void setBusinessAgent(String businessAgent) {
		this.businessAgent = businessAgent;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Date getBusinessEndDate() {
		return businessEndDate;
	}

	public void setBusinessEndDate(Date businessEndDate) {
		this.businessEndDate = businessEndDate;
	}

	public String[] getBusinessManagerIds() {
		return businessManagerIds;
	}

	public void setBusinessManagerIds(String[] businessManagerIds) {
		this.businessManagerIds = businessManagerIds;
	}

	public String[] getBusinessManagers() {
		return businessManagers;
	}

	public void setBusinessManagers(String[] businessManagers) {
		this.businessManagers = businessManagers;
	}

	public String[] getManagerMobiles() {
		return managerMobiles;
	}

	public void setManagerMobiles(String[] managerMobiles) {
		this.managerMobiles = managerMobiles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public List<DealerAdjustment> getDealerAdjustmentList() {
		return dealerAdjustmentList;
	}
	public void setDealerAdjustmentList(List<DealerAdjustment> dealerAdjustmentList) {
		this.dealerAdjustmentList = dealerAdjustmentList;
	}
	public DealerAdjustment getDealerAdjustment() {
		return dealerAdjustment;
	}
	public void setDealerAdjustment(DealerAdjustment dealerAdjustment) {
		this.dealerAdjustment = dealerAdjustment;
	}
	public IGoalService getGoalService() {
		return goalService;
	}
	public void setGoalService(IGoalService goalService) {
		this.goalService = goalService;
	}
	public Materiel getMat() {
		return mat;
	}
	public void setMat(Materiel mat) {
		this.mat = mat;
	}
	public List<Materiel> getMatList() {
		return matList;
	}
	public void setMatList(List<Materiel> matList) {
		this.matList = matList;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getBezei() {
		return bezei;
	}
	public void setBezei(String bezei) {
		this.bezei = bezei;
	}
	public String getMvgr1() {
		return mvgr1;
	}
	public void setMvgr1(String mvgr1) {
		this.mvgr1 = mvgr1;
	}
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public String getDetalId() {
		return detalId;
	}
	public void setDetalId(String detalId) {
		this.detalId = detalId;
	}
	public String getAdjustId() {
		return adjustId;
	}
	public void setAdjustId(String adjustId) {
		this.adjustId = adjustId;
	}
	public String getApplyYear() {
		return applyYear;
	}
	public void setApplyYear(String applyYear) {
		this.applyYear = applyYear;
	}
	public String getApplyMonth() {
		return applyMonth;
	}
	public void setApplyMonth(String applyMonth) {
		this.applyMonth = applyMonth;
	}
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
	public String getNowTarget() {
		return nowTarget;
	}
	public void setNowTarget(String nowTarget) {
		this.nowTarget = nowTarget;
	}
	public String getNowDealerTarget() {
		return nowDealerTarget;
	}
	public void setNowDealerTarget(String nowDealerTarget) {
		this.nowDealerTarget = nowDealerTarget;
	}
	public String getAdjustTarget() {
		return adjustTarget;
	}
	public void setAdjustTarget(String adjustTarget) {
		this.adjustTarget = adjustTarget;
	}
	public DealerAdujstDetail getDealerAdujstDetail() {
		return dealerAdujstDetail;
	}
	public void setDealerAdujstDetail(DealerAdujstDetail dealerAdujstDetail) {
		this.dealerAdujstDetail = dealerAdujstDetail;
	}
	public List<DealerAdujstDetail> getDealerAdujstDetailList() {
		return dealerAdujstDetailList;
	}
	public void setDealerAdujstDetailList(
			List<DealerAdujstDetail> dealerAdujstDetailList) {
		this.dealerAdujstDetailList = dealerAdujstDetailList;
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
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public UserUtil getUserUtil() {
		return userUtil;
	}
	public void setUserUtil(UserUtil userUtil) {
		this.userUtil = userUtil;
	}

	public IWfeService getWfeServiceHessian() {
		return wfeServiceHessian;
	}

	public void setWfeServiceHessian(IWfeService wfeServiceHessian) {
		this.wfeServiceHessian = wfeServiceHessian;
	}
	public BooleanResult getExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(BooleanResult executeResult) {
		this.executeResult = executeResult;
	}
	public String getTheYear() {
		return theYear;
	}
	public void setTheYear(String theYear) {
		this.theYear = theYear;
	}
	public String getTheMonth() {
		return theMonth;
	}
	public void setTheMonth(String theMonth) {
		this.theMonth = theMonth;
	}
	public String getMatterName() {
		return matterName;
	}
	public void setMatterName(String matterName) {
		this.matterName = matterName;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getNextUserId() {
		return nextUserId;
	}
	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<Kunnr> getKunnrlist() {
		return kunnrlist;
	}
	public void setKunnrlist(List<Kunnr> kunnrlist) {
		this.kunnrlist = kunnrlist;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<CmsTbDict> getDictlist() {
		return dictlist;
	}
	public void setDictlist(List<CmsTbDict> dictlist) {
		this.dictlist = dictlist;
	}
	public IDictService getDictServiceHessian() {
		return dictServiceHessian;
	}
	public void setDictServiceHessian(IDictService dictServiceHessian) {
		this.dictServiceHessian = dictServiceHessian;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMaktx01() {
		return maktx01;
	}
	public void setMaktx01(String maktx01) {
		this.maktx01 = maktx01;
	}
	public String getMatnr01() {
		return matnr01;
	}
	public void setMatnr01(String matnr01) {
		this.matnr01 = matnr01;
	}
	
}
