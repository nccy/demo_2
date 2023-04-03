package com.hnust.controller;

import com.hnust.entity.Course;
import com.hnust.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;



/**
 * @author 长夜
 * @date 2023/3/23 8:54
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping ("/main_page")
    @ResponseBody
    public ModelAndView main_page() {
        ModelAndView mav = new ModelAndView("main");
        List<Course> courses=courseService.selectAll();
        mav.addObject("courses",courses);
        return mav;
    }
    @GetMapping ("/add_page")
    public String add_page() {
        //Boolean success =courseService.insertAutoId(course);
        return "add_course";
    }
    @GetMapping("/update_page")
    @ResponseBody
    public ModelAndView update_page(Course course){
        ModelAndView mav = new ModelAndView("update_course");
        mav.addObject("course",course);
        return mav;
    }
    @PostMapping("/add_solve")
    public String add_solve(Course course){
        System.out.println("course save ==> "+ course);
        List<Course> courses=courseService.selectAll();
        Boolean success =true;
        for(Course res :courses)
        {
            if(res.getName().equals(course.getName()))
            {
                success=false;break;
            }
        }
        if(success.equals(true)) {
            courseService.insertAutoId(course);
            System.out.println("{'module':'course save success'}");
        }else{
            System.out.println("{'module':'course save fail'}");
        }
        return "redirect:/course/main_page";
    }
    @PostMapping ("/update_solve")
    public String update_solve(Course course){
        System.out.println("course update ==> "+ course);
        List<Course> courses=courseService.selectAll();
        Boolean success =true;
        for(Course res :courses)
        {
            if(!(res.getId().equals(course.getId()))&&res.getName().equals(course.getName()))
            {
                System.out.println(res.getName());
                success=false;break;
            }
        }
        if(success.equals(true)) {
            courseService.update(course);
            System.out.println("{'module':'course update success'}");
        }else{
            System.out.println("{'module':'course update fail'}");
        }
        return "redirect:/course/main_page";
    }
    @GetMapping  ("/delete_solve")
    public String delete_solve(Integer id){
        System.out.println("course delete ==> "+ id);
        Boolean success = courseService.deleteById(id);
        if(success.equals(true)) {
            System.out.println("{'module':'course delete success'}");
        }else{
            System.out.println("{'module':'course delete fail'}");
        }
        return "redirect:/course/main_page";
    }

}
