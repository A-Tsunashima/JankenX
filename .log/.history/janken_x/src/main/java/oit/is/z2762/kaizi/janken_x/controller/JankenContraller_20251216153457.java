package oit.is.z2762.kaizi.janken_x.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JankenContraller {

  @PostMapping("/janken")
  public String janken(@RequestParam String name)
}
