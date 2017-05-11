package com.kintiger.platform.stock.service;

import java.util.List;

import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.stock.pojo.Instock;
import com.kintiger.platform.stock.pojo.Instock_det;
import com.kintiger.platform.stock.pojo.Stock;





public interface IInStockService {
	
	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public static final String ERROR_MESSAGE = "²Ù×÷Ê§°Ü";
	
	public int getInStockCount(Instock instock);
	
	public int getInStockOnly(Instock instock);
	
	public int getInStockDetailListByTotalIdCount(Instock_det instockdet );
	
	public List<Stock> getStockList(Stock stock);
	
	public List<Instock>getInStockList(Instock instock);
	
	public List<Instock_det>getInStockDetailListByTotalId(Instock_det instockdet);
		
	public boolean insertInstock(final Instock_det  instockdet);
	
	
	public boolean insertInstockTotal(final Instock  instock);
	
	public boolean insertInstockDt(final Instock_det  instockdet);
	
	public boolean insertInstockDt1(final Instock_det  instockdet);
	
	public boolean insertInstockDt2(final Instock_det  instockdet);

	public Instock getStockById(String instock_total_id,String type);
	
	public boolean updateStock(final Instock  instock);
	
	
	
	
	

}
