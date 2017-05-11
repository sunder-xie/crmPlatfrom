package com.kintiger.platform.citydict.action;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.citydict.pojo.CityDict;
import com.kintiger.platform.citydict.service.ICityDictService;
import com.kintiger.platform.dict.pojo.CmsTbDict;
import com.kintiger.platform.dict.pojo.CmsTbDictType;
import com.kintiger.platform.dict.service.IDictService;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;

public class CityDictAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3341088842720942210L;
	private static final Log logger = LogFactory
	.getLog(CityDictAction.class);
	
	private ICityDictService cityDictService;
	//private IQuestionService questionService;
	
	private IDictService dictServiceHessian; // �ֵ�ӿ�
	
	private List<CityDict> citylist=new ArrayList<CityDict>();
	private List<CityDict> citydictlist;
	@Decode
	private String countrytype; //����
	
	@Decode
	//private String city_fp_flag;
	
	private CityDict city;
	@Decode
	private String parent_city_id;
	private int total;
	@Decode
	private String cityName;
	@Decode
	private String parent_city_name;
	private String citylevel;
	private Long dictId;
	private String  todictId;
	private List<CmsTbDict> dictlist = new ArrayList<CmsTbDict>();
	private List<CmsTbDict> dicts = new ArrayList<CmsTbDict>();

	private String ids;
	
	
	private String cityFpFlag;
	private String cityType;
	/**
	 * 
	 * ����ҳ��
	 */
	@PermissionSearch
	public String searchCityDict() {
		
		return "searchCityDict";
	}
	
	/**
	 * 
	 * ���봴��ҳ��
	 */
	@PermissionSearch
	public String createCityDict() {
		
		return "createCityDict";
	}
	/**
	 * 
	 * ���봴��ҳ��
	 */
	@PermissionSearch
	public String editCityDict() {
		CityDict cityDict = new CityDict();
		cityDict.setDictId(dictId);
		cityDict.setStart(getStart());
		cityDict.setEnd(10);
		city=cityDictService.getCityDictList(cityDict).get(0);
		return "editCityDict";
	}
	public String updateCityDict() {
		if (StringUtils.isNotEmpty(parent_city_name)
				&& StringUtils.isNotEmpty(parent_city_name.trim())) {
			city.setParent_city_name(parent_city_name);
		}
		int rel=cityDictService.updateCityDict(city);
		if(rel>0){
			this.setSuccessMessage("�޸���Ϣ�ɹ�");
		}else{
			this.setFailMessage("�޸���Ϣʧ��");
		}
		return RESULT_MESSAGE;
	}
	public String saveCityDict() {
		if (StringUtils.isNotEmpty(parent_city_name)
				&& StringUtils.isNotEmpty(parent_city_name.trim())) {
			city.setParent_city_name(parent_city_name);
		}
			
		if (!validate(city)) {
			this.setFailMessage("ע�����Ϣ����ȷ");
			return RESULT_MESSAGE;
		}
		int level=Integer.parseInt(city.getCitylevel());
		city.setCitylevel(""+(level+1));
		Long result = cityDictService.insertCityDict(city);
		int result1=Integer.parseInt(result.toString());
		if (result1>0) {
			this.setSuccessMessage("�����ɹ�  ��������idΪ��"+result1);
		}else{
			this.setFailMessage("����ʧ��");
		}
		return RESULT_MESSAGE;
	}
	@PermissionSearch
	@JsonResult(field = "citydictlist", include = { "dictId", "cityName" ,"cityNumber"})
	public String blurSearchCityDict() {
		citydictlist = new ArrayList<CityDict>();
		CityDict cityDict = new CityDict();
		if (StringUtils.isNotEmpty(parent_city_name)
				&& StringUtils.isNotEmpty(parent_city_name.trim())) {
			try {
				parent_city_name = new String(getServletRequest().getParameter("parent_city_name")
						.getBytes("ISO8859-1"), "UTF-8");
				citylevel = new String(getServletRequest().getParameter("citylevel")
						.getBytes("ISO8859-1"), "UTF-8"); 
				cityDict.setCityName(parent_city_name.trim());
				cityDict.setCitylevel(citylevel);
				citylist = cityDictService.getCityList(cityDict);
				for(CityDict c:citylist){
					c.setCityName(c.getCityName()+" ���ţ�"+c.getCityNumber());
					citydictlist.add(c);
				}
			} catch (UnsupportedEncodingException e) {
				e.getMessage();
			}
		}
		return JSON;
	}
	
	@PermissionSearch
	@JsonResult(field = "citydictlist", include = { "dictId", "cityName" ,"cityNumber"})
	public String searchCityDictType() {
		citydictlist = new ArrayList<CityDict>();
		CityDict cityDict = new CityDict();
		if (StringUtils.isNotEmpty(citylevel)
				&& StringUtils.isNotEmpty(citylevel.trim())) {
			try {
				citylevel = new String(getServletRequest().getParameter("citylevel")
						.getBytes("ISO8859-1"), "UTF-8"); 
				cityDict.setCitylevel(citylevel);
				if (StringUtils.isNotEmpty(parent_city_id)
						&& StringUtils.isNotEmpty(parent_city_id.trim())) {
					parent_city_id = new String(getServletRequest().getParameter("parent_city_id")
							.getBytes("ISO8859-1"), "UTF-8"); 
					cityDict.setParent_city_id(parent_city_id);
				}
				
				citydictlist = cityDictService.getCityList(cityDict);
			} catch (UnsupportedEncodingException e) {
				e.getMessage();
			}
		}
		return JSON;
	}
	@PermissionSearch
	@JsonResult(field = "citylist", include = { "dictId", "cityNumber", "cityName",
			"citylevel", "parent_city_id","city_org_id","city_market_level","city_fp_flag", "state","parent_city_name","city_abbreviation" }, total = "total")
	public String searchCityDictListJson() {
		CityDict	city=new CityDict();
		city.setStart(this.getStart());
		city.setEnd(getEnd());
		if (StringUtils.isNotEmpty(cityName) && StringUtils.isNotEmpty(cityName.trim())) {
			city.setCityName(cityName);
		}
		if (StringUtils.isNotEmpty(parent_city_id) && StringUtils.isNotEmpty(parent_city_id.trim())) {
			city.setParent_city_id(parent_city_id);
		}
		if (StringUtils.isNotEmpty(todictId) && StringUtils.isNotEmpty(todictId.trim())) {
			city.setDictId(Long.parseLong(todictId));
		}
		if (StringUtils.isNotEmpty(citylevel) && StringUtils.isNotEmpty(citylevel.trim())) {
			city.setCitylevel(citylevel);
		}
		
		//city.setCitylevel(Integer.parseInt(citylevel));
		total=cityDictService.getCityDictCount(city);
		if (total != 0) {
		citylist = cityDictService.getCityDictList(city);
		} else {
			citylist = null;
		}
		return JSON;
	}
	
	public String deleteCityDicts(){
		this.setSuccessMessage("");
		this.setFailMessage("");
		CityDict  cityDict=new CityDict();
		String[] ls = ids.split(",");
		for(int i=0;i<ls.length;i++){
			cityDict.setCodes(ls);
		}
		cityDict.setStart(0);
		cityDict.setEnd(10);
		List<CityDict> citys=cityDictService.getCityDictList(cityDict);
		if(citys.size()>0){
			this.setFailMessage("ɾ��ʧ�ܣ� �������Ӽ�");
			return RESULT_MESSAGE;
		}
		int c=cityDictService.deleteCityDict(cityDict);
		if(c>0){
			this.setSuccessMessage("ɾ���ɹ���");
			
		}else{
			this.setFailMessage("ɾ��ʧ�ܣ�");
		}
		
		return RESULT_MESSAGE;
	}
