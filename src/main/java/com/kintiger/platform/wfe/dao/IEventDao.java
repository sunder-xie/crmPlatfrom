package com.kintiger.platform.wfe.dao;

import java.util.List;

import com.kintiger.platform.wfe.pojo.ProEventDetail;
import com.kintiger.platform.wfe.pojo.ProEventTotal;

public interface IEventDao {
	
	/**
	 * ��ȡ����ָ�������˶�Ӧ������Detail
	 * @param eventId
	 * @param userId
	 * @return
	 */
	public String getProEventDetail(String eventId, String userId);

	/**
	 * ��ȡXml��ʱ�ļ���
	 * @return
	 */
	public Long getEvent_XmlTempId();
	
	
	public String  getWfeActionId(String eventId);
	/**
	 * ��������Ų�ѯ�����ܱ�
	 */
	public ProEventTotal getEventTotalById(Long eventId);
	/**
	 * ��������Ų�ѯ��ϸ
	 * 
	 * @param eventDetail
	 * @return
	 */
	public List<ProEventDetail> getEventDetailList(ProEventDetail eventDetail);
	
	public List<ProEventDetail> getStationListByEventId(Long eventId);
	
	/**
	 * ��������ģ���ѯ��������
	 */
	public int getTripApplyListCount(ProEventTotal proEventTotal);
	/**
	 * ��������Ų�ѯ����
	 */
	public List<ProEventTotal> getTripApplyList(ProEventTotal proEventTotal);

}
