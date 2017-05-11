package com.kintiger.platform.sales.dao;

import java.util.List;

import com.kintiger.platform.sales.pojo.A603Konp;
import com.kintiger.platform.sales.pojo.Region;
import com.kintiger.platform.sales.pojo.T001;
import com.kintiger.platform.sales.pojo.T001w;
import com.kintiger.platform.sales.pojo.T171t;
import com.kintiger.platform.sales.pojo.Tspa;
import com.kintiger.platform.sales.pojo.Tvbot;
import com.kintiger.platform.sales.pojo.Tvbvk;
import com.kintiger.platform.sales.pojo.Tvgrt;
import com.kintiger.platform.sales.pojo.Tvkbt;
import com.kintiger.platform.sales.pojo.Tvkbz;
import com.kintiger.platform.sales.pojo.Tvko;
import com.kintiger.platform.sales.pojo.Tvtwt;
import com.kintiger.platform.sales.pojo.Tvv1t;
import com.kintiger.platform.sales.pojo.Tvv2t;
import com.kintiger.platform.sales.pojo.Zdqsf;
import com.kintiger.platform.sales.pojo.Zwlqy;

public interface ISalesDao {
	/**
	 * ���������֯����
	 * 
	 */
	public void deleteTvko();
	
	/**
	 * ��������������֯
	 * 
	 * @param tvkos
	 */
	public void createTvkoBatch(List<Tvko> tvkoList);
	
	/**
	 * ��ѯ������֯�б�����
	 * 
	 * @param tvko
	 * @return
	 */
	public int getTvkoCount(Tvko tvko);
	
	/**
	 * ��ѯ������֯�б�
	 * 
	 * @param tvko
	 * @return
	 */
	public List<Tvko> getTvkoList(Tvko tvko);
	
	/**
	 * ��շ�����������
	 * 
	 */
	public void deleteTvtwt();
	
	/**
	 * ����������������
	 * 
	 * @param tvtwts
	 */
	public void createTvtwtBatch(List<Tvtwt> tvtwtList);
	
	/**
	 * ��ѯ���������б�����
	 * 
	 * @param tvtwt
	 * @return
	 */
	public int getTvtwtCount(Tvtwt tvtwt);
	
	/**
	 * ��ѯ���������б�
	 * 
	 * @param tvtwt
	 * @return
	 */
	public List<Tvtwt> getTvtwtList(Tvtwt tvtwt);
	
	/**
	 * ��ղ�Ʒ������
	 * 
	 */
	public void deleteTspa();
	
	/**
	 * ����������Ʒ��
	 * 
	 * @param tspaList
	 */
	public void createTspaBatch(List<Tspa> tspaList);
	
	/**
	 * ��ѯ��Ʒ���б�����
	 * 
	 * @param tspa
	 * @return
	 */
	public int getTspaCount(Tspa tspa);
	
	/**
	 * ��ѯ��Ʒ���б�
	 * 
	 * @param tspa
	 * @return
	 */
	public List<Tspa> getTspaList(Tspa tspa);
	
	/**
	 * ������۲�������
	 * 
	 */
	public void deleteTvkbt();
	
	/**
	 * �����������۲���
	 * 
	 * @param tvkbtList
	 */
	public void createTvkbtBatch(List<Tvkbt> tvkbtList);
	
	/**
	 * ��ѯ���۲����б�����
	 * 
	 * @param tvkbt
	 * @return
	 */
	public int getTvkbtCount(Tvkbt tvkbt);
	
	/**
	 * ��ѯ���۲����б�
	 * 
	 * @param tvkbt
	 * @return
	 */
	public List<Tvkbt> getTvkbtList(Tvkbt tvkbt);
	
	/**
	 * ������۲���-��������-��Ʒ��-���۲��Ź�ϵ����
	 * 
	 */
	public void deleteTvkbz();
	
	/**
	 * �����������۲���-��������-��Ʒ��-���۲��Ź�ϵ
	 * 
	 * @param tvkbzList
	 */
	public void createTvkbzBatch(List<Tvkbz> tvkbzList);
	
