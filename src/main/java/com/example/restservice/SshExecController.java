package com.example.restservice;


import jdk.jfr.Registered;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SshExecController {
    private static final String template = "Hello, %s";

    @GetMapping("/api/sshexec")
    public SshExec sshExec(@RequestParam(value="host", defaultValue="some host") String host) {
        return new SshExec (String.format(template, host));
    }
}






/*
@ResponseBody
public String addFoo(@RequestParam(name = "id") String fooId, @RequestParam String name) {
        return "ID: " + fooId + " Name: " + name;
        }

 */