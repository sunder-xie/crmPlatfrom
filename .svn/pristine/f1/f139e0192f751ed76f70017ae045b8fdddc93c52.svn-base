package com.kintiger.platform.stock.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.stock.dao.IInStockDao;
import com.kintiger.platform.stock.dao.IStockDao;
import com.kintiger.platform.stock.pojo.Instock;
import com.kintiger.platform.stock.pojo.Instock_det;
import com.kintiger.platform.stock.pojo.Stock;
import com.kintiger.platform.stock.service.IStockService;

public class StockServiceImpl implements IStockService {

	public IStockDao stockDao;
	private IInStockDao inStockDao;

	public List<Stock> seachStockList(Stock stock) {
		try {
			return stockDao.seachStockList(stock);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int seachStockListCount(Stock stock) {
		try {
			return stockDao.seachStockListCount(stock);
		} catch (Exception e) {
			return 0;
		}
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public String pandianStock(List<Stock> stockList) {
		for (Stock s : stockList) {
			// 盘亏出库
			if (s.getStock_differ() > 0) {
				pandian(s, "O");
			}
			// 盘盈入库
			else if (s.getStock_differ() < 0) {
				pandian(s, "I");
			}
		}
		return "success";
	}

	// 盘盈入库
	public void pandian(Stock stock, String type) {
		// 总单
		Instock instock = new Instock();
		instock.setInstock_total_id("");// 盘点无单号
		instock.setInstock_send_place(stock.getStock_stock_place());
		instock.setInstock_good_place(stock.getStock_stock_place());
		instock.setInstock_flag("F");// 盘点标志
		instock.setInstock_provide_id(stock.getCust_id());// 客户
		instock.setInstock_provide_name(stock.getCustName());
		instock.setInstock_type(type);// 入库
		String totalId = inStockDao.insertInstock(instock);
		// 明细
		Instock_det instockdet = new Instock_det();
		instockdet.setInstock_total_id(totalId);
		instockdet.setInstockdetBatch(stock.getBatch());
		instockdet.setInstockdet_anumber((long) stock.getStock_differ());// 已到/已出数
		instockdet.setInstockdet_unumber(0L);// 未到/未出数
		instockdet.setInstockdet_number((long) stock.getStock_differ());// 总数
		instockdet.setMatnr(stock.getCategory_id());
		instockdet.setBezei(stock.getCategoryName());
		instockdet.setType(type);
		inStockDao.insetrInstockdet(instockdet);
		// 库存
		stock.setStock_quantity(stock.getStock_pandian());
		inStockDao.updateStockTotal(stock);

	}

	public IStockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(IStockDao stockDao) {
		this.stockDao = stockDao;
	}

	public IInStockDao getInStockDao() {
		return inStockDao;
	}

	public void setInStockDao(IInStockDao inStockDao) {
		this.inStockDao = inStockDao;
	}

}
