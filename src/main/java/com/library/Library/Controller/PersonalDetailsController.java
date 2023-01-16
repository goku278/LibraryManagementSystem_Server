package com.library.Library.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userDetails/")
@CrossOrigin(origins = "*")
public class PersonalDetailsController {
}