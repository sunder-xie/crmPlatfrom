/* upload function */

var fileInputNumber = $("#fileInputNumber").val() == undefined
		? 0
		: $("#fileInputNumber").val();

function addFile() {	
	var strFile = "file" + fileInputNumber;
	var filePath = document.getElementById(strFile);
	document.getElementById(strFile).style.display = "none";
	var paths = filePath.value.split("\\");
	var name = paths[paths.length - 1];
	var str1 = '<div style="background-color:#FEEEBD" >&nbsp;<span style="font-size: 12px"> '+ name
			+ '</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><img onclick=removeFile(this,"'
			+ strFile + '") src="' + imgUrl
			+ '/images/actions/action_del.png" border="0" title="ɾ������"/></a></div>';
	var _file = document.getElementById("_file");
	_file.insertAdjacentHTML("beforeend", str1);

	addInput();
}

function addInput() {
	fileInputNumber++;
	var strFile = "file" + fileInputNumber;
	var str2 = '<input name="upload" id="' + strFile
			+ '" type="file" value="��Ӹ���" onchange="addFile()" />';
	var _input = document.getElementById("input");
	_input.insertAdjacentHTML("afterbegin", str2);

}

function removeFile(id, strFile) {
	var new_tr = id.parentNode.parentNode;
	try {
		var tmp = new_tr.parentNode;
		// Ϊ����ie��firefox�¶�������ʹ��,��Ҫ����һ����������,��ȡ��һ��ĸ����,Ȼ��remove.
		tmp.removeChild(new_tr);

		removeInput(strFile);
	} catch (e) {
	}
}

function removeInput(strFile) {
	var _input = document.getElementById("input");
	try {
		// Ϊ����ie��firefox�¶�������ʹ��,��Ҫ����һ����������,��ȡ��һ��ĸ����,Ȼ��remove.
		_input.removeChild(strFile);

	} catch (e) {
	}
}

/* end of upload function */

