$(function() {
	initData();
	// ��ʼ��������ϸ��Ϣ
	initTable();
	//��ʼ������
	initWindow();
	// ��ʼ��Ʒ������б�
	initPxList();
	// ��ʼ�����˷����嵥
	//initKHTable();
	//��ʼ��������:��Ϊ�������û�
	if('E'==viewFlag){
	  initKunnr();
	  //
	  if('U'==$('#operFlag').val()){
		  $('#kunnr_id').combogrid('setText',$('#kunnr_name').val());
		  $('#tz').val("����֪ͨ");
			//�������Ԥ�����
			initYdk($('#kunnr_num').val());
			//����ɶ����ۿ۽��
			initDzk($('#kunnr_num').val());
			//�����ͻ���ַ
			initKunnrAddress();
	  }
	}
	//��ʼ���������ͻ���ַ
	if('K'==viewFlag){
     initKunnrAddress();
	}
	//����ˣ����Ų������ܲ�����ɾ��
	if('C'==$('#orderState').val()||'E'==$('#orderState').val()||'F'==$('#orderState').val()){
		$(".datagrid-toolbar").hide();
	}

	$('#hideFrame').bind('load', promgtMsg);
});

var editRow;
var materielType;
var kunnrAddress;
function initData() {
	$.ajax({
		url : appUrl + '/ordersAction!findMaterielTypeList.jspa',
		async : false,
		success : function(data) {
			materielType = data;
		}
	});
}

