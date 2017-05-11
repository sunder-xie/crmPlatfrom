package com.kintiger.platform.kunnrBusinessContact.dao.impl;

import java.util.List;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.kunnrBusinessContact.dao.IKunnrBusinessDao;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdjustment;
import com.kintiger.platform.kunnrBusinessContact.pojo.DealerAdujstDetail;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.pojo.KunnrBusiness;

public class KunnrBusinessDaoImpl extends BaseDaoImpl implements
		IKunnrBusinessDao {

	@Override
	public KunnrBusiness getKunnrBusiness(Kunnr kunnr) {
		return (KunnrBusiness) getSqlMapClientTemplate().queryForObject(
				"kunnrBusiness.getKunnrBusiness", kunnr);
	}

	@Override
	public int updateKunnrBusiness(KunnrBusiness kunnrBusiness) {
		return getSqlMapClientTemplate().update(
				"kunnrBusiness.updateKunnrBusiness", kunnrBusiness);
	}
	@SuppressWarnings("unchecked")
	public List<KunnrBusiness> getKunnrBusinessManagerList(KunnrBusiness kunnrBusiness){
		return getSqlMapClientTemplate().queryForList(
				"kunnrBusiness.getKunnrBusinessManagerList", kunnrBusiness);
	}
	@SuppressWarnings("unchecked")
	public List<KunnrBusiness> getKunnrBusinessHeadList(KunnrBusiness kunnrBusiness){
		return getSqlMapClientTemplate().queryForList(
				"kunnrBusiness.getKunnrBusinessHeadList", kunnrBusiness);
	}
	@SuppressWarnings("unchecked")
	public List<KunnrBusiness> getKunnrBusinessAgentList(KunnrBusiness kunnrBusiness){
		return getSqlMapClientTemplate().queryForList(
				"kunnrBusiness.getKunnrBusinessAgentList", kunnrBusiness);
	}
	public int updateHead(KunnrBusiness kunnrBusiness){
		return getSqlMapClientTemplate().update(
				"kunnrBusiness.updateHead", kunnrBusiness);
	}
	public int updateAgent(KunnrBusiness kunnrBusiness){
		return getSqlMapClientTemplate().update(
				"kunnrBusiness.updateAgent", kunnrBusiness);
	}
	public int updateBusinessManager(KunnrBusiness kunnrBusiness){
		return getSqlMapClientTemplate().update(
				"kunnrBusiness.updateBusinessManager", kunnrBusiness);
	}
	public int createHead(KunnrBusiness kunnrBusiness){
		getSqlMapClientTemplate().insert(
				"kunnrBusiness.createHead", kunnrBusiness);
		return 0;
	}
	public int createBusinessManager(KunnrBusiness kunnrBusiness){
		getSqlMapClientTemplate().insert(
				"kunnrBusiness.createBusinessManager", kunnrBusiness);
		return 0;
	}
	public int createAgent(KunnrBusiness kunnrBusiness){
		getSqlMapClientTemplate().insert(
				"kunnrBusiness.createAgent", kunnrBusiness);
		return 0;
	}
	@SuppressWarnings("unchecked")
	public List<String> getKunnrIdByHeadOrAgent(String userId){
		return (List<String>)getSqlMapClientTemplate().queryForList(
				"kunnrBusiness.getKunnrIdByHeadOrAgent", userId);
	}
	@SuppressWarnings("unchecked")
	public List<String> getKunnrIdByCompetent(String userId){
		return (List<String>)getSqlMapClientTemplate().queryForList(
				"kunnrBusiness.getKunnrIdByCompetent", userId);
	}
	@SuppressWarnings("unchecked")
	public List<String> getKunnrIdByKunnrBusiness(String userId){
		return (List<String>)getSqlMapClientTemplate().queryForList(
				"kunnrBusiness.getKunnrIdByKunnrBusiness", userId);
	}
	@SuppressWarnings("unchecked")
	public List<String> getKunnrIdByKunnrBusinessByKunag(String userId){
		return (List<String>)getSqlMapClientTemplate().queryForList(
				"kunnrBusiness.getKunnrIdByKunnrBusinessByKunag", userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<KunnrBusiness> exportForExcel(Kunnr kunnr){
		return (List<KunnrBusiness>)getSqlMapClientTemplate().queryForList(
				"kunnrBusiness.exportForExcel", kunnr);
	}
	
    public int searchKunnrBusinessManagerListCount(KunnrBusiness KunnrBusiness){
    	return (Integer)getSqlMapClientTemplate().queryForObject(
				"kunnrBusiness.searchKunnrBusinessManagerListCount", KunnrBusiness);
    }
	
	@SuppressWarnings("unchecked")
	public List<KunnrBusiness> searchKunnrBusinessManagerList(KunnrBusiness KunnrBusiness){
		return (List<KunnrBusiness>)getSqlMapClientTemplate().queryForList(
				"kunnrBusiness.searchKunnrBusinessManagerList", KunnrBusiness);
	}
	
    public int searchKunnrBusinessEmpListCount(KunnrBusiness KunnrBusiness){
    	return (Integer)getSqlMapClientTemplate().queryForObject(
				"kunnrBusiness.searchKunnrBusinessEmpListCount", KunnrBusiness);
    }
	
	@SuppressWarnings("unchecked")
	public List<KunnrBusiness> searchKunnrBusinessEmpList(KunnrBusiness KunnrBusiness){
		return (List<KunnrBusiness>)getSqlMapClientTemplate().queryForList(
				"kunnrBusiness.searchKunnrBusinessEmpList", KunnrBusiness);
	}
	
	public int updateKunnrBusinessEmp(KunnrBusiness KunnrBusiness){
		return (Integer)getSqlMapClientTemplate().update(
				"kunnrBusiness.updateKunnrBusinessEmp", KunnrBusiness);
	}
	/**
	 * Title: 统计数据
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月17日 下午12:30:46
	 * @param dealerAdjustment
	 * @return
	 */
	@Override
	public int getDealerAdjustmentCount(DealerAdjustment dealerAdjustment) {
		return (Integer) getSqlMapClientTemplate().queryForObject("kunnrBusiness.getDealerAdjustmentCount",dealerAdjustment);
	}
	/**
	 * Title: 获取提报调整事务列表
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月17日 下午12:30:52
	 * @param dealerAdjustment
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DealerAdjustment> getDealerAdjustmentList(DealerAdjustment dealerAdjustment) {
		return getSqlMapClientTemplate().queryForList("kunnrBusiness.getDealerAdjustmentList",dealerAdjustment);
	}
	/**
	 * Title: 验证经销商名称+代码+组织是否保持一致
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月19日 下午4:54:43
	 * @param detail
	 * @return
	 */
	@Override
	public int getDealerAdjustmentDetailCount(DealerAdujstDetail detail) {
		return (Integer)getSqlMapClientTemplate().queryForObject("kunnrBusiness.getDealerAdjustmentDetailCount", detail);
	}
	/**
	 * Title: 根据导入的经销商、品项、年月份带出相应的“经销商目标量”
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月20日 上午9:48:35
	 * @param detail
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DealerAdujstDetail> getDealerAdjustmentDetailKunnr(DealerAdujstDetail detail) {
		return (List<DealerAdujstDetail>)getSqlMapClientTemplate().queryForList("kunnrBusiness.getDealerAdjustmentDetailKunnr", detail);
	}
	/**
	 * Title: 品项判断
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月20日 上午10:15:31
	 * @param matter
	 * @return
	 */
	@Override
	public int getMattercount(DealerAdujstDetail detail) {
		return (Integer)getSqlMapClientTemplate().queryForObject("kunnrBusiness.getMattercount", detail);
	}
	/**
	 * Title: 经销商提报调整之明细保存
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月23日 上午11:34:56
	 * @param dealer
	 */
	@Override
	public String createDealerAdjustDetail(DealerAdujstDetail dealer) {
		return (String) getSqlMapClientTemplate().insert("kunnrBusiness.createDealerAdjustDetail", dealer);
	}
	/**
	 * Title: 经销商提报调整之总单保存
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月23日 上午11:34:56
	 * @param dealer
	 */
	@Override
	public String createDealerAdjustMennt(DealerAdjustment dealerAdjustment) {
		return (String) getSqlMapClientTemplate().insert("kunnrBusiness.createDealerAdjustMent", dealerAdjustment);
	}
	/**
	 * Title: 经销商详单之列表
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月24日 上午8:53:23
	 * @param dealerAdujstDetail
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<DealerAdujstDetail> getDealerAdjustDetailList(DealerAdujstDetail dealerAdujstDetail) {
		return (List<DealerAdujstDetail>)getSqlMapClientTemplate().queryForList("kunnrBusiness.getDealerAdjustDetailList", dealerAdujstDetail);
	}
	/**
	 * Title: 事务提交成功后保存事务号
	 * Description: crmPlatform
	 * @author lu
	 * @date 2016年5月24日 下午2:37:59
	 * @param dealerAdjustment
	 * @return
	 */
	@Override
	public int updateDealerAdjustment(DealerAdjustment dealerAdjustment) {
		return getSqlMapClientTemplate().update("kunnrBusiness.updateDealerAdjustment",dealerAdjustment);
	}
	
	@Override
	public DealerAdjustment getDealerAdjustmentById(String ids) {
		return (DealerAdjustment) getSqlMapClientTemplate().queryForObject("kunnrBusiness.getDealerAdjustmentById", ids);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DealerAdjustment> getDealerAdjustmentListForXls(DealerAdjustment dealerAdjustment) {
		return getSqlMapClientTemplate().queryForList("kunnrBusiness.getDealerAdjustmentListForXls",dealerAdjustment);
	}

	@Override
	public DealerAdjustment getDealerAdjustment(DealerAdjustment dealerAdjustment1) {
		return (DealerAdjustment)getSqlMapClientTemplate().queryForObject("kunnrBusiness.getDealerAdjustment",dealerAdjustment1);
	}

	@Override
	public int updateDealerAdjustmentById(DealerAdjustment dealerAdjustment) {
		return getSqlMapClientTemplate().update("kunnrBusiness.updateDealerAdjustmentById",dealerAdjustment);
	}

	@Override
	public int updateCrmTbTarget(DealerAdujstDetail detail) {
		return getSqlMapClientTemplate().update("kunnrBusiness.updateCrmTbTarget",detail);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DealerAdujstDetail> getKunnrForCrmTarget(DealerAdujstDetail dealerAdujstDetail) {
		return getSqlMapClientTemplate().queryForList("kunnrBusiness.getKunnrForCrmTarget",dealerAdujstDetail);
	}
	/**
	 * 找到userId所在组织,包括下级组织
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AllUsers> getOrgsByUserId(String userId) {
		return (List<AllUsers>)getSqlMapClientTemplate().queryForList("goal.getOrgsByUserId",userId);
	}

	@Override
	public DealerAdujstDetail getMatnrAndMaktx(DealerAdujstDetail detail) {
		return (DealerAdujstDetail) getSqlMapClientTemplate().queryForObject("kunnrBusiness.getMatnrAndMaktx",detail);
	}
	
}
