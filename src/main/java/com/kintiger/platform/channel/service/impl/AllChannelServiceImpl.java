package com.kintiger.platform.channel.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.channel.dao.AllIChannelDao;
import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.channel.service.AllIChannelService;

public class AllChannelServiceImpl implements AllIChannelService{

	private AllIChannelDao allchannelDao;
	private static final Logger logger = Logger
	.getLogger(AllChannelServiceImpl.class);

	

	public List<Bchannel> getAllUnderChannel(Bchannel channel) {
	    try {
        		return allchannelDao.getAllUnderChannel(channel);
        	} catch (Exception e) {
        		logger.error(e);
        		return null;
        	}
	}



	public AllIChannelDao getAllchannelDao() {
	    return allchannelDao;
	}



	public void setAllchannelDao(AllIChannelDao allchannelDao) {
	    this.allchannelDao = allchannelDao;
	}



	


}
