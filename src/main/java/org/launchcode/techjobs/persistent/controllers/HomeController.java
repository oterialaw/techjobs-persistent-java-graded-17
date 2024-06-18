package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobRepository jobRepository;


    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {
//        System.out.println("Employer ID should be: " + employerId);
//        System.out.println("Skills should be: " + skills);

        if (employerRepository.findById(employerId).isPresent()) {
//            System.out.println("Should set the employer ID to the newJob Object");
            newJob.setEmployer(employerRepository.findById(employerId).get());
        }

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
//        System.out.println("skillObjs should be something..." + skillObjs);
//        if(!skillObjs.isEmpty()) {
//            System.out.println("skill objects should be " + skillObjs);
            newJob.setSkills(skillObjs);
//            System.out.println("Updated newJob: " + newJob.getSkills());
//        }
//        System.out.println("newJob should be updated..." + newJob.getEmployer());

        if (errors.hasErrors()) {
//            System.out.println("There is an error...");
//            System.out.println(errors);
            return "add";
        }
        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}
