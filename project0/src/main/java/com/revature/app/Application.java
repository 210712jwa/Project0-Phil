package com.revature.app;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import com.revature.util.ConnectionUtility;
import com.revature.controller.AccountController;
import com.revature.controller.ClientController;

import io.javalin.Javalin;

public class Application {

	public static Javalin app;
	public static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		app = Javalin.create();
		app.start(1000);

		mapControllers(new ClientController(), new ExceptionController(), new AccountController());

//		System.out.println(System.getenv("db_url0"));
//		System.out.println(System.getenv("db_username"));
//		System.out.println(System.getenv("db_password"));

//		try {
//			Connection connection = ConnectionUtility.getConnection();
//			System.out.println(connection);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	public static void mapControllers(Controller... controllers) {
		for (Controller c : controllers) {

			c.mapEndpoints(Application.app);
		}
	}

}