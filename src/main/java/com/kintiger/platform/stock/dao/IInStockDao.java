package com.kintiger.platform.stock.dao;

import java.util.List;



import com.kintiger.platform.stock.pojo.Instock;
import com.kintiger.platform.stock.pojo.Instock_det;
import com.kintiger.platform.stock.pojo.Stock;



public interface IInStockDao {

	public int getInStockCount(Instock instock);
	
	public int getInStockOnly(Instock instock);
	
	public int getInStockDetailListByTotalIdCount(Instock_det instockdet);
		
	public List<Instock>getInStockList(Instock instock);
	
	public List<Stock>getStockList(Stock stock);
	
	public List<Instock_det>getInStockDetailListByTotalId(Instock_det instockdet);
	
	public String insertInstock(Instock  instock);
	
	public String insetrInstockdet(Instock_det instockdet);
	
	public String insertStock(Stock stock);
	
	public Instock getStockById(String  instock_total_id,String type);
	
	public int  updateStock(Instock instock);
	
	public int  updateStockTotal(Stock stock);
	
	
}
