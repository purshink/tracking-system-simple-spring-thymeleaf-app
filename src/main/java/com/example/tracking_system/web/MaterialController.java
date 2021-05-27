package com.example.tracking_system.web;

import com.example.tracking_system.model.binding.MaterialBindingModel;
import com.example.tracking_system.model.binding.MaterialUpdateBindingModel;
import com.example.tracking_system.model.binding.SupplierUpdateBindingModel;
import com.example.tracking_system.model.entity.Material;
import com.example.tracking_system.model.entity.service.MaterialServiceModel;
import com.example.tracking_system.service.MaterialService;
import com.example.tracking_system.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@EnableWebMvc
public class MaterialController {

    private final MaterialService materialService;
    private final SupplierService supplierService;
    private final ModelMapper modelMapper;

    @Autowired
    public MaterialController(MaterialService materialService, SupplierService supplierService, ModelMapper modelMapper) {
        this.materialService = materialService;
        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/material-add")
    public String showHome(Model model){

        if(!model.containsAttribute("materialBindingModel")) {
            model.addAttribute("materialBindingModel", new MaterialBindingModel());
            model.addAttribute("noSupplier", false);
        }
        return "material-add";
    }

    @PostMapping("/material-add")
    public String saveMaterial(@Valid MaterialBindingModel materialBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("materialBindingModel", materialBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.materialBindingModel", bindingResult);
            if(this.supplierService.findSupplier(materialBindingModel.getSupplier())== null){
                redirectAttributes.addFlashAttribute("noSupplier", true);
            }

            return "material-add";
        }
        boolean isSaved = this.materialService.saveMaterial(this.modelMapper.map(materialBindingModel, MaterialServiceModel.class));

        if(!isSaved){
            redirectAttributes.addFlashAttribute("materialBindingModel", materialBindingModel);
            if(this.supplierService.findSupplier(materialBindingModel.getSupplier())== null){
                redirectAttributes.addFlashAttribute("noSupplier", true);
            }
            return "redirect:/material-add";
        }

        return"redirect:/";
                }

    @GetMapping("/material-update/{id}")
    public String showUpdateMaterial(@PathVariable Long id, Model model) {


        if(!model.containsAttribute("materialUpdateBindingModel")) {
            Material materialById = this.materialService.findMaterialById(id);
            MaterialUpdateBindingModel materialUpdateBindingModel = this.modelMapper.map(materialById, MaterialUpdateBindingModel.class);
            model.addAttribute("materialUpdateBindingModel", materialUpdateBindingModel);

        }
            return "material-update";

    }
    @PostMapping("/material-update/{id}")
    public String updateBusiness(@PathVariable("id") Long id,@Valid MaterialUpdateBindingModel materialUpdateBindingModel ,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("materialUpdateBindingModel", materialUpdateBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.materialUpdateBindingModel", bindingResult);

                return "redirect:/material-update/{id}";

            }

            else {
                materialUpdateBindingModel.setId(id);
                boolean isSaved = this.materialService.updateMaterial(materialUpdateBindingModel);

                if(!isSaved){

                    return  "redirect:/material-update/{id}";
                }

                return"redirect:/";
            }
    }

    @GetMapping("/delete-material/{id}")
    public String deleteById(@PathVariable Long id) {
        this.materialService.removeMaterialFromDB(id);

        return "redirect:/";

    }
        }
