
CREATE DATABASE IF NOT EXISTS airsoftshop;
USE airsoftshop;

CREATE TABLE IF NOT EXISTS guns (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    model VARCHAR(255) NOT NULL,
    producer VARCHAR(255) NOT NULL,
    img VARCHAR(255)
);

INSERT INTO guns (name, description, price, status, model, producer, img) VALUES
('AK-47', 'Классическая винтовка с цельнометаллической конструкцией', 299.99, 'AVAILABLE', 'AK-47', 'CYMA', 'https://images.stopgame.ru/uploads/users/2021/315558/00771.4uOmuax.jpg'),
('M4A1', 'Современная тактическая винтовка с регулируемым прикладом', 349.99, 'AVAILABLE', 'M4A1', 'G&G', 'https://avatars.mds.yandex.net/i?id=bbe794a037c81c3d969583fb1a112c64_l-5333586-images-thumbs&n=13');