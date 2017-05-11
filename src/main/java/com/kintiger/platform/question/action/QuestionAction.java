package com.kintiger.platform.question.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.question.pojo.Borg;
import com.kintiger.platform.question.pojo.Communit;
import com.kintiger.platform.question.pojo.Demand;
import com.kintiger.platform.question.pojo.DemandCommunit;
import com.kintiger.platform.question.pojo.DemandUser;
import com.kintiger.platform.question.pojo.FileTmp;
import com.kintiger.platform.question.pojo.Question;
import com.kintiger.platform.question.pojo.SimpleMailSender;
import com.kintiger.platform.question.pojo.Tree4Ajax;
import com.kintiger.platform.question.service.IQuestionService;

public class QuestionAction extends BaseAction {

	private static final long serialVersionUID = 7813901383646348452L;

	private List<Question> questionList = new ArrayList<Question>();

	private int total;

	private Long qId;
	private Long authorId;
	private @Decode
	String author;
	private String phone;
	private String orgId;
	private @Decode
	String orgName;
	private String qTime;
	private String qTime2;
	private String qTemp;
	private String qTemp2;
	private String question;
	private String qLevel;
	private String qType;
	private @Decode
	String answerAuthor;
	private Long answerAuthorId;
	private String answerAuthorEmail;
	private String handleState;
	private String visit;
	private String remark;
	private String authorEmail;
	private String qTitle;
	private IQuestionService questionService;

	private Question q;
	private String deleteQuestionId;

	private String userId;
	private String loginId;

	private IOrgService orgServiceHessian;
	private File[] upload;
	private String[] uploadFileName;
	private String questionFilePath;
	private String fileId;

	private List<Communit> communitList;
	private Communit c;
	private String content;

	private boolean emailResult;

	private List<Tree4Ajax> treeList;
	private List<AllUsers> userList;
	
	private Demand demand;
	private List<Demand> demandList;
	private Long demandId;
	private String title;
	private String demandLevel;
	private String demandType;
	private Date startDate;
	private Date endDate;

	public String searchQuestion() {
		AllUsers user = this.getUser();
		userId = user.getUserId();
		loginId = user.getLoginId();
		orgId = user.getOrgId();
		orgName = questionService.getOrgName(Long.parseLong(orgId));
		if(orgId.equals("51101") || loginId.equals("admin")){
			return "toSearchQuestion";
		}else{
		    return "toSearchQuestionUser";
		}
	}
	
	@PermissionSearch
	@JsonResult(field = "questionList", include = { "qId", "qTitle", "author", "orgName",
			"qTime", "qTime2", "qTemp", "qTemp2", "question", "qLevel",
			"qType", "answerAuthor", "lastDate", "handleState", "visit",
			"remark" }, total = "total")
	public String getQuestionJsonList() {
		String date1 = null;
		String date2 = null;
		if (qTime != null && !qTime.isEmpty()) {
			String[] times1 = qTime.split("-");
			date1 = times1[0] + times1[1] + times1[2];
		}
		if (qTime2 != null && !qTime2.isEmpty()) {
			String[] times2 = qTime2.split("-");
			date2 = times2[0] + times2[1] + times2[2];
			if (qTime != null && !qTime.isEmpty()) {
				if (Long.parseLong(date1) > Long.parseLong(date2)) {
					String tmp = qTime;
					qTime = qTime2;
					qTime2 = tmp;
				}
			}
		}
		Question q = new Question();
		AllUsers user = this.getUser();
		String userName = user.getUserName();
		String loginId=user.getLoginId();
		q.setStart(getStart());
		q.setEnd(getEnd());
		q.setqId(qId);
		if (StringUtils.isNotEmpty(author)) {
			q.setAuthor(author);
		}
		if(StringUtils.isNotEmpty(orgId)) {
					q.setOrgId(orgId);
		}
		q.setqTime(qTime);
		q.setqTime2(qTime2);
		q.setqTemp(qTemp);
		q.setqLevel(qLevel);
		q.setqTitle(qTitle);
		if (StringUtils.isNotEmpty(answerAuthor)) {
			q.setAnswerAuthor(answerAuthor);
		}
		q.setHandleState(handleState);
		q.setqType(qType);
		Question question = new Question();
		question.setAnswerAuthorId(Long.parseLong(user.getUserId()));
		int count=questionService.getQuestionCount(question);
		if(!user.getOrgId().equals("51101") && !user.getLoginId().equals("admin")){
			q.setAnswerAuthorId(Long.parseLong(user.getUserId()));
		}
		total = questionService.getQuestionCount(q);
		// System.out.println("total="+total);
		if (total != 0) {
			questionList = questionService.getQuestionList(q);
		}

		return JSON;
	}

