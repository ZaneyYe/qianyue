<%--
  Created by IntelliJ IDEA.
  User: yezhangyuan
  Date: 2018/10/25 0025
  Time: 下午 4:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery-1.11.2.min.js"></script>
<html>
  <head>
    <title>HELLO</title>
  </head>
  <body>
      code : <%= request.getParameter("code")%> <br /> <input type="hidden" id="code" value="<%= request.getParameter("code")%>>">
      state : <%= request.getParameter("state") %><br />
      errmsg : <%= request.getParameter("errmsg") %><br />

      <input id="shenqingqianyue" type="button" value="申请签约" /> : (签约协议号)<label id="contractId"></label>
  </body>
</html>
<script>
    var code = $("code").val();
    $("shenqingqianyue").click(function () {
        $.ajax({
            url: "/qianyue",
            type: "post",
            dataType: "json",
            data: {"code":code},
            success: function (result) {

            }
        })
    })
</script>
