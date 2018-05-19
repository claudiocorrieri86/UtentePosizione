/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Cloud
 */
public class UserHelper {
    Session session = null;

    public UserHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public User getUserByUsername(String username) {
        User retUser = null;
        org.hibernate.Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("from User as user where user.username = :username");
            q.setParameter("username", username);
            List<User> userList = (List<User>) q.list();
            if(userList != null && !userList.isEmpty()){
                retUser = userList.get(0);
            }
            tx.commit();
        } catch (Exception e) {
            //LOG
        }
        return retUser;
    }
    
    public int updateUserActiveById(Integer active, Integer id) {
        int result = 0;
        org.hibernate.Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("update User set active = :active where id = :id'");
            q.setParameter("active", active);
            q.setParameter("id", id);
            
            result = q.executeUpdate();

            tx.commit();
        } catch (Exception e) {
            //LOG
        }
        return result;
    }
}
