package com.kintiger.platform.kunnr.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.base.pojo.BooleanResult;
import com.kintiger.platform.base.pojo.StringResult;
import com.kintiger.platform.framework.annotations.Decode;
import com.kintiger.platform.framework.annotations.JsonResult;
import com.kintiger.platform.framework.annotations.PermissionSearch;
import com.kintiger.platform.goal.action.GoalAction;
import com.kintiger.platform.kunnr.pojo.KunnrAddress;
import com.kintiger.platform.kunnr.service.IKunnrAddressService;

public class KunnrAddressAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 295833911357720279L;
	private static Log logger = LogFactory.getLog(GoalAction.class);
	private KunnrAddress kunnrAddress;
	private IKunnrAddressService kunnrAddressService;
	private Long id;
	private String kunnr;
	private String kunnrId;
	private String kunnrSd;// 送达方
	private String address;
	private String name;
	private String telephone;
	private String mobile;
	private int total = 0;
	private List<KunnrAddress> kunnrAddressList;
	private String ids;
	private String maxSd;
	private @Decode
	String kunnrName;
	private String maximum;

	/**
	 * 送达方查询维护跳转
	 * 
	 * @return
	 */
	@PermissionSearch
	public String kunnrAddressSearchPre() {
		return "search";

	}

	@PermissionSearch
	@JsonResult(field = "kunnrAddressList", include = { "kunnrSd", "kunnr",
			"kunnrName", "address", "name", "telephone", "mobile" ,"maximum","maximumTxt","state","province","city","area","town","xzAddress"}, total = "total")
	public String kunnrAddressSearch() {
		kunnrAddress = new KunnrAddress();
		kunnrAddress.setStart(getStart());
		kunnrAddress.setEnd(getEnd());
		if (StringUtils.isNotEmpty(kunnrSd)) {
			kunnrAddress.setKunnrSd(kunnrSd);
		}
		if (StringUtils.isNotEmpty(kunnrId)) {
			kunnrAddress.setKunnr(kunnrId);
		}
		if (StringUtils.isNotEmpty(kunnrName)) {
			kunnrAddress.setKunnrName(kunnrName);
		}

		total = kunnrAddressService.kunnrAddressSearchCount(kunnrAddress);
		if (total != 0) {
			kunnrAddressList = kunnrAddressService
					.kunnrAddressSearch(kunnrAddress);
		}
		return JSON;
	}

	/**
	 * 送达方新增pre
	 * 
	 * 
	 * @return
	 */
	@PermissionSearch
	public String createKunnrAddressPrepare() {

		return "createKunnrAddress";
	}

	/**
	 * 送达方新增
	 * 
	 * 
	 * @return
	 */
	public String createKunnrAddress() {
		this.setFailMessage("");
		this.setSuccessMessage("");
		BooleanResult booleanResult = kunnrAddressService
				.createKunnrAddress(kunnrAddress);
		if (booleanResult.getResult()) {
			this.setSuccessMessage("送达方创建成功");
		} else {
			this.setFailMessage(booleanResult.getCode());
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 送达方删除
	 * 
	 * 
	 * @return
	 */
	public String deleteKunnrAddress() {
		kunnrAddress = new KunnrAddress();
		String[] l = ids.split(",");

		try {
			kunnrAddress.setCodes(l);

			StringResult result = kunnrAddressService
					.deleteKunnrAddress(kunnrAddress);

			if (IKunnrAddressService.ERROR.equals(result.getCode())) {

				this.setFailMessage(result.getResult());
			} else {
				this.setSuccessMessage("处理成功");
			}
		} catch (Exception e) {
			this.setFailMessage("处理失败！");
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 送达方修改Pre
	 * 
	 * 
	 * @return
	 */
	@PermissionSearch
	public String updateKunnrAddressPre() {

		if (StringUtils.isNotEmpty(kunnrSd)
				&& StringUtils.isNotEmpty(kunnrSd.trim())) {
			try {
				kunnrSd = new String(kunnrSd.trim().getBytes("ISO8859-1"),
						"UTF-8");/* 转码你懂么 */
				kunnrAddress = kunnrAddressService.getKunnrAddressById(kunnrSd);
			} catch (UnsupportedEncodingException e) {
				logger.error(kunnrSd, e);
			}
		}
		kunnrAddress = kunnrAddress == null ? new KunnrAddress() : kunnrAddress;
		return "updateKunnrAddressPre";

	}

	/**
	 * 送达方修改
	 * 
	 * 
	 * @return
	 */
	public String updateKunnrAddress() {
		BooleanResult b = kunnrAddressService.updateKunnrAddress(kunnrAddress);
		if (b.getResult()) {
			this.setSuccessMessage("处理成功！");
		} else {
			this.setFailMessage(b.getCode());
		}
		return RESULT_MESSAGE;
	}

	/**
	 * 
	 * 获取经销商所属最大的送达方编号
	 * 
	 * @return
	 */
	@JsonResult(field = "maxSd")
	@PermissionSearch
	public String getMaxKunnrSd() {
		maxSd = kunnrAddressService.getMaxKunnrSd(kunnrId);
		if (StringUtils.isNotEmpty(maxSd)) {
			int ext = Integer.parseInt(maxSd.substring(8, 10)) + 1;
			if(ext>=10){
			maxSd = maxSd.substring(0, 8) + ext;
			}else{
			maxSd = maxSd.substring(0, 9) + ext;
			}
		} else {
			maxSd = kunnrId + "01";
		}
		return JSON;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<KunnrAddress> getKunnrAddressList() {
		return kunnrAddressList;
	}

	public void setKunnrAddressList(List<KunnrAddress> kunnrAddressList) {
		this.kunnrAddressList = kunnrAddressList;
	}

	public KunnrAddress getKunnrAddress() {
		return kunnrAddress;
	}

	public void setKunnrAddress(KunnrAddress kunnrAddress) {
		this.kunnrAddress = kunnrAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKunnr() {
		return kunnr;
	}

	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}

	public String getKunnrSd() {
		return kunnrSd;
	}

	public void setKunnrSd(String kunnrSd) {
		this.kunnrSd = kunnrSd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public IKunnrAddressService getKunnrAddressService() {
		return kunnrAddressService;
	}

	public void setKunnrAddressService(IKunnrAddressService kunnrAddressService) {
		this.kunnrAddressService = kunnrAddressService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getKunnrId() {
		return kunnrId;
	}

	public void setKunnrId(String kunnrId) {
		this.kunnrId = kunnrId;
	}

	public String getMaxSd() {
		return maxSd;
	}

	public void setMaxSd(String maxSd) {
		this.maxSd = maxSd;
	}

	public String getKunnrName() {
		return kunnrName;
	}

	public void setKunnrName(String kunnrName) {
		this.kunnrName = kunnrName;
	}

	public String getMaximum() {
		return maximum;
	}

	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}

	
}
