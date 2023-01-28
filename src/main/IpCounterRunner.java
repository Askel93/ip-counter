package main;

import counter.IpCounter;
import counter.IpCounterBufferedInputStream;

import java.time.Duration;
import java.time.Instant;

public class IpCounterRunner {

    private static String fileName = "src/test_file.txt";

    public static void main(String[] args) {

        var startTime = Instant.now();

        System.out.println("Start parsing");
        System.out.println("-----------------");

        IpCounter ipCounter =new IpCounterBufferedInputStream();

        int count = ipCounter.getCountOfDistinctIp(fileName);

        var time = Duration.between(startTime, Instant.now());
        System.out.println("Time = " + time);

        System.out.println("-----------------");
        System.out.println("uniqueIps = " + count);
        System.out.println("-----------------");
    }
}