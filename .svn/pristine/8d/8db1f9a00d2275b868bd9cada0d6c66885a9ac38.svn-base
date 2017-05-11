var nextUser;
var processInstanceId;
var addressCount = $("#addressListSize").val();
var brandCount = $("#brandListSize").val();
var acountCount = $("#acountListSize").val();
var areaCount = $("#kunnrSalesAreaListSize").val();
var goalCount = $("#bCustomerTargetListSize").val();
var killSalesArea = [];
var kunnrSalesAreaList = [];
var kunnrAcountList=[];
var month = [];
$(document)
		.ready(
				function() {
					initHide();
					if($('#konzs').val()==''){
						$('.type2J').hide();
					}else{
						$('.type2J').show();
					}
					$('#hideFrame').bind('load', promgtMsg);
					// 最大可通行车型
					$('#maximum02')
							.combobox(
									{
										valueField : 'itemName',
										textField : 'itemValue',
										url : appUrl
												+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
												+ 'carType'
									});
					for ( var i = 1; i <= addressCount; i++) {
						$('#maximum_' + i)
								.combobox(
										{
											valueField : 'itemName',
											textField : 'itemValue',
											url : appUrl
													+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
													+ 'carType'
										});
					}

					// 行政区划
					function getRegion(level) {
						$('#region' + level)
								.combobox(
										{
											editable : false,
											url : appUrl
													+ '/salesMsgAction!searchRegion.jspa?level='
													+ level + '&pid='
													+ $('#region').val(),
											textField : 'text',
											valueField : 'id',
											onLoadSuccess : function() {
												$('#region')
														.val(
																$(
																		'#region'
																				+ level)
																		.combobox(
																				'getValue'));
												++level;
												getRegion(level);
											},
											onSelect : function(re) {
												$('#region').val(re.id);
												getRegion(level);
											}

										});

					}
					;
					getRegion(1);

					// 性别
					$('#sex').combobox({
						valueField : 'value',
						textField : 'text',
						data : [{
							value : '',
							text : '请选择'
						} , {
							value : 'male',
							text : '男'
						}, {
							value : 'female',
							text : '女'
						} ]
					});

					// 纳税人类型
					$('#kverm').combobox({
						valueField : 'text',
						textField : 'text',
						data : [ {
							value : '小规模纳税人',
							text : '小规模纳税人'
						}, {
							value : '一般纳税人',
							text : '一般纳税人'
						} ],
						onSelect : function(r) {
							refreshLicense();
						}
					});

					// 客户分类 渠道
					$('#channelId')
							.combobox(
									{
										url : appUrl
												+ '/kunnrAction!getChannel.jspa?channelName='
												+ 'K',
										valueField : 'channelId',
										textField : 'channelName'
									});

					// 客户科目组
					$('#ktokd').combobox({
						//url : appUrl + '/kunnrAction!getKtokd.jspa',//action中没此方法，固注释  by cg.jiang 201607
						valueField : 'itemValue',
						textField : 'itemValue'
					});

					// 上级经销商查询
					/*$('#konzs').combogrid({
						panelHeight : 280,
						panelWidth : 420,
						pagination : true,
						method : 'post',
						singleSelect : true,
						url : appUrl + '/kunnrAction!kunnrSearch.jspa',
						idField : 'kunnr',
						textField : 'name1',
						columns : [ [ {
							field : 'kunnr',
							title : '经销商编号',
							width : 120
						}, {
							field : 'name1',
							title : '名称',
							width : 250
						} ] ],
						toolbar : '#toolbarKonzs',
						onSelect : function(index, record) {
							if ($('#konzs').combobox("getValue")) {
								show();
							} else {
								hide();
							}
						}
					});*/

					// 销售大区
					$('#vkgrp').combogrid({
						panelHeight : 280,
						panelWidth : 420,
						pagination : true,
						method : 'post',
						singleSelect : true,
						url : appUrl + '/salesMsgAction!getT171tJsonList.jspa?rows=100',
						idField : 'bzirk',
						textField : 'bztxt',
						columns : [ [ {
							field : 'bzirk',
							title : '大区编号',
							width : 100
						}, {
							field : 'bztxt',
							title : '名称',
							width : 120
						} ] ],
						toolbar : '#toolbarVkgrp',
						onSelect : function(index, record) {
							$('#bzirk').combogrid('setValue', '');
							$('#vkbur').combobox('setValue', '');
							$('#bzirk').combogrid({
								disabled : false
							});
							loadBzirk(record.bzirk);
						}
					});

					// 销售省份
					var bzirk = $('#bzirk').combogrid("getValue");
					loadBzirk(bzirk);
					if (bzirk == '') {
						$('#bzirk').combogrid({
							disabled : true
						});
						$('#vkbur').combobox({
							disabled : true
						});
					}

					function loadBzirk(bzirk) {
						$('#bzirk')
								.combogrid(
										{
											panelHeight : 280,
											panelWidth : 420,
											pagination : false,
											method : 'post',
											singleSelect : true,
											url : appUrl
													+ '/salesMsgAction!getTvgrtListByZdqsf.jspa?bzirk='
													+ $('#vkgrp').combogrid(
															'getValue'),
											idField : 'vkgrp',
											textField : 'bezei',
											columns : [ [ {
												field : 'vkgrp',
												title : '省份编号',
												width : 100
											}, {
												field : 'bezei',
												title : '名称',
												width : 120
											} ] ],
											toolbar : '#toolbarBzirk',
											onSelect : function(index, record) {
												$('#vkbur').combobox(
														'setValue', '');
												$('#vkbur').combobox({
													disabled : false
												});
												loadVkbur(vkbur);
											}
										});
					}
					var vkbur = $('#bzirk').combogrid('getValue');
					if (vkbur == '') {
						$('#vkbur').combobox({
							disabled : true
						});
					} else {
						loadVkbur(vkbur);
					}
					// 销售部门
					function loadVkbur(vkbur) {
						$('#vkbur')
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getTvkbtJsonListByTvbvk.jspa?vkgrp='
													+ $('#bzirk').combogrid(
															'getValue'),
											valueField : 'vkbur',
											textField : 'bezei'
										});
					}
					// 公司
					$('#bukrs').combobox({
						url : appUrl + '/salesMsgAction!getTvkoJsonList.jspa',
						valueField : 'bukrs',
						textField : 'bukrs',
						onSelect : function(record) {
							$("#vkorg").val(record.vkorg);
						}
					});

					for ( var i = 1; i <= areaCount; i++) {
						// 工厂
						$('#werks_' + i)
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getT001wJsonList.jspa',
											valueField : 'werks',
											textField : 'name1',
											onSelect : function(title, index) {
												var werks = title.werks;
												if (werks != '6000') {
													/*$.messager.alert('Tips',
															"此项不能选,请重新操作！",
															'warning');
													$('#werks_' + i).combobox(
															'setValue', '');
													return;
												} else {*/
													var str = werks
															.substr(0, 2);
													$('#vsbed_' + i).combobox(
															'setValue', str);
												}
											}
										});

						// 装运条件
						$('#vsbed_' + i)
								.combobox(
										{
											valueField : 'itemName',
											textField : 'itemValue',
											url : appUrl
													+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
													+ 'zyc'
										});
					}
					// 交货优先权 :01—KA客户，02-非KA
					$('#lprio').combobox({
						data : [ {
							'id' : '01',
							'txt' : '01-KA客户'
						}, {
							'id' : '02',
							'txt' : '02-非KA'
						} ],
						valueField : 'id',
						textField : 'txt'
					});

					// 物料回扣
					if (acountCount > 0) {
						for ( var i = 1; i <= acountCount; i++) {

							$('#bonus_' + i)
									.combobox(
											{
												url : appUrl
														+ '/salesMsgAction!getTvbotJsonList.jspa',
												valueField : 'bonus',
												textField : 'vtext'
											});
						}
					} else {
						$('#bonus_1')
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getTvbotJsonList.jspa',
											valueField : 'bonus',
											textField : 'vtext'
										});
						acountCount++;
					}

					// 证照
