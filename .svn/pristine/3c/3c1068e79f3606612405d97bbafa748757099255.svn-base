var acountCount = $("#acountListSize").val();
var kunnrSalesAreaCount = $("#kunnrSalesAreaListSize").val();
var bCustomerTargetCount = $("#bCustomerTargetListSize").val();
var kunnrAddressCount=$('#addressListSize').val();
var month;
$(document)
		.ready(
				function() {
					if($('#konzs').val()){
						$('.type2J').show();
					}else{
						$('.type2J').hide();
					}
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
					for ( var i = 1; i <= kunnrAddressCount; i++) {
					$('#maximum_'+i)
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
						data : [ {
							value : '',
							text : '请选择'
						} ,{
							value : 'male',
							text : '男'
						}, {
							value : 'female',
							text : '女'
						} ]
					});

					// 客户分类 渠道
					$('#channelId').combobox({
						url : appUrl + '/kunnrAction!getChannel.jspa?channelName='+'K',
						valueField : 'channelId',
						textField : 'channelName'
					});

					/*// 上级经销商查询
					$('#konzs').combogrid({
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
						} ] ]
					});*/


					// 销售大区
					$('#vkgrp')
							.combogrid(
									{
										panelHeight : 280,
										panelWidth : 420,
										pagination : true,
										method : 'post',
										singleSelect : true,
										url : appUrl
												+ '/salesMsgAction!getT171tJsonList.jspa?rows=100',
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
											$('#bzirk').combogrid('setValue',
													'');
											$('#vkbur')
													.combobox('setValue', '');
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
					}
					// 交货优先权 :01—KA客户，02-非KA
					$('#lprio').combobox(
							{
								data : [ {
									'id' : '01',
									'txt' : '01—KA客户'
								}, {
									'id' : '02',
									'txt' : '02-非KA'
								} ],
								valueField : 'id',
								textField : 'txt',
								multiple:false,
								onLoadSuccess : function() {
									
								}
							});

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
					$('#bukrs')
							.combobox(
									{
										url : appUrl
												+ '/salesMsgAction!getTvkoJsonList.jspa',
										valueField : 'bukrs',
										textField : 'bukrs',
										onSelect : function(record) {
											$("#vkorg").val(record.vkorg);
										}
									});

					// 品项
				/*	$('#categories').combogrid({
						panelWidth : 400,
						panelHight : 280,
						idField : 'matnr',
						textField : 'maktx',
						pagination : true,// 是否分页
						collapsible : false,// 是否可折叠的
						method : 'post',
						multiple : true,
						singleSelect : false,
						url : appUrl + '/goal/goalAction!getMatList.jspa',
						columns : [ [ {
							field : 'matnr',
							title : '物料号',
							width : 120
						}, {
							field : 'maktx',
							title : '物料描述',
							align : 'center',
							width : 120
						}, {
							field : 'bezei',
							title : '物料组描述',
							align : 'center',
							width : 100
						} ] ]
					});
					$('#categories').combogrid('setValues',
							$("#categoriesHide").val().split(","));
*/
					for ( var i = 1; i <= kunnrSalesAreaCount; i++) {
					// 工厂
					$('#werks_'+i)
							.combobox(
									{
										url : appUrl
												+ '/salesMsgAction!getT001wJsonList.jspa',
										valueField : 'werks',
										textField : 'name1'
									});
					
					$('#vsbed_'+i)
					.combobox(
							{
								valueField : 'itemName',
								textField : 'itemValue',
								url : appUrl
										+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
										+ 'zyc'
							});
					}

					for ( var i = 1; i <= acountCount; i++) {
						// 物料回扣
						$('#bonus_' + i)
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getTvbotJsonList.jspa',
											valueField : 'bonus',
											textField : 'vtext'
										});
					}
					/*$('#bonus_1')
					.combobox(
							{
								url : appUrl
										+ '/salesMsgAction!getTvbotJsonList.jspa',
								valueField : 'bonus',
								textField : 'vtext'
							});*/
					for ( var i = 1; i <= bCustomerTargetCount; i++) {
						// 对应物料
						$('#wid_'+i).combogrid({
							panelWidth : 400,
				            panelHight : 600,
				            idField : 'mvgr1',
				            textField : 'bezei',
				            pagination : true,// 是否分页 collapsible : false,//
				            // 是否可折叠的
				            method : 'post',
				            url:appUrl+'/goal/goalAction!getMaterielList.jspa',
				            columns : [ [ {
				              field : 'ck',
				              checkbox : true,
				              hidden : true
				            },{
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
							toolbar : '#toolbar_'+i
						});
						$('#search_' + i).searchbox({
							searcher : function(value, name) {
								search(value, i);
							},
							prompt : '请填写物料号或物料名'
						});
						}
					});
	//对应物料toolbar

	function search(value, index) {
		value = encodeURIComponent(value);
		$('#wid_' + index).combogrid({
			url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + value
		});
		$('#wid_' + index).combogrid("grid").datagrid('reload');

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