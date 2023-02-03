package com.green.nowon.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BusController {
	
	@Autowired
	private BusService busService;
	
	//그냥 페이지 이동
	//@ResponseBody
	@GetMapping("/bus")
	public ModelAndView bus() {
		return new ModelAndView("bus/bus-index");
	}
	
	//ajax 요청
	@GetMapping("/bus/search")
	public ModelAndView busSearch(String strSrch, ModelAndView mv) {
		mv.setViewName("bus/list");
		busService.getBusPath(strSrch, mv);
		return mv;
	}
}
