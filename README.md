# TaskManager - Spring Boot Application

Liz Brito                                 R.A: 22240941-2
Renata Casoni                             R.A: 22272753-2
Lorenzo Begnozzi                          R.A: 23067179-2
## Overview

Aplicação Spring Boot com autenticação JWT e gerenciamento de usuários. Ele permite o registro de novos usuários, login e acesso a recursos protegidos. O sistema de segurança usa um filtro JWT para proteger endpoints e garantir que somente usuários autenticados possam acessar recursos sensíveis.

## Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.x**
* **Spring Security** (com autenticação baseada em JWT)
* **JPA e Hibernate** para persistência de dados com MySQL
* **BCrypt** para codificação de senhas
* **JWT (JSON Web Token)** para gerenciamento de sessões de usuário
* **MySQL** como banco de dados relacional

## Funcionalidades

* **Registro de Usuário**: Novo usuário pode ser registrado com nome, email, senha e função.
* **Login**: Autenticação do usuário via email e senha, gerando um token JWT.
* **Proteção de Endpoints**: Endpoints específicos são protegidos com JWT para garantir que apenas usuários autenticados possam acessá-los.

## Endpoints

### 1. Registro de Usuário

**POST** `/auth/register`

Body:

```json
{
    "name": "Nome do Usuário",
    "email": "usuario@dominio.com",
    "password": "senha",
    "role": "USER" // ou "ADMIN"
}
```

Resposta:

```text
Usuário registrado com sucesso!
```

### 2. Login

**POST** `/auth/login`

Body:

```json
{
    "email": "usuario@dominio.com",
    "password": "senha"
}
```

Resposta:

```json
{
    "token": "jwt-token-gerado"
}
```

### 3. Recursos Protegidos

**GET** `/tasks/**`
Este endpoint exige um token JWT válido para acesso. Pode ser acessado por usuários com as permissões `USER` ou `ADMIN`.

**GET** `/admin/**`
Este endpoint exige um token JWT válido e acesso restrito ao usuário com a permissão `ADMIN`.

## Como Rodar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/taskmanager.git
```

### 2. Configurar o banco de dados

Crie um banco de dados no MySQL chamado `taskmanager_db` ou altere a configuração no `application.properties` para refletir sua configuração de banco.

### 3. Instalar as dependências

```bash
mvn clean install
```

### 4. Subir o servidor

```bash
mvn spring-boot:run
```

O servidor estará disponível em `http://localhost:8080`.

## Considerações Finais

Este projeto é um exemplo básico de autenticação JWT com Spring Boot e pode ser expandido com funcionalidades adicionais, como a gestão de tarefas e outras permissões de usuários. A segurança foi implementada de forma simples para fins de aprendizado.

---
