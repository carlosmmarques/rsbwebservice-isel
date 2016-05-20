**Projecto de Sistema de Gestão para Corpos de Bombeiros Sapadores**
====================================================================

Configurações
---

Para uma correcta utilização, após criação de cada módulo (via *build.gradle*), garanta as seguintes configurações:

- A execução do módulo **RSB-WebService** necessita de criação prévia de uma base de dados de nome **rsbWebServiceDB**, em PostgreSQL, que tenha um utilizador de perfil *owner* com **username** = "dbuser" e **password** = "dbpass";
- O módulo **RSB-WebClient** executa no porto **1234** (http://localhost:1234);
- Se pretender utilizar outras configurações, terá de alterar manualmente o ficheiro **/src/main/resources/application.properties** do respectivo módulo.

Para mais detalhes consultar a página *wiki* do projecto (https://github.com/isel-leic-ps/s1516v39/wiki).

___

Projecto desenvolvido, no âmbito da Unidade Curricular Projecto e Seminário (Semestre de Verão 2015/2016), por:
- Carlos Marques n.º 25993
- Tiago Venturinha n.º 31531
 

