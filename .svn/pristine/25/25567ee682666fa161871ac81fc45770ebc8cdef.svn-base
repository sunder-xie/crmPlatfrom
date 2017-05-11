$(function() {
	initDDZT();
	initTable();
	//$('#ddzt').combobox('setValue','');
	/*$("#ddrq_c").datebox('setValue', getCurrentMonthFirst());
	$("#ddrq_z").datebox('setValue', getCurrentMonthLast());*/
	$('#hideFrame').bind('load', promgtMsg);
});

// 初始化订单状态
function initDDZT() {
	$('#ddzt').combobox({
		valueField : 'id',
		textField : 'text',
		singleSelect:false,
		editable : false,
		multiple:true,
		data : [ {
			  "id" : "A",
			"text" : "未提交"
		}, {
			"id" : "B",
			"text" : "未审核"
		}, {
			"id" : "C",
			"text" : "已审核"
		}, {
			"id" : "E",
			"text" : "部分排产"
		}, {
			"id" : "F",
			"text" : "完全排产"
		},{
			  "id" : "",
			"text" : "所有状态"
		}]
	});
}

// 初始化table
function initTable() {
	$('#ddlb').datagrid({
		rownumbers : true,
		singleSelect : false,
		pagination : true,
		pageSize : 20,
		autoRowHeight:true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'order_state',
			title : '订单状态',
			width : 80,
			resizable : false,
			formatter : function(value, row, rec) {
				var s = row.order_state;
				var txt='';
				if (s == "A") {
					txt= '未提交';
				} else if (s == "B"){
					txt= '未审核';
				}else if (s == "C"){
					txt= '已审核';
				}else if (s == "E"){
					txt= '部分排产';
				}else if (s == "F"){
					txt= '完全排产';
				}
				return txt;
			}
		}, {
			field : 'order_id',
			title : '单号',
			width : 80,
			resizable : false,
			formatter : function(value, row, rec) {
				var orderId = row.order_id;
				return '<a href="#" style="color:blue" onclick=update("'+orderId+'")>'
							+ orderId + '</a>';
			}
		}, {
			field : 'provinceArea',
			title : '省区',
			width : 100,
			resizable : false
		}, {
			field : 'province',
			title : '省',
			width : 80,
			resizable : false
		}, {
			field : 'city',
			title : '城市',
			width : 100,
			resizable : false
		}, {
			field : 'kunnr_num',
			title : '经销商代码',
			width : 80,
			resizable : false
		}, {
			field : 'kunnr_name',
			title : '经销商名称',
			width : 150,
			resizable : false
		}, {
			field : 'order_date',
			title : '订单日期',
			width : 80,
			resizable : false
		}, {
			field : 'order_count',
			title : '订单数量',
			width : 80,
			resizable : false
		}, {
			field : 'order_up_fund',
			title : '订单使用预打款金额',
			width : 80,
			resizable : false
		}, {
			field : 'order_give_fund',
			title : '订单搭赠金额',
			width : 80,
			resizable : false
		}, {
			field : 'order_use_fund',
			title : '订单使用费用金额',
			width : 80,
			resizable : false
		} ] ],
		toolbar : [ "-", {
			text : '新增订单',
			iconCls : 'icon-add',
			handler : function() {
				add();
			}
		}, "-", {
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				edt();
			}
		}, "-", {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				del();
			}
		} ],
		url : appUrl + "/ordersAction!searchOrdersTotal.jspa?kunnrNum="+$('#jxsdm').val()+'&orgId='+$('#orgId').val(),
		view : detailview,
		detailFormatter : function(index, row) {
			return '<table id="ddv-' + index + '"></table>';
		},
		onExpandRow : function(index, row) {
			$('#ddv-' + index).datagrid({
				singleSelect : true,
				autoRowHeight:true,
				url:appUrl + "/ordersAction!findOrderListByTitle.jspa?orderId="+row.order_id,
				columns : [ [ {
					field : 'aaa',
					title : '',
					width : 370,
					resizable : false
				}, {
					field : 'list_id',
					title : '行项目',
					width : 100,
					resizable : false
				}, {
					field : 'materiel_mvgr',
					title : '品类',
					width : 80,
					resizable : true,
					formatter : function(value, row, rec) {
						return value+'/'+row.materiel_mvgr_name;
					}
				}, {
					field : 'materiel_num',
					title : '品项',
					width : 150,
					resizable : true,
					formatter : function(value, row, rec) {
						return value+'/'+(undefined==row.materiel_name?'':row.materiel_name);
					}
				}, {
					field : 'materiel_price',
					title : '价格',
					width : 80,
					resizable : false
				}, {
					field : 'list_count',
					title : '数量',
					width : 80,
					resizable : false
				}, {
					field : 'order_up_fund',
					title : '使用预打款金额',
					width : 80,
					resizable : false
				}, {
					field : 'order_give_fund',
					title : '使用搭赠金额',
					width : 80,
					resizable : false
				}, {
					field : 'order_use_fund',
					title : '使用费用金额',
					width : 78,
					resizable : false
				} ] ],
				onResize:function(){                        
					$('#ddv-' + index).datagrid('fixDetailRowHeight',index);
                     $('#ddlb').datagrid('fixDetailRowHeight',index);
               },
               onLoadSuccess:function(){
                 setTimeout(function(){                                  
                	 $('#ddv-' + index).datagrid('fixDetailRowHeight',index);//在加载成功时，获取父列表的明细高度，使其适应明显点开后的高度，注意此时的行索引为index
                	 $('#ddv-' + index).datagrid('fixRowHeight',index);//在加载成功时，获取子明细点开后父列表的列高，防止在超过加载成功前的高度时，出现垂直方向的滑动条
                     $('#ddlb').datagrid('fixDetailRowHeight',index);//在加载成功时，获取父列表的明细高度，使其适应前面父列表和子列表的高度变化，注意此时的行索引为index
                     $('#ddlb').datagrid('fixRowHeight',index);//在加载成功时，获取父列表的明细点开后爷爷列表的高度，防止在超过加载成功前的高度时，出现垂直方向的滑动条
                 },0);
             }
			});
			//$('#ddlb').datagrid('fixDetailRowHeight', index);
		}
	})
}

