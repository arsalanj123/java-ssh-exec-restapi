package com.example.restservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class SshExec {

    private final String host;
    private final String username;


    public SshExec(String host, String username) {
        this.host = host;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getHost() {
        return host;
    }

    public void main(String[] args) {

        String newhost = host;
        System.out.println(newhost);
        
    }
}














//    public SshExec(String command, String host, String portNumber, String user, String password, String stringToFind, String ifString, String ifNotString, boolean showCmdOutput, boolean successFound) {


//    private final String portNumber;
//    private final String user;
//    private final String password;
//    private final String stringToFind;
//    private final String ifString;
//    private final String ifNotString;
//    private final boolean showCmdOutput;
//    private final boolean successFound;