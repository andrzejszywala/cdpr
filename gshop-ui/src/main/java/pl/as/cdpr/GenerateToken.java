package pl.as.cdpr;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import io.smallrye.jwt.build.Jwt;

/**
 * Generates sample jwt token and prints it to console. Copy generated token and
 * paste in gshop.resource.ts file
 */
public class GenerateToken {

	// add -Dsmallrye.jwt.sign.key.location=privateKey.pem to vm arguments
	public static void main(String[] args) {
		String token = Jwt.issuer("https://gshop.pl/issuer").upn("andrzej").issuedAt(new Date().getTime())
				.expiresAt(new Date().getTime() + 1000000000).groups(new HashSet<>(Arrays.asList("USER"))).sign();
		System.out.println(token);
	}
}