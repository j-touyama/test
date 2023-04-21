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
public class AsyncServiceErrorHandle {

	Logger log = LoggerFactory.getLogger(AsyncServiceErrorHandle.class);

    @Async
    public CompletableFuture<String> errorHandle(CompletableFuture<String> future) {
    	// void型をラップする
    	return future.exceptionally(NullPointerException -> {
    		log.info("エラーが発生してましたが大丈夫！");
    		return "No Error!";
    	});
    }
}
