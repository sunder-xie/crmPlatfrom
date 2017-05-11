var flag;
var orgflag=1;
$(document).ready(function() {
	
	loadGrid();
	loadCustKunnr();
	$('#hideFrame').bind('load', promgtMsg);
	// �ͻ����� ����
	var type = $('#type').val();
	if (type == '') {
		type = 'Z';
	}
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa',
		valueField : 'channelId',
		textField : 'channelName'
	});

});
/**
 * ����������������
 */
function loadCustKunnr() {
	// ���׿ͻ�
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
			title : '�ͻ����',
			width : 120
		}, {
			field : 'custName',
			title : '�ͻ�����',
			width : 200
		} ] ],
		toolbar : '#toolbarParent'
	});
	$('#custKunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/master!kunnrSearchFromMaster.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ /*
					 * [ { field : 'ck', checkbox : true } ],
					 */[ {
			field : 'kunnr',
			title : '�����̱��',
			width : 120
		}, {
			field : 'name1',
			title : '����',
			width : 200
		} ] ],
		toolbar : '#toolbarKonzs'
	});
}

/*******************************************************************************
 * �������������б�
 */

var editorline = -1;
function onClickCell(index, field) {

	//	var row =  $("#datagrid").datagrid('getSelected');
	//alert(JSON.stringify(row));
	//alert(row.scale);

	if (editorline == -1) {
		$('#datagrid').datagrid('beginEdit', index);
		editorline = index;
	} else {
		$('#datagrid').datagrid('endEdit', editorline);
		$('#datagrid').datagrid('beginEdit', index);
		editorline = index;
	}

}

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						 url : appUrl + '/master!getSupervisorItems.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						onClickCell : onClickCell,
						striped : true,
						queryParams : {
							orgId : $("#orgId").val(),
							bhxjFlag : $("#bhxjFlag").val()
						},
						frozenColumns : [ [
								{
									field : 'price',
									title : '����',
									width : setColumnWidth(0.18),
									hidden : true,
									align : 'center',
									formatter : function(value, row, index) {
										var kunnr = row.kunnr;
										var status = row.status;
										var license = " <a href='#' onclick=viewLicense('"
												+ kunnr + "')>֤��</a>";

										var scan = "|<a href='#' onclick=viewInfo('"
												+ kunnr + "')>����</a>";
										var edit = status == 1 ? "|<a href='#' onclick=editInfo('"
												+ kunnr + "')>�޸�ҵ����ϵ</a>"
												: "&nbsp;";

										return license + scan + edit;
									}
								}, {
									field : 'checkId',
									title : '�����id',
									width : setColumnWidth(0.05),
									hidden : true,
									sortable : true
								}, {
									field : 'custId',
									title : '�ŵ�ID',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'custName',
									title : '�ŵ�',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'orgName',
									title : '�ŵ���֯',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'channelName',
									title : '����',
									width : setColumnWidth(0.05),
									sortable : true
								}, {
									field : 'createName',
									title : '����������',
									width : setColumnWidth(0.08),
									sortable : true
								}, {
									field : 'createTime',
									title : '����ʱ��',
									width : setColumnWidth(0.05),
									sortable : true
								} ] ],
						columns : [ [ {
							title : '����',
							colspan : 45
						}, {
							title : '3����',
							colspan : 36
						}, {
							title : '��ͥ����װ',
							colspan : 12
						}, {
							title : '���',
							colspan : 6
						}, {
							title : '���װ',
							colspan : 6
						} , {
							title : '��ͥ������װ',
							colspan : 1
						} ], [
{field:'checkId1',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue1',title:'Ҭ��ԭζ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice1',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice1',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId2',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue2',title:'Ҭ������',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice2',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice2',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId3',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue3',title:'Ҭ����ݮ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice3',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice3',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId4',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue4',title:'Ҭ������',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice4',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice4',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId5',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue5',title:'Ҭ������',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice5',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice5',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId6',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue6',title:'Ҭ���ɿ���',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice6',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice6',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId7',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue7',title:'��Բ����',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice7',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice7',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId8',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue8',title:'�춹',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice8',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice8',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId9',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue9',title:'֥ʿ����',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice9',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice9',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId10',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue10',title:'����Ҭ��',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice10',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice10',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId11',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue11',title:'�����ɲ�',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice11',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice11',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId12',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue12',title:'ԭ֭�춹',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice12',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice12',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId13',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue13',title:'â������',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice13',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice13',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId14',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue14',title:'��ݮ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice14',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice14',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId15',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue15',title:'ѩ��Ҭ��',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice15',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice15',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId16',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue16',title:'ԭζ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice16',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice16',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId17',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue17',title:'����',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice17',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice17',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId18',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue18',title:'��ݮ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice18',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice18',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId19',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue19',title:'����',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice19',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice19',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId20',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue20',title:'��Բ����',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice20',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice20',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId21',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue21',title:'�춹',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice21',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice21',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId22',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue22',title:'֥ʿ����',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice22',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice22',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId23',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue23',title:'����Ҭ��',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice23',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice23',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId24',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue24',title:'�����ɲ�',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice24',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice24',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId25',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue25',title:'â������',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice25',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice25',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId26',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue26',title:'��ݮ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice26',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice26',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId27',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue27',title:'ѩ��Ҭ��',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice27',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice27',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId28',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue28',title:'Ҭ��16��װ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice28',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice28',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId29',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue29',title:'Ҭ��12��װ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice29',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice29',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId30',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue30',title:'��ζ16��װ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice30',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice30',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId31',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue31',title:'��ζ12��װ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice31',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice31',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId32',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue32',title:'Ҭ��12��װ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice32',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice32',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId33',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue33',title:'��Ʒ���װ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice33',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice33',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId34',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue34',title:'Ҭ������',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice34',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice34',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId35',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue35',title:'��ζ��Ʒ',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice35',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice35',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},
{field:'checkId36',title:'������ζ������ID',   width:setColumnWidth(0.05),sortable:true,hidden:true},{field:'checkValue36',title:'��ͥ���',   width:setColumnWidth(0.05),editor:{type:'checkbox',options:{on:'Y',off:'N'}},sortable:true},{field:'minPrice36',title:'��ͼ۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true},{field:'maxPrice36',title:'��߼۸�',   width:setColumnWidth(0.05),editor:'numberbox',sortable:true}

						] ],
						toolbar : [ "-", {
							text : '��������',
							iconCls : 'icon-download',
							handler : function() {
								excel();
							}
						}, "-", {
							text : 'ģ������',
							iconCls : 'icon-excel',
							handler : function() {
								exportMould();
							}
						}, "-", {
							text : '��������',
							iconCls : 'icon-add',
							handler : function() {
								importCheckItem();
							}
						}, "-" , {
							text : '�����޸�',
							iconCls : 'icon-edit',
							handler : function() {
								saveChagCheckItem();
							}
						}, "-", {
							text : '�����̻�����дY �� N'
						
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

/**
 *  ���� 
 */

function  addLineCheckItem(){
	initMaintAccount(false,400,200,'����������������', '/master!!toAddSupervisorItems.jspa');
}

/**
 * �����޸�
 */
function saveChagCheckItem(){

	$('#datagrid').datagrid('endEdit', editorline);
	var rows = $('#datagrid').datagrid('getChanges');
	
	
	for(var i=0;i<rows.length;i++){
		rows[i].channelName="";
		rows[i].custName="";
		rows[i].orgName="";
		
	}
	
	if (rows.length>0){
		
		$.ajax({
			   type: "POST",
			   url: appUrl + '/master!saveChagCheckItem.jspa',
			   data: {"supervisorLineCheckItemListjson":$.toJSON(rows)},
			   success: function(data){
				   $("#datagrid").datagrid('load');
			   }
			});
		
	}
	else 
		$.messager.alert('Tips', 'û�и��ĵ�����!', 'warning');
	
	//alert(rows.length+' rows are changed!');
}



//excel�����ն˿ͻ�����
function excel() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ������?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/master!itemsExport.jspa';
			form.submit();
			$.messager.alert('Tips', '���ڵ���,���Ժ�!', 'warning');
		}
	});
}

