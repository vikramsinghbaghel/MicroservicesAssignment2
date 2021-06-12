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
public class ReceiverServiceController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/receiveritemDetails")
	public String rItemDetails() {
		return"receiveritemDetails";
	}
	
	
	@ResponseBody
	@GetMapping("allDonations")
	public ModelAndView alldonations(HttpSession session) {
		if(session.getAttribute("user")==null) {
			ModelAndView mv=new ModelAndView("login");
			return mv;
		}
		else {
		
		
		UserItems useritems = restTemplate.getForObject("http://localhost:8082/items", UserItems.class);

		List<Items> list = useritems.getUsritems().stream().collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("allDonations");
		mv.addObject("products", list);
		return mv;
		}
	}

	@PostMapping("/ritems")
	public ModelAndView addrItem( RequestedItems item, HttpSession session) {
		 
		if(session.getAttribute("user")==null) {
			ModelAndView mv=new ModelAndView("login");
			return mv;
		}
		else {
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 HttpEntity<RequestedItems> entity=new HttpEntity<RequestedItems>(item, headers);
	     restTemplate.exchange("http://localhost:8083/"+"radditem", HttpMethod.POST, entity, String.class).getBody();	     
	    // return "receiveritemDetails.html";
	     
	     ModelAndView mv=new ModelAndView("receiveritemDetails");
			return mv;
	     
		}
	
	}
	
	@RequestMapping("/rdelete")
	public String rremove(int id) {
		 HttpHeaders headers = new HttpHeaders();  
		 HttpEntity<Integer> entity=new HttpEntity<Integer>(id,headers);
	     restTemplate.exchange("http://localhost:8083/delete?id="+id, HttpMethod.GET, entity, String.class).getBody();
	     return "redirect:/receiverView";
	}
	@RequestMapping("/ritem")
	public String rfindItem(int id,Model model) {
		HttpHeaders headers = new HttpHeaders();  
		HttpEntity<Integer> entity=new HttpEntity<Integer>(id,headers);
	    Items item=restTemplate.exchange("http://localhost:8083/item?id="+id, HttpMethod.GET, entity, Items.class).getBody();
	    System.out.println(item);
		if (item != null) {
				model.addAttribute("items", item);
				return "receiveritem";
			} else {
				model.addAttribute("message", "no Item is availabe with ItemId= " + id);
				return "noticket";
			}
	     
	}
	
	@GetMapping("/ritems")
	public String rgetItem() {
		
	     return"redirect:/receiverView";
	
	}
	
	@RequestMapping("receiverView")
	public ModelAndView receiverLoginSuccess(HttpSession session)
	{ 
		if(session.getAttribute("user")==null) {
			ModelAndView mv=new ModelAndView("login");
			return mv;
		}
		else {
		
	ReceiverUserItems useritems =restTemplate.getForObject("http://localhost:8083/items", ReceiverUserItems.class);
		
		List<RequestedItems> list= useritems.getUsritems().stream().collect(Collectors.toList());
		ModelAndView mv = new ModelAndView("receiversuccess");
		mv.addObject("products", list);
		return mv;	
		}
	}

}