///***
// * 
// * �ı�״̬	
// * @return
// */
//	public String updateCityDicts(){
//		this.setSuccessMessage("");
//		this.setFailMessage("");
//		CityDict  cityDict=new CityDict();
//		String[] ls = ids.split(",");
//		for(int i=0;i<ls.length;i++){
//			cityDict.setCodes(ls);
//		}
//		cityDict.setCity_fp_flag(city_fp_flag);
//		int c=cityDictService.updateCityDictDtype(cityDict);
//		if(c>0){
//			this.setSuccessMessage("����״̬�޸ĳɹ���");
//		}else{
//			this.setFailMessage("����״̬�޸ĳɹ���");
//		}
//		
//		return RESULT_MESSAGE;
//	}
	
	/**
	 * �����ȼ�����
	 * 
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "dictlist", include = { "itemName", "itemValue" })
	public String getDicttype() {
		CmsTbDictType cm= new CmsTbDictType();
		cm.setDictTypeId(Long.valueOf(cityType));
		dictlist=dictServiceHessian.getDictListByDictType(cm);
		return JSON;
	}
	
	/**
	 * ״̬����
	 * @return
	 */
	@PermissionSearch
	@JsonResult(field = "dicts", include = { "itemName", "itemValue" })
	public String getCity_fp_flag() {
		CmsTbDictType cm= new CmsTbDictType();
		cm.setDictTypeId(Long.valueOf(cityFpFlag));
		dicts=dictServiceHessian.getDictListByDictType(cm);
		return JSON;
	}
	private boolean validate(CityDict cityDict) {

		if (cityDict == null) {
			return false;
		}
		if ( StringUtils.isEmpty(cityDict.getCityName())
				|| StringUtils.isEmpty(cityDict.getCityName().trim())||StringUtils.isEmpty(cityDict.getParent_city_name())
				|| StringUtils.isEmpty(cityDict.getParent_city_name().trim())||StringUtils.isEmpty(cityDict.getParent_city_number())
				|| StringUtils.isEmpty(cityDict.getParent_city_number().trim())||StringUtils.isEmpty(cityDict.getCityNumber())
				|| StringUtils.isEmpty(cityDict.getCityNumber().trim())) {
			return false;
		}

		return true;
	}
	
	
	/**
	 * ��ϸ
	 * 
	 * @return
	 */
	@PermissionSearch
	public String excelCityDicts() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		CityDict	cityDict=new CityDict();
		cityDict.setStart(this.getStart());
		cityDict.setEnd(getEnd());
		if (StringUtils.isNotEmpty(cityName) && StringUtils.isNotEmpty(cityName.trim())) {
			cityDict.setCityName(cityName);
		}
		if (StringUtils.isNotEmpty(parent_city_id) && StringUtils.isNotEmpty(parent_city_id.trim())) {
			cityDict.setParent_city_id(parent_city_id);
		}
		if (StringUtils.isNotEmpty(todictId) && StringUtils.isNotEmpty(todictId.trim())) {
			cityDict.setDictId(Long.parseLong(todictId));
		}
		if (StringUtils.isNotEmpty(citylevel) && StringUtils.isNotEmpty(citylevel.trim())) {
			cityDict.setCitylevel(citylevel);
		}

		citylist = cityDictService.getCityListExcel(cityDict);
		if (null == citylist) {
			this.setFailMessage("û�����ݿɵ�����");
		} else {
			// 
			for (int i = 0; i < citylist.size(); i++) {
				CityDict con = new CityDict();
				con = citylist.get(i);
				
					if ("T".equals(con.getCity_fp_flag())) {
						con.setCity_fp_flag("��ͨ");
					}else{
						con.setCity_fp_flag("δ��ͨ");
					}
					if("0".equals(con.getCitylevel())){
						con.setCitylevel("ʡ");
						}else if("1".equals(con.getCitylevel())){
							con.setCitylevel("��");
						}else if("2".equals(con.getCitylevel())){
						
							con.setCitylevel("������");
						}else if("3".equals(con.getCitylevel())){
							con.setCitylevel("����");
						}else {
							con.setCitylevel("����");
					}
					citylist.set(i, con);
			}
			try {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				// ȡ�������
				os = response.getOutputStream();
				// ��������
				response.reset();
				// �趨����ļ�ͷ

				response.setHeader(
						"Content-Disposition",
						"attachment; filename=\""
								+ new String("����������".getBytes("GBK"),
										("ISO8859-1")) + ".xls\"");
				// �����������

				response.setContentType("application/msexcel");
				// ����excel�ļ�
				wbook = Workbook.createWorkbook(os);
				WritableSheet wsheet = wbook.createSheet("sheet1", 0);
				WritableCellFormat cellFormat = new WritableCellFormat();
				cellFormat.setAlignment(jxl.format.Alignment.CENTRE);

				// WritableSheet.setColumnView(int i,int width);
				// ������ָ����i+1�еĿ�ȣ����磺
				// ����һ�еĿ����Ϊ30
				// sheet.setColumnView(0,30);
				// wsheet.setRowView(0,10);
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
						WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
						jxl.format.Colour.DARK_RED);
				WritableCellFormat cellFormat_top = new WritableCellFormat();
				cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
				// ���õ�Ԫ�񱳾���ɫ
				cellFormat_top.setBackground(Colour.LIGHT_BLUE);
				// ���������ʽ
				cellFormat_top.setFont(font1);
				cellFormat_top.setBorder(jxl.format.Border.ALL,
						jxl.format.BorderLineStyle.THIN);

				WritableCellFormat cellFormat_bottom = new WritableCellFormat();
				cellFormat_bottom.setBorder(jxl.format.Border.ALL,
						jxl.format.BorderLineStyle.THIN);

				WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
				// ���þ���
				cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
				// ���ñ��߿�
				cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
						jxl.format.BorderLineStyle.THIN);

				// ��ͷ
				Label label_0 = new Label(0, 0, "����");
				label_0.setCellFormat(cellFormat_top);
				wsheet.addCell(label_0);

				Label label_1 = new Label(1, 0, "ʡ");
				label_1.setCellFormat(cellFormat_top);
				wsheet.addCell(label_1);
				wsheet.setColumnView(1, 20);

				Label label_2 = new Label(2, 0, "��");
				label_2.setCellFormat(cellFormat_top);
				wsheet.addCell(label_2);

				Label label_3 = new Label(3, 0, "����");
				label_3.setCellFormat(cellFormat_top);
				wsheet.addCell(label_3);
				wsheet.setColumnView(3, 20);
				
				Label label_4 = new Label(4, 0, "����");
				label_4.setCellFormat(cellFormat_top);
				wsheet.addCell(label_4);

				Label label_5 = new Label(5, 0, "�������");
				label_5.setCellFormat(cellFormat_top);
				wsheet.addCell(label_5);

				Label label_6 = new Label(6, 0, "�Ƿ�ͨ����");
				label_6.setCellFormat(cellFormat_top);
				wsheet.addCell(label_6);

				Label label_7 = new Label(7, 0, "�����ȼ�");
				label_7.setCellFormat(cellFormat_top);
				wsheet.addCell(label_7);
				for (int x = 0; x < citylist.size(); x++) {
					CityDict con = new CityDict();
					con = citylist.get(x);
					Label label_x_0 = null;
					Label label_x_1= null;
					Label label_x_2= null;
					Label label_x_3= null;
					Label label_x_4= null;
					if(con.getCitylevel().equals("����")){
						label_x_0 = new Label(0, x + 1, con.getCityName());
						label_x_1 = new Label(1, x + 1, con.getParent_city_name3());
						label_x_2 = new Label(2, x + 1, con.getParent_city_name3());
						label_x_3 = new Label(3, x + 1, con.getParent_city_name3());
						label_x_4 = new Label(4, x + 1, con.getParent_city_name3());
					}else if(con.getCitylevel().equals("ʡ")){
						label_x_0 = new Label(0, x + 1, con.getParent_city_name());
						label_x_1 = new Label(1, x + 1, con.getCityName());
						label_x_2 = new Label(2, x + 1, con.getParent_city_name3());
						label_x_3 = new Label(3, x + 1, con.getParent_city_name3());
						label_x_4 = new Label(4, x + 1, con.getParent_city_name3());
					}else if(con.getCitylevel().equals("��")){
						label_x_0 = new Label(0, x + 1, con.getParent_city_name1());
						label_x_1 = new Label(1, x + 1, con.getParent_city_name());
						label_x_2 = new Label(2, x + 1, con.getCityName());
						label_x_3 = new Label(3, x + 1, con.getParent_city_name3());
						label_x_4 = new Label(4, x + 1, con.getParent_city_name3());
					}else if(con.getCitylevel().equals("������")){
						label_x_0 = new Label(0, x + 1, con.getParent_city_name2());
						label_x_1 = new Label(1, x + 1, con.getParent_city_name1());
						label_x_2 = new Label(2, x + 1, con.getParent_city_name());
						label_x_3 = new Label(3, x + 1, con.getCityName());
						label_x_4 = new Label(4, x + 1, con.getParent_city_name3());
					}else if(con.getCitylevel().equals("����")){
						label_x_0 = new Label(0, x + 1, con.getParent_city_name3());
						label_x_1 = new Label(1, x + 1, con.getParent_city_name2());
						label_x_2 = new Label(2, x + 1, con.getParent_city_name1());
						label_x_3 = new Label(3, x + 1, con.getParent_city_name());
						label_x_4 = new Label(4, x + 1, con.getCityName());
					}
					
					// �������
//					 label_x_0 = new Label(0, x + 1, con.getParent_city_name3());
					label_x_0.setCellFormat(cellFormat_bottom_1);
					wsheet.addCell(label_x_0);
					
//					 label_x_1 = new Label(1, x + 1,con.getParent_city_name2());
					label_x_1.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_1);
					
