package com.kintiger.platform.customer.service;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.org.pojo.Borg;

public interface IBatChangeService {
	public List<AllUsers> getEmpListByOrgId(String orgId);
	public List<Customer> getCustomert(Customer customer);
	public int getCustomerBy(String custNumber,String  custName);
	public int getCustomerBySapId(String custNumber,String  custName);
	public int updateByCustprentId(Customer customer);
	public int updateChannelIdById(Customer customer);
	public List<AllUsers> getStationListByOrgId(AllUsers user);
	public String getStationUserId(String userId);
	public int updateStationUserIdById(Customer customer);
	/**
	 * 根据组名字模糊查找人员信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<AllUsers> getEmpListByUserName(AllUsers user);
	
	/*
	 * 兼职岗位组织 
	 */
	public List<String> getOrgIds(String userId);

	public List<Borg> getOrgsByOrgIds(List<String> allOrg) ;

}
