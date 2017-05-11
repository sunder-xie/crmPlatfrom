package com.kintiger.platform.channel.service;

import java.util.List;

import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.channel.pojo.Bchannel;

public interface IChannelService {

	public List<Bchannel> getChannelTreeList(String node);

	public String getCompanyName(String companyMark);

	public int getChannelListCount(Bchannel bchannel);

	public List<Bchannel> getChannelList(Bchannel bchannel);

	public BooleanResult startup(Bchannel bchannel);

	public BooleanResult creatChannel(Bchannel bchannel);

	public Bchannel getChannelById(Bchannel channel);

	public BooleanResult updateChannelInfo(Bchannel bchannel);
	
	/**
	 * ²éÑ¯×îµÍ¼¶channel
	 * @return
	 */
	public List<Bchannel> getUnderChannel(Bchannel channel);

	public List<Bchannel> getAllChannel(Bchannel channel);

	public List<Bchannel> getAllUnderChannel(Bchannel channel);
	
}
