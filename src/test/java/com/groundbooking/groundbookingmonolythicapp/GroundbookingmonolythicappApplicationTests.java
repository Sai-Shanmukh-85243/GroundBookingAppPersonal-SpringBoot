package com.groundbooking.groundbookingmonolythicapp;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


class GroundbookingmonolythicappApplicationTests {

	@Test
	void contextLoads() {
	}


	@Test
	public void testPasswordEncryption() {

		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("johncena"); // encryptor's private key
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		String plainText = "";	// Enter you plain text here and run this function to get encrypted text of the plain text. then past this encrpted text in application.properties with ENC(encryptedtext)
		String encryptedPassword = encryptor.encrypt(plainText);
		System.out.println("encryptedPassword : " + encryptedPassword);
	}

}
