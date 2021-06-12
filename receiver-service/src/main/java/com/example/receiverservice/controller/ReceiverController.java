package com.example.receiverservice.controller;

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

import com.example.receiverservice.dao.RequestedItemDao;
import com.example.receiverservice.model.ReceiverUserItems;
import com.example.receiverservice.model.RequestedItems;
import com.example.receiverservice.service.RequestedItemService;

@Controller
public class ReceiverController {

	@Autowired
	private RequestedItemService requestedItemService;

	@Autowired
	RequestedItemDao itemDao;

	@ResponseBody
	@GetMapping("/items")
	public ReceiverUserItems getItem() {
		List<RequestedItems> list = itemDao.findAll();
		ReceiverUserItems ruserItems=new ReceiverUserItems();
		ruserItems.setUsritems(list);
		return ruserItems;

	}

	@PostMapping("/radditem")
	@ResponseBody
	public String additem(@RequestBody RequestedItems item) {

		requestedItemService.addItem(item);
		return "AddItems.............";
	}

	@RequestMapping("/delete")
	public String deleteProduct(@RequestParam("id") int id) {

		this.requestedItemService.removeItem(id);
		return "redirect:/items";

	}

	@RequestMapping("/itemDetails")
	public String ItemDetails(RequestedItems item) {
		return "itemDetails";
	}

	@RequestMapping("/item")
	public String finditem(@RequestParam("id") int id, Model model) {

		RequestedItems item = this.requestedItemService.getItem(id);
		if (item != null) {
			model.addAttribute("items", item);
			return "item";
		} else {
			model.addAttribute("message", "no Item is availabe with ItemId= " + id);
			return "noticket";
		}
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String ExceptionHandlerGeneric(Model model) {
		model.addAttribute("message", "Exception has Occured.. in receiver controller ");
		System.out.println("Exception has Occured.. in receiver controller ");
		return "error_page";
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NullPointerException.class)
	public String ExceptionHandlerNull(Model model) {
		model.addAttribute("message", "Null pointer Exception has Occured..in receiver controller ");
		System.out.println( "Null pointer Exception has Occured..in receiver controller ");
		return "error_page";
	}

}
