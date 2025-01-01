-- Physical Products
INSERT INTO products (name, price, on_sale, weight, product_type) VALUES
('Gaming Laptop', 1299.99, true, 2.5, 'PHYSICAL'),
('Mechanical Keyboard', 129.99, false, 1.2, 'PHYSICAL'),
('Wireless Mouse', 49.99, true, 0.3, 'PHYSICAL'),
('Gaming Monitor', 399.99, false, 5.0, 'PHYSICAL'),
('Gaming Chair', 299.99, true, 15.0, 'PHYSICAL'),
('USB Microphone', 89.99, false, 0.8, 'PHYSICAL'),
('Webcam HD', 79.99, true, 0.4, 'PHYSICAL'),
('External SSD', 159.99, false, 0.2, 'PHYSICAL'),
('Graphics Card', 699.99, true, 1.5, 'PHYSICAL'),
('Gaming Headset', 99.99, false, 0.6, 'PHYSICAL');

-- Digital Products
INSERT INTO products (name, price, on_sale, size_mb, product_type) VALUES
('Antivirus Software', 49.99, true, 250, 'DIGITAL'),
('Video Editing Software', 199.99, false, 1500, 'DIGITAL'),
('Game - RPG Adventure', 59.99, true, 35000, 'DIGITAL'),
('Photo Editor Pro', 149.99, false, 800, 'DIGITAL'),
('Music Production Suite', 299.99, true, 5000, 'DIGITAL'),
('Office Software Package', 129.99, false, 3000, 'DIGITAL'),
('Programming IDE', 89.99, true, 750, 'DIGITAL'),
('Game - Racing Simulator', 49.99, false, 25000, 'DIGITAL'),
('Cloud Storage 1TB', 9.99, true, 0, 'DIGITAL'),
('Password Manager', 29.99, false, 100, 'DIGITAL');
