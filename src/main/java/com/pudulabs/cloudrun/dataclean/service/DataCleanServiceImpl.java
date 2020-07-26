package com.pudulabs.cloudrun.dataclean.service;

import com.pudulabs.cloudrun.dataclean.config.Secret;
import com.pudulabs.cloudrun.dataclean.dto.DataCleanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DataClean Multitenant Service
 *
 * @author Oliver Fierro V.
 */
@Slf4j
@Service
public class DataCleanServiceImpl implements DataCleanService {

  @Autowired
  private Secret secret;

  /**
   * Clean Data By Table Name
   *
   * @return DataCleanResponse
   */
  public DataCleanResponse cleanDataMultitenant(String tenant) {

    DataCleanResponse response = new DataCleanResponse();

    try {
      System.out.println("User: " + secret.secretVar.get("CL_DB_USER"));
      System.out.println("Password: " + secret.secretVar.get("CL_DB_PWD"));

      response.setStatus(true);
    } catch (Exception ex) {
      response.setStatus(false);
    }
    return response;
  }

}
