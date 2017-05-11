$(document).ready(function() {
	loadCustKunnr();
	loadSku();
	loadCustSystem();
	$('#hideFrame').bind('load', promgtMsg);
	// 客户分类 渠道
	var type = $('#type').val();
	if (type == '') {
		type = 'Z';
	}
	$('#channelName').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa?channelName=' + type,
		valueField : 'channelId',
		textField : 'channelName',
		onSelect : function (){
		    $('#channelId').val($('#channelName').combobox('getValue'));
		}
	});

});

function loadCustKunnr() {
	// 二阶客户
	$('#custParent').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/customerAction!getTwoLevelCustomer.jspa?orgId='+$('#orgId').val(),
		idField : 'custId',
		textField : 'custName',
		multiple : true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'custId',
			title : '客户编号',
			width : 120
		}, {
			field : 'custName',
			title : '客户名称',
			width : 200
		} ] ],
		toolbar : '#toolbarParent'
	});
	$('#saleItemsKunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ 
					 [ { field : 'ck', checkbox : true } ],
					 [ {
			field : 'kunnr',
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 200
		} ] ],
		toolbar : '#toolbarKonzs'
	});
	$('#saleItemsKunnr').combogrid('setText',$('#saleItemsKunnrName').val());


}


function loadCustSystem() {
	// 所属系统
	$('#saleItemsSystem').combogrid(
			{
				panelHeight : 250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : true,
				url : appUrl
						+ '/crmdictAction!getDictJsonList.jspa?dictTypeValue='
						+ 'system@customer',
				idField : 'itemId',
				textField : 'itemName',
				columns : [
				           [ { field : 'ck', checkbox : true } ],
				           [ {
					field : 'itemId',
					title : '系统编号',
					width : 60
				}, {
					field : 'itemName',
					title : '系统名称',
					width : 200
				}, {
					field : 'itemValue',
					title : '描述',
					width : 200
				} ] ],
				toolbar : '#toolbarSys'
			});
	$('#saleItemsSystem').combogrid('setValue', $("#saleItemsSystemId").val());
	$('#saleItemsSystem').combogrid('setText', $("#saleItemsSystemName").val());
}

function loadSku() {
	
	$('#skuId').combogrid(
			{
				panelHeight :250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : true,
				multiple : true,
				url : appUrl
						+ '/saleItemsAction!getSkuName.jspa',
				idField : 'skuId',
				textField : 'skuName',
				columns : [
				           [ { field : 'ck', checkbox : true } ],
				           [ {
					field : 'skuId',
					title : '品项编号',
					width : 60
				}, {
					field : 'skuName',
					title : '品项名称',
					width : 200
				} ] ],
				toolbar : '#toolbarSku'
			});
//	$('#sku').combogrid('setValue', $("#sku_id").val());
	$('#skuId').combogrid('setText', $("#skuName").val());
//	$('#skuId').combogrid({
//		onClickRow :  function(rowIndex,rowData){
//			alert(rowData.skuName);
//		}
//	});
}





function searcherKonzs(name1) {
	var queryParams = $('#saleItemsKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#saleItemsKunnr').combogrid("grid").datagrid('reload');
}

function searcherSys(name1) {
	var queryParams = $('#saleItemsKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#saleItemsKunnr').combogrid("grid").datagrid('reload');
}

// 二阶客户下拉查询
function searcherParent(name1) {
	var queryParams = $('#custParent').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#custParent').combogrid("grid").datagrid('reload');
}

function searcher1(skuName) {
	var queryParams = $('#skuId').combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(skuName);
	$('#skuId').combogrid("grid").datagrid('reload');

}


function openWindow(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#OpenGoal").window({
		title : title,
		width : WWidth,
		height : WHeight,
		content : '<iframe frameborder="no" width="100%" height="100%" src='
				+ url + '/>',
		shadow : true,
		modal : true,
		closed : true,
		closable : true,
		minimizable : true,
		maximizable : false,
		collapsible : true,
		draggable : true
	});
	$win.window('open');
}


function selectOrgTree() {
	openWindow('选择组织', '/saleItemsAction!orgTreePage.jspa', 200, 300);
}

function submit() {

	
//	var ps = $("#channelId").combobox('getValue');
//	var pa = $("#saleItemsKunnr").combogrid('getValue');
//	var pb = $("#saleItemsSystem").combogrid('getValue');
//	var pq = $("#sku").combogrid('getValue');
//	
//	if (! ( ps && pa && pb && pq) ) {
//		return;
//	}
	$.messager.confirm('Confirm', '是否确认提交?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + "/saleItems/saleItemsAction!updateSaleItems.jspa";
			form.target = "hideFrame";
			form.submit();
		}		
	});		
	

}

function close() {
	window.parent.closeWindow();
}


document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	return true;
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info',function(){
		close();
		window.parent.search();
});
	}
}