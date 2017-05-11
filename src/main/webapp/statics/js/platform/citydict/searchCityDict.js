$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	
});
 
function loadGrid() {
	$('#city1').combobox(
									{
										textField : 'cityName',
										valueField : 'dictId',
										url:appUrl
														+ '/cityDictAction!searchCityDictType.jspa?citylevel='+encodeURIComponent("-1"),
//										onChange : function(r) {
//												var urlStr = appUrl
//														+ "/cityDictAction!searchCityDictType.jspa?citylevel="+encodeURIComponent("-1");
//												$("#city1").combobox("reload",
//														urlStr);
//										},
										onLoadSuccess : function() {
											$('#city1').combobox("setText", '--��ѡ��--');
										},
										onSelect:function(re){
											$('#citylevel').val(0);
											$('#parent_city_id').val(re.dictId);
											bindSecondCombox(2,re.dictId);
											search();
										}
							});
	
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/cityDictAction!searchCityDictListJson.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						idField : 'totalId',
						nowrap : true,
						remoteSort : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'parent_city_name',
									title : '�ϼ�����',
									width : setColumnWidth(0.2),
									align : 'center',
									sortable : true
								},
								{
									field : 'cityName',
									title : '��������',
									width : setColumnWidth(0.15),
									align : 'center',
									sortable : true
								},
								{
									field : 'cityNumber',
									title : '���б��',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable : true
								},
								{
									field : 'city_abbreviation',
									title : '���',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable : true
								},
//								{
//									field : 'city_org_id',
//									title : '��֯id',
//									width : setColumnWidth(0.1),
//									align : 'center',
//									sortable : true
//								},
								{
									field : 'city_fp_flag',
									title : '�Ƿ�ͨ����',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable : true,
										formatter : function(value, row, index) {
											if(value=="T"){
											return "��ͨ";
											}else {
												return "δ��ͨ";
											}
										}
								}
								,{
									field : 'citylevel',
									title : '���еȼ�',
									width : setColumnWidth(0.2),
									align : 'center',
										formatter : function(value, row, index) {
										if(value=="0"){
										return "ʡ";
										}else if(value=="1"){
										return "��";
										}else if(value=="2"){
										return "������";
										}else if(value=="3"){
										return "����";
										}else {
										return "����";
										}
												
												
										}
								},
								{
									field : 'price',
									title : '����',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, index) {
										var id = row.dictId;
										return '<img style="cursor:pointer" onclick=editCityDict('
												+ id
												+ ') title="�޸�����������Ϣ" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								createCityDict();
							}
						}, "-", {
							text : 'ɾ��',
							iconCls : 'icon-remove',
							handler : function() {
								deleteCityDict();
							}
						}, "-", {
							text : '����������Ϣ',
							iconCls : 'icon-excel',
							handler : function() {
								excel();
							}
						}, "-", {
							text : '������������Ϣ',
							iconCls : 'icon-excel',
							handler : function() {
								excel4();
							}
						}, "-"  ]
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


function initCityDict(title, url,WWidth,WHeight) {
	var url = appUrl + url;
//	var WWidth = 850;
//	var WHeight = 450;
	var $win = $("#citydictDiv")
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

function closeCityDict(){
	// �رմ���ҳ��
$("#citydictDiv").window('close');
}
function createCityDict() {
	initCityDict('����������Ϣ', '/cityDictAction!createCityDict.jspa',600,400);
}
function editCityDict(id) {
	initCityDict('�޸�������Ϣ', '/cityDictAction!editCityDict.jspa?dictId='+id,800,450);
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.cityName=encodeURIComponent($('#cityName').val());
	queryParams.parent_city_id=encodeURIComponent($('#parent_city_id').val());
	queryParams.todictId=encodeURIComponent($('#todictId').val());
	queryParams.citylevel=encodeURIComponent($('#citylevel').val());
	$('#datagrid').datagrid('load');
	$('#datagrid').datagrid('clearSelections');
	
	
}


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if(successResult){
		$.messager.alert('Tips', successResult, 'info',function(){search();});
		
	}
}

function deleteCityDict() {
	$.messager.confirm('Confirm', '�Ƿ������h������������Ϣ?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  û���б�ѡ��!');
				return;
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].dictId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action =appUrl + "/cityDictAction!deleteCityDicts.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}
//function srarchdorrow(bookid,name){
//	var WWidth = 860;
//	name=encodeURIComponent(name);
//	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
//	window.open(appUrl + "/bookManagerAction!searchBorrow.jspa?bookid="
//					+ bookid+"&&bookname="+name, "searchEventDetail", "left=" + WLeft + ",top=20"
//					+ ",width=" + WWidth + ",height="
//					+ (window.screen.height - 100)
//					+ ",toolbar=no,rolebar=no,scrollbars=yes");
//}

function bindSecondCombox(id,dictId) {
	$('#city' + id).combo({disabled:false});
	$('#city' + id).combobox({
		url:appUrl+ '/cityDictAction!searchCityDictType.jspa?citylevel='+encodeURIComponent((id-2))+'&&parent_city_id='+encodeURIComponent(dictId),
		textField : 'cityName',
		valueField : 'dictId',
		onLoadSuccess : function() {
			$('#city' + id).combobox("setText", '--��ѡ��--');
		},
		editable : false,
		onSelect : function(re) {
			var ids=id+1;
			if(id<5){
				bindSecondCombox(ids,re.dictId);
				$('#citylevel').val(ids-2);
				$('#parent_city_id').val(re.dictId);
			}else if(id==5){
				$('#citylevel').val(id-2);
				$('#todictId').val(re.dictId);
			}
			search();
		}
	});
}
function excel() {
	$.messager
			.confirm(
					'Confirm',
					'�Ƿ񵼳���ѯ��������?',
					function(r) {
						if (r) {

							var form = window.document.forms[0];
							form.action = appUrl
									+ '/city/cityDictAction!excelCityDicts.jspa';
							form.target = "hideFrame";
							form.submit();

						}
					});
}
function excel4() {
	$.messager
			.confirm(
					'Confirm',
					'�Ƿ񵼳�����������?',
					function(r) {
						if (r) {

							var form = window.document.forms[0];
							form.action = appUrl
									+ '/city/cityDictAction!excelCity4Dicts.jspa';
							form.target = "hideFrame";
							form.submit();

						}
					});
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




