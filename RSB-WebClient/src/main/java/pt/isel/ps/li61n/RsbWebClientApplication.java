package pt.isel.ps.li61n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class RsbWebClientApplication{

    /**
     * Definição dos URL globais da aplicação
     */
    public static final String
		UNIDADES_ESTRUTURAIS_URL = "/unidadesEstruturais"
		,MAPA_FORCA_URL = "/mapaForca"
		,PESSOAL_URL = "/pessoal"
		,INSTALACOES_BASE_URL = "/instalacoes"
		,INSTALACOES_URL = UNIDADES_ESTRUTURAIS_URL + "/{id}" + INSTALACOES_BASE_URL
        ,UNIDADES_OPERACIONAIS_URL = "/unidadesOperacionais"
        ,ESCALAS_URL = "/escalas"
	;

	/**
	 * Formatadores de datas
	 * @param args
     */
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );

	public static void main(String[] args) {
		SpringApplication.run(RsbWebClientApplication.class, args);
	}
}
