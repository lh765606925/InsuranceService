	$(function() {
		$("#dels").click(function() {
			$('#myModal').modal({
				show : true,
				backdrop : true,
			});
		});
	});
	function insert() {
		clearForm();
		$("#modalTab input").removeAttr("readOnly");
		$("#myModal").find('.modal-title').text('产品添加');
		modifyPic();
		$(".modal-footer")
				.html(
						'<button type="button" class="btn btn-primary" onclick="save()">保存</button>'
								+ '<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>');
	}

	function save() {
		var url = "back/product_insert.action";
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return true;
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#myModal').modal('hide');
					$("#productTable").bootstrapTable('refresh');
					showDlog("保存成功", "success");
				} else {
					showDlog("保存失败", "danger");
					return;
				}
			}
		});
	}

	function update() {
		var url = "back/product_update.action";
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return true;
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#myModal').modal('hide');
					$("#productTable").bootstrapTable('refresh');
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
				$("#productTable").bootstrapTable('refresh');
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
			return '<abbr title=' + value + '>' + value.substring(0, 4)
					+ '</abbr>';
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

	function operateFormatter(value, row, index) {
		return [
				'<a id="look" title="详细信息" class="success" data-toggle="modal" data-target="#myModal">',
				'<span class="glyphicon glyphicon-user"></span>', '</a>',
				'<a id="remove" title="删除" class="danger">',
				'<span class="glyphicon glyphicon-trash"></span>', '</a>' ]
				.join('');
	}
	window.operateEvents = {
		'click #look' : function(e, value, row, index) {
			$("#myModal").find('.modal-title').text('产品信息');
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
			$("#myModal").find('.modal-title').text('产品信息修改');
			$("#modalTab input").removeAttr("readOnly");
			$("#td_sex")
					.html(
							'<div class="panel panel-default" style="height: 34px;margin-bottom:0px;padding: 6px 12px;"> <div class="radio" style="margin-left: 5px;margin-top:0;margin-bottom:0;">'
									+ '<label><input type="radio" id="sex" name="salesman.sex" value="男" checked="checked">男</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
									+ '<label><input type="radio" id="sex" name="salesman.sex" value="女">女</label></div></div>');
			$(".modal-footer")
					.html(
							'<button type="button" class="btn btn-default" data-dismiss="modal" onclick="clearForm()">关闭</button>'
									+ '<button type="button" class="btn btn-primary" onclick="update()">保存</button>');
			$("input:radio[value=" + row.sex + "]").attr("checked", "true");
			fillForm(row);
			modifyPic();
		},
		'click #remove' : function(e, value, row, index) {
			var dialog = BootstrapDialog.confirm('确认删除吗？', 'danger', function(
					result) {
				if (result) {
					$.post("product_delete.action", {
						ids : row.id
					}, function(result) {
						var result = eval('(' + result + ')');
						if (result.success) {
							dialog.close();
							$("#productTable").bootstrapTable('refresh');
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
		$("input#id").val("");
		$("input#name").val("");
		$("input#price").val("");
		$("textarea#insuranceNote").val("");
		$("input#productType_id").val("");
		$("textarea#insuranceDeclaration").val("");
		$("textarea#insuranceRules").val("");
		$("textarea#claimNote").val("");
		$("span#idPic").html(
				"<input type='file' class='btn btn-default' name='idPic'>");
	}
	function fillForm(row) {
		$("input#id").val(row.id);
		$("input#name").val(row.name);
		$("input#insuranceType_id").val(row.insuranceType_id);
		$("input#price").val(row.price);
		$("textarea#insuranceNote").val(row.insuranceNote);
		$("input#productType_id").val(row.productType_id);
		$("input#productBrand").val(row.productBrand);
		$("textarea#insuranceDeclaration").val(row.insuranceDeclaration);
		$("textarea#insuranceRules").val(row.insuranceRules);
		$("textarea#claimNote").val(row.claimNote);
	}
	function fillPic(row) {
		$("span#idPic")
				.html(
						'<img alt="照片未找到！请重新上传！" src="' + row.productImgList
					+ '" class="span_Pic">');
	}

	function modifyPic() {
		$("span#idPic").html(
				"<input type='file' class='btn btn-default' name='idPic'>");
	}

	function checkIsNull(str) {
		if (str == "" || str == null) {
			return true;
		} else {
			return false;
		}
	}

	(function($, K) {
		if (!K)
			throw "KindEditor未定义!";

		function create(target) {
			var opts = $.data(target, 'kindeditor').options;
			var editor = K.create(target, opts);
			$.data(target, 'kindeditor').options.editor = editor;
		}

		$.fn.kindeditor = function(options, param) {
			if (typeof options == 'string') {
				var method = $.fn.kindeditor.methods[options];
				if (method) {
					return method(this, param);
				}
			}
			options = options || {};
			return this.each(function() {
				var state = $.data(this, 'kindeditor');
				if (state) {
					$.extend(state.options, options);
				} else {
					state = $.data(this, 'kindeditor', {
						options : $.extend({}, $.fn.kindeditor.defaults,
								$.fn.kindeditor.parseOptions(this), options)
					});
				}
				create(this);
			});
		}

		$.fn.kindeditor.parseOptions = function(target) {
			return $.extend({}, $.parser.parseOptions(target, []));
		};

		$.fn.kindeditor.methods = {
			editor : function(jq) {
				return $.data(jq[0], 'kindeditor').options.editor;
			}
		};

		$.fn.kindeditor.defaults = {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : false,
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'emoticons', 'image', 'link' ],
			afterChange : function() {
				this.sync();//这个是必须的,如果你要覆盖afterChange事件的话,请记得最好把这句加上.
			}
		};
		$.parser.plugins.push("kindeditor");
	})(jQuery, KindEditor);