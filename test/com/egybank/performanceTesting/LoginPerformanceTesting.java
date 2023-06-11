package com.egybank.performanceTesting;

import com.egybank.controllers.onboarding.login.LoginController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginPerformanceTesting {

    private static final int THREAD_COUNT = 60;
    private static LoginController loginController = new LoginController();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Long> threadTimes = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadIndex = i;
            executorService.execute(() -> login(threadIndex, threadTimes));
        }

        executorService.shutdown();

        // Wait until all threads have finished executing
        while (!executorService.isTerminated()) {
            // Waiting for threads to finish
        }

        // Calculate statistics
        calculateStatistics(threadTimes);
    }

    private static void login(int threadIndex, List<Long> threadTimes) {
        String email = "m@m.m";
        String password = "1";

        long startTime = System.currentTimeMillis();
        loginController.login(email, password);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        // Store the time taken by this thread in the list
        threadTimes.add(elapsedTime);

        System.out.println("Thread " + threadIndex + " elapsed time: " + elapsedTime + "ms");
    }

    private static void calculateStatistics(List<Long> threadTimes) {
        long minTime = Long.MAX_VALUE;
        long maxTime = Long.MIN_VALUE;
        long totalTime = 0;

        for (Long time : threadTimes) {
            totalTime += time;
            minTime = Math.min(minTime, time);
            maxTime = Math.max(maxTime, time);
        }

        int threadCount = threadTimes.size();
        double averageTime = (double) totalTime / threadCount;

        System.out.println("Total threads executed: " + threadCount);
        System.out.println("Min time: " + minTime + "ms");
        System.out.println("Max time: " + maxTime + "ms");
        System.out.println("Average time: " + averageTime + "ms");
    }
}
