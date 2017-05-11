package com.kintiger.platform.channel.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.channel.dao.IChannelDao;
import com.kintiger.platform.channel.pojo.Bchannel;
import com.kintiger.platform.channel.service.IChannelService;

public class ChannelServiceImpl implements IChannelService{

	private IChannelDao channelDao;
	private static final Logger logger = Logger
	.getLogger(ChannelServiceImpl.class);

	public List<Bchannel> getChannelTreeList(String node) {
		try {
			return channelDao.getChannelTreeList(node);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public String getCompanyName(String companyMark) {
		try {
			return channelDao.getCompanyName(companyMark);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	

	public int getChannelListCount(Bchannel bchannel) {
		try {
			return channelDao.getChannelListCount(bchannel);
		} catch (Exception e) {
			logger.error(e);
			return 0;
		}
	}


	public BooleanResult startup(Bchannel bchannel) {
		try {
			BooleanResult booleanResult = new BooleanResult();
			booleanResult.setResult(false);
			int n = channelDao.startup(bchannel);
			if(n==1){
				booleanResult.setResult(true);
			}
			return booleanResult;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public BooleanResult creatChannel(Bchannel bchannel) {
		try {
			BooleanResult booleanResult = new BooleanResult();
			booleanResult.setResult(false);
			Long n = channelDao.creatChannel(bchannel);
			if(n != null){
				booleanResult.setResult(true);
			}
			return booleanResult;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public List<Bchannel> getChannelList(Bchannel bchannel) {
		try {
			return channelDao.getChannelList(bchannel);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	

	public Bchannel getChannelById(Bchannel channel) {
		try {
			return channelDao.getChannelById(channel);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public BooleanResult updateChannelInfo(Bchannel bchannel) {
		try {
			BooleanResult booleanResult = new BooleanResult();
			booleanResult.setResult(false);
			int n = channelDao.updateChannelInfo(bchannel);
			if(n == 1){
				booleanResult.setResult(true);
			}
			return booleanResult;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	
	public List<Bchannel> getUnderChannel(Bchannel channel) {
		try {
			return channelDao.getUnderChannel(channel);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public IChannelDao getChannelDao() {
		return channelDao;
	}

	public void setChannelDao(IChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	public List<Bchannel> getAllChannel(Bchannel channel) {
	    try {
		return channelDao.getAllChannel(channel);
	} catch (Exception e) {
		logger.error(e);
		return null;
	}
	}

	public List<Bchannel> getAllUnderChannel(Bchannel channel) {
	    try {
        		return channelDao.getAllUnderChannel(channel);
        	} catch (Exception e) {
        		logger.error(e);
        		return null;
        	}
	}


}