	@PermissionSearch
	@JsonResult(field = "questionList", include = { "qId", "qTitle", "author", "orgName",
			"qTime", "qTime2", "qTemp", "qTemp2", "question", "qLevel",
			"qType", "answerAuthor", "lastDate", "handleState", "visit",
			"remark" }, total = "total")
	public String getQuestionJsonListUser() {
		String date1 = null;
		String date2 = null;
		if (qTime != null && !qTime.isEmpty()) {
			String[] times1 = qTime.split("-");
			date1 = times1[0] + times1[1] + times1[2];
		}
		if (qTime2 != null && !qTime2.isEmpty()) {
			String[] times2 = qTime2.split("-");
			date2 = times2[0] + times2[1] + times2[2];
			if (qTime != null && !qTime.isEmpty()) {
				if (Long.parseLong(date1) > Long.parseLong(date2)) {
					String tmp = qTime;
					qTime = qTime2;
					qTime2 = tmp;
				}
			}
		}
		Question q = new Question();
		AllUsers user = this.getUser();
		String userName = user.getUserName();
		q.setStart(getStart());
		q.setEnd(getEnd());
		q.setqId(qId);
		if(StringUtils.isEmpty(orgId)) {
			q.setAuthor(userName);
		} else if (StringUtils.isNotEmpty(author)) {
			q.setAuthor(author);
		}
		if(StringUtils.isNotEmpty(orgId)) {
					q.setOrgId(orgId);
		}
		q.setqTime(qTime);
		q.setqTime2(qTime2);
		q.setqTemp(qTemp);
		q.setqLevel(qLevel);
		q.setqTitle(qTitle);
		if (StringUtils.isNotEmpty(answerAuthor)) {
			q.setAnswerAuthor(answerAuthor);
		}
		q.setHandleState(handleState);
		q.setqType(qType);
		total = questionService.getQuestionCount(q);
		// System.out.println("total="+total);
		if (total != 0) {
			questionList = questionService.getQuestionList(q);
		}

		return JSON;
	}

	public String toCreateQuestion() {
		AllUsers user = this.getUser();
		author = user.getUserName();
		authorId = Long.parseLong(user.getUserId());
		String[] phones = questionService.getEmpPhone(user.getLoginId()).split(
				",");
		if(phones.length>1){
			if (StringUtils.isNotEmpty(phones[0])
					&& StringUtils.isNotEmpty(phones[1])) {
				phone = phones[0] + "/" + phones[1];
			} else if (StringUtils.isNotEmpty(phones[0])
					&& StringUtils.isEmpty(phones[1])) {
				phone = phones[0];
			} else {
				phone = phones[1];
			}
		}else{
			phone=phones[0];
		}
		authorEmail = questionService.getEmpEmail(authorId);
		return "toCreateQuestion";
	}

	public String createQuestion() {
		qId = createId();
		AllUsers user = this.getUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String orgId = user.getOrgId();
		String orgName = questionService.getOrgName(Long.parseLong(orgId));
//		String orgParentId = questionService.getOrgParentId(Long
//				.parseLong(orgId));
//		String orgParentId2 = questionService.getOrgParentId(Long
//				.parseLong(orgParentId));
//		while (orgParentId != null && !orgParentId.equals("0")) {
//			orgName = questionService.getOrgName(Long.parseLong(orgParentId))
//					+ "//" + orgName;
//			orgParentId = orgParentId2;
//			orgParentId2 = questionService.getOrgParentId(Long
//					.parseLong(orgParentId));
//		}
		q.setqId(qId);
		q.setHandleState("s101");
		q.setqTime(sdf.format(new Date()));
		q.setOrgId(orgId);
		q.setOrgName(orgName);

//		if (q.getAnswerAuthorId() != null) {
//			answerAuthorEmail = questionService.getEmpEmail(q
//					.getAnswerAuthorId());
//			q.setAnswerAuthorEmail(answerAuthorEmail);
//			String content = "你好，你有一个问题未处理，请尽快登录系统处理！</br>" + "问题编号："
//					+ q.getqId() + "</br>" + "所属组织：" + q.getOrgName() + "</br>"
//					+ "提报人：" + q.getAuthor() + "</br>"
//					+ "</br>此邮件为系统自动发出，请勿回复，谢谢！";
//			SimpleMailSender sms = new SimpleMailSender("exp@zjxpp.com",
//					"a123456");
//			try {
//				sms.send(answerAuthorEmail, "香飘飘EXP问题管理系统——问题处理", content);
//			} catch (AddressException e) {
//				e.printStackTrace();
//			} catch (MessagingException e) {
//				e.printStackTrace();
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}

