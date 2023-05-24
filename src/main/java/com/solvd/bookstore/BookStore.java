package com.solvd.bookstore;

import com.solvd.bookstore.entities.*;
import com.solvd.bookstore.exceptions.*;
import com.solvd.bookstore.interfaces.IBookFilter;
import com.solvd.bookstore.interfaces.IPaymentMethod;
import com.solvd.bookstore.linkedList.UsersLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;

public class BookStore {

   private static final Logger logger = LogManager.getLogger(BookStore.class);

   private final UsersLinkedList<User> users = new UsersLinkedList<>();
   private final Inventory inventory = new Inventory();
   private final ArrayList<Order> orders = new ArrayList<>();

   public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

      BookStore bookstore = new BookStore();

      Address address1 = new Address("123 North Street", "New York", "NY", "74644", "USA");
      Admin admin1 = new Admin("admin2", "admin2@gmail.com", "username", "a2");
      Address address2 = new Address("321 South Street", "Los Angeles", "CA", "84743", "USA");
      Customer customer2 = new Customer("customer2", "customer2@gmail.com", "username", "c2", address2);
      bookstore.users.addFirst(new Admin("admin", "admin@gmail.com", "username", "a"));
      bookstore.users.addLast(new Customer("customer", "customer@gmail.com", "username", "c", address1));
      bookstore.users.insertAt(1, admin1);
      bookstore.users.insertAt(2, customer2);

      logger.info("Welcome to the Bookstore!");
      logger.info("");

