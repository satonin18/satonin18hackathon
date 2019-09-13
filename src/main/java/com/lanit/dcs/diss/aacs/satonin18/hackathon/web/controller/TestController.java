package com.lanit.dcs.diss.aacs.satonin18.hackathon.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//todo remove name and good check
@Controller("testController")
public class TestController {

	@RequestMapping(value = "/test/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "index";
	}

}