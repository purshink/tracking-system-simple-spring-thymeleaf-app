package com.example.tracking_system.web;

import com.example.tracking_system.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    private final MaterialService materialService;

    @Autowired
    public HomeController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping("/")
    public String showHome(Model model){
        model.addAttribute("materials", this.materialService.getAllMaterials());

        return "index";
    }
}
