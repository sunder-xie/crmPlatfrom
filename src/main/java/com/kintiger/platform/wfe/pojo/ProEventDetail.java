package com.kintiger.platform.wfe.pojo;

import java.io.File;
import java.util.Date;

/**
 * ����������ϸ��
 * 
 * @author hfwu
 * 
 */
public class ProEventDetail extends ProEventTotal {

	private static final long serialVersionUID = 5150545730645207461L;

	/**
	 * ��������ID
	 */
	private Long eventDtlId;

	/**
	 * ��ǰ�����˵�user_id
	 */
	private String curUserId;
	
	private String curUserName;

	/**
	 * ��һ��������user_id
	 */
	private String nextUserId;

	/**
	 * ����
	 */
	private String subTitle;

	/**
	 * �ϴ�����ID
	 */
	private String link;

	/**
	 * ������0Ϊ�ܾ���1Ϊͬ��,9:δ����
	 */
	private String operation;

	/**
	 * ����ʱ��
	 */
	private Date time;

	/**
	 * ��ǰ�����˵�station_id
	 */
	private String curStaId;

	/**
	 * ��ǰģ����ϸID
	 */
	private Long curModelDtlId;

	/**
	 * ��һ��������ϸID
	 */
	private String nextEventdtlId;

	/**
	 * ǰһ�������˵�USER_ID
	 */
	private String preUserId;

	/**
	 * ��һ�������˵�station_id
	 */
	private String preStaId;

	/**
	 * ��һ�������˵�station_id
	 */
	private String nextStaId;

	/**
	 * N��������D��ʾȡ����R��ʾ���ˣ�B��ʾȡ�أ�S��ʾ��Ȩ
	 */
	private String eventDtlFlag;

	/**
	 * ��һ��λID
	 */
	private Long nextOrgId;

	/**
	 * �߰�ʱ��
	 */
	private String limitTime;

	/**
	 * ����Ȩ��ID
	 */
	private String priUserId;

	private Long curOrgId;

	/**
	 * ���еȴ���next_model_detail_id
	 */
	private String waitNmd;

	/**
	 * �ϴ���������
	 */
	private String[] uploadFileNames;

	/**
	 * ���ض����һ�����˽�ɫ
	 */
	private String[] nextStaIds;

	/**
	 * ���ض����һ��������֯
	 */
	private Long[] nextOrgIds;

	/**
	 * ���ض����һ������user_id
	 */
	private String[] nextUserIds;

	/**
	 * ���ض���߰�����
	 */
	private Long[] limitDays;

	private Long limitDay;

	/**
	 * ��ɫ����
	 */
	private String roleName;
	
	/**
	 * ��������
	 */
	private String filename;
	
	private Long fileId;

	/**
	 * ������ϵͳ�ϴ���
	 */
	private String filenameNew;

	/**
	 * ����
	 */
	private File[] uploadFiles;

	private String orgName;

	public Long getEventDtlId() {
		return eventDtlId;
	}

	public void setEventDtlId(Long eventDtlId) {
		this.eventDtlId = eventDtlId;
	}

	public String getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(String curUserId) {
		this.curUserId = curUserId;
	}

	public String getNextUserId() {
		return nextUserId;
	}

	public void setNextUserId(String nextUserId) {
		this.nextUserId = nextUserId;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCurStaId() {
		return curStaId;
	}

	public void setCurStaId(String curStaId) {
		this.curStaId = curStaId;
	}

	public Long getCurModelDtlId() {
		return curModelDtlId;
	}

	public void setCurModelDtlId(Long curModelDtlId) {
		this.curModelDtlId = curModelDtlId;
	}

	public String getNextEventdtlId() {
		return nextEventdtlId;
	}

	public void setNextEventdtlId(String nextEventdtlId) {
		this.nextEventdtlId = nextEventdtlId;
	}

	public String getPreUserId() {
		return preUserId;
	}

	public void setPreUserId(String preUserId) {
		this.preUserId = preUserId;
	}

	public String getPreStaId() {
		return preStaId;
	}

	public void setPreStaId(String preStaId) {
		this.preStaId = preStaId;
	}

	public String getNextStaId() {
		return nextStaId;
	}

	public void setNextStaId(String nextStaId) {
		this.nextStaId = nextStaId;
	}

	public String getEventDtlFlag() {
		return eventDtlFlag;
	}

	public void setEventDtlFlag(String eventDtlFlag) {
		this.eventDtlFlag = eventDtlFlag;
	}

	public Long getNextOrgId() {
		return nextOrgId;
	}

	public void setNextOrgId(Long nextOrgId) {
		this.nextOrgId = nextOrgId;
	}

	public String getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}

	public String getPriUserId() {
		return priUserId;
	}

	public void setPriUserId(String priUserId) {
		this.priUserId = priUserId;
	}

	public Long getCurOrgId() {
		return curOrgId;
	}

	public void setCurOrgId(Long curOrgId) {
		this.curOrgId = curOrgId;
	}

	public String getWaitNmd() {
		return waitNmd;
	}

	public void setWaitNmd(String waitNmd) {
		this.waitNmd = waitNmd;
	}

	public String[] getUploadFileNames() {
		return uploadFileNames;
	}

	public void setUploadFileNames(String[] uploadFileNames) {
		this.uploadFileNames = uploadFileNames;
	}

	public String[] getNextStaIds() {
		return nextStaIds;
	}

	public void setNextStaIds(String[] nextStaIds) {
		this.nextStaIds = nextStaIds;
	}

	public Long[] getNextOrgIds() {
		return nextOrgIds;
	}

	public void setNextOrgIds(Long[] nextOrgIds) {
		this.nextOrgIds = nextOrgIds;
	}

	public String[] getNextUserIds() {
		return nextUserIds;
	}

	public void setNextUserIds(String[] nextUserIds) {
		this.nextUserIds = nextUserIds;
	}

	public Long[] getLimitDays() {
		return limitDays;
	}

	public void setLimitDays(Long[] limitDays) {
		this.limitDays = limitDays;
	}

	public Long getLimitDay() {
		return limitDay;
	}

	public void setLimitDay(Long limitDay) {
		this.limitDay = limitDay;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilenameNew() {
		return filenameNew;
	}

	public void setFilenameNew(String filenameNew) {
		this.filenameNew = filenameNew;
	}

	public File[] getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(File[] uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getCurUserName() {
		return curUserName;
	}

	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}

}