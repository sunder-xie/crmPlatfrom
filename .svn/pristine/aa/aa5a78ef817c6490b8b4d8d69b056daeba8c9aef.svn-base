package com.kintiger.platform.kunnr.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.framework.util.EncryptUtil;
import com.kintiger.platform.goal.pojo.BCustomerTarget;
import com.kintiger.platform.kunnr.dao.IKunnrDao;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrAcount;
import com.kintiger.platform.kunnr.pojo.KunnrAddress;
import com.kintiger.platform.kunnr.pojo.KunnrApplySave;
import com.kintiger.platform.kunnr.pojo.KunnrBrand;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;
import com.kintiger.platform.kunnr.pojo.KunnrLicense;
import com.kintiger.platform.kunnr.pojo.KunnrLogisticsArea;
import com.kintiger.platform.kunnr.pojo.KunnrRole;
import com.kintiger.platform.kunnr.pojo.KunnrSalesArea;
import com.kintiger.platform.kunnr.pojo.KunnrSapCodeObject;
import com.kintiger.platform.kunnr.service.IKunnrAddressService;
import com.kintiger.platform.org.pojo.Borg;

public class KunnrDaoImpl extends BaseDaoImpl implements IKunnrDao {
	private IKunnrAddressService kunnrAddressService;

	public IKunnrAddressService getKunnrAddressService() {
		return kunnrAddressService;
	}
	public void setKunnrAddressService(IKunnrAddressService kunnrAddressService) {
		this.kunnrAddressService = kunnrAddressService;
	}
	public String getRanKunnrCode() {
		return (String) this.getSqlMapClientTemplate().queryForObject(
				"kunnr.getRanKunnrCode");
	}
	public String getRanKunnrSapCode() {
		return (String) this.getSqlMapClientTemplate().queryForObject(
				"kunnr.getRanKunnrSapCode");
	}
	public String getRanKunnrDMCode() {
		return (String) this.getSqlMapClientTemplate().queryForObject(
				"kunnr.getRanKunnrDMCode");
	}
	/**
	 * 经销商sap编码序列：省/城市+(该城市下最大编号+1)
	 *参数：省+城市
	 * @return
	 */
	public String getRanKunnrCodeNew(String code){
		return (String) this.getSqlMapClientTemplate().queryForObject(
		"kunnr.getRanKunnrCodeNew",code);
	}
	public long createKunnr(Kunnr kunnr) {
		return (Long) getSqlMapClientTemplate().insert("kunnr.createKunnr",
				kunnr);
	}

	public long createKunnrBusiness(KunnrBusiness business) {
		return (Long) getSqlMapClientTemplate().insert(
				"kunnr.createKunnrBusiness", business);
	}

