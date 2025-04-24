# spring_boot_todo_api

### Breve Descricao

Este projeto consiste em uma API REST desenvolvida com Java/Spring Boot, utilizando uma arquitetura orientada em camadas e eventos, com o intuito de aplicar e demostrar os meus conhecimentos tecnicos em algumas technologias envolvidas no desenvolvimento backend, especificamente no ecossistema Java.


### Tecnologias e Conceitos Utilizados

- ✅ Linguagem Java(POO, lambdas, Optionals, streams...)
- ✅ Spring Boot(Web, Security, Cache, Dependency Injection, etc...)
- ✅ Spring Cache com provider configurável
- ✅ Logging com SLF4J e Logback
- ✅ JPA + Hibernate(Relacionamentos entre Entidades, CRUD, controle de transações...)
- ✅ Migrations com Flyway
- ✅ Banco de dados MySQL
- ✅ JWT para autenticação e autorização
- ✅ Role Based Access Control
- ✅ Arquitetura em camadas com Clean Architecture
- ✅ Padrões de design: Strategy, Observer/Event-Driven, Singleton, DTO
- ✅ Testes de unidade com JUnit e Mockito
- ✅ Integração com API externa via RestTemplate/WebClient
- ✅ Gerenciamento de dependências com Maven
- ✅ Versionamento com Git e hospedagem no GitHub


## Sobre o Projeto/Motivação

Este projeto consiste numa aplicação de lista de tarefas. Uma proposta bem simples, mas intencionalmente construida de forma complexa. A escolha de um domínio pequeno e conhecido foi proposital, pois me permitiu focar na arquitetura, nos padrões de projeto, na integração de ferramentas e na flexibilidade do desenvolvimento, algo que seria dificil se tivesse que aplicar em um dominio compexo. No entando, concentrei meus esforços em experimentar, aprender e aplicar conceitos avançados do ecossistema Java e Spring Boot, de modo a implementar um sistema modular, testável e escalável, mesmo sendo um simples To-Do List.


### Integração com um Serviço Externo

A aplicação também se comunica com uma API externa(Fake API) como parte do processo de demonstração de integração de serviços. Após o login, o usuário pode acessar um endpoint que busca tarefas remotas, filtradas de acordo com o seu próprio ID.


#### Essa funcionalidade inclui implementacaoes como:
- Integração com APIs externas usando WebClient;
- Consumo e transformação de dados JSON de serviços terceiros;
- Boas práticas de isolamento e desacoplamento da lógica externa.


### Arquitetura Orientada a Eventos

O sistema também adota uma abordagem orientada a eventos, utilizando o Apache Kafka para processar fluxos de dados de forma assíncrona. no entando, Sempre que uma nova tarefa é criada, o sistema publica um evento no tópico do Kafka, sinalizando que há uma nova ação ocorrida.

E só para deixar a coisa mais interessante foi aplicada uma simples regra de particionamento, a qual garante que os eventos criados até o meio-dia são enviados para uma partição específica do tópico, enquanto os criados após o meio-dia vão para outra partição.

Essa separação simula cenários reais de processamento segmentado por tempo, facilitando análises, escalabilidade e estratégias personalizadas de consumo de eventos.


### Registro de Logs

A API conta com um sistema de logs configurado com o Logback, onde os logs do próprio framework Spring Boot são exibidos diretamente no terminal durante a execução, facilitando o debuging em tempo real,  enquanto os logs relacionados à lógica de negócio e ao fluxo interno da aplicação são armazenados separadamente em um arquivo dedicado, garantindo melhor organização e rastreabilidade.

Isso permite identificar com facilidade o que está acontecendo em cada camada da aplicação, seja durante a execução de regras de negócio, chamadas externas ou operações sensíveis, falicitando desta forma a depuração, auditoria e monitoramento do sistema.


### Testes Unitários

A aplicação conta com uma cobertura de testes unitários, com foco principal nas regras de negócio e nos componentes de serviço. Utilizei ferramentas como JUnit e Mockito para garantir que cada parte da aplicação funcione corretamente de forma isolada, testando diferentes cenários e validando comportamentos esperados, com o objetivo de garantir a confiabilidade, facilidade de manutenção e permitir futuras mudanças no código sem comprometer funcionalidades existentes.


## Como Executar a Aplicação

Para rodar este projeto localmente, siga os passos abaixo:

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

### 2. Instalar dependências e compilar o projeto

Antes de rodar a aplicação, é necessário instalar as dependências com o Maven:
```bash
./mvnw clean install
```

### 3. Subir os serviços com Docker (Kafka)

A aplicação utiliza o Apache Kafka via Docker Compose. Para iniciar os containers necessários, execute:
```bash
docker-compose up -d
```

### 4. Configuração do Banco de Dados

Certifique-se de que o banco MySQL esteja rodando e que o banco de dados esteja criado antes de iniciar a aplicação. em seguida ajusta as credenciais e URL do banco de dados apartir do arquivo: src/main/resources/application-local.properties

```conf
spring.datasource.url=
spring.datasource.username=root
spring.datasource.password=
```

### 5. Rodar os testes

Para executar todos os testes unitários da aplicação, você pode usar o seguinte comando Maven:
```bash
./mvnw test
```

### 6. Rodar a aplicação

Com os serviços externos no ar, agora você pode iniciar a aplicação com o Maven:
```bash
./mvnw spring-boot:run
```

### 7. Acessar a API

Com tudo no ar, a API estará disponível em:
[http://localhost:8080]


## Sobre Mim

Felizardo Chirindja
Desenvolvedor de software

## Contactos

- GitHub: [https://github.com/felizardochirindja]
- LinkedIn: [https://www.linkedin.com/in/felizardo-chirindja-7190b2212]
- Email: [felizardo.chirindja@gmail.com]

## Projetos que podem te interessar

- biblioteca desenvolvida em php que ajuda a validar dados de forma simples
radar_php_lib: [https://packagist.org/packages/felizardochirindja/radar]
 