//					 label_x_2 = new Label(2, x + 1, con.getParent_city_name1());
					label_x_2.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_2);

//					 label_x_3 = new Label(3, x + 1, con.getParent_city_name());
					label_x_3.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_3);
					
//					 label_x_4 = new Label(4, x + 1, con.getCityName());
					label_x_4.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_4);
					
					Label label_x_5 = new Label(5, x + 1, con.getCityNumber());
					label_x_5.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_5);

					
					Label label_x_6 = new Label(6, x + 1,
							con.getCity_fp_flag());
					label_x_6.setCellFormat(cellFormat_bottom_1);
					wsheet.addCell(label_x_6);

					Label label_x_7 = new Label(7, x + 1, con.getCitylevel());
					label_x_7.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_7);
				}
				wbook.write();
			} catch (Exception e) {
				logger.error(e);
			} finally {
				if (wbook != null) {
					try {
						wbook.close();
					} catch (Exception e) {
						logger.error(e);
					}
					wbook = null;
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

		}
		return RESULT_MESSAGE;
	}
	/**
	 * ��ϸ
	 * 
	 * @return
	 */
	@PermissionSearch
	public String excelCity4Dicts() {
		OutputStream os = null;
		WritableWorkbook wbook = null;
		citylist = cityDictService.getCity4ListExcel();
		if (null == citylist) {
			this.setFailMessage("û�����ݿɵ�����");
		} else {
			// 
			for (int i = 0; i < citylist.size(); i++) {
				CityDict con = new CityDict();
				con = citylist.get(i);
				
					if ("T".equals(con.getCity_fp_flag())) {
						con.setCity_fp_flag("��ͨ");
					}else{
						con.setCity_fp_flag("δ��ͨ");
					}
					if("0".equals(con.getCitylevel())){
						con.setCitylevel("ʡ");
						}else if("1".equals(con.getCitylevel())){
							con.setCitylevel("��");
						}else if("2".equals(con.getCitylevel())){
						
							con.setCitylevel("������");
						}else if("3".equals(con.getCitylevel())){
							con.setCitylevel("����");
						}else {
							con.setCitylevel("����");
					}
					citylist.set(i, con);
			}
			try {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				// ȡ�������
				os = response.getOutputStream();
				// ��������
				response.reset();
				// �趨����ļ�ͷ

				response.setHeader(
						"Content-Disposition",
						"attachment; filename=\""
								+ new String("����������".getBytes("GBK"),
										("ISO8859-1")) + ".xls\"");
				// �����������

				response.setContentType("application/msexcel");
				// ����excel�ļ�
				wbook = Workbook.createWorkbook(os);
				WritableSheet wsheet = wbook.createSheet("sheet1", 0);
				WritableCellFormat cellFormat = new WritableCellFormat();
				cellFormat.setAlignment(jxl.format.Alignment.CENTRE);

				// WritableSheet.setColumnView(int i,int width);
				// ������ָ����i+1�еĿ�ȣ����磺
				// ����һ�еĿ����Ϊ30
				// sheet.setColumnView(0,30);
				// wsheet.setRowView(0,10);
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 10,
						WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
						jxl.format.Colour.DARK_RED);
				WritableCellFormat cellFormat_top = new WritableCellFormat();
				cellFormat_top.setAlignment(jxl.format.Alignment.CENTRE);
				// ���õ�Ԫ�񱳾���ɫ
				cellFormat_top.setBackground(Colour.LIGHT_BLUE);
				// ���������ʽ
				cellFormat_top.setFont(font1);
				cellFormat_top.setBorder(jxl.format.Border.ALL,
						jxl.format.BorderLineStyle.THIN);

				WritableCellFormat cellFormat_bottom = new WritableCellFormat();
				cellFormat_bottom.setBorder(jxl.format.Border.ALL,
						jxl.format.BorderLineStyle.THIN);

				WritableCellFormat cellFormat_bottom_1 = new WritableCellFormat();
				// ���þ���
				cellFormat_bottom_1.setAlignment(jxl.format.Alignment.CENTRE);
				// ���ñ��߿�
				cellFormat_bottom_1.setBorder(jxl.format.Border.ALL,
						jxl.format.BorderLineStyle.THIN);

				// ��ͷ
				Label label_0 = new Label(0, 0, "����");
				label_0.setCellFormat(cellFormat_top);
				wsheet.addCell(label_0);

				Label label_1 = new Label(1, 0, "ʡ");
				label_1.setCellFormat(cellFormat_top);
				wsheet.addCell(label_1);
				wsheet.setColumnView(1, 20);

				Label label_2 = new Label(2, 0, "��");
				label_2.setCellFormat(cellFormat_top);
				wsheet.addCell(label_2);

				Label label_3 = new Label(3, 0, "����");
				label_3.setCellFormat(cellFormat_top);
				wsheet.addCell(label_3);
				wsheet.setColumnView(3, 20);
				
//				Label label_4 = new Label(4, 0, "����");
//				label_4.setCellFormat(cellFormat_top);
//				wsheet.addCell(label_4);

				Label label_5 = new Label(4, 0, "�������");
				label_5.setCellFormat(cellFormat_top);
				wsheet.addCell(label_5);

				Label label_6 = new Label(5, 0, "�Ƿ�ͨ����");
				label_6.setCellFormat(cellFormat_top);
				wsheet.addCell(label_6);

				Label label_7 = new Label(6, 0, "�����ȼ�");
				label_7.setCellFormat(cellFormat_top);
				wsheet.addCell(label_7);
				for (int x = 0; x < citylist.size(); x++) {
					CityDict con = new CityDict();
					con = citylist.get(x);
					Label label_x_0 = null;
					Label label_x_1= null;
					Label label_x_2= null;
					Label label_x_3= null;
					if(con.getCitylevel().equals("����")){
						label_x_0 = new Label(0, x + 1, con.getCityName());
						label_x_1 = new Label(1, x + 1, con.getParent_city_name3());
						label_x_2 = new Label(2, x + 1, con.getParent_city_name3());
						label_x_3 = new Label(3, x + 1, con.getParent_city_name3());
					}else if(con.getCitylevel().equals("ʡ")){
						label_x_0 = new Label(0, x + 1, con.getParent_city_name());
						label_x_1 = new Label(1, x + 1, con.getCityName());
						label_x_2 = new Label(2, x + 1, con.getParent_city_name3());
						label_x_3 = new Label(3, x + 1, con.getParent_city_name3());
					}else if(con.getCitylevel().equals("��")){
						label_x_0 = new Label(0, x + 1, con.getParent_city_name1());
						label_x_1 = new Label(1, x + 1, con.getParent_city_name());
						label_x_2 = new Label(2, x + 1, con.getCityName());
						label_x_3 = new Label(3, x + 1, con.getParent_city_name3());
					}else if(con.getCitylevel().equals("������")){
						label_x_0 = new Label(0, x + 1, con.getParent_city_name2());
						label_x_1 = new Label(1, x + 1, con.getParent_city_name1());
						label_x_2 = new Label(2, x + 1, con.getParent_city_name());
						label_x_3 = new Label(3, x + 1, con.getCityName());
					}
					
					// �������
//					 label_x_0 = new Label(0, x + 1, con.getParent_city_name3());
					label_x_0.setCellFormat(cellFormat_bottom_1);
					wsheet.addCell(label_x_0);
					
//					 label_x_1 = new Label(1, x + 1,con.getParent_city_name2());
					label_x_1.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_1);
					
//					 label_x_2 = new Label(2, x + 1, con.getParent_city_name1());
					label_x_2.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_2);

//					 label_x_3 = new Label(3, x + 1, con.getParent_city_name());
					label_x_3.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_3);
					