//					$("#tabs").tabs({
//						onSelect : function(title, index) {
//							if (title == '证照上传') {
//								var currTab = $('#tabs').tabs('getTab', title);
//								refreshLicense(currTab);
//							}
//						}
//					});
					// 目标量月份
					month = [ {
						'txt' : '01',
						'value' : '1月'
					}, {
						'txt' : '02',
						'value' : '2月'
					}, {
						'txt' : '03',
						'value' : '3月'
					}, {
						'txt' : '04',
						'value' : '4月'
					}, {
						'txt' : '05',
						'value' : '5月'
					}, {
						'txt' : '06',
						'value' : '6月'
					}, {
						'txt' : '07',
						'value' : '7月'
					}, {
						'txt' : '08',
						'value' : '8月'
					}, {
						'txt' : '09',
						'value' : '9月'
					}, {
						'txt' : '10',
						'value' : '10月'
					}, {
						'txt' : '11',
						'value' : '11月'
					}, {
						'txt' : '12',
						'value' : '12月'
					} ];

					for ( var i = 1; i <= goalCount; i++) {

						// 月份
						$('#month_' + i).combobox({
							data : month,
							valueField : 'txt',
							textField : 'value'
						});
						// 年份
						$('#year_' + i).combobox({
							url : appUrl + '/goalAction!getDictTypeList.jspa',
							textField : 'itemName',
							valueField : 'itemName'
						});

						// 对应物料
						$('#wid_' + i)
								.combogrid(
										{
											panelWidth : 400,
											panelHight : 600,
											idField : 'mvgr1',
											textField : 'bezei',
											pagination : true,// 是否分页
																// collapsible :
																// false,//
											// 是否可折叠的
											method : 'post',
											url : appUrl
													+ '/goal/goalAction!getMaterielList.jspa',
											columns : [ [ {
												field : 'ck',
												checkbox : true,
												hidden : true
											}, {
												field : 'mvgr1',
												title : '物料组',
												align : 'center',
												width : 120
											}, {
												field : 'bezei',
												title : '物料组描述',
												align : 'center',
												width : 200
											} ] ],
											toolbar : '#toolbar_' + i
										});
						$('#search_' + i).searchbox({
							searcher : function(value, name) {
								search(value, i);
							},
							prompt : '请填写物料号或物料名'
						});
					}
				});
