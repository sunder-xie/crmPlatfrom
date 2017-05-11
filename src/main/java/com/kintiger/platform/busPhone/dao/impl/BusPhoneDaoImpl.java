package com.kintiger.platform.busPhone.dao.impl;

import java.util.List;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.busPhone.dao.IBusPhoneDao;
import com.kintiger.platform.busPhone.pojo.BusPhone;

@SuppressWarnings("rawtypes")
public class BusPhoneDaoImpl extends BaseDaoImpl implements IBusPhoneDao{

	@Override
	public int getEmpListCount(BusPhone busPhone) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("busPhone.getEmpListCount", busPhone);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusPhone> getEmpList(BusPhone busPhone) {
		return this.getSqlMapClientTemplate().queryForList("busPhone.getEmpList", busPhone);
	}

	@Override
	public int getKunnrEmpListCount(BusPhone busPhone) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("busPhone.getKunnrEmpListCount", busPhone);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusPhone> getKunnrEmpList(BusPhone busPhone) {
		return this.getSqlMapClientTemplate().queryForList("busPhone.getKunnrEmpList", busPhone);
	}

	@Override
	public int updateEmp(BusPhone busPhone) {
		return this.getSqlMapClientTemplate().update("busPhone.updateEmp", busPhone);
	}

	@Override
	public int updateKunnrEmp(BusPhone busPhone) {
		return this.getSqlMapClientTemplate().update("busPhone.updateKunnrEmp", busPhone);
	}
	
    public int deleteEmp(BusPhone busPhone){
    	return this.getSqlMapClientTemplate().update("busPhone.deleteEmp", busPhone);
    }
	
	public int deleteKunnrEmp(BusPhone busPhone){
		return this.getSqlMapClientTemplate().update("busPhone.deleteKunnrEmp", busPhone);
	}

}
