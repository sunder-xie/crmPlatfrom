package com.kintiger.platform.stock.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.stock.dao.IStockDao;
import com.kintiger.platform.stock.pojo.Instock;
import com.kintiger.platform.stock.pojo.Stock;

/***
 * 
 * 库存盘点操作
 * @author lc
 *
 */
public class StockDaoImpl extends BaseDaoImpl implements IStockDao{

	
	@SuppressWarnings("unchecked")
	public List<Stock> seachStockList(Stock stock) {
		return (List<Stock>) this.getSqlMapClientTemplate().queryForList(
				"stock.seachStockList", stock);
	}

	public Stock getStockByI(Stock stock) {
		return (Stock) this.getSqlMapClientTemplate().queryForObject(
				"stock.getStockByI", stock);
	}
	
/***
 * 盘点操作
 * 
 * 
 * 
 */
	public void updateStockList(final List<Stock> list) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				List<Stock> inStocks=new ArrayList<Stock>();//入库数组
				List<Stock> outStocks=new ArrayList<Stock>();//出库数组
				for (Stock s : list) {
					if(s.getStock_differ()>0){
						outStocks.add(s);
					}else if(s.getStock_differ()<0){
						inStocks.add(s);
					}else{
						inStocks.add(s);
						continue;
					}
				}
				//入库是 原有库存小于盘点库存
				//出库是原有库存大于盘点库存
				if(inStocks.size()>0){
					Instock in=new Instock();
					//in.set
					for (Stock st : inStocks) {
//						if(getStockByI(st)==null){
//							 st.setStock_flag("N");
////							 st.setShowflag("N");
//							executor.insert("stock.createStock", st);
//						}else{
//							executor.update("stock.updateStock", st);
//						}
					}
					for (Stock st : inStocks) {
						if(getStockByI(st)==null){
							 st.setStock_flag("N");
//							 st.setShowflag("N");
							executor.insert("stock.createStock", st);
						}else{
							executor.update("stock.updateStock", st);
						}
					}
					
				}
				if(outStocks.size()>0){
					for (Stock sc : inStocks) {
						executor.update("stock.updateStock", sc);
					}
				}
				executor.executeBatch();
				return null;
			}
		});
	}

public int seachStockListCount(Stock stock) {
	return (Integer) this.getSqlMapClientTemplate().queryForObject(
			"stock.seachStockListCount", stock);
}

}
