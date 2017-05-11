package com.kintiger.platform.supercheck.dao;

import java.util.List;

import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.supercheck.pojo.AllitemConf;
import com.kintiger.platform.supercheck.pojo.WeightAndPrice;

public interface SupercheckDao {

    // 1)��ȡ����������

    List<Bchannel> getCheckChannelNames(Bchannel bchannel);

    // 2)��ȡ��ζ�б�

    List<Materiel> getMateriels(Materiel b);

    List<WeightAndPrice> getWeightAndPrices(WeightAndPrice b);

    void delWpByChannel(Bchannel bchannel);

    void saveWeightprices(WeightAndPrice weightAndPrice);

    void saveAllitemConf(AllitemConf allitemConf);

    List<AllitemConf> getAllitemConfs(AllitemConf allitemConf);

    void delAllitemConf(String allitemName);

    List<AllitemConf> getDistinctAllitemNames();

}