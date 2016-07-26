<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>产品管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

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
		$("input#price").val(row.price);
		$("textarea#insuranceNote").val(row.insuranceNote);
		$("input#productType_id").val(row.productType_id);
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
</script>
</head>
<body>
	<div class="center">
		<div class="data_content">
			<div id="custom-toolbar">
				<button type="button" onclick="insert()" class="btn btn-primary"
					data-toggle="modal" data-target="#myModal">
					<span class="glyphicon glyphicon-plus"></span>产品添加
				</button>
				&nbsp;&nbsp;
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
					data-cache="false" data-height="450" data-click-to-select="true">
					<thead>
						<tr style="font-size: 1.2em;">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="name" data-width="120">产品名称</th>
							<th data-field="createDate" data-width="20">创建日期</th>
							<th data-field="price" data-width="20">起始价格</th>
							<th data-field="productImgList" data-width="20">缩略图</th>
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

										<td>接口ID:</td>
										<td><input type="text" id="id" name="product.id"
											class="form-control"></td>
										<td>接口ID:</td>
										<td><input type="text" id="id" name="product.id"
											class="form-control"></td>
									</tr>
									<tr>
										<td>保险名称:</td>
										<td><input type="text" id="name" name="product.name"
											class="form-control"></td>
										<td>创建时间:</td>
										<td><input type="text" id="createDate"
											name="product.createDate" class="form-control"></td>
									</tr>


									<tr>
										<td>产品缩略图：</td>
										<td colspan="2" height="200px"><span id="idPic"></span></td>
										<td>起始价格（￥：单位元）</td>
										<td><input type="text" id="price" name="product.price"
											class="form-control"></td>
									</tr>
									<tr>
										<td>保险类别</td>
										<td colspan="2"><select id="insuranceNote"
											name="product.productType_id" class="form-control">
												<option>车险</option>
												<option>人寿险</option>
												<option>综合险</option>
										</select></td>

										<td>保险品牌</td>
										<td><input type="text" id="productBrand"
											name="product.productBrand" class="form-control"></td>
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
									<tr>
										<td>理赔须知</td>
										<td colspan="4">
											<!-- 加载编辑器的容器 -->
											<div>
												<script id="editor" type="text/plain"
													style="width:720px;height:380px;"></script>
											</div>

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

	<script type="text/javascript">
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor');

		function isFocus(e) {
			alert(UE.getEditor('editor').isFocus());
			UE.dom.domUtils.preventDefault(e)
		}
		function setblur(e) {
			UE.getEditor('editor').blur();
			UE.dom.domUtils.preventDefault(e)
		}
		function insertHtml() {
			var value = prompt('插入html代码', '');
			UE.getEditor('editor').execCommand('insertHtml', value)
		}
		function createEditor() {
			enableBtn();
			UE.getEditor('editor');
		}
		function getAllHtml() {
			alert(UE.getEditor('editor').getAllHtml())
		}
		function getContent() {
			var arr = [];
			arr.push("使用editor.getContent()方法可以获得编辑器的内容");
			arr.push("内容为：");
			arr.push(UE.getEditor('editor').getContent());
			alert(arr.join("\n"));
		}
		function getPlainTxt() {
			var arr = [];
			arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
			arr.push("内容为：");
			arr.push(UE.getEditor('editor').getPlainTxt());
			alert(arr.join('\n'))
		}
		function setContent(isAppendTo) {
			var arr = [];
			arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
			UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
			alert(arr.join("\n"));
		}
		function setDisabled() {
			UE.getEditor('editor').setDisabled('fullscreen');
			disableBtn("enable");
		}

		function setEnabled() {
			UE.getEditor('editor').setEnabled();
			enableBtn();
		}

		function getText() {
			//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
			var range = UE.getEditor('editor').selection.getRange();
			range.select();
			var txt = UE.getEditor('editor').selection.getText();
			alert(txt)
		}

		function getContentTxt() {
			var arr = [];
			arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
			arr.push("编辑器的纯文本内容为：");
			arr.push(UE.getEditor('editor').getContentTxt());
			alert(arr.join("\n"));
		}
		function hasContent() {
			var arr = [];
			arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
			arr.push("判断结果为：");
			arr.push(UE.getEditor('editor').hasContents());
			alert(arr.join("\n"));
		}
		function setFocus() {
			UE.getEditor('editor').focus();
		}
		function deleteEditor() {
			disableBtn();
			UE.getEditor('editor').destroy();
		}
		function disableBtn(str) {
			var div = document.getElementById('btns');
			var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
			for (var i = 0, btn; btn = btns[i++];) {
				if (btn.id == str) {
					UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
				} else {
					btn.setAttribute("disabled", "true");
				}
			}
		}
		function enableBtn() {
			var div = document.getElementById('btns');
			var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
			for (var i = 0, btn; btn = btns[i++];) {
				UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
			}
		}

		function getLocalData() {
			alert(UE.getEditor('editor').execCommand("getlocaldata"));
		}

		function clearLocalData() {
			UE.getEditor('editor').execCommand("clearlocaldata");
			alert("已清空草稿箱")
		}
	</script>
</body>
</html>