package com.cegeka.gistexplorer.progresstracker.rest;

import com.cegeka.gistexplorer.progresstracker.service.ProgressTrackerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProgressTrackerController {
    private static Logger LOGGER = LoggerFactory.getLogger(ProgressTrackerController.class);

    @Autowired
    private ProgressTrackerService progressTrackerService;

    @GetMapping(value = "/getProgress")
    public String getProgress(Model model) {
        LOGGER.info("getProgress called");
        model.addAttribute("progressList", progressTrackerService.trackProgressOfForks());
        return "progress";
    }
}
