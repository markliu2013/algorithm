package com.zfwhub.ml.antai;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.StatelessSession;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.Session;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zfwhub.ml.utils.JdbcUtils;

public class Main {

    static JdbcTemplate jdbc = new JdbcTemplate(JdbcUtils.getDataSource());

//    static EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("com.zfwhub.ml");
//    static EntityManager entityManager = sessionFactory.createEntityManager();
    
    static int maxResult = 1000;


   /* private static void query1() {
        String hql1 = "select t.itemBuyerPk.buyerAdminId from ItemBuyerTrain as t group by t.itemBuyerPk.buyerAdminId";
        List<?> list = entityManager.createQuery(hql1).getResultList();
        System.out.println(list.size());
    }*/
    
    /*private static void test() {
        String hql1 = "select itemBuyerPk.buyerAdminId, max(itemBuyerPk.iRank) from ItemBuyerTrain group by itemBuyerPk.buyerAdminId";
        int offset = 0;
        Query query = entityManager.createQuery(hql1);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        while (true) {
            query.setFirstResult(offset);
            query.setMaxResults(maxResult);
            List<?> list = query.getResultList();
            for (Object object : list) {
                Object[] row = (Object[]) object;
                System.out.println(row[0]);
            }
            entityManager.flush();
            entityManager.clear();
            offset += maxResult;
        }
//        tx.commit();
//        entityManager.close();
    }*/
    
    // 购买之前购买的比率有多少
    public static void query3() {
        Set<String> set = new HashSet<>();
        String sql = "select * from buyer";
        boolean resultFlag = true;
        int offset = 0;
        while (resultFlag) {
            List<Map<String, Object>> list = jdbc.queryForList(JdbcUtils.page(sql, offset, maxResult));
            if (list.size() == 0) {
                resultFlag = false;
                continue;
            }
            for (Map<String, Object> map : list) {
                String buyerId = ((BigDecimal) map.get("BUYER_ADMIN_ID")).toString();
                String sql2 = "select * from an_train where buyer_admin_id=" + buyerId +" order by irank desc";
                List<Map<String, Object>> list2 = jdbc.queryForList(sql2);
                for (int i = 0; i < list2.size()-1; i++) {
                    Map<String, Object> map2 = list2.get(i);
                    if (map2.get("ITEM_ID").equals(map.get("ITEM_ID"))) {
                        System.out.println(buyerId);
                        String sql3 = "insert into t2 values("+buyerId+")";
                        jdbc.execute(sql3);
                        set.add(buyerId);
                    }
                }
            }
            offset += maxResult;
        }
        System.out.println(set.size()+"------");
    }
    
