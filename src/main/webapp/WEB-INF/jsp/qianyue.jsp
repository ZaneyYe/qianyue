<%--
  Created by IntelliJ IDEA.
  User: yezhangyuan
  Date: 2018/12/20 0020
  Time: 下午 2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-1.11.2.min.js"></script>
<html>
<head>
    <title>HELLO</title>
</head>
<body>
code : <%=request.getParameter("code") %>
<input type="hidden" id="code" value="<%=request.getParameter("code") %>"/>
<br />

<input id="shenqingqianyue" type="button" value="申请签约" /> : (签约协议号)<label id="contractId"></label>

</body>
</html>
<script>
    var code = $("#code").val();

    $("#shenqingqianyue").click(function () {
        $.ajax({
            url: "/shenqingqianyue.do",
            type: "post",
            dataType: "json",
            data: {"code":code},
            success: function (result) {
                if(result.contractId){
                    $("#contractId").innerHTML = result.contractId;
                }else{
                    $("#contractId").innerHTML = "申请失败";
                }
            }
        })
    })




</script>
