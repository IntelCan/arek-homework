DROP TABLE IF EXISTS SHOPPING_LISTS, PRODUCTS;

CREATE TABLE SHOPPING_LISTS (
  id             UUID DEFAULT random_uuid() PRIMARY KEY,
  name           VARCHAR(100)               UNIQUE,
);

CREATE TABLE PRODUCTS (
  id                 UUID DEFAULT random_uuid() PRIMARY KEY,
  name               VARCHAR(100) NOT NULL,
  amount             NUMERIC      NOT NULL,
  unit               VARCHAR(48) NOT NULL,
  is_checked         BOOLEAN      NOT NULL,
  shopping_list_id   UUID         NOT NULL,
);

ALTER TABLE PRODUCTS
ADD CONSTRAINT fk_products_list FOREIGN KEY (shopping_list_id) REFERENCES SHOPPING_LISTS (id);
