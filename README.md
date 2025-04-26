### Это API, где осуществляются CRUD-операции на Spring Boot для работы с продуктами
### Работа с проектом:
1. Клонирование репозитория:
<pre class="language-bash"><code>git clone git@github.com:Forneus348/spring-products.git</code></pre>
2. Создание Docker-образа:
<pre class="language-bash"><code>docker run --name demo-db -e POSTGRES_PASSWORD=12345678 -p 5432:5432 -d postgres</code></pre>
3. Создание бд (я использовал pgAdmin):
    - Name: demo-db
    - Host name/address: 127.0.0.1
    - Maintenance database: postgres
    - Username: postgres
4. Создание таблицы:
<pre class="language-bash"><code>CREATE TABLE products (
    Id SERIAL PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Description TEXT
);
</code></pre>
5. Запуск проекта
