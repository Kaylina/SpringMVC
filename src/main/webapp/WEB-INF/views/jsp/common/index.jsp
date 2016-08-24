<%--
  Created by IntelliJ IDEA.
  User: jingyan
  Date: 2016/8/24
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="pageContext.jsp" %>
<%@include file="common.jsp" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Elixir</title>
    <link rel="stylesheet" type="text/css" href="${path}/rs/css/clock/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/rs/css/clock/default.css">
    <link href='http://fonts.useso.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" media="screen" href="${path}/rs/css/clock/main.css"/>
</head>
<body>

<div class="htmleaf-container">
    <div class="container" style="margin-top: 30px;">
        <div id="myclock"></div>
    </div>
</div>
<div style="margin-top: 10px;"><font size="4">http://localhost:8080/&nbsp;</font><input id="urls" type="text"
                                                                                        placeholder="请输入要测试请求.">&nbsp;
    <input type="button" value="请求" onClick="clickHandler()"></div>


<script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js" type="text/javascript"></script>
<script>window.jQuery || document.write('<script src="${path}/rs/js/clock/jquery-2.1.1.min.js"><\/script>')</script>
<script language="javascript" type="text/javascript" src="${path}/rs/js/clock/jquery.thooClock.js"></script>


<script language="javascript">
    /* 回车键 */
    $(function () {
        document.onkeydown = function (e) {
            var ev = document.all ? window.event : e;
            if (ev.keyCode == 13) {
                clickHandler();
            }
        }
    });

    function clickHandler() {
        var url = $('#urls').val();
        var reqUrl = "http://localhost:8080/" + url;
        window.open(reqUrl, '_blank');
        $("#urls").val("");//清空
    }

    var intVal, myclock;
    $(window).resize(function () {
        window.location.reload()
    });
    $(document).ready(function () {
        var audioElement = new Audio("");
        $('#myclock').thooClock({
            size: $(document).height() / 1.4,
            onAlarm: function () {
                $('#alarm1').show();
                alarmBackground(0);
                document.body.appendChild(audioElement);
                var canPlayType = audioElement.canPlayType("audio/ogg");
                if (canPlayType.match(/maybe|probably/i)) {
                    audioElement.src = 'alarm.ogg';
                } else {
                    audioElement.src = 'alarm.mp3';
                }
                audioElement.addEventListener('canplay', function () {
                    audioElement.loop = true;
                    audioElement.play();
                }, false);
            },
            showNumerals: true,
            brandText: 'Jingyan',
            brandText2: 'Watch',
            onEverySecond: function () {
                //callback that should be fired every second
            },
            //alarmTime:'15:10',
            offAlarm: function () {
                $('#alarm1').hide();
                audioElement.pause();
                clearTimeout(intVal);
                $('body').css('background-color', '#FCFCFC');
            }
        });

    });


    $('#turnOffAlarm').click(function () {
        $.fn.thooClock.clearAlarm();
    });


    $('#set').click(function () {
        var inp = $('#altime').val();
        $.fn.thooClock.setAlarm(inp);
    });


    function alarmBackground(y) {
        var color;
        if (y === 1) {
            color = '#CC0000';
            y = 0;
        }
        else {
            color = '#FCFCFC';
            y += 1;
        }
        $('body').css('background-color', color);
        intVal = setTimeout(function () {
            alarmBackground(y);
        }, 100);
    }
</script>
</body>
</html>