/**
 *  ��������
 */

function importCheckItem() {

	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>ѡ���ļ��ϴ���</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	openImportDialog('����Ŀ��', html);
}

var mask_;
/* �򿪵�����ĿExcel�Ի��� */
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '��������',
					html : "<div id='import'>"
							+ "</br>"
							+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" + "</div>"
				}).appendTo('body');
	}
	$('#excelDialog').dialog(
			{
				modal : true,
				resizable : false,
				dragable : false,
				closable : false,
				open : function() {
					$('#excelDialog').css('padding', '0.8em');
					$('#excelDialog .ui-accordion-content').css('padding',
							'0.4em').height($('#excelDialog').height() - 100);
				},
				buttons : [ {
					text : 'ȷ��',
					handler : function() {
						var file = document.getElementById('uploadFile').value;
						if (/^.+\.(csv|CSV)$/.test(file)) {
							$.messager.progress();
							var action = '';
							action = appUrl + "/master!ImportItems.jspa";
							var form = document.getElementById('fileForm');

							form.action = action;
							// "eventId="+processInstanceId;
							form.target = "hideFrame";
							form.submit();
						} else {
							$.messager.alert("��ʾ", "�뵼��csv�ļ�");
						}
						$.messager.progress('close');

					}
				}, {
					text : 'ȡ��',
					handler : function() {
						window.location.href = window.location.href;
						$('#excelDialog').dialog('close');

					}
				} ],

				width : document.documentElement.clientWidth * 0.28,
				height : document.documentElement.clientHeight * 0.50
			});
}

