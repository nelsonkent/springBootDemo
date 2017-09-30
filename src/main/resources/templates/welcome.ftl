<!DOCTYPE html>
<html lang="zh">
<head>
    <title>welcome</title>
</head>
<body style="">
<div style="width: 50%;margin: 0;">
Date: ${time?date}
<br>
Time: ${time?time}
<br>
Message: ${message}
</div>
<div>
    账号：${account}
    <br>
    用户组: ${groupMember}
    <br>
    <a href="/login">登录</a>
</div>
<div style="float: right">
    <img src="../picture_head.png">
</div>
</body>

</html>