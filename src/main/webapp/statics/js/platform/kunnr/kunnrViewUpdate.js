var acountCount = $("#acountListSize").val();
var areaCount = $("#kunnrSalesAreaListSize").val();
var goalCount = $("#bCustomerTargetListSize").val();
var addressCount = $("#addressListSize").val();
var month=[];
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
													+ '/sales/salesAction!searchRegion.jspa?level='
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
					$('#brands').combobox({
						valueField : 'value',
						textField : 'text',
						multiple : true,
						editable : false,
						data : [ {
							value : 'X',
							text : '香飘飘'
						}, {
							value : 'L',
							text : '兰芳园'
						}, {
							value : 'M',
							text : 'MECO'
						}]
					});
					// 客户分类 渠道
					$('#channelId').combobox({
						url : appUrl + '/kunnrAction!getChannel.jspa?channelName='+'K',
						valueField : 'channelId',
						textField : 'channelName'
					});

					// 上级经销商查询
					//$('#konzs').combogrid('setValue',$('#konzsHidde').val());
				/*	$('#konzs').combogrid({
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
							if ($('#konzs').combogrid("getValue")) {
								show();
							} else {
								hide();
							}
						}
					});*/
					

					/*// 产品组
					$('#spart')
							.combobox(
									{
										url : appUrl
												+ '/sales/salesAction!getTspaJsonList.jspa',
										valueField : 'spart',
										textField : 'vtext',
										multiple : true
									});
					$('#spart').combobox('setValues',
							$("#spartHide").val().split(","));

					// 分销渠道
					$('#vtweg')
							.combobox(
									{
										url : appUrl
												+ '/sales/salesAction!getTvtwtJsonList.jspa',
										valueField : 'vtweg',
										textField : 'vtext',
										multiple : true
									});
					$('#vtweg').combobox('setValues',
							$("#vtwegHide").val().split(","));
*/
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
												+ '/salesMsgAction!getT171tJsonList.jspa',
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
					/*$('#categories').combogrid({
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
					for ( var i = 1; i <= areaCount; i++) {
						// 工厂
						$('#werks_' + i)
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getT001wJsonList.jspa',
											valueField : 'werks',
											textField : 'name1'
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
						var werks=$('#werks_'+i).combobox('getValue');
						if(werks!='6000'){
					    var str= werks.substr(0, 2); 
				        $('#vsbed_'+i).combobox('setValue',str);
				        }
					}
					// 交货优先权 :01—KA客户，02-非KA
					$('#lprio').combobox({
						data : [ {
							'id' : '01',
							'txt' : '01—KA客户'
						}, {
							'id' : '02',
							'txt' : '02-非KA'
						} ],
						valueField : 'id',
						textField : 'txt'
					});
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
							$('#wid_' + i).combogrid({
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
	// 上级经销商查询框
	function searcherKonzs(name1) {
		var queryParams = $('#konzs').combogrid("grid").datagrid('options').queryParams;
		queryParams.name1 = encodeURIComponent(name1);
		$('#konzs').combogrid("grid").datagrid('reload');
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
	 * 目标量信息导出
	 */
	function exportGoals() {
		var form = window.document.forms[0];
		form.action = appUrl + '/kunnrAction!exportGoals.jspa';
		form.target = "hideFrame";
		form.submit();
	}