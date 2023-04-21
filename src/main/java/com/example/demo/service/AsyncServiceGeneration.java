package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * CompletableFutureクラスの生成処理をまとめたサンプルクラス
 */
@Service
@Slf4j
public class AsyncServiceGeneration {

	Logger log = LoggerFactory.getLogger(AsyncServiceGeneration.class);

    @Async
    public CompletableFuture<Void> runAsyncSample(String logMsg) throws InterruptedException {
    	Thread.sleep(100);
    	// void型をラップする
    	return CompletableFuture.runAsync(() -> log.info(logMsg));
    }

    @Async
    public CompletableFuture<String> supplyAsyncSample(String firstName, String LastName) throws InterruptedException {
    	Thread.sleep(100);
    	// Supplierの結果をラップする。
    	// ※Supplier:引数を受取らず、処理を実施する。
    	return CompletableFuture.supplyAsync(() -> firstName + " " + LastName);
    }
    @Async
    public CompletableFuture<String> completedFutureSample(String name) throws InterruptedException {
    	Thread.sleep(100);
    	// 結果をラップするだけ
    	return CompletableFuture.completedFuture(name);
    }


}
