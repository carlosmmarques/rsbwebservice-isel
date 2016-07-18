package pt.isel.ps.li61n.viewModel;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 05/07/2016.
 *
 * @author Carlos Marques - carlosmmarques@gmail.com
 *         Tiago Venturinha - tventurinha@gmail.com

*
 */
public final class ErrorsMsg {



    //
    // Mensagens para formulários com a entidade 'Elemento'
    //
    public static final class Elemento{

        public static final class Nome{

            public static final String
                NOT_NULL = "Preencha o campo \"Nome completo\"."
                ,SIZE = "A dimensão do nome não pode exceder os 250 caracteres."
            ;
        }

        public static final class DataNascimento{

            public static final String
                //NOT_NULL = "Preencha o campo \"Data de nascimento\".",
                PATTERN = "Preencha o campo \"Data de nascimento\" com uma data válida (dd/mm/aaaa)."
            ;
        }

        public static final class NumMecanografico{

            public static final String
                NOT_NULL = "Preencha o campo \"Número mecanográfico\".";
        }

        public static final class Matricula{

            public static final String
                NOT_NULL = "Preencha o campo \"Matrícula\"."
                ,RANGE = "O número de matrícula tem de ser positvo."
            ;
        }

        public static final class DataIngresso{

            public static final String
                //NOT_NULL = "Preencha o campo \"Data de ingresso\"."
                PATTERN = "Preencha o campo \"Data de ingresso\" com uma data válida (dd/mm/aaaa)."
            ;
        }
    }

    //
    // Mensagens para formulários com a entidade 'Instalacao'
    //
    public static final class Instalacao{

        public static final class Localizacao{
            public static final String
                NOT_NULL = "Preencha o campo \"Localização\"."
                ,SIZE = "A dimensão da localização não pode exceder os 100 caracteres."
            ;
        }

        public static final class Descricao{
            public static final String
                    NOT_NULL = "Preencha o campo \"Descrição\"."
                    ,SIZE = "A dimensão da descricao não pode exceder os 200 caracteres."
                    ;
        }
    }

    public static final class Geral{

        public static final class Designacao{
            public static final String
                    NOT_NULL = "Preencha o campo \"Designação\"."
                    ,SIZE = "A dimensão da designação não pode exceder os 50 caracteres."
                    ;
        }
    }

    public static final class Periodo{

        public static final class DataInicio{

            public static final String
                    //NOT_NULL = "Preencha o campo \"Data de ingresso\"."
                    PATTERN = "Preencha o campo \"Data de inicío\" com uma data válida (dd/mm/aaaa)."
                    ;
        }


        public static final class DataFim{

            public static final String
                    //NOT_NULL = "Preencha o campo \"Data de ingresso\"."
                    PATTERN = "Preencha o campo \"Data de fim\" com uma data válida (dd/mm/aaaa)."
                    ;
        }
    }
}
