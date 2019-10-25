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
       <%=basePath %>
       <input type="hidden" id="appId" value="<%= request.getAttribute("appId") %>">
       <input type="hidden" id="timestamp" value="<%= request.getAttribute("timestamp") %>">
       <input type="hidden" id="nonceStr" value="<%= request.getAttribute("nonceStr") %>">
       <input type="hidden" id="signature" value="<%= request.getAttribute("signature") %>">
       <br /><br />
       测试按钮：<br  />
       <input type="button" value="获取活检照片" id="photo"/><br /><br />
       活检照片:
       <img src="" id="huojianpic" /><br /><br />
       <input type="button" value="获取活检视频" id="vedio"/><br /><br />
       <vedio id="faceVideoSrc"></vedio><br /><br />


       <input type="button" value="设置标题" id="title"/><br /><br />
       <input type="button" value="扫描" id="saomiao"/><br /><br />
       <input type="button" value="选取照片" id="choseImg"/><br /><br />
       选取的照片:
       <img src="" id="choseImgShow" /><br /><br />
       <input type="button" value="获取地理位置" id="getLocationGps"/><br /><br />

       <input type="button" value="设置右边栏" id="setRightBtn"/><br /><br />

        地图插件：<br />
        起点纬度：<input type="text" value="起点纬度" id="sLat" /><br />
        起点经度：<input type="text" value="起点经度" id="sLon" /><br />
        起点名称: <input type="text" value="起点名称" id="sName" /><br />
        终点纬度: <input type="text" value="终点纬度" id="dLat" /><br />
        终点经度: <input type="text" value="终点经度" id="dLon" /><br />
        终点名称: <input type="text" value="终点名称" id="dName" /><br />
        <input type="button" value="开始导航" id="naviBtn"/><br /><br />

        屏幕亮度值：<br />
        <input type="text" value="屏幕亮度值" id="brightness" /><br />
        <input type="button" value="调节亮度" id="brightBtn"/><br /><br />
        <input type="button" value="获取当前亮度" id="getBrightBtn"/><br /><br />

        截屏通知：<br />
        <input type="button" value="打开屏幕截图权限（仅限android使用）" id="openScreenShot"/><br /><br />
        <input type="button" value="监听屏幕截屏（仅限ios使用）" id="monitorBtn"/><br /><br />
        <input type="button" value="移除监听屏幕截屏（仅限ios使用）" id="removeBtn"/><br /><br />

        一键绑卡：<br />
        sn：<input type="text" value="sn" id="snValue" /><br />
        <input type="button" value="一键绑卡" id="oneBang" /><br />

	    关闭：
	    <input type="button" value="关闭" id="closeApp" />


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

	
    $("#closeApp").click(function(){
	   upsdk.closeWebApp();
    })

    //验证通过执行
    upsdk.ready(function () {
        //活检照片
        $("#photo").click(function () {
            upsdk.readFaceImageData({
                bufferSize: '1024',
                fromOffset: '0',
                success:function(data){
                // 成功返回{data:’文件块的base64’,isFinished:’1’}
                // isFinished：0代表文件还有块未获取，1代表文件所有块已获取完毕
                    $.ajax({
                        url: "<%=basePath %>/getPic.do",
                        type: "post",
                        data: {"data": data.data},
                        success: function (res) {
                            console.log(res);
                            $("#huojianpic").attr("width","100%");
                            $("#huojianpic").attr("src",res);
                        }
                    });
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
        
        $("#vedio").click(function () {
            upsdk.readFaceVideoData({
                success:function(data){
                // 成功返回{data:’文件块的base64’,isFinished:’1’}
                // isFinished：0代表文件还有块未获取，1代表文件所有块已获取完毕
                    alert(data.data);
                    $("#faceVideoSrc").attr('src', 'data:video/mp4;base64,' + data.data);
                    $.ajax({
                        url: "<%=basePath %>/saveVideo.do",
                        type: "post",
                        data: {"data": data.data},
                        success: function (res) {
                            console.log(res);
                        }
                    });
                },
                fail: function(err){
                // 失败回调{code:’失败码’, msg:’失败原因描述’}
                // {code:’00’, msg:’参数错误’}
                // {code:’01’, msg:’内部错误’}
                // {code:’07’, msg:’文件不存在’}
                    alert(JSON.stringify(err));
               }
           });
        })



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
		    upsdk.pay({
			tn: result,
			success: function(){
				alert("ok")
			},
			fail:function(err){
				alert("fail")
			}	
   		    })
                }
          });
        });

        //选择照片
        $("#choseImg").click(function () {
            upsdk.chooseImage({
                maxWidth: '500',
                maxHeight: '1000',
                sourceType: '1|2|3',
                success: function (data) {
                    if (data.base64) {
                        $.ajax({
                            url: "<%=basePath %>/getPic.do",
                            type: "post",
                            data: {"data": data.base64},
                            success: function (res) {
                                console.log(res);
                                $("#choseImgShow").attr("width","100%");
                                $("#choseImgShow").attr("src",res);
                            },
                            fail: function (res) {
                                alert(res);
                            }
                        });
                    }
                }
            });
        });

        //获取经纬度
        $("#getLocationGps").click(function () {
            upsdk.getLocationGps({
                success: function (data) {
                    if(typeof data == 'string'){
                        var obj = JSON.parse(data);
                        alert("GPS \n lat:" + obj.latitude + ";\n  lon : " + obj.longitude);
                    } else {
                        alert("GPS \n lat:" + data.latitude + ";\n lon : " + data.longitude);
                    }
                },
                fail: function () {
                    alert("get gps failed")
                }
            });
        })

        //设置右边栏
        $("#setRightBtn").click(function () {
            upsdk.setNavigationBarRightButton({
                title:  '什么鬼',
                image: '',
                handler: function(){
                     // 用户点击标题按钮以后回调函数
                     alert("该吃药了");
                }
            });
        })



        //地图导航
        $("#naviBtn").click(function () {
            var sLat = $("#sLat").val();
            var sLon = $("#sLon").val();
            var sName = $("#sName").val();
            var dLat = $("#dLat").val();
            var dLon = $("#dLon").val();
            var dName = $("#dName").val();

            upsdk.navi({
                sLat: sLat,     // 起点纬度
                sLon: sLon,   // 起点经度
                sName: sName,  // 起点名称
                dLat: dLat,    // 终点维度
                dLon: dLon,   // 终点经度
                dName: dName,      // 终点名称
                success:function(){  // 成功回调
                    alert("导航成功");
                },
                fail: function(msg){
                    alert("导航失败");
                }
            })
        })

        //设置屏幕亮度
        $("#brightBtn").click(function () {
            var brightNess = $("#brightness").val();
            if(brightNess > 1){
                alert("设置值在0-1之间");
                return;
            }
            upsdk.setScreenBrightness({
                brightness: brightNess,     // 屏幕亮度值，范围取值0-1。精确到小数点后一位
                success:function(data){
                    alert("调节屏幕亮度成功");
                },
                fail: function(msg) {
                    alert("调节屏幕亮度失败");
                }
            })
        })

        //获取屏幕亮度
        $("#getBrightBtn").click(function () {
            upsdk.getScreenBrightness({
                success:function(data){
                    alert(data.brightness);
                },
                fail: function(msg){
                }
            })
        })

        //安卓打开截屏权限
        $("#openScreenShot").click(function () {
            upsdk.changeScreenShot({
                switch: true,     // true表示禁止截屏，false表示允许截屏
                success: function () {
                    alert("开启成功")
                },
                fail: function (msg) {
                    alert(msg)
                }
            })
        })

        //监听截屏通知
        $("#monitorBtn").click(function () {
            upsdk.monitorScreenShot({
                success:function(){
                    // 成功回调，表明用户已经进行了截屏
                    alert("截屏成功了，ok")
                }
            });
        })

        //移除截屏通知
        $("#removeBtn").click(function () {
            upsdk.removeScreenShot({
                success:function(){
                    // 成功回调，表明用户已经移除了截屏
                    alert("移除截屏通知，ok")
                }
            });
        })

        //一键绑卡
        $("#oneBang").click(function () {
             var sn = $("#snValue").val();
             console.log(sn);
             window.location.href = "upwallet://quickbindcard?sn="+sn;
        })

        


    })//config ready


    //验证失败执行
    upsdk.error(function (err) {
        alert(err);
    })

</script>