//					 label_x_4 = new Label(4, x + 1, con.getCityName());
//					label_x_4.setCellFormat(cellFormat_bottom);
//					wsheet.addCell(label_x_4);
					
					Label label_x_5 = new Label(4, x + 1, con.getCityNumber());
					label_x_5.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_5);

					
					Label label_x_6 = new Label(5, x + 1,
							con.getCity_fp_flag());
					label_x_6.setCellFormat(cellFormat_bottom_1);
					wsheet.addCell(label_x_6);

					Label label_x_7 = new Label(6, x + 1, con.getCitylevel());
					label_x_7.setCellFormat(cellFormat_bottom);
					wsheet.addCell(label_x_7);
				}
				wbook.write();
			} catch (Exception e) {
				logger.error(e);
			} finally {
				if (wbook != null) {
					try {
						wbook.close();
					} catch (Exception e) {
						logger.error(e);
					}
					wbook = null;
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

		}
		return RESULT_MESSAGE;
	}
	public ICityDictService getCityDictService() {
		return cityDictService;
	}

	public void setCityDictService(ICityDictService cityDictService) {
		this.cityDictService = cityDictService;
	}

	public List<CityDict> getCitylist() {
		return citylist;
	}

	public void setCitylist(List<CityDict> citylist) {
		this.citylist = citylist;
	}



	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getParent_city_name() {
		return parent_city_name;
	}

	public void setParent_city_name(String parent_city_name) {
		this.parent_city_name = parent_city_name;
	}

	public CityDict getCity() {
		return city;
	}

	public void setCity(CityDict city) {
		this.city = city;
	}

	

	public String getCitylevel() {
		return citylevel;
	}

	public void setCitylevel(String citylevel) {
		this.citylevel = citylevel;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<CityDict> getCitydictlist() {
		return citydictlist;
	}

	public void setCitydictlist(List<CityDict> citydictlist) {
		this.citydictlist = citydictlist;
	}

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	

	public List<CmsTbDict> getDictlist() {
		return dictlist;
	}

	public void setDictlist(List<CmsTbDict> dictlist) {
		this.dictlist = dictlist;
	}

	public List<CmsTbDict> getDicts() {
		return dicts;
	}

	public void setDicts(List<CmsTbDict> dicts) {
		this.dicts = dicts;
	}

	public String getCountrytype() {
		return countrytype;
	}

	public void setCountrytype(String countrytype) {
		this.countrytype = countrytype;
	}

	public String getParent_city_id() {
		return parent_city_id;
	}

	public void setParent_city_id(String parent_city_id) {
		this.parent_city_id = parent_city_id;
	}

	public String getTodictId() {
		return todictId;
	}

	public void setTodictId(String todictId) {
		this.todictId = todictId;
	}

	public IDictService getDictServiceHessian() {
		return dictServiceHessian;
	}

	public void setDictServiceHessian(IDictService dictServiceHessian) {
		this.dictServiceHessian = dictServiceHessian;
	}

	public String getCityFpFlag() {
		return cityFpFlag;
	}

	public void setCityFpFlag(String cityFpFlag) {
		this.cityFpFlag = cityFpFlag;
	}

	public String getCityType() {
		return cityType;
	}

	public void setCityType(String cityType) {
		this.cityType = cityType;
	}

	
	
	
}
