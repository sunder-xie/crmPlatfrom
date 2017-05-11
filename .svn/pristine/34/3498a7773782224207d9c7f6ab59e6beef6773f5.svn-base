package com.kintiger.platform.order.service;

import java.math.BigDecimal;
import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.order.pojo.AssesReturnList;
import com.kintiger.platform.order.pojo.OrderList;
import com.kintiger.platform.order.pojo.OrderTitle;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

public interface IOrderPlatService {

	/**
	 * 查询物料组
	 * 
	 * @param param
	 * @return
	 */
	List<Materiel> findMaterielTypeList(Materiel param);

	/**
	 * 查询物料
	 * 
	 * @param param
	 * @return
	 */
	List<Materiel> findMaterielList(Materiel param);

	/**
	 * 查询订单明细
	 * 
	 * @param titleId
	 * @return
	 */
	List<OrderList> findOrderDetailListByTitle(Long titleId);

	/**
	 * 查询订单
	 * 
	 * @param title
	 * @return
	 */
	List<OrderTitle> getOrderTitleList(OrderTitle title);

	/**
	 * 查询订单count
	 * 
	 * @param title
	 * @return
	 */
	int getOrderTitleListCount(OrderTitle title);

	/**
	 * 根据订单ID查询订单
	 * 
	 * @param title
	 * @return
	 */
	OrderTitle getOrderTitleById(OrderTitle title);

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
	BooleanResult importExcel(List<AssesReturnList> list);

	/**
	 * 批量删除返还清单
	 * 
	 * @param asses
	 */
	BooleanResult delAssesReturnList(AssesReturnList asses);

	/**
	 * 验证经销商是否存在
	 * 
	 * @param kunnr
	 * @return
	 */
	List<Kunnr> getKunnrs(Kunnr kunnr);

	/**
	 * 删除订单明细
	 * 
	 * @param detail
	 * @return
	 */
	BooleanResult deleteOrderDetail(OrderList detail);

	/**
	 * 保存编辑后的订单
	 * 
	 * @param detail
	 * @return
	 */
	BooleanResult editOreder(OrderTitle title, List<OrderList> lists);

	/**
	 * 删除订单或撤掉、审核订单
	 * 
	 * @param title
	 * @return
	 */
	BooleanResult updateOrderState(OrderTitle title);
	/**
	 * 根据经销商获取费用兑换、预打款金额
	 * @param kunnr
	 * @return
	 */
	Map<String, BigDecimal> getVaildMoneyByKunnr(String kunnr);
}
