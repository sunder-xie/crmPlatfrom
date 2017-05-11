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
	 * ����������ģ��������Ա��Ϣ
	 * 
	 * @param orgId
	 * @return
	 */
	public List<AllUsers> getEmpListByUserName(AllUsers user);
	
	/**
	 * ��ְ��λ��֯��
	 * @param userId
	 * @return
	 */
	public List<String> getOrgIds(String userId);
	
	public List<Borg> getOrgsByOrgIds(String orgIds);

}
