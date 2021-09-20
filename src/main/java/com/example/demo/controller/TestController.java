package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.service.UserService;

@Controller
public class TestController {
	/** @Service/@Repository/@Componetを付与したクラスを使用可 */
	@Autowired
	UserService service;

	/** @RequestMappingでも同じ。 */
	@GetMapping("/test")
//	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String getIndex()  {
		return "/index";
	}

	/** Stringやintなどの型で受け取る場合 */
	@PostMapping("/user")
//	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String postIndex(@RequestParam("id")int id, Model model) {
		try {
			String name =  service.getUser(id);
			model.addAttribute("name", name);
			return "/response";
		} catch(Exception e) {
			// DBエラーが発生したら、エラーがめんへ。
			e.printStackTrace();
			return "/error";
		}
	}

	/** オブジェクトで受け取る場合 */
//	@RequestMapping(value="/user", method=RequestMethod.POST)
//	public String postIndex(@ModelAttribute("form")User form, Model model) {
//
//		String name =  service.getUser(form.getId());
//		form.setName(name);
//		model.addAttribute("form", form);
//		return "/response";
//	}

	/** リダイレクトで返却する */
	@RequestMapping(value="/back", method=RequestMethod.POST)
	public String backBtn() {
		return "redirect:/test";
	}
}
