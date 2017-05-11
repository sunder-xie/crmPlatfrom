package com.kintiger.platform.qualityChecking.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.kintiger.platform.qualityChecking.pojo.Materiel;
import com.kintiger.platform.qualityChecking.pojo.QualityChecking;


/**
 * @Description:���񵥽ӿ�
 * @author:xg.chen
 * @time:2017��5��8�� ����2:58:51
 * @version:1.0
 */
public interface IQualityCheckingService {
	/**
	 * @Description:��������ͳ��
	 * @author:xg.chen 
	 * @date:2017��5��8�� ����4:26:58 
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	int getMaterielListCount(Materiel mat);
	/**
	 * @Description:�����б�
	 * @author:xg.chen 
	 * @date:2017��5��8�� ����4:27:07 
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	List<Materiel> getMaterielList(Materiel mat);
	/**
	 * @Description:����������֤
	 * @author:xg.chen 
	 * @date:2017��5��9�� ����11:12:03 
	 * @param uploadFile
	 * @return
	 * @version:1.0
	 */
	Map<String, Object> qualityCheckingImportCsv(File uploadFile);
	/**
	 * @Description:��������
	 * @author:xg.chen 
	 * @date:2017��5��9�� ����4:40:34 
	 * @param qualityChecking1
	 * @version:1.0
	 */
	int creatQualityChecking(QualityChecking qualityChecking1);
	/**
	 * @Description:��������
	 * @author:xg.chen 
	 * @date:2017��5��9�� ����5:36:42 
	 * @param qualityCheck
	 * @return
	 * @version:1.0
	 */
	int getQualityCheckingCount(QualityChecking qualityCheck);
	/**
	 * @Description:��ȡ�б�
	 * @author:xg.chen 
	 * @date:2017��5��9�� ����5:39:21 
	 * @param qualityCheck
	 * @return
	 * @version:1.0
	 */
	List<QualityChecking> getQualityCheckingJsonList(
			QualityChecking qualityCheck);
	/**
	 * @Description:�ϴ�����
	 * @author:xg.chen 
	 * @date:2017��5��10�� ����11:30:15 
	 * @param qualityCheck
	 * @return
	 * @version:1.0
	 */
	int updateQualityCheckingUploadFile(QualityChecking qualityCheck);
	/**
	 * @Description:��ȡ����
	 * @author:xg.chen 
	 * @date:2017��5��10�� ����2:39:39 
	 * @param id
	 * @return
	 * @version:1.0
	 */
	QualityChecking getQualityCheckingName(String id);
	/**
	 * @Description:��ȡ��½�߽�ɫ
	 * @author:xg.chen 
	 * @date:2017��5��11�� ����9:09:09 
	 * @param userId
	 * @param string
	 * @return
	 * @version:1.0
	 */
	boolean getOfficeRole(String userId, String string);

}
