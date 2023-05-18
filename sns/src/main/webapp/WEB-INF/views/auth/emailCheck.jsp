<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
    <c:redirect url="/emailHashCheck">
        <c:param name="code" value="${param.code}"/>
    </c:redirect>
    // $.ajax({
    //   url : "/emailHashCheck",
    //   type : "post",
    //   data: {
    //     code: <c:out value="${param.code}"/>
    //   },
    //   success : function(obj){
    //      alert(obj);
    //   },
    //   error : function(){
    //     alert("오류");
    //   }
    // })



</script>
</head>
<body>

</body>
</html>