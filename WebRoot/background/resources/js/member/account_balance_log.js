/**
 * 
 */
function typeFormatter(value, row, index) {
	if (value == "brokerage")
		return "佣金发放";
	if (value == "partPayment")
		return "部分支付";
	if (value == "paid")
		return "已支付"
				+ '<a id="ship" title="下单人信息" class="success"><span class="glyphicon glyphicon-ok"></span>';
	if (value == "partRefund")
		return "部分退款";
	if (value == "refunded")
		return "全额退款";
}

function nameFormatter(value) {
	if(value==null)
		return value;
	return value.realName;
}