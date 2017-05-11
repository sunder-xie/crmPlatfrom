/**
 * Çå¿ÕËÑË÷¿ò
 */
function clearValue() {
	document.forms[0].reset();
}
/**
 * Æ¥ÅäÍ¼Æ¬¸ñÊ½
 * 
 * @param file
 * @returns {Boolean}
 */
function matchImage(file, errMsg) {
	var allImgExt = ".jpg|.jpeg|.gif|.bmp|.png|.JPG|.JPEG|.GIF|.BMP|.PNG|";
	var extName = file.substring(file.lastIndexOf("."));
	if (allImgExt.indexOf(extName + "|") == -1) {
		$.messager.alert('Tips', errMsg, 'warning');
		return false;
	}
	return true;
}