Starbux Coffee - Backend API
We need a backend for our online coffee place startup, starbux coffee, where users can order drinks/toppings and admins can create/update/delete drinks/toppings and have access to reports.
Functional Requirements
● Develop an API that will be used to order drinks with any of the topping combinations.
● Visitor journeys should be transparent, the current amount of the cart and the products
should be communicated back to the caller of the API.
● When finalizing the order, the original amount and the discounted amount should be
communicated back to the caller of the API.
● Reports are present with the criteria suggested in the admin API requirements.
Drinks:
● Black Coffee - 4 eur
● Latte - 5 eur
● Mocha - 6 eur
● Tea-3eur
Toppings/sides:
● Milk-2eur
● Hazelnut syrup - 3 eur
● Chocolate sauce - 5 eur
● Lemon - 2 eur
Discount logic:
● If the total of the cart is more than 12 euros, there should be a 25% discount.
● If there are 3 or more drinks in the cart, the one with the lowest amount (including
toppings) should be free.
● If the cart is eligible for both promotions, the promotion with the lowest cart amount
should be used and the other one should be ignored.
Admin api:
● Should be able to create/update/delete products and toppings. ● Reports:
○ Total amount of the orders per customer.
○ Most used toppings for drinks.
Other requirements:
● Java 8+, spring boot, (any other library you need, i.e H2 database)
● Test coverage > 70%
● Logging
● All endpoints should follow the REST guidelines.
● No extra configuration should be needed to spin up the application.
Bonus points:
● Dockerized application
● Cloud deployment (AWS, heroku etc.)
● Integration tests
● API Documentation
