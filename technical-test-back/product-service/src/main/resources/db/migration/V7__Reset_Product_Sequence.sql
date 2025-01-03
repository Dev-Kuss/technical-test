-- Get the current maximum ID
SELECT setval('products_id_seq', (SELECT MAX(id) FROM products));
