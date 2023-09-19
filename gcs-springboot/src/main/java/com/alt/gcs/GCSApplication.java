package com.alt.gcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.alt.gcs", "io.mavsdk"})
public class GCSApplication {


	public static void main(String[] args) {

		try {
			SpringApplication.run(GCSApplication.class, args);
		}
		catch (Throwable t) {
			System.out.println(t.getMessage());
			t.printStackTrace();
		}
	}

}
