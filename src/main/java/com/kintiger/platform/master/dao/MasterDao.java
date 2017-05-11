package com.kintiger.platform.master.dao;

import java.util.List;
















import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.CrmTbCg;
import com.kintiger.platform.master.pojo.CrmTbSku;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.master.pojo.SupervisorCheckItem;
import com.kintiger.platform.master.pojo.SupervisorLineCheckItem;

public interface MasterDao {
	
	/**
	 * ɾ�����ϱ�
	 * @return
	 */
	public void deleteMateriel();
	/**
	 * ��������
	 * @param materiel
	 */
	public void createMateriel(Materiel materiel);
	/**
	 * ��ȡ�����б�����
	 * @param materiel
	 * @return �б�����
	 */
	public int getMaterielCount(Materiel materiel);
	/**
	 * ��ȡ�����б�
	 * @param materiel
	 * @return
	 */
	public List<Materiel> getMaterielList(Materiel materiel);
	/**
	 * ���Ʒ����Ƶõ�Ʒ��ID
	 * getSkuCategoryIdByName
	 * @param  @param name
	 * @param  @return    �趨�ļ�
	 * @return long    DOM����
	 * @throws 
	 * @since  CodingExample��Ver 1.1
	 */
	public List<CrmTbCg> getSkuCategoryIdByName(CrmTbCg cg );
	/**
	 * ����Ʒ��
	 * createCrmTbCg
	 * @param  @param name
	 * @param  @return    �趨�ļ�
	 * @return long    DOM����
	 * @throws 
	 * @since  CodingExample��Ver 1.1
	 */
	public Long createCrmTbCg(CrmTbCg cg );
	/**
	 * �޸�Ʒ��
	 * updateCrmTbcg
	 * @param  @param name
	 * @param  @return    �趨�ļ�
	 * @return long    DOM����
	 * @throws 
	 * @since  CodingExample��Ver 1.1
	 */
	public void updateCrmTbcg(CrmTbCg cg );
	/**
	 * ��ݹ�˾��sku��Ƶõ�sku��Ϣ
	 * getSkuIdByNameAndCompany
	 * @param  @param name
	 * @param  @return    �趨�ļ�
	 * @return long    DOM����
	 * @throws 
	 * @since  CodingExample��Ver 1.1
	 */
	public List<CrmTbSku> getSkuIdByNameAndCompany(CrmTbSku sku);
	/**
	 * ����SKU
	 * createCrmTbSku
	 * @param  @param name
	 * @param  @return    �趨�ļ�
	 * @return long    DOM����
	 * @throws 
	 * @since  CodingExample��Ver 1.1
	 */
	public Long createCrmTbSku(CrmTbSku sku);
	/**
	 * �޸�SKU
	 * updateCrmTbSku
	 * @param  @param name
	 * @param  @return    �趨�ļ�
	 * @return long    DOM����
	 * @throws 
	 * @since  CodingExample��Ver 1.1
	 */
	public void updateCrmTbSku(CrmTbSku sku);
	public Customer validateChanelAndCust(String custName,
			String channelName);
	
	public Long saveSupervisorCheckItem(SupervisorCheckItem supervisorCheckItem);
	public int getSupervisorItemsCount(
			SupervisorLineCheckItem supervisorLineCheckItem);
	
	public List<SupervisorCheckItem> getSupervisorItems(
			SupervisorLineCheckItem supervisorLineCheckItem);
	
	public void clearItems();
	public void saveChagCheckItem(SupervisorCheckItem supervisorCheckItem);
	public int kunnrSearchCount(Kunnr kunnr);
	public List<Kunnr> kunnrSearch(Kunnr kunnr);
	public Customer validateCustId(String custId);
	public List<Customer> getExportMouldCsvCust(Customer c);
	public List<Customer> getExportMouldCsvCustWithCons(
			SupervisorLineCheckItem supervisorLineCheckItem);
	public List<Materiel> getMasterCols(Materiel materiel);
	public int getSupervisorItemsColsCount(
			SupervisorLineCheckItem supervisorLineCheckItem);
	public List<Customer> getCustomerListCols(SupervisorLineCheckItem supervisorLineCheckItem);
	public int validateCustIdExist(String custId);
	public void delSupervisorCheckItemByCustId(String custId);
	public List<SupervisorCheckItem> getSupervisorItemsByCustId(String custId);
	


}
