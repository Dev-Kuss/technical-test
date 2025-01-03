-- Drop the existing sequence and create a new bigint sequence
DROP SEQUENCE IF EXISTS products_id_seq CASCADE;
CREATE SEQUENCE products_id_seq AS BIGINT;

-- Alter the id column to use bigint and the new sequence
ALTER TABLE products ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE products ALTER COLUMN id SET DEFAULT nextval('products_id_seq');
ALTER SEQUENCE products_id_seq OWNED BY products.id;
