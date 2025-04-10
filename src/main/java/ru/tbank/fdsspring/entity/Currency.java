package ru.tbank.fdsspring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "currency")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE currency SET is_deleted = true WHERE id=?")  // Для soft delete
@Where(clause = "is_deleted = false")  // Автоматическая фильтрация удалённых
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название валюты обязательно")
    @Size(max = 50, message = "Название не должно превышать 50 символов")
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Базовая валюта обязательна")
    @Size(min = 3, max = 3, message = "Код валюты должен состоять из 3 символов")
    @Column(name = "base_currency", nullable = false, length = 3)
    private String baseCurrency = "RUB";

    @NotBlank(message = "Диапазон изменения цены обязателен")
    @Pattern(regexp = "^[+-]\\d+%/[+-]\\d+%$", message = "Формат: '+10%/-5%'")
    @Column(name = "price_change_range", nullable = false, length = 20)
    private String priceChangeRange;

    @Size(max = 255, message = "Описание не должно превышать 255 символов")
    private String description;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}