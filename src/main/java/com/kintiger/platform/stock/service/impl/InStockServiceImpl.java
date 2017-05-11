package com.kintiger.platform.stock.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.stock.dao.IInStockDao;
import com.kintiger.platform.stock.pojo.Instock;
import com.kintiger.platform.stock.pojo.Instock_det;
import com.kintiger.platform.stock.pojo.Stock;
import com.kintiger.platform.stock.service.IInStockService;


/**
 * @author Allen
 *
 */
public class InStockServiceImpl implements IInStockService{
	private TransactionTemplate transactionTemplate;
	private IInStockDao iInStockDao;
	private static final Logger logger = Logger
	.getLogger(InStockServiceImpl.class);
	
	
	
	
	public IInStockDao getiInStockDao() {
		return iInStockDao;
	}

	public void setiInStockDao(IInStockDao iInStockDao) {
		this.iInStockDao = iInStockDao;
	}

	public int getInStockCount(Instock instock){
		try {
			return iInStockDao.getInStockCount(instock);
		} catch (Exception e) {
			logger.error(instock);
		}
		return 0;
	}
	public int getInStockOnly(Instock instock){
		try {
			return iInStockDao.getInStockOnly(instock);
		} catch (Exception e) {
			logger.error(instock);
		}
		return 0;
	}
	
	
	public int getInStockDetailListByTotalIdCount(Instock_det instockdet){
		try {
			return iInStockDao.getInStockDetailListByTotalIdCount(instockdet);
		} catch (Exception e) {
			logger.error(instockdet);
		}
		return 0;	
	}

	
	public List<Instock>getInStockList(Instock instock){
		List<Instock> list = null;
		try {
			list = iInStockDao.getInStockList(instock);
		} catch (Exception e) {
			logger.error(instock, e);
		}
		return list;
	}
	public List<Stock> getStockList(Stock stock){
		List<Stock> list = null;
		try {
			list = iInStockDao.getStockList(stock);
		} catch (Exception e) {
			logger.error(stock, e);
		}
		return list;
	}
	
	public List<Instock_det>getInStockDetailListByTotalId(Instock_det instockdet){
		List<Instock_det> list = null;
		try {
			list = iInStockDao.getInStockDetailListByTotalId(instockdet);
		} catch (Exception e) {
			logger.error(instockdet, e);
		}
		return list;
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public  boolean insertInstock(final  Instock_det instockdet){
		boolean b = false;
		try{
			Instock instock = new Instock();
			instock.setInstock_total_id(instockdet.getInstock_total_id());
			instock.setInstock_send_place(instockdet.getInstock_send_place());
			instock.setInstock_good_place(instockdet.getInstock_good_place());
			instock.setInstock_flag("C");
			instock.setInstock_provide_id(instockdet.getInstock_provide_id());
			instock.setInstock_provide_name(instockdet.getInstock_provide_name());
			iInStockDao.insertInstock(instock);
			iInStockDao.insetrInstockdet(instockdet);
			 b=true;
		}
		catch(Exception e){
			logger.error(instockdet, e);
		}
		return b;	
	}
	
//	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
//	public  boolean insertStock(final  Stock stock){
//		boolean b = false;
//		try{
//			Instock instock = new Instock();
//			instock.setInstock_total_id(instockdet.getInstock_total_id());
//			instock.setInstock_send_place(instockdet.getInstock_send_place());
//			instock.setInstock_good_place(instockdet.getInstock_good_place());
//			instock.setInstock_flag("C");
//			instock.setInstock_provide_id(instockdet.getInstock_provide_id());
//			instock.setInstock_provide_name(instockdet.getInstock_provide_name());
//			iInStockDao.insertInstock(instock);
//			iInStockDao.insetrInstockdet(instockdet);
//			 b=true;
//		}
//		catch(Exception e){
//			logger.error(instockdet, e);
//		}
//		return b;	
//	}
	
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public  boolean insertInstockTotal(final  Instock instock){
		boolean b = false;
		try{
			instock.setInstock_flag("C");
			iInStockDao.insertInstock(instock);
			 b=true;
		}
		catch(Exception e){
			logger.error(instock, e);
		}
		return b;	
	}
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public boolean insertInstockDt(final Instock_det instockdet){
		boolean b = false;
		try{
			Stock stock = new Stock(); 
			stock.setBatch(instockdet.getInstockdetBatch());
			stock.setCategory_id(instockdet.getMatnr());
			stock.setCust_id(instockdet.getInstock_provide_id());
			stock.setStock_stock_place(instockdet.getInstock_good_place());
			stock.setStock_quantity(Integer.parseInt(instockdet.getInstockdet_anumber().toString()));
			stock.setStock_operator(instockdet.getEmpId());
			stock.setInstock_total_id(instockdet.getInstock_total_id());
			stock.setStock_flag("N");
			iInStockDao.insertStock(stock);
			iInStockDao.insetrInstockdet(instockdet);		
			b=true;
		}
		catch(Exception e){
			logger.error(instockdet, e);
		}
		return b;	
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public boolean insertInstockDt1(final Instock_det instockdet){
		boolean b = false;
		try{
			Stock stock = new Stock(); 
			stock.setBatch(instockdet.getInstockdetBatch());
			stock.setCategory_id(instockdet.getMatnr());
			stock.setCust_id(instockdet.getInstock_provide_id());
			stock.setStock_stock_place(instockdet.getInstock_good_place());
			stock.setInstock_total_id(instockdet.getInstock_total_id());
			stock.setStock_quantity(Integer.parseInt(instockdet.getInstockdet_anumber().toString())
					+instockdet.getSum());
			stock.setStock_operator(instockdet.getEmpId());
			stock.setStock_flag("N");
			iInStockDao.updateStockTotal(stock);
			iInStockDao.insetrInstockdet(instockdet);		
			b=true;
		}
		catch(Exception e){
			logger.error(instockdet, e);
		}
		return b;	
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public boolean insertInstockDt2(final Instock_det instockdet){
		boolean b = false;
		try{
			Stock stock = new Stock(); 
			stock.setBatch(instockdet.getInstockdetBatch());
			stock.setCategory_id(instockdet.getMatnr());
			stock.setCust_id(instockdet.getInstock_provide_id());
			stock.setInstock_total_id(instockdet.getInstock_total_id());
			stock.setStock_stock_place(instockdet.getInstock_good_place());
			stock.setStock_quantity(instockdet.getSum()-Integer.parseInt(instockdet.getInstockdet_anumber().toString()));
			stock.setStock_operator(instockdet.getEmpId());
			stock.setStock_flag("N");
			iInStockDao.updateStockTotal(stock);
			iInStockDao.insetrInstockdet(instockdet);		
			b=true;
		}
		catch(Exception e){
			logger.error(instockdet, e);
		}
		return b;	
	}

	public Instock getStockById(String instock_total_id,String type){
		try {
			return iInStockDao.getStockById(instock_total_id,type);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public boolean updateStock(final Instock instock){
		boolean b = false;
		try{
			iInStockDao.updateStock(instock);
			 b=true;
		}
		catch(Exception e){
			logger.error(instock, e);
		}
		return b;	
		
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	

	


}