// ��ʼ��table
function initTable() {
	$('#ddxq').datagrid({
		rownumbers : true,
		singleSelect : false,
		// pagination:true,
		// pageSize: 10,
		url:appUrl+'/ordersAction!findOrderListByTitle.jspa?orderId='+$('#order_id').val(),
		onClickRow : function(rowIndex, rowData) {
			if (editRow != rowIndex) {
				$(this).datagrid('endEdit', editRow)
				$(this).datagrid('beginEdit', rowIndex);
				editRow = rowIndex;
			}
			//��������
			var editor_w = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'materiel_num'});
			var mList=[];
			$.ajax({
			      url : appUrl + '/ordersAction!findMaterielList.jspa?matkl='+rowData.materiel_mvgr,
			      async : false,
			      success : function(data) {
				          mList = data;
			      }
		    });
			editor_w.target.combobox( 'loadData' , mList);
			//�����ʹ﷽
			var editor = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'address'});
			editor.target.combobox( 'loadData' , kunnrAddress);
			//�۸�
			var editor_price = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'materiel_price'});
			//�ܽ��
			var editor_up = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'order_up_fund'});
			//Ԥ���
			var editor_payMoney = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'payMoney'});
			//��������
			var editor_list_count = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'list_count'});
			editor_list_count.target.bind('change', function(){
				//�����ܽ��=�۸�*��������change��blur
				var totalMoney=editor_price.target.val()*editor_list_count.target.val()*1;
				var pay=totalMoney-editor_use_fund.target.val()*1;
				editor_up.target.numberbox('setValue',totalMoney); 
				editor_payMoney.target.numberbox('setValue',pay);  
				//���»���
				countTotalMoney();
		    });
			//���öһ�
			var editor_use_fund = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'order_use_fund'});
			editor_use_fund.target.bind('change', function(){
				//Ԥ���=�ܽ��-���öһ�
				var pay=editor_up.target.val()*1-editor_use_fund.target.val()*1;
				editor_payMoney.target.numberbox('setValue',pay); 
				//���»���
				countTotalMoney();
		    });
		},
		columns : [ [ {
			checkbox : true
		},{
			field : 'list_id',
			title : '��ϸID',
			width : 60,
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'materiel_mvgr',
			title : 'Ʒ��',
			width : 150,
			/*formatter : function(value, row, index) {
				var d = materielType.filter(function(item) {
					return item.matkl == row.matkl;
				});
				return d[0].wgbez;
			},*/
			formatter : function(value, row, index) {
				return value;
			},
			editor : {
				type : 'combobox',
				options : {
					valueField : 'matkl',
					textField : 'wgbez',
					data : materielType,
					required : true,
					editable : false,
					onSelect : function(record) {
						var index = getRowIndex(this);
						$('#ddxq').datagrid('endEdit', index);//��ֹ����ѡ���б���Ȼ��ʾ��bug
						//�ı�Ʒ��������
						$('#ddxq').datagrid('updateRow', {index:index,row:{materiel_mvgr_name:record.wgbez,materiel_num:'',materiel_name:''}});
						//���¼������ϵ��б�
						var editor_w = $('#ddxq').datagrid('getEditor', {index : index,field : 'materiel_num'});
						editor_w.target.combobox('setValue','');
						$('#ddxq').datagrid('endEdit', index);
						var mList=[];
						$.ajax({
						      url : appUrl + '/ordersAction!findMaterielList.jspa?matkl='+record.matkl,
						      async : false,
						      success : function(data) {
							          mList = data;
						      }
					    });
						editor_w.target.combobox( 'loadData' , mList);
					},
					onHidePanel : function() {
					}
				}
			}
		},{
			field : 'materiel_mvgr_name',
			title : 'Ʒ������',
			width : 150,
			hidden:true
		}, {
			field : 'materiel_num',
			title : '����',
			width : 150,
			formatter : function(value, row, index) {
				return value;
			},
			editor : {
				type : 'combobox',
				options : {
					valueField : 'matnr',
					textField : 'maktx',
					required : true,
					editable : false,
					onSelect : function(record) {
						var index = getRowIndex(this);
						//�ı�Ʒ��������
						//var new_row = $('#ddxq').datagrid("selectRow", index).datagrid("getSelected");
						//new_row.materiel_name = record.maktx;
						//$('#ddxq').datagrid('updateRow',{index:index,row:new_row});
					    $('#ddxq').datagrid('endEdit', index);
						$('#ddxq').datagrid('updateRow', {index:index,row:{materiel_name:record.maktx}});
					},
					onHidePanel : function() {}
				}
			}
		},{
			field : 'materiel_name',
			title : '��������',
			width : 150,
			hidden:false
		}, {
			field : 'materiel_price',
			title : '�۸�',
			width : 60,
			formatter : function(value, row, index) {
				return value;
			},
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision:2
				}
			}
		}, {
			field : 'list_count',
			title : '��������',
			width : 60,
			formatter : function(value, row, index) {
				return value;
			},
			editor : {
				type : 'numberbox',
				options : {
					required : true
				}
			}
		}, {
			field : 'order_up_fund',
			title : '�ܽ��',
			width : 80,
			formatter : function(value, row, index) {
				return value;
			},
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision:2,
					disabled:true
				}
			}
		}, {
			field : 'payMoney',
			title : 'Ԥ���',
			width : 80,
			formatter : function(value, row, index) {
				return value;
			},
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision:2,
					disabled:true
				}
			}
		}, {
			field : 'order_use_fund',
			title : '���öһ�',
			width : 80,
			formatter : function(value, row, index) {
				return value;
			},
			editor : {
				type : 'numberbox',
				options : {
					required : true,
					precision:2
				}
			}
		}, {
			field : 'order_give_count',
			title : '��������',
			width : 60,
			formatter : function(value, row, index) {
				return value;
			},
			editor : {
				type : 'numberbox',
				options : {
					required : true
				}
			}
		}, {
			field : 'address',
			title : '�ͻ���ַ',
			width : 150,
			formatter : function(value, row, index) {
				return value;
			},
			editor : {
					type : 'combobox',
					options : {
						valueField : 'kunnrSd',
						textField : 'address',
						data:kunnrAddress,
						required : true,
						editable : false,
						onHidePanel : function() {
						}
					}
				}
		},{
			field : 'list_type',
			title : '����',
			width : 60,
			hidden:true,
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'list_type_txt',
			title : '����',
			width : 60,
			formatter : function(value, row, index) {
				if('A'==row.list_type){
					return '��Ʒ';
				}else{
					return '����';
				}
			}
		},{
			field : 'remark',
			title : '��ע',
			width : 200,
			formatter : function(value, row, index) {
				return value;
			},
			editor : {
				type : 'validatebox',
				options : {
					required : true
				}
			}
		} ] ],
		onBeforeEdit : function(index, row) {
			//row.editing = true;
			updateActions(index);
		},
		onAfterEdit : function(index, row) {
			//row.editing = false;
			updateActions(index);
		},
		onCancelEdit : function(index, row) {
			//row.editing = false;
			updateActions(index);
		},
		toolbar : [ '-', {
			id:'addBtn',
			text : '����',
			iconCls : 'icon-add',
			handler : function() {
				add();
			}
		}, '-', {
			id:'delBtn',
			text : 'ɾ��',
			iconCls : 'icon-remove',
			handler : function() {
				del();
			}
		}, '-', {
			id:'removeBtn',
			text : '��շ���',
			iconCls : 'icon-reload',
			handler : function() {
				remove();
			}
		} ]
	})
}