	public void createKunnrAddress(final List<KunnrAddress> kunnrAddressList,
			final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrAddress address : kunnrAddressList) {
					address.setKunnr(kunnr);
					executor.insert("kunnr.createKunnrAddress", address);
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	public void createKunnrBrand(final List<KunnrBrand> kunnrBrandList,
			final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrBrand brand : kunnrBrandList) {
					brand.setKunnr(kunnr);
					executor.insert("kunnr.createKunnrBrand", brand);
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	public void createKunnrAcount(final List<KunnrAcount> kunnrAcountList,
			final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				// 折扣类型行转列
				for (KunnrAcount acount : kunnrAcountList) {
					KunnrAcount count = new KunnrAcount();
					count.setKunnr(kunnr);
					count.setBonus(acount.getBonus());
					count.setFlag(acount.getFlag());
					if (null != acount.getStartDate()) {
						count.setStartDate(acount.getStartDate());
					} else {
						count.setStartDate("");
					}
					if (null != acount.getEndDate()) {
						count.setEndDate(acount.getEndDate());
					} else {
						count.setEndDate("");
					}
					// Y表示2级返一级
					if ("Y".equals(acount.getFlag())) {
						if (StringUtils.isNotEmpty(acount.getType2A())) {
							count.setAcountType("A");
							count.setAcount(acount.getType2A());
							count.setTypeMoney(acount.getTypeMoney());
							if (null != acount.getAcountSapA()) {
								count.setAcountSap(acount.getAcountSapA());
							} else {
								count.setAcountSap("");
							}
							executor.insert("kunnr.createKunnrAcount", count);
						}
						if (StringUtils.isNotEmpty(acount.getType2B())) {
							count.setAcountType("B");
							count.setAcount(acount.getType2B());
							count.setTypeMoney(acount.getTypeMoney());
							if (null != acount.getAcountSapB()) {
								count.setAcountSap(acount.getAcountSapB());
							} else {
								count.setAcountSap("");
							}
							executor.insert("kunnr.createKunnrAcount", count);
						}
						if (StringUtils.isNotEmpty(acount.getType2C())) {
							count.setAcountType("C");
							count.setAcount(acount.getType2C());
							count.setTypeMoney(acount.getTypeMoney());
							if (null != acount.getAcountSapC()) {
								count.setAcountSap(acount.getAcountSapC());
							} else {
								count.setAcountSap("");
							}
							executor.insert("kunnr.createKunnrAcount", count);
						}
					}
					if (StringUtils.isNotEmpty(acount.getTypeA())) {
						count.setAcountType("A");
						count.setAcount(acount.getTypeA());
						count.setTypeMoney(acount.getTypeMoney());
						count.setFlag("N");
						if (null != acount.getAcountSapA()) {
							count.setAcountSap(acount.getAcountSapA());
						} else {
							count.setAcountSap("");
						}
						executor.insert("kunnr.createKunnrAcount", count);
					}
					if (StringUtils.isNotEmpty(acount.getTypeB())) {
						count.setAcountType("B");
						count.setAcount(acount.getTypeB());
						count.setTypeMoney(acount.getTypeMoney());
						count.setFlag("N");
						if (null != acount.getAcountSapB()) {
							count.setAcountSap(acount.getAcountSapB());
						} else {
							count.setAcountSap("");
						}
						executor.insert("kunnr.createKunnrAcount", count);
					}
					if (StringUtils.isNotEmpty(acount.getTypeC())) {
						count.setAcountType("C");
						count.setFlag("N");
						count.setAcount(acount.getTypeC());
						if (null != acount.getAcountSapC()) {
							count.setAcountSap(acount.getAcountSapC());
						} else {
							count.setAcountSap("");
						}
						executor.insert("kunnr.createKunnrAcount", count);
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	public void createKunnrLicense(final List<KunnrLicense> kunnrLicenseList,
			final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrLicense license : kunnrLicenseList) {
					license.setKunnr(kunnr);
					KunnrLicense lice = new KunnrLicense();
					lice.setLicenseName(license.getLicenseName());
					lice.setKunnr(kunnr);
					List<KunnrLicense> liceList = new ArrayList<KunnrLicense>();
					liceList = getKunnrLicenseListByLicense(lice);
					if (liceList.size() > 0) {              //判断经销商此类证照是否存在，存在则将旧的置为无效，然后增加当前上传的证照
						for (int i = 0; i < liceList.size(); i++) {
							KunnrLicense lice1 = new KunnrLicense();
							lice1 = liceList.get(i);
							lice1.setKunnr(kunnr);
							//if(lice1.getLicenseValid().equals(license.getLicenseValid())){
							executor.update("kunnr.updateKunnrLicense", lice1);
							//}
						}
						executor.insert("kunnr.createKunnrLicense", license);
					} else {             //经销商不存在此类照片时，则新增此类照片
						executor.insert("kunnr.createKunnrLicense", license);
					}

				}
				executor.executeBatch();
				return true;
			}
		});
	}

	public boolean kunnrFreeze(Kunnr kunnr) {
		int result = getSqlMapClientTemplate().update("kunnr.kunnrFreeze",kunnr);
		return result > 0 ? true : false;
	}

	public boolean kunnrClose(Kunnr kunnr) {
		int result = getSqlMapClientTemplate()
				.update("kunnr.kunnrClose", kunnr);
		return result > 0 ? true : false;
	}

	public Integer updateKunnr(Kunnr kunnr) {
		return (Integer) getSqlMapClientTemplate().update("kunnr.updateKunnr",
				kunnr);
	}

	public Integer updateKunnrBusiness(KunnrBusiness business) {
		return (Integer) getSqlMapClientTemplate().update(
				"kunnr.updateKunnrBusiness", business);
	}

	public void updateAndCreateKunnrAddress(
			final List<KunnrAddress> kunnrAddressList, final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrAddress address : kunnrAddressList) {
					address.setKunnr(kunnr);
					BooleanResult booleanResult =null;
					if (address.getId() != null) {//已存在，修改送达方
//						executor.update("kunnr.updateKunnrAddress", address);
						 booleanResult = kunnrAddressService
								.updateKunnrAddress(address);
					} else {//不存在，就新增送达方
						String	maxSd = kunnrAddressService.getMaxKunnrSd(kunnr);
						if (StringUtils.isNotEmpty(maxSd)) {
							int ext = Integer.parseInt(maxSd.substring(8, 10)) + 1;
							if(ext>=10){
							maxSd = maxSd.substring(0, 8) + ext;
							}else{
							maxSd = maxSd.substring(0, 9) + ext;
							}
						} else {
							maxSd = kunnr + "01";
						}
						address.setKunnrSd(maxSd);
						 booleanResult = kunnrAddressService
								.createKunnrAddress(address);
					}
					if(!booleanResult.getResult()){
//							executor.insert("kunnr.createKunnrAddress", address);
						return false;
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	public void updateAndCreateKunnrBrand(
			final List<KunnrBrand> kunnrBrandList, final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrBrand brand : kunnrBrandList) {
					brand.setKunnr(kunnr);
					if (brand.getId() != null) {
						executor.update("kunnr.updateKunnrBrand", brand);
					} else {
						executor.insert("kunnr.createKunnrBrand", brand);
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	public void updateAndCreateKunnrAcount(
			final List<KunnrAcount> kunnrAcountList, final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				// 折扣类型行转列
				for (KunnrAcount acount : kunnrAcountList) {
					// Y表示2级返一级
					KunnrAcount count = new KunnrAcount();
					count.setFlag(acount.getFlag());
					count.setKunnr(kunnr);
					count.setBonus(acount.getBonus());
					if (null != acount.getStartDate()) {
						count.setStartDate(acount.getStartDate());
					} else {
						count.setStartDate("");
					}
					if (null != acount.getEndDate()) {
						count.setEndDate(acount.getEndDate());
					} else {
						count.setEndDate("");
					}
					if ("Y".equals(acount.getFlag())) {
						if (StringUtils.isNotEmpty(acount.getType2A())) {
							count.setAcountType("A");
							count.setAcount(acount.getType2A());
							count.setTypeMoney(acount.getTypeMoney());
							if (null != acount.getAcountSapA()) {
								count.setAcountSap(acount.getAcountSapA());
							} else {
								count.setAcountSap("");
							}
							if (acount.getType2AId() != null) {
								count.setId(acount.getType2AId());
								executor.update("kunnr.updateKunnrAcount",
										count);
							} else {
								executor.insert("kunnr.createKunnrAcount",
										count);
							}
						}
						if (StringUtils.isNotEmpty(acount.getType2B())) {
							count.setAcountType("B");
							count.setAcount(acount.getType2B());
							count.setTypeMoney(acount.getTypeMoney());
							if (null != acount.getAcountSapB()) {
								count.setAcountSap(acount.getAcountSapB());
							} else {
								count.setAcountSap("");
							}
							if (acount.getType2BId() != null) {
								count.setId(acount.getType2BId());
								executor.update("kunnr.updateKunnrAcount",
										count);
							} else {
								executor.insert("kunnr.createKunnrAcount",
										count);
							}
						}
						if (StringUtils.isNotEmpty(acount.getType2C())) {
							count.setAcountType("C");
							count.setAcount(acount.getType2C());
							count.setTypeMoney(acount.getTypeMoney());
							if (null != acount.getAcountSapC()) {
								count.setAcountSap(acount.getAcountSapC());
							} else {
								count.setAcountSap("");
							}
							if (acount.getType2CId() != null) {
								count.setId(acount.getType2CId());
								executor.update("kunnr.updateKunnrAcount",
										count);
							} else {
								executor.insert("kunnr.createKunnrAcount",
										count);
							}
						}
					}
					if (StringUtils.isNotEmpty(acount.getTypeA())) {
						count.setAcountType("A");
						count.setAcount(acount.getTypeA());
						count.setFlag("N");
						count.setTypeMoney(acount.getTypeMoney());
						if (null != acount.getAcountSapA()) {
							count.setAcountSap(acount.getAcountSapA());
						} else {
							count.setAcountSap("");
						}
						if (acount.getTypeAId() != null) {
							count.setId(acount.getTypeAId());
							executor.update("kunnr.updateKunnrAcount", count);
						} else {
							executor.insert("kunnr.createKunnrAcount", count);
						}
					}
					if (StringUtils.isNotEmpty(acount.getTypeB())) {
						count.setAcountType("B");
						count.setAcount(acount.getTypeB());
						count.setFlag("N");
						count.setTypeMoney(acount.getTypeMoney());
						if (null != acount.getAcountSapB()) {
							count.setAcountSap(acount.getAcountSapB());
						} else {
							count.setAcountSap("");
						}
						if (acount.getTypeBId() != null) {
							count.setId(acount.getTypeBId());
							executor.update("kunnr.updateKunnrAcount", count);
						} else {
							executor.insert("kunnr.createKunnrAcount", count);
						}
					}
					if (StringUtils.isNotEmpty(acount.getTypeC())) {
						count.setAcountType("C");
						count.setFlag("N");
						count.setTypeMoney(acount.getTypeMoney());
						if (null != acount.getAcountSapC()) {
							count.setAcountSap(acount.getAcountSapC());
						} else {
							count.setAcountSap("");
						}
						count.setAcount(acount.getTypeC());
						if (acount.getTypeCId() != null) {
							count.setId(acount.getTypeCId());
							executor.update("kunnr.updateKunnrAcount", count);
						} else {
							executor.insert("kunnr.createKunnrAcount", count);
						}
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	public void removeBrand(final String killBrand) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				String[] ids = killBrand.split(",");
				for (String brandId : ids) {
					if (StringUtils.isNotEmpty(brandId)) {
						executor.delete("kunnr.removeBrand",
								Long.parseLong(brandId));
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	public void removeAcount(final String killAcount) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				String[] ids = killAcount.split(",");
				for (String acountId : ids) {
					if (StringUtils.isNotEmpty(acountId)) {
						executor.update("kunnr.removeAcount",
								Long.parseLong(acountId));
					}
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	public int kunnrSearchCount(Kunnr kunnr) {
		return (Integer) getSqlMapClientTemplate().queryForObject("kunnr.kunnrSearchCount", kunnr);
	}

	@SuppressWarnings("unchecked")
	public List<Kunnr> kunnrSearch(Kunnr kunnr) {
		return (List<Kunnr>) getSqlMapClientTemplate().queryForList(
				"kunnr.kunnrSearch", kunnr);
	}

	public Kunnr getKunnrEntity(Kunnr kunnr) {
		return (Kunnr) getSqlMapClientTemplate().queryForObject(
				"kunnr.getKunnrEntity", kunnr);
	}

	public KunnrBusiness getKunnrBusinessEntity(Kunnr kunnr) {
		return (KunnrBusiness) getSqlMapClientTemplate().queryForObject(
				"kunnr.getKunnrBusinessEntity", kunnr);
	}

	@SuppressWarnings("unchecked")
	public List<KunnrAddress> getKunnrAddressList(Kunnr kunnr) {
		return (List<KunnrAddress>) getSqlMapClientTemplate().queryForList(
				"kunnr.getKunnrAddressList", kunnr);
	}

	@SuppressWarnings("unchecked")
	public List<KunnrBrand> getKunnrBrandList(Kunnr kunnr) {
		return (List<KunnrBrand>) getSqlMapClientTemplate().queryForList(
				"kunnr.getKunnrBrandList", kunnr);
	}

	@SuppressWarnings("unchecked")
	public List<KunnrLicense> getKunnrLicenseList(Kunnr kunnr) {
		return (List<KunnrLicense>) getSqlMapClientTemplate().queryForList(
				"kunnr.getKunnrLicenseList", kunnr);
	}

	@SuppressWarnings("unchecked")
	public List<BCustomerTarget> getBCustomerTargetList(Kunnr kunnr) {
		return (List<BCustomerTarget>) getSqlMapClientTemplate().queryForList(
				"goal.getBCustomerTargetList", kunnr);
	}

	@SuppressWarnings("unchecked")
	public List<KunnrSalesArea> getKunnrSalesAreaList(Kunnr kunnr) {
		return (List<KunnrSalesArea>) getSqlMapClientTemplate().queryForList(
				"kunnr.getKunnrSalesAreaList", kunnr);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KunnrAcount> getKunnrAcountList(Kunnr kunnr) {
		List<KunnrAcount> tempList = (List<KunnrAcount>) getSqlMapClientTemplate()
				.queryForList("kunnr.getKunnrAcountList", kunnr);
		Map<String, KunnrAcount> tempMap = new HashMap<String, KunnrAcount>();
		List<KunnrAcount> kunnrAcountList = new ArrayList<KunnrAcount>();
		// 折扣类型行转列
		for (KunnrAcount acount : tempList) {
			if (tempMap.containsKey(acount.getBonus())) {
				if ("A".equals(acount.getAcountType())) {
					if ("Y".equals(acount.getFlag())) {
						tempMap.get(acount.getBonus()).setType2A(
								acount.getAcount());
						tempMap.get(acount.getBonus()).setType2AId(
								acount.getId());
						tempMap.get(acount.getBonus()).setAcountSapA(acount.getAcountSap());
						tempMap.get(acount.getBonus()).setFlag("Y");
					} else {
						tempMap.get(acount.getBonus()).setTypeA(
								acount.getAcount());
						tempMap.get(acount.getBonus()).setTypeAId(
								acount.getId());
						tempMap.get(acount.getBonus()).setAcountSapA(acount.getAcountSap());
						tempMap.get(acount.getBonus()).setTypeMoney(
								acount.getTypeMoney());
					}
				}
				if ("B".equals(acount.getAcountType()))
					if ("Y".equals(acount.getFlag())) {
						tempMap.get(acount.getBonus()).setType2B(
								acount.getAcount());
						tempMap.get(acount.getBonus()).setType2BId(
								acount.getId());
						tempMap.get(acount.getBonus()).setAcountSapB(acount.getAcountSap());
					} else {
						tempMap.get(acount.getBonus()).setTypeB(
								acount.getAcount());
						tempMap.get(acount.getBonus()).setTypeBId(
								acount.getId());
						tempMap.get(acount.getBonus()).setAcountSapB(acount.getAcountSap());
						tempMap.get(acount.getBonus()).setTypeMoney(
								acount.getTypeMoney());
					}
				if ("C".equals(acount.getAcountType()))
					if ("Y".equals(acount.getFlag())) {
						tempMap.get(acount.getBonus()).setType2C(
								acount.getAcount());
						tempMap.get(acount.getBonus()).setType2CId(
								acount.getId());
						tempMap.get(acount.getBonus()).setAcountSapC(acount.getAcountSap());
					} else {
						tempMap.get(acount.getBonus()).setTypeC(
								acount.getAcount());
						tempMap.get(acount.getBonus()).setTypeCId(
								acount.getId());
						tempMap.get(acount.getBonus()).setAcountSapC(acount.getAcountSap());
						tempMap.get(acount.getBonus()).setTypeMoney(
								acount.getTypeMoney());
					}
			} else {
				if ("A".equals(acount.getAcountType()))
					if ("Y".equals(acount.getFlag())) {
						acount.setType2A(acount.getAcount());
						acount.setType2AId(acount.getId());
						acount.setAcountSapA(acount.getAcountSap());
					} else {
						acount.setTypeA(acount.getAcount());
						acount.setTypeAId(acount.getId());
						acount.setAcountSapA(acount.getAcountSap());
					}
				if ("B".equals(acount.getAcountType()))
					if ("Y".equals(acount.getFlag())) {
						acount.setType2B(acount.getAcount());
						acount.setType2BId(acount.getId());
						acount.setAcountSapB(acount.getAcountSap());
					} else {
						acount.setTypeB(acount.getAcount());
						acount.setTypeBId(acount.getId());
						acount.setAcountSapB(acount.getAcountSap());
					}
				if ("C".equals(acount.getAcountType()))
					if ("Y".equals(acount.getFlag())) {
						acount.setType2C(acount.getAcount());
						acount.setType2CId(acount.getId());
						acount.setAcountSapC(acount.getAcountSap());
					} else {
						acount.setTypeC(acount.getAcount());
						acount.setTypeCId(acount.getId());
						acount.setAcountSapC(acount.getAcountSap());
					}
				tempMap.put(acount.getBonus(), acount);
			}
		}
		Iterator it = tempMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			kunnrAcountList.add((KunnrAcount) entry.getValue());
		}
		return kunnrAcountList;
	}

	public void createSaleArea(final List<KunnrSalesArea> kunnrSalesAreaList,
			final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrSalesArea area : kunnrSalesAreaList) {
					area.setKunnr(kunnr);
					executor.insert("kunnr.createSaleArea", area);
				}
				executor.executeBatch();
				return true;
			}
		});
	}

	public void createKunnrGoal(final List<BCustomerTarget> bCustomerTargetList) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (BCustomerTarget target : bCustomerTargetList) {
					executor.insert("goal.insertGoal", target);
				}
				executor.executeBatch();
				return true;
			}
		});

	}

	public void updateAndCreateSalesArea(
			final List<KunnrSalesArea> salesAreaList, final String kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrSalesArea salesArea : salesAreaList) {
					salesArea.setKunnr(kunnr);
					if (salesArea.getId() != null) {
						executor.update("kunnr.updateKunnrSalesArea", salesArea);
					} else {
						executor.insert("kunnr.createSaleArea", salesArea);
					}
				}
				executor.executeBatch();
				return true;
			}
		});

	}

	public void removeSalesArea(final List<KunnrSalesArea> killSalesArea) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrSalesArea area : killSalesArea) {
					if (null != area) {
						Long salesAreaId = area.getId();
						executor.update("kunnr.removeSalesArea", salesAreaId);
					}
				}
				executor.executeBatch();
				return true;
			}
		});

	}

	@SuppressWarnings("unchecked")
	public List<KunnrLogisticsArea> getKunnrLogisticsArea(
			KunnrLogisticsArea area) {
		return (List<KunnrLogisticsArea>) getSqlMapClientTemplate()
				.queryForList("kunnr.getKunnrLogisticsArea", area);
	}

	public int getKunnrLogisticsAreaCount(KunnrLogisticsArea area) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"kunnr.getKunnrLogisticsAreaCount", area);
	}

	public void updateLogisticArea(final List<KunnrLogisticsArea> areaList) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (KunnrLogisticsArea area : areaList) {
					executor.update("kunnr.updateLogisticArea", area);

				}
				executor.executeBatch();
				return true;
			}
		});
	}

	public int getRoleOnEventByUser(String userId, String roleId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"kunnr.getRoleOnEventByUser", map);
	}

	@SuppressWarnings("unchecked")
	public List<KunnrLicense> getKunnrLicenseListByLicense(KunnrLicense license) {
		return (List<KunnrLicense>) getSqlMapClientTemplate().queryForList(
				"kunnr.getKunnrLicenseListByLicense", license);
	}

	public void updateKunnrTarget(final Kunnr kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
					executor.update("kunnr.updateKunnrTarget", kunnr);
				executor.executeBatch();
				return true;
			}
		});
	
	}

	public void kunnrApplySave(KunnrApplySave kunnrApply) {
		getSqlMapClientTemplate().insert("kunnr.kunnrApplySave",kunnrApply);
	}

	public int kunnrApplySaveSearchCount(KunnrApplySave kunnrApply) {
		return (Integer) getSqlMapClientTemplate().queryForObject("kunnr.kunnrApplySaveSearchCount",kunnrApply);
	}

	@SuppressWarnings("unchecked")
	public List<KunnrApplySave> kunnrApplySaveSearch(KunnrApplySave kunnrApply) {
		return (List<KunnrApplySave>)getSqlMapClientTemplate().queryForList("kunnr.kunnrApplySaveSearch",kunnrApply);
	}

	public void updateKunnrApplySave(KunnrApplySave kunnrApply) {
		getSqlMapClientTemplate().update("kunnr.updateKunnrApplySave",kunnrApply);
	}
	public Long createKunnrSapCode(KunnrSapCodeObject obj) {
		return (Long)getSqlMapClientTemplate().insert("kunnr.createKunnrSapCode",obj);
	}
	public void updateKunnrSapCodeStatus(KunnrSapCodeObject obj) {
		getSqlMapClientTemplate().update("kunnr.updateKunnrSapCodeStatus",obj);
	}
	public int updateKunnrStatus(Kunnr kunnr) {
		return (Integer)getSqlMapClientTemplate().update("kunnr.updateKunnrStatus",kunnr);
	}
	public Long createKunnrUser(Kunnr kunnr) {
		try {
			kunnr.setKunnrCode(EncryptUtil.md5Encry("111111"));//用户密码
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Long)getSqlMapClientTemplate().insert("kunnr.createKunnrUser",kunnr);
	}
	public void createKunnrUserRole(final KunnrRole role) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
					executor.insert("kunnr.createKunnrUserRole", role);
				executor.executeBatch();
				return true;
			}
		});
	
			
	}
	/**
	 * 经销商关户后禁用经销商用户及其雇员用户
	 * @param kunnr
	 */
	public void closeKunnrUser(final Kunnr kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
					executor.update("kunnr.closeKunnrUser", kunnr);
				executor.executeBatch();
				return true;
			}
		});
	}
	public void updateKunnrUserStaff(final Kunnr kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
					executor.update("kunnr.updateKunnrUserStaff",kunnr);
				executor.executeBatch();
				return true;
			}
		});
	}
	public void updateKunnrUser(final Kunnr kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
					executor.update("kunnr.updateKunnrUser", kunnr);
				executor.executeBatch();
				return true;
			}
		});
	}
	public void releaseKunnrTarget(final Kunnr kunnr) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
					executor.update("kunnr.releaseKunnrTarget", kunnr);
				executor.executeBatch();
				return true;
			}
		});
	}
	@Override
	public void modifyProcessVariable(String eventId) {
		getSqlMapClientTemplate().update("kunnr.modifyProcessVariable",eventId);
	}
	/**
	 * Title: 经销商当前月之后的月份的经销商目标量清空</p>
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月10日 下午4:50:21
	 * @param kunnrTar
	 */
	public void deleteKunnrTarget(Kunnr kunnrTar) {
		getSqlMapClientTemplate().delete("kunnr.deleteKunnrTarget",kunnrTar);
	}
	@Override
	public int getOfficeRole(String userId, String roleId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"kunnr.getOfficeRole", map);
	}
	@Override
	public String getCityOrgId(Borg borg) {
		try{
			return (String) this.getSqlMapClientTemplate().queryForObject("kunnr.getCityOrgId",borg);
		}catch(Exception e){
			return null;
		}
	}
}
