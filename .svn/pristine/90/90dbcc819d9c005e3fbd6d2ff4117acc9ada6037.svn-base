$(function() {
	initTable();
	$('#hideFrame').bind('load', promgtMsg);
});

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
			hidden:true,
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
				return value;
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
			width : 200,
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
			text : '订单审核',
			iconCls : 'icon-add',
			handler : function() {
				review();
			}
		}],
		url : appUrl + "/ordersAction!searchOrdersTotal.jspa?orderState="+$('#ddzt').val(),
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
					width : 289,
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
					resizable : false
				}, {
					field : 'materiel_num',
					title : '品项',
					width : 200,
					resizable : false
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

//修改
function update(orderId) {
	initMaintWindow('修改销售订单', '/ordersAction!orderEdit.jspa?operFlag=U&orderId='+orderId, 800, 600);
}
//关闭弹出窗口
function closeWindow(){
	$("#maintWindow").window('close');
}
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
	var queryParams = $('#ddlb').datagrid('options').queryParams;
	queryParams.orderId = encodeURIComponent($("#dh").val());
	/*if (document.getElementById("bhxjFlag").checked) {
		queryParams.bhxjFlag = $("#bhxjFlag").val();
	}else{
		queryParams.bhxjFlag='';
	}*/
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnrNum=$("#jxsdm").val();
	queryParams.kunnrName=encodeURIComponent($("#jxsmc").val());
	queryParams.startDate=encodeURIComponent($("#ddrq_c").datebox('getValue'));
	queryParams.endDate=encodeURIComponent($("#ddrq_z").datebox('getValue'));
	$("#ddlb").datagrid('load');
}
/**
 * 审核
 */
function review(){
	  var rows = $('#ddlb').datagrid('getSelections');  //获取选中行  
      if (rows == '') {
			$.messager.alert('Tips', '未选中行数据!');
			return;
		}
	$.messager.confirm('确认','确认审核?',function(r){  
        if(r){  
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].order_id);
			}
			$("#ids").val(ids);
        
		$.messager.progress();
		var form = window.document.forms[0];
	    form.action = appUrl+'/ordersAction!updateOrder.jspa?orderState=C';
		form.target = "hideFrame";
		form.submit();
     }
  });
}
/**
 * 打开组织树
 */
function selectOrgTree() {
	initOrgWindow('选择组织', '/customerAction!orgTreePage.jspa', 300, 460);
}
/**
 * 组织树选组织返回到输入框
 * 
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}
/**
 * 组织树选择完之后关闭
 */
function closeOrgTree() {
	closeWindow();
}
//组织窗口对象
function initOrgWindow(title, url, width, height) {
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
						maximized : false,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
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