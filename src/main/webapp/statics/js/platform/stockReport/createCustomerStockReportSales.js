$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadCust();
});

/**
 * �ر�ҳ��
 */
function cencel() {
	window.parent.closeMaintWindow();
}

function createStockReport() {
	if($('#custId').combobox("getValue")==""){
		$.messager.alert('��ʾ', '�ŵ겻��Ϊ�գ�', '��ʾ');
		return;
	}
	if($('#skuId').combobox("getValue")==""){
		$.messager.alert('��ʾ', 'Ʒ���Ϊ�գ�', '��ʾ');
		return;
	}
	if($('#checkTime').val()==""){
		$.messager.alert('��ʾ', '���ڲ���Ϊ�գ�', '��ʾ');
		return;
	}
	if($('#quantity').val()==""){
		$.messager.alert('��ʾ', '��������Ϊ�գ�', '��ʾ');
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/stockReportAction!searchCustomerSalesList.jspa",
		data : {
			custId : $('#custId').combobox("getValue"),
			skuId : $('#skuId').combobox("getValue"),
			productionStartDate : $('#checkTime').val(),
			productionEndDate : $('#checkTime').val(),
		},
		success : function(data) {
			if(data.total>0){
				$.messager.alert('��ʾ', '�Ѵ�����ͬƷ���ᱨ��¼�������޸Ĳ�����', '��ʾ');
//					$.messager.confirm('Confirm',
//						'�����Ѵ�����ͬƷ���ᱨ��¼���Ƿ��ظ��ᱨ��',
//						function(r) {
//					if (r) {
//						var form = window.document.forms[0];
//						form.action = appUrl + "/stockReportAction!createStockReport.jspa";
//						form.target = "hideFrame";
//						form.submit();
//					}
//				});
			}else{
				$.messager.confirm(
						'Confirm',
						'ȷ���ύ��',
						function(r) {
							if (r) {
								var form = window.document.forms[0];
								form.action = appUrl + "/stockReportAction!createCustomerStock.jspa";
								form.target = "hideFrame";
								form.submit();
							}
						});
			}
		}
	});
}

function loadCust(){
	$('#kunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val()+'&bhxjFlag=C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ 
					 [ {
			field : 'kunnr',
			title : '�����̱��',
			width : 120
		}, {
			field : 'name1',
			title : '����',
			width : 200
		} ] ],
		toolbar : '#toolbarKunnr',
		onChange : function(newValue, oldValue){
			$('#custId').combogrid({
				disabled : false,
				url : appUrl + '/customerAction!customerSearch.jspa?custKunnr='+$('#kunnr').combobox("getValue")
			});
		}
	});
	
	$('#custId').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		disabled : true,
		idField : 'custId',
		textField : 'custName',
		// multiple:true,
		columns : [ 
					 [ {
			field : 'custId',
			title : '�ŵ���',
			width : 120
		}, {
			field : 'custName',
			title : '�ŵ�����',
			width : 200
		} ] ],
		toolbar : '#toolbarKonzs'
	});
	
	$('#skuId').combogrid(
			{
				panelHeight :250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : false,
				multiple : false,
				url : appUrl
						+ '/stockReportAction!searchSku.jspa',
				idField : 'skuId',
				textField : 'skuName',
				columns : [
//				           [ { field : 'ck', checkbox : true } ],
				           [ {
					field : 'skuId',
					title : 'Ʒ����',
					width : 60
				}, {
					field : 'skuName',
					title : 'Ʒ������',
					width : 200
				} ] ],
				toolbar : '#toolbarSku',
				onChange : function(newValue,oldValue){
					$.ajax({
						type : "post",
						url : appUrl + "/stockReportAction!searchSkuUnitList.jspa",
						data : {
							skuId : $('#skuId').combobox("getValue"),
							flag : "W"
						},
						success : function(data) {
							$.each(data.rows,function(k,v){
								$('#unitDesc').val(v.skuUnit);
							});
						}
					});
				}
			});
	
//	$('#categoryId').combogrid(
//			{
//				panelHeight :250,
//				panelWidth : 380,
//				pagination : true,
//				method : 'post',
//				singleSelect : false,
//				multiple : false,
//				url : appUrl
//						+ '/stockReportAction!searchCategory.jspa',
//				idField : 'categoryId',
//				textField : 'categoryName',
//				columns : [
////				           [ { field : 'ck', checkbox : true } ],
//				           [ {
//					field : 'categoryId',
//					title : 'Ʒ����',
//					width : 60
//				}, {
//					field : 'categoryName',
//					title : 'Ʒ������',
//					width : 200
//				} ] ],
//				toolbar : '#toolbarCategory'
//			});
}

function searcherKonzs(custName) {
	$.ajax({
		type : "post",
		url : appUrl + "/customerAction!customerSearch.jspa",
		data : {
			custNumber : custName
		},
		success : function(data) {
			if(data.total==0){
				var queryParams = $('#custId').combogrid("grid").datagrid('options').queryParams;
				queryParams.custName = encodeURIComponent(custName);
				queryParams.custNumber = null;
				$('#custId').combogrid("grid").datagrid('reload');	
			}else{
				var queryParams = $('#custId').combogrid("grid").datagrid('options').queryParams;
				queryParams.custNumber = custName;
				queryParams.custName = null;
				$('#custId').combogrid("grid").datagrid('reload');
			}
		}
	});
	
}

function searcherSku(val) {
	var queryParams = $('#skuId').combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(val);
	$('#skuId').combogrid("grid").datagrid('reload');
}

function searcherKunnr(name1) {
	var regx = /^\d+$/;
	if(regx.test(name1)){
		var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
		queryParams.kunnrId = name1;
		queryParams.name1 = null;
		$('#kunnr').combogrid("grid").datagrid('reload');
	}else{
		var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
		queryParams.kunnrId = null;
		queryParams.name1 = encodeURIComponent(name1);
		$('#kunnr').combogrid("grid").datagrid('reload');
	}
}

//function searcherCategory(val) {
//	var queryParams = $('#categoryId').combogrid("grid").datagrid('options').queryParams;
//	queryParams.categoryName = encodeURIComponent(val);
//	$('#categoryId').combogrid("grid").datagrid('reload');
//
//}

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