// 对应物料toolbar

function search(value, index) {
	value = encodeURIComponent(value);
	$('#wid_' + index).combogrid({
		url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + value
	});
	$('#wid_' + index).combogrid("grid").datagrid('reload');

}

// 刷新证照页面
function refreshLicense() {
	$('#panel').panel({    
	    href : appUrl
	           + '/kunnrAction!displayLicensePre.jspa?dictTypeName='
		       + $("#kverm").combobox('getValue')    
	});
//	$('#tabs')
//			.tabs(
//					'update',
//					{
//						tab : currTab ? currTab : $('#tabs').tabs('getTab',
//								'证照上传'),
//						options : {
//							href : appUrl
//									+ '/kunnrAction!displayLicensePre.jspa?dictTypeName='
//									+ $("#kverm").combobox('getValue')
//						}
//					});
}

// 二级返一级 检查是否有上级经销商
function checkKonzs(count) {
	if (!$('#konzsTxt').val()) {
		$.messager.alert('Tips', '没有选择上级经销商,因此不能二级返一级', 'warning');
		$("#flag_" + count).attr("checked", false);
	}
}

// 上级经销商查询框
function searcherKonzs(name1) {
	var queryParams = $('#konzs').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#konzs').combogrid("grid").datagrid('reload');
}

// 销售组织查询框
function searcherVkorg(vkorg) {
	var queryParams = $('#vkorg').combogrid("grid").datagrid('options').queryParams;
	queryParams.vkorg = encodeURIComponent(vkorg);
	$('#vkorg').combogrid("grid").datagrid('reload');
}

// 省
function searcherBzirk(bztxt) {
	var queryParams = $('#bzirk').combogrid("grid").datagrid('options').queryParams;
	queryParams.bztxt = encodeURIComponent(bztxt);
	$('#bzirk').combogrid("grid").datagrid('reload');
}

