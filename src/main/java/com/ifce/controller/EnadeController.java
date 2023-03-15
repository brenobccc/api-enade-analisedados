package com.ifce.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import db.DB;

@RestController
@RequestMapping("/analiseenade")
public class EnadeController {
	static Connection conn = null;
	@GetMapping 
	public List<String> get() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
						
			st = conn.prepareStatement("SELECT * FROM INSTITUTO_IES WHERE "
						+ "MUNICIPIO = 'Fortaleza'");

			rs = st.executeQuery();
			List<String> list = new ArrayList<String>();
			if(rs.next()) {
				do {
					System.out.print(rs.toString());
					list.add(rs.getString("CODIGO_IES") + " - " +
							rs.getString("NOME_IES"));
				}while(rs.next());
			}
			
			rs.close();
			return list;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			System.out.println("\nFim requisição Get");
			//DB.closeConnection();
		}
	}

}