// ��ʼ��Ʒ��
function initPxList() {
	$('#tt').datagrid({
		url : appUrl + '/ordersAction!findMaterielTypeList.jspa',
		fitColumns : true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'kbetr',
			hidden : true
		}, {
			field : 'matkl',
			title : 'Ʒ�����',
			width : 70
		}, {
			field : 'wgbez',
			title : 'Ʒ������',
			width : 130
		} ] ]
	});
}

// ��ʼ�����˷����嵥
function initKHTable() {
	var kunnrNum=$('#kunnr_num').val();
	if(undefined==kunnrNum||null==kunnrNum||""==kunnrNum){
		$.messager.alert('Tips', "����ѡ������!", 'warning');
		return;
	}
	$('#khfdqd').datagrid({
		rownumbers : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		url : appUrl + '/ordersAction!searchReturnLists.jspa?kunnrNum='+kunnrNum,
		columns : [ [ {
			field : 'materielName',
			title : '����',
			width : 160
		}, {
			field : 'cataName',
			title : 'Ʒ��',
			width : 180
		}, {
			field : 'yearPayPrice',
			title : '2015�������',
			width : 100
		}, {
			field : 'totalBouns',
			title : '�����ܽ���',
			width : 80
		}, {
			field : 'monthAsses',
			title : '�¶ȿ���',
			width : 80
		}, {
			field : 'quarterAsses',
			title : '���ȿ���',
			width : 80
		}, {
			field : 'yearAsses',
			title : '��ȿ���',
			width : 80
		}, {
			field : 'yearNakedPrice',
			title : '2015�����',
			width : 80
		} ] ]
	})
}
/**
 * ��ʼ��������
 */
function initKunnr(){
$('#kunnr_id').combogrid({
	panelHeight : 250,
	panelWidth : 380,
	pagination : true,
	method : 'post',
	editable:false,
	singleSelect : true,
	url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+orgId+'&bhxjFlag='+'C',
	idField : 'kunnr',
	textField : 'name1',
	columns : [[ {
		field : 'kunnr',
		title : '�����̱��',
		width : 120
	}, {
		field : 'name1',
		title : '����',
		width : 200
	} ] ],
	toolbar : '#toolbarKunnr',
	onSelect : function(index,recode) {
		$('#kunnr_num').val(recode.kunnr);
		$('#kunnrId').val(recode.kunnr);
		$('#kunnr_name').val(recode.name1);
		$('#tz').val("����֪ͨ");
		//�������Ԥ�����
		initYdk(recode.kunnr);
		//����ɶ����ۿ۽��
		initDzk(recode.kunnr);
		//�����ͻ���ַ
		initKunnrAddress();
	}
});
}
/**
 * �ͻ���ַ
 */
function initKunnrAddress(){
	var kunnrNum=$('#kunnr_num').val();
	$.ajax({
		url : appUrl + '/ordersAction!getKunnrAddress.jspa?kunnrNum='+$('#kunnr_num').val(),
		async : false,
		success : function(data) {
			kunnrAddress = data;
		}
	});
	/*$('#shdz').combogrid({
	panelHeight : 250,
	panelWidth : 400,
	pagination : true,
	method : 'post',
	singleSelect : true,
	url :appUrl+ '/kunnrAddressAction!kunnrAddressSearch.jspa?kunnrId='+kunnrNum,
	idField : 'kunnrSd',
	textField : 'address',
	columns : [[ {
		field : 'kunnrSd',
		title : '�ʹ﷽����',
		width : 100
	},{
		field : 'address',
		title : '��ϸ��ַ',
		width : 200
	}, {
		field : 'xzAddress',
		title : '��������',
		width : 200
	} ] ]
});
*/}
// ��Ӷ�����ϸ
function addItem() {
	var nodes = $('#tt').datagrid('getChecked');
	// ѡ����ϸ�����뵽�����
	for (var i = 0; i < nodes.length; i++) {
		addOneItem(nodes[i]);
	}
	$('#winAdd').window('close');
}
//������ϸ
function addOneItem(node) {
	//��Ʒ
	$('#ddxq').datagrid('appendRow', {
		  list_id :'',
    materiel_mvgr :node.matkl,
materiel_mvgr_name :node.wgbez,
     materiel_num :'',
    materiel_name :'',
   materiel_price :node.kbetr,
       list_count :0,
    order_up_fund :0,
         payMoney :0,
   order_use_fund :0,
 order_give_count :0,
          address :'',
        list_type :'A',
           remark :''
	});
	//��Ʒ
	$('#ddxq').datagrid('appendRow', {
		  list_id :'',
  materiel_mvgr :node.matkl,
materiel_mvgr_name :node.wgbez,
   materiel_num :'',
  materiel_name :'',
 materiel_price :node.kbetr,
     list_count :0,
  order_up_fund :0,
       payMoney :0,
 order_use_fund :0,
order_give_count :0,
        address :'',
      list_type :'B',
         remark :''
	});
}