/**
 * ģ������
 */
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.custNumber = $("#custNumber").val();
	queryParams.custName = encodeURIComponent($("#custName").val());
	queryParams.channelId = $('#channelId').combobox("getValue");
	queryParams.orgId = $("#orgId").val();
	queryParams.stationUserId = $("#stationUserId").val();
	queryParams.contactName = encodeURIComponent($("#contactName").val());
	queryParams.custState = $("#custState").val();// $("#custState").combobox("getValue");
	queryParams.custKunnr = $("#custKunnr").combogrid("getValue");
	queryParams.custParent = $("#custParent").combogrid("getValue");
	queryParams.custType = $("#custType").val();
	queryParams.createOrgId = $("#createOrgId").val();
	queryParams.createDateStart = $("#createDateStart").datebox("getValue");
	queryParams.createDateEnd = $("#createDateEnd").datebox("getValue");
	$("#datagrid").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function viewInfo(id) {
	openWindow("�ն��ŵ���Ϣ�鿴", "/customerAction!viewCustomer.jspa?custId=" + id,
			1000, 480);
}

function editInfo(id, state, type) {
	openWindow("�ͻ���Ϣ�޸�", "/customerAction!toUpdateCustomer.jspa?custId=" + id
			+ '&type=' + type, 1100, 480);
}
/**
 * �ر��ն��ŵ�
 * 
 * @param id
 */
function freezeKunnr(id, state) {
	var ids = [];
	ids.push(id);
	$("#ids").val(ids);
	if (state == "�ѹػ�") {
		$.messager.alert('Tips', '�ͻ�״̬Ϊ�ѹر�״̬�������ظ�������', 'warning');
		return;
	}
	$.messager.confirm('Confirm', '�Ƿ�ȷ���ر��ն��ŵ�?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/customerAction!closeCustomer.jspa?custState1=' + '4';
			form.target = "hideFrame";
			form.submit();

		}
	});
}

