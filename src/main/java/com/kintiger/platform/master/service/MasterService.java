package com.kintiger.platform.master.service;

import java.util.Collection;
import java.util.List;

import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.master.pojo.CrmTbSku;
import com.kintiger.platform.master.pojo.Materiel;
import com.kintiger.platform.master.pojo.SupervisorCheckItem;
import com.kintiger.platform.master.pojo.SupervisorLineCheckItem;

public interface MasterService {
	public static final String SUCCESS = "success";

	public static final String ERROR = "error";

	public StringResult synchMateriel();
	

	public int getMaterielCount(Materiel materiel);
	
	public List<Materiel> getMaterielList(Materiel materiel);
	
	public StringResult synchToSku(CrmTbSku sku);


	public Customer validateChanelAndCust(String string, String string2);

	public StringResult saveSupervisorCheckItem(
			SupervisorCheckItem supervisorCheckItem);

	public int getSupervisorItemsCount(
			SupervisorLineCheckItem supervisorLineCheckItem);

	public List<SupervisorLineCheckItem> getSupervisorItems(
			SupervisorLineCheckItem supervisorLineCheckItem);

	public void clearItems();

	public void saveChagCheckItem(
			List<SupervisorLineCheckItem> supervisorLineCheckItemList);

	public int kunnrSearchCount(Kunnr kunnr);

	public List<Kunnr> kunnrSearchFromMaster(Kunnr kunnr);

	public Customer validateCustId(String trim);

	public List<Customer> getExportMouldCsvCust(Customer c);


	public List<Customer> getExportMouldCsvCustWithCons(
			SupervisorLineCheckItem supervisorLineCheckItem);


	public List<Materiel> getMasterCols(Materiel materiel);


	public int getSupervisorItemsColsCount(
			SupervisorLineCheckItem supervisorLineCheckItem);


	public List<Customer> getCustomerListCols(SupervisorLineCheckItem supervisorLineCheckItem);


	public int validateCustIdExist(String trim);

	public void delSupervisorCheckItemByCustId(String trim);


	public List<SupervisorCheckItem> getSupervisorItemsByCustId(String custId);


	public void clearAndSaveItems(
			List<SupervisorCheckItem> supervisorCheckItemList);



}
