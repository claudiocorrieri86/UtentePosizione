/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.List;
import model.Location;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author Cloud
 */
public class LocationHelper {
    Session session = null;

    public LocationHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List<Location> getLocation(Double lat, Double lng, String start, String end) {
        List<Location> locations = null;
        org.hibernate.Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createSQLQuery("from locations as loc where loc.lat = :lat and loc.lng = :lng and loc.insdate between :start and :end");
            q.setParameter("lat", lat);
            q.setParameter("lng", lng);
            q.setParameter("start", start);
            q.setParameter("end", end);
            locations = (List<Location>) q.list();

            tx.commit();
        } catch (Exception e) {
            //LOG
        }
        return locations;
    }
}
