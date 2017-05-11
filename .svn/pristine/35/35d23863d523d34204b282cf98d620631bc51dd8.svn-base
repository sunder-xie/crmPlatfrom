package com.kintiger.platform.sales.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.sales.dao.ISalesDao;
import com.kintiger.platform.sales.pojo.A603Konp;
import com.kintiger.platform.sales.pojo.Region;
import com.kintiger.platform.sales.pojo.T001;
import com.kintiger.platform.sales.pojo.T001w;
import com.kintiger.platform.sales.pojo.T171t;
import com.kintiger.platform.sales.pojo.Tspa;
import com.kintiger.platform.sales.pojo.Tvbot;
import com.kintiger.platform.sales.pojo.Tvbvk;
import com.kintiger.platform.sales.pojo.Tvgrt;
import com.kintiger.platform.sales.pojo.Tvkbt;
import com.kintiger.platform.sales.pojo.Tvkbz;
import com.kintiger.platform.sales.pojo.Tvko;
import com.kintiger.platform.sales.pojo.Tvtwt;
import com.kintiger.platform.sales.pojo.Tvv1t;
import com.kintiger.platform.sales.pojo.Tvv2t;
import com.kintiger.platform.sales.pojo.Zdqsf;
import com.kintiger.platform.sales.pojo.Zwlqy;

@SuppressWarnings("rawtypes")
public class SalesDaoImpl extends BaseDaoImpl implements ISalesDao {

	public void deleteTvko() {
		this.getSqlMapClientTemplate().delete("sales.deleteTvko");
	}