	/**
	 * ��ѯ���۲���-��������-��Ʒ��-���۲��Ź�ϵ�б�����
	 * 
	 * @param tvkbz
	 * @return
	 */
	public int getTvkbzCount(Tvkbz tvkbz);
	
	/**
	 * ��ѯ���۲���-��������-��Ʒ��-���۲��Ź�ϵ�б�
	 * 
	 * @param tvkbz
	 * @return
	 */
	public List<Tvkbz> getTvkbzList(Tvkbz tvkbz);
	
	/**
	 * �������ʡ������
	 * 
	 */
	public void deleteTvgrt();
	
	/**
	 * ������������ʡ��
	 * 
	 * @param tvgrtList
	 */
	public void createTvgrtBatch(List<Tvgrt> tvgrtList);
	
	/**
	 * ��ѯ����ʡ���б�����
	 * 
	 * @param tvgrt
	 * @return
	 */
	public int getTvgrtCount(Tvgrt tvgrt);
	
	/**
	 * ��ѯ����ʡ���б�
	 * 
	 * @param tvgrt
	 * @return
	 */
	public List<Tvgrt> getTvgrtList(Tvgrt tvgrt);
	
	/**
	 * ������۴�������
	 * 
	 */
	public void deleteT171t();
	
	/**
	 * �����������۴���
	 * 
	 * @param t171tList
	 */
	public void createT171tBatch(List<T171t> t171tList);
	
	/**
	 * ��ѯ���۴����б�����
	 * 
	 * @param t171t
	 * @return
	 */
	public int getT171tCount(T171t t171t);
	
	/**
	 * ��ѯ���۴����б�
	 * 
	 * @param t171t
	 * @return
	 */
	public List<T171t> getT171tList(T171t t171t);
	
	/**
	 * ��չ�������
	 * 
	 */
	public void deleteT001w();
	
	/**
	 * ������������
	 * 
	 * @param t001wList
	 */
	public void createT001wBatch(List<T001w> t001wList);
	
	/**
	 * ��ѯ�����б�����
	 * 
	 * @return
	 */
	public int getT001wCount();
	
	/**
	 * ��ѯ�����б�
	 * 
	 * @param t001w
	 * @return
	 */
	public List<T001w> getT001wList(T001w t001w);
	
	/**
	 * ��չ�˾��������
	 * 
	 */
	public void deleteT001();
	
	/**
	 * ����������˾����
	 * 
	 * @param t001List
	 */
	public void createT001Batch(List<T001> t001List);
	
	/**
	 * ��ѯ��˾�����б�����
	 * 
	 * @return
	 */
	public int getT001Count(T001 t001);
	
	/**
	 * ��ѯ��˾�����б�
	 * 
	 * @param t001
	 * @return
	 */
	public List<T001> getT001List(T001 t001);
	
	/**
	 * ��ճ�������
	 * 
	 */
	public void deleteTvv1t();
	
	/**
	 * ������������
	 * 
	 * @param tvv1tList
	 */
	public void createTvv1tBatch(List<Tvv1t> tvv1tList);
	
	/**
	 * ��ѯ�����б�����
	 * 
	 * @return
	 */
	public int getTvv1tCount(Tvv1t tvv1t);
	
	/**
	 * ��ѯ�����б�
	 * 
	 * @param tvv1t
	 * @return
	 */
	public List<Tvv1t> getTvv1tList(Tvv1t tvv1t);
	
	/**
	 * �����������
	 * 
	 */
	public void deleteTvv2t();
	
	/**
	 * ������������
	 * 
	 * @param tvv2tList
	 */
	public void createTvv2tBatch(List<Tvv2t> tvv2tList);
	
	/**
	 * ��ѯ�����б�����
	 * 
	 * @return
	 */
	public int getTvv2tCount(Tvv2t tvv2t);
	
	/**
	 * ��ѯ�����б�
	 * 
	 * @param tvv2t
	 * @return
	 */
	public List<Tvv2t> getTvv2tList(Tvv2t tvv2t);
	
	/**
	 * ������ϻؿ�������
	 * 
	 */
	public void deleteTvbot();
	
	/**
	 * �����������ϻؿ���
	 * 
	 * @param tvbotList
	 */
	public void createTvbotBatch(List<Tvbot> tvbotList);
	