// 城市
function searcherKvgr1(bezei) {
	var queryParams = $('#kvgr1').combogrid("grid").datagrid('options').queryParams;
	queryParams.bezei = encodeURIComponent(bezei);
	$('#kvgr1').combogrid("grid").datagrid('reload');
}

// 区域
function searcherKvgr2(bezei) {
	var queryParams = $('#kvgr2').combogrid("grid").datagrid('options').queryParams;
	queryParams.bezei = encodeURIComponent(bezei);
	$('#kvgr2').combogrid("grid").datagrid('reload');
}

// 公司
function searcherBukrs(butxt) {
	var queryParams = $('#bukrs').combogrid("grid").datagrid('options').queryParams;
	queryParams.butxt = encodeURIComponent(butxt);
	$('#bukrs').combogrid("grid").datagrid('reload');
}

// 品项
function searcherCategories(value) {
	var val = encodeURIComponent(value);
	$('#categories').combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
	});
	$('#categories').combogrid("grid").datagrid('reload');
}
function submit() {
	    array2json();
		if(kunnrSalesAreaList==''){
			$.messager.alert('Tips', '销售范围未维护!', 'warning');
        	return;
		}else{
			for ( var i = 1; i <= areaCount; i++) {
				if (document.getElementById("saleAreaTr_" + i)) {
				if($('#werks_'+i).combobox('getValue')==''){
					$.messager.alert('Tips', '销售范围的交货工厂未维护!', 'warning');
	            	return;
				}}
			}
		}
		$.messager.confirm('Confirm', '请确认经销商资料信息,保存?', function(r) {
			if(r){
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + "/kunnrAction!updateKunnrApplySales.jspa?bukrs="+$('#bukrs').combobox('getValue');
			form.target = "hideFrame";
			form.submit();
			}
			
		});
}


// 添加折扣一行
function addAcount() {
	acountCount++;
	var html = '';
	html += '<tr id="salesAcountTr_' + acountCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeAcount('
			+ acountCount + ')"><img align="absMiddle" src="' + imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;&nbsp;物料回扣组:';
	html += '<input class="easyui-combobox" id="bonus_' + acountCount
			+ '"></td>';
	html += '<td class="head" noWrap>';
	html += '月返(元):<input class="easyui-numberbox" style="width:60px" id="typeA_'
			+ acountCount + '">';
	html += '&nbsp;&nbsp;季返(元):<input class="easyui-numberbox" style="width:60px" id="typeB_'
			+ acountCount + '">';
	html += '&nbsp;&nbsp;年返(元):<input class="easyui-numberbox" style="width:60px" id="typeC_'
			+ acountCount + '">';
	html += '&nbsp;&nbsp;协议价(元):<input class="easyui-numberbox" style="width:60px" id="typeMoney_'
			+ acountCount + '"</td>';
	if ($('#konzsTxt').val()) {
		html += '<td class="head" noWrap> <div class="type2J">';
		html += '<label><font color="red">二级返一级</font><input type="checkbox" id="flag_'
				+ acountCount
				+ '" value="Y" onclick="checkKonzs('
				+ acountCount + ')"></label>';
		html += '&nbsp;月返(元):<input class="easyui-numberbox" style="width:60px" id="type2A_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;季返(元):<input class="easyui-numberbox" style="width:60px" id="type2B_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;年返(元):<input class="easyui-numberbox" style="width:60px" id="type2C_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')"></div></td>';
	} else {
		html += '<td class="head" noWrap> <div class="type2J" style="display:none;">';
		html += '<label><font color="red">二级返一级</font><input type="checkbox" id="flag_'
				+ acountCount
				+ '" value="Y" onclick="checkKonzs('
				+ acountCount + ')"></label>';
		html += '&nbsp;月返(元):<input class="easyui-numberbox" style="width:60px" id="type2A_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;季返(元):<input class="easyui-numberbox" style="width:60px" id="type2B_'
				+ acountCount + '" onclick="checkKonzs(' + acountCount + ')">';
		html += '&nbsp;&nbsp;年返(元):<input class="easyui-numberbox" style="width:60px" id="type2C_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')"></div></td>';
	}
	html += '<td width="20%">&nbsp;</td><td width="20%">&nbsp;</td><td width="20%">&nbsp;</td>';
	html += '</tr>';
	$('#salesAcountTable').append(html);
	$.parser.parse($('#salesAcountTr_' + acountCount));

	// 物料回扣
	$('#bonus_' + acountCount).combobox({
		url : appUrl + '/salesMsgAction!getTvbotJsonList.jspa',
		valueField : 'bonus',
		textField : 'vtext'
	});
}

