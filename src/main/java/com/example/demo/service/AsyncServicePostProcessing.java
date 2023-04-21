package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * CompletableFutureクラスの後処理をまとめたサンプルクラス
 */
@Service
@Slf4j
public class AsyncServicePostProcessing {

	Logger log = LoggerFactory.getLogger(AsyncServicePostProcessing.class);

    @Async
    public CompletableFuture<Void> thenRunSample(CompletableFuture<Void> runTest, String logMsg) throws InterruptedException {
    	Thread.sleep(1000);
    	// 受取った非同期処理の後に、Runnableの処理を付け加える
    	return runTest.thenRun(() -> log.info(logMsg));
    }

    @Async
    public CompletableFuture<String> thenApplySample(CompletableFuture<String> future, boolean sleepFlg) throws InterruptedException {
    	if (sleepFlg) {
    		Thread.sleep(1000);
    	}
    	// 受取った非同期処理の後に、Functionの処理を付け加える
    	// ※Function:引数を受取り、同じ型で返却する。
    	return future.thenApply(f -> f.concat(", Hello!"));
    }

    @Async
    public CompletableFuture<Void> thenAcceptSample(CompletableFuture<String> future, boolean sleepFlg) throws InterruptedException {
    	if (sleepFlg) {
    		Thread.sleep(1000);
    	}
    	// 受取った非同期処理の後に、Consumerの処理を付け加える
    	// ※Consumer:引数を受取るが、returnはしない。
    	return future.thenAccept(f -> log.info(f));
    }

    @Async
    public CompletableFuture<Void> thenApplyAndAcceptSample(CompletableFuture<String> future, boolean sleepFlg) throws InterruptedException {
    	return thenAcceptSample(thenApplySample(future, sleepFlg), sleepFlg);
    }
}
