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
	 * 获取行政区划信息
	 * 
	 * @param cityDict
	 * @return
	 */
	public List<CityDict> getCityList(CityDict cityDict);

	/**
	 * 获取字典信息
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict);

	/**
	 * 客户开户
	 * 
	 * @param customer
	 * @return
	 */
	public BooleanResult customerOpen(Customer customer);

	/**
	 * 根据渠道id获取渠道信息
	 * 
	 * @param channelId
	 * @return
	 */
	public String getChannelName(int channelId);

	/**
	 * 根据岗位id获取岗位名称
	 * 
	 * @param stationUserId
	 * @return
	 */
	public String getStationUserNameById(String stationUserId);

	/**
	 * 根据行政区划id获取行政区划名称
	 * 
	 * @param string
	 * @return
	 */
	public String getCustomerProvince(String string);

	/**
	 * 获取客户表seq
	 * 
	 * @return
	 */
	public Long getCustomerNumberId();

	/**
	 * 客户list count
	 * 
	 * @param customer
	 * @return
	 */
	public int getCustomerListCount(Customer customer);

	/**
	 * 获取客户list
	 * 
	 * @param customer
	 * @return
	 */
	public List<Customer> getCustomerList(Customer customer);

	/**
	 * 关户
	 * 
	 * @param cust
	 * @return
	 */
	public int closeCustomer(Customer cust);

	/**
	 * 根据客户id查询客户信息
	 * 
	 * @param customer
	 * @return
	 */
	public Customer getCustomerObjectByCustId(Customer customer);


	/**
	 * 根据客户行政区划最底层区划获取其区划信息
	 * @param zwl04
	 * @return
	 */
	public Zwlqy getCustomerXZArea(Zwlqy zwlqy); 
	/**
	 * 根据客户行政区划最底层区划获取其上级区划信息,并拼成‘省,市,县,镇’形式的字符
	 * 
	 * @param customer
	 * @return
	 */
//	public String getCustomerXZAreaStringByCust(String dict);

	/**
	 * 获取经销商信息
	 * 
	 * @param k
	 * @return
	 */
	public Kunnr getKunnrByKunnrId(Kunnr k);

	/**
	 * 修改客户资料
	 * 
	 * @param cust
	 * @return
	 */
	public BooleanResult updateCustomer(Customer customer);
	/**
	 * 根据名字查某城市的客户数
	 * @return
	 */
	public int getCustomerByName(Customer cust);
	/**
	 * 判断组织是否存在
	 * @return
	 */
	public Borg gerOrgIsExit(Borg org);
	
	/**
	 * 查二阶客户
	 * @param cust
	 * @return
	 */
	public List<Customer> getTwoLevelCustomer(Customer cust);
	/**
	 * 查二阶客户count
	 * @param cust
	 * @return
	 */
	public int getTwoLevelCustomerCount(Customer cust);
	/**
	 * 经销商登录 查经销商编号
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
	 * 根据经销商ID 查经销商负责人
	 * @param allUser
	 * @return
	 */
public List<AllUsers> getCustKunnrById(AllUsers allUser);

/**
 *      更新经销商门店对应雇员
 * @param customerList
 * @return
 */
public BooleanResult saveChagKunnrUser(List<Customer> customerList);

public int getCustomerByNumber(Customer cust);

public int updateImportCustomer(Customer cust);

/**
 * 批量修改客户业务负责人
 * @param cust
 * @return
 */
public Long getCustomerStationUserId(AllUsers user);

public int updateCustomerStationUserId(Customer cust);
/**
 * MethodsTitle: 根据客户编号更新门店重要度和门店年销售额
 * @author: xg.chen
 * @date:2016年11月4日 
 * @param customer
 */
public int updateCustomerImportance4CustNumber(Customer customer);
/**
 * MethodsTitle: 根据客户编号更新门店重要度
 * @author: xg.chen
 * @date:2016年11月7日 
 * @param customer
 */
public int updateCustomerImportance4CustNumber1(Customer customer);
}
