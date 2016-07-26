<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>完整demo</title>


</head>
<body>
	<div class="center" style="height:auto; padding-bottom:10px">
		<div class="data_content" style="width:98%; padding-bottom:20px">
			<div class="main_table">
				<form id="fm" method="post" enctype="multipart/form-data"
					action="${pageContext.request.contextPath}/back/product_insert.action">
					<table id="modalTab" style="padding:10px"
						class="table table-bordered">
						<tr>
							<td rowspan="2" align="center" style="vertical-align: inherit;"><img
								src="${pageContext.request.contextPath}/background/resources/images/logo.png"
								style="height: 80px;width: 120px;" alt="..." class="img-rounded"></td>

							<td>保险名称:</td>
							<td><input type="text" id="name" name="product.name"
								class="form-control"></td>
							<td>接口ID:</td>
							<td><input type="text" id="id"
								name="product.insuranceType_id" class="form-control"></td>
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
							<td colspan="2" ><span id="idPic"> <input
									type='file' class='btn btn-default' name='idPic'>
							</span></td>
							<td>产品简介：</td>
							<td colspan="2" ><textarea id="summary"
									name="product.summary" class="form-control" rows="5"></textarea>
							</td>
							

						</tr>
						<tr>
							<td>保险类别</td>
							<td colspan="2"><select id="insuranceNote"
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
							<td>投保须知</td>
							<td colspan="4"><textarea id="insuranceNote"
									name="product.insuranceNote" class="summernote"></textarea></td>
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
					<div style="text-align:right;padding-right:10px">
						<input class="button" type="submit" value="提交"
							onclick="save()">
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>