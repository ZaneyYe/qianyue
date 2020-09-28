<%--
  Created by IntelliJ IDEA.
  User: yezhangyuan
  Date: 2018/10/25 0025
  Time: 下午 4:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ path;
%>
<html>
  <head>
      <!-- meta使用viewport以确保页面可自由缩放 -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>微信测试入口</title>
      <script src="<%= request.getContextPath() %>/js/jquery-3.3.1.js"></script>
      <script src="<%= request.getContextPath() %>/js/jquery-1.11.2.min.js"></script>
      <script src="<%= request.getContextPath() %>/js/jquery.mobile-1.4.5.js"></script>
      <link rel="stylesheet" href="<%= request.getContextPath() %>/js/jquery.mobile-1.4.5.css">
      <script src="http://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>
  </head>
  <body>
     <input type="hidden" id="appId" value="<%= request.getAttribute("appId") %>">
     <input type="hidden" id="timestamp" value="<%= request.getAttribute("timestamp") %>">
     <input type="hidden" id="nonceStr" value="<%= request.getAttribute("nonceStr") %>">
     <input type="hidden" id="signature" value="<%= request.getAttribute("signature") %>">

     <p>hello</p>





  </body>

</html>
<script>
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印
        appId: $("#appId").val(), // 必填，公众号的唯一标识
        timestamp: $("#timestamp").val(), // 必填，生成签名的时间戳
        nonceStr: $("#nonceStr").val(), // 必填，生成签名的随机串
        signature: $("#signature").val(),// 必填，签名
        jsApiList: [], // 必填，需要使用的JS接口列表
        openTagList: [] // 可选，需要使用的开放标签列表，例如['wx-open-launch-app']
    });

    wx.ready(function () {
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中
        console.log("success");
        wx.getLocation({
            type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
            success: function (res) {
                var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                var speed = res.speed; // 速度，以米/每秒计
                var accuracy = res.accuracy; // 位置精度
                alert(latitude);
                alert(longitude);
            }
        });

    });

    wx.error(function (res) {
        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名
        console.log("error");
        alert("error");
        console.log(JSON.stringify(res));
    });


</script>
