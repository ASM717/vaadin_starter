package com.example.demo.ui;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
@Route("products")
@PageTitle("Магазин продуктов")
public class ProductUI extends VerticalLayout {

    private final ProductService productService;
    Grid<Product> grid = new Grid<>(Product.class);

    TextField nameField = new TextField("Название продукта");
    TextField priceField = new TextField("Цена");

    @Autowired
    public ProductUI(ProductService productService) {
        this.productService = productService;

        // Устанавливаем данные в таблицу
        grid.setItems(productService.getAllProducts());

        // Создаем форму для ввода данных о новом продукте
        FormLayout formLayout = new FormLayout(nameField, priceField);

        // Создаем кнопку для добавления нового продукта
        Button addButton = new Button("Добавить продукт", event -> addProduct());

        // Добавляем компоненты на страницу
        HorizontalLayout header = new HorizontalLayout(addButton);
        header.setAlignItems(FlexComponent.Alignment.BASELINE);

        add(formLayout, header, grid);
    }

    private void addProduct() {
        // Получаем данные из текстовых полей
        String productName = nameField.getValue();
        String priceText = priceField.getValue();

        // Проверяем, что поля не пусты
        if (productName.isEmpty() || priceText.isEmpty()) {
            showNotification("Пожалуйста, заполните все поля");
            return;
        }

        try {
            // Преобразуем текстовое значение цены в Double
            Double productPrice = Double.parseDouble(priceText);

            // Создаем новый объект Product
            Product newProduct = new Product();
            newProduct.setName(productName);
            newProduct.setPrice(productPrice);

            // Вызываем метод addProduct из ProductService
            productService.addProduct(newProduct);

            // Обновление интерфейса Vaadin
            refreshGrid();
            showNotification("Продукт добавлен");
        } catch (NumberFormatException e) {
            showNotification("Введите корректное значение для цены");
        }
    }

    private void refreshGrid() {
        // Обновление данных в таблице
        grid.setItems(productService.getAllProducts());
    }

    private void showNotification(String message) {
        Notification notification = new Notification(message, 3000, Notification.Position.BOTTOM_START);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.open();
    }
}

