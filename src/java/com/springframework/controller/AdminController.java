/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.controller;

import com.springframework.model.Barang;
import com.springframework.model.KategoriBarang;
import com.springframework.model.Pembelian;
import com.springframework.model.Temporary;
import com.springframework.service.dao.BarangDAO;
import com.springframework.service.dao.KategoriBarangDAO;
import com.springframework.service.dao.PembelianDAO;
import com.springframework.service.dao.TemporaryDAO;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author rizal
 */
@Controller
@SessionAttributes("username")
public class AdminController {

    @Autowired
    private PembelianDAO service;

    @Autowired
    private BarangDAO serviceBarang;

    @Autowired
    private TemporaryDAO serviceTemporary;

    @Autowired
    private KategoriBarangDAO serviceKategori;

    @RequestMapping(value = "dashboardadmin.htm")
    public String viewDashboardAdmin(ModelMap modelMap, Model model, HttpSession session,
            HttpServletResponse response) throws IOException {

        if (!model.containsAttribute("username")) {
            response.sendRedirect("accesdenied.htm");
        }

        List<KategoriBarang> b = serviceKategori.getAll();
        modelMap.put("list", b);

        if (session.getAttribute("idbeli") != null) {
            List<Temporary> t = serviceTemporary.getCount(session.getAttribute("idbeli").toString());
            modelMap.put("chart", t);
        }

        return "dashboardadmin";
    }

    @RequestMapping(value = "shopping.htm")
    public String shopping(ModelMap modelMap, Model model, HttpSession session,
            HttpServletResponse response, @RequestParam(value = "id",
                    required = false) long id) throws IOException {

        List<Barang> l = serviceBarang.getByStatusStock(id);
        modelMap.put("list", l);

        if (session.getAttribute("idbeli") != null) {
            List<Temporary> t = serviceTemporary.getCount(session.getAttribute("idbeli").toString());
            modelMap.put("chart", t);
        }

        return "shopping";
    }

    @RequestMapping(value = "errorchart.htm")
    public String viewErrorChart() {
        return "errorchart";
    }

    @RequestMapping(value = "errorcheckout.htm")
    public String viewErrorCheckout() {
        return "errorcheckout";
    }

    @RequestMapping(value = "addtochart.htm", method = RequestMethod.POST)
    public void addToChart(HttpServletResponse response, ModelMap map, HttpSession session, @ModelAttribute("Temporary") Temporary t,
            @RequestParam(value = "cbBarang", required = false) String id) throws IOException {

        if (t.getQty() > 0 && t.getQty() <= serviceBarang.getStockById(id)) {
            if (session.getAttribute("idbeli") == null) {

                if (service.getIdBeli() == null) {
                    session.setAttribute("idbeli", service.generateId("Temporary"));
                    t.setIdBeli(service.generateId("Temporary"));
                } else if (service.getIdBeli() != null) {
                    session.setAttribute("idbeli", service.generateId("Pembelian"));
                    t.setIdBeli(service.generateId("Pembelian"));
                }

            } else {
                t.setIdBeli(session.getAttribute("idbeli").toString());
            }

            Barang b = serviceBarang.getById(id);
            t.setIdBarang(b);

            if (serviceTemporary.save(t)) {
                session.setAttribute("id", t.getIdBeli());

                response.sendRedirect("dashboardadmin.htm");
            } else {
                response.sendRedirect("errorchart.htm");
            }
        } else {
            response.sendRedirect("errorchart.htm");;
        }
    }

    @RequestMapping(value = "emptychart.htm")
    public String emptyChart() {
        return "emptychart";
    }

    @RequestMapping(value = "chart.htm", method = RequestMethod.GET)
    public String viewChart(ModelMap modelMap, Model model, HttpServletResponse response,
            HttpSession session) throws IOException {

        if (!model.containsAttribute("username")) {
            response.sendRedirect("accesdenied.htm");
        }

        if (session.getAttribute("idbeli") == null) {
            response.sendRedirect("emptychart.htm");
        } else {

            List<Temporary> t = serviceTemporary.getCount(session.getAttribute("idbeli").toString());
            modelMap.put("chart", t);

            List<Temporary> c = serviceTemporary.getChartById(session.getAttribute("idbeli").toString());
            modelMap.put("list", c);

            double sub = serviceTemporary.subTotalByChart(session.getAttribute("idbeli").toString());
            modelMap.put("sub", sub);
        }

        return "chart";
    }

    @RequestMapping(value = "plusqty.htm")
    public void plusChart(HttpServletResponse response,
            @RequestParam(value = "id", required = false) String id,
            HttpSession session, ModelMap modelMap) throws IOException {

        service.plusChart(session.getAttribute("idbeli").toString(), id);
        List<Temporary> c = serviceTemporary.getChartById(session.getAttribute("idbeli").toString());
        modelMap.put("list", c);

        double sub = serviceTemporary.subTotalByChart(session.getAttribute("idbeli").toString());
        modelMap.put("sub", sub);

        response.sendRedirect("chart.htm");
    }

    @RequestMapping(value = "minqty.htm")
    public void minChart(HttpServletResponse response,
            @RequestParam(value = "id", required = false) String id,
            HttpSession session, ModelMap modelMap) throws IOException {

        if (service.minChart(session.getAttribute("idbeli").toString(), id)) {

            List<Temporary> c = serviceTemporary.getChartById(session.getAttribute("idbeli").toString());
            modelMap.put("list", c);

            double sub = serviceTemporary.subTotalByChart(session.getAttribute("idbeli").toString());
            modelMap.put("sub", sub);

        }

        response.sendRedirect("chart.htm");
    }

    @RequestMapping(value = "deletebrg.htm", method = RequestMethod.GET)
    public void deleteBarang(HttpServletResponse response,
            @RequestParam(value = "id", required = false) String id,
            HttpSession session, ModelMap modelMap) throws IOException {

        service.deleteByChart(session.getAttribute("idbeli").toString(), id);

        List<Temporary> c = serviceTemporary.getChartById(session.getAttribute("idbeli").toString());
        modelMap.put("list", c);

        double sub = serviceTemporary.subTotalByChart(session.getAttribute("idbeli").toString());
        modelMap.put("sub", sub);

        response.sendRedirect("chart.htm");
    }

    @RequestMapping("checkout.htm")
    public void checkout(HttpServletResponse response,
            @RequestParam(value = "total", required = false) double ttl,
            @RequestParam(value = "bayar", required = false) double byr,
            HttpSession session) throws IOException {

        if (byr >= ttl) {
            Pembelian model = new Pembelian();
            model.setIdBeli(session.getAttribute("idbeli").toString());
            model.setTglBeli(new Date());
            model.setTotal(ttl);

            if (service.save(model)) {
                service.clearChart(session.getAttribute("idbeli").toString());
                session.removeAttribute("idbeli");
                response.sendRedirect("dashboardadmin.htm");
            } else {
                response.sendRedirect("errorchart.htm");
            }
        } else {
            response.sendRedirect("errorcheckout.htm");
        }
    }
}
