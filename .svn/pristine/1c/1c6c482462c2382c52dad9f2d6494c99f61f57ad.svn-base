var selectedId;
var selectedName;
$(document)
		.ready(
				function() {
					$('#orgTree').tree({
						onContextMenu : function(e, node) {
							e.preventDefault();
							selectedId = node.id;
							selectedName = node.text;
							$('#treeMenu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
						}

					});

					$("#treeMenu").menu({
						onClick : function(item) {
							select(selectedId, selectedName);
						}
					});

					$('#orgTree')
							.tree(
									{
										animate : true,
										url : appUrl
												+ '/channelTreeAction!getChannelTreeListByAjax.jspa?node=-1',
										onBeforeExpand : function(node, param) {
											$('#orgTree').tree('options').url = appUrl
													+ "/channelTreeAction!getChannelTreeListByAjax.jspa?node="
													+ node.id;
										},
										onClick : function(node) {// µ¥»÷ÊÂ¼þ
											$(this).tree('toggle', node.target);
											if (!node.state) {
												add(node.text, node.attributes);
											}
										}
									});
				});

function select(selectedId, selectedName) {
	if(window.parent.document.getElementById("flag") == null){
		return;
	}
	document.getElementById("channelParentName").value = selectedName;
	document.getElementById("channelParentId").value = selectedId;
//	selectedName = selectedName.split('¡¾')[0];
	selectedId = selectedId.split(',')[0];
	window.parent.returnValue(selectedId, selectedName);
	window.parent.closeOrgTree();
}
