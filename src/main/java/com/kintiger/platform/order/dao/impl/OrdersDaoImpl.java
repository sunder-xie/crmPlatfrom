package com.kintiger.platform.order.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrAddress;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.order.dao.IOrdersDao;
import com.kintiger.platform.order.pojo.AssesReturnList;
import com.kintiger.platform.order.pojo.OrderList;
import com.kintiger.platform.order.pojo.OrderTitle;

@SuppressWarnings("rawtypes")
public class OrdersDaoImpl extends BaseDaoImpl implements IOrdersDao {
	@Override
	public int getOrderTitleListCount(OrderTitle title) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("orders.getOrderTitleListCount",title);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderTitle> searchOrderTitles(OrderTitle ot) {
		return this.getSqlMapClientTemplate().queryForList("orders.searchOrderTitles", ot);
	}
	
	@Override
	public long insertOrderTitles(OrderTitle ot) {
		return (Long) this.getSqlMapClientTemplate().insert("orders.insertOrderTitles", ot);
	}

	@Override
	public int updateOrderTitles(OrderTitle ot) {
		return this.getSqlMapClientTemplate().update("orders.updateOrderTitles", ot);
	}

	@Override
	public List<OrderList> searchOrderLists(int orderId) {
		return null;
	}

	@Override
	public int updateOrderList(List<OrderList> orderList) {
		return 0;
	}

	@Override
	public void deleteOrderList(OrderList detail) {
		this.getSqlMapClientTemplate().update("orders.deleteOrderList", detail);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Materiel> findMaterielTypeList(Materiel param) {
		return this.getSqlMapClientTemplate().queryForList("orders.findMaterielTypeList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderList> findOrderDetailListByTitle(Long titleId) {
		return this.getSqlMapClientTemplate().queryForList("orders.findOrderDetailListByTitle", titleId);
	}
	@SuppressWarnings("unchecked")
	public List<AssesReturnList> searchReturnLists(AssesReturnList returnList) {
		return this.getSqlMapClientTemplate().queryForList("orders.searchReturnLists", returnList);
	}

	@Override
	public int searchReturnListCount(AssesReturnList returnList) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("orders.searchReturnListCount",returnList);
	}

	@Override
	public void importExcel(final List<AssesReturnList> list) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (AssesReturnList asses : list) {
					executor.insert("orders.createAssesReturnList", asses);
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public void delAssesReturnList(AssesReturnList asses) {
		getSqlMapClientTemplate().update("orders.delAssesReturnList",asses);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Materiel> findMaterielList(Materiel param) {
		return getSqlMapClientTemplate().queryForList("orders.findMaterielList",param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kunnr> getKunnrs(Kunnr kunnr) {
		return getSqlMapClientTemplate().queryForList("orders.getKunnrs",kunnr);
	}

	@Override
	public void editOrderDetail(final List<OrderList> orderList, final OrderTitle title) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (OrderList d : orderList) {
					d.setUserId(String.valueOf(title.getUser_id()));
					d.setOrder_id(title.getOrder_id());
					if(null!=d.getList_id()&&0!=d.getList_id()){//修改明细
						executor.insert("orders.updateOrderDetailList", d);
					}else{
						executor.insert("orders.insertOrderDetailList", d);//创建明细
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	@Override
	public void updateOrderState(OrderTitle ot) {
		getSqlMapClientTemplate().update("orders.updateOrderState",ot);
	}

	public OrderTitle getOrderTitleById(OrderTitle title) {
		return (OrderTitle)getSqlMapClientTemplate().queryForObject("orders.getOrderTitleById",title);
	}


}
