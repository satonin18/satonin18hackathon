package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller.rest_service;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.valid.CarDto4save;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.valid.PersonDto4save;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Car;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.entity.Person;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.dto.StatisticsDto;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.CarService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.service.PersonService;
import com.lanit.dcs.diss.aacs.satonin18.hackathon.web.helper.PropertiesApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;

//@SuppressWarnings("ALL")
@RestController //todo check binding with other anotations (=analog /*@ResponseBody*/, but not @RequestBody)
@Controller("restServiceController")
public class RestServiceController {

	@Autowired
	private PersonService personService;
	@Autowired
	private CarService carService;

	//todo can be has 3 controllers: Car, Person, Util=Static remove)

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public void save_person(
			HttpServletResponse response,
			@Valid @RequestBody PersonDto4save dto,
			BindingResult bindingResult
	) {
		try {
			if (bindingResult.hasErrors()) throw new Exception();

			Person person = new Person();
			person.setId(dto.getId());
			person.setName(dto.getName());

			//todo replace
			SimpleDateFormat format = new SimpleDateFormat(PropertiesApp.DATA_FORMAT_BIRTHDATE);
			java.util.Date utilData = format.parse(dto.getBirthdate());
			java.sql.Date sqlData = new java.sql.Date(utilData.getTime());

			person.setBirthdate(sqlData);

			personService.save(person);
			response.setStatus(HttpStatus.OK.value());
		} catch (Exception e){
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
	}

	@RequestMapping(value = "/car", method = RequestMethod.POST)
	public void save_car(
			HttpServletResponse response,
			@Valid @RequestBody CarDto4save dto,
			BindingResult bindingResult
	) {
		try {
			if (bindingResult.hasErrors()) throw new Exception();

			Car car = new Car();
			car.setId(dto.getId());
			car.setHorsepower(dto.getHorsepower());
			String[] mas = dto.getModel().split("-");
			car.setVendor(mas[0]);
			car.setModel(mas[1]);

			Person ownerPerson = personService.findById(dto.getOwnerId()).orElseThrow( () -> new Exception() );
//			car.setPerson(ownerPerson);
			car.setOwnerId(ownerPerson.getId());

			carService.save(car);
			response.setStatus(HttpStatus.OK.value());
		} catch (Exception e){
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
	}

	@RequestMapping(value = "/personwithcars", method = RequestMethod.GET)
	public Person get_personwithcars(
			HttpServletResponse response,
			Long personid //todo Long (not null)
//			can be @Valid Long personid,
//			 BindingResult bindingResult
	) {
		try {
//	        if (bindingResult.hasErrors()) {
//	            return "";// todo пусто ??? //status 400 Bad Request - ошибки валидации
//	        }
			Person person = personService.findById(personid).orElseThrow(() -> new Exception());
//			Person person = personService.findById(personid).get();
//			if(person == null) throw new Exception(); //status 400 Bad Request - ошибки валидации

			response.setStatus(HttpStatus.OK.value());
			return person;
		} catch (Exception e){
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
	}

//	for test
//	@ResponseStatus(HttpStatus.OK)//always MUST return = status 200
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public StatisticsDto statistics(HttpServletResponse response) {
		try {
			Long personcount = personService.count();
			Long carcount = carService.count();
			Long uniquevendorcount = carService.countDistinctVendor();

			if(personcount == null || carcount == null || uniquevendorcount == null)
				throw new Exception();

			StatisticsDto statisticsDto = new StatisticsDto();
			statisticsDto.setPersoncount(personcount);
			statisticsDto.setCarcount(carcount);
			statisticsDto.setUniquevendorcount(uniquevendorcount);

			//for test
			response.setStatus(HttpStatus.OK.value());

			return statisticsDto;
		} catch (Exception e) {
			//for test
			response.setStatus(HttpStatus.BAD_REQUEST.value());

			return null; // "Exception (BUT always MUST return = status 200)"
		}
	}

	//todo no rest becouse editable GET-reguest
//	for test
//	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public void clear(HttpServletResponse response) {
		try {
			carService.deleteAll();
			personService.deleteAll();

			//for test
			response.setStatus(HttpStatus.OK.value());
		}
		catch (Exception ignor) {
			/*NOP*/

			//for test
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
	}

}