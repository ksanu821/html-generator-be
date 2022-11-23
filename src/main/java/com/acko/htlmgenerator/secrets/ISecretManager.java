package com.acko.htlmgenerator.secrets;


import com.acko.htlmgenerator.pojo.DBCredential;
import com.acko.htlmgenerator.pojo.Secrets;

public interface ISecretManager {

    Secrets getSecrets();

    DBCredential getDBCredential(String dbName);
}
