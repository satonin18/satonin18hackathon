package ipr.web.controller.rest_service;

import ipr.web.dto.input.valid.CarDto;
import ipr.web.dto.input.valid.PersonDto;
import ipr.web.dto.output.StatisticsDto;
import ipr.web.entity.Car;
import ipr.web.entity.Ent;
import ipr.web.entity.Person;
import ipr.web.repository.EntRepository;
import ipr.web.service.CarService;
import ipr.web.service.PersonService;
import ipr.web.service.StatisticsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController //todo check binding with other anotations (=analog /*@ResponseBody*/, but not @RequestBody)
@Controller("restServiceController")
public class RestServiceController {

	private final PersonService personService;
	private final CarService carService;
	private final StatisticsService statisticsService;
	private final EntRepository entRepository;
	@Autowired
	public RestServiceController(
			final PersonService personService,
			final CarService carService,
			final StatisticsService statisticsService,
			final EntRepository entRepository
	) {
		this.personService = personService;
		this.carService = carService;
		this.statisticsService = statisticsService;
		this.entRepository = entRepository;
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public ResponseEntity save_person(
			@Valid @RequestBody PersonDto dto,
			BindingResult bindingResult
	) {
		try {
			// VALIDATE DTO -----------------------------------------------------
			if (bindingResult.hasErrors()) throw new Exception();
			// MAPPING -----------------------------------------------------
			Person person = new Person();
			ModelMapper mapper = new ModelMapper();
			mapper.map(dto, person);
			// ----------------------------------------------------------------------
			personService.savePerson(person); // -> validation entity

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/car", method = RequestMethod.POST)
	public ResponseEntity save_car(
			@Valid @RequestBody CarDto dto,
			BindingResult bindingResult
	) {
		try {
			// VALIDATE DTO ----------------------------------------------------------------------
			if (bindingResult.hasErrors()) throw new Exception();
			// MAPPING todo replace on custom Mapper -----------------------------------------------
			Car car = new Car();
			car.setId(dto.getId());
			car.setHorsepower(dto.getHorsepower());

			String[] fullName = dto.getModel().split("-",2);
			car.setVendor(fullName[0]);
			car.setModel(fullName[1]);
			
			Person ownerPerson = personService.findById(dto.getOwnerId()).orElseThrow( () -> new Exception() );
			car.setPerson(ownerPerson);
			// -----------------------------------------------------------------
			carService.saveCar(car); // -> validation entity

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	//todo can return PersonWithCarsDto from mapping
	@RequestMapping(value = "/personwithcars", method = RequestMethod.GET)
	public ResponseEntity<Person> get_personwithcars(
			Long personid
	) {
		try {
	        if(personid == null) throw new Exception();

			Person person = personService.getPerson(personid);

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
			Ent ent = new Ent();
			ent.setId(1L);
			entRepository.save(ent);
			StatisticsDto statisticsDto = statisticsService.getStatisticsDto();

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