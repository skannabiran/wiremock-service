package com.mav.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Configuration
@Log4j2
public class WireMockConfig {

  @Value("${wiremock.port}")
  private int wireMockPort;

  @Value("${wiremock.usingFilesUnderClassPath}")
  private String filesClassPath;

  @Value("${wiremock.verbose}")
  private boolean wireMockVerbose;

  @Bean
  public WireMockServer wireMockServer() {
    WireMockServer wireMockServer =
        new WireMockServer(
            wireMockConfig()
                .port(wireMockPort)
                .usingFilesUnderClasspath(filesClassPath)
                .notifier(new ConsoleNotifier(wireMockVerbose)));
    wireMockServer.start();
    log.info("wiremock stub mappings::{}", wireMockServer.getStubMappings());
    log.info("Wiremock server is up & running::{}", wireMockPort);
    return wireMockServer;
  }
}
