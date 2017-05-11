package com.kintiger.platform.qualityChecking.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.qualityChecking.pojo.Materiel;
import com.kintiger.platform.qualityChecking.pojo.QualityChecking;


/**
 * @Description:服务单接口
 * @author:xg.chen
 * @time:2017年5月8日 下午2:58:51
 * @version:1.0
 */
public interface IQualityCheckingService {
	/**
	 * @Description:物料总数统计
	 * @author:xg.chen 
	 * @date:2017年5月8日 下午4:26:58 
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	int getMaterielListCount(Materiel mat);
	/**
	 * @Description:物料列表
	 * @author:xg.chen 
	 * @date:2017年5月8日 下午4:27:07 
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	List<Materiel> getMaterielList(Materiel mat);
	/**
	 * @Description:导入数据验证
	 * @author:xg.chen 
	 * @date:2017年5月9日 上午11:12:03 
	 * @param uploadFile
	 * @return
	 * @version:1.0
	 */
	Map<String, Object> qualityCheckingImportCsv(File uploadFile);
	/**
	 * @Description:保存数据
	 * @author:xg.chen 
	 * @date:2017年5月9日 下午4:40:34 
	 * @param qualityChecking1
	 * @version:1.0
	 */
	int creatQualityChecking(QualityChecking qualityChecking1);
	/**
	 * @Description:计算总数
	 * @author:xg.chen 
	 * @date:2017年5月9日 下午5:36:42 
	 * @param qualityCheck
	 * @return
	 * @version:1.0
	 */
	int getQualityCheckingCount(QualityChecking qualityCheck);
	/**
	 * @Description:获取列表
	 * @author:xg.chen 
	 * @date:2017年5月9日 下午5:39:21 
	 * @param qualityCheck
	 * @return
	 * @version:1.0
	 */
	List<QualityChecking> getQualityCheckingJsonList(
			QualityChecking qualityCheck);
	/**
	 * @Description:上传附件
	 * @author:xg.chen 
	 * @date:2017年5月10日 上午11:30:15 
	 * @param qualityCheck
	 * @return
	 * @version:1.0
	 */
	int updateQualityCheckingUploadFile(QualityChecking qualityCheck);
	/**
	 * @Description:获取附件
	 * @author:xg.chen 
	 * @date:2017年5月10日 下午2:39:39 
	 * @param id
	 * @return
	 * @version:1.0
	 */
	QualityChecking getQualityCheckingName(String id);
	/**
	 * @Description:获取登陆者角色
	 * @author:xg.chen 
	 * @date:2017年5月11日 上午9:09:09 
	 * @param userId
	 * @param string
	 * @return
	 * @version:1.0
	 */
	boolean getOfficeRole(String userId, String string);

}
