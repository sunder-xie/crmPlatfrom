package com.kintiger.platform.supercheck.service;

import java.util.ArrayList;
import java.util.List;

import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.supercheck.pojo.AllitemConf;
import com.kintiger.platform.supercheck.pojo.WeightAndPrice;

public interface SupercheckService {
    
    public List<Bchannel>getCheckChannelNames();

    public List<Materiel> getMateriels();

    public List<WeightAndPrice> getWeightAndPrices(WeightAndPrice w);

    public void delWpByChannel(List<Bchannel> channelList);

    public void saveWeightprices(List<WeightAndPrice> weightAndPriceList);

    public void saveAllitemConf(String allitemName);

    public List<AllitemConf> getAllitemConfs(AllitemConf allitemConf);

    public void delAllitemConf(String allitemName);

    // 先删除单行， 再插入
    public void changeAllitemConf(List<AllitemConf> allItemList);

    public List<AllitemConf> getDistinctAllitemNames();


}