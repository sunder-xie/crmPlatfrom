package com.kintiger.platform.supercheck.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.supercheck.dao.SupercheckDao;
import com.kintiger.platform.supercheck.pojo.AllitemConf;
import com.kintiger.platform.supercheck.pojo.WeightAndPrice;

@SuppressWarnings("rawtypes")
public class SupercheckDaoImpl extends BaseDaoImpl implements SupercheckDao{

    public List<Bchannel> getCheckChannelNames(Bchannel bchannel) {
	return  this.getSqlMapClientTemplate().queryForList("supercheck.getCheckChannelNames", bchannel);
    }

    public List<Materiel> getMateriels(Materiel m) {
	return  this.getSqlMapClientTemplate().queryForList("supercheck.getMateriels", m);
    }

    public List<WeightAndPrice> getWeightAndPrices(WeightAndPrice m) {
	return  (List<WeightAndPrice>)this.getSqlMapClientTemplate().queryForList("supercheck.getWeightAndPrices", m);
    }

    public void delWpByChannel(Bchannel bchannel) {
	getSqlMapClientTemplate().delete("supercheck.delWpByChannel",bchannel.getCheckChannelName());
    }

    public void saveWeightprices(WeightAndPrice weightAndPrice) {
	getSqlMapClientTemplate().insert("supercheck.saveWeightprices",weightAndPrice);
    }


    public void saveAllitemConf(AllitemConf allitemConf) {
	getSqlMapClientTemplate().insert("supercheck.saveAllitemConf",allitemConf);
    }

    public List<AllitemConf> getAllitemConfs(AllitemConf allitemConf) {
	return getSqlMapClientTemplate().queryForList("supercheck.getAllitemConfs",allitemConf);
    }

    public void delAllitemConf(String allitemName) {
	getSqlMapClientTemplate().delete("supercheck.delAllitemConf",allitemName);
    }

    public List<AllitemConf> getDistinctAllitemNames() {
	AllitemConf allitemConf = new AllitemConf();
	allitemConf.setEnd(100);
	return (List<AllitemConf>)getSqlMapClientTemplate().queryForList("supercheck.getDistinctAllitemNames",allitemConf);
    }

}