		this.setSuccessMessage("创建成功.");
		BooleanResult booleanResult = questionService.createQuestion(q);
		if (booleanResult.getResult()) {
			if (upload != null && upload.length > 0) {
				questionService.processAttachments(upload, uploadFileName,
						q.getqId(), String.valueOf(new Date().getTime()),
						q.getAuthor());
			}
		}
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	public Long createId() {

		// 获取当前日期

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String tmp2 = sdf.format(new Date());

		// 获取最大ID号
		String maxId = questionService.getMaxId() + "";
		if (maxId.equals("null")) {
			return Long.parseLong(tmp2 + "1001");
		} else {
			String tmp = maxId.substring(0, 8);
			if (tmp.equals(tmp2)) {
				return Long.parseLong(maxId) + 1;
			} else {
				return Long.parseLong(tmp2 + "1001");
			}
		}
	}

	public String toUpdateQuestion() {
		AllUsers user = this.getUser();
		q = questionService.getQuestionById(qId.toString());
		if(q.getAnswerAuthor()==null){
		    if (user.getOrgId().equals("51101") || user.getLoginId().equals("admin")) {
		    	return "toUpdateQuestion";
		    }else if(Long.parseLong(user.getUserId()) == (q.getAuthorId())) {
				return "toUpdateQuestionUser";
			} else {
				return "toShowQuestion";
			}
		}else{
		    if (user.getOrgId().equals("51101") || user.getLoginId().equals("admin")) {
		    	return "toUpdateQuestion";
		    }else if(Long.parseLong(user.getUserId()) == (q.getAuthorId())) {
				return "toUpdateQuestionUser";
			} else {
				return "toShowQuestion";
			}
		 }
	}

