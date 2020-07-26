package com.pudulabs.cloudrun.dataclean.config;

import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.ProjectName;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * DataClean Secrets Load Service (GCP Secret Manager)
 *
 * @author Oliver Fierro V.
 */
@Slf4j
@Configuration
public class Secret {

    private String projectId;

    private String versionId;

    public Map<String, String> secretVar = new HashMap<>();

    public Secret(@Value ("${gcp.project}") String projectId, @Value ("${gcp.secrets.versionId}") String versionId) {
        this.projectId = projectId;
        this.versionId = versionId;
        accessSecretVersion();
    }

    public void accessSecretVersion() {

        try {
            log.info("DataClean Init accessSecretVersion");
            ProjectName projectName = ProjectName.of(projectId);
            SecretManagerServiceClient client = SecretManagerServiceClient.create();
            SecretManagerServiceClient.ListSecretsPagedResponse pagedResponse = client.listSecrets(projectName);

            // List all secrets.
            pagedResponse
                    .iterateAll()
                    .forEach(
                            secret -> {
                                String secretName = secret.getName().substring(secret.getName().lastIndexOf("/")+1);
                                SecretVersionName secretVersionName = SecretVersionName.of(projectId, secretName, versionId);
                                AccessSecretVersionResponse response = client.accessSecretVersion(secretVersionName);
                                System.out.println("My secret: " + secretName + "|" + response.getPayload().getData().toStringUtf8());
                                secretVar.put(secretName, response.getPayload().getData().toStringUtf8());
                            });
        } catch (IOException e) {
            log.error("IOException: {}", e.getMessage());
        }
    }
}
