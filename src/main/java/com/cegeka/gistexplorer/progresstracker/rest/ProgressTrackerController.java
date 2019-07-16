package com.cegeka.gistexplorer.progresstracker.rest;

import com.cegeka.gistexplorer.progresstracker.service.ProgressTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProgressTrackerController {

    @Autowired
    private ProgressTrackerService progressTrackerService;

    @GetMapping(value = "/getProgress")
    public String getProgress(Model model) {
        model.addAttribute("progressList", progressTrackerService.trackProgressOfForks());
        return "progress";
    }
}
