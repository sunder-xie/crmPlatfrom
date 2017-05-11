package com.kintiger.platform.customer.dao;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.org.pojo.Borg;

public interface IBatChangeDao {
	public List<AllUsers> getEmpListByOrgId(String orgId);
	public List<Customer> getCustomert(Customer customer);
	public int updateStationUserIdById(Customer customer);
	/**
	 * 根据组名字模糊查找人员信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<AllUsers> getEmpListByUserName(AllUsers user);
	
	/**
	 * 兼职岗位组织树
	 * @param userId
	 * @return
	 */
	public List<String> getOrgIds(String userId);
	
	public List<Borg> getOrgsByOrgIds(String orgIds);

}
