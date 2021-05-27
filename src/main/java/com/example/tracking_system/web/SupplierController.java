package com.example.tracking_system.web;

import com.example.tracking_system.model.binding.SearchSupplierBindingModel;
import com.example.tracking_system.model.binding.SupplierBingingModel;
import com.example.tracking_system.model.binding.SupplierUpdateBindingModel;
import com.example.tracking_system.model.entity.Supplier;
import com.example.tracking_system.model.entity.service.SupplierServiceModel;
import com.example.tracking_system.service.CityService;
import com.example.tracking_system.service.CountryService;
import com.example.tracking_system.service.MaterialService;
import com.example.tracking_system.service.SupplierService;
import com.example.tracking_system.view.SupplierViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller
public class    SupplierController {

    private final SupplierService supplierService;
    private final MaterialService materialService;
    private final CityService  cityService;
    private final CountryService countryService;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierController(SupplierService supplierService, MaterialService materialService, CityService cityService, CountryService countryService, ModelMapper modelMapper) {
        this.supplierService = supplierService;
        this.materialService = materialService;
        this.cityService = cityService;
        this.countryService = countryService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("supplierModel")
    public SupplierBingingModel userModel() {
        return new SupplierBingingModel();
    }

    @GetMapping("/supplier-add")
    public String showHome(Model model){
        if(!model.containsAttribute("supplierBingingModel")) {
            model.addAttribute("supplierBingingModel", new SupplierBingingModel());
            model.addAttribute("supplierNameExists", false);
            model.addAttribute("vatExists", false);
            model.addAttribute("uidExists", false);
            model.addAttribute("ibanExists", false);
            model.addAttribute("codeExists", false);
            model.addAttribute("cities", this.cityService.getCities());
            model.addAttribute("countries", this.countryService.getCountries());
        }

        return "supplier-add";
    }

    @PostMapping("/supplier-add")
    public String addSupplier(@Valid SupplierBingingModel supplierBingingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || this.supplierService.supplierExists(supplierBingingModel.getSupplierName())|| this.supplierService.ibanExists(supplierBingingModel.getIban())
        || this.supplierService.uidExists(supplierBingingModel.getUid())||this.supplierService.vatExists(supplierBingingModel.getVat())
        || this.supplierService.codeExists(supplierBingingModel.getCode())) {
            redirectAttributes.addFlashAttribute("supplierBingingModel", supplierBingingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.supplierBingingModel", bindingResult);
            if( this.supplierService.supplierExists(supplierBingingModel.getSupplierName())){
                    redirectAttributes.addFlashAttribute("supplierNameExists",true);
            }
            if(this.supplierService.ibanExists(supplierBingingModel.getIban())){
                redirectAttributes.addFlashAttribute("ibanExists",true);
            }
            if(this.supplierService.uidExists(supplierBingingModel.getUid())){
                redirectAttributes.addFlashAttribute("uidExists",true);
            }
                if(this.supplierService.codeExists(supplierBingingModel.getCode())){
                    redirectAttributes.addFlashAttribute("codeExists",true);
            }
            if(this.supplierService.vatExists(supplierBingingModel.getVat())){
                redirectAttributes.addFlashAttribute("vatExists",true);
            }

            return "redirect:/supplier-add";
        }

        boolean isSaved = this.supplierService.saveSupplier(this.modelMapper.map(supplierBingingModel, SupplierServiceModel.class));

        if(!isSaved){
            redirectAttributes.addFlashAttribute("supplierBingingModel", supplierBingingModel);
            redirectAttributes.addFlashAttribute("isExists", true);
            return "supplier-add";
        }

        return"redirect:/";
    }


    @GetMapping("/supplier-update/{id}")
    public String showUpdateSupplierProfile(@PathVariable Long id, Model model) {

        Supplier supplierServiceById = this.supplierService.findById(id);
        SupplierUpdateBindingModel supplierUpdateBindingModel = this.modelMapper.map(supplierServiceById, SupplierUpdateBindingModel.class);
        if(!model.containsAttribute("supplierUpdateBindingModel")) {
            model.addAttribute("supplierUpdateBindingModel", supplierUpdateBindingModel);
            model.addAttribute("vatExists", false);
            model.addAttribute("uidExists", false);
            model.addAttribute("ibanExists", false);
            model.addAttribute("codeExists", false);
            model.addAttribute("supplierNameExists", false);
            model.addAttribute("cities", this.cityService.getCities());
            model.addAttribute("countries", this.countryService.getCountries());
        }
        return "supplier-update";

    }


    @PostMapping("/supplier-update/{id}")
    public String updateSupplierProfile(@PathVariable("id") Long id,@Valid SupplierUpdateBindingModel supplierUpdateBindingModel ,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || this.supplierService.cantUpdateName(supplierUpdateBindingModel.getSupplierName(), supplierUpdateBindingModel.getId())|| this.supplierService.cantUpdateIban(supplierUpdateBindingModel.getIban(),supplierUpdateBindingModel.getId())
                || this.supplierService.cantUpdateUid(supplierUpdateBindingModel.getUid(), supplierUpdateBindingModel.getId())||this.supplierService.cantUpdateVat(supplierUpdateBindingModel.getVat(),supplierUpdateBindingModel.getId())
                || this.supplierService.cantUpdateCode(supplierUpdateBindingModel.getCode(),supplierUpdateBindingModel.getId())) {
            redirectAttributes.addFlashAttribute("supplierUpdateBindingModel", supplierUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.supplierUpdateBindingModel", bindingResult);
            if( this.supplierService.cantUpdateName(supplierUpdateBindingModel.getSupplierName(), supplierUpdateBindingModel.getId())){
                redirectAttributes.addFlashAttribute("supplierNameExists",true);
            }
            if(this.supplierService.cantUpdateIban(supplierUpdateBindingModel.getIban(),supplierUpdateBindingModel.getId())){
                redirectAttributes.addFlashAttribute("ibanExists",true);
            }
            if(this.supplierService.cantUpdateUid(supplierUpdateBindingModel.getUid(),supplierUpdateBindingModel.getId())){
                redirectAttributes.addFlashAttribute("uidExists",true);
            }
            if(this.supplierService.cantUpdateCode(supplierUpdateBindingModel.getCode(),supplierUpdateBindingModel.getId())){
                redirectAttributes.addFlashAttribute("codeExists",true);
            }
            if(this.supplierService.cantUpdateVat(supplierUpdateBindingModel.getVat(),supplierUpdateBindingModel.getId())){
                redirectAttributes.addFlashAttribute("vatExists",true);
            }

            return "redirect:/supplier-update/{id}";
        }

        else {
            supplierUpdateBindingModel.setId(id);
            boolean isSaved = this.supplierService.updateSupplier(supplierUpdateBindingModel);

            if(!isSaved){
                redirectAttributes.addFlashAttribute("supplierUpdateBindingModel", supplierUpdateBindingModel);

                return "redirect:/supplier-update/{id}";
            }

        }
        return "redirect:/";

    }

    @GetMapping("/delete-supplier/{id}")
    public String deleteById(@PathVariable Long id) {
             this.materialService.findIfSupplierHasMaterialsInDBAndDelete(id);
            this.supplierService.removeSupplierFromDB(id);

        return "redirect:/";

    }

    @GetMapping("/supplier-profile/{id}")
    public String showProfile(@PathVariable Long id, Model model) {
        Supplier supplier = this.supplierService.findById(id);
        SupplierViewModel supplierViewModel = this.modelMapper.map(supplier, SupplierViewModel.class);
        supplierViewModel.setCountry(supplier.getCountry().getName());
        supplierViewModel.setCity(supplier.getCity().getName());
        model.addAttribute("supplier",supplierViewModel);

        return "supplier-profile";

    }


    @GetMapping("/supplier-find")
    public String showAllSuppliers(Model model){
        if((!model.containsAttribute("suppliers")) && (!model.containsAttribute("supplier"))) {
                    model.addAttribute("suppliers", this.supplierService.findAll());
                    model.addAttribute("supplier", new SearchSupplierBindingModel());
                    model.addAttribute("noSupplier", false);

        }


        return "supplier-find";
    }

    @PostMapping("/supplier-find")
    public String findSupplier(@Valid SearchSupplierBindingModel searchSupplierBindingModel, RedirectAttributes redirectAttributes){
        Long id = this.supplierService.findSupplierId(searchSupplierBindingModel.getSupplierName());
        if(id != null){
            return "redirect:/supplier-profile/" + id;
        }
        else {
            redirectAttributes.addFlashAttribute("supplier", new SearchSupplierBindingModel());
            redirectAttributes.addFlashAttribute("noSupplier", true);
            redirectAttributes.addFlashAttribute("suppliers", this.supplierService.findAll());
            return "redirect:/supplier-find";
        }
    }
}

