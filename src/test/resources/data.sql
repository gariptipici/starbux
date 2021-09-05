INSERT INTO "PUBLIC"."CUSTOMER"
VALUES (2),
       (3);

INSERT INTO "PUBLIC"."CART"
VALUES (3, 54.00, 13.50, 2),
       (5, 54.00, 13.50, 3);

INSERT INTO "PUBLIC"."CART_ITEM"
VALUES (5, 18.00, 2, 3, 1),
       (6, 36.00, 3, 3, 2),
       (7, 18.00, 2, NULL, 1),
       (8, 36.00, 3, NULL, 2),
       (9, 18.00, 2, NULL, 1),
       (10, 36.00, 3, NULL, 2);

INSERT INTO "PUBLIC"."CART_ITEM_SIDE_PRODUCTS"
VALUES (5, 1),
       (5, 2),
       (6, 2),
       (6, 3),
       (7, 1),
       (7, 2),
       (8, 2),
       (8, 3),
       (9, 1),
       (9, 2),
       (10, 2),
       (10, 3);

INSERT INTO "PUBLIC"."CART_CART_ITEMS"
VALUES (3, 5),
       (3, 6),
       (5, 9),
       (5, 10);

INSERT INTO "PUBLIC"."ORDERR"
VALUES (2, 54.00, 13.50, 3);

INSERT INTO "PUBLIC"."ORDERR_CART_ITEMS"
VALUES (2, 7),
       (2, 8);









