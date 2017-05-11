package com.kintiger.platform.busPhone.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.busPhone.dao.IBusPhoneDao;
import com.kintiger.platform.busPhone.pojo.BusPhone;
import com.kintiger.platform.busPhone.service.IBusPhoneService;

public class BusPhoneServiceImpl implements IBusPhoneService {
	
	IBusPhoneDao busPhoneDao;
	private static Log logger = LogFactory.getLog(BusPhoneServiceImpl.class);

	@Override
	public int getEmpListCount(BusPhone busPhone) {
		try{
			return busPhoneDao.getEmpListCount(busPhone);
		}catch (Exception e) {
			logger.error(busPhone, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<BusPhone> getEmpList(BusPhone busPhone) {
		try{
			return busPhoneDao.getEmpList(busPhone);
		}catch (Exception e) {
			logger.error(busPhone, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getKunnrEmpListCount(BusPhone busPhone) {
		try{
			return busPhoneDao.getKunnrEmpListCount(busPhone);
		}catch (Exception e) {
			logger.error(busPhone, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<BusPhone> getKunnrEmpList(BusPhone busPhone) {
		try{
			return busPhoneDao.getKunnrEmpList(busPhone);
		}catch (Exception e) {
			logger.error(busPhone, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateEmp(BusPhone busPhone) {
		try{
			return busPhoneDao.updateEmp(busPhone);
		}catch (Exception e) {
			logger.error(busPhone, e);
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateKunnrEmp(BusPhone busPhone) {
		try{
			return busPhoneDao.updateKunnrEmp(busPhone);
		}catch (Exception e) {
			logger.error(busPhone, e);
			e.printStackTrace();
			return 0;
		}
	}

    public int deleteEmp(BusPhone busPhone){
    	try{
			return busPhoneDao.deleteEmp(busPhone);
		}catch (Exception e) {
			logger.error(busPhone, e);
			e.printStackTrace();
			return 0;
		}
    }
	
	public int deleteKunnrEmp(BusPhone busPhone){
		try{
			return busPhoneDao.deleteKunnrEmp(busPhone);
		}catch (Exception e) {
			logger.error(busPhone, e);
			e.printStackTrace();
			return 0;
		}
	}

	public IBusPhoneDao getBusPhoneDao() {
		return busPhoneDao;
	}

	public void setBusPhoneDao(IBusPhoneDao busPhoneDao) {
		this.busPhoneDao = busPhoneDao;
	}

}