// 删除折扣一行
function removeAcount(count) {
	if ($("#salesAcountTr_" + count)) {
		var n = count + 1;
		$("#salesAcountTr_" + count).remove();
		$("#salesAcountTr_" + n).remove();
	}
}

// 删除折扣一行
function removeAcount(count) {
	if ($("#salesAcountTr_" + count)) {

		$("#killAcount").val(
				$("#killAcount").val() + ',' + $("#typeAId_" + count).val()
						+ ',' + $("#typeBId_" + count).val() + ','
						+ $("#typeCId_" + count).val() + ','
						+ $("#type2AId_" + count).val() + ','
						+ $("#type2BId_" + count).val() + ','
						+ $("#type2CId_" + count).val());
		var n = count + 1;
		$("#salesAcountTr_" + count).remove();
		$("#salesAcountTr_" + n).remove();
	}
}
// 添加销售范围一行
function addArea(obj,areaCount) {
	//areaCount++;
	var html = '';
	html += '<tr id="saleAreaTr_' + areaCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeArea('
			+ areaCount + ')"><img align="absMiddle" src="' + imgUrl
			+ '/images/actions/action_del.png"/></td>';
	html += '<td class="head" noWrap>销售组织:</td>';
	html += '<td><input type="hidden" id="vkorg_'
			+ areaCount
			+ '" value="'
			+ obj.vkorg
			+ '" style="width:40px" readonly ></input>'
			+ '<input class="easyui-validatebox" id="vkorgTxt_'
			+ areaCount
			+ '" value="'
			+ obj.vkorgTxt
			+ '" data-options="required:true" style="width:40px" readonly ></input></td>';
	html += '<td class="head" noWrap>分销渠道:</td>';
	html += '<td><input  type="hidden" id="vtweg_' + areaCount + '" value="'
			+ obj.vtweg + '" readonly>'
			+ '<input  class="easyui-validatebox" id="vtwegTxt_' + areaCount
			+ '" value="' + obj.vtwegTxt
			+ '" data-options="required:true" readonly></td>';
	html += '<td class="head" noWrap>产品组:</td>';
	html += ' <td><input  type="hidden" id="spart_' + areaCount + '" value="'
			+ obj.spart + '" readonly></input>'
			+ '<input  class="easyui-validatebox" id="spartTxt_' + areaCount
			+ '" value="' + obj.spartTxt
			+ '" data-options="required:true" readonly></input></td>';
	html += '<td class="head" noWrap>交货工厂:</td>';
	html += '<td><input class="easyui-combobox" id="werks_' + areaCount
			+ '"  data-options="required:true,editable:false"></td>';
	html += '<td class="head" noWrap>装运条件:</td>';
	html += '<td><input class="easyui-combobox" id="vsbed_'
			+ areaCount
			+ '"  data-options="required:true" style="width:60px" validType="length[2,2]"></td>';
	html += '</tr>';
	$('#saleAreaTable').append(html);
	$.parser.parse($('#saleAreaTr_' + areaCount));
	// 工厂
	var areaCounts = areaCount;
	$('#werks_' + areaCount).combobox({
		url : appUrl + '/salesMsgAction!getT001wJsonList.jspa',
		valueField : 'werks',
		textField : 'name1',
		onSelect : function(title, index) {
			var werks = title.werks;
			if (werks != '6000') {
				/*$.messager.alert('Tips', "此项不能选,请重新操作！", 'warning');
				$('#werks_' + areaCounts).combobox('setValue', '');
				$('#vsbed_' + areaCounts).combobox('setValue', '');
				return;
			} else {*/
				var str = werks.substr(0, 2);
				$('#vsbed_' + areaCounts).combobox('setValue', str);
			}
		}
	});
	// 装运条件
	$('#vsbed_' + areaCounts).combobox(
			{
				valueField : 'itemName',
				textField : 'itemValue',
				url : appUrl
						+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
						+ 'zyc'
			});
}
// 刪除销售范围一行
function removeArea(i) {
	if ($("#saleAreaTr_" + i)) {
		killSalesArea.push({
			"id" : $("#salesAreaId_" + i).val(),
			"vkorg" : $("#vkorg_" + i).val(),
			"vkorgTxt" : $("#vkorgTxt_" + i).val(),
			"spart" : $("#spart_" + i).val(),
			"spartTxt" : $("#spartTxt_" + i).val(),
			"werks" : $("#werks_" + i).combobox('getValue'),
			"vsbed" : $("#vsbed_" + i).combobox('getValue'),
			"vtweg" : $("#vtweg_" + i).val(),
			"vtwegTxt" : $("#vtwegTxt_" + i).val()
		});
		$("#killSalesArea").val($.toJSON(killSalesArea));
		$("#saleAreaTr_" + i).remove();
	}
}

// 对应物料toolbar

function search(value, index) {
	value = encodeURIComponent(value);
	$('#wid_' + index).combogrid({
		url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + value
	});
	$('#wid_' + index).combogrid("grid").datagrid('reload');

}

// 地址品牌array->json
function array2json() {
	 kunnrAcountList=[];
	for ( var i = 1; i <= acountCount; i++) {
			if (document.getElementById("salesAcountTr_" + i)) {
				if ($("#bonus_" + i).combobox("getValue")) {
					var flag;
					if ($("#flag_" + i).attr("checked")) {
						flag = 'Y';
					} else {
						flag = 'N';
					}
					kunnrAcountList.push({
						"typeA" : $("#typeA_" + i).val(),
						"typeAId" : $("#typeAId_" + i).val(),
						"typeB" : $("#typeB_" + i).val(),
						"typeBId" : $("#typeBId_" + i).val(),
						"typeC" : $("#typeC_" + i).val(),
						"typeCId" : $("#typeCId_" + i).val(),
						"type2A" : $("#type2A_" + i).val(),
						"type2AId" : $("#type2AId_" + i).val(),
						"type2B" : $("#type2B_" + i).val(),
						"type2BId" : $("#type2BId_" + i).val(),
						"type2C" : $("#type2C_" + i).val(),
						"type2CId" : $("#type2CId_" + i).val(),
						"bonus" : $("#bonus_" + i).combobox("getValue"),
						"flag" : flag,
						"typeMoney":$("#typeMoney_" + i).val()
					});
				}
		}
	}

	for ( var i = 1; i <= areaCount; i++) {
		if (document.getElementById("saleAreaTr_" + i)) {
			if ($("#vkorg_" + i).val() != $('#bukrs').combobox('getValue')) {
				$.messager.alert('Tips', "销售组织不一致,请重新操作！", 'warning');
				return;
			}
			kunnrSalesAreaList.push({
				"id" : $("#salesAreaId_" + i).val(),
				"vkorg" : $("#vkorg_" + i).val(),
				"vkorgTxt" : $("#vkorgTxt_" + i).val(),
				"spart" : $("#spart_" + i).val(),
				"spartTxt" : $("#spartTxt_" + i).val(),
				"werks" : $("#werks_" + i).combobox('getValue'),
				"vsbed" : $("#vsbed_" + i).combobox('getValue'),
				"vtweg" : $("#vtweg_" + i).val(),
				"vtwegTxt" : $("#vtwegTxt_" + i).val()
			});
		}
	}
	$("#kunnrAcountList").val($.toJSON(kunnrAcountList));
	$("#kunnrSalesAreaList").val($.toJSON(kunnrSalesAreaList));
}

// 返回信息
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	}else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}

