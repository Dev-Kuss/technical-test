-- Physical Products
INSERT INTO products (product_type, name, price, on_sale, weight, dtype) VALUES
('PHYSICAL', 'Gaming Laptop', 1299.99, true, 2.5, 'PHYSICAL'),
('PHYSICAL', 'Mechanical Keyboard', 129.99, false, 1.2, 'PHYSICAL'),
('PHYSICAL', 'Wireless Mouse', 49.99, true, 0.3, 'PHYSICAL'),
('PHYSICAL', 'Gaming Monitor', 399.99, false, 5.0, 'PHYSICAL'),
('PHYSICAL', 'Gaming Chair', 299.99, true, 15.0, 'PHYSICAL'),
('PHYSICAL', 'USB Microphone', 89.99, false, 0.8, 'PHYSICAL'),
('PHYSICAL', 'Webcam HD', 79.99, true, 0.4, 'PHYSICAL'),
('PHYSICAL', 'External SSD', 159.99, false, 0.2, 'PHYSICAL'),
('PHYSICAL', 'Graphics Card', 699.99, true, 1.5, 'PHYSICAL'),
('PHYSICAL', 'Gaming Headset', 99.99, false, 0.6, 'PHYSICAL');

-- Digital Products
INSERT INTO products (product_type, name, price, on_sale, size_mb, dtype) VALUES
('DIGITAL', 'Antivirus Software', 49.99, true, 250.0, 'DIGITAL'),
('DIGITAL', 'Video Editing Software', 199.99, false, 1500.0, 'DIGITAL'),
('DIGITAL', 'Game - RPG Adventure', 59.99, true, 35000.0, 'DIGITAL'),
('DIGITAL', 'Photo Editor Pro', 149.99, false, 800.0, 'DIGITAL'),
('DIGITAL', 'Music Production Suite', 299.99, true, 5000.0, 'DIGITAL'),
('DIGITAL', 'Office Software Pack', 249.99, false, 3000.0, 'DIGITAL'),
('DIGITAL', 'Programming IDE', 89.99, true, 750.0, 'DIGITAL'),
('DIGITAL', 'Game - Racing Simulator', 49.99, false, 25000.0, 'DIGITAL'),
('DIGITAL', 'Cloud Storage 1TB', 9.99, true, 0.0, 'DIGITAL'),
('DIGITAL', 'Password Manager', 29.99, false, 100.0, 'DIGITAL');