    // 生成提交数据，按照会购买之前购买的规则。
    public static void query4() {
        /*for (int i = 1; i <= 30; i++) {
            String sql10 = "update submit p set p.p"+i+"=null";
            jdbc.execute(sql10);
        }*/
        String sql = "select * from submit";
        int count = 0;
        List<Map<String, Object>> list = jdbc.queryForList(sql);
        for (Map<String, Object> map : list) {
            int buyerId = ((BigDecimal)map.get("U_ID")).intValue();
            List<Integer> preds = new ArrayList<>();
            String sql2 = "select * from an_test where buyer_admin_id=" + buyerId +" order by irank";
            List<Map<String, Object>> list2 = jdbc.queryForList(sql2);
            for (Map<String, Object> map2 : list2) {
                Integer itemId = ((BigDecimal)map2.get("ITEM_ID")).intValue();
                if (!preds.contains(itemId)) {
                    preds.add(itemId);
                    if (preds.size()>=30) {
                        break;
                    }
                }
            }
            if (preds.size() < 30) {
                Integer preItemId = ((BigDecimal)list2.get(0).get("ITEM_ID")).intValue();
                // 查找之前同一个店铺
                String sql3 = "select item_id from item_arr where cate_id = (select cate_id from item_arr where item_id="+preItemId+") order by item_price";
                List<Map<String, Object>> list3 = jdbc.queryForList(sql3);
                for (Map<String, Object> map3 : list3) {
                    Integer itemId = ((BigDecimal)map3.get("ITEM_ID")).intValue();
                    preds.add(itemId);
                    if (preds.size()>=30) {
                        break;
                    }
                }
            }
            
            // 仍然不够30个预测, 选择预测的前一天，销量最高的产品。
            if (preds.size() < 30) {
                String sql3 = "select item_id, count(item_id) count_item_id from an_test where to_char(create_order_time, 'YYYY-MM-DD') in ('2018-08-31', '2018-08-30', '2018-08-29') group by item_id order by count_item_id desc";
                System.out.println(JdbcUtils.page(sql3, 0, 30));
                List<Map<String, Object>> list3 = jdbc.queryForList(JdbcUtils.page(sql3, 0, 30));
                for (Map<String, Object> map3 : list3) {
                    Integer itemId = ((BigDecimal)map3.get("ITEM_ID")).intValue();
                    preds.add(itemId);
                    if (preds.size()>=30) {
                        break;
                    }
                }
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("update submit set ");
            for (int i = 0; i < preds.size(); i++) {
                if (i+1>30) {
                    break;
                }
                sb.append("p");
                sb.append(i+1);
                sb.append("=");
                sb.append(preds.get(i));
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
            sb.append(" where u_id="+buyerId);
            jdbc.update(sb.toString());
            System.out.println(count++);
        }
    }
    
    public static void query5() {
        String sql = "select * from submit";
        int count = 0;
        List<Map<String, Object>> list = jdbc.queryForList(sql);
        for (Map<String, Object> map : list) {
            int buyerId = ((BigDecimal)map.get("U_ID")).intValue();
            List<Integer> preds = new ArrayList<>();
            String sql2 = "select * from an_test where buyer_admin_id=" + buyerId +" order by irank";
            List<Map<String, Object>> list2 = jdbc.queryForList(sql2);
            int count1 = 0;
            for (Map<String, Object> map2 : list2) {
                count1++;
                if (count1>15) {
                    break;
                }
                Integer itemId = ((BigDecimal)map2.get("ITEM_ID")).intValue();
                if (!preds.contains(itemId)) {
                    preds.add(itemId);
                    if (preds.size()>=30) {
                        break;
                    }
                }
            }
            if (preds.size() < 30) {
                Integer preItemId = ((BigDecimal)list2.get(0).get("ITEM_ID")).intValue();
                // 查找之前同一个店铺
                String sql3 = "select item_id from item_arr where cate_id = (select cate_id from item_arr where item_id="+preItemId+") order by item_price";
                List<Map<String, Object>> list3 = jdbc.queryForList(sql3);
                for (Map<String, Object> map3 : list3) {
                    Integer itemId = ((BigDecimal)map3.get("ITEM_ID")).intValue();
                    preds.add(itemId);
                    if (preds.size()>=30) {
                        break;
                    }
                }
            }
            
            // 仍然不够30个预测, 选择预测的前一天，销量最高的产品。
            if (preds.size() < 30) {
                String sql3 = "select item_id, count(item_id) count_item_id from an_test where to_char(create_order_time, 'YYYY-MM-DD') in ('2018-08-31', '2018-08-30', '2018-08-29') group by item_id order by count_item_id desc";
                System.out.println(JdbcUtils.page(sql3, 0, 30));
                List<Map<String, Object>> list3 = jdbc.queryForList(JdbcUtils.page(sql3, 0, 30));
                for (Map<String, Object> map3 : list3) {
                    Integer itemId = ((BigDecimal)map3.get("ITEM_ID")).intValue();
                    preds.add(itemId);
                    if (preds.size()>=30) {
                        break;
                    }
                }
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("update submit set ");
            for (int i = 0; i < preds.size(); i++) {
                if (i+1>30) {
                    break;
                }
                sb.append("p");
                sb.append(i+1);
                sb.append("=");
                sb.append(preds.get(i));
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
            sb.append(" where u_id="+buyerId);
            jdbc.update(sb.toString());
            System.out.println(count++);
        }
    }

    public static void main(String[] args) {
        query5();
        
        //        String sql = "select * from item_arr";
        //        List<Item> items = jdbc.query(sql, new BeanPropertyRowMapper<Item>());
        //        System.out.println(items.size());
//                EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("com.zfwhub.ml");
//                EntityManager entityManager = sessionFactory.createEntityManager();
//                TypedQuery<Item> query = entityManager.createQuery("from Item", Item.class);
////                
//                query.setFirstResult(0);
//                query.setMaxResults(5);
//                List<Item> items2 = query.getResultList();
//                for (Item item : items2) {
//                    System.out.println(item);
//                }
//                System.out.println("------");
//                query.setFirstResult(5);
//                query.setMaxResults(5);
//                List<Item> items = query.getResultList();
//                for (Item item : items) {
//                    System.out.println(item);
//                }
    }

}
