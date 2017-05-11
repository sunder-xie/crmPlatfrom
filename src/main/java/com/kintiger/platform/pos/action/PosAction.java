package com.kintiger.platform.pos.action;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.org.service.IOrgService;
import com.kintiger.platform.pos.pojo.Pos;
import com.kintiger.platform.pos.service.IPosService;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.crmdict.pojo.CrmDict;
import com.kintiger.platform.distributionData.pojo.DistributionData;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.framework.util.SuperCSV;

import com.kintiger.platform.org.pojo.Borg;
import com.kintiger.platform.org.service.IOrgService;
public class PosAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IPosService posService;
	public StringResult  stringresult = new StringResult();
	
	private static Log logger = LogFactory.getLog(PosAction.class);

	private List<Pos>  posLists; 
	
	
	
	private String uploadFileFileName;
	private File uploadFile;
	private String xmlFilePath;
	private IOrgService orgServiceHessian;

	private String matter;

	private String matterNum;
	
	private String[] ids;
	private String orgId;
	
	@Decode
	private String orgName;

	private String systemId;

	@Decode
	private String systemName;

	private String startDate;

	private String endDate;

	private int size;

	private String userId;
	private long pId;
	
	private String delId;
	

	
	public String searchPos() {
		userId = this.getUser().getUserId();
		Borg org = orgServiceHessian.getOrgByUserId(userId);
		orgId = org.getOrgId().toString();
		return "toSearchPos";
		
	}
	
	/**
	 * ��ѯĿ�� add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "posLists", total = "size", include = {"pId",
			 "systemId","systemName","orgCity", "inputDate","aOneLC","dOne","dTwo","dThree","bEight","cEight",
			"aOneXY","aOneQKL","aOneYW","aOneMX","aOneCM","aOneKF","bOne","cOne","aEightYW","aEightXY","inputName",
			"ygSale","totalSale","totalMoney"
			})
	public String getPosList() {
		posLists = new ArrayList<Pos>();
		Pos dGoal = new Pos();
		dGoal.setOrgId(this.getUser().getOrgId());
		if (StringUtils.isNotEmpty(orgId)) {
			dGoal.setOrgId(orgId);
		}
		if (StringUtils.isNotEmpty(orgName)) {
			dGoal.setOrgName(orgName);
		}
		if (StringUtils.isNotEmpty(systemId)) {
			dGoal.setSystemId(systemId);
		}
		if (StringUtils.isNotEmpty(systemName)) {
			dGoal.setSystemName(systemName);
		}
		if (StringUtils.isNotEmpty(startDate)) {
			dGoal.setStartDate(startDate);
		}
		if (StringUtils.isNotEmpty(endDate)) {
			dGoal.setEndDate(endDate);
		}

		dGoal.setStart(this.getStart());
		dGoal.setEnd(this.getEnd());
		size = posService.getPosCount(dGoal);
		if (size != 0) {
			posLists = posService
					.getPosList(dGoal);
		}
		return JSON;
	}

	
	/**
	 * ����CSVģ�� add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String exportMouldCsv() {
		OutputStream os = null;
		String report_name = "POS���ݵ���ģ��";
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
			linedata.append("��������");
			linedata.append(",");
			linedata.append("ϵͳ����");
			linedata.append(",");
			linedata.append("�����·�");
			linedata.append(",");
			linedata.append("����");
			linedata.append(",");
			linedata.append("�ɿ���");
			linedata.append(",");
			linedata.append("ԭζ");
			linedata.append(",");
			linedata.append("����");
			linedata.append(",");
			linedata.append("����");
			linedata.append(",");
			linedata.append("��ݮ");
			linedata.append(",");
			linedata.append("�̲�");
			linedata.append(",");
			linedata.append("�춹");
			linedata.append(",");
			linedata.append("��Բ����");
			linedata.append(",");	
			linedata.append("ԭζ������");
			linedata.append(",");
			linedata.append("����������");
			linedata.append(",");
			linedata.append("�춹������");
			linedata.append(",");
			linedata.append("��Բ����������");
			linedata.append(",");
			linedata.append("Ҭ����ͥ����װ");
			linedata.append(",");
			linedata.append("�춹��ͥ����װ");
			linedata.append(",");
			linedata.append("��ƮƮ���Ҭ���̲�");
			linedata.append(",");
			linedata.append("��������");
			linedata.append(",");
			linedata.append("�ܱ���");
			linedata.append(",");
			linedata.append("�����۽��");
			linedata.append("\n");
			if (linedata.length() > 0) {
				print.write(linedata.toString());
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (print != null) {
				try {
					print.close();
				} catch (Exception e) {
					logger.error(e);
				}
				print = null;
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					logger.error(e);
				}
				os = null;
			}
		}
		return RESULT_MESSAGE;
	}

	public String orgTreePage() {
		userId = this.getUser().getUserId();
		Borg borg = orgServiceHessian.getOrgByUserId(userId);
		if ("B".equals(borg.getOrgCity())) {
			return "orgAllTree";
		}
		return "orgtree";
	}
	/*
	 * ����pos����
	 * */
	
	@SuppressWarnings("unused")
	public String importPosCsv() {
		StringBuffer result = new StringBuffer();
		BooleanResult result1 = new BooleanResult();
		try {
			posLists = new ArrayList<Pos>();
			String rcs = "";
			String rcs2 = "";
			if (StringUtils.isNotEmpty(uploadFileFileName)) {
				String end = StringUtils.substring(uploadFileFileName,
						StringUtils.lastIndexOf(uploadFileFileName, '.'));
				if ((end != null && (end.equals(".csv")))
						|| (end != null && (end.equals(".CSV")))) {
					String[] header = SuperCSV.getHeaderFromFile(new File(
							uploadFile.toString()));
					List<String[]> content = SuperCSV
							.getContentFromFile(new File(uploadFile.toString())); // ��ȡString����
					for (int j = 0; j < content.size(); j++) {
					Pos pGoal1 = new Pos();
						String[] s = content.get(j);
						if (s.length == header.length) {
							int ij;
							rcs = "";
							if (s.length > 37) {
								this.setFailMessage("��" + (j + 2) + "����������ȷ.");
								return RESULT_MESSAGE;
							}
							ij = 0;
							String org_city = s[ij++];
							String systemName = s[ij++];
							String input_date = s[ij++];
							String aOneXY = s[ij++] ;
							String aOneQKL = s[ij++];
							String aOneYW = s[ij++];
							String aOneMX = s[ij++];
							String aOneCM = s[ij++];
							String aOneLC = s[ij++];//�̲�
							String aOneKF = s[ij++];
							String bOne = s[ij++];
							String cOne = s[ij++];
							String aEightYW = s[ij++];
							String aEightXY = s[ij++];
							String bEight = s[ij++];//�춹������
							String cEight = s[ij++];//��Բ����������
							String dOne = s[ij++];//Ҭ����ͥ����װ
							String dTwo = s[ij++];//�춹��ͥ����װ
							String dThree = s[ij++];//��ƮƮ���Ҭ���̲�
					
							String ygSale = s[ij++];
//							String ygMoney= s[ij++];
//							String hdSale= s[ij++];
//							String hdMoney= s[ij++];
							String totalSale= s[ij++];
							String totalMoney= s[ij++];

							Pos dG = new Pos();
							dG.setSystemName(systemName);
							dG.setInputDate(input_date);
							dG.setOrgCity(org_city);
							if (null != org_city) {
										Pos org = posService
												.getOrgByOrgName(org_city);
										if (org == null) {
											rcs = rcs + "��" + (j + 2)
													+ "�г���  ��" + org_city
													+ " ����ȷ.</br>";
										} else if (org.getOrgId() != null) {											
												pGoal1.setOrgId(org.getOrgId());
												pGoal1.setOrgCity(org_city);
//												System.out.print(pGoal1.getOrgId());
//												System.out.print(pGoal1.getOrgCity());

											}
										 else {
											rcs = rcs + "��" + (j + 2)
													+ "�г���  ��" + org_city
													+ " ����ȷ.</br>";
										}
									} else {
										rcs = rcs + "��" + (j + 2)
												+ "�г���: Ϊ��.</br>";
									}
							
							if (null != systemName) {
								Pos sys = posService
										.getSystemBySystemName(systemName);
								if (sys == null) {
									rcs = rcs + "��" + (j + 2)
											+ "��ϵͳ  ��" + systemName
											+ " ����ȷ.</br>";
								} else if (sys.getSystemId() != null) {											
										pGoal1.setSystemId(sys.getSystemId());
										pGoal1.setSystemName(systemName);
//										System.out.print(pGoal1.getSystemId());
//										System.out.print(pGoal1.getSystemName());
									}
								 else {
									rcs = rcs + "��" + (j + 2)
											+ "��ϵͳ  ��" + org_city
											+ " ����ȷ.</br>";
								}
							} else {
								rcs = rcs + "��" + (j + 2)
										+ "��ϵͳ: Ϊ��.</br>";
							}
							
							if (null != input_date) {
								String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]? ((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
								Pattern p = Pattern.compile(eL);
								Matcher ma = p.matcher(input_date);
								boolean flag = ma.matches();
								if (flag) {
									input_date = this.dateChange(input_date);
									pGoal1.setInputDate(input_date);
								} else {
									rcs = rcs
											+ "��"
											+ (j + 2)
											+ "�����ڸ�ʽ����,�밴��2014/01/01��2014-01-01��ʽ��д.</br>";
								}
							} else {
								rcs = rcs + "��" + (j + 2) + "������: Ϊ��.</br>";
							}
								Pos pGoal = new Pos();
//								System.out.print(content.size()+"\n");
								pGoal.setUserId(this.getUser().getUserId());
								pGoal.setOrgId(pGoal1.getOrgId());
								pGoal.setOrgCity(org_city);
								pGoal.setSystemId(pGoal1.getSystemId());
								pGoal.setSystemName(systemName);
								pGoal.setInputDate(pGoal1.getInputDate());
								pGoal.setInputName(this.getUser().getUserName());
								pGoal.setaEightXY(aEightXY);
								pGoal.setaEightYW(aEightYW);
								pGoal.setaOneCM(aOneCM);
								pGoal.setaOneKF(aOneKF);
								pGoal.setaOneMX(aOneMX);
								pGoal.setaOneQKL(aOneQKL);
								pGoal.setaOneXY(aOneXY);
								pGoal.setaOneYW(aOneYW);
								pGoal.setbOne(bOne);
								pGoal.setcOne(cOne);
								pGoal.setYgSale(ygSale);
								pGoal.setTotalSale(totalSale);
								pGoal.setTotalMoney(totalMoney);
								pGoal.setaOneLC(aOneLC);
								pGoal.setbEight(bEight);
								pGoal.setcEight(cEight);
								pGoal.setdOne(dOne);
								pGoal.setdTwo(dTwo);
								pGoal.setdThree(dThree);
								posLists.add(pGoal);	
															
					}else {
							rcs = rcs + "��" + (j + 2) + "���������ԣ�</br>";
						}
						rcs2 = rcs2 + rcs;
				}	
					if (!"".equals(rcs2)) {
						result.append(rcs2.toString() + "</br>");
						this.setFailMessage(result.toString());
						return RESULT_MESSAGE;
					} else {
						if (posLists.size() != 0
								&& result.toString().equals("")) {
							for (int q = 0; q < posLists.size(); q++) {

								try {
									rcs2 = "";
									result1 = posService
											.insertPosData(posLists
													.get(q));
									rcs2 += result1.getCode();
									result.append(rcs2.toString() + "</br>");
								} catch (Exception e) {
									logger.error(e);
									rcs2 += "��" + (q + 2)
											+ "�����ݱ������ݿ�ʧ��.����ϵϵͳ����Ա.</br>";
									result.append(rcs2.toString() + "</br>");
								}
							}
						} else {

							this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br></br>"
									+ rcs2.toString());
							return RESULT_MESSAGE;
						}
					}
					this.getSession().setAttribute("posLists",
							posLists);
					if (result1.getResult()) {
						this.setSuccessMessage("����ɹ�����������Ϊ:" + content.size()
								+ "��");
					} else if (!result1.getResult()) {
						this.setFailMessage("����ʧ��</br></br>  ��ش�����Ϣ��</br></br></br>"
								+ result.toString());
					}
					return RESULT_MESSAGE;
				}
						
			}else {// �ļ�������
				this.setFailMessage("�ļ�������");
				return RESULT_MESSAGE;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return RESULT_MESSAGE;
	}
						
	
	/**
	 * ҳ��ɾ��Ŀ��add by allen
	 * 
	 * @return
	 */
	@PermissionSearch
	public String deletePos() {
		this.setSuccessMessage("�����ɹ���");
		System.out.println(delId);
		ids =  delId.split(",");
		for(int i=0;i<ids.length;i++){
			Pos deletePos = new Pos();
			deletePos.setpId(Long.parseLong(ids[i]));
			deletePos.setpState("D");		
			BooleanResult booleanResult = posService.updatePos(deletePos);
			if (!booleanResult.getResult()) {
			// stringResult.setResult("����ʧ��");
			// stringResult.setCode(booleanResult.getCode());
			this.setFailMessage("����ʧ��!");
			}
		}
		return RESULT_MESSAGE;
	}
	
	public String dateChange(String date) {
		String dateRen = null;
		String year = "";
		String month = "";
		String day = "";
		if (date.indexOf("-") != -1) {
			String[] date_arr1 = date.split("\\-");
			year = date_arr1[0];
			if (date_arr1[1].length() == 1)
				month = "0" + date_arr1[1];
			else
				month = date_arr1[1];
			if (date_arr1[2].length() == 1)
				day = "0" + date_arr1[2];
			else
				day = date_arr1[2];
			dateRen = year + "-" + month + "-" + day;

		} else if (date.indexOf("/") != -1) {
			String[] date_arr2 = date.split("\\/");
			year = date_arr2[0];
			if (date_arr2[1].length() == 1)
				month = "0" + date_arr2[1];
			else
				month = date_arr2[1];
			if (date_arr2[2].length() == 1)
				day = "0" + date_arr2[2];
			else
				day = date_arr2[2];
			dateRen = year + "-" + month + "-" + day;
		}
		return dateRen;
	}
	
	public IPosService getPosService() {
		return posService;
	}

	public void setPosService(IPosService posService) {
		this.posService = posService;
	}

	public List<Pos> getPosLists() {
		return posLists;
	}

	public void setPosLists(List<Pos> posLists) {
		this.posLists = posLists;
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

	public String getXmlFilePath() {
		return xmlFilePath;
	}

	public void setXmlFilePath(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public IOrgService getOrgServiceHessian() {
		return orgServiceHessian;
	}

	public void setOrgServiceHessian(IOrgService orgServiceHessian) {
		this.orgServiceHessian = orgServiceHessian;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public String getMatterNum() {
		return matterNum;
	}

	public void setMatterNum(String matterNum) {
		this.matterNum = matterNum;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getSystemId() {
		return systemId;
	}


	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}


	public String getSystemName() {
		return systemName;
	}


	public void setSystemName(String systemName) {
		this.systemName = systemName;
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


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

	 public String getDelId() {
		return delId;
	}

	public void setDelId(String delId) {
		this.delId = delId;
	}

	public void addActionError(String anErrorMessage){
		 String s=anErrorMessage;
		 System.out.println(s);
		 }
		 public void addActionMessage(String aMessage){
		 String s=aMessage;
		 System.out.println(s);
		
		 }
		 public void addFieldError(String fieldName, String errorMessage){
		 String s=errorMessage;
		 String f=fieldName;
		 System.out.println(s);
		 System.out.println(f);
		
		 }


}