/**
 * ������������������ѯ
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}
// ���׿ͻ�������ѯ
function searcherParent(name1) {
	var queryParams = $('#custParent').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#custParent').combogrid("grid").datagrid('reload');
}
// �������ڶ���
function openWindow(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#hiddenWin")
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');

}
// �رմ���
function closeWindow() {
	$("#hiddenWin").window('close');
}
/**
 * ����֯��
 */
function selectOrgTree() {
	orgflag=1;
	openWindow('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * ����֯��
 */
function selectOrgTree0() {
	orgflag=0;
	openWindow('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * ��֯��ѡ��֯���ص������
 * 
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	if (flag == 1) {
		document.getElementById('orgId01').value = selectedId;
		document.getElementById('orgName01').value = selectedName;
	} else {
		if (orgflag==1){
		    document.getElementById('orgId').value = selectedId;
		    document.getElementById('orgName').value = selectedName;
		}else{
		    document.getElementById('createOrgId').value = selectedId;
		    document.getElementById('createOrgName').value = selectedName;
		}
	}
	$('#custKunnr').combogrid(
			{
				url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='
						+ $('#orgId').val() + '&bhxjFlag=' + 'C'
			});
	
		$('#custParent').combogrid(
				{
					url : appUrl
							+ '/customerAction!getTwoLevelCustomer.jspa?orgId='
							+ $('#orgId').val()
				});
}
/**
 * ��֯��ѡ����֮��ر�
 */
function closeMaintEvent() {
	closeWindow();
}
/**
 * ѡ��ҵ��
 */
function selectOrgTree4Station() {
	var node = $('#orgId').val();
	var orgName = encodeURIComponent($('#orgName').val());
	openWindow('ѡ��ҵ�����', '/batChangeAction!selectOrgTree4Station.jspa?node='
			+ node + '&orgName=' + orgName, 520, 460);
}
/**
 * ҵ��ѡ��֮��ر�
 */
function closeOrgTree() {
	closeWindow();
}
/**
 * ����֯��
 */
function selectOrgTree1() {
	flag = 1;
	openWindow('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}

/*
 * ����Աѡ����
 */
function selectUser() {
	openWindow('ѡ��ҵ����Ա', '/batChangeAction!selectOrgTreeUser.jspa', 550, 350);
}
/*
 * ���о�������ѡ�񷵻�ֵ
 */
function returnUser(userId, userName) {
	document.getElementById('stationUserId01').value = userId;
	document.getElementById('stationUserName01').value = userName;
}


function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table><tr>'
			+ '<td class="head" noWrap>����ѡ��</td>'
			+ '<td><select id="type" name="type" style="width:130px">'
			+ '<option value="Z" checked>--�ն��ŵ�--</option>'
			+ '<option value="T" selected>--���׿ͻ�--</option>'
			+ '</select></td></tr>'
			+ '<tr><td class="head" noWrap>ģ������</td>'
			+ '<td><a style="color:blue"   onclick=javascript:exportCustomerMouldCsv();> 1������ģ��</a></td></tr>'
			/*
			 * + '<tr><td></td><td><a class="easyui-linkbutton"
			 * style="color:blue" data-options="plain:true,iconCls:"icon-excel""
			 * onclick=javascript:exportCustomerHelps();>2�����ؿͻ�����ĸ�����Ϣ����</a></td></tr>' + '<tr><td class="head" noWrap>������֯</td><td>' + '<input
			 * class="easyui-validatebox" id="orgName01" name="orgName01"
			 * readonly/>' + '<button onclick="javascript:selectOrgTree1()">ѡ��</button>' + '<input
			 * type="hidden" id="orgId01" name="orgId01"/></td></tr>' + '<tr><td class="head" noWrap>��˾ҵ������</td><td>' + '<input
			 * class="easyui-validatebox" id="stationUserName01"
			 * name="stationUserName01" readonly/>' + '<button
			 * onclick="javascript:selectUser()">ѡ��</button>' + '<input
			 * type="hidden" id="stationUserId01" name="stationUserId01"/></td></tr>'
			 */
			+ '<tr><td class="head" noWrap>��������</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importCustomerCsv('��������', html);
}
// csv�����ն��ŵ굼��ģ��
function exportCustomerMouldCsv() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ������ģ��?', function(r) {
		if (r) {
			var form = window.document.forms[0];
				//document.getElementById("fileForm");
			form.action = appUrl
					+ '/customerAction!exportMouldCsvWithCons.jspa';
			form.submit();
		}
	});

}
// excel�����ն��ŵ굼�븨����Ϣ
function exportCustomerHelps() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ�������ظ�����Ϣ?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/customerAction!exportCustomerHelps.jspa';
			form.submit();
		}
	});
}
// ���������ն��ŵ���Ϣ
function importCustomerCsv(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : 'ѡ���ϴ��ļ�',
					html : "<div id='import'>"
					// + "</br>"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" // +
							// "</div>"
				}).appendTo('body');
	} else {
		// $('#import').html(html);
	}
	$('#excelDialog')
			.dialog(
					{
						modal : true,
						resizable : false,
						dragable : false,
						closable : false,
						open : function() {
							$('#excelDialog').css('padding', '0.8em');
							$('#excelDialog .ui-accordion-content').css(
									'padding', '0.4em').height(
									$('#excelDialog').height() - 100);
						},
						buttons : [
								{
									text : 'ȷ��',
									handler : function() {
										/*
										 * if ($('#orgId01').val() == '' ||
										 * $('#orgId01').val() == undefined) {
										 * $.messager.alert("��ʾ", "��ѡ��������֯");
										 * return; } if
										 * ($('#stationUserId01').val() == '' ||
										 * $('#stationUserId01').val() ==
										 * undefined) { $.messager.alert("��ʾ",
										 * "��ѡ��ҵ������"); return; }
										 */
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/customerAction!importCustomerCsv.jspa";
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("��ʾ", "�뵼��csv�ļ�");
										}

									}
								}, {
									text : 'ȡ��',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.35,
						height : document.documentElement.clientHeight * 0.55
					});
}

