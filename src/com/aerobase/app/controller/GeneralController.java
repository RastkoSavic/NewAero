package com.aerobase.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {

	@RequestMapping({"/home", "/"})
    public String home() {
        return "index";
    }
	
	/*@RequestMapping("/register-company")
	public String registerCompany() {
		return "register-company";
	}*/

    @RequestMapping("/about")
    public String about() {
        return "main/about";
    }

    /*@RequestMapping("/parts")
    public String parts() {
        return "main/parts";
    }*/

    /*@RequestMapping("/message-board")
    public String messageBoard() {
        return "main/message-board";
    }*/

    /*@RequestMapping("/user-profile")
    public String userProfile() {
        return "main/user-profile";
    }*/

    /*@RequestMapping("/company-profile")
    public String companyProfile() {
        return "main/company-profile";
    }*/

    @RequestMapping("/user")
    public String user() {
        return "main/user";
    }

    /*@RequestMapping("/new-stock")
    public String newStock() {
        return "main/new-stock";
    }*/

    @RequestMapping({"/admin"})
    public String admin() {
        return "admin/admin";
    }

    @RequestMapping("/admin-part-hub")
    public String adminPartHub() {

        return "admin/parts/admin-part-hub";
    }

    /*@RequestMapping("/admin-part-categories")
    public String adminPartCategories() {
        return "admin/parts/admin-part-categories";
    }*/

    /*@RequestMapping("/admin-parts")
    public String adminParts() {
        return "admin/parts/admin-parts";
    }*/

    /*@RequestMapping("/admin-part-conditions")
    public String adminPartConditions() {
        return "admin/parts/admin-part-conditions";
    }*/

    @RequestMapping("/admin-part-collections")
    public String adminPartCollections() {
        return "admin/parts/admin-part-collections";
    }

    /*@RequestMapping("/admin-stock-parts")
    public String adminStockParts() {
        return "admin/parts/admin-stock-parts";
    }*/

    @RequestMapping("/admin-aircraft-hub")
    public String adminAircraftHub() {
        return "admin/aircrafts/admin-aircraft-hub";
    }

    /*@RequestMapping("/admin-aircraft-type")
    public String adminAircraftType() {
        return "admin/aircrafts/admin-aircraft-type";
    }*/

    /*@RequestMapping("/admin-aircraft-model")
    public String adminAircraftModel() {
        return "admin/aircrafts/admin-aircraft-model";
    }*/

   /* @RequestMapping("/admin-aircraft-variant")
    public String adminAircraftVariant() {
        return "admin/aircrafts/admin-aircraft-variant";
    }*/

    @RequestMapping("/admin-aog")
    public String adminAOG() {
        return "admin/aircrafts/admin-aog";
    }

    @RequestMapping("/admin-locations")
    public String adminLocations() {
        return "admin/locations/admin-locations";
    }

    /*@RequestMapping("/admin-countries")
    public String adminCountries() {
        return "admin/locations/admin-countries";
    }*/

    /*@RequestMapping("/admin-cities")
    public String adminCities() {
        return "admin/locations/admin-cities";
    }*/

   /* @RequestMapping("/admin-addresses")
    public String adminAddresses() {
        return "admin/locations/admin-addresses";
    }*/

    /*@RequestMapping("/admin-airports")
    public String adminAirports() {
        return "admin/locations/admin-airports";
    }*/

    @RequestMapping("/admin-message-hub")
    public String adminMessageHub() {
        return "admin/messages/admin-message-hub";
    }

    /*@RequestMapping("/admin-message-types")
    public String adminMessageTypes() {
        return "admin/messages/admin-message-types";
    }*/

    /*@RequestMapping("/admin-messages")
    public String adminMessages() {
        return "admin/messages/admin-messages";
    }*/

    @RequestMapping("/admin-bills")
    public String adminBills() {
        return "admin/messages/admin-bills";
    }

    /*@RequestMapping("/admin-company-hub")
    public String adminCompanyHub() {
        return "admin/companies/admin-company-hub";
    }*/

    /*@RequestMapping("/admin-companies")
    public String adminCompanies() {
        return "admin/companies/admin-companies";
    }*/

    /*@RequestMapping("/admin-primary-services")
    public String adminPrimaryServies() {
        return "admin/companies/admin-primary-services";
    }*/

    /*@RequestMapping("/admin-users")
    public String adminUsers() {
        return "admin/companies/admin-users";
    }*/
}
