package ru.netology.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RadioTest {

    // Тесты конструкторов
    @Test
    public void shouldCreateDefaultRadio() {
        Radio radio = new Radio();
        assertEquals(9, radio.getMaxStation());
        assertEquals(0, radio.getCurrentStation());
        assertEquals(0, radio.getCurrentVolume());
    }

    @Test
    public void shouldCreateCustomRadio() {
        Radio radio = new Radio(15);
        assertEquals(14, radio.getMaxStation());
    }

    @Test
    public void shouldHandleInvalidStationCount() {
        Radio radio = new Radio(-5);
        assertEquals(9, radio.getMaxStation());
    }

    @Test
    public void shouldHandleZeroStationCount() {
        Radio radio = new Radio(0);
        assertEquals(9, radio.getMaxStation());
    }

    // Тесты для setCurrentStation - полное покрытие
    @Test
    public void shouldSetValidStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(5);
        assertEquals(5, radio.getCurrentStation());
    }

    @Test
    public void shouldNotSetStationBelowMin() {
        Radio radio = new Radio();
        radio.setCurrentStation(2); // устанавливаем валидное значение
        radio.setCurrentStation(-1); // пытаемся установить невалидное
        assertEquals(2, radio.getCurrentStation()); // должно остаться предыдущее значение
    }

    @Test
    public void shouldNotSetStationAboveMax() {
        Radio radio = new Radio();
        radio.setCurrentStation(2); // устанавливаем валидное значение
        radio.setCurrentStation(15); // пытаемся установить невалидное
        assertEquals(2, radio.getCurrentStation()); // должно остаться предыдущее значение
    }

    @Test
    public void shouldSetStationAtMinBoundary() {
        Radio radio = new Radio();
        radio.setCurrentStation(0);
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    public void shouldSetStationAtMaxBoundary() {
        Radio radio = new Radio();
        radio.setCurrentStation(9);
        assertEquals(9, radio.getCurrentStation());
    }

    @Test
    public void shouldNotSetStationJustAboveMax() {
        Radio radio = new Radio();
        radio.setCurrentStation(5);
        radio.setCurrentStation(10);
        assertEquals(5, radio.getCurrentStation());
    }

    // Тесты для setCurrentVolume - полное покрытие
    @Test
    public void shouldSetValidVolume() {
        Radio radio = new Radio();
        radio.setCurrentVolume(50);
        assertEquals(50, radio.getCurrentVolume());
    }

    @Test
    public void shouldNotSetVolumeBelowMin() {
        Radio radio = new Radio();
        radio.setCurrentVolume(30);
        radio.setCurrentVolume(-1);
        assertEquals(30, radio.getCurrentVolume());
    }

    @Test
    public void shouldNotSetVolumeAboveMax() {
        Radio radio = new Radio();
        radio.setCurrentVolume(30);
        radio.setCurrentVolume(101);
        assertEquals(30, radio.getCurrentVolume());
    }

    @Test
    public void shouldSetVolumeAtMinBoundary() {
        Radio radio = new Radio();
        radio.setCurrentVolume(0);
        assertEquals(0, radio.getCurrentVolume());
    }

    @Test
    public void shouldSetVolumeAtMaxBoundary() {
        Radio radio = new Radio();
        radio.setCurrentVolume(100);
        assertEquals(100, radio.getCurrentVolume());
    }

    @Test
    public void shouldNotSetVolumeJustAboveMax() {
        Radio radio = new Radio();
        radio.setCurrentVolume(50);
        radio.setCurrentVolume(101);
        assertEquals(50, radio.getCurrentVolume());
    }

    // Тесты переключения станций
    @Test
    public void shouldSwitchToNextStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(5);
        radio.nextStation();
        assertEquals(6, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchFromMaxToFirstStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(9);
        radio.nextStation();
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchToPrevStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(5);
        radio.prevStation();
        assertEquals(4, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchFromFirstToLastStation() {
        Radio radio = new Radio();
        radio.setCurrentStation(0);
        radio.prevStation();
        assertEquals(9, radio.getCurrentStation());
    }

    // Тесты работы с громкостью
    @Test
    public void shouldIncreaseVolume() {
        Radio radio = new Radio();
        radio.setCurrentVolume(50);
        radio.increaseVolume();
        assertEquals(51, radio.getCurrentVolume());
    }

    @Test
    public void shouldNotIncreaseVolumeAboveMax() {
        Radio radio = new Radio();
        radio.setCurrentVolume(100);
        radio.increaseVolume();
        assertEquals(100, radio.getCurrentVolume());
    }

    @Test
    public void shouldDecreaseVolume() {
        Radio radio = new Radio();
        radio.setCurrentVolume(50);
        radio.decreaseVolume();
        assertEquals(49, radio.getCurrentVolume());
    }

    @Test
    public void shouldNotDecreaseVolumeBelowMin() {
        Radio radio = new Radio();
        radio.setCurrentVolume(0);
        radio.decreaseVolume();
        assertEquals(0, radio.getCurrentVolume());
    }

    // Тесты для пользовательского количества станций
    @Test
    public void shouldWorkWithCustomStations() {
        Radio radio = new Radio(5);
        assertEquals(4, radio.getMaxStation());

        radio.setCurrentStation(4);
        radio.nextStation();
        assertEquals(0, radio.getCurrentStation());

        radio.setCurrentStation(0);
        radio.prevStation();
        assertEquals(4, radio.getCurrentStation());
    }

    @Test
    public void shouldHandleStationSettingWithCustomMax() {
        Radio radio = new Radio(5);

        // Валидные значения
        radio.setCurrentStation(0);
        assertEquals(0, radio.getCurrentStation());

        radio.setCurrentStation(4);
        assertEquals(4, radio.getCurrentStation());

        // Невалидные значения
        radio.setCurrentStation(2);
        radio.setCurrentStation(-1);
        assertEquals(2, radio.getCurrentStation());

        radio.setCurrentStation(5);
        assertEquals(2, radio.getCurrentStation());
    }
}