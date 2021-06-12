package com.example.providerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.providerservice.dao.ItemDao;
import com.example.providerservice.model.Items;
import com.example.providerservice.model.UserItems;
import com.example.providerservice.service.ItemService;

@Controller
public class ProviderController {

	@Autowired
	private ItemService itemService;

	@Autowired
	ItemDao itemDao;
	
	@ResponseBody
	@GetMapping("/items")
	public UserItems getItem() {
		List<Items> list = itemDao.findAll();
		UserItems userItems=new UserItems();
		userItems.setUsritems(list);
		return userItems;
	}
	
	
	@PostMapping("/additem")
	@ResponseBody
	public String additem(@RequestBody Items item) {
		this.itemService.addItem(item);
		return "AddItems.............";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public void deleteProduct(@RequestParam("id") int id) {
		itemService.removeItem(id);
	}

	@RequestMapping("/itemDetails")
	public String ItemDetails() {
		return "itemDetails";
	}

	@ResponseBody
	@RequestMapping("/item")
	public Items finditem(@RequestParam("id") int id) {
		
		Items item = this.itemService.getItem(id);
	//	System.out.println(item);
		return item;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String ExceptionHandlerGeneric(Model model) {
		model.addAttribute("message", "Exception has Occured.. in provider controller ");
		System.out.println("Exception has Occured.. in provider controller ");
		return "error_page";
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NullPointerException.class)
	public String ExceptionHandlerNull(Model model) {
		model.addAttribute("message", "Null pointer Exception has Occured.. occured int provider controller ");
		return "error_page";
	}

	
}

