$(document).ready(function() {
	init();
	loadOrderDetail();
	$('#hideFrame').bind('load', promgtMsg);
});

function init(){
	var status=$('#status');
	var orderStatus=$('#orderStatus');
	var orderFundsStatus=$('#orderFundsStatus');
	status.get(0).value = $('#statusValue').val();
	orderStatus.get(0).value = $('#orderStatusValue').val();
	orderFundsStatus.get(0).value = $('#orderFundsStatusValue').val();
	
	
	
	$('#kunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$("#orgId").val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ 
					 [ {
			field : 'kunnr',
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 200
		} ] ],
		onClickRow : function(rowIndex, rowData){
			$("#kunnr").val(rowData.kunnr);
			$("#name2").val(rowData.name3);
			$("#mobile").val(rowData.mobNumber);
			$("#carType").val(rowData.maximumTxt);
			$("#address").val(rowData.street1);
		},
		toolbar : '#toolbarKonzs'
	});
	$('#kunnr').combogrid('setText', $("#kunnrName").val());
}

/**
 * 关闭页面
 */
function cencel() {
	window.parent.closeMaintWindow();
}

function calcu(id){
	$('#totalPriceDetail_'+id).val(accMul($('#price_'+id).val(),$('#quantity_'+id).val()));
}

function accMul(arg1,arg2){
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{m+=s1.split(".")[1].length}catch(e){}
	try{m+=s2.split(".")[1].length}catch(e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

function updateOrder() {
	var form = window.document.forms[0];
	form.action = appUrl + "/order/orderNewAction!updateOrderNew.jspa";
	form.target = "hideFrame";
	form.submit();
}

function loadOrderDetail(){
	$.ajax({
		type : "post",
		url : appUrl + "/orderNewAction!searchOrderNewDetailList.jspa?",
		data : { orderId : $("#orderId").val() },
		success : function(data) {
			$.each(data,function(key,val){
				if(val.realQuantity==null){
					val.realQuantity=0;
				}
				$("#orderDetailList").append(
						'<table width="100%" border="0" id="detail_'+ key +'">' +
						'<tr><td class="head" nowrap>明细编号：</td>' +
						'<td><input type="text" size="5" id="orderDetailId_'+ key +'" name="orderDetailIds" value="'+ val.orderDetailId +'" class="easyui-validatebox" readonly="true"></td>' +
						'<td class="head" nowrap>品项：</td>' +
						'<td><input id="skuName_'+ key +'" type="hidden" value="'+ val.skuName +'">' +
							'<input id="skuId_'+ key +'" name="skuIds" data-options="required:true,editable:false">' +
						'</td>' +
						'    <div id="toolbarSku_'+ key +'" class="datagrid-toolbar" >' +
                        '   <input id="searchBox_'+ key +'" class="easyui-searchbox" searcher="searcherSku" prompt="请填写物料名"  style="width:300px"></input>' +
                        '   </div>' +
//                        '<td class="head" nowrap>订单类型：</td>' +
//						'<td>' +
//						'    <input type="hidden" id="orderTypeValue_'+ key +'" value="'+ val.orderType +'" />' +
//						'	<select id="orderType_'+ key +'" name="orderTypes">' +
//						'	<option value="A">本品</option>' +
//						'	<option value="B">赠品</option>' +
//						'	</select>' +
//						'</td>' +
						'<td class="head" nowrap>单价：</td>' +
						'<td>' +
						'	<input type="text" size="4" id="price_'+ key +'" name="prices" value="'+ val.price +'" class="easyui-validatebox" onblur="calcu('+ key +')">' +
						'</td>' +
                        '<td class="head" nowrap>数量：</td>' +
						'<td >' +
						'	<input type="text" size="4" id="quantity_'+ key +'" name="quantitys" value="'+ val.quantity +'" class="easyui-validatebox" onblur="calcu('+ key +');">' +
						'</td>' +
						'<td class="head" nowrap>实际数量：</td>' +
						'<td >' +
						'	<input type="text" size="4" id="realQuantity_'+ key +'" name="realQuantitys"  value="'+ val.realQuantity +'" class="easyui-validatebox">' +
						'</td>' +
						'<td class="head" nowrap>总价：</td>' +
						'<td>' +
						'	<input type="text" size="5" id="totalPriceDetail_'+ key +'" name="totalPriceDetails" value="'+ val.totalPrice +'" class="easyui-validatebox" readonly="true">' +
						'</td></tr>' +
						'<tr><td>&nbsp;</td></tr>'+
						'</table>'
						          );
				$('#searchBox_'+key).searchbox();
				
				$('#skuId_'+key).combogrid(
						{
							panelHeight :250,
							panelWidth : 380,
							pagination : true,
							method : 'post',
							singleSelect : true,
							multiple : false,
							url : appUrl
									+ '/orderNewAction!getSkuName.jspa',
							idField : 'skuId',
							textField : 'skuName',
							columns : [
							           
							           [ {
								field : 'skuId',
								title : '品项编号',
								width : 60
							}, {
								field : 'skuName',
								title : '品项名称',
								width : 200
							} ] ],
							toolbar : '#toolbarSku_'+key
						});
				$('#skuId_'+key).combogrid('setText', $("#skuName_"+key).val());
				

//				$('#orderType_'+key).get(0).value = $('#orderTypeValue_'+key).val();
			})
		}
	});
}

function searcherSku(val) {
	var id=$(this).attr('id');
	var ids=id.split('_');
	var queryParams = $('#skuId_'+ids[1]).combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(val);
	$('#skuId_'+ids[1]).combogrid("grid").datagrid('reload');
}

function searcherKonzs(name1) {
	var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#kunnr').combogrid("grid").datagrid('reload');
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

//关闭创建页面
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
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

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
