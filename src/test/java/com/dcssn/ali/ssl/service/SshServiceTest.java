package com.dcssn.ali.ssl.service;

import com.aliyun.core.utils.IOUtils;
import com.dcssn.ali.ssl.AliSslApplicationTests;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Vector;

class SshServiceTest extends AliSslApplicationTests {

    @Resource
    private SshService sshService;

    @Test
    void getSession() throws Exception {
        Session session = sshService.getSession("39.100.65.25", 22, "root", "@YyCg88888888@1");
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        Vector vector = channel.ls("/docker/nginx/cert");
        for (Object o : vector) {
            ChannelSftp.LsEntry file = (ChannelSftp.LsEntry) o;
        }
        InputStream inputStream = channel.get("/docker/nginx/cert/www.dcssn.com.key");
        String utf8String = IOUtils.toUtf8String(inputStream);
        System.out.println(utf8String);
        channel.disconnect();
        session.disconnect();
    }


    @Test
    void uploadFile() throws Exception {
        Session session = sshService.getSession("39.100.65.25", 22, "root", "@YyCg88888888@");
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        channel.put(new ByteArrayInputStream("123".getBytes()), "/docker/nginx/cert/101.dcssn.com.key", ChannelSftp.OVERWRITE);
        channel.disconnect();
        session.disconnect();
    }

    @Test
    void execShell() throws Exception {
        Session session = sshService.getSession("39.100.65.25", 22, "root", "@YyCg88888888@");
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand("docker stop nginx");
        channel.connect();
        channel.disconnect();
        session.disconnect();
    }


}