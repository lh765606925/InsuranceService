package com.insurance.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "t_order")
public class Order extends BaseEntity {

	private static final long serialVersionUID = -8541323033439515148L;

	public static final int DEFAULT_ORDER_LIST_PAGE_SIZE = 15;// 订单列表默认每页显示数

	// 订单状态（未处理、已处理、已完成、已作废）
	public enum OrderStatus {
		unprocessed, processed, completed, invalid
	};

	// 付款状态（未支付、部分支付、已支付、部分退款、全额退款）
	public enum PaymentStatus {
		unpaid, partPayment, paid, partRefund, refunded
	};

	// 配送状态（未发货、部分发货、已发货、部分退货、已退货）
	public enum ShippingStatus {
		unshipped, partShipped, shipped, partReshiped, reshiped
	};
	

	private String orderSn;// 订单编号
	private OrderStatus orderStatus;// 订单状态
	private PaymentStatus paymentStatus;// 支付状态
	private ShippingStatus shippingStatus;// 发货状态
	private String deliveryTypeName;// 配送方式名称
	private String paymentConfigName;// 支付方式名称
	private BigDecimal productTotalPrice;// 商品总价格
	private BigDecimal deliveryFee;// 配送费用
	private BigDecimal paymentFee;// 支付费用
	private BigDecimal totalAmount;// 订单总额
	private BigDecimal paidAmount;// 已付金额
	private Double productWeight;// 商品重量
	private Integer productTotalQuantity;// 商品总数
	private String shipName;// 收货人姓名
	private String shipArea;// 收货地区
	private String shipAreaPath;// 收货地区路径
	private String shipAddress;// 收货地址
	private String shipZipCode;// 收货邮编
	private String shipPhone;// 收货电话
	private String shipMobile;// 收货手机
	private String memo;// 附言


	private PaymentConfig paymentConfig;// 支付方式
	private Set<OrderItem> orderItemSet;// 订单项
	private Set<OrderLog> orderLogSet;// 订单日志
	private Set<Payment> paymentSet;// 收款
	private Set<Refund> refundSet;// 退款
	
	@Column(unique = true, updatable = false, nullable = false)
	public String getOrderSn() {
		return orderSn;
	}
	
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	@Enumerated
	@Column(nullable = false)
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Enumerated
	@Column(nullable = false)
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Enumerated
	@Column(nullable = false)
	public ShippingStatus getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(ShippingStatus shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getProductTotalPrice() {
		return productTotalPrice;
	}
	
	// 精度处理
	public void setProductTotalPrice(BigDecimal productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}
	
	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}
	
	// 精度处理
	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	
	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getPaymentFee() {
		return paymentFee;
	}

	// 精度处理
	public void setPaymentFee(BigDecimal paymentFee) {
		this.paymentFee = paymentFee;
	}

	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	// 精度处理
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(precision = 15, scale = 5, nullable = false)
	public BigDecimal getPaidAmount() {
		return paidAmount;
	}
	
	// 精度处理
	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	@Column(nullable = false)
	public String getDeliveryTypeName() {
		return deliveryTypeName;
	}
	
	public void setDeliveryTypeName(String deliveryTypeName) {
		this.deliveryTypeName = deliveryTypeName;
	}
	
	@Column(nullable = false)
	public String getPaymentConfigName() {
		return paymentConfigName;
	}

	public void setPaymentConfigName(String paymentConfigName) {
		this.paymentConfigName = paymentConfigName;
	}
	
	@Column(nullable = false)
	public Double getProductWeight() {
		return productWeight;
	}
	
	public void setProductWeight(Double productWeight) {
		this.productWeight = productWeight;
	}
	
	@Column(nullable = false)
	public Integer getProductTotalQuantity() {
		return productTotalQuantity;
	}

	public void setProductTotalQuantity(Integer productTotalQuantity) {
		this.productTotalQuantity = productTotalQuantity;
	}

	@Column(nullable = false)
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	@Column(nullable = false)
	public String getShipArea() {
		return shipArea;
	}

	public void setShipArea(String shipArea) {
		this.shipArea = shipArea;
	}

	public String getShipAreaPath() {
		return shipAreaPath;
	}

	public void setShipAreaPath(String shipAreaPath) {
		this.shipAreaPath = shipAreaPath;
	}

	@Column(nullable = false)
	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	@Column(nullable = false)
	public String getShipZipCode() {
		return shipZipCode;
	}

	public void setShipZipCode(String shipZipCode) {
		this.shipZipCode = shipZipCode;
	}

	public String getShipPhone() {
		return shipPhone;
	}

	public void setShipPhone(String shipPhone) {
		this.shipPhone = shipPhone;
	}

	public String getShipMobile() {
		return shipMobile;
	}

	public void setShipMobile(String shipMobile) {
		this.shipMobile = shipMobile;
	}
	
	@Column(length = 5000)
	public String getMemo() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	

	@OneToOne(fetch = FetchType.LAZY)
	public PaymentConfig getPaymentConfig() {
		return paymentConfig;
	}

	public void setPaymentConfig(PaymentConfig paymentConfig) {
		this.paymentConfig = paymentConfig;
	}

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.DELETE })
	@OrderBy("createDate desc")
	public Set<OrderItem> getOrderItemSet() {
		return orderItemSet;
	}

	public void setOrderItemSet(Set<OrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
	}

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.DELETE })
	@OrderBy("createDate asc")
	public Set<OrderLog> getOrderLogSet() {
		return orderLogSet;
	}

	public void setOrderLogSet(Set<OrderLog> orderLogSet) {
		this.orderLogSet = orderLogSet;
	}

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.DELETE })
	@OrderBy("createDate desc")
	public Set<Payment> getPaymentSet() {
		return paymentSet;
	}

	public void setPaymentSet(Set<Payment> paymentSet) {
		this.paymentSet = paymentSet;
	}

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	@Cascade(value = { CascadeType.DELETE })
	@OrderBy("createDate desc")
	public Set<Refund> getRefundSet() {
		return refundSet;
	}

	public void setRefundSet(Set<Refund> refundSet) {
		this.refundSet = refundSet;
	}



}