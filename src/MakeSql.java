import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;



public class MakeSql {

	
	public static void main(String[] args) {
//		Random random = new Random();
//		for(int i=0;i<20000000;i++){
//	         float lat = (float) ((random.nextInt(18000000)*-1+9000000)/100000.0);
//	         float lng = (float) ((random.nextInt(36000000)*-1+18000000)/100000.0);
//		
//			String sql ="insert into `index` (`lat`,`lng`) values ("+lat+","+lng+");";
//		    System.out.println(sql);
//		}
		float f= 12.354f;
		StringBuffer sql = new StringBuffer();
        sql.append("SELECT id,lat,lng,6366.564864*2*ASIN(SQRT(POWER(SIN((");
        sql.append("@lat");
        sql.append("- lat)*pi()/180 / 2), 2) + COS(");
        sql.append("@lat");
        sql.append(" * pi()/180) * COS(lat * pi()/180) *  POWER(SIN((");
        sql.append("@lng");
        sql.append(" - lng) * pi()/180 / 2), 2) )) as dist FROM RockUser WHERE lat BETWEEN ");
        sql.append("@lat_left");
        sql.append(" AND ");
        sql.append("@lat_right");
        sql.append(" AND lng BETWEEN ");
        sql.append("@lng_left");
        sql.append(" AND ");
        sql.append("@lng_right");
        sql.append(" having dist < ");
        sql.append("@dist");
        sql.append(" ORDER BY dist;");
//		System.out.println(sql.toString());
//		System.out.println(new Timestamp(System.currentTimeMillis()));
//	    for(int i=0;i<1000;i++){
//	    	String	sqls = "INSERT INTO `t_role` (`roleId`, `powerIds`, `roleName`, `roleDesc`) VALUES (null, '"+i+"', '超级管理员', '超级管理员拥有最高权限');";
//	    	System.out.println(sqls);
//	    }

//        List<String> l = null;
//        l.add("123");
//        if(l==null){
//        	System.out.println("nulll");
//        }else{
//        	System.out.println(l);
//        }
        String a=".1.2";
        int i=a.lastIndexOf("5");
//        System.out.println(i);
        System.out.println(a.substring(-1));
//        System.out.println(a.substring(a.lastIndexOf("5")));
        
	}
}
