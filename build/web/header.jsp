<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="Controller?action=home"><img id="banner" src="images/logo.jpg" alt="Tough Udder logo"></a>
<div id='menu'>
    <ul>
        <li><a href='#'>COMPANY PROFILE</a></li>
        <li><a href='Controller?action=events'>FIND AN EVENT</a></li>
        <!--
        <li><a href='#'>OBSTACLES</a></li>
        -->
        <c:if test="${account != null && account.login == true}">
            <li><a href='#'>MY CART</a></li>
            <li><a href='#'>MY ACCOUNT</a></li>
            <li><a href='Controller?action=logout'>LOG OUT</a></li>
        </c:if>
    </ul>
</div>
<hr>