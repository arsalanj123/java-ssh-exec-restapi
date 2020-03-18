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
    public SshExec getSshDetail(@RequestParam(value="hostname", defaultValue="some host") String hostName, @RequestParam(value="username", defaultValue="some user") String userName, @RequestParam(value="password", defaultValue="some password") String password, @RequestParam(value="sshport", defaultValue="22") String sshPort, @RequestParam(value="command", defaultValue="sh run") String command, @RequestParam(value="stringtofind", defaultValue="find someething") String stringToFind, @RequestParam(value="ifstringfound", defaultValue="found") String ifStringFound, @RequestParam(value="ifstringnotfound", defaultValue="not found") String ifStringNotFound)
    {
        SshExec objSshExec = new SshExec("sbx-nxos-mgmt.cisco.com", "admin", "Admin_1234!", "8181", "show running-config eigrp", "authentication mode md5", "EIGRP Authenctication is Secure - Using MD5 Authentication", "EIGRP Authentication is Insecure - Recommended to use MD5 Authentication");

        //objSshExec.executeOnDevice(hostName, userName);

        return new SshExec (String.format(objSshExec.executeOnDevice(hostName, userName, password, sshPort, command, stringToFind, ifStringFound, ifStringNotFound)), userName, password, sshPort, command, stringToFind, ifStringFound, ifStringNotFound);
    }
}
//    String hostName,
//    String userName,
//    String password,
//    Integer sshPort,
//    String command,
//    String stringToFind,
//    String ifStringFound,
//    String ifStringNotFound
//
// Add command to run on ClI of the device
//        command = "show running-config eigrp";
//                // Add the hostname or host IP
//                hostName = "sbx-nxos-mgmt.cisco.com";
//                // Add the port number
//                sshPort = 8181;
//                // Add the username to login with
//                userName= "admin";
//                // Add the password to login with
//                password = "Admin_1234!";
//                // Add the substring to find in the resulting output of the command
//                stringToFind = "authentication mode md5";
//                // Add the output required to be displayed if the substring is found
//                ifStringFound = "EIGRP Authenctication is Secure - Using MD5 Authentication";
//                // Add the output required to be displayed if the substring is NOT found
//                ifStringNotFound = "EIGRP Authentication is Insecure - Recommended to use MD5 Authentication";
//                //Set true or false if you want to see command execution output display
//                showCmdOutput = true;



/*
@ResponseBody
public String addFoo(@RequestParam(name = "id") String fooId, @RequestParam String name) {
        return "ID: " + fooId + " Name: " + name;
        }

 */