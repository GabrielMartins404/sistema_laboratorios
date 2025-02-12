# Sistema de laboratorios BackEnd

## Descrição
Este é um sistema desenvolvido em Spring Boot para gerenciar laboratórios e seus respectivos horários de funcionamento. Ele permite a visualização de laboratórios e horários, cadastro de usuários, criação e cancelamento de reservas.

##Tecnologias usadas
Java versão 23
Apache Maven versão 3.9.9
Mysql versão 8.0.40


## Instalação e configuração
1. Clone o repositório com o comando [git clone https://github.com/GabrielMartins404/sistema_laboratorios.git].
2. Basta executar o projeto seguindo o caminho: src/main/java/MainApplication.java
3. Execute o projeto.

## Uso
Basta abrir o projeto e deixar em execução, pois automaticamente será criado banco usando o SGBD mySql de nome sistemaLaboratorio.
O sistema usará por padrão a porta 8080. Portanto, é de suma importância que não haja nada em execução

## Estrutura do Projeto

Este projeto é desenvolvido com Spring Boot e segue uma arquitetura modular baseada em camadas, garantindo organização, reutilização de código e facilidade de manutenção.  

### Estrutura dos Diretórios  

- src/main/java/com/sistema_laboratorios/main/  
  - config/
    WebConfig.java: Classe de configuração para a aplicação, podendo incluir CORS, interceptadores e outros ajustes globais.  

    controllers/ (Camada de controle - Define os endpoints da API) 
    - HorarioController.java: Controlador para gerenciamento de horários.  
    - LaboratorioController.java: Controlador para gestão dos laboratórios.  
    - ReservaController.java: Controlador responsável pelas reservas.  
    - UsuarioController.java: Controlador para operações relacionadas a usuários.  

    dto/ (Objetos de Transferência de Dados - DTOs) 
    - UsuarioRetornoDto.java: Define o formato de retorno dos dados do usuário, evitando exposição direta do modelo.  

    models/ (Camada de Modelo - Representa as entidades do sistema)  
    - Horario.java: Modelo que representa os horários disponíveis.  
    - Laboratorio.java: Modelo referente aos laboratórios.  
    - Reserva.java: Modelo que gerencia as reservas feitas pelos usuários.  
    - Usuario.java: Modelo de usuário, contendo seus dados e permissões.  

    repositories/ (Camada de Acesso a Dados - Repositórios JPA)  
    - HorarioRepository.java: Interface para operações com a entidade Horario.  
    - LaboratorioRepository.java: Interface para operações com Laboratorio.  
    - ReservaRepository.java: Interface para operações com Reserva.  
    - UsuarioRepository.java: Interface para operações com Usuario.  

    services/ (Camada de Serviço - Contém a lógica de negócio) 
    - MainApplication.java: Classe principal que inicia a aplicação Spring Boot. (Obs.: Se for um serviço, normalmente ele estaria separado dos controladores e modelos, então pode ser interessante reorganizar esse arquivo caso ele seja o ponto de entrada da aplicação.)  

- src/main/resources/ (Arquivos de configuração e recursos estáticos)
  - Contém arquivos como application.properties ou application.yml`, que definem configurações do sistema.
  
 Sobre a Arquitetura  
O projeto segue o padrão MVC (Model-View-Controller), separando responsabilidades entre as camadas:  
✔ Models representam os dados da aplicação.  
✔ Repositories interagem com o banco de dados.  
✔ Services processam as regras de negócio.  
✔ Controllers expõem endpoints para consumo da API.  

Essa estrutura modular facilita futuras expansões e manutenção do código.  