      bookstore.showMainMenu();
   }

   public void showMainMenu() {

      logger.info("You are now in the main menu.");
      logger.info("");
      logger.info("Are you a customer or an admin?");
      logger.info("1. Customer");
      logger.info("2. Admin");

      Scanner scanner = new Scanner(System.in);

      try {
         String selection = scanner.nextLine();

         if (selection.equals("1") || selection.equalsIgnoreCase("customer")) {
            showCustomerLoginMenu();
         } else if (selection.equals("2") || selection.equalsIgnoreCase("admin")) {
            showAdminLoginMenu();
         } else {
            logger.error("You are only allowed to enter 1 and 2");
         }
      } catch (Exception e) {
         logger.error("An error occurred: " + e.getMessage());
      }
   }

   public void showCustomerLoginMenu() throws UserNotFoundException, InvalidIsbnException, InvalidUsernameException, InvalidOptionException, InvalidShippingMethodException {

      logger.info("Do you want to login or sign up?");
      logger.info("1. Login");
      logger.info("2. Sign up");

      Scanner scanner = new Scanner(System.in);
      String selection = scanner.nextLine();

      if (selection.equals("1") || selection.equals("login")) {
         logger.info("What is your username");
         String username = scanner.nextLine();
         logger.info("What is your password");
         String password = scanner.nextLine();

         User user = User.login(username, password, users);

         if (user == null) {
            throw new UserNotFoundException("User was not found");
         } else if (user instanceof Customer customer) {
            showCustomerMenu(customer);
            return;
         } else {
            logger.error("You are trying to login as an customer");
            logger.info("Try to login as a admin");
            logger.info("");
            showAdminLoginMenu();
         }
         showCustomerLoginMenu();

      } else if (selection.equals("2") || selection.equalsIgnoreCase("sign up")) {
         users.addLast(Customer.createCustomer());
      }

      logger.info("Users:" + users);

      Iterator<User> iterator = users.iterator();
      while (iterator.hasNext()) {
         User user = iterator.next();
         logger.info(user.getPassword());
      }
   }

   public void showAdminLoginMenu() throws UserNotFoundException, InvalidIsbnException, InvalidUsernameException, InvalidOptionException, InvalidShippingMethodException {

      Scanner scanner = new Scanner(System.in);

      logger.info("You are in the admin login menu");

      logger.info("Type in 0 as your username to go to the main menu!");

      logger.info("What is your username?");
      String username = scanner.next();
      logger.info("What is your password?");
      String password = scanner.next();
      User user = User.login(username, password, users);

      if (username.equalsIgnoreCase("0")) {
         showMainMenu();
      } else if (user == null) {
         throw new InvalidUsernameException("User was not found");
      } else if (user instanceof Admin) {
         showAdminMenu((Admin) user);
      } else {
         logger.info("You are trying to login as an administrator");
         logger.info("Try to login as a customer");
         logger.info("");
         showCustomerLoginMenu();
      }
      showAdminLoginMenu();
   }

   public void showCustomerMenu(Customer customer) throws InvalidIsbnException, InvalidOptionException, InvalidShippingMethodException {

      logger.info("");
      logger.info("");

      Scanner scanner = new Scanner(System.in);

      logger.info("Welcome " + customer.getName() + " to the Customer Menu");
      logger.info("Choose an option.");

      logger.info("1. Search for book by keyword");
      logger.info("2. Show all books available");
      logger.info("3. Show all books in the cart");
      logger.info("4. Show book by category");
      logger.info("5. Add book to cart");
      logger.info("6. Checkout");
      logger.info("7. Change Username");
      logger.info("8. Change Password");

      String selection = scanner.nextLine();

      switch (selection) {

         case "1" -> {
            logger.info("Enter a keyword to search for");
            String keyword = scanner.nextLine();

            IBookFilter<Book> keywordFilter = book -> book.searchByKeyword(keyword);

            inventory.getBooks().stream()
                    .filter(keywordFilter::test)
                    .forEach(System.out::println);
         }

      case "2" -> inventory.getBooks().forEach(System.out::println);
      case "3" -> showAllTheBooksInTheCart(customer);
      case "4" -> {
            logger.info("What category are you interested in?");
            Category category = Category.valueOf(scanner.nextLine());

         Predicate<Book> categoryFilter = book -> book.getCategory().getLabel().equalsIgnoreCase(category.getLabel());

         inventory.getBooks().stream()
                 .filter(categoryFilter)
                 .forEach(System.out::println);
         }

         case "5" -> {
         logger.info("What is the ISBN of the book?");
         String isbn = scanner.nextLine();

         Book selectedBook = Book.getBookByIsbn(isbn, inventory.getBooks());
         try {
            if (selectedBook == null) {
               throw new InvalidIsbnException("The isbn for the book you have provided is not in our inventory");
            } else {
               customer.getCart().addBook(selectedBook);
               logger.error(selectedBook.getTitle() + " was added to your cart!");
            }
         } catch (InvalidIsbnException e) {
            logger.error(e.getMessage());
            logger.error("Going back to the Main menu ...");
            showMainMenu();
         }
      }
      case "6" -> checkout(customer, scanner);
      case "7" -> changeUsername(customer);
      case "8" -> changePassword(customer);
      default -> throw new InvalidOptionException("Please only enter values from 1 to 6");
   }

   showMainMenu();

}
   private Category getCategoryByName(String categoryName) {
      return Arrays.stream(Category.values())
              .filter(category -> category.getLabel().equalsIgnoreCase(categoryName))
              .findFirst()
              .orElse(null);
   }

   private void changePassword(User user) {
      Scanner scanner = new Scanner(System.in);

      logger.info("What is your current password?");
      String password = scanner.nextLine();

      logger.info("What is your new password?");
      String newPassword = scanner.nextLine();

      boolean isSuccessFul = user.changePassword(password, newPassword);

      if (isSuccessFul) {
         logger.info("Your password has been changed to " + ("*".repeat(newPassword.length())));
      } else {
         logger.info("The password you have provided was wrong");
      }
   }

   private void changeUsername(User user) {
      Scanner scanner = new Scanner(System.in);

      logger.info("What is your current username?");
      String username = scanner.nextLine();

      logger.info("What is your new username?");
      String newUsername = scanner.nextLine();

      boolean isSuccessFul = user.changeUserName(username, newUsername);

      if (isSuccessFul) {
         logger.info("Your username has been changed to " + newUsername);
      } else {
         logger.info("The username you have provided was wrong");
      }
   }

   private void checkout(Customer customer, Scanner scanner) throws InvalidIsbnException, InvalidOptionException, InvalidShippingMethodException {
      logger.info("Subtotal $" + customer.getCart().getTotalPrice());

      logger.info("Are you sure you want to continue?");

      String choice = scanner.nextLine();

      if (choice.equalsIgnoreCase("yes")) {


         ShippingMethod shippingMethod = null;

         logger.info("What shipping method do you prefer?");
         logger.info("1. Express shipping($" + ShippingMethod.EXPRESS_DELIVERY.getCost() + ")");
         logger.info("2. Standard shipping($" + ShippingMethod.EXPRESS_DELIVERY.getCost() + ")");

         String answer = scanner.nextLine();
         if (answer.equals("1") || answer.equalsIgnoreCase("express")) {
            shippingMethod = ShippingMethod.EXPRESS_DELIVERY;
         } else if (answer.equals("2") || answer.equalsIgnoreCase("standard")) {
            shippingMethod = ShippingMethod.STANDARD_DELIVERY;
         }

         try {
            double shippingCost;
            if (shippingMethod == ShippingMethod.EXPRESS_DELIVERY) {
               shippingCost = ShippingMethod.EXPRESS_DELIVERY.getCost();
            } else if (shippingMethod == ShippingMethod.STANDARD_DELIVERY) {
               shippingCost = ShippingMethod.STANDARD_DELIVERY.getCost();
            } else {
               throw new InvalidShippingMethodException("You did not provide the correct information");
            }
            Shipping shipping = new Shipping(shippingCost, shippingMethod, customer.getAddress());

         logger.info("Do you have a coupon code?");
         String couponAnswer = scanner.nextLine();

            if (couponAnswer.equalsIgnoreCase("Yes")) {
               logger.info("What is the code of the coupon? ");
               String code = scanner.nextLine();

               if (code.equals(Coupon.OFF20.getCode())) {
                  customer.getCart().setCoupon(Coupon.OFF20);
               } else if (code.equals(Coupon.OFF30.getCode())) {
                  customer.getCart().setCoupon(Coupon.OFF30);
               } else {
                  logger.info("Invalid coupon code");
                  customer.getCart().setCoupon(null);
               }
            } else {
               customer.getCart().setCoupon(null);
            }

            BigDecimal shipCost = new BigDecimal(shippingCost);
         BigDecimal orderPrice = BigDecimal.valueOf(customer.getCart().getTotalPrice());

         BigDecimal sum = shipCost.add(orderPrice);

         double orderTotalPrice = sum.doubleValue();

         if (customer.getCart().getCoupon() != null) {
            orderTotalPrice = customer.getCart().getCoupon().calculateDiscount(orderTotalPrice);
            logger.info("Since you have a coupon your new price is " + orderTotalPrice);
         }

         IPaymentMethod paymentMethod = createPaymentMethod();

         ArrayList<Book> books = customer.getCart().getBooks();

         Order order = new Order(books, paymentMethod, shipping, orderTotalPrice);

         order.getPaymentMethod().pay(orderTotalPrice);

         orders.add(order);
         logger.info("Your order has been confirmed");
         logger.info("Thank you for your order!");
         logger.info("Here are you order details:");
         logger.info("Order:" + order);

         } catch (InvalidShippingMethodException e) {
            logger.error(e.getMessage());
            logger.info("Opening Customer Menu ...");
            showCustomerMenu(customer);
         }

         for (Book book : customer.getCart().getBooks()) {
            inventory.getBooks().remove(book);

         }
         customer.getCart().emptyCart();

      } else if (choice.equalsIgnoreCase("no")) {
         logger.info("Returning to the customer Menu ...");
         showCustomerMenu(customer);
      } else {
         logger.info("You can only enter yes or no.");
         showCustomerMenu(customer);
      }
   }


   private IPaymentMethod createPaymentMethod() {
      Scanner scanner = new Scanner(System.in);
      logger.info("What method do you want to use?");
      logger.info("1. Credit Card");
      logger.info("2. Paypal");
      String selection = scanner.nextLine();

      if (selection.equals("1")) {
         return createPaymentMethodInstance(PaymentMethod.CREDIT_CARD);
      } else if (selection.equals("2")) {
         return createPaymentMethodInstance(PaymentMethod.PAYPAL);
      } else {
         logger.error("Please only enter 1 or 2");
         logger.info("Try again ...");
         return createPaymentMethod();
      }
   }

   private IPaymentMethod createPaymentMethodInstance(PaymentMethod paymentMethod) {
      Class<? extends IPaymentMethod> paymentMethodClass = paymentMethod.getPaymentMethodClass();

      if (paymentMethodClass == Creditcard.class) {
         return createCreditCardPaymentMethod();
      } else if (paymentMethodClass == Paypal.class) {
         return createPaypalPaymentMethod();
      }

      return null;
   }

   private Paypal createPaypalPaymentMethod() {
      Scanner scanner = new Scanner(System.in);

      logger.info("What is your email address?");
      String email = scanner.nextLine();

      logger.info("What is your password?");
      String password = scanner.nextLine();

      return new Paypal(email, password);
   }

   private Creditcard createCreditCardPaymentMethod() {
      Scanner scanner = new Scanner(System.in);

      logger.info("What is your card number");
      String cardNumber = scanner.nextLine();

      logger.info("What is your expiration date?");
      String expirationDate = scanner.nextLine();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate date = LocalDate.parse(expirationDate, formatter);

      logger.info("What is your cvv?");
      String cvv = scanner.nextLine();

      return new Creditcard(cardNumber, date, cvv);
   }
   private static void showAllTheBooksInTheCart(Customer customer) {
      List<Book> booksInCart = customer.getCart().getBooks();

      booksInCart.stream()
              .filter(Objects::nonNull)
              .forEach(book -> logger.info(book.getTitle() + " : $" + book.getPrice()));
   }

   public void showAdminMenu(Admin admin) {
      Scanner scanner = new Scanner(System.in);

      logger.info("Welcome " + admin.getName() + " to the Admin Menu");
      logger.info("What do you want to do?");
      logger.info("1. Add a new book");
      logger.info("2. Delete a book");
      logger.info("3. Show all the books");
      logger.info("4. Show all the orders");
      logger.info("5. Change Username");
      logger.info("6. Change Password");
      String selection = scanner.nextLine();

      switch (selection) {
         case "1" -> {
            Book book = admin.createBook();
            this.inventory.addBook(book);
            logger.info("The book with the title " + book.getTitle() + " has been added to the inventory");

            showMainMenu();
         }
         case "2" -> {
            logger.info("What is the isbn of the book you want to delete?");
            String isbn = scanner.nextLine();

            Book book = Book.getBookByIsbn(isbn, inventory.getBooks());

            if (book == null) {
               logger.info("The book you want to delete is not in the inventory");
            } else {
               inventory.removeBook(book);
               logger.info(book.getTitle() + " has been removed");
            }
         }
         case "3" -> inventory.getBooks().forEach(System.out::println);
         case "4" -> orders.forEach(System.out::println);
         case "5" -> changeUsername(admin);
         case "6" -> changePassword(admin);
         default -> logger.error("Please only insert the numbers 1 to 4");
      }
     showMainMenu();
   }

}
