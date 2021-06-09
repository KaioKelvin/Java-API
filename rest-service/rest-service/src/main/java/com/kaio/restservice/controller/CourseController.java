package com.kaio.restservice.controller;

import com.kaio.restservice.model.Course;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
public class CourseController {

    private int counter = 1;
    private Map<Integer, Course> courses;
    private Map<Integer, String> items;
    private LocalDate date = LocalDate.now();

    public CourseController() {

        courses = new HashMap<Integer, Course>();
    }

    //Mapping home
    @GetMapping("/")
    public ResponseEntity<Object> home() {
        items = new HashMap<Integer, String>();
        items.put(0, "Endpoints disponíveis: ");
        items.put(1, "/");
        items.put(2, "/course   with GET, POST, PUT");
        items.put(3, "/course/{id}  with GET, DELETE");

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    //Mapping all courses
    @GetMapping("/course")
    public ResponseEntity<List<Course>> List() {

        final List<Course> result = new ArrayList<>(courses.values());

        if (result.isEmpty()) {
            System.out.println("No courses registered!");
            return new ResponseEntity("No courses registered!", HttpStatus.NOT_FOUND);
        }
        System.out.println("Courses found!");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //Mapping course with id
    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    public ResponseEntity<Course> Find(@PathVariable("id") Integer id) {

        HttpHeaders responseHeaders = new HttpHeaders();
        Course course = courses.get(id);

    if (course == null) {
        responseHeaders.set("Return message", "Course not found!");
        return new ResponseEntity("Course not found!", responseHeaders, HttpStatus.NOT_FOUND);
    }
        System.out.println("Course found -> " + course.getName());
        responseHeaders.set("Return message", "Course successfully found!");
        return new ResponseEntity<Course>(course, responseHeaders, HttpStatus.OK);
    }

    //Adding new courses with POST method
    @RequestMapping(value = "/course", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> postCourse(@RequestBody Course course){

        HttpHeaders responseHeaders = new HttpHeaders();
        Course newCourse = new Course(counter, course.getName(), course.getArea(), course.getPeriod(), course.getDuration(), course.getPrice(), date);
        courses.put(counter, newCourse);
        counter++;

        if (newCourse.getName().isEmpty()) {
            responseHeaders.set("Return message", "Course name not found");
            return new ResponseEntity("Course name not found!", responseHeaders, HttpStatus.NOT_FOUND);
        }

        System.out.println("Course successfully created -> " + course.getName());
        responseHeaders.set("Return message", "Course successfully created!");
        return new ResponseEntity<Course>(newCourse, responseHeaders, HttpStatus.OK);
    }

    //Modifying a course with PUT method
    @RequestMapping(value = "/course/" , method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> putCourse(@RequestBody Course course) {

        HttpHeaders responseHeaders = new HttpHeaders();
        Course ModifiedCourse = new Course(course.getId(), course.getName(), course.getArea(), course.getPeriod(), course.getDuration(), course.getPrice(), date);
        Course id = courses.get(course.getId());

        if (id == null) {
            responseHeaders.set("Return message", "Course not found");
            return new ResponseEntity("Course not found",responseHeaders, HttpStatus.NOT_FOUND);
        }

        courses.put(course.getId(), ModifiedCourse);
        System.out.println("Course successfully modified -> " + course.getName());
        responseHeaders.set("Return message", "Course successfully modified!");
        return new ResponseEntity<Course>(ModifiedCourse, responseHeaders, HttpStatus.OK);
    }

    //Mapping a course with Delete method
    @RequestMapping(value = "/course/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Course>DeleteCourse(@PathVariable Integer id) {
        HttpHeaders responseHeaders = new HttpHeaders();
        Course course = courses.remove(id);

        if (course == null) {
            responseHeaders.set("Return message", "ID not found");
            return new ResponseEntity("Id not found", responseHeaders, HttpStatus.NOT_FOUND);
        }

        System.out.println("Course successfully deleted ->  " + course.getName());
        responseHeaders.set("Return message", "Course successfully deleted");
        return new ResponseEntity<>(course, responseHeaders, HttpStatus.OK);
    }
}
