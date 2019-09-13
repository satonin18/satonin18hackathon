package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller.rest_service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.model.Car;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.model.Person;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.model.PersonWithCars;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.model.Statistics;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

//TODO ALL METHODS DECORATE TRY-CATH

@RestController //todo check binding with other anotations (=analog /*@ResponseBody*/, but not @RequestBody)
@Controller("restServiceController")
public class RestServiceController {

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public String save_person(
			@Valid @RequestBody Person person,
            BindingResult bindingResult
	) {
        if (bindingResult.hasErrors()) {
            return "";// todo пусто ??? //status 400 Bad Request - ошибки валидации
        }
        //todo servicePerson.save(person);
		return ""; // todo пусто ??? //status 200
	}

	@RequestMapping(value = "/car", method = RequestMethod.POST)
	public String save_car(
            @Valid @RequestBody Car car,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "";// todo пусто ??? //status 400 Bad Request - ошибки валидации
        }
        //todo serviceCar.save(car);
        return ""; // todo пусто ??? //status 200
	}

	// todo in TZ no ~40* no true param. need Obj with validator and submint only 400
	@RequestMapping(value = "/personwithcars", method = RequestMethod.GET)
	public PersonWithCars get_personwithcars(
	        long personid //todo Long (not null)
            //can be @Valid Long personid,
            // BindingResult bindingResult
    ) {
//        if (bindingResult.hasErrors()) {
//            return "";// todo пусто ??? //status 400 Bad Request - ошибки валидации
//        }
        //todo PersonWithCars personWithCars = servicePerson.find(personid);
        //todo if(personWithCars != null) ... else //status 400 Bad Request - ошибки валидации
        return null; // todo personWithCars //status 200
	}

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public Statistics statistics() {
	    //todo Statistics statistics = new Statistic = ...
		return null; // todo statistics //status 200
	}

	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public String clear() {
	    //boolean succses = serviceAll.removeAll; //ранее сохраненые
        //always return //status 200
		return ""; // todo пусто ??? //status 200
	}

}