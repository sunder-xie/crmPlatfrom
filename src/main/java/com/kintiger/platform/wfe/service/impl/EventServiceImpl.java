package com.kintiger.platform.wfe.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import org.apache.log4j.Logger;
import org.springframework.transaction.support.TransactionTemplate;


import com.kintiger.platform.wfe.dao.IEventDao;
import com.kintiger.platform.wfe.pojo.ProEventDetail;
import com.kintiger.platform.wfe.pojo.ProEventTotal;
import com.kintiger.platform.wfe.service.IEventService;


public class EventServiceImpl implements IEventService {
	
	private static final Logger logger = Logger.getLogger(EventServiceImpl.class);
	
	private IEventDao eventDao;
	private String appUrl;		//应用工程路径
	private TransactionTemplate transactionTemplate;
	private String wfeUploadPath;
	private String wfeDownloadPath;
	
	public IEventDao getEventDao() {
		return eventDao;
	}

	public void setEventDao(IEventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	public Long getEvent_XmlTempId(){
		return eventDao.getEvent_XmlTempId();
	}
	
	
	
	public String getProEventDetail(String eventId, String userId){
		return eventDao.getProEventDetail(eventId, userId);	
	}

	
	
	/**
	 * 获取ActionId
	 */
	public String getWfeActionId(String eventId) {
		String actionId = eventDao.getWfeActionId(eventId);
		return actionId;
	}
	
	public ProEventTotal getEventTotalById(Long eventId) {
		try {
			return eventDao.getEventTotalById(eventId);
		} catch (Exception e) {
			logger.error("eventId:" + eventId, e);
		}

		return null;
	}
	
	/***
	 * 查询事务明细并排序
	 */
	public List<ProEventDetail> getEventDetailListAndSort(Long eventId) {
		try {
			ProEventDetail proEventDetail = new ProEventDetail();
			proEventDetail.setEventId(eventId);
//			proEventDetail.setSort("time");
//			proEventDetail.setDir("asc");

			List<ProEventDetail> temp = eventDao.getEventDetailList(proEventDetail);

			Map<Long, ProEventDetail> eventDetailMap = new LinkedHashMap<Long, ProEventDetail>();
			StringBuffer link = new StringBuffer();
			for (ProEventDetail detail : temp) {
				// 删除link
				link.delete(0, link.length());

				if ( !"".equals(detail.getFilename()) && detail.getFilename() != null) {
					link.append("<a href=\"javascript:downLoad(");
					link.append(detail.getFileId());
					link.append(");\">");
					link.append(detail.getFilename());
					link.append("</a>&nbsp;");
				}

				ProEventDetail ped = eventDetailMap.get(detail.getEventDtlId());
				if (ped == null) {
					detail.setLink(link.toString());
					eventDetailMap.put(detail.getEventDtlId(), detail);
				} else {
					ped.setLink(link.append(ped.getLink()).toString());
				}
			}

			return new ArrayList<ProEventDetail>(eventDetailMap.values());
		} catch (Exception e) {
			logger.error("查询事务：" + eventId + " 出错！", e);
		}
		return null;
	}
	
	
	public List<ProEventDetail> getStationListByEventId(Long eventId) {
		try {
			return eventDao.getStationListByEventId(eventId);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
	
	
	
	
	
	
	/**
	 * 根据事务模板查询事务总数
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal){
		try {
			return eventDao.getTripApplyListCount(proEventTotal);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}
	/**
	 * 根据事务号查询事务
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal){
		try {
			return eventDao.getTripApplyList(proEventTotal);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public String getWfeUploadPath() {
		return wfeUploadPath;
	}

	public void setWfeUploadPath(String wfeUploadPath) {
		this.wfeUploadPath = wfeUploadPath;
	}

	public String getWfeDownloadPath() {
		return wfeDownloadPath;
	}

	public void setWfeDownloadPath(String wfeDownloadPath) {
		this.wfeDownloadPath = wfeDownloadPath;
	}

	
}
