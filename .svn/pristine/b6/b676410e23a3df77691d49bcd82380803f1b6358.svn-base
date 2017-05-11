package com.kintiger.platform.wfe.dao;

import java.util.List;

import com.kintiger.platform.wfe.pojo.ProEventDetail;
import com.kintiger.platform.wfe.pojo.ProEventTotal;

public interface IEventDao {
	
	/**
	 * 获取事务指定处理人对应的流程Detail
	 * @param eventId
	 * @param userId
	 * @return
	 */
	public String getProEventDetail(String eventId, String userId);

	/**
	 * 获取Xml临时文件名
	 * @return
	 */
	public Long getEvent_XmlTempId();
	
	
	public String  getWfeActionId(String eventId);
	/**
	 * 根据事务号查询事务总表
	 */
	public ProEventTotal getEventTotalById(Long eventId);
	/**
	 * 根据事务号查询明细
	 * 
	 * @param eventDetail
	 * @return
	 */
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail);
	
	public List<ProEventDetail> getStationListByEventId(Long eventId);
	
	/**
	 * 根据事务模板查询事务总数
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal);
	/**
	 * 根据事务号查询事务
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal);

}
