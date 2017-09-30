<!DOCTYPE html>
<html>
<script src="" type="text/javascript"></script>
<head>
    <title>login</title>
    <style>
        form button{
            background: #F9F9F9; /* Set button background */
            border: 1px solid #DFDFDF; /* Small border around our submit button */
            padding: 8px; /* Add some more space around our button text */
        }

        input{
            font: normal 16px Georgia; /* Set font for our input fields */
            border: 1px solid #DFDFDF; /* Small border around our input field */
            padding: 8px; /* Add some more space around our text */
        }
    </style>
</head>
<body>
<div>
    <form action="/accounts/add.do" method="post">
        <span>账号：</span><input name="account" type="text" required onchange="validAccount(this)">&nbsp;<span id="MSG" style="color: red"></span>
        <br>
        <span>验证邮箱：</span><input name="email" type="email" required>
        <br>
        <span>密码：</span><input name="password" type="password" required>
        <br>
        <span>确认密码：</span><input name="password" type="password" required>
        <br>
        <img src="/verificationCode?wordType=character" onclick="changeImage(this)"/>
        <input name="verificationCode" type="text" maxlength="4" required >
        <br>
        <button type="submit">注册</button>&nbsp;<span style="color: red">${MSG!""}</span>
    </form>
</div>
</body>
<script>
    function changeImage(obj) {
        obj.src='/verificationCode?wordType=character&'+ new Date().getTime();
    }

    function validAccount(obj){
        var request = getRequest();
        request.open("get", "/accounts/validAccount.do?account="+obj.value);
        request.send(null);
        request.onreadystatechange = function(value){
            if(request.readyState == "4"){
                if(request.status == "200"){
                    if(request.responseText == "false"){
                        document.getElementById("MSG").innerHTML = "账号已被使用";
                    }else{
                        document.getElementById("MSG").innerHTML = "";
                    }
                }
            }
        }
    }
    function getRequest(){
        return new XMLHttpRequest();
    }
</script>
</html>