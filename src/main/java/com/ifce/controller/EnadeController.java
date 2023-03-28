package com.ifce.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import db.DB;

@RestController
@RequestMapping("/analiseenade")
public class EnadeController {
	static Connection conn = null;
	
	/*@GetMapping("/getTeste") 
	public List<String> get() {
		try {
			return DaoEnade.get(conn);
		}catch(Exception e) {
			throw e;
		}
	}*/
	@GetMapping("/consulta-todas-areas")
	public List<String> consultaAreas() throws Exception{
		try {
			return DaoEnade.consultarTodasAreas(conn);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/consulta-edicoes-por-area")
	public List<String> consultaEdicoesPorArea(@RequestParam(name = "nomeArea") String nomeArea) throws Exception{
		try {
			return DaoEnade.consultaEdicoesArea(conn, nomeArea);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/consulta-todos-municipios")
	public List<String> consultaMunicipios() throws Exception{
		try {
			return DaoEnade.consultarTodosMunicipios(conn);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/consulta-todos-ies")
	public List<String> consultaTodosIES() throws Exception{
		try {
			return DaoEnade.consultarTodosIES(conn);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/consulta-dados-por-ano-municipio-area-nomeies")
	public List<String> consultaEdicoesPorAnoMunicipio(
			@RequestParam(name = "anoInicial") String anoInicial,
			@RequestParam(name = "anoFinal") String anoFinal,
			@RequestParam(name="municipio") String municipio,
			@RequestParam(name="area") String area,
			@RequestParam(name="nomeies") String nomeIes) throws Exception{
		try {
			return DaoEnade.consultarIndicesPorAnoMunicipioAreaNomeIES(conn, anoInicial, anoFinal, municipio, area, nomeIes);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/consulta-dados-por-ano-municipio")
	public List<String> consultaEdicoesPorAnoMunicipio(@RequestParam(name = "ano") String ano,@RequestParam(name="municipio") String municipio) throws Exception{
		try {
			return DaoEnade.consultarEdicoesAnoMunicipio(conn, ano, municipio);
		}catch(Exception e) {
			throw e;
		}
	}
	
	

}