/*
 * function importCustomerCsv(){ $.messager.confirm('Confirm', '�Ƿ�ȷ�����������ն��ŵ�?',
 * function(r) { if (r) { var form = window.document.forms[0]; form.action =
 * appUrl + '/customerAction!importCustomerCsv.jspa'; form.submit(); } }); }
 */
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
function clearFormValue() {
	// document.forms[0].reset();
	clearValue();
	$('#custParent').combogrid('setValue', '');
	$('#custKunnr').combogrid('setValue', '');
	$('#channelId').combobox('setValue', '');
	$('#custState').val('1');
	$('#createOrgId').val('');
	$('#createDateStart').datebox('setValue','');
	$('#createDateEnd').datebox('setValue','');
}
/**
 * ҳ�淵����ʾ��Ϣ
 */
function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		// search();
		$.messager.alert('Tips', successResult, 'info');
		$('#datagrid').datagrid('reload');

	}
}
/**
 *  ģ�嵼��
 */
function exportMould() {
	$.messager.alert('Tips', "��Ҫ���˵Ŀ�ζ��Ӧ���棬��Y��N����Ҭ��ԭζ��Ҫ���ˣ���дֵΪY. ", 'info');
	var form = window.document.forms[0];
//	form.action = appUrl + '/master!exportMouldCsv.jspa';
	form.action = appUrl + '/master!exportMouldCsvWithCons.jspa';
	form.target = "hideFrame";
	form.submit();
}
/**
 *  ��������excel
 */

function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl + '/kunnrBusinessAction!exportForExcel.jspa';
	form.target = "hideFrame";
	form.submit();
}