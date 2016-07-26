$(function() {
	$("#dels").click(function() {
		$('#myModal').modal({
			show : true,
			backdrop : true,
		});
	});
});

/* 业务员添加 */
function insert() {
	clearForm();
	$("#modalTab input").removeAttr("readOnly");
	$("#myModal").find('.modal-title').text('业务员添加');
	modifyPic();
	$(".modal-footer")
			.html(
					'<button type="button" class="btn btn-primary" onclick="save()">保存</button>'
							+ '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>');
}
/* 充值 */
function deposit() {
	clearForm();
	$("#modalTab input").removeAttr("readOnly");
	$("#myModal").find('.modal-title').text('充值');
	modifyPic();
	$(".modal-footer")
			.html(
					'<button type="button" class="btn btn-primary" onclick="save()">保存</button>'
							+ '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>');
}

function save() {
	var url = "back/salesman_insert.action";
	$("#fm").form("submit", {
		url : url,
		onSubmit : function() {
			return true;
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$('#myModal').modal('hide');
				$("#saleTable").bootstrapTable('refresh');
				showDlog("保存成功", "success");
			} else {
				showDlog("保存失败", "danger");
				return;
			}
		}
	});
}

$("#fm").submit(function() {
	$.post(this.attr("action"), function(result) {
		var result = eval('(' + result + ')');
		if (result.success) {
			$("#saleTable").bootstrapTable('refresh');
		}
	});
});

function emailFormatter(value, row, index) {
	if (value.length > 16) {
		return '<abbr title=' + value + '>' + value.substring(0, 16)
				+ '</abbr>';
	} else {
		return value;
	}
}
function nameFormatter(value, row, index) {
	if (value.length > 4) {
		return '<abbr title=' + value + '>' + value.substring(0, 4) + '</abbr>';
	} else {
		return value;
	}
}
function idNumFormatter(value, row, index) {
	if (value.length > 10) {
		return '<abbr title=' + value + '>' + value.substring(0, 10)
				+ '</abbr>';
	} else {
		return value;
	}
}
function nowAddrFormatter(value, row, index) {
	if (value.length > 10) {
		return '<abbr title=' + value + '>' + value.substring(0, 10)
				+ '</abbr>';
	} else {
		return value;
	}
}
function createDateFormatter(value, row, index) {
	if (value.length > 10) {
		return '<abbr title=' + value + '>' + value.substring(0, 10)
				+ '</abbr>';
	} else {
		return value;
	}
}
/**
 * 操作
 */
function operateFormatter(value, row, index) {
	return [
			'<a id="look" title="详细信息" class="success" data-toggle="modal" data-target="#myModal">',
			'<span class="glyphicon glyphicon-user"></span>',
			'</a>',
			'<a id="save" title="充值" data-toggle="modal" data-target="#myModal">',
			'<span class="glyphicon glyphicon-edit"></span>', '</a>',
			'<a id="remove" title="删除" class="danger">',
			'<span class="glyphicon glyphicon-trash"></span>', '</a>' ]
			.join('');
}
window.operateEvents = {
	'click #look' : function(e, value, row, index) {
		$("#myModal").find('.modal-title').text('业务员信息');
		$("#td_sex")
				.html(
						'<input type="text" id="sex" name="salesman.sex" class="form-control">');
		$(".modal-footer")
				.html(
						'<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>');
		$("#modalTab input").attr("readOnly", true);
		fillForm(row);
		fillPic(row);
	},
	'click #save' : function(e, value, row, index) {
		$("#myModal").find('.modal-title').text('余额充值');
		$("#modalTab input").removeAttr("readOnly");
		$(".modal-footer")
				.html(
						'<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>'
								+ '<button type="button" class="btn btn-primary">保存</button>');
		$("input:radio[value=" + row.sex + "]").attr("checked", "true");
		fillForm(row);
		modifyPic();
	},
	'click #remove' : function(e, value, row, index) {
		var dialog = BootstrapDialog.confirm('确认删除吗？', 'danger', function(
				result) {
			if (result) {
				$.post("salesman_delete.action", {
					ids : row.sid
				}, function(result) {
					var result = eval('(' + result + ')');
					if (result.success) {
						dialog.close();
						$("#saleTable").bootstrapTable('refresh');
					}
				});
			} else {
				dialog.close();
			}
		});
		dialog.setSize(BootstrapDialog.SIZE_SMALL);
	}
};

