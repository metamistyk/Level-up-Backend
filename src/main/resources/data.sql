-- SE RESETEA TODO EN ORDEN CORRECTO DE LLAVES FORÁNEAS

-- 1) Borrar items del carrito (dependen de carts y de products)
DELETE FROM cart_items;

-- 2) Borrar carritos
DELETE FROM carts;

-- 3) Borrar productos
DELETE FROM products;

-- 4) Reset de IDs
ALTER TABLE cart_items AUTO_INCREMENT = 1;
ALTER TABLE carts AUTO_INCREMENT = 1;
ALTER TABLE products AUTO_INCREMENT = 1;

-- 1. Audífonos HyperX Cloud II
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Audifonos HYPERX Cloud II',
    'Audífonos gamer over-ear con sonido envolvente virtual 7.1 y micrófono desmontable.',
    90000,
    'audifonos.png',
    FALSE
);

-- 2. Carcassonne
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Carcassonne',
    'Juego de mesa estratégico para 2 a 5 jugadores basado en colocación de losetas.',
    29990,
    'carca1.jpg',
    FALSE
);

-- 3. Catan
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Catan',
    'Juego de estrategia familiar que mezcla comercio y administración de recursos para colonizar terrenos.',
    35000,
    'catan.webp',
    FALSE
);

-- 4. Joystick Xbox
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Joystick Xbox',
    'Control inalámbrico oficial de Xbox con diseño ergonómico y conectividad Bluetooth.',
    97235,
    'control-xbox.png',
    FALSE
);

-- 5. Mouse Gamer Logitech G502 Hero
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Mouse Gamer Logitech G502 Hero',
    'Mouse gamer con sensor HERO 25.600 DPI, 11 botones programables y retroiluminación RGB.',
    53990,
    'mouse.png',
    FALSE
);

-- 6. Mousepad
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Mousepad',
    'Alfombrilla de mouse que mejora precisión, control y deslizamiento.',
    15000,
    'mouspad.png',
    FALSE
);

-- 7. CPU ASUS ROG
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'CPU ASUS ROG',
    'Computador de escritorio gamer de alto rendimiento con diseño ROG.',
    1600000,
    'pc.png',
    TRUE
);

-- 8. Polera Level-Up
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Polera Level-Up!',
    'Polera con estampado oficial de la tienda Level-Up Gamer.',
    10990,
    'polera.png',
    FALSE
);

-- 9. PlayStation 5
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'PlayStation 5',
    'Consola de nueva generación con gráficos 4K, SSD ultra rápido y retrocompatibilidad.',
    634990,
    'ps5.png',
    TRUE
);

-- 10. Silla Gamer Secret Lab TITAN Evo 2022
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Silla Gamer Secret Lab TITAN Evo 2022',
    'Silla ergonómica premium diseñada para largas sesiones de juego.',
    975073,
    'silla.png',
    FALSE
);

-- 11. Nintendo Switch
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Nintendo Switch',
    'Consola híbrida de Nintendo compatible con modo portátil y sobremesa.',
    599990,
    'nintendoSwitch.png',
    FALSE
);

-- 12. Xbox Series X
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Xbox Series X',
    'Consola de nueva generación de Microsoft con 1TB SSD y soporte 4K 120Hz.',
    459990,
    'seriesX.png',
    FALSE
);

-- 13. Luces LED
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Luces LED',
    'Sistema de iluminación RGB con múltiples modos y compatibilidad con asistentes inteligentes.',
    5990,
    'luces.png',
    FALSE
);

-- 14. Monitor Gamer Xiaomi G34WQi
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Monitor Gamer Xiaomi G34WQi',
    'Monitor ultrawide de 34" WQHD, 180Hz, 1ms y compatible con FreeSync Premium.',
    243750,
    'monitor_xiaomi.png',
    TRUE
);

-- 15. Parlantes Gamer Targa Diamond Pro
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Kit parlantes gamer pc Diamond Pro Targa',
    'Parlantes gamer 2.0 con subwoofer, luces LED y controles de bajos/agudos.',
    40990,
    'parlantesGamer.png',
    FALSE
);

-- 16. Teclado Gamer Monster Punisher
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Teclado gamer Monster Punisher',
    'Teclado mecánico 80% con switches Blue y 9 modos de iluminación RGB.',
    24990,
    'tecladog.png',
    FALSE
);

-- 17. Notebook Nitro 5
INSERT INTO products (name, description, price, image_url, destacado)
VALUES (
    'Notebook Acer Nitro 5',
    'Notebook gamer con CPU Intel/AMD, GPU Nvidia y pantalla 15.6" 144Hz.',
    799990,
    'nitro5.png',
    FALSE
);
