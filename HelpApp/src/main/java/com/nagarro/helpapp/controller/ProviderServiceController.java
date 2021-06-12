package com.nagarro.helpapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.helpapp.demoentity.Items;
import com.nagarro.helpapp.demoentity.ReceiverUserItems;
import com.nagarro.helpapp.demoentity.RequestedItems;
import com.nagarro.helpapp.demoentity.UserItems;

@Controller
public class ProviderServiceController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/provideritemDetails")
	public ModelAndView ItemDetails(HttpSession session) {
		if(session.getAttribute("user")==null) {
			ModelAndView mv=new ModelAndView("login");
			return mv;
		}
		else {
	//	return "provideritemDetails";
		ModelAndView mv=new ModelAndView("provideritemDetails");
		return mv;
		}
	}

	@ResponseBody
	@RequestMapping("/allRequests")
	public ModelAndView allrequests(HttpSession session) {
		if(session.getAttribute("user")==null) {
			ModelAndView mv=new ModelAndView("login");
			return mv;
		}
		else {
		
		ReceiverUserItems useritems = restTemplate.getForObject("http://localhost:8083/items", ReceiverUserItems.class);

		List<RequestedItems> list = useritems.getUsritems().stream().collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("allRequests");
		mv.addObject("products", list);
		return mv;
		}
	}

	@PostMapping("/items")
	public String addItem(Items item) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Items> entity = new HttpEntity<Items>(item, headers);
		restTemplate.exchange("http://localhost:8082/" + "additem", HttpMethod.POST, entity, String.class).getBody();
		return "provideritemDetails.html";

	}

	@RequestMapping("/delete")
	public String remove(int id) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Integer> entity = new HttpEntity<Integer>(id, headers);
		restTemplate.exchange("http://localhost:8082/delete?id=" + id, HttpMethod.GET, entity, String.class).getBody();
		return "redirect:/providerView";
	}

	@RequestMapping("/item")
	public String findItem(int id, Model model) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Integer> entity = new HttpEntity<Integer>(id, headers);
		Items item = restTemplate.exchange("http://localhost:8082/item?id=" + id, HttpMethod.GET, entity, Items.class)
				.getBody();
		System.out.println(item);
		if (item != null) {
			model.addAttribute("items", item);
			return "item";
		} else {
			model.addAttribute("message", "no Item is availabe with ItemId= " + id);
			return "noticket";
		}

	}

	@GetMapping("/items")
	public String getItem() {

		return "redirect:/providerView";

	}

	@RequestMapping("providerView")
	public ModelAndView providerLoginSuccess(HttpSession session) {
		
		if(session.getAttribute("user")==null) {
			ModelAndView mv=new ModelAndView("login");
			return mv;
		}
		else {
		UserItems useritems = restTemplate.getForObject("http://localhost:8082/items", UserItems.class);

		List<Items> list = useritems.getUsritems().stream().collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("providersuccess");
		mv.addObject("products", list);
		return mv;
		}
	}

}
