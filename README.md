# 🐾 Vet Clinic API

REST API para gerenciamento de uma clínica veterinária, desenvolvida com Java e Spring Boot. O sistema permite o cadastro e gerenciamento de donos e seus pets.

## 🚀 Tecnologias

- Java 21
- Spring Boot 4
- Spring Data JPA / Hibernate
- Spring Validation
- MySQL
- Lombok
- Maven

## 📋 Funcionalidades

- Cadastro, listagem, busca, edição e remoção de donos (Owner)
- Cadastro, listagem, busca e remoção de pets vinculados a um dono
- Validação de dados de entrada nos DTOs
- Tratamento global de exceções com respostas padronizadas

## 🗂️ Estrutura do Projeto

```
src/
├── controller/       # Camada de entrada HTTP
├── service/          # Regras de negócio
├── repository/       # Acesso ao banco de dados
├── model/            # Entidades JPA
├── dto/              # Objetos de transferência de dados (Request/Response)
└── exception/        # Exceções customizadas e handler global
```

## 🔗 Endpoints

### Owner

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/owners` | Cadastrar novo dono |
| GET | `/owners` | Listar todos os donos |
| GET | `/owners/{id}` | Buscar dono por ID |
| PUT | `/owners/{id}/edit` | Editar dados do dono |
| DELETE | `/owners/{id}` | Remover dono |

### Pet

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/owners/{ownerId}/pets` | Cadastrar pet para um dono |
| GET | `/owners/{ownerId}/pets` | Listar pets de um dono |
| GET | `/pets` | Listar todos os pets |
| GET | `/pets/{id}` | Buscar pet por ID |
| DELETE | `/pets/{id}` | Remover pet |

## ⚙️ Como rodar localmente

### Pré-requisitos

- Java 21+
- MySQL 8+
- Maven

### Configuração

1. Clone o repositório:
```bash
git clone https://github.com/RyanPess/vet-api.git
cd vet-api
```

2. Crie o banco de dados no MySQL:
```sql
CREATE DATABASE vet_db;
```

3. Crie o arquivo `src/main/resources/application.properties` com suas configurações:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vet_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4. Execute o projeto:
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## 📄 Exemplo de requisição

**Cadastrar dono:**
```http
POST /owners
Content-Type: application/json

{
  "name": "João Silva",
  "cpf": "123.456.789-00",
  "email": "joao@email.com",
  "numberPhone": "71999999999",
  "address": "Rua X, 123"
}
```

**Cadastrar pet:**
```http
POST /owners/{ownerId}/pets
Content-Type: application/json

{
  "name": "Rex",
  "specie": "CANINE",
  "breed": "Labrador",
  "age": 3
}
```

## 👨‍💻 Autor

Ryan Almir Pessoa da Silva  
[LinkedIn](https://www.linkedin.com/in/ryan-almir-pessoada-silva) • [GitHub](https://github.com/RyanPess)
