$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : 'Ԥ�����϶����б�',
						url : appUrl + '/goalAction!searchMatterPrice.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'matter',
									title : 'Ԥ��ھ�����',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'bezei',
									title : 'Ԥ��ھ�����',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'price',
									title : '����(Ԫ)',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.price * 1;
										return s.toFixed(6);
									}
								},
								{
									field : 'startDate',
									title : '��Ч��ʼ����',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(v) {
										return v;
									}
								},
								{
									field : 'endDate',
									title : '��Ч��ֹ����',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(v) {
										return v;
									}
								},
								{
									field : 'remark',
									title : '��ע',
									width : setColumnWidth(0.2),
									align : 'center',
									formatter : function(v) {
										return v;
									}
								},
								{
									field : 'oper',
									title : '����',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value, row, index) {
										return '<img style="cursor:pointer" onclick="updatePrice('
												+ row.matPriceId
												+ ')" title="�޸�" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';

									}
								} ] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								addPrice();
							}
						}, "-" ]
					});

	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.matter = encodeURIComponent($("#matter").val());
	queryParams.bezei = encodeURIComponent($("#bezei").val());
	queryParams.remark = encodeURIComponent($("#remark").val());
	$("#datagrid").datagrid('load');
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});
	}
}
/**
 * �޸�
 */
function updatePrice(id) {
	initMaint('���϶����޸�', '/goalAction!toCreateOrUpdatePrice.jspa?type='+'update'+'&id='+id, 550, 400);
}
/**
 * ����
 */
function addPrice() {
	initMaint('���϶��۴���', '/goalAction!toCreateOrUpdatePrice.jspa?type='+'insert', 550, 400);
}

function initMaint(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maint")
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
						minimizable : true,
						maximizable : false,
						collapsible : true,
						draggable : true
					});
	$win.window('open');
}


function closeMain() {
$("#maint").window('close');
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};