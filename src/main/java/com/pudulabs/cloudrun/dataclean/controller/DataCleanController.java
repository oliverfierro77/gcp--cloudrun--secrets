package com.pudulabs.cloudrun.dataclean.controller;

import com.pudulabs.cloudrun.dataclean.dto.DataCleanResponse;
import com.pudulabs.cloudrun.dataclean.service.DataCleanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * DataClean Controller
 *
 * @author Oliver Fierro V.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class DataCleanController {

  private final DataCleanService service;

  private static final String TENANT_HEADER_NAME = "X-TENANT-ID";

  @DeleteMapping("/dataclean")
  public ResponseEntity<DataCleanResponse> dataCleanStart(
      @RequestHeader(TENANT_HEADER_NAME) String tenant) {

    DataCleanResponse response = service.cleanDataMultitenant(tenant);

    return ResponseEntity.ok(response);
  }
}
