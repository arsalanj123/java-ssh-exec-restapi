package com.example.restservice;

import jdk.jfr.Registered;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController


public class SshExecController {

    @GetMapping("/api/sshexec")
    public String getSshDetail(@RequestParam(value="hostname", defaultValue="some host") String hostName, @RequestParam(value="username", defaultValue="some user") String userName, @RequestParam(value="password", defaultValue="some password") String password, @RequestParam(value="sshport", defaultValue="22") String sshPort, @RequestParam(value="command", defaultValue="sh run") String command, @RequestParam(value="stringtofind", defaultValue="find someething") String stringToFind, @RequestParam(value="ifstringfound", defaultValue="found") String ifStringFound, @RequestParam(value="ifstringnotfound", defaultValue="not found") String ifStringNotFound)
    {
        SshExec objSshExec = new SshExec("sbx-nxos-mgmt.cisco.com", "admin", "Admin_1234!", "8181", "show running-config eigrp", "authentication mode md5", "EIGRP Authenctication is Secure - Using MD5 Authentication", "EIGRP Authentication is Insecure - Recommended to use MD5 Authentication");

        String apiResponse;

        apiResponse = objSshExec.executeOnDevice(hostName, userName, password, sshPort, command, stringToFind, ifStringFound, ifStringNotFound);

        String[] response = {hostName, userName, password, sshPort, command, stringToFind, ifStringFound, ifStringNotFound, apiResponse};

        for (int i = 0; i < response.length; i++){
        System.out.println(response[i]);
    }

        return apiResponse;

//        return new SshExec ((objSshExec.executeOnDevice(hostName, userName, password, sshPort, command, stringToFind, ifStringFound, ifStringNotFound)), userName, password, sshPort, command, stringToFind, ifStringFound, ifStringNotFound);
    }
}

//http://localhost:8080/api/sshexec?hostname=sbx-nxos-mgmt.cisco.com&username=admin&password=Admin_1234!&sshport=8181&command=show%20running-config%20eigrp&stringtofind=authentication%20mode%20md5&ifstringfound=Eigrp%20Secure&ifstringnotfound=Eigrp%Not%Secure