// 初始化时一定要隐一列
function initHide() {
	$('.type2J').hide();
}

// 隐列
function hide() {
	$('.type2J').hide();
}

// 显列
function show() {
	$('.type2J').show();
}
/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintWindow4Post('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
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
	$("#nextUserDialog").window('close');
}
/*
 * 打开人员选择树
 */
function selectManager() {
	userRole = 'M';
	initMaintWindow4Post('选择城市经理', '/batChangeAction!selectOrgTreeUser.jspa',
			550, 350);
}
/*
 * 打开人员选择树
 */
function selectDirectory() {
	userRole = 'D';
	initMaintWindow4Post('选择客户经理', '/batChangeAction!selectOrgTreeUser.jspa',
			550, 350);
}
/*
 * 城市经理、主管选择返回值
 */
function returnUser(userId, userName) {
	if (userRole == 'M') {
		document.getElementById('businessManagerId').value = userId;
		document.getElementById('businessManager').value = userName;
	}
	if (userRole == 'D') {
		document.getElementById('businessCompetentId').value = userId;
		document.getElementById('businessCompetent').value = userName;
	}
}
/*
 * 打开销售范围
 */
function selectTvkbz(num) {
	initMaintWindow4Post('选择销售范围', '/salesMsgAction!toSearchTvkbz.jspa?vkorg='
			+ $('#bukrs').combobox('getValue') + '&num=' + num, 700, 550);
}
/*
 * 销售范围选择返回值 vkorg销售组织,vtweg销售渠道,spart产品组,vkbur销售部门
 */
