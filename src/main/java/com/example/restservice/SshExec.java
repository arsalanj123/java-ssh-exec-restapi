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

import java.io.File;
import java.io.FileReader;

public class SshExec {

    private final String hostName;
    private final String userName;
    private final String password;
    private final String sshPort;
    private final String command;
    private final String stringToFind;
    private final String ifStringFound;
    private final String ifStringNotFound;

    public SshExec(String hostName,
                   String userName,
                   String password,
                   String sshPort,
                   String command,
                   String stringToFind,
                   String ifStringFound,
                   String ifStringNotFound) {

        this.hostName = hostName;
        this.userName = userName;
        this.password = password;
        this.sshPort = sshPort;
        this.command = command;
        this.stringToFind = stringToFind;
        this.ifStringFound = ifStringFound;
        this.ifStringNotFound = ifStringNotFound;
    }

    public String getUserName() {
        return userName;
    }

    public String getHostName() {
        return hostName;
    }

    public String getPassword(){
        return password;
    }

    public String getSshPort(){
        return sshPort;
    }

    public String getCommand() {
        return command;
    }

    public String getStringToFind() {
        return stringToFind;
    }

    public String getIfStringFound() {
        return ifStringFound;
    }

    public String getIfStringNotFound() {
        return ifStringNotFound;
    }

    public String executeOnDevice(String hostName,
                                  String userName,
                                  String password,
                                  String sshPort,
                                  String command,
                                  String stringToFind,
                                  String ifStringFound,
                                  String ifStringNotFound
    ) {
//        // Add command to run on ClI of the device
//        command = "show running-config eigrp";
//        // Add the hostname or host IP
//        hostName = "sbx-nxos-mgmt.cisco.com";
//        // Add the port number
//        sshPort = "8181";
//        // Add the username to login with
//        userName= "admin";
//        // Add the password to login with
//        password = "Admin_1234!";
//        // Add the substring to find in the resulting output of the command
//        stringToFind = "authentication mode md5";
//        // Add the output required to be displayed if the substring is found
//        ifStringFound = "EIGRP Authenctication is Secure - Using MD5 Authentication";
//        // Add the output required to be displayed if the substring is NOT found
//        ifStringNotFound = "EIGRP Authentication is Insecure - Recommended to use MD5 Authentication";
//        //Set true or false if you want to see command execution output display
//        System.out.println(hostName+userName+password+sshPort+command+stringToFind+ifStringFound+ifStringNotFound);


        try {
            Integer portNumber = Integer.parseInt(sshPort);

            boolean showCmdOutput = true;

            // Leave this variable
            boolean successFound = false;

            // Instantiate an object of JSch class
            JSch jsch = new JSch();
            // define paramaters for getSession method and apply to object and another object of it
            Session session = jsch.getSession(userName, hostName, portNumber);
            Properties config = new Properties();
            // Strict Host Key Checking
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            ;
            session.setPassword(password);
            session.connect();

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream input = channel.getInputStream();
            channel.connect();

            System.out.println("\nChannel Connected to machine " + hostName + " server with command: " + command + "\n");

            try {
                InputStreamReader inputReader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(inputReader);
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    if (showCmdOutput == true) {
                        System.out.println(line);
                    } else {
                        continue;
                    }
                    boolean isFound = line.contains(stringToFind);
                    if (isFound == true) {
                        successFound = true;
                    } else {
                        continue;
                    }
                }

                bufferedReader.close();
                inputReader.close();

                if (successFound == false) {
                    System.out.println(ifStringNotFound);
                    return ifStringNotFound;
                } else {
                    System.out.println(ifStringFound);
                    return ifStringFound;
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println(ex);
            }

            channel.disconnect();
            session.disconnect();
        } catch (
                Exception ex) {
            ex.printStackTrace();
            System.out.println(ex);

        }
        return "Error running the try";

    }
}
