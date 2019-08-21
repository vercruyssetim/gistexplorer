package com.cegeka.gistexplorer.progresstracker.progress;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ProgressTrackerController {
    private static Logger LOGGER = LoggerFactory.getLogger(ProgressTrackerController.class);

    private Map<String, String> selfEvaluationMap = ImmutableMap.<String, String>builder()
            .put("1", "10-programming-fundamentals-java")
            .put("2", "20-programming-advanced-java")
            .put("3", "30-enterprise-software-development-java")
            .put("4", "40-databases-orm-java")
            .put("5", "50-web-development-web-frameworks-vaadin-7")
            .build();

    @Autowired
    private ProgressTrackerService progressTrackerService;

    @GetMapping(value = "/getProgress")
    public String getProgress(Model model) {
        model.addAttribute("progressList", progressTrackerService.trackProgressOfForks());
        model.addAttribute("title", "summer homework");
        return "progress";
    }

    @GetMapping(value = "/selfEvaluation")
    public String getSelfEvaluation2(Model model, @RequestParam("number") String number) {
        model.addAttribute("progressList", progressTrackerService.getProgressOfSelfEvaluation(selfEvaluationMap.get(number)));
        model.addAttribute("title", "self evaluation " + number);
        return "progress";
    }

}