	public void createTvkoBatch(final List<Tvko> tvkos) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Tvko tvko : tvkos) {
					executor.insert("sales.createTvko", tvko);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getTvkoCount(Tvko tvko) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getTvkoCount", tvko);
	}

	@SuppressWarnings("unchecked")
	public List<Tvko> getTvkoList(Tvko tvko) {
		return this.getSqlMapClientTemplate().queryForList("sales.getTvkoList",
				tvko);
	}

	public void deleteTvtwt() {
		this.getSqlMapClientTemplate().delete("sales.deleteTvtwt");
	}

	public void createTvtwtBatch(final List<Tvtwt> tvtwts) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Tvtwt tvtwt : tvtwts) {
					executor.insert("sales.createTvtwt", tvtwt);
				}
				executor.executeBatch();
				return null;
			}
		});

	}

	public int getTvtwtCount(Tvtwt tvtwt) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getTvtwtCount", tvtwt);
	}

	@SuppressWarnings("unchecked")
	public List<Tvtwt> getTvtwtList(Tvtwt tvtwt) {
		return this.getSqlMapClientTemplate().queryForList(
				"sales.getTvtwtList", tvtwt);
	}

	public void deleteTspa() {
		this.getSqlMapClientTemplate().delete("sales.deleteTspa");
	}

	public void createTspaBatch(final List<Tspa> tspaList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Tspa tspa : tspaList) {
					executor.insert("sales.createTspa", tspa);
				}
				executor.executeBatch();
				return null;
			}
		});

	}

	public int getTspaCount(Tspa tspa) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getTspaCount", tspa);
	}

	@SuppressWarnings("unchecked")
	public List<Tspa> getTspaList(Tspa tspa) {
		return this.getSqlMapClientTemplate().queryForList("sales.getTspaList",
				tspa);
	}

	public void deleteTvkbt() {
		this.getSqlMapClientTemplate().delete("sales.deleteTvkbt");

	}

	public void createTvkbtBatch(final List<Tvkbt> tvkbtList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Tvkbt tvkbt : tvkbtList) {
					executor.insert("sales.createTvkbt", tvkbt);
				}
				executor.executeBatch();
				return null;
			}
		});

	}

	public int getTvkbtCount(Tvkbt tvkbt) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getTvkbtCount", tvkbt);
	}

	@SuppressWarnings("unchecked")
	public List<Tvkbt> getTvkbtList(Tvkbt tvkbt) {
		return this.getSqlMapClientTemplate().queryForList(
				"sales.getTvkbtList", tvkbt);
	}

	public void deleteTvkbz() {
		this.getSqlMapClientTemplate().delete("sales.deleteTvkbz");
	}

	public void createTvkbzBatch(final List<Tvkbz> tvkbzList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Tvkbz tvkbz : tvkbzList) {
					executor.insert("sales.createTvkbz", tvkbz);
				}
				executor.executeBatch();
				return null;
			}
		});

	}

	public int getTvkbzCount(Tvkbz tvkbz) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getTvkbzCount", tvkbz);
	}

	@SuppressWarnings("unchecked")
	public List<Tvkbz> getTvkbzList(Tvkbz tvkbz) {
		return this.getSqlMapClientTemplate().queryForList(
				"sales.getTvkbzList", tvkbz);
	}

	public void deleteTvgrt() {
		this.getSqlMapClientTemplate().delete("sales.deleteTvgrt");
	}

	public void createTvgrtBatch(final List<Tvgrt> tvgrtList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Tvgrt tvgrt : tvgrtList) {
					executor.insert("sales.createTvgrt", tvgrt);
				}
				executor.executeBatch();
				return null;
			}
		});

	}

	public int getTvgrtCount(Tvgrt tvgrt) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getTvgrtCount", tvgrt);
	}

	@SuppressWarnings("unchecked")
	public List<Tvgrt> getTvgrtList(Tvgrt tvgrt) {
		return this.getSqlMapClientTemplate().queryForList(
				"sales.getTvgrtList", tvgrt);
	}

	public void deleteT171t() {
		this.getSqlMapClientTemplate().delete("sales.deleteT171t");
	}

	public void createT171tBatch(final List<T171t> t171tList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (T171t t171t : t171tList) {
					executor.insert("sales.createT171t", t171t);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getT171tCount(T171t t171t) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getT171tCount", t171t);
	}

	@SuppressWarnings("unchecked")
	public List<T171t> getT171tList(T171t t171t) {
		return this.getSqlMapClientTemplate().queryForList(
				"sales.getT171tList", t171t);
	}

	public void deleteT001w() {
		this.getSqlMapClientTemplate().delete("sales.deleteT001w");
	}

	public void createT001wBatch(final List<T001w> t001wList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (T001w t001w : t001wList) {
					executor.insert("sales.createT001w", t001w);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getT001wCount() {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getT001wCount");
	}

	@SuppressWarnings("unchecked")
	public List<T001w> getT001wList(T001w t001w) {
		return this.getSqlMapClientTemplate().queryForList(
				"sales.getT001wList", t001w);
	}

	public void deleteT001() {
		this.getSqlMapClientTemplate().delete("sales.deleteT001");
	}

	public void createT001Batch(final List<T001> t001List) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (T001 t001 : t001List) {
					executor.insert("sales.createT001", t001);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getT001Count(T001 t001) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getT001Count", t001);
	}

	@SuppressWarnings("unchecked")
	public List<T001> getT001List(T001 t001) {
		return this.getSqlMapClientTemplate().queryForList("sales.getT001List",
				t001);
	}

	public void deleteTvv1t() {
		this.getSqlMapClientTemplate().delete("sales.deleteTvv1t");
	}

	public void createTvv1tBatch(final List<Tvv1t> tvv1tList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Tvv1t tvv1t : tvv1tList) {
					executor.insert("sales.createTvv1t", tvv1t);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getTvv1tCount(Tvv1t tvv1t) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getTvv1tCount", tvv1t);
	}

	@SuppressWarnings("unchecked")
	public List<Tvv1t> getTvv1tList(Tvv1t tvv1t) {
		return this.getSqlMapClientTemplate().queryForList(
				"sales.getTvv1tList", tvv1t);
	}

	public void deleteTvv2t() {
		this.getSqlMapClientTemplate().delete("sales.deleteTvv2t");
	}

	public void createTvv2tBatch(final List<Tvv2t> tvv2tList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Tvv2t tvv2t : tvv2tList) {
					executor.insert("sales.createTvv2t", tvv2t);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getTvv2tCount(Tvv2t tvv2t) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getTvv2tCount", tvv2t);
	}

	@SuppressWarnings("unchecked")
	public List<Tvv2t> getTvv2tList(Tvv2t tvv2t) {
		return this.getSqlMapClientTemplate().queryForList(
				"sales.getTvv2tList", tvv2t);
	}

	public void deleteTvbot() {
		this.getSqlMapClientTemplate().delete("sales.deleteTvbot");
	}

	public void createTvbotBatch(final List<Tvbot> tvbotList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Tvbot tvbot : tvbotList) {
					executor.insert("sales.createTvbot", tvbot);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getTvbotCount(Tvbot tvbot) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getTvbotCount", tvbot);
	}

	@SuppressWarnings("unchecked")
	public List<Tvbot> getTvbotList(Tvbot tvbot) {
		return this.getSqlMapClientTemplate().queryForList(
				"sales.getTvbotList", tvbot);
	}

	public void deleteZwlqy() {
		this.getSqlMapClientTemplate().delete("sales.deleteZwlqy");
	}

	public void createZwlqyBatch(final List<Zwlqy> zwlqyList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Zwlqy zwlqy : zwlqyList) {
					executor.insert("sales.createZwlqy", zwlqy);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getZwlqyCount(Zwlqy zwlqy) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getZwlqyCount", zwlqy);
	}

	@SuppressWarnings("unchecked")
	public List<Zwlqy> getZwlqyList(Zwlqy zwlqy) {
		return this.getSqlMapClientTemplate().queryForList(
				"sales.getZwlqyList", zwlqy);
	}

	@SuppressWarnings("unchecked")
	public List<Region> searchRegion(Region region) {
		return (List<Region>) this.getSqlMapClientTemplate().queryForList(
				"sales.searchRegion", region);
	}
	
	public void deleteZdqsf() {
		this.getSqlMapClientTemplate().delete("sales.deleteZdqsf");
	}

	public void createZdqsfBatch(final List<Zdqsf> zdqsfList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Zdqsf zdqsf : zdqsfList) {
					executor.insert("sales.createZdqsf", zdqsf);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getZdqsfCount(Zdqsf zdqsf) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getZdqsfCount", zdqsf);
	}

	@SuppressWarnings("unchecked")
	public List<Zdqsf> getZdqsfList(Zdqsf zdqsf) {
		return this.getSqlMapClientTemplate().queryForList("sales.getZdqsfList",
				zdqsf);
	}

	public void deleteTvbvk() {
		this.getSqlMapClientTemplate().delete("sales.deleteTvbvk");
	}

	public void createTvbvkBatch(final List<Tvbvk> tvbvkList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Tvbvk tvbvk : tvbvkList) {
					executor.insert("sales.createTvbvk", tvbvk);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getTvbvkCount(Tvbvk tvbvk) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getTvbvkCount", tvbvk);
	}

	@SuppressWarnings("unchecked")
	public List<Tvbvk> getTvbvkList(Tvbvk tvbvk) {
		return this.getSqlMapClientTemplate().queryForList("sales.getTvbvkList",
				tvbvk);
	}

	@SuppressWarnings("unchecked")
	public List<Tvgrt> getTvgrtListByZdqsf(Zdqsf zdqsf) {
		return this.getSqlMapClientTemplate().queryForList("sales.getTvgrtListByZdqsf",zdqsf);
	}

	public int getTvgrtZdqsfCount(Zdqsf zdqsf) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("sales.getTvgrtZdqsfCount", zdqsf);
	}

	@SuppressWarnings("unchecked")
	public List<Tvkbt> getTvkbtJsonListByTvbvk(Tvbvk tvbvk) {
		return this.getSqlMapClientTemplate().queryForList("sales.getTvkbtJsonListByTvbvk",tvbvk);
	}

	public int getTvkbtJsonListByTvbvkCount(Tvbvk tvbvk) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("sales.getTvkbtJsonListByTvbvkCount", tvbvk);
	}
	
	public void deleteKbetr() {
		this.getSqlMapClientTemplate().delete("sales.deleteKbetr");
	}

	public void createKbetrBatch(final List<A603Konp> a603KonpList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (A603Konp a603Konp : a603KonpList) {
					executor.insert("sales.createKbetr", a603Konp);
				}
				executor.executeBatch();
				return null;
			}
		});
	}

	public int getKbetrCount(A603Konp a603Konp) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"sales.getKbetrCount", a603Konp);
	}

	@SuppressWarnings("unchecked")
	public List<A603Konp> getKbetrList(A603Konp a603Konp) {
		return this.getSqlMapClientTemplate().queryForList("sales.getKbetrList",
			a603Konp);
	}

}
