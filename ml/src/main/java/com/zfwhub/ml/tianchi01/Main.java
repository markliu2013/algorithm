package com.zfwhub.ml.tianchi01;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    
    // 生成训练集或测试集的SQL，根据某一天
    private static String generateTrainSQLByDay(Calendar c, boolean isTrain) {
        String tableName = "t3";
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, -1);
        Date date1 = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, -1);
        Date date2 = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, -1);
        Date date3 = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, -1);
        Date date4 = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, -1);
        Date date5 = c.getTime();
        
        String isBuy = "";
        if (isTrain) {
            isBuy = ",case when (count(case when (behavior_type = 4 and (time BETWEEN TO_DATE('"+df.format(date)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end)) > 0 then 1 else 0 end is_buy";
        }
        
        String countView1 = ",count(case when (behavior_type = 1 and (time BETWEEN TO_DATE('"+df.format(date1)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date1)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_view_1";
        String countFav1 = ",count(case when (behavior_type = 2 and (time BETWEEN TO_DATE('"+df.format(date1)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date1)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_fav_1";
        String countCart1 = ",count(case when (behavior_type = 3 and (time BETWEEN TO_DATE('"+df.format(date1)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date1)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_cart_1";
        String countBuy1 = ",count(case when (behavior_type = 4 and (time BETWEEN TO_DATE('"+df.format(date1)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date1)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_buy_1";
        
        String countView2 = ",count(case when (behavior_type = 1 and (time BETWEEN TO_DATE('"+df.format(date2)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date2)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_view_2";
        String countFav2 = ",count(case when (behavior_type = 2 and (time BETWEEN TO_DATE('"+df.format(date2)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date2)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_fav_2";
        String countCart2 = ",count(case when (behavior_type = 3 and (time BETWEEN TO_DATE('"+df.format(date2)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date2)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_cart_2";
        String countBuy2 = ",count(case when (behavior_type = 4 and (time BETWEEN TO_DATE('"+df.format(date2)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date2)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_buy_2";
        

        String countView3 = ",count(case when (behavior_type = 1 and (time BETWEEN TO_DATE('"+df.format(date3)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date3)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_view_3";
        String countFav3 = ",count(case when (behavior_type = 2 and (time BETWEEN TO_DATE('"+df.format(date3)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date3)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_fav_3";
        String countCart3 = ",count(case when (behavior_type = 3 and (time BETWEEN TO_DATE('"+df.format(date3)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date3)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_cart_3";
        String countBuy3 = ",count(case when (behavior_type = 4 and (time BETWEEN TO_DATE('"+df.format(date3)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date3)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_buy_3";
        

        String countView4 = ",count(case when (behavior_type = 1 and (time BETWEEN TO_DATE('"+df.format(date4)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date4)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_view_4";
        String countFav4 = ",count(case when (behavior_type = 2 and (time BETWEEN TO_DATE('"+df.format(date4)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date4)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_fav_4";
        String countCart4 = ",count(case when (behavior_type = 3 and (time BETWEEN TO_DATE('"+df.format(date4)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date4)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_cart_4";
        String countBuy4 = ",count(case when (behavior_type = 4 and (time BETWEEN TO_DATE('"+df.format(date4)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date4)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_buy_4";
        

        String countView5 = ",count(case when (behavior_type = 1 and (time BETWEEN TO_DATE('"+df.format(date5)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date5)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_view_5";
        String countFav5 = ",count(case when (behavior_type = 2 and (time BETWEEN TO_DATE('"+df.format(date5)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date5)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_fav_5";
        String countCart5 = ",count(case when (behavior_type = 3 and (time BETWEEN TO_DATE('"+df.format(date5)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date5)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_cart_5";
        String countBuy5 = ",count(case when (behavior_type = 4 and (time BETWEEN TO_DATE('"+df.format(date5)+"', 'YYYY-MM-DD') AND TO_DATE('"+df.format(date5)+" 23:59:59', 'YYYY-MM-DD HH24:MI:SS'))) then 1 else null end) count_buy_5";
        
        String SQL = "select user_id, item_id, '" + df.format(date) + "' as check_date"
                
                + countView1
                + countFav1
                + countCart1
                + countBuy1
                
                + countView2
                + countFav2
                + countCart2
                + countBuy2
                
                + countView3
                + countFav3
                + countCart3
                + countBuy3
                
                + countView4
                + countFav4
                + countCart4
                + countBuy4
                
                + countView5
                + countFav5
                + countCart5
                + countBuy5
                
                + isBuy + " "
                
                + "from " + tableName + " "
                + "group by user_id, item_id";
        
        return SQL;
    }
    
    public static String generateTrainSQL() {
        StringBuilder sb = new StringBuilder();
        List<Calendar> calendars = new ArrayList<>();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2014, 10, 18);
        calendars.add(startCalendar);
        for (int i = 0; i < 30; i++) {
            Calendar c = (Calendar)startCalendar.clone();
            c.add(Calendar.DAY_OF_YEAR, i+1);
            calendars.add(c);
        }
        for (int i = 0; i < calendars.size()-1; i++) {
            String sql = generateTrainSQLByDay(calendars.get(i), true);
            sb.append(sql);
            sb.append(" UNION ");
        }
        sb.append(generateTrainSQLByDay(calendars.get(calendars.size()-1), true));
        return sb.toString();
    }
    
    public static String generatePredictSQL() {
        Calendar c = Calendar.getInstance();
        // 要预c测的日期
        c.set(2014, 11, 19);
        return generateTrainSQLByDay(c, false);
    }
    
    public static void main(String[] args) {
        System.out.println(generateTrainSQL());
        System.out.println(generatePredictSQL());
    }
    
}
