package com.kintiger.platform.order.dao;

import java.util.List;

import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.order.pojo.AssesReturnList;
import com.kintiger.platform.order.pojo.OrderList;
import com.kintiger.platform.order.pojo.OrderTitle;

public interface IOrdersDao {
	/**
	 * 查询订单count
	 * 
	 * @param title
	 * @return
	 */
	int getOrderTitleListCount(OrderTitle title);

	// 查询订单列表
	public List<OrderTitle> searchOrderTitles(OrderTitle ot);

	/**
	 * 根据订单ID查询订单
	 * 
	 * @param title
	 * @return
	 */
	OrderTitle getOrderTitleById(OrderTitle title);

	// 插入订单
	public long insertOrderTitles(OrderTitle ot);

	// 保存订单
	public int updateOrderTitles(OrderTitle ot);

	// 查询订单明细
	public List<OrderList> searchOrderLists(int orderId);

	// 保存订单明细
	public int updateOrderList(List<OrderList> orderList);

	// 删除订单明细
	public void deleteOrderList(OrderList detail);

	/**
	 * 编辑订单明细
	 * 
	 * @param orderList
	 * @param title
	 */
	void editOrderDetail(List<OrderList> orderList, OrderTitle title);

	/**
	 * 获取经销商订单平台的物料类别
	 * 
	 * @param param
	 * @return
	 */
	public List<Materiel> findMaterielTypeList(Materiel param);

	/**
	 * 查询物料
	 * 
	 * @param param
	 * @return
	 */
	List<Materiel> findMaterielList(Materiel param);

	/**
	 * 根据订单ID获取经销商订单平台明细
	 * 
	 * @param titleId
	 * @return
	 */
	public List<OrderList> findOrderDetailListByTitle(Long titleId);

	/**
	 * 查询考核返还清单count
	 * 
	 * @param returnList
	 * @return
	 */
	int searchReturnListCount(AssesReturnList returnList);

	/**
	 * 查询考核返还清单
	 * 
	 * @param returnList
	 * @return
	 */
	List<AssesReturnList> searchReturnLists(AssesReturnList returnList);

	/**
	 * 批量导入考核返还清单
	 * 
	 * @param list
	 * @return
	 */
	void importExcel(List<AssesReturnList> list);

	/**
	 * 批量删除返还清单
	 * 
	 * @param asses
	 */
	void delAssesReturnList(AssesReturnList asses);

	/**
	 * 验证经销商是否存在
	 * 
	 * @param kunnr
	 * @return
	 */
	List<Kunnr> getKunnrs(Kunnr kunnr);

	/**
	 * 修改订单状态
	 * 
	 * @param ot
	 */
	void updateOrderState(OrderTitle ot);
}
