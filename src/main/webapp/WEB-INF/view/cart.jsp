<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your Shopping Cart</title>
</head>
<body>
    <h1>Your Cart</h1>
    <c:if test="${not empty cartItems}">
        <table border="1">
            <tr>
                <th>Product</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Subtotal</th>
                <th>Action</th>
            </tr>
            <c:forEach var="item" items="${cartItems}">
                <tr>
                    <td>${item.product.name}</td>
                    <td>${item.quantity}</td>
                    <td>$${item.product.price}</td>
                    <td>$${item.quantity * item.product.price}</td>
                    <td>
                        <form action="cart/remove" method="post">
                            <input type="hidden" name="productId" value="${item.product.id}" />
                            <button type="submit">Remove</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>Total: $${total}</p>
        <a href="checkout">Proceed to Checkout</a>
    </c:if>
    <c:if test="${empty cartItems}">
        <p>Your cart is empty.</p>
    </c:if>
    <a href="home">Continue Shopping</a>
</body>
</html>
