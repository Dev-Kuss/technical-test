# Build stage
FROM node:18-alpine AS builder

# Set working directory
WORKDIR /app

# Install dependencies first (better caching)
COPY package*.json ./

# Instalar dependências globais necessárias
RUN npm install -g typescript vite @vitejs/plugin-react

# Instalar dependências do projeto
RUN npm install --legacy-peer-deps

# Copy source files
COPY . .

# Set environment variables for build
ENV NODE_ENV=production
ENV VITE_API_URL=/api
ENV VITE_PRICE_API_URL=/prices

# Build the application
RUN npm run build

# Production stage
FROM nginx:alpine

# Copy nginx configuration
COPY nginx.conf /etc/nginx/nginx.conf

# Copy built files from builder stage
COPY --from=builder /app/dist /usr/share/nginx/html

# Add healthcheck
HEALTHCHECK --interval=30s --timeout=3s \
    CMD wget -q --spider http://localhost/ || exit 1

# Expose port
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
