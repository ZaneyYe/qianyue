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
	    <input type="button" value="关闭" id="closeApp" /><br />

       扫一扫：
       <input type="button" value="扫一扫" id="zhusao" /><br />

       被扫：
       <input type="button" value="被扫" id="beisao" /><br />

       <hr />

       预准备：
       <input type="button" value="跳转徽商银行预准备" id="prepareApp"/><br />

       打开小程序：
       <input type="button" value="打开小程序" id="openApp" /><br />


       url：<input type="text" value="输入打开的网址" id="url" /><br />
       <input type="button" value="新打开webview" id="creatWebView" /><br />

       数据存储：<br />
       key：<input type="text" value="" id="key" /><br />
       value: <input type="text" value="" id="val" /><br />
       <input type="button" value="数据存贮" id="saveData" /><br />

       key值: <input type="text" value="" id="qkey" /><br />
       <input type="button" value="获取" id="queryData" /><br />
       <input type="button" value="删除" id="deleteData" /><br />

       <input type="button" value="获取已存储数据key值" id="getAllKeysBtn" /><br />

       <input type="button" value="删除已存储数据key值" id="deleteAllKeysBtn" /><br />

       <input type="button" value="打开rn页" id="openRN" /><br />
       
       背景: <input type="text" value="" id="color" /><br />
       样式: <input type="text" value="" id="style" /><br />
       返回是否显示: <input type="text" value="" id="btnv" /><br />
       标题栏是否显示：	<input type="text" value="" id="titleVis" /><br />
       渐变色方向：	<input type="text" value="" id="gradientOrient" /><br />
       渐变色起始颜色：<input type="text" value="" id="startColor" /><br />
       渐变色结束颜色：<input type="text" value="" id="endColor" /><br />
       <input type="button" value="设置小程序导航栏" id="titleStyle" /><br />
       
       
       <input type="button" value="获取屏幕分辨率" id="getScreen" /><br />
       
       
        <input type="button" value="获取最近使用小程序" id="userAppletList" /><br />
        
        小程序id：<input type="text" value="" id="applets_id_del" /><br />
        <input type="button" value="删除最近使用小程序" id="deleteAppletList" /><br />
        
        关注小程序id:<input type="text" value="" id="applets_id_col" /><br />
        <input type="button" value="关注小程序" id="collectApplet" /><br />
        
        取消关注小程序id:<input type="text" value="" id="applets_id_cac" /><br />

        <input type="button" value="取消关注小程序" id="cancelCollectApplet" /><br />
        
        获取我的关注列表：
        <input type="button" value="获取我的关注列表" id="getAppletList" /><br />

        自定义渠道分享：
        <input type="button" value="自定义渠道分享" id="showSharePP" /><br />


        <a href="tel:18905565507">拨打电话</a><br />

				链接:<input type="text" value="" id="outLink" /><br />
				<input type="button" value="跳转" id="linkTo" /><br />
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
    	
    		$("#linkTo").click(function(){
    				var link = $("#outLink").val();
    				location.href = link;
    			})
    	
        console.log("hello");
	 			
        upsdk.addConsole();

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
//                title:  '什么鬼',
//                image: '',
                image: 'http://paytest.95577.com.cn/unionpay/CloudFlashover/img/why_black@3x.png',
                handler: function(){
                     // 用户点击标题按钮以后回调函数
                     alert("ok");
		     upsdk.showSharePopup({
                	title:"分享xxxx",
                	desc:"描述yyyy",
                	shareUrl:"https://www.baidu.com",
                	picUrl:"http://paytest.95577.com.cn/unionpay/CloudFlashover/img/why_black@3x.png"
       		       })

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

        //主扫
        $("#zhusao").click(function(){
            upsdk.scanQRCodeNew()
        })

        //被扫
        $("#beisao").click(function(){
            upsdk.qrCodePay()
        })

        //预准备
        $("#prepareApp").click(function(){
            upsdk.prepareApplet({
                appId : "6f2e617262261415",
                fail:function(code){
                    alert(code);
                }
            })
        })

        //打开小程序
        $("#openApp").click(function(){
            upsdk.openApplet({
                appId : "6f2e617262261415",
								param:"k1=v1&k2=v2",
                fail:function(code){
                    alert(code);
                }
            })
        })

        //webview打开
        $("#creatWebView").click(function () {
            var url = $("#url").val()
            upsdk.createWebView({
                url: url,  // webview容器中运行的目标页面地址
                isFinish:'0'  //是否关闭当前的窗口,'1':关闭，'0':不关闭
             })
        })


        //数据存储
        $("#saveData").click(function () {
            var key = $("#key").val();
            var val = $("#val").val()
            upsdk.saveData({
                key: key,
                value: val,
                success:function(result){
                  console.log(result.code)
                }
            })
        })

        //查询key值
        $("#queryData").click(function () {
            var qkey = $("#qkey").val();
            upsdk.queryData({
                key: qkey,
                success:function(result){
                    // result = {code:'',msg:'',key:'xxx',value:'xxx'}
                    console.log("查询 ："+  JSON.stringify(result));
                }
            });
        })

        //删除key
        $("#deleteData").click(function () {
            var qkey = $("#qkey").val();
            upsdk.deleteData({
                key:qkey,
                success:function(result){
                    console.log("删除 ：" + JSON.stringify(result));
                }
            });
        })

        //删除所有key值
        $("#deleteAllKeysBtn").click(function () {
            upsdk.deleteAllKeys({
                success:function(result){
                    console.log("删除所有 ：" + JSON.stringify(result));
                }
            })
        })
        
        //获取所有key值
        $("#getAllKeysBtn").click(function () {
            upsdk.getAllKeys({
                success:function(result){
                    console.log("获取所有 ：" + JSON.stringify(result));
                }
            })
        })

				//打开rn页
				$("#openRN").click(function(){
							upsdk.openRNPage({
								dest: "rncoupondetail",
								extraData: {
									couponId: "1320200528258446",
									updTs:"",
									prePage:"",
									billSt:"",
									billNum:""
								}
							})
				})
				
				//设置导航栏
				$("#titleStyle").click(function(){
					var color = $("#color").val();
					var costyle = $("#style").val();
					var btnv= $("#btnv").val();
					var titleVis=$("#titleVis").val();
					var gradientOrient=$("#gradientOrient").val();
					var startColor=$("#startColor").val();
					var endColor=$("#endColor").val();
					console.log("124");
					upsdk.setTitleStyle({
						navBackgroundColor: color,
						appletStyle:costyle,
						backBtnVisible:btnv,
						appletTitleBarVisible:titleVis,
						appletTitleGradientOrient:gradientOrient,
						appletTitleGradientStartColor:startColor,
						appletTitleGradientEndColor:endColor
					})
				})
				
				//获取屏幕分辨率
				$("#getScreen").click(function(){
						upsdk.getScreenParams({
								success:function(data){  
									// 成功回调 data 详细内容见下面说明
									 console.log("获取所有 ：" + JSON.stringify(data));
									 alert(JSON.stringify(data));
								}
						});
				})
				
				
				//获得最近使用
				$("#userAppletList").click(function(){
                        upsdk.recentlyUsedAppletList({
                                 success:function(data){
                                     console.log("获取所有最近使用 ：" + JSON.stringify(data));
                                 },
                                 fail:function(error){
                                 // 失败回调 error={"code":""}
                                 // ‘01’:用户未登录，’02’:其它错误
                                     console.log("获取最近使用失败" + JSON.stringify(error));
                                 }
                         })
				})

				
				//删除最近使用
				$("#deleteAppletList").click(function(){
                     var del_appid = $("#applets_id_del").val();
                     upsdk.deleteRecentlyUsedApplet({
                     appIdList:[del_appid],
                     success:function(data){
                             console.log("删除是否成功：" + JSON.stringify(data));
                     },
                     fail:function(error){
                           console.log("删除失败" + JSON.stringify(error));
                     }
                     })
				})
               
        
        //关注小程序
        $("#collectApplet").click(function () {
            var col_appid = $("#applets_id_col").val();
            upsdk.collectApplet({
                encryptAppId: col_appid,
                success:function(data){
                // 成功回调 data={"code":"00"}
                    alert("关注成功");
                    console.log("关注成功：" + JSON.stringify(data));
               },
              fail:function(error){
                  console.log("关注失败" + JSON.stringify(error));
              }
          })
        })

         //取消关注小程序
         $("#cancelCollectApplet").click(function () {
             var cac_col_appid = $("#applets_id_cac").val();
             upsdk.cancelCollectApplet({
                 encryptAppId: cac_col_appid,
                 success:function(data){
                     alert("取消关注成功");
                     console.log("取消关注成功：" + JSON.stringify(data));
                 },
                 fail:function(error){
                     console.log("取消关注失败" + JSON.stringify(error));
                 }
              });
         })


        //获取关注列表
       $("#getAppletList").click(function () {
           upsdk.getAppletCollectionList({
               success:function(data){
                   console.log("获取关注列表：" + JSON.stringify(data));
               },
               fail:function(error){
                   console.log("获取关注列表失败" + JSON.stringify(error));
               }
           });
       })
        
   					
        //自定义渠道分享
        $("#showSharePP").click(function(){
                upsdk.showShareMorePanel({
                    title: '银联云闪付随机立减大优惠～！',
                desc :"描述yyyy",
                shareUrl :"https://www.baidu.com",
                picUrl: "http://paytest.95577.com.cn/unionpay/CloudFlashover/img/why_black@3x.png",
                shareList: [{shareId:'3', shareType:'',shareData:{}}],
                fail: function(code){
                                    //code: ‘01’:对应渠道的应用未安装，例如微信未安装，或该渠道调用失败
                         console.log(code);
                      }
                });
        	}
        )
   
	})//config ready


    //验证失败执行
    upsdk.error(function (err) {
        alert(err);
    })

</script>
