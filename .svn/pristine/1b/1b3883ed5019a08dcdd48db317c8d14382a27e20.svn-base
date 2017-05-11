package com.kintiger.platform.question.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.question.pojo.Borg;
import com.kintiger.platform.question.dao.IQuestionDao;
import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.question.pojo.Communit;
import com.kintiger.platform.question.pojo.Demand;
import com.kintiger.platform.question.pojo.DemandCommunit;
import com.kintiger.platform.question.pojo.DemandUser;
import com.kintiger.platform.question.pojo.DemandFile;
import com.kintiger.platform.question.pojo.FileTmp;
import com.kintiger.platform.question.pojo.Question;

public class QuestionDaoImpl extends BaseDaoImpl implements IQuestionDao {

	@Override
	public int getQuestionCount(Question question) {
		return (Integer) getSqlMapClientTemplate().queryForObject("question.getQuestionCount" , question);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionList(Question question) {
		return (List<Question>) getSqlMapClientTemplate().queryForList("question.getQuestionList", question);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQuestionListJson() {
		return (List<Question>) getSqlMapClientTemplate().queryForList("question.getQuestionListjosn");
	}

	@Override
	public Integer createQuestion(Question question) {
		return (Integer) getSqlMapClientTemplate().insert("question.createQuestion",question);
	}

	@Override
	public int updateQuestion(Question question) {
		return (Integer) getSqlMapClientTemplate().update("question.updateQuestion",question);
	}

	@Override
	public Question getQuestion(Question question) {
		return (Question) getSqlMapClientTemplate().queryForObject("question.getQuestion" , question);
	}
	@Override
	public Long getMaxId() {
		return (Long) getSqlMapClientTemplate().queryForObject("question.getMaxId");
	}

	@Override
	public String getOrgName(Long orgId) {
		return (String) getSqlMapClientTemplate().queryForObject("question.getOrgName", orgId);
	}

	@Override
	public String getOrgParentId(Long orgId) {
		return (String) getSqlMapClientTemplate().queryForObject("question.getOrgParentId", orgId);
	}
	
	@Override
	public Long createFileTmp(FileTmp fileTmp) {
		return (Long) getSqlMapClientTemplate().insert(
				"question.createFileTmp", fileTmp);
	}

	@Override
	public Question getQuestionById(long qId) {
		return (Question)getSqlMapClientTemplate().queryForObject("question.getQuestionById", qId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Borg> getOrgsByUserId(String userId) {
		return getSqlMapClientTemplate().queryForList("question.getOrgsByUserId", userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<AllUsers> getEmpListByOrgId(String orgId) {
		Long org_id = Long.valueOf(orgId);
		System.out.println(org_id.getClass());
		return getSqlMapClientTemplate().queryForList("question.getEmpListByOrgId", org_id);
	}
	
	@Override
	public String getEmpPhone(String empCode) {
		return (String)getSqlMapClientTemplate().queryForObject("question.getEmpPhone", empCode);
	}
	
	@Override
	public String getEmpEmail(long empId) {
		return (String)getSqlMapClientTemplate().queryForObject("question.getEmpEmail", empId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FileTmp> getFileTmpList(long qId) {
		return getSqlMapClientTemplate().queryForList("question.getFileTmpList", qId);
	}
	
	public FileTmp getFileTmpByFileId(Long fileId) {
		return (FileTmp)getSqlMapClientTemplate().queryForObject("question.getFileTmpByFileId", fileId);
	}

	@Override
	public int getCommunitListCount(Communit communit) {
		return (Integer)getSqlMapClientTemplate().queryForObject("question.getCommunitListCount",communit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Communit> getCommunitList(Communit communit) {
		return (List<Communit>)getSqlMapClientTemplate().queryForList("question.getCommunitList",communit);
	}

	@Override
	public Integer createCommunit(Communit communit) {
		return (Integer)getSqlMapClientTemplate().insert("question.createCommunit",communit);
	}
	
	public int searchDemandListCount(Demand demand){
		return (Integer)getSqlMapClientTemplate().queryForObject("question.searchDemandListCount",demand);
	}
	
	@SuppressWarnings("unchecked")
	public List<Demand> searchDemandList(Demand demand){
		return (List<Demand>)getSqlMapClientTemplate().queryForList("question.searchDemandList",demand);
	}
	
	public Demand getDemand(Demand demand){
		return (Demand)getSqlMapClientTemplate().queryForObject("question.getDemand",demand);
	}
	
	public int createDemand(Demand demand){
		getSqlMapClientTemplate().insert("question.createDemand",demand);
		return 1;
	}
	
	public int createDemandFile(DemandFile demandfile){
		getSqlMapClientTemplate().insert("question.createDemandFile",demandfile);
		return 1;
	}
	
	public int updateDemand(Demand demand){
		return getSqlMapClientTemplate().update("question.updateDemand",demand);
	}
	
	public int createDemandCommunit(DemandCommunit demandCommunit){
		getSqlMapClientTemplate().insert("question.createDemandCommunit",demandCommunit);
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	public List<DemandCommunit> searchDemandCommunitList(DemandCommunit demandCommunit){
		return (List<DemandCommunit>)getSqlMapClientTemplate().queryForList("question.searchDemandCommunitList",demandCommunit);
	}
	
	public int createDemandUser(DemandUser demandUser){
		getSqlMapClientTemplate().insert("question.createDemandUser",demandUser);
		return 1;
	}
	
	public Long getDemandId(){
		return (Long)getSqlMapClientTemplate().queryForObject("question.getDemandId");
	}
	
	@SuppressWarnings("unchecked")
	public List<DemandUser> searchDemandUserList(DemandUser demandUser){
		return (List<DemandUser>)getSqlMapClientTemplate().queryForList("question.searchDemandUserList",demandUser);
	}

	public int updateDemandUser(DemandUser demandUser){
		return getSqlMapClientTemplate().update("question.updateDemandUser",demandUser);
	}

}
