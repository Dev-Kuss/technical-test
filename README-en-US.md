# Mili Technical Test

This is my implementation of the Mili technical test challenge, where I developed a microservices-based e-commerce product management system using Spring Boot and React.

## 👋 About This Project

Hi! I'm excited to share my solution for the Mili technical test. The challenge was to create a complete e-commerce product management system, and I decided to go with a modern microservices architecture to showcase both my technical skills and architectural decision-making.

### My Approach

For this test, I developed a system that can:
- Handle product management through an intuitive interface
- Process real-time price updates and statistics
- Manage notifications for important system events
- Implement different pricing strategies
- Support both physical and digital products

I chose to split the application into microservices not just because it's trendy, but because it demonstrates my ability to:
- Design scalable and maintainable systems
- Implement complex business logic across distributed services
- Handle inter-service communication effectively
- Apply modern development practices and patterns

### Technical Decisions

In building this solution, I made several key technical choices:
- Used React with TypeScript for a robust frontend
- Implemented Spring Boot microservices for the backend
- Integrated RabbitMQ for event-driven communication
- Utilized PostgreSQL for reliable data storage
- Set up Docker for easy deployment and testing

The result is a fully functional system that not only meets the test requirements but also showcases my understanding of modern software architecture and best practices.

## 🏗️ Architecture

### Microservices
- **Product Service** (Port 8080)
  - Handles product CRUD operations
  - Manages product metadata and basic information
  - Base URL: `/api/products`

- **Price Service** (Port 8081)
  - Handles price-related calculations
  - Provides price statistics
  - Base URL: `/api/prices`

- **Notification Service** (Port 8082)
  - Handles asynchronous notifications
  - Manages event-driven communications
  - Base URL: `/api/notifications`

### Frontend Application (Port 80)
- React-based SPA with TypeScript
- Modern UI with Material-UI components
- Responsive design for all devices

## 🛠️ Technologies

### Backend
- **Java 17**
- **Spring Boot 3.x**
  - Spring Web
  - Spring Data JPA
  - Spring AMQP
  - Spring Validation
- **PostgreSQL** - Primary database
- **RabbitMQ** - Message broker
- **Swagger/OpenAPI** - API documentation
- **Flyway** - Database migrations
- **JUnit 5 & Mockito** - Testing

### Frontend
- **React 18**
- **TypeScript**
- **Vite** - Build tool
- **Material-UI** - UI components
- **Axios** - HTTP client
- **React Query** - Data fetching
- **Nginx** - Web server

### DevOps
- **Docker** & Docker Compose
- **GitHub Actions** - CI/CD

## 🚀 Getting Started

### Prerequisites
- Docker & Docker Compose
- Node.js 18+ (for local development)
- Java 17 (for local development)

### Running the Application
```bash
docker compose up -d
```

The application will be available at:
- Frontend: http://localhost
- Swagger UI:
  - Products: http://localhost:8080/swagger-ui.html
  - Prices: http://localhost:8081/swagger-ui.html
  - Notifications: http://localhost:8082/swagger-ui.html

### Credentials
- **PostgreSQL**
  - Database: product_db
  - Username: myuser
  - Password: secret
  - Port: 5432

- **RabbitMQ**
  - Management UI: http://localhost:15672
  - Username: guest
  - Password: guest
  - AMQP Port: 5672
  - Management Port: 15672

## 📡 API Endpoints

### Product Service (`/api/products`)
- `GET /` - List all products
- `GET /{id}` - Get product by ID
- `POST /` - Create new product
- `PUT /{id}` - Update product
- `DELETE /{id}` - Delete product

### Price Service (`/api/prices`)
- `GET /average` - Get average product price
- `GET /most-expensive` - Get most expensive product
- `POST /calculate` - Calculate price with discounts

### Notification Service (`/api/notifications`)
- `POST /subscribe` - Subscribe to notifications
- `POST /notify` - Send notification

## 🎯 Design Patterns & Methodologies

### Architecture Patterns
- **Microservices Architecture**
- **Event-Driven Architecture** using RabbitMQ
- **API Gateway Pattern** using Nginx
- **Circuit Breaker** for service resilience

### Design Patterns
- **Repository Pattern** - Data access abstraction
- **Factory Pattern** - Object creation
- **Builder Pattern** - Complex object construction
- **Strategy Pattern** - Price calculation strategies
- **Observer Pattern** - Notification system

### Development Methodologies
- **Domain-Driven Design (DDD)**
- **Test-Driven Development (TDD)**
- **SOLID Principles**
- **Clean Architecture**
- **12-Factor App** methodology

## 🧪 Testing

### Backend Testing
```bash
./mvnw clean test
```

### Frontend Testing
```bash
npm test
```

## 📦 Project Structure

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

## 🌐 Environment Variables

### Frontend
- `VITE_API_URL=/api`
- `VITE_PRICE_API_URL=/api/prices`

### Backend
- `SPRING_PROFILES_ACTIVE=prod`
- `POSTGRES_HOST=postgres`
- `RABBITMQ_HOST=rabbitmq`

## 🔒 Security

- CORS configuration
- Input validation
- Rate limiting
- Security headers
- HTTPS ready

## 🌟 Features

- Responsive design
- Real-time price updates
- Async notification system
- Comprehensive error handling
- Detailed logging
- Performance monitoring
- Database migrations
- API documentation

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.
