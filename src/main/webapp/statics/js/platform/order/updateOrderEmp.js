$(document).ready(function() {
	init();
	loadOrderDetail();
	$('#hideFrame').bind('load', promgtMsg);
});

function init(){
	if($('#statusValue').val()=='U'){
		$('#status').val('����');
	}else{
		$('#status').val('����');
	};
	
	if($('#orderStatusValue').val()=='Y'){
		$('#orderStatus').val('����');
	}else{
		$('#orderStatus').val('δ��');
	};
	if($('#orderFundsStatusValue').val()=='Y'){
		$('#orderFundsStatus').val('�Ѵ��');
	}else{
		$('#orderFundsStatus').val('δ���');
	};
	
}

/**
 * �ر�ҳ��
 */
function cencel() {
	window.parent.closeMaintWindow();
}

function loadOrderDetail(){
	$.ajax({
		type : "post",
		url : appUrl + "/orderAction!searchOrderDetailList.jspa?",
		data : { orderId : $("#orderId").val() },
		success : function(data) {
			var sum=parseFloat(0);
			$.each(data,function(key,val){
				sum+=parseFloat(val.totalPrice);
				if(val.realQuantity==null){
					val.realQuantity=0;
				}
				$("#orderDetailList").append(
						'<table width="100%" border="0" id="detail_'+ key +'">' +
						'<tr><td class="head" nowrap>��ϸ��ţ�</td>' +
						'<td><input type="text" size="5" id="orderDetailId_'+ key +'" name="orderDetailIds" value="'+ val.orderDetailId +'" class="easyui-validatebox" readonly="true"></td>' +
						'<td class="head" nowrap>Ʒ�</td>' +
						'<td><input type="text" id="skuName_'+ key +'"  value="'+ val.skuName +'" class="easyui-validatebox" readonly="true">' +
							'<input type="hidden" id="skuId_'+ key +'" name="skuIds">' +
						'</td>' +
                        '<td class="head" nowrap>�������ͣ�</td>' +
						'<td>' +
						'    <input type="hidden" id="orderTypeValue_'+ key +'" value="'+ val.orderType +'" />' +
						'	<input type="text" id="orderType_'+ key +'" name="orderTypes" class="easyui-validatebox" readonly="true">' +
						'</td>' +
						'<td class="head" nowrap>���ۣ�</td>' +
						'<td>' +
						'	<input type="text" size="4" id="price_'+ key +'" name="prices" value="'+ val.price +'" class="easyui-validatebox" readonly="true">' +
						'</td>' +
                        '<td class="head" nowrap>������</td>' +
						'<td >' +
						'	<input type="text" size="4" id="quantity_'+ key +'" name="quantitys" value="'+ val.quantity +'" class="easyui-validatebox" readonly="true">' +
						'</td>' +
						'<td class="head" nowrap>ʵ��������</td>' +
						'<td >' +
						'	<input type="text" size="4" id="realQuantity_'+ key +'" name="realQuantitys"  value="'+ val.realQuantity +'" class="easyui-validatebox" readonly="true">' +
						'</td>' +
						'<td class="head" nowrap>�ܼۣ�</td>' +
						'<td>' +
						'	<input type="text" size="5" id="totalPriceDetail_'+ key +'" name="totalPriceDetails" value="'+ val.totalPrice +'" class="easyui-validatebox" readonly="true">' +
						'</td></tr>' +
						'<tr><td>&nbsp;</td></tr>'+
						'</table>'
						          );
				if($('#orderTypeValue_'+key).val()=='A'){
					$('#orderType_'+key).val('��Ʒ');
				}else if($('#orderTypeValue_'+key).val()=='B'){
					$('#orderType_'+key).val('��Ʒ');
				}
			});
			$("#orderDetailList").append(
					'<table width="100%" border="0">' +
					'<tr>' +
					'<td class="head" nowrap>�ܼۺϼƣ�</td>' +
					'<td>' +
					'<input type="text" size="5" value="'+ sum +'" class="easyui-validatebox" readonly="true">' +
					'</td>' +
					'</tr>'
					);
		}
	});
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				cencel();
				window.parent.search();
			}
		});
	}
}

/*
 * document.onkeydown = function(e) { var theEvent = e || window.event; var code =
 * theEvent.keyCode || theEvent.which || theEvent.charCode; if (code == 13) {
 * search(); return false; } return true; };
 */

function initMaintWindow(title, url, id, WWidth, WHeight, fit) {
	var url = appUrl + url;
	var $win = $("#" + id)
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						fit : fit,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});

	$win.window('open');
}

//�رմ���ҳ��
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		return false;
	}
	return true;
};
