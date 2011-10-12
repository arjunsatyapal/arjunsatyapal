package com.smartgwt.sample.server;

import com.isomorphic.jpa.EMF;
import com.isomorphic.rpc.RPCManager;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FillDataServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        EntityManager em = null;
        Object tx = null;
        try {
            Country country = null;
            City city = null;
            em = EMF.getEntityManager();
            Query q = em.createQuery("select c from Country c where c.countryCode = :countryCode");
            q.setParameter("countryCode", "LT");
            List res = q.getResultList();
            if (res.size() == 0) {
                tx = EMF.getTransaction(em);
                country = new Country();
                country.setCountryCode("LT");
                country.setCountryName("Lithuania");
                city = new City ();
                city.setCityName("Vilnius");
                country.getCities().add(city);
                city = new City ();
                city.setCityName("Kaunas");
                country.getCities().add(city);
                city = new City ();
                city.setCityName("Klaipėda");
                country.getCities().add(city);
                em.persist(country);
                EMF.commitTransaction(tx);
            }
            q.setParameter("countryCode", "LV");
            res = q.getResultList();
            if (res.size() == 0) {
                tx = EMF.getTransaction(em);
                country = new Country();
                country.setCountryCode("LV");
                country.setCountryName("Latvia");
                city = new City ();
                city.setCityName("Riga");
                country.getCities().add(city);
                em.persist(country);
                EMF.commitTransaction(tx);
            }
            q.setParameter("countryCode", "EE");
            res = q.getResultList();
            if (res.size() == 0) {
                tx = EMF.getTransaction(em);
                country = new Country();
                country.setCountryCode("EE");
                country.setCountryName("Estonia");
                city = new City ();
                city.setCityName("Tallinn");
                country.getCities().add(city);
                em.persist(country);
                EMF.commitTransaction(tx);
            }

            RPCManager rpcManager = new RPCManager(request, response);
            rpcManager.send("");
        } catch (Exception ex) {
            EMF.rollbackTransaction(tx);
            ex.printStackTrace();
        } finally {
            EMF.returnEntityManager(em);
        }
    } 

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

}
