package com.kintiger.platform.wfe.service;

import java.util.List;

import com.kintiger.platform.wfe.pojo.ProEventDetail;
import com.kintiger.platform.wfe.pojo.ProEventTotal;

public interface IEventService {
	public static final String SUCCESS = "success";

	public static final String ERROR = "error";
	
	public static final String NEXT = "next";

	public static final String ERROR_MESSAGE = "操作失败！";

	public static final String ERROR_INPUT_MESSAGE = "操作失败，输入有误！";

	public static final String ERROR_NULL_MESSAGE = "操作失败，单据已不存在！";
	
	public Long getEvent_XmlTempId();

	public String getProEventDetail(String eventId, String userId);
	
	public String  getWfeActionId(String eventId);
	/**
	 * 根据事务号查询事务总表
	 */
	public ProEventTotal getEventTotalById(Long eventId);
	/**
	 * 根据事务模板查询事务总数
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal);
	/**
	 * 根据事务号查询事务
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal);
	/**
	 * 获取事务明细列表并且排序
	 * 
	 * @param eventId
	 * @return
	 */
	public List<ProEventDetail> getEventDetailListAndSort(Long eventId);
	

	
	public List<ProEventDetail> getStationListByEventId(Long eventId);
	
	
}
