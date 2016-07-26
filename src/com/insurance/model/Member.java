package com.insurance.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;



public class Member {
	private String username;// 用户名
	private String password;// 密码
	private String email;// E-mail
	private String safeQuestion;// 密码保护问题
	private String safeAnswer;// 密码保护问题答案
	private Integer point;// 积分
	private BigDecimal deposit;// 预存款
	private Boolean isAccountEnabled;// 账号是否启用
	private Boolean isAccountLocked;// 账号是否锁定
	private Integer loginFailureCount;// 连续登录失败的次数
	private Date lockedDate;// 账号锁定日期
	private String registerIp;// 注册IP
	private String loginIp;// 最后登录IP
	private Date loginDate;// 最后登录日期
	private String passwordRecoverKey;// 密码找回Key
	
//	private MemberRank memberRank;// 会员等级
////	private Map<MemberAttribute, String> memberAttributeMapStore;// 会员注册项储存
//	private Set<Receiver> receiverSet;// 收货地址
//	private Set<Product> favoriteProductSet;// 收藏夹商品
//	private Set<CartItem> cartItemSet;// 购物车项
//	private Set<Message> inboxMessageSet;// 收件箱消息
//	private Set<Message> outboxMessageSet;// 发件箱消息
//	private Set<Order> orderSet;// 订单
//	private Set<Deposit> depositSet;// 预存款
}
