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
			// �̿�����
			if (s.getStock_differ() > 0) {
				pandian(s, "O");
			}
			// ��ӯ���
			else if (s.getStock_differ() < 0) {
				pandian(s, "I");
			}
		}
		return "success";
	}

	// ��ӯ���
	public void pandian(Stock stock, String type) {
		// �ܵ�
		Instock instock = new Instock();
		instock.setInstock_total_id("");// �̵��޵���
		instock.setInstock_send_place(stock.getStock_stock_place());
		instock.setInstock_good_place(stock.getStock_stock_place());
		instock.setInstock_flag("F");// �̵��־
		instock.setInstock_provide_id(stock.getCust_id());// �ͻ�
		instock.setInstock_provide_name(stock.getCustName());
		instock.setInstock_type(type);// ���
		String totalId = inStockDao.insertInstock(instock);
		// ��ϸ
		Instock_det instockdet = new Instock_det();
		instockdet.setInstock_total_id(totalId);
		instockdet.setInstockdetBatch(stock.getBatch());
		instockdet.setInstockdet_anumber((long) stock.getStock_differ());// �ѵ�/�ѳ���
		instockdet.setInstockdet_unumber(0L);// δ��/δ����
		instockdet.setInstockdet_number((long) stock.getStock_differ());// ����
		instockdet.setMatnr(stock.getCategory_id());
		instockdet.setBezei(stock.getCategoryName());
		instockdet.setType(type);
		inStockDao.insetrInstockdet(instockdet);
		// ���
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
