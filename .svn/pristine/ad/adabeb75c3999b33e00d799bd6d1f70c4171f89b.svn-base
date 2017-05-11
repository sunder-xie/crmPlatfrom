package com.kintiger.platform.wfe.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.wfe.dao.IEventDao;
import com.kintiger.platform.wfe.pojo.ProEventDetail;
import com.kintiger.platform.wfe.pojo.ProEventTotal;

@SuppressWarnings("rawtypes")
public class EventDaoImpl extends BaseDaoImpl implements IEventDao {

	/**
	 * 获取事务指定当前处理人对应的流程Detail(第一条,有可能多条)
	 * @param eventId
	 * @param userId
	 * @return
	 */
	public String getProEventDetail(String eventId, String userId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("event_id", Long.parseLong(eventId.trim()));
		params.put("cur_user_id", userId);
		return (String)getSqlMapClientTemplate().queryForObject("wfe.getDeatilByCurUserAndEventID", params);
	}
	
	public Long getEvent_XmlTempId() {
		return (Long)getSqlMapClientTemplate().queryForObject("wfe.getEvent_XmlTempID");
	}

	

	public String getWfeActionId(String eventId) {
		return (String) getSqlMapClientTemplate().queryForObject("wfe.getWfeActionId",eventId);
	}
	/**
	 * 获取流程明细
	 */
	public ProEventTotal getEventTotalById(Long eventId) {
		return (ProEventTotal) getSqlMapClientTemplate().queryForObject(
				"wfe.getEventTotalById", eventId);
	}
	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail) {
		return getSqlMapClientTemplate().queryForList(
				"wfe.getEventDetailList", eventDetail);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProEventDetail> getStationListByEventId(Long eventId) {
		return getSqlMapClientTemplate().queryForList("wfe.getStationListByEventId", eventId);
	}
	
	
	
	
	/**
	 * 根据事务模板查询事务总数
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal){
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.getTripApplyListCount", proEventTotal);
	}
	/**
	 * 根据事务号查询事务列表
	 */
	@SuppressWarnings("unchecked")
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal){
		return getSqlMapClientTemplate().queryForList("wfe.getTripApplyList", proEventTotal);
	}
	
}
