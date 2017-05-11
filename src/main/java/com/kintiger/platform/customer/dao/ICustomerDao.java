package com.kintiger.platform.customer.dao;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.citydict.pojo.CityDict;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.sales.pojo.Zwlqy;

public interface ICustomerDao {

	/**
	 * 鑾峰彇琛屾斂鍖哄垝淇℃伅
	 * 
	 * @param cityDict
	 * @return
	 */
	public List<CityDict> getCityList(CityDict cityDict);

	/**
	 * 鑾峰彇瀛楀吀淇℃伅
	 * 
	 * @param cmsTbDict
	 * @return
	 */
	public List<CmsTbDict> getCmsTbDictList(CmsTbDict cmsTbDict);

	/**
	 * 瀹㈡埛寮�埛
	 * 
	 * @param customer
	 * @return
	 */
	public Long customerOpen(Customer customer);

	/**
	 * 鏍规嵁娓犻亾id鑾峰彇娓犻亾淇℃伅
	 * 
	 * @param channelId
	 * @return
	 */
	public String getChannelName(int channelId);

	/**
	 * 鏍规嵁宀椾綅id鑾峰彇宀椾綅鍚嶇О
	 * 
	 * @param stationUserId
	 * @return
	 */
	public String getStationUserNameById(String stationUserId);

	/**
	 * 鏍规嵁琛屾斂鍖哄垝id鑾峰彇琛屾斂鍖哄垝鍚嶇О
	 * 
	 * @param string
	 * @return
	 */
	public String getCustomerProvince(String string);

	/**
	 * 鑾峰彇瀹㈡埛琛╯eq
	 * 
	 * @return
	 */
	public Long getCustomerNumberId();

	/**
	 * 鐢熸垚鎷滆璺嚎
	 * 
	 * @param n
	 * @param stationUserId
	 * @param visitFreq
	 * @param visitDays
	 * @return
	 */
	public String updateCustLine(Long n, String stationUserId,
			Integer visitFreq, String visitDays);

	/**
	 * 鑾峰彇瀹㈡埛list
	 * 
	 * @param customer
	 * @return
	 */
	public List<Customer> getCustomerList(Customer customer);

	/**
	 * 瀹㈡埛list count
	 * 
	 * @param customer
	 * @return
	 */
	public int getCustomerListCount(Customer customer);

	/**
	 * 鍏虫埛
	 * 
	 * @param cust
	 * @return
	 */
	public int closeCustomer(Customer cust);

	/**
	 * 鏍规嵁瀹㈡埛id鏌ヨ瀹㈡埛淇℃伅
	 * 
	 * @param customer
	 * @return
	 */
	public Customer getCustomerObjectByCustId(Customer customer);


	/**
	 * 鏍规嵁瀹㈡埛琛屾斂鍖哄垝鏈�簳灞傚尯鍒掕幏鍙栧叾鍖哄垝淇℃伅
	 * @param zwl04
	 * @return
	 */
	public Zwlqy getCustomerXZArea(Zwlqy zwlqy); 

	/**
	 * 鏍规嵁瀹㈡埛琛屾斂鍖哄垝鏈�簳灞傚尯鍒掕幏鍙栧叾涓婄骇鍖哄垝淇℃伅,骞舵嫾鎴愨�鐪�甯�鍘�闀団�褰㈠紡鐨勫瓧绗�
	 * 
	 * @param customer
	 * @return
	 */
	//public String getCustomerXZAreaStringByCust(String dict);

	/**
	 * 鑾峰彇缁忛攢鍟嗕俊鎭�
	 * 
	 * @param k
	 * @return
	 */
	public Kunnr getKunnrByKunnrId(Kunnr k);

	/**
	 * 淇敼瀹㈡埛璧勬枡
	 * 
	 * @param cust
	 * @return
	 */
	public int updateCustomer(Customer cust);
	
	/**
	 * 鏍规嵁鍚嶅瓧鏌ユ煇鍩庡競鐨勫鎴锋暟
	 * @return
	 */
	public int getCustomerByName(Customer cust);
	
	/**
	 * 鍒ゆ柇缁勭粐鏄惁瀛樺湪
	 * @return
	 */
	public Borg gerOrgIsExit(Borg org);
	/**
	 * 鏌ヤ簩闃跺鎴�
	 * @param cust
	 * @return
	 */
	public List<Customer> getTwoLevelCustomer(Customer cust);
	/**
	 * 鏌ヤ簩闃跺鎴穋ount
	 * @param cust
	 * @return
	 */
	public int getTwoLevelCustomerCount(Customer cust);
	/**
	 * 缁忛攢鍟嗙櫥褰�鏌ョ粡閿�晢缂栧彿
	 * @param cust
	 * @return
	 */
	public String getKunnrByEmpId(String userId);

	/**
	 * 缁忛攢鍟嗙紪鍙�鏌ョ粡閿�晢璐熻矗浜�
	 * @param allUser
	 * @return
	 */
	public int getCustKunnrByIdCount(AllUsers allUser);

	/**
	 * 鏍规嵁缁忛攢琛岀紪鍙�鏌ヨ缁忛攢鍟嗚礋璐ｄ汉鍒楄〃
	 * @param allUser
	 * @return
	 */
	public List<AllUsers> getCustKunnrById(AllUsers allUser);

	/**
	 *  鏇存柊缁忛攢鍟嗛棬搴�缁忛攢鍟嗚礋璐ｄ汉
	 * @param customerList
	 * @return
	 */
	public int saveChagKunnrUser(Customer customer);

	public int getCustomerByNumber(Customer cust);

	public int updateImportCustomer(Customer cust);
	
	/**
	 * 鎵归噺淇敼瀹㈡埛涓氬姟璐熻矗浜�
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
	 * @return
	 */
	public int updateCustomerImportance4CustNumber1(Customer customer);
}
