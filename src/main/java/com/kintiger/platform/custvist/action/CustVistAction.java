package com.kintiger.platform.custvist.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.customer.pojo.Customer;
import com.kintiger.platform.custvist.pojo.BCustVwDetail;
import com.kintiger.platform.custvist.service.ICustVistService;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.util.ExcelUtil;



public class CustVistAction extends BaseAction  {


	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CustVistAction.class);
	
	private ICustVistService iCustVistService;
	private List<BCustVwDetail> custDetailList;
	private Customer customer;
	private String orgName;
	private String orgId;
	private String posId;
	private String sort;
	private String order;
	private int total;
	private String updateString;

	public String getUpdateString() {
		return updateString;
	}
	public void setUpdateString(String updateString) {
		this.updateString = updateString;
	}


	private String weekDay;
	private BCustVwDetail bCustVwDetail;
	private String custOrderIds;

	private boolean resultBoolean;

	/**
	 * ҳ����ʾ���ݷõĿͻ���Ϣ
	 */
	public String custVistOrder() {
		return SUCCESS;
	}
	public String toOrgTree(){
		return "toOrgTree";
	}
	public String selectOrgTree4Station(){
		return "selectOrgTree4Station";
	}
	/**
	 * ��ת���ͻ��ݷô���ά��ҳ��
	 */
 	@JsonResult(field = "custDetailList", include = { "custDetailId", "posId",
 			"orgId", "empName", "custNumber", "custNameZH", "custAddress",
 			"channelName", "itemName", "weekDay", "visitTime", "visitOrder",
 			"posName", "custOrgName", "contactPhone", "contactName",
 			"custLevelName" },total="total")
	public String getCustVistOrderJsonList() {
		bCustVwDetail = new BCustVwDetail();
		if (orgId != null &&orgId.equals("firsttime")) {
			return null;
		}
		
		bCustVwDetail.setOrgId(orgId);
		
		bCustVwDetail.setPosId(posId);		
		bCustVwDetail.setWeekDay(weekDay);
		if(this.getSort()==null||this.getSort().equals("")){
			bCustVwDetail.setSort("visitOrder");
		}else{
			bCustVwDetail.setSort(this.getSort());
		}
		
		if(this.getSort()==null||this.getSort().equals("")){
			bCustVwDetail.setDir("asc");
		}else{
			bCustVwDetail.setDir(this.getOrder());
		}
		
		//bCustVwDetail.setStart(getStart());
		//bCustVwDetail.setEnd(getEnd());

		custDetailList = iCustVistService.getCustVistList(bCustVwDetail);
		
		total=iCustVistService.getCustVistListTotal(bCustVwDetail);
	
		return JSON;
	}
 	



	/**
	 * �ͻ��ݷô������
	 */

	public String  updateCustVistOrder() {
		System.err.println(updateString);
		updateString=updateString.substring(0,updateString.length()-1);
		List<BCustVwDetail> bcdlist = new ArrayList<BCustVwDetail>();
		BCustVwDetail bcvw;	
		String[] strings = updateString.split(",");
		for (int i = 0; i < strings.length; i++) {
			bcvw=new BCustVwDetail();
			String[] strings2 = strings[i].split(":");
			bcvw.setCustDetailId(strings2[0]);
			bcvw.setVisitOrder(strings2[1]);
			bcdlist.add(bcvw);
		}
		boolean res = iCustVistService.updateCustVistOrder(bcdlist);
		if (res) {
			resultBoolean = true;
		} else {
			resultBoolean = false;
		}
		return RESULT_MESSAGE;
	}

	/**
	 * excel �����ͻ��ݷô���
	 */
	public String exportCustVisitOrder(){
		bCustVwDetail = new BCustVwDetail();
		bCustVwDetail.setOrgId(orgId);		
		bCustVwDetail.setPosId(posId);		
		bCustVwDetail.setWeekDay(weekDay);
		bCustVwDetail.setSort("visitOrder");
		bCustVwDetail.setDir("asc");
		custDetailList = iCustVistService.getCustVistList(bCustVwDetail);

		HttpServletResponse response = ServletActionContext
				.getResponse();
				 PrintWriter print = null;
				 try {
			        response.setContentType("application/csv;charset=gb18030");
			    	response.setHeader(
							"Content-Disposition",
							"attachment; filename=\""
									+ new String("�ͻ��ݷô�������".getBytes("GBK"),
											("ISO8859-1")) + ".csv\"");
			        print = response.getWriter();
			        StringBuffer linedata = new StringBuffer();
			            linedata.append("�ͻ���֯");
			            linedata.append(",");
			            linedata.append("ְλ����");
			            linedata.append(",");
			            linedata.append("ҵ������");
			            linedata.append(",");
			            linedata.append("�ͻ�����");
			            linedata.append(",");
			            linedata.append("�ͻ�����");
			            linedata.append(",");
			            linedata.append("�ͻ���ַ");
			            linedata.append(",");
			            linedata.append("��ϵ��");
			            linedata.append(",");
			            linedata.append("��ϵ�绰");
			            linedata.append(",");
			            linedata.append("��������");
			            linedata.append(",");
			            linedata.append("�ݷ�Ƶ��");
			            linedata.append(",");
			            linedata.append("�ݷ�ʱ��");
			            linedata.append(",");
			            linedata.append("�ݷ��ճ�");
			            linedata.append(",");
			            linedata.append("�ݷô���");
			            linedata.append(",");
			            linedata.append("�ͻ��ȼ�");
			            linedata.append("\n");
			            if(custDetailList!=null){
			                for (int i = 0; i < custDetailList.size(); i++) {
				            	BCustVwDetail detail = custDetailList.get(i);
				            	linedata.append(clearNull(detail.getCustOrgName())+",");//�ͻ���֯
				            	linedata.append(clearNull(detail.getPosName())+",");//ְλ����
				            	linedata.append(clearNull(detail.getEmpName())+",");//ҵ������
				            	linedata.append(clearNull(detail.getCustNumber())+",");//�ͻ�����
				            	linedata.append(clearNull(detail.getCustNameZH())+",");//�ͻ�����
				            	linedata.append(clearNull(detail.getCustAddress())+",");//�ͻ���ַ
				            	linedata.append(clearNull(detail.getContactName())+",");//��ϵ��
				            	linedata.append(clearNull(detail.getContactPhone())+",");//��ϵ�绰
				            	linedata.append(clearNull(detail.getChannelName())+",");//��������
				            	linedata.append(clearNull(detail.getItemName())+",");//�ݷ�Ƶ��
				            	linedata.append(clearNull(detail.getVisitTime())+",");//�ݷ�ʱ��
				            	linedata.append(clearNull(detail.getWeekDay())+",");//�ݷ��ճ�
				            	linedata.append(clearNull(detail.getVisitOrder())+",");//�ݷô���
				            	linedata.append(clearNull(detail.getCustLevelName()));//�ͻ��ȼ�
				            	linedata.append("\n");
							} 	
			            }
			    
			            
			            
			            if (linedata.length() > 0) {
			                print.write(linedata.toString());
			            } 
			        }catch (Exception ex) {
			            ex.printStackTrace();
			        } finally {
			                    if (print != null)
			                        print.close();
			        }
		
		
		
		return RESULT_MESSAGE;
		
	}
	
	
	private static String clearNull(Object obj){
		if(obj==null||obj.equals(""))
		return "";
		return obj.toString();
		
	}

		

	public ICustVistService getiCustVistService() {
		return iCustVistService;
	}

	public void setiCustVistService(ICustVistService iCustVistService) {
		this.iCustVistService = iCustVistService;
	}

	public List<BCustVwDetail> getCustDetailList() {
		return custDetailList;
	}

	public void setCustDetailList(List<BCustVwDetail> custDetailList) {
		this.custDetailList = custDetailList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public BCustVwDetail getbCustVwDetail() {
		return bCustVwDetail;
	}

	public void setbCustVwDetail(BCustVwDetail bCustVwDetail) {
		this.bCustVwDetail = bCustVwDetail;
	}

	public String getCustOrderIds() {
		return custOrderIds;
	}

	public void setCustOrderIds(String custOrderIds) {
		this.custOrderIds = custOrderIds;
	}

	public boolean isResultBoolean() {
		return resultBoolean;
	}

	public void setResultBoolean(boolean resultBoolean) {
		this.resultBoolean = resultBoolean;
	}
	public String getSort() {
		return sort;
	}


	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}
}
