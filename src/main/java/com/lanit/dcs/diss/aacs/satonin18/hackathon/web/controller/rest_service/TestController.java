package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller.rest_service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Car;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Person;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.CarService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

//todo remove name and good check
@RestController //todo check binding with other anotations (=analog /*@ResponseBody*/, but not @RequestBody)
@Controller("testController")
public class TestController {

//	@RequestMapping(value = "/test/", method = RequestMethod.GET)
//	public String printWelcome(ModelMap model) {
//		return "index";
//	}
//
//	@RequestMapping(value = "/test2/", method = RequestMethod.GET)
//	public String printWelcome2(ModelMap model) {
//		return "index2";
//	}

	@Autowired
	private PersonService personService;
	@Autowired
	private CarService carService;

	@RequestMapping(value = "/get_person", method = RequestMethod.GET)
	public Person get_person(
			HttpServletResponse response
	) {
		try {
			Person person = personService.findById(1L).orElseThrow(() -> new Exception());

			response.setStatus(HttpStatus.OK.value());
			return person;
		} catch (Exception e){
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
	}

	@RequestMapping(value = "/get_car", method = RequestMethod.GET)
	public Car get_car(
			HttpServletResponse response
	) {
		try {
			Car car= carService.findById(1L).orElseThrow(() -> new Exception());

			response.setStatus(HttpStatus.OK.value());
			return car;
		} catch (Exception e){
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
	}
}