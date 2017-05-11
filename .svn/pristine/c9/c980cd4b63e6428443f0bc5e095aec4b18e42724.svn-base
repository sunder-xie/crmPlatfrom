package com.kintiger.platform.question.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.framework.util.DateUtil;
import com.kintiger.platform.framework.util.FileUtil;
import com.kintiger.platform.framework.util.LogUtil;
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
import com.kintiger.platform.question.service.IQuestionService;

public class QuestionServiceImpl implements IQuestionService {

	private static final Log logger = LogFactory
			.getLog(QuestionServiceImpl.class);

	private IQuestionDao questionDao;
	
	private TransactionTemplate transactionTemplate;

	private String questionFilePath;

	@Override
	public int getQuestionCount(Question question) {
		try {
			return questionDao.getQuestionCount(question);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(question), e);
		}
		return 0;
	}

	@Override
	public List<Question> getQuestionList(Question question) {
		try {
			return questionDao.getQuestionList(question);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(question), e);
		}
		return null;
	}

	@Override
	public List<Question> getQuestionListJson() {
		try {
			return questionDao.getQuestionListJson();
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(""), e);
		}
		return null;
	}

	@Override
	public BooleanResult createQuestion(Question question) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			Integer questionId=questionDao.createQuestion(question);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(questionId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("提交失败");
			logger.error(LogUtil.parserBean(question), e);
		}
		return booleanResult;
	}

	@Override
	public BooleanResult updateQuestion(Question question) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			Integer questionId=questionDao.updateQuestion(question);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(questionId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("更新失败");
			logger.error(LogUtil.parserBean(question), e);
		}
		return booleanResult;
	}

	@Override
	public Question getQuestion(Question question) {
		try {
			return questionDao.getQuestion(question);
		} catch (Exception e) {
			logger.error(LogUtil.parserBean(question), e);
		}
		return null;
	}
	public Long getMaxId(){
		try {
			return questionDao.getMaxId();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@Override
	public String getOrgName(Long orgId) {
		try {
			return questionDao.getOrgName(orgId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@Override
	public Question getQuestionById(String qId) {
		try {
			Question question = questionDao.getQuestionById(Long.parseLong(qId));
			List<FileTmp> fileTmpList = questionDao.getFileTmpList(Long.parseLong(qId));
			StringBuffer link = new StringBuffer();
			for (FileTmp fileTmp : fileTmpList) {
				link.append("<a href=\"javascript:downLoad(");
				link.append(fileTmp.getFileId());
				link.append(");\">");
				link.append(fileTmp.getFileName());
				link.append("</a>&nbsp;");
			}
			question.setLink(link.toString());
			return question;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	public String getFileByFileId(Long id){
		List<FileTmp> fileTmpList = questionDao.getFileTmpList(id);
		StringBuffer link = new StringBuffer();
		for (FileTmp fileTmp : fileTmpList) {
			link.append("<a href=\"javascript:downLoad(");
			link.append(fileTmp.getFileId());
			link.append(");\">");
			link.append(fileTmp.getFileName());
			link.append("</a>&nbsp;");
		}
		return link.toString();
	}
	
	@Override
	public List<Borg> getOrgsByUserId(String userId) {
		try {
			return questionDao.getOrgsByUserId(userId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@Override
	public List<AllUsers> getEmpListByOrgId(String orgId) {
		try {
			return questionDao.getEmpListByOrgId(orgId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@Override
	public String getEmpPhone(String empCode) {
		try {
			return questionDao.getEmpPhone(empCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@Override
	public String getEmpEmail(long empId) {
		try {
			return questionDao.getEmpEmail(empId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@Override
	public boolean processAttachments(final File[] uploadFiles, final String[] uploadFileNames, final Long miaDetailId,
			final String timestamp, final String author) {
		boolean result = true;
		if (uploadFileNames != null && uploadFileNames.length > 0 && StringUtils.isNotEmpty(timestamp)) {

			result = (Boolean) transactionTemplate.execute(new TransactionCallback() {
				public Boolean doInTransaction(TransactionStatus ts) {
					// 重命名
					String newFileName = null;
					boolean saveAsFile = false;

					File savedir = new File(questionFilePath + "/" + DateUtil.datetime("yyyyMM"));
					// 如果目录不存在，则新建
					if (!savedir.exists()) {
						savedir.mkdirs();
					}

					for (int i = 0; i < uploadFiles.length; i++) {
						if (uploadFileNames[i] != null && uploadFileNames[i].length() > 0) {

							newFileName = timestamp + i + FileUtil.getFileExtention(uploadFileNames[i]);

							File imageFile =
								new File(questionFilePath + "/" + DateUtil.datetime("yyyyMM") + "/" + newFileName);

							saveAsFile = FileUtil.saveAsFile(uploadFiles[i], imageFile);

							if (!saveAsFile) {
								logger.error("saveAsFile error: " + imageFile);
								ts.setRollbackOnly();
								return false;
							}

							String filename = uploadFileNames[i];
							FileTmp ftp = new FileTmp();
							ftp.setCreator(author);
							ftp.setFileName(filename);
							ftp.setFileNameNew(newFileName);
							ftp.setQuestionKey(miaDetailId);
							ftp.setSubFolders(DateUtil.datetime("yyyyMM"));
							try {
								questionDao.createFileTmp(ftp);
							} catch (Exception e) {
								logger.error(e);
								ts.setRollbackOnly();
								return false;
							}

						}
					}

					return true;
				}
			});
		}

		return result;

	}
	
	public FileTmp getFileTmpByFileId(String fileId) {
		try {
			return questionDao.getFileTmpByFileId(Long.parseLong(fileId.trim()));
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return null;
	}
	
	@Override
	public String getOrgParentId(Long orgId) {
		return questionDao.getOrgParentId(orgId);
	}
	
	@Override
	public int getCommunitListCount(Communit communit) {
		try {
			return questionDao.getCommunitListCount(communit);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return 0;
	}
	
	@Override
	public List<Communit> getCommunitList(Communit communit) {
		try {
			return questionDao.getCommunitList(communit);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return null;
	}
	
	@Override
	public BooleanResult createCommunit(Communit communit) {
		BooleanResult booleanResult = new BooleanResult();
		try {
			Integer communitId=questionDao.createCommunit(communit);
			booleanResult.setResult(true);
			booleanResult.setCode(String.valueOf(communitId));
		} catch (Exception e) {
			booleanResult.setResult(false);
			booleanResult.setCode("创建失败");
			logger.error(LogUtil.parserBean(communit), e);
		}
		return booleanResult;
	}
	
	public int searchDemandListCount(Demand demand){
		try {
			return questionDao.searchDemandListCount(demand);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return 0;
	}
	
	public List<Demand> searchDemandList(Demand demand){
		try {
			return questionDao.searchDemandList(demand);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return null;
	}
	
	public Demand getDemand(Demand demand){
		try {
			Demand d=questionDao.getDemand(demand);
			List<FileTmp> fileTmpList = questionDao.getFileTmpList(demand.getDemandId());
			StringBuffer link = new StringBuffer();
			for (FileTmp fileTmp : fileTmpList) {
				link.append("<a href=\"javascript:downLoad(");
				link.append(fileTmp.getFileId());
				link.append(");\">");
				link.append(fileTmp.getFileName());
				link.append("</a>&nbsp;");
			}
			d.setLink(link.toString());
			return d;
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return null;
	}
	
	public int createDemand(Demand demand){
		try {
			return questionDao.createDemand(demand);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return 0;
	}
	
	public int createDemandFile(DemandFile demandFile){
		try {
			return questionDao.createDemandFile(demandFile);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return 0;
	}
	
	public int updateDemand(Demand demand){
		try {
			return questionDao.updateDemand(demand);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return 0;
	}
	
	public int createDemandCommunit(DemandCommunit demandCommunit){
		try {
			return questionDao.createDemandCommunit(demandCommunit);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return 0;
	}
	
	public List<DemandUser> searchDemandUserList(DemandUser demandUser){
		try {
			return questionDao.searchDemandUserList(demandUser);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return null;
	}
	
	public int createDemandUser(DemandUser demandUser){
		try {
			return questionDao.createDemandUser(demandUser);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return 0;
	}
	
	public List<DemandCommunit> searchDemandCommunitList(DemandCommunit demandCommunit){
		try {
			return questionDao.searchDemandCommunitList(demandCommunit);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return null;
	}
	
	public Long getDemandId(){
		try {
			return questionDao.getDemandId();
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return null;
	}
	
	public int updateDemandUser(DemandUser demandUser){
		try {
			return questionDao.updateDemandUser(demandUser);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return 0;
	}

	public IQuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(IQuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public String getQuestionFilePath() {
		return questionFilePath;
	}

	public void setQuestionFilePath(String questionFilePath) {
		this.questionFilePath = questionFilePath;
	}





}
