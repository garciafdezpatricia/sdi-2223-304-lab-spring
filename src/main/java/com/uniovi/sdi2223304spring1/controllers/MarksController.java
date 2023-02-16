package com.uniovi.sdi2223304spring1.controllers;

import com.uniovi.sdi2223304spring1.entities.Mark;
import com.uniovi.sdi2223304spring1.services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarksController {

    @Autowired
    private MarksService marksService;

    @RequestMapping("/mark/list")
    public String getList() {
        return marksService.getMarks().toString();
    }
    @RequestMapping("/mark/details/{id}")
    public String getDetail(@PathVariable Long id) {
        return marksService.getMark(id).toString();
    }

    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(@ModelAttribute Mark mark) {
        marksService.addMark(mark);
        return "Ok";
    }

    @RequestMapping(value="/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id){
        marksService.deleteMark(id);
        return "Ok";
    }

}
