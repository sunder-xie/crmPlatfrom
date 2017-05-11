package com.kintiger.platform.channel.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.channel.dao.IChannelDao;
import com.kintiger.platform.channel.pojo.Bchannel;

public class ChannelDaoImpl extends BaseDaoImpl implements IChannelDao{

	@SuppressWarnings("unchecked")
	public List<Bchannel> getChannelTreeList(String node) {
		Bchannel b = new Bchannel();
		b.setChannelParentId(node);
		return  getSqlMapClientTemplate().queryForList("channel.getChannelTreeList", b);
	}

	public String getCompanyName(String companyMark) {
		return (String) getSqlMapClientTemplate().queryForObject("channel.getCompanyName", companyMark);
	}

	public int getChannelListCount(Bchannel bchannel) {
		return  (Integer) getSqlMapClientTemplate().queryForObject("channel.getChannelListCount", bchannel);
	}

	@SuppressWarnings("unchecked")
	public List<Bchannel> getChannelList(Bchannel bchannel) {
		return getSqlMapClientTemplate().queryForList("channel.getChannelList",bchannel);
	}

	public int startup(Bchannel bchannel) {
		return getSqlMapClientTemplate().update("channel.startup",bchannel);
	}

	public Long creatChannel(Bchannel bchannel) {
		return (Long) getSqlMapClientTemplate().insert("channel.creatChannel",bchannel);
	}

	public Bchannel getChannelById(Bchannel channel) {
		return (Bchannel) getSqlMapClientTemplate().queryForObject("channel.getChannelById",channel);
	}

	public int updateChannelInfo(Bchannel bchannel) {
		return getSqlMapClientTemplate().update("channel.updateChannelInfo",bchannel);
	}

	@SuppressWarnings("unchecked")
	public List<Bchannel> getUnderChannel(Bchannel channel) {
		return getSqlMapClientTemplate().queryForList("channel.getUnderChannel",channel);
		}

	public List<Bchannel> getAllChannel(Bchannel channel) {
	    return getSqlMapClientTemplate().queryForList("channel.getAllChannel",channel);
	}

	
	public List<Bchannel> getAllUnderChannel(Bchannel channel) {
	    return getSqlMapClientTemplate().queryForList("channel.getAllUnderChannel",channel);
	}


}
