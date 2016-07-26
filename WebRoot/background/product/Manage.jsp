<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>产品管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script src="${pageContext.request.contextPath}/background/resources/js/ToolTip.js"></script>

<style>
td a {
	margin-left: 5px;
	font-size: 1.2em;
}

.span_Pic {
	width: 100%;
	height: 100%;
	color: red;
}

.success {
	color: #5cb85c;
}

.success:focus,.success:hover {
	color: #449d44;
}

.danger {
	color: #d9534f;
}

.danger:focus,.danger:hover {
	color: #c9302c;
}
</style>


<script type="text/javascript">
$(function() {
	$("#dels").click(
			function(e, value, row, index) {
				$saleTable = $('#productTable').bootstrapTable();
				var selects = $saleTable.bootstrapTable('getSelections');
				var ids = $.map(selects, function(row) {
					return row.id;
				});
				var dialog = BootstrapDialog.confirm('确认删除吗？', 'danger',
						function(result) {
							if (result) {
								$.post("product_delete.action?ids=[" + ids
										+ "]", ids, function(result) {
									var result = eval('(' + result + ')');
									if (result.success) {
										dialog.close();
										$("#saleTable").bootstrapTable(
												'refresh');
									}
								});
							} else {
								dialog.close();
							}
						});
				dialog.setSize(BootstrapDialog.SIZE_SMALL);
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

	function productImgFormatter(value, row, index) {
		return [	'<img class="lgImg" src="'+value+'" height="25px"/>' ]
				.join('');
	}
	function operateFormatter(value, row, index) {
		return [
				'<a id="look" title="详细信息" class="success" data-toggle="modal" data-target="#myModal">',
				'<span class="glyphicon glyphicon-user"></span>',
				'</a>',
				'<a id="save" title="修改" data-toggle="modal" data-target="#myModal">',
				'<span class="glyphicon glyphicon-edit"></span>', '</a>',
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
			modifyPic(row);
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
		$("input#createDate").val("");
		$("input#name").val("");
		$("input#price").val("");
		$("input#brokerage").val("");
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
		$("input#createDate").val(row.createDate);
		$("input#name").val(row.name);
		$("input#insuranceType_id").val(row.insuranceType_id);
		$("input#price").val(row.price);
		$("input#brokerage").val(row.brokerage);
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

	function modifyPic(row) {
		$("span#idPic")
				.html(
						'<input type="file" class="btn btn-default" name="idPic">	<img alt="照片未找到！请重新上传！" src="' + row.productImgList
				+ '" class="span_Pic">');
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
</script>
</head>
<body>
	<div class="center">
		<div class="data_content">
			<div id="custom-toolbar">
				<button type="button" id="dels" class="btn btn-danger">
					<span class="glyphicon glyphicon-trash"></span> 批量删除
				</button>
			</div>
			<div class="main_table">
				<!-- Button trigger modal -->
				<table id="productTable" class="table-condensed"
					data-side-pagination="server" data-pagination="true"
					data-page-list="[5, 10, 20, 50, 100, 200]" data-toggle="table"
					data-show-refresh="true" data-search="true" data-show-toggle="true"
					data-toolbar="#custom-toolbar"
					data-url="${pageContext.request.contextPath}/back/product_list.action"
					data-cache="false" data-height="512" data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-width="120">产品名称</th>
							<th data-field="createDate" data-width="20">创建日期</th>
							<th data-field="price" data-width="100">起始价格</th>
							<th data-field="productImgList" data-width="20"
								data-formatter="productImgFormatter">缩略图</th>
							<th data-field="productType_id" data-width="10">保险类别</th>
							<th data-field="operate" data-align="center" data-width="80"
								data-formatter="operateFormatter" data-events="operateEvents">操作</th>
						</tr>
					</thead>
				</table>
			</div>
			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width:1000px">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">产品添加</h4>
						</div>
						<div class="modal-body">
							<form id="fm" method="post" enctype="multipart/form-data">
								<table id="modalTab" class="table table-bordered">
									<tr>
										<td rowspan="2" align="center"
											style="vertical-align: inherit;"><img
											src="${pageContext.request.contextPath}/background/resources/images/logo.png"
											style="height: 80px;width: 120px;" alt="..."
											class="img-rounded"></td>

										<td>产品名称:</td>
										<td><input type="text" id="name" name="product.name"
											class="form-control"></td>
										<td>接口ID:</td>
										<td><input type="text" id="insuranceType_id"
											name="product.insuranceType_id" class="form-control">
											<input type="text" id="createDate" name="product.createDate"
											style="display: none;"> <input type="text" id="id"
											name="product.id" style="display: none;"></td>
									</tr>
									<tr>
										<td>起始价格（￥：单位元）</td>
										<td><input type="text" id="price" name="product.price"
											class="form-control"></td>
										<td>保额（￥：单位元）</td>
										<td><input type="text" id="price" name="product.amount"
											class="form-control"></td>
									</tr>

									<tr>
										<td>产品缩略图：</td>
										<td colspan="2" height="200px"><span id="idPic"></span></td>
										<td>产品简介：</td>
										<td colspan="2"><textarea id="summary"
												name="product.summary" class="form-control" rows="5"></textarea>
										</td>
									</tr>

									<tr>
										<td>佣金比例</td>
										<td colspan="4"><input type="text" id="brokerage"
											name="product.brokerage" class="form-control"></td>
									</tr>
									<tr>
										<td>保险类别</td>
										<td colspan="2"><select id="productType_id"
											name="product.productType_id" class="form-control">
												<option>人寿险</option>
												<option>车险</option>
												<option>综合险</option>
										</select></td>

										<td>保险品牌</td>
										<td colspan="2"><select id="productBrand"
											name="product.productBrand" class="form-control">
												<option>前海人寿</option>
												<option>中国平安</option>
										</select></td>
									</tr>
									<tr>
										<td>保险期限</td>
										<td colspan="2"><select id="startdate"
											name="product.startdate" class="form-control">
												<option>网上投保次日零时</option>
												<option>网上投保第三日零时</option>
										</select></td>

										<td>保险品牌</td>
										<td><select id="datelimit" name="product.datelimit"
											class="form-control">
												<option>一年</option>
												<option>半年</option>
												<option>一个月</option>
										</select></td>
									</tr>
									<tr>
										<td>投保须知</td>
										<td colspan="4"><textarea id="insuranceNote"
												name="product.insuranceNote" class="form-control" rows="5"></textarea>
										</td>
									</tr>
									<tr>
										<td>投保声明书</td>
										<td colspan="4"><textarea id="insuranceDeclaration"
												name="product.insuranceDeclaration" class="form-control"
												rows="5"></textarea></td>
									</tr>
									<tr>
										<td>投保规则</td>
										<td colspan="4"><textarea id="insuranceRules"
												name="product.insuranceRules" class="form-control" rows="5"></textarea>
										</td>

									</tr>
									<tr>
										<td>理赔须知</td>
										<td colspan="4"><textarea id="claimNote"
												name="product.claimNote" class="form-control" rows="5"></textarea>
										</td>
									</tr>
								</table>
							</form>
						</div>
						<div class="modal-footer"></div>
					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>