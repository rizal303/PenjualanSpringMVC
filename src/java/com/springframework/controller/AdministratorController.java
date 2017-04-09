/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.controller;

import com.springframework.model.Barang;
import com.springframework.model.KategoriBarang;
import com.springframework.service.dao.BarangDAO;
import com.springframework.service.dao.KategoriBarangDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class AdministratorController {

    @Autowired
    private KategoriBarangDAO service;

    @Autowired
    private BarangDAO servicebarang;

    @RequestMapping(value = "dashboardadministrator.htm")
    public String viewDashboardAdministrator(Model model, HttpServletResponse response) throws IOException {

        if (!model.containsAttribute("username")) {
            response.sendRedirect("accesdenied.htm");
        }
        return "dashboardadministrator";
    }

    @RequestMapping(value = "errorkategoritambah.htm")
    public String viewErrorCategory1() {
        return "errorkategoritambah";
    }

    @RequestMapping(value = "errorkategoriedit.htm")
    public String viewErrorCategory2() {
        return "errorkategoritambah";
    }

    @RequestMapping(value = "errorkategoridelete.htm")
    public String viewErrorCategory3() {
        return "errorkategoridelete";
    }

    @RequestMapping(value = "errorbarangtambah.htm")
    public String viewErrorBarang1() {
        return "errorbarangambah";
    }

    @RequestMapping(value = "errorbarangedit.htm")
    public String viewErrorbarang2() {
        return "erroreditambah";
    }

    @RequestMapping(value = "errorbarangdelete.htm")
    public String viewErrorbarang3() {
        return "errorbarangdelete";
    }

    @RequestMapping(value = "kategoribarang.htm", method = RequestMethod.GET)
    public String viewKategoriBarang(ModelMap modelMap, Model model, HttpServletResponse response) throws IOException {

        if (!model.containsAttribute("username")) {
            response.sendRedirect("accesdenied.htm");
        }

        List<KategoriBarang> lp = service.getAll();
        modelMap.put("list", lp);

        return "kategoribarang";
    }

    @RequestMapping(value = "tambahkategori.htm")
    public String viewCreateCategory() {
        return "tambahkategori";
    }

    @RequestMapping(value = "prosestambahkategori.htm", method = RequestMethod.POST)
    public void prosesTambahKategori(HttpServletResponse response,
            @ModelAttribute("KategoriBarang") KategoriBarang k) throws IOException {

        k.setStatus("Aktif");

        if (service.save(k)) {
            response.sendRedirect("tambahkategori.htm");
        } else {
            response.sendRedirect("errorkategoritambah.htm");
        }
    }

    @RequestMapping(value = "editkategori.htm", method = RequestMethod.GET)
    public String viewEditKategori(Model model, HttpServletResponse response,
            ModelMap modelMap, @RequestParam(value = "id",
                    required = false) long id) throws IOException {

        if (!model.containsAttribute("username")) {
            response.sendRedirect("accesdenied.htm");
        }

        KategoriBarang kb = service.getById(id);
        modelMap.put("kb", kb);

        return "editkategori";
    }

    @RequestMapping(value = "proseseditkategori", method = RequestMethod.POST)
    public void prosesEditKategori(HttpServletResponse response,
            @ModelAttribute("KategoriBarang") KategoriBarang k) throws IOException {

        if (service.edit(k)) {
            response.sendRedirect("kategoribarang.htm");
        } else {
            response.sendRedirect("errorkategoriedit.htm");
        }
    }

    @RequestMapping(value = "deletekategori.htm")
    public void prosesDeleteKategori(HttpServletResponse response,
            @RequestParam(value = "id", required = false) long id) throws IOException {

        if (service.editById(id)) {
            response.sendRedirect("kategoribarang.htm");
        } else {
            response.sendRedirect("errorkategoridelete.htm");
        }
    }

    @RequestMapping(value = "barang.htm", method = RequestMethod.GET)
    public String viewBarang(ModelMap modelMap, Model model, HttpServletResponse response) throws IOException {

        if (!model.containsAttribute("username")) {
            response.sendRedirect("accesdenied.htm");
        }

        List<Barang> br = servicebarang.getAll();
        modelMap.put("list", br);

        return "barang";
    }

    @RequestMapping(value = "tambahbarang.htm")
    public String viewCreateBarang(ModelMap modelMap, Model model, HttpServletResponse response) throws IOException {

        if (!model.containsAttribute("username")) {
            response.sendRedirect("accesdenied.htm");
        }

        List<KategoriBarang> lp = service.getAll();
        modelMap.put("list", lp);

        return "tambahbarang";
    }

    @RequestMapping(value = "prosestambahbarang.htm", method = RequestMethod.POST)
    public void prosesTambahBarang(HttpServletResponse response, @ModelAttribute("Barang") Barang b,
            @RequestParam(value = "cbKategori", required = false) long id) throws IOException {

        KategoriBarang kb = service.getById(id);

        b.setIdBarang(servicebarang.generateId());
        b.setIdKategori(kb);
        b.setStatus("Dijual");

        if (servicebarang.save(b)) {
            response.sendRedirect("tambahbarang.htm");
        } else {
            response.sendRedirect("errorbarangtambah.htm");
        }
    }

    @RequestMapping(value = "editbarang.htm", method = RequestMethod.GET)
    public String viewEditBarang(Model model, HttpServletResponse response,
            ModelMap modelMap, @RequestParam(value = "id",
                    required = false) String id) throws IOException {

        if (!model.containsAttribute("username")) {
            response.sendRedirect("accesdenied.htm");
        }

        List<KategoriBarang> lp = service.getAll();
        modelMap.put("list", lp);

        Barang b = servicebarang.getById(id);
        modelMap.put("b", b);

        return "editbarang";
    }

    @RequestMapping(value = "proseseditbarang.htm", method = RequestMethod.POST)
    public void prosesEditBarang(HttpServletResponse response, @ModelAttribute("Barang") Barang b,
            @RequestParam(value = "cbKategori", required = false) long id) throws IOException {

        KategoriBarang kb = service.getById(id);
        b.setIdKategori(kb);
        
        if (servicebarang.edit(b)) {
            response.sendRedirect("barang.htm");
        } else {
            response.sendRedirect("errorbarangedit.htm");
        }
    }

    @RequestMapping(value = "deletebarang.htm")
    public void prosesDeleteBarang(HttpServletResponse response,
            @RequestParam(value = "id", required = false) String id) throws IOException {

        if (servicebarang.editById(id)) {
            response.sendRedirect("barang.htm");
        } else {
            response.sendRedirect("errorbarangdelete.htm");
        }
    }
}
