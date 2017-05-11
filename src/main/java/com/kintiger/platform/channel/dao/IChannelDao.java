package com.kintiger.platform.channel.dao;

import java.util.List;

import com.kintiger.platform.channel.pojo.Bchannel;

public interface IChannelDao {

	public List<Bchannel> getChannelTreeList(String node);

	public String getCompanyName(String companyMark);

	public int getChannelListCount(Bchannel bchannel);

	public List<Bchannel> getChannelList(Bchannel bchannel);

	public int startup(Bchannel bchannel);

	public Long creatChannel(Bchannel bchannel);

	public Bchannel getChannelById(Bchannel channel);

	public int updateChannelInfo(Bchannel bchannel);
	
	/**
	 * ²éÑ¯×îµÍ¼¶channel
	 * @return
	 */
	public List<Bchannel> getUnderChannel(Bchannel channel);

	public List<Bchannel> getAllChannel(Bchannel channel);

	public List<Bchannel> getAllUnderChannel(Bchannel channel);

}
