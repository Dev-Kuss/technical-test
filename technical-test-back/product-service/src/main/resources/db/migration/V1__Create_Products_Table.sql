CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    product_type VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    on_sale BOOLEAN DEFAULT false,
    weight DECIMAL(10,2),
    size_mb INTEGER,
    dtype VARCHAR(31) NOT NULL
);
