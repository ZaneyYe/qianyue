<%--
  Created by IntelliJ IDEA.
  User: yezhangyuan
  Date: 2018/10/25 0025
  Time: 下午 4:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <!-- meta使用viewport以确保页面可自由缩放 -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>HELLO</title>
      <script src="<%= request.getContextPath() %>/js/jquery-3.3.1.js"></script>
      <script src="<%= request.getContextPath() %>/js/jquery-1.11.2.min.js"></script>
      <script src="<%= request.getContextPath() %>/js/jquery.mobile-1.4.5.js"></script>
      <link rel="stylesheet" href="<%= request.getContextPath() %>/js/jquery.mobile-1.4.5.css">
  </head>
  <body>
      code : <%= request.getParameter("code")%> <br /> <input type="hidden" id="code" value="<%= request.getParameter("code")%>>">
      state : <%= request.getParameter("state") %><br />
      errmsg : <%= request.getParameter("errmsg") %><br />

      <input type="button" value="签约免密" id="qianyue"/><br />

      <input type="button" value="生成code" id="ceshi" /><br />

      <input type="button" value="测试插件" id="chajian" /><br />

  </body>

</html>
<script>
    $("#qianyue").click(function () {
        console.log("1234");
        window.location.href = "https://open.95516.com/s/open/noPwd/html/open.html?appId=a5949221470c4059b9b0b45a90c81527" +
            "&redirectUri=http://47.98.179.66:8088/qianyue&responseType=code&scope=upapi_contract&state=qianyue";
    });

    $("#ceshi").click(function () {
        window.location.href = "https://open.95516.com/s/open/html/oauth.html?appId=d43714e0246a435e87037f80495d2c6d&redirectUri=http://47.98.179.66:8088/qianyue&responseType=code&scope=upapi_mobile&state=STATE";
    })


    $("#chajian").click(function () {
//        alert("");
        window.location.href = "http://47.98.179.66:8088/qianyue/toBiopsy.do";
    })




</script>