	/**
	 * ��ѯ���ϻؿ����б�����
	 * 
	 * @return
	 */
	public int getTvbotCount(Tvbot tvbot);
	
	/**
	 * ��ѯ���ϻؿ����б�
	 * 
	 * @param tvbot
	 * @return
	 */
	public List<Tvbot> getTvbotList(Tvbot tvbot);
	
	/**
	 * �������������������
	 * 
	 */
	public void deleteZwlqy();
	
	/**
	 * ��������������������
	 * 
	 * @param zwlqyList
	 */
	public void createZwlqyBatch(List<Zwlqy> zwlqyList);
	
	/**
	 * ��ѯ�������������б�����
	 * 
	 * @return
	 */
	public int getZwlqyCount(Zwlqy zwlqy);
	
	/**
	 * ��ѯ���ϻؿ����б�
	 * 
	 * @param tvbot
	 * @return
	 */
	public List<Zwlqy> getZwlqyList(Zwlqy zwlqy);
	
	/**
	 * ��������������
	 * @param region
	 * @return
	 */
	public List<Region> searchRegion(Region region);
	
	/**
	 * �������ʡ�������۴�����ϵ����
	 * 
	 */
	public void deleteZdqsf();
	
	/**
	 * ������������ʡ�������۴�����ϵ
	 * 
	 * @param zdqsfList
	 */
	public void createZdqsfBatch(List<Zdqsf> zdqsfList);
	
	/**
	 * ��ѯ����ʡ�������۴�����ϵ����
	 * 
	 * @param zdqsf
	 * @return
	 */
	public int getZdqsfCount(Zdqsf zdqsf);
	
	/**
	 * ��ѯ����ʡ�������۴�����ϵ�б�
	 * 
	 * @param tspa
	 * @return
	 */
	public List<Zdqsf> getZdqsfList(Zdqsf zdqsf);
	
	/**
	 * �������ʡ�������۲��Ź�ϵ����
	 * 
	 */
	public void deleteTvbvk();
	
	/**
	 * ������������ʡ�������۲��Ź�ϵ
	 * 
	 * @param tvbvkList
	 */
	public void createTvbvkBatch(List<Tvbvk> tvbvkList);
	
	/**
	 * ��ѯ����ʡ�������۲��Ź�ϵ����
	 * 
	 * @param zdqsf
	 * @return
	 */
	public int getTvbvkCount(Tvbvk tvbvk);
	
	/**
	 * ��ѯ����ʡ�������۲��Ź�ϵ�б�
	 * 
	 * @param tspa
	 * @return
	 */
	public List<Tvbvk> getTvbvkList(Tvbvk tvbvk);
	
	/**
	 * ���ݴ�����ѯ����ʡ���б�
	 * 
	 * @param tvgrt
	 * @return
	 */
	public List<Tvgrt> getTvgrtListByZdqsf(Zdqsf zdqsf);
	/**
	 * ���ݴ�����ѯ����ʡ�ݻ���
	 * @param zdqsf
	 * @return
	 */
	public int getTvgrtZdqsfCount(Zdqsf zdqsf);
	
	/**
	 * ����ʡ�ݲ�ѯ���۲����б�
	 * 
	 * @param 
	 * @return
	 */
	public List<Tvkbt> getTvkbtJsonListByTvbvk(Tvbvk tvbvk);
	/**
	 * ����ʡ�ݲ�ѯ���۲��Ż���
	 * @param 
	 * @return
	 */
	public int getTvkbtJsonListByTvbvkCount(Tvbvk tvbvk);
	
	
	/**
	 * ���������۸�����
	 * 
	 */
	public void deleteKbetr();
	
	/**
	 * ��������������۸�
	 * 
	 * @param a603KonpList
	 */
	public void createKbetrBatch(List<A603Konp> a603KonpList);
	
	/**
	 * ��ѯ������۸��б�����
	 * 
	 * @param a603Konp
	 * @return
	 */
	public int getKbetrCount(A603Konp a603Konp);
	
	/**
	 * ��ѯ������۸��б�
	 * 
	 * @param a603Konp
	 * @return
	 */
	public List<A603Konp> getKbetrList(A603Konp a603Konp);

}
