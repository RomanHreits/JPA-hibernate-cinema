<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <title>Title</title>
</head>
<style>
    input:invalid {
        border: 2px dashed red;
    }

    input:valid {
        border: 2px solid black;
    }

    body {
        background-color: white;
    }

    h2 {
        color: white;
    }

    h3 {
        color: red
    }
</style>
<body>
<%--<a style="margin-left: 5%" href="${pageContext.request.contextPath}/registration" class="btn btn-success btn-lg" role="button">Registration</a>--%>
<h2 style="margin-left: 5%">Enter your login and password !!!</h2>
<h3 style="color: darkred">${errorMessage}</h3>
<div class="container">
    <form method="post" class="was-validated" action="${pageContext.request.contextPath}/login">
        <div class="form-group">
            <div class="form-group col-md-6">
                <label for="username">UserLogin:</label>
                <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Field cannot be empty</div>
            </div>
            <div class="form-group col-md-6">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Field cannot be empty</div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>
<br>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
</body>
</html>