function returnTvkbz(vkorg, vkorgTxt, vtweg, vtwegTxt, spart, spartTxt, vkbur,
		vkburTxt, num) {
	document.getElementById('vkorg_' + num).value = vkorg;
	document.getElementById('vtweg_' + num).value = vtweg;
	document.getElementById('spart_' + num).value = spart;

	document.getElementById('vkorgTxt_' + num).value = vkorgTxt;
	document.getElementById('vtwegTxt_' + num).value = vtwegTxt;
	document.getElementById('spartTxt_' + num).value = spartTxt;
}

function returnTvkbzList(list) {
	for ( var i = 0; i < list.length; i++) {
		areaCount++;
		addArea(list[i],areaCount);
	}
}
/*
 * 选择地址
 */
function selectAddress(num) {
	if (undefined == num) {
		initMaintWindow4Post('选择地址', '/kunnrAction!searchAddress.jspa', 700,
				550);
	} else {
		initMaintWindow4Post('选择地址', '/kunnrAction!searchAddress.jspa?num='
				+ num, 700, 550);
	}
}
/*
 * 地址选择返回值zwl01t省，zwl02t市，zwl03t区zwl04t镇
 */

function returnAddress(zwl01t, zwl02t, zwl03t, zwl04t, num) {
	if (undefined == num) {
		document.getElementById('street').value = zwl01t + zwl02t + zwl03t
				+ zwl04t;
	} else {
		document.getElementById('address_' + num).value = zwl01t + zwl02t
				+ zwl03t + zwl04t;
	}
}
/**
 * 弹出窗口
 * 
 * @param title
 * @param url
 * @param width
 * @param height
 */
function initMaintWindow4Post(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#nextUserDialog")
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
						left : $(window).width() * 0.2,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true
					//
					});

	$win.window('open');
}
/**
 * 目标量信息导出
 */
function exportGoals() {
	var form = window.document.forms[0];
	form.action = appUrl + '/kunnrAction!exportGoals.jspa';
	form.target = "hideFrame";
	form.submit();
}
function exportSalesGoals() {
	var form = window.document.forms[0];
	form.action = appUrl + '/stockReportAction!exportSalesGoalForKunnrApply.jspa?kunnr='+$("#kunnrCode").val();
	form.target = "hideFrame";
	form.submit();
}
/**
 * 打印
 */
function print(){
	var eventId=$('#eventId').val();
	var xmlFilePath=$('#xmlFilePath').val();
	var subFolders=$('#subFolders').val();
	var WWidth = 950;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl + '/kunnrAction!toKunnrPrintPre.jspa?eventId='+eventId+'&printType='+'apply'+'&subFolders='+subFolders+'&xmlFilePath='+xmlFilePath,"kunnrPrint","left=" + WLeft + ",top=20" + ",width=" + WWidth + 
",height="
			+ (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
}