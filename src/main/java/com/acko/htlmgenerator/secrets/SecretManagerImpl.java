package com.acko.htlmgenerator.secrets;

import com.acko.htlmgenerator.pojo.DBCredential;
import com.acko.htlmgenerator.pojo.Secrets;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Log4j2
public class SecretManagerImpl implements ISecretManager {

    @Value(value = "${secrets.filepath}")
    private String secretsFilePath;

    private Secrets secrets;
    private Map<String, DBCredential> dbCredentialMap;

    @PostConstruct
    public void init() throws IOException {

        this.secrets = new ObjectMapper().readValue(new File(secretsFilePath), Secrets.class);
        this.dbCredentialMap = secrets.getDb().stream().collect(
            Collectors.toMap(DBCredential::getDbname, dbCredential -> dbCredential)
        );
        System.out.println("MAP: "+dbCredentialMap.toString());
    }

    @Override
    public Secrets getSecrets() {
        return this.secrets;
    }

    @Override
    public DBCredential getDBCredential(String dbName) {
        return this.dbCredentialMap.get(dbName);
    }
}
