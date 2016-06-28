package pt.isel.ps.li61n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RsbWebClientApplication {

    /**
     * Definição dos URL globais da aplicação
     */
    public static final String UNIDADES_ESTRUTURAIS_URL = "/unidadesEstruturais";
	public static final String FORMACAO_URL = "/pessoal/formacao";

	public static final String MAPA_FORCA_URL = "/mapaForca";

    public static final String PESSOAL = "/pessoal";

	public static void main(String[] args) {
		SpringApplication.run(RsbWebClientApplication.class, args);
	}
}