	public String updateQuestion() {
		if (StringUtils.isNotEmpty(content)) {
			Date date = new Date();
			Communit com = new Communit();
			author = this.getUser().getUserName();
			com.setqId(qId);
			com.setAuthor(author);
			com.setContent(content);
			com.setCreateTime(date);
			questionService.createCommunit(com);
			
			String authorName=questionService.getQuestionById(qId.toString()).getAuthor();
			if(!authorName.equals(author)){
				String emailContent = author + "对编号为 "+qId+" 的问题作出了回复:"+"</br>"+content+"</br>"
						+"回复时间："+date;
				SimpleMailSender sms = new SimpleMailSender("exp@chinaxpp.com", "52xpp!123");
				try {
					sms.send(authorEmail, "香飘飘EXP问题管理系统新回复", emailContent);
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
		if (answerAuthorId != null) {
			answerAuthorEmail = questionService.getEmpEmail(answerAuthorId);
		}
		q.setAnswerAuthorEmail(answerAuthorEmail);
		q.setAnswerAuthorId(answerAuthorId);
		this.setSuccessMessage("更新成功.");
		BooleanResult booleanResult = questionService.updateQuestion(q);
		if (booleanResult.getResult()) {
			if (upload != null && upload.length > 0) {
				questionService.processAttachments(upload, uploadFileName,
						q.getqId(), String.valueOf(new Date().getTime()),
						q.getAuthor());
			}
		}
		if (!booleanResult.getResult()) {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	public String deleteQuestion() {
		this.setSuccessMessage("操作成功！");
		String tmp1 = deleteQuestionId.replace("[", "");
		String tmp2 = tmp1.replace("]", "");
		String[] ids = tmp2.split(",");
		for (int i = 0; i < ids.length; i++) {
			Question deleteQuestion = new Question();
			deleteQuestion.setqId(Long.parseLong(ids[i]));
			deleteQuestion.setqState("D");
			BooleanResult booleanResult = questionService
					.updateQuestion(deleteQuestion);
			if (!booleanResult.getResult()) {
				/*
				 * stringResult.setResult("操作失败！");
				 * stringResult.setCode(booleanResult.getCode());
				 */
				this.setFailMessage("操作失败!");

			}
		}
		return RESULT_MESSAGE;
	}

	@PermissionSearch
	@JsonResult(field = "communitList", include = { "qId", "cId", "author",
			"content", "createTime" })
	public String getCommunitList() {
		Communit com = new Communit();
		com.setqId(qId);
		communitList = questionService.getCommunitList(com);
		return JSON;
	}

	public String selectAnswer() {
		return "selectAnswer";
	}

	@PermissionSearch
	@JsonResult(field = "emailResult")
	public String sendEmail() {
		emailResult = true;
		answerAuthorEmail = questionService.getEmpEmail(answerAuthorId);
		if (StringUtils.isEmpty(answerAuthorEmail)) {
			emailResult = false;
		}
		String content = "你好，你已被指定为EXP系统问题处理人，请尽快登录系统处理问题！</br>" + "问题编号："
				+ qId.toString() + "</br>" + "所属组织：" + orgName.toString()
				+ "</br>" + "提报人：" + author.toString() + "</br>"
				+ "</br>此邮件为系统自动发出，请勿回复，谢谢！";
		SimpleMailSender sms = new SimpleMailSender("smtp.exmail.qq.com","exp@chinaxpp.com", "52xpp!123");
		try {
			sms.send(answerAuthorEmail, "香飘飘EXP问题管理系统指定处理人", content);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return JSON;
	}

	@PermissionSearch
	public String downLoadFile() {
		FileTmp fileTmp = questionService.getFileTmpByFileId(fileId);
		File source = new File(questionFilePath + "/" + fileTmp.getSubFolders()
				+ "/" + fileTmp.getFileNameNew());
		if (source.exists()) {
			display(source, fileTmp.getFileName(),
					ServletActionContext.getResponse());
		} else {
			this.setFailMessage("文件不存在!");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 文件下载
	 * 
	 * @param file
	 * @param fileName
	 * @param response
	 * @return6
	 */
	@SuppressWarnings("finally")
	@PermissionSearch
	private boolean display(File file, String fileName,
			HttpServletResponse response) {

		FileInputStream in = null;
		OutputStream out = null;
		try {

			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ fileName);
			in = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			response.flushBuffer();
		} catch (Exception ex) {
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (final Exception e) {
				}
			}
			return true;
		}
	}

	public String orgTreePage() {
		return "orgtree";
	}

	@PermissionSearch
	@JsonResult(field = "treeList", include = { "id", "text", "state" })
	public String getAllOrgTreeByAjax() {
		treeList = new ArrayList<Tree4Ajax>();
		List<Borg> orgTreeList = questionService.getOrgsByUserId("88647");
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
	 * 根据组织ID查找人员信息
	 * 
	 * @return
	 */
	@JsonResult(field = "userList", include = { "userId", "posName",
			"userName", "orgId" })
	@PermissionSearch
	public String getEmpListByOrgId() {
		if (orgId != null && !"".equals(orgId)) {
			userList = questionService.getEmpListByOrgId(orgId);
		}
		return JSON;
	}
	
	private Long[] userIds;
	private String[] userNames;
	private String[] orgNames;
	private String[] expectDates;
	private String[] states;
	private String userName;
	private @Decode String demandContent;
	private List<DemandUser> demandUserList;
	private List<DemandCommunit> demandCommunitList;
	
	public String toDemand() {
		AllUsers user = this.getUser();
		userId = user.getUserId();
		loginId = user.getLoginId();
		return "toDemand";
	}

	
	public String toCreateDemand() {
		AllUsers user = this.getUser();
		author = user.getUserName();
		authorId = Long.parseLong(user.getUserId());
		phone = questionService.getEmpPhone(user.getLoginId());
		authorEmail = questionService.getEmpEmail(authorId);
		return "toCreateDemand";
	}
	
	public String toUpdateDemand() {
		userId = this.getUser().getUserId();
		demand = new Demand();
		demand.setDemandId(demandId);
		demand = questionService.getDemand(demand);
		return "toUpdateDemand";
	}
	
	@PermissionSearch
	@JsonResult(field = "demandList", include = { "demandId", "title", "author", "orgName",
			"createDate","content", "demandLevel","demandType","orgId","resultDate"}, total = "total")
	public String searchDemandList() {
		demand = new Demand();
		demand.setDemandId(demandId);
		demand.setAuthor(author);
		demand.setAuthorId(authorId);
		demand.setTitle(title);
		demand.setOrgId(orgId);
		demand.setDemandLevel(demandLevel);
		demand.setDemandType(demandType);
		demand.setStartDate(startDate);
		demand.setEndDate(endDate);
		demand.setStart(getStart());
		demand.setEnd(getEnd());
		total=questionService.searchDemandListCount(demand);
		if(total>0){
			demandList=questionService.searchDemandList(demand);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "demandUserList", include = { "demandId", "userId", "userName", "orgName",
			"createDate","state", "expectDate"})
	public String searchDemandUserList() {
		DemandUser demandUser = new DemandUser();
		demandUser.setDemandId(demandId);
	    demandUserList=questionService.searchDemandUserList(demandUser);
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "demandCommunitList", include = { "demandId", "userId", "userName", "orgName",
			"createDate","content"})
	public String searchDemandCommunitList() {
		DemandCommunit demandCommunit = new DemandCommunit();
		demandCommunit.setDemandId(demandId);
		demandCommunitList=questionService.searchDemandCommunitList(demandCommunit);
		return JSON;
	}
	
	public String createDemand() {
		Long demandId=questionService.getDemandId();
		
		demand.setOrgId(this.getUser().getOrgId());
		demand.setAuthorId(Long.parseLong(this.getUser().getUserId()));
		demand.setDemandId(demandId);
		int num=questionService.createDemand(demand);
		
		if (num>0) {
			if (upload != null && upload.length > 0) {
				questionService.processAttachments(upload, uploadFileName,
						demandId, String.valueOf(new Date().getTime()),
						this.getUser().getUserId());
			}
		}
		
		DemandUser demandUser = new DemandUser();
		if(userIds!=null && userIds.length>0){
			for(int i=0;i<userIds.length;i++){
				demandUser.setUserId(userIds[i]);
				demandUser.setUserName(userNames[i]);
				demandUser.setOrgName(orgNames[i]);
				demandUser.setState(states[i]);
				demandUser.setDemandId(demandId);
				questionService.createDemandUser(demandUser);
				sendDemandEmail(demandId,userIds[i].toString());
			}
		}
		if(num>0){
			this.setSuccessMessage("创建成功！");
		}else{
			this.setFailMessage("创建失败！");
		}
		return RESULT_MESSAGE;
	}
	
	public String createDemandCommunit(){
		AllUsers user = this.getUser();
		userId=user.getUserId();
		userName=user.getUserName();
		orgName=user.getOrgName();
		if(StringUtils.isNotEmpty(demandContent)){
			DemandCommunit demandCommunit = new DemandCommunit();
			demandCommunit.setDemandId(demandId);
			demandCommunit.setContent(demandContent);
			demandCommunit.setUserName(userName);
			demandCommunit.setUserId(Long.parseLong(userId));
			demandCommunit.setOrgName(orgName);
			questionService.createDemandCommunit(demandCommunit);
		}
		return JSON;
	}
	
	public String updateDemand() {
		if(StringUtils.isNotEmpty(demand.getHandleAttitude())
				||StringUtils.isNotEmpty(demand.getHandleQuality())
				||StringUtils.isNotEmpty(demand.getHandleSpeed())){
			demand.setResultDate(new Date());
		}
		int num=questionService.updateDemand(demand);
		if(userNames==null){
			userNames=new String[0];
		}
		if(userIds!=null && userIds.length>0){
			for(int i=0;i<userIds.length-userNames.length;i++){
				DemandUser demandUser = new DemandUser();
				demandUser.setUserId(userIds[i]);
				demandUser.setExpectDate(expectDates[i]);
				demandUser.setState(states[i]);
				demandUser.setDemandId(demand.getDemandId());
				questionService.updateDemandUser(demandUser);
			}
			
			for(int i=0;i<userNames.length;i++){
				DemandUser demandUser = new DemandUser();
				demandUser.setUserId(userIds[userIds.length-userNames.length+i]);
				demandUser.setUserName(userNames[i]);
				demandUser.setOrgName(orgNames[i]);
				demandUser.setState("1");
				demandUser.setDemandId(demand.getDemandId());
				questionService.createDemandUser(demandUser);
			}
		}
		
		if (num>0) {
			if (upload != null && upload.length > 0) {
				questionService.processAttachments(upload, uploadFileName,
						demand.getDemandId(), String.valueOf(new Date().getTime()),
						this.getUser().getUserId());
			}
		}
		
		if(num>0){
			this.setSuccessMessage("修改成功！");
		}else{
			this.setFailMessage("修改失败！");
		}
		return RESULT_MESSAGE;
	}
	
	@PermissionSearch
	@JsonResult(field = "emailResult")
	public String sendDemandEmail() {
		emailResult = true;
		answerAuthorEmail = questionService.getEmpEmail(Long.parseLong(userId));
		if (StringUtils.isEmpty(answerAuthorEmail)) {
			emailResult = false;
		}
		
		demand = new Demand();
		demand.setDemandId(demandId);
		demand = questionService.getDemand(demand);
		
		String content = "你好，目前有一个需求需要你来处理，请尽快登录系统查看！</br>" 
		        + "需求编号："+ demandId.toString() + "</br>" 
		        + "所属组织：" + demand.getOrgName().toString()+ "</br>" 
		        + "发起人：" + demand.getAuthor().toString() + "</br>"
				+ "需求标题：" + demand.getTitle().toString() + "</br>"
				+ "需求内容：" + demand.getContent().toString() + "</br>"
				+ "</br>此邮件为系统自动发出，请勿回复，谢谢！";
		SimpleMailSender sms = new SimpleMailSender("smtp.exmail.qq.com","exp@chinaxpp.com", "52xpp!123");
		try {
			sms.send(answerAuthorEmail, "香飘飘EXP需求管理系统指定处理人", content);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return JSON;
	}
	
	public String sendDemandEmail(Long demandId,String userId) {
		emailResult = true;
		answerAuthorEmail = questionService.getEmpEmail(Long.parseLong(userId));
		if (StringUtils.isEmpty(answerAuthorEmail)) {
			emailResult = false;
		}
		
		demand = new Demand();
		demand.setDemandId(demandId);
		demand = questionService.getDemand(demand);
		
		String content = "你好，目前有一个需求需要你来处理，请尽快登录系统查看！</br>" 
		        + "需求编号："+ demandId.toString() + "</br>" 
		        + "所属组织：" + demand.getOrgName()+ "</br>" 
		        + "发起人：" + demand.getAuthor() + "</br>"
				+ "需求标题：" + demand.getTitle() + "</br>"
				+ "需求内容：" + demand.getContent() + "</br>"
				+ "</br>此邮件为系统自动发出，请勿回复，谢谢！";
		SimpleMailSender sms = new SimpleMailSender("smtp.exmail.qq.com","exp@chinaxpp.com", "52xpp!123");
		try {
			sms.send(answerAuthorEmail, "香飘飘EXP需求管理系统指定处理人", content);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return JSON;
	}
	
	public String deleteDemand(){
		this.setSuccessMessage("操作成功！");
		String tmp1 = deleteQuestionId.replace("[", "");
		String tmp2 = tmp1.replace("]", "");
		String[] ids = tmp2.split(",");
		for (int i = 0; i < ids.length; i++) {
			demand = new Demand();
			demand.setDemandId(Long.parseLong(ids[i]));
			demand.setState("D");
			int num = questionService.updateDemand(demand);
			if (num==0) {
				this.setFailMessage("操作失败!");
			}
		}
		return RESULT_MESSAGE;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Long getqId() {
		return qId;
	}

	public void setqId(Long qId) {
		this.qId = qId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getqTemp() {
		return qTemp;
	}

	public void setqTemp(String qTemp) {
		this.qTemp = qTemp;
	}

	public String getqTemp2() {
		return qTemp2;
	}

	public void setqTemp2(String qTemp2) {
		this.qTemp2 = qTemp2;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getqLevel() {
		return qLevel;
	}

	public void setqLevel(String qLevel) {
		this.qLevel = qLevel;
	}

	public String getqType() {
		return qType;
	}

	public void setqType(String qType) {
		this.qType = qType;
	}

	public String getAnswerAuthor() {
		return answerAuthor;
	}

	public void setAnswerAuthor(String answerAuthor) {
		this.answerAuthor = answerAuthor;
	}

	public String getqTime() {
		return qTime;
	}

	public void setqTime(String qTime) {
		this.qTime = qTime;
	}

	public String getHandleState() {
		return handleState;
	}

	public void setHandleState(String handleState) {
		this.handleState = handleState;
	}

	public String getVisit() {
		return visit;
	}

	public void setVisit(String visit) {
		this.visit = visit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public IQuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	public Question getQ() {
		return q;
	}

	public void setQ(Question q) {
		this.q = q;
	}

	public String getDeleteQuestionId() {
		return deleteQuestionId;
	}

	public void setDeleteQuestionId(String deleteQuestionId) {
		this.deleteQuestionId = deleteQuestionId;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public String getqTime2() {
		return qTime2;
	}

	public void setqTime2(String qTime2) {
		this.qTime2 = qTime2;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getQuestionFilePath() {
		return questionFilePath;
	}

	public void setQuestionFilePath(String questionFilePath) {
		this.questionFilePath = questionFilePath;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Communit getC() {
		return c;
	}

	public void setC(Communit c) {
		this.c = c;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCommunitList(List<Communit> communitList) {
		this.communitList = communitList;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getAnswerAuthorId() {
		return answerAuthorId;
	}

	public void setAnswerAuthorId(Long answerAuthorId) {
		this.answerAuthorId = answerAuthorId;
	}

	public boolean isEmailResult() {
		return emailResult;
	}

	public void setEmailResult(boolean emailResult) {
		this.emailResult = emailResult;
	}

	public String getAnswerAuthorEmail() {
		return answerAuthorEmail;
	}

	public void setAnswerAuthorEmail(String answerAuthorEmail) {
		this.answerAuthorEmail = answerAuthorEmail;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public List<Tree4Ajax> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<Tree4Ajax> treeList) {
		this.treeList = treeList;
	}

	public List<AllUsers> getUserList() {
		return userList;
	}

	public void setUserList(List<AllUsers> userList) {
		this.userList = userList;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		this.demand = demand;
	}

	public List<Demand> getDemandList() {
		return demandList;
	}

	public void setDemandList(List<Demand> demandList) {
		this.demandList = demandList;
	}

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDemandLevel() {
		return demandLevel;
	}

	public void setDemandLevel(String demandLevel) {
		this.demandLevel = demandLevel;
	}

	public String getDemandType() {
		return demandType;
	}

	public void setDemandType(String demandType) {
		this.demandType = demandType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long[] getUserIds() {
		return userIds;
	}

	public void setUserIds(Long[] userIds) {
		this.userIds = userIds;
	}

	public String[] getUserNames() {
		return userNames;
	}

	public void setUserNames(String[] userNames) {
		this.userNames = userNames;
	}

	public String[] getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String[] orgNames) {
		this.orgNames = orgNames;
	}

	public String[] getExpectDates() {
		return expectDates;
	}

	public void setExpectDates(String[] expectDates) {
		this.expectDates = expectDates;
	}

	public String[] getStates() {
		return states;
	}

	public void setStates(String[] states) {
		this.states = states;
	}

	public List<DemandUser> getDemandUserList() {
		return demandUserList;
	}

	public void setDemandUserList(List<DemandUser> demandUserList) {
		this.demandUserList = demandUserList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<DemandCommunit> getDemandCommunitList() {
		return demandCommunitList;
	}

	public void setDemandCommunitList(List<DemandCommunit> demandCommunitList) {
		this.demandCommunitList = demandCommunitList;
	}

	public String getDemandContent() {
		return demandContent;
	}

	public void setDemandContent(String demandContent) {
		this.demandContent = demandContent;
	}

//	 public void addActionError(String anErrorMessage){
//	 String s=anErrorMessage;
//	 System.out.println(s);
//	 }
//	 public void addActionMessage(String aMessage){
//	 String s=aMessage;
//	 System.out.println(s);
//	
//	 }
//	 public void addFieldError(String fieldName, String errorMessage){
//	 String s=errorMessage;
//	 String f=fieldName;
//	 System.out.println(s);
//	 System.out.println(f);
//	
//	 }

}
