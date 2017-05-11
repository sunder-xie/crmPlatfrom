$(function() {
	initData();
	// 初始化订单详细信息
	initTable();
	//初始化窗口
	initWindow();
	// 初始化品项加载列表
	initPxList();
	// 初始化考核返点清单
	//initKHTable();
	//初始化经销商:不为经销商用户
	if('E'==viewFlag){
	  initKunnr();
	  //
	  if('U'==$('#operFlag').val()){
		  $('#kunnr_id').combogrid('setText',$('#kunnr_name').val());
		  $('#tz').val("政策通知");
			//计算可用预打款金额
			initYdk($('#kunnr_num').val());
			//计算可兑现折扣金额
			initDzk($('#kunnr_num').val());
			//加载送货地址
			initKunnrAddress();
	  }
	}
	//初始化经销商送货地址
	if('K'==viewFlag){
     initKunnrAddress();
	}
	//已审核，已排产，不能操作增删改
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

// 初始化table
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
			//加载物料
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
			//加载送达方
			var editor = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'address'});
			editor.target.combobox( 'loadData' , kunnrAddress);
			//价格
			var editor_price = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'materiel_price'});
			//总金额
			var editor_up = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'order_up_fund'});
			//预打款
			var editor_payMoney = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'payMoney'});
			//需求数量
			var editor_list_count = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'list_count'});
			editor_list_count.target.bind('change', function(){
				//计算总金额=价格*需求数量change，blur
				var totalMoney=editor_price.target.val()*editor_list_count.target.val()*1;
				var pay=totalMoney-editor_use_fund.target.val()*1;
				editor_up.target.numberbox('setValue',totalMoney); 
				editor_payMoney.target.numberbox('setValue',pay);  
				//重新汇总
				countTotalMoney();
		    });
			//费用兑换
			var editor_use_fund = $('#ddxq').datagrid('getEditor', {index : rowIndex,field : 'order_use_fund'});
			editor_use_fund.target.bind('change', function(){
				//预打款=总金额-费用兑换
				var pay=editor_up.target.val()*1-editor_use_fund.target.val()*1;
				editor_payMoney.target.numberbox('setValue',pay); 
				//重新汇总
				countTotalMoney();
		    });
		},
		columns : [ [ {
			checkbox : true
		},{
			field : 'list_id',
			title : '明细ID',
			width : 60,
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'materiel_mvgr',
			title : '品项',
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
						$('#ddxq').datagrid('endEdit', index);//防止下拉选择，列表依然显示的bug
						//改变品项名称列
						$('#ddxq').datagrid('updateRow', {index:index,row:{materiel_mvgr_name:record.wgbez,materiel_num:'',materiel_name:''}});
						//重新加载物料的列表
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
			title : '品项名称',
			width : 150,
			hidden:true
		}, {
			field : 'materiel_num',
			title : '物料',
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
						//改变品项名称列
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
			title : '物料描述',
			width : 150,
			hidden:false
		}, {
			field : 'materiel_price',
			title : '价格',
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
			title : '需求数量',
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
			title : '总金额',
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
			title : '预打款',
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
			title : '费用兑换',
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
			title : '搭赠数量',
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
			title : '送货地址',
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
			title : '类型',
			width : 60,
			hidden:true,
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'list_type_txt',
			title : '类型',
			width : 60,
			formatter : function(value, row, index) {
				if('A'==row.list_type){
					return '本品';
				}else{
					return '搭赠';
				}
			}
		},{
			field : 'remark',
			title : '备注',
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
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				add();
			}
		}, '-', {
			id:'delBtn',
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				del();
			}
		}, '-', {
			id:'removeBtn',
			text : '清空费用',
			iconCls : 'icon-reload',
			handler : function() {
				remove();
			}
		} ]
	})
}

// 初始化品项
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
			title : '品类编码',
			width : 70
		}, {
			field : 'wgbez',
			title : '品类名称',
			width : 130
		} ] ]
	});
}

