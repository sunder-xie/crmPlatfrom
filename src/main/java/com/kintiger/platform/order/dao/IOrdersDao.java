package com.kintiger.platform.order.dao;

import java.util.List;

import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.order.pojo.AssesReturnList;
import com.kintiger.platform.order.pojo.OrderList;
import com.kintiger.platform.order.pojo.OrderTitle;

public interface IOrdersDao {
	/**
	 * ��ѯ����count
	 * 
	 * @param title
	 * @return
	 */
	int getOrderTitleListCount(OrderTitle title);

	// ��ѯ�����б�
	public List<OrderTitle> searchOrderTitles(OrderTitle ot);

	/**
	 * ���ݶ���ID��ѯ����
	 * 
	 * @param title
	 * @return
	 */
	OrderTitle getOrderTitleById(OrderTitle title);

	// ���붩��
	public long insertOrderTitles(OrderTitle ot);

	// ���涩��
	public int updateOrderTitles(OrderTitle ot);

	// ��ѯ������ϸ
	public List<OrderList> searchOrderLists(int orderId);

	// ���涩����ϸ
	public int updateOrderList(List<OrderList> orderList);

	// ɾ��������ϸ
	public void deleteOrderList(OrderList detail);

	/**
	 * �༭������ϸ
	 * 
	 * @param orderList
	 * @param title
	 */
	void editOrderDetail(List<OrderList> orderList, OrderTitle title);

	/**
	 * ��ȡ�����̶���ƽ̨���������
	 * 
	 * @param param
	 * @return
	 */
	public List<Materiel> findMaterielTypeList(Materiel param);

	/**
	 * ��ѯ����
	 * 
	 * @param param
	 * @return
	 */
	List<Materiel> findMaterielList(Materiel param);

	/**
	 * ���ݶ���ID��ȡ�����̶���ƽ̨��ϸ
	 * 
	 * @param titleId
	 * @return
	 */
	public List<OrderList> findOrderDetailListByTitle(Long titleId);

	/**
	 * ��ѯ���˷����嵥count
	 * 
	 * @param returnList
	 * @return
	 */
	int searchReturnListCount(AssesReturnList returnList);

	/**
	 * ��ѯ���˷����嵥
	 * 
	 * @param returnList
	 * @return
	 */
	List<AssesReturnList> searchReturnLists(AssesReturnList returnList);

	/**
	 * �������뿼�˷����嵥
	 * 
	 * @param list
	 * @return
	 */
	void importExcel(List<AssesReturnList> list);

	/**
	 * ����ɾ�������嵥
	 * 
	 * @param asses
	 */
	void delAssesReturnList(AssesReturnList asses);

	/**
	 * ��֤�������Ƿ����
	 * 
	 * @param kunnr
	 * @return
	 */
	List<Kunnr> getKunnrs(Kunnr kunnr);

	/**
	 * �޸Ķ���״̬
	 * 
	 * @param ot
	 */
	void updateOrderState(OrderTitle ot);
}
