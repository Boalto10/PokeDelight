-- Create database and tables for PokeDelight (extended)
CREATE DATABASE IF NOT EXISTS pokedelight CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE pokedelight;

CREATE TABLE IF NOT EXISTS pokemon_menu (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  ptype VARCHAR(50),
  price DECIMAL(6,2) NOT NULL,
  description VARCHAR(500),
  image_path VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS orders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  customer_name VARCHAR(200),
  address VARCHAR(500),
  total DECIMAL(8,2),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_items (
  id INT AUTO_INCREMENT PRIMARY KEY,
  order_id INT,
  pokemon_id INT,
  quantity INT,
  price DECIMAL(6,2),
  FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
  FOREIGN KEY (pokemon_id) REFERENCES pokemon_menu(id) ON DELETE SET NULL
);

-- Sample data
INSERT INTO pokemon_menu (name, ptype, price, description, image_path) VALUES
('Pikachu Special Bowl', 'Electric', 19.90, 'Montadito com arroz, salmão grelhado e molho cítrico — energia garantida!', 'src/main/resources/images/item1.png'),
('Charizard Flame-Grilled', 'Fire/Flying', 29.50, 'Picanha grelhada com especiarias flamejantes e batatas rústicas.', 'src/main/resources/images/item2.png'),
('Bulbasaur Garden Salad', 'Grass/Poison', 12.00, 'Salada fresca com mix de folhas, molho de ervas e sementes crocantes.', 'src/main/resources/images/item3.png'),
('Squirtle Surf Tacos', 'Water', 14.75, 'Tacos com peixe empanado, repolho, e molho refrescante.', 'src/main/resources/images/item4.png'),
('Eevee Variety Platter', 'Normal', 24.00, 'Pequenos petiscos variados para compartilhar — escolha 3 sabores.', 'src/main/resources/images/item5.png'),
('Jigglypuff Dessert', 'Fairy', 8.50, 'Doces leves com chantilly e frutas.', 'src/main/resources/images/item6.png'),
('Gengar Spicy Stew', 'Ghost/Poison', 16.00, 'Ensopado picante com toque misterioso.', 'src/main/resources/images/item7.png'),
('Snorlax Mega Burger', 'Normal', 22.00, 'Hambúrguer duplo com queijo, bacon e fritas.', 'src/main/resources/images/item8.png'),
('Mewtwo Premium Sashimi', 'Psychic', 34.00, 'Seleção premium de sashimi fresco.', 'src/main/resources/images/item9.png'),
('Lapras Seafood Platter', 'Water/Ice', 28.00, 'Mariscos, camarões e peixes grelhados.', 'src/main/resources/images/item10.png'),
('Dragonite Roast', 'Dragon/Flying', 31.00, 'Assado suculento com temperos especiais.', 'src/main/resources/images/item11.png'),
('Magikarp Crispy Roll', 'Water', 9.50, 'Roll crocante ideal para petiscar.', 'src/main/resources/images/item12.png');
