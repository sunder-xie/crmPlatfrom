package com.kintiger.platform.channel.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.channel.dao.AllIChannelDao;
import com.kintiger.platform.channel.pojo.Bchannel;

public class AllChannelDaoImpl extends BaseDaoImpl implements AllIChannelDao{

	
	public List<Bchannel> getAllUnderChannel(Bchannel channel) {
	    return getSqlMapClientTemplate().queryForList("allchannel.getAllUnderChannel",channel);
	}


}
