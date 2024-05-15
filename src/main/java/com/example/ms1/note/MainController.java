package com.example.ms1.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @RequestMapping("/")
    public String main(Model model, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "false") String isSearchModal) {
        MainDataDto mainDataDto = mainService.getDefaultMainData(keyword);
        model.addAttribute("mainDataDto", mainDataDto);
        model.addAttribute("isSearchModal", isSearchModal);
        model.addAttribute("keyword", keyword);

        return "main";
    }

}
