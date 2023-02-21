package com.snhu.sslserver;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SslServerApplication {
	@RequestMapping("/hash")
	public static String myHash() {
		String data = "Hello Tristin Watson!";
		String alg = "MD2";
		try {
			// getInstance method is called with algorithm MD2
			MessageDigest md = MessageDigest.getInstance(alg);
			
			// digest() method is called to 
			// calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(data.getBytes());
			
			// convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);
			
			// convert messsage digest into hex value
			String hashText = no.toString(16);
			
			// add preceding 0s to make it 32 bit
			while (hashText.length() < 32) {
				hashText = "0" + hashText;
				
			}
			return "<p>data: " + data + " Name of Cipher Algorithm Used: " + alg + " Checksum "
					+ "value: " + hashText;
			}
		// for specifiying wrong mesage digest algorithms
		catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
			}
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
