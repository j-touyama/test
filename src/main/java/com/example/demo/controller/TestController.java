package com.example.demo.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AsyncServiceErrorHandle;
import com.example.demo.service.AsyncServiceGeneration;
import com.example.demo.service.AsyncServicePostProcessing;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

	@Autowired 
	private AsyncServiceGeneration generationService;
	@Autowired 
	private AsyncServicePostProcessing postProcessingService;
	@Autowired 
	private AsyncServiceErrorHandle errorHandleService;

	Logger log = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "asyncTest", method = RequestMethod.GET)
    public void asyncProcess() throws InterruptedException {

		/** joinの実行 */ 
		// CompletableFutureクラスの生成
		// 上記で生成したrunTestの処理にさらに付け足す。
		CompletableFuture<Void> runAsyncSample = generationService.runAsyncSample("completedFuture TEST");
		CompletableFuture<Void> thenRunSample = postProcessingService.thenRunSample(runAsyncSample, "==========");
		thenRunSample.join();

		/** getの実行 */ 
		// CompletableFutureクラスの生成
		CompletableFuture<String> supplyAsyncSample = generationService.supplyAsyncSample("田中", "太郎");
		try {
			// get()で実行結果を取得する
			log.info(supplyAsyncSample.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		/** allOfの実行 */ 
		log.info("- allOf start -");
		// CompletableFutureクラスの生成
		CompletableFuture<String> futureSample1 = generationService.completedFutureSample("Alice");
		CompletableFuture<String> futureSample2 = generationService.completedFutureSample("Bob");
		// 上記で生成した処理にさらに付け足す。
		CompletableFuture<Void> thenApplySample1 = postProcessingService.thenApplyAndAcceptSample(futureSample1, true);
		CompletableFuture<Void> thenApplySample2 = postProcessingService.thenApplyAndAcceptSample(futureSample2, false);

		CompletableFuture.allOf(thenApplySample1, thenApplySample2).join();
		log.info("- allOf end -");

		/** anyOfの実行 */ 
		log.info("- anyOf start -");
		CompletableFuture<String> futureSample3 = generationService.completedFutureSample("Chris");
		CompletableFuture<String> futureSample4 = generationService.completedFutureSample("Diana");
		CompletableFuture<Void> thenApplySample3 = postProcessingService.thenAcceptSample(futureSample3, true);
		CompletableFuture<Void> thenApplySample4 = postProcessingService.thenAcceptSample(futureSample4, false);

		CompletableFuture.anyOf(thenApplySample3, thenApplySample4).join();
		log.info("- anyOf end -");

		/** exceptionallyの実行 */ 
		errorHandleService.errorHandle(CompletableFuture.supplyAsync(() -> {throw new NullPointerException("");})).join();

		log.info("==========");
	}
}