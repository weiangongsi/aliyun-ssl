package com.dcssn.ali.ssl.service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class SshService {

    public Session getSession(String host, int port, String username, String password) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setTimeout(5000);
        session.setConfig("StrictHostKeyChecking", "no"); // 不验证SSH服务器的公钥指纹
        session.connect();
        return session;
    }

    public void execShell(Session session, String command) throws Exception {
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.connect();
        channel.disconnect();
    }

    public void uploadFile(Session session, String path, String filename, String content) throws Exception {
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        channel.put(new ByteArrayInputStream(content.getBytes()), path + "/" + filename, ChannelSftp.OVERWRITE);
        channel.disconnect();
    }

}
