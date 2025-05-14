package nl.lunarflow;

import java.io.IOException;
import com.rabbitmq.client.*;

import nl.lunarflow.models.Config;

public class Server {
    public static Config conf;
    public static void main(String[] args) throws IOException {
        conf = new Config();
        ConnectionFactory cfac = new ConnectionFactory();
        cfac.setHost(conf.mqHost);
        cfac.setUsername(conf.mqUser);
        cfac.setPassword(conf.mqPass);
    }
}