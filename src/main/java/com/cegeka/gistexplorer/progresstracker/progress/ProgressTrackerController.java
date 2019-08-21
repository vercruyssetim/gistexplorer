package com.cegeka.gistexplorer.progresstracker.progress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProgressTrackerController {
    private static Logger LOGGER = LoggerFactory.getLogger(ProgressTrackerController.class);

    @Autowired
    private ProgressTrackerService progressTrackerService;

    @GetMapping(value = "/getProgress")
    public String getProgress(Model model) {
        model.addAttribute("progressList", progressTrackerService.trackProgressOfForks());
        model.addAttribute("title", "summer homework");
        return "progress";
    }

    @GetMapping(value = "/selfEvaluation2")
    public String getSelfEvaluation2(Model model) {
        model.addAttribute("progressList", progressTrackerService.getProgressOfSelfEvaluation("20-programming-advanced-java"));
        model.addAttribute("title", "self evaluation 2");
        return "progress";
    }

}
