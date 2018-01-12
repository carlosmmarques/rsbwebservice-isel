**Sistema de Gestão para Corpos de Bombeiros**
====================================================================

Aplicação WEB desenvolvida em Java8 usando a framework Pivotal Spring. consiste de:

 - Serviço Web rico e devidamente documentado (v/ Wiki), expondo todos os serviços através de API REST.
 - Aplicação Cliente mínima para teste do serviço.



Configurações
---

Para uma correcta utilização, após criação de cada módulo (via *build.gradle*), garanta as seguintes configurações:

- A execução do módulo **RSB-WebService** necessita de criação prévia de uma base de dados de nome **rsbWebServiceDB**, em PostgreSQL, que tenha um utilizador de perfil *owner* com **username** = "dbuser" e **password** = "dbpass";
- O módulo **RSB-WebService** executa no porto **1234** (http://localhost:1234). O acesso é protegido com basic authentication (header `` Authorization: Basic dXNlcjpwYXNzd29yZA==``, ou credenciais ``user=user`` e ``password=password``)

- O módulo **RSB-WebClient** executa no porto **8080** (http://localhost:8080);
- Se pretender utilizar outras configurações, terá de alterar manualmente o ficheiro **/src/main/resources/application.properties** do respectivo módulo.


___

Projecto desenvolvido por:
- Carlos Marques
- Tiago Venturinha
 



