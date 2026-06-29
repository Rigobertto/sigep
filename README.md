# SIGEP - API (API de Informações Gerenciais de Exploração e Produção)

Esta é uma API desenvolvida em **Java + Spring Boot** responsável por realizar o processamento e a importação de planilhas de dados (CSV) da ANP (Agência Nacional do Petróleo, Gás Natural e Biocombustíveis), mais especificamente a tabela de **Poços Perfurados no País**, e disponibilizá-los em formato JSON através de endpoints REST.

## 🏗️ Arquitetura e Tecnologias Utilizadas

A aplicação foi desenhada utilizando uma arquitetura em camadas (MVC/Layered) para garantir a separação de responsabilidades e facilitar a manutenção:

- **Model (`Poco.java`)**: Representa a entidade de banco de dados e os mapeamentos diretos das colunas do CSV, utilizando as anotações do JPA e do OpenCSV.
- **Repository (`PocoRepository.java`)**: Camada de acesso a dados (Data Access Layer) utilizando **Spring Data JPA**. Ela abstrai as consultas ao PostgreSQL e provê suporte nativo a paginação e consultas complexas usando a linguagem JPQL.
- **Service (`PocoService.java`)**: Contém as regras de negócio. É responsável por receber o arquivo via HTTP, processá-lo na memória, e enviar grandes volumes de dados para o banco sem estourar o limite de memória (através de lotes / *batching*). Além disso, higieniza os parâmetros de pesquisa.
- **Controller (`PocoController.java`)**: A camada de comunicação REST, que expõe os endpoints de entrada e saída.

**Stack Tecnológico:**
- **Java 26**
- **Spring Boot 4.1.0** (Web, Data JPA, Validation)
- **PostgreSQL** (Banco de dados relacional)
- **OpenCSV** (Parser de alto desempenho para leitura estruturada do CSV)
- **Lombok** (Redução de código boilerplate - Getters/Setters)
- **Maven** (Gerenciamento de dependências e build wrapper)

---

## 🛠️ Como Instalar e Configurar

### Pré-requisitos
1. **Java Development Kit (JDK) 26** (ou compatível com a versão configurada no projeto).
2. **PostgreSQL** instalado e rodando (porta padrão `5432`).
3. Criar um banco de dados vazio no PostgreSQL chamado `og-prod`.

### Configuração do Banco de Dados
No arquivo `src/main/resources/application.properties`, configure as credenciais do seu banco de dados caso sejam diferentes do padrão do sistema:

```properties
# Database Configuration (PostgreSQL)
spring.datasource.url=jdbc:postgresql://localhost:5432/og-prod
spring.datasource.username=postgres
spring.datasource.password=admin
```

> A aplicação utiliza a estratégia `ddl-auto=update`, o que significa que a tabela `pocos` (e suas colunas) será criada **automaticamente** na primeira vez em que você iniciar a API, baseando-se na entidade `Poco.java`.

---

## 🚀 Como Executar a Aplicação

Na raiz do projeto (onde está o arquivo `pom.xml`), abra um terminal e rode o comando que utiliza o wrapper embutido do Maven:

**No Windows:**
```bash
.\mvnw.cmd spring-boot:run
```

**No Linux/Mac:**
```bash
./mvnw spring-boot:run
```

O servidor embutido do Tomcat será iniciado na porta **8080**.

---

## 📖 Como Usar a API (Endpoints)

### 1. Fazer o Upload do CSV
Pega um arquivo CSV com os dados da ANP e o envia para ser inserido no banco de dados.

- **Método**: `POST`
- **URL**: `http://localhost:8080/api/pocos/upload`
- **Body / Payload**: `multipart/form-data`
  - Chave: `file` (Tipo: Arquivo)
  - Valor: `<selecione-seu-arquivo.csv>`

**Exemplo com cURL:**
```bash
curl -F "file=@/caminho/completo/para/sua/planilha.csv" http://localhost:8080/api/pocos/upload
```

*(Nota: O limite de tamanho de upload foi configurado para **50MB** para suportar as planilhas densas da ANP).*

---

### 2. Listar e Filtrar os Dados Injetados
Retorna a lista paginada dos poços em formato JSON.

- **Método**: `GET`
- **URL**: `http://localhost:8080/api/pocos`

**Filtros Suportados (Via Query Params):**
- `poco`: Filtra por código do poço (Ignora maiúsculas/minúsculas e suporta trechos parciais).
- `operador`: Filtra pelo nome da operadora (Ignora maiúsculas/minúsculas e suporta trechos parciais).
- `estado`: Filtra exatamente pelo estado.
- `bacia`: Filtra exatamente pela bacia.

**Paginação (Nativa do Spring Data):**
- `page`: Número da página (inicia no `0`). Padrão: 0.
- `size`: Quantidade de itens por página. Padrão: 20.
- `sort`: Propriedade para ordenar. Ex: `sort=poco,asc`

**Exemplos de Buscas:**
- Buscar os primeiros 50 poços da Mandacaru Energia no Rio Grande do Norte:
  ```
  http://localhost:8080/api/pocos?operador=mandacaru&estado=RN&size=50
  ```
Exemplo de retorno:
<img width="1082" height="851" alt="image" src="https://github.com/user-attachments/assets/1fa17ba8-635a-48d3-8b99-7972313773c9" />
  
- Buscar todos os poços da Bacia de Campos que contenham "RJS" no nome:
  ```
  http://localhost:8080/api/pocos?bacia=Campos&poco=rjs
  ```
Exemplo de retorno:
<img width="1078" height="851" alt="image" src="https://github.com/user-attachments/assets/02cbe1fe-d350-447a-844f-6a4f4c08d659" />

---

## 📚 Documentação (Swagger UI)

Esta API conta com uma interface gráfica gerada automaticamente pelo **Swagger (OpenAPI 3)**. Através dela, você pode visualizar todos os endpoints, ler as descrições dos parâmetros e até mesmo executar requisições (como fazer o upload do arquivo) diretamente pelo navegador, sem precisar do cURL.

Para acessar, com o servidor rodando, abra no seu navegador:
**[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html)**

<img width="720" height="326" alt="image" src="https://github.com/user-attachments/assets/6ab21c04-9df9-4774-b575-976587f5df7c" />

