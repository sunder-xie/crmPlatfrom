package com.kintiger.platform.qualityChecking.dao;

import java.util.List;

import com.kintiger.platform.qualityChecking.pojo.Materiel;
import com.kintiger.platform.qualityChecking.pojo.QualityChecking;


/**
 * @Description:数据层接口
 * @author:xg.chen
 * @time:2017年5月8日 下午2:59:17
 * @version:1.0
 */
public interface IQualityCheckingDao {
	/**
	 * @Description:物料总数统计
	 * @author:xg.chen 
	 * @date:2017年5月8日 下午4:32:23 
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	int getMaterielListCount(Materiel mat);
	/**
	 * @Description:物料列表
	 * @author:xg.chen 
	 * @date:2017年5月8日 下午4:32:26 
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	List<Materiel> getMaterielList(Materiel mat);
	/**
	 * @Description:获取物料描述
	 * @author:xg.chen 
	 * @date:2017年5月9日 下午4:23:23 
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	List<Materiel> getMaterielNameList(Materiel mat);
	/**
	 * @Description:保存数据
	 * @author:xg.chen 
	 * @date:2017年5月9日 下午4:42:19 
	 * @param qualityChecking1
	 * @version:1.0
	 */
	void creatQualityChecking(QualityChecking qualityChecking1);
	/**
	 * @Description:统计数据
	 * @author:xg.chen 
	 * @date:2017年5月9日 下午5:41:52 
	 * @param qualityCheck
	 * @return
	 * @version:1.0
	 */
	int getQualityCheckingCount(QualityChecking qualityCheck);
	/**
	 * @Description:获取列表
	 * @author:xg.chen 
	 * @date:2017年5月9日 下午5:41:55 
	 * @param qualityCheck
	 * @return
	 * @version:1.0
	 */
	List<QualityChecking> getQualityCheckingJsonList(
			QualityChecking qualityCheck);
	/**
	 * @Description:上传附件
	 * @author:xg.chen 
	 * @date:2017年5月10日 上午11:32:26 
	 * @param qualityCheck
	 * @version:1.0
	 */
	void updateQualityCheckingUploadFile(QualityChecking qualityCheck);
	/**
	 * @Description:获取附件
	 * @author:xg.chen 
	 * @date:2017年5月10日 下午2:41:12 
	 * @param id
	 * @return
	 * @version:1.0
	 */
	QualityChecking getQualityCheckingName(String id);
	/**
	 * @Description:获取登陆者角色
	 * @author:xg.chen 
	 * @date:2017年5月11日 上午9:11:31 
	 * @param userId
	 * @param string
	 * @return
	 * @version:1.0
	 */
	int getOfficeRole(String userId, String string);

}
