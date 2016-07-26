package com.insurance.bean;

public class VehicleInsurance {

	// 5.1.1. 接口错误代码
	public enum Enum511 {
		SUCCESS(""), FAILURE("0000");
		private String codeString;

		private Enum511(String codeString) {
			this.codeString = codeString;
		}
	}

	// 5.1.2. 号牌种类（107）
	public enum Enum512 {
		HP1("1", "大型汽车号牌"), 
		HP2("2", "小型汽车号牌"), 
		HP3("3", "使馆汽车号牌"), 
		HP4("4", "领馆汽车号牌"), 
		HP5("5", "境外汽车号牌"),
		HP6("6", "外籍汽车号牌"),
		HP13("13", "农用运输车号牌"), 
		HP14("14", "拖拉机号牌"), 
		HP15("15", "挂车号牌"), 
		HP16("16", "教练汽车号牌"), 
		HP18("18", "试验汽车号牌"), 
		HP20("20", "临时入境汽车号牌"), 
		HP22("22", "临时行驶车号牌"), 
		HP23("23", "公安警车号牌"), 
		HP24("24", "其它车型"), 
		HP25("25", "专用机械"), 
		HP27("27", "大型警车"), 
		HP28("28", "军队号牌");
		private String code;
		private String name;

		private Enum512(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

//5.1.3.	使用性质（103）
	public enum Enum513 {
		HP1("1", "非营业"), 
		HP2("2", "营业");
		private String code;
		private String name;

		private Enum513(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}

	//5.1.4.	所属性质（127）
		public enum Enum514 {
			HP1("1", "个人用车"), 
			HP2("2", "机关用车"),
			HP3("3", "企业用车");
			private String code;
			private String name;

			private Enum514(String code, String name) {
				this.code = code;
				this.name = name;
			}
		}

		//5.1.5.	车辆种类（901）
		public enum Enum515{
			HP1("1", "客车(非营业)"), 
			HP2("2", "货车"),
			HP6("6", "挂车"),
			HP7("7", "特一挂车"),
			HP8("8", "特二挂车"),
			HP9("9", "特三挂车"),
			HP10("10", "客车（出租）"),
			HP11("11", "客车（租赁）"),
			HP12("12", "客车（城市公交）"),
			HP13("13", "客车（公路客运）"),
			HP21("21", "低速载货、三轮"),
			HP51("51", "特种车一"),
			HP52("52", "特种车二"),
			HP53("53", "特种车三"),
			HP54("54", "特种车四"),
			HP71("71", "拖拉机(14.7K以下)"),
			HP72("72", "拖拉机(14.7K以上)"),
			HP73("73", "运输型拖拉机(14.7KW及以下)"),
			HP74("74", "运输型拖拉机(14.7KW以上)"),
			HP75("75", "兼用型拖拉机(14.7KW及以下)"),
			HP76("76", "兼用型拖拉机(14.7KW以上)"),
			HP77("77", "变型拖拉机");
			private String code;
			private String name;

			private Enum515(String code, String name) {
				this.code = code;
				this.name = name;
			}
		}


		//5.1.6.	交管车辆种类（000102）
		public enum Enum516{
			HP1("1", "客车(非营业)"), 
			HP77("77", "变型拖拉机");
			private String code;
			private String name;

			private Enum516(String code, String name) {
				this.code = code;
				this.name = name;
			}
		}

		//5.1.6.	交管车辆种类（000102）
public enum Enum517{	

		HP2("2", "货车"),
		HP6("6", "挂车");
		
		private String code;
		private String name;

		private Enum517(String code, String name) {
			this.code = code;
			this.name = name;
		}
	}
		
			
			
			
	// 请求类型代码:V010车型模糊查询,V020车型查询,V030投保查询,V031投保查询校验,V040保费计算,
	// V041保费计算校验,V050申请核保,V060撤回修改,V110保单信息查询,V120
	// 理赔信息查询,V070暂存,V080暂存查询,V130车船税配置查询
	public enum RequestType {
		V010, V020, V030, V031, V040, V041, V050, V060, V110, V120, V070, V080, V130
	}

	// 车辆来历凭证种类:销售发票,法院调解书,法院裁定书,法院判决书,仲裁裁决书,相关文书（继承、赠予、协议抵债）,批准文件,调拨证明,修理发票
	public enum ImportFlag {
		XSFP, FYTJS, FYCDS, FYPJS, ZCCJS, XGWS, BZWJ, DBZM, XLFP
	}
}
