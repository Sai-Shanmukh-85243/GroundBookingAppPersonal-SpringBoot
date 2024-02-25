package com.groundbooking.groundbookingmonolythicapp.Configs;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.salt.FixedStringSaltGenerator;
import org.jasypt.salt.ZeroSaltGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@EnableEncryptableProperties
//@PropertySource("classpath:application.properties")
public class JasyptEncryptorConfig  {

    @Value("${jasypt.encryptor.password}")
    private String secretKey;
    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor  customEncryptor(){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(secretKey); // encryptor's private key

        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");

//        FixedStringSaltGenerator fixedSaltGenerator = new FixedStringSaltGenerator();
//        fixedSaltGenerator.setSalt("MySalt"); // Set your fixed salt here
//        config.setSaltGenerator(fixedSaltGenerator);
//        config.setSaltGeneratorClassName("org.jasypt.salt.ZeroSaltGenerator");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");

        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }
}