// 初始化考核返点清单
function initKHTable() {
	var kunnrNum=$('#kunnr_num').val();
	if(undefined==kunnrNum||null==kunnrNum||""==kunnrNum){
		$.messager.alert('Tips', "请先选择经销商!", 'warning');
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
			title : '物料',
			width : 160
		}, {
			field : 'cataName',
			title : '品项',
			width : 180
		}, {
			field : 'yearPayPrice',
			title : '2015财年打款价',
			width : 100
		}, {
			field : 'totalBouns',
			title : '考核总奖金',
			width : 80
		}, {
			field : 'monthAsses',
			title : '月度考核',
			width : 80
		}, {
			field : 'quarterAsses',
			title : '季度考核',
			width : 80
		}, {
			field : 'yearAsses',
			title : '年度考核',
			width : 80
		}, {
			field : 'yearNakedPrice',
			title : '2015年裸价',
			width : 80
		} ] ]
	})
}
/**
 * 初始化经销商
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
		title : '经销商编号',
		width : 120
	}, {
		field : 'name1',
		title : '名称',
		width : 200
	} ] ],
	toolbar : '#toolbarKunnr',
	onSelect : function(index,recode) {
		$('#kunnr_num').val(recode.kunnr);
		$('#kunnrId').val(recode.kunnr);
		$('#kunnr_name').val(recode.name1);
		$('#tz').val("政策通知");
		//计算可用预打款金额
		initYdk(recode.kunnr);
		//计算可兑现折扣金额
		initDzk(recode.kunnr);
		//加载送货地址
		initKunnrAddress();
	}
});
}
/**
 * 送货地址
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
		title : '送达方编码',
		width : 100
	},{
		field : 'address',
		title : '详细地址',
		width : 200
	}, {
		field : 'xzAddress',
		title : '行政区划',
		width : 200
	} ] ]
});
*/}
// 添加订单明细
function addItem() {
	var nodes = $('#tt').datagrid('getChecked');
	// 选中明细，加入到表格中
	for (var i = 0; i < nodes.length; i++) {
		addOneItem(nodes[i]);
	}
	$('#winAdd').window('close');
}
//订单明细
function addOneItem(node) {
	//本品
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
	//赠品
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

// 弹出考核返点清单
function openKHWin() {
	var kunnrNum=$('#kunnr_num').val();
	if(undefined==kunnrNum||null==kunnrNum||""==kunnrNum){
		$.messager.alert('Tips', "请先选择经销商!", 'warning');
		return;
	}
	$('#winKHFDQD').window('open');
	initKHTable();//加载数据
}

// 初始化弹出窗口
function initWindow() {
	$('#winAdd').window({
		title : '新增品项',
		collapsible : false,
		minimizable : false,
		maximizable : false,
		resizable : false,
		shadow : false,
		closed : true
	});

	$('#winKHFDQD').window({
		title : '考核返点清单',
		collapsible : false,
		minimizable : false,
		maximizable : false,
		resizable : false,
		shadow : false,
		closed : true
	});
}
// 弹出品项窗口
function add() {
	var kunnrNum=$('#kunnr_num').val();
	if(undefined==kunnrNum||null==kunnrNum||""==kunnrNum){
		$.messager.alert('Tips', "请先选择经销商!", 'warning');
		return;
	}
	$('#winAdd').window('open');
}

/**
 * 经分销商下拉查询
 * 
 * @param name1
 */
function searcherKunnr(val) {
	var queryParams = $('#kunnr_id').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(val);
	$('#kunnr_id').combogrid("grid").datagrid('reload');
}
/**
 * 品项
 * @param val
 */
function searcherPx(val){
	var queryParams = $('#tt').datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(val);
	$('#tt').datagrid('reload');
}
//计算可用预打款金额
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
//计算可兑现折扣金额
function initDzk(kunnr) {
	$.ajax({
		url : appUrl + '/ordersAction!getKunnrDiscountAmount.jspa?kunnrNum='+kunnr,
		async : false,
		success : function(data) {
			$('#kdxzkje').val(data);
		}
	});
}
//汇总总额、费用兑换、预打款
function countTotalMoney(){
	var rows = $('#ddxq').datagrid('getRows');
	var total=0;
	var use=0;
	for ( var i = 0; i < rows.length; i++) {
		var index = $('#ddxq').datagrid('getRowIndex', rows[i]);
		//费用兑换
		var editor_use_fund = $('#ddxq').datagrid('getEditor', {index : index,field : 'order_use_fund'});
		if(null==editor_use_fund){
			use=use+rows[i].order_use_fund*1;
		}else{
			use=use+editor_use_fund.target.val()*1;
		}  
		//总金额
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
 * 删除行
 */
function del(){
	var rows = $('#ddxq').datagrid('getSelections');  //获取选中行  
    if (rows == '') {
		$.messager.alert('Tips', '未选中行数据!');
		return;
	}
	$.messager.confirm('确认','确认删除选中行数据?',function(r){  
        if(r){  
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				if(null!=rows[i].list_id&&undefined!=rows[i].list_id&&''!=rows[i].list_id){
					ids.push(rows[i].list_id);
				}
				//删行项目
				var index = $('#ddxq').datagrid('getRowIndex', rows[i]);
	        	$('#ddxq').datagrid('deleteRow', index);
			}
			$("#ids").val(ids);
       
	    //删表
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
 * 清空费用
 */
function remove(){
	 var rows = $('#ddxq').datagrid('getRows');  //获取行  
     /*if (rows == '') {
			$.messager.alert('Tips', '未选中行数据!');
			return;
	 }*/
	$.messager.confirm('确认','确认清空费用?',function(r){  
        if(r){  
			for ( var i = 0; i < rows.length; i++) {
				var index = $('#ddxq').datagrid('getRowIndex', rows[i]);
				$('#ddxq').datagrid('beginEdit', index);
				//费用兑换
				var editor_use_fund = $('#ddxq').datagrid('getEditor', {index : index,field : 'order_use_fund'});
				//预打款
				var editor_payMoney = $('#ddxq').datagrid('getEditor', {index : index,field : 'payMoney'});
				editor_use_fund.target.numberbox('setValue',0);  
				//总金额
				var editor_up = $('#ddxq').datagrid('getEditor', {index : index,field : 'order_up_fund'});
			    editor_payMoney.target.numberbox('setValue',editor_up.target.val()*1);  
			}
			//重新汇总
			countTotalMoney();
        } 
  });
}
/**
 * 保存草稿、提交订单
 */
function submitForm(flag){
	//C保存，S提交
	var msgTitle='提交';
	if('A'==flag){
		msgTitle='保存';
	}
    if(null==$('#kunnr_num').val()||undefined==$('#kunnr_num').val()||''==$('#kunnr_num').val()){
    	$.messager.alert('Tips', '请填写经销商!');
		return;
    }
    if(!$('#djrq').datebox('isValid')){
    	$.messager.alert('Tips', '请填写单据日期!');
		return;
    }
    var rows = $('#ddxq').datagrid('getSelections');  //获取行  
    if (rows == '') {
		$.messager.alert('Tips', '未选中行数据!');
		return;
	}
	//$('#ddxq').datagrid('selectAll');
	$.messager.confirm('确认','确认'+msgTitle+'选中的行数据?',function(r){  
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
	window.parent.search();//刷新父界面
}

function windowRefresh() {
	window.parent.refreshWindow();
}
/**
 * 返回信息
 */
function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips',successResult+'是否关闭编辑窗口?',function(r){  
	        if(r){ 
	        	windowsClose();
	        }else{
	        	//刷新
	        	windowRefresh();
	        }
         });
   }
}