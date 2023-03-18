package com.ifce.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import db.DB;

public class DaoEnade {
	public static List<String> get(Connection conn) {
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

	public static List<String> consultarTodasAreas(Connection conn) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
						
			st = conn.prepareStatement("SELECT CODIGO_AREA, NOME_AREA "
					+ "FROM AREA_AVALIACAO "
					+ "INNER JOIN EXAME_ENADE ON CODIGO_AREA = FK_CODIGO_AREA "
					+ "GROUP BY NOME_AREA;");

			rs = st.executeQuery();
			List<String> list = new ArrayList<String>();
			if(rs.next()) {
				do {
					System.out.print(rs.toString());
					String arrayValores = "[" +rs.getString("CODIGO_AREA") + ","+ rs.getString("NOME_AREA")+"]";
					list.add(arrayValores);
				}while(rs.next());
			}
			
			rs.close();
			return list;
			
		}catch(Exception e) {
			throw e;
		}finally {
			System.out.println("\n Fim requisição | consultar Areas");
		}
	}

	public static List<String> consultaEdicoesArea(Connection conn,String area) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
						
			List<String> list = new ArrayList<String>();
			
			if(area==null || area.trim() == "")
				return list;
			
			st = conn.prepareStatement("SELECT ANO, NOME_AREA "
					+ "FROM AREA_AVALIACAO "
					+ "INNER JOIN EXAME_ENADE ON CODIGO_AREA = FK_CODIGO_AREA "
					+ "where NOME_AREA LIKE '"+area+"%' "
					+ "GROUP BY ANO ORDER BY ANO DESC;");

			rs = st.executeQuery();
			if(rs.next()) {
				do {
					System.out.print(rs.toString());
					String arrayValores = "[" +rs.getString("ANO") + ","+ rs.getString("NOME_AREA").trim()+"]";
					list.add(arrayValores);
				}while(rs.next());
			}
			
			rs.close();
			return list;
			
		}catch(Exception e) {
			throw e;
		}finally {
			System.out.println("\n Fim requisição | consultar Areas");
		}		
	}

	public static List<String> consultarTodosMunicipios(Connection conn) throws Exception{
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
						
			st = conn.prepareStatement("SELECT DISTINCT MUNICIPIO FROM instituto_ies ORDER BY MUNICIPIO;");

			rs = st.executeQuery();
			List<String> list = new ArrayList<String>();
			if(rs.next()) {
				do {
					//System.out.print(rs.toString());
					list.add(rs.getString("MUNICIPIO"));
				}while(rs.next());
			}
			
			rs.close();
			return list;
			
		}catch(Exception e) {
			throw e;
		}finally {
			System.out.println("\n Fim requisição | consultar Areas");
		}
	}

	public static List<String> consultarTodosIES(Connection conn) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
						
			st = conn.prepareStatement("SELECT DISTINCT NOME_IES FROM INSTITUTO_IES ORDER BY CODIGO_IES, NOME_IES;");

			rs = st.executeQuery();
			List<String> list = new ArrayList<String>();
			if(rs.next()) {
				do {
					//System.out.print(rs.toString());
					list.add(rs.getString("NOME_IES"));
				}while(rs.next());
			}
			
			rs.close();
			return list;
			
		}catch(Exception e) {
			throw e;
		}finally {
			System.out.println("\n Fim requisição | consultar Areas");
		}
	}

	public static List<String> consultarEdicoesAnoMunicipio(Connection conn, String ano, String municipio) throws Exception {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = DB.getConnection();
						
			List<String> list = new ArrayList<String>();
			
			if(ano==null || ano.trim() == "")
				return list;
			if(municipio==null || municipio.trim() == "")
				return list;
			
			st = conn.prepareStatement("SELECT FK_CODIGO_IES, SIGLA_IES, NOME_IES, ANO,"
					+ " NOME_AREA, FK_MUNICIPIO, MODALIDADE_ENSINO, "
					+ "CONCEITO_ENADE_CONTINUO, NUMERO_CONCLUINTES_PARTICIPANTES,"
					+ " NUMERO_CONCLUINTES_INSCRITOS "
					+ "FROM EXAME_ENADE "
					+ "INNER JOIN AREA_AVALIACAO ON CODIGO_AREA = FK_CODIGO_AREA "
					+ "INNER JOIN INSTITUTO_IES ON CODIGO_IES = FK_CODIGO_IES "
					+ "WHERE ANO = "+ano.trim()+" AND MUNICIPIO = '"+municipio+"' AND FK_MUNICIPIO = '"+municipio+"' "
					+ "GROUP BY  FK_CODIGO_IES, NOME_IES, NOME_AREA;");

			rs = st.executeQuery();
			if(rs.next()) {
				do {
					System.out.print(rs.toString());
					String arrayValores = "["
					+rs.getString("FK_CODIGO_IES") + ","+ rs.getString("SIGLA_IES").trim()+","+
					rs.getString("NOME_IES") + ","+ rs.getString("ANO").trim()+","+
					rs.getString("NOME_AREA") + ","+ rs.getString("FK_MUNICIPIO").trim()+","+
					rs.getString("MODALIDADE_ENSINO") + ","+ rs.getString("CONCEITO_ENADE_CONTINUO").trim()+","+
					rs.getString("NUMERO_CONCLUINTES_PARTICIPANTES") + ","+
					rs.getString("NUMERO_CONCLUINTES_INSCRITOS").trim()+
					"]";
					list.add(arrayValores);
				}while(rs.next());
			}
			
			rs.close();
			return list;
			
		}catch(Exception e) {
			throw e;
		}finally {
			System.out.println("\n Fim requisição | consultar Areas");
		}
	}
}
