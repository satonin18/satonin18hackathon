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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//@SuppressWarnings("ALL")
@RestController //todo check binding with other anotations (=analog /*@ResponseBody*/, but not @RequestBody)
@Controller("restServiceController")
public class RestServiceController {

	@Autowired
	private PersonService personService;
	@Autowired
	private CarService carService;

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public ResponseEntity save_person(
			@Valid @RequestBody PersonDto4save dto,
			BindingResult bindingResult
	) {
		try {
			//------------------------------------
			if (bindingResult.hasErrors()) throw new Exception();
			if ( personService.existsById(dto.getId()) ) throw new Exception();
			//todo replace
			SimpleDateFormat format = new SimpleDateFormat(PropertiesApp.DATA_FORMAT_BIRTHDATE);
			java.util.Date utilData = format.parse(dto.getBirthdate()); // throw new Exception();
			java.sql.Date sqlDataBirthdate = new java.sql.Date(utilData.getTime());
			LocalDate birthday = sqlDataBirthdate.toLocalDate();
			long days = LocalDate.from(birthday).until(LocalDate.now(), ChronoUnit.DAYS);
			if(days < 0) throw new Exception();
			//------------------------------------
			Person person = new Person();
			person.setId(dto.getId());
			person.setName(dto.getName());
			person.setBirthdate(sqlDataBirthdate);

			personService.save(person);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/car", method = RequestMethod.POST)
	public ResponseEntity save_car(
			@Valid @RequestBody CarDto4save dto,
			BindingResult bindingResult
	) {
		try {
			//------------------------------------
			if (bindingResult.hasErrors()) throw new Exception();
			if ( carService.existsById(dto.getId()) ) throw new Exception();
			if(dto.getHorsepower() <= 0) throw new Exception();
			Person ownerPerson = personService.findById(dto.getOwnerId()).orElseThrow( () -> new Exception() );
			LocalDate birthday = ownerPerson.getBirthdate().toLocalDate();
			long age = LocalDate.from(birthday).until(LocalDate.now(), ChronoUnit.YEARS);
			if(age < 18) throw new Exception();

			String[] mas = dto.getModel().split("-",2);
			if(mas[0].length() > 50 || mas[1].length() > 50) throw new Exception();
			//------------------------------------

			Car car = new Car();
			car.setId(dto.getId());
			car.setHorsepower(dto.getHorsepower());
			car.setVendor(mas[0]);
			car.setModel(mas[1]);

//			car.setPerson(ownerPerson);
			car.setOwnerId(ownerPerson.getId());

			carService.save(car);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/personwithcars", method = RequestMethod.GET)
	public ResponseEntity<Person> get_personwithcars(
			Long personid //todo Long (not null)
	) {
		try {
	        if (personid == null) throw new Exception();

			if ( ! personService.existsById(personid) ) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			Person person = personService.findById(personid).get();
			if(person == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<Person>(
					person,
					HttpStatus.OK
			);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public ResponseEntity<StatisticsDto> statistics() {
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

			return new ResponseEntity<StatisticsDto>(
					statisticsDto,
					HttpStatus.OK
			);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.OK);
		}
	}

	//todo no rest, GET-reguest = edit state
	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public ResponseEntity clear() {
		try {
			carService.deleteAll();
			personService.deleteAll();
		} finally {
			return new ResponseEntity(HttpStatus.OK);
		}
	}

}