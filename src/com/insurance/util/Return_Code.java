package com.insurance.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Return_Code {
	
	
	/**
	 * 过滤4 utf-8
	 */
	public static String filterOffUtf8Mb4(String text)
	throws UnsupportedEncodingException {
		byte[] bytes = text.getBytes("utf-8");
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		int i = 0;
		while (i < bytes.length) {
			short b = bytes[i];
			if (b > 0) {
				buffer.put(bytes[i++]);
				continue;
			}
			b += 256;
			if ((b ^ 0xC0) >> 4 == 0) {
				buffer.put(bytes, i, 2);
				i += 2;
			}
			else if ((b ^ 0xE0) >> 4 == 0) {
				buffer.put(bytes, i, 3);
				i += 3;
			}
		
			else if ((b ^ 0xF0) >> 4 == 0) {
				i += 4;
			}
			// 添加处理如b的指为-48等情况出现的死循环
			else {
				buffer.put(bytes[i++]);
				continue;
			}
		}
		buffer.flip();

		return new String(buffer.array(), "utf-8");
	}
	/** 
	 * Gson
	 */
	private static Gson NewsGson = null;
	public static Gson getGson(){
		if(NewsGson==null){
			NewsGson = new Gson();
		}
		return NewsGson;
	}
	
	/**
	 * 中国区号
	 */
	private static HashMap<String, String> China;
	public static String getChina(String key){
		/**
		 * 三大运营商号段： 中国移动号段：134、135、136、137、138、139、150、151、152、157、158、159、147、182、183、184、187、188 
		 * 中国联通号段：130、131、132、145、155、156、185、186 （145属于联通无线上网卡号段）、176 
		 * 中国电信号段：133 、153 、180 、181 、189 
		 */
		if(China==null){
			China = new HashMap<String, String>();
			China.put("134", "134");
			China.put("135", "135");
			China.put("136", "136");
			China.put("137", "137");
			China.put("138", "138");
			China.put("139", "139");
			China.put("150", "150");
			China.put("151", "151");
			China.put("152", "152");
			China.put("157", "157");
			China.put("158", "158");
			China.put("159", "159");
			China.put("147", "147");
			China.put("182", "182");
			China.put("183", "183");
			China.put("184", "184");
			China.put("187", "187");
			China.put("188", "188");
			
			China.put("130", "130");
			China.put("131", "131");
			China.put("132", "132");
			China.put("145", "145");
			China.put("155", "155");
			China.put("156", "156");
			China.put("185", "185");
			China.put("186", "186");
			China.put("176", "176");
			 
			China.put("133", "133");
			China.put("153", "153");
			China.put("180", "180");
			China.put("181", "181");
			China.put("189", "189");
		}
		return China.get(key);
	}

	/**
	 * Key
	 */
	public static String DateKey() {
		Date df = new Date();
		return String.valueOf(df.getTime());
	}
	
	public static String getRandom(){
		Random random = new Random();
		return Integer.toHexString(random.nextInt());
	}

	/**
	 * json
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String json, TypeToken<T> token) {
		if (StringUtil.isEmpty(json)) {
			return null;
		}

		try {
			return (T) getGson().fromJson(json, token.getType());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 数字判断
	 */
	public static boolean numberJudge(String value){
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence)value);
		return matcher.matches();
	}
	

	/**
	 *uuid
	 */
	public static String UUKey() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	/**
	 * 获取当前日期n天后的日期
	 * @param n
	 *            :返回当天后的第N天
	 * @return 返回的日期
	 */
	public static String getAfterDate(int n) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, n);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
	}
	
	/**
	 * 
	 * @return
	 */
	public static String[] getCcopeTime(){
		 Calendar can = Calendar.getInstance();
		 can.add(Calendar.MINUTE, -1000);//
		 String[] str = new String[2];
		 str[0] = Return_Code.getDf().format(can.getTime());
		 can.add(Calendar.MINUTE, 2000);
		 str[1] = Return_Code.getDf().format(can.getTime());
		 return str;
	}
	
	
	public static String getNumber(String str){
		String regex = "\\D*(\\d+)\\D*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		int count = 0;
		String found = "";
		while (matcher.find() && count++ == 0) {
			found = matcher.group(1);
		}
		if (count == 1) {
			System.out.println("Find one.: " + found);
		}
		return found;
	}
	
	/**
	 * @return时间
	 */
	public static DateFormat df = null;
	public static DateFormat getDf() {
		if(df==null){
			setDf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		}
		return df;
	}
	public static void setDf(DateFormat df) {
		Return_Code.df = df;
	}

	public static String DateThis() {
		return getDf().format(new Date());
	}
	
	public static Date getDate() throws ParseException{
		return getDf().parse(DateThis());
	}
	
	public static String getTime() {
		return new Date().getTime()+"";
	}

	/**
	 */
	public static String USER_OTHER = "Other Equipment";
	/**
	 * 用户注册
	 */
	public static String INSERT_WRONG = "The user exists";
	public static String INSERT_REALNAME = "The name exists";
	public static String INSERT_SUCCESS = "Insert Success";
	public static String INSERT_FAILURE = "Insert Failure";
	
	/**
	 *  创建好友组
	 */
	public static String ESTABLISH_GROUP_SUCCESS="ESTABLISH SUCCESS";
	public static String ESTABLISH_GROUP_FAILURE="ESTABLISH FAILURE";
	public static String ESTABLISH_GROUP_NUMBER="ESTABLISH NUMBER";
	
	/**
	 * 删除分组
	 */
	public static String ESTABLISH_GROUP_REMOVE_SUCCESS="GROUP REMOVE_SUCCESS";
	public static String ESTABLISH_GROUP_REMOVE_FAILURE="GROUP INOPERATION";
	public static String ESTABLISH_GROUP_REMOVE_FRIENDS="GROUP REMOVE_FRIENDS";
	
	/**
	 * 修改分组明
	 */
	public static String UPDATE_GROUP_NAME_SUCCESS="UPDATE_GROUP_SUCCESS";
	public static String UPDATE_GROUP_NAME_FRIENDS="UPDATE_GROUP_FRIENDS";
	
	/**
	 */
	public static String LOGIN_SUCCESS = "Login Success";
	public static String LOGIN_PASS_ERROR = "Login Password Error";
	public static String LOGIN_NAME_ERROR = "Login Name Error";
	public static String LOGIN_NAME_ENABLE = "Login Name Enable";
	
	public static String LOGEN_PRIASE_SUCCESS = "Login priase Success";
	public static String LOGEN_PRIASE_FAILURE = "Login priase Error";
	public static String LOGEN_PRIASE_KEYDOWN = "Login priase KeyDown";
	public static String LOGEN_PRIASE_DELETE = "Login priase delete";

	/**
	 */
	public static String UPDATE_SUCCESS = "Update Success";
	public static String UPDATE_NAME_FAILURE = "Update name exists";
	/**
	 */
	public static String USER_IMAGESUCCESS = "Insert image Success";
	public static String USER_IFAILURE = "Insert image Error";

	/**
	 */
	public static String DATE_FAILURE = "date Query Error";

	/**
	 */
	public static String REVIEW_FAILURE = "review error";
	public static String REVIEW_SUCCESS = "review success";

	/**
	 */
	public static String BEMOVE_FAILURE = "SendLove Error";
	public static String BEMOVE__SUCCESS = "SendLove Success";

	/**
	 */
	public static String RELATED_FAILURE = "Related Error";
	public static String RELATED_SUCCESS = "Related Success";

	/**
	 */
	public static String REPLY_FAILURE = "Reply Error";
	public static String REPLY_SUCCESS = "Reply Success";

	/**
	 */
	public static String PEOPLE_NEARBY_SUCCESS = "near Success";
	public static String PEOPLE_NEARBY_FAILURE = "near Error";

	/**
	 */
	public static String ADTTEN_SUCCESS = "adtten Success";
	public static String ADTTEN_FAILURE = "Violation Rule";
	public static String ADTTEN_DELETE = "adtten delete";

	/**
	 */
	public static String SERVER_ERROR = "The server Error";
	
	/**
	 *发公益规范 
	 */
	public static String PUBLICINTEREST= "publicinterest/";
	
	/**
	 * 包含关键字不允发送
	 */
	public static String SENDPUSH = "Context sensitive";
	/**
	 */
	public static String ImgUrl = null;
	public static String ImgUrlTo(Object th,String urlName){
		if(ImgUrl==null){
			String url = th.getClass().getResource("/").toString();
			try {
				url =  java.net.URLDecoder.decode(url,"utf-8");
			 
			url = url.substring(0, url.indexOf("Bemoved"));
			url = url.substring(url.indexOf(":") + 2, url.length());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
			ImgUrl = url+"gandong/";
		}
		
		return ImgUrl+urlName;
	}
	
	
	private static LinkedHashMap<String, String> map;
	public static String linkChina(String key){
		if(map==null){
			map = new LinkedHashMap<String, String>();
			map.put("中国", "86");
			map.put("中国香港", "852");
			map.put("中国澳门", "853");
			map.put("中国台湾", "886");
			map.put("马来西亚", "60");
			map.put("印度尼西亚", "62");
			map.put("菲律宾", "63");
			map.put("新加坡", "65");
			map.put("泰国", "66");
			map.put("文莱", "673");
			map.put("日本", "81");
			map.put("韩国", "82");
			map.put("越南", "84");
			map.put("朝鲜", "850");
			map.put("柬埔寨", "855");
			map.put("老挝", "856");
			map.put("孟加拉国", "880");
			map.put("土耳其", "90");
			map.put("印度", "91");
			map.put("巴基斯坦", "92");
			map.put("阿富汗", "93");
			map.put("斯里兰卡", "94");
			map.put("缅甸", "95");
			map.put("马尔代夫", "960");
			map.put("黎巴嫩", "961");
			map.put("约旦", "962");
			map.put("叙利亚", "963");
			map.put("伊拉克", "964");
			map.put("科威特", "965");
			map.put("沙特阿拉伯", "966");
			map.put("阿曼", "968");
			map.put("以色列", "972");
			map.put("巴林", "973");
			map.put("卡塔尔", "974");
			map.put("不丹", "975");
			map.put("蒙古", "976");
			map.put("尼泊尔", "977");
			map.put("伊朗", "98");
			map.put("塞浦路斯", "357");
			map.put("巴勒斯坦", "970");
			map.put("阿联酋", "971");
			map.put("也门", "967");
			map.put("俄罗斯联邦", "7");
			map.put("希腊", "30");
			map.put("荷兰", "31");
			map.put("比利时", "32");
			map.put("法国", "33");
			map.put("西班牙", "34");
			map.put("直布罗陀", "350");
			map.put("葡萄牙", "351");
			map.put("卢森堡", "352");
			map.put("爱尔兰", "353");
			map.put("冰岛", "354");
			map.put("阿尔巴尼亚", "355");
			map.put("马耳他", "356");
			map.put("安道尔", "376");
			map.put("芬兰", "358");
			map.put("保加利亚", "359");
			map.put("匈牙利", "36");
			map.put("德国", "49");
			map.put("南斯拉夫", "381");
			map.put("意大利", "39");
			map.put("圣马力诺", "378");
			map.put("梵蒂冈", "3906698");
			map.put("罗马尼亚", "40");
			map.put("瑞士", "41");
			map.put("列支登士敦", "423");
			map.put("奥地利", "43");
			map.put("英国", "44");
			map.put("丹麦", "45");
			map.put("瑞典", "46");
			map.put("挪威", "47");
			map.put("波兰", "48");
			map.put("捷克", "420");
			map.put("斯洛伐克", "421");
			map.put("摩纳哥", "377");
			map.put("马其顿", "389");
			map.put("克罗地亚", "385");
			map.put("斯洛文尼亚", "386");
			map.put("波斯尼亚和塞哥维那", "387");
			map.put("亚美尼亚共和国", "374");
			map.put("白俄罗斯共和国", "375");
			map.put("格鲁吉亚共和国", "995");
			map.put("哈萨克斯坦共和国", "7");
			map.put("吉尔吉斯坦共和国", "996");
			map.put("乌兹别克斯坦共和国", "998");
			map.put("塔吉克斯坦共和国", "992");
			map.put("土库曼斯坦共和国", "993");
			map.put("乌克兰", "380");
			map.put("立陶宛", "370");
			map.put("拉脱维亚", "371");
			map.put("爱沙尼亚", "372");
			map.put("摩尔多瓦", "373");
			map.put("阿塞拜疆", "994");
			map.put("埃及", "20");
			map.put("阿尔及利亚", "213");
			map.put("突尼斯", "216");
			map.put("利比亚", "218");
			map.put("冈比亚", "220");
			map.put("塞内加尔", "221");
			map.put("毛里塔尼亚", "222");
			map.put("马里", "223");
			map.put("几内亚", "224");
			map.put("科特迪瓦", "225");
			map.put("布基拉法索", "226");
			map.put("尼日尔", "227");
			map.put("多哥", "228");
			map.put("贝宁", "229");
			map.put("毛里求斯", "230");
			map.put("利比里亚", "231");
			map.put("塞拉利昂", "232");
			map.put("加纳", "233");
			map.put("尼日利亚", "234");
			map.put("乍得", "235");
			map.put("中非", "236");
			map.put("喀麦隆", "237");
			map.put("佛得角", "238");
			map.put("圣多美", "239");
			map.put("普林西比", "239");
			map.put("赤道几内亚", "240");
			map.put("加蓬", "241");
			map.put("刚果", "242");
			map.put("扎伊尔", "243");
			map.put("安哥拉", "244");
			map.put("几内亚比绍", "245");
			map.put("阿森松", "247");
			map.put("塞舌尔", "248");
			map.put("苏丹", "249");
			map.put("卢旺达", "250");
			map.put("埃塞俄比亚", "251");
			map.put("索马里", "252");
			map.put("吉布提", "253");
			map.put("肯尼亚", "254");
			map.put("坦桑尼亚", "255");
			map.put("乌干达", "256");
			map.put("布隆迪", "257");
			map.put("莫桑比克", "258");
			map.put("赞比亚", "260");
			map.put("马达加斯加", "261");
			map.put("留尼旺岛", "262");
			map.put("津巴布韦", "263");
			map.put("纳米比亚", "264");
			map.put("马拉维", "265");
			map.put("莱索托", "266");
			map.put("博茨瓦纳", "267");
			map.put("斯威士兰", "268");
			map.put("科摩罗", "269");
			map.put("南非", "27");
			map.put("圣赫勒拿", "290");
			map.put("阿鲁巴岛", "297");
			map.put("法罗群岛", "298");
			map.put("厄里特里亚", "291");
			map.put("加那利群岛(西)(拉斯帕尔马斯)", "3428");
			map.put("加那利群岛(西)(圣克鲁斯)", "3422");
			map.put("迪戈加西亚", "246");
			map.put("桑给巴尔", "259");
			map.put("马约特岛", "269");
			map.put("美国", "1");
			map.put("加拿大", "1");
			map.put("中途岛", "1808");
			map.put("夏威夷", "1808");
			map.put("威克岛", "1808");
			map.put("安圭拉岛", "1264");
			map.put("维尔京群岛", "1340");
			map.put("圣卢西亚", "1758");
			map.put("波多黎各", "1809");
			map.put("牙买加", "1876");
			map.put("巴哈马", "1242");
			map.put("巴巴多斯", "1246");
			map.put("阿拉斯加", "1907");
			map.put("格陵兰岛", "299");
			map.put("安提瓜和巴布达", "1268");
			map.put("百慕大群岛", "1441");
			map.put("开曼群岛", "1345");
			map.put("多米尼加联邦", "1767");
			map.put("多米尼加共和国", "1809");
			map.put("格林纳达", "1473");
			map.put("蒙特塞拉特岛", "1664");
			map.put("荷属安的列斯群岛", "599");
			map.put("波多黎哥", "1787");
			map.put("圣皮埃尔岛密克隆岛（法）", "508");
			map.put("圣克里斯托弗和尼维斯", "1869");
			map.put("圣文森特岛（英）", "1784");
			map.put("特立尼达和多巴哥", "1868");
			map.put("特克斯和凯科斯群岛", "1649");
			map.put("瓜多罗普", "590");
			map.put("福克兰群岛", "500");
			map.put("伯利兹", "501");
			map.put("危地马拉", "502");
			map.put("萨尔瓦多", "503");
			map.put("洪都拉斯", "504");
			map.put("尼加拉瓜", "505");
			map.put("哥斯达黎加", "506");
			map.put("巴拿马", "507");
			map.put("海地", "509");
			map.put("秘鲁", "51");
			map.put("墨西哥", "52");
			map.put("古巴", "53");
			map.put("阿根廷", "54");
			map.put("巴西", "55");
			map.put("智利", "56");
			map.put("哥伦比亚", "57");
			map.put("委内瑞拉", "58");
			map.put("玻利维亚", "591");
			map.put("圭亚那", "592");
			map.put("厄瓜多尔", "593");
			map.put("法属圭亚那", "594");
			map.put("巴拉圭", "595");
			map.put("马提尼克", "596");
			map.put("苏里南", "597");
			map.put("乌拉圭", "598");
			map.put("澳大利亚", "61");
			map.put("新西兰", "64");
			map.put("关岛", "1671");
			map.put("科科斯岛", "619162");
			map.put("诺福克岛", "619164");
			map.put("圣诞岛", "1671");
			map.put("瑙鲁", "674");
			map.put("汤加", "676");
			map.put("所罗门群岛", "677");
			map.put("瓦努阿图", "678");
			map.put("斐济", "679");
			map.put("科克群岛", "682");
			map.put("纽埃岛", "683");
			map.put("东萨摩亚", "684");
			map.put("西萨摩亚", "685");
			map.put("基里巴斯", "686");
			map.put("图瓦卢", "688");
			map.put("法属波里尼西亚、塔希提", "689");
			map.put("马里亚纳群岛", "1670");
			map.put("新咯里多尼亚群岛 ", "687");
			map.put("巴布亚新几内亚", "675");
			map.put("帕劳 ", "680");
			map.put("托克鲁", "690");
			map.put("密克罗尼西亚", "691");
			map.put("马绍尔群岛", "692");
			map.put("瓦里斯和富士那群岛", "1681");
		}
		return map.get(key);
	}

}
