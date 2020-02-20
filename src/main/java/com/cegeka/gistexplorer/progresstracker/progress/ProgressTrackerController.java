package com.cegeka.gistexplorer.progresstracker.progress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProgressTrackerController {

    @Autowired
    private ProgressTrackerService progressTrackerService;

    @GetMapping(value = "/getProgress")
    public String getProgress(Model model) {
        model.addAttribute("progressList", progressTrackerService.getProgressOfGists());
        model.addAttribute("title", "summer homework");
        return "progress";
    }

    @GetMapping(value = "/selfEvaluation")
    public String getSelfEvaluation2(Model model, @RequestParam("number") String number) {
        model.addAttribute("progressList", progressTrackerService.getProgressOfSelfEvaluation(number));
        model.addAttribute("title", "self evaluation " + number);
        return "progress";
    }

}
