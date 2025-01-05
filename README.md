# Teste Técnico Mili

Esta é minha implementação do desafio técnico da Mili, desenvolvi um sistema de gerenciamento de produtos e-commerce baseado em microsserviços utilizando Spring Boot e React.

## 👋 Sobre o Projeto

O desafio era criar um sistema completo de gerenciamento de produtos e-commerce, e optei por uma arquitetura moderna de microsserviços para demonstrar tanto minhas habilidades técnicas quanto minha capacidade de tomar decisões arquiteturais.

### Minha Abordagem

Para este teste, desenvolvi um sistema capaz de:
- Gerenciar produtos através de uma interface intuitiva
- Processar atualizações de preços e estatísticas em tempo real
- Gerenciar notificações para eventos importantes do sistema
- Implementar diferentes estratégias de precificação
- Suportar produtos físicos e digitais

Escolhi dividir a aplicação em microsserviços não apenas por ser uma tendência, mas porque isso demonstra minha capacidade de:
- Projetar sistemas escaláveis e manuteníveis
- Implementar lógica de negócios complexa em serviços distribuídos
- Gerenciar comunicação entre serviços de forma eficiente
- Aplicar práticas e padrões modernos de desenvolvimento

### Decisões Técnicas

Na construção desta solução, tomei algumas decisões técnicas importantes:
- Utilizei React com TypeScript para um frontend robusto
- Implementei microsserviços com Spring Boot no backend
- Integrei RabbitMQ para comunicação orientada a eventos
- Utilizei PostgreSQL para armazenamento confiável de dados
- Configurei Docker para facilitar implantação e testes

O resultado é um sistema completamente funcional que não apenas atende aos requisitos do teste, mas também demonstra minha compreensão de arquitetura de software moderna e boas práticas.

### O Que Ele Pode Fazer?

Nosso sistema ajuda você a:
- Gerenciar seu catálogo de produtos com uma interface intuitiva
- Acompanhar preços e obter estatísticas automaticamente
- Receber notificações instantâneas sobre mudanças importantes
- Calcular preços com diferentes estratégias de desconto
- Lidar com produtos físicos e digitais

Dividimos a aplicação em pequenos serviços independentes (microsserviços) que trabalham juntos de forma harmoniosa. Isso significa que o sistema não é apenas poderoso, mas também confiável e fácil de escalar. Seja gerenciando uma pequena loja ou um grande marketplace, este sistema está preparado para atender suas necessidades!

### Por Que Essa Abordagem?

Escolhemos uma arquitetura de microsserviços porque ela nos dá a flexibilidade para:
- Escalar cada parte do sistema independentemente
- Fazer atualizações sem tempo de inatividade
- Adicionar novos recursos sem afetar todo o sistema
- Garantir alta disponibilidade e tolerância a falhas

O frontend foi construído com React e Material-UI, oferecendo uma interface moderna e responsiva que é um prazer usar. O backend utiliza Spring Boot, nos dando uma base robusta para lidar com a lógica de negócios e gerenciamento de dados.

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
