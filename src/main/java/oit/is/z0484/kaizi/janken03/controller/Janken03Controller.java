package oit.is.z0484.kaizi.janken03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z0484.kaizi.janken03.model.Janken;

@Controller
public class Janken03Controller {

  @GetMapping("/janken03")
  public String janken03() {
    System.out.println("GET");
    return "janken.html";
  }

  @PostMapping("/janken03")
  public String janken(@RequestParam String name, ModelMap model) {
    System.out.println("POST");
    model.addAttribute("name", name);
    return "janken.html";
  }

  @GetMapping("/hand")
  public String userHand(@RequestParam String user_hand, ModelMap model) {
    String cpu_hand = Janken.cpuHand();
    String result = Janken.judge(user_hand, cpu_hand);
    model.addAttribute("hand1", user_hand);
    model.addAttribute("hand2", cpu_hand);
    model.addAttribute("result", result);
    return "janken.html";
  }

}
