/**
 * headTitle 黄色背景
 * @param text
 */
function alertWarning(text) {
	BootstrapDialog.alert({
		title : '系统提示',
		type : BootstrapDialog.TYPE_WARNING,
		message : text
	});
}
/**
 * headTitle 蓝色背景
 * @param text
 */
function alertPrimary(text) {
	BootstrapDialog.alert({
		title : '系统提示',
		type : BootstrapDialog.TYPE_PRIMARY,
		message : text
	});
}
/**
 * headTitle 红色背景
 * @param text
 */
function alertDanger(text) {
	BootstrapDialog.alert({
		title : '系统提示',
		type : BootstrapDialog.TYPE_DANGER,
		message : text
	});
}
/**
 * headTitle 绿色背景
 * @param text
 */
function alertSuccess(text) {
	BootstrapDialog.alert({
		title : '系统提示',
		type : BootstrapDialog.TYPE_SUCCESS,
		message : text
	});
}
/**
 * headTitle 浅蓝色背景
 * @param text
 */
function alertInfo(text) {
	BootstrapDialog.alert({
		title : '系统提示',
		type : BootstrapDialog.TYPE_INFO,
		message : text
	});
}
/**
 * headTitle 白色背景
 * @param text
 */
function alertDefault(text) {
	var dialog = new BootstrapDialog.alert({
		title : '系统提示',
		message : text,
		type : BootstrapDialog.TYPE_DEFAULT,
	});
	dialog.setSize(BootstrapDialog.SIZE_SMALL);
}

/**
 * 
 * @param text
 * @param type title样式：primary、warning、success、danger、info、default
 */
function showDlog(text, type) {
	switch (type) {
	case "primary":
		type = BootstrapDialog.TYPE_PRIMARY;
		break;
	case "warning":
		type = BootstrapDialog.TYPE_WARNING;
		break;
	case "success":
		type = BootstrapDialog.TYPE_SUCCESS;
		break;
	case "danger":
		type = BootstrapDialog.TYPE_DANGER;
		break;
	case "info":
		type = BootstrapDialog.TYPE_INFO;
		break;
	case "default":
		type = BootstrapDialog.TYPE_DEFAULT;
		break;
	default:
		type = BootstrapDialog.TYPE_PRIMARY;
		break;
	}
	var dialog = new BootstrapDialog.show({
		title : '系统提示',
		message : text,
		type : type,
		draggable : true,
		buttons : [ {
			label : 'OK',
			cssClass : 'btn-primary',
			action : function(dialog) {
				dialog.close();
			}
		} ]
	});
	dialog.setSize(BootstrapDialog.SIZE_SMALL);
}
