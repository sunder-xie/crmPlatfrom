package com.kintiger.platform.qualityChecking.dao;

import java.util.List;

import com.kintiger.platform.qualityChecking.pojo.Materiel;
import com.kintiger.platform.qualityChecking.pojo.QualityChecking;


/**
 * @Description:���ݲ�ӿ�
 * @author:xg.chen
 * @time:2017��5��8�� ����2:59:17
 * @version:1.0
 */
public interface IQualityCheckingDao {
	/**
	 * @Description:��������ͳ��
	 * @author:xg.chen 
	 * @date:2017��5��8�� ����4:32:23 
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	int getMaterielListCount(Materiel mat);
	/**
	 * @Description:�����б�
	 * @author:xg.chen 
	 * @date:2017��5��8�� ����4:32:26 
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	List<Materiel> getMaterielList(Materiel mat);
	/**
	 * @Description:��ȡ��������
	 * @author:xg.chen 
	 * @date:2017��5��9�� ����4:23:23 
	 * @param mat
	 * @return
	 * @version:1.0
	 */
	List<Materiel> getMaterielNameList(Materiel mat);
	/**
	 * @Description:��������
	 * @author:xg.chen 
	 * @date:2017��5��9�� ����4:42:19 
	 * @param qualityChecking1
	 * @version:1.0
	 */
	void creatQualityChecking(QualityChecking qualityChecking1);
	/**
	 * @Description:ͳ������
	 * @author:xg.chen 
	 * @date:2017��5��9�� ����5:41:52 
	 * @param qualityCheck
	 * @return
	 * @version:1.0
	 */
	int getQualityCheckingCount(QualityChecking qualityCheck);
	/**
	 * @Description:��ȡ�б�
	 * @author:xg.chen 
	 * @date:2017��5��9�� ����5:41:55 
	 * @param qualityCheck
	 * @return
	 * @version:1.0
	 */
	List<QualityChecking> getQualityCheckingJsonList(
			QualityChecking qualityCheck);
	/**
	 * @Description:�ϴ�����
	 * @author:xg.chen 
	 * @date:2017��5��10�� ����11:32:26 
	 * @param qualityCheck
	 * @version:1.0
	 */
	void updateQualityCheckingUploadFile(QualityChecking qualityCheck);
	/**
	 * @Description:��ȡ����
	 * @author:xg.chen 
	 * @date:2017��5��10�� ����2:41:12 
	 * @param id
	 * @return
	 * @version:1.0
	 */
	QualityChecking getQualityCheckingName(String id);
	/**
	 * @Description:��ȡ��½�߽�ɫ
	 * @author:xg.chen 
	 * @date:2017��5��11�� ����9:11:31 
	 * @param userId
	 * @param string
	 * @return
	 * @version:1.0
	 */
	int getOfficeRole(String userId, String string);

}
