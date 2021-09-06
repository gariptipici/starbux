

INSERT INTO "PUBLIC"."CUSTOMER"
VALUES (4),
       (5);

INSERT INTO "PUBLIC"."CART"
VALUES (6, 54.00, 13.50, 4),
       (8, 54.00, 13.50, 5);

INSERT INTO "PUBLIC"."CART_ITEM"
VALUES (11, 18.00, 2, 6, 1),
       (12, 36.00, 3, 6, 2),
       (13, 18.00, 2, NULL, 1),
       (14, 36.00, 3, NULL, 2),
       (15, 18.00, 2, NULL, 1),
       (16, 36.00, 3, NULL, 2);

INSERT INTO "PUBLIC"."CART_ITEM_SIDE_PRODUCTS"
VALUES (11, 1),
       (12, 2),
       (13, 2),
       (14, 3),
       (15, 1),
       (16, 2);

INSERT INTO "PUBLIC"."CART_CART_ITEMS"
VALUES (6, 11),
       (6, 12),
       (8, 15),
       (8, 16);

INSERT INTO "PUBLIC"."ORDERR"
VALUES (3, 54.00, 13.50, 5);

INSERT INTO "PUBLIC"."ORDERR_CART_ITEMS"
VALUES (3, 15),
       (3, 16);