function clearForm() {
	$("input#invate").val("");
	$("input#name").val("");
	$("input#realName").val("");
	$("input#sex").val("");
	$("input#phone").val("");
	$("input#email").val("");
	$("input#nowAddr").val("");
	$("input#workAddr").val("");
	$("input#bank").val("");
	$("input#bankUser").val("");
	$("input#bankNum").val("");
	$("input#idNum").val("");
	$("input#money").val("");
	$("input#record").val("");
	$("span#idPic1").html(
			"<input type='file' class='btn btn-default' name='idPic'>");
	$("span#idPic2").html(
			"<input type='file' class='btn btn-default' name='idPic'>");
	$("input#congye").val("");
	$("span#cyPic1").html(
			"<input type='file' class='btn btn-default' name='cyPic'>");
	$("span#cyPic2").html(
			"<input type='file' class='btn btn-default' name='cyPic'>");
	$("input#zhanye").val("");
	$("span#zyPic1").html(
			"<input type='file' class='btn btn-default' name='zyPic'>");
	$("span#zyPic2").html(
			"<input type='file' class='btn btn-default' name='zyPic'>");
}
function fillForm(row) {
	$("input#invate").val(row.invate);
	$("input#name").val(row.name);
	$("input#realName").val(row.realName);
	$("input#sex").val(row.sex);
	$("input#phone").val(row.phone);
	$("input#email").val(row.email);
	$("input#nowAddr").val(row.nowAddr);
	$("input#workAddr").val(row.workAddr);
	$("input#bank").val(row.bank);
	$("input#bankUser").val(row.bankUser);
	$("input#bankNum").val(row.bankNum);
	$("input#idNum").val(row.idNum);
	$("input#money").val(row.money);
	$("input#record").val(row.record);
}
function fillPic(row) {
	$("span#idPic1").html(
			'<img alt="照片未找到！请重新上传！" src="' + row.idPic1
					+ '" class="span_Pic">');
	$("span#idPic2").html(
			'<img alt="照片未找到！请重新上传！" src="' + row.idPic2
					+ '" class="span_Pic">');
	$("span#cyPic1").html(
			'<img alt="照片未找到！请重新上传！" src="' + row.cyPic1
					+ '" class="span_Pic">');
	$("span#cyPic2").html(
			'<img alt="照片未找到！请重新上传！" src="' + row.cyPic2
					+ '" class="span_Pic">');
	$("span#zyPic1").html(
			'<img alt="照片未找到！请重新上传！" src="' + row.zyPic1
					+ '" class="span_Pic">');
	$("span#zyPic2").html(
			'<img alt="照片未找到！请重新上传！" src="' + row.zyPic2
					+ '" class="span_Pic">');
}

function modifyPic() {
	$("span#idPic1").html(
			"<input type='file' class='btn btn-default' name='idPic'>");
	$("span#idPic2").html(
			"<input type='file' class='btn btn-default' name='idPic'>");
	$("input#congye").val("");
	$("span#cyPic1").html(
			"<input type='file' class='btn btn-default' name='cyPic'>");
	$("span#cyPic2").html(
			"<input type='file' class='btn btn-default' name='cyPic'>");
	$("input#zhanye").val("");
	$("span#zyPic1").html(
			"<input type='file' class='btn btn-default' name='zyPic'>");
	$("span#zyPic2").html(
			"<input type='file' class='btn btn-default' name='zyPic'>");
}

function checkIsNull(str) {
	if (str == "" || str == null) {
		return true;
	} else {
		return false;
	}
}