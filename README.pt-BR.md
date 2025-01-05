# Teste Técnico Mili

Um sistema moderno de gerenciamento de produtos e-commerce baseado em microsserviços, construído com Spring Boot e React.

## 🏗️ Arquitetura

### Microsserviços
- **Serviço de Produtos** (Porta 8080)
  - Gerencia operações CRUD de produtos
  - Administra metadados e informações básicas dos produtos
  - URL Base: `/api/products`

- **Serviço de Preços** (Porta 8081)
  - Gerencia cálculos relacionados a preços
  - Fornece estatísticas de preços
  - URL Base: `/api/prices`

- **Serviço de Notificações** (Porta 8082)
  - Gerencia notificações assíncronas
  - Administra comunicações orientadas a eventos
  - URL Base: `/api/notifications`

### Aplicação Frontend (Porta 80)
- SPA baseada em React com TypeScript
- Interface moderna com componentes Material-UI
- Design responsivo para todos os dispositivos

## 🛠️ Tecnologias

### Backend
- **Java 17**
- **Spring Boot 3.x**
  - Spring Web
  - Spring Data JPA
  - Spring AMQP
  - Spring Validation
- **PostgreSQL** - Banco de dados principal
- **RabbitMQ** - Broker de mensagens
- **Swagger/OpenAPI** - Documentação da API
- **Flyway** - Migrações de banco de dados
- **JUnit 5 & Mockito** - Testes

### Frontend
- **React 18**
- **TypeScript**
- **Vite** - Ferramenta de build
- **Material-UI** - Componentes de UI
- **Axios** - Cliente HTTP
- **React Query** - Busca de dados
- **Nginx** - Servidor web

### DevOps
- **Docker** & Docker Compose
- **GitHub Actions** - CI/CD

## 🚀 Começando

### Pré-requisitos
- Docker & Docker Compose
- Node.js 18+ (para desenvolvimento local)
- Java 17 (para desenvolvimento local)

### Executando a Aplicação
```bash
docker compose up -d
```

A aplicação estará disponível em:
- Frontend: http://localhost
- Swagger UI:
  - Produtos: http://localhost:8080/swagger-ui.html
  - Preços: http://localhost:8081/swagger-ui.html
  - Notificações: http://localhost:8082/swagger-ui.html

### Credenciais
- **PostgreSQL**
  - Banco de dados: product_db
  - Usuário: myuser
  - Senha: secret
  - Porta: 5432

- **RabbitMQ**
  - Interface de Gerenciamento: http://localhost:15672
  - Usuário: guest
  - Senha: guest
  - Porta AMQP: 5672
  - Porta de Gerenciamento: 15672

## 📡 Endpoints da API

### Serviço de Produtos (`/api/products`)
- `GET /` - Listar todos os produtos
- `GET /{id}` - Buscar produto por ID
- `POST /` - Criar novo produto
- `PUT /{id}` - Atualizar produto
- `DELETE /{id}` - Excluir produto

### Serviço de Preços (`/api/prices`)
- `GET /average` - Obter preço médio dos produtos
- `GET /most-expensive` - Obter produto mais caro
- `POST /calculate` - Calcular preço com descontos

### Serviço de Notificações (`/api/notifications`)
- `POST /subscribe` - Inscrever-se para notificações
- `POST /notify` - Enviar notificação

## 🎯 Padrões de Projeto & Metodologias

### Padrões de Arquitetura
- **Arquitetura de Microsserviços**
- **Arquitetura Orientada a Eventos** usando RabbitMQ
- **Padrão API Gateway** usando Nginx
- **Circuit Breaker** para resiliência de serviços

### Padrões de Projeto
- **Padrão Repository** - Abstração de acesso a dados
- **Padrão Factory** - Criação de objetos
- **Padrão Builder** - Construção de objetos complexos
- **Padrão Strategy** - Estratégias de cálculo de preços
- **Padrão Observer** - Sistema de notificações

### Metodologias de Desenvolvimento
- **Domain-Driven Design (DDD)**
- **Test-Driven Development (TDD)**
- **Princípios SOLID**
- **Arquitetura Limpa**
- **Metodologia 12-Factor App**

## 🧪 Testes

### Testes Backend
```bash
./mvnw clean test
```

### Testes Frontend
```bash
npm test
```

## 📦 Estrutura do Projeto

```
.
├── technical-test-back/
│   ├── product-service/
│   ├── price-service/
│   └── notification-service/
├── technical-test-front/
│   ├── src/
│   │   ├── components/
│   │   ├── services/
│   │   └── types/
│   └── public/
└── docker-compose.yml
```

## 🌐 Variáveis de Ambiente

### Frontend
- `VITE_API_URL=/api`
- `VITE_PRICE_API_URL=/api/prices`

### Backend
- `SPRING_PROFILES_ACTIVE=prod`
- `POSTGRES_HOST=postgres`
- `RABBITMQ_HOST=rabbitmq`

## 🔒 Segurança

- Configuração CORS
- Validação de entrada
- Limitação de taxa
- Headers de segurança
- Pronto para HTTPS

## 🌟 Funcionalidades

- Design responsivo
- Atualizações de preço em tempo real
- Sistema de notificação assíncrono
- Tratamento abrangente de erros
- Logging detalhado
- Monitoramento de performance
- Migrações de banco de dados
- Documentação da API

## 🤝 Contribuindo

1. Faça um fork do repositório
2. Crie uma branch para sua feature
3. Faça commit das suas alterações
4. Faça push para a branch
5. Crie um Pull Request

## 📝 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para detalhes.