// �������˷����嵥
function openKHWin() {
	var kunnrNum=$('#kunnr_num').val();
	if(undefined==kunnrNum||null==kunnrNum||""==kunnrNum){
		$.messager.alert('Tips', "����ѡ������!", 'warning');
		return;
	}
	$('#winKHFDQD').window('open');
	initKHTable();//��������
}

// ��ʼ����������
function initWindow() {
	$('#winAdd').window({
		title : '����Ʒ��',
		collapsible : false,
		minimizable : false,
		maximizable : false,
		resizable : false,
		shadow : false,
		closed : true
	});

	$('#winKHFDQD').window({
		title : '���˷����嵥',
		collapsible : false,
		minimizable : false,
		maximizable : false,
		resizable : false,
		shadow : false,
		closed : true
	});
}
// ����Ʒ���
function add() {
	var kunnrNum=$('#kunnr_num').val();
	if(undefined==kunnrNum||null==kunnrNum||""==kunnrNum){
		$.messager.alert('Tips', "����ѡ������!", 'warning');
		return;
	}
	$('#winAdd').window('open');
}

/**
 * ��������������ѯ
 * 
 * @param name1
 */
function searcherKunnr(val) {
	var queryParams = $('#kunnr_id').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(val);
	$('#kunnr_id').combogrid("grid").datagrid('reload');
}
/**
 * Ʒ��
 * @param val
 */
function searcherPx(val){
	var queryParams = $('#tt').datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(val);
	$('#tt').datagrid('reload');
}
//�������Ԥ�����
function initYdk(kunnr) {
	$.ajax({
		url : appUrl + '/ordersAction!getKunnrUsedPay.jspa?kunnrNum='+kunnr,
		async : false,
		data:kunnr,
		success : function(data) {
			$('#kyykkje').val(data);
		}
	});
}
//����ɶ����ۿ۽��
function initDzk(kunnr) {
	$.ajax({
		url : appUrl + '/ordersAction!getKunnrDiscountAmount.jspa?kunnrNum='+kunnr,
		async : false,
		success : function(data) {
			$('#kdxzkje').val(data);
		}
	});
}
//�����ܶ���öһ���Ԥ���
function countTotalMoney(){
	var rows = $('#ddxq').datagrid('getRows');
	var total=0;
	var use=0;
	for ( var i = 0; i < rows.length; i++) {
		var index = $('#ddxq').datagrid('getRowIndex', rows[i]);
		//���öһ�
		var editor_use_fund = $('#ddxq').datagrid('getEditor', {index : index,field : 'order_use_fund'});
		if(null==editor_use_fund){
			use=use+rows[i].order_use_fund*1;
		}else{
			use=use+editor_use_fund.target.val()*1;
		}  
		//�ܽ��
		var editor_up = $('#ddxq').datagrid('getEditor', {index : index,field : 'order_up_fund'});
		if(null==editor_up){
			total =total+rows[i].order_up_fund*1;
		}else{
			total =total+editor_up.target.val()*1;
		}
	}
	var pay=(total-use)*1;
	$('#ddzje').val(total);
	$('#sykdxzkje').val(use);
	$('#syydkje').val(pay);
}
/**
 * ɾ����
 */
function del(){
	var rows = $('#ddxq').datagrid('getSelections');  //��ȡѡ����  
    if (rows == '') {
		$.messager.alert('Tips', 'δѡ��������!');
		return;
	}
	$.messager.confirm('ȷ��','ȷ��ɾ��ѡ��������?',function(r){  
        if(r){  
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				if(null!=rows[i].list_id&&undefined!=rows[i].list_id&&''!=rows[i].list_id){
					ids.push(rows[i].list_id);
				}
				//ɾ����Ŀ
				var index = $('#ddxq').datagrid('getRowIndex', rows[i]);
	        	$('#ddxq').datagrid('deleteRow', index);
			}
			$("#ids").val(ids);
       
	    //ɾ��
        if(ids.length>0){
		  $.messager.progress();
		  var form = window.document.forms[0];
	      form.action = appUrl+'/ordersAction!deleteOrderDetail.jspa';
		  form.target = "hideFrame";
		  form.submit();
		}
     } 
  });
}
function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

