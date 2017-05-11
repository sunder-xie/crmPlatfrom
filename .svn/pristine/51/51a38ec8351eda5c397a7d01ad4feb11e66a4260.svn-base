package com.kintiger.platform.order.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.order.dao.IOrdersDao;
import com.kintiger.platform.order.pojo.AssesReturnList;
import com.kintiger.platform.order.pojo.OrderList;
import com.kintiger.platform.order.pojo.OrderTitle;
import com.kintiger.platform.order.service.IOrderPlatService;
import com.sun.xml.internal.xsom.impl.scd.Iterators.Map;

public class OrderPlatServiceImpl implements IOrderPlatService {

	private IOrdersDao ordersDao;
	private static Log logger = LogFactory.getLog(OrderPlatServiceImpl.class);
	private TransactionTemplate transactionTemplate;

	public IOrdersDao getOrdersDao() {
		return ordersDao;
	}

	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public List<Materiel> findMaterielTypeList(Materiel param) {
		try {
			return ordersDao.findMaterielTypeList(param);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	@Override
	public List<OrderList> findOrderDetailListByTitle(Long titleId) {
		try {
			return ordersDao.findOrderDetailListByTitle(titleId);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public int searchReturnListCount(AssesReturnList returnList) {
		try {
			return ordersDao.searchReturnListCount(returnList);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public List<AssesReturnList> searchReturnLists(AssesReturnList returnList) {
		try {
			return ordersDao.searchReturnLists(returnList);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public BooleanResult importExcel(List<AssesReturnList> list) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		result.setCode("操作失败.");
		try {
			ordersDao.importExcel(list);
			result.setResult(true);
			result.setCode("操作成功.");
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	public BooleanResult delAssesReturnList(AssesReturnList asses) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		result.setCode("操作失败.");
		try {
			ordersDao.delAssesReturnList(asses);
			result.setResult(true);
			result.setCode("操作成功.");
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	@Override
	public List<Materiel> findMaterielList(Materiel param) {
		try {
			return ordersDao.findMaterielList(param);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public List<Kunnr> getKunnrs(Kunnr kunnr) {
		try {
			return ordersDao.getKunnrs(kunnr);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public BooleanResult deleteOrderDetail(OrderList detail) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		result.setCode("操作失败.");
		try {
			ordersDao.deleteOrderList(detail);
			result.setResult(true);
			result.setCode("操作成功.");
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	@Override
	public BooleanResult editOreder(final OrderTitle title,
			final List<OrderList> lists) {
		return (BooleanResult) transactionTemplate
				.execute(new TransactionCallback() {
					public BooleanResult doInTransaction(TransactionStatus ts) {
						BooleanResult result = new BooleanResult();
						result.setResult(true);
						result.setCode("操作成功.");
						// order title
						Long titleId = null;
						try {
							if (null != title.getOrder_id()
									&& 0 != title.getOrder_id()) {
								titleId = title.getOrder_id();
								//判断单子是否已审核
								OrderTitle t=new OrderTitle();
								t.setOrder_id(titleId);
								String[] ss={"C"};
								t.setStates(ss);
								int count=getOrderTitleListCount(t);
								if(count>1){
									result.setCode("订单已审核.");
									result.setResult(false);
									return result;
								}
								ordersDao.updateOrderTitles(title);// 修改订单
							} else {
								titleId = ordersDao.insertOrderTitles(title);// 创建订单
							}
						} catch (Exception e) {
							logger.error(e);
							ts.isRollbackOnly();
							result.setCode("订单母单维护失败.");
							result.setResult(false);
							return result;
						}
						// 订单明细
						OrderTitle t = new OrderTitle();
						t.setUser_id(title.getUser_id());
						t.setOrder_id(titleId);
						try {
							ordersDao.editOrderDetail(lists, t);
						} catch (Exception e) {
							logger.error(e);
							ts.isRollbackOnly();
							result.setResult(false);
							result.setCode("订单明细维护失败.");
							return result;
						}
						return result;
					}
				});
	}

	@Override
	public List<OrderTitle> getOrderTitleList(OrderTitle title) {
		try {
			return ordersDao.searchOrderTitles(title);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public int getOrderTitleListCount(OrderTitle title) {
		try {
			return ordersDao.getOrderTitleListCount(title);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}

	@Override
	public BooleanResult updateOrderState(OrderTitle title) {
		BooleanResult result = new BooleanResult();
		result.setResult(false);
		result.setCode("操作失败.");
		try {
			ordersDao.updateOrderState(title);
			result.setResult(true);
			result.setCode("操作成功.");
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	public OrderTitle getOrderTitleById(OrderTitle title) {
		try {
			return ordersDao.getOrderTitleById(title);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Map<String, BigDecimal> getVaildMoneyByKunnr(String kunnr) {
		// TODO Auto-generated method stub
		return null;
	}
}
