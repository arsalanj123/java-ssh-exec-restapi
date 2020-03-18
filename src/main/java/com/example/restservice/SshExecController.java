package com.example.restservice;


import jdk.jfr.Registered;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SshExecController {
    private static final String hostnameTemplate = "This is host: %s";
    private static final String usernameTemplate = "This is user: %s";

    @GetMapping("/api/sshexec")
    public SshExec sshExec(@RequestParam(value="hostname", defaultValue="some host") String hostname, @RequestParam(value="username", defaultValue="some user") String username) {
        return new SshExec (String.format(hostnameTemplate, hostname), String.format(usernameTemplate, username));
    }
}






/*
@ResponseBody
public String addFoo(@RequestParam(name = "id") String fooId, @RequestParam String name) {
        return "ID: " + fooId + " Name: " + name;
        }

 */