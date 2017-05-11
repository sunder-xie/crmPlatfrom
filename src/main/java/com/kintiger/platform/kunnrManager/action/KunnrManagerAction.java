package com.kintiger.platform.kunnrManager.action;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.ExportExcelUtil;
import com.kintiger.platform.framework.util.SuperCSV;
import com.kintiger.platform.kunnr.pojo.Kunnr;
import com.kintiger.platform.kunnr.service.IKunnrService;
import com.kintiger.platform.kunnrManager.pojo.DateControl;
import com.kintiger.platform.kunnrManager.pojo.KunnrManager;
import com.kintiger.platform.kunnrManager.pojo.Sku;
import com.kintiger.platform.kunnrManager.pojo.StockSafety;
import com.kintiger.platform.kunnrManager.service.IKunnrManagerService;
import com.kintiger.platform.org.service.IOrgService;

public class KunnrManagerAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2757778592329360890L;
	
	private IKunnrManagerService kunnrManagerService;
	private IKunnrService kunnrService;
	private IOrgService orgServiceHessian;
	
	private List<KunnrManager> kunnrManagerList;
	private KunnrManager kunnrManager;
	private List<StockSafety> stockSafetyList;
	private StockSafety stockSafety;
	private String kunnr;
	private Long orgId;
	private String orgName;
	private String orgIds;
	private String startDate;
	private String endDate;
	private Long skuId;
	private Long categoryId;
	private Integer total;
	private DateControl dateControl;
	private String isImportant;
	
	private String uploadFileFileName;
	private File uploadFile;

	
	public String toKunnrManager(){
		orgId=Long.parseLong(getUser().getOrgId());
		orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		return "toKunnrManager";
	}
	
	public String toStockSafety(){
		orgId=Long.parseLong(getUser().getOrgId());
		orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		return "toStockSafety";
	}
	
	public String toProductionPlan(){
		orgId=Long.parseLong(getUser().getOrgId());
		orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		return "toProductionPlan";
	}
	
	public String toProductionNeed(){
		orgId=Long.parseLong(getUser().getOrgId());
		orgName = orgServiceHessian.getOrgNameByOrgid(this.getUser().getOrgId());
		return "toProductionNeed";
	}
	
	@PermissionSearch
	@JsonResult(field = "kunnrManagerList",include = {"orgRegion","orgProvince","orgCity","orgId","kunnr",
			"kunnrName","year","month","skuId","skuName","cgId","categoryName","salesPlan","salesPlanNext","salesPlanNext2",
			"salesPlanLast","lastEstimateStock","stockSafety","stockSafetyNext","productionPlan","productionPlanNext",
			"salesReal","salesPlanFinal","salesPlanPercent","salesLastYearLast","salesLastYear","salesLastYearNext",
			"salesLastMonth","salesLastMonthFinal","salesLastMonthPercent","salesYear","salesYearGoalEstimate",
			"salesYearGoal","salesYearPercent","stockSafetyLimit","stockSafetyNextLimit","productionPlanLast"},total="total")
	public String searchKunnrManagerList(){
		kunnrManagerList = new ArrayList<KunnrManager>();
		kunnrManager = new KunnrManager();
		if(StringUtils.isNotEmpty(startDate)){
			kunnrManager.setStartDate(startDate);
		}
		if(StringUtils.isNotEmpty(endDate)){
			kunnrManager.setEndDate(endDate);
		}
		kunnrManager.setOrgIds(orgIds);
		kunnrManager.setKunnr(kunnr);
		kunnrManager.setSkuId(skuId);
		kunnrManager.setIsImportant(isImportant);
		kunnrManager.setStart(getStart());
		kunnrManager.setEnd(getEnd());
		total=kunnrManagerService.searchKunnrManagerListCount(kunnrManager);
		if(total>0){
			List<KunnrManager> list = kunnrManagerService.searchKunnrManagerList(kunnrManager);
				KunnrManager km = kunnrManagerService.getKunnrManagerListSum(kunnrManager);
				km.setKunnrName("�ϼƣ����䣩");
				kunnrManagerList.add(km);
			for(KunnrManager kmList : list){
				kunnrManagerList.add(kmList);
			}
		}
		return JSON;
	}
	
	public String exportKunnrManagerCsv() {
		OutputStream os = null;
		String report_name = "�����̷���Ԥ������ģ��";
		PrintWriter print = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("�����̱��");
			linedata.append(",");
			linedata.append("����������");
			linedata.append(",");
			linedata.append("sku���");
			linedata.append(",");
			linedata.append("���");
			linedata.append(",");
			linedata.append("�·�");
			linedata.append(",");
			linedata.append("���·���Ԥ��");
			linedata.append(",");
			linedata.append("���·���Ԥ��");
			linedata.append(",");
			linedata.append("�δ��·���Ԥ��");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (print != null) {
				try {
					print.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				print = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				os = null;
			}
		}
		return RESULT_MESSAGE;
	}
	
	public String importKunnrManagerCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		Map<String,String> kunnrMap=new HashMap<String,String>();
		Map<String,String> skuMap=new HashMap<String,String>();
		try {
			String rcs = "";
			String rcs2 = "";
			kunnrManagerList = new ArrayList<KunnrManager>();
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))  //�ж��Ƿ�ΪCSV�ļ�
						|| (end != null && (end.equals(".CSV")))) {
					String[] header = SuperCSV.getHeaderFromFile(new File( //��ȡͷ���ֶ�
							uploadFile.toString()));
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // ��ȡ��������
					for(int j=0;j<content.size();j++){
						String[] s=content.get(j);
						if(s.length<=header.length){
							if(s.length>8){
								this.setFailMessage("��" + (j + 2) + "����������ȷ.");
								return RESULT_MESSAGE;
							}
							
							if(StringUtils.isEmpty(s[0])){
								rcs = rcs + "��" + (j + 2) + "�о����̱��Ϊ��.</br>";
								continue;
							}if(StringUtils.isEmpty(s[1])){
								rcs = rcs + "��" + (j + 2) + "�о���������Ϊ��.</br>";
								continue;
							}else{
								if(s[0].length()<8){
									int length=8-s[0].length();
									for(int x=0;x<length;x++){
										s[0]="0"+s[0];
									}
								}
								
								//��ID�����Ʒ���MAP,�ӿ���֤�ٶ�
								String kunnrName=kunnrMap.get(s[0]);
								if(kunnrName==null){
									Kunnr kunnr=new Kunnr();
									kunnr.setKunnr(s[0].trim());
									kunnr.setName1(s[1].trim());
									kunnr.setStatus("1");
									kunnr.setPagination("false");
									int num=kunnrService.kunnrSearchCount(kunnr);
									kunnr.setStatus("3");
									int num_1=kunnrService.kunnrSearchCount(kunnr);
									if(num==0 && num_1==0){
										rcs = rcs + "��" + (j + 2) + "�о����̱��/���Ʋ���ȷ���߸þ������ѹػ�.</br>";
									}else{
										kunnrMap.put(s[0], s[1]);
									}
								}else{
									if(!kunnrName.equals(s[1])){
										rcs = rcs + "��" + (j + 2) + "�о����̱�Ż����Ʋ���ȷ.</br>";
									}
								}
							}
							
							if(StringUtils.isEmpty(s[2])){
								rcs = rcs + "��" + (j + 2) + "��sku���Ϊ��.</br>";
							}else{
								String skuName=skuMap.get(s[2]);
								if(skuName==null){
									Sku sku = new Sku();
									sku.setSkuId(Long.valueOf(s[2]));
									List<Sku> skuList=kunnrManagerService.searchSkuList(sku);
									if(skuList==null || skuList.size()==0){
										rcs = rcs + "��" + (j + 2) + "��sku��Ų���ȷ.</br>";
									}else{
										skuMap.put(s[2], skuList.get(0).getSkuName());
									}
								}
							}
							
							if(StringUtils.isNotEmpty(s[3]) && StringUtils.isNotEmpty(s[4])){
								int year=Integer.parseInt(s[3]);
								int month=Integer.parseInt(s[4]);
								if(year<2000 || year>2100 || month<1 || month>12){
									rcs = rcs + "��" + (j + 2) + "����ݻ��·ݸ�ʽ����.</br>";
								}else{
									SimpleDateFormat sdf=new SimpleDateFormat("MM");
									SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
									int monthNow=Integer.parseInt(sdf.format(new Date()));
									int yearNow=Integer.parseInt(sdf1.format(new Date()));
									dateControl = new DateControl();
									dateControl.setId(month);
									dateControl.setStartDate(new Date());
									total = kunnrManagerService.searchKunnrManagerDateCount(dateControl);
									if(yearNow<year){
										month=(year-yearNow)*12+month;
									}
									if(total==0){ 
										if(month<=monthNow+1){
											if(month>12){
												rcs = rcs + "�޷��޸�"+(month-12)+"�����ݣ�</br>";
											}else{
												rcs = rcs + "�޷��޸�"+(month)+"�����ݣ�</br>";
											}
										}
									}else{
//										if(month<=monthNow){
//											rcs = rcs + "�޷��޸�"+(monthNow)+"�¼�֮ǰ���ݣ�</br>";
//										}
									}
								}
							}else{
								rcs = rcs + "��" + (j + 2) + "����ݺ��·ݲ���Ϊ��.</br>";
							}
							
							if(StringUtils.isEmpty(s[5]) || 
									StringUtils.isEmpty(s[6])|| StringUtils.isEmpty(s[7])){
								rcs = rcs + "��" + (j + 2) + "�з���Ԥ������Ϊ�գ�</br>";
							}else{
								Pattern p = Pattern.compile("^\\-?\\d+(\\.\\d+)?$");//������ʽ�ж��Ƿ�����
	        					Matcher matcher1 = p.matcher(s[5]);
	        					Matcher matcher2 = p.matcher(s[6]);
	        					Matcher matcher3 = p.matcher(s[7]);
								if (!matcher1.matches()){
									rcs = rcs + "��" + (j + 2) + "�з���Ԥ��Ϊ������ֵ��</br>";
								}else if(!matcher2.matches()){
									rcs = rcs + "��" + (j + 2) + "�д��·���Ԥ��Ϊ������ֵ��</br>";
								}else if(!matcher3.matches()){
									rcs = rcs + "��" + (j + 2) + "�дδ��·���Ԥ��Ϊ������ֵ��</br>";
								}else{
									if(s[4].length()<2){
										s[4]="0"+s[4];
									}
									KunnrManager kunnrManager=new KunnrManager();
									kunnrManager.setKunnr(s[0].trim());
									kunnrManager.setSkuId(Long.valueOf(s[2]));
									kunnrManager.setYear(s[3]);
									kunnrManager.setMonth(s[4]);
									kunnrManager.setUserId(Long.parseLong(this.getUser().getUserId()));
									
									kunnrManager.setSalesPlan(Double.parseDouble(s[5]));
									kunnrManager.setSalesPlanNext(Double.parseDouble(s[6]));
									kunnrManager.setSalesPlanNext2(Double.parseDouble(s[7]));
									
									kunnrManagerList.add(kunnrManager);
								}
							}
								
							if (!"".equals(rcs)) {
								result.append(rcs.toString() + "</br>");
								rcs = "";
								continue;
							}
						}else{
							rcs = rcs + "��" + (j + 2)
									+ "����������.</br>";
							result.append(rcs.toString() + "</br>");
							rcs = "";
							
						}
					}
					int num=0;
					if (kunnrManagerList.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<kunnrManagerList.size();j++){
							try {
								rcs2 = "";
								num+=kunnrManagerService.updateKunnrManager(kunnrManagerList.get(j));
								result1.setResult(true);
								result.append(rcs2.toString() + "</br>");
							} catch (Exception e) {
								rcs2 = rcs2 + "��" + (j + 2)
										+ "�����ݱ������ݿ�ʧ��.����ϵϵͳ����Ա.</br>";
								result.append(rcs2.toString() + "</br>");
							}
						}
					}
					if (result1.getResult()) {
						if(num==0){
							this.setSuccessMessage("����ɹ�����������Ϊ:"
									+ content.size() + "��");
						}else{
							this.setSuccessMessage("����ɹ�����������Ϊ:"
									+ content.size() + "��,���и���"+num+"������");
						}
					} else if (!result.toString().equals("")) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br>"
								+ result.toString());
					} else if (!result1.getResult()) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br>"
								+ result.toString());
					}
				}
			}else{
				this.setFailMessage("�ļ�������");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			this.setFailMessage("����ʧ�ܣ����������Ƿ���ȷ��");
			e.printStackTrace();
			return RESULT_MESSAGE;
		}finally{
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		}
		return RESULT_MESSAGE;
	}
	
	public String exportKunnrImportantCsv() {
		OutputStream os = null;
		String report_name = "�ص㾭���̵���ģ��";
		PrintWriter print = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("�����̱��");
			linedata.append(",");
			linedata.append("����������");
			linedata.append(",");
			linedata.append("�Ƿ��ص㣨Y/N��");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (print != null) {
				try {
					print.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				print = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				os = null;
			}
		}
		return RESULT_MESSAGE;
	}
	
	public String importKunnrImportantCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		Map<String,String> kunnrMap=new HashMap<String,String>();
		try {
			String rcs = "";
			String rcs2 = "";
			kunnrManagerList = new ArrayList<KunnrManager>();
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))  //�ж��Ƿ�ΪCSV�ļ�
						|| (end != null && (end.equals(".CSV")))) {
					String[] header = SuperCSV.getHeaderFromFile(new File( //��ȡͷ���ֶ�
							uploadFile.toString()));
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // ��ȡ��������
					for(int j=0;j<content.size();j++){
						String[] s=content.get(j);
						if(s.length<=header.length){
							if(s.length>7){
								this.setFailMessage("��" + (j + 2) + "����������ȷ.");
								return RESULT_MESSAGE;
							}
							
							if(StringUtils.isEmpty(s[0])){
								rcs = rcs + "��" + (j + 2) + "�о����̱��Ϊ��.</br>";
								continue;
							}if(StringUtils.isEmpty(s[1])){
								rcs = rcs + "��" + (j + 2) + "�о���������Ϊ��.</br>";
								continue;
							}else{
								if(s[0].length()<8){
									int length=8-s[0].length();
									for(int x=0;x<length;x++){
										s[0]="0"+s[0];
									}
								}
								
								//��ID�����Ʒ���MAP,�ӿ���֤�ٶ�
								String kunnrName=kunnrMap.get(s[0]);
								if(kunnrName==null){
									Kunnr kunnr=new Kunnr();
									kunnr.setKunnr(s[0]);
									kunnr.setName1(s[1]);
									kunnr.setPagination("false");
									int num=kunnrService.kunnrSearchCount(kunnr);
									if(num==0){
										rcs = rcs + "��" + (j + 2) + "�о����̱�Ż����Ʋ���ȷ.</br>";
									}else{
										kunnrMap.put(s[0], s[1]);
									}
								}else{
									if(!kunnrName.equals(s[1])){
										rcs = rcs + "��" + (j + 2) + "�о����̱�Ż����Ʋ���ȷ.</br>";
									}
								}
							}
							
							if(StringUtils.isEmpty(s[2])){
								rcs = rcs + "��" + (j + 2) + "���Ƿ��ص㾭���̲���Ϊ�գ�</br>";
							}else if(!"Y".equals(s[2]) && !"N".equals(s[2])){
								rcs = rcs + "��" + (j + 2) + "���밴Ҫ����д�ص��ǣ�</br>";
							}else{
								KunnrManager kunnrManager=new KunnrManager();
								kunnrManager.setKunnr(s[0]);
								if("Y".equals(s[2])){
									kunnrManager.setIsImportant("X");
								}
								
								kunnrManagerList.add(kunnrManager);
							}
								
							if (!"".equals(rcs)) {
								result.append(rcs.toString() + "</br>");
								rcs = "";
								continue;
							}
						}else{
							rcs = rcs + "��" + (j + 2)
									+ "����������.</br>";
							result.append(rcs.toString() + "</br>");
							rcs = "";
							
						}
					}
					if (kunnrManagerList.size() != 0
							&& result.toString().equals("")) {
						int count=kunnrManagerService.updateKunnrImportant(new KunnrManager());
						System.out.println(count);
						for(int j=0;j<kunnrManagerList.size();j++){
							try {
								rcs2 = "";
								kunnrManagerService.updateKunnrImportant(kunnrManagerList.get(j));
								result1.setResult(true);
								result.append(rcs2.toString() + "</br>");
							} catch (Exception e) {
								rcs2 = rcs2 + "��" + (j + 2)
										+ "�����ݱ������ݿ�ʧ��.����ϵϵͳ����Ա.</br>";
								result.append(rcs2.toString() + "</br>");
							}
						}
					}
					if (result1.getResult()) {
						this.setSuccessMessage("����ɹ�����������Ϊ:"
								+ content.size() + "��");
					} else if (!result.toString().equals("")) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br>"
								+ result.toString());
					} else if (!result1.getResult()) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br>"
								+ result.toString());
					}
				}
			}else{
				this.setFailMessage("�ļ�������");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			this.setFailMessage("����ʧ�ܣ����������Ƿ���ȷ��");
			e.printStackTrace();
			return RESULT_MESSAGE;
		}finally{
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		}
		return RESULT_MESSAGE;
	}
	
	public void exportForExcelKunnrManager() throws Exception{
		ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Ing");
    	
		ServletActionContext.getRequest().getSession().setAttribute("exportExcel", "Y");
		kunnrManagerList = new ArrayList<KunnrManager>();
		kunnrManager = new KunnrManager();
		if(StringUtils.isNotEmpty(startDate)){
			kunnrManager.setStartDate(startDate);
		}
		if(StringUtils.isNotEmpty(endDate)){
			kunnrManager.setEndDate(endDate);
		}
		kunnrManager.setOrgIds(orgIds);
		kunnrManager.setKunnr(kunnr);
		kunnrManager.setSkuId(skuId);
		kunnrManager.setIsImportant(isImportant);
		kunnrManager.setPagination("false");
		kunnrManagerList = kunnrManagerService.searchKunnrManagerList(kunnrManager);
		
		String[] rowName={"����", "ʡ��", "����", "�����̱��", "������", "sku", "��",
				"��", "���·���Ԥ��","���·���Ԥ��","�δ��·���Ԥ��"};
		String[] colNames={"orgRegion","orgProvince","orgCity","kunnr","kunnrName","skuName","year",
				"month","salesPlan","salesPlanNext","salesPlanNext2"};
		
		ExportExcelUtil ee=new ExportExcelUtil("����Ԥ����Ϣ", rowName, colNames, kunnrManagerList);
		ee.export();

		ServletActionContext.getRequest().getSession().removeAttribute("exportExcel");
	    ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Over");
	}
	
	public String deleteKunnrManager(){
		try {
			if(kunnrManagerList!=null && kunnrManagerList.size()>0){
				int count=0;
				for(KunnrManager kunnrManager:kunnrManagerList){
					kunnrManager.setStatus("D");
					kunnrManager.setUserId(Long.valueOf(this.getUser().getUserId()));
					kunnrManagerService.deleteKunnrManager(kunnrManager);
					count++;
				}
				this.setSuccessMessage("�ɹ�ɾ��"+count+"�����ݣ�");
			}else{
				this.setFailMessage("ɾ��ʧ�ܣ�");
			}
		} catch (Exception e) {
			this.setFailMessage("ɾ����������ϵ����Ա��");
			e.printStackTrace();
		}
		return RESULT_MESSAGE;
	}
	
	public String exportStockSafetyCsv() {
		OutputStream os = null;
		String report_name = "��ȫ��浼��ģ��";
		PrintWriter print = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			os = response.getOutputStream();
			response.reset();
			response.setContentType("application/csv;charset=gb18030");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(report_name.getBytes("GBK"), ("ISO8859-1"))
					+ ".csv\"");
			print = response.getWriter();
			StringBuffer linedata = new StringBuffer();
			linedata.append("�����̱��");
			linedata.append(",");
			linedata.append("����������");
			linedata.append(",");
			linedata.append("������������");
			linedata.append(",");
			linedata.append("��������");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (print != null) {
				try {
					print.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				print = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				os = null;
			}
		}
		return RESULT_MESSAGE;
	}
	
	public String importStockSafetyCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		Map<String,String> kunnrMap=new HashMap<String,String>();
		try {
			String rcs = "";
			String rcs2 = "";
			stockSafetyList = new ArrayList<StockSafety>();
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))  //�ж��Ƿ�ΪCSV�ļ�
						|| (end != null && (end.equals(".CSV")))) {
					String[] header = SuperCSV.getHeaderFromFile(new File( //��ȡͷ���ֶ�
							uploadFile.toString()));
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // ��ȡ��������
					for(int j=0;j<content.size();j++){
						String[] s=content.get(j);
						if(s.length<=header.length){
							if(s.length>4){
								this.setFailMessage("��" + (j + 2) + "����������ȷ.");
								return RESULT_MESSAGE;
							}
							
							if(StringUtils.isEmpty(s[0])){
								rcs = rcs + "��" + (j + 2) + "�о����̱��Ϊ��.</br>";
								continue;
							}if(StringUtils.isEmpty(s[1])){
								rcs = rcs + "��" + (j + 2) + "�о���������Ϊ��.</br>";
								continue;
							}else{
								if(s[0].length()<8){
									int length=8-s[0].length();
									for(int x=0;x<length;x++){
										s[0]="0"+s[0];
									}
								}
								
								//��ID�����Ʒ���MAP,�ӿ���֤�ٶ�
								String kunnrName=kunnrMap.get(s[0]);
								if(kunnrName==null){
									Kunnr kunnr=new Kunnr();
									kunnr.setKunnr(s[0].trim());
									kunnr.setName1(s[1].trim());
									kunnr.setStatus("1");
									kunnr.setPagination("false");
									int num=kunnrService.kunnrSearchCount(kunnr);
									kunnr.setStatus("2");
									int num_1=kunnrService.kunnrSearchCount(kunnr);
									if(num==0 && num_1==0){
										rcs = rcs + "��" + (j + 2) + "�о����̱��/���Ʋ���ȷ���߸þ������ѹػ�.</br>";
									}else{
										kunnrMap.put(s[0], s[1]);
									}
								}else{
									if(!kunnrName.equals(s[1])){
										rcs = rcs + "��" + (j + 2) + "�о����̱�Ż����Ʋ���ȷ.</br>";
									}
								}
							}
							
							if(StringUtils.isEmpty(s[2])){
								rcs = rcs + "��" + (j + 2) + "�ж����������ڲ���Ϊ�գ�</br>";
							}else if(StringUtils.isEmpty(s[3])){
								rcs = rcs + "��" + (j + 2) + "���������ڲ���Ϊ�գ�</br>";
							}else{
								StockSafety stockSafety=new StockSafety();
								stockSafety.setKunnr(s[0].trim());
								stockSafety.setOrderTime(Double.valueOf(s[2]));
								stockSafety.setExpressTime(Double.valueOf(s[3]));
								stockSafety.setUserId(this.getUser().getUserId());
								
								BigDecimal b1=new BigDecimal(Double.valueOf(s[2]));
								BigDecimal b2=new BigDecimal(Double.valueOf(s[3]));
								BigDecimal b3=b1.add(b2);
								stockSafety.setDay(Math.ceil(b3.multiply(new BigDecimal(1.5)).doubleValue()));
								String kunnr=kunnrManagerService.getKunagByKunnr(s[0]);
								
								if(StringUtils.isNotEmpty(kunnr)){
									stockSafety.setKunnr(kunnr);
								}
								
								stockSafetyList.add(stockSafety);
							}
								
							if (!"".equals(rcs)) {
								result.append(rcs.toString() + "</br>");
								rcs = "";
								continue;
							}
						}else{
							rcs = rcs + "��" + (j + 2)
									+ "����������.</br>";
							result.append(rcs.toString() + "</br>");
							rcs = "";
							
						}
					}
					int num=0;
					if (stockSafetyList.size() != 0
							&& result.toString().equals("")) {
						for(int j=0;j<stockSafetyList.size();j++){
							try {
								rcs2 = "";
								num+=kunnrManagerService.updateStockSafety(stockSafetyList.get(j));
								result1.setResult(true);
								result.append(rcs2.toString() + "</br>");
							} catch (Exception e) {
								rcs2 = rcs2 + "��" + (j + 2)
										+ "�����ݱ������ݿ�ʧ��.����ϵϵͳ����Ա.</br>";
								result.append(rcs2.toString() + "</br>");
							}
						}
					}
					if (result1.getResult()) {
						if(num==0){
							this.setSuccessMessage("����ɹ�����������Ϊ:"
									+ content.size() + "��");
						}else{
							this.setSuccessMessage("����ɹ�����������Ϊ:"
									+ content.size() + "��,��������"+num+"������");
						}
					} else if (!result.toString().equals("")) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br>"
								+ result.toString());
					} else if (!result1.getResult()) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br>"
								+ result.toString());
					}
				}
			}else{
				this.setFailMessage("�ļ�������");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			this.setFailMessage("����ʧ�ܣ����������Ƿ���ȷ��");
			e.printStackTrace();
			return RESULT_MESSAGE;
		}finally{
			ServletActionContext.getRequest().getSession()
			.setAttribute("DownLoad", "Over");
		}
		return RESULT_MESSAGE;
	}
	
	@PermissionSearch
	@JsonResult(field = "stockSafetyList",include = {"orgProvince","orgCity",
			"orgRegion","kunnr","kunnrName","orderTime","expressTime","day",
			"createDate","userId"},total="total")
	public String searchStockSafetyList(){
		stockSafety = new StockSafety();
		stockSafety.setKunnr(kunnr);
		stockSafety.setOrgId(orgId);
		stockSafety.setIsImportant(isImportant);
		stockSafety.setStart(getStart());
		stockSafety.setEnd(getEnd());
		total=kunnrManagerService.searchStockSafetyListCount(stockSafety);
		if(total>0){
		    stockSafetyList = kunnrManagerService.searchStockSafetyList(stockSafety);
		}
		return JSON;
	}
	
	public String deleteStockSafety(){
		try {
			if(stockSafetyList!=null && stockSafetyList.size()>0){
				for(StockSafety stockSafety:stockSafetyList){
					stockSafety.setStatus("D");
					stockSafety.setUserId(this.getUser().getUserId());
					kunnrManagerService.updateStockSafety(stockSafety);
				}
				this.setSuccessMessage("ɾ���ɹ���");
			}else{
				this.setFailMessage("ɾ��ʧ�ܣ�");
			}
		} catch (Exception e) {
			this.setFailMessage("ɾ����������ϵ����Ա��");
			e.printStackTrace();
		}
		return RESULT_MESSAGE;
	}
	
	public void exportForExcelStockSafety() throws Exception{
		ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Ing");
    	
		ServletActionContext.getRequest().getSession().setAttribute("exportExcel", "Y");
		
		stockSafety = new StockSafety();
		stockSafety.setKunnr(kunnr);
		stockSafety.setOrgId(orgId);
		stockSafety.setPagination("false");
		stockSafetyList = kunnrManagerService.searchStockSafetyList(stockSafety);
		
		String[] rowName={"����", "ʡ��", "����", "�����̱��", "������",
				"������������", "��������","�ϼƣ�������"};
		String[] colNames={"orgRegion","orgProvince","orgCity","kunnr","kunnrName",
				"orderTime","expressTime","day"};
		
		ExportExcelUtil ee=new ExportExcelUtil("��ȫ�����Ϣ", rowName, colNames, stockSafetyList);
		ee.export();

		ServletActionContext.getRequest().getSession().removeAttribute("exportExcel");
	    ServletActionContext.getRequest().getSession().setAttribute("DownLoad", "Over");
	}
	
	@PermissionSearch
	@JsonResult(field = "kunnrManagerList",include = {"orgProvince","orgCity",
			"orgRegion","kunnr","kunnrName","skuId","skuName","year","month","salesPlan",
			"salesPlanNext","estimateStockLast","estimateStock","stockSafety","stockSafetyNext",
			"salesTotal","productionPlan","productionPlanNext","productionPlanTotal","salesYear",
			"salesYearGoal"},total="total")
	public String searchProductionPlanList(){
		kunnrManager = new KunnrManager();
		kunnrManager.setOrgIds(orgIds);
		kunnrManager.setKunnr(kunnr);
		kunnrManager.setSkuId(skuId);
		kunnrManager.setStartDate(startDate);
		kunnrManager.setEndDate(endDate);
		kunnrManager.setStart(getStart());
		kunnrManager.setEnd(getEnd());
		total=kunnrManagerService.searchProductionPlanListCount(kunnrManager);
		if(total>0){
			kunnrManagerList = kunnrManagerService.searchProductionPlanList(kunnrManager);
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "kunnrManagerList",include = {"orgProvince","orgCity",
			"orgRegion","kunnr","kunnrName","skuId","skuName","skuVolume","skuPrice","isImportantSku",
			"stockWarning","salesPlanNextWeek","stockStandard","orderOnWay","putNotTakeOrder",
			"stockTotal","productNeed","productNeedVolume","productSum","productSumVolume",
			"notPutOrder","takeOrder","takeOrderPrice","stockStandardNextWeek","productNeedNextWeek",
			"productSumNextWeek","takeOrderPriceNextWeek","stockWarningTwoWeek","takeOrderFirst",
			"estimateStock"},total="total")
	public String searchProductionNeedList(){
		kunnrManager = new KunnrManager();
		kunnrManager.setOrgIds(orgIds);
		kunnrManager.setKunnr(kunnr);
		kunnrManager.setSkuId(skuId);
		kunnrManager.setStart(getStart());
		kunnrManager.setEnd(getEnd());
		total=kunnrManagerService.searchProductionNeedListCount(kunnrManager);
		if(total>0){
			kunnrManagerList = kunnrManagerService.searchProductionNeedList(kunnrManager);
		}
		return JSON;
	}

	public IKunnrManagerService getKunnrManagerService() {
		return kunnrManagerService;
	}

	public void setKunnrManagerService(IKunnrManagerService kunnrManagerService) {
		this.kunnrManagerService = kunnrManagerService;
	}

	public IKunnrService getKunnrService() {
		return kunnrService;
	}

	public void setKunnrService(IKunnrService kunnrService) {
		this.kunnrService = kunnrService;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public List<KunnrManager> getKunnrManagerList() {
		return kunnrManagerList;
	}

	public void setKunnrManagerList(List<KunnrManager> kunnrManagerList) {
		this.kunnrManagerList = kunnrManagerList;
	}

	public KunnrManager getKunnrManager() {
		return kunnrManager;
	}

	public void setKunnrManager(KunnrManager kunnrManager) {
		this.kunnrManager = kunnrManager;
	}

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public DateControl getDateControl() {
		return dateControl;
	}

	public void setDateControl(DateControl dateControl) {
		this.dateControl = dateControl;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(String isImportant) {
		this.isImportant = isImportant;
	}

	public List<StockSafety> getStockSafetyList() {
		return stockSafetyList;
	}

	public void setStockSafetyList(List<StockSafety> stockSafetyList) {
		this.stockSafetyList = stockSafetyList;
	}

	public StockSafety getStockSafety() {
		return stockSafety;
	}

	public void setStockSafety(StockSafety stockSafety) {
		this.stockSafety = stockSafety;
	}

}
