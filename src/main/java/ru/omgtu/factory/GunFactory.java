package ru.omgtu.factory;

import java.util.List;

public class GunFactory {
    public static List<String> createInitialGuns() {
        return List.of(
            "{\"id\":1,\"price\":29999.99,\"name\":\"M4A1\",\"description\":\"Автомат M4A1\",\"status\":\"AVAILABLE\",\"model\":\"M4A1\",\"producer\":\"Tokyo Marui\",\"img\":\"https://images.stopgame.ru/uploads/users/2021/315558/00771.4uOmuax.jpg\"}",
            "{\"id\":2,\"price\":34999.99,\"name\":\"AK-47\",\"description\":\"Автомат Калашникова\",\"status\":\"AVAILABLE\",\"model\":\"AK-47\",\"producer\":\"CYMA\",\"img\":\"https://avatars.mds.yandex.net/i?id=bbe794a037c81c3d969583fb1a112c64_l-5333586-images-thumbs&n=13\"}"
        );
    }
} 