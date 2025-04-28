package ru.omgtu.factory;

import ru.omgtu.model.Gun;
import ru.omgtu.model.ProductStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GunFactory {
    public static List<Gun> createInitialGuns() {
        List<Gun> guns = new ArrayList<>();
        
        guns.add(new Gun(1L, new BigDecimal("29999.99"), "M4A1", "Автомат M4A1",
                ProductStatus.AVAILABLE, "M4A1", "Tokyo Marui", 
                "https://images.stopgame.ru/uploads/users/2021/315558/00771.4uOmuax.jpg"));
        
        guns.add(new Gun(2L, new BigDecimal("34999.99"), "AK-47", "Автомат Калашникова",
                ProductStatus.AVAILABLE, "AK-47", "CYMA", 
                "https://avatars.mds.yandex.net/i?id=bbe794a037c81c3d969583fb1a112c64_l-5333586-images-thumbs&n=13"));
        
        return guns;
    }
} 