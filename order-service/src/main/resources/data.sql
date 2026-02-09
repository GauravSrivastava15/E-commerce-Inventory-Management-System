INSERT INTO orders (total_price, order_status) VALUES
(12999, 'CONFIRMED'),
(45999, 'PENDING'),
(7999, 'CONFIRMED'),
(23999, 'CANCELLED'),
(15999, 'PENDING'),
(8999, 'CONFIRMED'),
(34999, 'CONFIRMED'),
(21999, 'PENDING'),
(4999, 'CANCELLED'),
(27999, 'CONFIRMED');

INSERT INTO order_item(order_id, product_id, quantity) VALUES
(1, 102, 2),
(1, 102, 1),
(2, 103, 1),
(2, 104, 3),
(3, 105, 1),
(3, 106, 2),
(4, 107, 2),
(5, 108, 3),
(6, 109, 2),
(7, 110, 1),
(8, 111, 2),
(9, 112, 3),
(10, 113, 2);