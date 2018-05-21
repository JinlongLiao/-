<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>编辑文章</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="http://127.0.0.1:81/utf8-php/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="http://127.0.0.1:81/utf8-php/third-party/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="http://127.0.0.1:81/utf8-php/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="http://127.0.0.1:81/utf8-php/umeditor.min.js"></script>
    <script type="text/javascript" src="http://127.0.0.1:81/utf8-php/lang/zh-cn/zh-cn.js"></script>

    <?php
    header('Content-Type:text/html;charset=UTF-8');
    /**
     * Created by IntelliJ IDEA.
     * User: liaojl
     * Date: 2018/5/19
     * Time: 10:58
     */
    require_once '../../SqlHelper.php';
    require_once '../../util.php';
    $content = null;
    if (trim($_REQUEST['id']) != null) {
        $client = new \Eureka\SqlHelper();
        $sql = 'SELECT * FROM tb_content WHERE id=\'' . mysqli_real_escape_string($client->conn, trim($_REQUEST["id"])) . '\'';
        $result = $client->execute_dql2($sql);
        if (sizeof($result) <= 0) {
            echo "<script>alert('小朋友不乖哦(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ 根本没有这条记录●﹏● ●﹏● ●﹏● ●﹏●');</script>";
        } else {
            $content = $result[0];
            echo "<script>
    $(function () {
      $('#title').val('" . base64_decode($result[0]['title']) . "');
      $('.edui-body-container').html('" . base64_decode($result[0]['content']) . "');
    })
</script>";
        }
    } else {
        echo "<script>alert('小朋友不乖哦(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ');</script>";
    }
    ?>
    <style type="text/css">
        h1 {
            font-family: "微软雅黑";
            font-weight: normal;
        }

        .btn {
            display: inline-block;
            *display: inline;
            padding: 4px 12px;
            margin-bottom: 0;
            *margin-left: .3em;
            font-size: 14px;
            line-height: 20px;
            color: #333333;
            text-align: center;
            text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
            vertical-align: middle;
            cursor: pointer;
            background-color: #f5f5f5;
            *background-color: #e6e6e6;
            background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
            background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
            background-repeat: repeat-x;
            border: 1px solid #cccccc;
            *border: 0;
            border-color: #e6e6e6 #e6e6e6 #bfbfbf;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
            border-bottom-color: #b3b3b3;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#ffe6e6e6', GradientType=0);
            filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
            *zoom: 1;
            -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn:hover,
        .btn:focus,
        .btn:active,
        .btn.active,
        .btn.disabled,
        .btn[disabled] {
            color: #333333;
            background-color: #e6e6e6;
            *background-color: #d9d9d9;
        }

        .btn:active,
        .btn.active {
            background-color: #cccccc \9;
        }

        .btn:first-child {
            *margin-left: 0;
        }

        .btn:hover,
        .btn:focus {
            color: #333333;
            text-decoration: none;
            background-position: 0 -15px;
            -webkit-transition: background-position 0.1s linear;
            -moz-transition: background-position 0.1s linear;
            -o-transition: background-position 0.1s linear;
            transition: background-position 0.1s linear;
        }

        .btn:focus {
            outline: thin dotted #333;
            outline: 5px auto -webkit-focus-ring-color;
            outline-offset: -2px;
        }

        .btn.active,
        .btn:active {
            background-image: none;
            outline: 0;
            -webkit-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn.disabled,
        .btn[disabled] {
            cursor: default;
            background-image: none;
            opacity: 0.65;
            filter: alpha(opacity=65);
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
        }
    </style>
</head>
<body>
<h1>微服务PHP端 </h1>
来个标题呗
<input type="text" id="title"><br><br><br>
<!--style给定宽度可以影响编辑器的最终宽度-->
<DIV id="con">

    <textarea type="text/plain" id="myEditor" style="width:1000px;height:240px;">
	</textarea>

</DIV>

<div class="clear"></div>
<div id="btns">
    <table>
        <tr>
            <td>
                <!--<button class="btn" unselected="on" onclick="getAllHtml()">获得整个html的内容</button>&nbsp;-->
                <!--<button class="btn" onclick="getContent()">获得内容</button>&nbsp;-->
                <button class="btn" onclick="setContent()">写入内容</button>&nbsp;
                <button class="btn" onclick="setContent(true)">追加内容</button>&nbsp;
                <!--<button class="btn" onclick="getContentTxt()">获得纯文本</button>&nbsp;-->
                <!--<button class="btn" onclick="getPlainTxt()">获得带格式的纯文本</button>&nbsp;-->
                <!--<button class="btn" onclick="hasContent()">判断是否有内容</button>-->
            </td>
        </tr>
        <tr>
            <td>
                <!--<button class="btn" onclick="setFocus()">编辑器获得焦点</button>&nbsp;-->
                <!--<button class="btn" onmousedown="isFocus();return false;">编辑器是否获得焦点</button>&nbsp;-->
                <!--<button class="btn" onclick="doBlur()">编辑器取消焦点</button>&nbsp;-->
                <!--<button class="btn" onclick="insertHtml()">插入给定的内容</button>&nbsp;-->
                <!--<button class="btn" onclick="getContentTxt()">获得纯文本</button>&nbsp;-->
                <button class="btn" id="enable" onclick="setEnabled()">可以编辑</button>&nbsp;
                <button class="btn" onclick="setDisabled()">不可编辑</button>
            </td>
        </tr>
        <tr>
            <td>
                <button class="btn" onclick="UM.getEditor('myEditor').setHide()">隐藏编辑器</button>&nbsp;
                <button class="btn" onclick="UM.getEditor('myEditor').setShow()">显示编辑器</button>&nbsp;
                <!--<button class="btn" onclick="UM.getEditor('myEditor').setHeight(300)">设置编辑器的高度为300</button>&nbsp;-->
                <!--<button class="btn" onclick="UM.getEditor('myEditor').setWidth(1200)">设置编辑器的宽度为1200</button>-->
            </td>
        </tr>

    </table>
</div>
<!--<table>-->
<!--<tr>-->
<!--<td>-->
<!--<button class="btn" onclick="createEditor()"/>-->
<!--创建编辑器</button>-->
<!--<button class="btn" onclick="deleteEditor()"/>-->
<!--删除编辑器</button>-->
<!--</td>-->
<!--</tr>-->
<!--</table>-->

<div>
    <h3 id="focush2" STYLE="display: none"></h3>
</div>
</body>
</html>

<script type="text/javascript">
    var content = '<p><strong><span style="font-family: &#39;Microsoft JhengHei UI&#39;;color: rgb(255, 0, 0);letter-spacing: 0;font-size: 29pxfont-family:Microsoft JhengHei UI">老婆漂亮的程序员，鄙视老婆不漂亮的程序员。</span></strong><strong><span style="font-family: &#39;Microsoft JhengHei UI&#39;;color: rgb(255, 0, 0);letter-spacing: 0;font-size: 29px"><br/></span></strong><strong><span style="font-family: &#39;Microsoft JhengHei UI&#39;;color: rgb(255, 0, 0);letter-spacing: 0;font-size: 29pxfont-family:Microsoft JhengHei UI">有老婆的程序员，鄙视没有老婆的程序员。</span></strong><strong><span style="font-family: &#39;Microsoft JhengHei UI&#39;;color: rgb(255, 0, 0);letter-spacing: 0;font-size: 29px"><br/></span></strong><strong><span style="font-family: &#39;Microsoft JhengHei UI&#39;;color: rgb(255, 0, 0);letter-spacing: 0;font-size: 29pxfont-family:Microsoft JhengHei UI">没有老婆有女朋友的程序员，鄙视单身程序狗。</span></strong><strong><span style="font-family: &#39;Microsoft JhengHei UI&#39;;color: rgb(255, 0, 0);letter-spacing: 0;font-size: 29px"><br/></span></strong><strong><span style="font-family: &#39;Microsoft JhengHei UI&#39;;color: rgb(255, 0, 0);letter-spacing: 0;font-size: 29pxfont-family:Microsoft JhengHei UI">在单身狗之间，才有语言、编辑器和操作系统的互相鄙视。</span></strong></p><p> <br/></p>';
    //实例化编辑器
    var um = UM.getEditor('myEditor');
    um.setContent(content, true);
    um.addListener('blur', function () {
        $('#focush2').html('编辑器失去焦点了')
    });
    um.addListener('focus', function () {
        $('#focush2').html('')
    });

    //按钮的操作
    function insertHtml() {
        var value = prompt('插入html代码', '');
        um.execCommand('insertHtml', value)
    }

    function isFocus() {
        alert(um.isFocus())
    }

    function doBlur() {
        um.blur()
    }

    function createEditor() {
        enableBtn();
        um = UM.getEditor('myEditor');
    }

    function getAllHtml() {
        //	alert(UM.getEditor('myEditor').getAllHtml())
        return UM.getEditor('myEditor').getAllHtml();
    }

    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UM.getEditor('myEditor').getContent());
        alert(arr.join("\n"));
    }

    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UM.getEditor('myEditor').getPlainTxt());
        alert(arr.join('\n'))
    }

    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用umeditor')方法可以设置编辑器的内容");

        var html = getAllHtml();
        var title = $('#title').val();
        // console.log(getAllHtml());
        if (title === null || (title).length == 0) {
            alert("大佬给个标题呗→_→");
            return;
        }
        var content = UM.getEditor('myEditor').getContent();
        jQuery.ajax({
            url: '/utf8-php/php/setContent.php',
            type: 'POST',
            dataType: 'json',
            <?php
            if ($content == null) {
                echo "data: {'content': content,'html':html,title': title},";
            } else {
                echo "data: {'content': content,'html':html,'title': title,'id':{$content['id']}},";
            }
            ?>
            complete: function (xhr, textStatus) {
                //called when complete
            },
            success: function (data, textStatus, xhr) {
                //called when successful
                if (data.RESULT) {
                    alert("上传成功！d=====(￣▽￣*)b ")
                    location.reload();
                }
                ;
            },
            error: function (xhr, textStatus, errorThrown) {
                //called when there is an error
                console.log(errorThrown)
            }
        });

        UM.getEditor('myEditor').setContent(content, isAppendTo);
    }

    function setDisabled() {
        UM.getEditor('myEditor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UM.getEditor('myEditor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UM.getEditor('myEditor').selection.getRange();
        range.select();
        var txt = UM.getEditor('myEditor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UM.getEditor('myEditor').getContentTxt());
        alert(arr.join("\n"));
    }

    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UM.getEditor('myEditor').hasContents());
        alert(arr.join("\n"));
        return UM.getEditor('myEditor').hasContents();
    }

    function setFocus() {
        UM.getEditor('myEditor').focus();
    }

    function deleteEditor() {
        disableBtn();
        UM.getEditor('myEditor').destroy();
    }

    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }

    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            domUtils.removeAttributes(btn, ["disabled"]);
        }
    }
</script>
