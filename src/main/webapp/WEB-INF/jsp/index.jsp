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
      <title>测试入口</title>
      <script src="<%= request.getContextPath() %>/js/jquery-3.3.1.js"></script>
      <script src="<%= request.getContextPath() %>/js/jquery-1.11.2.min.js"></script>
      <script src="<%= request.getContextPath() %>/js/jquery.mobile-1.4.5.js"></script>
      <link rel="stylesheet" href="<%= request.getContextPath() %>/js/jquery.mobile-1.4.5.css">
      <script src="https://open.95516.com/s/open/js/upsdk.js"></script>
	  <script src="<%= request.getContextPath() %>/js/clipboard.js"></script>
  </head>
  <body>
      code : <%= request.getParameter("code")%> <br /> <input type="hidden" id="code" value="<%= request.getParameter("code")%>">
      state : <%= request.getParameter("state") %><br />
      errmsg : <%= request.getParameter("errmsg") %><br />

      <input type="button" value="获取用户信息" id="getUserInfo" /> <br />


      <input type="button" value="link跳转" id="linkTo"/><br />

      <input type="button" value="小程序生成code" id="getCode" /><br />

      <input type="button" value="测试插件" id="chajian" /><br />

      <input type="button" value="分享" id="shareTest" /><br />

	  <button class="btn">复制</button>

	   <input type="button" value="关注小程序" id="collectCurrent" /><br />
  </body>

</html>
<script>
   // setTimeout(function(){
	upsdk.pluginReady(function(){
		console.log('123');
		upsdk.addConsole();
		upsdk.setNavigationBarTitle({
			title:"修改成功"
		})
	})
   // }, 1000)
    
    $("#getUserInfo").click(function () {
        var code = $("#code").val().replaceAll("\\+","%2B");
        $.ajax({
            url: "<%=basePath %>/getUserInfo.do",
            type: "post",
            data: {"code": code},
            success: function (res) {
                console.log(res);
            }
        });
	window.location.href='chsp://cityservice.95516.com';
    });
    


    $("#getCode").click(function () {
        
 	upsdk.appletAuth({
            success: function (data) {
               	 alert(data.code);
		 console.log("获取到的code ：" + JSON.stringify(data));
            },
            fail: function (error) {
                console.log("获取code失败" + JSON.stringify(error));
            }
        })
	/**
	upsdk.showSharePopup({
		title:"分享xxxx",
		desc:"描述yyyy",
		shareUrl:"https://www.baidu.com",
		picUrl:"http://paytest.95577.com.cn/unionpay/CloudFlashover/img/why_black@3x.png"
	})
	
	upsdk.openApplet({
		appId: "6cdc1348f9223726",
		fail:function(code){
			console.log(code);	
		}
	})
	**/
    })


	var clipboard = new ClipboardJS('.btn', {
        text: function() {
            return 'hello success';
        }
    });

    clipboard.on('success', function(e) {
        console.log(e);
    });

    clipboard.on('error', function(e) {
        console.log(e);
    });


   $("#collectCurrent").click(function () {
        upsdk.collectCurrentApplet({
	    success: function (data) {
				 console.log("获取到的code ：" + JSON.stringify(data));
            },
            fail: function (error) {
                console.log("获取code失败" + JSON.stringify(error));
            }
	})
    });


    
    $("#linkTo").click(function () {
        window.location.href = "https://usl.95516.com/applet?encryptAppId=6cdc1348f9223726";
		//window.close();
    });

    $("#shareTest").click(function () {
        upsdk.showSharePopup({
			title:"分享test",
			desc:"小程序分享测试",
			shareUrl: "http://47.98.179.66:8088/qianyue",
			picUrl:"http://paytest.95577.com.cn/unionpay/CloudFlashover/img/why_black@3x.png"
		})
    })

	

    $("#chajian").click(function () {
        window.location.href = "http://47.98.179.66:8088/qianyue/toBiopsy.do";
    })

</script>
