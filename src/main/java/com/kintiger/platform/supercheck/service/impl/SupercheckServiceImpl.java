package com.kintiger.platform.supercheck.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.master.dao.MasterDao;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.supercheck.dao.SupercheckDao;
import com.kintiger.platform.supercheck.pojo.AllitemConf;
import com.kintiger.platform.supercheck.pojo.WeightAndPrice;
import com.kintiger.platform.supercheck.service.SupercheckService;

public class SupercheckServiceImpl implements SupercheckService {
    // private SAPConnectionBean sapConnection;
    private SupercheckDao supercheckDao;
    private static final Logger logger = Logger.getLogger(SupercheckServiceImpl.class);
    private TransactionTemplate transactionTemplate;

    public List<Bchannel> getCheckChannelNames() {
	Bchannel b = new Bchannel();
	b.setStart(0);
	b.setEnd(100);
	return supercheckDao.getCheckChannelNames(b);
    }

    public SupercheckDao getSupercheckDao() {
	return supercheckDao;
    }

    public void setSupercheckDao(SupercheckDao supercheckDao) {
	this.supercheckDao = supercheckDao;
    }

    public TransactionTemplate getTransactionTemplate() {
	return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
	this.transactionTemplate = transactionTemplate;
    }

    public List<Materiel> getMateriels() {
	Materiel b = new Materiel();
	b.setStart(0);
	b.setEnd(100);
	return supercheckDao.getMateriels(b);
    }

    public List<WeightAndPrice> getWeightAndPrices(WeightAndPrice b) {
	if (b == null) {
	    b = new WeightAndPrice();
	    b.setStart(0);
	    b.setEnd(100);
	}
	return supercheckDao.getWeightAndPrices(b);
    }

    public void delWpByChannel(List<Bchannel> channelList) {
	for (Bchannel bchannel : channelList) {
	    String checkChannelName = bchannel.getCheckChannelName();
	    try {
		checkChannelName = java.net.URLDecoder.decode(checkChannelName, "UTF-8");
	    } catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	    }
	    bchannel.setCheckChannelName(checkChannelName);
	    supercheckDao.delWpByChannel(bchannel);
	}

    }

    public void saveWeightprices(List<WeightAndPrice> weightAndPriceList) {
	for (WeightAndPrice weightAndPrice : weightAndPriceList) {
	    String checkChannelName = weightAndPrice.getCheckChannelName();
	    try {
		checkChannelName = java.net.URLDecoder.decode(checkChannelName, "UTF-8");
	    } catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	    }
	    weightAndPrice.setCheckChannelName(checkChannelName);
	    supercheckDao.saveWeightprices(weightAndPrice);
	}

    }

    public void saveAllitemConf(String allitemName) {

	List<Materiel> list = new ArrayList<Materiel>();
	list = this.getMateriels();
	for (Materiel m : list) {
	    AllitemConf allitemConf = new AllitemConf();
	    allitemConf.setAllitemName(allitemName);
	    allitemConf.setAllitems(m.getMatg());
	    supercheckDao.saveAllitemConf(allitemConf);
	}

    }

    public List<AllitemConf> getAllitemConfs(AllitemConf allitemConf) {
	allitemConf.setEnd(1000);
	List<AllitemConf> allitems = supercheckDao.getAllitemConfs(allitemConf);
	return allitems;
    }

    public void delAllitemConf(String allitemName) {
	supercheckDao.delAllitemConf(allitemName);

    }

    public void changeAllitemConf(final List<AllitemConf> allItemList) {

	BooleanResult bool = (BooleanResult) transactionTemplate.execute(new TransactionCallback() {
	    public BooleanResult doInTransaction(TransactionStatus ts) {
		BooleanResult bresult = new BooleanResult();
		bresult.setResult(true);

		try {
		    AllitemConf a = allItemList.get(0);
		    supercheckDao.delAllitemConf(java.net.URLDecoder.decode(a.getAllitemName(), "UTF-8"));
                         
		    for (AllitemConf allitemConf : allItemList) {
			allitemConf.setAllitemName(java.net.URLDecoder.decode(a.getAllitemName(), "UTF-8"));
			supercheckDao.saveAllitemConf(allitemConf);
		    }
		} catch (Exception e) {
		      logger.error(e);
			ts.isRollbackOnly();
			bresult.setCode("市场渠道部综合品项配置表 修改失败.");
			bresult.setResult(false);
		}

		return bresult;

	    }
	});
	System.out.println("---------");

    }

    public List<AllitemConf> getDistinctAllitemNames() {
	return supercheckDao.getDistinctAllitemNames();
    }

}
