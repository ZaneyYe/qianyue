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
      <title>活检测试</title>
      <script src="<%= request.getContextPath() %>/js/jquery-3.3.1.js"></script>
      <script src="<%= request.getContextPath() %>/js/jquery-1.11.2.min.js"></script>
      <script src="<%= request.getContextPath() %>/js/jquery.mobile-1.4.5.js"></script>
      <script src="https://open.95516.com/s/open/js/upsdk.js"></script>
      <link rel="stylesheet" href="<%= request.getContextPath() %>/js/jquery.mobile-1.4.5.css">
  </head>

  <body>
      参数：
       <%= request.getAttribute("appId") %>
       <input type="hidden" id="appId" value="<%= request.getAttribute("appId") %>">
       <input type="hidden" id="timestamp" value="<%= request.getAttribute("timestamp") %>">
       <input type="hidden" id="nonceStr" value="<%= request.getAttribute("nonceStr") %>">
       <input type="hidden" id="signature" value="<%= request.getAttribute("signature") %>">
       <br /><br />
       测试按钮：
       <input type="button" value="获取活检照片" id="photo"/><br /><br />
       <input type="button" value="设置标题" id="title"/><br /><br />
       <input type="button" value="扫描" id="saomiao"/><br /><br />
       <input type="button" value="选取照片" id="choseImg"/><br /><br />
  </body>

</html>
<script>
    upsdk.config({
        appId: $("#appId").val(), // 必填，接入方的唯一标识
        timestamp: $("#timestamp").val(), // 必填，生成签名的时间戳，从1970年1月1日00:00:00至今的秒数
        nonceStr: $("#nonceStr").val(), // 必填，生成签名的随机串
        signature: $("#signature").val(),// 必填，生成签名的摘要，采用sha256算法, 详见最后一章FAQ
        debug: true  // 开发阶段可打开此标记，云闪付)APP会将调试信息toast出来
    });

    //验证通过执行
    upsdk.ready(function () {
        alert("ok");
        console.log("upsdk config ok");

        $("#photo").click(function () {
            upsdk.readFaceImageData({
                bufferSize: '1024',
                fromOffset: '0',
                success:function(data){
                // 成功返回{data:’文件块的base64’,isFinished:’1’}
                // isFinished：0代表文件还有块未获取，1代表文件所有块已获取完毕
                    alert(data);
                },
                fail: function(err){
                // 失败回调{code:’失败码’, msg:’失败原因描述’}
                // {code:’00’, msg:’参数错误’}
                // {code:’01’, msg:’内部错误’}
                // {code:’07’, msg:’文件不存在’}
                    alert(err.msg);
                }
            });
        });

        //设置标题
        $("#title").click(function () {
            upsdk.setNavigationBarTitle({
                title: '试一下'
            });
        });

        //扫描
        $("#saomiao").click(function () {
            upsdk.scanQRCode({
                scanType: ["qrCode","barCode"],
                success: function(result){
                    alert("Scan result = " + result);
                }
          });
        });
        
//        $("#choseImg").click(function () {
//            upsdk.chooseImage({
//                maxWidth: ‘目标图片宽度, 默认500’, 可选
//            maxHeight: ‘目标图片高度, 默认1000’, 可选
//            sourceType: ‘1|2|3, 仅允许拍照|仅允许从手机相册中选图|拍照或从手机相册中选图都支持, 默认为3’，可选
//            success: function (data) {
//                if (data.base64) {
//                    // 目标图片采用base64编码.
//                }
//            }
//             });
//        });


    })


    //验证失败执行
    upsdk.error(function (err) {
        alert(err);
    })
</script>
