package com.kintiger.platform.order.action;

import java.util.Date;
import java.util.List;

import com.hitrust.b2b.trustpay.client.b2b.*;
import com.kintiger.platform.base.action.BaseAction;
import com.kintiger.platform.order.pojo.Account;
import com.kintiger.platform.order.pojo.OrderDetail;
import com.kintiger.platform.order.pojo.XppOrder;
import com.kintiger.platform.order.service.IOrderNewService;

public class OrderResultAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6157618677363366907L;
	
	private String MSG;
	private IOrderNewService orderNewService;
	private XppOrder order;
	private List<OrderDetail> orderDetailList;
	private Account account;
	
	public String getB2bMsg(){
		try {
//			MSG="PE1TRz48TWVzc2FnZT48VHJ4UmVzcG9uc2U+PE1lcmNoYW50SUQ+MjMzMDEwNTAwMjYxQjAxPC9NZXJjaGFudElEPjxDb3Jwb3JhdGlvbkN1c3RvbWVyTm8+MzM5OTk3ODY3OTY8L0NvcnBvcmF0aW9uQ3VzdG9tZXJObz48TWVyY2hhbnRUcm54Tm8+MjAxNDA4MDQxMDI5MzI8L01lcmNoYW50VHJueE5vPjxUcm54U04+OTAxNDA4MDQxNTI3MDA1NDQ2NTwvVHJueFNOPjxUcm54VHlwZT5GVU5EX1RSQU5TRkVSPC9Ucm54VHlwZT48VHJueEFNVD4wLjAxPC9Ucm54QU1UPjxPcmdpbmFsRnJlZXplTm8+IDwvT3JnaW5hbEZyZWV6ZU5vPjxGcmVlemVObz4gPC9GcmVlemVObz48QWNjb3VudE5vPjE5LTEwNTgwMTA0MDAwMTE3OTwvQWNjb3VudE5vPjxBY2NvdW50TmFtZT7P48auxq7Ks8a3ucm33dPQz965q8u+PC9BY2NvdW50TmFtZT48QWNjb3VudEJhbms+1eO9rbfW0NA8L0FjY291bnRCYW5rPjxBY2NvdW50REJObz4xOTEwNTEwMTA0MDIyNzA1MDwvQWNjb3VudERCTm8+PEFjY291bnREQk5hbWU+z+PGrsauyrPGt7nJt93T0M/euavLvjwvQWNjb3VudERCTmFtZT48QWNjb3VudERCQmFuaz7V472tt9bQ0DwvQWNjb3VudERCQmFuaz48VHJueFRpbWU+MjAxNC04LTQgMTU6Mjc6MDA8L1RybnhUaW1lPjxUcm54U3RhdHVzPjI8L1RybnhTdGF0dXM+PFJldHVybkNvZGU+MDAwMDwvUmV0dXJuQ29kZT48L1RyeFJlc3BvbnNlPjwvTWVzc2FnZT48U2lnbmF0dXJlLUFsZ29yaXRobT5TSEExd2l0aFJTQTwvU2lnbmF0dXJlLUFsZ29yaXRobT48U2lnbmF0dXJlPitaNUZwcFFPWVdITlJuenl6NFRPUTV0UmU5R3dTYlI2d1RXWkF0QlBGU0NVMVZaYjNsQkRHem5QSGRETmZHeVFoM0ttRjhEY0xDWkI4Y05teDhnZnU0Z21UR0o1d05pLy9hbEpieDM3TUFxNVFNTFNFcVEwcTM5ZWJJNFoyQnJKM2F3YjlJMkVuUmx1SjhjMTYyN0xKU1d1TFlMY0NuK3o4M2h2SmRLa1ZTST08L1NpZ25hdHVyZT48L01TRz4=";
//			System.out.println("b2b-MSG="+MSG);
//			String msg=ServletActionContext.getRequest().getParameter("MSG");
//			MSG=msg;
			System.out.println("b2b-MSG="+MSG);
			TrnxResult tResult = new TrnxResult(MSG);
			System.out.println("tResult="+tResult);
			System.out.println("isSuccess="+tResult.isSuccess());
			if (tResult.isSuccess()) {
				order = new XppOrder();
				System.out.println("支付成功");
				System.out.println("交易编号="+tResult.getValue("MerchantTrnxNo"));
				System.out.println("交易状态="+tResult.getValue("TrnxStatus"));
				System.out.println("order="+order);
				order.setOrderId(Long.parseLong(tResult.getValue("MerchantTrnxNo")));
				System.out.println(order.getOrderId());
				order.setOrderFundsStatus("Y");
				orderNewService.updateOrder(order, orderDetailList);
			}
			else {
				System.out.println(tResult.getErrorMessage());
				this.setFailMessage("支付失败,"+tResult.getErrorMessage());
			}
			return "getB2bMsg";
		} catch (com.hitrust.b2b.trustpay.client.TrxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String getAccountB2bMsg(){
		try {
//			MSG="PE1TRz48TWVzc2FnZT48VHJ4UmVzcG9uc2U+PE1lcmNoYW50SUQ+MjMzMDEwNTAwMjYxQjAxPC9NZXJjaGFudElEPjxDb3Jwb3JhdGlvbkN1c3RvbWVyTm8+MzM5OTk3ODY3OTY8L0NvcnBvcmF0aW9uQ3VzdG9tZXJObz48TWVyY2hhbnRUcm54Tm8+MjAxNDA4MDQxMDI5MzI8L01lcmNoYW50VHJueE5vPjxUcm54U04+OTAxNDA4MDQxNTI3MDA1NDQ2NTwvVHJueFNOPjxUcm54VHlwZT5GVU5EX1RSQU5TRkVSPC9Ucm54VHlwZT48VHJueEFNVD4wLjAxPC9Ucm54QU1UPjxPcmdpbmFsRnJlZXplTm8+IDwvT3JnaW5hbEZyZWV6ZU5vPjxGcmVlemVObz4gPC9GcmVlemVObz48QWNjb3VudE5vPjE5LTEwNTgwMTA0MDAwMTE3OTwvQWNjb3VudE5vPjxBY2NvdW50TmFtZT7P48auxq7Ks8a3ucm33dPQz965q8u+PC9BY2NvdW50TmFtZT48QWNjb3VudEJhbms+1eO9rbfW0NA8L0FjY291bnRCYW5rPjxBY2NvdW50REJObz4xOTEwNTEwMTA0MDIyNzA1MDwvQWNjb3VudERCTm8+PEFjY291bnREQk5hbWU+z+PGrsauyrPGt7nJt93T0M/euavLvjwvQWNjb3VudERCTmFtZT48QWNjb3VudERCQmFuaz7V472tt9bQ0DwvQWNjb3VudERCQmFuaz48VHJueFRpbWU+MjAxNC04LTQgMTU6Mjc6MDA8L1RybnhUaW1lPjxUcm54U3RhdHVzPjI8L1RybnhTdGF0dXM+PFJldHVybkNvZGU+MDAwMDwvUmV0dXJuQ29kZT48L1RyeFJlc3BvbnNlPjwvTWVzc2FnZT48U2lnbmF0dXJlLUFsZ29yaXRobT5TSEExd2l0aFJTQTwvU2lnbmF0dXJlLUFsZ29yaXRobT48U2lnbmF0dXJlPitaNUZwcFFPWVdITlJuenl6NFRPUTV0UmU5R3dTYlI2d1RXWkF0QlBGU0NVMVZaYjNsQkRHem5QSGRETmZHeVFoM0ttRjhEY0xDWkI4Y05teDhnZnU0Z21UR0o1d05pLy9hbEpieDM3TUFxNVFNTFNFcVEwcTM5ZWJJNFoyQnJKM2F3YjlJMkVuUmx1SjhjMTYyN0xKU1d1TFlMY0NuK3o4M2h2SmRLa1ZTST08L1NpZ25hdHVyZT48L01TRz4=";
//			System.out.println("b2b-MSG="+MSG);
//			String msg=ServletActionContext.getRequest().getParameter("MSG");
//			MSG=msg;
			System.out.println("b2b-MSG="+MSG);
			TrnxResult tResult = new TrnxResult(MSG);
			System.out.println("tResult="+tResult);
			System.out.println("isSuccess="+tResult.isSuccess());
			if (tResult.isSuccess()) {
				account = new Account();
				System.out.println("支付成功");
				System.out.println("交易编号="+tResult.getValue("MerchantTrnxNo"));
				System.out.println("交易状态="+tResult.getValue("TrnxStatus"));
				System.out.println("order="+order);
				account.setAccountId(Long.parseLong(tResult.getValue("MerchantTrnxNo")));
				System.out.println(account.getAccountId());
				account.setStatus("Y");
				account.setSuccessNum(tResult.getValue("trnxSN"));
				account.setSuccessDate(new Date());
				orderNewService.updateAccount(account);
			}
			else {
				System.out.println(tResult.getErrorMessage());
				this.setFailMessage("支付失败,"+tResult.getErrorMessage());
			}
			return "getB2bMsg";
		} catch (com.hitrust.b2b.trustpay.client.TrxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public String getMSG() {
		return MSG;
	}

	public void setMSG(String mSG) {
		MSG = mSG;
	}

	public IOrderNewService getOrderNewService() {
		return orderNewService;
	}

	public void setOrderNewService(IOrderNewService orderNewService) {
		this.orderNewService = orderNewService;
	}

	public XppOrder getOrder() {
		return order;
	}

	public void setOrder(XppOrder order) {
		this.order = order;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	

}
