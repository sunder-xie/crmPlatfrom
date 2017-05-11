package com.kintiger.platform.question.service;

import java.io.File;
import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.question.pojo.Borg;
import com.kintiger.platform.question.pojo.Communit;
import com.kintiger.platform.question.pojo.Demand;
import com.kintiger.platform.question.pojo.DemandCommunit;
import com.kintiger.platform.question.pojo.DemandUser;
import com.kintiger.platform.question.pojo.DemandFile;
import com.kintiger.platform.question.pojo.FileTmp;
import com.kintiger.platform.question.pojo.Question;

public interface IQuestionService {
	public int getQuestionCount(Question question);
	public List<Question> getQuestionList(Question question);
	public List<Question> getQuestionListJson();
	public BooleanResult createQuestion(Question question);
	
	public BooleanResult updateQuestion(Question question);

	public Question getQuestion(Question question);
	public Question getQuestionById(String qId);
	
	public Long getMaxId();
	public String getOrgName(Long orgId);
	public String getOrgParentId(Long orgId);
	public List<Borg> getOrgsByUserId(String userId);
	public List<AllUsers> getEmpListByOrgId(String orgId);
	public String getEmpPhone(String empCode);
	public String getEmpEmail(long empId);
	
	public boolean processAttachments(File[] uploadFiles, String[] uploadFileNames, Long miaDetailId, String timestamp,
			String creator);
	public FileTmp getFileTmpByFileId(String fileId);
	
	public int getCommunitListCount(Communit communit);
	public List<Communit> getCommunitList(Communit communit);
	public BooleanResult createCommunit(Communit communit);
	
	public int searchDemandListCount(Demand demand);
	public List<Demand> searchDemandList(Demand demand);
	public Demand getDemand(Demand demand);
	public int createDemand(Demand demand);
	public int createDemandFile(DemandFile demandFile);
	public int updateDemand(Demand demand);
	
	public int createDemandCommunit(DemandCommunit demandCommunit);
	public List<DemandCommunit> searchDemandCommunitList(DemandCommunit demandCommunit);
	
	public List<DemandUser> searchDemandUserList(DemandUser demandUser);
	public int createDemandUser(DemandUser demandUser);
	public Long getDemandId();
	public int updateDemandUser(DemandUser demandUser);
	public String getFileByFileId(Long fileId);
	
	

}
