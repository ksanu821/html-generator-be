package com.acko.htmlgenerator.secrets;


import com.acko.htmlgenerator.pojo.DBCredential;
import com.acko.htmlgenerator.pojo.Secrets;

public interface ISecretManager {

    Secrets getSecrets();

    DBCredential getDBCredential(String dbName);
}
