-- Physical Products
INSERT INTO products (product_type, name, price, on_sale, weight, dtype) VALUES
('PhysicalProduct', 'Gaming Laptop', 1299.99, true, 2.5, 'PhysicalProduct'),
('PhysicalProduct', 'Mechanical Keyboard', 129.99, false, 1.2, 'PhysicalProduct'),
('PhysicalProduct', 'Wireless Mouse', 49.99, true, 0.3, 'PhysicalProduct'),
('PhysicalProduct', 'Gaming Monitor', 399.99, false, 5.0, 'PhysicalProduct'),
('PhysicalProduct', 'Gaming Chair', 299.99, true, 15.0, 'PhysicalProduct'),
('PhysicalProduct', 'USB Microphone', 89.99, false, 0.8, 'PhysicalProduct'),
('PhysicalProduct', 'Webcam HD', 79.99, true, 0.4, 'PhysicalProduct'),
('PhysicalProduct', 'External SSD', 159.99, false, 0.2, 'PhysicalProduct'),
('PhysicalProduct', 'Graphics Card', 699.99, true, 1.5, 'PhysicalProduct'),
('PhysicalProduct', 'Gaming Headset', 99.99, false, 0.6, 'PhysicalProduct');

-- Digital Products
INSERT INTO products (product_type, name, price, on_sale, size_mb, dtype) VALUES
('DigitalProduct', 'Antivirus Software', 49.99, true, 250, 'DigitalProduct'),
('DigitalProduct', 'Video Editing Software', 199.99, false, 1500, 'DigitalProduct'),
('DigitalProduct', 'Game - RPG Adventure', 59.99, true, 35000, 'DigitalProduct'),
('DigitalProduct', 'Photo Editor Pro', 149.99, false, 800, 'DigitalProduct'),
('DigitalProduct', 'Music Production Suite', 299.99, true, 5000, 'DigitalProduct'),
('DigitalProduct', 'Office Software Package', 129.99, false, 3000, 'DigitalProduct'),
('DigitalProduct', 'Programming IDE', 89.99, true, 750, 'DigitalProduct'),
('DigitalProduct', 'Game - Racing Simulator', 49.99, false, 25000, 'DigitalProduct'),
('DigitalProduct', 'Cloud Storage 1TB', 9.99, true, 0, 'DigitalProduct'),
('DigitalProduct', 'Password Manager', 29.99, false, 100, 'DigitalProduct');