function updateActions(index) {
	$('#ddxq').datagrid('updateRow', {
		index : index,
		row : {}
	});
}
/**
 * ��շ���
 */
function remove(){
	 var rows = $('#ddxq').datagrid('getRows');  //��ȡ��  
     /*if (rows == '') {
			$.messager.alert('Tips', 'δѡ��������!');
			return;
	 }*/
	$.messager.confirm('ȷ��','ȷ����շ���?',function(r){  
        if(r){  
			for ( var i = 0; i < rows.length; i++) {
				var index = $('#ddxq').datagrid('getRowIndex', rows[i]);
				$('#ddxq').datagrid('beginEdit', index);
				//���öһ�
				var editor_use_fund = $('#ddxq').datagrid('getEditor', {index : index,field : 'order_use_fund'});
				//Ԥ���
				var editor_payMoney = $('#ddxq').datagrid('getEditor', {index : index,field : 'payMoney'});
				editor_use_fund.target.numberbox('setValue',0);  
				//�ܽ��
				var editor_up = $('#ddxq').datagrid('getEditor', {index : index,field : 'order_up_fund'});
			    editor_payMoney.target.numberbox('setValue',editor_up.target.val()*1);  
			}
			//���»���
			countTotalMoney();
        } 
  });
}
/**
 * ����ݸ塢�ύ����
 */
function submitForm(flag){
	//C���棬S�ύ
	var msgTitle='�ύ';
	if('A'==flag){
		msgTitle='����';
	}
    if(null==$('#kunnr_num').val()||undefined==$('#kunnr_num').val()||''==$('#kunnr_num').val()){
    	$.messager.alert('Tips', '����д������!');
		return;
    }
    if(!$('#djrq').datebox('isValid')){
    	$.messager.alert('Tips', '����д��������!');
		return;
    }
    var rows = $('#ddxq').datagrid('getSelections');  //��ȡ��  
    if (rows == '') {
		$.messager.alert('Tips', 'δѡ��������!');
		return;
	}
	//$('#ddxq').datagrid('selectAll');
	$.messager.confirm('ȷ��','ȷ��'+msgTitle+'ѡ�е�������?',function(r){  
        if(r){  
            var orderDetailList=[];
			for ( var i = 0; i < rows.length; i++) {
				$('#ddxq').datagrid('endEdit', i);
				orderDetailList.push({
					"list_id" : rows[i].list_id,
					"materiel_mvgr" : rows[i].materiel_mvgr,
					"materiel_mvgr_name" : rows[i].materiel_mvgr_name,
					"materiel_num" : rows[i].materiel_num,
					"materiel_name" : rows[i].materiel_name,
					"materiel_price" : rows[i].materiel_price,
					"list_count" : rows[i].list_count,
					"order_up_fund" :rows[i].order_up_fund,
					"payMoney" : rows[i].payMoney,
					"order_use_fund" : rows[i].order_use_fund,
					"list_type":rows[i].list_type,
					"order_give_count" :rows[i].order_give_count,
					"order_give_fund"  :('B'==rows[i].list_type?rows[i].order_give_count*rows[i].materiel_price*1:0),
					"address" :rows[i].address,
					"remark":rows[i].remark,
					"list_state":'U'
				});
				}
			
        $("#orderDetailList").val($.toJSON(orderDetailList));
		$.messager.progress();
		var form = window.document.forms[0];
	    form.action = appUrl+'/ordersAction!editOreder.jspa?orderState='+flag;
		form.target = "hideFrame";
		form.submit();
      }
  });
}
function windowsClose() {
	window.parent.closeWindow();
	window.parent.search();//ˢ�¸�����
}

function windowRefresh() {
	window.parent.refreshWindow();
}
/**
 * ������Ϣ
 */
function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips',successResult+'�Ƿ�رձ༭����?',function(r){  
	        if(r){ 
	        	windowsClose();
	        }else{
	        	//ˢ��
	        	windowRefresh();
	        }
         });
   }
}