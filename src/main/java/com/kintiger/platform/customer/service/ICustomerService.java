package com.kintiger.platform.customer.service;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.citydict.pojo.CityDict;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.sales.pojo.Zwlqy;

public interface ICustomerService {

	/**
	 * ��ȡ����������Ϣ
	 * 
	 * @param cityDict
	 * @return
	 */
	public List<CityDict> getCityList(CityDict cityDict);

	/**
	 * ��ȡ�ֵ���Ϣ
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict);

	/**
	 * �ͻ�����
	 * 
	 * @param customer
	 * @return
	 */
	public BooleanResult customerOpen(Customer customer);

	/**
	 * ��������id��ȡ������Ϣ
	 * 
	 * @param channelId
	 * @return
	 */
	public String getChannelName(int channelId);

	/**
	 * ���ݸ�λid��ȡ��λ����
	 * 
	 * @param stationUserId
	 * @return
	 */
	public String getStationUserNameById(String stationUserId);

	/**
	 * ������������id��ȡ������������
	 * 
	 * @param string
	 * @return
	 */
	public String getCustomerProvince(String string);

	/**
	 * ��ȡ�ͻ���seq
	 * 
	 * @return
	 */
	public Long getCustomerNumberId();

	/**
	 * �ͻ�list count
	 * 
	 * @param customer
	 * @return
	 */
	public int getCustomerListCount(Customer customer);

	/**
	 * ��ȡ�ͻ�list
	 * 
	 * @param customer
	 * @return
	 */
	public List<Customer> getCustomerList(Customer customer);

	/**
	 * �ػ�
	 * 
	 * @param cust
	 * @return
	 */
	public int closeCustomer(Customer cust);

	/**
	 * ���ݿͻ�id��ѯ�ͻ���Ϣ
	 * 
	 * @param customer
	 * @return
	 */
	public Customer getCustomerObjectByCustId(Customer customer);


	/**
	 * ���ݿͻ�����������ײ�������ȡ��������Ϣ
	 * @param zwl04
	 * @return
	 */
	public Zwlqy getCustomerXZArea(Zwlqy zwlqy); 
	/**
	 * ���ݿͻ�����������ײ�������ȡ���ϼ�������Ϣ,��ƴ�ɡ�ʡ,��,��,����ʽ���ַ�
	 * 
	 * @param customer
	 * @return
	 */
//	public String getCustomerXZAreaStringByCust(String dict);

	/**
	 * ��ȡ��������Ϣ
	 * 
	 * @param k
	 * @return
	 */
	public Kunnr getKunnrByKunnrId(Kunnr k);

	/**
	 * �޸Ŀͻ�����
	 * 
	 * @param cust
	 * @return
	 */
	public BooleanResult updateCustomer(Customer customer);
	/**
	 * �������ֲ�ĳ���еĿͻ���
	 * @return
	 */
	public int getCustomerByName(Customer cust);
	/**
	 * �ж���֯�Ƿ����
	 * @return
	 */
	public Borg gerOrgIsExit(Borg org);
	
	/**
	 * ����׿ͻ�
	 * @param cust
	 * @return
	 */
	public List<Customer> getTwoLevelCustomer(Customer cust);
	/**
	 * ����׿ͻ�count
	 * @param cust
	 * @return
	 */
	public int getTwoLevelCustomerCount(Customer cust);
	/**
	 * �����̵�¼ �龭���̱��
	 * @param cust
	 * @return
	 */
	public String getKunnrByEmpId(String userId);
/**
 *    
 * @param allUser
 * @return
 */
	public int getCustKunnrByIdCount(AllUsers allUser);

	/**
	 * ���ݾ�����ID �龭���̸�����
	 * @param allUser
	 * @return
	 */
public List<AllUsers> getCustKunnrById(AllUsers allUser);

/**
 *      ���¾������ŵ��Ӧ��Ա
 * @param customerList
 * @return
 */
public BooleanResult saveChagKunnrUser(List<Customer> customerList);

public int getCustomerByNumber(Customer cust);

public int updateImportCustomer(Customer cust);

/**
 * �����޸Ŀͻ�ҵ������
 * @param cust
 * @return
 */
public Long getCustomerStationUserId(AllUsers user);

public int updateCustomerStationUserId(Customer cust);
/**
 * MethodsTitle: ���ݿͻ���Ÿ����ŵ���Ҫ�Ⱥ��ŵ������۶�
 * @author: xg.chen
 * @date:2016��11��4�� 
 * @param customer
 */
public int updateCustomerImportance4CustNumber(Customer customer);
/**
 * MethodsTitle: ���ݿͻ���Ÿ����ŵ���Ҫ��
 * @author: xg.chen
 * @date:2016��11��7�� 
 * @param customer
 */
public int updateCustomerImportance4CustNumber1(Customer customer);
}