// 新增
function add() {
	initMaintWindow('创建销售订单', '/ordersAction!orderEdit.jspa', 800, 600);
}
//修改
function edt(){
	 var rows = $('#ddlb').datagrid('getSelections');  //获取选中行  
     if (rows == '') {
		  $.messager.alert('Tips', '未选中行数据!');
		  return;
	 }
     if(rows.length>1){
    	 $.messager.alert('Tips', '请选择一个订单进行修改,勿多选!');
		  return;
	 }
        var id = '';
		var ids = [];
		var ids_b =[];
		var ids_c =[];
		var ids_d =[];
		for ( var i = 0; i < rows.length; i++) {
		    if(rows[i].order_state=='A'){//未提交的可删除
				ids.push(rows[i].order_id);
			}else if(rows[i].order_state=='B'){//未审核可撤销变为未提交
				ids_b.push(rows[i].order_id);
			}else if(rows[i].order_state=='C'){//已审核未排产可反审核为未审核
				ids_c.push(rows[i].order_id);
			}else if(rows[i].order_state=='E'||rows[i].order_state=='F'){//排产阶段不能删除
				ids_d.push(rows[i].order_id);
			}
		}
		if(ids_b.length>0){
			$.messager.alert('Tips', '选中的行项目已提交的订单,不能直接修改,请先撤销!');
			return;
		}else if(ids_c.length>0){
			$.messager.alert('Tips', '选中的行项目已审核的订单,不能修改!');
			return;
		}else if(ids_d.length>0){
			$.messager.alert('Tips', '选中的行项目已排产的订单,不能修改!');
			return;
		}else{
			id=ids[0];
		}
	initMaintWindow('修改销售订单', '/ordersAction!orderEdit.jspa?operFlag=U&orderId='+id, 800, 600);
}
//修改
function update(orderId) {
	initMaintWindow('修改销售订单', '/ordersAction!orderEdit.jspa?operFlag=U&orderId='+orderId, 800, 600);
}
//关闭弹出窗口
function closeWindow(){
	$("#maintWindow").window('close');
}
function refreshWindow(){
	$("#maintWindow").window('refresh');
}
//$('#pp').panel('open').panel('refresh');
// 创建窗口对象
function initMaintWindow(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						maximized : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}
//查询
function search(){
	var state='';
	var arr=$("#ddzt").combobox('getValues');
	for (var i = 0; i < arr.length; i++) {
		state +=arr[i]+',';
	}
	var queryParams = $('#ddlb').datagrid('options').queryParams;
	queryParams.orderId = encodeURIComponent($("#dh").val());
	queryParams.orderState = state;
	queryParams.kunnrName=encodeURIComponent($("#jxsmc").val());
	queryParams.startDate=encodeURIComponent($("#ddrq_c").datebox('getValue'));
	queryParams.endDate=encodeURIComponent($("#ddrq_z").datebox('getValue'));
	$("#ddlb").datagrid('load');
}
/**
 * 删除
 */
function del(){
	$.messager.confirm('确认','确认删除?',function(r){  
        if(r){  
            var rows = $('#ddlb').datagrid('getSelections');  //获取选中行  
            if (rows == '') {
				$.messager.alert('Tips', '未选中行数据!');
				return;
			}
            var temp = [];
			var ids = [];
			var ids_b =[];
			var ids_c =[];
			var ids_d =[];
			for ( var i = 0; i < rows.length; i++) {
			    if(rows[i].order_state=='A'){//未提交的可删除
					ids.push(rows[i].order_id);
				}else if(rows[i].order_state=='B'){//未审核可撤销变为未提交
					ids_b.push(rows[i].order_id);
				}else if(rows[i].order_state=='C'){//已审核未排产可反审核为未审核
					ids_c.push(rows[i].order_id);
				}else if(rows[i].order_state=='E'||rows[i].order_state=='F'){//排产阶段不能删除
					ids_d.push(rows[i].order_id);
				}
			}
			if(ids_b.length>0){
				$.messager.alert('Tips', '选中的行项目有已提交的订单,不能直接删除,请先撤销!');
				return;
			}else if(ids_c.length>0){
				$.messager.alert('Tips', '选中的行项目有已审核的订单,不能删除!');
				return;
			}else if(ids_d.length>0){
				$.messager.alert('Tips', '选中的行项目有已排产的订单,不能删除!');
				return;
			}
			$("#ids").val(ids);
        
		$.messager.progress();
		var form = window.document.forms[0];
	    form.action = appUrl+'/ordersAction!updateOrder.jspa?orderState=D';
		form.target = "hideFrame";
		form.submit();
     }
  });
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
		$.messager.confirm('Tips',successResult,function(r){  
	        if(r){ 
	        	search();
	        }
         });
   }
}