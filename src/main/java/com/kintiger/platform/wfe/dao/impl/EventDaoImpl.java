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
	 * ��ȡ����ָ����ǰ�����˶�Ӧ������Detail(��һ��,�п��ܶ���)
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
	 * ��ȡ������ϸ
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
	 * ��������ģ���ѯ��������
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal){
		return (Integer) getSqlMapClientTemplate().queryForObject("wfe.getTripApplyListCount", proEventTotal);
	}
	/**
	 * ��������Ų�ѯ�����б�
	 */
	@SuppressWarnings("unchecked")
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal){
		return getSqlMapClientTemplate().queryForList("wfe.getTripApplyList", proEventTotal);
	}
	
}
